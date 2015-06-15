<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>성적 부여</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/resources/stylesheet/starter-template.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    </head>
    <body class="lead">
        <div class="navbar navbar-inverse navbar-fixed-top nav_color" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav text-uppercase">
                        <li class="active">
</li>
                        <li>
</li>
                        <li>
</li>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
        </div>
        <div class="container">
            <div class="starter-template">
                <img src="${pageContext.request.contextPath}/resources/picture/%EB%AA%85%EC%A7%80%EB%8C%80%ED%95%99%EA%B5%90.jpg">
                <h1 style="display: block;"><b style="display: inline-block;">성적 부여</b></h1>
                <h1 style="display: block;"></h1>
                <hr>
                <!-- 과목 반복 -->
            <%Map<String, List<JsonObject>> data = (Map<String, List<JsonObject>>)request.getAttribute("data");%>
            <%for (String curLectureName : data.keySet()) {%>
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <h2 style="display: block;" class="text-left"><b><%=curLectureName%></b></h2>
                        <table class="table"> 
                        	<thead> 
                            	<tr style="display: table-row;"> 
                                	<th class="text-center" style="display: table-cell;">학생</th>                                         
                                    <th class="text-center">성적</th>
                                    <th class="text-center">성적입력</th>
                                </tr>                                     
                            </thead>                                
                            <tbody>
                            <!-- 학생 반복 --> 
                            <%for (JsonObject e : data.get(curLectureName)) {%>
                            	<tr style="display: table-row;">
                                	<form action="${pageContext.request.contextPath}/LectureController/setGrade" method="post">
                                		<td><%=e.get("student_name").getAsString()%></td>                                         
                                		<td><input type="text" class="form-control" name="grade" value="<%=e.get("grade").getAsString()%>"></td>
                                		<input type="hidden" name="lecture_id" value="<%=e.get("lecture_id").getAsString()%>">
                                		<input type="hidden" name="student_id" value="<%=e.get("student_id").getAsString()%>">
                                		<input type="hidden" name="session_id" value="<%=session.getAttribute("id")%>">
                                		<td><input class="btn" type="submit" value="확인"></td>
                                	</form>
                                </tr>                                     
                            <%}%>
                            </tbody>
                       </table>                             
                    </div>
                </div>
                <%}%>
<a type="button" href="${pageContext.request.contextPath}/SignController/logOut" class="btn btn-default button_margin" style="display: inline-block;">LOG OUT</a>            </div>
        </div>
        <footer class="">
            <div class="">
                <address class="text-center" style="display: block;">makerSM</address>
            </div>
        </footer>
        <!-- /.container -->
        <!-- Bootstrap core JavaScript
    ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="${pageContext.request.contextPath}/resources/assets/js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>
