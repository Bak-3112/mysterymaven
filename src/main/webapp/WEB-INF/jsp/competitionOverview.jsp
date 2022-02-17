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

<!--Morris Chart CSS -->
<link rel="stylesheet"
	href="<%=UI_PATH%>/plugins/morris/morris.css">

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

<script src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>
<style>
.dot {
	height: 10px;
	width: 10px;
	border-radius: 50%;
	display: inline-block;
}

.header-title {
	background-color: #ffff !important;
	color: black !important;
}

.card-box {
	box-shadow: none !important;
}
/* .region{border-bottom: 1px solid black; background-color: #6c6666!important;color: white!important;} */
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
					<div class="main-content  position-relative nav-left mb-3">
						<div class="position-relative">

							<div class="pt-3 pb-3  bg-white rounded desc-header"
								style="padding-left: 0px !important; margin-left: 18px;">
								<div class="d-flex flex-wrap ">
									<div class="col">
										<h2 class="d-inline-block pt-2">
											<b>Competition Overview</b>
											<!-- <button class="btn btn-primary" id="export">Download pdf</button> -->
										</h2>
									</div>

								</div>
							</div>
						</div>

						<div class="container-fluid">
						
						<div class="row">
						<div class="col-lg-12 my-2">
						<form action="CompetitionOverview"
											method="GET">

											<div class="card shadow-card">
						
						
							<div class="row d-flex justify-content-center px-4 mb-3  mt-3">
							<input type="hidden" name="brand" value="" id="brand">
								<div class="col-sm-6 col-md py-3">
									<label>Region</label>
									<select name="region_id"
											class="form-control" id="Region" onChange="getdealers();getoutlets();">
											<%-- <option value="">Select Region</option>
											<option value="" <c:if test = "${region_id == '' }">selected </c:if>>All</option>
											 <c:forEach var="rgBean" items="${activeRegionList}">
												<option value="${rgBean.sk_region_id}" <c:if test = "${region_id == rgBean.sk_region_id }">selected </c:if>>${rgBean.region_name}</option>
											</c:forEach>  --%>
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
									<label>Dealer</label>
									<select name="dealer_id"
											class="form-control" id="Dealer"onChange="getoutlets();" >
											<%-- <option value="">Select Dealer</option>
											<option value="" <c:if test = "${dealer_id == '' }">selected </c:if>>All</option>
											 <c:forEach var="gBean" items="${activedealersbyid}">
												<option value="${gBean.dealer_id}" <c:if test = "${dealer_id == gBean.dealer_id }">selected </c:if>>${gBean.dealer_name}</option>
											</c:forEach>  --%>
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
										<label>Location</label>
									<select name="outlet_id"
											class="form-control" id="Location">
											<option value="">Select Location</option>
											<option value="" <c:if test = "${outlet_id == '' }">selected </c:if>>All</option>
											 <c:forEach var="gBean" items="${activeoutletsbyid}">
												<option value="${gBean.outlet_id}" <c:if test = "${outlet_id == gBean.outlet_id }">selected </c:if>>${gBean.outlet_name}</option>
											</c:forEach> 
											
										</select>
								</div>
								<div class="col-sm-6 col-md py-4" style="max-width:100px;">
											<label></label>
									<button class="btn btn-primary btn-block" type="submit">View</button>
								</div>
							</div> 
							</div>
										</form>
										</div>
						</div>
						
						
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

          <div class="col-lg-8 my-2 ">
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
								</div>
          
                   
      </div>
						</div>
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
												<div id="column-bar-chart1" class="bar-container"
													style="width: 100%; height: 270px; margin: 0 auto"></div>
												<img alt="" src="" id="canvasimg1"> <span><center>Region
														1</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<div id="column-bar-chart2" class="bar-container"
													style="width: 100%; height: 270px; margin: 0 auto"></div>
												<img alt="" src="" id="canvasimg2"> <span><center>Region
														2</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<div id="column-bar-chart3" class="bar-container"
													style="width: 100%; height: 270px; margin: 0 auto"></div>
												<img alt="" src="" id="canvasimg3"> <span><center>Region
														3</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<div id="column-bar-chart4" class="bar-container"
													style="width: 100%; height: 270px; margin: 0 auto"></div>
												<img alt="" src="" id="canvasimg4"> <span><center>Region
														4</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<div id="column-bar-chart5" class="bar-container"
													style="width: 100%; height: 270px; margin: 0 auto"></div>
												<img alt="" src="" id="canvasimg5"> <span><center>Region
														5</center></span>
											</div>
										</div>
										<div class="col-lg-2 col-md-4 col-sm-6 my-3">
											<div class="border">
												<div id="column-bar-chart6" class="bar-container"
													style="width: 100%; height: 270px; margin: 0 auto"></div>
												<img alt="" src="" id="canvasimg6"> <span><center>Region
														6</center></span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- content -->

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

         <!-- Parsley js -->
        <script type="text/javascript" src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>
        
	<script
		src="<%=UI_PATH%>/assets/js/jquery.slimscroll.js"></script>
		
		<!-- App js -->
	<script
		src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>
      

        <!-- jQuery  -->
        
	<c:forEach var="status" items="${getRegionData}" varStatus='loop'>

		<c:forEach var="status1" items="${status.answerDetails}">
			<input value="${status1.brand_name}" id="regionbrand${loop.index+1}"
				type="hidden">
			<input value="${status1.percentage}" id="regionper${loop.index+1}"
				type="hidden">
		</c:forEach>

	</c:forEach>


	<input value='${dataarray}' id="array" type="hidden">
	


    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="<%=UI_PATH%>/assets/graphs/js/shopping-score-chart.js"></script>
    <script src="<%=UI_PATH%>/assets/graphs/js/reginal-analysis-bar-chart.js"></script>
    <script src="<%=UI_PATH%>/assets/graphs/js/custom-script.js"></script>
        <!-- Graphs Js's end-->

<!-- manoj d ends -->
      <!--   <script type="text/javascript">
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

        </script> -->
        <script>
        function getmonths(bid){
        	$("#month").empty();
        	 $.ajax({
                 url: "<%=dashboardURL%>getmonths",
                 type: "GET", 
                   data: { 'bid': bid },
                 success: function(response)
                             {
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
                 url: "<%=dashboardURL%>getDealersbyid",
                 type: "GET", 
                   data: { 'bid': bid,'region_id':region_id,'dealer_id':dealer_id },
                 success: function(response)
                             {
                	 $("#Dealer").append("<option value=''>All</option>");
					$.each(response,function(k,v){
						$("#Dealer").append("<option value='"+v.dealer_id+"' <c:if test = "${dealer_id == v.dealer_id }">selected </c:if>>"+v.dealer_name+"</option>");
					})
                             }
       	  });
        }
        
        function getoutlets(){
        	$("#Location").empty();
        	var did=$("#Dealer").val();
        	/* alert(did); */
        	//var bid=$("#brand_id").val();
        	var region_id=$("#Region").val();
        	 $.ajax({
                 url: "<%=dashboardURL%>getoutletsforcompitation",
                 type: "GET", 
                   data: { 'did': did},
                 success: function(response)
                             {
                	 $("#Location").append("<option value=''>All</option>");
					$.each(response,function(k,v){
						$("#Location").append("<option value='"+v.outlet_id+"' <c:if test = "${outlet_id == v.outlet_id }">selected </c:if>>"+v.outlet_name+"</option>");
					})
				}
       	  });
        }
         
            </script>
      
</body>
</html>