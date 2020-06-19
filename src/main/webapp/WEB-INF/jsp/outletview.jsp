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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta
	content="A fully featured admin theme which can be used to build CRM, CMS, etc."
	name="description" />
<meta content="Coderthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<!-- App favicon -->
<link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">

<link href="<%=UI_PATH%>/plugins/select2/css/select2.min.css"
	rel="stylesheet" type="text/css" />
<!-- DataTables -->
<link
	href="<%=UI_PATH%>/plugins/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=UI_PATH%>/plugins/datatables/buttons.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />
<!-- Responsive datatable examples -->
<link
	href="<%=UI_PATH%>/plugins/datatables/responsive.bootstrap4.min.css"
	rel="stylesheet" type="text/css" />

<!-- Custom box css -->
<link href="<%=UI_PATH%>/plugins/custombox/css/custombox.min.css"
	rel="stylesheet">

<!-- App css -->
<link href="<%=UI_PATH%>/assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=UI_PATH%>/assets/css/icons.css" rel="stylesheet"
	type="text/css" />
<link href="<%=UI_PATH%>/assets/css/metismenu.min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=UI_PATH%>/assets/css/style.css" rel="stylesheet"
	type="text/css" />
	 <link href="<%=UI_PATH%>/assets/css/nice-select.css" rel="stylesheet" type="text/css" />
	
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.min.css" />
	
<script src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>
<style>
#userType {
	position: relative;
	left: 20px;
}

