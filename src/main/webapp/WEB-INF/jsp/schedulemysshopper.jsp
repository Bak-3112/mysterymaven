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
<title>MYS-Shedule Visit</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta
	content="A fully featured admin theme which can be used to build CRM, CMS, etc."
	name="description" />
<meta content="Coderthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<!-- App favicon -->
<link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">

<link href="<%=UI_PATH%>/plugins/select2/css/select2.min.css"
	rel="stylesheet" type="text/css" />
	
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
<!-- DataTables -->
<link
	href="<%=UI_PATH%>/plugins/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=UI_PATH%>/plugins/datatables/buttons.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<!-- Responsive datatable examples -->
<link
	href="<%=UI_PATH%>/plugins/datatables/responsive.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />

<!-- Custom box css -->
<link href="<%=UI_PATH%>/plugins/custombox/css/custombox.min.css"
	rel="stylesheet">

<!-- App css -->
<link href="<%=UI_PATH%>/assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=UI_PATH%>/assets/css/icons.css" rel="stylesheet"
	type="text/css" />
<link href="<%=UI_PATH%>/assets/css/metismenu.min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=UI_PATH%>/assets/css/style.css" rel="stylesheet"
	type="text/css" />
<%-- 	 <link href="<%=UI_PATH%>/assets/css/nice-select.css" rel="stylesheet" type="text/css" />
 --%>	
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.min.css" />
	
<script src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>
<style>
#userType {
	position: relative;
	left: 20px;
}

#icon {
	position: relative;
	top: 31px;
	font-size: 20px;
}
</style>

</head>

