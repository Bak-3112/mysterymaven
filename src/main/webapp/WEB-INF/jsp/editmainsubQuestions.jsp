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
<title>Questionnaire</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta
	content="A fully featured admin theme which can be used to build CRM, CMS, etc."
	name="description" />
<meta content="Coderthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<META Http-Equiv="Cache-Control" Content="no-cache">
<META Http-Equiv="Pragma" Content="no-cache">
<META Http-Equiv="Expires" Content="0">

<link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">

<link
	href="<%=UI_PATH%>/plugins/select2/css/select2.min.css"
	rel="stylesheet" type="text/css" />
<!-- DataTables -->
<link
	href="<%=UI_PATH%>/plugins/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=UI_PATH%>/plugins/datatables/buttons.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<!-- Responsive datatable examples -->
<link
	href="<%=UI_PATH%>/plugins/datatables/responsive.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />

<!-- Custom box css -->
<link
	href="<%=UI_PATH%>/plugins/custombox/css/custombox.min.css"
	rel="stylesheet">

<!-- App css -->
<link
	href="<%=UI_PATH%>/assets/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=UI_PATH%>/assets/css/icons.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=UI_PATH%>/assets/css/metismenu.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=UI_PATH%>/assets/css/style.css"
	rel="stylesheet" type="text/css" />

<script
	src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>

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

.radio label::after {
	top: 9px !important;
}

.btnstyle {
	height: 35px;
}
</style>

</head>

