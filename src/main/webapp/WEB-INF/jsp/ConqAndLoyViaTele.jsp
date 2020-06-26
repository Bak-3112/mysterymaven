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
    
        <meta charset="utf-8" />
<title>CL contact</title>
<link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">

		<link href="<%=UI_PATH%>/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <!-- DataTables --> 
        <link href="<%=UI_PATH%>/plugins/datatables/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/plugins/datatables/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css" />
         <!-- Responsive datatable examples -->
        <link href="<%=UI_PATH%>/plugins/datatables/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        
        <!-- Custom box css -->
        <link href="<%=UI_PATH%>/plugins/custombox/css/custombox.min.css" rel="stylesheet">

        <!-- App css -->
        <link href="<%=UI_PATH%>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/metismenu.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/style.css" rel="stylesheet" type="text/css" />

        <script src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>
		<%-- <link rel="stylesheet" href="<%=UI_PATH%>/assets/css/nice-select.css" type="text/css" /> --%>
		
		  <script src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">       
        <link rel="stylesheet" href="<%=UI_PATH%>/assets/graphs/css/style.css">
        <link rel="stylesheet" href="<%=UI_PATH%>/assets/graphs/css/responsive.css">  

<script src="assets/js/modernizr.min.js"></script>
<style>
.dot {
	height: 10px;
	width: 10px;
	background-color: #3c86d8;
	border-radius: 50%;
	display: inline-block;
}

.dot1 {
	height: 10px;
	width: 10px;
	background-color: #d8c53c;
	border-radius: 50%;
	display: inline-block;
}
.chart-main-head{ font-weight:600; color:#000;   font-size: 15px;
    padding-bottom: 15px;}
