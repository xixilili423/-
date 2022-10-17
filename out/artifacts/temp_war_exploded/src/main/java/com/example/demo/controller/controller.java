package com.example.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.Result;
import com.example.demo.pojo.user;
import com.example.demo.service.loginimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wniemiec.util.data.Pair;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user.let")
public class controller
{
    @Autowired
    loginimpl userBiz;
    @Autowired
    user US;


    // 传入线路名称，返回当前时间该线路上所有站点的经纬度和入站量，出站量等信息
    // 有time参数版,传进来的time格式为05:10，肯定为整数
    @GetMapping( value = {"/login.html/{name}/{pwd}"})
    @ResponseBody
    public Result<?> log(@PathVariable(value = "name") String id, @PathVariable(value = "pwd", required = false) String passwprd)
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



}