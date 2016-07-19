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
    <title>problem selection</title>
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
                                <h5 class="grey-text">Select problems</h5>
                            </div>
                            <ul class="collapsible" data-collapsible="accordion">
                                <c:forEach items = "${problemInfoVoList}" var = "vo">
                                    <li>
                                        <div class="collapsible-header light-green lighten-5">"${vo.name}", Time: "${vo.estimatedTime}"
                                        </div>
                                        <a class="right btn-floating btn waves-effect waves-light red darken-4"><i class="material-icons">add</i></a>
                                        <div class="collapsible-body"><p>"${vo.description}"</p></div>
                                    </li>
                                    <!--<tr>
                                        <th>문제 목록</th>
                                        <th>&nbsp</th>
                                        <th>&nbsp</th>
                                    </tr>
                                    <tr id="${vo.id}" onclick="addOnList(${vo.id})">
                                        <td>"${vo.name}"</td>
                                        <td>"${vo.description}"</td>
                                        <td>"${vo.estimatedTime}"</td>
                                    </tr> -->
                                </c:forEach>


                                <li>
                                    <div class="collapsible-header light-green lighten-5">problem 1
                                        <a class="right btn-floating btn waves-effect waves-light red"><i class="material-icons">add</i></a>
                                    </div>
                                    <div class="collapsible-body"><p>problem 1 description</p></div>
                                </li>
                                <li>
                                    <div class="collapsible-header light-green lighten-5">problem 2</div>
                                    <div class="collapsible-body"><p>problem 2 description</p></div>
                                </li>
                                <li>
                                    <div class="collapsible-header light-green lighten-5">problem 3</div>
                                    <div class="collapsible-body"><p>problem 3 description</p></div>
                                </li>
                            </ul>
                        </div>
                        <br>
                    </div>
                </div>
            </div>
            <!-- Modal Trigger -->
            <a class="waves-effect waves-light btn modal-trigger" href="#modal1">Modal</a>

            <!-- Modal Structure -->
            <div id="modal1" class="modal bottom-sheet">
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
</div>

<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/materialize/js/materialize.min.js"></script>
<script>
    $(document).ready(function(){
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal-trigger').leanModal();
    });
</script>
</body>
</html>