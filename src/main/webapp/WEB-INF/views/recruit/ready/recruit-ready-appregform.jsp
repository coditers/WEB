<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>codit</title>
    <!--Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css"-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>

<form method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td><input type="text" size="40" name="recruit-name"
                       value="지원자 정보 입력"></td>
        </tr>
        <tr>
            <td><input type="file" name="excel-file"></td>
        </tr>
        <tr>
            <td><input type="submit" value="제출"></td>
        </tr>
    </table>
</form>

<table>
    <tr>
        <th>이름</th>
        <th>직군</th>
        <th>이메일</th>
    </tr>
    <tr>
        <td>염준호</td>
        <td>Back-end</td>
        <td>j@gmail.com</td>
    </tr>
</table>
<button>메인으로</button>
</body>
</html>