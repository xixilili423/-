<%--
  Created by IntelliJ IDEA.
  User: ojhgg
  Date: 2022/10/16
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>upload</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body>

<form id="fmLogin" method="post" action="user.let?type=file">
    <input type="file" name="file"/>
    <input type="submit"  value="提交"/>
</form>
</body>
</html>

