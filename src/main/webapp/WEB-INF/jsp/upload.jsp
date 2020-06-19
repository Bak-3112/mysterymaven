<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ page import = "java.util.ResourceBundle" %> 
   
 <%
ResourceBundle resource =  ResourceBundle.getBundle("resources/web");
String UI_PATH=resource.getString("UiPath");
String title=resource.getString("dashboardTitle");
String dashboardURL=resource.getString("dashboardURL");
%>

<!DOCTYPE html>
<html>
<head>
<title>MYS</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Poppins"
	rel="stylesheet">
<link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">
<link rel='stylesheet'
	href='<%=UI_PATH%>/design/css/bootstrap.min.css'>
<link rel="stylesheet" type="text/css"
	href="<%=UI_PATH%>/design/css/form-validation.css">
<link rel="stylesheet" type="text/css"
	href="<%=UI_PATH%>/design/css/time.css">
<link rel="stylesheet"
	href="<%=UI_PATH%>/design/css/custom.css"
	type="text/css">
</head>



<body class="bodysection">

	<header class="header clearfix">
		<div class="logo pull-left">
			<a> <img
				src="<%=UI_PATH%>/design/images/bmw.png"
				width="50" height="50" alt="logo">
			</a> <a> <img
				src="<%=UI_PATH%>/design/images/mini-logo.svg"
				width="128px" alt="logo">
			</a>

		</div>
		<div class="greetingtext pull-right">
			<span>Welcome <!-- Mystery Shopper  -->
				<b>${visitor_name}</b></span> <a
				href="<%=dashboardURL%>upload/${shopper_id}"><img
				src="<%=UI_PATH%>/design/images/home.png"
				width="16" height="16"></a>
		</div>
	</header>
	<div class=" quizwrap quizwrap-adjst clearfix">
		<div class="col-xs-12 col-md-12   fig-wrap">
			<figure class="banner-figure">
				<img src="<%=UI_PATH%>/design/images/car.jpg"
					width="956" height="450" class="img-responsive">
			</figure>



		</div>


		<%--  <div class="col-xs-12 col-md-12 pt-60 bg-white d-f fd-column jc-center form-wrap">
                  <h2 class="text-center"><b>Thank you for being a part of the BMW Mystery Shopping Program</b></h2>
                  <div class="button-wrap text-center">
                    <a href="<%=UI_PATH%>/document/${shopper_id}" class="btn btn-upload btn-block">
                        Upload documents
                    </a>
                    <span>Or</span>
                    <a href="<%=UI_PATH%>/shopperDetails/${shopper_id}" class="btn btn-upload btn-block">
                        Continue with questionnaire
                    </a>
                
                </div>
            </div> --%>

		<div
			class="col-xs-12 col-md-12 pt-60 bg-white d-f fd-column jc-center form-wrap">
			<h2 class="text-center">
				<b>Thank you for being a part of the BMW Mystery Shopping
					Program</b>
			</h2>
			<div class="button-wrap text-center">
				<a href="<%=dashboardURL%>document/${shopper_id}"
					class="btn btn-upload btn-block"> Upload documents </a> <span>Or</span>
				<a
					href="<%=dashboardURL%>shopperDetails/${shopper_id}"
					class="btn btn-upload btn-block"> Continue with questionnaire </a>

			</div>
		</div>


	</div>

	<div class="container"></div>

	<script
		src="<%=UI_PATH%>/design/js/jquery.min.js"></script>
	<script
		src="<%=UI_PATH%>/design/js/bootstrap.min.js"></script>
	<script
		src='<%=UI_PATH%>/design/js/date&time/jquery-2.2.4.min.js'></script>
	<script
		src='<%=UI_PATH%>/design/js/date&time/moment.min.js'></script>
	<script
		src='<%=UI_PATH%>/design/js/date&time/bootstrap-datetimepicker.min.js'></script>
	<script
		src="<%=UI_PATH%>/design/js/date&time/date&time.js"></script>
	<script
		src="<%=UI_PATH%>/design/js/form-validation/jquery.validate.min.js"></script>
	<script
		src="<%=UI_PATH%>/design/js/form-validation/jquery.formtowizard.js"></script>
	<script
		src="<%=UI_PATH%>/design/js/form-validation/main.js"></script>
</body>
</html>