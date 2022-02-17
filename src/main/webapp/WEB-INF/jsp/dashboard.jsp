<html style=""
	class=" js flexbox flexboxlegacy canvas canvastext webgl no-touch geolocation postmessage websqldatabase indexeddb hashchange history draganddrop websockets rgba hsla multiplebgs backgroundsize borderimage borderradius boxshadow textshadow opacity cssanimations csscolumns cssgradients cssreflections csstransforms csstransforms3d csstransitions fontface generatedcontent video audio localstorage sessionstorage webworkers applicationcache svg inlinesvg smil svgclippaths">
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
<head>
<style>
.gm-control-active>img {
	box-sizing: content-box; 	
	display: none;
	left: 50%;
	pointer-events: none;
	position: absolute;
	top: 50%;
	transform: translate(-50%, -50%)
}

.gm-control-active>img:nth-child(1) {
	display: block
}

.gm-control-active:hover>img:nth-child(1), .gm-control-active:active>img:nth-child(1)
	{
	display: none
}

.gm-control-active:hover>img:nth-child(2), .gm-control-active:active>img:nth-child(3)
	{
	display: block
}
</style>
<link type="text/css" rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Google+Sans">
<style>
.gm-ui-hover-effect {
	opacity: .6
}

.gm-ui-hover-effect:hover {
	opacity: 1
}
</style>
<style>
.gm-style .gm-style-cc span, .gm-style .gm-style-cc a, .gm-style .gm-style-mtc div
	{
	font-size: 10px;
	box-sizing: border-box
}
</style>
<style>
@media print {
	.gm-style .gmnoprint, .gmnoprint {
		display: none
	}
}

@media screen {
	.gm-style .gmnoscreen, .gmnoscreen {
		display: none
	}
}
</style>
<style>
.gm-style-pbc {
	transition: opacity ease-in-out;
	background-color: rgba(0, 0, 0, 0.45);
	text-align: center
}

.gm-style-pbt {
	font-size: 22px;
	color: white;
	font-family: Roboto, Arial, sans-serif;
	position: relative;
	margin: 0;
	top: 50%;
	-webkit-transform: translateY(-50%);
	-ms-transform: translateY(-50%);
	transform: translateY(-50%)
}
</style>
<style>
.gm-style img {
	max-width: none;
}

.gm-style {
	font: 400 11px Roboto, Arial, sans-serif;
	text-decoration: none;
}

@media (min-width: 1650px)
.table tbody tr td {
    padding: 5px 5px !important;
}
#wrapper{
overflow-y:scroll !important;
}
</style>

<meta charset="utf-8">
<title>overview report</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta
	content="A fully featured admin theme which can be used to build CRM, CMS, etc."
	name="description">
<meta content="Coderthemes" name="author">
<meta http-equiv="X-UA-Compatible" content="IE=edge">


<!-- App favicon -->

<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/design/images/DQI-icon.png">
<!-- jvectormap -->
<link href="<%=UI_PATH %>/plugins/jvectormap/jquery-jvectormap-2.0.2.css"/>
<link rel="shortcut icon" href="/design/images/DQI-icon.png">

<!-- App css -->
<link href="<%=UI_PATH %>/assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="<%=UI_PATH %>/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="<%=UI_PATH %>/assets/css/metismenu.min.css" rel="stylesheet"
	type="text/css">
<link href="<%=UI_PATH %>/assets/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=UI_PATH %>/assets/css/custom.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" href="<%=UI_PATH %>/assets/graphs/css/style.css">
<link rel="stylesheet" href="<%=UI_PATH %>/assets/graphs/css/responsive.css">
<script src="<%=UI_PATH %>/assets/js/modernizr.min.js"></script>
<style>
.gm-style-mtc, .gm-svpc {
	display: none;
}

.gmnoprint a, .gmnoprint span, .gmnoprint div {
	display: none;
}

.gmnoprint div {
	background: none !important;
}

#GMapsID div div a div img {
	display: none;
}

.score-list li {
	list-style-type: initial;
}

#googleMap button {
	display: none;
}

.font-size strong {
	font-size: 19px;
}
</style>
<script type="text/javascript" charset="UTF-8"
	src="https://maps.googleapis.com/maps-api-v3/api/js/39/9/common.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="https://maps.googleapis.com/maps-api-v3/api/js/39/9/util.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="https://maps.googleapis.com/maps-api-v3/api/js/39/9/map.js"></script>
<style type="text/css">
.cufon-canvas {
	text-indent: 0
}

@media screen , projection {
	.cufon-canvas {
		display: inline;
		display: inline-block;
		position: relative;
		vertical-align: middle;
		font-size: 1px;
		line-height: 1px
	}
	.cufon-canvas .cufon-alt {
		display: -moz-inline-box;
		display: inline-block;
		width: 0;
		height: 0;
		overflow: hidden
	}
	.cufon-canvas canvas {
		position: relative
	}
}

