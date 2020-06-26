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

#googleMap button {
	display: none;
}

.font-size strong {
	font-size: 19px;
}

.progress-bar{
background-color:#70a9fe !important;}

.enlarged #wrapper .content-page {
    margin-left: 10px !important;
</style>
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
.crd-min-hgt .card{min-height: 362px;}
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

<link rel="shortcut icon" href="<%=UI_PATH%>/design/images/DQI-icon.png">
<!-- jvectormap -->
<link href="<%=UI_PATH%>/plugins/jvectormap/jquery-jvectormap-2.0.2.css"/>
<link rel="shortcut icon" href="/design/images/DQI-icon.png">

<!-- App css -->
<link href="<%=UI_PATH%>/assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="<%=UI_PATH%>/assets/css/icons.css" rel="stylesheet" type="text/css">
<link href="<%=UI_PATH%>/assets/css/metismenu.min.css" rel="stylesheet"
	type="text/css">
<link href="<%=UI_PATH%>/assets/css/style.css" rel="stylesheet" type="text/css">
<link href="<%=UI_PATH%>/assets/css/custom.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" href="<%=UI_PATH%>/assets/graphs/css/style.css">
<link rel="stylesheet" href="<%=UI_PATH%>/assets/graphs/css/responsive.css">
<script src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>
<!-- <script src="https://kendo.cdn.telerik.com/2019.1.220/js/jquery.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2019.1.220/js/jszip.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2019.1.220/js/kendo.all.min.js"></script> -->
       <script src="https://kendo.cdn.telerik.com/2017.2.621/js/jquery.min.js"></script>
  <script src="https://kendo.cdn.telerik.com/2017.2.621/js/jszip.min.js"></script>
  <script src="https://kendo.cdn.telerik.com/2017.2.621/js/kendo.all.min.js"></script> 
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


<body onload="" class="enlarged">
	<div id="wrapper">
		<!-- Navigation Bar-->
		<%-- <jsp:include page="include/sidemenu.jsp"></jsp:include> --%>
		<div class="pdfcontent">
		<jsp:include page="include/header.jsp"></jsp:include>
		


		<!-- ============================================================== -->
		<!-- Start right Content here -->
		<!-- ============================================================== -->
		<div id="DivPrint">
		<div class="content-page" style="margin-left:0px;">
			<div class="content" id="chartdiv1">
				<div
					class="main-content pt-3 position-relative nav-left  nav-left-adjst "
					style="padding-left: 0px !important; margin-left: -6px;">
					<a href="<%=dashboardURL %>Reports" class="btn btn-light waves-effect m-l-5" >Back</a>
					<button class="btn btn-primary pull-right" id="export"> Download pdf</button>
					<form action="<%=dashboardURL %>allgraphs" method="GET">
						<div class="position-relative brand-div">

							<div class="pt-3 pb-3  bg-white "
								style="padding-left: 0px !important; margin-left: 18px;">
								<div class="d-flex flex-wrap ">
									
									<div class="col col-160 ml-auto">
										<select class="form-control" name="brand_id" id="brand_id" onChange="getmonths(this.value);getdealers();getoutlets();getregionset()">
										 <c:forEach var="gBean" items="${BrandList}" varStatus="count">
												<option value="${gBean.brand_id}" <c:if test = "${brand_id == gBean.brand_id }">selected </c:if>>${gBean.brand_name}</option>
											</c:forEach>
											
										</select>
									
										<%-- <form id="brand_form" action="<%=UI_PATH%>/home">
											<select class="form-control" name="brand_id"
												id="exampleFormControlSelect1"
												onchange="getBrand(this.value);">

						
												<option value="1" <c:if test = "${brand_id == '1' }">selected </c:if>>BMW</option>



												<option value="2" <c:if test = "${brand_id == '2' }">selected </c:if>>MINI</option>



											</select> <input type="hidden" value="1" id="brand_name">
										</form> --%>
									</div>
								</div>
							</div>
						</div>
						<div class="container-fluid my-3 filter-div">
							<div class="card shadow-card">


								<div class="row d-flex justify-content-center px-4 mb-3  mt-3">

									<div class="col-sm-6 col-md py-3">
										<label>Month <span class="text-danger"></span></label> <select required class="form-control" name="month"
											id="month">
											<option value="">Select Month</option>
											<option value="" <c:if test = "${month == '' }">selected </c:if>>All</option>
										 <c:forEach var="gBean" items="${getMonths}">
												<option value="${gBean.month}" <c:if test = "${month == gBean.month }">selected </c:if>>${gBean.month_name}</option>
											</c:forEach> 

										</select>
									</div>
									<div class="col-sm-6 col-md py-3">
										<label>Region</label> <select  name="region_id"
											required class="form-control" id="Region" onChange="getdealers();getoutlets()">
											<option value="">Select Region</option>
											<option value="" <c:if test = "${region_id == '' }">selected </c:if>>All</option>
											 <c:forEach var="rgBean" items="${activeRegionList}">
												<option value="${rgBean.sk_region_id}" <c:if test = "${region_id == rgBean.sk_region_id }">selected </c:if>>${rgBean.region_name}</option>
											</c:forEach> 
											
										</select>
									</div>
									<div class="col-sm-6 col-md py-3">
										<label>Dealer</label> <select name="dealer_id"
											required class="form-control" id="Dealer"onChange="getoutlets()" >
											<option value="">Select Dealer</option>
											<option value="" <c:if test = "${dealer_id == '' }">selected </c:if>>All</option>
											<c:forEach var="gBean" items="${activedealersbyid}">
												<option value="${gBean.dealer_id}" <c:if test = "${dealer_id == gBean.dealer_id }">selected </c:if>>${gBean.dealer_name}</option>
											</c:forEach> 
										</select>
									</div>
									<div class="col-sm-6 col-md py-3">
										<label>Location</label> <select name="outlet_id"
											required class="form-control" id="Location">
											<option value="">Outlet Location</option>
											<option value="" <c:if test = "${outlet_id == '' }">selected </c:if>>All</option>
											 <c:forEach var="gBean" items="${activeoutletsbyid}">
												<option value="${gBean.outlet_id}" <c:if test = "${outlet_id == gBean.outlet_id }">selected </c:if>>${gBean.outlet_name}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-sm-6 col-md py-3">
										<label>Year</label> <select name="year"
											class="form-control" id="year">
											<option value="">Select Year</option> 
											<c:choose>
                                      <c:when test="${empty year}">
                                       <option value="2019">2019</option>
                                         <option value="2020">2020</option>	
                                      </c:when>
                                      <c:otherwise>
                                        <option value="${year}"<c:if test = "${selected_year == year}">selected </c:if>>${year}</option>
                                         <option value="2019">2019</option>
                                          <option value="2020">2020</option>	
                                     </c:otherwise>
                                      </c:choose>
										</select>
									</div>
									<div class="col-sm-6 col-md py-4" style="max-width: 100px;">
										<label></label>
										<button class="btn btn-primary btn-block" type="submit">View</button>
									</div>
									<input type="hidden" value="${selected_year}" id="selected_year" class="selected_year">
								</div>
							</div>
						</div>
					</form>
					<input type="hidden" value="${month}" id="monthtemp"> <input
							type="hidden" value="${dealer_id}" id="dealertemp"> <input
							type="hidden" value="${region_id}" id="regiontemp"> <input
							type="hidden" value="${outlet_id}" id="outlettemp">
					<div class="position-relative">

							<div class="pt-2 pb-2  bg-white rounded desc-header ml-0">
								<div class="d-flex flex-wrap ">
									<div class="col">
										<h2 class="d-inline-block pt-2">
											<b>Mystery Shopping Overview</b>
										</h2>
									</div>
									<%-- <div class="col col-160 ml-auto">
										<!-- <button class="btn btn-primary" id="export"> Download pdf</button> -->
										<form id="brand_form"
											action="<%=UI_PATH%>/home">
											<select class="form-control" name="brand_id"
												id="exampleFormControlSelect1"
												onchange="getBrand(this.value);">

						
												<option value="1" <c:if test = "${brand_id == '1' }">selected </c:if>>BMW</option>



												<option value="2" <c:if test = "${brand_id == '2' }">selected </c:if>>MINI</option>



											</select> <input type="hidden" value="1" id="brand_name">
										</form>
									</div>
									<script>
										function getBrand() {
											$("#brand_form").submit();
										}
									</script> --%>
								</div>
							</div>
						</div>
					<%-- 	<div class="container" style="margin-top: 70px;width: 21cm; margin: 3cm; width: 12in;margin: 1in;">   <div class="col-md-12">
        
       
        <div class="main-text background-image" >
            <h1>Dealer Standards</h1>
            <p>${dealership_name } | Audit Results and Executive Summary ${phase } ${year }</p>
           
            <p class="last-txt">B3-IN-${phase } ${year }</p>
        </div></div></div> --%>
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
															<div style="font-size: 13px;">Map data Â©2020
																Google, Mapa GISrael, ORION-ME</div>
															<button draggable="false" title="Close"
																aria-label="Close" type="button"
																class="gm-ui-hover-effect"
																style="background: none; display: block; border: 0px; margin: 0px; padding: 0px; position: absolute; cursor: pointer; user-select: none; top: 0px; right: 0px; width: 37px; height: 37px;">
																<img  src="<%=UI_PATH%>/data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2224px%22%20height%3D%2224px%22%20viewBox%3D%220%200%2024%2024%22%20fill%3D%22%23000000%22%3E%0A%20%20%20%20%3Cpath%20d%3D%22M19%206.41L17.59%205%2012%2010.59%206.41%205%205%206.41%2010.59%2012%205%2017.59%206.41%2019%2012%2013.41%2017.59%2019%2019%2017.59%2013.41%2012z%22%2F%3E%0A%20%20%20%20%3Cpath%20d%3D%22M0%200h24v24H0z%22%20fill%3D%22none%22%2F%3E%0A%3C%2Fsvg%3E%0A" style="pointer-events: none; display: block; width: 13px; height: 13px; margin: 12px;">
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
																		Data</a><span>Map data Â©2020 Google, Mapa GISrael,
																		ORION-ME</span>
																</div>
															</div>
														</div>
														<div class="gmnoscreen"
															style="position: absolute; right: 0px; bottom: 0px;">
															<div
																style="font-family: Roboto, Arial, sans-serif; font-size: 11px; color: rgb(68, 68, 68); direction: ltr; text-align: right; background-color: rgb(245, 245, 245);">Map
																data Â©2020 Google, Mapa GISrael, ORION-ME</div>
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
																src="<%=UI_PATH%>/data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2218%22%20height%3D%2218%22%20viewBox%3D%220%20018%2018%22%3E%0A%20%20%3Cpath%20fill%3D%22%23666%22%20d%3D%22M0%2C0v2v4h2V2h4V0H2H0z%20M16%2C0h-4v2h4v4h2V2V0H16z%20M16%2C16h-4v2h4h2v-2v-4h-2V16z%20M2%2C12H0v4v2h2h4v-2H2V12z%22%2F%3E%0A%3C%2Fsvg%3E%0A"
																style="height: 18px; width: 18px;"><img
																src="<%=UI_PATH%>/data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2218%22%20height%3D%2218%22%20viewBox%3D%220%200%2018%2018%22%3E%0A%20%20%3Cpath%20fill%3D%22%23333%22%20d%3D%22M0%2C0v2v4h2V2h4V0H2H0z%20M16%2C0h-4v2h4v4h2V2V0H16z%20M16%2C16h-4v2h4h2v-2v-4h-2V16z%20M2%2C12H0v4v2h2h4v-2H2V12z%22%2F%3E%0A%3C%2Fsvg%3E%0A"
																style="height: 18px; width: 18px;"><img
																src="<%=UI_PATH%>/data:image/svg+xml,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%2218%22%20height%3D%2218%22%20viewBox%3D%220%200%2018%2018%22%3E%0A%20%20%3Cpath%20fill%3D%22%23111%22%20d%3D%22M0%2C0v2v4h2V2h4V0H2H0z%20M16%2C0h-4v2h4v4h2V2V0H16z%20M16%2C16h-4v2h4h2v-2v-4h-2V16z%20M2%2C12H0v4v2h2h4v-2H2V12z%22%2F%3E%0A%3C%2Fsvg%3E%0A"

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

			
			
		<!-- content -->

	<div class="content" id="chartdiv2">
			<div class="pt-2 pb-2  bg-white rounded desc-header ml-0">
							<div class="d-flex flex-wrap ">
								<div class="col">
									<h2 class="d-inline-block pt-2">Overall Performance</h2>
								</div>
								<div class="col col-160 ml-auto">
								
							</div>
						</div>
					</div>
				
					<div class="container-fluid my-3">
						<input type="hidden" value="${month}" id="monthtemp"> <input
							type="hidden" value="${dealer}" id="dealertemp"> <input
							type="hidden" value="${region}" id="regiontemp"> <input
							type="hidden" value="${outlet}" id="outlettemp">
  <div class="row my-3">
          <div class="col-md-12 text-center my-3">
            <h4 class="chart-title-heading">YTD Performance Trend</h4>
              <div class="shadow">
                <!-- <div class="linchartlegend-wrap">
                                        <ul class="d-flex flex-wrap list-unstyled justify-content-center mt-3">
                                            <li><span style="background: #3F78CF"></span>National Average MTD</li>
                                            <li><span style="background:#83BCFF "></span>National Average YTD</li>
                                        </ul>
                                    </div> -->
                                 	<c:forEach var="ytdmtd" items="${ytdmtd}">
								<input type="hidden" value="${ytdmtd.YTD_YTD}"
									class="ytd">
									<input type="hidden" value="${ytdmtd.MTD}"
									class="mtd">
									<input type="hidden" value="${ytdmtd.month}"
									class="monthytdmtd">
							</c:forEach>   
                     <div  id="ytdchart" style="width: 100%; height: 300px; margin: 0 auto"></div>
                    <!--  <img alt="" src="" id="ytdimg1"> -->
                    <img alt="" src="" id="canvasimg1">
              </div>
          
          </div>
      
        </div>
       <div class="row justify-content-center">
            <div class="col-md-4 text-center my-3 ">
                <h4 class="chart-title-heading">Process Excellence</h4>
              <div class="shadow" id="processchart" style="width: 100%; height: 300px; margin: 0 auto"></div>
              <img alt="" src="" id="canvasimg2">
            </div>
            <div class="col-md-4 text-center my-3">
                <h4 class="chart-title-heading">Customer Treatment</h4>
              <div class="shadow" id="customerchart" style="width: 100%; height: 300px; margin: 0 auto"></div>
              <img alt="" src="" id="canvasimg3">
            </div> 
            <c:if test="${brand != '2'}">
            <div class="col-md-4 text-center my-3 ">
                <h4 class="chart-title-heading">Online Sales Channel</h4>
              <div class="shadow" id="Onlinesale" style="width: 100%; height: 300px; margin: 0 auto"></div>
              <img alt="" src="" id="canvasimg4">
            </div>
            </c:if>
        </div>
 
						<div class="row my-3" style="display: none;">
							
							<c:forEach var="qBean" items="${getProcessPercentage}" varStatus="count">
							<c:if test="${count.index<4}">
								<input type="hidden" value="${qBean.percentage}"
									id="processpercentage">
									</c:if>
							</c:forEach>

							<c:forEach var="qBean" items="${getProcessPercentage}">
								<c:forEach var="sectionscore" items="${qBean.sectionScore}"
									varStatus="count">

									<input type="hidden"
										value="${sectionscore.subsection_name},${sectionscore.percentage},<c:if test="${count.index=='0'}">#CCE3FF</c:if><c:if test="${count.index=='1'}">#99C8FF</c:if><c:if test="${count.index=='2'}">#83BCFF</c:if><c:if test="${count.index=='3'}">#5094FC</c:if><c:if test="${count.index=='4'}">#287cff</c:if>"
										class="processpercentagesubsection">
										
								</c:forEach>
							</c:forEach>

							<c:forEach var="qBean" items="${getCustomerPercentage}">
								<input type="hidden" value="${qBean.percentage}"
									id="customerpercentage">
							</c:forEach>

							<c:forEach var="qBean" items="${getCustomerPercentage}">
								<c:forEach var="sectionscore" items="${qBean.sectionScore}"
									varStatus="count">
									
									<input type="hidden"
										value="${sectionscore.subsection_name},${sectionscore.percentage},<c:if test="${count.index=='0'}">#CCE3FF</c:if><c:if test="${count.index=='1'}">#99C8FF</c:if><c:if test="${count.index=='2'}">#83BCFF</c:if><c:if test="${count.index=='3'}">#5094FC</c:if><c:if test="${count.index=='4'}">#287cff</c:if>"
										class="customerpercentagesubsection">
									
								</c:forEach>
							</c:forEach>
					<c:forEach var="qBean" items="${getOnlineSalesChannel}">
								<input type="hidden" value="${qBean.percentage}"
									id="OnlineSalesChannelpercentage">
							</c:forEach>

							<c:forEach var="qBean" items="${getOnlineSalesChannel}" varStatus="count1">
								<c:forEach var="sectionscore" items="${qBean.sectionScore}"
									varStatus="count">
									<input type="text" value="${count1.index}">
									<input type="text"
										value="${sectionscore.subsection_name},${sectionscore.percentage},<c:if test="${count.index=='1'}">#CCE3FF</c:if><c:if test="${count.index=='2'}">#99C8FF</c:if><c:if test="${count.index=='3'}">#83BCFF</c:if><c:if test="${count.index=='4'}">#5094FC</c:if><c:if test="${count.index=='5'}">#287cff</c:if><c:if test="${count.index=='6'}">#1972fdc9</c:if>"
										class="OnlineSalesChannelpercentagesubsection">
								</c:forEach>
							</c:forEach>
							<div class="col-md-4 text-center">
								<h4>
									<b>Process Excellence</b>
								</h4>
								<div class="border" id="processchart"
									style="width: 100%; height: 400px; margin: 0 auto"></div>
							</div>
							<div class="col-md-4 text-center">
								<h4>
									<b>Customer Treatment</b>
								</h4>
								<div class="border" id="customerchart"
									style="width: 100%; height: 400px; margin: 0 auto"></div>
							</div>
						</div>
						<c:if test="${selected_year == 2019}">
						<div class="row">
							<div class="col-md-4 table-part mt-3">
								<table class="table table-bordered table-striped scoretable">
									<thead>
										<tr>
											<th scope="col">Success Factors</th>
											<th scope="col">Score</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${mode=='yes'}">
											<tr>
												<th scope="row">1. Personal Response within 2 working
													days</th>
												<td class="td-progress">${personal_avg}%<span
													style="width:${personal_avg}%;
                        <c:if test="${personal_avg<=90.0}">background:#FF0000</c:if>"></span>
												</td>
											</tr>
										</c:if>
										<tr>
											<th scope="row">2. Vehicle Presentation</th>
											<td class="td-progress">${vehicle_presentation}%<span
												style="width:${vehicle_presentation}%;
                        <c:if test="${vehicle_presentation<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
										<tr>
											<th scope="row">3. Test Drive Offer</th>
											<td class="td-progress">${Test_Drive_Offer}%<span
												style="width:${Test_Drive_Offer}%;
                        <c:if test="${Test_Drive_Offer<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
										<tr>
											<th scope="row">4. New Car Offer Given</th>
											<td class="td-progress">${car_offer}%<span
												style="width:${car_offer}%; 
                      <c:if test="${car_offer<=90.0}">background:#FF0000</c:if> "></span></td>
										</tr>
										<tr>
											<th scope="row">5. Follow-up on Offer</th>
											<td class="td-progress">${offer_followup}%<span
												style="width:${offer_followup}%;
                        <c:if test="${offer_followup<=90.0}">background:#FF0000</c:if> "></span></td>
										</tr>
										<tr>
											<th scope="row">6. Consider Buying a Vehicle</th>
											<td class="td-progress">${Buy_vehicle}%<span
												style="width:${Buy_vehicle}%;
                      <c:if test="${Buy_vehicle<=90.0}">background:#FF0000</c:if> "></span></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="col-md-4 table-part mt-3">
								<table class="table table-bordered table-striped scoretable">
									<thead>
										<tr>
											<th scope="col">Working Standards</th>
											<th scope="col">Score</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${mode=='yes'}">
											<tr>
												<th scope="row">1. Auto Response (instantly)</th>
												<td class="td-progress">${auto_response}%<span
													style="width:${auto_response}%;
                        <c:if test="${auto_response<=90.0}">background:#FF0000</c:if>"></span></td>
											</tr>
											<tr>
												<th scope="row">2. Personal Response within 4 hrs</th>
												<td class="td-progress">${response_4_hours}%<span
													style="width:${response_4_hours}%;
                        <c:if test="${response_4_hours<=90.0}">background:#FF0000</c:if>"></span></td>
											</tr>
											<tr>
												<th scope="row">3. Quality of Response: Standards</th>
												<td class="td-progress">${quality_of_response}%<span
													style="width:${quality_of_response}%;
                        <c:if test="${quality_of_response<=90.0}">background:#FF0000</c:if>"></span></td>
											</tr>
											<tr>
												<th scope="row">4. Attempt to make an Appointment</th>
												<td class="td-progress">${appointment}%<span
													style="width:${appointment}%;
                        <c:if test="${appointment<=90.0}">background:#FF0000</c:if>"></span></td>
											</tr>
										</c:if>
										<tr>
											<th scope="row">5. Retail Lead Documentation</th>
											<td class="td-progress">${retail_lead}%<span
												style="width:${retail_lead}%;
                        <c:if test="${retail_lead<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
										<tr>
											<th scope="row">6. Test Drive Offer</th>
											<td class="td-progress">${Test_Drive_Offer}%<span
												style="width:${Test_Drive_Offer}%;
                        <c:if test="${Test_Drive_Offer<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
										<tr>
											<th scope="row">7. New Car Offer</th>
											<td class="td-progress">${car_offer}%<span
												style="width:${car_offer}%;
                        <c:if test="${car_offer<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
										<tr>
											<th scope="row">8. Financing Product Offer given</th>
											<td class="td-progress">${financial_offer }%<span
												style="width:${financial_offer }%;
                        <c:if test="${financial_offer<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
										<tr>
											<th scope="row">9. Follow-up on Offer</th>
											<td class="td-progress">${offer_followup}%<span
												style="width:${offer_followup}%;
                        <c:if test="${offer_followup<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="col-md-4 table-part mt-3">
								<table class="table table-bordered table-striped scoretable">
									<thead>
										<tr>
											<th scope="col">Retail Standards</th>
											<th scope="col">Score</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${mode=='yes'}">
											<tr>
												<th scope="row">1. Contacted personally by the
													dealership</th>
												<td class="td-progress">${personal_avg }%<span
													style="width:${personal_avg}%;
                        <c:if test="${personal_avg<=90.0}">background:#FF0000</c:if>"></span></td>
											</tr>
										</c:if>
										<tr>
											<th scope="row">2. Actively welcomed and guided</th>
											<td class="td-progress">${welcome_guided}%<span
												style="width:${welcome_guided}%;
                        <c:if test="${welcome_guided<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
										<tr>
											<th scope="row">3. Systematic and customer oriented
												product presentation</th>
											<td class="td-progress">${product_presentation}%<span
												style="width:${product_presentation}%;
                        <c:if test="${product_presentation<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
										<tr>
											<th scope="row">4. Detailed product information upon
												request</th>
											<td class="td-progress">${product_information}%<span
												style="width:${product_information}%;
                        <c:if test="${product_information<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
										<tr>
											<th scope="row">5. Test drive actively offered</th>
											<td class="td-progress">${Test_Drive_Offer}%<span
												style="width:${Test_Drive_Offer}%;
                        <c:if test="${Test_Drive_Offer<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
										<tr>
											<th scope="row">6. Test drive procedure requirements
												fulfilled</th>
											<td class="td-progress">${drive_requirements}%<span
												style="width:${drive_requirements}%;
                        <c:if test="${drive_requirements<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
										<tr>
											<th scope="row">7. Test drive vehicle condition</th>
											<td class="td-progress">${vehicle_condition}%<span
												style="width:${vehicle_condition}%;
                        <c:if test="${vehicle_condition<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
										<tr>
											<th scope="row">8. Contacted in the corresponding time
												frame</th>
											<td class="td-progress">${offer_followup}%<span
												style="width:${offer_followup}%;
                        <c:if test="${offer_followup<=90.0}">background:#FF0000</c:if>"></span></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						</c:if>
					</div>
				</div>
	<!-- end overall performance -->

<!-- CRM -->


			<!-- Start content -->
			<div class="content" id="chartdiv3">
			<div class="pt-2 pb-2  bg-white rounded desc-header ml-0">
							<div class="d-flex flex-wrap ">
								<div class="col">
									<h2 class="d-inline-block pt-2">CRM Analysis</h2>
								</div>
								<div class="col col-160 ml-auto">
								
							</div>
						</div>
					</div>
				<div class="main-content  position-relative nav-left">
				
					<input type="hidden" value="${month}" id="monthtemp"> <input
							type="hidden" value="${dealer_id}" id="dealertemp"> <input
							type="hidden" value="${region_id}" id="regiontemp"> <input
							type="hidden" value="${outlet_id}" id="outlettemp">
					<div class="container-fluid">
						<ul class="d-flex flex-wrap justify-content-center max-1000">
							<li class="col-sm-6 col-md-6 col-lg-6 col-xl py-2">
								<div class="card-head card-line-skyblue">
									<strong>${noncom_percentage}%</strong>
									<p class="mb-0">Non Compliance</p>
								</div>
							</li>
							<li class="col-sm-6 col-md-6 col-lg-6 col-xl py-2">
								<div class="card-head card-line-skyblue">
									<strong>${delaycompliance}%</strong>
									<p class="mb-0">Delayed Compliance</p>
								</div>
							</li>
							<li class="col-sm-6 col-md-6 col-lg-6 col-xl py-2">
								<div class="card-head card-line-skyblue">
									<strong>${name_percentage}%</strong>
									<p class="mb-0">Incorrect Contact Name
									</p>
									</div>
							</li>
							<li class="col-sm-6 col-md-6 col-lg-6 col-xl py-2">
								<div class="card-head card-line-skyblue">
									<strong>${email_percentage}%</strong>
									<p class="mb-0">Incorrect Email Address</p>
								</div>
							</li>
							<li class="col-sm-6 col-md-6 col-lg-6 col-xl py-2">
								<div class="card-head card-line-skyblue">
									<strong>${timely_percentage}%</strong>
									<p class="mb-0">Timely Compliance</p>
									<input id="nodelay" value="${nodelay}" type="hidden">
									<input id="more" value="${more_percentage}" type="hidden">
									<input id="upto" value="${upto_percentage}" type="hidden">
									<input id="monthdata" value="${months}" type="hidden">
									<input id="yes" value="${yes}" type="hidden">
									<input id="no" value="${no}" type="hidden">
								</div>
							</li>
						</ul>
					</div>
					<!-- graphs -->
					<div class="container-fluid mt-4 mb-5">
						<div class="row">
							<div class="col-md-6 my-3">
										<h4 class=" chart-title-heading text-center">CRM Compliance</h4>
								<div class="card">
									<div class="card-body">
										<div id="crm-container-bar-graph"
											style="height: 425px; margin: 0 auto"></div>
											<img alt="" src="" id="canvasimg5">
									</div>
								</div>
							</div>
							<div class="col-md-6 my-3">
										<h4 class="chart-title-heading  text-center">CRM Timelines</h4>
								<div class="card">
									<div class="card-body">
										<div id="chartContainer" style="height: 446px; width: 100%;"></div>
										<img alt="" src="" id="chartcanvasimgtimelines">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

<!-- CRM -->
<!-- CLcontact -->

 <div class="content" id="chartdiv4">
<div class="pt-2 pb-2  bg-white rounded desc-header ml-0">
							<div class="d-flex flex-wrap ">
								<div class="col">
									<h2 class="d-inline-block pt-2">Conquest & Loyalty: Contact</h2>
								</div>
								<div class="col col-160 ml-auto">
								
							</div>
						</div>
					</div>
				<div class="main-content  position-relative nav-left">
				
					<input type="hidden" value="${month}" id="monthtemp"> <input
						type="hidden" value="${dealer_id}" id="dealertemp"> <input
						type="hidden" value="${region_id}" id="regiontemp"> <input
						type="hidden" value="${outlet_id}" id="outlettemp">
					<!-- <div class="container">           
        <div class="row my-3">
            <div class="col-md-6 my-3">
                <div class="border">
                  <h6 class="text-center  chart-title-heading">Did you instantly receive an auto response email?</h6>
                  <div id="responsecontainer" style="min-width: 310px; height: 270px; margin: 0 auto"></div>
                  <img alt="" src="" id="canvasimg6">
                </div>
            </div>
            <div class="col-md-6 my-3">
                <div class="border">
                  <h6 class="text-center  chart-title-heading">Did the response meet the standards?</h6>
                    <div id="responsecontainer2" style="min-width: 310px; height: 270px; margin: 0 auto"></div>
                    <img alt="" src="" id="canvasimg7">
                </div>
            </div>
            <div class="col-md-6 my-3">
              <div class="border">
                <h6 class="text-center  chart-title-heading">When were you contacted personally at first by the dealership?</h6>
                <div id="dealer" style="min-width: 310px; height: 270px; margin: 0 auto"></div>
                <img alt="" src="" id="canvasimg8">
              </div>
            </div>
            <div class="col-md-6 my-3">
              <div class="border">
                <h6 class="text-center  chart-title-heading">Did the sales representative attempt to set an appointment?</h6>
                <div id="appointment" style="min-width: 310px; height: 270px; margin: 0 auto"></div>
                <img alt="" src="" id="canvasimg9">
              </div>
            </div>
        </div>
          </div> -->
           <div class="container">           
        <div class="row my-3">
        <div class="col-md-6 my-3">
                 <h6 class="text-center  chart-title-heading "><b>Did you instantly receive an auto response email?</b></h6>
                  
                  <div id="responsecontainer" class="container-fluid shadow" style="background-color: white;overflow: hidden;padding-left: 0px;padding-right: 0px;padding-top: 1px;"></div>
               <img alt="" src="" id="canvasimg6">
            </div>
            <div class="col-md-6 my-3">
               <h6 class="text-center  chart-title-heading "><b>Did the response meet the standards?</b></h6>
                    <div id="responsecontainer2" class="container-fluid shadow" style="background-color: white;overflow: hidden;padding-left: 0px;padding-right: 0px;padding-top: 1px;"></div>
           <img alt="" src="" id="canvasimg7">
            </div>
            <div class="col-md-6 my-3">
             <h6 class="text-center  chart-title-heading "><b>When were you contacted personally at first by the dealership?</b></h6>
                <div id="conq-loy-dealer" class="container-fluid shadow" style="background-color: white;overflow: hidden;padding-left: 0px;padding-right: 0px;padding-top: 1px;"></div>
             <img alt="" src="" id="canvasimg8">
            </div>
            <div class="col-md-6 my-3">
             <h6 class="text-center  chart-title-heading "><b>Did the sales representative attempt to set an appointment?</b></h6>
                <div id="appointment" class="container-fluid shadow" style="background-color: white;overflow: hidden;padding-left: 0px;padding-right: 0px;padding-top: 1px;"></div>
             <img alt="" src="" id="canvasimg9">
            </div>
        </div>
          </div>
				</div>

			</div> 


<!-- CLcontact telephone-->
<div class="container" id="chartdiv5">
 <div class="pt-2 pb-2  bg-white rounded desc-header ml-0">
							<div class="d-flex flex-wrap ">
								<div class="col">
									<h2 class="d-inline-block pt-2">Conquest & Loyalty: Telephonic Enquiry</h2>
								</div>
								<div class="col col-160 ml-auto">
								
							</div>
						</div>
					</div>
						<div class="row my-3">
            <div class="col-md-6 my-3">
             <h6 class="text-center  chart-title-heading "><b>Did the sales representative define next steps / provide information on next steps to take?</b></h6>
                <div class="shadow">
                 
                 <div id="responsecontainerTele" >
                 <img alt="" src="" id="canvasimg10">
                 </div>
                   <%-- <div id="responsecontainer" style="min-width: 310px; height: 300px; margin: 0px auto; overflow: hidden;" data-highcharts-chart="0"><div id="highcharts-s80m0k6-0" dir="ltr" class="highcharts-container " style="position: relative; overflow: hidden; width: 348px; height: 300px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg version="1.1" class="highcharts-root" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="348" height="300" viewBox="0 0 348 300"><desc>Created with Highcharts 7.0.3</desc><defs><clipPath id="highcharts-s80m0k6-1"><rect x="0" y="0" width="328" height="225" fill="none"></rect></clipPath></defs><rect fill="#ffffff" class="highcharts-background" x="0" y="0" width="348" height="300" rx="0" ry="0"></rect><rect fill="none" class="highcharts-plot-background" x="10" y="23" width="328" height="225"></rect><g class="highcharts-grid highcharts-xaxis-grid " data-z-index="1"><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 91.5 23 L 91.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 173.5 23 L 173.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 255.5 23 L 255.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 337.5 23 L 337.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 9.5 23 L 9.5 248" opacity="1"></path></g><g class="highcharts-grid highcharts-yaxis-grid " data-z-index="1"><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 248.5 L 338 248.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 192.5 L 338 192.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 136.5 L 338 136.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 79.5 L 338 79.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 22.5 L 338 22.5" opacity="1"></path></g><rect fill="none" class="highcharts-plot-border" data-z-index="1" x="10" y="23" width="328" height="225"></rect><g class="highcharts-axis highcharts-xaxis " data-z-index="2"><path fill="none" class="highcharts-axis-line" stroke="transparent" stroke-width="1" data-z-index="7" d="M 10 248.5 L 338 248.5"></path></g><g class="highcharts-axis highcharts-yaxis " data-z-index="2"><path fill="none" class="highcharts-axis-line" data-z-index="7" d="M 10 23 L 10 248"></path></g><g class="highcharts-series-group" data-z-index="3"><g data-z-index="0.1" class="highcharts-series highcharts-series-0 highcharts-column-series highcharts-tracker " transform="translate(10,23) scale(1 1)" clip-path="url(#highcharts-s80m0k6-1)"><rect x="8" y="0" width="66" height="226" fill="#3F78CF" class="highcharts-point"></rect><rect x="90" y="0" width="66" height="226" fill="rgb(63,120,207)" class="highcharts-point "></rect><rect x="172" y="0" width="66" height="226" fill="rgb(63,120,207)" class="highcharts-point "></rect><rect x="254" y="0" width="66" height="226" fill="rgb(63,120,207)" class="highcharts-point "></rect></g><g data-z-index="0.1" class="highcharts-markers highcharts-series-0 highcharts-column-series " transform="translate(10,23) scale(1 1)" clip-path="none"></g></g><text x="174" text-anchor="middle" class="highcharts-title" data-z-index="4" style="color:#333333;font-size:18px;fill:#333333;" y="24"></text><text x="174" text-anchor="middle" class="highcharts-subtitle" data-z-index="4" style="color:#666666;fill:#666666;" y="24"></text><g data-z-index="6" class="highcharts-data-labels highcharts-series-0 highcharts-column-series highcharts-tracker " transform="translate(10,23) scale(1 1)" opacity="1"><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(18,101)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">100%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(100,101)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">100%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(182,101)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">100%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(264,101)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">100%</text></g></g><g class="highcharts-legend" data-z-index="7" transform="translate(144,-15)"><rect fill="none" class="highcharts-legend-box" rx="0" ry="0" x="0" y="0" width="59" height="26" visibility="visible"></rect><g data-z-index="1"><g><g class="highcharts-legend-item highcharts-column-series highcharts-color-undefined highcharts-series-0" data-z-index="1" transform="translate(8,3)"><text x="21" style="color:#003366;cursor:pointer;font-size:13px;font-weight:400;font-family:'BMWGroup-Regular';fill:#003366;" text-anchor="start" data-z-index="2" y="16"><tspan>Yes</tspan></text><rect x="1.5" y="4" width="13" height="13" fill="#3F78CF" rx="6.5" ry="6.5" class="highcharts-point" data-z-index="3"></rect></g></g></g></g><g class="highcharts-axis-labels highcharts-xaxis-labels " data-z-index="7"><text x="51" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>January,</tspan><tspan dy="14" x="51">2018</tspan></text><text x="133" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>April, 2018</tspan></text><text x="215" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>July, 2018</tspan></text><text x="297" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>October,</tspan><tspan dy="14" x="297">2018</tspan></text></g><g class="highcharts-axis-labels highcharts-yaxis-labels " data-z-index="7"></g><g class="highcharts-label highcharts-tooltip highcharts-color-undefined" style="pointer-events:none;white-space:nowrap;" data-z-index="8" transform="translate(159,-9999)" opacity="0" visibility="visible"><path fill="none" class="highcharts-label-box highcharts-tooltip-box highcharts-shadow" d="M 3.5 0.5 L 49.5 0.5 55.5 -5.5 61.5 0.5 109 0.5 C 112.5 0.5 112.5 0.5 112.5 3.5 L 112.5 44.5 C 112.5 47.5 112.5 47.5 109.5 47.5 L 3.5 47.5 C 0.5 47.5 0.5 47.5 0.5 44.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#000000" stroke-opacity="0.049999999999999996" stroke-width="5" transform="translate(1, 1)"></path><path fill="none" class="highcharts-label-box highcharts-tooltip-box highcharts-shadow" d="M 3.5 0.5 L 49.5 0.5 55.5 -5.5 61.5 0.5 109 0.5 C 112.5 0.5 112.5 0.5 112.5 3.5 L 112.5 44.5 C 112.5 47.5 112.5 47.5 109.5 47.5 L 3.5 47.5 C 0.5 47.5 0.5 47.5 0.5 44.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#000000" stroke-opacity="0.09999999999999999" stroke-width="3" transform="translate(1, 1)"></path><path fill="none" class="highcharts-label-box highcharts-tooltip-box highcharts-shadow" d="M 3.5 0.5 L 49.5 0.5 55.5 -5.5 61.5 0.5 109 0.5 C 112.5 0.5 112.5 0.5 112.5 3.5 L 112.5 44.5 C 112.5 47.5 112.5 47.5 109.5 47.5 L 3.5 47.5 C 0.5 47.5 0.5 47.5 0.5 44.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#000000" stroke-opacity="0.15" stroke-width="1" transform="translate(1, 1)"></path><path fill="rgba(247,247,247,0.85)" class="highcharts-label-box highcharts-tooltip-box" d="M 3.5 0.5 L 49.5 0.5 55.5 -5.5 61.5 0.5 109 0.5 C 112.5 0.5 112.5 0.5 112.5 3.5 L 112.5 44.5 C 112.5 47.5 112.5 47.5 109.5 47.5 L 3.5 47.5 C 0.5 47.5 0.5 47.5 0.5 44.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#3F78CF" stroke-width="1"></path><text x="8" data-z-index="1" style="font-size:12px;color:#333333;cursor:default;fill:#333333;" y="20"><tspan style="font-size: 10px">July, 2018</tspan><tspan style="fill:#3F78CF" x="8" dy="15">Yes</tspan><tspan dx="0">: </tspan><tspan style="font-weight:bold" dx="0">100</tspan><tspan dx="0"> (100%)</tspan></text></g></svg></div></div>
                 --%></div>
            </div>
            <div class="col-md-6 my-3">
             <h6 class="text-center chart-title-heading "><b>Did the response meet the standards?</b></h6>
                <div class="shadow mt-4">
                 
                    <div id="responsecontainerTele2" >
                    <img alt="" src="" id="canvasimg11">
                    </div>
                   <%--  <div id="responsecontainer2" style="min-width: 310px; height: 300px; margin: 0px auto; overflow: hidden;" data-highcharts-chart="1"><div id="highcharts-s80m0k6-6" dir="ltr" class="highcharts-container " style="position: relative; overflow: hidden; width: 348px; height: 300px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg version="1.1" class="highcharts-root" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="348" height="300" viewBox="0 0 348 300"><desc>Created with Highcharts 7.0.3</desc><defs><clipPath id="highcharts-s80m0k6-7"><rect x="0" y="0" width="328" height="225" fill="none"></rect></clipPath></defs><rect fill="#ffffff" class="highcharts-background" x="0" y="0" width="348" height="300" rx="0" ry="0"></rect><rect fill="none" class="highcharts-plot-background" x="10" y="23" width="328" height="225"></rect><g class="highcharts-grid highcharts-xaxis-grid " data-z-index="1"><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 91.5 23 L 91.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 173.5 23 L 173.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 255.5 23 L 255.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 337.5 23 L 337.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 9.5 23 L 9.5 248" opacity="1"></path></g><g class="highcharts-grid highcharts-yaxis-grid " data-z-index="1"><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 248.5 L 338 248.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 192.5 L 338 192.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 136.5 L 338 136.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 79.5 L 338 79.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 22.5 L 338 22.5" opacity="1"></path></g><rect fill="none" class="highcharts-plot-border" data-z-index="1" x="10" y="23" width="328" height="225"></rect><g class="highcharts-axis highcharts-xaxis " data-z-index="2"><path fill="none" class="highcharts-axis-line" stroke="transparent" stroke-width="1" data-z-index="7" d="M 10 248.5 L 338 248.5"></path></g><g class="highcharts-axis highcharts-yaxis " data-z-index="2"><path fill="none" class="highcharts-axis-line" data-z-index="7" d="M 10 23 L 10 248"></path></g><g class="highcharts-series-group" data-z-index="3"><g data-z-index="0.1" class="highcharts-series highcharts-series-0 highcharts-column-series highcharts-tracker " transform="translate(10,23) scale(1 1)" clip-path="url(#highcharts-s80m0k6-7)"><rect x="8" y="0" width="66" height="226" fill="rgb(63,120,207)" class="highcharts-point "></rect><rect x="90" y="0" width="66" height="226" fill="rgb(63,120,207)" class="highcharts-point "></rect><rect x="172" y="0" width="66" height="226" fill="rgb(63,120,207)" class="highcharts-point "></rect><rect x="254" y="0" width="66" height="226" fill="#3F78CF" class="highcharts-point"></rect></g><g data-z-index="0.1" class="highcharts-markers highcharts-series-0 highcharts-column-series " transform="translate(10,23) scale(1 1)" clip-path="none"></g></g><text x="174" text-anchor="middle" class="highcharts-title" data-z-index="4" style="color:#333333;font-size:18px;fill:#333333;" y="24"></text><text x="174" text-anchor="middle" class="highcharts-subtitle" data-z-index="4" style="color:#666666;fill:#666666;" y="24"></text><g data-z-index="6" class="highcharts-data-labels highcharts-series-0 highcharts-column-series highcharts-tracker " transform="translate(10,23) scale(1 1)" opacity="1"><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(18,101)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">100%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(100,101)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">100%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(182,101)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">100%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(264,101)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">100%</text></g></g><g class="highcharts-legend" data-z-index="7" transform="translate(144,-15)"><rect fill="none" class="highcharts-legend-box" rx="0" ry="0" x="0" y="0" width="59" height="26" visibility="visible"></rect><g data-z-index="1"><g><g class="highcharts-legend-item highcharts-column-series highcharts-color-undefined highcharts-series-0" data-z-index="1" transform="translate(8,3)"><text x="21" style="color:#003366;cursor:pointer;font-size:13px;font-weight:400;font-family:'BMWGroup-Regular';fill:#003366;" text-anchor="start" data-z-index="2" y="16"><tspan>Yes</tspan></text><rect x="1.5" y="4" width="13" height="13" fill="#3F78CF" rx="6.5" ry="6.5" class="highcharts-point" data-z-index="3"></rect></g></g></g></g><g class="highcharts-axis-labels highcharts-xaxis-labels " data-z-index="7"><text x="51" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>January,</tspan><tspan dy="14" x="51">2018</tspan></text><text x="133" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>April, 2018</tspan></text><text x="215" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>July, 2018</tspan></text><text x="297" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>October,</tspan><tspan dy="14" x="297">2018</tspan></text></g><g class="highcharts-axis-labels highcharts-yaxis-labels " data-z-index="7"></g><g class="highcharts-label highcharts-tooltip highcharts-color-undefined" style="pointer-events:none;white-space:nowrap;" data-z-index="8" transform="translate(1,-9999)" opacity="0" visibility="visible"><path fill="none" class="highcharts-label-box highcharts-tooltip-box highcharts-shadow" d="M 3.5 0.5 L 43.5 0.5 49.5 -5.5 55.5 0.5 109 0.5 C 112.5 0.5 112.5 0.5 112.5 3.5 L 112.5 44.5 C 112.5 47.5 112.5 47.5 109.5 47.5 L 3.5 47.5 C 0.5 47.5 0.5 47.5 0.5 44.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#000000" stroke-opacity="0.049999999999999996" stroke-width="5" transform="translate(1, 1)"></path><path fill="none" class="highcharts-label-box highcharts-tooltip-box highcharts-shadow" d="M 3.5 0.5 L 43.5 0.5 49.5 -5.5 55.5 0.5 109 0.5 C 112.5 0.5 112.5 0.5 112.5 3.5 L 112.5 44.5 C 112.5 47.5 112.5 47.5 109.5 47.5 L 3.5 47.5 C 0.5 47.5 0.5 47.5 0.5 44.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#000000" stroke-opacity="0.09999999999999999" stroke-width="3" transform="translate(1, 1)"></path><path fill="none" class="highcharts-label-box highcharts-tooltip-box highcharts-shadow" d="M 3.5 0.5 L 43.5 0.5 49.5 -5.5 55.5 0.5 109 0.5 C 112.5 0.5 112.5 0.5 112.5 3.5 L 112.5 44.5 C 112.5 47.5 112.5 47.5 109.5 47.5 L 3.5 47.5 C 0.5 47.5 0.5 47.5 0.5 44.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#000000" stroke-opacity="0.15" stroke-width="1" transform="translate(1, 1)"></path><path fill="rgba(247,247,247,0.85)" class="highcharts-label-box highcharts-tooltip-box" d="M 3.5 0.5 L 43.5 0.5 49.5 -5.5 55.5 0.5 109 0.5 C 112.5 0.5 112.5 0.5 112.5 3.5 L 112.5 44.5 C 112.5 47.5 112.5 47.5 109.5 47.5 L 3.5 47.5 C 0.5 47.5 0.5 47.5 0.5 44.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#3F78CF" stroke-width="1"></path><text x="8" data-z-index="1" style="font-size:12px;color:#333333;cursor:default;fill:#333333;" y="20"><tspan style="font-size: 10px">January, 2018</tspan><tspan style="fill:#3F78CF" x="8" dy="15">Yes</tspan><tspan dx="0">: </tspan><tspan style="font-weight:bold" dx="0">100</tspan><tspan dx="0"> (100%)</tspan></text></g></svg></div></div>
               --%>  </div>
            </div>
           <div class="col-md-6 my-3" style="margin: 0 auto;
        float: none;
        margin-bottom: 10px;">
             
              <h6 class="text-center chart-title-heading   mt-3"><b>When did you reach anyone at the dealership? </b></h6>
              <div class="shadow">
              
                <label></label>
                <div id="dealers" ></div>
                <img alt="" src="" id="canvasimg12">
               <%--  <div id="dealers" style="min-width: 310px; height: 300px; margin: 0px auto; overflow: hidden;" data-highcharts-chart="2"><div id="highcharts-s80m0k6-12" dir="ltr" class="highcharts-container " style="position: relative; overflow: hidden; width: 348px; height: 300px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg version="1.1" class="highcharts-root" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="348" height="300" viewBox="0 0 348 300"><desc>Created with Highcharts 7.0.3</desc><defs><clipPath id="highcharts-s80m0k6-13"><rect x="0" y="0" width="328" height="180" fill="none"></rect></clipPath></defs><rect fill="#ffffff" class="highcharts-background" x="0" y="0" width="348" height="300" rx="0" ry="0"></rect><rect fill="none" class="highcharts-plot-background" x="10" y="68" width="328" height="180"></rect><g class="highcharts-grid highcharts-xaxis-grid " data-z-index="1"><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 91.5 68 L 91.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 173.5 68 L 173.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 255.5 68 L 255.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 337.5 68 L 337.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 9.5 68 L 9.5 248" opacity="1"></path></g><g class="highcharts-grid highcharts-yaxis-grid " data-z-index="1"><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 248.5 L 338 248.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 158.5 L 338 158.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 67.5 L 338 67.5" opacity="1"></path></g><rect fill="none" class="highcharts-plot-border" data-z-index="1" x="10" y="68" width="328" height="180"></rect><g class="highcharts-axis highcharts-xaxis " data-z-index="2"><path fill="none" class="highcharts-axis-line" stroke="transparent" stroke-width="1" data-z-index="7" d="M 10 248.5 L 338 248.5"></path></g><g class="highcharts-axis highcharts-yaxis " data-z-index="2"><path fill="none" class="highcharts-axis-line" data-z-index="7" d="M 10 68 L 10 248"></path></g><g class="highcharts-series-group" data-z-index="3"><g data-z-index="0.1" class="highcharts-series highcharts-series-0 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" clip-path="url(#highcharts-s80m0k6-13)"><rect x="8" y="172" width="66" height="9" fill="#3F78CF" class="highcharts-point"></rect><rect x="90" y="165" width="66" height="16" fill="#3F78CF" class="highcharts-point"></rect><rect x="172" y="158" width="66" height="23" fill="#3F78CF" class="highcharts-point"></rect><rect x="254" y="172" width="66" height="9" fill="#3F78CF" class="highcharts-point"></rect></g><g data-z-index="0.1" class="highcharts-markers highcharts-series-0 highcharts-column-series " transform="translate(10,68) scale(1 1)" clip-path="none"></g><g data-z-index="0.1" class="highcharts-series highcharts-series-1 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" clip-path="url(#highcharts-s80m0k6-13)"><rect x="8" y="158" width="66" height="14" fill="#5094FC" class="highcharts-point"></rect><rect x="90" y="131" width="66" height="34" fill="#5094FC" class="highcharts-point"></rect><rect x="172" y="152" width="66" height="6" fill="#5094FC" class="highcharts-point"></rect><rect x="254" y="158" width="66" height="14" fill="#5094FC" class="highcharts-point"></rect></g><g data-z-index="0.1" class="highcharts-markers highcharts-series-1 highcharts-column-series " transform="translate(10,68) scale(1 1)" clip-path="none"></g><g data-z-index="0.1" class="highcharts-series highcharts-series-2 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" clip-path="url(#highcharts-s80m0k6-13)"><rect x="8" y="69" width="66" height="89" fill="#83BCFF" class="highcharts-point"></rect><rect x="90" y="15" width="66" height="116" fill="#83BCFF" class="highcharts-point"></rect><rect x="172" y="24" width="66" height="128" fill="#83BCFF" class="highcharts-point"></rect><rect x="254" y="19" width="66" height="139" fill="#83BCFF" class="highcharts-point"></rect></g><g data-z-index="0.1" class="highcharts-markers highcharts-series-2 highcharts-column-series " transform="translate(10,68) scale(1 1)" clip-path="none"></g><g data-z-index="0.1" class="highcharts-series highcharts-series-3 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" clip-path="url(#highcharts-s80m0k6-13)"><rect x="8" y="0" width="66" height="69" fill="#B0D5FF" class="highcharts-point"></rect><rect x="90" y="0" width="66" height="15" fill="#B0D5FF" class="highcharts-point"></rect><rect x="172" y="0" width="66" height="24" fill="#B0D5FF" class="highcharts-point"></rect><rect x="254" y="0" width="66" height="19" fill="#B0D5FF" class="highcharts-point"></rect></g><g data-z-index="0.1" class="highcharts-markers highcharts-series-3 highcharts-column-series " transform="translate(10,68) scale(1 1)" clip-path="none"></g></g><text x="174" text-anchor="middle" class="highcharts-title" data-z-index="4" style="color:#333333;font-size:18px;fill:#333333;" y="24"></text><text x="174" text-anchor="middle" class="highcharts-subtitle" data-z-index="4" style="color:#666666;fill:#666666;" y="24"></text><g data-z-index="6" class="highcharts-data-labels highcharts-series-0 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" opacity="1"><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(25,162)" opacity="0" visibility="hidden"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">5%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(107,161)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">9%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(186,158)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">13%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(271,162)" opacity="0" visibility="hidden"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">5%</text></g></g><g data-z-index="6" class="highcharts-data-labels highcharts-series-1 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" opacity="1"><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(25,154)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">8%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(104,137)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">19%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(189,144)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">3%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(271,154)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">8%</text></g></g><g data-z-index="6" class="highcharts-data-labels highcharts-series-2 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" opacity="1"><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(22,102)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">49%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(104,62)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">64%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(186,77)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">71%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(268,77)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">77%</text></g></g><g data-z-index="6" class="highcharts-data-labels highcharts-series-3 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" opacity="1"><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(22,23)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">38%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(107,-4)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">8%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(186,1)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">13%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(268,-2)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">10%</text></g></g><g class="highcharts-legend" data-z-index="7" transform="translate(88,-15)"><rect fill="none" class="highcharts-legend-box" rx="0" ry="0" x="0" y="0" width="172" height="71" visibility="visible"></rect><g data-z-index="1"><g><g class="highcharts-legend-item highcharts-column-series highcharts-color-undefined highcharts-series-0" data-z-index="1" transform="translate(8,3)"><text x="21" style="color:#003366;cursor:pointer;font-size:13px;font-weight:600;font-family:'BMWGroup-Regular';fill:#003366;" text-anchor="start" data-z-index="2" y="16"><tspan>After 4 hours on the Ã¢â¬Â¦</tspan></text><rect x="1.5" y="4" width="13" height="13" fill="#3F78CF" rx="6.5" ry="6.5" class="highcharts-point" data-z-index="3"></rect></g><g class="highcharts-legend-item highcharts-column-series highcharts-color-undefined highcharts-series-1" data-z-index="1" transform="translate(8,18)"><text x="21" y="16" style="color:#003366;cursor:pointer;font-size:13px;font-weight:600;font-family:'BMWGroup-Regular';fill:#003366;" text-anchor="start" data-z-index="2"><tspan>Not at all</tspan></text><rect x="1.5" y="4" width="13" height="13" fill="#5094FC" rx="6.5" ry="6.5" class="highcharts-point" data-z-index="3"></rect></g><g class="highcharts-legend-item highcharts-column-series highcharts-color-undefined highcharts-series-2" data-z-index="1" transform="translate(8,33)"><text x="21" y="16" style="color:#003366;cursor:pointer;font-size:13px;font-weight:600;font-family:'BMWGroup-Regular';fill:#003366;" text-anchor="start" data-z-index="2"><tspan>Within 2 working Ã¢â¬Â¦</tspan></text><rect x="1.5" y="4" width="13" height="13" fill="#83BCFF" rx="6.5" ry="6.5" class="highcharts-point" data-z-index="3"></rect></g><g class="highcharts-legend-item highcharts-column-series highcharts-color-undefined highcharts-series-3" data-z-index="1" transform="translate(8,48)"><text x="21" y="16" style="color:#003366;cursor:pointer;font-size:13px;font-weight:600;font-family:'BMWGroup-Regular';fill:#003366;" text-anchor="start" data-z-index="2"><tspan>Within 4 hours Ã¢â¬Â¦</tspan></text><rect x="1.5" y="4" width="13" height="13" fill="#B0D5FF" rx="6.5" ry="6.5" class="highcharts-point" data-z-index="3"></rect></g></g></g></g><g class="highcharts-axis-labels highcharts-xaxis-labels " data-z-index="7"><text x="51" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>January,</tspan><tspan dy="14" x="51">2018</tspan></text><text x="133" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>April, 2018</tspan></text><text x="215" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>July, 2018</tspan></text><text x="297" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>October,</tspan><tspan dy="14" x="297">2018</tspan></text></g><g class="highcharts-axis-labels highcharts-yaxis-labels " data-z-index="7"></g></svg></div></div>
             --%>  </div>
            </div>
           
        </div>
					</div>
<!-- CLcontact focusArea -->

 <div class="content" id="chartdiv6">
				<div class="pt-2 pb-2  bg-white rounded desc-header ml-0">
							<div class="d-flex flex-wrap ">
								<div class="col">
									<h2 class="d-inline-block pt-2">Conquest & Loyalty: Focus area Performance</h2>
								</div>
								<div class="col col-160 ml-auto">
								
							</div>
						</div>
					</div>
				<div class="main-content pt-3 position-relative nav-left">
				
					<div class="container-fluid my-3">
						<input type="hidden" value="${month}" id="monthtemp"> <input
							type="hidden" value="${dealer}" id="dealertemp"> <input
							type="hidden" value="${region}" id="regiontemp"> <input
							type="hidden" value="${outlet}" id="outlettemp">

					<!-- graphs -->
					<!-- <div class=" container-fluid mt-3 mb-5">
					
								
								
						<div class="row ">
							<div class="col-md-6 my-3">
								<h4 class="chart-title-heading text-center">Were all
											contact details spelt correctly?</h4>
								<div class="card">
									<div class="card-body">
									
										<div id="container-bar-graph-1" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg1">
									</div>
								</div>
							</div>
							<div class="col-md-6 my-3">
								<h4 class="chart-title-heading text-center">Financing
											or leasing options</h4>
								<div class="card">
									<div class="card-body">
									
										<div id="container-bar-graph-2" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg2">
									</div>
								</div>
							</div>
						</div>
						<div class="row">
						
							<div class="col-md-4 my-3">
							<h4 class="chart-title-heading text-center">Was a test
											drive actively offered?</h4>
								<div class="card">
									<div class="card-body">
										
										<div id="container-bar-graph-3" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg3">
									</div>
								</div>
							</div>
							<div class="col-md-4 my-3">
							<h4 class="chart-title-heading text-center">Individualized
											Offer</h4>
								<div class="card">
									<div class="card-body">
										
										<div id="container-bar-graph-4" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg4">
									</div>
								</div>
							</div>
							<div class="col-md-4 my-3">
							<h4 class="chart-title-heading text-center">Contacted
											in the corresponding time frame?</h4>
								<div class="card">
									<div class="card-body">
										
										<div id="container-bar-graph-5" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg5">
									</div>
								</div>
							</div>
						</div>
					</div> -->
					<div class=" container-fluid mt-3 mb-5">
					
								
							
						<div class="row ">
							<div class="col-md-4 my-3">
							<c:if test="${selected_year == 2019 }">
								 <h4 class="chart-title-heading text-center">Was a test drive actively offered</h4></c:if> 
											 <c:if test="${selected_year == 2020 }">
								 <h4 class="chart-title-heading text-center">Test Drive Offer</h4> </c:if> 
								<div class="card">
									<div class="card-body">
									<c:if test="${selected_year == 2019 }">
										<div id="container-bar-graph-3" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg3">
											</c:if> 
											<c:if test="${selected_year == 2020 }">
										<div id="container-bar-graph-8" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg8">
											</c:if> 
											
									</div>
								</div>
							</div>
							
							<div class="col-md-4 my-3">
							<c:if test="${selected_year == 2019 }">
								 <h4 class="chart-title-heading text-center">Financing
											or leasing options</h4> </c:if>
										 <c:if test="${selected_year == 2020 }">	
							 <h4 class="chart-title-heading text-center">Financing Product Offer</h4>	 
							</c:if>		 
								<div class="card">
									<div class="card-body">
									<c:if test="${selected_year == 2019 }">
										<div id="container-bar-graph-2" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg2">
											</c:if>
											<c:if test="${selected_year == 2020 }">
										<div id="container-bar-graph-7" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg7">
											</c:if>
											
									</div>
								</div>
							</div>
							<c:choose>
							<c:when test="${selected_year ==2019}">
							<div class="col-md-4 my-3">
							 <h4 class="chart-title-heading text-center">Individualized
											Offer</h4> 
					         <!-- <h4 class="chart-title-heading text-center">New Car Offer?</h4> -->
								<div class="card">
									<div class="card-body">
										<div id="container-bar-graph-4" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto">
											<img alt="" src="" id="focuscanvasimg4">
											</div>
											
									</div>
								</div>
							</div>
							</c:when>
							<c:when test="${selected_year ==2020}">
							<div class="col-md-4 my-3">
							<!-- <h4 class="chart-title-heading text-center">Individualized
											Offer</h4> -->
					         <h4 class="chart-title-heading text-center">New Car Offer</h4>
								<div class="card">
									<div class="card-body">
										
										<div id="container-bar-graph-9" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg9">
									</div>
								</div>
							</div>
							</c:when>
							</c:choose>
						</div>
						<div class="row">
						<c:if test="${selected_year == 2019}">
							<div class="col-md-6 my-3 crd-min-hgt">
							
							 <h4 class="chart-title-heading text-center">Were all contact details spelt correctly</h4> 
											<%-- <c:if test="${selected_year == 2020}">
											 <h4 class="chart-title-heading text-center">Test Drive Offer</h4> </c:if> --%>
								<div class="card">
									<div class="card-body">
										
										<div id="container-bar-graph-1" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg1">
											
									</div>
								</div>
							</div>
							
							<div class="col-md-6 my-3 crd-min-hgt">
							
							 <h4 class="chart-title-heading text-center">Contacted
											in the corresponding time frame</h4> 
											<%-- <c:if test="${selected_year == 2020}">
											 <h4 class="chart-title-heading text-center">Follow up on offer within 48 hours</h4> </c:if> --%>
								<div class="card">
									<div class="card-body">
										
										<div id="container-bar-graph-5" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg5">
									</div>
								</div>
							</div>
							</c:if>
							<c:if test="${selected_year == 2020}">
							<div class="col-md-4 my-3 crd-min-hgt">
							
							 <!-- <h4 class="chart-title-heading text-center">Were all contact details spelt correctly</h4>  -->
											
											 <h4 class="chart-title-heading text-center">Lost league</h4> 
								<div class="card">
									<div class="card-body">
										
										<div id="container-bar-graph-6" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg6">
									</div>
								</div>
							</div>
							
							<div class="col-md-4 my-3 crd-min-hgt">
							
							 <!-- <h4 class="chart-title-heading text-center">Contacted
											in the corresponding time frame</h4> --> 
											
											 <h4 class="chart-title-heading text-center">Follow up on offer within 48 hours</h4>
								<div class="card">
									<div class="card-body">
										
										<div id="container-bar-graph-10" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg10">
									</div>
								</div>
							</div>
							<div class="col-md-4 my-3 crd-min-hgt">
						<%-- 	<c:if test="${selected_year == 2019 }">
								 <h4 class="chart-title-heading text-center">Financing
											or leasing options</h4> </c:if> --%>
										
							 <h4 class="chart-title-heading text-center">Courtesy Text Message </h4>	 
								
								<div class="card">
									<div class="card-body">
									
										<div id="container-bar-graph-11" class="bar-container"
											style="min-width: 300px; height: 300px; margin: 0 auto"></div>
											<img alt="" src="" id="focuscanvasimg11">
									</div>
								</div>
							</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div> 

<!-- ignore this ... CL focus is commented in OLD code Start-->
<!-- CLcontact focus -->

	<%-- <div class="content">
	<div class="pt-2 pb-2  bg-white rounded desc-header ml-0">
							<div class="d-flex flex-wrap ">
								<div class="col">
									<h2 class="d-inline-block pt-2">Sales</h2>
								</div>
								<div class="col col-160 ml-auto">
								
							</div>
						</div>
					</div>
                     <div class="main-content pt-3 position-relative nav-left">
                     
            <!-- graphs -->
            <div class="container mt-3 mb-5">
            <div class="row">
              <div class="col-md-4 col-sm-6 my-3">
               <h4 class="chart-title-heading text-center"> Region 1</h4>
                <div class="card">
                     <div class="card-body" style="width: 300px;overflow-x: auto;margin: 0 auto;">
                 <div class="reg-1 ">
                  <div id="region-1" class="bar-container" style=" height: 300px; margin: 0 auto;min-width: <c:out value="${fn:length(region1)*50/2}" />px;max-width: 600px;"></div>
                </div> 
                 <img alt="" src="" id="regioncanvasimg1">
                    </div>
              </div>
              </div>
              <c:forEach var="qBean" items="${region1}">
              <input type="hidden" class="region1" value="${qBean.name}">
              <input type="hidden" class="region1value" value="${qBean.percentage}">
              </c:forEach>
              <c:forEach var="qBean" items="${region2}">
              <input type="hidden" class="region2" value="${qBean.name}">
              <input type="hidden" class="region2value" value="${qBean.percentage}">
              </c:forEach>
              <c:forEach var="qBean" items="${region3}">
              <input type="hidden" class="region3" value="${qBean.name}">
              <input type="hidden" class="region3value" value="${qBean.percentage}">
              </c:forEach>
              <c:forEach var="qBean" items="${region4}">
              <input type="hidden" class="region4" value="${qBean.name}">
              <input type="hidden" class="region4value" value="${qBean.percentage}">
              </c:forEach>
              <c:forEach var="qBean" items="${region5}">
              <input type="hidden" class="region5" value="${qBean.name}">
              <input type="hidden" class="region5value" value="${qBean.percentage}">
              </c:forEach>
              <c:forEach var="qBean" items="${region6}">
              <input type="hidden" class="region6" value="${qBean.name}">
              <input type="hidden" class="region6value" value="${qBean.percentage}">
              </c:forEach>
              <div class="col-md-4 col-sm-6 my-3">
               <h4 class="chart-title-heading text-center"> Region 2</h4>
                <div class="card">
                 <div class="card-body" style="width: 300px;overflow-x: auto;margin: 0 auto;">
               
                  <div id="region-2" class="bar-container" style=" height: 300px; margin: 0 auto;min-width: <c:out value="${fn:length(region2)*50/2}" />px;max-width: 600px;"></div>
                   <img alt="" src="" id="regioncanvasimg2">
                    </div>
              </div>
              </div>
              <div class="col-md-4 col-sm-6 my-3">
                 <h4 class="chart-title-heading text-center"> Region 3</h4>
                <div class="card">
                  <div class="card-body" style="width: 300px;overflow-x: auto;margin: 0 auto;">
                 
                      <div id="region-3" class="bar-container" style=" height: 300px; margin: 0 auto;min-width:  <c:out value="${fn:length(region3)*50/2}" />px;max-width: 600px;"></div>
                       <img alt="" src="" id="regioncanvasimg3">
                  </div>
              </div>
              </div>
            </div>
           <!--  <div class="row">
             
                   <div class="reg-1">
                  <div id="region-1" class="bar-container" style="width: 293px; height: 300px; margin: 0 auto"></div>
                
              </div>
            </div>  -->
            <div class="row">
                <div class="col-md-4 col-sm-6 my-3">
                <h4 class="chart-title-heading text-center"> Region 4</h4>
                  <div class="card">
                  <div class="card-body"  style="width: 300px;overflow-x: auto;margin: 0 auto;">
                
                  <div id="region-4" class="bar-container" style=" height: 300px; margin: 0 auto;min-width:  <c:out value="${fn:length(region4)*50/2}" />px;max-width: 600px;"></div>
                   <img alt="" src="" id="regioncanvasimg4">
                      </div>  
                </div>
                </div>
                <div class="col-md-4 col-sm-6 my-3">
                <h4 class="chart-title-heading text-center"> Region 5</h4>
                  <div class="card">
                    <div class="card-body"  style="width: 300px;overflow-x: auto;margin: 0 auto;">
                      
                        <div id="region-5" class="bar-container" style=" height: 300px; margin: 0 auto;min-width:  <c:out value="${fn:length(region5)*50/2}" />px;max-width: 600px;"></div>
                         <img alt="" src="" id="regioncanvasimg5">
                    </div>
                  </div>
                </div>
                <div class="col-md-4 col-sm-6 my-3">
                  <h4 class="chart-title-heading text-center"> Region 6</h4>
                  <div class="card">
                    <div class="card-body"  style="width: 300px;overflow-x: auto;margin: 0 auto;">
                    
                        <div id="region-6" class="bar-container" style=" height: 300px; margin: 0 auto;min-width:  <c:out value="${fn:length(region6)*50/2}" />px;max-width: 600px;"></div>
                     <img alt="" src="" id="regioncanvasimg6">
                    </div>
                  </div>
                </div>
              </div>
          </div>    
            </div>
           
       </div>
<!-- ignore this ... CL focus is commented in OLD code END-->
<!-- Sales --> --%>

	 <div class="content" id="chartdiv7">
	<div class="pt-2 pb-2  bg-white rounded desc-header ml-0">
							<div class="d-flex flex-wrap ">
								<div class="col">
									<h2 class="d-inline-block pt-2">Discount Analysis</h2>
								</div>
								<div class="col col-160 ml-auto">
								
							</div>
						</div>
					</div>
                      <div class="main-content  position-relative nav-left">
            <div class="container-fluid mt-3 mb-3">
             <label class="mt-4 textCenter"><strong>Discount Offered</strong></label>
              <div class="row">
             
              <div class="col-xl-4 mt-3">
            
                <div class="card">
                  
                  <input type="hidden" value="${amount}" id="discountamount">
								<input type="hidden" value="${yess}" id="discountyes">
								<input type="hidden" value="${nooo}" id="discountno">
                  <div id="customerchartdiscount" style="width: 100%; height: 260px; margin: 0 auto"></div>
                  <img alt="" src="" id="canvasimg24">
                </div>
              </div>
              <div class="col-xl-8  mt-3">
                  <div class="table-responsive">
                 <table class="table table-stripped table-bordered discount-table" style="border-collapse:collapse;">
                      <thead>
                          <tr>
                              <th>Model</th>
                              <c:forEach var="qBean" items="${getDiscountPrices}">
                              <th>${qBean.answer}</th>
                              </c:forEach>
                             
                          </tr>
                      </thead>
                      <tbody>
                      <c:forEach var="qBean" items="${data}" varStatus="count">
                          <tr data-toggle="collapse" data-target=".demo${count.index}">
                              <th class="bg-top">
                              <c:if test="${qBean.model_name!='X1' && qBean.model_name!='X2' && qBean.model_name!='X3'}">
                              <img class="float-left" src="<%=UI_PATH%>/assets/graphs/images/add-more.png">
                              </c:if>
                              ${qBean.model_name}</th>
                              <c:if test="${qBean.model_name=='X1' || qBean.model_name=='X2' || qBean.model_name=='X3'}">
                              <c:forEach var="qBean1" items="${qBean.answerDetails}" >
                              <c:forEach var="qBean2" items="${qBean1.answerDetails}" >
                              <td class="bg-top">${qBean2.oneValue}</td>
                              </c:forEach>
                              </c:forEach>
                              </c:if>
                              <c:if test="${qBean.model_name!='X1' && qBean.model_name!='X2' && qBean.model_name!='X3'}">
                              <c:forEach var="qBean1" items="${qBean.answerDetails}" varStatus="count1">
                              <c:if test="${count1.index=='0'}">
                              <c:forEach var="qBean2" items="${qBean1.answerDetails}" >
                              <td class="bg-top"></td>
                              </c:forEach>
                              </c:if>
                              </c:forEach>
                              </c:if>
                          </tr>
                          <c:if test="${qBean.model_name!='X1' && qBean.model_name!='X2' && qBean.model_name!='X3'}">
                          <c:forEach var="qBean1" items="${qBean.answerDetails}" >
                          <tr>
                          	 
                              <th class="hiddenRow">
                                  <div class="collapse demo${count.index}">${qBean1.varient_name}</div>
                              </th>
                              <c:forEach var="qBean2" items="${qBean1.answerDetails}">
                              <td class="hiddenRow">
                                  <div class="collapse demo${count.index}">${qBean2.oneValue}</div>
                              </td>
                              </c:forEach>
                              
                          </tr>
                          </c:forEach>
                          </c:if>
                          </c:forEach>
                          
                          
                      </tbody>
                  </table>     
                      </div>
                      
              </div>
            </div>
          </div>    
        </div>
       

                </div> 

<!-- Discount End-->
<div class="content" id="chartdiv8">
		
					<div class="main-content  position-relative nav-left">
						<div class="position-relative">

							<div class="pt-3 pb-3  bg-white rounded desc-header"
								style="padding-left: 0px !important; margin-left: 18px;">
								<div class="d-flex flex-wrap ">
									<div class="col">
										<h2 class="d-inline-block pt-2">
											<b>Competition Overview</b>
										</h2>
									</div>

								</div>
							</div>
						</div>

						<div class="container-fluid">
     <div class="row">
							<div class="col-lg-4 mt-2">
								<h4 style="background: #fff;"
											class="chart-title-heading  text-center">Competition
											Comparison</h4>
									<div class="card-box p-0 border-0 my-2">
										
										<c:forEach var="qBean" items="${getBrandData}">
											<div
												class="card-body-wrap d-flex flex-wrap py-2 completion-row">
												<div class="col-12">
													
													<div class="d-flex">
													
													<div>
													<h6>${qBean.brand_name}</h6>
													<p class="mb-0">${qBean.percentage}%</p>
													<p class="mb-0">MYS Score</p>
													</div>
													
													<div class="ml-auto">
													<img alt="" style="width:80px;height:80px;"  src="<%=UI_PATH%>/design/images/<c:if test="${qBean.brand_name=='BMW'}">bmw.png</c:if><c:if test="${qBean.brand_name=='AUDI'}">Audi1.png</c:if><c:if test="${qBean.brand_name=='Mercedes Benz'}">merc.png</c:if>">
													
													</div>
													</div>
												</div>
												
											</div>
										</c:forEach>
									
									</div>
								</div>

          <%-- <div class="col-lg-8 my-2 ">
								<h4 style="background: #fff"
										class="chart-title-heading  text-center">Mystery
										Shopping Score Trends</h4>
										<div class=" card shadow-card my-2">
									<div class=" mb-5" style="margin-top: -36px;">
										
									</div>
									<input type="hidden" value="${dealer}" id="dealertemp">
									<input type="hidden" value="${region}" id="regiontemp">
									<input type="hidden" value="${outlet}" id="outlettemp">
									

									<div class="border-0 my-3">
										<div id="graph-container"
											style="min-width: 310px; height: 350px; margin: 0 auto"></div>
										<img alt="" src="" id="graphimg">
									</div>
									</div>
								</div> --%>
          <div class="col-lg-8 my-2 ">
								<h4 style="background: #fff"
										class="chart-title-heading  text-center">Mystery
										Shopping Score Trends</h4>
										<div class=" card shadow-card my-2">
									<div class=" mb-5" style="margin-top: -36px;">
										
									</div>
									<input type="hidden" value="${dealer_id}" id="dealertemp">
									<input type="hidden" value="${region_id}" id="regiontemp">
									<input type="hidden" value="${outlet_id}" id="outlettemp">
									

									<div class="border-0 my-3">
										<div id="graph-container"
											style="min-width: 310px; height: 350px; margin: 0 auto"></div>
										<img alt="" src="" id="graphimg">
									</div>
									</div>
								</div>
                   
      </div>
						</div>
						<%-- <div class="">
							<div></div>
								<h4  style="background: #fff"
									class="chart-title-heading  text-center"
									style="color: white;">Regional Analysis</h4>
							<div class="mt-2  card shadow-card" style="border: 0px solid black;">
							
								<ul
									class="list-type d-flex flex-wrap justify-content-center mt-4">
									<!-- 
								<li><div class="red-dot"></div>
									<p>Audi</p></li>
								<li><div class="blue-dot"></div>
									<p>BMW</p></li>
								<li><div class="grey-dot"></div>
									<p>JLR</p></li>
								<li><div class="black-dot"></div>
									<p>Mercedes</p></li> -->
									<c:forEach var="status" items="${getRegionData}"
										varStatus='loop'>
										<c:if test="${loop.index==1}">
											<c:forEach var="status1" items="${status.answerDetails}"
												varStatus="loop1">
												<li><div
														<c:if test="${loop1.index==0}">class="blue-dot"</c:if>
														<c:if test="${loop1.index==1}">class="red-dot"</c:if>
														<c:if test="${loop1.index==2}">class="black-dot"</c:if>
														<c:if test="${loop1.index==3}">class="red-dot"</c:if>></div>
													<p>${status1.brand_name}</p></li>
											</c:forEach>
										</c:if>
									</c:forEach>
								</ul>

								<div class="container-fluid">
									<div class="row d-flex justify-content-center my-3">
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<div id="column-bar-chart1" class="bar-container"
													style="width: 100%; height: 270px; margin: 0 auto"></div>
												<img alt="" src="" id="competitioncanvasimg1"> <span><center>Region
														1</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<div id="column-bar-chart2" class="bar-container"
													style="width: 100%; height: 270px; margin: 0 auto"></div>
												<img alt="" src="" id="competitioncanvasimg2"> <span><center>Region
														2</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<div id="column-bar-chart3" class="bar-container"
													style="width: 100%; height: 270px; margin: 0 auto"></div>
												<img alt="" src="" id="competitioncanvasimg3"> <span><center>Region
														3</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<div id="column-bar-chart4" class="bar-container"
													style="width: 100%; height: 270px; margin: 0 auto"></div>
												<img alt="" src="" id="competitioncanvasimg4"> <span><center>Region
														4</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<div id="column-bar-chart5" class="bar-container"
													style="width: 100%; height: 270px; margin: 0 auto"></div>
												<img alt="" src="" id="competitioncanvasimg5"> <span><center>Region
														5</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<div id="column-bar-chart6" class="bar-container"
													style="width: 100%; height: 270px; margin: 0 auto"></div>
												<img alt="" src="" id="competitioncanvasimg6"> <span><center>Region
														6</center></span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div> --%>
						<div class="">
							<div></div>
								<h4  style="background: #fff"
									class="chart-title-heading  text-center"
									style="color: white;">Regional Analysis</h4>
							<div class="mt-2  card shadow-card" style="border: 0px solid black;">
							
								<ul
									class="list-type d-flex flex-wrap justify-content-center mt-4">
									<!-- 
								<li><div class="red-dot"></div>
									<p>Audi</p></li>
								<li><div class="blue-dot"></div>
									<p>BMW</p></li>
								<li><div class="grey-dot"></div>
									<p>JLR</p></li>
								<li><div class="black-dot"></div>
									<p>Mercedes</p></li> -->
									<c:forEach var="status" items="${getRegionData}"
										varStatus='loop'>
										<c:if test="${loop.index==1}">
											<c:forEach var="status1" items="${status.answerDetails}"
												varStatus="loop1">
												<li><div
														<c:if test="${loop1.index==0}">class="blue-dot"</c:if>
														<c:if test="${loop1.index==1}">class="red-dot"</c:if>
														<c:if test="${loop1.index==2}">class="black-dot"</c:if>
														<c:if test="${loop1.index==3}">class="red-dot"</c:if>></div>
													<p>${status1.brand_name}</p></li>
											</c:forEach>
										</c:if>
									</c:forEach>
								</ul>

								<div class="container-fluid">
									<div class="row d-flex justify-content-center my-3">
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<!-- <div id="column-bar-chart1" class="bar-container" style="width: 100%; height: 270px; margin: 0 auto"></div> -->
												 <div id="column-bar-chart1" class="bar-container" style="width: 100%; height: 300px; margin: 0 auto"></div>
												<img alt="" src="" id="competitioncanvasimg1"> <span><center>Region
														1</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<!-- <div id="column-bar-chart2" class="bar-container" style="width: 100%; height: 270px; margin: 0 auto"></div> -->
												<div id="column-bar-chart2" class="bar-container" style="width: 100%; height: 300px; margin: 0 auto"></div>
												<img alt="" src="" id="competitioncanvasimg2"> <span><center>Region
														2</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<!-- <div id="column-bar-chart3" class="bar-container" style="width: 100%; height: 270px; margin: 0 auto"></div> -->
												<div id="column-bar-chart3" class="bar-container" style="width: 100%; height: 300px; margin: 0 auto"></div>
												<img alt="" src="" id="competitioncanvasimg3"> <span><center>Region
														3</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<!-- <div id="column-bar-chart4" class="bar-container" style="width: 100%; height: 270px; margin: 0 auto"></div> -->
												<div id="column-bar-chart4" class="bar-container" style="width: 100%; height: 300px; margin: 0 auto"></div>
												<img alt="" src="" id="competitioncanvasimg4"> <span><center>Region
														4</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<!-- <div id="column-bar-chart5" class="bar-container" style="width: 100%; height: 270px; margin: 0 auto"></div> -->
												<div id="column-bar-chart5" class="bar-container" style="width: 100%; height: 300px; margin: 0 auto"></div>
												<img alt="" src="" id="competitioncanvasimg5"> <span><center>Region
														5</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<!-- <div id="column-bar-chart6" class="bar-container" style="width: 100%; height: 270px; margin: 0 auto"></div> -->
												<div id="column-bar-chart6" class="bar-container" style="width: 100%; height: 300px; margin: 0 auto"></div>
												<img alt="" src="" id="competitioncanvasimg6"> <span><center>Region
														6</center></span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

<c:forEach var="status" items="${getRegionData}" varStatus='loop'>

		<c:forEach var="status1" items="${status.answerDetails}">
			<input value="${status1.brand_name}" id="regionbrand${loop.index+1}"
				type="hidden">
			<input value="${status1.percentage}" id="regionper${loop.index+1}"
				type="hidden">
		</c:forEach>

	</c:forEach>


	<input value='${dataarray}' id="array" type="hidden">
<!-- Competition Overview -->

<div class="content" id="chartdiv9">
		<div class="pt-2 pb-2  bg-white rounded desc-header ml-0">
							<div class="d-flex flex-wrap ">
								<div class="col">
									<h2 class="d-inline-block pt-2">Accessories & Lifestyle Analysis</h2>
								</div>
								<div class="col col-160 ml-auto">
								
							</div>
						</div>
			</div>
				<div class="main-content  position-relative nav-left">
				
					<div class="container mt-5">

						<div class="sub-card mt-12">
							<h4 class="chart-title-heading text-center">What take home material was provided
								by the dealership to you during/within 24 hrs of your visit?</h4>
							<div class="container-fluid  mb-5">
								<div class="row">
									<div class="col-md-12">
										<div class="card">
											<div class="card-body">
												<div id="container-bar-graph1" class="bar-container"
													style="height: 425px; margin: 0 auto"></div>
												<img alt="" src="" id="lifestylecanvasimg1">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="sub-card mt-12">
							<h4 class="chart-title-heading text-center">Did the salesperson proactively
								offer certain additional option and accessories during the
								vehicle's configuration process?</h4>
							<div class="container-fluid  mb-5">
								<div class="row">
									<div class="col-md-12">
										<div class="card">
										<div class="col-12">
											<div id="container-bar-graph2" class="bar-container"
												style="height: 425px; margin: 0 auto"></div>
											<img alt="" src="" id="lifestylecanvasimg2">
										</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>

			</div> 

<!-- Training Need Analysis -->

	 <div class="content" id="chartdiv10">
	
	<div class="pt-2 pb-2  bg-white rounded desc-header ml-0">
							<div class="d-flex flex-wrap ">
								<div class="col">
									<h2 class="d-inline-block pt-2">Training Need Analysis</h2>
								</div>
								<div class="col col-160 ml-auto">
								
							</div>
						</div>
					</div>
				<div class="main-content  position-relative nav-left">
					
					<div class="container-fluid">
						<div class="row">
							<div class="col-md-4">
							<h3 class="text-center chart-title-heading">Need Analysis</h3>
								<div class="card mt-1">									
									<div class="px-2 pb-2">
										<c:forEach var="qBean" items="${needAnalysisScore}" varStatus="loop">
											<div class="row">
												<div class="col-md-8">
													<label>${qBean.subQuestion}</label>
												</div>
												<div class="col-md-4">
													<label class="percent-level">${qBean.score}%</label>
													<div class="progress bar-radius">
														<div class="progress-bar" role="progressbar"
															style="width: ${qBean.score}%;" aria-valuenow="75"
															aria-valuemin="0" aria-valuemax="100"></div>
													</div>
												</div>
											</div>
											<c:if test="${!loop.last}">
												<hr class="hr-margins mt-0">
											</c:if>

										</c:forEach>
									</div>
								</div>
							</div>
							<div class="col-md-4">
							<h3 class="text-center chart-title-heading">Feature
										Presentation</h3>
								<div class="card mt-1">
									<div class="px-2 pb-2">
										<c:forEach var="qBean" items="${featurePresentationScore}" varStatus="loop">
											<div class="row">
												<div class="col-md-8">
													<label>${qBean.subQuestion}</label>
												</div>
												<div class="col-md-4">
													<label class="percent-level">${qBean.score}%</label>
													<div class="progress bar-radius">
														<div class="progress-bar" role="progressbar"
															style="width: ${qBean.score}%;" aria-valuenow="75"
															aria-valuemin="0" aria-valuemax="100"></div>
													</div>
												</div>
											</div>
											<c:if test="${!loop.last}">
												<hr class="hr-margins mt-0">
											</c:if>

										</c:forEach>
										
									</div>
								</div>
							</div>
							<div class="col-md-4">
							<h3 class="text-center chart-title-heading">Five Point
										Presentation</h3>
								<div class="card mt-1">									
									<div class="px-2 pb-2">
										<c:forEach var="qBean" items="${fivePointScore}" varStatus="loop">
											<div class="row">
												<div class="col-md-8">
													<label>${qBean.subQuestion}</label>
												</div>
												<div class="col-md-4">
													<label class="percent-level">${qBean.score}%</label>
													<div class="progress bar-radius">
														<div class="progress-bar" role="progressbar"
															style="width: ${qBean.score}%;" aria-valuenow="75"
															aria-valuemin="0" aria-valuemax="100"></div>
													</div>
												</div>
											</div>
											<c:if test="${!loop.last}">
												<hr class="hr-margins  mt-0">
											</c:if>

										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						<c:forEach var="qBean" items="${ratingstandard}">
						<input id="subQuestion" class="subQuestion"  value="${qBean.subQuestion}" type="hidden">
						<input id="score" class="score" value="${qBean.score}" type="hidden">
						</c:forEach>
						<div class="row mt-4">
					
							<div class="col-8">
								<h3 class="text-center chart-title-heading">Rating Standards</h3>
								<div class="card pt-5">
									<div id="container-fluid"
										style="min-width: 310px; height: 400px; margin: 0 auto" class="w-100"></div>
										<img alt="" src="" id="canvasimg33">
								</div>
							</div>
							<div class="col-4">
							<h3 class="text-center chart-title-heading">Product Presentation</h3>
								<div class="card">									
									<div class=" p-2">
										<div class="card-head card-line-skyblue">
											<strong>${productpercentage}%</strong>
											<p class="mb-0">Product Presentation</p>
										</div>
									</div>
									<div class="p-2">
										<div class="card-head card-line-skyblue">
											<strong>${digital}%</strong>
											<p class="mb-0">Digital Tools Used</p>
										</div>
									</div>
								</div>
								<div class="py-4">
								<h3 class="text-center chart-title-heading">Follow Up</h3>
									<div class="card ">										
										<div class=" p-2">
											<div class="card-head card-line-skyblue">
												<strong>${days} days</strong>
												<p class="mb-0">Average Time Taken</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>

			</div> 


<!-- Life Style -->
		<div class="content" id="chartdiv11">
		<div class="pt-2 pb-2  bg-white rounded desc-header ml-0">
							<div class="d-flex flex-wrap ">
								<div class="col">
									<h2 class="d-inline-block pt-2">Financial Service Analysis</h2>
								</div>
								<div class="col col-160 ml-auto">
								
							</div>
						</div>
			</div>
						  <%-- <div class="row">
                        <div class="col-md-7">
                        <h4 class="text-center chart-main-head">Did the dealer staff actively offer BMW Financial Services?</h4>
                     
                               <div class="sub-card mt-4 shadow">
                         <div class="container-fluid mt-3 mb-5">
                          <div class="row">
                            <div class="col-md-12">
                              <div class="">
                                <div class="card-body">
                                  <div class="scroll-x">
                                 <div id="container-bar-graph-financial1" style="height: 425px; margin: 0 auto"></div>
                                 <img alt="" src="" id="canvasimg34">
                               </div>
                                </div>
                              </div>
                            </div>
                          </div>
                      </div>
                 </div>
 <h4 class="text-center chart-main-head">Did the Staff proactively offer or explain the 360 buy back program?</h4>
                     
                        <div class="sub-card mt-2 shadow">
                        <div class="container-fluid mt-3 mb-5">
                          <div class="row">
                            <div class="col-md-12">
                              <div class="">
                                <div class="card-body">
                                   <div class="scroll-x">
                                     <div id="container-bar-graph-financial" style="height: 425px; margin: 0 auto"></div>
                                     <img alt="" src="" id="canvasimg35">
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                      </div>
                 </div>
                        </div>
                         <div class="col-md-5 ">
                          <h4 class="text-center chart-main-head">Financial Options offered breakdown</h4>
                         
                          <div class="sub-card mt-4 shadow">
                            <div class="" id="financialprocesschart" style="width: 100%; height: 470px; margin: 0 auto"></div>
                           <img alt="" src="" id="canvasimg36">
                           </div>
                           <c:if test="${selected_year == 2020}">
                    <h6 class="text-center  chart-title-heading "><b>Did staff proactively handover finance offer ?</b></h6>
                           <div class="sub-card mt-2 shadow">
                         <div class="container-fluid mt-3 mb-5">
                          <div class="row">
                            <div class="col-md-12">
                              <div class="">
                                <div class="card-body">
                                  <div class="scroll-x">
                                 <div id="container-bar-graph-financial2" style="height: 425px; margin: 0 auto"></div>
                                 <img alt="" src="" id="canvasimg37">
                               </div>
                                </div>
                              </div>
                            </div>
                          </div>
                      </div>
                 </div>
                    </c:if>
                         </div>
                      </div> --%>
                      <div class="row">
                        <div class="col-md-6">
                        <h6 class="text-center  chart-title-heading "><b>Did the dealer staff actively offer BMW Financial Services?</b></h6>
                     
                          <div class="sub-card mt-4 shadow">
                         <div class="container-fluid mt-3 mb-5">
                          <div class="row">
                            <div class="col-md-12">
                              <div class="">
                                <div class="card-body">
                                  <div class="scroll-x">
                                 <div id="container-bar-graph-financial" style="height: 425px; margin: 0 auto"></div>
                                 <img alt="" src="" id="canvasimg34">
                               </div>
                                </div>
                              </div>
                            </div>
                          </div>
                      </div>
                 </div>
                 <h6 class="text-center  chart-title-heading "><b>Did the Staff proactively offer or explain the 360 buy back program?</b></h6>
                        <div class="sub-card mt-2 shadow">
                        <div class="container-fluid mt-3 mb-5">
                          <div class="row">
                            <div class="col-md-12">
                              <div class="">
                                <div class="card-body">
                                   <div class="scroll-x">
                                     <div id="container-bar-graph-financial1" style="height: 425px; margin: 0 auto"></div>
                                     <img alt="" src="" id="canvasimg35">
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                      </div>
                 </div>
                        </div>
                         <div class="col-md-6 ">
                          <h6 class="text-center  chart-title-heading "><b>Financial Options offered breakdown ?</b></h6>
                          <div class="sub-card mt-4 shadow">
                            <div class="" id="financialprocesschart" style="width: 100%; height: 470px; margin: 0 auto"></div>
                           <img alt="" src="" id="canvasimg36">
                           </div><br>
                           <c:if test="${selected_year == 2020 }">
                           <h6 class="text-center  chart-title-heading "><b>Did staff proactively handover finance offer ?</b></h6>
                           <div class="sub-card mt-2 shadow">
                         <div class="container-fluid mt-3 mb-5">
                          <div class="row">
                            <div class="col-md-12">
                              <div class="">
                                <div class="card-body">
                                  <div class="scroll-x">
                                 <div id="container-bar-graph-financial2" style="width: 100%; height: 425px; margin: 0 auto"></div>
                                 <img alt="" src="" id="canvasimg37">
                               </div>
                                </div>
                              </div>
                            </div>
                          </div>
                      </div>
                 </div>
                        </c:if>   
                 </div>
                         </div> 
                    
    
					</div>

 
<!--  Financial Service Analysis -->
	<div class="content" id="chartdiv12">
	<div class="pt-2 pb-2  bg-white rounded desc-header ml-0">
							<div class="d-flex flex-wrap ">
								<div class="col">
									<h2 class="d-inline-block pt-2">BPS Analysis</h2>
								</div>
								<div class="col col-160 ml-auto">
								
							</div>
						</div>
					</div>
				<div class="main-content  position-relative nav-left">
				<div class="container">
						<div class="sub-card mt-5">
							<h4 class="chart-title-heading text-center">Did the dealer staff offer to create
								a Trade-in Offer for the current customer vehicle?</h4>
							<div class="container-fluid mt-2">
								<div class="row">
									<div class="col-md-12">
										<div class="card">
											<div class="card-body">
												<div class="scroll-x">
													<div id="container-bar-graph-bps" style="height: 425px; margin: 0 auto"></div>
														  <img alt="" src="" id="canvasimg38"> 
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>

			</div> 

<!-- BPS Analysis -->

	</div>
	</div>

</div>

	<!-- ============================================================== -->
	<!-- End Right content here -->
	<!-- ============================================================== -->

	<!-- END wrapper -->



	<!-- jQuery  -->
	<script
		src="<%=UI_PATH%>/assets/js/jquery.min.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/popper.min.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/bootstrap.min.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/metisMenu.min.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/waves.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/jquery.slimscroll.js"></script>

	<!-- Jvector map -->
	<script
		src="<%=UI_PATH%>/plugins/jvectormap/jquery-jvectormap-2.0.2.min.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/jvectormap/gdp-data.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/jvectormap/jquery-jvectormap-us-aea-en.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/jvectormap/jquery-jvectormap-uk-mill-en.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/jvectormap/jquery-jvectormap-au-mill.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/jvectormap/jquery-jvectormap-us-il-chicago-mill-en.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/jvectormap/jquery-jvectormap-ca-lcc.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/jvectormap/jquery-jvectormap-de-mill.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/jvectormap/jquery-jvectormap-in-mill.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/jvectormap/jquery-jvectormap-asia-mill.js"></script>
	<script
		src="<%=UI_PATH%>/assets/pages/jquery.jvectormap.init.js"></script>
	<!-- App js -->
	<script
		src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>
	<script
		src="<%=UI_PATH%>/assets/graphs/js/custom-script.js"></script>
		
		
		<script src="https://code.highcharts.com/highcharts.js"></script>
	<script
		src="<%=UI_PATH%>/assets/graphs/js/processexcellence-chart.js"></script> 	
	<script
		src="<%=UI_PATH%>/assets/graphs/js/customertreatment-chart.js"></script>
<%-- <script
		src="<%=request.getContextPath()%>/resources/assets/graphs/js/ytdperformance-chart.js"></script> --%>
	<script
		src="<%=UI_PATH%>/assets/graphs/js/custom-script.js"></script> 
		    <script src="<%=UI_PATH%>/assets/graphs/js/ytdtrend-chart.js"></script>
		        <script src="<%=UI_PATH%>/assets/graphs/js/onlinesaleperformance.js"></script>
		        
   <script
		src="<%=UI_PATH%>/assets/graphs/js/allgraphCLFocus.js"></script>
		        
		        
	<script
		src="<%=UI_PATH%>/assets/graphs/js/canvasjs.min.js"></script>
	<script
		src="<%=UI_PATH%>/assets/graphs/js/crm-compliance.js"></script>
	<script
		src="<%=UI_PATH%>/assets/graphs/js/crm-timelines.js"></script>
		
		<script
		src="<%=UI_PATH%>/assets/graphs/js/response-email-chart2.js"></script>
	<script
		src="<%=UI_PATH%>/assets/graphs/js/response-standards-chart2.js"></script>
	<script
		src="<%=UI_PATH%>/assets/graphs/js/dealership-chart2.js"></script>
	<script
		src="<%=UI_PATH%>/assets/graphs/js/appointment-chart2.js"></script>
		
	
     <script src="<%=UI_PATH%>/assets/graphs/js/salesregion1.js"></script>
      <script
		src="<%=UI_PATH%>/assets/graphs/js/shopping-score-chart.js"></script> 
	<script
		src="<%=UI_PATH%>/assets/graphs/js/reginal-analysis-bar-chart.js"></script>
     
     <script
		src="<%=UI_PATH%>/assets/graphs/js/training-need-analysis.js"></script>
          <script src="<%=UI_PATH%>/assets/graphs/js/customer-treat2.js"></script>
    <script
		src="<%=UI_PATH%>/assets/graphs/js/finanacial-analysis.js"></script>

    <%-- <script src="<%=request.getContextPath()%>/resources/assets/graphs/js/service-analysis2.js"></script>  --%>

<script>
	  
        $(".form-control").change(function(){
            var data={};
            data["local"]=$(".form-control").val();
            
        });
        </script>
        
<script src="https://code.highcharts.com/highcharts.js"></script>  
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.5.0-beta4/html2canvas.svg.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<!-- Load Pako ZLIB library to enable PDF compression -->
  <script src="https://kendo.cdn.telerik.com/2017.3.913/js/pako_deflate.min.js"></script>
<script>
      // Import DejaVu Sans font for embedding

      // NOTE: Only required if the Kendo UI stylesheets are loaded
      // from a different origin, e.g. cdn.kendostatic.com
      kendo.pdf.defineFont({
          "DejaVu Sans"             : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans.ttf",
          "DejaVu Sans|Bold"        : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Bold.ttf",
          "DejaVu Sans|Bold|Italic" : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Oblique.ttf",
          "DejaVu Sans|Italic"      : "https://kendo.cdn.telerik.com/2016.2.607/styles/fonts/DejaVu/DejaVuSans-Oblique.ttf",
          "WebComponentsIcons"      : "https://kendo.cdn.telerik.com/2017.1.223/styles/fonts/glyphs/WebComponentsIcons.ttf"
      });
  </script>
<!-- Overallperformance -->

<script>
EXPORT_WIDTH = 1;

function save_chart1(chart) {
    var render_width = EXPORT_WIDTH;
    var render_height = render_width * chart.chartHeight / chart.chartWidth

    // Get the cart's SVG code
    var svg = chart.getSVG({
        exporting: {
            sourceWidth: chart.chartWidth,
            sourceHeight: chart.chartHeight
        }
    });

    // Create a canvas
    var canvas = document.createElement('canvas');
    canvas.height = render_height;
    canvas.width = render_width;
    document.body.appendChild(canvas);

    // Create an image and draw the SVG onto the canvas
    var image = new Image;
    image.onload = function() {
        canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
    };
   $("#ytdchart").hide();
    $("#canvasimg1").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));
    
}


