<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="fn" %> 
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
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <META Http-Equiv="Cache-Control" Content="no-cache">
		<META Http-Equiv="Pragma" Content="no-cache">
		<META Http-Equiv="Expires" Content="0">

       <!-- App favicon -->
        <link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">
		<link rel="shortcut icon" href="<%=UI_PATH%>/assets/images/lugma_favicon.png">
		
		<link href="<%=UI_PATH%>/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <!-- DataTables --> 
        <link href="<%=UI_PATH%>/plugins/datatables/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/plugins/datatables/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css" />
         <!-- Responsive datatable examples -->
        <link href="<%=UI_PATH%>/plugins/datatables/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        
        <!-- Custom box css -->
        <link href="<%=UI_PATH%>/plugins/custombox/css/custombox.min.css" rel="stylesheet">

        <!-- App css -->
        <link href="<%=UI_PATH%>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/metismenu.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/style.css" rel="stylesheet" type="text/css" />

        <script src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>
		<link rel="stylesheet" href="<%=UI_PATH%>/css/nice-select.css" type="text/css" />
		
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		
		<style>
		#userType{
		position: relative;
    	left: 20px;
		}
		#icon{
		position: relative;
	    top: 31px;
	    font-size: 20px;
		}
		.radio label::after{
		    top: 9px!important;
		
		}
		
		.btnstyle{
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
                                    <p class="text-muted font-14 m-b-20">
                                    </p>

                                    <form method="POST" action="<%=dashboardURL%>updatequestionpost/${question_id}">
	                                     <div class="form-row">
	                                     <div  class="form-group col-md-3">
                                                <label for="userName">Brand<span class="text-danger">*</span></label>
                                                <select name="sk_brand_id" parsley-trigger="change" required class="form-control" id="sk_brand_id" disabled>
                                                  <option value="${brand_id}">${brand_name}</option> 
                                                  
                                                   <c:forEach var="dbBean" items="${activebrandList}">
                                    					 <option value="${dbBean.sk_brand_id }">${dbBean.brand_name }</option>
                                     					</c:forEach>
                                                </select>
                                                    <input type="hidden" value="${brand_id}" name="brand_id" >
                                            </div>
	                                     
	                                     		<div  class="form-group col-md-3">
		                                            <label for="userName">Mode Of Contact<span class="text-danger">*</span></label>
		                                            <select name="mode_of_contact" parsley-trigger="change" required class="form-control" id="modeOfContact" disabled>
		                                            	<option value="${modeofcontact}">${modeofcontact}</option> 
		                                            	<option value="All">All</option>      
		                                            	<option value="Email/Website">Email/Website</option>
		                                            	<option value="Telephone">Telephone</option>
		                                            	<option value="Walk In">Walk In</option>
		                                            	<option value="Online Sales Channel">Online Sales Channel</option>
		                                            	
		                                            </select>
		                                        </div>
		                                         <input type="hidden" value="${modeofcontact}" name="mode_of_contact" >
		                                       
		                                        <div  class="form-group col-md-3">
		                                            <label for="userName">Section<span class="text-danger">*</span></label>
		                                            <select name="sk_section_id" parsley-trigger="change" disabled required class="form-control" id="sk_section_id" onchange="getSubSection(this.value)">
		                                            <option value="${section_id}">${section_name}</option>  
		                                            	 <c:forEach var="dbBean" items="${sectionList}">
                                    					 <option value="${dbBean.sk_section_id }">${dbBean.section_name }</option>
                                     					</c:forEach>
		                                            	
		                                            </select>
		                                        </div>
		                                        
		                                        <div  class="form-group col-md-3">
		                                            <label for="userName">Sub Section<span class="text-danger">*</span></label>
		                                            <select name="sk_subsection_id" parsley-trigger="change" disabled required class="form-control" id="sk_subsection_id">
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
		                                        
		                                        <div  class="form-group col-md-3">
		                                            <label for="userName">Standard Number<span class="text-danger">*</span></label>
		                                            <input type="text" name="standard_number" value="${stdno}" disabled id="standardNumber" parsley-trigger="change" placeholder="Enter Standard Number" required class="form-control">
		                                        </div>
	                                     
	                                     
		                                        <div  class="form-group col-md-3">
		                                            <label for="userName">Question Type<span class="text-danger">*</span></label>
		                                            <select name="question_type" parsley-trigger="change" disabled  class="form-control" id="questionType" required onchange="checkQuestion(this.value)">
		                                            	<option value="${question_type}">${question_type}</option>       
		                                            	<option value="Main Question">Main Question</option>
		                                            	<option value="Main Question With Set Of SubQuestions">Main Question With Set Of SubQuestions</option>
		                                            	<option value="Dependent Question">Dependent Question</option>
		                                            	<option value="Dependent Question With Set Of SubQuestions">Dependent Question With Set Of SubQuestions</option>
		                                            	
		                                            </select>
		                                        </div>
		                                        <input type="hidden" value="${question_type}" name="question_type" >
		                                        <input type="hidden" value="${answer_type}" name="answer_type" >
		                                        <div  class="form-group col-md-2">
		                                            <label for="userName">Question Mandatory<span class="text-danger">*</span></label>
		                                            <select name="question_optiontype" parsley-trigger="change"  class="form-control" id="questionOptionType">
		                                            	<option value="${question_optiontype}">${question_optiontype}</option>  
		                                            	<option value="Mandatory">Mandatory</option>   
		                                            	<option value="Optional">Optional</option>   
		                                            </select>
		                                        </div>
		                                         <div class="form-group col-md-2">
		                                          <label for="userName">Points<span class="text-danger">*</span></label>
			                                            <input type="text" value="${question_points}"   name="question_points" id="questionPoints" parsley-trigger="change" placeholder="Enter Points" required class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-2">
			                                        <label for="userName">Code<span class="text-danger">*</span></label>
			                                            <input type="text" value="${question_code}"   name="question_code" id="questionCode" parsley-trigger="change" placeholder="Enter Code" required class="form-control">
			                                        </div>
	                                       </div>
                                        
                                       	<div class="mainquestion">
                                       	
                                       	</div>
                                       	
                                       	<div class="mainquestionwithsetofsubQuestions">
                                       	
                                       	</div>
                                       	
                                       	<div class="dependentquestion">
                                       	
                                       	</div>
                                       	
                                       	<div class="dependentquestionwithsetofsubQuestions">
                                       	
                                       	</div>
                                        <div class="form-group text-right m-b-0">
                                            <button class="btn btn-gradient waves-effect waves-light" type="submit">
                                                Update
                                            </button>
                                            <a href="<%=dashboardURL%>questions" class="btn btn-light waves-effect m-l-5">
                                                Cancel
                                            </a>
                                        </div>

                                    </form>
                                </div> <!-- end card-box -->
                            </div>
                            <!-- end col -->

                            
                            
                            
                            
                            
                        </div>
                        <!-- end row -->

                        

                    </div> <!-- container -->

                </div> <!-- content -->

            </div>


            <!-- ============================================================== -->
            <!-- End Right content here -->
            <!-- ============================================================== -->


        </div>
        <!-- END wrapper -->
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
        <script src="<%=UI_PATH%>/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/select2/js/select2.min.js" type="text/javascript"></script>
        <script src="<%=UI_PATH%>/plugins/bootstrap-select/js/bootstrap-select.js" type="text/javascript"></script>
        <!--  Required datatable js -->
        <script src="<%=UI_PATH%>/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/dataTables.bootstrap4.min.js"></script>
        <!-- Buttons examples -->
        <script src="<%=UI_PATH%>/plugins/datatables/dataTables.buttons.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/buttons.bootstrap4.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/jszip.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/pdfmake.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/vfs_fonts.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/buttons.html5.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/buttons.print.min.js"></script>
        <!-- Responsive examples -->
        <script src="<%=UI_PATH%>/plugins/datatables/dataTables.responsive.min.js"></script>
        <script src="<%=UI_PATH%>/plugins/datatables/responsive.bootstrap4.min.js"></script>
        
<%--         <script src="<%=UI_PATH%>/plugins/bootstrap-inputmask/bootstrap-inputmask.min.js" type="text/javascript"></script>
 --%>        <script src="<%=UI_PATH%>/plugins/autoNumeric/autoNumeric.js" type="text/javascript"></script>

        <!--  App js -->
        <script src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
        <script src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>
          
        <!-- Init Js file -->
        <script type="text/javascript" src="<%=UI_PATH%>/assets/pages/jquery.form-advanced.init.js"></script>
        <!-- Parsley js -->
        <script type="text/javascript" src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>
        
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

        <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
        </script>
     
 <script type="text/javascript">
 
 
 $(document).ready(function(){
	 var questiontype='${question_type}';
	 checkQuestion(questiontype);
 });
 
 $(document).ready(function(){
	 var answer_type='${answer_type}';
	 var question_type='${question_type}';
	 if(question_type!="Dependent Question With Set Of SubQuestions"){
			 checkAnswerType(answer_type);
	 }
	
 });
 
 
 
 
 
 

 
 
 function checkQuestion(questiontype){
	 if(questiontype=="Main Question"){
		 $(".mainquestions").remove();
		 var questionTxt="${question}";
		 
		 $(".mainquestion").append('<div class="mainquestions"><div class="form-group"><label for="emailAddress">Main Question<span class="text-danger">*</span></label>'
                 +'<input type="text" name="question" parsley-trigger="change" placeholder="Enter question" class="form-control" value="'+questionTxt+'" required id="question">'
                 +'</div>'
                 +'<div  class="form-group">'
                 +'  <label for="userName">Answer Type<span class="text-danger">*</span></label>'
                 +'  <select name="answer_type" disabled parsley-trigger="change" class="form-control" id="answerType" onchange="checkAnswerType(this.value)">'
                 +' 	<option value="${answer_type}">${answer_type}</option> '      
                 +' 	<option value="Paragraph">Paragraph</option>'
                 +'  	<option value="Date">Date</option>'
                 +'  	<option value="Date & Time">Date & Time</option>'
                 +'  	<option value="Select/List">Select/List</option>'
                 +'   </select>'
                 +' </div>'
                 +'<div class="row"><div  class="form-group col-lg-6">'
                 +'  <label for="userName">Add Comment Box</label>'
                 +'  <select name="comment_mandatory" parsley-trigger="change" class="form-control" id="comment_mandatory" >'
                 +' 	<option value="${comment_mandatory}">${comment_mandatory}</option>'
                 +' 	<option value="yes-mandatory">Yes-Mandatory</option>'
                 +'  	<option value="any-optional">Yes-Optional</option>'
                 +'  	<option value="no">No</option>'
                 +'   </select>'
                 +' </div>'
                 +'<div class="form-group col-lg-6 uploadfile" style="margin-top: 33px">'
                 +'<div class="checkbox checkbox-purple">'
                 +' <input id="uploadfile" name="upload_file" value="Yes" type="checkbox">'
                 +'   <label for="uploadfile">'
                 +'   Upload File'
                 +' </label>'
                 +'</div></div>'
                 +'<div class="datetime"><div class="dateandtimemaindiv"></div></div>'
                 +'<div class="date"><div class="datemaindiv"></div></div>'
                 +'<div class="selectlist"><div class="selectlistmaindiv"></div></div></div>');
		
		 var optionValues =[];
		 $('#comment_mandatory option').each(function(){
		    if($.inArray(this.value, optionValues) >-1){
		       $(this).remove()
		    }else{
		       optionValues.push(this.value);
		    }
		 });
		 var fileupload='${uploadfile}';
	
		 if(fileupload=="Yes" || fileupload=="yes"){
			
			 $("#uploadfile").attr("checked",true);
		 }
		 
		
		  
	 }else
 
  if(questiontype=="Dependent Question"){
	  var questionTxt="${question}";
		 $(".mainquestions").remove();
		 $(".dependentquestions").remove();
		 $(".dependentquestion").append('<div class="dependentquestions"><button type="button" class="add_field_button5 btn btn-info superQuetionAddRow">+</button><div class="form-row"><div  class="form-group col-md-3 pull-left"><label for="userName">Super Question<span class="text-danger">*</span></label></div><div class="form-group col-md-3 pull-left"></div><div  class="form-group col-md-3 pull-left"></div><div  class="form-group col-md-3 pull-left"><label for="userName">Answer<span class="text-danger">*</span></label></div></div>'
				 +'<div class="superquestionappend"><c:forEach var="qBean" items="${superquestionsList}" varStatus="status"><div class="form-row superQuestionRow"><div  class="form-group col-md-3 pull-left"><select name="superquestions[${status.index}].super_question_section_id"  parsley-trigger="change"onchange="getSubSections(this.value,${status.count});getstandardnumber1(${status.count});" class="form-control dependentsection" id="super_section_id_${status.count}"><option value="${qBean.super_question_section_id}">${qBean.super_question_section_name}</option>'
				 +'<c:forEach var="dbBean" items="${sectionList}">'
				 +'<option value="${dbBean.sk_section_id }">${dbBean.section_name }</option>'
				 +'</c:forEach>'
				 +' </select></div><div  class="form-group col-md-3 pull-left"><select name="superquestions[${status.index}].super_question_subsection_id" parsley-trigger="change"  class="form-control dependentsubsection" onchange="getstandardnumber1(${status.count});" id="super_subsection_id_${status.count}"><option value="${qBean.super_question_subsection_id}">${qBean.super_question_subsection_name}</option></select></div><div  class="form-group col-md-3 pull-left"><select name="superquestions[${status.index}].super_question_standard_number"  parsley-trigger="change" class="form-control dependentstandardnum" onchange="getSuperQuestionAnswer(this.value,${status.count});" required id="super_standard_Number_1"><option value="${qBean.super_question_standard_number}">${qBean.super_question_standard_number} - ${qBean.year}</option></select></div><div class="form-group col-md-3 pull-left">'
				 +' <select id="superquestionanswer_1"  name="superquestions[${status.index}].super_question_answer" required class="form-control select2 form-control select2-multiple" multiple data-placeholder="Choose Answers.." data-parsley-errors-container="#chke">'
				 +' <c:forEach var="qBeans" items="${qBean.optionsList}"><option value="${qBeans.sk_answer_id }" <c:forEach var="qBeans1" items="${qBean.selectedoptionsList}"><c:if test = "${qBeans.sk_answer_id == qBeans1.sk_answer_id }">selected </c:if> </c:forEach>>${qBeans.answer }</option></c:forEach></select><div id="chke"></div></div>'
				 +'</div></c:forEach></div><div class="form-group"><label for="emailAddress">Main Question<span class="text-danger">*</span></label>'
                 +'<input type="text" value="'+questionTxt+'" name="question" parsley-trigger="change" placeholder="Enter question" class="form-control" required id="question">'
                 +'</div>'
                 +'<div  class="form-group">'
                 +'  <label for="userName">Answer Type<span class="text-danger">*</span></label>'
                 +'  <select name="answer_type" disabled parsley-trigger="change" class="form-control" id="answerType" onchange="checkAnswerType(this.value)">'
                 +' 	<option value="${answer_type}">${answer_type}</option> '      
                 +' 	<option value="Paragraph">Paragraph</option>'
                 +'  	<option value="Date">Date</option>'
                 +'  	<option value="Date & Time">Date & Time</option>'
                 +'  	<option value="Select/List">Select/List</option>'
                 +'   </select>'
                 +' </div>'
                 +'<div class="row"><div  class="form-group col-lg-6">'
                 +'  <label for="userName">Add Comment Box</label>'
                 +'  <select name="comment_mandatory" parsley-trigger="change" class="form-control" id="comment_mandatory" >'
                 +' 	<option value="${comment_mandatory}">${comment_mandatory}</option>'
                 +' 	<option value="yes-mandatory">Yes-Mandatory</option>'
                 +'  	<option value="any-optional">Yes-Optional</option>'
                 +'  	<option value="no">No</option>'
                 +'   </select>'
                 +' </div>'
                 +'<div class="form-group col-lg-6 uploadfile" style="margin-top: 33px">'
                 +'<div class="checkbox checkbox-purple">'
                 +' <input id="uploadfile" name="upload_file" value="Yes" type="checkbox">'
                 +'   <label for="uploadfile">'
                 +'   Upload File'
                 +' </label>'
                 +'</div></div>'
                 +'<div class="datetime"><div class="dateandtimemaindiv"></div></div>'
                 +'<div class="date"><div class="datemaindiv"></div></div>'
                 +'<div class="selectlist"><div class="selectlistmaindiv"></div></div></div>');
		 
		 $('select').select2();
		 var optionValues =[];
		 $('#comment_mandatory option').each(function(){
		    if($.inArray(this.value, optionValues) >-1){
		       $(this).remove()
		    }else{
		       optionValues.push(this.value);
		    }
		 });
		 
		 
		 
		 
		 
		  
		  
   var alphabets=65;
		 var add_button      = $(".add_field_button5"); //Add button ID
		 var max_fields      = 30; //maximum input boxes allowed
		 var x=$(".superquestionappend").find(".superQuestionRow ").length-1;
			 $(add_button).on("click",function(){
				 x++;
				 var z=x+1
				 var y=x-1;
				 $(".superquestionappend").append('<div class="form-row superQuestionRow" ><div  class="form-group col-md-3 pull-left"><select name="superquestions['+x+'].super_question_section_id" parsley-trigger="change"onchange="getSubSections(this.value,'+z+');" class="form-control dependentsection" id="super_section_id_'+z+'"><option value="">Select Section</option>'
		              +'<c:forEach var="dbBean" items="${sectionList}">'
			               +'<option value="${dbBean.sk_section_id }">${dbBean.section_name }</option>'
			               +'</c:forEach>'
			               +' </select></div><div  class="form-group col-md-3 pull-left"><select name="superquestions['+x+'].super_question_subsection_id" parsley-trigger="change" class="form-control dependentsubsection" onchange="getstandardnumber1('+z+');" id="super_subsection_id_'+z+'"><option value="">Select Sub Section</option></select></div><div  class="form-group col-md-3 pull-left"><select name="superquestions['+x+'].super_question_standard_number" parsley-trigger="change" class="form-control dependentstandardnum" onchange="getSuperQuestionAnswer(this.value,'+z+');" required id="super_standard_Number_'+z+'"><option value="">Select Standard Number</option></select></div><div  class="form-group col-md-2 pull-left">'
			               +' <select id="superquestionanswer_'+z+'" name="superquestions['+x+'].super_question_answer" required class="form-control select2 form-control select2-multiple" multiple data-placeholder="Choose Answers.." data-parsley-errors-container="#chke">'
			               +' </select> <div id="chke"></div></div><div  class="form-group col-md-1 pull-left"><button class="remove_field pull-right btn btn-info" >-</button></div>');
				     $('select').select2();
					 $(".superQuestionRow").on("click",".remove_field", function(e){
						    //user click on remove text
				            e.preventDefault(); 
				            $(this).closest('.superQuestionRow').remove();
				            });
				 }); 
		    


		 var fileupload='${uploadfile}';
			
		 if(fileupload=="Yes" || fileupload=="yes"){
			
			 $("#uploadfile").attr("checked",true);
		 }
		   
		 
	 } 
else{
		 $(".mainquestions").remove();
		 $(".mainsubquestions").remove();
		 $(".dependentquestions").remove();
		 $(".dependentsubquestions").remove();
	 }
 } 
//main question with set of subquestions options selectlist


$(document).ready(function(){
		var x=0;
		var y=1;
	$(".subquestionanswertypeinput").each(function(){
		var anstype =$(this).val();
		//onloadcheckSubsetQuestionType(anstype,y,x);
		x++;
		y++;
	});
	
});
 
</script>
 
 <script>
 //onload dependent sub set questions
 function onloadcheckSubsetQuestionType(val,row,optionval){
	 
		if(val=="Select/List"){
			 $(".dateandtimemaindiv"+row).remove();
			 $(".datemaindiv"+row).remove();
			var answerwrapper   = $("#subquestiontype_list"+row);
	    	$(answerwrapper).append('<div class="form-row  sub_answer'+row+' col-md-12" id="sub_answer"><button class="add_field_button3 btn btn-info" id="buttonanswer1" type="button">+</button><div class="form-group col-md-2 pull-left"><label for="emailAddress">Answer<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Points<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Code<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Response<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Mandatory<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Comment<span class="text-danger">*</span></label></div></div>');
	    	$(answerwrapper).append('<div class="form-row sub_answer'+row+' col-md-12" id="sub_answer"><c:forEach var="qBean" items="${subquestionoptionsList}" varStatus="count"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[${count.index}].answer" value="${qBean.answer}" id="subanswer_main1" parsley-trigger="change"  placeholder="Enter Answer" class="form-control sub subquestionanswerinput"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[${count.index}].answer_points" value="${qBean.answer_points}" id="subanswerpoints_main1" parsley-trigger="change"  placeholder="Enter Points" class="form-control subquestionpointsinput"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[${count.index}].answer_code" value="${qBean.answer_code}" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[${count.index}].answer_response" value="${qBean.answer_response}" id="subanswerresponse_main1" parsley-trigger="change"  placeholder="Enter Response" class="form-control subquestionresponseinput"></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers[${count.index}].answer_optiontype"  value="${qBean.answer_optiontype}" id="mandatory_main" parsley-trigger="change"  class="form-control mandatory_main"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers[${count.index}].answer_comment" id="comment_main" parsley-trigger="change" class="form-control"><option value="${qBean.answer_comment}">${qBean.answer_comment}</option></select></div></div></c:forEach></div>');
			
	 
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
		            	$(answerwrapper).append('<div class="form-row sub_answer'+row+' col-md-12" id="sub_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer" id="subanswer_main1" parsley-trigger="change"  placeholder="Enter Answer" class="form-control sub subquestionanswerinput"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_points" id="subanswerpoints_main1" parsley-trigger="change"  placeholder="Enter Points" class="form-control subquestionpointsinput"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_code" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_response" id="subanswerresponse_main1" parsley-trigger="change"  placeholder="Enter Response" class="form-control subquestionresponseinput"></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_optiontype" id="mandatory_main" parsley-trigger="change"  class="form-control mandatory_main"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_comment" id="comment_main" parsley-trigger="change"  class="form-control comment_main"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field">-</button></div></div>');
		            }
		        });
		        
		        $(answerwrapper).on("click",".remove_field", function(e){ //user click on remove text
		            
		        	   e.preventDefault(); 
		   	        $(this).parent('#sub_answer').remove(); 
		   	        x--;
		            });
		          
		
		
		}else if(val=="Date"){
			var x=0;
			$(".sub_answer"+row).remove();
			 $(".dateandtimemaindiv"+row).remove();
			 $(".datemaindiv"+row).remove();
			var answerwrapper   = $("#subquestiondate"+row);
			 $(answerwrapper).append('<div class="datemaindiv'+row+'" style="padding: 20px;"><div class="row">'
					 +'<div class="form-group col-md-2">'
					 +'<label for="userName">Date:<span class="text-danger"></span></label>'
					 +' </div>' 
					 +'	<c:forEach var="qBean" items="${dependentsubquestionsDateList}"><div class="form-group col-md-2">'
					 +'<label for="userName">Points<span class="text-danger">*</span></label>'
					 +' <input type="hidden" value="${qBean.sk_subquestion_id}" name="sk_datetime_id['+optionval+']">    <input type="text" name="subquestions['+optionval+'].date_points" value="${qBean.date_points}" id="datePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
					 +' </div>'
					 +'<div class="form-group col-md-2">'
					 +'<label for="userName">Code<span class="text-danger">*</span></label>'
					 +'    <input type="text" name="subquestions['+optionval+'].date_code" value="${qBean.date_code}" id="dateCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">'
					 +'</div>'
					 +' <div class="form-group col-md-2">'
					 +' <label for="userName">Response<span class="text-danger">*</span></label>'
					 +'    <input type="text" name="subquestions['+optionval+'].date_response" id="dateResponse" value="${qBean.date_response}" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
					 +'</div>'
					 +'  <div class="form-group col-md-3">'
					 +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
					 +'   <input type="text" name="subquestions['+optionval+'].date_routing" id="dateRouting"  value="${qBean.date_routing}"   parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
					 +'</div>'
					
	                 
	                  +'</div></c:forEach></div>'); 
	 }else  if(val=="Date & Time"){
		 var answerwrapper   = $("#subquestiondatetime"+row);
		 $(".dateandtimemaindiv"+row).remove();
		 $(".datemaindiv"+row).remove();
			$(".sub_answer"+row).remove();
		 $(answerwrapper).append('<div class="dateandtimemaindiv'+row+'" style="padding: 20px;"><div class="row">'
				 +'<div class="form-group col-md-2">'
				 +'<label for="userName">Date:<span class="text-danger"></span></label>'  
				 +' </div>'
				 +'	<c:forEach var="qBean" items="${dependentsubquestionsDateTimeList}"><div class="form-group col-md-2">'
				 +'<label for="userName">Points<span class="text-danger">*</span></label>'
				 +'  <input type="hidden" value="${qBean.sk_subquestion_id}" name="sk_datetime_id['+optionval+']">  <input type="text" name="subquestions['+optionval+'].date_points" value="${qBean.date_points}" id="datePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
				 +' </div>'
				 +'<div class="form-group col-md-2">'
				 +'<label for="userName">Code<span class="text-danger">*</span></label>'
				 +'    <input type="text" name="subquestions['+optionval+'].date_code" id="dateCode" parsley-trigger="change" value="${qBean.date_code}" placeholder="Enter Code" class="form-control">'
				 +'</div>'
				 +' <div class="form-group col-md-2">'
				 +' <label for="userName">Response<span class="text-danger">*</span></label>'
				 +'    <input type="text" name="subquestions['+optionval+'].date_response"  value="${qBean.date_response}" id="dateResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
				 +'</div>'
				 +'  <div class="form-group col-md-3">'
				 +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
				 +'   <input type="text" name="subquestions['+optionval+'].date_routing" value="${qBean.date_routing}"  id="dateRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
				 +'</div>'
				 +'<br>'
	              +'<div class="form-group col-md-2">'
	              +' <label for="userName">Time:<span class="text-danger"></span></label>'
	              +'</div>'
	              +'<div class="form-group col-md-2">'
	              +' <label for="userName">Points<span class="text-danger">*</span></label>'
	              +'   <input type="text" name="subquestions['+optionval+'].time_points" id="timePoints"  value="${qBean.time_points}"   parsley-trigger="change" placeholder="Enter Points" class="form-control">'
	              +' </div>'
	              +'<div class="form-group col-md-2">'
	              +' <label for="userName">Code<span class="text-danger">*</span></label>'
	              +'    <input type="text" name="subquestions['+optionval+'].time_code" id="timeCode" value="${qBean.time_code}"  parsley-trigger="change" placeholder="Enter Code" class="form-control">'
	              +' </div>'
	              +'<div class="form-group col-md-2">'
	              +'<label for="userName">Response<span class="text-danger">*</span></label>'
	              +'    <input type="text" name="subquestions['+optionval+'].time_response" value="${qBean.time_response}"  id="timeResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
	              +' </div>'
	              +' <div class="form-group col-md-3">'
	              +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
	              +'   <input type="text" name="subquestions['+optionval+'].time_routing" id="timeRouting" value="${qBean.time_routing}"  parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
	              +' </div>'
	              +'</div></c:forEach></div>'); 
		 
		 
	 }else{
		 $(".dateandtimemaindiv"+row).remove();
		 $(".datemaindiv"+row).remove();
			$(".sub_answer"+row).remove();
	 }
	 }
 
 </script>
 
 
 <script>
 //for sub set questions
 function checkSubsetQuestionType(val,row,optionval){
	 
		if(val=="Select/List"){
			 $(".dateandtimemaindiv"+row).remove();
			 $(".datemaindiv"+row).remove();
			var answerwrapper   = $("#subquestiontype_list"+row);
	    	$(answerwrapper).append('<div class="form-row  sub_answer'+row+' col-md-12" id="sub_answer"><button class="add_field_button3 btn btn-info" id="buttonanswer1" type="button">+</button><div class="form-group col-md-2 pull-left"><label for="emailAddress">Answer<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Points<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Code<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Response<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Mandatory<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Comment<span class="text-danger">*</span></label></div></div>');
	    	$(answerwrapper).append('<div class="form-row sub_answer'+row+' col-md-12" id="sub_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[0].answer" id="subanswer_main1" parsley-trigger="change"  placeholder="Enter Answer" class="form-control sub subquestionanswerinput"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[0].answer_points" id="subanswerpoints_main1" parsley-trigger="change"  placeholder="Enter Points" class="form-control subquestionpointsinput"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[0].answer_code" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[0].answer_response" id="subanswerresponse_main1" parsley-trigger="change"  placeholder="Enter Response" class="form-control subquestionresponseinput"></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers[0].answer_optiontype" id="mandatory_main" parsley-trigger="change"  class="form-control mandatory_main"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers[0].answer_comment" id="comment_main" parsley-trigger="change"  class="form-control comment_main"><option value="Yes">Yes</option><option value="No">No</option></select></div></div></div>');
			
	 
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
		            	$(answerwrapper).append('<div class="form-row sub_answer'+row+' col-md-12" id="sub_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer" id="subanswer_main1" parsley-trigger="change"  placeholder="Enter Answer" class="form-control sub subquestionanswerinput"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_points" id="subanswerpoints_main1" parsley-trigger="change"  placeholder="Enter Points" class="form-control subquestionpointsinput"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_code" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_response" id="subanswerresponse_main1" parsley-trigger="change"  placeholder="Enter Response" class="form-control subquestionresponseinput"></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_optiontype" id="mandatory_main" parsley-trigger="change"  class="form-control mandatory_main"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_comment" id="comment_main" parsley-trigger="change"  class="form-control comment_main"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field">-</button></div></div>');
		            }
		        });
		        
		        $(answerwrapper).on("click",".remove_field", function(e){ //user click on remove text
		            
		        	   e.preventDefault(); 
		   	        $(this).parent('#sub_answer').remove(); 
		   	        x--;
		            });
		          
		
		
		}else if(val=="Date"){
			var x=0;
			$(".sub_answer"+row).remove();
			 $(".dateandtimemaindiv"+row).remove();
			 $(".datemaindiv"+row).remove();
			var answerwrapper   = $("#subquestiondate"+row);
			 $(answerwrapper).append('<div class="datemaindiv'+row+'" style="padding: 20px;"><div class="row">'
					 +'<div class="form-group col-md-2">'
					 +'<label for="userName">Date:<span class="text-danger"></span></label>'
					 +' </div>' 
					 +'	<c:forEach var="qBean" items="${dependentsubquestionsDateList}"><div class="form-group col-md-2">'
					 +'<label for="userName">Points<span class="text-danger">*</span></label>'
					 +' <input type="hidden" value="${qBean.sk_subquestion_id}" name="sk_datetime_id['+optionval+']">    <input type="text" name="subquestions['+optionval+'].date_points"  id="datePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
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
					
	                 
	                  +'</div></c:forEach></div>'); 
	 }else  if(val=="Date & Time"){
		 var answerwrapper   = $("#subquestiondatetime"+row);
		 $(".dateandtimemaindiv"+row).remove();
		 $(".datemaindiv"+row).remove();
			$(".sub_answer"+row).remove();
		 $(answerwrapper).append('<div class="dateandtimemaindiv'+row+'" style="padding: 20px;"><div class="row">'
				 +'<div class="form-group col-md-2">'
				 +'<label for="userName">Date:<span class="text-danger"></span></label>'  
				 +' </div>'
				 +'	<c:forEach var="qBean" items="${dependentsubquestionsDateTimeList}"><div class="form-group col-md-2">'
				 +'<label for="userName">Points<span class="text-danger">*</span></label>'
				 +'  <input type="hidden" value="${qBean.sk_subquestion_id}" name="sk_datetime_id['+optionval+']">  <input type="text" name="subquestions['+optionval+'].date_points"  id="datePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
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
	              +'</div></c:forEach></div>'); 
		 
		 
	 }else{
		 $(".dateandtimemaindiv"+row).remove();
		 $(".datemaindiv"+row).remove();
			$(".sub_answer"+row).remove();
	 }
	 }
 
 </script>
 
 
 
 
 
 
 
 
  <script type="text/javascript">
 function checkAnswerType(answertype){
	 
	 if(answertype=="Date & Time"){
		 $(".dateandtimemaindiv").remove();
		 $(".selectlistmaindiv").remove();
		 $(".datemaindiv").remove();
		 $(".datetime").append('<div class="dateandtimemaindiv" style="padding: 20px;"><div class="row">'
				 +'<div class="form-group col-md-2">'
				 +'<label for="userName">Date:<span class="text-danger"></span></label>'
				 +' </div>'
				 +'	<div class="form-group col-md-2">'
				 +'<label for="userName">Points<span class="text-danger">*</span></label>'
				 +'  <input type="hidden" value="${datetime_id}" name="sk_datetime_id">  <input type="text" value="${datepoints}" name="date_points" id="datePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
				 +' </div>'
				 +'<div class="form-group col-md-2">'
				 +'<label for="userName">Code<span class="text-danger">*</span></label>'
				 +'    <input type="text" value="${datecode}" name="date_code" id="dateCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">'
				 +'</div>'
				 +' <div class="form-group col-md-2">'
				 +' <label for="userName">Response<span class="text-danger">*</span></label>'
				 +'    <input type="text" value="${dateresponse}" name="date_response" id="dateResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
				 +'</div>'
				 +'  <div class="form-group col-md-3">'
				 +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
				 +'   <input type="text" value="${daterouting}"  name="date_routing" id="dateRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
				 +'</div>'
				 +'<br>'
                  +'<div class="form-group col-md-2">'
                  +' <label for="userName">Time:<span class="text-danger"></span></label>'
                  +'</div>' 
                  +'<div class="form-group col-md-2">'
                  +' <label for="userName">Points<span class="text-danger">*</span></label>'
                  +'   <input type="text" value="${timepints}" name="time_points" id="timePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
                  +' </div>'
                  +'<div class="form-group col-md-2">'
                  +' <label for="userName">Code<span class="text-danger">*</span></label>'
                  +'    <input type="text" value="${timecode}" name="time_code" id="timeCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">'
                  +' </div>'
                  +'<div class="form-group col-md-2">'
                  +'<label for="userName">Response<span class="text-danger">*</span></label>'
                  +'    <input type="text" value="${timeresponse}" name="time_response" id="timeResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
                  +' </div>'
                  +' <div class="form-group col-md-3">'
                  +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
                  +'   <input type="text" value="${timerouting}" name="time_routing" id="timeRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
                  +' </div>'
                  +'</div></div>'); 
	 }else
		 if(answertype=="Date"){
			 $(".dateandtimemaindiv").remove();
			 $(".selectlistmaindiv").remove();
			 $(".datemaindiv").remove();
			 $(".date").append('<div class="datemaindiv" style="padding: 20px;"><div class="row">'
					 +'<div class="form-group col-md-2">'
					 +'<label for="userName">Date:<span class="text-danger"></span></label>'
					 +' </div>'
					 +'	<div class="form-group col-md-2">'
					 +'<label for="userName">Points<span class="text-danger">*</span></label>'
					 +'  <input type="hidden" value="${datetime_id}" name="sk_datetime_id">  <input type="text" value="${datepoints}" name="date_points" id="datePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
					 +' </div>'
					 +'<div class="form-group col-md-2">'
					 +'<label for="userName">Code<span class="text-danger">*</span></label>'
					 +'    <input type="text" value="${datecode}" name="date_code" id="dateCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">'
					 +'</div>'
					 +' <div class="form-group col-md-2">'
					 +' <label for="userName">Response<span class="text-danger">*</span></label>'
					 +'    <input type="text" value="${dateresponse}" name="date_response" id="dateResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
					 +'</div>'
					 +'  <div class="form-group col-md-3">'
					 +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
					 +'   <input type="text" value="${daterouting}" name="date_routing" id="dateRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
					 +'</div>'
	                 +'</div></div>'); 
		 }else
		 
		 
		 if(answertype=="Select/List"){
		 $(".dateandtimemaindiv").remove();
		 $(".datemaindiv").remove();
		 $(".selectlistmaindiv").remove();
		 var max_fields      = 30; //maximum input boxes allowed
	        var wrapper         = $(".selectlist"); //Fields wrapper
	       
	    	$(wrapper).append('<div class="selectlistmaindiv"><div class="form-group col-md-2 pull-left"><label for="emailAddress">Answer<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Points<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Code<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Response<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Mandatory<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Routing Type<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Comment<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><button class="add_field_button btn btn-info">+</button></div></div>'); //add //on add input button click


		 $(wrapper).append('<div class="selectlistmaindiv"> <c:forEach var="qBean" items="${questionselectList}" varStatus="count"><div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="hidden" name="answerdata[${count.index}].sk_answer_id"  value="${qBean.sk_answer_id}" ><input type="text" name="answerdata[${count.index}].answer"  value="${qBean.answer}" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control answer_main"></div><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata[${count.index}].answer_points" value="${qBean.answer_points}" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control points_main"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="answerdata[${count.index}].answer_code" value="${qBean.answer_code}" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="answerdata[${count.index}].answer_response" id="response_main" parsley-trigger="change" value="${qBean.answer_response}"  placeholder="Enter Response" class="form-control response_main"></div><div class="form-group col-md-1 pull-left"><select name="answerdata[${count.index}].answer_optiontype"   id="mandatory_main" parsley-trigger="change"  class="form-control mandatory_main"><option value="${qBean.answer_optiontype}">${qBean.answer_optiontype}</option><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata[${count.index}].routing_type"  value="${qBean.routing_type}" id="routing_id" parsley-trigger="change"  placeholder="Enter Routing Type" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="answerdata[${count.index}].answer_comment" id="comment_main" parsley-trigger="change"  class="form-control"><option value="${qBean.answer_comment}">${qBean.answer_comment}</option><option value="Yes">Yes</option><option value="No">No</option></select></div><button data-id="${qBean.sk_answer_id}" value="${qBean.sk_answer_id}" onclick="deleteselectedanswer(this.value)" data-toggle="modal" data-target="#myModal" data-count="${count.index}" type="button" class="btnstyle">-</button></div></div> </c:forEach></div>');     	
	

		 
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

		 var add_button      = $(".add_field_button"); //Add button ID
		  var x = '${fn:length(questionselectList)}'-1; 
		 
	        $(add_button).on("click",function(e){
				
	             e.preventDefault();
	            if(x < max_fields){ //max input box allowed
	                x++; //text box increment
	                
	                $(wrapper).append('<div class="selectlistmaindiv"><div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="hidden" name="answerdata['+x+'].sk_answer_id"  value="no_id" ><input type="text" name="answerdata['+x+'].answer" id="answer_main" parsley-trigger="change" required placeholder="Enter Answer" class="form-control answer_main"></div><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata['+x+'].answer_points" id="points_main" parsley-trigger="change" required placeholder="Enter Points" class="form-control points_main"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="answerdata['+x+'].answer_code" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="answerdata['+x+'].answer_response" id="response_main" parsley-trigger="change"  placeholder="Enter Response" required class="form-control response_main"></div><div class="form-group col-md-1 pull-left"><select name="answerdata['+x+'].answer_optiontype" id="mandatory_main" parsley-trigger="change"  class="form-control mandatory_main"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata['+x+'].routing_type" id="routing_id" parsley-trigger="change"  placeholder="Enter Routing Type" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="answerdata['+x+'].answer_comment" id="comment_main" parsley-trigger="change"  class="form-control comment_main"><option value="Yes">Yes</option><option value="No">No</option></select></div><button class="remove_field">-</button></div></div></div>'); //add input box
	                
	            }
	            
	        });
	        
	        
	       
	        	 
	        
	        
	        $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
	            e.preventDefault(); 
	        $(this).parent('#main_answer').remove(); 
	        x--;
	        })
	        
	        $(".btnstyle").each(function() {
	        	if($(this).attr("data-count")=="0"){
	        		$(this).attr("disabled",true);
	        	}
	        });
	        
	 }else{
		 $(".dateandtimemaindiv").remove();
		 $(".selectlistmaindiv").remove();
	 }
 }

 </script>
 
 
