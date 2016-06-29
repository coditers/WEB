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
        <td><h2>2016년 하반기</h2></td>
        <td>&nbsp</td>
    </tr>
    <tr>
        <td colspan="4">
            지원자 정보 입력
        </td>
        <td colspan="4">
            <a href="${pageContext.request.contextPath}/recruit/${recruitId}/appregform"><button> > </button></a>
        </td>
    </tr>
    <tr>
        <td colspan="4">문제 선택</td>
        <td>
            <button> > </button>
        </td>
    </tr>
    <tr>
        <td colspan="4">
            Report - Deactive
        </td>
        <td>
            <button> > </button>
        </td>
    </tr>
    <tr>
        <td colspan="4">
            Test 해보기
        </td>
        <td>
            <button> > </button>
        </td>
    </tr>
</table>

<button>시험 기간 설정</button>
<p>달력</p>
    <table>
        <tr>
            <td>
                <label> 시작일 : </label>
            </td>
            <td>
                <input type = "text" value="2016/07/08"> <input type="text" value="23:59">
            </td>
        </tr>
        <tr>
            <td>
                <label> 종료일 : </label>
            </td>
            <td>
                <input type = "text" value="2016/07/08"> <input type="text" value="23:59">
            </td>
        </tr>
        <tr>
            <td>
                <label> 티켓 발행일 : </label>
            </td>
            <td>
                <input type = "text" value="2016/07/08"> <input type="text" value="23:59">
            </td>
        </tr>
        <tr>
            <td>
                <label> 총기간 : </label>
            </td>
            <td>
                <input type = "text" value="7일">
            </td>
        </tr>
        <button>저장</button>
    </table>

</body>

</html>