function save_chart2(chart) {
    var render_width = EXPORT_WIDTH;
    var render_height = render_width * chart.chartHeight / chart.chartWidth

    // Get the cart's SVG code
    var svg = chart.getSVG({
        exporting: {
            sourceWidth: chart.chartWidth,
            sourceHeight: chart.chartHeight
        }
    });

    // Create a canvas
    var canvas = document.createElement('canvas');
    canvas.height = render_height;
    canvas.width = render_width;
    document.body.appendChild(canvas);

    // Create an image and draw the SVG onto the canvas
    var image = new Image;
    image.onload = function() {
        canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
    };
   $("#processchart").hide();
    $("#canvasimg2").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));
    
}

function save_chart3(chart) {
    var render_width = EXPORT_WIDTH;
    var render_height = render_width * chart.chartHeight / chart.chartWidth

    // Get the cart's SVG code
    var svg = chart.getSVG({
        exporting: {
            sourceWidth: chart.chartWidth,
            sourceHeight: chart.chartHeight
        }
    });

    // Create a canvas
    var canvas = document.createElement('canvas');
    canvas.height = render_height;
    canvas.width = render_width;
    document.body.appendChild(canvas);

    // Create an image and draw the SVG onto the canvas
    var image = new Image;
    image.onload = function() {
        canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
    };
   $("#customerchart").hide();
    $("#canvasimg3").attr('src','data:image/svg+xml;base64,' +btoa(unescape(encodeURIComponent(svg)))
);
    
}
function save_chart4(chart) {
    var render_width = EXPORT_WIDTH;
    var render_height = render_width * chart.chartHeight / chart.chartWidth

    // Get the cart's SVG code
    var svg = chart.getSVG({
        exporting: {
            sourceWidth: chart.chartWidth,
            sourceHeight: chart.chartHeight
        }
    });

    // Create a canvas
    var canvas = document.createElement('canvas');
    canvas.height = render_height;
    canvas.width = render_width;
    document.body.appendChild(canvas);

    // Create an image and draw the SVG onto the canvas
    var image = new Image;
    image.onload = function() {
        canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
    };
   $("#Onlinesale").hide();
    $("#canvasimg4").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));
    
}


