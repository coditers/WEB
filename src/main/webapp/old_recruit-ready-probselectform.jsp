<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>codit</title>
    <!--Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css"-->
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/List.js"></script>

    <script type="text/javascript">

        var list = new List();
        //onclick problem tr, add and remove problem id into list
        var addOnList = function( id ){
            if( list.isExist(id) == false){
                $("#"+id).css({"backgroundColor": "#287fc2"});
                list.push(id);
                console.log( list.arr.join(","));
            }else{
                $("#"+id).css({"backgroundColor": "#ffffff"});
                list.eliminate(id);
            }
        }

        //dynamically generate form tag, and submit the problem-Id list
        var submitList = function(){

            var formTag = document.createElement("form");
            formTag.method = "post";
            formTag.action = "${pageContext.request.contextPath}/recruit/${recruitId}/selectproblem";

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

</head>

<body>
<h2> recruit-title </h2>
<!-- 문제 리스트 테이블-->
<table>
    <c:forEach items = "${ problemInfoVoList}" var = "vo">
    <tr>
        <th>문제 목록</th>
        <th>&nbsp</th>
        <th>&nbsp</th>
    </tr>
    <tr id="${vo.id}" onclick="addOnList(${vo.id})">
        <td>"${vo.name}"</td>
        <td>"${vo.description}"</td>
        <td>"${vo.estimatedTime}"</td>
    </tr>
    </c:forEach>
</table>
<button>카트 보기</button>

<!-- 문제 리스트 테이블-->
<table>
    <tr>
        <td>prob-name</td>
        <td>prob-level</td>
        <td>estimated-time</td>
        <td><button>삭제</button></td>
    </tr>
</table>

<table>
    <tr>
        <td>총 난이도</td>
        <td> 67/100</td>
    </tr>
    <tr>
        <td>총 예상 시간</td>
        <td> 67min</td>
    </tr>
    <tr>
        <td>총 문제수</td>
        <td> 5개</td>
    </tr>
</table>


<!-- cart에 담긴 문제리스트 제출 -->
<button onclick="submitList()">제출</button>
<button>메인으로</button>
</body>

</html>