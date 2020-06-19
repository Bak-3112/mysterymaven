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
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="format-detection" content="telephone=no">
<title>HTML- 5</title>

<!-- SET: FAVICON -->
<link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">

<!-- SET: STYLESHEET -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="<%=UI_PATH%>/assets1/css/bootstrap4hack.css">
<link rel="stylesheet" type="text/css" href="<%=UI_PATH%>/assets1/css/style.css">

<style>

#appendRow{
    float: right;
    margin-right: 93px;
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
	<div class="wrapper" >
      
        <div class="container">
              <header>
                    <nav class="navbar navbar-expand-lg navbar-light ">
                    <a href="<%=dashboardURL %>Reports" class="previous">&laquo;  Back To Reports</a>
                        <a class="navbar-brand" href="#">BMW GROUP - Sales Mystery Shopping 2018</a>
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
              
               		 <h4 class="bg-lite-blue text-white p-2 heading">${qBean.subsection_name}<span class="float-right"> ${qBean.percentage}</span></h4>
                		
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
            <input id="nonOscId" value="${NoscShopper_id}" type="hidden">
             <input id="oscId" value="${shopper_id}" type="hidden">
             <input id="dealer" value="${dealer}" type="hidden">
            <div id="appendRow" class="pagination">
           </div>
             <!-- <p><a href="page2.html" class="btn btn-link">Prev</a> <a href="page4.html" class="btn btn-link">Next</a></p>  -->
         </div>
        
    </div>
    
<!-- wrapper ends -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>


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
</script>
<script>
window.onload = getIdss;     


function getIdss(){
    var nonOscid=$('#nonOscId').val();
    var oscId=$('#oscId').val();
    var dealer=$('#dealer').val();

    $.ajax({
           url:   "<%=request.getContextPath()%>/getIdss" ,  
          type: "GET", 
            data: {"nonOscId":nonOscid, "oscId":oscId},
          
          success: function(response)
        
                      {
            $.each(response,function(k,v){
           //   alert(v.questionCount);
              var as= v.questionCount/ 5;
             // alert(as);
              var myNumber = as;
              var integerPart = parseInt(myNumber);
             // alert(integerPart);
              var decimalPart = (myNumber - integerPart).toFixed(2);
//                  alert(decimalPart[2].toString());
              var finall="";
              if(decimalPart[2].toString()=="0")
                {
                finall=integerPart;
                }
              else
                {
                finall=integerPart + 1;
                }
            //  alert(finall);
              //document.getElementById("count").value=finall;
             // var i=1;
              var page_index= parseInt($("#pid").val());
              var class_active;
              $("#appendRow").append("<li class='page-item'><a class='page-link page-prev'>Prev</a></li>");
              for(var i=1;i!=finall+1;i++)
                {
                if( i == page_index){
                	class_active ="active"
                	
                }
                else{
                	class_active =""
                }
                $("#appendRow").append("<li class='page-item "+class_active+"'><a class='page-link ' href='<%=dashboardURL%>performance3/"+nonOscid+"/"+oscId+"/"+dealer+"/"+i+"'>"+i+"</a></li>");
                
                }
              $("#appendRow").append("<li class='page-item'><a class='page-link page-next'> Next</a></li>");
              $("ul").each(function(){
                var ans=$(this).find(".answers").attr('value');
                $(this).find('span').each(function(){
                  var option=$(this).attr('data-id');
                  if(ans==option){
                    $(this).parent().find('[type=radio]').prop('checked',true);
                    
                  }
                  
                });
                
              });
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
              

              /*                     alert(aa.toString()[0]);
              alert(aa.toString()[2]);
*/                  });
                  }
          });
}

$(document).on('click','.page-next',function(e){
	var page_url = $('.pagination .page-item.active').next().find('a').attr('href');
	if(page_url==undefined)
	{
		
	}else{
		window.location.href = page_url; 
		
	}
})
$(document).on('click','.page-prev',function(e){
	
	var page_url = $('.pagination .page-item.active').prev().find('a').attr('href');
	if(page_url==undefined)
	{
		
	}else{
		window.location.href = page_url;
	}
	
})

/* $('.row.sub_question').each(function(){
	$(this).find(".input").css("display","none");
	$(this).siblings(".brder.pb-1").css("display","none");
	});
 */
 
/*  $(".radionoanswer").each(function(){
	if($(this).find(".answers").val()===""){
	$(this).find(".radionoanswer").css("display","none");
	$(this).find(".brder.pb-1").css("display","none");
	}
 }); */
</script>


</body>
</html>