</script>

<!-- Overallperformance -->


<!-- CRM -->

<script>

function save_chart5(chart) {
    var render_width = EXPORT_WIDTH;
    var render_height = render_width * chart.chartHeight / chart.chartWidth

    // Get the cart's SVG code
    var svg = chart.getSVG({
        exporting: {
            sourceWidth: chart.chartWidth,
            sourceHeight: chart.chartHeight
        }
    });

    // Create a canvas
    var canvas = document.createElement('canvas');
    canvas.height = render_height;
    canvas.width = render_width;
    document.body.appendChild(canvas);

    // Create an image and draw the SVG onto the canvas
    var image = new Image;
    image.onload = function() {
        canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
    };
   $("#crm-container-bar-graph").hide();
    $("#canvasimg5").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));
    
}

function save_chart55(chart) {
    var render_width = EXPORT_WIDTH;
    var render_height = render_width * chart.chartHeight / chart.chartWidth

    // Get the cart's SVG code
    var svg = chart.getSVG({
        exporting: {
            sourceWidth: chart.chartWidth,
            sourceHeight: chart.chartHeight
        }
    });

    // Create a canvas
    var canvas = document.createElement('canvas');
    canvas.height = render_height;
    canvas.width = render_width;
    document.body.appendChild(canvas);

    // Create an image and draw the SVG onto the canvas
    var image = new Image;
    image.onload = function() {
        canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
    };
   $("#chartContainer").hide();
    $("#chartcanvasimgtimelines").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));
    
}
</script>