<body>
	<div id="wrapper">
		<!-- Navigation Bar-->
		<jsp:include page="include/header.jsp"></jsp:include>
		<jsp:include page="include/sidemenu.jsp"></jsp:include>
		<!-- End Navigation Bar-->

		<div class="content-page">
			<!-- Start content -->
			<div class="content">
				<div class="container-fluid">

					<div class="row">
						<div class="col-sm-12">
							<div class="page-title-box">
								<div class="btn-group pull-right">
									<ol class="breadcrumb hide-phone p-0 m-0">
										<li class="breadcrumb-item"><a href="#">Mystery Shopping</a></li>
										<li class="breadcrumb-item"><a href="#">Schedule Mystery Shopping Visit</a></li>
									</ol>
								</div>
								<h4 class="page-title">Schedule Mystery Shopping Visit</h4>
							</div>
						</div>
					</div>

					<div class="row">

						<div class="col-lg-12">

							<div class="card-box ">
								<h4 class="header-title m-t-0"></h4>
								<form method="POST" action="<%=dashboardURL%>scheduleMysteryShoppePost" autocomplete="off">
									<div class="row">
										<div class="form-group col-md-3">
											<label for="Address" style="font-weight:600; color:#797979;">Name<span
												class="text-danger">*</span></label> <input type="text" 
												class="form-control" id="name" name="name"
												data-parsley-pattern="/^[a-zA-Z ]*$/"
												placeholder="Enter Full  Name" required>
										</div>
										<div class="form-group col-md-3">
											<label for="Address" style="font-weight:600; color:#797979;">Car Owned Brand<span
												class="text-danger">*</span></label> <input type="text" 
												class="form-control" id="shoppers_car_owned_brand" name="shoppers_car_owned_brand"
												
												placeholder="Enter Car Owned Brand" required>
										</div>
										
										<div class="form-group col-md-3">
											<label for="Address" style="font-weight:600; color:#797979;">Car Owned Model<span
												class="text-danger">*</span></label> <input type="text" 
												class="form-control" id="shoppers_car_owned" name="shoppers_car_owned"
												
												placeholder="Enter Car Owned Model" required>
										</div>
										
										<div class="form-group col-md-3">
											<label for="Address" style="font-weight:600; color:#797979;">Year of Purchase<span
												class="text-danger">*</span></label> <input type="text" 
												class="form-control" id="shoppers_car_yop" name="shoppers_car_yop"
												placeholder="Enter Year  Of Purchase" required data-parsley-length="[4, 4]" data-parsley-type="integer">
										</div>
										
										<div class="form-group col-md-3">
											<label for="Address" style="font-weight:600; color:#797979;">Email Given to Dealership<span class="text-danger">*</span></label>
											<input type="email"  class="form-control" id="email"
												name="email" 
												placeholder="Enter Email" required>                                        
										</div>
										<div class="form-group col-md-3">
											<label for="Address" style="font-weight:600; color:#797979;">Contact Number<span class="text-danger">*</span></label>
											<input type="text" required class="form-control" id="contact_number"
												name="contact_number"
												data-parsley-length-message="Please Enter Valid Mobile Number"
												data-parsley-length="[10, 10]" data-parsley-type="integer"
												placeholder="Enter Mobile" >
										</div>
										<div class="form-group col-md-3">
											<label for="Address" style="font-weight:600; color:#797979;">Age<span class="text-danger">*</span></label>
											<input type="text" required class="form-control" id="age"
												name="age"
												placeholder="Enter Age"  required data-parsley-length="[2, 2]" data-parsley-type="integer">
										</div>
										
										
										<div class="form-group col-md-3">
											<label for="pass1" style="font-weight:600; color:#797979;">Gender*<span class="text-danger">*</span></label>
											<select id="gender" name="gender" required
												class="form-control">
												<option value="">Select Gender</option>
												<option value="Male">Male</option>
                                  	            <option value="Female">Female</option>
											</select>
										</div>
										
										<div class="form-group col-md-3">
											<label for="pass1" style="font-weight:600; color:#797979;">Quarter<span class="text-danger">*</span></label>
											<select id="quarter" name="quarter" required
												class="form-control">
												<option value="">Select Quarter</option>
												<option value="Q1">Q1</option>
                                                <option value="Q2">Q2</option>
                                                <option value="Q3">Q3</option>
                                                <option value="Q4">Q4</option>
											</select>
										</div>
										
										<div class="form-group col-md-3">
											<label for="pass1" style="font-weight:600; color:#797979;">Year<span class="text-danger">*</span></label>
											<select id="year" name="year" required
												class="form-control">
												<option value="">Select Year</option>
												<option value="2018">2018</option>
                                                <option value="2019">2019</option>
                                                <option value="2020">2020</option>
                                                <option value="2021">2021</option>
											</select>
										</div>
										
										<div class="form-group col-md-3">
											<label for="pass1" style="font-weight:600; color:#797979;">Mode of Contact*<span class="text-danger">*</span></label>
											<select id="mode_of_contact" name="mode_of_contact" required
												class="form-control">
												<option value="">Select Mode of Contact</option>
												<option value="EMail/Website">EMail/Website</option>
                                  	            <option value="Telephone">Telephone</option>
                                  	            <option value="Walk In">Walk In</option>
                                  	            <option value="Online Sales Channel">Online Sales Channel</option>
											</select>
										</div>
										
										 <div class="form-group col-md-3">
                                    <label for="Address" style="font-weight:600; color:#797979;">Visit Date<span class="text-danger">*</span></label>
                                    <input type="text" id="visit_date" name="visit_date" placeholder="Select Date" required  value="${uBean.audit_date }" class="datepicker form-control">
                                </div>
										
										<div class="form-group col-md-3">
                                    <label for="pass1" style="font-weight:600; color:#797979;">Brand<span class="text-danger">*</span></label>
                                   <select id="brand_id" name="brand_id" required class="form-control " onchange="getOutlets(this.value);getModelVariant(this.value)"  data-placeholder="Choose Brands..">
									<option value="" >Select Brand</option>
                                    <c:forEach var="dbBean" items="${activebrandList}">
                                     <option value="${dbBean.sk_brand_id }">${dbBean.brand_name }</option>
                                     </c:forEach>
                                     </select>  
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1" style="font-weight:600; color:#797979;">Outlet<span class="text-danger">*</span></label>
                                    <select id="outlet" name="outlet_id" class="form-control" data-parsley-errors-container="#chke"  required>
                                     <option value="">Select Outlet</option>
                                </select>
                                <div id="chke"></div>
                              
                                </div>
                                  
                                <div class="form-group col-md-3">
                                    <label for="pass1" style="font-weight:600; color:#797979;">Model-Variant Shopped<span class="text-danger">*</span></label>
                                   
                                    <select id="variant_id" name="variant_id" required class="form-control" data-parsley-errors-container="#chke1" >
									 <option value="" >Select Model-Variant</option>
                                     </select>   
                                    <div id="chke1"></div>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1" style="font-weight:600; color:#797979;">Type of Mystery shopper<span class="text-danger">*</span></label>
                                    <select id="type" name="type" required class="form-control" >
                                    <option value="">Select type</option>
                                  	<option value="EY Employee">EY Employee</option>
                                  	<option value="Friends and family">Friends and family</option>
                                    </select>
                                </div>
                                
									</div>

									<div class="form-group text-right m-b-0">
										<button class="btn btn-gradient waves-effect waves-light"
											id="button-submit" type="submit">Submit</button>
										<button type="reset" id="reset"
											class="btn btn-light waves-effect m-l-5" id="reset">Reset</button>

									</div>

								</form>
							</div>
							<!-- end card-box -->
						</div>
					</div>
					<!-- end row -->
				</div>
			</div>
		</div>
		<!-- end container -->
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
	<script
		src="<%=UI_PATH%>/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js"></script>
	<script src="<%=UI_PATH%>/plugins/select2/js/select2.min.js"
		type="text/javascript"></script>
	<script
		src="<%=UI_PATH%>/plugins/bootstrap-select/js/bootstrap-select.js"
		type="text/javascript"></script>
	<!--  Required datatable js -->
	<script src="<%=UI_PATH%>/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/datatables/dataTables.bootstrap4.min.js"></script>
	<!-- Buttons examples -->
	<script src="<%=UI_PATH%>/plugins/datatables/dataTables.buttons.min.js"></script>
	<script src="<%=UI_PATH%>/plugins/datatables/buttons.bootstrap4.min.js"></script>
	<script src="<%=UI_PATH%>/plugins/datatables/jszip.min.js"></script>
	<script src="<%=UI_PATH%>/plugins/datatables/pdfmake.min.js"></script>
	<script src="<%=UI_PATH%>/plugins/datatables/vfs_fonts.js"></script>
	<script src="<%=UI_PATH%>/plugins/datatables/buttons.html5.min.js"></script>
	<script src="<%=UI_PATH%>/plugins/datatables/buttons.print.min.js"></script>
	<!-- Responsive examples -->
	<script
		src="<%=UI_PATH%>/plugins/datatables/dataTables.responsive.min.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/datatables/responsive.bootstrap4.min.js"></script>

     <!-- Parsley js -->
	<script type="text/javascript"
		src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>

	<!--  App js -->
	<script src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
	<script src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>
	<%-- <script src="<%=UI_PATH%>/js/script.js"></script> --%>

	<!-- Init Js file -->
	<script type="text/javascript"
		src="<%=UI_PATH%>/assets/pages/jquery.form-advanced.init.js"></script>
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.js"></script>
	 
	 <script type="text/javascript">
		$(document).ready(function() {
			$('form').parsley();
			
		});
		$('#reset').click(function () {
		    $('form').parsley().reset();
		});
	
	</script>
	
	
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
      <script>
  $( function() {
    $( ".datepicker" ).datepicker({
    	dateFormat: "yy-mm-dd",
    	onSelect:function(){
    		$(this).parsley().validate()
    	}
    });
  } );
  </script>
  <script>
        function getOutlets(brand_id) 
        { 
         
           $("#outlet option").remove();  
          $.ajax({
                  url: "<%=dashboardURL%>getOutletsByBrandId",

                  type: "GET", 
                    data: { 'brand_id': brand_id },
                    
                  success: function(response)
                  
                              {
                	 
                	  $("#outlet").append("<option value=''>Select Outlet</option>");
                	  $.each(response,function(k,v){
                          $("#outlet").append("<option value='"+v.outlet_id+"'>"+v.dealer_name+" "+v.outlet_location_id+"  "+v.outlet_name+"</option>");

                	  });
                                    
                              }
                  });  
          
        }
  
        function getModelVariant(brand_id) 
        { 
         
           $("#variant_id option").remove();  
         // var brand_id=$("#brand_id").val();
          
            $.ajax({
                  url: "<%=dashboardURL%>getModelVariantById",
                  type: "GET", 
                    data: { 'brand_id': brand_id },
                  success: function(response)
                              {
                	  $("#variant_id").append("<option value=''>Select Model-Variant</option>");
                	  $.each(response,function(k,v){
                          $("#variant_id").append("<option value='"+v.variant_id+"'>"+v.model_name+" "+v.variant_name+"</option>");

                	  });
                                    
                              }
                  });  
          
        }
        </script>

</body>
</html>