<body>
	<div id="wrapper">
		<!-- Navigation Bar-->
		<jsp:include page="include/header.jsp"></jsp:include>
		<jsp:include page="include/sidemenu.jsp"></jsp:include>
		<jsp:include page="include/modal.jsp"></jsp:include>
		<!-- End Navigation Bar-->

		<!-- ============================================================== -->
		<!-- Start right Content here -->
		<!-- ============================================================== -->
		<div class="content-page">
			<!-- Start content -->
			<div class="content">
				<div class="container-fluid">

					<div class="row">
						<div class="col-12">
							<div class="page-title-box">

								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					<!-- end row -->


					<div class="row">
						<div class="col-lg-12">

							<div class="card-box">
								<h4 class="header-title m-t-0">Edit Questions</h4>
								<p class="text-muted font-14 m-b-20"></p>

								<form method="POST"
									action="<%=dashboardURL%>/updatequestionpost/${question_id}">
									<div class="form-row">
										<div class="form-group col-md-3">
											<label for="userName">Brand<span class="text-danger">*</span></label>
											<select name="sk_brand_id" parsley-trigger="change" required
												class="form-control" id="sk_brand_id" disabled>
												<option value="${brand_id}">${brand_name}</option>
												<c:forEach var="dbBean" items="${activebrandList}">
													<option value="${dbBean.sk_brand_id }">${dbBean.brand_name }</option>
												</c:forEach>
											</select>
										</div>
										<input type="hidden" value="${brand_id}" name="brand_id" >

										<div class="form-group col-md-3">
											<label for="userName">Mode Of Contact<span
												class="text-danger">*</span></label> <select name="mode_of_contact"
												parsley-trigger="change" required class="form-control"
												id="modeOfContact" disabled>
												<option value="${modeofcontact}">${modeofcontact}</option>
												<option value="All">All</option>
												<option value="Email/Website">Email/Website</option>
												<option value="Telephone">Telephone</option>
												<option value="Walk In">Walk In</option>
												<option value="Online Sales Channel">Online Sales
													Channel</option>

											</select>
											  <input type="hidden" value="${modeofcontact}" name="mode_of_contact" >
										</div>

										<div class="form-group col-md-3">
											<label for="userName">Section<span
												class="text-danger">*</span></label> <select name="sk_section_id"
												parsley-trigger="change" disabled required
												class="form-control" id="sk_section_id"
												onchange="getSubSection(this.value)">
												<option value="${section_id}">${section_name}</option>
												<c:forEach var="dbBean" items="${sectionList}">
													<option value="${dbBean.sk_section_id }">${dbBean.section_name }</option>
												</c:forEach>

											</select>
										</div>

										<div class="form-group col-md-3">
											<label for="userName">Sub Section<span
												class="text-danger">*</span></label> <select name="sk_subsection_id"
												parsley-trigger="change" disabled required
												class="form-control" id="sk_subsection_id">
												<option value="${subsection_id}">${subsection_name}</option>

												<c:forEach var="dbBean" items="${subsectionList}">
													<option value="${dbBean.sk_subsection_id }">${dbBean.subsection_name }</option>
												</c:forEach>

											</select>
										</div>
										 <div  class="form-group col-md-2">
		                                            <label for="userName">Year<span class="text-danger">*</span></label>
		                                            <select name="year_applied"  class="form-control" id="year_applied">
		                                           <option value="${year}">${year}</option> 
		                                            <option value="2021">2021</option> 
		                                             <option value="2022">2022</option> 
		                                              <option value="2023">2023</option>
		                                              <option value="2024">2024</option> 
		                                              <option value="2025">2025</option>       
		                                            </select>
		                                        </div>

										<div class="form-group col-md-3">
											<label for="userName">Standard Number<span
												class="text-danger">*</span></label> <input type="text"
												name="standard_number" value="${stdno}" disabled
												id="standardNumber" parsley-trigger="change"
												placeholder="Enter Standard Number" required
												class="form-control">
										</div>


										<div class="form-group col-md-3">
											<label for="userName">Question Type<span
												class="text-danger">*</span></label> <select name="question_type"
												parsley-trigger="change" disabled class="form-control"
												id="questionType" required
												onchange="checkQuestion(this.value)">
												<option value="${question_type}">${question_type}</option>
												<option value="Main Question">Main Question</option>
												<option value="Main Question With Set Of SubQuestions">Main
													Question With Set Of SubQuestions</option>
												<option value="Dependent Question">Dependent
													Question</option>
												<option value="Dependent Question With Set Of SubQuestions">Dependent
													Question With Set Of SubQuestions</option>

											</select>
										</div>
										 <input type="hidden" name="formulFlag" id="formulaflag" value="no">
										<input type="hidden" value="${question_type}"
											name="question_type"> <input type="hidden"
											value="${answer_type}" name="answer_type">
										<div class="form-group col-md-2">
											<label for="userName">Question Mandatory<span
												class="text-danger">*</span></label> <select
												name="question_optiontype" parsley-trigger="change"
												class="form-control" id="questionOptionType">
												<option value="${question_optiontype}">${question_optiontype}</option>
												<option value="Mandatory">Mandatory</option>
												<option value="Optional">Optional</option>
											</select>
										</div>
										<div class="form-group col-md-2">
											<label for="userName">Points<span class="text-danger">*</span></label>
											<input type="text" value="${question_points}"
												name="question_points" id="questionPoints"
												parsley-trigger="change" placeholder="Enter Points" required
												class="form-control">
										</div>
										<div class="form-group col-md-2">
											<label for="userName">Code<span class="text-danger">*</span></label>
											<input type="text" value="${question_code}"
												name="question_code" id="questionCode"
												parsley-trigger="change" placeholder="Enter Code" required
												class="form-control">
										</div>
									</div>


									<div class="mainquestionwithsetofsubQuestions">
										<div class="mainsubquestions">
											<div class="form-group">
												<label for="emailAddress">Main Question<span
													class="text-danger">*</span></label> <input type="text"
													name="question" parsley-trigger="change"
													placeholder="Enter question" class="form-control" value="${question }" required
													id="question">
													<input type="hidden"
													name="sk_question_id" parsley-trigger="change"
													placeholder="Enter question" class="form-control" value="${qid }" required
													id="sk_question_id">
											</div>
											 <div class="row"><div  class="form-group col-lg-6">
                   <label for="userName">Add Comment Box</label>
                   <select name="comment_mandatory" parsley-trigger="change" class="form-control" id="comment_mandatory" >
                  	<option value="${comment_mandatory}">${comment_mandatory}</option>
                  	<option value="yes-mandatory">Yes-Mandatory</option>
                   	<option value="any-optional">Yes-Optional</option>
                  	<option value="no">No</option>
                    </select>
                  </div><div  class="form-group col-lg-6"></div></div>
										
											
											<div class="col-lg-12" id="main_question_sub"
												style="display: block;">
												<div id="accordion" class="m-b-30 subquestions">
													<button class="add_field_button2 btn btn-info"
														type="button">+</button>

													<c:forEach var="qBean" items="${subquestionsList}"
														varStatus="status">
														<div class="card" id="main_answer_${status.count }">
															<div class="card-header headerlength" id="heading">
																<h6 class="m-0">
																	<a href="#collapse${status.count }" class="text-dark"
																		data-toggle="collapse" aria-expanded="true"
																		aria-controls="collapse1">Sub Question
																		#${status.count } </a>
																</h6>
															</div>
															<div id="collapse${status.count }" class="collapse show"
																aria-labelledby="headingOne" data-parent="#accordion">
																<div class="card-body">
																	<div class="form-group col-md-1 pull-left">
																		<label for="emailAddress">SLNo<span
																			class="text-danger">*</span></label><input type="text"
																			name="question_sl_1" parsley-trigger="change"
																			placeholder="Enter question" class="form-control"
																			id="question_sl_1" value="${status.count }">
																	</div>
																	<div class="form-group col-md-5 pull-left">
																		<label for="emailAddress">Sub Question
																			${status.count }<span class="text-danger">*</span>
																		</label><input type="text"
																			name="subquestions[${status.index}].subQuestion"
																			parsley-trigger="change" placeholder="Enter question"
																			class="form-control subquestioninput"
																			value="${qBean.subQuestion}" id="question_1"
																			required="">
																	</div>
																	<input type="hidden"
																		name="subquestions[${status.index}].subQuestionAnswerType"
																		value="${qBean.subQuestionAnswerType}"> <input
																		type="hidden"
																		name="subquestions[${status.index}].sk_subquestion_id"
																		value="${qBean.sk_subquestion_id}">
																	<div class="form-group col-md-4 pull-left">
																		<label for="userName">Answer Type<span
																			class="text-danger">*</span></label><select
																			name="subquestions[${status.index}].subQuestionAnswerType"
																			parsley-trigger="change" disabled
																			class="form-control subquestionanswertypeinput"
																			id="question_type_1"
																			onchange="checkSubsetQuestionType(this.value,${status.count },${status.index })"
																			required=""><option
																				value="${qBean.subQuestionAnswerType}">${qBean.subQuestionAnswerType}</option>
																			<option value="Paragraph">Paragraph</option>
																			<option value="Date">Date</option>
																			<option value="Date &amp; Time">Date &amp;
																				Time</option>
																			<option value="Select/List">Select/List</option></select>
																	</div>
																	<div class="form-group col-md-2 pull-left">
																		<label for="emailAddress">Upload File<span
																			class="text-danger">*</span></label><input value="Yes"
																			type="checkbox"
																			name="subquestions[${status.index}].uploadFile"
																			class="form-control uploadfiles" data-upload="${qBean.uploadFile}"
																			id="subquestionuploadfile${status.index+1}">
																	</div>
																	<c:choose>
																		<c:when
																			test="${qBean.subQuestionAnswerType=='Select/List'}">
																			<div class="form-row col-md-12 subquestiontype_list${status.index}"
																				id="subquestiontype_list${status.index}">
																				
																					<div class="form-row  sub_answer1 col-md-12"
																						id="sub_answer">
																						<button class="add_field_button3 btn btn-info"
																							id="buttonanswer1" type="button" data-row="${status.count }" data-optionval="${status.index }">+</button>
																						<div class="form-group col-md-2 pull-left">
																							<label for="emailAddress">Answer<span
																								class="text-danger">*</span></label>
																						</div>
																						<div class="form-group col-md-2 pull-left">
																							<label for="emailAddress">Points<span
																								class="text-danger">*</span></label>
																						</div>
																						<div class="form-group col-md-1 pull-left">
																							<label for="emailAddress">Code<span
																								class="text-danger">*</span></label>
																						</div>
																						<div class="form-group col-md-1 pull-left">
																							<label for="emailAddress">Response<span
																								class="text-danger">*</span></label>
																						</div>
																						<div class="form-group col-md-2 pull-left">
																							<label for="emailAddress">Mandatory<span
																								class="text-danger">*</span></label>
																						</div>
																						<div class="form-group col-md-2 pull-left">
																							<label for="emailAddress">Comment<span
																								class="text-danger">*</span></label>
																						</div>
																					</div>
																					
																					<c:forEach var="count" items="${optionsList.get(status.index)}" varStatus="status1">
																					<div class="form-row sub_answer1 sub_otpions col-md-12"
																						id="sub_answer">
																						<div class="form-group col-md-2 pull-left">
																						<input
																		type="hidden"
																		name="subquestions[${status.index}].subquestionanswers[${status1.index}].sk_answer_id"
																		value="${count.sk_answer_id}">
																							<input type="text"
																								name="subquestions[${status.index}].subquestionanswers[${status1.index}].answer"
																								id="subanswer_main1" parsley-trigger="change" value="${count.answer }"
																								placeholder="Enter Answer"
																								class="form-control sub subquestionanswerinput">
																						</div>
																						<div class="form-group col-md-2 pull-left">
																							<input type="text"
																								name="subquestions[${status.index}].subquestionanswers[${status1.index}].answer_points"
																								id="subanswerpoints_main1"
																								parsley-trigger="change" value="${count.answer_points }"
																								placeholder="Enter Points"
																								class="form-control subquestionpointsinput">
																						</div>
																						<div class="form-group col-md-1 pull-left">
																							<input type="text"
																								name="subquestions[${status.index}].subquestionanswers[${status1.index}].answer_code"
																								id="code_main" parsley-trigger="change" value="${count.answer_code }"
																								placeholder="Enter Code" class="form-control">
																						</div>
																						<div class="form-group col-md-1 pull-left">
																							<input type="text"
																								name="subquestions[${status.index}].subquestionanswers[${status1.index}].answer_response"
																								id="subanswerresponse_main1"
																								parsley-trigger="change" value="${count.answer_response }"
																								placeholder="Enter Response"
																								class="form-control subquestionresponseinput">
																						</div>
																						<div class="form-group col-md-2 pull-left">
																							<select
																								name="subquestions[${status.index}].subquestionanswers[${status1.index}].answer_optiontype"
																								id="mandatory_main" parsley-trigger="change"
																								class="form-control mandatory_main">
																								<option value="${count.answer_optiontype }">${count.answer_optiontype }</option>
																								<option
																									value="Yes">Yes</option>
																								<option value="No">No</option></select>
																						</div>
																						<div class="form-group col-md-2 pull-left">
																							<select
																								name="subquestions[${status.index}].subquestionanswers[${status1.index}].answer_comment"
																								id="comment_main" parsley-trigger="change"
																								class="form-control comment_main">
																								<option value="${count.answer_comment }">${count.answer_comment }</option>
																								<option
																									value="Yes">Yes</option>
																								<option value="No" >No</option></select>
																						</div>
																						<button class="btnstyle remove_field" data-id="${count.sk_answer_id}" value="${count.sk_answer_id}" onclick="deleteselectedanswer(this.value)" data-toggle="modal" data-target="#myModal" data-count="${status.index}" type="button">-</button>