<!-- CRM -->

<!-- Conquest contact -->

<script type="text/javascript">
function save_chart6(chart) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight / chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
    exporting: {
        sourceWidth: chart.chartWidth,
        sourceHeight: chart.chartHeight
    }
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
};
$("#responsecontainer").hide();
$("#canvasimg6").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));

}


function save_chart7(chart) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight / chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
    exporting: {
        sourceWidth: chart.chartWidth,
        sourceHeight: chart.chartHeight
    }
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
};
$("#responsecontainer2").hide();
$("#canvasimg7").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));

}

function save_chart8(chart) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight / chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
    exporting: {
        sourceWidth: chart.chartWidth,
        sourceHeight: chart.chartHeight
    }
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
};
$("#conq-loy-dealer").hide();
$("#canvasimg8").attr('src','data:image/svg+xml;base64,' +btoa(unescape(encodeURIComponent(svg)))
);

}
function save_chart9(chart) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight / chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
    exporting: {
        sourceWidth: chart.chartWidth,
        sourceHeight: chart.chartHeight
    }
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
};
$("#appointment").hide();
$("#canvasimg9").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));

}

</script>
<!-- Conquest contact -->


<!-- Conquest telephone -->


<script>

EXPORT_WIDTH = 1;

function save_chart10(chart) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight / chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
    exporting: {
        sourceWidth: chart.chartWidth,
        sourceHeight: chart.chartHeight
    }
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
};
$("#responsecontainerTele").hide();
$("#canvasimg10").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));

}


