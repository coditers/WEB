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

    <!-- Date picker jQuery UI -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script>
        $(function () {
            /*
             * this swallows backspace keys on any non-input element.
             * stops backspace -> back
             */
            var rx = /INPUT|SELECT|TEXTAREA/i;

            $(document).bind("keydown keypress", function(e){
                if( e.which == 8 ){ // 8 == backspace
                    if(!rx.test(e.target.tagName) || e.target.disabled || e.target.readOnly ){
                        e.preventDefault();
                    }
                }
            });

            $("#from").datepicker({
                                    defaultDate: 0,
                                    minDate:+1,
                                    changeMonth: true,
                                    numberOfMonths: 3,
                                    onClose: function (selectedDate) {
                                        $("#to").datepicker("option", "minDate", selectedDate);
                                        $("#ticket").datepicker("option", "maxDate", selectedDate);
                                        $("#to").datepicker("option", "maxDate", dateValue.setDate(selectedDate.getDate()+14));
                                    }
                                  });
            $("#to").datepicker({
                                    defaultDate: 0,
                                    changeMonth: true,
                                    numberOfMonths: 3,
                                    onClose: function (selectedDate) {
                                        $("#from").datepicker("option", "maxDate", selectedDate);
                                        $("#from").datepicker("option", "minDate", dateValue.setDate(selectedDate.getDate()-14));
                                    }
                                });
            $("#ticket").datepicker({
                                     defaultDate: 0,
                                     minDate: +1,
                                     changeMonth: true,
                                     numberOfMonths: 3
                                   });

            var fromDate = $( "#from" ).datepicker( "getDate" );
            var toDate = $( "#to" ).datepicker( "getDate" );

            duration = toDate.getDate() - fromDate.getDate();

            $("#duration").attr("value", duration);
        });
    </script>

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
            <button><a href="#"> > </a></button>
        </td>
    </tr>
    <tr>
        <td colspan="4">문제 선택</td>
        <td>
            <button><a href="#"> > </a></button>
        </td>
    </tr>
    <tr>
        <td colspan="4">
            Report - Deactive
        </td>
        <td>
            <button><a href="#"> > </a></button>
        </td>
    </tr>
    <tr>
        <td colspan="4">
            Test 해보기
        </td>
        <td>
            <button><a href="#"> > </a></button>
        </td>
    </tr>
</table>

<button>시험 기간 설정</button>
<p>달력</p>
<table>
    <tr>
        <td>
            <label for="from"> 시작일 : </label>
        </td>
        <td>
            <input type="text" id="from" name="from">
        </td>
    </tr>
    <tr>
        <td>
            <label for="to"> 종료일 : </label>
        </td>
        <td>
            <input type="text" id="to" name="to">
        </td>
    </tr>
    <tr>
        <td>
            <label> 티켓 발행일 : </label>
        </td>
        <td>
            <input type="text" id="ticket" name="ticket">
        </td>
    </tr>
    <tr>
        <td>
            <label> 총기간 : </label>
        </td>
        <td>
            <input type="text" id="duration" value="">
        </td>
    </tr>
    <button>저장</button>
</table>

</body>

</html>