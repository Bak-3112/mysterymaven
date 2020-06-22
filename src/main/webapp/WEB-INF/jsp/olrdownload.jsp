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

<script
	src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>
	<script src="https://kendo.cdn.telerik.com/2019.1.220/js/jquery.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2019.1.220/js/jszip.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2019.1.220/js/kendo.all.min.js"></script> 
    
 <!--  <script src="https://kendo.cdn.telerik.com/2017.2.621/js/jquery.min.js"></script>
  <script src="https://kendo.cdn.telerik.com/2017.2.621/js/jszip.min.js"></script>
  <script src="https://kendo.cdn.telerik.com/2017.2.621/js/kendo.all.min.js"></script> -->
    <style type="text/css">
.table_secondary{
table-layout:fixed;
}
.progress-status span{background-color:transparent;}
</style>
    
</head>

<body>
	<!-- wrapper starts -->
	<div class="wrapper">
		<div class="container">
		<div id="DivPrint">
			<header>
				<nav class="navbar navbar-expand-lg navbar-light ">
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
											${rBean.ytd}%)</th>
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
			<%-- <table class="table table_secondary">
          <thead>
            <tr>
              <th class="text-center bg-title" colspan="11">Retail Standards</th>
            </tr>
            <tr  class="bg-title-2">
              <th class="text-center" colspan="5" width="40%"></th>
              <c:forEach var="qBean" items="${retailstandards1}"
							varStatus="loop">
						<td>2019_${qBean.quarter}</td>
						</c:forEach>
              <td>YTD</td>
              <td>YTD SCORE</td>
            </tr>
          </thead>
          <tbody>
            
            
              <td scope="row">2.1</td>
              <td colspan="4"> Were you actively welcomed after entering the retailer and passed on to the
correct person or area?</td>
                <c:forEach var="qBean" items="${retailstandards1}" varStatus="loop">
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
              <td scope="row">2.4</td>
              <td colspan="4"> Was a systematic and customer oriented product presentation conducted?</td>
                <c:forEach var="qBean" items="${retailstandards2}" varStatus="loop">
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
              <td scope="row">2.6</td>
              <td colspan="4">Were you provided with detailed product information for relevant products and product features upon request?</td>
             <c:forEach var="qBean" items="${retailstandards3}" varStatus="loop">
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
              <td scope="row">3.1</td>
              <td colspan="4">Was a test drive actively offered to you after the need analysis and before any sales close?</td>
             <c:forEach var="qBean" items="${retailstandards4}" varStatus="loop">
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
               <td scope="row">3.2</td>
              <td colspan="4">Were ALL of the test drive procedure requirements fulfilled?</td>
             <c:forEach var="qBean" items="${retailstandards5}" varStatus="loop">
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
               <td scope="row">3.3</td>
              <td colspan="4">Was the test drive vehicle clean inside and outside, without personal
belongings and was the vehicle prepared for the test drive (enough fuel/battery
charged)?</td>
             <c:forEach var="qBean" items="${retailstandards6}" varStatus="loop">
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
              <td scope="row">5.2</td>
              <td colspan="4">Were you contacted in the corresponding time frame after creating the sales
offer?</td>
                <c:forEach var="qBean" items="${retailstandards1}" varStatus="loop">
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
			
			

<div class="questions stdnum">
            <div>
            <input type="hidden" name="pid" value="${pageid}" id="pid">
            
              
                 <c:forEach var="qBean" items="${questionsandoptions}" varStatus="counter"> 
                 <c:choose>
    <c:when test="${qBean.mode_of_contact=='Online Sales Channel'}"> 
        <h4 class="bg-lite-blue text-white p-2 online">Online Sales Channel</h4>
     </c:when>
     
    <c:otherwise>
       
    </c:otherwise>
