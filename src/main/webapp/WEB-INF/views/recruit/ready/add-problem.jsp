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
    <script src="${pageContext.request.contextPath }/assets/ace/ace.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(function () {
            var editor1 = ace.edit("editor-1");
            var editor2  = ace.edit("editor-2");
            var editor3 = ace.edit("editor-3");
            editor1.setTheme("ace/theme/monokai");
            editor2.setTheme("ace/theme/monokai");
            editor3.setTheme("ace/theme/monokai");
            editor1.getSession().setMode("ace/mode/c_cpp");
            editor2.getSession().setMode("ace/mode/java");
            editor3.getSession().setMode("ace/mode/python");
            $('#btn-submit').submit(function(event) {
                var code1 = editor1.getValue();
                $("#input1").val(code1);
                var code2 = editor2.getValue();
                $("#input2").val(code2);
                var code3 = editor3.getValue();
                $("#input3").val(code3);
                return true;
            });
        })
    </script>
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
                            <p class="grey-text">*유의사항: 소스코드의 파일이름은 task.c, task.java, task.py로 정해져 있습니다. 특히 java의 경우 메인 클래스 이름은 Task로 해야 합니다.</p>
                            <form id="btn-submit" class="col s12" action ="${pageContext.request.contextPath}/recruit/${recruitId}/add-problem" method="post">
                                <div class="row">
                                    <label class="brown-text" for="textarea1">Problem title</label>
                                    <input name="name" id="textarea1" class="materialize-textarea"/>

                                    <label class="brown-text" for="textarea2">Problem description</label>
                                    <input name="description" id="textarea2" class="materialize-textarea"/>

                                    <label class="brown-text">estimated time</label>
                                    <input type="text" name="estimatedTime"/>

                                    <label class="brown-text" for="editor-1">C skeleton-code</label>
                                    <input id="input1" type="hidden" name="skeleton-code-c"/>
                                    <div id="editor-1" style="height:500px" >#include &lt;stdio.h&gt;&#10;&#9;int main(){&#10;&#9;printf(&quot;hello world&quot;;&#10;&#9;}</div>

                                    <label class="brown-text" for="editor-2">java skeleton-code</label>
                                    <input id="input2" type="hidden" name="skeleton-code-java"/>
                                    <div id="editor-2" style="height:500px">public class Task{&#10;&#9;public static void main(String[] args) {&#10;&#9;System.out.println(&quot;Hello World&quot;);&#10;&#9;}&#10;&#9;}</div>

                                    <label class="brown-text" for="editor-3">python skeleton-code</label>
                                    <input id="input3" type="hidden" name="skeleton-code-python"/>
                                    <div id="editor-3" style="height:500px">print(&quot;hello world&quot;)</div>
                                </div>
                                <div class="row center">
                                    <button type="submit" class="btn brown white-text">submit</button>
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
