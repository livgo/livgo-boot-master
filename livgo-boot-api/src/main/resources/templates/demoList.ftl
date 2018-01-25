<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Demo</title>
</head>
<body>
<h1 style="text-align: center">Demo List</h1>
<#list demoList as demo>
${demo.name} = ${demo.age}<br>
</#list>
</body>

</html>