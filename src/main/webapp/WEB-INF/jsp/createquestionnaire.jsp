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
        <title>Mystery</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

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
		
		.remove_field{
		height: 35px;
		}
		</style>
		
    </head>

    <body>
    <div id="wrapper">
        <!-- Navigation Bar-->
        <jsp:include page="include/header.jsp"></jsp:include>
        <jsp:include page="include/sidemenu.jsp"></jsp:include>
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
                                    <h4 class="header-title m-t-0">Add Questions</h4>
                                    <p class="text-muted font-14 m-b-20">
                                    </p>

                                    <form method="POST" action="<%=dashboardURL%>createQuestionnairePost">
	                                     <div class="form-row">
	                                     <div  class="form-group col-md-3">
                                                <label for="userName">Brand<span class="text-danger">*</span></label>
                                                <select name="sk_brand_id" parsley-trigger="change" onchange="getstandardnumber();" required class="form-control" id="sk_brand_id" >
                                                  <option value="">Select Brand</option> 
                                                   <c:forEach var="dbBean" items="${activebrandList}">
                                    					 <option value="${dbBean.sk_brand_id}">${dbBean.brand_name }</option>
                                     					</c:forEach>
                                                </select>
                                            </div>
	                                     
	                                     		<div  class="form-group col-md-3">
		                                            <label for="userName">Mode Of Contact<span class="text-danger">*</span></label>
		                                            <select name="mode_of_contact" parsley-trigger="change" onchange="getstandardnumber();" required class="form-control" id="modeOfContact" >
		                                            	<option value="">Select Mode Of Contact</option> 
		                                            	<option value="All">All</option>      
		                                            	<option value="Email/Website">Email/Website</option>
		                                            	<option value="Telephone">Telephone</option>
		                                            	<option value="Walk In">Walk In</option>
		                                            	<option value="Online Sales Channel">Online Sales Channel</option>
		                                            	
		                                            </select>
		                                        </div>
		                                        
		                                        <div  class="form-group col-md-2">
		                                            <label for="userName">Section<span class="text-danger">*</span></label>
		                                            <select name="sk_section_id" parsley-trigger="change" required class="form-control" id="sk_section_id" onchange="getSubSection(this.value)">
		                                            	<option value="">Select Section</option>       
		                                            	 <c:forEach var="dbBean" items="${sectionList}">
                                    					 <option value="${dbBean.sk_section_id }">${dbBean.section_name }</option>
                                     					</c:forEach>
		                                            	
		                                            </select>
		                                        </div>
		                                        
		                                        <div  class="form-group col-md-2">
		                                            <label for="userName">Sub Section<span class="text-danger">*</span></label>
		                                            <select name="sk_subsection_id" parsley-trigger="change" required class="form-control" id="sk_subsection_id">
		                                           <option value="">Select Sub Section</option>       
		                                            </select>
		                                        </div>
		                                         <div  class="form-group col-md-2">
		                                            <label for="userName">Year<span class="text-danger">*</span></label>
		                                            <select name="year_applied" parsley-trigger="change" required class="form-control" id="year_applied">
		                                           <option value="">Select Year</option> 
		                                            <option value="2021">2021</option> 
		                                             <option value="2022">2022</option> 
		                                              <option value="2023">2023</option>
		                                              <option value="2024">2024</option> 
		                                              <option value="2025">2025</option>       
		                                            </select>
		                                        </div>
		                                        
		                                        <div  class="form-group col-md-3">
		                                            <label for="userName">Standard Number<span class="text-danger">*</span></label>
		                                            <input type="text" onkeyup="standardnumberexist()" name="standard_number" id="standardNumber" parsley-trigger="change" placeholder="Enter Standard Number" required class="form-control">
		                                        <span id="errormsg" style="color:red"></span>
                                				 <input type="text"   id="testbox" style="display: none">
		                                        </div>
	                                     
	                                     
		                                        <div  class="form-group col-md-3">
		                                            <label for="userName">Question Type<span class="text-danger">*</span></label>
		                                            <select name="question_type" parsley-trigger="change"  class="form-control" id="questionType" required onchange="checkQuestion(this.value)">
		                                            	<option value="">Select Question Type</option>       
		                                            	<option value="Main Question">Main Question</option>
		                                            	<option value="Main Question With Set Of SubQuestions">Main Question With Set Of SubQuestions</option>
		                                            	<option value="Dependent Question">Dependent Question</option>
		                                            	<option value="Dependent Question With Set Of SubQuestions">Dependent Question With Set Of SubQuestions</option>
		                                            	
		                                            </select>
		                                        </div>
		                                        <input type="hidden" name="formulFlag" id="formulaflag" value="no">
		                                        <div  class="form-group col-md-2">
		                                            <label for="userName">Question Mandatory<span class="text-danger">*</span></label>
		                                            <select name="question_optiontype" parsley-trigger="change"  class="form-control" id="questionOptionType">
		                                            	<option value="Mandatory">Mandatory</option>   
		                                            	<option value="Optional">Optional</option>   
		                                            </select>
		                                        </div>
		                                         <div class="form-group col-md-2">
		                                          <label for="userName">Points<span class="text-danger">*</span></label>
			                                            <input type="text" name="question_points" id="questionPoints" parsley-trigger="change" placeholder="Enter Points" required class="form-control">
			                                        </div>
			                                        <div class="form-group col-md-2">
			                                        <label for="userName">Code<span class="text-danger">*</span></label>
			                                            <input type="text" name="question_code" id="questionCode" parsley-trigger="change" placeholder="Enter Code" required class="form-control">
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
                                            <button class="btn btn-gradient waves-effect waves-light btnsubmit mainquestionbtn d-none" type="submit">
                                                Submit
                                            </button>
                                             <button class="btn btn-gradient waves-effect waves-light btnsubmit formulaupload continueupload subquestionbtn d-none"  type="submit">
                                                Continue To Upload Formula
                                            </button>
                                            <button  class="btn btn-light waves-effect m-l-5 btnsubmit subquestionbtn d-none" type="submit">
                                                Submit
                                            </button>
                                            <button type="reset" class="btn btn-light waves-effect m-l-5">
                                                Cancel
                                            </button>
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
         <!-- Standard Number existeor not -->
      <script type="text/javascript">
      
      
      $(".continueupload").on("click",function(){
    	  $("#formulaflag").val("yes");
      });
      
      
      
      
      $("#sk_brand_id,#modeOfContact,#sk_section_id,#sk_subsection_id").on("change",function(){
    	  var brand_id=$("#sk_brand_id").val();
    	  var modeOfContact=$("#modeOfContact").val();
    	  var section_id=$("#sk_section_id").val();
    	  var subsection_id=$("#sk_subsection_id").val();
    	  var standardNumber=$("#standardNumber").val();
    	  standardnumberexist();
      });
      
        
          $(".btnsubmit").on("click",function(){
      	  $('form').parsley();
        	if($("#errormsg").text()=="Standard Number is  already exist ...!"){
        		 $("#testbox").prop('required',true);
        		 setTimeout(function(){
           			var id=$("#testbox").attr("data-parsley-id");
           			  $("#parsley-id-"+id+"").hide();
           		}, 10);
        	}else{
        		 $("#testbox").prop('required',false);
        	}
         
       });
      
   
      function standardnumberexist(){
    	  var brand_id=$("#sk_brand_id").val();
    	  var modeOfContact=$("#modeOfContact").val();
    	  var section_id=$("#sk_section_id").val();
    	  var subsection_id=$("#sk_subsection_id").val();
    	  var year_applied=$("#year_applied").val();
    	  var standardNumber=$("#standardNumber").val();
      	 $.ajax({
               url: "<%=dashboardURL%>getStandardNumberExistance",
               type: "GET", 
                 data: { 'brand_id': brand_id,'modeOfContact':modeOfContact,'section_id':section_id,'subsection_id':subsection_id,'year_applied':year_applied,'standardNumber':standardNumber},
               success: function(response)
                           {
             	  var msg=response.message;
					   if(msg=="exist"){
						   $("#errormsg").text("Standard Number is  already exist ...!")
					   }else{
					var id=$("#testbox").attr("data-parsley-id");
				   $("#parsley-id-"+id+"").remove();
						   $("#errormsg").text("");
					   }
                           }
     	  });
      	 
      	
      }
      </script> 
        
        
        
        
     
 <script type="text/javascript">
 function checkQuestion(questiontype){
	 if(questiontype=="Main Question With Set Of SubQuestions" || questiontype=="Dependent Question With Set Of SubQuestions"){
		 
		 $(".subquestionbtn").removeClass("d-none");
		 $(".mainquestionbtn").addClass("d-none");
		 
	 }else{
		 $(".mainquestionbtn").removeClass("d-none");
		 $(".subquestionbtn").addClass("d-none");
	 }
	 
	 
	 
	 
	 if(questiontype=="Main Question"){
		 $(".mainquestions").remove();
		 $(".mainsubquestions").remove();
		 $(".dependentquestions").remove();
		 $(".dependentsubquestions").remove();
		 $(".mainquestion").append('<div class="mainquestions"><div class="form-group"><label for="emailAddress">Main Question<span class="text-danger">*</span></label>'
                 +'<input type="text" name="question" parsley-trigger="change" placeholder="Enter question" class="form-control" required id="question">'
                 +'</div>'
                 +'<div  class="form-group">'
                 +'  <label for="userName">Answer Type<span class="text-danger">*</span></label>'
                 +'  <select name="answer_type" required parsley-trigger="change" class="form-control" id="answerType" onchange="checkAnswerType(this.value)">'
                 +' 	<option value="">Select Answer Type</option> '      
                 +' 	<option value="Paragraph">Paragraph</option>'
                 +'  	<option value="Date">Date</option>'
                 +'  	<option value="Date & Time">Date & Time</option>'
                 +'  	<option value="Select/List">Select/List</option>'
                 +'   </select>'
                 +' </div>'
                 +'<div class="row"><div  class="form-group col-lg-6">'
                 +'  <label for="userName">Add Comment Box</label>'
                 +'  <select name="comment_mandatory" required parsley-trigger="change" class="form-control" id="comment_mandatory" >'
                 +' 	<option value="yes-mandatory">Yes-Mandatory</option>'
                 +'  	<option value="any-optional">Yes-Optional</option>'
                 +'  	<option value="no">No</option>'
                 +'   </select>'
                 +' </div>'
                 +'<div class="form-group col-lg-6 uploadfile" style="margin-top: 33px">'
                 +'<div class="checkbox checkbox-purple">'
                 +' <input id="uploadfile" name="upload_file" value="Yes" type="checkbox" >'
                 +'   <label for="uploadfile">'
                 +'   Upload File'
                 +' </label>'
                 +'</div></div>'
                 +'<div class="datetime"><div class="dateandtimemaindiv"></div></div>'
                 +'<div class="date"><div class="datemaindiv"></div></div>'
                 +'<div class="selectlist"><div class="selectlistmaindiv"></div></div></div>');
		
		 
		 
	 }else if(questiontype=="Main Question With Set Of SubQuestions"){
		 $(".mainquestions").remove();
		 $(".mainsubquestions").remove();
		 $(".dependentquestions").remove();
		 $(".dependentsubquestions").remove();
		 $(".mainquestionwithsetofsubQuestions").append('<div class="mainsubquestions"><div class="form-group"><label for="emailAddress">Main Question<span class="text-danger">*</span></label>'
                 +'<input type="text" name="question" parsley-trigger="change" placeholder="Enter question" class="form-control" required id="question">'
                 +'</div>'
                 +'<div class="row"><div  class="form-group col-lg-6">'
                 +'  <label for="userName">Add Comment Box</label>'
                 +'  <select name="comment_mandatory" required parsley-trigger="change" class="form-control" id="comment_mandatory" >'
                 +' 	<option value="yes-mandatory">Yes-Mandatory</option>'
                 +'  	<option value="any-optional">Yes-Optional</option>'
                 +'  	<option value="no">No</option>'
                 +'   </select>'
                 +' </div>'
                 +'<div class="form-group col-lg-6 uploadfile" style="margin-top: 33px">'
                 +'<div class="checkbox checkbox-purple">'
                 +' <input id="uploadfile" name="upload_file" value="Yes" type="checkbox" >'
                 +'   <label for="uploadfile">'
                 +'   Upload File'
                 +' </label>'
                 +'</div></div>'
                 +'<div class="col-lg-12" id="main_question_sub" style="display: block;">'
                 +' <div id="accordion" class="m-b-30 subquestions">'
                 +' <button class="add_field_button2 btn btn-info" type="button">+</button>'
                 +' <div class="card" id="main_answer_1"><div class="card-header" id="heading"><h6 class="m-0"><a href="#collapse1" class="text-dark" data-toggle="collapse" aria-expanded="true" aria-controls="collapse1">Sub Question #1 </a></h6></div><div id="collapse1" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion"><div class="card-body"><div class="form-group col-md-1 pull-left"><label for="emailAddress">SLNo<span class="text-danger">*</span></label><input type="text" name="question_sl_1" parsley-trigger="change" placeholder="Enter question" class="form-control" id="question_sl_1" value="1"></div><div class="form-group col-md-5 pull-left"><label for="emailAddress">Sub Question 1<span class="text-danger">*</span></label><input type="text" name="subquestions[0].subQuestion" parsley-trigger="change" placeholder="Enter question" class="form-control subquestioninput" id="question_1" required=""></div><div class="form-group col-md-4 pull-left"><label for="userName">Answer Type<span class="text-danger">*</span></label><select name="subquestions[0].subQuestionAnswerType" parsley-trigger="change" class="form-control subquestionanswertypeinput" id="question_type_1" onchange="checkSubsetQuestionType(this.value,1,0)" required=""><option value="">Select Answer Type</option><option value="Paragraph">Paragraph</option><option value="Date">Date</option><option value="Date &amp; Time">Date &amp; Time</option><option value="Select/List">Select/List</option></select></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Upload File<span class="text-danger">*</span></label><input value="Yes" type="checkbox" name="subquestions[0].uploadFile" class="form-control" id="subquestionuploadfile1"></div><div class="form-row col-md-12" id="subquestiondate1" ></div><div class="form-row col-md-12" id="subquestiondatetime1" ></div><div class="form-row col-md-12" id="subquestiontype_list1" ></div></div></div></div></div></div>'
                 +'</div><div class="form-group col-lg-6" id="formula"></div>');
		 
		 var x = 0; 
		 var z = 0; 
	        var y=0;
	        var alphabets=65;
	        var add_button      = $(".add_field_button2"); //Add button ID
	        var max_fields      = 30; //maximum input boxes allowed
	        var wrapper         = $(".subquestions"); //Fields wrapper
	        $(add_button).on("click",function(e){
				
	             e.preventDefault();
	            if(x < max_fields){ //max input box allowed
	                x++; //text box increment
	                y=x+1;
	                u=0;
	                z++;
	                $(wrapper).append('<div class="card" id="main_answer_'+y+'" ><div class="card-header d-flex" id="heading"><h6 class="m-0 mr-auto"><a href="#collapse'+y+'" class="text-dark" data-toggle="collapse" aria-expanded="true" aria-controls="collapse'+y+'">Sub Question #'+y+' </a></h6><button class="remove_field pull-right btn btn-info" >-</button></div><div id="collapse'+y+'" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion"><div class="card-body"><div class="form-group col-md-1 pull-left"><label for="emailAddress">SLNo<span class="text-danger">*</span></label><input type="text" name="question_sl_'+y+'" parsley-trigger="change" placeholder="Enter question" class="form-control" id="question_sl_'+y+'" value="'+y+'"></div><div class="form-group col-md-5 pull-left"><label for="emailAddress">Sub Question '+y+'<span class="text-danger">*</span></label><input type="text" name="subquestions['+x+'].subQuestion" parsley-trigger="change" required placeholder="Enter question" class="form-control subquestioninput" id="question_'+y+'"></div><div  class="form-group col-md-4 pull-left"><label for="userName">Answer Type<span class="text-danger">*</span></label><select name="subquestions['+x+'].subQuestionAnswerType" parsley-trigger="change" required class="form-control subquestionanswertypeinput" id="question_type_'+y+'" onchange="checkSubsetQuestionType(this.value,'+y+','+z+')"><option value="">Select Answer Type</option><option value="Paragraph">Paragraph</option><option value="Date">Date</option><option value="Date & Time">Date & Time</option><option value="Select/List">Select/List</option></select></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Upload File<span class="text-danger">*</span></label><input value="Yes" type="checkbox" name="subquestions['+y+'].uploadFile" class="form-control" id="subquestionuploadfile'+y+'"></div><div class="form-row col-md-12" id="subquestiontype_list'+y+'" ></div><div class="form-row col-md-12" id="subquestiondate'+y+'" ></div><div class="form-row col-md-12" id="subquestiondatetime'+y+'" ></div></div></div></div><div class="form-group col-lg-6" id="formula"></div>'); //add input box
	            }
	        });
	        
	        $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
	            
	            e.preventDefault(); 
	            $(this).closest('.card').remove();
	            });
	          
		 
	 } else if(questiontype=="Dependent Question"){
		 $('select').select2();
		 $(".mainquestions").remove();
		 $(".mainsubquestions").remove();
		 $(".dependentquestions").remove();
		 $(".dependentsubquestions").remove();
		 $(".dependentquestion").append('<div class="dependentquestions"><button class="add_field_button3 btn btn-info superQuetionAddRow">+</button><div class="form-row"><div  class="form-group col-md-3 pull-left"><label for="userName">Super Question<span class="text-danger">*</span></label></div><div class="form-group col-md-3 pull-left"></div><div  class="form-group col-md-3 pull-left"></div><div  class="form-group col-md-3 pull-left"><label for="userName">Answer<span class="text-danger">*</span></label></div></div>'
				 +'<div class="superquestionappend"><div  class="form-group col-md-3 pull-left"><select name="superquestions[0].super_question_section_id" parsley-trigger="change"onchange="getSubSections(this.value,1);getstandardnumber1(1);" class="form-control dependentsection" id="super_section_id_1"><option value="">Select Section</option>'
				 +'<c:forEach var="dbBean" items="${sectionList}">'
				 +'<option value="${dbBean.sk_section_id }">${dbBean.section_name }</option>'
				 +'</c:forEach>'
				 +' </select></div><div  class="form-group col-md-3 pull-left"><select name="superquestions[0].super_question_subsection_id" parsley-trigger="change" class="form-control dependentsubsection" onchange="getstandardnumber1(1);" id="super_subsection_id_1"><option value="">Select Sub Section</option></select></div><div  class="form-group col-md-3 pull-left"><select name="superquestions[0].super_question_standard_number" parsley-trigger="change" class="form-control dependentstandardnum" onchange="getSuperQuestionAnswer(this.value,1);" required id="super_standard_Number_1"><option value="">Select Standard Number</option></select></div><div class="form-group col-md-3 pull-left">'
				 +' <select id="superquestionanswer_1" name="superquestions[0].super_question_answer" required class="form-control select2 form-control select2-multiple" multiple data-placeholder="Choose Answers.." data-parsley-errors-container="#chke">'
				 +' </select><div id="chke"></div>'
				 +'</div></div><div class="form-group"><label for="emailAddress"><br>Main Question<span class="text-danger">*</span></label>'
                 +'<input type="text" name="question" parsley-trigger="change" placeholder="Enter question" class="form-control" required id="question">'
                 +'</div>'
                 +'<div  class="form-group">'
                 +'  <label for="userName">Answer Type<span class="text-danger">*</span></label>'
                 +'  <select name="answer_type" required parsley-trigger="change" class="form-control" id="answerType" onchange="checkAnswerType(this.value)">'
                 +' 	<option value="">Select Answer Type</option> '      
                 +' 	<option value="Paragraph">Paragraph</option>'
                 +'  	<option value="Date">Date</option>'
                 +'  	<option value="Date & Time">Date & Time</option>'
                 +'  	<option value="Select/List">Select/List</option>'
                 +'   </select>'
                 +' </div>'
                 +'<div class="row"><div  class="form-group col-lg-6">'
                 +'  <label for="userName">Add Comment Box</label>'
                 +'  <select name="comment_mandatory" required parsley-trigger="change" required class="form-control" id="comment_mandatory" >'
                 +' 	<option value="yes-mandatory">Yes-Mandatory</option>'
                 +'  	<option value="any-optional">Yes-Optional</option>'
                 +'  	<option value="no">No</option>'
                 +'   </select>'
                 +' </div>'
                 +'<div class="form-group col-lg-6 uploadfile" style="margin-top: 33px">'
                 +'<div class="checkbox checkbox-purple">'
                 +' <input id="uploadfile" name="upload_file" value="Yes" type="checkbox" >'
                 +'   <label for="uploadfile">'
                 +'   Upload File'
                 +' </label>'
                 +'</div></div>'
                 +'<div class="datetime"><div class="dateandtimemaindiv"></div></div>'
                 +'<div class="date"><div class="datemaindiv"></div></div>'
                 +'<div class="selectlist"><div class="selectlistmaindiv"></div></div></div>');
		 var x=1;
		 $(".superQuetionAddRow").on("click",function(){
			 x=x+1;
			 var y=x-1;
			 $(".superquestionappend").append('<div class="form-row superQuestionRow"><div  class="form-group col-md-3 pull-left"><select name="superquestions['+y+'].super_question_section_id" parsley-trigger="change"onchange="getSubSections(this.value,'+x+');" class="form-control dependentsection" id="super_section_id_'+x+'"><option value="">Select Section</option>'
	                 +'<c:forEach var="dbBean" items="${sectionList}">'
		               +'<option value="${dbBean.sk_section_id }">${dbBean.section_name }</option>'
		               +'</c:forEach>'
		               +' </select></div><div  class="form-group col-md-3 pull-left"><select name="superquestions['+y+'].super_question_subsection_id" parsley-trigger="change" class="form-control dependentsubsection" onchange="getstandardnumber1('+x+');" id="super_subsection_id_'+x+'"><option value="">Select Sub Section</option></select></div><div  class="form-group col-md-3 pull-left"><select name="superquestions['+y+'].super_question_standard_number" parsley-trigger="change" class="form-control dependentstandardnum" onchange="getSuperQuestionAnswer(this.value,'+x+');" required id="super_standard_Number_'+x+'"><option value="">Select Standard Number</option></select></div><div  class="form-group col-md-2 pull-left">'
		               +' <select id="superquestionanswer_'+x+'" name="superquestions['+y+'].super_question_answer" required class="form-control select2 form-control select2-multiple" multiple data-placeholder="Choose Answers.." data-parsley-errors-container="#chke">'
		               +' </select> <div id="chke"></div></div><div  class="form-group col-md-1 pull-left"><button class="remove_field pull-right btn btn-info" >-</button></div>');
			     $('select').select2();
				 $(".superQuestionRow").on("click",".remove_field", function(e){
					    //user click on remove text
			            e.preventDefault(); 
			            $(this).closest('.superQuestionRow').remove();
			            });
			 });
	         $('select').select2();
		 
		
		 
	 }else  if(questiontype=="Dependent Question With Set Of SubQuestions"){
		 $('select').select2();
		 $(".mainquestions").remove();
		 $(".mainsubquestions").remove();
		 $(".dependentquestions").remove();
		 $(".dependentsubquestions").remove();
		 $(".dependentquestionwithsetofsubQuestions").append('<div class="dependentsubquestions"><button class="add_field_button3 btn btn-info superQuetionAddRow">+</button><div class="form-row"><div  class="form-group col-md-3 pull-left"><label for="userName">Super Question<span class="text-danger">*</span></label></div><div class="form-group col-md-3 pull-left"></div><div  class="form-group col-md-3 pull-left"></div><div  class="form-group col-md-3 pull-left"><label for="userName">Answer<span class="text-danger">*</span></label></div></div>'
				 +'<div class="superquestionappend"><div  class="form-group col-md-3 pull-left"><select name="superquestions[0].super_question_section_id" parsley-trigger="change"onchange="getSubSections(this.value,1);getstandardnumber1(1);" class="form-control dependentsection" id="super_section_id_1"><option value="">Select Section</option>'
				 +'<c:forEach var="dbBean" items="${sectionList}">'
				 +'<option value="${dbBean.sk_section_id }">${dbBean.section_name }</option>'
				 +'</c:forEach>'
				 +' </select></div><div  class="form-group col-md-3 pull-left"><select name="superquestions[0].super_question_subsection_id" parsley-trigger="change" class="form-control dependentsubsection" onchange="getstandardnumber1(1);" id="super_subsection_id_1"><option value="">Select Sub Section</option></select></div><div  class="form-group col-md-3 pull-left"><select name="superquestions[0].super_question_standard_number" parsley-trigger="change" class="form-control dependentstandardnum" onchange="getSuperQuestionAnswer(this.value,1);" required id="super_standard_Number_1"><option value="">Select Standard Number</option></select></div><div class="form-group col-md-3 pull-left">'
				 +' <select id="superquestionanswer_1" name="superquestions[0].super_question_answer" required class="form-control select2 form-control select2-multiple" multiple data-placeholder="Choose Answers.." data-parsley-errors-container="#chke">'
				 +' </select><div id="chke"></div>'
				 +'</div></div><div class="form-group"><label for="emailAddress"><br>Main Question<span class="text-danger">*</span></label>'
                 +'<input type="text" name="question" parsley-trigger="change" placeholder="Enter question" class="form-control" required id="question">'
                 +'</div>'
                 +'<div class="row"><div  class="form-group col-lg-6">'
                 +'  <label for="userName">Add Comment Box</label>'
                 +'  <select name="comment_mandatory" required parsley-trigger="change" class="form-control" id="comment_mandatory" >'
                 +' 	<option value="yes-mandatory">Yes-Mandatory</option>'
                 +'  	<option value="any-optional">Yes-Optional</option>'
                 +'  	<option value="no">No</option>'
                 +'   </select>'
                 +' </div>'
                 +'<div class="form-group col-lg-6 uploadfile" style="margin-top: 33px">'
                 +'<div class="checkbox checkbox-purple">'
                 +' <input id="uploadfile" name="upload_file" value="Yes" type="checkbox" >'
                 +'   <label for="uploadfile">'
                 +'   Upload File'
                 +' </label>'
                 +'</div></div>'
                 +'<div class="col-lg-12" id="main_question_sub" style="display: block;">'
                 +' <div id="accordion" class="m-b-30 subquestions">'
                 +' <button class="add_field_button2 btn btn-info" type="button">+</button>'
                 +' <div class="card" id="main_answer_1"><div class="card-header" id="heading"><h6 class="m-0"><a href="#collapse1" class="text-dark" data-toggle="collapse" aria-expanded="true" aria-controls="collapse1">Sub Question #1 </a></h6></div><div id="collapse1" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion"><div class="card-body"><div class="form-group col-md-1 pull-left"><label for="emailAddress">SLNo<span class="text-danger">*</span></label><input type="text" name="question_sl_1" parsley-trigger="change" placeholder="Enter question" class="form-control" id="question_sl_1" value="1"></div><div class="form-group col-md-5 pull-left"><label for="emailAddress">Sub Question 1<span class="text-danger">*</span></label><input type="text" name="subquestions[0].subQuestion" parsley-trigger="change" placeholder="Enter question" class="form-control subquestioninput" id="question_1" required=""></div><div class="form-group col-md-4 pull-left"><label for="userName">Answer Type<span class="text-danger">*</span></label><select name="subquestions[0].subQuestionAnswerType" parsley-trigger="change" class="form-control subquestionanswertypeinput" id="question_type_1" onchange="checkSubsetQuestionType(this.value,1,0)" required=""><option value="">Select Answer Type</option><option value="Paragraph">Paragraph</option><option value="Date">Date</option><option value="Date &amp; Time">Date &amp; Time</option><option value="Select/List">Select/List</option></select></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Upload File<span class="text-danger">*</span></label><input value="Yes" type="checkbox" name="subquestions[0].uploadFile" class="form-control" id="subquestionuploadfile1"></div><div class="form-row col-md-12" id="subquestiondate1" ></div><div class="form-row col-md-12" id="subquestiondatetime1" ></div><div class="form-row col-md-12" id="subquestiontype_list1" ></div></div></div></div></div></div>'
                 +'</div><div class="form-group col-lg-6" id="formula"></div>');
		 $('select').select2();
		 var x = 0; 
	        var y=0;
	        var z=0;
	        var alphabets=65;
	        var add_button      = $(".add_field_button2"); //Add button ID
	        var max_fields      = 30; //maximum input boxes allowed
	        var wrapper         = $(".subquestions"); //Fields wrapper
	        $(add_button).on("click",function(e){
				
	             e.preventDefault();
	            if(x < max_fields){ //max input box allowed
	                x++; //text box increment
	                y=x+1;
	                u=0;
	                z++;
	                $(wrapper).append('<div class="card" id="main_answer_'+y+'" ><div class="card-header d-flex" id="heading"><h6 class="m-0 mr-auto"><a href="#collapse'+y+'" class="text-dark" data-toggle="collapse" aria-expanded="true" aria-controls="collapse'+y+'">Sub Question #'+y+' </a></h6><button class="remove_field pull-right btn btn-info" >-</button></div><div id="collapse'+y+'" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion"><div class="card-body"><div class="form-group col-md-1 pull-left"><label for="emailAddress">SLNo<span class="text-danger">*</span></label><input type="text" name="question_sl_'+y+'" parsley-trigger="change" placeholder="Enter question" class="form-control" id="question_sl_'+y+'" value="'+y+'"></div><div class="form-group col-md-5 pull-left"><label for="emailAddress">Sub Question '+y+'<span class="text-danger">*</span></label><input type="text" name="subquestions['+x+'].subQuestion" parsley-trigger="change" required placeholder="Enter question" class="form-control subquestioninput" id="question_'+y+'"></div><div  class="form-group col-md-4 pull-left"><label for="userName">Answer Type<span class="text-danger">*</span></label><select name="subquestions['+x+'].subQuestionAnswerType" parsley-trigger="change" required class="form-control subquestionanswertypeinput" id="question_type_'+y+'" onchange="checkSubsetQuestionType(this.value,'+y+','+z+')"><option value="">Select Answer Type</option><option value="Paragraph">Paragraph</option><option value="Date">Date</option><option value="Date & Time">Date & Time</option><option value="Select/List">Select/List</option></select></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Upload File<span class="text-danger">*</span></label><input value="Yes" type="checkbox" name="subquestions['+x+'].uploadFile" class="form-control" id="subquestionuploadfile'+x+'"></div><div class="form-row col-md-12" id="subquestiontype_list'+y+'" ></div></div><div class="form-row col-md-12" id="subquestiondate'+y+'" ></div><div class="form-row col-md-12" id="subquestiondatetime'+y+'" ></div></div></div></div><div class="form-group col-lg-6" id="formula"></div>'); //add input box
	            }
	        });
	        
	        $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
	            e.preventDefault(); 
	            $(this).closest('.card').remove();
	            });
	        var x=1;
			 $(".superQuetionAddRow").on("click",function(){
				 x=x+1;
				 var y=x-1;
				 $(".superquestionappend").append('<div class="form-row superQuestionRow"><div  class="form-group col-md-3 pull-left"><select name="superquestions['+y+'].super_question_section_id" parsley-trigger="change"onchange="getSubSections(this.value,'+x+');" class="form-control dependentsection" id="super_section_id_'+x+'"><option value="">Select Section</option>'
		                 +'<c:forEach var="dbBean" items="${sectionList}">'
			               +'<option value="${dbBean.sk_section_id }">${dbBean.section_name }</option>'
			               +'</c:forEach>'
			               +' </select></div><div  class="form-group col-md-3 pull-left"><select name="superquestions['+y+'].super_question_subsection_id" parsley-trigger="change" class="form-control dependentsubsection" onchange="getstandardnumber1('+x+');" id="super_subsection_id_'+x+'"><option value="">Select Sub Section</option></select></div><div  class="form-group col-md-3 pull-left"><select name="superquestions['+y+'].super_question_standard_number" parsley-trigger="change" class="form-control dependentstandardnum" onchange="getSuperQuestionAnswer(this.value,'+x+');" required id="super_standard_Number_'+x+'"><option value="">Select Standard Number</option></select></div><div  class="form-group col-md-2 pull-left">'
			               +' <select id="superquestionanswer_'+x+'" name="superquestions['+y+'].super_question_answer" required class="form-control select2 form-control select2-multiple" multiple data-placeholder="Choose Answers.." data-parsley-errors-container="#chke">'
			               +' </select> <div id="chke"></div></div><div  class="form-group col-md-1 pull-left"><button class="remove_field pull-right btn btn-info" >-</button></div>');
				     $('select').select2();
					 $(".superQuestionRow").on("click",".remove_field", function(e){
						    //user click on remove text
				            e.preventDefault(); 
				            $(this).closest('.superQuestionRow').remove();
				            });
				 });
		         $('select').select2();
			 
			
		 
		 
	 }else{
		 $(".mainquestions").remove();
		 $(".mainsubquestions").remove();
		 $(".dependentquestions").remove();
		 $(".dependentsubquestions").remove();
	 }
 }
 
 
 
 //main question with set of subquestions options selectlist
 
 
 
 </script>
 <script>
 //for sub set questions
 function checkSubsetQuestionType(val,row,optionval){
		if(val=="Select/List"){
			 $(".dateandtimemaindiv"+row).remove();
			 $(".datemaindiv"+row).remove();
			var answerwrapper   = $("#subquestiontype_list"+row);
	    	$(answerwrapper).append('<div class="form-row  sub_answer'+row+' col-md-12" id="sub_answer"><button class="add_field_button3 btn btn-info" id="buttonanswer1" type="button">+</button><div class="form-group col-md-2 pull-left"><label for="emailAddress">Answer<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Points<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Code<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Response<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Mandatory<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Comment<span class="text-danger">*</span></label></div></div>');
	    	$(answerwrapper).append('<div class="form-row sub_answer'+row+' col-md-12" id="sub_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[0].answer" id="subanswer_main1" parsley-trigger="change"  placeholder="Enter Answer" class="form-control sub subquestionanswerinput"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[0].answer_points" id="subanswerpoints_main1" parsley-trigger="change"  placeholder="Enter Points" class="form-control subquestionpointsinput"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[0].answer_code" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers[0].answer_response" id="subanswerresponse_main1" parsley-trigger="change"  placeholder="Enter Response" class="form-control subquestionresponseinput"></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers[0].answer_optiontype" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers[0].answer_comment" required id="comment_main" parsley-trigger="change" required  class="form-control"><option value="Yes">Yes</option><option value="No" selected>No</option></select></div></div></div>');
			
	 
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
		            	$(answerwrapper).append('<div class="form-row sub_answer'+row+' col-md-12" id="sub_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer" id="subanswer_main1" parsley-trigger="change"  placeholder="Enter Answer" class="form-control sub subquestionanswerinput"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_points" id="subanswerpoints_main1" parsley-trigger="change"  placeholder="Enter Points" class="form-control subquestionpointsinput"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_code" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_response" id="subanswerresponse_main1" parsley-trigger="change"  placeholder="Enter Response" class="form-control subquestionresponseinput"></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_optiontype" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestions['+optionval+'].subquestionanswers['+x+'].answer_comment" required id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No" selected>No</option></select></div><button class="remove_field">-</button></div></div>');
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
					 +'	<div class="form-group col-md-2">'
					 +'<label for="userName">Points<span class="text-danger">*</span></label>'
					 +'    <input type="text" name="subquestions['+optionval+'].date_points" required id="datePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
					 +' </div>'
					 +'<div class="form-group col-md-2">'
					 +'<label for="userName">Code<span class="text-danger">*</span></label>'
					 +'    <input type="text" name="subquestions['+optionval+'].date_code" required id="dateCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">'
					 +'</div>'
					 +' <div class="form-group col-md-2">'
					 +' <label for="userName">Response<span class="text-danger">*</span></label>'
					 +'    <input type="text" name="subquestions['+optionval+'].date_response" required id="dateResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
					 +'</div>'
					 +'  <div class="form-group col-md-3">'
					 +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
					 +'   <input type="text" name="subquestions['+optionval+'].date_routing" required id="dateRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
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
				 +'    <input type="text" name="subquestions['+optionval+'].date_points" required id="datePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
				 +' </div>'
				 +'<div class="form-group col-md-2">'
				 +'<label for="userName">Code<span class="text-danger">*</span></label>'
				 +'    <input type="text" name="subquestions['+optionval+'].date_code" required id="dateCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">'
				 +'</div>'
				 +' <div class="form-group col-md-2">'
				 +' <label for="userName">Response<span class="text-danger">*</span></label>'
				 +'    <input type="text" name="subquestions['+optionval+'].date_response" required id="dateResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
				 +'</div>'
				 +'  <div class="form-group col-md-3">'
				 +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
				 +'   <input type="text" name="subquestions['+optionval+'].date_routing" required id="dateRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
				 +'</div>'
				 +'<br>'
	              +'<div class="form-group col-md-2">'
	              +' <label for="userName">Time:<span class="text-danger"></span></label>'
	              +'</div>'
	              +'<div class="form-group col-md-2">'
	              +' <label for="userName">Points<span class="text-danger">*</span></label>'
	              +'   <input type="text" name="subquestions['+optionval+'].time_points" required id="timePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
	              +' </div>'
	              +'<div class="form-group col-md-2">'
	              +' <label for="userName">Code<span class="text-danger">*</span></label>'
	              +'    <input type="text" name="subquestions['+optionval+'].time_code" required id="timeCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">'
	              +' </div>'
	              +'<div class="form-group col-md-2">'
	              +'<label for="userName">Response<span class="text-danger">*</span></label>'
	              +'    <input type="text" name="subquestions['+optionval+'].time_response" required id="timeResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
	              +' </div>'
	              +' <div class="form-group col-md-3">'
	              +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
	              +'   <input type="text" name="subquestions['+optionval+'].time_routing" required id="timeRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
	              +' </div>'
	              +'</div></div>'); 
		 
		 
	 }else{
		 
		 $(".dateandtimemaindiv"+row).remove();
		 $(".datemaindiv"+row).remove();
			$(".sub_answer"+row).remove();
	 }
	 }
 
 </script>
 
 
 <script>
