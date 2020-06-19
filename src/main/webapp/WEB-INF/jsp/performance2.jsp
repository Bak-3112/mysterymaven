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


</head>
  <body>
    <!-- wrapper starts -->
    <div class="wrapper" >
      <div class="container">
            <header>
                    <nav class="navbar navbar-expand-lg navbar-light ">
                        <a class="navbar-brand" href="#">BMW GROUP - Sales Mystery Shopping 2019</a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                          <span class="navbar-toggler-icon"></span>
                        </button>
                      
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                          <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                              <a class="nav-link" href="#"> <span class="sr-only">(current)</span></a>
                            </li>
                            <li class="nav-item">
                              <a class="nav-link" href="#"></a>
                            </li>
                    
                            <li class="nav-item">
                              <a class="nav-link " href="#" tabindex="-1" aria-disabled="true"></a>
                            </li>
                          </ul>
                          <div class="form-inline my-2 my-lg-0">
                            <figure>
                                <img src="<%=UI_PATH%>/assets1/images/bmwgroup-logo.PNG">
                            </figure>
                            <figure>
                                <img src="<%=UI_PATH%>/assets1/images/nextlogo.PNG">
                            </figure>
                            <figure>
                                <img src="<%=UI_PATH%>/assets1/images/bmw-logo.png">
                            </figure>
                            <figure>
                                <img src="<%=UI_PATH%>/assets1/images/mini-logo.PNG">
                            </figure> 
                          </div>
                   </div>
                      </nav>
                </header>
        <table class="table table_secondary">
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
        </table>
           <!-- <table class="table table_secondary">
          <thead>
            <tr class="bg_secondary">
              <th class="text-center bg-title bg_secondary" colspan="12">FAILED QUESTIONS</th>
            </tr>
            <tr  class="bg-title-2">
              <th class="text-center" colspan="5"></th>
                <td>RESULT</td>
              
            </tr>
          </thead>
          <tbody>
            
            <tr>
              <td scope="row">5.2</td>
              <td colspan="4">Were you contacted in the corresponding time frame after creating the sales offer?</td>
                <td>0/30</td>
             
            </tr>
            <tr>
              <td scope="row">5.6</td>
              <td colspan="4"> Within the follow-up call, did the retailer staff ask about the current status of your decision?</td>
                <td>0/15</td>
             
            </tr>
           
            
           
          </tbody>
        </table> -->
        <input type="hidden" name="NonOsc_shopper_id" value="${NonOsc_shopper_id}">
        <input type="hidden" name="osc_shopper_id" value="${osc_shopper_id}">
         <input type="hidden" name="dealer" value="${dealer}">
          <p><a href="<%=dashboardURL%>performance/${outlet}/${dealer}/${month}/${yearr}/${brand}/1" class="btn btn-link">Prev</a> <a href="<%=dashboardURL%>performance3/${NonOsc_shopper_id}/${osc_shopper_id}/${dealer}/1" class="btn btn-link">Next</a></p>
      </div>
    </div>
    <!-- wrapper ends -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    <script>
      $(document).ready(function() {
      
      	
      });
    </script>
  </body>
</html>