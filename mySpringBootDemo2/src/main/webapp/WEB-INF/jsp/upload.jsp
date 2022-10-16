<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form method="post" action="./request" enctype="multipart/form-data">
    <input type="file" name="file" value="请选择上传的文件"><br>
    <input type="submit" value="上传">
</form>
</body>
</html>