<%-- 																						<button class="remove_field" data-id="${count.sk_answer_id }" type="button">-</button>
 --%>																					</div>
																					</c:forEach>
																				
																			</div>
																		</c:when>
																		<c:when
																			test="${qBean.subQuestionAnswerType=='Date & Time'}">
																			<div class="form-row col-md-12"
																				id="subquestiondatetime${status.index}">
																				<div class="dateandtimemaindiv${status.index}"
																					style="padding: 20px;">
																					<input type="hidden" value="nodatetimeid" name=" subquestions[${status.index}].sk_datetime_id">
																					<div class="row">
																						<div class="form-group col-md-2">
																							<label for="userName">Date:<span
																								class="text-danger"></span></label>
																						</div>
																						<div class="form-group col-md-2">
																							<label for="userName">Points<span
																								class="text-danger">*</span></label> <input type="text"
																								name="subquestions[${status.index}].date_points" value="${qBean.date_points}"
																								id="datePoints" parsley-trigger="change"
																								placeholder="Enter Points" class="form-control">
																						</div>
																						<div class="form-group col-md-2">
																							<label for="userName">Code<span
																								class="text-danger">*</span></label> <input type="text"
																								name="subquestions[${status.index}].date_code" id="dateCode" value="${qBean.date_code }"
																								parsley-trigger="change"
																								placeholder="Enter Code" class="form-control">
																						</div>
																						<div class="form-group col-md-2">
																							<label for="userName">Response<span
																								class="text-danger">*</span></label> <input type="text" value="${qBean.date_response }"
																								name="subquestions[${status.index}].date_response"
																								id="dateResponse" parsley-trigger="change"
																								placeholder="Enter Response"
																								class="form-control">
																						</div>
																						<div class="form-group col-md-3">
																							<label for="userName">Routing Type<span
																								class="text-danger">*</span></label> <input type="text" value="${qBean.date_routing}"
																								name="subquestions[${status.index}].date_routing"
																								id="dateRouting" parsley-trigger="change"
																								placeholder="Enter Rounting Type"
																								class="form-control">
																						</div>
																						<br>
																						<div class="form-group col-md-2">
																							<label for="userName">Time:<span
																								class="text-danger"></span></label>
																						</div>
																						<div class="form-group col-md-2">
																							<label for="userName">Points<span
																								class="text-danger">*</span></label> <input type="text" value="${qBean.time_points}"
																								name="subquestions[${status.index}].time_points"
																								id="timePoints" parsley-trigger="change"
																								placeholder="Enter Points" class="form-control">
																						</div>
																						<div class="form-group col-md-2">
																							<label for="userName">Code<span
																								class="text-danger">*</span></label> <input type="text"
																								name="subquestions[${status.index}].time_code" id="timeCode" value="${qBean.time_code}"
																								parsley-trigger="change"
																								placeholder="Enter Code" class="form-control">
																						</div>
																						<div class="form-group col-md-2">
																							<label for="userName">Response<span
																								class="text-danger">*</span></label> <input type="text" value="${qBean.time_response}"
																								name="subquestions[${status.index}].time_response"
																								id="timeResponse" parsley-trigger="change"
																								placeholder="Enter Response"
																								class="form-control">
																						</div>
																						<div class="form-group col-md-3">
																							<label for="userName">Routing Type<span
																								class="text-danger">*</span></label> <input type="text" value="${qBean.time_routing}"
																								name="subquestions[${status.index}].time_routing"
																								id="timeRouting" parsley-trigger="change"
																								placeholder="Enter Rounting Type"
																								class="form-control">
																						</div>
																					</div>
																				</div>
																			</div>
																		</c:when>
																		<c:when
																			test="${qBean.subQuestionAnswerType=='Date'}">
																			<div class="form-row col-md-12"
																				id="subquestiondate${status.index}">
																				<div class="datemaindiv${status.index}" style="padding: 20px;">
																				<input type="hidden" value="nodateid" name=" subquestions[${status.index}].sk_datetime_id">
																					<div class="row">
																						<div class="form-group col-md-2">
																							<label for="userName">Date:<span
																								class="text-danger"></span></label>
																						</div>
																						<div class="form-group col-md-2">
																							<label for="userName">Points<span
																								class="text-danger">*</span></label> <input type="text" value="${qBean.date_points}"
																								name="subquestions[${status.index}].date_points" 
																								id="datePoints" parsley-trigger="change"
																								placeholder="Enter Points" class="form-control">
																						</div>
																						<div class="form-group col-md-2">
																							<label for="userName">Code<span
																								class="text-danger">*</span></label> <input type="text" value="${qBean.date_code}"
																								name="subquestions[${status.index}].date_code" id="dateCode"
																								parsley-trigger="change"
																								placeholder="Enter Code" class="form-control">
																						</div>
																						<div class="form-group col-md-2">
																							<label for="userName">Response<span
																								class="text-danger">*</span></label> <input type="text" value="${qBean.date_response}"
																								name="subquestions[${status.index}].date_response"
																								id="dateResponse" parsley-trigger="change"
																								placeholder="Enter Response"
																								class="form-control">
																						</div>
																						<div class="form-group col-md-3">
																							<label for="userName">Routing Type<span
																								class="text-danger">*</span></label> <input type="text" value="${qBean.date_routing}"
																								name="subquestions[${status.index}].date_routing"
																								id="dateRouting" parsley-trigger="change"
																								placeholder="Enter Rounting Type"
																								class="form-control">
																						</div>
																					</div>
																				</div>

																			</div>
																			</c:when>
																		<c:otherwise>
																		</c:otherwise>

																	</c:choose>



																</div>
															</div>
														</div>
													</c:forEach>
												</div>
											</div>
										</div>
									</div>

									<div class="form-group text-right m-b-0">
										<button class="btn btn-gradient waves-effect waves-light btnsubmit formulaupload continueupload subquestionbtn "  type="submit">
                                              Continue To Upload Formula
                                            </button>
                                            <button  class="btn btn-light waves-effect m-l-5 btnsubmit subquestionbtn " type="submit">
                                                Submit
                                            </button>
                                          <a href="<%=dashboardURL%>questions" class="btn btn-light waves-effect m-l-5">
                                                Cancel
                                            </a>
									</div>

								</form>
							</div>
							<!-- end card-box -->
						</div>
						<!-- end col -->






					</div>
					<!-- end row -->



				</div>
				<!-- container -->

			</div>
			<!-- content -->

		</div>


		<!-- ============================================================== -->
		<!-- End Right content here -->
		<!-- ============================================================== -->


	</div>
	<!-- END wrapper -->
	<!-- Footer -->
	<jsp:include page="include/footer.jsp"></jsp:include>
	<!-- End Footer -->



	<!-- jQuery  -->
	<script
		src="<%=UI_PATH%>/assets/js/jquery.min.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/popper.min.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/bootstrap.min.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/metisMenu.min.js"></script>
	<script src="<%=UI_PATH%>/assets/js/waves.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/jquery.slimscroll.js"></script>
	<!-- Parsley js -->
	<script type="text/javascript"
		src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>
	<!--  App js -->
	<script
		src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>


	<script type="text/javascript">
		$(document).ready(function() {
			$('form').parsley();
		});
	    
	      $(".continueupload").on("click",function(){
	    	  $("#formulaflag").val("yes");
	      });
	      
	</script>

	<script type="text/javascript">
		function checkQuestion(questiontype) {
			
				

				var x = 0;
				var z = 0;
				var y = 0;
				var alphabets = 65;
				var add_button = $(".add_field_button2"); //Add button ID
				var max_fields = 30; //maximum input boxes allowed
				var wrapper = $(".subquestions"); //Fields wrapper
				$(add_button)
						.on(
								"click",
								function(e) {

									e.preventDefault();
									if (x < max_fields) { //max input box allowed
										x++; //text box increment
										y = x + 1;
										u = 0;
										z++;
										$(wrapper)
												.append(
														'<div class="card" id="main_answer_'+y+'" ><div class="card-header d-flex" id="heading"><h6 class="m-0 mr-auto"><a href="#collapse'+y+'" class="text-dark" data-toggle="collapse" aria-expanded="true" aria-controls="collapse'+y+'">Sub Question #'
																+ y
																+ ' </a></h6><button class="remove_field pull-right btn btn-info" >-</button></div><div id="collapse'+y+'" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion"><div class="card-body"><div class="form-group col-md-1 pull-left"><label for="emailAddress">SLNo<span class="text-danger">*</span></label><input type="text" name="question_sl_'+y+'" parsley-trigger="change" placeholder="Enter question" class="form-control" id="question_sl_'+y+'" value="'+y+'"></div><div class="form-group col-md-5 pull-left"><label for="emailAddress">Sub Question '
																+ y
																+ '<span class="text-danger">*</span></label><input type="text" name="subquestions['+x+'].subQuestion" parsley-trigger="change" required placeholder="Enter question" class="form-control subquestioninput" id="question_'+y+'"><input type="hidden" name="subquestions['+x+'].sk_subquestion_id" value="nosubid" id="question_'+y+'"></div><div  class="form-group col-md-4 pull-left"><label for="userName">Answer Type<span class="text-danger">*</span></label><select name="subquestions['
																+ x
																+ '].subQuestionAnswerType" parsley-trigger="change" required class="form-control subquestionanswertypeinput" id="question_type_'
																+ y
																+ '" onchange="checkSubsetQuestionType(this.value,'
																+ y
																+ ','
																+ z
																+ ')"><option value="">Select Answer Type</option><option value="Paragraph">Paragraph</option><option value="Date">Date</option><option value="Date & Time">Date & Time</option><option value="Select/List">Select/List</option></select></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Upload File<span class="text-danger">*</span></label><input value="Yes" type="checkbox" name="subquestions['+y+'].uploadFile" class="form-control" id="subquestionuploadfile'+y+'"></div><div class="form-row col-md-12" id="subquestiontype_list'+y+'" ></div><div class="form-row col-md-12" id="subquestiondate'+y+'" ></div><div class="form-row col-md-12" id="subquestiondatetime'+y+'" ></div></div></div></div><div class="form-group col-lg-6" id="formula"></div>'); //add input box
									}
								});

				$(wrapper).on("click", ".remove_field", function(e) { //user click on remove text

					e.preventDefault();
					$(this).closest('.card').remove();
				});

			} 
		

		//main question with set of subquestions options selectlist
	</script>
	
	<script type="text/javascript">

	
	$(".subquestions").each(function(){
	
				var x = $(this).find(".headerlength").length-1;
				var z = $(this).find(".headerlength").length-1;
				var y = $(this).find(".headerlength").length-1;
				var alphabets = 65;
				var add_button = $(".add_field_button2"); //Add button ID
				var max_fields = 30; //maximum input boxes allowed
				var wrapper = $(".subquestions"); //Fields wrapper
				$(add_button)
						.on(
								"click",
								function(e) {

									e.preventDefault();
									if (x < max_fields) { //max input box allowed
										x++; //text box increment
										y = x + 1;
										u = 0;
										z++;
										$(wrapper)
												.append(
														'<div class="card" id="main_answer_'+y+'" ><div class="card-header d-flex" id="heading"><h6 class="m-0 mr-auto"><a href="#collapse'+y+'" class="text-dark" data-toggle="collapse" aria-expanded="true" aria-controls="collapse'+y+'">Sub Question #'
																+ y
																+ ' </a></h6><button class="remove_field pull-right btn btn-info" >-</button></div><div id="collapse'+y+'" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion"><div class="card-body"><div class="form-group col-md-1 pull-left"><label for="emailAddress">SLNo<span class="text-danger">*</span></label><input type="text" name="question_sl_'+y+'" parsley-trigger="change" placeholder="Enter question" class="form-control" id="question_sl_'+y+'" value="'+y+'"></div><div class="form-group col-md-5 pull-left"><label for="emailAddress">Sub Question '
																+ y
																+ '<span class="text-danger">*</span></label><input type="text" name="subquestions['+x+'].subQuestion" parsley-trigger="change" required placeholder="Enter question" class="form-control subquestioninput" id="question_'+y+'"></div><input type="hidden" name="subquestions['+x+'].sk_subquestion_id" value="nosubid" id="question_'+y+'"><div  class="form-group col-md-4 pull-left"><label for="userName">Answer Type<span class="text-danger">*</span></label><select name="subquestions['
																+ x
																+ '].subQuestionAnswerType" parsley-trigger="change" required class="form-control subquestionanswertypeinput" id="question_type_'
																+ y
																+ '" onchange="checkSubsetQuestionType(this.value,'
																+ y
																+ ','
																+ z
																+ ')"><option value="">Select Answer Type</option><option value="Paragraph">Paragraph</option><option value="Date">Date</option><option value="Date & Time">Date & Time</option><option value="Select/List">Select/List</option></select></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Upload File<span class="text-danger">*</span></label><input value="Yes" type="checkbox" name="subquestions['+y+'].uploadFile" class="form-control" id="subquestionuploadfile'+y+'"></div><div class="form-row col-md-12" id="subquestiontype_list'+y+'" ></div><div class="form-row col-md-12" id="subquestiondate'+y+'" ></div><div class="form-row col-md-12" id="subquestiondatetime'+y+'" ></div></div></div></div><div class="form-group col-lg-6" id="formula"></div>'); //add input box
									}
								});

				$(wrapper).on("click", ".remove_field", function(e) { //user click on remove text

					e.preventDefault();
					$(this).closest('.card').remove();
				});

	})

		//main question with set of subquestions options selectlist
	</script>
	
	<script>
	
	
	
	
	
        var alphabets=65;
        var add_button      = $(".add_field_button3"); //Add button ID
        var max_fields      = 30; //maximum input boxes allowed
        var wrapper         = $(".subquestions"); //Fields wrapper
  	 // var x1=$(".subquestiontype_list3").find(".sub_otpions ").length;
  
        $(add_button).on("click",function(e){
        
        	 
        	
        	 var row =$(this).attr("data-row") ;
        	 var optionval =$(this).attr("data-optionval") ;
        	 var answerwrapper   = $("#subquestiontype_list"+optionval);
        	 console.log(row)
        	 console.log(optionval)
        	  console.log(answerwrapper)
        	   var x1=$(".subquestiontype_list"+optionval).find(".sub_otpions").length-1;
        	 console.log(x)
             e.preventDefault();
        	 var x=$(".subquestiontype_list"+optionval).find(".sub_otpions").length; 	
            if(x < max_fields){ //max input box allowed
                x++;
                x1++;
               //text box increment
            	$(answerwrapper).append('<div class="form-row sub_answer'+row+' sub_otpions col-md-12" id="sub_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x1+'].answer" id="subanswer_main1" parsley-trigger="change"  placeholder="Enter Answer" class="form-control sub subquestionanswerinput"><input type="hidden" name="subquestions['+optionval+'].subquestionanswers['+x1+'].sk_answer_id" value="noanswerid" class="form-control sub subquestionanswerinput"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x1+'].answer_points" id="subanswerpoints_main1" parsley-trigger="change"  placeholder="Enter Points" class="form-control subquestionpointsinput"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x1+'].answer_code" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x1+'].answer_response" id="subanswerresponse_main1" parsley-trigger="change"  placeholder="Enter Response" class="form-control subquestionresponseinput"></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers['+x1+'].answer_optiontype" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers['+x1+'].answer_comment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field1" data-rows="'+x1+'">-</button></div></div>');
            }
            x1++;
            $(answerwrapper).on("click",".remove_field1", function(e){ //user click on remove text
	            
	        	   e.preventDefault(); 
	   	        $(this).parent('#sub_answer').remove(); 
	   	        x--;
	            });
        });
   	
          

	
	
	
 //for sub set questions
 function checkSubsetQuestionType(val,row,optionval){
	 
		if(val=="Select/List"){
			 $(".dateandtimemaindiv"+row).remove();
			 $(".datemaindiv"+row).remove();
			var answerwrapper   = $("#subquestiontype_list"+row);
	    	$(answerwrapper).append('<div class="form-row  sub_answer'+row+' col-md-12" id="sub_answer"><button class="add_field_button3 btn btn-info" id="buttonanswer1" type="button">+</button><div class="form-group col-md-2 pull-left"><label for="emailAddress">Answer<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Points<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Code<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Response<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Mandatory<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Comment<span class="text-danger">*</span></label></div></div>');
	    	$(answerwrapper).append('<div class="form-row sub_answer'+row+' col-md-12" id="sub_answer"><div class="form-group col-md-2 pull-left"><input type="hidden" name="subquestions['+optionval+'].subquestionanswers[0].sk_answer_id" value="noanswerid" class="form-control sub subquestionanswerinput"><input type="text" name="subquestions['+optionval+'].subquestionanswers[0].answer" id="subanswer_main1" parsley-trigger="change"  placeholder="Enter Answer" class="form-control sub subquestionanswerinput"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[0].answer_points" id="subanswerpoints_main1" parsley-trigger="change"  placeholder="Enter Points" class="form-control subquestionpointsinput"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[0].answer_code" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[0].answer_response" id="subanswerresponse_main1" parsley-trigger="change"  placeholder="Enter Response" class="form-control subquestionresponseinput"></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers[0].answer_optiontype" id="mandatory_main" parsley-trigger="change"  class="form-control mandatory_main"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers[0].answer_comment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div></div></div>');
			
	 
	    	 var x = 0; 
	    	 var row = row;
		        var alphabets=65;
		        var add_button      = $(".add_field_button3"); //Add button ID
		        var max_fields      = 30; //maximum input boxes allowed
		        var wrapper         = $(".subquestions"); //Fields wrapper
		        $(add_button).on("click",function(e){
					
		             e.preventDefault();
		            if(x < max_fields){ //max input box allowed
		                x++; //text box increment
		            	$(answerwrapper).append('<div class="form-row sub_answer'+row+' col-md-12" id="sub_answer"><div class="form-group col-md-2 pull-left"><input type="hidden" name="subquestions['+optionval+'].subquestionanswers['+x+'].sk_answer_id" value="noanswerid" class="form-control sub subquestionanswerinput"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer" id="subanswer_main1" parsley-trigger="change"  placeholder="Enter Answer" class="form-control sub subquestionanswerinput"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_points" id="subanswerpoints_main1" parsley-trigger="change"  placeholder="Enter Points" class="form-control subquestionpointsinput"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_code" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_response" id="subanswerresponse_main1" parsley-trigger="change"  placeholder="Enter Response" class="form-control subquestionresponseinput"></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_optiontype" id="mandatory_main" parsley-trigger="change"  class="form-control mandatory_main"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_comment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field">-</button></div></div>');
		            }
		        });
		        
		        $(answerwrapper).on("click",".remove_field", function(e){ //user click on remove text
		            
		        	   e.preventDefault(); 
		   	        $(this).parent('#sub_answer').remove(); 
		   	        x--;
		            });
		          
		
		
		}else if(val=="Date"){
			alert(val)
			var x=0;
			$(".sub_answer"+row).remove();
			 $(".dateandtimemaindiv"+row).remove();
			 $(".datemaindiv"+row).remove();
			var answerwrapper   = $("#subquestiondate"+row);
			 $(answerwrapper).append('<div class="datemaindiv'+row+'" style="padding: 20px;"><div class="row">'
					 +'<div class="form-group col-md-2">'
					 +'<label for="userName">Date:<span class="text-danger"></span></label>'
					 +' </div>' 
					 +'	<div class="form-group col-md-2">'
					 +'<label for="userName">Points<span class="text-danger">*</span></label>'
					 +' <input type="hidden" value="nodateid" name="subquestions['+optionval+'].sk_datetime_id">    <input type="text" name="subquestions['+optionval+'].date_points"  id="datePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
					 +' </div>'
					 +'<div class="form-group col-md-2">'
					 +'<label for="userName">Code<span class="text-danger">*</span></label>'
					 +'    <input type="text" name="subquestions['+optionval+'].date_code"  id="dateCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">'
					 +'</div>'
					 +' <div class="form-group col-md-2">'
					 +' <label for="userName">Response<span class="text-danger">*</span></label>'
					 +'    <input type="text" name="subquestions['+optionval+'].date_response" id="dateResponse"  parsley-trigger="change" placeholder="Enter Response" class="form-control">'
					 +'</div>'
					 +'  <div class="form-group col-md-3">'
					 +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
					 +'   <input type="text" name="subquestions['+optionval+'].date_routing" id="dateRouting"    parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
					 +'</div>'
					
	                 
	                  +'</div></div>'); 
	 }else  if(val=="Date & Time"){
		 var answerwrapper   = $("#subquestiondatetime"+row);
		 $(".dateandtimemaindiv"+row).remove();
		 $(".datemaindiv"+row).remove();
			$(".sub_answer"+row).remove();
		 $(answerwrapper).append('<div class="dateandtimemaindiv'+row+'" style="padding: 20px;"><div class="row">'
				 +'<div class="form-group col-md-2">'
				 +'<label for="userName">Date:<span class="text-danger"></span></label>'  
				 +' </div>'
				 +'	<div class="form-group col-md-2">'
				 +'<label for="userName">Points<span class="text-danger">*</span></label>'
				 +'  <input type="hidden" value="nodatetimeid" name=" subquestions['+optionval+'].sk_datetime_id">  <input type="text" name="subquestions['+optionval+'].date_points"  id="datePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
				 +' </div>'
				 +'<div class="form-group col-md-2">'
				 +'<label for="userName">Code<span class="text-danger">*</span></label>'
				 +'    <input type="text" name="subquestions['+optionval+'].date_code" id="dateCode" parsley-trigger="change"  placeholder="Enter Code" class="form-control">'
				 +'</div>'
				 +' <div class="form-group col-md-2">'
				 +' <label for="userName">Response<span class="text-danger">*</span></label>'
				 +'    <input type="text" name="subquestions['+optionval+'].date_response"   id="dateResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
				 +'</div>'
				 +'  <div class="form-group col-md-3">'
				 +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
				 +'   <input type="text" name="subquestions['+optionval+'].date_routing"   id="dateRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
				 +'</div>'
				 +'<br>'
	              +'<div class="form-group col-md-2">'
	              +' <label for="userName">Time:<span class="text-danger"></span></label>'
	              +'</div>'
	              +'<div class="form-group col-md-2">'
	              +' <label for="userName">Points<span class="text-danger">*</span></label>'
	              +'   <input type="text" name="subquestions['+optionval+'].time_points" id="timePoints"     parsley-trigger="change" placeholder="Enter Points" class="form-control">'
	              +' </div>'
	              +'<div class="form-group col-md-2">'
	              +' <label for="userName">Code<span class="text-danger">*</span></label>'
	              +'    <input type="text" name="subquestions['+optionval+'].time_code" id="timeCode"   parsley-trigger="change" placeholder="Enter Code" class="form-control">'
	              +' </div>'
	              +'<div class="form-group col-md-2">'
	              +'<label for="userName">Response<span class="text-danger">*</span></label>'
	              +'    <input type="text" name="subquestions['+optionval+'].time_response"   id="timeResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
	              +' </div>'
	              +' <div class="form-group col-md-3">'
	              +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
	              +'   <input type="text" name="subquestions['+optionval+'].time_routing" id="timeRouting"   parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
	              +' </div>'
	              +'</div></div>'); 
		 
		 
	 }else{
		 $(".dateandtimemaindiv"+row).remove();
		 $(".datemaindiv"+row).remove();
			$(".sub_answer"+row).remove();
	 }
	 }
 
	$(document).ready(function () {
		
		$(".mandatory_main,.comment_main").each(function(){
			var usedNames = {};
			$(this).find("option").each(function(){
				
				if (usedNames[this.value]) {
                    $(this).remove();
                } else {
                    usedNames[this.value] = this.text;
                }
			});
			
		});
	
    });
 
 </script>
 
 
 
 
 <script>
 $(".uploadfiles").each(function(){
	 if($(this).attr("data-upload")=="yes" || $(this).attr("data-upload")=="Yes"){
		 $(this).attr("checked",true);
	 }else{
		 $(this).attr("checked",false);
	 }
 });
 
 

 </script>
 
 <script>
    
 var optionValues =[];
 $('#comment_mandatory option').each(function(){
    if($.inArray(this.value, optionValues) >-1){
       $(this).remove()
    }else{
       optionValues.push(this.value);
    }
 });
 
 function deleteselectedanswer(id){

	 var qid='${question_id}';
	 var value=id;
 	var url="<%=dashboardURL%>deleteanwerOptions/"+value+"/"+qid;
 	$(".btn-edit").attr("href",url);
 }
    </script>
 
 
 
</body>
</html>