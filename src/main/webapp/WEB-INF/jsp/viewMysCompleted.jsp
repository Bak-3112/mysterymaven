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
        <title>MYS-MYS</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="<%=UI_PATH%>/design/images/DQI-icon.png">
		
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
		.shopname{
    width:220px !important;
    }
		</style>
		
    </head>

    <body>
    <div id="wrapper">
        <!-- Navigation Bar-->
        <jsp:include page="include/header.jsp"></jsp:include>
         <jsp:include page="include/sidemenu.jsp"></jsp:include>
        <!-- End Navigation Bar-->
      <div class="content-page">
                <!-- Start content -->
                <div class="content">
                    <div class="container-fluid">

                        <div class="row">
                    <div class="col-sm-12">
                        <div class="page-title-box">
                            <div class="btn-group pull-right">
                                <ol class="breadcrumb hide-phone p-0 m-0">
                                    <li class="breadcrumb-item"><a href="#">Mystery Shopping</a></li>
                                    <li class="breadcrumb-item"><a href="#">Completed Mystery Shopping Visits</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">View Mystery Shopping Visit Status</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                
               
                    <div class="col-md-12">
                         <div class="card-box px-0">
                                   

                                    <ul class="nav nav-tabs">
                                        <li class="nav-item all">
                                            <a href="#Scheduled" data-toggle="tab" aria-expanded="false" class="nav-link">
                                                Scheduled
                                            </a>
                                        </li>
                                        <li class="nav-item visited">
                                            <a href="#visited" data-toggle="tab" aria-expanded="true" class="nav-link ">
                                             Visited
                                            </a>
                                        </li>
                                        <li class="nav-item exception">
                                            <a href="#Completed" data-toggle="tab" aria-expanded="true" class="nav-link active">
                                              Review
                                            </a>
                                        </li>
                                        <li class="nav-item closed">
                                            <a href="#Closed" data-toggle="tab" aria-expanded="true" class="nav-link ">
                                                Published
                                            </a>
                                        </li>
                                       
                                    </ul>
                                    <div class="tab-pane fade" id="Scheduled">
                                            
                                        </div>
                                   <div class="tab-pane fade" id="visited">
                                            
                                        </div>
        
                                    <div class="tab-content">
                                        <div class="tab-pane fade show active" id="Completed">
                           

                             <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>Dealership</th>
                                    <th>Outlet</th>
                                    <th>Brand Name</th>
                                    <th> Mode Of Contact</th>
                                    <th>Visit Date</th>
                                    <th class="shopname">Mystery Shopper Name</th><!-- 
                                    <th>Email id</th>
                                    <th>Phone No.</th> -->
                                    <th>SC Name</th>
                                    <th>Outlet Score</th>
                                    <th>PE Score</th>
                                    <th>CT Score</th>	
                                    <th>Documents</th>
                                    <th>Review</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="mvBean" items="${mysCompletedList }">
                                <tr>
                                   
                                    <td>${mvBean.dealer_name}</td>   
                                      <td>${mvBean.outlet_name}</td>
                                      <td>${mvBean.brand_name}</td>
                                      <td>${mvBean.mode_of_contact}</td>
                                      <td style="white-space: nowrap;">${mvBean.visit_date}</td>
                                      <td>${mvBean.name}</td>
                                      <td>${mvBean.sc_name}</td> 
                                        <!-- <td>75%</td> -->
                                      <!-- <td>80%</td>
                                      <td>100%</td> --> 
                                      <td>${mvBean.outlet_level_score}</td> 
                                      <td>${mvBean.process_excellence_score}</td>                           
                                      <td>${mvBean.customer_treatment_score}</td>  
                                       <c:if test="${mvBean.shopper_document_count=='0'}">
                                      <td>No Documents Found</td>
                                      </c:if> 
                                      <c:if test="${mvBean.shopper_document_count!='0'}"> 
                                      <td><a class="btn btn-primary" href="<%=dashboardURL%>viewDocuments/${mvBean.sk_shopper_id }">View</a></td>
                                      </c:if> 
                                      <td><a  class="btn btn-primary" href="<%=dashboardURL%>getMysQuestionnaire/${mvBean.sk_shopper_id }">view</a></td>   
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                          <!-- EXCEPTIONS -->
                                        
                                        <div class="tab-pane fade" id="Closed">
                                            
                                        </div>
                                        
                                         <!-- EXCEPTIONS -->
                    </div>
                </div>
                <!-- end row -->

            </div> <!-- end container -->
        </div>
        </div></div>

                            
                             <!-- Modal -->
      
                            
        <!-- end wrapper -->


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

        <!--  App js -->
        <script src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
        <script src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>
        <script src="<%=UI_PATH%>/js/script.js"></script>
          
        <!-- Init Js file -->
        <script type="text/javascript" src="<%=UI_PATH%>/assets/pages/jquery.form-advanced.init.js"></script>
        <!-- Parsley js -->
        <script type="text/javascript" src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>

        <script type="text/javascript">
        
        $(".nav-item.exception").click(function (){
         	location.replace("<%=dashboardURL%>viewMysCompleted"); 
        });
        $(".nav-item.all").click(function (){
         	location.replace("<%=dashboardURL%>viewScheduledMysteryShoppers"); 
        });
        $(".nav-item.closed").click(function (){
         	location.replace("<%=dashboardURL%>viewMysClosed"); 
        });
        $(".nav-item.visited").click(function (){
         	location.replace("<%=dashboardURL%>viewMysVisited"); 
        });
        
            $(document).ready(function() {
                $('#datatable').DataTable();

                //Buttons examples
                var table = $('#datatable-buttons').DataTable({
                    lengthChange: false,
                    buttons: ['copy', 'excel', 'pdf'],
                    "scrollX": true
                });

                table.buttons().container()
                        .appendTo('#datatable-buttons_wrapper .col-md-6:eq(0)');
            } );

        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
        </script>

      
</body>
</html>