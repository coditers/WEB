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
    <title>dashboard: started</title>
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
                        <br>
                        <div class="row center">
                            <h3>${recruitVo.title}</h3>
                        </div>
                        <div class="row">
                            <h6 class="inline">Applying situation: </h6>
                            <h5 class="inline">${submittedApplicantCount}/${applicantCount}</h5>
                            <br>
                            <br>
                            <h6 class="inline">Time remaining: </h6> <h5 id="h-timer" class="inline"></h5> <h6 class="inline">(until: ${recruitVo.toDate} )</h6>
                            <br>
                            <br>
                            <div class="collection">
                                <a href="${pageContext.request.contextPath}/recruit/${recruitVo.id}/applicantstatform"
                                   class="collection-item bold "><i class="small material-icons">perm_identity</i>Report-sorted by applicants</a>
                                <a href="${pageContext.request.contextPath}/recruit/${recruitVo.id}/problemstatform"
                                   class="collection-item bold "><i class="small material-icons">list</i>Report-sorted by problems</a>
                            </div>
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
<script>

    function TimeRemaining()
    {
        var r = document.getElementById('h-timer');

        var now = new Date();
        var st = "${recruitVo.toDate}";
        var t = st.split(/[- :]/);
        var end = new Date(t[0], t[1]-1, t[2], t[3], t[4], t[5]);

       //mysql format: 2016-07-27 00:00:00.0

        var nt = now.getTime();
        var et = end.getTime();

        sec = parseInt(et - nt) / 1000;
        day = parseInt(sec/60/60/24);
        sec = (sec - (day * 60 * 60 * 24));
        hour = parseInt(sec/60/60);
        sec = (sec - (hour*60*60));
        min = parseInt(sec/60);
        sec = parseInt(sec-(min*60));

        if(sec<0){
            r.innerHTML = 'End';
        }else{
            r.innerHTML = day+' days '+hour+' h '+min+' m '+sec+' s ';
        }
    }

    setInterval(TimeRemaining,1000);

</script>
</body>
</html>
