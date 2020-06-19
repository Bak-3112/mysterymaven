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

					 <form method="POST" action="<%=dashboardURL%>createFormula/${qid}">
                        <div class="row">
                            <div class="col-lg-12">

                                <div class="card-box">
                                    <h4 class="header-title m-t-0">Add Formula</h4>
                                    <p class="text-muted font-14 m-b-20">
                                    </p>
									<div class="form-group col-lg-12" id="formula">
		                        <button class="add_field_button3 btn btn-info">+</button>                  
	                            
	                             
                                <div class="card" id="formul" >
                                <div class="card-header" role="tab" id="headingOne0">
                                <h6 class="mb-0 mt-0">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne0" class="text-dark" aria-expanded="true" aria-controls="collapseOneundefined">Add Formula 1</a></h6>
                                </div><div id="collapseOne0" class="collapse" role="tabpanel" aria-labelledby="headingOne">
                                <c:forEach var="qBean" items="${subsetquestionsList}" varStatus="status">
                                <div class="card-body formulae" id="formulae1"><div class="row"><div class="form-group col-md-9">
                                <div class="checkbox1"><input id="checkbox0" name="formula[0].formuladata[${status.count}].sk_subquestion_id" class="checkbox0" value="${qBean.sk_subquestion_id }" type="checkbox">
                                <label for="checkbox0">${qBean.subQuestion }</label></div></div><div class="form-group col-md-3">
                                 
                                <select name="formula[0].formuladata[${status.count}].sk_answer_id" class="form-control" id="formulaetype">
                                <option value="">Select </option>
                                <c:forEach  var="count" items="${optionsList.get(status.index)}">
                                <option value="${count.sk_answer_id }">${count.answer }</option>
                                  </c:forEach>
                                </select>
                                </div></div></div>
                                 </c:forEach>
                                    <input type="hidden" name="formula[0].formulaCount" value="f0">
                                  <div class="pull-right  row">
                                <div class="form-group col-md-10"><label for="emailAddress">Result</label><input type="text" name="formula[0].formulaFinalResult" parsley-trigger="change" placeholder="Enter Result" class="form-control" id="result"></div></div>
                                </div>
                                </div>
                               
                               </div>
                               
                                
                                </div>
                                    
                                </div> <!-- end card-box -->
                            </div>
                            <div class="form-group text-right m-b-0">
                                            <button class="btn btn-gradient waves-effect waves-light btnsubmit mainquestionbtn" type="submit">
                                                Submit
                                            </button>
                                            <button type="reset" class="btn btn-light waves-effect m-l-5">
                                                Cancel
                                            </button>
                                        </div>
                            </form>
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
     
        
     
 <script>
    
    $(document).ready(function() {
        var max_fields      = 10; //maximum input boxes allowed
        var wrapper         = $("#formula"); //Fields wrapper
        var add_button      = $(".add_field_button3"); //Add button ID
    	var x = 0; 
    	var y = 0; 
        $(add_button).on("click",function(e){
        	var div= document.getElementById("formulae1").innerHTML;
            
             e.preventDefault();
            if(x < max_fields){ //max input box allowed
                x++; //text box increment
                y =x+1;
                $(wrapper).append('<div class="card" id="formul" >'
                       +' <div class="card-header" role="tab" id="headingOne0">'
                       +'   <h6 class="mb-0 mt-0">'
                       +'  <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne'+x+'" class="text-dark" aria-expanded="true" aria-controls="collapseOneundefined">Add Formula '+y+'</a></h6>'
                       +'  </div><div id="collapseOne'+x+'" class="collapse" role="tabpanel" aria-labelledby="headingOne">'
                       +'  <c:forEach var="qBean" items="${subsetquestionsList}" varStatus="status">'
                       +' <div class="card-body formulae" id="formulae1"><div class="row"><div class="form-group col-md-9">'
                       +' <div class="checkbox1"><input id="checkbox0" name="formula['+x+'].formuladata[${status.count}].sk_subquestion_id" class="checkbox0" value="${qBean.sk_subquestion_id }" type="checkbox">'
                       +' <label for="checkbox0">${qBean.subQuestion }</label></div></div><div class="form-group col-md-3">'
                        
                       +' <select name="formula['+x+'].formuladata[${status.count}].sk_answer_id" class="form-control" id="formulaetype">'
                       +'  <option value="">Select </option>'
                       +'  <c:forEach  var="count" items="${optionsList.get(status.index)}">'
                       +'  <option value="${count.sk_answer_id }">${count.answer }</option>'
                       +'   </c:forEach>'
                       +'  </select>'
                       +' </div></div></div>'
                       +'   </c:forEach>'
                       +'<input type="hidden" name="formula['+x+'].formulaCount" value="f'+x+'">'
                       +'    <div class="pull-right  row">'
                       +'  <div class="form-group col-md-10"><label for="emailAddress">Result</label><input type="text" name="formula['+x+'].formulaFinalResult" parsley-trigger="change" placeholder="Enter Result" class="form-control" id="result"></div></div>'
                       +'  </div>'
                       +' </div>'); //add input box
               
              
            }
            //alert(div);
            
           var div1=div.replace(/\[0\]/g,"["+x+"]")
           //alert("hello"+div1);
            	document.getElementById("formulae"+(x+1)).innerHTML=div1;	 
            
            
            
        });
        
        $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
            e.preventDefault(); 
        $(this).parent('#formul').remove(); 
        x--;
        })
    });
    </script>
 
    </body>
</html>