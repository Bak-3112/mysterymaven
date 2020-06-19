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
      <title>MYS</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
      <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
      <!-- App favicon -->
      <link rel="shortcut icon"
         href="<%=UI_PATH%>/design/images/DQI-icon.png">
      <link rel='stylesheet' href='<%=UI_PATH%>/design/css/bootstrap.min.css'>
      <link rel="stylesheet" type="text/css" href="<%=UI_PATH%>/design/css/form-validation.css">
      <link rel="stylesheet" type="text/css" href="<%=UI_PATH%>/design/css/time.css">
      <link rel="stylesheet" href="<%=UI_PATH%>/design/css/custom.css" type="text/css">
      <style type="text/css">
      .position-relative{position:relative;}
   /*   .active a {background-image:url(<%=UI_PATH%>/design//images/right-arrow.png);background-size: 40px 14px;position: absolute;
    left: 0px;
    background-repeat: no-repeat;top:0;} */
      .active a{
 
  width: 100%;

  text-shadow:    -1px 1px 0 #ddd,
		-2px 2px 0 #c8c8c8,
		-3px 3px 0 #ccc,
		-4px 4px 0 #b8b8b8,
		-4px 4px 0 #bbb,
		0px 1px 1px rgba(0,0,0,.4),
		0px 2px 2px rgba(0,0,0,.3),
		-1px 3px 3px rgba(0,0,0,.2),
		-1px 5px 5px rgba(0,0,0,.1),
		-2px 8px 8px rgba(0,0,0,.1),
		-2px 13px 13px rgba(0,0,0,.1)
}
         .page {
         display: none;
         }
         .page-active {
         display: block;
         }
         .parsley-required{
         list-style: none;
         color: red;
         }
         .bs-wizard-stepnum a{color:#000000;font-size:16px;}
       .active a{color:#1c69d4; }
       .border-right{border-right:1px solid #000000;}
       .li-padding{padding:10px 0 10px 0;} 
       .li-padding a{font-family: BMWGroup-Regular!important;font-weight: 600;}
      </style>
   </head>
   <body class="bodysection">
      <!-- <div class="text-center h-txt">
         <h4>BMW GROUP Sales Mystery Shopping - 2018</h4>
         <p>Sales and Online Integrated Questionnaire</p>
         </div> -->
      <header class="header clearfix">
         <div class="logo pull-left">
            <a>
            <img src="<%=UI_PATH%>/design/images/bmw.png"  width="50" height="50" alt="logo">
            </a>
            <a>
            <img src="<%=UI_PATH%>/design/images/mini-logo.svg" width="128px"    alt="logo">
            </a>
         </div>
         <div class="greetingtext pull-right">
            <span>
               Welcome <!-- Mystery Shopper  --><b>${shoopername }</b>
            </span>
            <a href="#"><img src="<%=UI_PATH%>/resources/design/images/home.png" width="16" height="16"></a>
         </div>
      </header>
      <div class="">
      <div class="quizwrap clearfix row" id="quizwrap">
       
            <!-- <div class="col-xs-3 bs-wizard-step disabled" style="width: 16.66%;">
               complete
               <div class="text-center bs-wizard-stepnum" style="word-break:break-all">Sales Consultation</div>
               <div class="progress">
                  <div class="progress-bar"></div>
               </div>
               <a href="#" class="bs-wizard-dot"></a>
               <div class="bs-wizard-info text-center"></div>
            </div>
            <div class="col-xs-3 bs-wizard-step disabled" style="width: 16.66%;">
               active
               <div class="text-center bs-wizard-stepnum" style="word-break:break-all">Test Drive</div>
               <div class="progress">
                  <div class="progress-bar"></div>
               </div>
               <a href="#" class="bs-wizard-dot"></a>
               <div class="bs-wizard-info text-center"></div>
            </div>
            <div class="col-xs-3 bs-wizard-step disabled" style="width: 16.66%;">
               active
               <div class="text-center bs-wizard-stepnum" style="word-break:break-all">Offer</div>
               <div class="progress">
                  <div class="progress-bar"></div>
               </div>
               <a href="#" class="bs-wizard-dot"></a>
               <div class="bs-wizard-info text-center"></div>
            </div>
            <div class="col-xs-3 bs-wizard-step disabled" style="width: 16.66%;">
               active
               <div class="text-center bs-wizard-stepnum" style="word-break:break-all">Follow-up</div>
               <div class="progress">
                  <div class="progress-bar"></div>
               </div>
               <a href="#" class="bs-wizard-dot"></a>
               <div class="bs-wizard-info text-center"></div>
            </div>
            <div class="col-xs-3 bs-wizard-step disabled" style="width: 16.66%;">
               active
               <div class="text-center bs-wizard-stepnum" style="word-break:break-all">Customer treatment</div>
               <div class="progress">
                  <div class="progress-bar"></div>
               </div>
               <a href="#" class="bs-wizard-dot"></a>
               <div class="bs-wizard-info text-center"></div>
            </div>
         </div> -->
        <div class="col-md-9 border-right" >
         <form  id="demo-form" data-parsley-validate="" enctype="multipart/form-data" method="post">
            <c:forEach  var="mBean" items="${allquestionsList}" varStatus="status">
              
               <c:choose>
                  <c:when test="${mBean.mainquestiontype=='Main Question' || mBean.mainquestiontype=='Dependent Question'}">
                     <div class="card">
                     
                    
                        <input type="hidden" value="${mBean.mainquestiontype}" name="question_type" id="question_type">
                        <input type="hidden" value="${mBean.standard_number}" name="standard_number">
                        <input type="hidden" value="${mBean.question_id }" name="question_id">
                        <input type="hidden" value="${mBean.mainquestion }" name="questions">
                            <input type="hidden" value="${mBean.section_id }" name="section_id">
                        <input type="hidden" value="${mBean.section_name }" name="section_name">
                            <input type="hidden" value="${mBean.subsection_id }" name="subsection_id">
                        <input type="hidden" value="${mBean.subsection_name }" name="subsection_name">
                        
                        <input type="hidden" value="${mBean.mainanswertype}" name="answer_type">
                        <input type="hidden" value="${mBean.standard_number_sequence}" name="standard_number_sequence">
                        <input type="hidden" value="${mBean.question_code}" name="question_code">
                        <input type="hidden" value="${mBean.formula_flag}" name="formula_flag">
                        <input type="hidden" value="${mBean.question_points}" name="question_points">
                        <input type="hidden" value="${mBean.super_question_id}" name="super_question_id" id="super_question_id">
                        <input type="hidden" value="${mBean.super_question_answer}" name="super_question_answer" id="super_question_answer">
                        <input type="hidden" value="${mBean.super_question_selected_id}" name="super_question_selected_id" id="super_question_selected_id">
                        <input type="hidden" value="${mBean.super_selected_qid}" name="Super_selected_qid" id="Super_selected_qid">
                         <input type="hidden" value="${mBean. question_optiontype}" name=" questionoptiontype" id="questionoptiontype">
                         <input type="hidden" value="" name="question_optiontype" id="question_optiontype">
                                   <ol class="questions">
                                    <li style="list-style-type: none;">
         	<div class="position-relative">
               <div class="text-center bs-wizard-stepnum  li-padding active " style="word-break:break-all;text-align: left;"><a href="#">${mBean.section_name } / ${mBean.subsection_name }</a></div>
            </div>
                     </li>
                                 </ol>             
                                 
                        <c:choose>
                           <c:when test="${mBean.mainanswertype=='Select/List'}">
                          
                              <div class="questions">
                             
                                 <ol>
                                   
                                    <li>
                                    
                                       <span class="q-number">${mBean.standard_number}</span>
                                       ${mBean.mainquestion}  
                                    </li>
                                 </ol>
                              </div>
                              <div class="clearfix"></div>
                              <br>
                              <div class="check-row">
                                 <c:forEach  var="count1" items="${mBean.optionslist}" varStatus="optioncount">
                                  <div class="col-md-12">
                                  <div class="col-md-6">
                                       <label class="checkbox-label">
                                          <input  type="radio" id="option_comment" data-id="${count1.optioncomment}" required="required" name="optionid" value="${count1.optionid}" 
                                          <c:if test = "${count1.optionid  == count1.selected_option_id }">checked </c:if>>
                                          <span>${count1.options }</span>	<input type="hidden" value="${count1.options }" name="options">
                                       </label>
                                    </div>
                                     <div class="col-md-4" style="display:<c:if test = "${count1.optionid  != count1.selected_option_id || count1.optioncomment !='Yes'}">none</c:if>" id="optioncomment"> 
                                    
										<input type="text" disabled  <c:if test = "${count1.optionid  == count1.selected_option_id && count1.optioncomment =='Yes'}">required</c:if> class="form-control"  value="<c:if test = "${count1.optionid  == count1.selected_option_id && count1.optioncomment =='Yes'}">${count1.selected_option_comment}</c:if>" name="option_comment">   
                                 
                                    </div>
                                    </div>  <br>
                                 </c:forEach>
                                 <br>
                                 <c:if test = "${mBean.mainquestionComment == 'yes-mandatory'  }">
                                    <div class="col-md-6 txt-area ">
                                       <textarea class="form-control" required="required" placeholder="Write your response here"   required name="main_ques_comment">${mBean.selected_main_comment }</textarea>
                                    </div>
                                    <div class="col-md-6 txt-area "></div>
                                 </c:if>
                                 <c:if test = "${mBean.mainquestionComment == 'yes-optional' }">
                                    <div class="col-md-6 txt-area ">
                                       <textarea class="form-control"  placeholder="Write your response here" required name="main_ques_comment">${mBean.selected_main_comment }</textarea>
                                    </div>
                                    <div class="col-md-6 txt-area "></div>
                                 </c:if>
                              </div>
                              <br><br>
                           </c:when>
                           <c:when test="${mBean.mainanswertype=='Date & Time'}">
                              <div class="questions">
                                 <ol>
                                    <li>
                                       <span class="q-number">${mBean.standard_number}</span>
                                       ${mBean.mainquestion} 
                                    </li>
                                 </ol>
                              </div>
                              <div class="clearfix"></div>
                              <br>
                              <div class="row datetime-row">
                                 <div class="col-md-3 col-sm-6 mrg-bttm">
                                    <div class="input-group date datepicker" id="datepicker">
                                       <input class="form-control" required name="answerDate" value="${mBean.selected_date }"><span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                                    </div>
                                 </div>
                                 <div class="col-md-3 col-sm-6  mrg-bttm">
                                    <div class="input-group time timepicker1" id="timepicker1" >
                                       <input class="form-control" required name="answerTime" value="${mBean.selected_time }">
                                       <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                    </div>
                                 </div>
                              </div>
                              <br>
                              <c:if test = "${mBean.mainquestionComment == 'yes-mandatory' }">
                                 <div class="col-md-6 txt-area ">
                                    <textarea class="form-control" required="required" placeholder="Write your response here" required name="main_ques_comment">${mBean.selected_main_comment }</textarea>
                                 </div>
                                 <div class="col-md-6 txt-area "></div>
                              </c:if>
                              <c:if test = "${mBean.mainquestionComment == 'yes-optional' }">
                                 <div class="col-md-6 txt-area ">
                                    <textarea class="form-control"  placeholder="Write your response here" required name="main_ques_comment">${mBean.selected_main_comment }</textarea>
                                 </div>
                                 <div class="col-md-6 txt-area "></div>
                              </c:if>
                           </c:when>
                           <c:when test="${mBean.mainanswertype=='Date'}">
                              <div class="questions">
                                 <ol>
                                    <li>
                                       <span class="q-number">${mBean.standard_number}</span>
                                       ${mBean.mainquestion} 
                                    </li>
                                 </ol>
                              </div>
                              <div class="clearfix"></div>
                              <br>
                              <div class="row datetime-row">
                                 <div class="col-md-3 col-sm-6 mrg-bttm">
                                    <div class="input-group date datepicker" id="datepicker">
                                       <input class="form-control" value="${mBean.selected_date }" required name="answerDate"><span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                                    </div>
                                 </div>
                              </div>
                              <br><br>
                              <br>
                              <c:if test = "${mBean.mainquestionComment == 'yes-mandatory' }">
                                 <div class="col-md-6 txt-area ">
                                    <textarea class="form-control" required="required" placeholder="Write your response here" required name="main_ques_comment">${mBean.selected_main_comment }</textarea>
                                 </div>
                                 <div class="col-md-6 txt-area "></div>
                              </c:if>
                              <c:if test = "${mBean.mainquestionComment == 'yes-optional' }">
                                 <div class="col-md-6 txt-area ">
                                    <textarea class="form-control"  placeholder="Write your response here" required name="main_ques_comment">${mBean.selected_main_comment }</textarea>
                                 </div>
                                 <div class="col-md-6 txt-area "></div>
                              </c:if>
                           </c:when>
                           <c:otherwise>
                              <div class="questions">
                                 <ol>
                                    <li>
                                       <span class="q-number">${mBean.standard_number}</span>
                                       ${mBean.mainquestion} 
                                    </li>
                                 </ol>
                              </div>
                              <div class="clearfix"></div>
                              <div class="comment-row clearfix">
                                 <div class="col-md-6 txt-area ">
                                    <textarea class="form-control" required="required" placeholder="Write your response here" required name="answerParagraph">${mBean.selected_pagaragraph }</textarea>
                                 </div>
                              </div>
                              <br><br>
                           </c:otherwise>
                        </c:choose>
                     </div>
                  </c:when>
                  <c:otherwise>
                     <div class="card">
                        <input type="hidden" value="${mBean.mainquestiontype}" name="question_type" id="question_type">
                        <input type="hidden" value="${mBean.standard_number}" name="standard_number">
                        <input type="hidden" value="${mBean.question_id }" name="question_id">
                        <input type="hidden" value="${mBean.mainquestion }" name="questions">
                        <input type="hidden" value="${mBean.subanswertype}" name="answer_type">
                        <input type="hidden" value="${mBean.standard_number_sequence}" name="standard_number_sequence">
                        <input type="hidden" value="${mBean.question_code}" name="question_code">
                        <input type="hidden" value="${mBean.question_points}" name="question_points">
                        <input type="hidden" value="${mBean.formula_flag}" name="formula_flag">
                        <input type="hidden" value="${mBean.super_question_id}" name="super_question_id" id="super_question_id">
                        <input type="hidden" value="${mBean.super_question_answer}" name="super_question_answer" id="super_question_answer">
                        <input type="hidden" value="${mBean.super_question_selected_id}" name="super_question_selected_id" id="super_question_selected_id">
                        <input type="hidden" value="${mBean.super_selected_qid}" name="Super_selected_qid" id="Super_selected_qid">
                        <input type="hidden" value="${mBean.section_id }" name="section_id">
                        <input type="hidden" value="${mBean.section_name }" name="section_name">
                            <input type="hidden" value="${mBean.subsection_id }" name="subsection_id">
                        <input type="hidden" value="${mBean.subsection_name }" name="subsection_name">
                         <input type="hidden" value="${mBean. question_optiontype}" name=" questionoptiontype" id="questionoptiontype">
                         <input type="hidden" value="" name="question_optiontype" id="question_optiontype">
                        
                        <ol class="questions">
                                    <li style="list-style-type: none;">
         	<div class="position-relative">
               <div class="text-center bs-wizard-stepnum  li-padding active " style="word-break:break-all;text-align: left;"><a href="#">${mBean.section_name } / ${mBean.subsection_name }</a></div>
            </div>
                     </li>
                                 </ol>    
                       <div class="questions">
                                 <ol>
                                    <li>
                                       <span class="q-number">${mBean.standard_number}</span>
                                       ${mBean.mainquestion}
                                    </li>
                                 </ol>
                              </div>
                           
 						
                              
                              <div>
                                 <c:forEach  var="count" items="${subquestionList.get(status.index)}" varStatus="status1" >
                                    <div class="col-md-6  mrg-top">
                                       <input type="hidden" name="answerdata[${status1.count  }].subquestionid" value="${count.subquestionid}">
                                       <input type="hidden" name="answerdata[${status1.count  }].subquestion" value="${count.subquestion}">
                                       <ul class="rmve-dot">
                                       
                                          <li ><c:forEach begin="1" end="${status1.index / 26 + 1}">&#${status1.index % 26 + 97};</c:forEach>.&nbsp;${count.subquestion}</li>
                                       </ul>
                                    </div>
                                    <input type="hidden" value="${count.answer_type}" name="answerdata[${status1.count  }].answer_type">
                                    <c:choose>
                           			<c:when test="${count.answer_type=='Select/List'}">
                                    <div class="col-md-3 col-sm-6 mrg-bttm mrg-top select-wrap">
                                       <select name="answerdata[${status1.count  }].optionid"  id="subquestionanswer${status1.count }" onchange="callcomment(${status1.count})"   class="form-control" required>
                                          <option value="">Select Answer</option>
                                          <c:forEach  var="count1" items="${count.optionslist}">
                                             <option value="${count1.optionid }" data-optioncomment="${count1.optioncomment }" 
                                             <c:if test = "${count1.optionid  == count1.selected_option_id }">selected</c:if>
                                             >${count1.options }</option>
                                          </c:forEach>
                                       </select>
                                       <div class="down-arrw1">
                                          <i class="fa fa-chevron-down"></i>
                                       </div>
                                    </div>
                                     </c:when>
                           <c:when test="${count.answer_type=='Paragraph'}">
								<div class="comment-row clearfix">
                                 <div class="col-md-3 txt-area ">
                                    <textarea class="form-control" required="required" placeholder="Write your response here" required name="answerdata[${status1.count  }].answerParagraph">${count.selected_pagaragraph }</textarea>
                                 </div>
                              </div>
                           </c:when>
                             <c:when test="${count.answer_type=='Date & Time'}">
								<div class="comment-row clearfix">
                                  <div class="row datetime-row">
                                 <div class="col-md-3 col-sm-6 mrg-bttm">
                                    <div class="input-group date datepicker" id="datepicker">
                                       <input class="form-control" required name="answerdata[${status1.count  }].answerDate" value="${count.selected_date }"><span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                                    </div>
                                 </div>
                                 <div class="col-md-3 col-sm-6  mrg-bttm">
                                    <div class="input-group time timepicker1" id="timepicker1" >
                                       <input class="form-control" required name="answerdata[${status1.count  }].answerTime" value="${count.selected_time }">
                                       <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                    </div>
                                 </div>
                              </div>
                              </div>
                           </c:when>
                            <c:when test="${count.answer_type=='Date'}">
								<div class="comment-row clearfix">
                                  <div class="row datetime-row">
                                 <div class="col-md-3 col-sm-6 mrg-bttm">
                                    <div class="input-group date datepicker" id="datepicker">
                                       <input class="form-control" required name="answerdata[${status1.count  }].answerDate" value="${count.selected_date }"><span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                                    </div>
                                 </div>
                              </div>
                              </div>
                           </c:when>
                        </c:choose>
                                 <c:if test = "${count.selected_option_comment  != null }"> 
                                     <div class="col-md-3 col-sm-6 commentclass1 comment-area mrg-top" id="subquestioncomment${status1.count }">
                                       <textarea class="form-control "  id="subquestioncomments${status1.count }" name="answerdata[${status1.count }].option_comment" placeholder="Comment here ">${count.selected_option_comment }</textarea>
                                    </div>
                    			</c:if>            
                    			 <c:if test = "${count.selected_option_comment  == null }"> 
                                    <div class="col-md-3 col-sm-6 commentclass1 comment-area mrg-top" id="subquestioncomment${status1.count }" style="display:none">
                                       <textarea class="form-control "  id="subquestioncomments${status1.count }"   name="answerdata[${status1.count }].option_comment" placeholder="Comment here"></textarea>
                                    </div>
                                    </c:if>
                                    <div class="clearfix"></div>
                                      <br>
                                 <c:if test = "${mBean.mainquestionComment == 'yes-mandatory'  }">
                                    <div class="col-md-6 txt-area ">
                                       <textarea class="form-control" required="required" placeholder="Write your response here"   required name="main_ques_comment">${mBean.selected_main_comment }</textarea>
                                    </div>
                                    <div class="col-md-6 txt-area "></div>
                                 </c:if>
                                 <c:if test = "${mBean.mainquestionComment == 'yes-optional' }">
                                    <div class="col-md-6 txt-area ">
                                       <textarea class="form-control"  placeholder="Write your response here" required name="main_ques_comment">${mBean.selected_main_comment }</textarea>
                                    </div>
                                    <div class="col-md-6 txt-area "></div>
                                 </c:if>
                                 </c:forEach>
                              </div>
                          
			
                        
                     </div>
                  </c:otherwise>
               </c:choose>
            
            <br>
            <div id="appendRow">
            </div>
            <input id="storevisitid" value="${sid}" type="hidden">
            <input id="bid" value="${bid}" type="hidden">
            <input id="year_applied" value="${year_applied}" type="hidden">
            <input id="count" value="" type="hidden">
            <input id="mode" value="${modeofcontact}" type="hidden">
             <input id="finalpage" value="" type="hidden" name="finalpage">
            
            <div class="btnwrap center">
               <a class="btn btn-grey mrg-top Previous" data-id="" >Previous</a> <input type="submit"  class="btn btn-info mrg-top Next" value="Next" data-id=""/>
                <c:if test = "${mBean.question_optiontype =='Optional' }"><input type="submit" <c:if test = "${mBean.mandatory_optional_status =='answered' }"> disabled</c:if> class="btn btn-info mrg-top skip" value="Skip" data-id=""/></c:if>
               <input type="hidden" id="pageCountVal"/>
               <input type="hidden" value="${pagecount }" name="count" id="pagecount"/>
            </div>
            <ul id="pagination-demo" class="pagination-lg pull-right"></ul>
            </c:forEach>
         </form>
        </div>
        <!--  -->
         <div class="col-md-3" style="border-bottom:0;">
         <c:forEach  var="mBean" items="${allquestionsList}" varStatus="status">
       <c:forEach  var="count" items="${mBean.sectionsList}" varStatus="status">
         	<div class="position-relative" >
               <div class="text-center bs-wizard-stepnum  li-padding <c:if test = '${count.subsection_id == mBean.subsection_id}'>active</c:if> "  style="word-break:break-all"><a href="#">${count.subsection_name}</a></div>
            </div>
            </c:forEach>
           </c:forEach> 
          
         </div>
     
             </div>
      </div>
      <script src="<%=UI_PATH%>/design/js/jquery.min.js"></script>
      <script src="<%=UI_PATH%>/design/js/bootstrap.min.js"></script>
      <script src='<%=UI_PATH%>/design/js/date&time/jquery-2.2.4.min.js'></script>
      <script src='<%=UI_PATH%>/design/js/date&time/moment.min.js'></script>
      <script src='<%=UI_PATH%>/design/js/date&time/bootstrap-datetimepicker.min.js'></script>
      <script src="<%=UI_PATH%>/design/js/date&time/date&time.js"></script>
      <script src="<%=UI_PATH%>/design/js/form-validation/jquery.validate.min.js"></script>
      <script src="<%=UI_PATH%>/design/js/form-validation/jquery.formtowizard.js"></script>
      <script src="<%=UI_PATH%>/design/js/form-validation/main.js"></script>
      <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
      <script src="<%=UI_PATH%>/design/js/range-slider.js"></script>
      <script src="<%=UI_PATH%>/design/js/formValidation.js"></script>
      <script src="<%=UI_PATH%>/design/js/formValidationScript.js"></script>
      <script type="text/javascript" src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>
      <script type="text/javascript">
         function callcomment(index){
          if($("#subquestionanswer"+index).find(':selected').attr('data-optioncomment')=="yes" || $("#subquestionanswer"+index).find(':selected').attr('data-optioncomment')=="Yes"){
           $("#subquestioncomment"+index).css("display", "block");
           $("#subquestioncomments"+index).attr("required", true);
           $("#subquestioncomments"+index).val('');
          }else{
           $("#subquestioncomment"+index).css("display", "none");
           $("#subquestioncomments"+index).attr("required", false);
           $("#subquestioncomments"+index).val('');
          }
          
         }
      </script>
      
      
      
      
       <script type="text/javascript">
       

       $("input[type=radio]#option_comment").on("change",function(){
                        
                         var selected=$(this).val();
                          $("input[type=radio]#option_comment").each(function(){ 
                               if(selected==$(this).val() && $(this).attr('data-id')=='Yes'){
                            	   
                                           $(this).parent().parent().next('#optioncomment').css('display','block');
                                           $(this).parent().parent().next('#optioncomment').find('input[name="option_comment"]').attr('disabled',false);
                                           $(this).parent().parent().next('#optioncomment').find('input[name="option_comment"]').attr('required',true);
                                        
                                      }else{
                                    	  $(this).parent().parent().next('#optioncomment').find('input[name="option_comment"]').attr('disabled',true);
                                    	  $(this).parent().parent().next('#optioncomment').find('input[name="option_comment"]').attr('required',false);
                                         $(this).parent().parent().next('#optioncomment').css('display','none');
                                         
                                        }
                             })
                           
                });
       
       
       
        
       
       
  $("input[type=radio]#option_comment").each(function(){ 
    	   if ($(this).is(":checked")) {
    		 
    		   $(this).parent().parent().next('#optioncomment').find('input[name="option_comment"]').attr('disabled',false);
    		   }else{
            	    $(this).parent().parent().next('#optioncomment').find('input[name="option_comment"]').attr('disabled',true);
               }
    	   if ($(this).is(":checked") && $(this).attr('data-id')=='Yes') {
      		 
    		   $(this).parent().parent().next('#optioncomment').find('input[name="option_comment"]').attr('required',true);
    		   }else{
            	    $(this).parent().parent().next('#optioncomment').find('input[name="option_comment"]').attr('required',false);
               }
         })    
           
         function callselectcomment(index){
          if($("#option_comment"+index).attr('data-id')=="yes" || $("#option_comment"+index).attr('data-id')=="Yes"){
           $("#optioncomment"+index).css("display", "block");
           $("#optioncomment"+index).attr("required", true);
          }else{
           $("#optioncomment"+index).css("display", "none");
           $("#optioncomment"+index).attr("required", false);
          }
          
         }
         
       $("input[type=radio]").each(function(){
    	   $(this).on("change",function(){
    		 if($(this).attr("data-id")=="yes"||"Yes"){
    			 //alert("yes")
    		 }else{
    			 //alert("no")
    		 }
    	   });
       });
         
      </script>
      
      
      <script type="text/javascript"></script>
      <script>
        var counts, dependentQues = false;
        var sid=$('#storevisitid').val();
        var bid=$('#bid').val();
        window.onload = getIds;     
        
        function getIds(){
            
            var mode=$('#mode').val();
            var year_applied=$('#year_applied').val();
            $.ajax({
                    url: "<%=dashboardURL%>getIds",
                    type: "GET", 
                    data: {"sid":sid,'bid':bid,'mode':mode,'year_applied':year_applied},
                    success: function(response)
                                {
                        counts=response.count;
                    $("#count").val(counts);
                        
                        var as= response.count / 2;
                        var myNumber = as;
                        var integerPart = parseInt(myNumber);
                        var decimalPart = (myNumber - integerPart).toFixed(2);
                        var finall="";
                        if(decimalPart[2].toString()=="0")
                        {
                        finall=integerPart;
                        
                        }
                        else
                        {
                        finall=integerPart + 1;
                        
                        }
                    
                        document.getElementById("pageCountVal").value=finall;
        
                        for(var i=1;i!=finall+1;i++)
                        {
                        
                        //$("#appendRow").append("<a href='/questionare/"+sid+"/"+i+"'>"+i+"</a>&nbsp;");
                        
                        }  
                    
                       
                        
                            }
                    });
        } 
        
        
        
        
      
        
        
        
        console.log("@@@@@@@@@@@@", $("#question_type").val())
        var result=true;
        
        const redirectNext = () => {
        	//debugger;
            var i = $("#pagecount").val();
            var load = localStorage.getItem('load');
            var previous = localStorage.getItem("previous") || false; 
            console.log("@@@@@@@@ should come here", load, " previous = ", previous);
            console.log("dep = ", dependentQues, " result = ", result);
            if(previous == "true"){
                i = dependentQues && !result ? parseInt(i) - 1 : parseInt(i);
                //localStorage.setItem("pre");
                load = dependentQues && !result ? "true" : "false";
            }else{
               i = dependentQues && !result ? parseInt(i) + 1 : parseInt(i);
            }
            if(load == "true"){
            	
            	if(result==true){
                    localStorage.setItem("load", "false");
                }else{
                    //localStorage.setItem("load", "true");
                }
                
                window.location.href ="<%=dashboardURL%>questionare/"+sid+"/"+i;
            }
        }
         
         
         
        if($("#question_type").val()=="Dependent Question" || $("#question_type").val()=="Dependent Question With Set Of SubQuestions"){
            dependentQues = true;
            var super_question_id=$("#super_question_id").val().split("##");
            var super_question_answer=$("#super_question_answer").val();
            var Super_question_selected_id=$("#super_question_selected_id").val().split(",");
            var Super_selected_qid=$("#Super_selected_qid").val().split(",");
            console.log("super_question_id=="+super_question_id)
            console.log("super_question_answer=="+super_question_answer)
            console.log("Super_question_selected_id=="+Super_question_selected_id)
            console.log("Super_selected_qid=="+Super_selected_qid);
            var aids=super_question_answer.split("##");
            var qdata=[];
            for(var i=0;i<super_question_id.length;i++){
                var ansList={};
                ansList["qid"]=super_question_id[i];
                ansList["anslist"]=aids[i].split(",");
                qdata.push(ansList);
            }
            console.log(qdata);
            var res=[];
            
            var data=Super_selected_qid.length;
            
            for (var i=0;i<Super_selected_qid.length;i++){
                var ans1=Super_selected_qid[i].split("-");
                var ans={};
            
                ans["qid"]=ans1[0];
                ans["ansid"]=ans1[1];
                res.push(ans);
            } 
            
            const sendFlag = (ques, ans) => {
                let matchFound = [];
                let qlen = ques.length;
            
                for(let i = 0 ; i < qlen ; i++){
                    for(let j = 0, alen = ans.length; j < alen ; j++){
                        if(ques[i].qid == ans[j].qid && ques[i].anslist.includes(ans[j].ansid)){
                                matchFound.push(true);
                        }
                    }
                }
                return matchFound.length == qlen;
            }
            
            result = sendFlag(qdata,res);
            console.log(result);
            redirectNext();
        } 
         
        
         
         var i = $("#pagecount").val();
         
         var sid=$('#storevisitid').val();
          
          $(function () {
         	  $('#demo-form').parsley().on('field:validated', function() {
         		  
         		 
         	    var ok = $('.parsley-error').length === 0;
         	    $('.bs-callout-info').toggleClass('hidden', !ok);
         	    $('.bs-callout-warning').toggleClass('hidden', ok);
         	  })
         	  .on('form:submit', function() {
         		  i++;
         		
         		  var pagecount=parseInt($("#pagecount").val())+1;
         	        if(pagecount==$("#count").val()){

         				$("#finalpage").val("finalpage");
         	          window.open("<%=dashboardURL%>/final","_self");
         	        }
         		 localStorage.setItem("load", "true");
         		 localStorage.setItem("previous", "false");
         		  $("#demo-form").attr("action","<%=dashboardURL%>answer/"+sid+"/"+i)
         	    return true; // Don't submit form for this demo
         	  });
         	});
          
          
         
          //window.location.href ="/questionare/"+sid+"/"+i;
         
         
         
         
         
         
         if($("#pagecount").val()<=0){
                		
            $(".Previous").hide();
         
         }else{
            $(".Previous").removeAttr("display","none");
            $('.Previous').on('click', function(){
                //i--;
                localStorage.setItem("previous", "true");
                localStorage.setItem("load", "false");
                i = dependentQues && !result ? parseInt(i) - 1 : parseInt(i) - 1 ; 
                // $("#formaction").attr("action","/answer/"+sid+"/"+i)
                window.location.href ="<%=dashboardURL%>questionare/"+sid+"/"+i;
                
            })
         }
         
         $(".card").on("click",function() {
        	
        	  $(this).closest('.card').data('changed', true);
        	});
         $('.card').click(function() {
        	  if($(this).closest('.card').data('changed')) {
        	    $(".skip").attr('disabled',true);
        	  }else{
        		    $(".skip").attr('disabled',false);
        	  }
        	});
         
        $(".skip").on('click', function(){
        
 		 if($("#questionoptiontype").val()=="Optional"){
        	 $("#demo-form input").prop("required", false);
        	 $("#question_optiontype").val("no");
         }
         });
      </script>
   </body>
</html>