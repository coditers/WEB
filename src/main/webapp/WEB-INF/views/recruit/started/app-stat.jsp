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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialize-custom.css" media="screen,projection"/>
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
                                    <th data-field="applicant name" class="center-align">Name</th>
                                    <c:forEach items="${problemInfoList}" var="vo">
                                        <th data-field="problem" class="center-align">${vo.name }</th>
                                    </c:forEach>

                                    <th class="center-align">Total Score</th>
                                    <th class="center-align">Details</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items = "${applicantStatList}" var = "applicantStatVo">
                                    <tr class="center-align center">
                                        <td class="center-align">${applicantStatVo.applicantName}</td>
                                        <c:forEach items = "${applicantStatVo.problemScoreList}" var = "score">
                                            <td class="center-align">${score}</td>
                                        </c:forEach>
                                        <td class="center-align">${applicantStatVo.totalScore}</td>
                                        <td class="center-align"><a href="${pageContext.request.contextPath}/recruit/${recruitId}/applicantresultdetail?applicantId=${applicantStatVo.applicantId}">
                                            <button class="btn small green darken-4 btn-detail">details</button>
                                        </a></td>
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