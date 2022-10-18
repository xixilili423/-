<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords"  content = "图书 java jsp"/>
    <meta http-equiv="author" content="phenix"/>
    <script src="Js/prototype.lite.js" type="text/javascript"></script>
    <script src="Js/moo.fx.js" type="text/javascript"></script>
    <script src="Js/moo.fx.pack.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="Style/skin.css" />
    <script type="text/javascript">
        window.onload = function () {
            var contents = document.getElementsByClassName('content');
            var toggles = document.getElementsByClassName('type');

            var myAccordion = new fx.Accordion(
            toggles, contents, {opacity: true, duration: 400}
            );
            myAccordion.showThisHideOpen(contents[0]);
            for(var i=0; i<document .getElementsByTagName("a").length; i++){
                var dl_a = document.getElementsByTagName("a")[i];
                    dl_a.onfocus=function(){
                        this.blur();
                    }
            }
        }
    </script>
</head>

<body>
    <table width="100%" height="200" border="0" cellpadding="0" cellspacing="0" >
        <tr>
            <td width="182" valign="top">
                <div id="container">
                    <h1 class="type"><a href="javascript:void(0)">文件选择</a></h1>
                    <div class="content">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><img src="Images/menu_top_line.gif" width="182" height="5" /></td>
                            </tr>
                        </table>
                        <ul class="RM">
                            <li><a href="file.jsp" target="main">上传文件</a></li>
                        </ul>
                    </div>
                    <h1 class="type"><a href="javascript:void(0)">个人中心</a></h1>
                    <div class="content">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><img src="Images/menu_top_line.gif" width="182" height="5" /></td>
                            </tr>
                        </table>
                        <ul class="RM">
                            <li><a href="set_pwd.jsp" target="main">修改密码</a></li>

                        </ul>
                    </div>

                </div>
            </td>
        </tr>
    </table>
</body>
</html>
