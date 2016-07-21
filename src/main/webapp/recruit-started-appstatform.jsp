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
    <title>report: by applicants</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/materialize/css/materialize.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/materialize/css/materialize-custom.css" media="screen,projection"/>
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
                                <h5 class="grey-text">Report - sorted by applicants</h5>
                            </div>

                            <table>
                                <thead>
                                <tr>
                                    <th data-field="applicant name">Name</th>

                                    <!-- todo 문제 이름 뿌려줄 것 -->
                                    <!--
                                    <c:forEach items = "${problemInfoList}" var = "vo">
                                    <th data-field="problem">${vo.name}</th>
                                    </c:forEach>-->
                                    <th>prob1</th>
                                    <th>prob2</th>
                                    <th>prob3</th>

                                    <th>Total Score</th>
                                    <th>Details</th>
                                    <th>Print</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items = "${applicantList}" var = "applicantVo">
                                    <tr>
                                        <td>${applicantVo.name}</td>
                                        <td>15</td>
                                        <td>15 /td>
                                        <td>30</td>
                                        <td>60</td>

                                        <td>-></td>
                                        <td>ㅁ</td>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/materialize/js/materialize.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/List.js"></script>
</body>
</html>