function save_chart11(chart) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight / chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
    exporting: {
        sourceWidth: chart.chartWidth,
        sourceHeight: chart.chartHeight
    }
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
};
$("#responsecontainerTele2").hide();
$("#canvasimg11").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));

}

function save_chart12(chart) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight / chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
    exporting: {
        sourceWidth: chart.chartWidth,
        sourceHeight: chart.chartHeight
    }
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
};
$("#dealers").hide();
$("#canvasimg12").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));

}

</script>

<!-- Conquest telephone -->

<!-- Conquest focus -->

<script>

function save_chart13(chart,val) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight / chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
    exporting: {
        sourceWidth: chart.chartWidth,
        sourceHeight: chart.chartHeight
    }
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
};
$("#container-bar-graph-"+val).hide();
$("#focuscanvasimg"+val).attr('src','data:image/svg+xml;base64,' + btoa(unescape(encodeURIComponent(svg))));

}


</script>

<!-- Conquest focus -->

<!-- Sales -->
 <!-- <script>
function save_chart14(chart,val) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight / chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
    exporting: {
        sourceWidth: chart.chartWidth,
        sourceHeight: chart.chartHeight
    }
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
};
$("#region-"+val).hide();
$("#competitioncanvasimg"+val).attr('src','data:image/svg+xml;base64,' + btoa(unescape(encodeURIComponent(svg)))
);

}

</script>  -->
<!-- Sales -->

<!-- discount -->
<script>
function save_chart15(chart) {
    var render_width = EXPORT_WIDTH;
    var render_height = render_width * chart.chartHeight / chart.chartWidth

    // Get the cart's SVG code
    var svg = chart.getSVG({
        exporting: {
            sourceWidth: chart.chartWidth,
            sourceHeight: chart.chartHeight
        }
    });

    // Create a canvas
    var canvas = document.createElement('canvas');
    canvas.height = render_height;
    canvas.width = render_width;
    document.body.appendChild(canvas);

    // Create an image and draw the SVG onto the canvas
    var image = new Image;
    image.onload = function() {
        canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
    };
   $("#customerchartdiscount").hide();
    $("#canvasimg24").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));
    
}
</script>
<!-- discount -->

<!-- Competition overview -->

<script>
	EXPORT_WIDTH = 1;

	function save_chart16(chart, val) {
		var render_width = EXPORT_WIDTH;
		var render_height = render_width * chart.chartHeight
				/ chart.chartWidth

		// Get the cart's SVG code
		var svg = chart.getSVG({
			exporting : {
				sourceWidth : chart.chartWidth,
				sourceHeight : chart.chartHeight
			}
		});

		// Create a canvas
		var canvas = document.createElement('canvas');
		canvas.height = render_height;
		canvas.width = render_width;
		document.body.appendChild(canvas);

		// Create an image and draw the SVG onto the canvas
		var image = new Image;
		image.onload = function() {
			canvas.getContext('2d').drawImage(this, 0, 0, render_width,
					render_height);
		};
		//image.src = 'data:image/svg+xml;base64,' + window.btoa(svg);
		//  alert(window.btoa(svg))
		$("#column-bar-chart" + val).hide();
		$("#competitioncanvasimg" + val).attr('src',
				'data:image/svg+xml;base64,' + window.btoa(svg));

	}
	 function save_chart17(chart) {
		var render_width = EXPORT_WIDTH;
		var render_height = render_width * chart.chartHeight
				/ chart.chartWidth

		// Get the cart's SVG code
		var svg = chart.getSVG({
			exporting : {
				sourceWidth : chart.chartWidth,
				sourceHeight : chart.chartHeight
			}
		});

		// Create a canvas
		var canvas = document.createElement('canvas');
		canvas.height = render_height;
		canvas.width = render_width;
		document.body.appendChild(canvas);

		// Create an image and draw the SVG onto the canvas
		var image = new Image;
		image.onload = function() {
			canvas.getContext('2d').drawImage(this, 0, 0, render_width,
					render_height);
		};
		//image.src = 'data:image/svg+xml;base64,' + window.btoa(svg);
		//  alert(window.btoa(svg))
		$("#graph-container").hide();
		$("#graphimg").attr('src',
				'data:image/svg+xml;base64,' + window.btoa(svg));

	}
 
</script>

<!-- Competition overview -->

<!-- Lifestyle analysis -->
<script type="text/javascript">
function save_chart18(chart, val) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight
		/ chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
	exporting : {
		sourceWidth : chart.chartWidth,
		sourceHeight : chart.chartHeight
	}
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
	canvas.getContext('2d').drawImage(this, 0, 0, render_width,
			render_height);
};
$("#container-bar-graph" + val).hide();
$("#lifestylecanvasimg" + val).attr('src','data:image/svg+xml;base64,' + window.btoa(svg));

}
</script>
<!-- Lifestyle analysis -->

<!-- Training -->

<script type="text/javascript">
function save_chart19(chart) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight / chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
    exporting: {
        sourceWidth: chart.chartWidth,
        sourceHeight: chart.chartHeight
    }
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
};
$("#container-fluid").hide();
$("#canvasimg33").attr('src','data:image/svg+xml;base64,' + btoa(unescape(encodeURIComponent(svg))));

}
</script>
<!-- Training -->

<!-- Financial -->
<script type="text/javascript">
function save_chart20(chart) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight / chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
    exporting: {
        sourceWidth: chart.chartWidth,
        sourceHeight: chart.chartHeight
    }
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
};
$("#container-bar-graph-financial1").hide();
$("#canvasimg34").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));

}

function save_chart21(chart) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight / chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
    exporting: {
        sourceWidth: chart.chartWidth,
        sourceHeight: chart.chartHeight
    }
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
};
$("#container-bar-graph-financial").hide();
$("#canvasimg35").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));

}
var year=$("#selected_year").val();
if(year == 2020){
function save_chart100(chart) {
	var render_width = EXPORT_WIDTH;
	var render_height = render_width * chart.chartHeight / chart.chartWidth

	// Get the cart's SVG code
	var svg = chart.getSVG({
	    exporting: {
	        sourceWidth: chart.chartWidth,
	        sourceHeight: chart.chartHeight
	    }
	});

	// Create a canvas
	var canvas = document.createElement('canvas');
	canvas.height = render_height;
	canvas.width = render_width;
	document.body.appendChild(canvas);

	// Create an image and draw the SVG onto the canvas
	var image = new Image;
	image.onload = function() {
	    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
	};
	$("#container-bar-graph-financial2").hide();
	$("#canvasimg37").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));

	}
}
function save_chart22(chart) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight / chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
    exporting: {
        sourceWidth: chart.chartWidth,
        sourceHeight: chart.chartHeight
    }
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
};
$("#financialprocesschart").hide();
$("#canvasimg36").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));

}
</script>
<!-- Financial -->
<script type="text/javascript">
 function save_chart23(chart) {
	var render_width = EXPORT_WIDTH;
	var render_height = render_width * chart.chartHeight / chart.chartWidth

	// Get the cart's SVG code
	var svg = chart.getSVG({
	    exporting: {
	        sourceWidth: chart.chartWidth,
	        sourceHeight: chart.chartHeight
	    }
	});

	// Create a canvas
	var canvas = document.createElement('canvas');
	canvas.height = render_height;
	canvas.width = render_width;
	document.body.appendChild(canvas);

	// Create an image and draw the SVG onto the canvas
	var image = new Image;
	image.onload = function() {
	    canvas.getContext('2d').drawImage(this, 0, 0, render_width, render_height);
	};
	$("#container-bar-graph-bps").hide();
	//$("#canvasimg37").attr('src','data:image/svg+xml;base64,' + window.btoa(svg));
	$("#canvasimg38").attr('src','data:image/svg+xml;base64,' + btoa(unescape(encodeURIComponent(svg))));

} 

/* function save_chart24 (chart) {
var render_width = EXPORT_WIDTH;
var render_height = render_width * chart.chartHeight
		/ chart.chartWidth

// Get the cart's SVG code
var svg = chart.getSVG({
	exporting : {
		sourceWidth : chart.chartWidth,
		sourceHeight : chart.chartHeight
	}
});

// Create a canvas
var canvas = document.createElement('canvas');
canvas.height = render_height;
canvas.width = render_width;
document.body.appendChild(canvas);

// Create an image and draw the SVG onto the canvas
var image = new Image;
image.onload = function() {
	canvas.getContext('2d').drawImage(this, 0, 0, render_width,
			render_height);
};
$("#material").hide();
$("#canvasimg38").attr('src',
		'data:image/svg+xml;base64,' + window.btoa(svg));

} */
</script>
<!-- bps analysis -->

<!-- bps analysis -->
<script type="text/javascript">

$('#export').click(function() {
	
  // alert(1);
	document.getElementById('chartdiv1').style.height = "14043px";
	//document.getElementById('chartdiv1').style.width = "9933px";
	document.getElementById('chartdiv2').style.height = "14043px";
	//document.getElementById('chartdiv2').style.width = "9933px";
	document.getElementById('chartdiv3').style.height = "14043px";
	//document.getElementById('chartdiv3').style.width = "9933px";
	document.getElementById('chartdiv4').style.height = "14043px";
	//document.getElementById('chartdiv4').style.width = "9933px";
	document.getElementById('chartdiv5').style.height = "14043px";
	//document.getElementById('chartdiv5').style.width = "9933px";
	document.getElementById('chartdiv6').style.height = "14043px";
	//document.getElementById('chartdiv6').style.width = "9933px";
	document.getElementById('chartdiv7').style.height = "14043px";
	//document.getElementById('chartdiv7').style.width = "9933px";
	document.getElementById('chartdiv8').style.height = "14043px";
	//document.getElementById('chartdiv8').style.width = "9933px";
	document.getElementById('chartdiv9').style.height = "14043px";
	//document.getElementById('chartdiv9').style.width = "9933px";
	document.getElementById('chartdiv10').style.height = "14043px";
	//document.getElementById('chartdiv10').style.width = "9933px";
	document.getElementById('chartdiv11').style.height = "14043px";
	//document.getElementById('chartdiv11').style.width = "9933px";
	document.getElementById('chartdiv12').style.height = "14043px";
	//document.getElementById('chartdiv12').style.width = "9933px";
	save_chart1($('#ytdchart').highcharts());
    save_chart2($('#processchart').highcharts());
    save_chart3($('#customerchart').highcharts());
    save_chart4($('#Onlinesale').highcharts());
    save_chart5($('#crm-container-bar-graph').highcharts());
    save_chart6($('#responsecontainer').highcharts());
    save_chart7($('#responsecontainer2').highcharts());
    save_chart8($('#conq-loy-dealer').highcharts());
    save_chart9($('#appointment').highcharts());
    save_chart10($('#responsecontainerTele').highcharts());
    save_chart11($('#responsecontainerTele2').highcharts());
    save_chart12($('#dealers').highcharts());
    //alert(2);
    var year=$("#selected_year").val();
    //alert(year);
    if(year == 2019){
    for(var i=1;i<=5;i++){
    	
   // alert(3);
		//alert('#container-bar-graph-'+i)
	    save_chart13($('#container-bar-graph-'+i).highcharts(),i);
	}
    }
    else{
    	for(var i=6;i<=11;i++){
        	
    		   // alert(3);
    				//alert('#container-bar-graph-'+i)
    			    save_chart13($('#container-bar-graph-'+i).highcharts(),i);
    			}
    }
   // alert(4.1);
    save_chart15($('#customerchartdiscount').highcharts());
    
    
    //alert(4.3);
    
    for (var i = 1; i <= 2; i++) {
    	
    //alert(4.4);
	save_chart18($('#container-bar-graph' + i).highcharts(),i);
    }
	if(year == 2019){
    save_chart55($("#chartContainer").highcharts());
    save_chart19($("#container-fluid").highcharts());
    save_chart20($('#container-bar-graph-financial').highcharts());
    save_chart21($('#container-bar-graph-financial1').highcharts());
    //save_chart100($('#container-bar-graph-financial2').highcharts());
    save_chart22($('#financialprocesschart').highcharts());
    save_chart23($('#container-bar-graph-bps').highcharts());
	}
	else{
		 save_chart55($("#chartContainer").highcharts());
		    save_chart19($("#container-fluid").highcharts());
		    save_chart20($('#container-bar-graph-financial').highcharts());
		    save_chart21($('#container-bar-graph-financial1').highcharts());
		    save_chart100($('#container-bar-graph-financial2').highcharts());
		    save_chart22($('#financialprocesschart').highcharts());
		    save_chart23($('#container-bar-graph-bps').highcharts());
	}
    //save_chart24($('#material').highcharts());
      /* for(var i=1;i<=6;i++){
    	
        alert(4);
		alert('#region-'+i)
	    save_chart14($('#region-'+i).highcharts(),i);
	}  */
	 for (var i = 1; i <= 6; i++) {
	
   // alert(4.2);
    //alert('#column-bar-chart-'+i)
	save_chart16($('#column-bar-chart' + i).highcharts(), i);
	} 
	save_chart17($("#graph-container").highcharts());
    //alert(5);
    $('body').addClass("enlarged");
	
	$("#export").hide();

	$('.td-progress span').hide();
	//alert(6);
	 setTimeout(function(){ 
    	call() }, 1000);  


});


	 /*  function call(){
		$("#export").hide();
		$('.td-progress span').hide();
		$('.btn btn-light waves-effect m-l-5').hide();
	//alert(1);
	    // Convert the DOM element to a drawing using kendo.drawing.drawDOM
	    kendo.drawing.drawDOM($(".content-page"))
	    .then(function(group) {
	        // Render the result as a PDF file
	        return kendo.drawing.exportPDF(group, {
	            paperSize: "A2",
	            margin:{top:"1cm",bottom:"1cm"},
	           // margin: { left: "1cm", top: "1cm", right: "1cm", bottom: "1cm" },
	            forcePageBreak: ".page-break",
		        multiPage: true
	        });
	    })
	    .done(function(data) {
	        // Save the PDF file
	        kendo.saveAs({
	            dataURI: data,
	            fileName: "Management_Level_report.pdf",
	            proxyURL: "https://demos.telerik.com/kendo-ui/service/export"
	        });
	    });
	    setTimeout(function(){ 
			 location.reload(); }, 2000); 
		  
		  } */
	   
	  
	    function call(){
		$("#export").hide();
		$('.td-progress span').hide();
		$('.btn btn-light waves-effect m-l-5').hide();
	//alert(hello);
		 kendo.drawing.drawDOM(".content-page", {
		        paperSize: "A1",
		        //landscape: true,
		        margin:{top:"1cm",bottom:"1cm"},
		        //margin: { left: "0cm", top: "3cm", right: "0cm", bottom: "0cm" },
		        forcePageBreak: ".page-break",
		        multiPage: true
		      })
		      .then(function(group) {
		        kendo.drawing.pdf.saveAs(group, "Management_Level_report.pdf");
		      });
		      setTimeout(function(){ 
		    	  location.reload();
		    	  <%-- window.location.replace("<%=dashboardURL %>allgraphs");  --%>
		    	 //callforload();
				 }, 2000);
		      //$("#window1").data("kendoWindow").refresh({ cache: false });
	}   
	  
</script>


<!-- scripts of new code Start-->
<!-- dashboard start -->
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
<!-- dashboard end -->
<!-- Conquest And Loyalty Start -->
<script>
var autoresponseemailgraphjson= ${autoresponseemailgraphjson};
         console.log(autoresponseemailgraphjson)
          var arrMonth1=[];
          var yesCount = [];
          var noCount = [];
          $.each(autoresponseemailgraphjson,function(k,v){
        	  arrMonth1.push(v.month+","+v.year);
        	  yesCount.push(v.yesCount);
           	  noCount.push(v.noCount) ;
        	  
          });
          console.log(arrMonth1)
            console.log(noCount)

          Highcharts.chart('responsecontainer', {
        	  chart: {
          	    type: 'column',
          	    
                       style: {
                         height:'450px',
                     }
          	  },
        	     legend: {
        	          enabled:true,
        	        align: 'center',
        	        verticalAlign: 'top',
        	        x: 0,
        	        y: -25,
        	        itemDistance: 7,
        	         itemStyle: {
        	            color: '#003366',
        	             fontSize:'13px',
        	            fontWeight: 400,
        	         fontFamily: "'BMWGroup-Regular'",
        	        }
        	          
        	    },
        	     credits: {
        	      enabled: false
        	  },
        	  title: {
        	    text: null
        	  },
        	  xAxis: {
        	    categories: arrMonth1,
        	       tickLength: 0,
        	      lineColor: 'transparent',
        	      labels: {
        	         
        	         style: {
        	            color: '#003366',
        	            'font-family': "'BMWGroup-Regular'",
        	             'font-size':'13px',
        	             'font-weight':400,
        	         }
        	      },
        	  },
        	  yAxis: {
        	      reversedStacks: false,
        	    min: 0,
        	       gridLineColor: 'transparent',
        	        labels: {
        	            enabled: false,
        	        }, 
        	    title: {
        	      text: null
        	    }
        	  },
        	   
        	    
        	  tooltip: {
        	    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
        	    shared: true
        	  },
        	  plotOptions: {
        	    column: {
        	      stacking: 'percent'
        	    },
        	        series: {
        	      borderWidth: 0,
        	      //   pointWidth: 26,
        	         pointPadding: 0,
        	            groupPadding: 0.1,
        	            borderWidth: 0,
        	            shadow: false,
        	      dataLabels: {
        	          enabled: true,
        	          inside:true,
        	           rotation: 0,
        	          verticalAlign:'middle',
        	          y:0,
        	        format: '{point.y}%',
        	          color:'#fff',
        	           style: {
        	                textOutline: 0,
        	                fontSize:'12px',
        	                fontFamily: "'BMWGroup-Regular'",
        	  },
        	      }
        	    }
        	  },
        	 series: [{
        		    name: 'Yes',
        		      "colorByPoint": false ,
        		        color: '#5094FC',
        		    data: yesCount.map(Number)
        		  }, 
        		 /*  {
        			    name: 'No',
        			      "colorByPoint": false ,
        			        color: '#3F78CF',
        			    data: noCount.map(Number)
        			  }, */]
        	});

        </script>
     

        <script>
        var dealershipgraphresponse= ${dealershipgraphresponse};
        console.log(dealershipgraphresponse)
         var arrMonth=[];
         var After4hoursonthesameworkingday = [];
         var Within2hoursaftersendingtherequest = [];
         var Within4hoursaftersendingtherequest = [];
         var Notatall = [];
         var Withinnextworkingdayaftersendingtherequest=[];
         var Nopersonalresponseuntilendofnextworkingday=[];
         var year='';
         $.each(dealershipgraphresponse,function(k,v){
       	  arrMonth.push(v.month+","+v.year);
       	  
       	After4hoursonthesameworkingday.push(v.After4hoursonthesameworkingdayp);
       	Within2hoursaftersendingtherequest.push(v.Within2hoursaftersendingtherequestp) ;
       	Within4hoursaftersendingtherequest.push(v.Within4hoursaftersendingtherequestp) ;
       	Notatall.push(v.Notatallp) ;
       	Withinnextworkingdayaftersendingtherequest.push(v.Withinnextworkingdayaftersendingtherequestp);
       	Nopersonalresponseuntilendofnextworkingday.push(v.Nopersonalresponseuntilendofnextworkingdayp);
        year=v.year;
         });
        // alert(year);
         if(year==2019)
         {
        	 //$('.dealers1').hide();
         console.log(After4hoursonthesameworkingday)
        Highcharts.chart('conq-loy-dealer', {
        	  chart: {
        	    type: 'column',
        	    
                     style: {
                       height:'450px',
                   }
        	  },
        	     legend: {
        	          enabled:true,
        	        align: 'center',
        	        verticalAlign: 'top',
        	        x: 0,
        	        y: -25,
        	        itemDistance: 7,
        	          itemStyle: {
        	            color: '#003366',
        	             fontSize:'13px',
        	            fontWeight: 600,
        	         fontFamily: "'BMWGroup-Regular'",
        	        }
        	          
        	    },
        	     credits: {
        	      enabled: false
        	  },
        	  title: {
        	    text: null
        	  },
        	  xAxis: {
        	    categories: arrMonth,
        	       tickLength: 0,
        	      lineColor: 'transparent',
        	      labels: {
        	         
        	         style: {
        	            color: '#003366',
        	            'font-family': "'BMWGroup-Regular'",
        	             'font-size':'13px',
        	             'font-weight':400,
        	         }
        	      },
        	  },
        	  yAxis: {
        	      reversedStacks: false,
        	    min: 0,
        	       gridLineColor: 'transparent',
        	        labels: {
        	            enabled: false,
        	        }, 
        	    title: {
        	      text: null
        	    }
        	  },
        	   
        	    
        	  tooltip: {
        	    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
        	    shared: true
        	  },
        	  plotOptions: {
        	    column: {
        	      stacking: 'percent'
        	    },
        	        series: {
        	         
        	      borderWidth: 0,
        	      //   pointWidth: 26,
        	         pointPadding: 0,
        	            groupPadding: 0.1,
        	            borderWidth: 0,
        	            shadow: false,
        	      dataLabels: {
        	          enabled: true,
        	          inside:true,
        	           rotation: 0,
        	          verticalAlign:'middle',
        	          y:0,
        	        format: '{point.y}%',
        	          color:'#fff',
        	           style: {
        	                textOutline: 0,
        	                fontSize:'12px',
        	                fontFamily: "'BMWGroup-Regular'",
        	  },
        	      }
        	    }
        	  },
        	  series: [{   
        		    name: 'After 4 hours on the same working day',
        		      "colorByPoint": false ,
        		        color: '#3F78CF',
        		    data:After4hoursonthesameworkingday.map(Number)
        		  },
        		           {
        		    name: 'Within 2 hours after sending the request',
        		      "colorByPoint": false ,
        		        color: '#5094FC',
        		    data: Within2hoursaftersendingtherequest.map(Number)
        		  },
        		          {
        		    name: 'Within 4 hours after sending the request',
        		      "colorByPoint": false ,
        		        color: '#83BCFF',
        		    data: Within4hoursaftersendingtherequest.map(Number)
        		  },          
        		  {
        			    name: 'Not at all',
        			      "colorByPoint": false ,
        			        color: '#B0D5FF',
        			    data: Notatall.map(Number)
        			  },
        	             
        	 ]
        	});
         }
         
         else
         
         {
        	// $('.dealers').hide();
        	  Highcharts.chart('conq-loy-dealer', {
            	  chart: {
            	    type: 'column',
            	    	
                         style: {
                           height:'450px',
                       }
            	  },
            	     legend: {
            	          enabled:true,
            	        align: 'center',
            	        verticalAlign: 'top',
            	        x: 0,
            	        y: -25,
            	        itemDistance: 7,
            	          itemStyle: {
            	            color: '#003366',
            	             fontSize:'13px',
            	            fontWeight: 600,
            	         fontFamily: "'BMWGroup-Regular'",
            	        }
            	          
            	    },
            	     credits: {
            	      enabled: false
            	  },
            	  title: {
            	    text: null
            	  },
            	  xAxis: {
            	    categories: arrMonth,
            	       tickLength: 0,
            	      lineColor: 'transparent',
            	      labels: {
            	         
            	         style: {
            	            color: '#003366',
            	            'font-family': "'BMWGroup-Regular'",
            	             'font-size':'13px',
            	             'font-weight':400,
            	         }
            	      },
            	  },
            	  yAxis: {
            	      reversedStacks: false,
            	    min: 0,
            	       gridLineColor: 'transparent',
            	        labels: {
            	            enabled: false,
            	        }, 
            	    title: {
            	      text: null
            	    }
            	  },
            	   
            	    
            	  tooltip: {
            	    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
            	    shared: true
            	  },
            	  plotOptions: {
            	    column: {
            	      stacking: 'percent'
            	    },
            	        series: {
            	         
            	      borderWidth: 0,
            	      //   pointWidth: 26,
            	         pointPadding: 0,
            	            groupPadding: 0.1,
            	            borderWidth: 0,
            	            shadow: false,
            	      dataLabels: {
            	          enabled: true,
            	          inside:true,
            	           rotation: 0,
            	          verticalAlign:'middle',
            	          y:0,
            	        format: '{point.y}%',
            	          color:'#fff',
            	           style: {
            	                textOutline: 0,
            	                fontSize:'12px',
            	                fontFamily: "'BMWGroup-Regular'",
            	  },
            	      }
            	    }
            	  },
            	  series: [{   
            		    name: 'After 4 hours on the same working day',
            		      "colorByPoint": false ,
            		        color: '#3F78CF',
            		    data:After4hoursonthesameworkingday.map(Number)
            		  },
            		           {
            		    name: 'Within next working day after sending the request',
            		      "colorByPoint": false ,
            		        color: '#5094FC',
            		    data: Withinnextworkingdayaftersendingtherequest.map(Number)
            		  },
            		          {
            		    name: 'Within 4 hours after sending the request',
            		      "colorByPoint": false ,
            		        color: '#83BCFF',
            		    data: Within4hoursaftersendingtherequest.map(Number)
            		  },          
            		  {
            			    name: 'No personal response until end of next working day',
            			      "colorByPoint": false ,
            			        color: '#B0D5FF',
            			    data: Nopersonalresponseuntilendofnextworkingday.map(Number)
            			  },
            	             
            	 ]
            	}); 
        	 
        	 
        	 
         }
        </script>
        
        <script>
        var appointmentchartjson= ${appointmentchartjson};
        console.log(appointmentchartjson)
         var arrMonth=[];
         var yesCount = [];
         var noCount = [];
         $.each(appointmentchartjson,function(k,v){
       	  arrMonth.push(v.month+","+v.year);
       	  yesCount.push(v.yesCount);
          	  noCount.push(v.noCount) ;
          	
         });
         console.log(yesCount)
           console.log(noCount)
        Highcharts.chart('appointment', {
        	chart: {
        	    type: 'column',
        	    
                     style: {
                       height:'450px',
                   }
        	  },
        	     legend: {
        	          enabled:true,
        	        align: 'center',
        	        verticalAlign: 'top',
        	        x: 0,
        	        y: -25,
        	        itemDistance: 7,
        	         itemStyle: {
        	            color: '#003366',
        	             fontSize:'13px',
        	            fontWeight: 600,
        	         fontFamily: "'BMWGroup-Regular'",
        	        }
        	          
        	    },
        	     credits: {
        	      enabled: false
        	  },
        	  title: {
        	    text: null
        	  },
        	  xAxis: {
        	    categories: arrMonth,
        	       tickLength: 0,
        	      lineColor: 'transparent',
        	      labels: {
        	         
        	         style: {
        	            color: '#003366',
        	            'font-family': "'BMWGroup-Regular'",
        	             'font-size':'13px',
        	             'font-weight':400,
        	         }
        	      },
        	  },
        	  yAxis: {
        	      reversedStacks: false,
        	    min: 0,
        	       gridLineColor: 'transparent',
        	        labels: {
        	            enabled: false,
        	        }, 
        	    title: {
        	      text: null
        	    }
        	  },
        	   
        	    
        	  tooltip: {
        	    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
        	    shared: true
        	  },
        	  plotOptions: {
        	    column: {
        	      stacking: 'percent'
        	    },
        	        series: {
        	         
        	      borderWidth: 0,
        	      //   pointWidth: 26,
        	         pointPadding: 0,
        	            groupPadding: 0.1,
        	            borderWidth: 0,
        	            shadow: false,
        	      dataLabels: {
        	          enabled: true,
        	          inside:true,
        	           rotation: 0,
        	          verticalAlign:'middle',
        	          y:0,
        	        format: '{point.y}%',
        	          color:'#fff',
        	           style: {
        	                textOutline: 0,
        	                fontSize:'12px',
        	                fontFamily: "'BMWGroup-Regular'",
        	  },
        	      }
        	    }
        	  },
        	  series: [{
        	    name: 'Yes',
        	      "colorByPoint": false ,
        	        color: '#5094FC',
        	    data: yesCount.map(Number)
        	  },
        	           
        	  {
        	    name: 'No',
        	      "colorByPoint": false ,
        	        color: '#3F78CF',
        	    data: noCount.map(Number)
        	  },
        	          ]
        	});
        
        </script>
        
       <script>
       var meetthestandardsjson= ${meetthestandardsjson};
       console.log(meetthestandardsjson)
        var arrMonth=[];
        var yesCount = [];
        var noCount = [];
        $.each(meetthestandardsjson,function(k,v){
      	  arrMonth.push(v.month+","+v.year);
      	  yesCount.push(v.yesCount);
         	  noCount.push(v.noCount) ;
         	
        });
        console.log("alshdsdjh=="+yesCount)
          console.log(noCount)
       
       Highcharts.chart('responsecontainer2', {
    	   chart: {
       	    type: 'column',
       	    
                    style: {
                      height:'450px',
                  }
       	  },
    	      legend: {      
    	         enabled:true,
    	         align: 'center',
    	         verticalAlign: 'top',
    	         x: 0,
    	         y: -25,
    	         itemDistance: 7,
    	           itemStyle: {
    	             color: '#003366',
    	              fontSize:'13px',
    	             fontWeight: 400,
    	          fontFamily: "'BMWGroup-Regular'",
    	         }
    	           
    	     },
    	      credits: {
    	       enabled: false
    	   },
    	   title: {
    	     text: null
    	   },
    	   xAxis: {
    	    
    	     categories: arrMonth,
    	        tickLength: 0,
    	       lineColor: 'transparent',
    	       labels: {
    	          
    	          style: {
    	             color: '#003366',
    	             'font-family': "'BMWGroup-Regular'",
    	              'font-size':'13px',
    	              'font-weight':400,
    	          }
    	       },
    	   },
    	   yAxis: {
    	          reversedStacks: false,
    	     min: 0,
    	        gridLineColor: 'transparent',
    	         labels: {
    	             enabled: false,
    	         }, 
    	     title: {
    	       text: false
    	     }
    	   },
    	    
    	     
    	   tooltip: {
    	     pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
    	     shared: true
    	   },
    	   plotOptions: {
    	     column: {
    	       stacking: 'percent'
    	     },
    	         series: {
    	       borderWidth: 0,
    	       //   pointWidth: 26,
    	          pointPadding: 0,
    	             groupPadding: 0.1,
    	             borderWidth: 0,
    	             shadow: false,
    	       dataLabels: {
    	           enabled: true,
    	           inside:true,
    	            rotation: 0,
    	           verticalAlign:'middle',
    	           y:0,
    	         format: '{point.y}%',
    	           color:'#fff',
    	            style: {
    	                 textOutline: 0,
    	                 fontSize:'12px',
    	                 fontFamily: "'BMWGroup-Regular'",            
    	   },
    	       }
    	     }
    	   },
    	   series: [{
    	     name: 'Yes',   
    	       "colorByPoint": false ,
    	         color: '#5094FC',
    	     data: yesCount.map(Number)
    	   }, /* {
      	     name: 'no',   
  	       "colorByPoint": false ,
  	         color: '#3F78CF',
  	     data: noCount.map(Number)
  	   }, */ ]
    	 });
       
       </script>
