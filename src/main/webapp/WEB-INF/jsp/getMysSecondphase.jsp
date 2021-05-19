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
<title>MYS-MYS SECOND PHASE</title>
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
<link
	href="<%=UI_PATH%>/assets/css/jquery.growl.css"
	rel="stylesheet" type="text/css" />
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
										<li class="breadcrumb-item"><a href="#">Mystery</a></li>
										<li class="breadcrumb-item"><a href="#">Mystery
												Shopper Submission</a></li>
									</ol>
								</div>
								<h4 class="page-title">Quality Assurance</h4>
							</div>
							<div class="row">
							<div class="col-sm-6">
							<a class="btn btn-light waves-effect m-l-5 Pull-left" href="<%=dashboardURL%>viewMysCompleted" style="margin: -21px -15px 10px 0;">Back</a>
														</div>
																	</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12">
							<div class="card-box">
								<div class="d-none d-lg-flex flex-wrap justify-content-between">
									<div class="col-12">
										<table class="table dataclass">
											<tr>
												<th>
													Quarter
												</th>
												<td>
												${quarter}_${year}
												</td>
												<th>
													Postal Code
												</th>
												<td>
													${pincode}
												</td>
<th>
													Visit Date
												</th>
												<td>
													${visit_date}
												</td>
                                                  <th>
													End Time
												</th>
												<td>
													${end_time}
												</td>
											</tr>	
											<tr>
												<th>
													Dealer Name
												</th>
												<td>
													${dealer_name}
												</td>
												<th>
													Country
												</th>
												<td>
													India
												</td>
	                                              <th>
													Visit Day
												</th>
												<td>
													${visit_day}
												</td>
	                                            <th>
													Met SC
												</th>
												<td>
													${met_sc}
												</td>
											</tr>	
											<tr>
												<th>
													Dealer Location
												</th>
												<td>
													${outlet_name}
												</td>
												<th>
													Brand
												</th>
												<td>
													${brand_name}
												</td>

												<th>
													Calender Week
												</th>
												<td>
													${calenderweek}
												</td>

												<th>
													SC Name
												</th>
												<td>
													${sc_name}
												</td>
											</tr>	
											<tr>
												<th>
													City
												</th>
												<td>
													${city_name}
												</td>
												
												<th>
													Model Shopped
												</th>
												<td>
													${model_name} ${variant_name}
												</td>

												<th>
													Mode of contact
												</th>
												<td>
													${mode_of_contact}
												</td>

												<th>
													SC Designation
												</th>
												<td>
													${sc_designation}
												</td>
											</tr>	
											<tr>
												<th>
													Location ID
												</th>
												<td>
													${outlet_location_id}
												</td>
												<th>
													Gender of MYS
												</th>
												<td>
													${gender}
												</td>
<th>
													Start Time
												</th>
												<td>
													${start_time}
												</td>
