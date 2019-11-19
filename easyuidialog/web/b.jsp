<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>test easy ui</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <script type="text/javascript" src="easyui/jquery.min.js">
    </script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js">
    </script>
</head>
<body>
<a href="">baidu</a>
<div>child page</div>
<script type="text/javascript">
    var doc = window.parent.document;
    console.log(doc);
    alert(doc.getElementById("dd").innerHTML);
    alert(doc.getElementById("cc").getAttribute("value"));
    alert($("#cc").val());
</script>
</body>
</html>