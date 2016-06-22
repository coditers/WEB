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
<h2> recruit-title </h2>
<!-- 문제 리스트 테이블-->
<table>
    <tr>
        <td>prob-name</td>
        <td>prob-level</td>
        <td>estimated-time</td>
    </tr>
    <tr>
        <td>
            problem description
        </td>
        <td>
            &nbsp;
        </td>
        <td>
            <button>카트에 담기</button>
        </td>
    </tr>
    <tr>
        <td>prob-name</td>
        <td>prob-level</td>
        <td>estimated-time</td>
    </tr>
    <tr>
        <td>
            problem description
        </td>
        <td>
            &nbsp;
        </td>
        <td>
            <button>카트에 담기</button>
        </td>
    </tr>
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
<button>제출</button>
<button>메인으로</button>
</body>

</html>