<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GameSolution</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="main.js"></script>
</head>
<body>
<header class="display-4 margin-10">Map Create</header>
<form name="regionMap" method="post">
    <table class="margin-10">
        <#list 1..10 as x>
            <tr>
                <#list 1..10 as y>
                    <td class="map-block">
                        <input class="hidden" value="${regionMap.status[x][y]}" id="status[${x}][${y}]" name="status[${x}][${y}]">
                    </td>
                </#list>
            </tr>
        </#list>
    </table>
    <input type="submit" value="DOIT" class="btn btn-outline-secondary margin-10">
    <select id="format-select-height" class="btn btn-outline-secondary margin-10">
        <#list 1..10 as x>
            <option value="${x}">${x}</option>
        </#list>
    </select>
    <select id="format-select-width" class="btn btn-outline-secondary margin-10">
        <#list 1..10 as x>
            <option value="${x}">${x}</option>
        </#list>
    </select>
</form>
<table class="margin-10">
    <#list 1..10 as x>
        <tr>
            <#list 1..10 as y>
                <td class="map-block-solution ${breakWallMap[x][y].rightBreak ? then('right-border-break', '')} ${breakWallMap[x][y].bottomBreak ? then('bottom-border-break', '')}">
                    <input class="hidden" value="${solutionMap.status[x][y]}">
                </td>
            </#list>
        </tr>
    </#list>
</table>
</body>
</html>
