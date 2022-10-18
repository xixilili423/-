<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>文件上传</title>
</head>

<body>
<form action="user.let?type=file" method="post"  enctype="multipart/form-data">
    <input type="file" name="myfile" accept=".csv">
    <input type="submit" value="上传文件" >
</form></body>
</html>