</style>
		
		
    </head>

    <body>
    <div id="wrapper">
        <!-- Navigation Bar-->
        <jsp:include page="include/header.jsp"></jsp:include>
         <jsp:include page="include/sidemenu.jsp"></jsp:include>
          <jsp:include page="include/modal.jsp"></jsp:include>
        <!-- End Navigation Bar-->
       <div class="content-page">
			<!-- Start content -->
			<div class="content">
				<div class="main-content  position-relative nav-left">
				<!-- <button class="btn btn-primary" id="export"> Download pdf</button> -->
					<form action="conquestContactTele" method="GET">
						<div class="position-relative">

							<div class="pt-3 pb-3  bg-white rounded desc-header"
								style="padding-left: 0px !important; margin-left: 18px;">
								<div class="d-flex flex-wrap ">
									<div class="col">
										<h2 class="d-inline-block pt-2"><b>Conquest & Loyalty: Telephonic Enquiry</b></h2>
									</div>
									<div class="col col-160 ml-auto">
										<select class="form-control" name="brand_id"
											id="brand_id" onChange="getmonths(this.value);getdealers();getoutlets();getregionset();">
										 <c:forEach var="gBean" items="${BrandList}" varStatus="count">
												<option value="${gBean.brand_id}" <c:if test = "${brand_id == gBean.brand_id }">selected </c:if>>${gBean.brand_name}</option>
											</c:forEach> 
											
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="container-fluid my-3">
							<div class="card shadow-card">
						
						
							<div class="row d-flex justify-content-center px-4 mb-3  mt-3">

									<div class="col-sm-6 col-md py-3">
										<label>Month</label> <select class="form-control" name="month"
											id="month">
											<option value="">Select Month</option>
											<option value="all" <c:if test = "${month == 'all' }">selected </c:if>>All</option>
										 <c:forEach var="gBean" items="${getMonths}">
												<option value="${gBean.month}" <c:if test = "${month == gBean.month }">selected </c:if>>${gBean.month_name}</option>
											</c:forEach> 

										</select>
									</div>
									<div class="col-sm-6 col-md py-3">
										<label>Region</label> <select name="region_id"
											class="form-control" id="Region" onChange="getdealers();getoutlets()">
												<%-- <option value="">Select Region</option>
											<option value="all" <c:if test = "${region_id == 'all' }">selected </c:if>>All</option>
											 <c:forEach var="rgBean" items="${activeRegionList}">
												<option value="${rgBean.sk_region_id}" <c:if test = "${region_id == rgBean.sk_region_id }">selected </c:if>>${rgBean.region_name}</option>
											</c:forEach> --%> 
											
											<c:choose>
                                           <c:when test = "${role_id == 7}">
                                   <c:forEach var="rgBean" items="${activeRegionList}">
												<option value="${rgBean.sk_region_id}" <c:if test = "${region_id == rgBean.sk_region_id }">selected </c:if>>${rgBean.region_name}</option>
											</c:forEach> 
                                      </c:when>
                                <c:otherwise>
                                <option value="">Select Region</option>
                                <option value="all" <c:if test = "${region_id == 'all' }">selected </c:if>>All</option>
											 <c:forEach var="rgBean" items="${activeRegionList}">
												<option value="${rgBean.sk_region_id}" <c:if test = "${region_id == rgBean.sk_region_id }">selected </c:if>>${rgBean.region_name}</option>
											</c:forEach>
                                </c:otherwise>
                               </c:choose>
											
										</select>
									</div>
									<div class="col-sm-6 col-md py-3">
										<label>Dealer</label> <select name="dealer_id"
											class="form-control" id="Dealer"onChange="getoutlets()" >
											<%-- <option value="">Select Dealer</option>
											<option value="all" <c:if test = "${dealer_id == 'all' }">selected </c:if>>All</option>
											 <c:forEach var="gBean" items="${activedealersbyid}">
												<option value="${gBean.dealer_id}" <c:if test = "${dealer_id == gBean.dealer_id }">selected </c:if>>${gBean.dealer_name}</option>
											</c:forEach> --%> 
											
											<c:choose>
                                           <c:when test = "${role_id == 7}">
                                           <c:forEach var="gBean" items="${activedealersbyid}">
												<option value="${gBean.dealer_id}" <c:if test = "${dealer_id == gBean.dealer_id }">selected </c:if>>${gBean.dealer_name}</option>
											</c:forEach> 
                                      </c:when>
											<c:otherwise>
											<option value="">Select Dealer</option>
                                <option value="all" <c:if test = "${dealer_id == 'all' }">selected </c:if>>All</option>
											 <c:forEach var="gBean" items="${activedealersbyid}">
												<option value="${gBean.dealer_id}" <c:if test = "${dealer_id == gBean.dealer_id }">selected </c:if>>${gBean.dealer_name}</option>
											</c:forEach> 
                                </c:otherwise>
                               </c:choose>

										</select>
									</div>
									<div class="col-sm-6 col-md py-3">
										<label>Location</label> <select name="outlet_id"
											class="form-control" id="Location">
											<option value="">Outlet Location</option>
											<option value="all" <c:if test = "${outlet_id == 'all' }">selected </c:if>>All</option>
											 <c:forEach var="gBean" items="${activeoutletsbyid}">
												<option value="${gBean.outlet_id}" <c:if test = "${outlet_id == gBean.outlet_id }">selected </c:if>>${gBean.outlet_name}</option>
											</c:forEach> 
											
										</select>
									</div>
									<!-- <div class="col-sm-6 col-md py-3">
                    <label>Year</label> <select name="year"
                      class="form-control" id="year">
                      <option value="">Select Year</option> 
                                            <option value="2019">2019</option>
                                             <option value="2020">2020</option>                    
                    </select>
                  </div> -->
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
								</div>
							</div>
						</div>
					</form>
					<div class="container">
						<div class="row my-3">
            <div class="col-md-6 my-3">
             <h6 class="text-center  chart-title-heading "><b>Did the sales representative define next steps / provide information on next steps to take?</b></h6>
                <div class="shadow">
                 
                 <div id="responsecontainer" >
                 <img alt="" src="" id="canvasimg1">
                 </div>
                   <%-- <div id="responsecontainer" style="min-width: 310px; height: 300px; margin: 0px auto; overflow: hidden;" data-highcharts-chart="0"><div id="highcharts-s80m0k6-0" dir="ltr" class="highcharts-container " style="position: relative; overflow: hidden; width: 348px; height: 300px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg version="1.1" class="highcharts-root" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="348" height="300" viewBox="0 0 348 300"><desc>Created with Highcharts 7.0.3</desc><defs><clipPath id="highcharts-s80m0k6-1"><rect x="0" y="0" width="328" height="225" fill="none"></rect></clipPath></defs><rect fill="#ffffff" class="highcharts-background" x="0" y="0" width="348" height="300" rx="0" ry="0"></rect><rect fill="none" class="highcharts-plot-background" x="10" y="23" width="328" height="225"></rect><g class="highcharts-grid highcharts-xaxis-grid " data-z-index="1"><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 91.5 23 L 91.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 173.5 23 L 173.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 255.5 23 L 255.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 337.5 23 L 337.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 9.5 23 L 9.5 248" opacity="1"></path></g><g class="highcharts-grid highcharts-yaxis-grid " data-z-index="1"><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 248.5 L 338 248.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 192.5 L 338 192.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 136.5 L 338 136.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 79.5 L 338 79.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 22.5 L 338 22.5" opacity="1"></path></g><rect fill="none" class="highcharts-plot-border" data-z-index="1" x="10" y="23" width="328" height="225"></rect><g class="highcharts-axis highcharts-xaxis " data-z-index="2"><path fill="none" class="highcharts-axis-line" stroke="transparent" stroke-width="1" data-z-index="7" d="M 10 248.5 L 338 248.5"></path></g><g class="highcharts-axis highcharts-yaxis " data-z-index="2"><path fill="none" class="highcharts-axis-line" data-z-index="7" d="M 10 23 L 10 248"></path></g><g class="highcharts-series-group" data-z-index="3"><g data-z-index="0.1" class="highcharts-series highcharts-series-0 highcharts-column-series highcharts-tracker " transform="translate(10,23) scale(1 1)" clip-path="url(#highcharts-s80m0k6-1)"><rect x="8" y="0" width="66" height="226" fill="#3F78CF" class="highcharts-point"></rect><rect x="90" y="0" width="66" height="226" fill="rgb(63,120,207)" class="highcharts-point "></rect><rect x="172" y="0" width="66" height="226" fill="rgb(63,120,207)" class="highcharts-point "></rect><rect x="254" y="0" width="66" height="226" fill="rgb(63,120,207)" class="highcharts-point "></rect></g><g data-z-index="0.1" class="highcharts-markers highcharts-series-0 highcharts-column-series " transform="translate(10,23) scale(1 1)" clip-path="none"></g></g><text x="174" text-anchor="middle" class="highcharts-title" data-z-index="4" style="color:#333333;font-size:18px;fill:#333333;" y="24"></text><text x="174" text-anchor="middle" class="highcharts-subtitle" data-z-index="4" style="color:#666666;fill:#666666;" y="24"></text><g data-z-index="6" class="highcharts-data-labels highcharts-series-0 highcharts-column-series highcharts-tracker " transform="translate(10,23) scale(1 1)" opacity="1"><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(18,101)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">100%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(100,101)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">100%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(182,101)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">100%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(264,101)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">100%</text></g></g><g class="highcharts-legend" data-z-index="7" transform="translate(144,-15)"><rect fill="none" class="highcharts-legend-box" rx="0" ry="0" x="0" y="0" width="59" height="26" visibility="visible"></rect><g data-z-index="1"><g><g class="highcharts-legend-item highcharts-column-series highcharts-color-undefined highcharts-series-0" data-z-index="1" transform="translate(8,3)"><text x="21" style="color:#003366;cursor:pointer;font-size:13px;font-weight:400;font-family:'BMWGroup-Regular';fill:#003366;" text-anchor="start" data-z-index="2" y="16"><tspan>Yes</tspan></text><rect x="1.5" y="4" width="13" height="13" fill="#3F78CF" rx="6.5" ry="6.5" class="highcharts-point" data-z-index="3"></rect></g></g></g></g><g class="highcharts-axis-labels highcharts-xaxis-labels " data-z-index="7"><text x="51" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>January,</tspan><tspan dy="14" x="51">2018</tspan></text><text x="133" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>April, 2018</tspan></text><text x="215" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>July, 2018</tspan></text><text x="297" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>October,</tspan><tspan dy="14" x="297">2018</tspan></text></g><g class="highcharts-axis-labels highcharts-yaxis-labels " data-z-index="7"></g><g class="highcharts-label highcharts-tooltip highcharts-color-undefined" style="pointer-events:none;white-space:nowrap;" data-z-index="8" transform="translate(159,-9999)" opacity="0" visibility="visible"><path fill="none" class="highcharts-label-box highcharts-tooltip-box highcharts-shadow" d="M 3.5 0.5 L 49.5 0.5 55.5 -5.5 61.5 0.5 109 0.5 C 112.5 0.5 112.5 0.5 112.5 3.5 L 112.5 44.5 C 112.5 47.5 112.5 47.5 109.5 47.5 L 3.5 47.5 C 0.5 47.5 0.5 47.5 0.5 44.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#000000" stroke-opacity="0.049999999999999996" stroke-width="5" transform="translate(1, 1)"></path><path fill="none" class="highcharts-label-box highcharts-tooltip-box highcharts-shadow" d="M 3.5 0.5 L 49.5 0.5 55.5 -5.5 61.5 0.5 109 0.5 C 112.5 0.5 112.5 0.5 112.5 3.5 L 112.5 44.5 C 112.5 47.5 112.5 47.5 109.5 47.5 L 3.5 47.5 C 0.5 47.5 0.5 47.5 0.5 44.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#000000" stroke-opacity="0.09999999999999999" stroke-width="3" transform="translate(1, 1)"></path><path fill="none" class="highcharts-label-box highcharts-tooltip-box highcharts-shadow" d="M 3.5 0.5 L 49.5 0.5 55.5 -5.5 61.5 0.5 109 0.5 C 112.5 0.5 112.5 0.5 112.5 3.5 L 112.5 44.5 C 112.5 47.5 112.5 47.5 109.5 47.5 L 3.5 47.5 C 0.5 47.5 0.5 47.5 0.5 44.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#000000" stroke-opacity="0.15" stroke-width="1" transform="translate(1, 1)"></path><path fill="rgba(247,247,247,0.85)" class="highcharts-label-box highcharts-tooltip-box" d="M 3.5 0.5 L 49.5 0.5 55.5 -5.5 61.5 0.5 109 0.5 C 112.5 0.5 112.5 0.5 112.5 3.5 L 112.5 44.5 C 112.5 47.5 112.5 47.5 109.5 47.5 L 3.5 47.5 C 0.5 47.5 0.5 47.5 0.5 44.5 L 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#3F78CF" stroke-width="1"></path><text x="8" data-z-index="1" style="font-size:12px;color:#333333;cursor:default;fill:#333333;" y="20"><tspan style="font-size: 10px">July, 2018</tspan><tspan style="fill:#3F78CF" x="8" dy="15">Yes</tspan><tspan dx="0">: </tspan><tspan style="font-weight:bold" dx="0">100</tspan><tspan dx="0"> (100%)</tspan></text></g></svg></div></div>
                 --%></div>
            </div>
            <div class="col-md-6 my-3">
             <h6 class="text-center chart-title-heading "><b>Did the response meet the standards?</b></h6>
                <div class="shadow mt-4">
                 
                    <div id="responsecontainer2" >
                    <img alt="" src="" id="canvasimg2">
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
                <img alt="" src="" id="canvasimg3">
               <%--  <div id="dealers" style="min-width: 310px; height: 300px; margin: 0px auto; overflow: hidden;" data-highcharts-chart="2"><div id="highcharts-s80m0k6-12" dir="ltr" class="highcharts-container " style="position: relative; overflow: hidden; width: 348px; height: 300px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg version="1.1" class="highcharts-root" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="348" height="300" viewBox="0 0 348 300"><desc>Created with Highcharts 7.0.3</desc><defs><clipPath id="highcharts-s80m0k6-13"><rect x="0" y="0" width="328" height="180" fill="none"></rect></clipPath></defs><rect fill="#ffffff" class="highcharts-background" x="0" y="0" width="348" height="300" rx="0" ry="0"></rect><rect fill="none" class="highcharts-plot-background" x="10" y="68" width="328" height="180"></rect><g class="highcharts-grid highcharts-xaxis-grid " data-z-index="1"><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 91.5 68 L 91.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 173.5 68 L 173.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 255.5 68 L 255.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 337.5 68 L 337.5 248" opacity="1"></path><path fill="none" data-z-index="1" class="highcharts-grid-line" d="M 9.5 68 L 9.5 248" opacity="1"></path></g><g class="highcharts-grid highcharts-yaxis-grid " data-z-index="1"><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 248.5 L 338 248.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 158.5 L 338 158.5" opacity="1"></path><path fill="none" stroke="transparent" stroke-width="1" data-z-index="1" class="highcharts-grid-line" d="M 10 67.5 L 338 67.5" opacity="1"></path></g><rect fill="none" class="highcharts-plot-border" data-z-index="1" x="10" y="68" width="328" height="180"></rect><g class="highcharts-axis highcharts-xaxis " data-z-index="2"><path fill="none" class="highcharts-axis-line" stroke="transparent" stroke-width="1" data-z-index="7" d="M 10 248.5 L 338 248.5"></path></g><g class="highcharts-axis highcharts-yaxis " data-z-index="2"><path fill="none" class="highcharts-axis-line" data-z-index="7" d="M 10 68 L 10 248"></path></g><g class="highcharts-series-group" data-z-index="3"><g data-z-index="0.1" class="highcharts-series highcharts-series-0 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" clip-path="url(#highcharts-s80m0k6-13)"><rect x="8" y="172" width="66" height="9" fill="#3F78CF" class="highcharts-point"></rect><rect x="90" y="165" width="66" height="16" fill="#3F78CF" class="highcharts-point"></rect><rect x="172" y="158" width="66" height="23" fill="#3F78CF" class="highcharts-point"></rect><rect x="254" y="172" width="66" height="9" fill="#3F78CF" class="highcharts-point"></rect></g><g data-z-index="0.1" class="highcharts-markers highcharts-series-0 highcharts-column-series " transform="translate(10,68) scale(1 1)" clip-path="none"></g><g data-z-index="0.1" class="highcharts-series highcharts-series-1 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" clip-path="url(#highcharts-s80m0k6-13)"><rect x="8" y="158" width="66" height="14" fill="#5094FC" class="highcharts-point"></rect><rect x="90" y="131" width="66" height="34" fill="#5094FC" class="highcharts-point"></rect><rect x="172" y="152" width="66" height="6" fill="#5094FC" class="highcharts-point"></rect><rect x="254" y="158" width="66" height="14" fill="#5094FC" class="highcharts-point"></rect></g><g data-z-index="0.1" class="highcharts-markers highcharts-series-1 highcharts-column-series " transform="translate(10,68) scale(1 1)" clip-path="none"></g><g data-z-index="0.1" class="highcharts-series highcharts-series-2 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" clip-path="url(#highcharts-s80m0k6-13)"><rect x="8" y="69" width="66" height="89" fill="#83BCFF" class="highcharts-point"></rect><rect x="90" y="15" width="66" height="116" fill="#83BCFF" class="highcharts-point"></rect><rect x="172" y="24" width="66" height="128" fill="#83BCFF" class="highcharts-point"></rect><rect x="254" y="19" width="66" height="139" fill="#83BCFF" class="highcharts-point"></rect></g><g data-z-index="0.1" class="highcharts-markers highcharts-series-2 highcharts-column-series " transform="translate(10,68) scale(1 1)" clip-path="none"></g><g data-z-index="0.1" class="highcharts-series highcharts-series-3 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" clip-path="url(#highcharts-s80m0k6-13)"><rect x="8" y="0" width="66" height="69" fill="#B0D5FF" class="highcharts-point"></rect><rect x="90" y="0" width="66" height="15" fill="#B0D5FF" class="highcharts-point"></rect><rect x="172" y="0" width="66" height="24" fill="#B0D5FF" class="highcharts-point"></rect><rect x="254" y="0" width="66" height="19" fill="#B0D5FF" class="highcharts-point"></rect></g><g data-z-index="0.1" class="highcharts-markers highcharts-series-3 highcharts-column-series " transform="translate(10,68) scale(1 1)" clip-path="none"></g></g><text x="174" text-anchor="middle" class="highcharts-title" data-z-index="4" style="color:#333333;font-size:18px;fill:#333333;" y="24"></text><text x="174" text-anchor="middle" class="highcharts-subtitle" data-z-index="4" style="color:#666666;fill:#666666;" y="24"></text><g data-z-index="6" class="highcharts-data-labels highcharts-series-0 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" opacity="1"><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(25,162)" opacity="0" visibility="hidden"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">5%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(107,161)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">9%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(186,158)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">13%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(271,162)" opacity="0" visibility="hidden"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">5%</text></g></g><g data-z-index="6" class="highcharts-data-labels highcharts-series-1 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" opacity="1"><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(25,154)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">8%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(104,137)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">19%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(189,144)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">3%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(271,154)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">8%</text></g></g><g data-z-index="6" class="highcharts-data-labels highcharts-series-2 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" opacity="1"><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(22,102)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">49%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(104,62)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">64%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(186,77)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">71%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(268,77)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">77%</text></g></g><g data-z-index="6" class="highcharts-data-labels highcharts-series-3 highcharts-column-series highcharts-tracker" transform="translate(10,68) scale(1 1)" opacity="1"><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(22,23)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">38%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(107,-4)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">8%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(186,1)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">13%</text></g><g class="highcharts-label highcharts-data-label highcharts-data-label-color-undefined " data-z-index="1" transform="translate(268,-2)"><text x="5" data-z-index="1" style="font-size:12px;font-weight:bold;font-family:'BMWGroup-Regular';color:#fff;fill:#fff;" y="17">10%</text></g></g><g class="highcharts-legend" data-z-index="7" transform="translate(88,-15)"><rect fill="none" class="highcharts-legend-box" rx="0" ry="0" x="0" y="0" width="172" height="71" visibility="visible"></rect><g data-z-index="1"><g><g class="highcharts-legend-item highcharts-column-series highcharts-color-undefined highcharts-series-0" data-z-index="1" transform="translate(8,3)"><text x="21" style="color:#003366;cursor:pointer;font-size:13px;font-weight:600;font-family:'BMWGroup-Regular';fill:#003366;" text-anchor="start" data-z-index="2" y="16"><tspan>After 4 hours on the â¦</tspan></text><rect x="1.5" y="4" width="13" height="13" fill="#3F78CF" rx="6.5" ry="6.5" class="highcharts-point" data-z-index="3"></rect></g><g class="highcharts-legend-item highcharts-column-series highcharts-color-undefined highcharts-series-1" data-z-index="1" transform="translate(8,18)"><text x="21" y="16" style="color:#003366;cursor:pointer;font-size:13px;font-weight:600;font-family:'BMWGroup-Regular';fill:#003366;" text-anchor="start" data-z-index="2"><tspan>Not at all</tspan></text><rect x="1.5" y="4" width="13" height="13" fill="#5094FC" rx="6.5" ry="6.5" class="highcharts-point" data-z-index="3"></rect></g><g class="highcharts-legend-item highcharts-column-series highcharts-color-undefined highcharts-series-2" data-z-index="1" transform="translate(8,33)"><text x="21" y="16" style="color:#003366;cursor:pointer;font-size:13px;font-weight:600;font-family:'BMWGroup-Regular';fill:#003366;" text-anchor="start" data-z-index="2"><tspan>Within 2 working â¦</tspan></text><rect x="1.5" y="4" width="13" height="13" fill="#83BCFF" rx="6.5" ry="6.5" class="highcharts-point" data-z-index="3"></rect></g><g class="highcharts-legend-item highcharts-column-series highcharts-color-undefined highcharts-series-3" data-z-index="1" transform="translate(8,48)"><text x="21" y="16" style="color:#003366;cursor:pointer;font-size:13px;font-weight:600;font-family:'BMWGroup-Regular';fill:#003366;" text-anchor="start" data-z-index="2"><tspan>Within 4 hours â¦</tspan></text><rect x="1.5" y="4" width="13" height="13" fill="#B0D5FF" rx="6.5" ry="6.5" class="highcharts-point" data-z-index="3"></rect></g></g></g></g><g class="highcharts-axis-labels highcharts-xaxis-labels " data-z-index="7"><text x="51" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>January,</tspan><tspan dy="14" x="51">2018</tspan></text><text x="133" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>April, 2018</tspan></text><text x="215" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>July, 2018</tspan></text><text x="297" style="color:#003366;cursor:default;font-size:11px;font-family:'BMWGroup-Regular';font-size:13px;font-weight:400;fill:#003366;" text-anchor="middle" transform="translate(0,0)" y="267" opacity="1"><tspan>October,</tspan><tspan dy="14" x="297">2018</tspan></text></g><g class="highcharts-axis-labels highcharts-yaxis-labels " data-z-index="7"></g></svg></div></div>
             --%>  </div>
            </div>
           
        </div>
					</div>
				</div>

			</div>
			<!-- container -->

		</div></div>
       
                           
        <!-- end wrapper -->


        <!-- Footer -->
        <jsp:include page="include/footer.jsp"></jsp:include>
        <!-- End Footer -->


        <!--  jQuery  -->
        <script src="<%=UI_PATH%>/assets/js/jquery.min.js"></script>
        <script src="<%=UI_PATH%>/assets/js/popper.min.js"></script>
        <script src="<%=UI_PATH%>/assets/js/bootstrap.min.js"></script>
        <script src="<%=UI_PATH%>/assets/js/metisMenu.min.js"></script>
        <script src="<%=UI_PATH%>/assets/js/waves.js"></script>



        <!-- Parsley js -->
        <script type="text/javascript" src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>
        
        <!-- jQuery  -->
         <script src="https://code.highcharts.com/highcharts.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/jquery.slimscroll.js"></script>
		
		<!-- App js -->
	<script
		src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>


       <script>
       
       function getregionset(){
    	   
    	   $('#Region').prop('selectedIndex',0);
       }
       
       
        function getmonths(bid){
        	$("#month").empty();
        	 $.ajax({
                 url: "<%=dashboardURL%>getmonths",
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
                 url: "<%=dashboardURL%>getDealers",
                 type: "GET", 
                   data: { 'bid': bid,'region_id':region_id ,'dealer_id':dealer_id},
                 success: function(response)
                             {
                	 $("#Dealer").prepend("<option value=''>Select Dealer</option><option value='all'>All</option>");
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
                 url: "<%=dashboardURL%>getoutlets",
                 type: "GET", 
                   data: { 'did': did,'bid':bid,'region_id':region_id},
                 success: function(response)
                             {
                	 $("#Location").prepend("<option value=''>Select Outlet</option><option value='all'>All</option>");
					$.each(response,function(k,v){
						$("#Location").append("<option value='"+v.outlet_id+"' >"+v.outlet_name+"</option>");
					})
                             }
       	  });
        }
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
  
  Highcharts.chart('responsecontainer', {
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
     
  Highcharts.chart('responsecontainer2', {
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
	alert(year);
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
      var year='';
      
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
       <script>
    $(document).ready(function() {
       var usedNames = {};
      $("select[name='year'] > option").each(function() {
        if (usedNames[this.text]) {
          $(this).remove();
        } else {
          usedNames[this.text] = this.value;
        }
      }); 
      
    });
    </script>
</body>
</html>