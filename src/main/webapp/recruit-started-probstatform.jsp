<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <th>문제 번호<img src=""></th>
        <th>정답률<img src=""></th>
        <th>점수 분포<img src=""></th>
        <th>&nbsp;</th>
    </tr>
    <tr>
        <td>
            1
        </td>
        <td>
            79%
        </td>
        <td>
            그래프 그림
        </td>
        <td>
            <input type="checkbox" name="a" value="totalScore">종합점수
            <input type="checkbox" name="b" value="timeUsage">TimeUsage
            <input type="checkbox" name="c" value="memoryUsage">MemoryUsage
        </td>
    </tr>
</table>
<button>메인으로</button>
<c:if test="${true}">
    <button>내보내기</button>
</c:if>
</body>

</html>