<th>
													SC Description
												</th>
												<td>
													${sc_description}
												</td>
											</tr>	
										</table>
									</div>
								</div>
								
								
							</div>
							
						</div>
                        <input type="hidden" id="sk_shopper_id" name="sk_shopper_id" value="${sk_shopper_id }"/>
                        
						<div class="col-md-12">
							<div class="card-box">


								<ul class="nav nav-tabs">
									<li class="nav-item all"><a href="#home" data-toggle="tab"
										aria-expanded="false" class="nav-link "> Mystery Shopper
											Submission </a></li>
									<li class="nav-item sp"><a href="#sp" data-toggle="tab"
										aria-expanded="false" class="nav-link active"> Quality
											Assurance </a></li>
									<!--  <li class="nav-item review">
                                            <a href="#review" data-toggle="tab" aria-expanded="true" class="nav-link ">
                                               Review 
                                            </a>
                                        </li> -->
									<li class="nav-item score"><a href="#score"
										data-toggle="tab" aria-expanded="true" class="nav-link ">
											Score </a></li>

								</ul>
								<div class="tab-content">
									<div class="tab-pane fade show active" id="sp">

										<form method="post" action="<%=dashboardURL%>submitReview2">
											<div class=" table-responsive">
												<h4 class="m-t-0 header-title">
													<b> </b>
												</h4>
													 <div class="form-group text-right m-b-0">
															<button class="btn btn-gradient waves-effect waves-light"
																type="submit">Submit Review</button>

														</div> 
			                             <input type= "hidden" id="sk_shopper_id"  name="sk_shopper_id" value="${sk_shopper_id }">
					
												<table class="table table-striped table-bordered"
													cellspacing="0" width="100%">
													<thead>
														<tr>
															<th>Process</th>
															<th>Sub Process</th>
															<th>Standard No.</th>
															<th>Question</th>
															<th>Status</th>
															<th>Points Scored</th>
															<th>Action</th>
														</tr>
													</thead>
													<tbody>
													<c:forEach var="mvBean" items="${QualityAssuranceQuestionstatus}">
															<tr>
																<td>${mvBean.section_name}</td>
																<td>${mvBean.subsection_name}</td>
																<td>${mvBean.standard_number}</td>
																<td>${mvBean.question_text}</td>
																<td>${mvBean.answer_status}</td>
																<c:choose>
																	<c:when test="${mvBean.formula_flag !='1'}">
																		<td>${mvBean.scored_points}</td>
																	</c:when>
																	<c:otherwise>
																		<td>${mvBean.points}</td>

																	</c:otherwise>
																</c:choose>
																<td><a href="<%=dashboardURL%>viewQualityAssuranceQuestions/${mvBean.sk_shopper_id}/${mvBean.question_id}">Review</a><br></td>
															</tr>
															</c:forEach>
															
													
													</tbody>
												</table>
												</div>
										</form>

									</div>

									<!-- EXCEPTIONS -->
									<div class="tab-pane fade" id="home"></div>
									<div class="tab-pane fade" id="score"></div>
									<div class="tab-pane fade" id="review"></div>

									<!-- EXCEPTIONS -->

								</div>
							</div>
						</div>
						<!-- end col -->


					</div>
					<!-- end row -->

				</div>
				<!-- end container -->
			</div>
		</div>
	</div>
	<!-- Signup modal content -->
	<div id="custom-modal" class="modal-demo">
		<button type="button" class="close" onclick="Custombox.close();">
			<span>&times;</span><span class="sr-only">Close</span>
		</button>
		<h4 class="custom-modal-title">Comment</h4>
		<div class="custom-modal-text">
			<form action="<%=dashboardURL%>updateComments" method="Post">
			 <input type= "hidden" id="sk_shopper_id"  name="sk_shopper_id" value="${sk_shopper_id }">
				<div class="row">
					<div class="col-lg-12">
						<div class="row">
							<div class="form-group col-md-6">
								<label>Result</label> <select id="results" required
									class="form-control" name="answer">
									<option value="1">YES</option>
									<option value="0">NO</option>
								</select>
							</div>
							<div class="form-group col-md-6">
								<label>Badge</label> <select id="badge" required
									class="form-control" name="badge">
									<option value="NO">NO</option>
									<option value="YES">YES</option>
								</select>
							</div>
							<div class="form-group col-md-6">
								<label>PMO Review Status</label> <select id="review_status"
									required class="form-control" name="review_status">
									<option value="Open">Open</option>
									<option value="Close">Close</option>
								</select>
							</div>
							<div class="form-group col-md-6">
								<label>Comments</label>
								<textarea required="required" name="comment" id="comments"
									class="form-control"></textarea>
							</div>
						</div>
					</div>
				</div>
				<input type="hidden" name="audit_id" id="oidd" value="">
				<div class="form-group text-right m-b-0">
					<button class="btn btn-gradient waves-effect waves-light"
						type="submit">Submit</button>

				</div>
			</form>
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
<script
		src="<%=UI_PATH%>/assets/js/jquery.growl.js"></script>
	<script type="text/javascript">
        
        $(".nav-item.all").click(function (){
        	var sk_shopper_id=$("#sk_shopper_id").val();
        	
         	location.replace("<%=dashboardURL%>getMysQuestionnaire"+"/"+sk_shopper_id); 
        });
        $(".nav-item.sp").click(function (){
        	var sk_shopper_id=$("#sk_shopper_id").val();
         	location.replace("<%=dashboardURL%>getMYSsecondphase"+"/"+sk_shopper_id); 
        });
        $(".nav-item.review").click(function (){
        	var sk_shopper_id=$("#sk_shopper_id").val();
         	location.replace("<%=dashboardURL%>getMYSreview"+"/"+sk_shopper_id); 
        });
        $(".nav-item.score").click(function (){
        	var sk_shopper_id=$("#sk_shopper_id").val();
         	location.replace("<%=dashboardURL%>getMysScore"+"/"+sk_shopper_id); 
        });
        
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
			$.growl.notice({
				message : "${statusMessage}"
			});

		}
		else {
			$.growl.error({
				message : "${statusMessage}"
			});
		}
	</script> 

</body>
</html>