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
</head>

<body>
<table>
    <tr>
        <th>이름</th>
        <th>직군</th>

        <!-- 동적으로 생성-->
        <th>1번</th>
        <th>2번</th>
        <th>3번</th>
        <!------------------>
        <th>총점</th>
    </tr>
    <tr>
        <td>
            이현웅
        </td>
        <td>
            front-end
        </td>
        <!-- 동적으로 생성-->
        <td>
            27/35<br>215MB<br>0.15ms
        </td>
        <td>
            1/35<br>15MB<br>1.23ms
        </td>
        <td>
            7/35<br>21MB<br>0.01ms
        </td>
        <!------------------>
        <td>
            67/100
        </td>
    </tr>
</table>
<button>메인으로</button>
<c:if test="${true}">
    <button>내보내기</button>
</c:if>
</body>

</html>