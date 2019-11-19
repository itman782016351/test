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
<a href="">sina</a>
<div>c page</div>
<script type="text/javascript">
    console.log(document.getElementById("iframe"));
    console.log(window.parent.document.getElementById("iframe"));
    alert($("#iframe", window.parent.document).html());
</script>
</body>
</html>