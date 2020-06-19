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
        <title>Mystery Shopping - Qustionnarie Sequence</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="<%=UI_PATH%>/design/images/DQI-icon.png">
		
		<!-- DataTables -->
        <link href="<%=UI_PATH%>/resources/plugins/datatables/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/resources/plugins/datatables/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <!-- Responsive datatable examples -->
        <link href="<%=request.getContextPath()%>/resources/plugins/datatables/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        
        <!-- App css -->
        <link href="<%=UI_PATH%>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/metismenu.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/style.css" rel="stylesheet" type="text/css" />

        <script src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>

    </head>


    <body>

        <!-- Begin page -->
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
                                    <!-- <h4 class="page-title float-left">View Questions</h4> -->
                                    <div class="row">
									<div class="col-lg-6">
										<h4>Questionnaire Sequence</h4>
			                        </div>
			                        
			                        <div class="col-lg-6">
			                      
			                      <form method="POST" action="<%=dashboardURL%>updateQuestionOrder">
			                        <div class="row">
			                        <div class="col-lg-3">
										<select class="form-control" name="sk_brand_id"
											id="brandId">
											<c:forEach var="dbBean" items="${activebrandList}">
												<option value="${dbBean.sk_brand_id}" <c:if test = "${brand_id == dbBean.sk_brand_id }">selected </c:if>>${dbBean.brand_name}</option>
											</c:forEach>
										</select>
 								</div>
			                        <div class="col-lg-6">
                                <select class="form-control" name="mode_of_contact" id="mode_of_contact">
                                    	<option value="${mode_of_contact}">${mode_of_contact}</option>
                                       	<option value="All">All</option>      
                                       	<option value="Email/Website">Email/Website</option>
                                       	<option value="Telephone">Telephone</option>
                                       	<option value="Walk In">Walk In</option>
		                                <option value="Online Sales Channel">Online Sales Channel</option>
                                    
                                    
                                 </select>    
                                </div>
                                  <div class="col-lg-3">
                                 <button class="btn btn-primary" type="submit">view</button>
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
                                    <h4 class="m-t-0 header-title"><b>Questionnaire Sequence</b></h4>
                                    <!-- <p class="text-muted font-14 m-b-30">
                                        The Buttons extension for DataTables provides a common set of options, API methods and styling to display buttons on a page that will interact with a DataTable. The core library provides the based framework upon which plug-ins can built.
                                    </p> -->

                                    <table id="datatable-buttons" class="table table-striped table-bordered border" cellspacing="0" width="100%">
                                        <thead>
                                        <tr>
                                            <th style="width: 100px;">Year</th>
                                        	<th style="width: 100px;">Standard No.</th>
                                            <th>Question</th>
                                            <th style="width: 22%;" colspan="2">Standard Sequence Number</th>
                                            
                                        </tr>
                                        </thead>


                                        <tbody>
                                       <c:forEach var="qBean" items="${questionnaireList}" varStatus="loop">
                                        <tr>
                                            <td>${qBean.year}</td>
                                        	<td>${qBean.standard_number}</td>
                                            <td>${qBean.question }</td>
                                            <td class="border-right-0" ><input class="std_sequence_no" id="sequencenumber${loop.index+1}" style="background-color:transparent;border:none;width:50px;" value="${qBean.standard_number_sequence}"></td>
                                            <td class="text-right"><button class="btn btn-primary" onClick="save(${qBean.sk_question_id},${loop.index+1})">save</button></td>
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



        <!-- jQuery  -->
       <%--  <script src="<%=UI_PATH%>/assets/js/jquery.min.js"></script> --%>
        <!-- jQuery  -->
        <script src="<%=UI_PATH%>/assets/js/jquery.min.js"></script>
        <script src="<%=UI_PATH%>/assets/js/popper.min.js"></script>
        <script src="<%=UI_PATH%>/assets/js/bootstrap.min.js"></script>
        <script src="<%=UI_PATH%>/assets/js/metisMenu.min.js"></script>
        <script src="<%=UI_PATH%>/assets/js/waves.js"></script>
        <script src="<%=UI_PATH%>/assets/js/jquery.slimscroll.js"></script>

        <!-- Required datatable js -->
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

        <!-- App js -->
        <script src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
        <script src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>

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
        
         <script>
       function save(i,x) 
        { 
    	   
           var standardNumberSequence=$('#sequencenumber'+x).val();
           var questionId=i;
           
           $.ajax({
               url: "<%=dashboardURL%>updateStandardSequenceNumber",
               type: "GET", 
                 data: { 'standardNumberSequence': standardNumberSequence,'questionId':questionId },
               success: function(response)
                           {
                     	
                     //	window.location.href = '<%=UI_PATH%>/updateQuestionOrder';
            	   		
            	  		                    
                           }
               }); 
           
          
        } 
       $(document).ready(function(){
    	   $(".dataTables_empty").attr('colspan',3);
    	   $(".dataTables_empty").addClass("text-center");
   
       $(".std_sequence_no").each(function(){
    	   if($(this).val()!=""){
    		   $(this).attr('value',parseInt($(this).val()));
    	   }
       });
       });
        </script>

    </body>
</html>