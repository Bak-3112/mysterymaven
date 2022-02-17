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
        <title>MYS-Published</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="<%=UI_PATH%>design/images/DQI-icon.png">
		
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
                                    <li class="breadcrumb-item"><a href="#">Closed Mystery Shopping Visits</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">View Mystery Shopping Visit Status</h4>
                        </div>
                    </div>
                   </div>      
                   <form method="POST" action="<%=dashboardURL%>viewMysPublished" autocomplete="off">
                   
                   <div class="card-box ">
                   
                               <div class="row">
                               
                               <div class="col-md-3">
                                    <label for="Address" style="font-weight:600; color:#797979;">Brand<span class="text-danger">*</span></label>
                                    <select required="" class="form-control" id="brand_id" name="brand_id"  onchange="getYear(this.value);">
					                      <option value="">Select Brand</option>
					                      <c:forEach var="dbBean" items="${activebrandList}">
                                         <option value="${dbBean.sk_brand_id }" <c:if test ="${brand_id == dbBean.sk_brand_id }">selected </c:if>>${dbBean.brand_name }</option>
                                        </c:forEach>
					                      </select>
                                </div>
                      
                            	<div class="col-md-3">
                                    <label for="Address" style="font-weight:600; color:#797979;">Year<span class="text-danger">*</span></label>
                                    <select required  class="form-control" id="year" name="year">
                                         
					                      <option value="">Select Year</option>
					                     </select>
                                </div>
                                
                                <div class="col-md-3">
                                    <label for="Address" style="font-weight:600; color:#797979;">Month<span class="text-danger">*</span></label>
                                    <select required="" class="form-control" id="month" name="month">
					                       
					                       <option value=''>Select Month</option>
                                           <option value='1'>Janaury</option>
                                           <option value='2'>February</option>
                                           <option value='3'>March</option>
                                           <option value='4'>April</option>
                                           <option value='5'>May</option>
                                           <option value='6'>June</option>
                                           <option value='7'>July</option>
                                           <option value='8'>August</option>
                                           <option value='9'>September</option>
                                           <option value='10'>October</option>
                                           <option value='11'>November</option>
                                           <option value='12'>December</option>
					                       
					                      </select>
                                </div>
                                <div class="col-md-3">
                                    <label for="Address" style="font-weight:600; color:#797979;">Mode of Contact<span class="text-danger">*</span></label>
                                    <select required class="form-control" id="mode_of_contact" name="mode_of_contact">
<%-- 					                     <option value="${mode_of_contact}">${mode_of_contact}</option>
 --%>					                       <option value="">Select Mode of Contact</option>
					                      <option value="EMail/Website">EMail/Website</option>
					                      <option value="Walk In">Walk In</option>
					                        <option value="Telephone">Telephone</option>
					                         <option value="Online Sales Channel">Online Sales Channel</option> 
					                      </select>
                                </div> 
                                 </div>
                                      <label></label>
                                  
								  <div class="row">
						
								
                               </div>
                               <div>&nbsp;&nbsp;</div>
                                 	  <div class="row">
                                <div class="col-md-6 mgmtclass">
                                    <button class="btn btn-gradient waves-effect waves-light" type="submit">
                                        View
                                    </button>
                                  
                                </div>
							</div>
							</div>
							</form>

                <div class="row">
                
               
                    <div class="col-md-12">
                         <div class="card-box">
                                   

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
                                            <a href="#Completed" data-toggle="tab" aria-expanded="true" class="nav-link ">
                                              Review
                                            </a>
                                        </li>
                                        <li class="nav-item closed">
                                            <a href="#Closed" data-toggle="tab" aria-expanded="true" class="nav-link active">
                                                Published
                                            </a>
                                        </li>
                                       
                                    </ul>
                                    <div class="tab-pane fade" id="Scheduled">
                                            
                                        </div>
                                        <div class="tab-pane fade" id="Completed">
                                            
                                        </div>
                                        <div class="tab-pane fade" id="visited">
                                            
                                        </div>
                                    <div class="tab-content">
                                        <div class="tab-pane fade show active" id="Closed">
                           
                            <div class='table-responsive'>
                             <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>Dealership</th>
                                    <th>Outlet</th>
                                    <th>Brand Name</th>
                                    <th>Mode Of Contact</th>
                                    <th>Visit Date</th>
                                    <th class="shopname">Mystery Shopper Name</th> 
                                    
                                    <th>SC Name</th>
                                    <th>Outlet Score</th>
                                    <th>Process Excellence Score</th>
                                    <th>Customer Treatment Score</th>	
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="mvBean" items="${mysPublishedList }">
                                <tr>
                                   
                                      <td>${mvBean.dealer_name}</td>   
                                      <td>${mvBean.outlet_name}</td>
                                      <td>${mvBean.brand_name}</td>
                                      <td>${mvBean.mode_of_contact}</td>
                                      <td style="white-space: nowrap;">${mvBean.visit_date}</td>
                                      <td>${mvBean.name}</td>
                                      <td>${mvBean.sc_name}</td> 
                                      <td>${mvBean.outlet_level_score}</td>
                                      <td>${mvBean.process_excellence_score}</td>                           
                                      <td>${mvBean.customer_treatment_score}</td> 
                                      <td><a href="<%=dashboardURL%>updatetoComplete/${mvBean.sk_shopper_id}" class="btn btn-primary">Reopen</a></td> 
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            </div>
                        </div>
                          <!-- EXCEPTIONS -->
                                        
                                        
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
        
        <script>
        function getYear(brand_id) 
        { 
         
           $("#year option").remove();  
          $.ajax({
                  url: "<%=dashboardURL%>getYear",

                  type: "GET", 
                    data: { 'brand_id': brand_id },
                    
                  success: function(response)
                  
                              {
                	 
                	   $("#year").append("<option value=''>Select Year</option>");
                	  $.each(response,function(k,v){
                          $("#year").append("<option value='"+v.year+"'>"+v.year+"</option>");

                	  }); 
                                    
                              }
                  });  
          
        }
        </script>
        
        <script>
        $(document).ready(function() {
            $("#mode_of_contact").val('${mode_of_contact}'); 
            $("#month").val('${month}'); 
            if(`${year}`!='')
            $('#year').append(`<option selected value="${year}">${year}</option>`); 
        });
        </script>
      
</body>
</html>