<!-- COnquest And Loyalty End -->
<!-- Conquest And Via Tele Start -->
<script>
var stepstotakegraphjsonArray= ${stepstotakegraphjsonArray};
console.log(stepstotakegraphjsonArray)
 var arrMonth=[];
 var yesCount = [];
 var noCount = [];
 $.each(stepstotakegraphjsonArray,function(k,v){
	  arrMonth.push(v.month+","+v.year);
	  yesCount.push(v.yesCount);
  	  noCount.push(v.noCount) ;
	  
 });
 console.log(yesCount)
   console.log(noCount)

Highcharts.chart('responsecontainerTele', {
chart: {
type: 'column'
},
 legend: {
      enabled:true,
    align: 'center',
    verticalAlign: 'top',
    x: 0,
    y: 0,
    itemDistance: 7,
     itemStyle: {
        color: '#003366',
         fontSize:'13px',
        fontWeight: 400,
     fontFamily: "'BMWGroup-Regular'",
    }
      
},
 credits: {
  enabled: false
},
title: {
text: null
},
xAxis: {
categories: arrMonth,
   tickLength: 0,
  lineColor: 'transparent',
  labels: {
     
     style: {
        color: '#003366',
        'font-family': "'BMWGroup-Regular'",
         'font-size':'13px',
         'font-weight':400,
     }
  },
},
yAxis: {
  reversedStacks: false,
min: 0,
   gridLineColor: 'transparent',
    labels: {
        enabled: false,
    }, 
title: {
  text: null
}
},


tooltip: {
pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
shared: true
},
plotOptions: {
column: {
  stacking: 'percent'
},
    series: {
  borderWidth: 0,
  //   pointWidth: 26,
     pointPadding: 0,
        groupPadding: 0.1,
        borderWidth: 0,
        shadow: false,
  dataLabels: {
      enabled: true,
      inside:true,
       rotation: 0,
      verticalAlign:'middle',
      y:0,
    format: '{point.y}%',
      color:'#fff',
       style: {
            textOutline: 0,
            fontSize:'12px',
            fontFamily: "'BMWGroup-Regular'",
},
  }
}
},
series: [
{
name: 'Yes',
  "colorByPoint": false ,

    color: '#70A9FE',
data:yesCount.map(Number)
},{
    name: 'No',
      "colorByPoint": false ,
      color: '#3F78CF',
        data: noCount.map(Number)
  }, ]
});


</script>
<script>

var meetthestandardtelephonesjson= ${meetthestandardtelephonesjson};
console.log(meetthestandardtelephonesjson)
var arrMonth=[];
var yesCount = [];
var noCount = [];
$.each(meetthestandardtelephonesjson,function(k,v){
  arrMonth.push(v.month+","+v.year);
  yesCount.push(v.yesCount);
	  noCount.push(v.noCount) ;
	
});
console.log(yesCount)
console.log(noCount)

Highcharts.chart('responsecontainerTele2', {
chart: {
type: 'column'
},
 legend: {
      enabled:true,
    align: 'center',
    verticalAlign: 'top',
    x: 0,
    y: 0,
    itemDistance: 7,
     itemStyle: {
        color: '#003366',
         fontSize:'13px',
        fontWeight: 200,
     fontFamily: "'BMWGroup-Regular'",
    }
      
},
 credits: {
  enabled: false
},
title: {
text: null
},
xAxis: {
categories: arrMonth,
   tickLength: 0,
  lineColor: 'transparent',
  labels: {
     
     style: {
        color: '#003366',
        'font-family': "'BMWGroup-Regular'",
         'font-size':'13px',
         'font-weight':400,
     }
  },
},
yAxis: {
  reversedStacks: false,
min: 0,
   gridLineColor: 'transparent',
    labels: {
        enabled: false,
    }, 
title: {
  text: null
}
},


tooltip: {
pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
shared: true
},
plotOptions: {
column: {
  stacking: 'percent'
},
    series: {
  borderWidth: 0,
  //   pointWidth: 26,
     pointPadding: 0,
        groupPadding: 0.1,
        borderWidth: 0,
        shadow: false,
  dataLabels: {
      enabled: true,
      inside:true,
       rotation: 0,
      verticalAlign:'middle',
      y:0,
    format: '{point.y}%',
      color:'#fff',
       style: {
            textOutline: 0,
            fontSize:'12px',
            fontFamily: "'BMWGroup-Regular'",
},
  }
}
},
series: [{
name: 'Yes',
  "colorByPoint": false ,
  color: '#70A9FE',
    
data:yesCount.map(Number)
},{
    name: 'No',
      "colorByPoint": false ,
      color: '#3F78CF',
        data: noCount.map(Number)
  }, ]
});

