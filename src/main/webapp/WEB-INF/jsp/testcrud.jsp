<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ page import="java.util.ResourceBundle"%>

<%
ResourceBundle resource = ResourceBundle.getBundle("resources/web");
String UI_PATH = resource.getString("UiPath");
String title = resource.getString("dashboardTitle");
String dashboardURL = resource.getString("dashboardURL");
%>
<!DOCTYPE html>
<html>
<head>
<%
String user_id = (String) request.getSession().getAttribute("user_id");
if (user_id == null) {
	response.sendRedirect(dashboardURL);
}
%>
<meta charset="utf-8" />
<title>DQI-Crud</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta
	content="A fully featured admin theme which can be used to build CRM, CMS, etc."
	name="description" />
<meta content="Coderthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<!-- App favicon -->
<link rel="shortcut icon"
	href="<%=UI_PATH%>assets/images/lugma_favicon.png">

<link href="<%=UI_PATH%>plugins/select2/css/select2.min.css"
	rel="stylesheet" type="text/css" />
<!-- DataTables -->
<link
	href="<%=UI_PATH%>plugins/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=UI_PATH%>plugins/datatables/buttons.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<!-- Responsive datatable examples -->
<link
	href="<%=UI_PATH%>plugins/datatables/responsive.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />

<!-- Custom box css -->
<link href="<%=UI_PATH%>plugins/custombox/css/custombox.min.css"
	rel="stylesheet">

<!-- App css -->
<link href="<%=UI_PATH%>assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=UI_PATH%>assets/css/icons.css" rel="stylesheet"
	type="text/css" />
<link href="<%=UI_PATH%>assets/css/metismenu.min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=UI_PATH%>assets/css/style.css" rel="stylesheet"
	type="text/css" />

