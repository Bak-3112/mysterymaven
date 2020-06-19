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
<title>Discount Analysis</title>
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
 <style>
        
        th, td{text-align: center;font-size: 14px;border: 1px solid #dee2e6;}
        /* table */
        .hiddenRow {
    padding: 0 !important; background: #cccc;
}
.bg-top{    background: #9e8b8bbb;}
.textCenter{margin-left: 110px ;}
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
					<!-- <button class="btn btn-primary" id="export">Download pdf</button> -->
					<form action="DiscountAnalysis" method="GET">
						<div class="position-relative">

							<div class="pt-3 pb-3  bg-white rounded desc-header"
								style="padding-left: 0px !important; margin-left: 18px;">
								<div class="d-flex flex-wrap ">
									<div class="col">
										<h2 class="d-inline-block pt-2">
											<b>Discount Analysis</b>
										</h2>
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
										<label>Month</label> <select required class="form-control" name="month"
											id="month">
											<option value="">Select Month</option>
											<option value="" <c:if test = "${month == '' }">selected </c:if>>All</option>
										 <c:forEach var="gBean" items="${getMonths}">
												<option value="${gBean.month}" <c:if test = "${month == gBean.month }">selected </c:if>>${gBean.month_name}</option>
											</c:forEach> 

										</select>
									</div>
									<div class="col-sm-6 col-md py-3">
										<label>Region</label> <select name="region_id"
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
											required class="form-control" id="Dealer"onChange="getoutlets()" >
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

					<div class="container-fluid mt-3 mb-3">
						<div class="row">
							<div class="col-xl-4 mt-3">
								<label class="mt-4 textCenter"><strong>Discount
										Offered</strong></label>
							</div>
							<div class="col-xl-8  mt-3"></div>
						</div>
						<div class="row">

							<div class="col-xl-4 mt-3">

								<div class="card">

									<input type="hidden" value="${amount}" id="discountamount"> <input
										type="hidden" value="${yess}" id="discountyes"> <input
										type="hidden" value="${nooo}" id="discountno">
									<div id="customerchartdiscount"
										style="width: 100%; height: 260px; margin: 0 auto"></div>

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
								<input type="hidden" value="${month}" id="monthtemp"> <input
									type="hidden" value="${dealership_id}" id="dealertemp"> <input
									type="hidden" value="${regionId}" id="regiontemp"> <input
									type="hidden" value="${outlet_id}" id="outlettemp">
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
		
<!-- Graph js -->
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script
		src="<%=UI_PATH%>/assets/graphs/js/customer-treat2.js"></script>
	<script
		src="<%=UI_PATH%>/assets/graphs/js/custom-script.js"></script>
	<!-- Graph js -->
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
                	 $("#month").prepend("<option value=''>Select Month</option><option value=''>All</option>");
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
	<script type="text/javascript">
		$(document).ready(
				function() {
					$('#datatable').DataTable();

					//Buttons examples
					var table = $('#datatable-buttons').DataTable({
						lengthChange : false,
						buttons : [ 'copy', 'excel', 'pdf' ]
					});

					table.buttons().container().appendTo(
							'#datatable-buttons_wrapper .col-md-6:eq(0)');
				});
	</script>

</body>
</html>