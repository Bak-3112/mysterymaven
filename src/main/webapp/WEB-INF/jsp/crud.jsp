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
<link rel="shortcut icon" href="<%=UI_PATH%>/design/images/DQI-icon.png">

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

<script src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>
<link rel="stylesheet" href="<%=UI_PATH%>/css/nice-select.css"
	type="text/css" />



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
				<div class="container-fluid">

					<div class="row">
						<div class="col-sm-12">
							<div class="page-title-box">
								<h4 class="page-title">Create Crud test</h4>
							</div>
						</div>
					</div>
					<div class="row">
						<jsp:include page="include/tab.jsp"></jsp:include>

						<div class="col-lg-6">

							<div class="card-box ">
								<h4 class="header-title m-t-0"></h4>
								<form method="POST" action="<%=dashboardURL%>createcrud"
									enctype="multipart/form-data" autocomplete="off">

									<div class="form-group col-md-12">
										<label for="name">Name<span class="text-danger">*</span></label>
										<input type="text" required class="form-control" id="name"
											name="name" placeholder="Enter Name"> <span
											id="nameerrormsg" style="color: red"></span> <input
											type="text" id="testbox" style="display: none">
									</div>

									<div class="form-group col-md-12">
										<label for="email">Email<span class="text-danger">*</span></label>
										<input type="email" required class="form-control" id="email"
											name="email" placeholder="Enter Email">
									</div>
									<div class="form-group col-md-12">
										<label for="name">Country<span class="text-danger">*</span></label>
										<select id="sk_country_id" name="sk_country_id" required
											class="form-control" onchange="getState()">
											<option value="">Select Country</option>
											<c:forEach var="coBean" items="${countryList}">
												<option value="${coBean.sk_country_id}">${coBean.country_name}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group col-md-12">
										<label for="state_name">State Name<span
											class="text-danger">*</span></label> <select id="sk_state_id"
											name="sk_state_id" required class="form-control"
											onchange="getCity()">
											<option value="">Select State</option>
										</select>
									</div>
									<div class="form-group col-md-12">
										<label for="city_name">City Name<span
											class="text-danger">*</span></label> <select id="sk_city_id"
											name="sk_city_id" required class="form-control">
											<option value="">Select city</option>
										</select>
									</div>
									<div class="form-group col-md-12">
										<label for="address">Address<span class="text-danger">*</span></label>
										<input type="text" required class="form-control" id="address"
											name="address" placeholder="Enter Address">
									</div>
									<div class="form-group text-right m-b-0">
										<button
											class="btn btn-gradient waves-effect waves-light btnsubmit"
											type="submit">Submit</button>
										<button type="reset" class="btn btn-light waves-effect m-l-5"
											id="reset">Reset</button>
									</div>

								</form>
							</div>
							<!-- end card-box -->
						</div>
						<div class="col-md-6">
							<div class="card-box table-responsive">
								<h4 class="m-t-0 header-title">
									<b> </b>
								</h4>
								
								<div class="tab-content">
									<div class="tab-pane fade show active">
										<table id="datatable-buttons"
											class="table table-striped table-bordered" cellspacing="0"
											width="100%">
											<thead>
												<tr>
													<th>Name</th>
													<th>Email</th>
													<th>Address</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="tBean" items="${tbeanList}">
													<tr>
														<td>${tBean.name }</td>
														<td>${tBean.email }</td>
														<td>${tBean.address }</td>
													<td><a href="<%=dashboardURL%>editCrud/${tBean.id}" class="fa fa-edit"></a>&nbsp;&nbsp;&nbsp;<a href="#"  class="fa fa-trash" data-id="${tBean.id}" data-toggle="modal" data-target="#myModal"></a></td>	
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<!-- end container -->
							</div>
						</div>
					</div>
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

	<!--  App js -->
	<script src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
	<script src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>
	<script src="<%=UI_PATH%>/js/script.js"></script>

	<!-- Init Js file -->
	<script type="text/javascript"
		src="<%=UI_PATH%>/assets/pages/jquery.form-advanced.init.js"></script>
	<!-- Parsley js -->
	<script type="text/javascript"
		src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>


	<script>
		 function  getState(){
			 $("#sk_state_id").empty(); 
	    	 var aid=$("#sk_country_id").val();
	    	 /* alert(aid); */
	    	  $.ajax({
	    	        url: "<%=dashboardURL%>getStateName",
	    	        type: "GET", 
	                data: {'aid' : aid},
	    	        success: function(response)
	    	                    {
	    	        			 var sList=JSON.stringify(response);
	    	        			 var jsonString=JSON.parse(sList);
	        	        	   		for(var i=0;i<jsonString.length;i++)
	        	        	 	    {
	        	  	        	 	 	   $("#sk_state_id").append("<option value='"+jsonString[i].sk_state_id+"'>"+jsonString[i].state_name+"</option>"); 
			
	 	       	        	 	    }                        
	    	                    }
	    	        });
	       }
		 </script>
	<script>
		 function  getCity(){	
			 $("#sk_city_id").empty(); 
	    	 var cid=$("#sk_state_id").val();
	    	 /* alert(aid); */
	    	  $.ajax({
	    	        url: "<%=dashboardURL%>getCityName",
	    	        type: "GET", 
	                data: {'cid' : cid},
	    	        success: function(response)
	    	                    {
	    	        			 var cList=JSON.stringify(response);
	    	        			 var jsonString=JSON.parse(cList);
	        	        	   		for(var i=0;i<jsonString.length;i++)
	        	        	 	    {
	        	  	        	 	 	   $("#sk_city_id").append("<option value='"+jsonString[i].city_id+"'>"+jsonString[i].city_name+"</option>"); 
			
	 	       	        	 	    }                        
	    	                    }
	    	        });
	       }
	</script>
	<script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
            $(".fa-trash").on("click",function(){
            	var value=$(this).attr("data-id");
            	var url="<%=dashboardURL%>deletecrud/"+value;
            	alert(value);
            	$(".btn-edit").attr("href",url);
            })
          </script>
</body>
</html>