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
<html lang="en">
	<head>
	
		<title>Review Question</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
		<link rel="shortcut icon" href="<%=UI_PATH%>/design/images/DQI-icon.png">
		
		<link rel="stylesheet" href="<%=UI_PATH%>/design/css/custom.css" type="text/css">  
		<link rel='stylesheet' href='<%=UI_PATH%>/design/css/bootstrap.min.css'>
		<link rel="stylesheet" type="text/css" href="<%=UI_PATH%>/design/css/time.css">
		
		<link rel="stylesheet" type="text/css" href="<%=UI_PATH%>/design/css/form-validation.css">
		 <link href="<%=UI_PATH%>/assets/css/icons.css" rel="stylesheet" type="text/css" />
		 <link href="<%=UI_PATH%>/assets/css/style.css" rel="stylesheet" type="text/css" />
		<style>
		.modal-backdrop.in{pointer-events:none;}
		/* .modal-open .modal{pointer-events:none;} */
		/* .modal-open form {pointer-events:auto;} */
		
		.btn-gradient{
  		  background: linear-gradient(to top, #5d6dc3, #5d6dc3) !important;
  		  }
  		  .btn-light {padding:8px 15px; color: #212529;background-color: #dae0e5;border-color: #d3d9df;}
		
		.radio-label1{white-space: nowrap;}
		.radio-cust-align{display: flex;flex-wrap: nowrap;}
		.mrg-tp{margin-top: 20px;}
		.mrg-bttm{margin-bottom: 15px;}
		</style>
	</head>
	<body class="bodysection">
	  		  		<button type="button" class="btn btn-info btn-lg hidden" data-toggle="modal" data-target="#myModal">Open Modal</button>
		
		<div id="myModal" class="modal fade" role="dialog">
		<div  class=" ">
		    <div class="row">
			  <form method="POST" action=""  autocomplete="off">
				
		    	<div class="col-md-8 col-md-offset-2">
				  	<div class="card bg-fff">	
				  	
				  	<div class="questions">
						 <ol>
						<li>
					     <span>${standard_number}</span>
						${question_text}.
						</li>							  
							</ol>
						</div>
						<input type="hidden" name="sk_shopper_id" value="${shopper_id}">
						<input type="hidden" name="question_id" value="${question_id}">
						<input type="hidden" name="question_text" value="${question_text}">
						<input type="hidden" name="standard_number" value="${standard_number}">
						<input type="hidden" name="question_type" value="${question_type}">
						
						<div>
						 <c:forEach var="mvBean"  items="${questionAnswerlist}" varStatus="status">	 	
					<input type="hidden" name=sk_answer_id value="${mvBean.sk_answer_id}">
					<input type="hidden" value="${mvBean.question_points}" name="question_points">
					<input type="hidden" value="${mvBean.question_code}" name="question_code">
                    <input type="hidden" value="${mvBean.formula_flag}" name="formula_flag">
                    <input type="hidden" value="${mvBean.answer_type}" name="answer_type">
                    <input type="hidden" value="${mvBean.scored_points}" name="scored_points">
						
				      <c:if test="${ (mvBean.question_type=='Main Question'  || mvBean.question_type=='Dependent Question')  &&  mvBean.answer_type=='Select/List'}">					
					<div class="row">
					
				<div class="col-md-6 mrg-bttm">
					<label>Old Answer</label><br>
					<input type="text" class="form-control" name="old_selectionoption_text" value="${mvBean.selected_option_text}" readonly>
					<input type="hidden" name="old_selectoption_id" value="${mvBean.selected_option_id}">
				 </div>
				 <c:set var = "num3" value="${mvBean.selected_option_comment}"/>
					<c:choose>
                    <c:when test = "${not empty num3}">
                  <div class="col-md-6 mrg-bttm">
					<label>Old Select Option Comment</label><br>
					<input type="text" class="form-control" name="oldselectOptioncomment" id="" value="${mvBean.selected_option_comment}" readonly>
				</div>
                 </c:when>
               <c:otherwise>
               <div></div>
            </c:otherwise>
            </c:choose>
				 <div class="">
				 	<c:forEach  var="count1" items="${mvBean.optionslist}" varStatus="status1">
					 <div class="col-md-12">
					
					 <div class="col-md-6 radio-cust-align">
					 <%-- <input type="hidden" name="" value="${count1.sk_answer_id}" id="">  --%>
			    	 <input id="${count1.sk_answer_id}" name="optionid" type="radio" value="${count1.sk_answer_id}" class="selectcomment"  data-parsley-errors-container="#checkbox-errors"  required><label class="radio-label1">${count1.options}</label> 
			    	<div id="checkbox-errors"></div>
			    	</div>
			    	
			    	 
			    	 
			    	<div class="col-md-6 commentDiv" id="radio_${count1.sk_answer_id}" style="display:none;">
					<input id="newcomment"  class="form-control"  name=option_comment type="text" value=""/> 
			    	</div>
			    		
			    	</div>
			    	</c:forEach>
			    	
			    	<c:if test="${mvBean.main_ques_comment!=null}">
			    	 <div class="col-md-6">
                   <input class="form-control" name="old_mainquestion_comment" value="${mvBean.main_ques_comment}" readonly>
			    </div>
			    <div class="col-md-6 txt-area">
      				<textarea class="form-control" name="main_ques_comment" id="" placeholder="Write your Comment here" required></textarea>
                </div>
                
                </c:if> 
				 </div>
				 
					
<%-- 					<input type="hidden" name="oldselectOptioncomment" id="" value="${mvBean.selected_option_comment}" >
 --%>				 </div>
					</c:if>
					<div class="clearfix"></div>
					
					  <c:if test="${ (mvBean.question_type=='Main Question' || mvBean.question_type=='Dependent Question')  && mvBean.answer_type=='Paragraph'}">
					<div  class="col-md-6 txt-area">
					<label>Old Answer</label><br>
					<textarea class="form-control" name="old_paragraph" readonly>${mvBean.selected_pagaragraph}</textarea><br>
					<textarea class="form-control" name="answerParagraph" placeholder="Write your response here" required></textarea> 
					</div>
					</c:if> 
					<div class="clearfix"></div>
					
				      <c:if test="${ (mvBean.question_type=='Main Question' || mvBean.question_type=='Dependent Question') && mvBean.answer_type=='Date & Time'}">
				     <div>
				     <div class="form-group col-md-6">
				    <label>Old Answer</label><br>
					<input type="text" class="form-control" name="old_date" value="${mvBean.selected_date}" readonly>
					</div>
					<div class="form-group col-md-6">
					<label>Old Answer</label><br>
					<input type="text" name="old_time" class="form-control" value="${mvBean.selected_time}" readonly>
					</div>
					<div class="form-group col-md-6">
                     
                     <div class="input-group date" id="datepicker">
          			<input  class="form-control" name="answerDate" class="form-control" required><span class="input-group-addon"><span class="fa fa-calendar"></span></span>
        		   </div>
                     </div>
                     <div class="form-group col-md-6">
                    <div class="input-group date" id="timepicker1">
                      <input class="form-control" id="start_time" name="answerTime" value=""
                        placeholder="Select" required> <span
                        class="input-group-addon input-group-append"><span class="fa fa-clock-o input-group-text"></span></span>
                    </div>    
                    </div>
                    </div>
                     </c:if>
                     <div class="clearfix"></div><br>
                       <c:if test="${(mvBean.question_type=='Main Question' || mvBean.question_type=='Dependent Question') && mvBean.answer_type=='Date'}">
                     <div>
                     <div class="form-group col-md-12">
                     <label>Old Answer</label><br>
					<input class="form-control" type="text" name="old_date" value="${mvBean.selected_date}" readonly>
					</div>  
                     <div class="form-group col-md-3">
                     <div class="input-group date" id="datepicker">
          			<input name="answerDate" class="form-control" required><span class="input-group-addon"><span class="fa fa-calendar"></span></span>
        		   </div>
                     </div>
                     </div>
					</c:if>  
					 
					<c:if test="${ (mvBean.question_type=='Main Question With Set Of SubQuestions'  || mvBean.question_type=='Dependent Question With Set Of SubQuestions')  &&  mvBean.answer_type=='Select/List'}">					
					<div class="row rowId">
					<div class="col-md-4 mrg-left mrg-top">																			
								<ul class="rmve-dot">
								<li><c:forEach begin="1" end="${status.index / 26 + 1}">&#${status.index % 26 + 97};</c:forEach>.&nbsp;${mvBean.subquestion} .</li>
								</ul>
								<input type="hidden"  value="${mvBean.subquestionid}" name="answerdata[${status.count }].subquestionid" id="">
								<input type="hidden"  value="${mvBean.subquestion}" name="answerdata[${status.count}].subquestion" id="">
								<input type="hidden"  value="${mvBean.answer_type}" name="answerdata[${status.count}].answer_type" id="">
								<input type="hidden"  value="${mvBean.scored_points}" name="answerdata[${status.count}].old_scored_points" id="">
								<input type="hidden"  value="${mvBean.question_text}" name="answerdata[${status.count}].old_question_text" id="">
								<%-- <input type="hidden"  value="${mvBean.question_id}" name="answerdata[${status.count}].old_question_id" id="">
								<input type="hidden"  value="${mvBean.sk_shopper_id}" name="answerdata[${status.count}].sk_shopper_id" id=""> --%>
							
					</div>
					<div class="col-md-4 comment-area mrg-top">
					        <label>Old Answer</label><br>
					            <input type="hidden" name="answerdata[${status.count}].old_selectoption_id" value="${mvBean.selected_option_id}">
								<input type="text" class="form-control" name="answerdata[${status.count}].old_selectionoption_text" value="${mvBean.selected_option_text}" readonly><br>
								
								<c:if test="${mvBean.selected_option_comment!=''}"><br>
							   <label>Old Comment</label>
								<%-- <input type="text"  class="" id="old_selected_option comment" name="old_selected_option" value="${mvBean.selected_option_comment}" readonly> --%>
								<input type="text"  class="form-control" id="old_selected_option comment" name="answerdata[${status.count}].oldselectOptioncomment" value="${mvBean.selected_option_comment}" readonly>
								<br>
								
								</c:if>
								</div>
								<%-- <c:if test="${mvBean.selected_option_comment!=''}">
							<div class="col-md-2 div2">
					        <label>old select option comment</label>
								<input type="text"  class="form-control" id="old_selected_option comment" name="old_selected_option" value="${mvBean.selected_option_comment}" readonly>
								
								<br>
								</div>
								</c:if> --%>
							
							<div class="col-md-4 div1 comment-area mrg-top">	
							<label>New Answer</label><br>						
			            		<select name="answerdata[${status.count}].optionid" id="selectoptioncomment" required  class="form-control selectoptioncomment">
									<option value="">Select Answer</option>
									<c:forEach var="mvBean1"  items="${mvBean.subquestionOptionList}">
									<option value="${mvBean1.optionId}#${mvBean1.option_comment}# ${mvBean1.option_points}">${mvBean1.options}</option>
									</c:forEach>
									</select>
								
							</div><br>
								
								<input type="hidden"  class="form-control" id="old_selected_option comment" name="old_selected_option" value="${mvBean.selected_option_comment}" readonly>
								<div class="col-md-4 div3 comment-area mrg-top" id="mydiv" style="display:none;  margin-left: -26PX;">
								<label  style="margin-left: 24PX;  margin-top: 36px;">New Comment</label><br>
								<input type="text" class="form-control" name="answerdata[${status.count}].option_comment" id="optioncommentid"   style="margin-left: 21PX;">
								</div>	
					 
				 </div>
				 </c:if>
					
					<c:if test="${ (mvBean.question_type=='Main Question With Set Of SubQuestions'  || mvBean.question_type=='Dependent Question With Set Of SubQuestions')  &&  mvBean.answer_type=='Paragraph'}">	
					<div class="col-md-4 mrg-left mrg-top">	
						 <c:if test="${mvBean.subquestion!=''}">
                          <ul class="rmve-dot">
							<li><c:forEach begin="1" end="${status.index / 26 + 1}">&#${status.index % 26 + 97};</c:forEach>.&nbsp;${mvBean.subquestion} .</li>
							</ul>
							</c:if>
							
							    <input type="hidden"  value="${mvBean.subquestionid}" name="answerdata[${status.count }].subquestionid" id="">
								<input type="hidden"  value="${mvBean.subquestion}" name="answerdata[${status.count}].subquestion" id="">
								<input type="hidden"  value="${mvBean.answer_type}" name="answerdata[${status.count}].answer_type" id="">
								<input type="hidden"  value="${mvBean.scored_points}" name="answerdata[${status.count}].old_scored_points" id="">
								<input type="hidden"  value="${mvBean.question_text}" name="answerdata[${status.count}].old_question_text" id="">		
					</div>
					<div  class="col-md-4 txt-area">
					<label>Old Answer</label><br>
					<textarea class="form-control" name="answerdata[${status.count}].old_paragraph" readonly>${mvBean.selected_pagaragraph}</textarea><br>
                   </div>
                   <div  class="col-md-4 txt-area">
                    <label>New Answer</label><br>
                    <textarea class="form-control"  name="answerdata[${status.count}].answerParagraph" placeholder="Write your response here" required></textarea> 
 				</div>
					
				 </c:if>
					<div class="clearfix"></div>
					
					<c:if test="${ (mvBean.question_type=='Main Question With Set Of SubQuestions'  || mvBean.question_type=='Dependent Question With Set Of SubQuestions')  &&  mvBean.answer_type=='Date & Time'}">					
					<div>
					<div class="col-md-4 mrg-left mrg-top">	
					<c:if test="${mvBean.subquestion!=''}">																		
								<ul class="rmve-dot">
								<li><c:forEach begin="1" end="${status.index / 26 + 1}">&#${status.index % 26 + 97};</c:forEach>.&nbsp;${mvBean.subquestion} .</li>
								</ul>
								</c:if>
					            <input type="hidden"  value="${mvBean.subquestionid}" name="answerdata[${status.count }].subquestionid" id="">
								<input type="hidden"  value="${mvBean.subquestion}" name="answerdata[${status.count}].subquestion" id="">
								<input type="hidden"  value="${mvBean.answer_type}" name="answerdata[${status.count}].answer_type" id="">
								<input type="hidden"  value="${mvBean.scored_points}" name="answerdata[${status.count}].old_scored_points" id="">
								<input type="hidden"  value="${mvBean.question_text}" name="answerdata[${status.count}].old_question_text" id="">		
					</div>
				     <div class="form-group col-md-6">
				    <label>Old Answer</label><br>
					<input type="text" class="form-control" name="answerdata[${status.count}].old_date" value="${mvBean.selected_date}" readonly>
					</div>
					<div class="form-group">
					<label style="margin-left:-185px;">Old Answer</label><br>
					<input type="text" class="form-control"  name="answerdata[${status.count}].old_time" value="${mvBean.selected_time}" readonly style="margin-left:-185px;">
					</div>
					<div class="form-group col-md-3">
                     
                     <div class="input-group date" id="datepicker">
          			<input name="answerdata[${status.count  }].answerDate" class="form-control"><span class="input-group-addon" required><span class="fa fa-calendar"></span></span>
        		   </div>
                     </div>
                     <div class="form-group col-md-3">
                    <div class="input-group date" id="timepicker1">
                      <input class="form-control" id="start_time" name="answerdata[${status.count  }].answerTime" value=""
                        placeholder="Select" required> <span
                        class="input-group-addon input-group-append"><span class="fa fa-clock-o input-group-text"></span></span>
                    </div>    
                    </div>
                    </div>
				 </c:if>
					<div class="clearfix"></div>
					
					<c:if test="${ (mvBean.question_type=='Main Question With Set Of SubQuestions'  || mvBean.question_type=='Dependent Question With Set Of SubQuestions')  &&  mvBean.answer_type=='Date'}">					
					 <div>
					 <div class="col-md-4 mrg-left mrg-top">
					 <c:if test="${mvBean.subquestion!=''}">																			
								<ul class="rmve-dot">
								<li><c:forEach begin="1" end="${status.index / 26 + 1}">&#${status.index % 26 + 97};</c:forEach>.&nbsp;${mvBean.subquestion} .</li>
								</ul>
								</c:if>
					            <input type="hidden"  value="${mvBean.subquestionid}" name="answerdata[${status.count }].subquestionid" id="">
								<input type="hidden"  value="${mvBean.subquestion}" name="answerdata[${status.count}].subquestion" id="">
								<input type="hidden"  value="${mvBean.answer_type}" name="answerdata[${status.count}].answer_type" id="">
								<input type="hidden"  value="${mvBean.scored_points}" name="answerdata[${status.count}].old_scored_points" id="">
								<input type="hidden"  value="${mvBean.question_text}" name="answerdata[${status.count}].old_question_text" id="">		
					</div>
                     <div class="form-group col-md-12">
                     <label>Old Answer</label><br>
					<input type="text" class="form-control" name="answerdata[${status.count}].old_date" value="${mvBean.selected_date}" readonly>
					</div>  
                     <div class="form-group col-md-3">
                     <div class="input-group date" id="datepicker">
          			<input name="answerdata[${status.count}].answerDate" class="form-control"><span class="input-group-addon" required><span class="fa fa-calendar"></span></span>
        		   </div>
                     </div>
                     </div>
				 </c:if>
					<div class="clearfix"></div>
					</c:forEach>
					</div> <br>
					
					
			   		 <div class="center">		    	
<!-- 					    <input type="submit" class="btn btn-info  btn-gradient" value="Update">
 -->					     <a href="<%=dashboardURL%>getMYSsecondphase/${shopper_id}" class="btn btn-light waves-effect m-l-5">
                                                Back
                                            </a>
				  	</div>	 
				  				  	    
			  	</div>	
	  		</div>
	  		  
			</form>			
		   
		</div>
	</div>	
	</div>
		    			
	    
		<script src="<%=UI_PATH%>/design/js/jquery.min.js"></script>
		
		<script src="<%=UI_PATH%>/design/js/bootstrap.min.js"></script>
		 <script src='<%=UI_PATH%>/design/js/date&time/jquery-2.2.4.min.js'></script>
		<script src='<%=UI_PATH%>/design/js/date&time/moment.min.js'></script>
		<script src='<%=UI_PATH%>/design/js/date&time/bootstrap-datetimepicker.min.js'></script>
		<script src="<%=UI_PATH%>/design/js/date&time/date&time.js"></script>	
                <script type="text/javascript" src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>

<script type="text/javascript">
$(window).on('load',function(){
   $('[data-toggle="modal"]').trigger('click');
});


</script>	
<script>
            $(document).ready(function() {
                $('form').parsley();
            });
    </script>   
   <script>
  $( function() {
    $( ".datepicker" ).datepicker({
    	dateFormat: "yy-mm-dd",
    	onSelect:function(){
    		$(this).parsley().validate()
    	}
    });
  } );
  </script>	
  <script>
  
 
  </script>
  <script>
  var old_selected_option=$("input[name=old_selected_option]").val();
  //alert(old_selected_option);
  $(".selectoptioncomment").each(function(){
	 
	  $(this).on("change",function(){
		  var status=$(this).val().split("#");
		  if(status[1]=='Yes' && old_selected_option.trim()!=''){
			  $(this).parent().parent().find("#mydiv").css("display", "block");
			  $(this).parent().parent().find("#optioncommentid").prop('required',true);
		  }else{
			  $(this).parent().parent().find("#mydiv").css("display", "none"); 
		  }
	  });
	  
  });
 
  </script>
  
  <script>
  var oldComment=$("input[name=oldselectOptioncomment]").val();
   $(".selectcomment").each(function(){
	   $(this).on("change",function(){
		   var idVal= $(this).attr('id');
		   
		   if(oldComment.trim()!=''){
			   //alert(1)
			   $("#radio_"+idVal).css("display","block");
			  $(".commentDiv").each(function(){
				  if($(this).attr("id")!="radio_"+idVal){
					  $(this).css("display","none");
					 // $("#radio_"+idVal).find("#newcomment").prop('required',true);
				  }
			  });
				 
			
		   }
		   else{
			   $("#radio_"+idVal).css("display","none");
			   //$("#newcomment").hide();
			   
		   }
		
	  });
	  
  }); 
 
  </script>
  
  
	</body>
</html>