</script>
<script>
if(year==2019)
{
var anyoneatdealershipjson= ${anyoneatdealershipjson};
console.log(anyoneatdealershipjson)
var arrMonth=[];
var WithoutwaitingloopWithin30secondsatthefirstattempt = [];
var WithoutwaitingloopWithin30secondsatthesecondattemp = [];

var InwaitingloopWithin120secondsatthefirstattempt = [];
var InwaitingloopWithin120secondsatthesecondattempt = [];
var Callbackwasnotconductedwithinoneworkingday = [];
var IcalledtwotimesbutIcouldnotreachthedealershipatall = [];

$.each(anyoneatdealershipjson,function(k,v){
  arrMonth.push(v.month+","+v.year);
  WithoutwaitingloopWithin30secondsatthefirstattempt.push(v.WithoutwaitingloopWithin30secondsatthefirstattemptp);
  WithoutwaitingloopWithin30secondsatthesecondattemp.push(v.WithoutwaitingloopWithin30secondsatthesecondattempp) ;
  InwaitingloopWithin120secondsatthefirstattempt.push(v.InwaitingloopWithin120secondsatthefirstattemptp);
  InwaitingloopWithin120secondsatthesecondattempt.push(v.InwaitingloopWithin120secondsatthesecondattemptp) ;
  Callbackwasnotconductedwithinoneworkingday.push(v.Callbackwasnotconductedwithinoneworkingdayp);
  IcalledtwotimesbutIcouldnotreachthedealershipatall.push(v.IcalledtwotimesbutIcouldnotreachthedealershipatallp) ;
	
});






Highcharts.chart('dealers', {
chart: {
type: 'column'
},
 legend: {
      enabled:true,
    align: 'center',
    verticalAlign: 'top',
    x: 0,
    y: -15,
    itemDistance: 7,
      itemStyle: {
        color: '#003366',
         fontSize:'13px',
        fontWeight: 400,
     fontFamily: "'BMWGroup-Regular'",
    }
      
},
 credits: {
  enabled: false
},
title: {
text: null
},
xAxis: {
categories: arrMonth,
   tickLength: 0,
  lineColor: 'transparent',
  labels: {
     
     style: {
        color: '#003366',
        'font-family': "'BMWGroup-Regular'",
         'font-size':'13px',
         'font-weight':400,
     }
  },
},
yAxis: {
  reversedStacks: false,
min: 0,
   gridLineColor: 'transparent',
    labels: {
        enabled: false,
    }, 
title: {
  text: null
}
},


tooltip: {
pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
shared: true
},
plotOptions: {
column: {
  stacking: 'percent'
},
    series: {
     
  borderWidth: 0,
  //   pointWidth: 26,
     pointPadding: 0,
        groupPadding: 0.1,
        borderWidth: 0,
        shadow: false,
  dataLabels: {
      enabled: true,
      inside:true,
       rotation: 0,
      verticalAlign:'middle',
      y:0,
    format: '{point.y}%',
      color:'#fff',
       style: {
            textOutline: 0,
            fontSize:'12px',
            fontFamily: "'BMWGroup-Regular'",
},
  }
}
},
series: [ {   
    name: 'Without waiting loop- Within 30 seconds at the first attempt',
      "colorByPoint": false ,
        color: '#3F78CF',
    data:WithoutwaitingloopWithin30secondsatthefirstattempt.map(Number)
  },
           
  {
    name: 'Without waiting loop- Within 30 seconds at the second attempt',
      "colorByPoint": false ,
        color: '#5285d3',
        data: WithoutwaitingloopWithin30secondsatthesecondattemp.map(Number)
  },
           {
    name: 'In waiting loop: Within 120 seconds at the first attempt',
      "colorByPoint": false ,
        color: '#6593d8',
        data: InwaitingloopWithin120secondsatthefirstattempt.map(Number)
  },
  {   
	    name: 'In waiting loop: Within 120 seconds at the second attempt',
	      "colorByPoint": false ,
	        color: '#78a0dd',
	    data: InwaitingloopWithin120secondsatthesecondattempt.map(Number)
	  },
	           
	  {
	    name: 'Call back was not conducted within one working day ',
	      "colorByPoint": false ,
	        color: '#8baee2',
	        data: Callbackwasnotconductedwithinoneworkingday.map(Number)
	  },
	           {
	    name: 'I called two times but I could not reach the dealership at all',
	      "colorByPoint": false ,
	        color: '#9fbbe7',
	        data:IcalledtwotimesbutIcouldnotreachthedealershipatall.map(Number)
	  },


]
});
}
else{
	var anyoneatdealershipjson= ${anyoneatdealershipjson};
     console.log(anyoneatdealershipjson)
      var arrMonth=[];
      //var WithoutwaitingloopWithin30secondsatthefirstattempt = [];
      //var WithoutwaitingloopWithin30secondsatthesecondattemp = [];
      
      var InwaitingloopWithin120secondsatthefirstattempt = [];
      var InwaitingloopWithin120secondsatthesecondattempt = [];
      var Callbackwasnotconductedwithinoneworkingday = [];
      var IcalledtwotimesbutIcouldnotreachthedealershipatall = [];
      
      $.each(anyoneatdealershipjson,function(k,v){
    	  arrMonth.push(v.month+","+v.year);
    	 // WithoutwaitingloopWithin30secondsatthefirstattempt.push(v.WithoutwaitingloopWithin30secondsatthefirstattemptp);
    	 // WithoutwaitingloopWithin30secondsatthesecondattemp.push(v.WithoutwaitingloopWithin30secondsatthesecondattempp) ;
    	  InwaitingloopWithin120secondsatthefirstattempt.push(v.InwaitingloopWithin120secondsatthefirstattemptp);
    	  InwaitingloopWithin120secondsatthesecondattempt.push(v.InwaitingloopWithin120secondsatthesecondattemptp) ;
    	  Callbackwasnotconductedwithinoneworkingday.push(v.Callbackwasnotconductedwithinoneworkingdayp);
    	  IcalledtwotimesbutIcouldnotreachthedealershipatall.push(v.IcalledtwotimesbutIcouldnotreachthedealershipatallp) ;
       	
      });
      


	
	
	
	Highcharts.chart('dealers', {
	  chart: {
	    type: 'column'
	  },
	     legend: {
	          enabled:true,
	        align: 'center',
	        verticalAlign: 'top',
	        x: 0,
	        y: -15,
	        itemDistance: 7,
	          itemStyle: {
	            color: '#003366',
	             fontSize:'13px',
	            fontWeight: 400,
	         fontFamily: "'BMWGroup-Regular'",
	        }
	          
	    },
	     credits: {
	      enabled: false
	  },
	  title: {
	    text: null
	  },
	  xAxis: {
	    categories: arrMonth,
	       tickLength: 0,
	      lineColor: 'transparent',
	      labels: {
	         
	         style: {
	            color: '#003366',
	            'font-family': "'BMWGroup-Regular'",
	             'font-size':'13px',
	             'font-weight':400,
	         }
	      },
	  },
	  yAxis: {
	      reversedStacks: false,
	    min: 0,
	       gridLineColor: 'transparent',
	        labels: {
	            enabled: false,
	        }, 
	    title: {
	      text: null
	    }
	  },
	   
	    
	  tooltip: {
	    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
	    shared: true
	  },
	  plotOptions: {
	    column: {
	      stacking: 'percent'
	    },
	        series: {
	         
	      borderWidth: 0,
	      //   pointWidth: 26,
	         pointPadding: 0,
	            groupPadding: 0.1,
	            borderWidth: 0,
	            shadow: false,
	      dataLabels: {
	          enabled: true,
	          inside:true,
	           rotation: 0,
	          verticalAlign:'middle',
	          y:0,
	        format: '{point.y}%',
	          color:'#fff',
	           style: {
	                textOutline: 0,
	                fontSize:'12px',
	                fontFamily: "'BMWGroup-Regular'",
	  },
	      }
	    }
	  },
	  series: [ {
		    name: 'Within 20 seconds at the first attempt',
		      "colorByPoint": false ,
		        color: '#6593d8',
		        data: InwaitingloopWithin120secondsatthefirstattempt.map(Number)
		  },
		  {
		    name: 'After 21 seconds but within 60 seconds at the first attempt ',
		      "colorByPoint": false ,
		        color: '#8baee2',
		        data: Callbackwasnotconductedwithinoneworkingday.map(Number)
		  },
		  
		  {   
			    name: 'Within 60 seconds at the second attempt',
			      "colorByPoint": false ,
			        color: '#78a0dd',
			    data: InwaitingloopWithin120secondsatthesecondattempt.map(Number)
			  },
			   {
			    name: 'I called two times but I could not reach the dealership at all',
			      "colorByPoint": false ,
			        color: '#9fbbe7',
			        data:IcalledtwotimesbutIcouldnotreachthedealershipatall.map(Number)
			  },
	    
	  
	  ]
	});
}
</script>
<!-- Conquest And Via Tele End -->
<!-- Conquest And Loyalty Focus Area Start 2019-->
<script>
            var contactdetailsspeltcorrectlyjson= ${contactdetailsspeltcorrectlyjson};
            console.log("first=="+contactdetailsspeltcorrectlyjson)
             var arrMonth=[];
             var yesCount = [];
             var noCount = [];
             $.each(contactdetailsspeltcorrectlyjson,function(k,v){
           	  arrMonth.push(v.month+","+v.year);
           	  yesCount.push(v.yesCount);
              	  noCount.push(v.noCount) ;
           	  
             });
             console.log(yesCount)
               console.log(noCount)
               
               Highcharts.chart('container-bar-graph-1', {
            	   chart: {
            	        type: 'column' },
            	      
            	      title: {
            	        align: 'center',
            	        text: '',
            	         style: {
            	                color: '#6c7070',
            	                fontWeight: '500',
            	             fontSize:'10px'
            	            }
            	      },        
            	      credits: {
            	          enabled: false
            	      },
            	      xAxis: {
            	          tickLength: 0,
            	        categories: arrMonth },
       
      
            	        yAxis: {
            	            reversedStacks: false, 
            	                  y: 16,
            	           min: 0,
            	           title: {
            	             text: '' },
            	              labels: {
            	                 format: '{value}%',         
            	             },
            	         
            	           stackLabels: {
            	             enabled: false,
            	             style: {
            	               fontWeight: 'bold',
            	               color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
      
      
      
            	               
            	               legend: {
            	                 align: 'center',
            	                 x: 0,
            	                 verticalAlign: 'top',
            	                 y: 0,
            	                 floating: false,
            	                 backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
            	                 // borderColor: '#CCC',
            	                 // borderWidth: 1,
            	                 shadow: false,
            	                 itemStyle: {
            	                         color: '#6c7070',
            	                         fontWeight: 400,
            	                      fontFamily: "'BMWGroup-Regular'",
            	                     }
            	               },
            	               tooltip: {
            	                   headerFormat: '<b>{point.x}</b><br/>',
            	                   pointFormat: '{series.name}: {point.y}<br/>Total: 100' },
      
            	                   
            	                   plotOptions: {
            	                     column: {
            	                       stacking: 'percent',
            	                       dataLabels: {
            	                         enabled: true,
            	                        } }, 
            	                   series: {
            	                       borderWidth: 0,
            	                       //   pointWidth: 26,
            	                          pointPadding: 0,
            	                             groupPadding: 0.1,
            	                             borderWidth: 0,
            	                             shadow: false,
            	                       dataLabels: {
            	                           enabled: true,
            	                           inside:true,
            	                            rotation: 0,
            	                          
            	                         format: '{point.y}%',
            	                           color:'#fff',
            	                            style: {
            	                                 textOutline: 0,
            	                                 fontSize:'12px',
            	                                 fontFamily: "'BMWGroup-Regular'",
            	                   },
            	                       }
            	                     }
            	                   },
      
      series: [
    	  {
    	        name: 'Yes',
    	          "colorByPoint": false ,
    	            color: '#70A9FE',
    	        data: yesCount.map(Number) },
      {
        name: 'No',
          "colorByPoint": false ,
            color: '#3F78CF',
        data: noCount.map(Number) },
     
      ] });
            
            </script>
            
            <script type="text/javascript">
            
            
         var Financingorleasingoptionsgson=   ${Financingorleasingoptionsgson};
         console.log(Financingorleasingoptionsgson)
          var arrMonth=[];
          var yesCount = [];
          var noCount = [];
          $.each(Financingorleasingoptionsgson,function(k,v){
        	  arrMonth.push(v.month+","+v.year);
        	  yesCount.push(v.yesCount);
           	  noCount.push(v.noCount) ;
        	  
          });
        // console.log(yesCount)
          //  console.log(noCount)
            
            
            Highcharts.chart('container-bar-graph-2', {
    chart: {
      type: 'column' },
    
    title: {
      align: 'center',
      text: '',
       style: {
              color: '#6c7070',
              fontWeight: '500',
           fontSize:'10px'
          }
    },        
    credits: {
        enabled: false
    },
    xAxis: {
        tickLength: 0,
      categories:arrMonth },
    
    yAxis: {
       reversedStacks: false, 
             y: 16,
      min: 0,
      title: {
        text: '' },
         labels: {
            format: '{value}%',         
        },
    
      stackLabels: {
        enabled: false,
        style: {
          fontWeight: 'bold',
          color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
    
    
    
    legend: {
      align: 'center',
      x: 0,
      verticalAlign: 'top',
      y: 0,
      floating: false,
      backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
      // borderColor: '#CCC',
      // borderWidth: 1,
      shadow: false,
      itemStyle: {
              color: '#6c7070',
              fontWeight: 400,
           fontFamily: "'BMWGroup-Regular'",
          }
    },
    
    tooltip: {
      headerFormat: '<b>{point.x}</b><br/>',
      pointFormat: '{series.name}: {point.y}<br/>Total: 100' },
    
    plotOptions: {
      column: {
        stacking: 'percent',
        dataLabels: {
          enabled: true,
         } }, 
    series: {
        borderWidth: 0,
        //   pointWidth: 26,
           pointPadding: 0,
              groupPadding: 0.1,
              borderWidth: 0,
              shadow: false,
        dataLabels: {
            enabled: true,
            inside:true,
             rotation: 0,
           
          format: '{point.y}%',
            color:'#fff',
             style: {
                  textOutline: 0,
                  fontSize:'12px',
                  fontFamily: "'BMWGroup-Regular'",
    },
        }
      }
    },
    
    series: [
    	 {
    	      name: 'Yes',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data:yesCount.map(Number)},
    {
      name: 'No',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: noCount.map(Number)},
   
    ] });
            
            </script>
            
            <script type="text/javascript">
            var testdriveactivelyofferedgson=${testdriveactivelyofferedgson};
            console.log("third=="+testdriveactivelyofferedgson)
            var arrMonth=[];
            var yesCount = [];
            var noCount = [];
            $.each(testdriveactivelyofferedgson,function(k,v){
          	  arrMonth.push(v.month+","+v.year);
          	  yesCount.push(v.yesCount);
             	  noCount.push(v.noCount) ;
          	  
            });
            console.log(yesCount)
              console.log(noCount)
              
              Highcharts.chart('container-bar-graph-3', {
            	  chart: {
            	      type: 'column' },
            	    
            	    title: {
            	      align: 'center',
            	      text: '',
            	       style: {
            	              color: '#6c7070',
            	              fontWeight: '500',
            	           fontSize:'10px'
            	          }
            	    },        
            	    credits: {
            	        enabled: false
            	    },
            	    xAxis: {
            	        tickLength: 0,
            	      categories: arrMonth },
            	    
            	    yAxis: {
            	       reversedStacks: false, 
            	             y: 16,
            	      min: 0,
            	      title: {
            	        text: '' },
            	         labels: {
            	            format: '{value}%',         
            	        },
            	    
            	      stackLabels: {
            	        enabled: false,
            	        style: {
            	          fontWeight: 'bold',
            	          color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
            	    
            	    
            	    
            	    legend: {
            	      align: 'center',
            	      x: 0,
            	      verticalAlign: 'top',
            	      y: 0,
            	      floating: false,
            	      backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
            	      // borderColor: '#CCC',
            	      // borderWidth: 1,
            	      shadow: false,
            	      itemStyle: {
            	              color: '#6c7070',
            	              fontWeight: 400,
            	           fontFamily: "'BMWGroup-Regular'",
            	          }
            	    },
            	    
            	    tooltip: {
            	      headerFormat: '<b>{point.x}</b><br/>',
            	      pointFormat: '{series.name}: {point.y}<br/>Total:100' },
            	    
            	    plotOptions: {
            	      column: {
            	        stacking: 'percent',
            	        dataLabels: {
            	          enabled: true,
            	         } }, 
            	    series: {
            	        borderWidth: 0,
            	        //   pointWidth: 26,
            	           pointPadding: 0,
            	              groupPadding: 0.1,
            	              borderWidth: 0,
            	              shadow: false,
            	        dataLabels: {
            	            enabled: true,
            	            inside:true,
            	             rotation: 0,
            	           
            	          format: '{point.y}%',
            	            color:'#fff',
            	             style: {
            	                  textOutline: 0,
            	                  fontSize:'12px',
            	                  fontFamily: "'BMWGroup-Regular'",
            	    },
            	        }
            	      }
            	    },
    series: [
    	  {
    	      name: 'Yes',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data: yesCount.map(Number)},
    {
      name: 'No ',
        "colorByPoint": false ,
          color: '#3F78CF',
      data:noCount.map(Number)},
  
    ] });



            
            </script>
            
            <script type="text/javascript">
            
            
            var individualizedOffer=${individualizedOffergson};
            
            
            var Yeshandedoverduringtheconsultationonofficialpaper=[];
            var Yesonofficialpapersentperemailduringrightaftertheconsultation=[];
            var YesIgotanwrittenofferbutnotonofficialpaper=[];
            var NoIdidnotreceiveanofferatall=[];
            
            var arrMonth=[];
            $.each(individualizedOffer,function(k,v){
          	  arrMonth.push(v.month+","+v.year);
          	Yeshandedoverduringtheconsultationonofficialpaper.push(v.Yeshandedoverduringtheconsultationonofficialpaperp);
          	Yesonofficialpapersentperemailduringrightaftertheconsultation.push(v.Yesonofficialpapersentperemailduringrightaftertheconsultationp) ;
          	YesIgotanwrittenofferbutnotonofficialpaper.push(v.YesIgotanwrittenofferbutnotonofficialpaperp);
          	NoIdidnotreceiveanofferatall.push(v.NoIdidnotreceiveanofferatallp);
          	  
            });
            console.log(Yeshandedoverduringtheconsultationonofficialpaper);
              console.log("Yesonofficialpapersentperemailduringrightaftertheconsultation="+Yesonofficialpapersentperemailduringrightaftertheconsultation)
             console.log("YesIgotanwrittenofferbutnotonofficialpaper="+YesIgotanwrittenofferbutnotonofficialpaper)
              console.log("NoIdidnotreceiveanofferatall="+NoIdidnotreceiveanofferatall)
            
            
            Highcharts.chart('container-bar-graph-4', {
                chart: {
                  type: 'column' },
                
                title: {
                  align: 'center',
                  text: '',
                   style: {
                          color: '#6c7070',
                          fontWeight: '500',
                       fontSize:'10px'
                      }
                },        
                credits: {
                    enabled: false
                },
                xAxis: {
                    tickLength: 0,
                  categories: arrMonth },
                
                yAxis: {
                   reversedStacks: false, 
                         y: 16,
                  min: 0,
                  title: {
                    text: '' },
                     labels: {
                        format: '{value}%',         
                    },
                
                  stackLabels: {
                    enabled: false,
                    style: {
                      fontWeight: 'bold',
                      color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
                
                
                
                legend: {
                  align: 'center',
                  x: 0,
                  verticalAlign: 'top',
                  y: 0,
                  floating: false,
                  backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
                  // borderColor: '#CCC',
                  // borderWidth: 1,
                  shadow: false,
                  itemStyle: {
                          color: '#6c7070',
                          fontWeight: 400,
                       fontFamily: "'BMWGroup-Regular'",
                      }
                },
                
                tooltip: {
                  headerFormat: '<b>{point.x}</b><br/>',
                  pointFormat: '{series.name}: {point.y}<br/>Total: 100' },
                
                plotOptions: {
                  column: {
                    stacking: 'percent',
                    dataLabels: {
                      enabled: true,
                     
                     } }, 
                series: {
                    borderWidth: 0,
                    //   pointWidth: 26,
                       pointPadding: 0,
                          groupPadding: 0.1,
                          borderWidth: 0,
                          shadow: false,
                    dataLabels: {
                        enabled: true,
                        inside:true,
                         rotation: 0,
                        
                      format: '{point.y}%',

                        color:'#fff',
                         style: {
                              textOutline: 0,
                              fontSize:'12px',
                              fontFamily: "'BMWGroup-Regular'",
                },
                    }
                  }
                },
                
                series: [
                	{
                	      name: 'Yes, handed over during the consultation on official paper',
                	        "colorByPoint": false ,
                	          color: '#78a0dd',
                	      data:  Yeshandedoverduringtheconsultationonofficialpaper.map(Number)
                	      },
			                {
			                  name: 'Yes, on official paper sent per e-mail during/right after the consultation',
			                    "colorByPoint": false ,
			                      color: '#6593d8',
			                  data:  Yesonofficialpapersentperemailduringrightaftertheconsultation.map(Number)
			                  },
			                  {
			                      name: 'Yes, I got an written offer but not on official paper',
			                        "colorByPoint": false ,
			                          color: '#5285d3',
			                      data: YesIgotanwrittenofferbutnotonofficialpaper.map(Number)
			                      },
			                      {
			                          name: 'No, I did not receive an offer at all',
			                            "colorByPoint": false ,
			                              color: '#3f78cf',
			                          data: NoIdidnotreceiveanofferatall.map(Number)
			                          },
                
                ]
                
            });


            </script>
           
            <script>
            var correspondingtimeframegson=${correspondingtimeframegson};
            var arrMonth=[];
            var yesCount = [];
            var noCount = [];
            $.each(correspondingtimeframegson,function(k,v){
          	  arrMonth.push(v.month+","+v.year);
          	  yesCount.push(v.yesCount);
             	  noCount.push(v.noCount) ;
          	  
            });
           // console.log(yesCount)
            //  console.log(noCount)
            Highcharts.chart('container-bar-graph-5', {
                chart: {
                  type: 'column' },
                
                title: {
                  align: 'center',
                  text: '',
                   style: {
                          color: '#6c7070',
                          fontWeight: '500',
                       fontSize:'10px'
                      }
                },        
                credits: {
                    enabled: false
                },
                xAxis: {
                    tickLength: 0,
                  categories: arrMonth },
                
                yAxis: {
                   reversedStacks: false, 
                         y: 16,
                  min: 0,
                  title: {
                    text: '' },
                     labels: {
                        format: '{value}%',         
                    },
                
                  stackLabels: {
                    enabled: false,
                    style: {
                      fontWeight: 'bold',
                      color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
                
                
                
                legend: {
                  align: 'center',
                  x: 0,
                  verticalAlign: 'top',
                  y: 0,
                  floating: false,
                  backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
                  // borderColor: '#CCC',
                  // borderWidth: 1,
                  shadow: false,
                  itemStyle: {
                          color: '#6c7070',
                          fontWeight: 400,
                       fontFamily: "'BMWGroup-Regular'",
                      }
                },
                
                tooltip: {
                  headerFormat: '<b>{point.x}</b><br/>',
                  pointFormat: '{series.name}: {point.y}<br/>Total: 100' },
                
                plotOptions: {
                  column: {
                    stacking: 'percent',
                    dataLabels: {
                      enabled: true,
                     } }, 
                series: {
                    borderWidth: 0,
                    //   pointWidth: 26,
                       pointPadding: 0,
                          groupPadding: 0.1,
                          borderWidth: 0,
                          shadow: false,
                    dataLabels: {
                        enabled: true,
                        inside:true,
                         rotation: 0,
                       
                      format: '{point.y}%',
                        color:'#fff',
                         style: {
                              textOutline: 0,
                              fontSize:'12px',
                              fontFamily: "'BMWGroup-Regular'",
                },
                    }
                  }
                },
                
                series: [
                	  {
                	      name: 'Yes',
                	        "colorByPoint": false ,
                	          color: '#70A9FE',
                	      data: yesCount.map(Number)},
                {
                  name: 'No ',
                    "colorByPoint": false ,
                      color: '#3F78CF',
                  data: noCount.map(Number)},
              
                ] });

            </script>
            <!-- new graph in 2020 manoj d -->
            
<!-- Conquest And Loyalty Focus Area End 2019-->
<!-- Conquest And Loyalty Focus Area Start 2020-->
<script>
            var contactdetailsspeltcorrectlyjson= ${contactdetailsspeltcorrectlyjson};
            console.log("first=="+contactdetailsspeltcorrectlyjson)
             var arrMonth=[];
             var yesCount = [];
             var noCount = [];
             $.each(contactdetailsspeltcorrectlyjson,function(k,v){
           	  arrMonth.push(v.month+","+v.year);
           	  yesCount.push(v.yesCount);
              	  noCount.push(v.noCount) ;
           	  
             });
             console.log(yesCount)
               console.log(noCount)
               
               Highcharts.chart('container-bar-graph-6', {
            	   chart: {
            	        type: 'column' },
            	      
            	      title: {
            	        align: 'center',
            	        text: '',
            	         style: {
            	                color: '#6c7070',
            	                fontWeight: '500',
            	             fontSize:'10px'
            	            }
            	      },        
            	      credits: {
            	          enabled: false
            	      },
            	      xAxis: {
            	          tickLength: 0,
            	        categories: arrMonth },
       
      
            	        yAxis: {
            	            reversedStacks: false, 
            	                  y: 16,
            	           min: 0,
            	           title: {
            	             text: '' },
            	              labels: {
            	                 format: '{value}%',         
            	             },
            	         
            	           stackLabels: {
            	             enabled: false,
            	             style: {
            	               fontWeight: 'bold',
            	               color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
      
      
      
            	               
            	               legend: {
            	                 align: 'center',
            	                 x: 0,
            	                 verticalAlign: 'top',
            	                 y: 0,
            	                 floating: false,
            	                 backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
            	                 // borderColor: '#CCC',
            	                 // borderWidth: 1,
            	                 shadow: false,
            	                 itemStyle: {
            	                         color: '#6c7070',
            	                         fontWeight: 400,
            	                      fontFamily: "'BMWGroup-Regular'",
            	                     }
            	               },
            	               tooltip: {
            	                   headerFormat: '<b>{point.x}</b><br/>',
            	                   pointFormat: '{series.name}: {point.y}<br/>Total: 100' },
      
            	                   
            	                   plotOptions: {
            	                     column: {
            	                       stacking: 'percent',
            	                       dataLabels: {
            	                         enabled: true,
            	                        } }, 
            	                   series: {
            	                       borderWidth: 0,
            	                       //   pointWidth: 26,
            	                          pointPadding: 0,
            	                             groupPadding: 0.1,
            	                             borderWidth: 0,
            	                             shadow: false,
            	                       dataLabels: {
            	                           enabled: true,
            	                           inside:true,
            	                            rotation: 0,
            	                          
            	                         format: '{point.y}%',
            	                           color:'#fff',
            	                            style: {
            	                                 textOutline: 0,
            	                                 fontSize:'12px',
            	                                 fontFamily: "'BMWGroup-Regular'",
            	                   },
            	                       }
            	                     }
            	                   },
      
      series: [
    	  {
    	        name: 'Yes',
    	          "colorByPoint": false ,
    	            color: '#70A9FE',
    	        data: yesCount.map(Number) },
      {
        name: 'No',
          "colorByPoint": false ,
            color: '#3F78CF',
        data: noCount.map(Number) },
     
      ] });
            
            </script>
            
            <script type="text/javascript">
            
            
         var Financingorleasingoptionsgson=   ${Financingorleasingoptionsgson};
         console.log(Financingorleasingoptionsgson)
          var arrMonth=[];
          var yesCount = [];
          var noCount = [];
          $.each(Financingorleasingoptionsgson,function(k,v){
        	  arrMonth.push(v.month+","+v.year);
        	  yesCount.push(v.yesCount);
           	  noCount.push(v.noCount) ;
        	  
          });
        // console.log(yesCount)
          //  console.log(noCount)
            
            
            Highcharts.chart('container-bar-graph-7', {
    chart: {
      type: 'column' },
    
    title: {
      align: 'center',
      text: '',
       style: {
              color: '#6c7070',
              fontWeight: '500',
           fontSize:'10px'
          }
    },        
    credits: {
        enabled: false
    },
    xAxis: {
        tickLength: 0,
      categories:arrMonth },
    
    yAxis: {
       reversedStacks: false, 
             y: 16,
      min: 0,
      title: {
        text: '' },
         labels: {
            format: '{value}%',         
        },
    
      stackLabels: {
        enabled: false,
        style: {
          fontWeight: 'bold',
          color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
    
    
    
    legend: {
      align: 'center',
      x: 0,
      verticalAlign: 'top',
      y: 0,
      floating: false,
      backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
      // borderColor: '#CCC',
      // borderWidth: 1,
      shadow: false,
      itemStyle: {
              color: '#6c7070',
              fontWeight: 400,
           fontFamily: "'BMWGroup-Regular'",
          }
    },
    
    tooltip: {
      headerFormat: '<b>{point.x}</b><br/>',
      pointFormat: '{series.name}: {point.y}<br/>Total: 100' },
    
    plotOptions: {
      column: {
        stacking: 'percent',
        dataLabels: {
          enabled: true,
         } }, 
    series: {
        borderWidth: 0,
        //   pointWidth: 26,
           pointPadding: 0,
              groupPadding: 0.1,
              borderWidth: 0,
              shadow: false,
        dataLabels: {
            enabled: true,
            inside:true,
             rotation: 0,
           
          format: '{point.y}%',
            color:'#fff',
             style: {
                  textOutline: 0,
                  fontSize:'12px',
                  fontFamily: "'BMWGroup-Regular'",
    },
        }
      }
    },
    
    series: [
    	 {
    	      name: 'Yes',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data:yesCount.map(Number)},
    {
      name: 'No',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: noCount.map(Number)},
   
    ] });
            
            </script>
            
            <script type="text/javascript">
            var testdriveactivelyofferedgson=${testdriveactivelyofferedgson};
            console.log("third=="+testdriveactivelyofferedgson)
            var arrMonth=[];
            var yesCount = [];
            var noCount = [];
            $.each(testdriveactivelyofferedgson,function(k,v){
          	  arrMonth.push(v.month+","+v.year);
          	  yesCount.push(v.yesCount);
             	  noCount.push(v.noCount) ;
          	  
            });
            console.log(yesCount)
              console.log(noCount)
              
              Highcharts.chart('container-bar-graph-8', {
            	  chart: {
            	      type: 'column' },
            	    
            	    title: {
            	      align: 'center',
            	      text: '',
            	       style: {
            	              color: '#6c7070',
            	              fontWeight: '500',
            	           fontSize:'10px'
            	          }
            	    },        
            	    credits: {
            	        enabled: false
            	    },
            	    xAxis: {
            	        tickLength: 0,
            	      categories: arrMonth },
            	    
            	    yAxis: {
            	       reversedStacks: false, 
            	             y: 16,
            	      min: 0,
            	      title: {
            	        text: '' },
            	         labels: {
            	            format: '{value}%',         
            	        },
            	    
            	      stackLabels: {
            	        enabled: false,
            	        style: {
            	          fontWeight: 'bold',
            	          color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
            	    
            	    
            	    
            	    legend: {
            	      align: 'center',
            	      x: 0,
            	      verticalAlign: 'top',
            	      y: 0,
            	      floating: false,
            	      backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
            	      // borderColor: '#CCC',
            	      // borderWidth: 1,
            	      shadow: false,
            	      itemStyle: {
            	              color: '#6c7070',
            	              fontWeight: 400,
            	           fontFamily: "'BMWGroup-Regular'",
            	          }
            	    },
            	    
            	    tooltip: {
            	      headerFormat: '<b>{point.x}</b><br/>',
            	      pointFormat: '{series.name}: {point.y}<br/>Total:100' },
            	    
            	    plotOptions: {
            	      column: {
            	        stacking: 'percent',
            	        dataLabels: {
            	          enabled: true,
            	         } }, 
            	    series: {
            	        borderWidth: 0,
            	        //   pointWidth: 26,
            	           pointPadding: 0,
            	              groupPadding: 0.1,
            	              borderWidth: 0,
            	              shadow: false,
            	        dataLabels: {
            	            enabled: true,
            	            inside:true,
            	             rotation: 0,
            	           
            	          format: '{point.y}%',
            	            color:'#fff',
            	             style: {
            	                  textOutline: 0,
            	                  fontSize:'12px',
            	                  fontFamily: "'BMWGroup-Regular'",
            	    },
            	        }
            	      }
            	    },
    series: [
    	  {
    	      name: 'Yes',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data: yesCount.map(Number)},
    {
      name: 'No ',
        "colorByPoint": false ,
          color: '#3F78CF',
      data:noCount.map(Number)},
  
    ] });



            
            </script>
            
            
            <!-- 2020 -->
            <script type="text/javascript">
            
            
            var individualizedOffer=${individualizedOffergson};
            
            
            var Yeshandedoverduringtheconsultationonofficialpaper=[];
            //var Yesonofficialpapersentperemailduringrightaftertheconsultation=[];
           // var YesIgotanwrittenofferbutnotonofficialpaper=[];
            var NoIdidnotreceiveanofferatall=[];
            
            var arrMonth=[];
            $.each(individualizedOffer,function(k,v){
          	  arrMonth.push(v.month+","+v.year);
          	Yeshandedoverduringtheconsultationonofficialpaper.push(v.Yeshandedoverduringtheconsultationonofficialpaperp);
          	/* Yesonofficialpapersentperemailduringrightaftertheconsultation.push(v.Yesonofficialpapersentperemailduringrightaftertheconsultationp) ;
          	YesIgotanwrittenofferbutnotonofficialpaper.push(v.YesIgotanwrittenofferbutnotonofficialpaperp); */
          	NoIdidnotreceiveanofferatall.push(v.NoIdidnotreceiveanofferatallp);
          	  
            });
            console.log(Yeshandedoverduringtheconsultationonofficialpaper);
              //console.log("Yesonofficialpapersentperemailduringrightaftertheconsultation="+Yesonofficialpapersentperemailduringrightaftertheconsultation)
            // console.log("YesIgotanwrittenofferbutnotonofficialpaper="+YesIgotanwrittenofferbutnotonofficialpaper)
              console.log("NoIdidnotreceiveanofferatall="+NoIdidnotreceiveanofferatall)
            
            
            Highcharts.chart('container-bar-graph-9', {
                chart: {
                  type: 'column' },
                
                title: {
                  align: 'center',
                  text: '',
                   style: {
                          color: '#6c7070',
                          fontWeight: '500',
                       fontSize:'10px'
                      }
                },        
                credits: {
                    enabled: false
                },
                xAxis: {
                    tickLength: 0,
                  categories: arrMonth },
                
                yAxis: {
                   reversedStacks: false, 
                         y: 16,
                  min: 0,
                  title: {
                    text: '' },
                     labels: {
                        format: '{value}%',         
                    },
                
                  stackLabels: {
                    enabled: false,
                    style: {
                      fontWeight: 'bold',
                      color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
                
                
                
                legend: {
                  align: 'center',
                  x: 0,
                  verticalAlign: 'top',
                  y: 0,
                  floating: false,
                  backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
                  // borderColor: '#CCC',
                  // borderWidth: 1,
                  shadow: false,
                  itemStyle: {
                          color: '#6c7070',
                          fontWeight: 400,
                       fontFamily: "'BMWGroup-Regular'",
                      }
                },
                
                tooltip: {
                  headerFormat: '<b>{point.x}</b><br/>',
                  pointFormat: '{series.name}: {point.y}<br/>Total: 100' },
                
                plotOptions: {
                  column: {
                    stacking: 'percent',
                    dataLabels: {
                      enabled: true,
                     
                     } }, 
                series: {
                    borderWidth: 0,
                    //   pointWidth: 26,
                       pointPadding: 0,
                          groupPadding: 0.1,
                          borderWidth: 0,
                          shadow: false,
                    dataLabels: {
                        enabled: true,
                        inside:true,
                         rotation: 0,
                        
                      format: '{point.y}%',

                        color:'#fff',
                         style: {
                              textOutline: 0,
                              fontSize:'12px',
                              fontFamily: "'BMWGroup-Regular'",
                },
                    }
                  }
                },
                
                series: [
                	{
                	      name: 'Yes',
                	        "colorByPoint": false ,
                	          color: '#78a0dd',
                	      data:  Yeshandedoverduringtheconsultationonofficialpaper.map(Number)
                	      },
			                /* {
			                  name: 'Yes, on official paper sent per e-mail during/right after the consultation',
			                    "colorByPoint": false ,
			                      color: '#6593d8',
			                  data:  Yesonofficialpapersentperemailduringrightaftertheconsultation.map(Number)
			                  },
			                  {
			                      name: 'Yes, I got an written offer but not on official paper',
			                        "colorByPoint": false ,
			                          color: '#5285d3',
			                      data: YesIgotanwrittenofferbutnotonofficialpaper.map(Number)
			                      }, */
			                      {
			                          name: 'No',
			                            "colorByPoint": false ,
			                              //color: '#3f78cf',
			                              color: '#6593d8',
			                          data: NoIdidnotreceiveanofferatall.map(Number)
			                          },
                
                ]
                
            });


            </script>
            <!-- 2020 -->
            <script>
            var correspondingtimeframegson=${correspondingtimeframegson};
            var arrMonth=[];
            var yesCount = [];
            var noCount = [];
            $.each(correspondingtimeframegson,function(k,v){
          	  arrMonth.push(v.month+","+v.year);
          	  yesCount.push(v.yesCount);
             	  noCount.push(v.noCount) ;
          	  
            });
           // console.log(yesCount)
            //  console.log(noCount)
            Highcharts.chart('container-bar-graph-10', {
                chart: {
                  type: 'column' },
                
                title: {
                  align: 'center',
                  text: '',
                   style: {
                          color: '#6c7070',
                          fontWeight: '500',
                       fontSize:'10px'
                      }
                },        
                credits: {
                    enabled: false
                },
                xAxis: {
                    tickLength: 0,
                  categories: arrMonth },
                
                yAxis: {
                   reversedStacks: false, 
                         y: 16,
                  min: 0,
                  title: {
                    text: '' },
                     labels: {
                        format: '{value}%',         
                    },
                
                  stackLabels: {
                    enabled: false,
                    style: {
                      fontWeight: 'bold',
                      color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
                
                
                
                legend: {
                  align: 'center',
                  x: 0,
                  verticalAlign: 'top',
                  y: 0,
                  floating: false,
                  backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
                  // borderColor: '#CCC',
                  // borderWidth: 1,
                  shadow: false,
                  itemStyle: {
                          color: '#6c7070',
                          fontWeight: 400,
                       fontFamily: "'BMWGroup-Regular'",
                      }
                },
                
                tooltip: {
                  headerFormat: '<b>{point.x}</b><br/>',
                  pointFormat: '{series.name}: {point.y}<br/>Total: 100' },
                
                plotOptions: {
                  column: {
                    stacking: 'percent',
                    dataLabels: {
                      enabled: true,
                     } }, 
                series: {
                    borderWidth: 0,
                    //   pointWidth: 26,
                       pointPadding: 0,
                          groupPadding: 0.1,
                          borderWidth: 0,
                          shadow: false,
                    dataLabels: {
                        enabled: true,
                        inside:true,
                         rotation: 0,
                       
                      format: '{point.y}%',
                        color:'#fff',
                         style: {
                              textOutline: 0,
                              fontSize:'12px',
                              fontFamily: "'BMWGroup-Regular'",
                },
                    }
                  }
                },
                
                series: [
                	  {
                	      name: 'Yes',
                	        "colorByPoint": false ,
                	          color: '#70A9FE',
                	      data: yesCount.map(Number)},
                {
                  name: 'No ',
                    "colorByPoint": false ,
                      color: '#3F78CF',
                  data: noCount.map(Number)},
              
                ] });

            </script>
            <!-- new graph in 2020 manoj d -->
            <script type="text/javascript">
            
            
         var CourtesyGraphgson=   ${CourtesyGraphgson};
         console.log(CourtesyGraphgson)
          var arrMonth=[];
          var yesCount = [];
          var noCount = [];
          $.each(CourtesyGraphgson,function(k,v){
        	  arrMonth.push(v.month+","+v.year);
        	  yesCount.push(v.yesCount);
           	  noCount.push(v.noCount) ;
        	  
          });
        // console.log(yesCount)
          //  console.log(noCount)
            
            
            Highcharts.chart('container-bar-graph-11', {
    chart: {
      type: 'column' },
    
    title: {
      align: 'center',
      text: '',
       style: {
              color: '#6c7070',
              fontWeight: '500',
           fontSize:'10px'
          }
    },        
    credits: {
        enabled: false
    },
    xAxis: {
        tickLength: 0,
      categories:arrMonth },
    
    yAxis: {
       reversedStacks: false, 
             y: 16,
      min: 0,
      title: {
        text: '' },
         labels: {
            format: '{value}%',         
        },
    
      stackLabels: {
        enabled: false,
        style: {
          fontWeight: 'bold',
          color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
    
    
    
    legend: {
      align: 'center',
      x: 0,
      verticalAlign: 'top',
      y: 0,
      floating: false,
      backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
      // borderColor: '#CCC',
      // borderWidth: 1,
      shadow: false,
      itemStyle: {
              color: '#6c7070',
              fontWeight: 400,
           fontFamily: "'BMWGroup-Regular'",
          }
    },
    
    tooltip: {
      headerFormat: '<b>{point.x}</b><br/>',
      pointFormat: '{series.name}: {point.y}<br/>Total: 100' },
    
    plotOptions: {
      column: {
        stacking: 'percent',
        dataLabels: {
          enabled: true,
         } }, 
    series: {
        borderWidth: 0,
        //   pointWidth: 26,
           pointPadding: 0,
              groupPadding: 0.1,
              borderWidth: 0,
              shadow: false,
        dataLabels: {
            enabled: true,
            inside:true,
             rotation: 0,
           
          format: '{point.y}%',
            color:'#fff',
             style: {
                  textOutline: 0,
                  fontSize:'12px',
                  fontFamily: "'BMWGroup-Regular'",
    },
        }
      }
    },
    
    series: [
    	 {
    	      name: 'Yes',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data:yesCount.map(Number)},
    {
      name: 'No',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: noCount.map(Number)},
   
    ] });
            
            </script>
<!-- Conquest And Loyalty Focus Area End 2020-->
<!-- Lifestyle Overview Start-->
<script>
       
       var Whattakehomematerialgson= ${Whattakehomematerialgson};
       console.log(Whattakehomematerialgson)
        var arrMonth=[];
        var yesCount = [];
        var noCount = [];
        $.each(Whattakehomematerialgson,function(k,v){
      	  arrMonth.push(v.month+","+v.year);
      	  yesCount.push(v.yesCount);
         	  noCount.push(v.noCount) ;
      	  
        });
        console.log(yesCount)
          console.log(noCount)
       
     
          
			
			Highcharts.chart('container-bar-graph1', {
		        chart: {
		          type: 'column' },
		        
		        title: {
		          align: 'center',
		          text: '',
		           style: {
		                  color: '#6c7070',
		                  fontWeight: '500',
		               fontSize:'10px'
		              }
		        },        
		        credits: {
		            enabled: false
		        },
		        xAxis: {
		            tickLength: 0,
		            labels:{
		                 style:{
		                      fontFamily: "'BMWGroup-Regular'",
		                 }
		            },
		          categories: arrMonth},
		        
		        yAxis: {
		           reversedStacks: false, 
		                 y: 16,
		          min: 0,
		          title: {
		            text: '' },
		             labels: {
		                format: '{value}%',
		                 style:{
		                      'font-family': "'BMWGroup-Regular'",
		                 }
		            },
		        
		          stackLabels: {
		            enabled: false,
		            style: {
		              fontWeight: 'bold',
		              color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
		        
		        
		        
		        legend: {
		          align: 'center',
		          x: 0,
		          verticalAlign: 'top',
		          y: 0,
		          floating: false,
		          backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
		          // borderColor: '#CCC',
		          // borderWidth: 1,
		          shadow: false,
		          itemStyle: {
		                  color: '#6c7070',
		                  fontWeight: 400,
		               fontFamily: "'BMWGroup-Regular'",
		              }
		        },
		        
		        tooltip: {
		          headerFormat: '<b>{point.x}</b><br/>',
		          pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}' },
		        
		        plotOptions: {
		          column: {
		            stacking: 'percent',
		            dataLabels: {
		              enabled: true,
		             } }, 
		        series: {
		            borderWidth: 0,
		            //   pointWidth: 26,
		               pointPadding: 0,
		                  groupPadding: 0.1,
		                  borderWidth: 0,
		                  shadow: false,
		            	  dataLabels: {
					          enabled: true,
					          inside:true,
					           rotation: 0,
					          verticalAlign:'middle',
					          y:0,
					        format: '{point.y}%',
					          color:'#fff',
					           style: {
					                textOutline: 0,
					                fontSize:'12px',
					                fontFamily: "'BMWGroup-Regular'",
					  },
					      }
		          }
		        },
		        
		        series: [
		        
		        {
		          name: 'Yes ',
		            "colorByPoint": false ,
		              color: '#70A9FE',
		          data:yesCount.map(Number) },
		        {
		          name: 'No',
		            "colorByPoint": false ,
		              color: '#3F78CF',
		          data: noCount.map(Number)},
		        ] });
				
			 
			</script>
	<script>
	  var configurationprocessgson= ${configurationprocessgson};
      console.log(configurationprocessgson)
       var arrMonth=[];
       var yesCount = [];
       var noCount = [];
       $.each(configurationprocessgson,function(k,v){
     	  arrMonth.push(v.month+","+v.year);
     	  yesCount.push(v.yesCount);
        	  noCount.push(v.noCount) ;
     	  
       });
       console.log(yesCount)
         console.log(noCount)
			
			Highcharts
					.chart(
							'container-bar-graph2',
							{
								chart : {
									type : 'column'
								},

								title : {
									align : 'center',
									text : '',
									style : {
										color : '#6c7070',
										fontWeight : '500',
										fontSize : '10px'
									}
								},
								credits : {
									enabled : false
								},
								xAxis : {
									tickLength : 0,
									labels : {
										style : {
											fontFamily : "'BMWGroup-Regular'",
										}
									},
									categories : arrMonth
								},

								yAxis : {
									reversedStacks : false,
									y : 16,
									min : 0,
									title : {
										text : ''
									},
									labels : {
										format : '{value}%',
										style : {
											'font-family' : "'BMWGroup-Regular'",
										}
									},

									stackLabels : {
										enabled : false,
										style : {
											fontWeight : 'bold',
											color : Highcharts.theme
													&& Highcharts.theme.textColor
													|| 'gray'
										}
									}
								},

								legend : {
									align : 'center',
									x : 0,
									verticalAlign : 'top',
									y : 0,
									floating : false,
									backgroundColor : Highcharts.theme
											&& Highcharts.theme.background2
											|| 'white',
									// borderColor: '#CCC',
									// borderWidth: 1,
									shadow : false,
									itemStyle : {
										color : '#6c7070',
										fontWeight : 400,
										fontFamily : "'BMWGroup-Regular'",
									}
								},

								tooltip : {
									headerFormat : '<b>{point.x}</b><br/>',
									pointFormat : '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
								},

								plotOptions : {
									column : {
										stacking : 'percent',
										dataLabels : {
											enabled : true,
										}
									},
									series : {
										borderWidth : 0,
										//   pointWidth: 26,
										pointPadding : 0,
										groupPadding : 0.1,
										borderWidth : 0,
										shadow : false,
										  dataLabels: {
									          enabled: true,
									          inside:true,
									           rotation: 0,
									          verticalAlign:'middle',
									          y:0,
									        format: '{point.y}%',
									          color:'#fff',
									           style: {
									                textOutline: 0,
									                fontSize:'12px',
									                fontFamily: "'BMWGroup-Regular'",
									  },
									      }
									}
								},

								series : [

								{
									name : 'Yes ',
									"colorByPoint" : false,
									color : '#70A9FE',
									data : yesCount.map(Number)
								}, {
									name : 'No',
									"colorByPoint" : false,
									color : '#3F78CF',
									data : noCount.map(Number)
								}, ]
							});

		
	</script>
<!-- Lifestyle Overview end-->
<!-- FinancialService Analysis -->
 <script>
	 
  var bmwfinancialservicegson=${bmwfinancialservicegson};
  
  console.log(bmwfinancialservicegson)
  var arrMonth=[];
  var yesCount = [];
  var noCount = [];
  $.each(bmwfinancialservicegson,function(k,v){
	  arrMonth.push(v.month+","+v.year);
	  yesCount.push(v.yesCount);
   	  noCount.push(v.noCount) ;
	  
  });
  console.log(yesCount)
    console.log(noCount)
      Highcharts.chart('container-bar-graph-financial', {
    	    chart: {
    	      type: 'column' },
    	  
    	    title: {
    	      align: 'center',
    	      text: null,
    	       style: {
    	              color: '#6c7070',
    	              fontWeight: '500',
    	           fontSize:'10px'
    	          }
    	    },        
    	   credits: {
    	        enabled: false
    	    },
    	    xAxis: {
    	        tickLength: 0,
    	      categories: arrMonth },
    	  
    	    yAxis: {
    	       reversedStacks: false, 
    	      min: 0,
    	      title: {
    	        text: '' },
    	         labels: {
    	            format: '{value}%',         
    	        },
    	  
    	      stackLabels: {
    	        enabled: true,
    	        style: {
    	          fontWeight: 'bold',
    	          color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
    	  
    	  
    	  
    	    legend: {
    	      align: 'center',
    	      x: 0,
    	      verticalAlign: 'top',
    	      y: 0,
    	      floating: false,
    	      backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
    	      // borderColor: '#CCC',
    	      // borderWidth: 1,
    	      shadow: false,
    	      itemStyle: {
    	              color: '#6c7070',
    	              fontWeight: 400,
    	           fontFamily: "'BMWGroup-Regular'",
    	          }
    	    },
    	  
    	    tooltip: {
    	      headerFormat: '<b>{point.x}</b><br/>',
    	      pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}' },
    	  
    	    plotOptions: {
    	      column: {
    	        stacking: 'percent',
    	        dataLabels: {
    	          enabled: true,
    	         } }, 
    	    series: {
    	        borderWidth: 0,
    	        //   pointWidth: 26,
    	           pointPadding: 0,
    	              groupPadding: 0.1,
    	              borderWidth: 0,
    	              shadow: false,
    	        dataLabels: {
    	            enabled: true,
    	            inside:true,
    	             rotation: 0,
    	           
    	          format: '{point.y}%',
    	            color:'#fff',
    	             style: {
    	                  textOutline: 0,
    	                  fontSize:'12px',
    	                  fontFamily: "'BMWGroup-Regular'",
    	    },
    	        }
    	      }
    	    },
    	  
    	    series: [
    	    
    	    {
    	      name: 'Yes ',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data: yesCount.map(Number) },
    	    {
    	      name: 'No',
    	        "colorByPoint": false ,
    	          color: '#3F78CF',
    	      data: noCount.map(Number) },
    	    ] });
	
	
		</script>
		
		
		<script>
		
		var buybackprogramgson=${buybackprogramgson};
		 console.log(buybackprogramgson)
		  var arrMonth=[];
		  var yesCount = [];
		  var noCount = [];
		  $.each(buybackprogramgson,function(k,v){
			  arrMonth.push(v.month+","+v.year);
			  yesCount.push(v.yesCount);
		   	  noCount.push(v.noCount) ;
			  
		  });
		  console.log(yesCount)
		    console.log(noCount)
	
      Highcharts.chart('container-bar-graph-financial1', {
    	    chart: {
    	      type: 'column' },
    	  
    	    title: {
    	      align: 'center',
    	      text: null,
    	       style: {
    	              color: '#6c7070',
    	              fontWeight: '500',
    	           fontSize:'10px'
    	          }
    	    },        
    	   credits: {
    	        enabled: false
    	    },
    	    xAxis: {
    	        tickLength: 0,
    	      categories: arrMonth },
    	  
    	    yAxis: {
    	       reversedStacks: false, 
    	      min: 0,
    	      title: {
    	        text: '' },
    	         labels: {
    	            format: '{value}%',         
    	        },
    	  
    	      stackLabels: {
    	        enabled: true,
    	        style: {
    	          fontWeight: 'bold',
    	          color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
    	  
    	  
    	  
    	    legend: {
    	      align: 'center',
    	      x: 0,
    	      verticalAlign: 'top',
    	      y: 0,
    	      floating: false,
    	      backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
    	      // borderColor: '#CCC',
    	      // borderWidth: 1,
    	      shadow: false,
    	      itemStyle: {
    	              color: '#6c7070',
    	              fontWeight: 400,
    	           fontFamily: "'BMWGroup-Regular'",
    	          }
    	    },
    	  
    	    tooltip: {
    	      headerFormat: '<b>{point.x}</b><br/>',
    	      pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}' },
    	  
    	    plotOptions: {
    	      column: {
    	        stacking: 'percent',
    	        dataLabels: {
    	          enabled: true,
    	         } }, 
    	    series: {
    	        borderWidth: 0,
    	        //   pointWidth: 26,
    	           pointPadding: 0,
    	              groupPadding: 0.1,
    	              borderWidth: 0,
    	              shadow: false,
    	        dataLabels: {
    	            enabled: true,
    	            inside:true,
    	             rotation: 0,
    	           
    	          format: '{point.y}%',
    	            color:'#fff',
    	             style: {
    	                  textOutline: 0,
    	                  fontSize:'12px',
    	                  fontFamily: "'BMWGroup-Regular'",
    	    },
    	        }
    	      }
    	    },
    	  
    	    series: [
    	    
    	    {
    	      name: 'Yes ',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data: yesCount.map(Number) },
    	    {
    	      name: 'No',
    	        "colorByPoint": false ,
    	          color: '#3F78CF',
    	      data: noCount.map(Number) },
    	    ] });
	</script>
	<script>
	
	var offeredbreakdowngson=${offeredbreakdowngson};
	console.log(offeredbreakdowngson)
	
	 var dataArray=[];
	
	   $.each(offeredbreakdowngson,function(k,v){
        	//alert(v.percentage);
        	if(v.percentage!=0){
        	 var obj={};
        	obj["name"]=v.option_text;
        	obj["y"]=JSON.parse(v.percentage);
        	dataArray.push(obj);
        	}
        });
	
	
	// Create the chart
	var chart = Highcharts.chart('financialprocesschart', {
	  chart: {
	    type: 'pie'
	  },
	  title: {
	    text: '',
	    align: 'center',
	    verticalAlign: 'middle',
	    y: -35
	  },
	    credits: {
	      enabled: false
	  },
	  subtitle: {
	    text: null
	  },

	  plotOptions: {
	       pie: {
	             crop: false,
	            size: 200,
	           overflow: "visible",
	              showInLegend: true,
	        },
	    series: {
	      dataLabels: {
	        enabled: true,
	          overflow:true,
	          crop:false,
	        format: '{point.y:.2f}%',
	           style: {
	            color: '#70A9FE',
	           'font-family': "'BMWGroup-Regular'",
	             fontSize:'10px',
	             fontWeight:500
	         }
	      }
	    }
	  },

	  tooltip: {
		    headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
		    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
		  },
	  

	  "series": [
	    {
	      "name": "Browsers",
	      
	      "colorByPoint": true,
	        innerSize: '70%',
	        color: '#70A9FE',
	      "data": dataArray,
	    }
	  ],

	});

	</script>
	<script>
	 
  var handoverfinanceoffergson=${handoverfinanceoffergson};
  
  console.log("handoverfinanceoffergson=="+JSON.stringify(bmwfinancialservicegson))
  var arrMonth=[];
  var yesCount = [];
  var noCount = [];
  $.each(handoverfinanceoffergson,function(k,v){
	  arrMonth.push(v.month+","+v.year);
	  yesCount.push(v.yesCount);
   	  noCount.push(v.noCount) ;
	  
  });
      Highcharts.chart('container-bar-graph-financial2', {
	      chart: {
		        type: 'column' },
		        title: {
			        align: 'center',
			        text: '',
			         style: {
			                color: '#6c7070',
			                fontWeight: '500',
			             fontSize:'10px'
			            }
			      },           
			      credits: {
			          enabled: false
			      },
			      xAxis: {
			          tickLength: 0,
			          labels:{
			               style:{
			                    fontFamily: "'BMWGroup-Regular'",
			               }
			          },
			        categories: arrMonth },
			        yAxis: {
				         reversedStacks: false, 
				               y: 16,
				        min: 0,
				        title: {
				          text: '' },
				           labels: {
				              format: '{value}%',
				               style:{
				                    'font-family': "'BMWGroup-Regular'",
				               }
				          },
				      
				        stackLabels: {
				          enabled: false,
				          style: {
				            fontWeight: 'bold',
				            color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
    	  
				            legend: {
				    	        align: 'center',
				    	        x: 0,
				    	        verticalAlign: 'top',
				    	        y: 0,
				    	        floating: false,
				    	        backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
				    	        // borderColor: '#CCC',
				    	        // borderWidth: 1,
				    	        shadow: false,
				    	        itemStyle: {
				    	                color: '#6c7070',
				    	                fontWeight: 400,
				    	             fontFamily: "'BMWGroup-Regular'",
				    	            }
				    	      },
    	  
				    	      tooltip: {
				    		        headerFormat: '<b>{point.x}</b><br/>',
				    		        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}' },
    	  
				    		        plotOptions: {
				    			        column: {
				    			          stacking: 'percent',
				    			          dataLabels: {
				    			            enabled: true,
				    			           } }, 
				    			      series: {
				    			          borderWidth: 0,
				    			          //   pointWidth: 26,
				    			             pointPadding: 0,
				    			                groupPadding: 0.1,
				    			                borderWidth: 0,
				    			                shadow: false,
				    			                dataLabels: {
				    			                    enabled: true,
				    			                    inside:true,
				    			                     rotation: 0,
				    			                    verticalAlign:'middle',
				    			                    y:0,
				    			                  format: '{point.y}%',
				    			                    color:'#fff',
				    			                     style: {
				    			                          textOutline: 0,
				    			                          fontSize:'12px',
				    			                          fontFamily: "'BMWGroup-Regular'",
				    			            },
				    			                }
				    			        }
				    			      },
    	  
    	    series: [
    	    
    	    {
    	      name: 'Yes ',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data: yesCount.map(Number) },
    	    {
    	      name: 'No',
    	        "colorByPoint": false ,
    	          color: '#3F78CF',
    	      data: noCount.map(Number) },
    	    ] });
	
	
		</script>
<!-- Financial Service Analysis -->
<!-- BPS Analysis -->
<script type="text/javascript">
            var currentcustomervehiclegson=${currentcustomervehiclegson};
            
            console.log(currentcustomervehiclegson)
             var arrMonth=[];
             var yesCount = [];
             var noCount = [];
             $.each(currentcustomervehiclegson,function(k,v){
           	  arrMonth.push(v.month+","+v.year);
           	  yesCount.push(v.yesCount);
              	  noCount.push(v.noCount) ;
           	  
             });
             console.log(yesCount)
               console.log(noCount)
      
      Highcharts.chart('container-bar-graph-bps', {
    	  chart: {
    	    type: 'column'
    	  },
    	     legend: {
    	          enabled:true,
    	        align: 'center',
    	        verticalAlign: 'top',
    	        x: 0,
    	        y: 0,
    	        itemDistance: 7,
    	         itemStyle: {
    	            color: '#003366',
    	             fontSize:'13px',
    	            fontWeight: 400,
    	         fontFamily: "'BMWGroup-Regular'",
    	        }
    	          
    	    },
    	     credits: {
    	      enabled: false
    	  },
    	  title: {
    	    text: null
    	  },
    	  xAxis: {
    	    categories: arrMonth,
    	       tickLength: 0,
    	      lineColor: 'transparent',
    	      labels: {
    	         
    	         style: {
    	            color: '#003366',
    	            'font-family': "'BMWGroup-Regular'",
    	             'font-size':'13px',
    	             'font-weight':400,
    	         }
    	      },
    	  },
    	  yAxis: {
    	      reversedStacks: false,
    	    min: 0,
    	       gridLineColor: 'transparent',
    	        labels: {
    	            enabled: false,
    	        }, 
    	    title: {
    	      text: null
    	    }
    	  },
    	   
    	    
    	  tooltip: {
    	    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
    	    shared: true
    	  },
    	  plotOptions: {
    	    column: {
    	      stacking: 'percent'
    	    },
    	        series: {
    	      borderWidth: 0,
    	      //   pointWidth: 26,
    	         pointPadding: 0,
    	            groupPadding: 0.1,
    	            borderWidth: 0,
    	            shadow: false,
    	      dataLabels: {
    	          enabled: true,
    	          inside:true,
    	           rotation: 0,
    	          verticalAlign:'middle',
    	          y:0,
    	        format: '{point.y}%',
    	          color:'#fff',
    	           style: {
    	                textOutline: 0,
    	                fontSize:'12px',
    	                fontFamily: "'BMWGroup-Regular'",
    	  },
    	      }
    	    }
    	  },
    	  series: [
    {
    	    name: 'Yes',
    	      "colorByPoint": false ,

    	        color: '#70A9FE',
    	    data:yesCount.map(Number)
    	  },{
    		    name: 'No',
    		      "colorByPoint": false ,
    		      color: '#3F78CF',
    		        data: noCount.map(Number)
    		  }, ]
    	});

            </script>
<!-- BPS Analysis -->
	<!-- <script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBUYL2-x9gKza5NMgA882GjpEdti8ZqH20&amp;callback=myMap"></script> -->
		<script>
        function getmonths(bid){
        	$("#month").empty();
        	 $.ajax({
                 url: "<%=dashboardURL %>getmonthsforOvper",
                 type: "GET", 
                   data: { 'bid': bid },
                 success: function(response)
                             {
                	 $("#month").prepend("<option value=''>Select Month</option><option value='all'>All</option>");
					$.each(response,function(k,v){
						$("#month").append("<option value='"+v.month+"'>"+v.month_name+"</option>");
					})
                             }
       	  });
        }
        
        function getdealers(){
        	$("#Dealer").empty();
        	var bid=$("#brand_id").val();
        	var region_id=$("#Region").val();
        	var dealer_id=$("#Dealer").val();
        	 $.ajax({
                 url: "<%=dashboardURL %>getDealers",
                 type: "GET", 
                   data: { 'bid': bid,'region_id':region_id,'dealer_id':dealer_id },
                 success: function(response)
                             {
                	 $("#Dealer").prepend("<option value=''>Select Dealer</option><option value=''>All</option>");
					$.each(response,function(k,v){
						$("#Dealer").append("<option value='"+v.dealer_id+"'>"+v.dealer_name+"</option>");
					})
                             }
       	  });
        }
        
        function getoutlets(){
        	$("#Location").empty();
        	var did=$("#Dealer").val();
        	var bid=$("#brand_id").val();
        	var region_id=$("#Region").val();
        	 $.ajax({
                 url: "<%=dashboardURL %>getoutlets",
                 type: "GET", 
                   data: { 'did': did,'bid':bid,'region_id':region_id},
                 success: function(response)
                             {
                	 $("#Location").prepend("<option value=''>Select Outlet</option><option value=''>All</option>");
					$.each(response,function(k,v){
						$("#Location").append("<option value='"+v.outlet_id+"'>"+v.outlet_name+"</option>");
					})
                             }
       	  });
        }
        </script>
		
	<!-- scripts of new code END -->
</body>
</html>