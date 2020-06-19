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
<!doctype html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="format-detection" content="telephone=no">
<title>Performance 1</title>

<!-- SET: FAVICON -->
<link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">

<!-- SET: STYLESHEET -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<%=UI_PATH%>/assets1/css/bootstrap4hack.css">
<link rel="stylesheet" type="text/css"
	href="<%=UI_PATH%>/assets1/css/style.css">
<style type="text/css">
.table_secondary{
table-layout:fixed;
}
.round {
  border-radius: 50%;
}
.previous {
background-color: #92a2bd;
  color: black;
}
a:hover {
  background-color: #ddd;
  color: black;
}
a {
  text-decoration: none;
  display: inline-block;
  padding: 8px 16px;
}

</style>

</head>

<body>
	<!-- wrapper starts -->
	<div class="wrapper">
		<div class="container">
		<a class="btn btn-primary" href="<%=dashboardURL%>performance/${outlet}/${month}/${yearr}/${brand}/${dealer}/download">Download </a>
			<header>
				<nav class="navbar navbar-expand-lg navbar-light ">
				   <a href="<%=dashboardURL%>/Reports" class="previous">&laquo;  Back To Reports</a>
					<a class="navbar-brand" href="#">BMW GROUP - Sales Mystery
						Shopping 2019</a>
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>

					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav mr-auto">
							<li class="nav-item active"><a class="nav-link" href="#">
									<span class="sr-only">(current)</span>
							</a></li>
							<li class="nav-item"><a class="nav-link" href="#"></a></li>

							<li class="nav-item"><a class="nav-link " href="#"
								tabindex="-1" aria-disabled="true"></a></li>
						</ul>
						<div class="form-inline my-2 my-lg-0">
							<figure>
								<img
									src="<%=UI_PATH%>/assets1/images/bmwgroup-logo.PNG">
							</figure>
							<figure>
								<img
									src="<%=UI_PATH%>/assets1/images/nextlogo.PNG">
							</figure>
							<figure>
								<img
									src="<%=UI_PATH%>/assets1/images/bmw-logo.png">
							</figure>
							<figure>
								<img
									src="<%=UI_PATH%>/assets1/images/mini-logo.PNG">
							</figure>
						</div>
					</div>
				</nav>
			</header>
			<table>
				<div class="head-block d-flex bg_primary px-2">
					<h2 class="mr-auto m-0 py-1">Dealership Performance ${year}</h2>
					<h2 class="m-0 py-1">Outlet Score:
						 ${outlet_score}%
						(${scored_points}/${max_points})
						- YTD: ${ytd} %</h2>
				</div>
			</table>
			<div class="sales-overview">
				<div class="d-flex  border-bottom" style="background-color: #bfb9b9;">
				<div class="col-6 py-2 px-2">
						</div>
						  <div class="col-6 py-1">
              <p class="mb-0" style="text-align: right;">Online Sales Channel Score : ${osc_outlet_score} %
             (${osc_scored_points}/${osc_max_points}) 
            - YTD:  ${osc_ytd} %</p>
            </div> 

					</div>
					<div class="d-flex border-bottom">
						<div class="col-6 py-2 px-2">
							<p class="mb-0"> ${outlet_id}-
								${dealer_name} </p>
						</div>
						
						<div class="col-3 py-2 px-2">
							<p class="mb-0">Brand: ${brand_name}</p>
						</div>
						<div class="col-3 py-2">
							<p class="mb-0">Location: ${location} </p>
						</div>
					</div>
					<div class="d-flex border-bottom">
						<div class="col-6 py-2 px-2">
							<p class="mb-0">Address: ${address}</p>
						</div>
						<div class="col-6 py-2 px-2">
							<p class="mb-0">Source of Enquiry: ${mode_of_contact} </p>
						</div>

					</div>
				  
					<div class="d-flex ">
						<div class="col-12 py-2 px-2">
							<p class="mb-0">Outlet Performance: ${monthname}
								${year} </p>
						</div>

					</div>
			</div>

			<div class="row mx-0">
				<div class="col-6" style="text-align: center;background:#92a2bd;color:#fff;">Dealer Visit</div>
				<div class="col-6" style="text-align: center;background:#92a2bd;color:#fff;">Online Sales Channel</div>
				
				</div>
				<div class="row">
				
					<div class="col-6">
						<c:forEach var="rBean" items="${getNonOscSection}"> 
						<div class="table-wrapper">
							<table class="table table_primary">
								<thead>
									<tr class="first-row">
										<th colspan="4"> ${rBean.section_name} </th>
									</tr>
									<tr class="second-row">
										<th colspan="1" style="text-align: left;" width="46%">TOTAL:  ${rBean.mtd}% - (YTD:
											${rBean.ytd}%)</th>
										<th width="18%">CURR.</th>
										<th width="18%">PREV.</th>
										<th width="18%">DIFF.</th>
									</tr>
								</thead>
								<tbody>
									 <c:forEach var="rBean1" items="${rBean.getNonOscSectionPercentage}"> 
										<tr>
											<td class="text-right"> ${rBean1.subsection_name }</td>
											<td>
												<div class="progress-status">
													<span style="width: 88%"></span> ${rBean1.currtmonth_percentage }%
												</div>
											</td>
											<td>
												<div class="progress-status">
													<span style="width: 88%"></span> ${rBean1.prevmonth_percentage}%
												</div>
											</td>
											<td> ${rBean1.diff}</td>
										</tr>
									 </c:forEach> 
								</tbody>

							</table>

						</div>
						<table class="table table_primary">
							<tfoot>
								<tr>
									<th colspan="5">Current :${year}_${monthname} </th>
								</tr>
								<tr>
									<th colspan="5">Previous : ${prevYear}_${pre_monthname} </th>
								</tr>
							</tfoot>
						</table>
						 </c:forEach> 
					</div>
					<div class="col-6">
					<c:forEach var="rBean" items="${getOscSection}"> 
						<div class="table-wrapper">
							<table class="table table_primary">
								<thead>
									<tr class="first-row">
										<th colspan="4"> ${rBean.section_name}</th>
									</tr>
									<tr class="second-row">
										<th colspan="1" style="text-align: left;" width="46%">TOTAL:  ${rBean.mtd}% - (YTD:
											${rBean.ytd}%) </th>
										<th width="18%">CURR.</th>
										<th width="18%">PREV.</th>
										<th width="18%">DIFF.</th>
									</tr>
								</thead>
								<tbody>
									 <c:forEach var="rBean1" items="${rBean.getoscsectionscore}"> 
										<tr>
											<td class="text-right"> ${rBean1.subsection_name }</td>
											<td>
												<div class="progress-status">
													<span style="width: 88%"></span> ${rBean1.currtmonth_percentage }%
												</div>
											</td>
											<td>
												<div class="progress-status">
													<span style="width: 88%"></span> ${rBean1.prevmonth_percentage}%
												</div>
											</td>
											<td> ${rBean1.diff}</td>
										</tr>
									 </c:forEach>
								</tbody>

							</table>

						</div>
						<table class="table table_primary">
							<tfoot>
								<tr>
									<th colspan="5">Current: ${year}_${monthname}</th>
								</tr>
								<tr>
									<th colspan="5">Previous :${prevYear}_${pre_monthname}</th>
								</tr>
							</tfoot>
						</table>
						 </c:forEach> 
					 </div>
				
			</div>
			<%-- <table class="table table_secondary workingtdtr">
				<thead>
					<tr>
						<th class="text-center bg-title" colspan="11">Working
							Standards</th>
					</tr>
					<tr class="bg-title-2">
						<th class="text-center" colspan="5" width="40%"></th>
						<c:forEach var="qBean" items="${workingstandard1}"
							varStatus="loop">
						<td>2019_${qBean.quarter}</td>
						</c:forEach>
						<td>YTD</td>
						<td>YTD SCORE</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th colspan="11">Online</th>
					</tr>
					<tr>
						<td scope="row">01.</td>
						<td colspan="4">Auto Response (instantly)</td>
						<c:forEach var="qBean" items="${workingstandard1}"
							varStatus="loop">
							<c:choose>
								
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>
					<tr>
						<td scope="row">02.</td>
						<td colspan="4">Personal Response within 4 hrs</td>
						<c:forEach var="qBean" items="${workingstandard2}"
							varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>
					<tr>
						<td scope="row">03.</td>
						<td colspan="4">Quality of Response: Standards</td>
						<c:forEach var="qBean" items="${workingstandard3}"
							varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>
					<tr>
						<td scope="row">04.</td>
						<td colspan="4">Attempt to make an Appointment</td>
						<c:forEach var="qBean" items="${workingstandard4}"
							varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>
					<tr>
						<th colspan="11">Onsite</th>
					</tr>
					<tr>
						<td scope="row">05.</td>
						<td colspan="4">Retail Lead Documentation</td>
						<c:forEach var="qBean" items="${workingstandard5}"
							varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>
					<tr>
						<td scope="row">06.</td>
						<td colspan="4">Test Drive Offer</td>
						<c:forEach var="qBean" items="${workingstandard6}"
							varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>
					<tr>
						<td scope="row">07.</td>
						<td colspan="4">New Car Offer</td>
						<c:forEach var="qBean" items="${workingstandard7}"
							varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>
					<tr>
						<td scope="row">08.</td>
						<td colspan="4">Financing Product Offer given</td>
						<c:forEach var="qBean" items="${workingstandard8}"
							varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>
					<tr>
						<td scope="row">09.</td>
						<td colspan="4">Follow-up on Offer</td>
						<c:forEach var="qBean" items="${workingstandard9}"
							varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>



				</tbody>
			</table> --%>

			<%-- <table class="table table_secondary">
				<thead>
					<tr>
						<th class="text-center bg-title" colspan="11">Success Factors</th>
					</tr>
					<tr class="bg-title-2">
						<th class="text-center" colspan="5" width="40%"></th>
						<c:forEach var="qBean" items="${successfactors1}"
							varStatus="loop">
						<td>2019_${qBean.quarter}</td>
						</c:forEach>
						<td>YTD</td>
						<td>YTD SCORE</td>
					</tr>
				</thead>
				<tbody>

					<tr>
						<td scope="row">01</td>
						<td colspan="4">Personal Response within 2 working days</td>
						<c:forEach var="qBean" items="${successfactors1}" varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>
					<tr>
						<td scope="row">02.</td>
						<td colspan="4">Vehicle Presentation</td>
						<c:forEach var="qBean" items="${successfactors2}" varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>
					<tr>
						<td scope="row">03.</td>
						<td colspan="4">Test Drive Offer</td>
						<c:forEach var="qBean" items="${successfactors3}" varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>
					<tr>
						<td scope="row">04.</td>
						<td colspan="4">New Car Offer given</td>
						<c:forEach var="qBean" items="${successfactors4}" varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>

					<tr>
						<td scope="row">05.</td>
						<td colspan="4">Follow-up on Offer</td>
						<c:forEach var="qBean" items="${successfactors5}" varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>
					<tr>
						<td scope="row">06.</td>
						<td colspan="4">Consider buying a Vehicle</td>
						<c:forEach var="qBean" items="${successfactors6}" varStatus="loop">
							<c:choose>
								<c:when test="${qBean.count!='0'}">
									<td>${qBean.answer_count}/${qBean.count}</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
							<c:if test="${loop.last}">
								<td>${qBean.one}/${qBean.two}</td>
								<td>${qBean.percentage}%</td>
							</c:if>
						</c:forEach>
					</tr>



				</tbody>
			</table> --%>
			<input type="hidden" name="NonOsc_shopper_id" value="${NonOsc_shopper_id}">
        <input type="hidden" name="osc_shopper_id" value="${osc_shopper_id}">
			<input type="hidden" name="dealer_id" value="${dealer}">
			 <p>
				<%-- <a href="<%=dashboardURL%>performance/${outlet}/${dealer}/${month}/${yearr}/${brand}/2" class="btn btn-link">Next</a> --%> <!-- commented as per client request(Hiding Olr 3 tables) -->
				<a href="<%=dashboardURL%>performance3/${NonOsc_shopper_id}/${osc_shopper_id}/${dealer}/1" class="btn btn-link">Next</a>
			</p> 
		</div>

	</div>
	<!-- wrapper ends -->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
		integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
		crossorigin="anonymous"></script>
	<script>
		$(document).ready(function() {

		});
	</script>
</body>
</html>
