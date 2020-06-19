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
<title>Mystery-Users</title>
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
										<li class="breadcrumb-item"><a href="#">MYS</a></li>
										<li class="breadcrumb-item"><a href="#">Users</a></li>
									</ol>
								</div>
								<h4 class="page-title">Create User</h4>
							</div>
						</div>
					</div>

					<div class="row">

						<div class="col-lg-12">

							<div class="card-box ">
								<h4 class="header-title m-t-0"></h4>
								<form method="POST" action="<%=dashboardURL%>userCreate" id="user" autocomplete="off">
									<div class="row">
									<div class="form-group col-md-6">
											<label for="pass1">Brand<span class="text-danger">*</span></label>
											<select id="brand_id" name="brand_id" required
												class="form-control">
												<option value="">Select Brand</option>
												<c:forEach var="dbBean" items="${activebrandList}">
                                                <option value="${dbBean.sk_brand_id }">${dbBean.brand_name }</option>
                                                </c:forEach>
												</select>
										</div>
										
										<div class="form-group col-md-5">
											<label for="pass1">User Type<span class="text-danger">*</span></label>
											<select id="user_type_id" name="user_type_id" required
												class="form-control">
												<option value="">Select user type</option>
												<c:forEach var="uBean" items="${userTypeList}">
													<option value="${uBean.user_type_id }">${uBean.user_type }</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group col-md-1" id="icon">
											<label for="Icon"><span class="text-danger"></span></label> <span
												class="logo-large"><i class="mdi mdi-account-plus"
												title="add user type" data-toggle="modal"
												data-target="#login-modal"></i> </span>
										</div>
										<div class="form-group col-md-6">
											<label for="pass1">Role<span class="text-danger">*</span></label>
											<select id="sk_role_id" name="sk_role_id" required
												class="form-control" onchange="RoleBasedEnable(this.value);">
												<option value="">Select role</option>
												<c:forEach var="uBean" items="${roleList}">
													<option value="${uBean.sk_role_id}">${uBean.role_name}</option>
												</c:forEach>
												
											</select>
										</div>
										<div class="form-group col-md-6">
											<label for="pass1">Region<span class="text-danger">*</span></label>
											<select id="region_id" name="region_id" required
												class="form-control" onchange="getDealer(this.value);">
												<option value="">Select Region</option>
												<option value="All">All</option>
												<c:forEach var="uBean" items="${regionList}">
													<option value="${uBean.region_id}">${uBean.region_name}</option>
												</c:forEach>
												</select>
										</div>
										
										<div class="form-group col-md-6">
											<label for="pass1">Dealer<span class="text-danger">*</span></label>
											<select id="dealer_id" name="dealer_id" required
												class="form-control" >
												<option value="">Select Dealer</option>
												</select>
										</div>
									
									
										<div class="form-group col-md-6">
											<label for="Address">First Name<span
												class="text-danger">*</span></label> <input type="text" 
												class="form-control" id="first_name" name="first_name"
												data-parsley-pattern="/^[a-zA-Z ]*$/"
												placeholder="Enter First Name" required data-parsley-maxlength="100">
										</div>
										<div class="form-group col-md-6">
											<label for="Address">Last Name<span
												class="text-danger">*</span></label> <input type="text" 
												class="form-control" id="last_name" name="last_name"
												data-parsley-pattern="/^[a-zA-Z ]*$/"
												placeholder="Enter Last Name" required data-parsley-maxlength="100">
										</div>
										<div class="form-group col-md-6">
											<label for="Address">Email<span class="text-danger">*</span></label>
											<input type="email"  class="form-control" id="email"
												name="email" onkeyup="checkMailExist(this.value)"
												placeholder="Enter Email" required> 
												 <span id="emailExistErrorMsg" style="color:red"></span>
                                       
										</div>
										<div class="form-group col-md-6">
											<label for="Address">Password<span
												class="text-danger">*</span></label> <input type="password" required
												class="form-control" id="password" name="password" 
												placeholder="Enter Password" data-parsley-pattern= "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$" data-parsley-pattern-message="Password Must contain at least one uppercase and one lowercase letter and one special character , and at least 8 or more characters">
										</div>
										<div class="form-group col-md-6">
											<label for="Address">Mobile<span class="text-danger">*</span></label>
											<input type="text" required class="form-control" id="mobile"
												name="mobile"
												data-parsley-length-message="Please Enter Valid Mobile Number"
												data-parsley-length="[10, 10]" data-parsley-type="integer"
												placeholder="Enter Mobile" >
										</div>
										
									</div>

									<div class="form-group text-right m-b-0">
										<button class="btn btn-gradient waves-effect waves-light"
											id="button-submit" type="submit">Submit</button>
										<button type="reset" id="reset"
											class="btn btn-light waves-effect m-l-5">Reset</button>

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