#icon {
	position: relative;
	top: 31px;
	font-size: 20px;
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
		<div class="content-page">
                <!-- Start content -->
                <div class="content">
                    <div class="container-fluid">

                        <div class="row">
                    <div class="col-sm-12">
                        <div class="page-title-box">
                            <div class="btn-group pull-right">
                                <ol class="breadcrumb hide-phone p-0 m-0">
                                    <li class="breadcrumb-item"><a href="#">MYS</a></li>
                                    <li class="breadcrumb-item"><a href="#">Outlets</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">View Outlets</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                
            
                    
                    <div class="col-md-12">
                        <div class="card-box table-responsive">
                            <h4 class="m-t-0 header-title"><b> </b></h4>
                           <ul class="nav nav-tabs">
                                        <li class="nav-item">
                                            <a href="#activestates" data-toggle="tab" aria-expanded="true" class="nav-link active">
                                                Active Outlets
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a href="#inactivestates" data-toggle="tab" aria-expanded="false" class="nav-link ">
                                                Inactive Outlets
                                            </a>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane fade show active" id="activestates">

                            <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                 <th>Dealership</th>
                                 	<th>Brand</th>
                                    <th>Location</th>
                                    <th>Regional Sales Manager</th>
                                  <!--   <th>Outlet Size</th> -->
                                    <th>Outlet Type</th>
                                    <th>Mobile</th>
                                    <th>Email</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                 <c:forEach var="uBean" items="${outletList}">
                                <tr>
                                      <td>${uBean.dealer_name }</td>
                                      <td>${uBean.brand_name }</td>
                                      <td>${uBean.outlet_name }</td>
                                      <td>${uBean.contact_person}</td>
                                      <td>${uBean.outlet_type }</td>
                                      <td>${uBean.mobile }</td>
                                      <td>${uBean.email }</td>                                        
                                    <td><a href="<%=dashboardURL%>editOutlet/${uBean.outlet_id}" class="fa fa-edit"></a>&nbsp;&nbsp;&nbsp;<a href="#"  class="fa fa-trash" data-id="${uBean.outlet_id}" data-toggle="modal" data-target="#myModal"></a></td>
                                </tr>
                                </c:forEach> 
                                </tbody>
                            </table>
                            </div>
                             <div class="tab-pane fade  " id="inactivestates">
                                         <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                           <thead>
                                <tr>
                                 <th>Dealership</th>
                                 	<th>Brand</th>
                                    <th>Location</th>
                                    <th>Regional Sales Manager</th>
                                  <!--   <th>Outlet Size</th> -->
                                    <th>Outlet Type</th>
                                    <th>Mobile</th>
                                    <th>Email</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                 <c:forEach var="uBean" items="${inactiveoutletList}">
                                <tr>
                                      <td>${uBean.dealer_name }</td>
                                      <td>${uBean.brand_name }</td>
                                      <td>${uBean.outlet_name }</td>
                                      <td>${uBean.contact_person}</td>
                                      <td>${uBean.outlet_type }</td>
                                      <td>${uBean.mobile }</td>
                                      <td>${uBean.email }</td>                                        
                                    <td><a href="#" data-restoreid="${uBean.outlet_id}" data-toggle="modal" data-target="#myModal1" title="Restore the Variant" class="mdi mdi-backup-restore"></a></td> 
                                </tr>
                                </c:forEach> 
                                </tbody>
                                </table>
                                        </div>
                        </div>
                    </div>
                </div>
                <!-- end row -->

            </div> <!-- end container -->
        </div>
        </div>
		<!-- end container -->
	</div>

	<!-- Signup modal content -->
	<div id="login-modal" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="custom-width-modalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" style="top: -12px; right: -10px;">×</button>
					<h4 class="text-uppercase text-center m-b-25">User Type</h4>

					<form class="form-horizontal" action="user_type"
						method="POST">

						<div class="form-group m-b-25">
							<div class="col-12">
								<label for="emailaddress1">User Type</label> <input
									class="form-control" type="text" id="user_type"
									name="user_type" onkeyup="checkUserTypeStatus()" required=""
									placeholder="Enter user type"> <span class="error_msg1"
									style="color: red;"></span>
							</div>
						</div>

						<div class="form-group account-btn text-center m-t-10">
							<div class="col-12">
								<button
									class="btn w-lg btn-rounded btn-custom waves-effect waves-light"
									id="button-submit1" type="submit">Submit</button>
							</div>
						</div>

					</form>

				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
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
	<script
		src="<%=UI_PATH%>/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js"></script>
	<script src="<%=UI_PATH%>/plugins/select2/js/select2.min.js"
		type="text/javascript"></script>
	<script
		src="<%=UI_PATH%>/plugins/bootstrap-select/js/bootstrap-select.js"
		type="text/javascript"></script>
	<!--  Required datatable js -->
	<script src="<%=UI_PATH%>/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/datatables/dataTables.bootstrap4.min.js"></script>
	<!-- Buttons examples -->
	<script src="<%=UI_PATH%>/plugins/datatables/dataTables.buttons.min.js"></script>
	<script src="<%=UI_PATH%>/plugins/datatables/buttons.bootstrap4.min.js"></script>
	<script src="<%=UI_PATH%>/plugins/datatables/jszip.min.js"></script>
	<script src="<%=UI_PATH%>/plugins/datatables/pdfmake.min.js"></script>
	<script src="<%=UI_PATH%>/plugins/datatables/vfs_fonts.js"></script>
	<script src="<%=UI_PATH%>/plugins/datatables/buttons.html5.min.js"></script>
	<script src="<%=UI_PATH%>/plugins/datatables/buttons.print.min.js"></script>
	<!-- Responsive examples -->
	<script
		src="<%=UI_PATH%>/plugins/datatables/dataTables.responsive.min.js"></script>
	<script
		src="<%=UI_PATH%>/plugins/datatables/responsive.bootstrap4.min.js"></script>

	<!--  App js -->
	<script src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
	<script src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>
	<script src="<%=UI_PATH%>/js/script.js"></script>

	<!-- Init Js file -->
	<script type="text/javascript"
		src="<%=UI_PATH%>/assets/pages/jquery.form-advanced.init.js"></script>
	<!-- Parsley js -->
	<script type="text/javascript"
		src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.js"></script>
	<script type="text/javascript">
            $(document).ready(function() {
                $('#datatable').DataTable();

                //Buttons examples
                var table = $('#datatable-buttons').DataTable({
                    lengthChange: false,
                    dom: 'Bfrtip',
                   // buttons: ['copy', 'excel', 'pdf']
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

        </script>
<script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
            $(".fa-trash").on("click",function(){
            	var value=$(this).attr("data-id");
            	//alert(value);
            	var url="<%=dashboardURL%>deleteOutlet/"+value;
            	$(".btn-edit").attr("href",url);
            });
            
            $(".mdi-backup-restore").on("click",function(){
            	var value=$(this).attr("data-restoreid");
            	var url="<%=dashboardURL%>restoreOutlet/"+value;
            	$(".btn-restore").attr("href",url);
            });
            
        </script>



</body>
</html>