<!--  to get SubSections  -->

<script>
 function getSubSection(section_id){
	 $("#sk_subsection_id").empty();
	 $.ajax({
         url: "<%=dashboardURL%>getSubsectionsById",
         type: "GET", 
           data: { 'section_id': section_id},
         success: function(response)
                     {
        	 $("#sk_subsection_id").append("<option value=''>Select Sub Section</option>") ;
	        	 $.each(response,function(k,v){
        		$("#sk_subsection_id").append("<option value="+v.sk_subsection_id+">"+v.subsection_name+"</option>") ;
        	 });
       	
                     }
	  });
 }
 
 
 function deleteselectedanswer(id){

	 var qid='${question_id}';
	 var value=id;
 	var url="<%=dashboardURL%>deleteanwerOptions/"+value+"/"+qid;
 	$(".btn-edit").attr("href",url);
 }
 
 </script>
 <script>
 function getSubSections(section_id){
	 $("#subsection_id").empty();
	 $.ajax({
         url: "<%=dashboardURL%>getSubsectionsById",
         type: "GET", 
           data: { 'section_id': section_id},
         success: function(response)
                     {
        	 $("#subsection_id").append("<option value=''>Select Sub Section</option>") ;
	        	 $.each(response,function(k,v){
        		$("#subsection_id").append("<option value="+v.sk_subsection_id+">"+v.subsection_name+"</option>") ;

	        	 
	        	 });
       	
                     }
	  });
 }
 
 </script>
 <script>
  function getstandardnumber(){
	  var section_id=$("#section_id").val();
	  var brand_id=$("#sk_brand_id").val();
	  var modeOfContact=$("#modeOfContact").val();
	  var subsection_id=$("#subsection_id").val();
	  $( "#standard_Number").empty();
  console.log(section_id+"="+brand_id +"="+ modeOfContact+"="+ +"="+subsection_id)
	$.ajax({
       url: "<%=dashboardURL%>getStandardNumberByIds",
       type: "GET", 
       data: {'section_id': section_id,'brand_id':brand_id,'modeOfContact':modeOfContact,'subsection_id':subsection_id},
       success: function(response)
                   {
       	$( "#standard_Number").append('<option value="">Select Standard Number</option>')
       	for(var i=0;i<response.length;i++){
       		$("#standard_Number").append('<option value="'+response[i].standard_number+'">'+response[i].standard_number+'</option>')
       	}
          }
     });
  }
  
  
  
  function getSuperQuestionAnswer(stdnum){
	  $( "#superquestionanswer").empty();
	$.ajax({
       url: "<%=dashboardURL%>getSuperQuestionAnswerBystdnum",
       type: "GET", 
       data: {'stdnum': stdnum},
       success: function(response)
                   {
       	$( "#superquestionanswer").append('<option value="">Select Answer</option>')
       	for(var i=0;i<response.length;i++){
       		$("#superquestionanswer").append('<option value="'+response[i].sk_answer_id+'">'+response[i].answer+'</option>')
       	}
          }
     });
  }
  
  
  </script>
  <script>
 function getSubSections(section_id,index){
	 $("#super_subsection_id_"+index).empty();
	 $.ajax({
         url: "<%=dashboardURL%>getSubsectionsById",
         type: "GET", 
           data: { 'section_id': section_id},
         success: function(response)
                     {
        	   $("#super_subsection_id_"+index).append("<option value=''>Select Sub Section</option>") ;
	        	 $.each(response,function(k,v){
        		$("#super_subsection_id_"+index).append("<option value="+v.sk_subsection_id+">"+v.subsection_name+"</option>") ;

	        	 
	        	 });
       	
                     }
	  });
 }
 
 
 
   function getstandardnumber1(index){
	  
	  var section_id=$("#super_section_id_"+index).val();
	  var brand_id=$("#sk_brand_id").val();
	  var modeOfContact=$("#modeOfContact").val();
	  var subsection_id=$("#super_subsection_id_"+index).val();
	  $( "#super_standard_Number_"+index).empty();
	  $( "#superquestionanswer_"+index).empty();
       console.log(section_id+"="+brand_id +"="+ modeOfContact+"="+ +"="+subsection_id)
	$.ajax({
       url: "<%=dashboardURL%>getStandardNumberByIds",
       type: "GET", 
       data: {'section_id': section_id,'brand_id':brand_id,'modeOfContact':modeOfContact,'subsection_id':subsection_id},
       success: function(response)
                   {
       	$( "#super_standard_Number_"+index).append('<option value="">Select Standard Number</option>')
       	for(var i=0;i<response.length;i++){
       		$("#super_standard_Number_"+index).append('<option value="'+response[i].standard_number+'">'+response[i].standard_number+' - '+response[i].year+'</option>')
       	}
          }
     });
  }
  
  
  function getSuperQuestionAnswer(stdnum,index){
	  var section_id=$("#super_section_id_"+index).val();
	  var brand_id=$("#sk_brand_id").val();
	  var modeOfContact=$("#modeOfContact").val();
	  var subsection_id=$("#super_subsection_id_"+index).val();
	  $( "#superquestionanswer_"+index).empty();
	$.ajax({
       url: "<%=dashboardURL%>getSuperQuestionAnswerBystdnum",
       type: "GET", 
       data: {'stdnum': stdnum,'section_id': section_id,'brand_id':brand_id,'modeOfContact':modeOfContact,'subsection_id':subsection_id},
       success: function(response)
                   {
       	$( "#superquestionanswer_"+index).append('<option value="">Select Answer</option>')
       	for(var i=0;i<response.length;i++){
       		$("#superquestionanswer_"+index).append('<option value="'+response[i].sk_answer_id+'">'+response[i].answer+'</option>')
       	}
          }
     });
  }
  
  
  
  
  
  
  
  
  

  </script>

 
    </body>
</html>