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
    <title>enroll applicants</title>
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
                        <a class="waves-effect waves-light btn grey lighten-2 left-align small"><i id="back-icon" class="material-icons left">arrow_back</i>Back</a>
                        <br>
                        <div class="row center">
                            <h3>${recruitVo.title}제목</h3>
                        </div>
                        <br>
                        <div class="row">
                            <div class="row">
                                <h5 class="grey-text">Enroll applicants</h5>
                            </div>
                            <br>
                            <form method="post" enctype="multipart/form-data" action ="${pageContext.request.contextPath}/recruit/${recruitVo.id}/appreg">
                                <div class="file-field input-field">
                                    <div class="btn green darken-3">
                                        Excel File
                                        <input name="excel-file" type="file">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate" type="text">
                                    </div>
                                </div>
                                <div class="row">
                                <button type="submit" class="waves-effect waves-light btn btn-block center-block orange accent-2 text-white">submit</button>
                                </div>
                            </form>
                            </div>
                            <div class="row center">
                            <table class="striped">
                                <thead>
                                <tr>
                                    <th data-field="id">Name</th>
                                    <th data-field="name">Email</th>
                                </tr>
                                </thead>

                                <tbody>
                                <tr>
                                    <td>Alvin</td>
                                    <td>Eclair</td>
                                </tr>
                                <tr>
                                    <td>Alan</td>
                                    <td>Jellybean</td>
                                </tr>
                                <tr>
                                    <td>Jonathan</td>
                                    <td>Lollipop</td>
                                </tr>
                                </tbody>
                            </table>
                            </div>
                        <br>
                        <div class="row center ">
                            <button type="submit" class="waves-effect waves-light btn btn-block center-block brown text-white">Save</button>
                        </div>
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
</body>
</html>
