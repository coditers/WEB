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
    <div id = "cart" class="card-panel white">
        <div class="row">
            <div class="col s10 offset-s1">
                <h4><i class="small material-icons">shopping_cart</i>Cart</h4>
            </div>

            <table id="table-cart" class="bordered">
                <thead>
                <tr>
                    <th>No</th>
                    <th data-field="problem_name">Problem</th>
                    <th data-field="estimated time">Time</th>
                </tr>
                </thead>
            </table>
            <br>
            <br>
            <div id="btn-cartsave" class="row center">
                <button onclick="submitList()" class="btn brown white-text">Save</button>
            </div>
        </div>



    </div>
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
                                    <th>No</th>
                                    <th data-field="problem_name">Problem</th>
                                    <th data-field="estimated time">Time</th>
                                    <th data-field="description">Description</th>
                                    <th data-field="add to cart?">Cart</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items = "${problemInfoVoList}" var = "vo" varStatus="status">
                                <tr id="tr-${vo.id}">
                                    <td id="td-no-${vo.id}">${status.count}</td>
                                    <td id="td-name-${vo.id}">${vo.name}</td>
                                    <td id="td-time-${vo.id}">${vo.estimatedTime}min</td>
                                    <td>${vo.description}</td>
                                    <td class="left"> <button id="${vo.id}" onclick="add_row(${vo.id})" class="btn red darken-4 right btn-addtocart">+ add to cart</button> </td>
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
<script type="text/javascript">

    var array_selected = new Array();
    <c:forEach items="${cartList}" var="item">
        array_selected.push("${item.problemInfoId}")
    </c:forEach>

    console.log(array_selected);

    var list = new List(); //cart list

    function initiate(array){
        for(var i in array) {
            id = array[i];
            var ele = document.getElementById(id);
            ele.className = "btn disabled btn-addtocart";
            list.push(id);
            console.log(list.arr.join(","));

            mytable = document.getElementById('table-cart');
            row = mytable.insertRow(mytable.rows.length);
            row.id = 'tr-cart' + id;
            cell1 = row.insertCell(0);
            cell2 = row.insertCell(1);
            cell3 = row.insertCell(2);
            cell1.innerHTML = document.getElementById('td-no-' + id).innerHTML;
            cell2.innerHTML = document.getElementById('td-name-' + id).innerHTML;
            cell3.innerHTML = document.getElementById('td-time-' + id).innerHTML;
        }
    }

    initiate(array_selected);


    //onclick problem tr, add and remove problem id into list

    function add_row(id){
        if( list.isExist(id) == false){
            var element = document.getElementById(id);
            element.className = "btn disabled btn-addtocart";
            list.push(id);
            console.log( list.arr.join(","));

            mytable = document.getElementById('table-cart');
            row = mytable.insertRow(mytable.rows.length);
            row.id='tr-cart'+id;
            cell1 = row.insertCell(0);
            cell2 = row.insertCell(1);
            cell3 = row.insertCell(2);
            cell1.innerHTML = document.getElementById('td-no-'+id).innerHTML;
            cell2.innerHTML = document.getElementById('td-name-'+id).innerHTML;;
            cell3.innerHTML = document.getElementById('td-time-'+id).innerHTML;;

        }else{
            var element = document.getElementById(id);
            element.className = "btn red darken-4 right btn-addtocart";
            list.eliminate(id);
            console.log( list.arr.join(","));

            var drow = document.getElementById('tr-cart'+id);
            mytable = document.getElementById('table-cart');
            if(mytable.rows.length < 2) return;
            //mytable.deleteRow(mytable.rows.length-1);
            mytable.deleteRow(drow.rowIndex);
        }
    }


//    var addOnList = function( id ){
//        if( list.isExist(id) == false){
//            var element = document.getElementById(id);
//            element.className = "btn disabled btn-addtocart";
//            list.push(id);
//            console.log( list.arr.join(","));
//
//        }else{
//            var element = document.getElementById(id);
//            element.className = "btn red darken-4 right btn-addtocart";
//            list.eliminate(id);
//            console.log( list.arr.join(","));
//        }
//    }

    //dynamically generate form tag, and submit the problem-Id list
    var submitList = function(){

        var formTag = document.createElement("form");
        formTag.method = "post";
        formTag.action = "${pageContext.request.contextPath}/recruit/${recruitVo.id}/select-problem";

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