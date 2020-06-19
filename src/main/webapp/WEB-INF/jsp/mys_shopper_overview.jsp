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
<title>Mystery Shopping</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Poppins"
	rel="stylesheet">
<link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">
<link rel='stylesheet'
	href='<%=UI_PATH%>/design/css/bootstrap.min.css'>
<link rel="stylesheet" type="text/css"
	href="<%=UI_PATH%>/design/css/form-validation.css">
<link rel="stylesheet" type="text/css"
	href="<%=UI_PATH%>/design/css/time.css">
<link rel="stylesheet"
	href="<%=UI_PATH%>/design/css/custom.css"
	type="text/css">
</head>
<body class="bodysection">
	<!-- <div class="text-center h-txt">
        <h4>BMW GROUP Sales Mystery Shopping - 2018</h4>
        <p>Sales and Online Integrated Questionnaire</p>
      </div> -->
	<header class="header clearfix">
		<div class="logo pull-left">
			<a> <img
				src="<%=UI_PATH%>/design/images/bmw.png"
				width="50" height="50" alt="logo">
			</a> <a> <img
				src="<%=UI_PATH%>/design/images/mini.png"
				width="100" alt="logo">
			</a>

		</div>
		<div class="greetingtext pull-right">
			<span>Welcome <!-- Mystery Shopper  -->
				<b>${visitor_name}</b></span> <a href="<%=dashboardURL%>upload/${sid}"><img
				src="<%=UI_PATH%>/design/images/home.png"
				width="16" height="16"></a>
		</div>
	</header>
	<div class="quizwrap">
		<div class="col-xs-12  fig-wrap">

			<figure class="banner-figure">
				<img
					src="<%=UI_PATH%>/design/images/car.jpg"
					width="956" height="450" class="img-responsive">
			</figure>

		</div>
		<div class="col-xs-12 form-wrap pt-60">

			<h2 class="text-center">
				<p class="count-wrap">
					<span class="step-number">1</span>/<span class="total-count">5</span>
				</p>
				VISIT OVERVIEW
			</h2>
			<h5 class="text-center">Please fill in the details below to get
				started with the Questionnaire</h5>
			<div class="overviewwrap">
				<form id="SignupForm" action="<%=dashboardURL%>viewquestion/${shopper_id}"
					method="post">
					<input type="hidden" value="${shopper_id}" name="sk_shopper_id">
					<c:forEach var="qBean" items="${shoppingList}" varStatus="loop">
					
						<fieldset>

							<div class="form-group">
								<div class="row">
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="Quarter">Quarter</label> <input id="Name"
											name="quarter1" type="text" placeholder="Enter Dealer Name"
											class="form-control"
											value="${qBean.quarter}&nbsp;${qBean.year}"
											readonly="readonly" />
									</div>
								</div>
								<div class="clearfix"></div>
								<br>
								<h6>Dealer Deatils</h6>
								<div class="col-xs-12">
									<hr>
								</div>
								<div class="row">
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="Name">Dealer Name</label> <input id="Name"
											name="outlets1" type="text" placeholder="Enter Dealer Name"
											class="form-control" value="${qBean.dealer_name}"
											readonly="readonly" />
									</div>
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="location">Location</label> <input id="Name"
											name="state1" type="text" placeholder="Enter Dealer Name"
											class="form-control" value="${qBean.state_name}"
											readonly="readonly" />

									</div>
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="City">City</label> <input id="Name" name="city1"
											type="text" placeholder="Enter Dealer Name"
											class="form-control" value="${qBean.city_name}"
											readonly="readonly" />

									</div>
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="Number">Location ID/BU No.</label> <input
											id="Number" name="outlet_id1" type="text"
											placeholder="Enter details" class="form-control"
											value="${qBean.outlet_id }" readonly="readonly" />
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="postalcode">Postal Code</label> <input
											id="postalcode" name="postalCode1" type="text"
											placeholder="Enter Postal code" class="form-control"
											value="${qBean.pincode}" readonly="readonly" />
									</div>
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="postalcode1">Country</label> <input
											id="postalcode1" name="country1" type="text"
											placeholder="Enter Country" class="form-control"
											value="India" readonly="readonly" />
									</div>
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="Brand">Brand</label> <input id="Name"
											name="shoppers_link_shoppersCarBrand" type="text" placeholder="Enter Brand Name"
											class="form-control" value="${qBean.brand_name }"
											readonly="readonly" />

									</div>
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="Brand">Model Shopped</label> <input id="Name"
											name="type1" type="text" placeholder="Enter Brand Name"
											class="form-control" value="${qBean.type }"
											readonly="readonly" />
									</div>
								</div>
								<div class="clearfix"></div>
								<br>
								<h6>Visit Details</h6>
								<div class="col-xs-12">
									<hr>
								</div>
								<div class="row">
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="Gender">Gender of Shopper</label> <input id="Name"
											name="gender1" type="text" placeholder="Enter Gender"
											class="form-control" value="${qBean.gender}"
											readonly="readonly" />

									</div>
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="visit-data">Visit Date</label>
										<div class="input-group date datepicker" id="datepicker">
											<input id="visit-data" name="shopper_link_visit_date" class="form-control"
												placeholder="${qBean.visit_date }"
												value="${qBean.visit_date }" required/> <span
												class="input-group-addon"><span
												class="fa fa-calendar"></span></span>
										</div>
									</div>

									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="Start-&-End-Time">Start Time</label>
										<div class="input-group date timepicker1" id="timepicker1">
											<input class="form-control " id="Start-Time" name="start_date" 
												placeholder="Select" value="${qBean.start_time}" required> <span
												class="input-group-addon"><span class="fa fa-clock-o"></span></span>
										</div>
									</div>
									<div class="col-xs-6 col-md-3 col-sm-6  mrg-top">
										<label for="Start-&-End-Time">End Time</label>
										<div class="input-group date" id="timepicker2">
											<input class="form-control" id="End-Time" name="end_date"
												placeholder="Select" value="${qBean.end_time}" required> <span
												class="input-group-addon"><span class="fa fa-clock-o"></span></span>
										</div>
									</div>


								</div>
								<div class="row">
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="Mode-of-Contact">Mode of Contact</label> <input
											id="Name" name="modeOfContact1" type="text"
											placeholder="Mode of Contact" value="${qBean.mode_of_contact}"
											class="form-control" readonly="readonly" />
									</div>
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="Met-SC">Met SC</label> <select
											class="form-control" id="Met-SC" name="met_sc"
											onChange="hidesc()">
											<option>Select</option>
											<option value="yes">Yes</option>
											<option value="no" selected>No</option>
										</select>
										<div class="down-arrw">
											<i class="fa fa-chevron-down"></i>
										</div>
									</div>
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="SC-Name">SC Name</label> <input id="SC-Name"
											name="sc_name" type="text" placeholder="Enter SC Name"
											class="form-control" readonly/>
									</div>
									<div class="col-xs-6 col-md-3 col-sm-6 mrg-top">
										<label for="SC-Designation">SC Designation</label> <select
											class="form-control" id="SC-Designation" name="sc_designation" disabled>
											<option value="">Select</option>
											<option value="Sales Manager">Sales Manager</option>
											<option value="Sales Consultant">Sales Consultant</option>
											<option value="NA">NA</option>
										</select>
										<div class="down-arrw">
											<i class="fa fa-chevron-down"></i>
										</div>
									</div>
									<div class="col-md-6 col-sm-6 mrg-top">
										<label for="SC-Description">SC Description</label> <input
											id="SC-Description" name="sc_description" type="text"
											placeholder="Enter SC Description" class="form-control" readonly/>
									</div>
								</div>
								<div class="col-xs-12">
									<hr class="form-line">
								</div>
							</div>
						</fieldset>

						<fieldset>

							<div class="form-group">
								<h6>Personal details provided at dealership</h6>
								<div class="col-xs-12">
									<hr>
								</div>

								<div class="row">
									<div class="col-xs-12 col-md-6 col-sm-6 mrg-top">
										<label for="Name1">Name</label> <input id="number1"
											name="shopper_link_name" type="text" placeholder="Enter name" value="${qBean.name}"
											class="form-control" required/>
									</div>
									<div class="col-xs-4 col-md-2 col-sm-2 mrg-top" style="white-space: nowrap;">
										<label for="Age">Age</label> <input id="number1"
											name="shopper_link_age" type="text" placeholder="Enter age" value="${qBean.age}"
											class="form-control" required/>
									</div>
									<div class="col-xs-8 col-md-4 col-sm-4 mrg-top">
										<label>Gender</label> <br> <label class="radio-label">
										
											<input type="radio" value="Male" name="shopper_link_gender" <c:if test="${qBean.gender=='Male' || qBean.gender=='male'}">checked</c:if>>
											<span>Male</span>
										</label> <label class="radio-label"> <input type="radio"
											value="Female" name="shopper_link_gender" <c:if test="${qBean.gender=='Female' || qBean.gender=='female'}">checked</c:if>> <span>Female</span>
										</label>
									</div>
									<div class="col-xs-12 col-md-6 col-sm-6 mrg-top">
										<label for="Age1">Email ID given to dealership</label> <input
											id="SC-Description" name="shopper_link_email" type="email"
											placeholder="Enter email" value="${qBean.email}" class="form-control" required/>
									</div>
									<div class="col-xs-12 col-md-6 col-sm-6 mrg-top">
										<label for="number1">Contact Number</label> <input
											id="number1" name="shopper_link_contact" type="tel"
											placeholder="Enter mobile no" value="${qBean.contact_number}" class="form-control" required/>
									</div>
								</div>
								<div class="row"></div>
								<div class="col-xs-12">
									<hr class="form-line">
								</div>
							</div>

						</fieldset>
						<fieldset>

							<div class="form-group">

								<h6>Vehicle Driven to Dealershippp</h6>
								<div class="col-xs-12">
									<hr>
								</div>
								<div class="row">
									<%-- <div class="col-md-6 col-sm-6 mrg-top">
										<label for="Gender1">Brand</label> <input id="Name"
											name="brand" type="text" value="${qBean.brandName}"  placeholder="Enter Brand"
											class="form-control" />
									</div> --%>
									<div class="col-md-6 col-sm-6 mrg-top">
										<label for="brand">Brand</label> <input id="brand" name="brand"
											type="text" placeholder="Enter Brand" value="${qBean.shoppers_car_owned}"  class="form-control" />
									</div>
									<div class="col-md-6 col-sm-6 mrg-top">
										<label for="Model">Model</label> <input id="Name" name="shoppers_link_shoppersCarModel"
											type="text" placeholder="Enter Model" value="${qBean.shoppers_link_shoppersCarModel}"  class="form-control" />
									</div>
									<div class="col-md-6 col-sm-6 mrg-top">
										<label for="Year-of-construction">Year of Purchase</label>
										<input id="Name" name="shoppers_link_shoppersCarYop" type="text"
											placeholder="Enter YOP"  value="${qBean.shoppers_car_yop}"  class="form-control" />
									</div>
									
								</div>
								<div class="col-xs-12">
									<hr class="form-line">
								</div>
							</div>
						</fieldset>
						<fieldset>

							<div class="form-group">
								<h6>Product Genius Details</h6>
								<div class="col-xs-12">
									<hr>
								</div>
								<div class="row">
									<div class="col-md-6  mrg-top">
										<label for="Met-SC1">Met during visit</label> <input id="Name"
											name="Shopper_link_productMet" value="${qBean.shopper_link_productMet}" type="text" placeholder="Enter details"
											class="form-control" />
									</div>
									<div class="col-md-6  mrg-top">
										<label for="Name-provided">Name provided</label> <input
											id="Name-provided" name="Shopper_link_productNameProvided" type="text" value="${qBean.shopper_link_productNameProvided}"
											placeholder="Enter details" class="form-control" />
									</div>
									<div class="col-md-6  mrg-top">
										<label for="Select-Designation1">Name</label> <input
											id="Select-Designation1" name="Shopper_link_productName" type="text" value="${qBean.shopper_link_productName}"
											placeholder="Enter details" class="form-control" />
									</div>
									<div class="col-md-6  mrg-top">
										<label for="Description1">Description</label> <input
											id="Description1" name="Shopper_link_productDesc" type="text" value="${qBean.shopper_link_productDesc}"
											placeholder="Enter details" class="form-control" />
									</div>
								</div>
								<div class="col-xs-12">
									<hr class="form-line">
								</div>
							</div>
						</fieldset>
					</c:forEach>

					<button id="SaveAccount" type="submit"
						class="btn btn-primary submit">Proceed</button>
				</form>
			</div>
		</div>

	</div>

	<div class="container"></div>

	<script
		src="<%=UI_PATH%>/design/js/jquery.min.js"></script>
	<script
		src="<%=UI_PATH%>/design/js/bootstrap.min.js"></script>
	<script
		src='<%=UI_PATH%>/design/js/date&time/jquery-2.2.4.min.js'></script>
	<script
		src='<%=UI_PATH%>/design/js/date&time/moment.min.js'></script>
	<script
		src='<%=UI_PATH%>/design/js/date&time/bootstrap-datetimepicker.min.js'></script>
	<script
		src="<%=UI_PATH%>/design/js/date&time/date&time.js"></script>
	<script
		src="<%=UI_PATH%>/design/js/form-validation/jquery.validate.min.js"></script>
	<script
		src="<%=UI_PATH%>/design/js/form-validation/jquery.formtowizard.js"></script>
	<script
		src="<%=UI_PATH%>/design/js/form-validation/main.js"></script>
	<script type="text/javascript" src="<%=UI_PATH%>plugins/parsleyjs/parsley.min.js"></script>
		
	<script type="text/javascript">
	 $(document).ready(function() {
         $('form').parsley();
     });
	
	
	
		function hidesc() {

			var metsc = $('#Met-SC').val();

			if (metsc == 'no') {
				$("#SC-Name").prop('readonly', true);
				$('#SC-Designation').prop('disabled', true);
				$('#SC-Description').prop('readonly',true);
				$("#SC-Name").val('');
				$("#SC-Designation").val('');
				$("#SC-Description").val('');
			} else if (metsc == 'yes') {
				$("#SC-Name").prop('readonly', false);
				$('#SC-Designation').prop('disabled', false);
				$('#SC-Description').prop('readonly',false);
			}

		}
	</script>
</body>
</html>