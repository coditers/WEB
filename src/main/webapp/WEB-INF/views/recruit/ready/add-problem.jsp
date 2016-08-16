<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/icon/0630_favicon_beige.ico">
    <title>enroll applicants</title>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/materialize/css/materialize.min.css"/>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/materialize-custom.css"
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
                <div class="row">
                    <div class="col s8 offset-s2">
                        <div class="row center">
                            <h3>${recruitVo.title}</h3>
                        </div>
                        <br>
                        <div class="row">
                            <div class="row">
                                <h5 class="grey-text">Add custom problem</h5>
                            </div>
                            <p class="grey-text">*블라블라</p>
                            <form class="col s12" action ="${pageContext.request.contextPath}/recruit/${recruitId}/add-problem" method="post">
                                <div class="row">
                                    <textarea name="name" id="textarea1" class="materialize-textarea">문제 제목을 입력하세요</textarea>
                                    <label class="brown-text" for="textarea1">Problem title</label>
                                    <textarea name="description" id="textarea2" class="materialize-textarea">문제 설명을 입력하세요</textarea>
                                    <label class="brown-text" for="textarea1">Problem description</label>
                                    <input type="text" name="estimatedTime">
                                    <label class="brown-text">estimated time</label>
                                    <textarea name="skeleton-code-c" id="textarea3" class="materialize-textarea">#include &lt;stdio.h&gt;&#13;&#10;int main(){&#13;&#10;&nbsp;&nbsp;&nbsp;&nbsp;printf(&quot;hello world&quot;;&#13;&#10;}</textarea>
                                    <label class="brown-text" for="textarea3">C skeleton-code</label>
                                    <textarea name="skeleton-code-java" id="textarea4" class="materialize-textarea">public class Task{&#13;&#10;&nbsp;&nbsp;&nbsp;&nbsp;public static void main(String[] args) {&#13;&#10System.out.println(&quot;Hello World&quot;);&#13;&#10&nbsp;&nbsp;&nbsp;&nbsp;}&#13;&#10}</textarea>
                                    <label class="brown-text" for="textarea4">java skeleton-code</label>
                                    <textarea name="skeleton-code-python" id="textarea5" class="materialize-textarea">#TODO</textarea>
                                    <label class="brown-text" for="textarea5">python skeleton-code</label>
                                </div>
                                <div class="row center">
                                    <button type="submit" class="btn brown white-text">submit</button>
                                </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
