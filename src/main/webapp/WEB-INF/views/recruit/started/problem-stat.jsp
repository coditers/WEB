<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/icon/0630_favicon_beige.ico">
    <title>report: by problems</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.7/css/materialize.min.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialize-custom.css"
          media="screen,projection"/>
</head>

<body class="orange lighten-5 flexbody">
<jsp:include page="/WEB-INF/views/include/header_light.jsp"></jsp:include>
<div class="section no-pad-bot" id="index-banner">
    <br>
    <br>
    <div class="row">
        <div class="col s8 offset-s2">
            <div class="card-panel white">
                <div class="row">
                    <div class="col s8 offset-s2">
                        <div class="row center">
                            <h3>${recruitVo.title}</h3>
                        </div>
                        <br>
                        <div class="row">
                            <div class="row">
                                <h5 class="grey-text">Report - sorted by problems</h5>
                            </div>

                            <table>
                                <thead>
                                <tr>
                                    <th class="center-align">Problem</th>
                                    <th class="center-align">Average</th>
                                    <th class="center-align">Standard deviation</th>
                                    <th class="center-align">Max</th>
                                    <th class="center-align">Min</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${problemStatList}" var="vo">
                                    <tr>
                                        <td class="center-align">${vo.problemName}</td>
                                        <td class="center-align">${vo.avg}</td>
                                        <td class="center-align">${vo.stdDev}</td>
                                        <td class="center-align">${vo.max}</td>
                                        <td class="center-align">${vo.min}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <br>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/materialize/js/materialize.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/List.js"></script>
</body>
</html>