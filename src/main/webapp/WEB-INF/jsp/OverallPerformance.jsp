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
        <title>Mystery</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
<link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">

<!-- App css -->
<link
	href="<%=UI_PATH%>/assets/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=UI_PATH%>/assets/css/icons.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=UI_PATH%>/assets/css/metismenu.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=UI_PATH%>/assets/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=UI_PATH%>/assets/css/custom.css"
	rel="stylesheet" type="text/css" />


<link rel="stylesheet"
	href="<%=UI_PATH%>/assets/graphs/css/style.css">
<link rel="stylesheet"
	href="<%=UI_PATH%>/assets/graphs/css/responsive.css">

<script
	src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>
		
		
		
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
			<!-- <button class="btn btn-primary" id="export">Download pdf</button> -->
				<form action="OverallPerformance" method="GET">
				<div class="position-relative">

							<div class="pt-3 pb-3  bg-white rounded desc-header"
								style="padding-left: 0px !important; margin-left: 18px;">
								<div class="d-flex flex-wrap ">
									<div class="col">
										<h2 class="d-inline-block pt-2"><b>Overall Performance</b></h2>
									</div>
									<div class="col col-160 ml-auto">
										<select class="form-control" name="brand_id"
											id="brand_id" onChange="getmonths(this.value);getdealers();getoutlets();getregionset()">
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
											<%-- <option value="">Select Region</option>
											<option value="" <c:if test = "${region_id == '' }">selected </c:if>>All</option>
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
                                <option value="" <c:if test = "${region_id == '' }">selected </c:if>>All</option>
											 <c:forEach var="rgBean" items="${activeRegionList}">
												<option value="${rgBean.sk_region_id}" <c:if test = "${region_id == rgBean.sk_region_id }">selected </c:if>>${rgBean.region_name}</option>
											</c:forEach>
                                </c:otherwise>
                               </c:choose>
											
										</select>
									</div>
									<div class="col-sm-6 col-md py-3">
										<label>Dealer</label> <select name="dealer_id"
											required class="form-control" id="Dealer"onChange="getoutlets()" >
											<%-- <option value="">Select Dealer</option>
											<option value="" <c:if test = "${dealer_id == '' }">selected </c:if>>All</option>
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
                                <option value="" <c:if test = "${dealer_id == '' }">selected </c:if>>All</option>
											 <c:forEach var="gBean" items="${activedealersbyid}">
												<option value="${gBean.dealer_id}" <c:if test = "${dealer_id == gBean.dealer_id }">selected </c:if>>${gBean.dealer_name}</option>
											</c:forEach> 
                                </c:otherwise>
                               </c:choose> 
											
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
									<div class="col-sm-6 col-md py-4" style="max-width: 100px;">
										<label></label>
										<button class="btn btn-primary btn-block" type="submit">View</button>
									</div>
								</div>
							</div>
						</div>
						</form>
					<div class="container-fluid my-3">
						<input type="hidden" value="${month}" id="monthtemp"> <input
							type="hidden" value="${dealer_id}" id="dealertemp"> <input
							type="hidden" value="${region_id}" id="regiontemp"> <input
							type="hidden" value="${outlet_id}" id="outlettemp">
  <div class="row my-3">
          <div class="col-md-12 text-center my-3">
            <h4 class="chart-title-heading">YTD Performance Trend</h4>
              <div class="shadow">
                
                               <c:forEach var="ytdmtd" items="${ytdmtd}">
								<input type="hidden" value="${ytdmtd.YTD_YTD}"
									class="ytd">
									<input type="hidden" value="${ytdmtd.MTD}"
									class="mtd">
									<input type="hidden" value="${ytdmtd.month}"
									class="monthytdmtd">
							</c:forEach>
                     <div  id="ytdchart" style="width: 100%; height: 300px; margin: 0 auto"></div>
                     
              </div>
          
          </div>
      
        </div>
        <div class="row justify-content-center">
            <div class="col-md-4 text-center my-3 ">
                <h4 class="chart-title-heading">Process Excellence</h4>
              <div class="shadow" id="processchart" style="width: 100%; height: 300px; margin: 0 auto"></div>
              
            </div>
            <div class="col-md-4 text-center my-3">
                <h4 class="chart-title-heading">Customer Treatment</h4>
              <div class="shadow" id="customerchart" style="width: 100%; height: 300px; margin: 0 auto"></div>
             
            </div> 
            <c:if test="${brand_id =='1'}">
            <div class="col-md-4 text-center my-3">
                <h4 class="chart-title-heading">Online Sales Channel</h4>
              <div class="shadow" id="Onlinesale" style="width: 100%; height: 300px; margin: 0 auto"></div>
              
            </div>
            </c:if>
        </div>

						 <div class="row my-3" style="display: none;">
							<div class="col-md-4 text-center">
								<h4>
									<b>YTD Performance Trend</b>
								</h4>
								<div class="border">

									<div id="ytdchart"
										style="width: 100%; height: 398px; margin: 0 auto"></div>
								</div>

							</div>
							<c:forEach var="gBean" items="${getProcessPercentage}" varStatus="count">
							<c:if test="${count.index<4}">
								<input type="hidden" value="${gBean.percentage}"
									id="processpercentage">
									</c:if>
							</c:forEach>

							<c:forEach var="gBean" items="${getProcessPercentage}">
								<c:forEach var="sectionscore" items="${gBean.sectionScore}"
									varStatus="count">

									<input type="hidden"
										value="${sectionscore.subsection_name},${sectionscore.percentage},<c:if test="${count.index=='0'}">#CCE3FF</c:if><c:if test="${count.index=='1'}">#99C8FF</c:if><c:if test="${count.index=='2'}">#83BCFF</c:if><c:if test="${count.index=='3'}">#5094FC</c:if><c:if test="${count.index=='4'}">#287cff</c:if>"
										class="processpercentagesubsection">
										
								</c:forEach>
							</c:forEach>

							<c:forEach var="gBean" items="${getCustomerPercentage}">
								<input type="hidden" value="${gBean.percentage}"
									id="customerpercentage">
							</c:forEach>

							<c:forEach var="gBean" items="${getCustomerPercentage}">
								<c:forEach var="sectionscore" items="${gBean.sectionScore}"
									varStatus="count">
									
									<input type="text"
										value="${sectionscore.subsection_name},${sectionscore.percentage},<c:if test="${count.index=='0'}">#CCE3FF</c:if><c:if test="${count.index=='1'}">#99C8FF</c:if><c:if test="${count.index=='2'}">#83BCFF</c:if><c:if test="${count.index=='3'}">#5094FC</c:if><c:if test="${count.index=='4'}">#287cff</c:if><c:if test="${count.index=='5'}">#1972fdc9</c:if>"
										class="customerpercentagesubsection">
									
								</c:forEach>
							</c:forEach>
					<c:forEach var="gBean" items="${getOnlineSalesChannel}">
								<input type="hidden" value="${gBean.percentage}"
									id="OnlineSalesChannelpercentage">
							</c:forEach>

							<c:forEach var="gBean" items="${getOnlineSalesChannel}" varStatus="count1">
								<c:forEach var="sectionscore" items="${gBean.sectionScore}"
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
						<!-- i have commented these three tables because client does not need these functionality -->
						<%-- <div class="row">    
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
						</div> --%>
					</div>
				</div>
				</div>
      </div>
       
                           
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
        <script src="<%=UI_PATH%>/assets/js/jquery.slimscroll.js"></script>
         <!-- Modal-Effect -->
        <script src="<%=UI_PATH%>/plugins/custombox/js/custombox.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/custombox/js/legacy.min.js"></script>
        
		 <!-- plugin Js -->
        <script src="<%=UI_PATH%>/plugins/switchery/switchery.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/select2/js/select2.min.js" type="text/javascript"></script>
        <script src="<%=UI_PATH%>/plugins/bootstrap-select/js/bootstrap-select.js" type="text/javascript"></script>
        <!--  Required datatable js -->
        <script src="<%=UI_PATH%>/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/dataTables.bootstrap4.min.js"></script>
        <!-- Buttons examples -->
        <script src="<%=UI_PATH%>/plugins/datatables/dataTables.buttons.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/buttons.bootstrap4.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/jszip.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/pdfmake.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/vfs_fonts.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/buttons.html5.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/buttons.print.min.js"></script>
        <!-- Responsive examples -->
        <script src="<%=UI_PATH%>/plugins/datatables/dataTables.responsive.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/responsive.bootstrap4.min.js"></script>

        <!--  App js -->
        <script src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
        <script src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>
        <%-- <script src="<%=UI_PATH%>/js/script.js"></script> --%>
          
        <!-- Init Js file -->
        
        <%-- <script type="text/javascript" src="<%=UI_PATH%>/assets/pages/jquery.form-advanced.init.js"></script> --%>
        <!-- Parsley js -->
        <script type="text/javascript" src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>
        <!-- manoj d starts -->
        
        <!-- jQuery  -->
        
	
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
		
		<!-- App js -->
	<script
		src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>
		
        <!-- Graphs Js's start -->
        <!-- high-charts -->
	<script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="<%=UI_PATH%>/assets/graphs/js/ytdtrend-chart.js"></script>
        <script src="<%=UI_PATH%>/assets/graphs/js/processexcellence-chart.js"></script>
    <script src="<%=UI_PATH%>/assets/graphs/js/customertreatment-chart.js"></script>
     <script src="<%=UI_PATH%>/assets/graphs/js/onlinesaleperformance.js"></script>
      <script src="<%=UI_PATH%>/assets/graphs/js/custom-script.js"></script> 
        <!-- Graphs Js's end-->

<script>
        
        function getregionset(){
     	   
     	   $('#Region').prop('selectedIndex',0);
        }
        
        </script>
<script>
        function getmonths(bid){
        	$("#month").empty();
        	 $.ajax({
                 url: "<%=dashboardURL%>getmonthsforOvper",
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
                 url: "<%=dashboardURL%>getoutlets",
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
<!-- manoj d ends -->
        <script type="text/javascript">
            $(document).ready(function() {
                $('#datatable').DataTable();

                //Buttons examples
                var table = $('#datatable-buttons').DataTable({
                    lengthChange: false,
                    buttons: ['copy', 'excel', 'pdf']
                });

                table.buttons().container()
                        .appendTo('#datatable-buttons_wrapper .col-md-6:eq(0)');
            } );

        </script>
      
</body>
</html>