<script src="<%=UI_PATH%>assets/js/modernizr.min.js"></script>
<link rel="stylesheet" href="<%=UI_PATH%>css/nice-select.css"
	type="text/css" />



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
										<li class="breadcrumb-item"><a href="#">DQI</a></li>
										<li class="breadcrumb-item"><a href="#">Crud</a></li>
									</ol>
								</div>
								<h4 class="page-title">crud</h4>
							</div>
						</div>
					</div>

					<div class="row">

						<div class="col-lg-4">

							<div class="card-box ">
								<h4 class="header-title m-t-0"></h4>
								<form method="POST" action="<%=dashboardURL%>testcrud"
									enctype="multipart/form-data" autocomplete="off">
									<div class="form-group col-md-12">
										<label for="Address">Name:<span
											class="text-danger">*</span></label> <input type="text" required
											class="form-control" id="name" name="name"
											placeholder="Enter Name">
									</div>
									<div class="form-group col-md-12">
										<label for="phone">Number:<span
											class="text-danger">*</span></label> <input type="tel" required
											class="form-control" id="phone" name="phone" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}"
											placeholder="Enter Phone Number">
									</div>
									<div class="form-group col-md-12">
										<label for="email">Email:<span
											class="text-danger">*</span></label> <input type="email" required
											class="form-control" id="email" name="email"
											placeholder="Enter Email Address">
									</div>
									<%-- <div class="form-group col-md-12">
											<label for="pass1">Country<span class="text-danger">*</span></label>
											<select id="region_id" name="region_id" required
												class="form-control" onchange="getState()">
												<option value="">Select Country</option>
												<option value="All">All</option>
												<c:forEach var="uBean" items="${countryList}">
													<option value="${uBean.country_id}">${uBean.country_name}</option>
												</c:forEach>
												</select>
										</div>
										<div class="form-group col-md-12">
                                    <label for="state_name">State Name<span class="text-danger">*</span></label>
									<select id="state_id" name="sk_state_id" required class="form-control"  onchange="getCity()">
									<option value="" >Select State</option>
                                    <c:forEach var="uBean" items="${stateList}">
                                     <option value="${uBean.sk_state_id }">${uBean.state_name }</option>
                                     </c:forEach>
                                     </select>                           
                                 </div>
 --%>                                 <div class="form-group col-md-12">
										<label for="city">City:<span
											class="text-danger">*</span></label> <input type="text" required
											class="form-control" id="city" name="city"
											placeholder="Enter City Name">
									</div>
									 <div class="form-group col-md-12">
										<label for="city">Address:<span
											class="text-danger">*</span></label> <input type="text" required
											class="form-control" id="address" name="address"
											placeholder="Enter Address">
									</div>
									<div class="form-group text-right m-b-0">
										<button class="btn btn-gradient waves-effect waves-light"
											type="submit">Submit</button>
										<button type="reset" class="btn btn-light waves-effect m-l-5">
											Reset</button>

									</div>

								</form>
							</div>
							<!-- end card-box -->
						</div>
					</div>
					<!-- end row -->

				</div>
				<!-- end container -->
			</div>
		</div>
	</div>
	<!-- Footer -->
	<jsp:include page="include/footer.jsp"></jsp:include>
	<!-- End Footer -->


	<!--  jQuery  -->
	<script src="<%=UI_PATH%>assets/js/jquery.min.js"></script>
	<script src="<%=UI_PATH%>assets/js/popper.min.js"></script>
	<script src="<%=UI_PATH%>assets/js/bootstrap.min.js"></script>
	<script src="<%=UI_PATH%>assets/js/metisMenu.min.js"></script>
	<script src="<%=UI_PATH%>assets/js/waves.js"></script>
	<script src="<%=UI_PATH%>assets/js/jquery.slimscroll.js"></script>
	<!-- Modal-Effect -->
	<script src="<%=UI_PATH%>plugins/custombox/js/custombox.min.js"></script>
	<script src="<%=UI_PATH%>plugins/custombox/js/legacy.min.js"></script>

	<!-- plugin Js -->
	<script src="<%=UI_PATH%>plugins/switchery/switchery.min.js"></script>
	<script
		src="<%=UI_PATH%>plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js"></script>
	<script src="<%=UI_PATH%>plugins/select2/js/select2.min.js"
		type="text/javascript"></script>
	<script
		src="<%=UI_PATH%>plugins/bootstrap-select/js/bootstrap-select.js"
		type="text/javascript"></script>
	<!--  Required datatable js -->
	<script src="<%=UI_PATH%>plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="<%=UI_PATH%>plugins/datatables/dataTables.bootstrap4.min.js"></script>
	<!-- Buttons examples -->
	<script src="<%=UI_PATH%>plugins/datatables/dataTables.buttons.min.js"></script>
	<script src="<%=UI_PATH%>plugins/datatables/buttons.bootstrap4.min.js"></script>
	<script src="<%=UI_PATH%>plugins/datatables/jszip.min.js"></script>
	<script src="<%=UI_PATH%>plugins/datatables/pdfmake.min.js"></script>
	<script src="<%=UI_PATH%>plugins/datatables/vfs_fonts.js"></script>
	<script src="<%=UI_PATH%>plugins/datatables/buttons.html5.min.js"></script>
	<script src="<%=UI_PATH%>plugins/datatables/buttons.print.min.js"></script>
	<!-- Responsive examples -->
	<script
		src="<%=UI_PATH%>plugins/datatables/dataTables.responsive.min.js"></script>
	<script
		src="<%=UI_PATH%>plugins/datatables/responsive.bootstrap4.min.js"></script>

	<!--  App js -->
	<script src="<%=UI_PATH%>assets/js/jquery.core.js"></script>
	<script src="<%=UI_PATH%>assets/js/jquery.app.js"></script>
	<script src="<%=UI_PATH%>js/script.js"></script>

	<!-- Init Js file -->
	<script type="text/javascript"
		src="<%=UI_PATH%>assets/pages/jquery.form-advanced.init.js"></script>
	<!-- Parsley js -->
	<script type="text/javascript"
		src="<%=UI_PATH%>plugins/parsleyjs/parsley.min.js"></script>

</body>
</html>