function checkSubQuestionType(val,row){
	if(val=="Select/List"){
		 $(".dateandtimemaindiv"+row).remove();
		 $(".datemaindiv"+row).remove();
		var answerwrapper   = $("#subquestiontype_list"+row);
    	$(answerwrapper).append('<div class="form-row  sub_answer'+row+' col-md-12" id="sub_answer"><button class="add_field_button3 btn btn-info" id="buttonanswer1" type="button">+</button><div class="form-group col-md-2 pull-left"><label for="emailAddress">Answer<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Points<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Code<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Response<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Mandatory<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Comment<span class="text-danger">*</span></label></div></div>');
    	$(answerwrapper).append('<div class="form-row sub_answer'+row+' col-md-12" id="sub_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions[0].subquestionanswers[0].answer" id="subanswer_main1" parsley-trigger="change"  placeholder="Enter Answer" class="form-control sub subquestionanswerinput"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions[0].subquestionanswers[0].answer_points" id="subanswerpoints_main1" parsley-trigger="change"  placeholder="Enter Points" class="form-control subquestionpointsinput"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions[0].subquestionanswers[0].answer_code" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions[0].subquestionanswers[0].answer_response" id="subanswerresponse_main1" parsley-trigger="change"  placeholder="Enter Response" class="form-control subquestionresponseinput"></div><div class="form-group col-md-2 pull-left"><select name="subquestions[0].subquestionanswers[0].answer_optiontype" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestions[0].subquestionanswers[0].answer_comment" id="comment_main" parsley-trigger="change" required class="form-control"><option value="Yes">Yes</option><option value="No" selected>No</option></select></div></div></div>');
		
 
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
	            	$(answerwrapper).append('<div class="form-row sub_answer'+row+' col-md-12" id="sub_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions[0].subquestionanswers[0].answer" id="subanswer_main1" parsley-trigger="change"  placeholder="Enter Answer" class="form-control sub subquestionanswerinput"></div><div class="form-group col-md-2 pull-left"><input type="text" name="subquestions[0].subquestionanswers[0].answer_points" id="subanswerpoints_main1" parsley-trigger="change"  placeholder="Enter Points" class="form-control subquestionpointsinput"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions[0].subquestionanswers[0].answer_code" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="subquestions[0].subquestionanswers[0].answer_response" id="subanswerresponse_main1" parsley-trigger="change"  placeholder="Enter Response" class="form-control subquestionresponseinput"></div><div class="form-group col-md-2 pull-left"><select name="subquestions[0].subquestionanswers[0].answer_optiontype" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><select name="subquestions[0].subquestionanswers[0].answer_comment" required id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No" selected>No</option></select></div><button class="remove_field">-</button></div></div>');
	            }
	        });
	        
	        $(answerwrapper).on("click",".remove_field", function(e){ //user click on remove text
	            
	        	   e.preventDefault(); 
	   	        $(this).parent('#sub_answer').remove(); 
	   	        x--;
	            });
	          
	
	
	}else if(val=="Date"){
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
				 +'    <input type="text" name="date_points" id="datePoints" required parsley-trigger="change" placeholder="Enter Points" class="form-control">'
				 +' </div>'
				 +'<div class="form-group col-md-2">'
				 +'<label for="userName">Code<span class="text-danger">*</span></label>'
				 +'    <input type="text" name="date_code" id="dateCode" required parsley-trigger="change" placeholder="Enter Code" class="form-control">'
				 +'</div>'
				 +' <div class="form-group col-md-2">'
				 +' <label for="userName">Response<span class="text-danger">*</span></label>'
				 +'    <input type="text" name="date_response" required id="dateResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
				 +'</div>'
				 +'  <div class="form-group col-md-3">'
				 +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
				 +'   <input type="text" name="date_routing" required id="dateRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
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
			 +'    <input type="text" name="date_points" id="datePoints" required parsley-trigger="change" placeholder="Enter Points" class="form-control">'
			 +' </div>'
			 +'<div class="form-group col-md-2">'
			 +'<label for="userName">Code<span class="text-danger">*</span></label>'
			 +'    <input type="text" name="date_code" id="dateCode" required parsley-trigger="change" placeholder="Enter Code" class="form-control">'
			 +'</div>'
			 +' <div class="form-group col-md-2">'
			 +' <label for="userName">Response<span class="text-danger">*</span></label>'
			 +'    <input type="text" name="date_response" required id="dateResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
			 +'</div>'
			 +'  <div class="form-group col-md-3">'
			 +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
			 +'   <input type="text" name="date_routing" required id="dateRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
			 +'</div>'
			 +'<br>'
              +'<div class="form-group col-md-2">'
              +' <label for="userName">Time:<span class="text-danger"></span></label>'
              +'</div>'
              +'<div class="form-group col-md-2">'
              +' <label for="userName">Points<span class="text-danger">*</span></label>'
              +'   <input type="text" name="time_points" required id="timePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
              +' </div>'
              +'<div class="form-group col-md-2">'
              +' <label for="userName">Code<span class="text-danger">*</span></label>'
              +'    <input type="text" name="time_code" required id="timeCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">'
              +' </div>'
              +'<div class="form-group col-md-2">'
              +'<label for="userName">Response<span class="text-danger">*</span></label>'
              +'    <input type="text" name="time_response" required id="timeResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
              +' </div>'
              +' <div class="form-group col-md-3">'
              +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
              +'   <input type="text" name="time_routing" required id="timeRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
              +' </div>'
              +'</div></div>'); 
	 
	 
 }
 }
 </script>
 
 
 
 <script type="text/javascript">
 function checkAnswerType(answertype){
	 //main question and dependent question
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
				 +'    <input type="text" name="date_points" required id="datePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
				 +' </div>'
				 +'<div class="form-group col-md-2">'
				 +'<label for="userName">Code<span class="text-danger">*</span></label>'
				 +'    <input type="text" name="date_code" required id="dateCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">'
				 +'</div>'
				 +' <div class="form-group col-md-2">'
				 +' <label for="userName">Response<span class="text-danger">*</span></label>'
				 +'    <input type="text" name="date_response" required id="dateResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
				 +'</div>'
				 +'  <div class="form-group col-md-3">'
				 +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
				 +'   <input type="text" name="date_routing" required id="dateRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
				 +'</div>'
				 +'<br>'
                  +'<div class="form-group col-md-2">'
                  +' <label for="userName">Time:<span class="text-danger"></span></label>'
                  +'</div>'
                  +'<div class="form-group col-md-2">'
                  +' <label for="userName">Points<span class="text-danger">*</span></label>'
                  +'   <input type="text" name="time_points" required id="timePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
                  +' </div>'
                  +'<div class="form-group col-md-2">'
                  +' <label for="userName">Code<span class="text-danger">*</span></label>'
                  +'    <input type="text" name="time_code" required id="timeCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">'
                  +' </div>'
                  +'<div class="form-group col-md-2">'
                  +'<label for="userName">Response<span class="text-danger">*</span></label>'
                  +'    <input type="text" name="time_response" required id="timeResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
                  +' </div>'
                  +' <div class="form-group col-md-3">'
                  +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
                  +'   <input type="text" name="time_routing" required id="timeRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
                  +' </div>'
                  +'</div></div>'); 
	 }else if(answertype=="Date"){
		 $(".dateandtimemaindiv").remove();
		 $(".datemaindiv").remove();
		 $(".selectlistmaindiv").remove();
		 $(".date").append('<div class="datemaindiv" style="padding: 20px;"><div class="row">'
				 +'<div class="form-group col-md-2">'
				 +'<label for="userName">Date:<span class="text-danger"></span></label>'
				 +' </div>'
				 +'	<div class="form-group col-md-2">'
				 +'<label for="userName">Points<span class="text-danger">*</span></label>'
				 +'    <input type="text" name="date_points" required id="datePoints" parsley-trigger="change" placeholder="Enter Points" class="form-control">'
				 +' </div>'
				 +'<div class="form-group col-md-2">'
				 +'<label for="userName">Code<span class="text-danger">*</span></label>'
				 +'    <input type="text" name="date_code" required id="dateCode" parsley-trigger="change" placeholder="Enter Code" class="form-control">'
				 +'</div>'
				 +' <div class="form-group col-md-2">'
				 +' <label for="userName">Response<span class="text-danger">*</span></label>'
				 +'    <input type="text" name="date_response" required id="dateResponse" parsley-trigger="change" placeholder="Enter Response" class="form-control">'
				 +'</div>'
				 +'  <div class="form-group col-md-3">'
				 +'<label for="userName">Routing Type<span class="text-danger">*</span></label>'
				 +'   <input type="text" name="date_routing" required id="dateRouting" parsley-trigger="change" placeholder="Enter Rounting Type" class="form-control">'
				 +'</div>'
				
                 
                  +'</div></div>'); 
	 }
	 
	 
	 
	 
	 else if(answertype=="Select/List"){
		 $(".dateandtimemaindiv").remove();
		 $(".selectlistmaindiv").remove();
		 $(".datemaindiv").remove();
		 var max_fields      = 30; //maximum input boxes allowed
	        var wrapper         = $(".selectlist"); //Fields wrapper
	       
	    	$(wrapper).append('<div class="selectlistmaindiv"><div class="form-group col-md-2 pull-left"><label for="emailAddress">Answer<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Points<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Code<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Response<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><label for="emailAddress">Mandatory<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Routing Type<span class="text-danger">*</span></label></div><div class="form-group col-md-2 pull-left"><label for="emailAddress">Comment<span class="text-danger">*</span></label></div><div class="form-group col-md-1 pull-left"><button class="add_field_button btn btn-info">+</button></div></div>'); //add //on add input button click

		 $(wrapper).append('<div class="selectlistmaindiv"><div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" required name="answerdata[0].answer" id="answer_main" parsley-trigger="change"  placeholder="Enter Answer" class="form-control answer_main"></div><div class="form-group col-md-2 pull-left"><input type="text" required name="answerdata[0].answer_points" id="points_main" parsley-trigger="change"  placeholder="Enter Points" class="form-control points_main"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="answerdata[0].answer_code" required id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="answerdata[0].answer_response" required id="response_main" parsley-trigger="change"  placeholder="Enter Response" class="form-control response_main"></div><div class="form-group col-md-1 pull-left"><select name="answerdata[0].answer_optiontype" required id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata[0].routing_type" required id="routing_id" parsley-trigger="change"  placeholder="Enter Routing Type" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="answerdata[0].answer_comment" required id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No" selected>No</option></select></div></div></div></div>');     	
	
		 var add_button      = $(".add_field_button"); //Add button ID
		  var x = 0; 
	        $(add_button).on("click",function(e){
				
	             e.preventDefault();
	            if(x < max_fields){ //max input box allowed
	                x++; //text box increment
	                
	                $(wrapper).append('<div class="selectlistmaindiv"><div class="form-row col-md-12" id="main_answer"><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata['+x+'].answer" id="answer_main" parsley-trigger="change" required placeholder="Enter Answer" class="form-control answer_main"></div><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata['+x+'].answer_points" id="points_main" parsley-trigger="change" required placeholder="Enter Points" class="form-control points_main"> </div><div class="form-group col-md-1 pull-left"><input type="text" name="answerdata['+x+'].answer_code" id="code_main" parsley-trigger="change"  placeholder="Enter Code" class="form-control"></div><div class="form-group col-md-1 pull-left"><input type="text" name="answerdata['+x+'].answer_response" id="response_main" parsley-trigger="change"  placeholder="Enter Response" required class="form-control response_main"></div><div class="form-group col-md-1 pull-left"><select name="answerdata['+x+'].answer_optiontype" id="mandatory_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No">No</option></select></div><div class="form-group col-md-2 pull-left"><input type="text" name="answerdata['+x+'].routing_type" id="routing_id" parsley-trigger="change"  placeholder="Enter Routing Type" class="form-control"></div><div class="form-group col-md-2 pull-left"><select name="answerdata['+x+'].answer_comment" required id="comment_main" parsley-trigger="change"  class="form-control"><option value="Yes">Yes</option><option value="No" selected>No</option></select></div><button class="remove_field">-</button></div></div></div>'); //add input box
	                
	            }
	            $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
		            e.preventDefault(); 
		        $(this).parent('#main_answer').remove(); 
		      
		        x--;
		        
		        })
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
	 $("#subsection_id").empty();
	 $.ajax({
         url: "<%=dashboardURL%>getSubsectionsById",
         type: "GET", 
           data: { 'section_id': section_id},
         success: function(response)
                     {
        	     $("#sk_subsection_id").append("<option value=''>Select Sub Section</option>") ;
	        	 $.each(response,function(k,v){
        		$("#sk_subsection_id").append("<option value="+v.sk_subsection_id+">"+v.subsection_name+"</option>") ;
        		$("#subsection_id").append("<option value="+v.sk_subsection_id+">"+v.subsection_name+"</option>") ;

	        	 
	        	 });
       	
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
        	   // $("#super_subsection_id_"+index).append("<option value=''>Select Sub Section</option>") ;
	        	 $.each(response,function(k,v){
        		$("#super_subsection_id_"+index).append("<option value="+v.sk_subsection_id+">"+v.subsection_name+"</option>") ;

	        	 
	        	 });
       	
                     }
	  });
 }
 
 </script>
 
 
 
 
  <script>
  function getstandardnumber(){
	  
	  var section_id=$("#super_section_id").val();
	  var brand_id=$("#sk_brand_id").val();
	  var modeOfContact=$("#modeOfContact").val();
	  var subsection_id=$("#super_subsection_id").val();
	  $( "#super_standard_Number_1").empty();
	  $( "#superquestionanswer_1").empty();
	  alert(section_id+"="+brand_id +"="+ modeOfContact+"="+ +"="+subsection_id)
  console.log(section_id+"="+brand_id +"="+ modeOfContact+"="+ +"="+subsection_id)
	$.ajax({
       url: "<%=dashboardURL%>getStandardNumberByIds",
       type: "GET", 
       data: {'section_id': section_id,'brand_id':brand_id,'modeOfContact':modeOfContact,'subsection_id':subsection_id},
       success: function(response)
                   {
       	$("#super_standard_Number_1").append('<option value="">Select Standard Number</option>')
       	for(var i=0;i<response.length;i++){
       		$("#super_standard_Number_1").append('<option value="'+response[i].standard_number+'">'+response[i].standard_number+'</option>')
       	}
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