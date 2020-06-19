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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta
	content="A fully featured admin theme which can be used to build CRM, CMS, etc."
	name="description" />
<meta content="Coderthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<!-- App favicon -->
<%-- <link rel="shortcut icon"
	href="<%=UI_PATH %>/assets/images/lugma_favicon.png"> --%>

 <link rel="shortcut icon"
	href="<%=request.getContextPath()%>/resources/design/images/DQI-icon.png">
	
<!--Morris Chart CSS -->
<link rel="stylesheet"
	href="<%=UI_PATH%>/plugins/morris/morris.css">

<link
	href="<%=UI_PATH %>/assets/css/jquery.growl.css"
	rel="stylesheet" type="text/css" />
<!-- App css -->
<link
	href="<%=UI_PATH%>/assets/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
	
<link href="<%=UI_PATH%>/assets/css/icons.css"
	rel="stylesheet" type="text/css" />
	
<link href="<%=UI_PATH%>/assets/css/style.css"
	rel="stylesheet" type="text/css" />

<script
	src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>

</head>



<body class="bg-accpunt-pages">

	<!-- HOME -->
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-12">

					<div class="wrapper-page">

						<div class="account-pages">
							<div class="account-box">
								<div class="account-logo-box">
									<div style="float: left">
										<img
											src="<%=UI_PATH %>/images/DQI_icon.png"
											alt="" height="50">
									</div>
									<a href="index.html" class="text-success"> <span><img
											src="<%=UI_PATH %>/assets/images/DQI_icon.png"
											alt="" height="18"></span>
									</a>

								</div>

								<div class="account-content">
									<h6 class="text-uppercase text-center font-bold mt-4">Forgot Password
										</h6>
									<form class="form-horizontal" action="<%=dashboardURL%>forgotPassword" method="Post"
										autocomplete="off">


										<div class="form-group m-b-20 row">
											<div class="col-12">
												<%-- <label for="emailaddress" style="color: red" class="message">${errMsg}</label> --%>
											</div>
											<div class="col-12">
												<label for="emailaddress">Email Address</label> <input
													class="form-control" type="email" name="email" id="email"  required
													placeholder="Enter Your Email">
											</div>
										</div>

										
										<div class="form-group row text-center m-t-10">
											<div class="col-12">
												<button
													class="btn btn-block btn-gradient waves-effect waves-light"
													type="submit">Submit</button>
											</div>
										</div>
										<a href="<%=dashboardURL%>signin" class="text-muted pull-right"><small>Sign In
														</small></a>

									</form>

									<div class="row m-t-50">
										<div class="col-sm-12 text-center">
											<!--  <p class="text-muted">Don't have an account? <a href="page-register.html" class="text-dark m-l-5"><b>Sign Up</b></a></p> -->
										</div>
									</div>

								</div>
							</div>
						</div>
						<!-- end card-box-->


					</div>
					<!-- end wrapper -->

				</div>
			</div>
		</div>
	</section>
	<!-- END HOME -->



	<!-- jQuery  -->
	<script
		src="<%=UI_PATH %>/assets/js/jquery.min.js"></script>
	<script
		src="<%=UI_PATH %>/assets/js/popper.min.js"></script>
	<script
		src="<%=UI_PATH %>/assets/js/bootstrap.min.js"></script>
	<script src="<%=UI_PATH %>/assets/js/waves.js"></script>
	<script
		src="<%=UI_PATH %>/assets/js/jquery.slimscroll.js"></script>

	<!--Morris Chart-->
	<script
		src="<%=UI_PATH %>/plugins/morris/morris.min.js"></script>
	<script
		src="<%=UI_PATH %>/plugins/raphael/raphael-min.js"></script>
	<script
		src="<%=UI_PATH %>/assets/pages/jquery.morris.init.js"></script>


      <script
		src="<%=UI_PATH %>/assets/js/jquery.growl.js"></script>
	<!-- Chart JS -->
	<script
		src="<%=UI_PATH %>/plugins/chart.js/chart.bundle.js"></script>
	<script
		src="<%=UI_PATH %>/assets/pages/jquery.chartjs.init.js"></script>

	<!-- App js -->
	<script
		src="<%=UI_PATH %>/assets/js/jquery.core.js"></script>
	<script
		src="<%=UI_PATH %>/assets/js/jquery.app.js"></script>
	<!-- Parsley js -->
	<script type="text/javascript"
		src="<%=UI_PATH %>/plugins/parsleyjs/parsley.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('form').parsley();
		});
	</script>
	
		<input type="hidden" id="validationstatus" value="${statusMessage}">
	<input type="hidden" id="statusCode" value="${statusCode}">
	<script>
		var validation_msg = $("#validationstatus").val();
		var statusCode = $("#statusCode").val();
		if (statusCode == "" || statusCode == null) {

		} 
		else if (statusCode == "0") {
			$.growl.error({
				message : "${statusMessage}"
			});

		}
		else {
			$.growl.notice({
				message : "${statusMessage}"
			});
		}
	</script> 
	
	

</body>