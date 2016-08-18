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
    <title>sign up</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/materialize/css/materialize.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/materialize-custom.css"
          media="screen,projection"/>
</head>

<body class="orange lighten-5 flexbody">
<jsp:include page="/WEB-INF/views/include/header_light.jsp"></jsp:include>
<div class="section no-pad-bot" id="index-banner">
    <br>
    <br>
    <div class="row ">
        <div class="col s6 offset-s3">
            <div class="card-panel white">
                <h4 class="header center black-text bold">Sign up</h4>
                <br>
                <div class="row">
                    <div class="col s8 offset-s2">
                        <form id="signform" action="${pageContext.request.contextPath}/client/signup" method="POST">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input name="corp-name" id="corp-name" type="text" class="validate">
                                    <label for="corp-name">Company Name</label>
                                    <h6 id="p-corp-name"></h6>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s10">
                                    <input id="email" type="email" name="email" class="validate">
                                    <label for="email">Company Email</label>
                                    <h6 id="p-email"></h6>
                                    <h6 id="p-checkEmail"></h6>
                                </div>
                                <div class="input-field col s2">
                                    <button id="btn-checkEmail" type="button" class="btn green darken-3">Check</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s6">
                                    <input name="firstName" id="first-name" type="text"
                                           class="validate">
                                    <label for="first-name">First Name</label>
                                    <h6 id="p-first-name"></h6>
                                </div>
                                <div class="input-field col s6">
                                    <input name="lastName" id="last-name" type="text" class="validate">
                                    <label for="last-name">Last Name</label>
                                    <h6 id="p-last-name"></h6>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="password" name="password" type="password" class="validate">
                                    <label for="password">Password</label>
                                    <h6 id="p-password"></h6>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="re-password" name="re-password" type="password" class="validate">
                                    <label for="password">Confirm password</label>
                                    <h6 id="p-re-password"></h6>
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="row center ">
                                <button id="btn-submit" type="button"
                                        class="waves-effect waves-light btn btn-block center-block brown text-white">
                                    Submit
                                </button>
                            </div>
                        </form>
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
<script>
    $(document).ready(function () {

        var flag_corp_name = false;
        var flag_email = false;
        var flag_checkEmail = false;
        var flag_first_name = false;
        var flag_last_name = false;
        var flag_password = false;
        var flag_re_password = false;


        //btn-checkEmail눌렷을 때
        $('#btn-checkEmail').on('click', function () {
            var email = $('#email').val();
            console.log(email);
            if (email == "") return;
            $.ajax({
                url: "${pageContext.request.contextPath}/client/checkemail?email=" + email,
                type: "get",
                dataType: "json",
                data: "",
                success: function (response) {
                    if (response.result != "success") {
                        return;
                    }
                    if (response.data == false) {
                        $('#p-checkEmail').html('This email already exists. Please use another one.').css('color', 'red');
                        $("#email").val("").focus();
                        return;
                    } else {
                        $('#p-checkEmail').html('You can use this email.').css('color', 'green');
                        flag_checkEmail = true;
                        return;
                    }
                },
                error: function (jqXHR, status, error) {
                    console.log(status + ":" + error);
                }
            });
        });


        $('#btn-submit').on('click', function () {

            //null check
            if ($('#corp-name').val() == "") {
                $('#p-corp-name').html('Please input the company name').css('color', 'red');
            }
            else {
                $('#p-corp-name').empty();
                flag_corp_name = true;
            }

            if ($('#email').val() == "") {
                $('#p-email').html('Please input company email').css('color', 'red');
            }
            else {
                $('#p-email').empty();
                flag_email = true;
            }

            if (flag_checkEmail == false) {
                $('#p-checkEmail').html('Please check email for duplication').css('color', 'red');
            }

            if ($('#first-name').val() == "") {
                $('#p-first-name').html('Please input first name').css('color', 'red');
            }
            else {
                $('#p-first-name').empty();
                flag_first_name = true;
            }

            if ($('#last-name').val() == "") {
                $('#p-last-name').html('Please input last name').css('color', 'red');
            }
            else {
                $('#p-last-name').empty();
                flag_last_name = true;
            }

            if ($('#password').val() == "") {
                $('#p-password').html('Please input password').css('color', 'red');
            }
            else {
                $('#p-password').empty();
                flag_password = true;
            }

            if ($('#re-password').val() == "" || $('#password').val() != $('#re-password').val()) {
                $('#p-re-password').html('Please confirm your password').css('color', 'red');
            }
            else {
                $('#p-re-password').empty();
                flag_re_password = true;
            }

            if (flag_corp_name == true && flag_email == true && flag_checkEmail == true && flag_first_name == true && flag_last_name == true && flag_password == true && flag_re_password == true) {
                $('#signform').submit();
            }
        })
    });

</script>
</body>
</html>