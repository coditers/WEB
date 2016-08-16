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
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialize-custom.css" media="screen,projection"/>
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
                                    <form id="form-selectdate" action="${pageContext.request.contextPath}/recruit/${recruitVo.id}/set-recruitdate" method = "POST">
                                    <input value="${recruitVo.fromDate}" name="fromDate" id= "fromDate" type="date" class="datepicker">
                                    ~
                                    <input value="${recruitVo.toDate}" name = "toDate" id= "toDate" type="date" class="datepicker">
                                    <button type="submit" id="btn-datesave" class="waves-effect waves-orange white btn-flat right-align">save</button>
                                    </form>
                                </li>
                                <a href="${pageContext.request.contextPath}/recruit/${recruitVo.id}/appregform"
                                   class="collection-item bold"><i class="small material-icons">perm_identity</i>Enroll
                                    applicants
                                    <span class="badge" data-badge-caption="custom caption">${applicantCount} enrolled</span>
                                </a>
                                <a href="${pageContext.request.contextPath}/recruit/${recruitVo.id}/problem-selectform"
                                   class="collection-item bold "><i class="small material-icons">list</i>Select
                                    test problems
                                    <span class="badge" data-badge-caption="custom caption">${problemCount} selected </span>
                                </a>
                                <a href="${pageContext.request.contextPath}/recruit/${recruitVo.id}/write-emailform" class="collection-item bold "><i
                                        class="small material-icons">email</i>Write email format</a>
                                <a href="#!" class="collection-item bold "><i
                                        class="small material-icons">trending_flat</i>Try test</a>
                            </div>
                        </div>
                        <br>

                        <div class="row center">
                                <a id = "btn-sendInvitations" class="btn-large waves-effect waves-light brown center modal-trigger" href="#modal-confirm">Send invitations!
                                    <i class="material-icons right">send</i>
                                </a>

                            <!-- Modal Structure -->
                            <div id="modal-confirm" class="modal">
                                <div class="modal-content">
                                    <h4>Are you sure want to submit this recruit as it is?</h4>
                                    <div row>
                                        <div class="col s6 offset-s3">
                                            <div class="row">
                                                <div id = "modal-p-content" class="row left left-align">
                                                    <p>Title: ${recruitVo.title}
                                                    <br>Recruit Period: ${recruitVo.fromDate} - ${recruitVo.toDate}
                                                    <br>The number of applicants: ${applicantCount}
                                                    <br>The number of selected problems: ${problemCount}
                                                    <br>Email Format: ${recruitVo.emailFormat}
                                                        <br>
                                                        <span id = "modal-span-content"></span>
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="center">
                                                <a id = "modal-a-sendInvitations" href="${pageContext.request.contextPath}/recruit/${recruitVo.id}/send-ticket" >
                                                    <button id = "modal-btn-sendInvitations" class="waves-effect waves-light btn btn-block center-block brown text-white" >Yes, Send invitations!</button></a>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
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
    $(document).ready(function() {
        $('.datepicker').pickadate({
                                       selectMonths: true, // Creates a dropdown to control month
                                       selectYears: 15, // Creates a dropdown of 15 years to control year
                                       format: 'yyyy-mm-dd'
                                   });

        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal-trigger').leanModal();

        //If one of (fromDate, toDate, applicantCount, problemCount, emailFormat) is null or zero, client cannot send invitations.
        //handling

        $('#btn-sendInvitations').click(function () {

            $('#modal-span-content').empty();

            var fromDate = "${recruitVo.fromDate}";
            var toDate =  "${recruitVo.toDate}";
            var applicantCount = "${applicantCount}";
            var problemCount = "${problemCount}";
            var emailFormat = "${recruitVo.emailFormat}";

            var message = "";

            if(fromDate == ""){ message += "*Please set the start date."; }
            if(toDate == ""){ message += "<br>*Please set the closing date."; }
            if(applicantCount == "0"){ message += "<br>*Please enroll applicants."; }
            if(problemCount == "0"){ message += "<br>*Please select test problems."; }
            if(emailFormat == ""){ message += "<br>*Please save email format.\n"; }

            var button = document.getElementById('modal-btn-sendInvitations');
            console.log(button);

            if(message == ""){

                button.className = "waves-effect waves-light btn btn-block center-block brown text-white";
                button.disabled = false;
                console.log('message is empty');
            }else{
                $('#modal-span-content').append("<br>"+message).css('color', 'red');
                button.className = "btn disabled";
                button.disabled = true;
                console.log('message is not empty');
            }

        })
    });
</script>
</body>
</html>