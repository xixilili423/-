package com.example.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wniemiec.util.data.Pair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class StationFlowController
{

    @Autowired
    private StationFlowService stationFlowService;

    @Autowired
    private StationDetailService stationDetailService;

    @Autowired
    private StationDetailMapper stationDetailMapper;

    @Autowired
    private StationFlowMapper stationFlowMapper;

    @Autowired
    private CongestionResultMapper congestionResultMapper;

    // 传入线路名称，返回当前时间该线路上所有站点的经纬度和入站量，出站量等信息
    // 有time参数版,传进来的time格式为05:10，肯定为整数
    @GetMapping( value = {"/StationByline/{lineName}/{time}", "/StationByline/{lineName}"})
    @ResponseBody
    public Result<?> getStationInformationByLineName(@PathVariable(value = "lineName") Integer lineName, @PathVariable(value = "time", required = false) String time)
    {
        if (time != null)
        {
            time = "2015/04/29 " + time + ":00";
        }
        else
        {
            /* 获得当前时间 */
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            time = dateFormat.format(date); // 当前时间

            /* 处理当前时间 */
            TimeUtil timeUtil = new TimeUtil();
            timeUtil.setTime(time);
            timeUtil.toApproachTime();
            time = timeUtil.getTime();
        }

        List<StationInformation> stationInfoInLineByTime = stationFlowService.getStationInfoInLineByTime(lineName, time);

        return Result.success(stationInfoInLineByTime);
    }


    // 接受前端请求，返回该天所有线路的所有时间点的客运量
    @GetMapping("/AllStationsPFlowDay")
    @ResponseBody
    public Result<?> getAllStationsPFlowAllDay()
    {
        /* 获得当前时间 */
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = dateFormat.format(date); // 当前时间

        /* 处理当前时间 */
        TimeUtil timeUtil = new TimeUtil();
        timeUtil.setTime(time);
        timeUtil.toApproachTime();
        time = timeUtil.getTime();

        List<Integer> linesNameList = stationDetailService.selectLinesName();    // 所有线路的名称
        List<LineInformationAllTime> lineInformationAllTimeList = new ArrayList<>();    // 最后返回的链表，存储结构体

        for (Integer lineName : linesNameList)
        {
            LineInformationAllTime lineInformationAllTime = new LineInformationAllTime();

            lineInformationAllTime.setLineName(lineName);   // 设置线路名
            lineInformationAllTime.setDate(time);   // 设置时间
            lineInformationAllTime.setInNumAllTime(stationFlowService.getLineInnumAllTime(lineName, time));  // 得到线路该天所有时间点的客运量

            lineInformationAllTimeList.add(lineInformationAllTime); // 添加到链表中
        }

        return Result.success(lineInformationAllTimeList);
    }

    @GetMapping(value = {"/getStationInNumRank", "/getStationInNumRank/{time}"})
    @ResponseBody
    public Result<?> getStationInNumRank(@PathVariable(value = "time", required = false) String time)
    {
        if (time != null)
        {
            time = "2015/04/29 " + time + ":00";
        }
        else
        {
            /* 获得当前时间 */
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            time = dateFormat.format(date); // 当前时间

            /* 处理当前时间 */
            TimeUtil timeUtil = new TimeUtil();
            timeUtil.setTime(time);
            timeUtil.toApproachTime();
            time = timeUtil.getTime();
        }

        List<StationInformation> stationInNumRank = stationFlowService.getStationInNumRank(time, 15);
        return Result.success(stationInNumRank);
    }

    @GetMapping(value = "/getLineInNumByID/{lineID}")
    @ResponseBody
    public Result<?> getLineInNumByID(@PathVariable("lineID") Integer lineID)
    {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = dateFormat.format(date); // 当前时间
        String HMS = time.substring(11,19);
        time="2015/04/29 "+ HMS;

        List<Pair<String, Integer>> lineInnumAllTime = stationFlowService.getLineInnumAllTime(lineID, time);

        return Result.success(lineInnumAllTime);
    }

    // 传入站点id，得到该站点在一整天所有时间点的入站量和出站量
    @GetMapping("/getStationInOutNumByID/{stationID}")
    @ResponseBody
    public Result<?> getStationInOutNumByID(@PathVariable("stationID") Integer stationID)
    {
        /* 处理时间 */
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = dateFormat.format(date); // 当前时间
        String HMS = time.substring(11,19);
        time="2015/04/29 "+ HMS;

        return Result.success(stationFlowService.getStationInOutNumAllTime(stationID, time));
    }

    // 传入站点名，对站点名进行处理后返回该站点在该时刻的相关信息
    @GetMapping("/getStationInfoByName/{stationName}")
    @ResponseBody
    public Result<?> getStationInfoByName(@PathVariable("stationName") String stationName)
    {
        StationInformation stationInformation  = new StationInformation();

        /* 获得当前时间 */
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time = dateFormat.format(date); // 当前时间
        TimeUtil timeUtil = new TimeUtil();
        timeUtil.setTime(time);
        timeUtil.toApproachTime();
        time = timeUtil.getTime();

        /* 处理站点名 */
        int length = stationName.length();
        stationName = stationName.substring(0, length - 5);

        /* 根据站点名检索站点id */
        QueryWrapper<StationDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stationName", stationName);
        List<StationDetail> stationDetails = stationDetailMapper.selectList(queryWrapper);
        Integer stationID = stationDetails.get(0).getStationid();

        /* 根据站点id检索站点相关信息 */
        StationFlow stationFlow = stationFlowMapper.selectOne(new QueryWrapper<StationFlow>().eq("stationID", stationID).eq("time", time));
        QueryWrapper<CongestionResult> congestionResultQueryWrapper = new QueryWrapper<>();
        congestionResultQueryWrapper.eq("stationID", stationID).eq("time", time);
        CongestionResult congestionResult = congestionResultMapper.selectOne(congestionResultQueryWrapper);
        StationDetail stationDetail = stationDetailMapper.selectOne(new QueryWrapper<StationDetail>().eq("stationID", stationID));

        stationInformation.setLongitude(stationDetail.getLongitude());
        stationInformation.setLatitude(stationDetail.getLatitude());
        stationInformation.setStationID(stationID);
        stationInformation.setName(stationName);
        stationInformation.setTime(time);
        stationInformation.setInNum(stationFlow.getInnum());
        stationInformation.setOutNum(stationFlow.getOutnum());
        stationInformation.setCongestion(congestionResult.getCongestionnormalization());

        return Result.success(stationInformation);
    }
}