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
                    <div class="col s10 offset-s1">
                        <br>
                        <div class="row center">
                            <h3>${recruitVo.title}</h3>
                        </div>
                        <div class="row">
                            <div class="collection">
                                <li id = "li-setperiods" class="collection-item bold"><i
                                        class="small material-icons">av_timer</i>Set periods:
                                    <form id="form-selectdate" action="${pageContext.request.contextPath}/recruit/${recruitVo.id}/setrecruitdate" method = "POST">
                                    <input value="${recruitVo.fromDate}" name="fromDate" id= "fromDate" type="date" class="datepicker">
                                    ~
                                    <input value="${recruitVo.toDate}" name = "toDate" id= "toDate" type="date" class="datepicker">
                                    <button type="submit" id="btn-datesave" class="waves-effect waves-orange white btn-flat right-align">save</button>
                                    </form>
                                </li>
                                <a href="${pageContext.request.contextPath}/recruit/${recruitVo.id}/appregform"
                                   class="collection-item bold"><i class="small material-icons">perm_identity</i>Enroll
                                    applicants
                                    <span class="badge" data-badge-caption="custom caption">0 enrolled</span>
                                </a>
                                <a href="${pageContext.request.contextPath}/recruit/${recruitVo.id}/probselectform"
                                   class="collection-item bold "><i class="small material-icons">list</i>Select
                                    test problems
                                    <span class="badge" data-badge-caption="custom caption">0 selected </span>
                                </a>
                                <a href="${pageContext.request.contextPath}/recruit/${recruitVo.id}/writeemailform" class="collection-item bold "><i
                                        class="small material-icons">email</i>Write email format</a>
                                <a href="#!" class="collection-item bold "><i
                                        class="small material-icons">trending_flat</i>Try test</a>
                            </div>
                        </div>
                        <br>

                        <div class="row center">
                                <a class="btn-large waves-effect waves-light brown center modal-trigger" href="#modal-confirm">Send invitations!
                                    <i class="material-icons right">send</i>
                                </a>

                            <!-- Modal Structure -->
                            <div id="modal-confirm" class="modal">
                                <div class="modal-content">
                                    <h4>Are you sure want to submit this recruit as it is?</h4>
                                    <div row>
                                        <div class="col s6 offset-s3">
                                            <div class="row left left-align">
                                                <p>Title: ${recruitVo.title}
                                                <br>Recruit Period: ${recruitVo.fromDate} - ${recruitVo.toDate}
                                                <br>The number of applicants: xx
                                                <br>Email Format: ${recruitVo.emailFormat}
                                                </p>
                                            </div>
                                                <br>
                                                <br>
                                            <br>
                                            <br>
                                            <br>
                                            <br>
                                            <div class="row left left-align">
                                                <a href="${pageContext.request.contextPath}/recruit/${recruitVo.id}/sendticket" class="waves-effect waves-light btn btn-block center-block brown text-white">
                                                    Yes, Send invitations!</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

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
<script>
    $(document).ready(function(){
        $('.datepicker').pickadate({
         selectMonths: true, // Creates a dropdown to control month
         selectYears: 15, // Creates a dropdown of 15 years to control year
         format: 'yyyy-mm-dd'
         });

        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal-trigger').leanModal();
    });
</script>
</body>
</html>