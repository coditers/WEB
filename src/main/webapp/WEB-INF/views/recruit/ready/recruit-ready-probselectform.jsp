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
                        <br>
                        <div class="row">
                            <div class="row">
                                <h5 class="grey-text">Select problems</h5>
                            </div>
                            <table>
                                <thead>
                                <tr>
                                    <th data-field="problem_name">Problem</th>
                                    <th data-field="estimated time">Time</th>
                                    <th data-field="description">Description</th>
                                    <th data-field="add to cart?">Cart</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items = "${problemInfoVoList}" var = "vo">
                                <tr>
                                    <td>${vo.name}</td>
                                    <td>${vo.estimatedTime}min</td>
                                    <td>${vo.description}</td>
                                    <td> <button id="${vo.id}" onclick="addOnList(${vo.id})" class="btn red darken-4 right btn-addtocart">+ add to cart</button> </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <br>
                        <div class="row center">
                            <button onclick="submitList()" class="btn brown white-text">Save</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/List.js"></script>
<script type="text/javascript">

    var list = new List();
    //onclick problem tr, add and remove problem id into list
    var addOnList = function( id ){
        if( list.isExist(id) == false){
            var element = document.getElementById(id);
            element.className = "btn disabled btn-addtocart";
            list.push(id);
            console.log( list.arr.join(","));
        }else{
            var element = document.getElementById(id);
            element.className = "btn red darken-4 right btn-addtocart";
            list.eliminate(id);
            console.log( list.arr.join(","));
        }
    }

    //dynamically generate form tag, and submit the problem-Id list
    var submitList = function(){

        var formTag = document.createElement("form");
        formTag.method = "post";
        formTag.action = "${pageContext.request.contextPath}/recruit/${recruitVo.id}/selectproblem";

        for( var i = 0 ; i < list.arr.length; i++ ){
            var input = document.createElement("input");
            input.type = "hidden";
            input.name = "probIdList";
            input.value = list.arr[i];
            formTag.insertBefore(input, null);
        }
        document.body.insertBefore(formTag,null);
        formTag.submit();
    }

</script>
</body>
</html>