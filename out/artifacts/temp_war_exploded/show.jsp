<%--
  Created by IntelliJ IDEA.
  User: ojhgg
  Date: 2022/7/12
  Time: 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>轨迹回放</title>

    <!-- 引入jquery.js -->
    <script src="JS/jquery-3.3.1.min.js"></script>


    <link rel="stylesheet" href="https://a.amap.com/jsapi_demos/static/demo-center/css/demo-center.css"/>
    <style>
        html, body, #container {
            height: 100%;
            width: 100%;
        }

        .input-card .btn{
            margin-right: 1.2rem;
            width: 9rem;
        }

        .input-card .btn:last-child{
            margin-right: 0;
        }
    </style>
</head>
<body>
<div id="container"></div>
<div class="input-card">
    <h4>轨迹回放控制</h4>
    <div class="input-item">
        <input type="button" class="btn" value="开始动画" id="start" onclick="startAnimation()"/>
        <input type="button" class="btn" value="暂停动画" id="pause" onclick="pauseAnimation()"/>
    </div>
    <div class="input-item">
        <input type="button" class="btn" value="继续动画" id="resume" onclick="resumeAnimation()"/>
        <input type="button" class="btn" value="停止动画" id="stop" onclick="stopAnimation()"/>
    </div>
</div>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=2.0&key=94d1069d6b6c1f6d76b5126005fe1616"></script>
<script>
    var nodeLine = new Array();

    $.ajax({
        type:"post",
        async:true,
        url:"showServlet.let?type=show",
        data:{},
        dataType:"json",

        success:function(result){
            if(result){
                console.log(result);

                for(var i=0;i<result.length;i++){
                    var node=new Array();
                    node.push(result[i].xsta);
                    node.push(result[i].ysta);
                    nodeLine.push(node);
                }
            }
        }
    })

    // JSAPI2.0 使用覆盖物动画必须先加载动画插件
    AMap.plugin('AMap.MoveAnimation', function(){

        var marker, lineArr = nodeLine;

        console.log(lineArr);
        console.log(nodeLine[0]);
        console.log(nodeLine);
        var map = new AMap.Map("container", {
            resizeEnable: true,
            center:nodeLine[0],
            zoom: 17,
            viewMode: '3D',
        });

        marker = new AMap.Marker({
            map: map,
            position: nodeLine[0],
            icon: "https://a.amap.com/jsapi_demos/static/demo-center-v2/car.png",
            offset: new AMap.Pixel(-13, -26),
        });

        // 绘制轨迹
        var polyline = new AMap.Polyline({
            map: map,
            path: lineArr,
            showDir:true,
            strokeColor: "#28F",  //线颜色
            // strokeOpacity: 1,     //线透明度
            strokeWeight: 6,      //线宽
            // strokeStyle: "solid"  //线样式
        });

        var passedPolyline = new AMap.Polyline({
            map: map,
            strokeColor: "#AF5",  //线颜色
            strokeWeight: 6,      //线宽
        });


        marker.on('moving', function (e) {
            passedPolyline.setPath(e.passedPath);
            map.setCenter(e.target.getPosition(),true)
        });

        map.setFitView();

        window.startAnimation = function startAnimation () {
            marker.moveAlong(lineArr, {
                // 每一段的时长
                duration: 2000,//可根据实际采集时间间隔设置
                // JSAPI2.0 是否延道路自动设置角度在 moveAlong 里设置
                autoRotation: true,
            });
        };

        window.pauseAnimation = function () {
            marker.pauseMove();
        };

        window.resumeAnimation = function () {
            marker.resumeMove();
        };

        window.stopAnimation = function () {
            marker.stopMove();
        };
    });
</script>
</body>
</html>