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
    <title>main</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/materialize/css/materialize.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/materialize/css/materialize-custom.css" media="screen,projection"/>

    <script>
        $(document).ready(function(){
            // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
            $('.modal-trigger').leanModal();
        });
    </script>

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
                            <div class="collection">
                                <div id="new-recruit" class="collection-item bold orange accent-1">Add new recruitment!
                                    <a class="btn-floating btn-large waves-effect waves-light orange darken-3 right modal-trigger" href="#modal1">
                                        <i class="material-icons">add</i></a>
                                </div>
                                <!-- Modal Structure -->
                                <div id="modal1" class="modal">
                                    <div class="modal-content">
                                        <h4>Modal Header</h4>
                                        <p>A bunch of text</p>
                                    </div>
                                    <div class="modal-footer">
                                        <a href="#!" class=" modal-action modal-close waves-effect waves-green btn-flat">Agree</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <h6 class="bold">Last recruits</h6>
                        </div>
                        <div class="row">
                            <div class="collection">
                                <a href="#!" class="collection-item bold">2016상반기</a>
                                <a href="#!" class="collection-item bold">2016상반기</a>
                                <a href="#!" class="collection-item bold">2016상반기</a>
                                <a href="#!" class="collection-item bold">2016상반기</a>
                                <a href="#!" class="collection-item bold">2016상반기</a>
                                <a href="#!" class="collection-item bold disabled">2016상반기</a>
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