</c:choose>
               
              <div class="radionoanswer" id="radionoanswer">
              
              <c:choose>
               <c:when test="${qBean.mode_of_contact=='Online Sales Channel'}"> 
               		 <h4 class="bg-lite-blue text-white p-2 headingonline">${qBean.subsection_name}<span class="float-right"> ${qBean.percentage}</span></h4>
               		 </c:when>
               		  <c:otherwise>
               		  <h4 class="bg-lite-blue text-white p-2 heading">${qBean.subsection_name}<span class="float-right"> ${qBean.percentage}</span></h4>
               		  </c:otherwise>
                		</c:choose>
                		 <c:if test="${qBean.question_type=='Main Question' && qBean.answer_type=='Select/List'}"> 
                		<div class="brder pb-1"></div>
                				<div class="row">
                  				  <div class="col-6">
                      				  <h5 class="mb-0"><span class="question-index" style="margin-right: 2.2px"; data-id="${qBean.standard_number}"> ${qBean.standard_number}</span>${qBean.question}</h5>
                    			  </div>
                   				 <div class="col-5">
                        			<ul class="custom-control custom-radio">
                        			   <input type="text" name="ans" class="answers" value="${qBean.selectedanswerid}" style="display: none"> <br>
                        			    <input type="text" name="anssssss" class="padleft" value="${qBean.selected_option_comment}" style="display: none" > 
                        			     
                        			   <c:forEach var="qBean1" items="${qBean.optionsList}"> 
                        			   <c:set var = "comment" value="${qBean.selected_option_comment}"/>
                        			      <c:set var = "num1" value="Yes"/>
                                         <c:set var = "num2" value="No"/>
                            <li>
                                <label class="mb-0 radio" ansss> 
      								 <input type="radio" class="correctans"  name="${qBean.selectedanswerid}${counter.count}" >
                                      <span class="answerss" data-id="${qBean1.options}"><c:out value="${qBean1.options}"/></br><c:if test="${not empty comment}">
                                                  <label id="btn_ans" style="display:none">Comment. ${qBean.selected_option_comment}</label>
                                                  </c:if></span><br> 
                                  
                                </label>
                            </li>
                             </c:forEach>
                        			</ul>
                    			</div>
                    			<c:choose>
                        <c:when test="${qBean.formulFlag == '1'}">
                        <div class="col-1">${qBean.formulapoints}/${qBean.total_ques_points}</div>
                        </c:when>
                        <c:when test="${qBean.total_ques_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:when test="${qBean.max_question_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:otherwise>
                        <div class="col-1">${qBean.max_scored_points}/${qBean.max_question_points}</div>
                        </c:otherwise>
                        </c:choose>
                    			 <div class="col-6">
                    			  <c:choose>
                            <c:when test="${qBean.comment_criteria=='null' || qBean.comment_criteria=='NULL'}">
                          <p></p>
                             </c:when> 
                           <c:otherwise> 
                             <p class="padleft">${qBean.comment_criteria}
                        </p>
                            </c:otherwise>
                            </c:choose> 
                    </div>
                				</div>
                				 </c:if>
            	</div>
                
				
				
				  <c:if test="${qBean.question_type=='Main Question' && qBean.answer_type=='Paragraph'}"> 
				 
				 
				  <div>
                <div class="brder pb-1"></div>
                <div class="row">
                    <div class="col-6">
                        <h5 class="mb-0"><span class="question-index" data-id="${qBean.standard_number}">${qBean.standard_number}</span>${qBean.question}</h5>
                        
                    </div>
                    <div class="col-5">
                    <p class="padleft">${qBean.paragraph}</p>
                    </div>
                    <c:choose>
                        <c:when test="${qBean.formulFlag == '1'}">
                        <div class="col-1">${qBean.formulapoints}/${qBean.total_ques_points}</div>
                        </c:when>
                         <c:when test="${qBean.total_ques_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:when test="${qBean.max_question_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:otherwise>
                        <div class="col-1">${qBean.max_scored_points}/${qBean.max_question_points}</div>
                        </c:otherwise>
                        </c:choose>
                    
                    
                    
                    <div class="col-6">
                           <c:choose>
                            <c:when test="${qBean.comment_criteria=='null' || qBean.comment_criteria=='NULL'}">
                          <p></p>
                            </c:when>
                            <c:otherwise>
                             <p class="padleft">${qBean.comment_criteria}
                        </p>
                            </c:otherwise>
                            </c:choose> 
                    </div>
                </div>
            </div>
				 </c:if> 
				
				  <c:if test="${qBean.question_type=='Main Question' && qBean.answer_type=='Date & Time'}">
				  <div>
                <div class="brder pb-1"></div>
                <div class="row">
                    <div class="col-6">
                        <h5 class="mb-0"><span class="question-index" data-id="${qBean.standard_number}">${qBean.standard_number}</span>${qBean.question}</h5>
                       
                    </div>
                    	<div class="col-5">
                    	  <p class="padleft" style="padding-left: 30px !important;">${qBean.date_code}  ${qBean.time_code} </p>
                    	  </div>
                    	  <c:choose>
                        <c:when test="${qBean.formulFlag == '1'}">
                        <div class="col-1">${qBean.formulapoints}/${qBean.total_ques_points}</div>
                        </c:when>
                        <c:when test="${qBean.total_ques_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:when test="${qBean.max_question_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:otherwise>
                        <div class="col-1">${qBean.max_scored_points}/${qBean.max_question_points}</div>
                        </c:otherwise>
                        </c:choose>
                    <div class="col-5">
                  
                        <c:choose>
                            <c:when test="${qBean.comment_criteria=='null' || qBean.comment_criteria=='NULL'}">
                          <p></p>
                            </c:when>
                            <c:otherwise>
                             <p class="padleft">${qBean.comment_criteria}
                        </p>
                            </c:otherwise>
                            </c:choose> 
                    </div>
                   
                     
                </div>
            </div>
				 </c:if> 
				 <c:if test="${qBean.question_type=='Main Question' && qBean.answer_type=='Date'}"> 
				  <div>
                <div class="brder pb-1"></div>
                <div class="row">
                    <div class="col-6">
                        <h5 class="mb-0"><span class="question-index" data-id="${qBean.standard_number}">${qBean.standard_number}</span>&nbsp; &nbsp;${qBean.question}</h5>
                    </div>
                    <div class="col-5">
                    	  <p class="padleft" style="padding-left: 30px !important;">${qBean.date_code}</p>
                    	  </div>
                    	  <c:choose>
                        <c:when test="${qBean.formulFlag == '1'}">
                        <div class="col-1">${qBean.formulapoints}/${qBean.total_ques_points}</div>
                        </c:when>
                        <c:when test="${qBean.total_ques_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:when test="${qBean.max_question_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:otherwise>
                        <div class="col-1">${qBean.max_scored_points}/${qBean.max_question_points}</div>
                        </c:otherwise>
                        </c:choose>
                    <div class="col-5">
                        <c:if test="${qBean.comment_criteria!='NULL'}">
                        <p class="padleft">${qBean.comment_criteria}
                        </p>
                        </c:if>
                    </div>
                </div>
            </div>
				 </c:if> 
				
				 <c:if test="${qBean.question_type=='Main Question' && qBean.answer_type=='Time'}"> 
				  <div>
                <div class="brder pb-1"></div>
                <div class="row">
                    <div class="col-5">
                        <h5 class="mb-0"><span class="question-index" data-id="${qBean.standard_number}">${qBean.standard_number}</span>${qBean.question}</h5>
                        
                    </div>
                    <div class="col-5">
                    	  <p class="padleft" style="padding-left: 30px !important;"> ${qBean.time_code}</p>
                    	  </div>
                    	   <c:choose>
                        <c:when test="${qBean.formulFlag == '1'}">
                        <div class="col-1">${qBean.formulapoints}/${qBean.total_ques_points}</div>
                        </c:when>
                        <c:when test="${qBean.total_ques_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:when test="${qBean.max_question_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:otherwise>
                        <div class="col-1">${qBean.max_scored_points}/${qBean.max_question_points}</div>
                        </c:otherwise>
                        </c:choose>
                    <div class="col-6">
                      <c:choose>
                            <c:when test="${qBean.comment_criteria=='null' || qBean.comment_criteria=='NULL'}">
                          <p></p>
                            </c:when>
                            <c:otherwise>
                             <p class="padleft">${qBean.comment_criteria}
                        </p>
                            </c:otherwise>
                            </c:choose> 
                    </div>
                </div>
            </div>
				 </c:if> 
				
			 	  <c:if test="${qBean.question_type=='Main Question With Set Of SubQuestions'}"> 
                  <div>
                    <div class="brder pb-1"></div>
                    <div class="row sub_question">
                        <div class="col-6">
                            <h5 class="mb-0"><span class="question-index" data-id="${qBean.standard_number}">${qBean.standard_number}</span>${qBean.question}</h5>
                        </div>
                        <div class="col-5 ty d-flex">    
                          <c:forEach var="qBean3" items="${qBean.selectedoptionsList}"> 
                                	   <input type="text" name="ans" class="input" value="${qBean3.selectedanswerid}" style="display: none"> 
                                	    <%-- <input type="text" name="ans" class="form-control" value="${qBean3.comment_criteria}" style="display: block">  --%>
                                	       
                          </c:forEach>     	   
                                	    <c:choose> 
                            <c:when test="${qBean3.selectedanswerid=='null'}">
                                	          
                            <ul class="custom-control radio">
                           
                             
                            
                               <%--  <li>
                                   <label class="mb-0 checkbox mainquestionchk"> 
                                        <input type="checkbox"  name="prodpresentation" value="${qBean3.paragraph}">
                                         <c:forTokens items="${mySplit}" delims="=" var="mySplit2">
                                        <span class="answerss" data-id="${qBean3.paragraph}"><c:out value="${qBean3.paragraph}"/> </span><br>
                                        
                                    </label>
                                      
                                </li> --%>
                                
                              
                            </ul>
                             </c:when> 
                            <c:otherwise> 
                            <ul class="custom-control radio">
                           
                            <c:forEach var="qBean2" items="${qBean.selectedoptionsList}"> 
                            
                                <li>
                                   <label class="mb-0 checkbox mainquestionchk"> 
                                        <input type="checkbox"  name="prodpresentation" value="${qBean2.subQuestion}">
                                         <c:set var = "num1" value="Yes"/>
                                         <c:set var = "num2" value="No"/>
                                         <c:set var = "num3" value="${qBean2.comment_criteria}"/>
                                         <c:set var = "para" value="${qBean2.paragraph}"/>
                                          <c:set var = "num5" value="${qBean2.date_code}"/>
                                          <c:set var = "datetime" value="${qBean2.time_code}"/>
                                           <c:set var = "SubAnswerType" value="Date & Time"/>
                                       <c:choose>
                                    <c:when test="${qBean2.selectedanswerid eq num1}">
                                                  <span class="answerss" data-id="${qBean2.subQuestion }"><c:out value="${qBean2.subQuestion}"/><br>
                                                    <input type="date" name="ans" class="form-control" value="${qBean2.date_code}" style="display: none">  
                                                    <c:if test="${qBean2.paragraph ne 'null'}">                                           
                                                    Answer. ${qBean2.paragraph}<br>
                                                    </c:if> 
                                                    <c:if test="${qBean2.date_code ne 'null'}">                                           
                                                    Answer. ${qBean2.date_code}<br>
                                                    </c:if> 
                                                    <c:if test="${qBean2.time_code ne 'null'}">                                           
                                                    Answer. ${qBean2.time_code}<br>
                                                    </c:if> 
                                                  <c:if test="${not empty num3}">
                                                  Comment. ${qBean2.comment_criteria} 
                                                  </c:if></span><br>
                                    </c:when>
                                    <c:when test="${qBean2.selectedanswerid eq num2}">
                                                  <span class="answerss" data-id="${qBean2.subQuestion }"><c:out value="${qBean2.subQuestion}"/><br> 
                                                  <c:if test="${qBean2.paragraph ne 'null'}">                                           
                                                    Answer. ${qBean2.paragraph}<br>
                                                    </c:if> 
                                                    <c:if test="${qBean2.date_code ne 'null'}">                                           
                                                    Answer. ${qBean2.date_code}<br>
                                                    </c:if> 
                                                    <c:if test="${qBean2.time_code ne 'null'}">                                           
                                                    Answer. ${qBean2.time_code}<br>
                                                    </c:if>
                                                  <c:if test="${not empty num3}">
                                                  Comment. ${qBean2.comment_criteria} 
                                                  </c:if></span><br>
                                    </c:when>
                                 
                                    <c:otherwise>
                                                    <span class="answerss" data-id="${qBean2.subQuestion }"><c:out value="${qBean2.subQuestion}"/>
                                                    
                                                    <c:if test="${qBean2.paragraph ne 'null'}">                                           
                                                    Answer. ${qBean2.paragraph}<br>
                                                    </c:if> 
                                                    <c:if test="${qBean2.date_code ne 'null'}">                                           
                                                    <br>Answer. ${qBean2.date_code}<br>
                                                    </c:if> 
                                                    <c:if test="${qBean2.time_code ne 'null'}">                                           
                                                    Answer. ${qBean2.time_code}
                                                    </c:if>
                                                    <c:if test="${qBean2.subQuestionAnswerType ne SubAnswerType}">
                                                    <br>Answer. ${qBean2.selectedanswerid}
                                                    </c:if>
                                                    <c:if test="${not empty num3}">
                                                    Comment. ${qBean2.comment_criteria}                                     
                                                  </c:if>  </span><br>
                                                    
                                    </c:otherwise>
                                    </c:choose>
                                    </label>
                                      
                                </li>
                                
                               </c:forEach>
                            </ul>
                             </c:otherwise>
                            
                            </c:choose> 
                            
                           
                        </div>
                       
                        <c:choose>
                        <c:when test="${qBean.formulFlag == '1'}">
                        <div class="col-1">${qBean.formulapoints}/${qBean.total_ques_points}</div>
                        </c:when>
                        <c:when test="${qBean.total_ques_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:when test="${qBean.sumOfQuesPoints == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:otherwise>
                        <div class="col-1">${qBean.sumOfScoredPoints}/${qBean.sumOfQuesPoints}</div>
                        </c:otherwise>
                        </c:choose>
                        <div class="col-6">
                          <c:choose>
                            <c:when test="${qBean.comment_criteria=='null' || qBean.comment_criteria=='NULL'}">
                          <p></p>
                            </c:when>
                            <c:otherwise> 
                             <p class="padleft"> ${qBean.comment_criteria} 
                        </p>
                            </c:otherwise>
                            </c:choose> 
                    </div>
                    </div>
                </div>
                
                
				 
				 </c:if>  
				
				  <c:if test="${qBean.question_type=='Dependent Question' && qBean.answer_type=='Select/List'}"> 
				  <div>
                		<div class="brder pb-1"></div>
                				<div class="row">
                  				  <div class="col-6">
                      				  <h5><span class="question-index" data-id="${qBean.standard_number}">${qBean.standard_number}</span>${qBean.question}</h5>
                    			  </div>
                   				 <div class="col-5">
                        			<ul class="custom-control custom-radio">
                        			  <input type="text" name="ans" class="answers" value="${qBean.selectedanswerid}" style="display: none"> 
                        			   <input type="text" name="anssssss" class="answers" value="${qBean.selected_option_comment}" style="display: none" >
                        			     <c:set var = "comment" value="${qBean.selected_option_comment}"/>
                        			   <c:forEach var="qBean1" items="${qBean.optionsList}"> 
                            		
                            <li>
                                <label class="mb-0 radio" ansss> 
      								 <input type="radio" class="correctans"  name="${qBean.selectedanswerid}${counter.count}" >
                                    <span class="answerss" data-id="${qBean1.options}"><c:out value="${qBean1.options}"/></br><c:if test="${not empty comment}">
                                                  <label id="btn_ans" style="display:none">Comment. ${qBean.selected_option_comment}</label>
                                                  </c:if></span><br> 
                                </label>
                            </li>
                             
                            
                             </c:forEach>
                        			</ul>
                    			</div>
                    			<c:choose>
                        <c:when test="${qBean.formulFlag == '1'}">
                        <div class="col-1">${qBean.formulapoints}/${qBean.total_ques_points}</div>
                        </c:when>
                        <c:when test="${qBean.total_ques_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:when test="${qBean.max_question_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:otherwise>
                        <div class="col-1">${qBean.max_scored_points}/${qBean.max_question_points}</div>
                        </c:otherwise>
                        </c:choose>
                    			<div class="col-6">
                          <c:choose>
                            <c:when test="${qBean.comment_criteria=='null' || qBean.comment_criteria=='NULL'}">
                          <p></p>
                            </c:when>
                            <c:otherwise>
                             <p class="padleft">${qBean.comment_criteria}
                        </p>
                            </c:otherwise>
                            </c:choose> 
                    </div>
                				</div>
            	</div>
				 </c:if>
				 <c:if test="${qBean.question_type=='Dependent Question' && qBean.answer_type=='Date & Time'}">
				  <div>
                		<div class="brder pb-1"></div>
                				<div class="row">
                  				  <div class="col-6">
                      				  <h5><span class="question-index" data-id="${qBean.standard_number}">${qBean.standard_number}</span>${qBean.question}</h5>
                    			  </div>
                   				 	   <div class="col-5">
                    <p class="padleft" style="padding-left: 30px !important;">${qBean.date_code}  ${qBean.time_code} </p>
                        
                    </div>
                    			<c:choose>
                        <c:when test="${qBean.formulFlag == '1'}">
                        <div class="col-1">${qBean.formulapoints}/${qBean.total_ques_points}</div>
                        </c:when>
                        <c:when test="${qBean.total_ques_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:when test="${qBean.max_question_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:otherwise>
                        <div class="col-1">${qBean.max_scored_points}/${qBean.max_question_points}</div>
                        </c:otherwise>
                        </c:choose>
                    			<div class="col-6">
                          <c:choose>
                           <c:when test="${qBean.comment_criteria=='null' || qBean.comment_criteria=='NULL'}">
                          <p></p>
                            </c:when> 
                            <c:otherwise>
                             <p class="padleft"> ${qBean.comment_criteria}  
                        </p>
                            </c:otherwise>
                            </c:choose> 
                    </div>
                				</div>
            	</div>
				</c:if>
				  <c:if test="${qBean.question_type=='Dependent Question' && qBean.answer_type=='Date'}">
				  <div>
                		<div class="brder pb-1"></div>
                				<div class="row">
                  				  <div class="col-6">
                      				  <h5><span class="question-index" data-id="${qBean.standard_number}">${qBean.standard_number}</span>${qBean.question}</h5>
                    			  </div>
                   				 	   <div class="col-5">
                    <p class="padleft" style="padding-left: 30px !important;">${qBean.date_code}</p>
                        
                    </div>
                    			<c:choose>
                        <c:when test="${qBean.formulFlag == '1'}">
                        <div class="col-1">${qBean.formulapoints}/${qBean.total_ques_points}</div>
                        </c:when>
                        <c:when test="${qBean.total_ques_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:when test="${qBean.max_question_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:otherwise>
                        <div class="col-1">${qBean.max_scored_points}/${qBean.max_question_points}</div>
                        </c:otherwise>
                        </c:choose>
                    			<div class="col-6">
                       <c:choose>
                             <c:when test="${qBean.comment_criteria=='null' || qBean.comment_criteria=='NULL'}">
                          <p></p>
                            </c:when> 
                            <c:otherwise>
                             <p class="padleft"> ${qBean.comment_criteria}  
                        </p>
                             </c:otherwise>
                            </c:choose> 
                    </div>
                				</div>
            	</div>
				</c:if>
				<c:if test="${qBean.question_type=='Dependent Question' && qBean.answer_type=='Time'}">
				  <div>
                		<div class="brder pb-1"></div>
                				<div class="row">
                  				  <div class="col-6">
                      				  <h5><span class="question-index" data-id="${qBean.standard_number}">${qBean.standard_number}</span>${qBean.question}</h5>
                    			  </div>
                   				 	   <div class="col-5">
                    <p class="padleft" style="padding-left: 30px !important;">${qBean.time_code}</p>
                        
                    </div>
                    			<c:choose>
                        <c:when test="${qBean.formulFlag == '1'}">
                        <div class="col-1">${qBean.formulapoints}/${qBean.total_ques_points}</div>
                        </c:when>
                        <c:when test="${qBean.total_ques_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:when test="${qBean.max_question_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:otherwise>
                        <div class="col-1">${qBean.max_scored_points}/${qBean.max_question_points}</div>
                        </c:otherwise>
                        </c:choose>
                    			<div class="col-6">
                         <c:choose>
                           <c:when test="${qBean.comment_criteria=='null' || qBean.comment_criteria=='NULL'}">
                          <p></p>
                            </c:when> 
                            <c:otherwise>
                             <p class="padleft"> ${qBean.comment_criteria} 
                        </p>
                             </c:otherwise>
                            </c:choose> 
                    </div>
                				</div>
            	</div>
				</c:if>
				<c:if test="${qBean.question_type=='Dependent Question' && qBean.answer_type=='Paragraph'}">
				 
				 
				  <div>
                <div class="brder pb-1"></div>
                <div class="row">
                    <div class="col-6">
                        <h5 class="mb-0"><span class="question-index" data-id="${qBean.standard_number}">${qBean.standard_number}</span>${qBean.question}</h5>
                        
                    </div>
                    <div class="col-5">
                    <p class="padleft"> ${qBean.paragraph}  </p>
                    </div>
                    <c:choose>
                        <c:when test="${qBean.formulFlag == '1'}">
                        <div class="col-1">${qBean.formulapoints}/${qBean.total_ques_points}</div>
                        </c:when>
                        <c:when test="${qBean.total_ques_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:when test="${qBean.max_question_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:otherwise>
                        <div class="col-1">${qBean.max_scored_points}/${qBean.max_question_points}</div>
                        </c:otherwise>
                        </c:choose>
                    
                    <div class="col-6">
                          <c:choose>
                            <c:when test="${qBean.comment_criteria=='null' || qBean.comment_criteria=='NULL'}">
                          <p></p>
                            </c:when> 
                            <c:otherwise>
                             <p class="padleft">${qBean.comment_criteria} 
                        </p>
                           </c:otherwise>
                            </c:choose> 
                    </div>
                </div>
            </div>
				</c:if>
				
				
				
				   <c:if test="${qBean.question_type=='Dependent Question With Set Of SubQuestions'}"> 
                  <div>
                    <div class="brder pb-1"></div>
                    <div class="row sub_question">
                        <div class="col-6">
                            <h5 class="mb-0"><span class="question-index" data-id="${qBean.standard_number}">${qBean.standard_number}</span>${qBean.question}</h5>
                        </div>
                        <div class="col-5 ty d-flex">    
                          <c:forEach var="qBean3" items="${qBean.selectedoptionsList}"> 
                                	   <input type="text" name="ans" class="input" value="${qBean3.selectedanswerid}" style="display: none"> 
                                	    <%-- <input type="text" name="ans" class="form-control" value="${qBean3.comment_criteria}" style="display: block">  --%>
                                	       
                          </c:forEach>     	   
                                	    <c:choose> 
                            <c:when test="${qBean3.selectedanswerid=='null'}">
                                	          
                            <ul class="custom-control radio">
                           
                             
                            
                               <%--  <li>
                                   <label class="mb-0 checkbox mainquestionchk"> 
                                        <input type="checkbox"  name="prodpresentation" value="${qBean3.paragraph}">
                                         <c:forTokens items="${mySplit}" delims="=" var="mySplit2">
                                        <span class="answerss" data-id="${qBean3.paragraph}"><c:out value="${qBean3.paragraph}"/> </span><br>
                                        
                                    </label>
                                      
                                </li> --%>
                                
                              
                            </ul>
                             </c:when> 
                            <c:otherwise> 
                            <ul class="custom-control radio">
                           
                            <c:forEach var="qBean2" items="${qBean.selectedoptionsList}"> 
                            
                                <li>
                                   <label class="mb-0 checkbox mainquestionchk"> 
                                        <input type="checkbox"  name="prodpresentation" value="${qBean2.subQuestion}">
                                         <c:set var = "num1" value="Yes"/>
                                         <c:set var = "num2" value="No"/>
                                         <c:set var = "num3" value="${qBean2.comment_criteria}"/>
                                         <c:set var = "para" value="${qBean2.paragraph}"/>
                                          <c:set var = "num5" value="${qBean2.date_code}"/>
                                          <c:set var = "datetime" value="${qBean2.time_code}"/>
                                          <c:set var = "SubAnswerType" value="Date & Time"/> 
                                       <c:choose>
                                    <c:when test="${qBean2.selectedanswerid eq num1}">
                                                  <span class="answerss" data-id="${qBean2.subQuestion }"><c:out value="${qBean2.subQuestion}"/><br>
                                                    <input type="date" name="ans" class="form-control" value="${qBean2.date_code}" style="display: none">  
                                                    <c:if test="${qBean2.paragraph ne 'null'}">                                           
                                                    Answer. ${qBean2.paragraph}<br>
                                                    </c:if> 
                                                    <c:if test="${qBean2.date_code ne 'null'}">                                           
                                                    Answer. ${qBean2.date_code}<br>
                                                    </c:if> 
                                                    <c:if test="${qBean2.time_code ne 'null'}">                                           
                                                    Answer. ${qBean2.time_code}<br>
                                                    </c:if> 
                                                  <c:if test="${not empty num3}">
                                                  Comment. ${qBean2.comment_criteria} 
                                                  </c:if></span><br>
                                    </c:when>
                                    <c:when test="${qBean2.selectedanswerid eq num2}">
                                                  <span class="answerss" data-id="${qBean2.subQuestion }"><c:out value="${qBean2.subQuestion}"/><br> 
                                                  <c:if test="${qBean2.paragraph ne 'null'}">                                           
                                                    Answer. ${qBean2.paragraph}<br>
                                                    </c:if> 
                                                    <c:if test="${qBean2.date_code ne 'null'}">                                           
                                                    Answer. ${qBean2.date_code}<br>
                                                    </c:if> 
                                                    <c:if test="${qBean2.time_code ne 'null'}">                                           
                                                    Answer. ${qBean2.time_code}<br>
                                                    </c:if>
                                                  <c:if test="${not empty num3}">
                                                  Comment. ${qBean2.comment_criteria} 
                                                  </c:if></span><br>
                                    </c:when>
                                 
                                    <c:otherwise>
                                                    <span class="answerss" data-id="${qBean2.subQuestion }"><c:out value="${qBean2.subQuestion}"/>
                                                    
                                                    <c:if test="${qBean2.paragraph ne 'null'}">                                           
                                                    Answer. ${qBean2.paragraph}<br>
                                                    </c:if> 
                                                    <c:if test="${qBean2.date_code ne 'null'}">                                           
                                                    Answer. ${qBean2.date_code}<br>
                                                    </c:if> 
                                                    <c:if test="${qBean2.time_code ne 'null'}">                                           
                                                    Answer. ${qBean2.time_code}<br>
                                                    </c:if>
                                                    <c:if test="${qBean2.subQuestionAnswerType ne SubAnswerType}">
                                                    <br>Answer. ${qBean2.selectedanswerid}
                                                    </c:if>
                                                    <c:if test="${not empty num3}">
                                    Comment. ${qBean2.comment_criteria}                                     
                                                  </c:if>  </span><br>
                                                    
                                    </c:otherwise>
                                    </c:choose>
                                    </label>
                                      
                                </li>
                                
                               </c:forEach>
                            </ul>
                             </c:otherwise>
                            
                            </c:choose> 
                            
                           
                        </div>
                       
                        <c:choose>
                        <c:when test="${qBean.formulFlag == '1'}">
                        <div class="col-1">${qBean.formulapoints}/${qBean.total_ques_points}</div>
                        </c:when>
                        <c:when test="${qBean.total_ques_points == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:when test="${qBean.sumOfQuesPoints == '0'}">
                         <div class="col-1">NA</div>
                        </c:when>
                        <c:otherwise>
                        <div class="col-1">${qBean.sumOfScoredPoints}/${qBean.sumOfQuesPoints}</div>
                        </c:otherwise>
                        </c:choose>
                        <div class="col-6">
                          <c:choose>
                            <c:when test="${qBean.comment_criteria=='null' || qBean.comment_criteria=='NULL'}">
                          <p></p>
                            </c:when>
                            <c:otherwise> 
                             <p class="padleft"> ${qBean.comment_criteria} 
                        </p>
                            </c:otherwise>
                            </c:choose> 
                    </div>
                    </div>
                </div>
                
                
				 
				 </c:if>  
				
               </c:forEach> 
                
            </div>
                </div>




                
            </div>
            </div>
            </div>
            
      
	<!-- wrapper ends -->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script0
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
	
  <script>
$("ul").each(function(){
	var ans=$(this).find(".answers").attr('value');
	//alert(ans);
	$(this).find('span').each(function(){
		var option=$(this).attr('data-id');
		//alert(option);
		
		if(ans==option){
			console.log(ans+"====="+option)
			$(this).parent().find('[type=radio]').prop('checked',true);
			validate_text();
		}
		else{
			console.log(ans+"== not ==="+option)
		}
		
	});
	
});
function validate_text(){
	
	$('.correctans').each(function(){
		   if(!$(this).is(':checked')){
		    $(this).next().find('#btn_ans').hide();
		}else{
		$(this).next().find('#btn_ans').show();
		}
		});
}

  var uniqueLi  = {};

$('.radionoanswer').each (function () {
	  var thisVal = $(this).find(".heading").text();
	  if ( !(thisVal in uniqueLi) ) {
		    uniqueLi[thisVal] = "";
		  } else {
			  $(this).find(".heading").remove();
		  }
}); 

  var uniqueLi  = {};

$('.online ').each (function () {
	  var thisVal = $(this).text();
	  if ( !(thisVal in uniqueLi) ) {
		    uniqueLi[thisVal] = "";
		  } else {
		    $(this).remove();
		  }
}); 
</script>
<script type="text/javascript">
 $('div.row.sub_question').each(function(){
                $(this).find('div.col-5.ty ').find("input[type='text']").each(function(){

                 if($(this).val().toLowerCase()==="yes"){
                console.log($(this).index());
                var i=$(this).index()+1;
                 $(this).parent().find("ul.custom-control li:nth-child("+i+")").find("input[type='checkbox']").prop('checked',true);
                 }else{
                var i=$(this).index()+1;
                 $(this).parent().find("ul.custom-control li:nth-child("+i+")").find("input[type='checkbox']").prop('checked',false);
                 }

                })
                });
 </script>  
<!-- <script>
 //var url="<%=dashboardURL%>performance/${outlet}/${dealer}/${month}/${year}/${brand}/1";

$( document ).ready(function() {
	   
	//alert(url);
	    kendo.drawing.drawDOM("#DivPrint", {
	        paperSize: "A2",
	        margin:{top:"1cm",bottom:"1cm"},
	        //margin: { left: "0cm", top: "3cm", right: "0cm", bottom: "0cm" },
	        forcePageBreak: ".page-break",
	        multiPage: true
	      })
	      .then(function(group) {
	        kendo.drawing.pdf.saveAs(group, "Outlet_Level_report.pdf");
	      });
	    setTimeout(function(){ 
	    	// window.location.replace("<%=dashboardURL%>performance/${outlet}/${dealer}/${month}/${year}/${brand}/1");
			 }, 5000); 
		  
	
});


</script> -->
 <!-- <script>
$("ul").each(function(){
	var ans=$(this).find(".answers").attr('value');
	//alert(ans);
	$(this).find('span').each(function(){
		var option=$(this).attr('data-id');
		//alert(option);
		
		if(ans==option){
			console.log(ans+"====="+option)
			$(this).parent().find('[type=radio]').prop('checked',true);
			validate_text();
		}
		else{
			console.log(ans+"== not ==="+option)
		}
		
	});
	
});


function validate_text(){
	
	$('.correctans').each(function(){
		   if(!$(this).is(':checked')){
		    $(this).next().find('#btn_ans').hide();
		}else{
		$(this).next().find('#btn_ans').show();
		}
		});
}

  var uniqueLi  = {};

$('.radionoanswer').each (function () {
	  var thisVal = $(this).find(".heading").text();
	  if ( !(thisVal in uniqueLi) ) {
		    uniqueLi[thisVal] = "";
		  } else {
			  $(this).find(".heading").remove();
		  }
}); 

  var uniqueLi  = {};

$('.online ').each (function () {
	  var thisVal = $(this).text();
	  if ( !(thisVal in uniqueLi) ) {
		    uniqueLi[thisVal] = "";
		  } else {
		    $(this).remove();
		  }
}); 
</script>
<script type="text/javascript">


$('div.row.sub_question').each(function(){
	$(this).find('div.col-5.ty ').find("input[type='text']").each(function(){

	 if($(this).val().toLowerCase()==="Yes"){
	console.log($(this).index());
	var i=$(this).index()+1;
	 $(this).parent().find("ul.custom-control li:nth-child("+i+")").find("input[type='checkbox']").prop('checked',true);
	 }else{
	var i=$(this).index()+1;
	 $(this).parent().find("ul.custom-control li:nth-child("+i+")").find("input[type='checkbox']").prop('checked',false);
	 }

	})
	});
</script>  -->
<script>
$( document ).ready(function() {
	   
	
	    kendo.drawing.drawDOM("#DivPrint", {
	        paperSize: "A2",
	        margin:{top:"1cm",bottom:"1cm"},
	        //margin: { left: "0cm", top: "3cm", right: "0cm", bottom: "0cm" },
	        forcePageBreak: ".page-break",
	        multiPage: true
	      })
	      .then(function(group) {
	        kendo.drawing.pdf.saveAs(group, "Outlet_Level_report.pdf");
	      });
	         setTimeout(function(){
			 window.location.replace("<%=dashboardURL%>performance/${outlet}/${dealer}/${month}/${yearr}/${brand}/1"); 
			 }, 5000);     
	   
});
</script>
</body>
</html>
