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
    <title>enroll applicants</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/materialize/css/materialize.min.css"/>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/materialize/css/materialize-custom.css"
          media="screen,projection"/>
</head>

<body class="orange lighten-5 flexbody">
<jsp:include page="/WEB-INF/views/include/header_light.jsp"></jsp:include>
<div class="section no-pad-bot" id="index-banner">
    <br>
    <br>
    <div class="row">
        <div class="col s6 offset-s3">
            <div class="card-panel white">
                <a href="${pageContext.request.contextPath}/recruit/${recruitVo.id}/main">
                    <button class="btn grey lighten-2 white-text">Back</button>
                </a>
                <div class="row">
                    <div class="col s8 offset-s2">
                        <div class="row center">
                            <h3>${recruitVo.title}</h3>
                        </div>
                        <div class="row">
                            <div class="row">
                                <h5 class="grey-text">Enroll applicants</h5>
                            </div>
                            <br>
                            <form id="excel-form" method="post" enctype="multipart/form-data"
                                  <%--action="${pageContext.request.contextPath}/recruit/${recruitVo.id}/register-applicant">--%>
                                <div class="file-field input-field">
                                    <div class="btn green darken-3">
                                        Excel File
                                        <input id="excel-file" name="excel-file" type="file">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate" type="text">
                                    </div>
                                </div>
                                <br>
                                <div class="row center">
                                    <button id="btn-submit" class="btn brown white-text" type="button">save</button>
                                </div>
                            </form>
                        </div>
                        <div class="row center">
                            <div class = "col s10 offset-s1">
                                <table class="striped">
                                    <thead>
                                    <tr>
                                        <th>No</th>
                                        <th data-field="id">Name</th>
                                        <th data-field="name">Email</th>
                                    </tr>
                                    </thead>

                                    <tbody id = "table-applist">
                                    <c:forEach items = "${applicantList}" var = "vo" varStatus="status">
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${vo.name}</td>
                                            <td>${vo.email}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
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
<script type="text/javascript"
        src="${pageContext.request.contextPath}/assets/materialize/js/materialize.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){

        $("#btn-submit").click(function(){
            var formData = new FormData();
            formData.append("excel-file", $("#excel-file")[0].files[0]);
            $.ajax({
                       url:"${pageContext.request.contextPath}/recruit/${recruitVo.id}/register-applicant",
                       type: "POST",
                       data: formData,
                       dataType: 'json',
                       processData: false,
                       contentType: false,

                       success: function(response, textStatus){
                           var list = response.data;
                           $('#table-applist').empty();
                           index = 1;
                           for(var i in list){
                               $('#table-applist').append("<tr><td>"+index+"</td><td>"+list[i].name+"</td><td>"+list[i].email+"</td></tr>");
                               index++;
                           }
                       },
                       error: function(request,status,error){
                           console.log(status+":"+error);
                           console.log("-->code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);}
                       })
                   });
        return false;
        });
</script>
</body>
</html>