@media print {
	.cufon-canvas {
		padding: 0 !important
	}
	.cufon-canvas canvas {
		display: none
	}
	.cufon-canvas .cufon-alt {
		display: inline
	}
}

#wrapper {
    height: 100%;
    overflow: none;
    width: 100%;
}

</style>
	<style>
body {
	font-family: 'BMWGroup-Regular';
}

body *:not (.fa ):not (.menu-arrow ):not ([class^="fi-"] ):not ([class*=" fi-"]
	){
	font-family: 'BMWGroup-Regular' !important;
}

.h1, .h2, .h3, .h4, .h5, .h6, h1, h2, h3, h4, h5, h6 {
	font-family: 'BMWGroup-Regular';
}

label, th, span, a {
	font-family: 'BMWGroup-Regular';
}

.navbar-custom {
	margin-left: 0px !important;
}

@media ( max-width :767px) {
	.page-title-box {
		padding-left: 65px;
	}
	.enlarged .button-menu-mobile {
		position: absolute !important;
		left: 100%;
	}
}

.navbar-custom .nav-link {
	color: rgb(255, 255, 255) !important;
}

.btn-gradient {
	background: linear-gradient(to top, #5d6dc3, #5d6dc3) !important;
}

.header-text {
	color: white;
	font-size: 18px;
	/* margin: 0 auto; */
	padding: 24px 0 0 0;
	text-align: center;
}

.highcharts-button-symbol {
	display: none;
}
.jvectormap-tip {
    background: #ffffff;
}
</style>
<script type="text/javascript" charset="UTF-8"
	src="https://maps.googleapis.com/maps-api-v3/api/js/39/9/onion.js"></script>
<script type="text/javascript" charset="UTF-8"
	src="https://maps.googleapis.com/maps-api-v3/api/js/39/9/controls.js"></script>
</head>


<body>
	<div id="wrapper">
		<!-- Navigation Bar-->




  <!-- Navigation Bar-->
          <jsp:include page="include/header.jsp"></jsp:include>
         <jsp:include page="include/sidemenu.jsp"></jsp:include>
        <!-- End Navigation Bar-->




		<div class="pdfcontent">







		
			<div class="topbar">

				<!-- LOGO -->
				<!--   <div class="topbar-left" style="background:#8e8e8e !important">
                    
                </div> -->

				<nav class="navbar-custom ml-0 pl-3"
					style="background: #8e8e8e !important">
					<%-- <a href="<%=UI_PATH %>/home"class="logo float-left mt-1 ml-5"> <span> <img src="<%=UI_PATH %>/design/images/bmw-logo.png" alt="" height="50" width="50">
					<img class="ml-3" src="<%=UI_PATH %>/design/images/mini-logo.svg" alt=""
							width="128px">

					</span> <i> </i>
					</a> --%>

					<a href="<%=dashboardURL%>/home" class="logo float-left mt-1 ml-5">
						<img src="<%=UI_PATH%>/design/images/bmw-logo.png" alt=""
						height="50" width="50"> <img class="ml-3"
						src="<%=UI_PATH%>/design/images/mini-logo.svg" alt=""
						width="136px">
					</a>

					<ul class="list-unstyled topbar-right-menu float-right mb-0">
					



						<li class="nav-item px-2"><a class="nav-link" href="#">
								Welcome ${role_name } team <!--
            <span>
                <img src="assets/images/house-outline.png">
            </span>
-->
						</a></li>
						<li class="nav-item px-2"><a class="nav-link" href="#"> <img
								src="<%=UI_PATH %>/design/images/user.png" style="margin-top: 25px;">
						</a></li>
						<li class="nav-item px-2"><a class="nav-link" href="<%=dashboardURL%>logout"> <img src="<%=UI_PATH %>/design/images/logout.png" style="margin-top: 25px;">
						</a></li>
</ul>

					<ul class="list-inline menu-left mb-0">
						<!-- <li class="float-left">
                            <button class="button-menu-mobile open-left waves-light waves-effect">
                                <i class="dripicons-menu"></i>
                            </button>
                        </li> -->

					</ul>
					<h5 class="header-text">Mystery Shopping Program</h5>
				</nav>

			</div>





			<!-- ============================================================== -->
			<!-- Start right Content here -->
			<!-- ============================================================== -->
			<div class="content-page">
				<div class="content">
					<div
						class="main-content pt-3 position-relative nav-left  nav-left-adjst "
						style="padding-left: 0px !important; margin-left: -6px;">
						<div class="position-relative">

							<div class="pt-2 pb-2  bg-white rounded desc-header ml-0">
								<div class="d-flex flex-wrap ">
									<div class="col">
										<h2 class="d-inline-block pt-2">
											<b>Mystery Shopping Overview</b>
										</h2>
									</div>
									<div class="col col-160 ml-auto">
										<!-- <button class="btn btn-primary" id="export"> Download pdf</button> -->
										<form id="brand_form"
											action="<%=UI_PATH %>/home">
											<select class="form-control" name="brand_id"
												id="exampleFormControlSelect1"
												onchange="getBrand(this.value);">

						
												<option value="1" <c:if test = "${brand_id == '1' }">selected </c:if>>BMW</option>



												<option value="2" <c:if test = "${brand_id == '2' }">selected </c:if>>MINI</option>



											</select> <input type="hidden" value="1" id="brand_name">
										</form>
									</div>
									<!-- <script>
										function getBrand() {
											$("#brand_form").submit();
										}
									</script> -->
								</div>
							</div>
						</div>
						<div class="d-flex flex-wrap ">

							<div class="col-md-5 py-2 hidedivmap">
								<div class="card">
									<div class="card-body">
										<h4 class="text-center ">${brand_name } Visit
											Scheduling Status</h4>

										<figure class="mb-0 position-relative">

											<div id="googleMap"
												style="width: 100%; height: 518px; position: relative; overflow: hidden;">
												<div
													style="height: 100%; width: 100%; position: absolute; top: 0px; left: 0px; background-color: rgb(229, 227, 223);">
													<div class="gm-style"
														style="position: absolute; z-index: 0; left: 0px; top: 0px; height: 100%; width: 100%; padding: 0px; border-width: 0px; margin: 0px;">
														<div tabindex="0"
															style="position: absolute; z-index: 0; left: 0px; top: 0px; height: 100%; width: 100%; padding: 0px; border-width: 0px; margin: 0px; cursor: url(&quot;https://maps.gstatic.com/mapfiles/openhand_8_8.cur&quot;), default; touch-action: pan-x pan-y;">
															<div
																style="z-index: 1; position: absolute; left: 50%; top: 50%; width: 100%; transform: translate(0px, 0px);">
																<div
																	style="position: absolute; left: 0px; top: 0px; z-index: 100; width: 100%;">
																	<div
																		style="position: absolute; left: 0px; top: 0px; z-index: 0;">
																		<div
																			style="position: absolute; z-index: 995; transform: matrix(0.738281, 0, 0, 0.738281, -54, -179);">
																			<div
																				style="position: absolute; left: 0px; top: 256px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: -256px; top: 256px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: -256px; top: 0px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: 0px; top: 0px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: 256px; top: 0px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: 256px; top: 256px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: 256px; top: 512px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: 0px; top: 512px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: -256px; top: 512px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: -512px; top: 512px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: -512px; top: 256px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: -512px; top: 0px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: -512px; top: -256px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: -256px; top: -256px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: 0px; top: -256px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																			<div
																				style="position: absolute; left: 256px; top: -256px; width: 256px; height: 256px;">
																				<div style="width: 256px; height: 256px;"></div>
																			</div>
																		</div>
																	</div>
																</div>
																<div
																	style="position: absolute; left: 0px; top: 0px; z-index: 101; width: 100%;"></div>
																<div
																	style="position: absolute; left: 0px; top: 0px; z-index: 102; width: 100%;"></div>
																<div
																	style="position: absolute; left: 0px; top: 0px; z-index: 103; width: 100%;"></div>
																<div
																	style="position: absolute; left: 0px; top: 0px; z-index: 0;">
																	<div
																		style="position: absolute; z-index: 995; transform: matrix(0.738281, 0, 0, 0.738281, -54, -179);">
																		<div
																			style="position: absolute; left: -256px; top: 256px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i22!3i14!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=80184"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: -256px; top: 0px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i22!3i13!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=69779"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: 0px; top: 256px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i23!3i14!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=8540"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: 0px; top: 0px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i23!3i13!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=129206"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: 256px; top: 256px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i24!3i14!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=67967"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: 256px; top: 0px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i24!3i13!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=57562"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: -256px; top: 512px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i22!3i15!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=90589"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: 0px; top: 512px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i23!3i15!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=18945"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: -256px; top: -256px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i22!3i12!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=59374"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: 0px; top: -256px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i23!3i12!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=118801"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: 256px; top: -256px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i24!3i12!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=47157"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: 256px; top: 512px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i24!3i15!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=78372"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: -512px; top: 256px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i21!3i14!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=20757"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: -512px; top: 512px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i21!3i15!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=31162"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: -512px; top: 0px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i21!3i13!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=10352"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																		<div
																			style="position: absolute; left: -512px; top: -256px; width: 256px; height: 256px; transition: opacity 200ms linear 0s;">
																			<img draggable="false" alt="" role="presentation"
																				src="https://maps.googleapis.com/maps/vt?pb=!1m5!1m4!1i5!2i21!3i12!4i256!2m3!1e0!2sm!3i498212582!3m12!2sen-US!3sUS!5e18!12m4!1e68!2m2!1sset!2sRoadmap!12m3!1e37!2m1!1ssmartmaps!4e0&amp;key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;token=131018"
																				style="width: 256px; height: 256px; user-select: none; border: 0px; padding: 0px; margin: 0px; max-width: none;">
																		</div>
																	</div>
																</div>
															</div>
															<div class="gm-style-pbc"
																style="z-index: 2; position: absolute; height: 100%; width: 100%; padding: 0px; border-width: 0px; margin: 0px; left: 0px; top: 0px; opacity: 0;">
																<p class="gm-style-pbt"></p>
															</div>
															<div
																style="z-index: 3; position: absolute; height: 100%; width: 100%; padding: 0px; border-width: 0px; margin: 0px; left: 0px; top: 0px; touch-action: pan-x pan-y;">
																<div
																	style="z-index: 4; position: absolute; left: 50%; top: 50%; width: 100%; transform: translate(0px, 0px);">
																	<div
																		style="position: absolute; left: 0px; top: 0px; z-index: 104; width: 100%;"></div>
																	<div
																		style="position: absolute; left: 0px; top: 0px; z-index: 105; width: 100%;"></div>
																	<div
																		style="position: absolute; left: 0px; top: 0px; z-index: 106; width: 100%;"></div>
																	<div
																		style="position: absolute; left: 0px; top: 0px; z-index: 107; width: 100%;"></div>
																</div>
															</div>
														</div>
														<iframe aria-hidden="true" frameborder="0"
															style="z-index: -1; position: absolute; width: 100%; height: 100%; top: 0px; left: 0px; border: none;"></iframe>
														<div
															style="margin-left: 5px; margin-right: 5px; z-index: 1000000; position: absolute; left: 0px; bottom: 0px;">
															<a target="_blank" rel="noopener"
																href="https://maps.google.com/maps?ll=22.5,81.9629&amp;z=5&amp;t=m&amp;hl=en-US&amp;gl=US&amp;mapclient=apiv3"
																title="Open this area in Google Maps (opens a new window)"
																style="position: static; overflow: visible; float: none; display: inline;"><div
																	style="width: 66px; height: 26px; cursor: pointer;">
																	<img alt="" src="https://maps.gstatic.com/mapfiles/api-3/images/google4.png"
																		draggable="false"
																		style="position: absolute; left: 0px; top: 0px; width: 66px; height: 26px; user-select: none; border: 0px; padding: 0px; margin: 0px;">
																</div></a>
														</div>
														<div
															style="background-color: white; padding: 15px 21px; border: 1px solid rgb(171, 171, 171); font-family: Roboto, Arial, sans-serif; color: rgb(34, 34, 34); box-sizing: border-box; box-shadow: rgba(0, 0, 0, 0.2) 0px 4px 16px; z-index: 10000002; display: none; width: 300px; height: 180px; position: absolute; left: 159px; top: 169px;">
															<div
																style="padding: 0px 0px 10px; font-size: 16px; box-sizing: border-box;">Map
																Data</div>
															<div style="font-size: 13px;">Map data ©2020
																Google, Mapa GISrael, ORION-ME</div>
															<button draggable="false" title="Close"
																aria-label="Close" type="button"
																class="gm-ui-hover-effect"
																style="background: none; display: block; border: 0px; margin: 0px; padding: 0px; position: absolute; cursor: pointer; user-select: none; top: 0px; right: 0px; width: 37px; height: 37px;">
																<img  src="<%=UI_PATH %>/data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2224px%22%20height%3D%2224px%22%20viewBox%3D%220%200%2024%2024%22%20fill%3D%22%23000000%22%3E%0A%20%20%20%20%3Cpath%20d%3D%22M19%206.41L17.59%205%2012%2010.59%206.41%205%205%206.41%2010.59%2012%205%2017.59%206.41%2019%2012%2013.41%2017.59%2019%2019%2017.59%2013.41%2012z%22%2F%3E%0A%20%20%20%20%3Cpath%20d%3D%22M0%200h24v24H0z%22%20fill%3D%22none%22%2F%3E%0A%3C%2Fsvg%3E%0A" style="pointer-events: none; display: block; width: 13px; height: 13px; margin: 12px;">
															</button>
														</div>
														<div class="gmnoprint"
															style="z-index: 1000001; position: absolute; right: 12px; bottom: 0px; width: 12px;">
															<div draggable="false" class="gm-style-cc"
																style="user-select: none; height: 14px; line-height: 14px;">
																<div
																	style="opacity: 0.7; width: 100%; height: 100%; position: absolute;">
																	<div style="width: 1px;"></div>
																	<div
																		style="background-color: rgb(245, 245, 245); width: auto; height: 100%; margin-left: 1px;"></div>
																</div>
																<div
																	style="position: relative; padding-right: 6px; padding-left: 6px; box-sizing: border-box; font-family: Roboto, Arial, sans-serif; font-size: 10px; color: rgb(68, 68, 68); white-space: nowrap; direction: ltr; text-align: right; vertical-align: middle; display: inline-block;">
																	<a
																		style="text-decoration: none; cursor: pointer; display: none;">Map
																		Data</a><span>Map data ©2020 Google, Mapa GISrael,
																		ORION-ME</span>
																</div>
															</div>
														</div>
														<div class="gmnoscreen"
															style="position: absolute; right: 0px; bottom: 0px;">
															<div
																style="font-family: Roboto, Arial, sans-serif; font-size: 11px; color: rgb(68, 68, 68); direction: ltr; text-align: right; background-color: rgb(245, 245, 245);">Map
																data ©2020 Google, Mapa GISrael, ORION-ME</div>
														</div>
														<div class="gmnoprint gm-style-cc" draggable="false"
															style="z-index: 1000001; user-select: none; height: 14px; line-height: 14px; position: absolute; right: 0px; bottom: 0px;">
															<div
																style="opacity: 0.7; width: 100%; height: 100%; position: absolute;">
																<div style="width: 1px;"></div>
																<div
																	style="background-color: rgb(245, 245, 245); width: auto; height: 100%; margin-left: 1px;"></div>
															</div>
															<div
																style="position: relative; padding-right: 6px; padding-left: 6px; box-sizing: border-box; font-family: Roboto, Arial, sans-serif; font-size: 10px; color: rgb(68, 68, 68); white-space: nowrap; direction: ltr; text-align: right; vertical-align: middle; display: inline-block;">
																<a
																	href="https://www.google.com/intl/en-US_US/help/terms_maps.html"
																	target="_blank" rel="noopener"
																	style="text-decoration: none; cursor: pointer; color: rgb(68, 68, 68);">Terms
																	of Use</a>
															</div>
														</div>
														<button draggable="false" title="Toggle fullscreen view"
															aria-label="Toggle fullscreen view" type="button"
															class="gm-control-active gm-fullscreen-control"
															style="background: none rgb(255, 255, 255); border: 0px; margin: 10px; padding: 0px; position: absolute; cursor: pointer; user-select: none; border-radius: 2px; height: 40px; width: 40px; box-shadow: rgba(0, 0, 0, 0.3) 0px 1px 4px -1px; overflow: hidden; display: none; top: 0px; right: 0px;">
															<img 
																src="<%=UI_PATH %>/data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2218%22%20height%3D%2218%22%20viewBox%3D%220%20018%2018%22%3E%0A%20%20%3Cpath%20fill%3D%22%23666%22%20d%3D%22M0%2C0v2v4h2V2h4V0H2H0z%20M16%2C0h-4v2h4v4h2V2V0H16z%20M16%2C16h-4v2h4h2v-2v-4h-2V16z%20M2%2C12H0v4v2h2h4v-2H2V12z%22%2F%3E%0A%3C%2Fsvg%3E%0A"
																style="height: 18px; width: 18px;"><img
																src="<%=UI_PATH %>/data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2218%22%20height%3D%2218%22%20viewBox%3D%220%200%2018%2018%22%3E%0A%20%20%3Cpath%20fill%3D%22%23333%22%20d%3D%22M0%2C0v2v4h2V2h4V0H2H0z%20M16%2C0h-4v2h4v4h2V2V0H16z%20M16%2C16h-4v2h4h2v-2v-4h-2V16z%20M2%2C12H0v4v2h2h4v-2H2V12z%22%2F%3E%0A%3C%2Fsvg%3E%0A"
																style="height: 18px; width: 18px;"><img
																src="<%=UI_PATH %>/data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2218%22%20height%3D%2218%22%20viewBox%3D%220%200%2018%2018%22%3E%0A%20%20%3Cpath%20fill%3D%22%23111%22%20d%3D%22M0%2C0v2v4h2V2h4V0H2H0z%20M16%2C0h-4v2h4v4h2V2V0H16z%20M16%2C16h-4v2h4h2v-2v-4h-2V16z%20M2%2C12H0v4v2h2h4v-2H2V12z%22%2F%3E%0A%3C%2Fsvg%3E%0A"

																src="data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2218%22%20height%3D%2218%22%20viewBox%3D%220%20018%2018%22%3E%0A%20%20%3Cpath%20fill%3D%22%23666%22%20d%3D%22M0%2C0v2v4h2V2h4V0H2H0z%20M16%2C0h-4v2h4v4h2V2V0H16z%20M16%2C16h-4v2h4h2v-2v-4h-2V16z%20M2%2C12H0v4v2h2h4v-2H2V12z%22%2F%3E%0A%3C%2Fsvg%3E%0A"
																style="height: 18px; width: 18px;"><img
																src="data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2218%22%20height%3D%2218%22%20viewBox%3D%220%200%2018%2018%22%3E%0A%20%20%3Cpath%20fill%3D%22%23333%22%20d%3D%22M0%2C0v2v4h2V2h4V0H2H0z%20M16%2C0h-4v2h4v4h2V2V0H16z%20M16%2C16h-4v2h4h2v-2v-4h-2V16z%20M2%2C12H0v4v2h2h4v-2H2V12z%22%2F%3E%0A%3C%2Fsvg%3E%0A"
																style="height: 18px; width: 18px;"><img
																src="data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2218%22%20height%3D%2218%22%20viewBox%3D%220%200%2018%2018%22%3E%0A%20%20%3Cpath%20fill%3D%22%23111%22%20d%3D%22M0%2C0v2v4h2V2h4V0H2H0z%20M16%2C0h-4v2h4v4h2V2V0H16z%20M16%2C16h-4v2h4h2v-2v-4h-2V16z%20M2%2C12H0v4v2h2h4v-2H2V12z%22%2F%3E%0A%3C%2Fsvg%3E%0A"

																style="height: 18px; width: 18px;">
														</button>
														<div draggable="false" class="gm-style-cc"
															style="user-select: none; height: 14px; line-height: 14px; display: none; position: absolute; right: 0px; bottom: 0px;">
															<div
																style="opacity: 0.7; width: 100%; height: 100%; position: absolute;">
																<div style="width: 1px;"></div>
																<div
																	style="background-color: rgb(245, 245, 245); width: auto; height: 100%; margin-left: 1px;"></div>
															</div>
															<div
																style="position: relative; padding-right: 6px; padding-left: 6px; box-sizing: border-box; font-family: Roboto, Arial, sans-serif; font-size: 10px; color: rgb(68, 68, 68); white-space: nowrap; direction: ltr; text-align: right; vertical-align: middle; display: inline-block;">
																<a target="_blank" rel="noopener"
																	title="Report errors in the road map or imagery to Google"
																	href="https://www.google.com/maps/@22.5,81.9629,5z/data=!10m1!1e1!12b1?source=apiv3&amp;rapsrc=apiv3"
																	style="font-family: Roboto, Arial, sans-serif; font-size: 10px; color: rgb(68, 68, 68); text-decoration: none; position: relative;">Report
																	a map error</a>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="map-legend">
												<ul class="mb-0">
													<li><span class="dt"></span>
														<p>
															MYS Conducted <span class="badge">${mys_condcuted }</span>
														</p></li>
													<li><span class="dt" style="background: #979797"></span>
														<p>
															MYS to be conducted <span class="badge">${mys_tobe_condcuted }</span>
														</p></li>
												</ul>
											</div>
										</figure>

									</div>
								</div>

							</div>
							<div class="col-md-7 py-2 d-flex flex-column">
								<div class="card col-12">
									<c:if test = "${brand_id == '1' }">
									<div class="card-body">
										<div class="row  flex-md-wrap oh justify-content-center">
											<div class="col-sm-6 col-md-6 col-lg-6 col-xl-3 pb-2">
												<div class="card-head card-line-skyblue font-size h-100"
													style="background: rgba(196, 196, 196, 0.76);">
													<strong>${national_avg }%</strong>
													<p class="mb-0">National Average</p>
												</div>
											</div>


											<div class="col-sm-6 col-md-6 col-lg-6 col-xl-3 pb-2">
												<div class="card-head card-line-skyblue font-size h-100">
													<strong>${pe_avg }%</strong>
													<p class="mb-0">Process Excellence</p>
												</div>
											</div>
											<div class="col-sm-6 col-md-6 col-lg-6 col-xl-3 pb-2">
												<div class="card-head card-line-skyblue font-size h-100">
													<strong>${ct_avg }%</strong>
													<p class="mb-0">Customer Treatment</p>
												</div>
											</div>
											<div class="col-sm-6 col-md-6 col-lg-6 col-xl-3 pb-2">
												<div class="card-head card-line-skyblue font-size h-100">
													<strong>${osc_avg }%</strong>
													<p class="mb-0">Online Sales Channel</p>
												</div>
											</div>


										</div>
										
										
										<div class="table-responsive">

											<table
												class="table table-bordered table-striped mb-0 rank-table">

												<thead>
													<tr>
														<th>Rank</th>
														<th  style="text-align:center">Dealership</th>
														<th>YTD<br> Dealer Average</th>
														<th>PE</th>
														<th>CT</th>
														<th>OSC</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="dBean" items="${getdashboardTabledata}" varStatus="count">
														<tr>
															<td>${dBean.rank }</td>
															<td>${dBean.dealer_name }</td>
															<c:choose>
															<c:when test="${dBean.movement<'0'}">

																<td><span class="mv-text">${dBean.ytd_dealer_avg1}%</span></td>
															</c:when>
															<c:otherwise>
																<td><span class="mv-text mv-text-green">${dBean.ytd_dealer_avg1}%</span></td>
															</c:otherwise>
														</c:choose>
															<td>${dBean.PE_avg}%</td>
															<td>${dBean.CT_avg}%</td>
															<td>${dBean.osc_avg}%</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>
								
								 </div>
								
      
									<div class="d-flex">
									 <c:if test = "${getdashboardTabledata =='[]'}">
										<h4 class="text align-self-end">
											<i>No Data</i>
										</h4>
 									</c:if>
 									 <c:if test = "${getdashboardTabledata!='[]'}">
										<h4 class="text align-self-end">
											<i>Till ${currentmonth } ${year2 }</i>
										</h4>
 									</c:if>
										<ul class="ml-auto score-list align-self-end mb-1">

											<li><h4 class="text mb-0">PE : Process Excellence</h4></li>
											<li><h4 class="text mb-0">CT : Customer Treatment</h4></li>
											<li><h4 class="text mb-0">OSC : Online Sales
													Channel</h4></li>

										</ul>
									</div>
									
									</c:if>
									<c:if test = "${brand_id == '2' }">
								 <div class="card-body">
								 
								 <div class="row  flex-md-wrap oh justify-content-center">
											<div class="col-sm-6 col-md-6 col-lg-6 col-xl-3 pb-2">
												<div class="card-head card-line-skyblue font-size h-100"
													style="background: rgba(196, 196, 196, 0.76);">
													<strong>${national_avg }%</strong>
													<p class="mb-0">National Average</p>
												</div>
											</div>
										</div>
								 
								 

										<div class="table-responsive">

											<table
												class="table table-bordered table-striped mb-0 rank-table">

												<thead>
													<tr>
														<th>Rank</th>
														<th  style="text-align:center">Dealership</th>
														<th>YTD<br> Dealer Average</th>
														<th>Process Excellence</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="dBean" items="${getdashboardTabledata}" varStatus="count">
														<tr>
															<td>${dBean.rank }</td>
															<td>${dBean.dealer_name }</td>
															<c:choose>
															<c:when test="${dBean.movement<'0'}">

																<td><span class="mv-text">${dBean.ytd_dealer_avg1}%</span></td>
															</c:when>
															<c:otherwise>
																<td><span class="mv-text mv-text-green">${dBean.ytd_dealer_avg1}%</span></td>
															</c:otherwise>
														</c:choose>
															<td>${dBean.PE_avg}%</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>
									</div>
									</c:if>
									 
									
								</div>
							</div>

						</div>
					</div>
				</div>


			</div>
			<!-- content -->


		</div>
	</div>

	<!-- ============================================================== -->
	<!-- End Right content here -->
	<!-- ============================================================== -->

	<!-- END wrapper -->



	<!-- jQuery  -->
	<script src="<%=UI_PATH %>/assets/js/jquery.min.js"></script>
	<script src="<%=UI_PATH %>/assets/js/popper.min.js"></script>
	<script src="<%=UI_PATH %>/assets/js/bootstrap.min.js"></script>
	<script src="<%=UI_PATH %>/assets/js/metisMenu.min.js"></script>
	<script src="<%=UI_PATH %>/assets/js/waves.js"></script>
	<script src="<%=UI_PATH %>/assets/js/jquery.slimscroll.js"></script>

	<!-- Jvector map -->
	<script src="<%=UI_PATH %>/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script src="<%=UI_PATH %>/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script src="<%=UI_PATH %>/plugins/jvectormap/gdp-data.js"></script>
	<script src="<%=UI_PATH %>/plugins/jvectormap/jquery-jvectormap-us-aea-en.js"></script>
	<script src="<%=UI_PATH %>/plugins/jvectormap/jquery-jvectormap-uk-mill-en.js"></script>
	<script src="<%=UI_PATH %>/plugins/jvectormap/jquery-jvectormap-au-mill.js"></script>
	<script
		src="<%=UI_PATH %>/plugins/jvectormap/jquery-jvectormap-us-il-chicago-mill-en.js"></script>
	<script src="<%=UI_PATH %>/plugins/jvectormap/jquery-jvectormap-ca-lcc.js"></script>
	<script src="<%=UI_PATH %>/plugins/jvectormap/jquery-jvectormap-de-mill.js"></script>
	<script src="<%=UI_PATH %>/plugins/jvectormap/jquery-jvectormap-in-mill.js"></script>
	<script src="<%=UI_PATH %>/plugins/jvectormap/jquery-jvectormap-asia-mill.js"></script>
	<!-- App js -->
	<script src="<%=UI_PATH %>/assets/js/jquery.core.js"></script>
	<script src="<%=UI_PATH %>/assets/js/jquery.app.js"></script>

	<script>
		var conductedloc = ${conductedlocgson};
		var scheduledloc = ${scheduledlocgson};

		console.log("conducted==" + JSON.stringify(conductedloc));
		console.log("tobe conducted=" + JSON.stringify(scheduledloc));

		var locs = [];
		var locs1 = [];

		/* $('.lon1').each(function(index, value) {
			var x = $(this).val().split(',');
			locs.push(x);
		})

		var locs1 = [];
		$('.lon2').each(function(index, value) {
			var x = $(this).val().split(',');
			locs1.push(x);
		}) */

		$.each(scheduledloc, function(index, v) {
			var loc = v.latitude + "," + v.longitute;
			var x = loc.split(',');
			locs.push(x);
		});

		$.each(conductedloc, function(index, v) {
			var loc = v.latitude + "," + v.longitute;
			var x = loc.split(',');
			locs1.push(x);
		});

		var locations = locs;
		var locations2 = locs1;

		console.log(locations)

		//var locations=["28.7041","77.1025","Delhi"]

		function myMap() {
			var mapProp = {
				center : new google.maps.LatLng(22.5, 81.9629),
				zoom : 4.56,
				disableDefaultUI : true,
				mapTypeControl : false,
				draggable : false,
				scaleControl : false,
				scrollwheel : false,
				navigationControl : false,
				streetViewControl : false,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			var map = new google.maps.Map(document.getElementById("googleMap"),
					mapProp);

			var marker, i;

			for (i = 0; i < locations.length; i++) {
				marker = new google.maps.Marker({
					position : new google.maps.LatLng(locations[i][0],
							locations[i][1]),
					icon : {
						path : google.maps.SymbolPath.CIRCLE,
						strokeColor : '#979797',
						strokeOpacity : 2,
						strokeWeight : 5,
						fillColor : '#979797',
						fillOpacity : 1,
						scale : 3
					},

					map : map

				});

				google.maps.event.addListener(marker, 'click', (function(
						marker, i) {
					return function() {
						infowindow.setContent(locations[i][0]);
						infowindow.open(map, marker);
					}
				})(marker, i));
			}
			for (i = 0; i < locations2.length; i++) {
				marker = new google.maps.Marker({
					position : new google.maps.LatLng(locations2[i][0],
							locations2[i][1]),
					icon : {
						path : google.maps.SymbolPath.CIRCLE,
						strokeColor : '#1CD444',
						strokeOpacity : 2,
						strokeWeight : 5,
						fillColor : '#1CD444',
						fillOpacity : 1,
						scale : 3
					},

					map : map

				});

				google.maps.event.addListener(marker, 'click', (function(
						marker, i) {
					return function() {
						infowindow.setContent(locations2[i][0]);
						infowindow.open(map, marker);
					}
				})(marker, i));
			}
		}
	</script>

	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;callback=myMap"></script>
	

	<!-- Scripts to download PDF -->

	<!-- <script src="https://code.highcharts.com/highcharts.js"></script> -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.5.0-beta4/html2canvas.svg.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script>
		EXPORT_WIDTH = 1;

		$('#export').click(function() {

			$('body').addClass("enlarged");

			//document.getElementsByClassName('hidedivmap').style.display='none';   
			$(".hidedivmap").hide();
			$("#export").hide();

			var options = {};
			options = {
				background : "#fff"
			};
			options = {
				format : "PNG",
				background : "#fff"
			}
			var pdf = new jsPDF('p', 'pt', 'a4');
			pdf.addHTML($(".pdfcontent"), 15, 15, options, function() {
				pdf.save('overview_report.pdf');
			});

			setTimeout(function() {
				location.reload();
			}, 2000);

		});
	</script>
	

	<!-- Scripts to download PDF -->

	<div
		style="position: absolute; left: 0px; top: -2px; height: 1px; overflow: hidden; visibility: hidden; width: 1px;">
		<span
			style="position: absolute; font-size: 300px; width: auto; height: auto; margin: 0px; padding: 0px; font-family: Roboto, Arial, sans-serif;">BESbswy</span>
	</div>
</body>
</html>