<div id="login-modal" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="custom-width-modalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" style="top: -12px; right: -10px;">×</button>
					<h4 class="text-uppercase text-center m-b-25">User Type</h4>

					<form class="form-horizontal" action="<%=dashboardURL%>user_type" method="POST" autocomplete="off">

						<div class="form-group m-b-25">
							<div class="col-12">
								<label for="emailaddress1">User Type</label> <input
									class="form-control" type="text" id="user_type"
									name="user_type"  onkeyup="checkUserTypeExist(this.value)" required
									placeholder="Enter User Type"> 
									<span id="user_type_error" style="color: red;"></span>
							</div>
						</div>

						<div class="form-group account-btn text-center m-t-10">
							<div class="col-12">
								<button
									class="btn w-lg btn-rounded btn-custom waves-effect waves-light"
									id="button-submit1" type="submit">Submit</button>
							</div>
						</div>

					</form>

				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>



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
		
		$('#form').on('submit', function() {
	         return $('#testForm').jqxValidator('validate');
	     });
		$("form").on('submit',function(){
			if($("#emailExistErrorMsg").text()!=""){
				return false;
			}
			else{
				$("#emailExistErrorMsg").text("");
				return true;
			}
		});
	function checkMailExist(email){
	   
		 $.ajax({
             url: "<%=dashboardURL%>getUserMailExisitance",
             type: "GET", 
               data: { 'email': email},
             success: function(response)
                         {
            	// alert(response);
            	 if(response.message=="emailexist" && response.status=="active"){
            		 $("#emailExistErrorMsg").text("Email Already Exist!");
            		 $("#emailExistErrorMsg").css("display","inline");
            		 $("#emailExistErrorMsg").css("position","absolute");
            	     } 
            	 else if(response.message=="emailexist" && response.status=="inactive")
            	 {
            		 $("#emailExistErrorMsg").text("Email already exists  in Inactive tab ,Please restore ...!");
            		 $("#emailExistErrorMsg").css("display","inline");
            		 $("#emailExistErrorMsg").css("position","absolute");
            	 }
            	 else{
            		 $("#emailExistErrorMsg").text("");
            		 $("#emailExistErrorMsg").css("display","none");
            		 $("#emailExistErrorMsg").css("position","absolute");
            	 }
            	 }
                         
			
		});
		 
		       $("#reset").on("click",function(){
	         	if($("#emailExistErrorMsg").text()!="")
	         		{
	         		$("#emailExistErrorMsg").text("");
	         		}
	         });
		}
	</script>
	
	
	<script>
	function getDealer(region_id) 
    { 
     
       $("#dealer_id option").remove();  
       var brand_id=$("#brand_id").val();
      
        $.ajax({
              url: "<%=dashboardURL%>GetDealerByRegionId",
              type: "GET", 
                data: { 'brand_id': brand_id, 'region_id': region_id },
              success: function(response)
                          {
            	  $("#dealer_id").append("<option value=''>Select Dealer</option>");
            	  $.each(response,function(k,v){
                      $("#dealer_id").append("<option value='"+v.dealer_id+"'>"+v.dealer_name+"</option>");

            	  }); 
                                
                          }
              });  
      
    }
	</script>
	
	<script>
	function checkUserTypeExist(user_type){
		// alert(user_type);
		 $.ajax({
            url: "<%=dashboardURL%>checkUserTypeExist",
            type: "GET", 
              data: { 'user_type': user_type},
            success: function(response)
                        {
           	 if(response.message=="usertypeexist" && response.status=="active"){
           		 $("#user_type_error").text("User Type Already Exist!");
           		 $("#user_type_error").css("display","inline");
           		 $("#user_type_error").css("position","absolute");
           		$("#button-submit1").attr("disabled", true);
           	     } 
           	 else{
           		 $("#user_type_error").text("");
           		 $("#user_type_error").css("display","none");
           		 $("#user_type_error").css("position","absolute");
           		$("#button-submit1").attr("disabled", false);
           	 }
           	 }
                        
		});       
		}
	</script>
<script>
function RoleBasedEnable(user_type){
	var roleId=$("#sk_role_id").val();
	//alert(roleId);
	if((roleId == 2)||(roleId == 4) || (roleId == 5))
	{
		$("#region_id").attr("disabled", true);
		$("#region_id").prop("required", false);
		$("#dealer_id").attr("disabled", true);
		$("#dealer_id").prop("required", false);
	}
	else if((roleId == 6))
		{
		$("#region_id").attr("disabled", false);
		$("#dealer_id").attr("disabled", true);
		$("#dealer_id").prop("required", false);
		}
	else
	{
		$("#region_id option[value='All']").remove();
		$("#dealer_id").attr("disabled", false);
		
	}
	
}

</script>
	
	

</body>
</html>