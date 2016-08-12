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
    <title>write email format</title>
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
        <div class="col s6 offset-s3">
            <div class="card-panel white">
                <div class="row">
                    <div class="col s8 offset-s2">
                        <div class="row center">
                            <h3>${recruitVo.title}</h3>
                        </div>
                        <div class="row">
                            <div class="row">
                                <h5 class="grey-text">Write email format</h5>
                            </div>
                            <br>
                            <p class="grey-text">*지원자 이름은 '#이름'으로 시험링크는 '#링크'로 표기해 주십시오. '#링크'는 메일에 반드시 포함되어야 합니다.</p>
                            <form class="col s12" action ="${pageContext.request.contextPath}/recruit/${recruitId}/save-email" method="post">
                                <div class="row">
                                    <div class="input-field col s12">
                                        <textarea name = "emailFormat" id="textarea1" class="materialize-textarea" > 안녕하세요, #이름님. 이스트소프트입니다. 지원해 주셔서 감사합니다. 다음 링크로 시험을 시작해 주세요.#링크</textarea>
                                        <label class="brown-text" for="textarea1">Email Format</label>
                                    </div>
                                    <div class="row center">
                                        <button type="submit" class="btn brown white-text">submit</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/materialize/js/materialize.min.js"></script>
</body>
</html>
