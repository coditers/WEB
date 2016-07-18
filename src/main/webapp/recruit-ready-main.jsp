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
    <title>dashboard: ready</title>
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
                        <br>
                        <div class="row center">
                            <h3>${recruitVo.title}</h3>
                        </div>
                        <br>
                        <div class="row">
                            <div class="collection">
                                <li id = "li-setperiods" class="collection-item bold"><i
                                        class="small material-icons">av_timer</i>Set periods:
                                    <form id="form-selectdate">
                                    <input value="${recruitVo.fromDate}" id= "start_time" type="date" class="datepicker">
                                    ~
                                    <input value="${recruitVo.toDate}" id= "submit_time" type="date" class="datepicker">
                                    <a id="btn-datesave" class="waves-effect waves-orange btn-flat">save</a>
                                    </form>
                                </li>
                                <a href="${pageContext.request.contextPath}/recruit/${recruitId}/probselectform"
                                   class="collection-item bold "><i class="small material-icons">list</i>Select
                                    test problems</a>
                                <a href="${pageContext.request.contextPath}/recruit/${recruitId}/appregform"
                                   class="collection-item bold "><i class="small material-icons">perm_identity</i>Enroll
                                    applicants</a>
                                <a href="#!" class="collection-item bold "><i
                                        class="small material-icons">email</i>Write email format</a>
                                <a href="#!" class="collection-item bold "><i
                                        class="small material-icons">trending_flat</i>Try test</a>
                            </div>
                        </div>
                        <br>

                        <div class="row center">
                            <a href="${pageContext.request.contextPath}/recruit/${recruitId}/sendticket">
                                <button class="btn-large waves-effect waves-light brown center"
                                        type="submit" name="action">Send invitations!
                                    <i class="material-icons right">send</i>
                                </button>
                            </a>
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
<script>
    $(document).ready(function(){
        $('.datepicker').pickadate({
         selectMonths: true, // Creates a dropdown to control month
         selectYears: 15 // Creates a dropdown of 15 years to control year
         });
    });
</script>
</body>
</html>