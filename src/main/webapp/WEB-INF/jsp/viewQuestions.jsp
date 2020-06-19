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
    <%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  %>
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

    </head>


    <body>

        <!-- Begin page -->
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
                                    <!-- <h4 class="page-title float-left">View Questions</h4> -->
                                    <div class="row">
									<div class="col-lg-8">
										<h4>View Questions</h4>
			                        </div>
			                        
			                        <div class="col-lg-4">
			                      
			                      <form method="POST" action="<%=dashboardURL%>/questions">
			                        <div class="row">
			                        <div class="col-lg-9">
                                <%-- <select class="form-control" name="modeOfContact" id="modeOfContact">
                                    <option value="${brand }">${brand }</option>
                                    	<option value="">Select Question Type</option> 
                                       	<option value="All">All</option>      
                                       	<option value="Email/Website">Email/Website</option>
                                       	<option value="Telephone">Telephone</option>
                                       	<option value="Walk In">Walk In</option>
		                                            	<option value="Online Sales Channel">Online Sales Channel</option>
                                    
                                    
                                 </select> --%>
                                 </div>
                                 <div class="form-group col-md-6" style="margin-left: -402px;">
                                    <label for="state_name">Brand Name<span class="text-danger"></span></label>
									<select id="sk_brand_id" name="sk_brand_id" class="form-control"  >
                                    <c:forEach var="uBean" items="${brandList}">
                                     <option value="${uBean.sk_brand_id }" <c:if test = "${brand_id == uBean.sk_brand_id }">selected </c:if>>${uBean.brand_name }</option>
                                     </c:forEach>
                                     </select>                           
                                 </div>
                                 
                                 <div  class="form-group col-md-6">
		                                            <label for="userName">Mode Of Contact<span class="text-danger"></span></label>
		                                            <select name="mode_of_contact" parsley-trigger="change" onchange="getstandardnumber();" required class="form-control" id="modeOfContact" >
		                                            	<option value="${mode_of_contact}">${mode_of_contact}</option>
		                                            	<option value="ALL">ALL</option>
		                                            	<option value="Email/Website">Email/Website</option>
		                                            	<option value="Telephone">Telephone</option>
		                                            	<option value="Walk In">Walk In</option>
		                                            	<option value="Online Sales Channel">Online Sales Channel</option>
		                                            	
		                                            </select>
		                                        </div>
                                 <div class="col-lg-3">
                                 <button class="btn btn-primary" type="submit" style="margin-top: 28px;">View</button>
                                 </div>
                                 </div>
                                 </form>
			                        </div>
                                    </div>

                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div>
                        <!-- end row -->


                        <div class="row">
                            <div class="col-12">
                                <div class="card-box table-responsive">
                                <!--     <h4 class="m-t-0 header-title"><b>View Questions</b></h4> -->
                                    <!-- <p class="text-muted font-14 m-b-30">
                                        The Buttons extension for DataTables provides a common set of options, API methods and styling to display buttons on a page that will interact with a DataTable. The core library provides the based framework upon which plug-ins can built.
                                    </p> -->

                                    <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                        <thead>
                                        <tr>
                                             <th>Year</th>
                                        	<th>Standard No.</th>
                                            <th>Question</th>
                                            <th>Question Type</th>
                                            <th>Points</th>
                                            <th>Code</th>
                                            <th>Response</th>
                                            <th>Action</th>
                                        </tr>
                                        </thead>


                                        <tbody>
                                        <c:forEach var="qBean" items="${questionnaireList}">
                                        <tr>
                                            <td>${qBean.year }</td>
                                        	<td>${qBean.standard_number }</td>
                                            <td>${qBean.question }</td>
                                            <td>${qBean.question_type }</td>
                                            <td>${qBean.question_points }</td>
                                            <td>${qBean.question_code }</td>
                                            <td>${qBean.question_response }</td>
                                            <td><c:choose><c:when test = "${qBean.question_type =='Main Question With Set Of SubQuestions'}"><a href="<%=dashboardURL%>editquestion/${qBean.sk_question_id}/${qBean.question_type}">View/Update</a></c:when><c:when test = "${qBean.question_type =='Dependent Question With Set Of SubQuestions'}"><a href="<%=dashboardURL%>editdependentquestion/${qBean.sk_question_id}/${qBean.question_type}">View/Update</a></c:when><c:otherwise><a href="<%=dashboardURL%>editquestion/${qBean.sk_question_id}">View/Update</a></c:otherwise></c:choose><br><a class="fa fa-trash" data-id="${qBean.sk_question_id}" data-toggle="modal" title="Delete" data-target="#myModal"></a></td>
                                        </tr>
                                         </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
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
        <script src="<%=UI_PATH%>/js/script.js"></script>
          
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
                  //  buttons: ['copy', 'excel', 'pdf']
                    buttons: [
                   	 {
                          	 extend: 'copy'
                              
               			},
               			{
                   	 extend: 'excelHtml5',
                        title: 'Mystery-Dealer'
                    },
             {
            	 extend: 'pdfHtml5'
               
              }
     
                   ]
                });

                table.buttons().container()
                        .appendTo('#datatable-buttons_wrapper .col-md-6:eq(0)');
            } );
            $(".fa-trash").on("click",function(){
            	var value=$(this).attr("data-id");
            	var url="<%=dashboardURL%>deletequestion/"+value;
            	$(".btn-edit").attr("href",url);
            });
            
        </script>

    </body>
</html>