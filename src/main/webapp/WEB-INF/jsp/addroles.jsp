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
        <title>MYS-Roles</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon"
	href="<%=UI_PATH%>/resources/design/images/DQI-icon.png">
		
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

        <script src="<%=UI_PATH%>/>assets/js/modernizr.min.js"></script>
		<link rel="stylesheet" href="<%=UI_PATH%>/css/nice-select.css" type="text/css" />
		<style>
		
		.panel{display:none;}
		
		.panel.sub-menu
		{
		   display:none;
    position: relative;
    left: 49px;
    top: 9px;
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
                                    <li class="breadcrumb-item"><a href="#">MYS</a></li>
                                    <li class="breadcrumb-item"><a href="#">Roles</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">Roles</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                
                <div class="col-lg-4">

                        <div class="card-box ">
                            <h4 class="header-title m-t-0">  </h4>
                            <form method="POST" action="<%=dashboardURL%>roles" enctype="multipart/form-data" autocomplete="off">
                               
                                <div class="form-group col-md-12">
                                    <label for="Address">Role Name<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="role" name="role" placeholder="Enter Role Name">
                                </div>
                               <div class="form-group col-md-12" >
                                    <label for="pass1">Role For<span class="text-danger">*</span></label>
                                    <select id="role_for" name="role_for" required class="form-control" >
                                    <option value="">Select</option>
                                   <%--  <c:forEach var="uBean" items="${userTypeList}">
                                    	<option value="${uBean.user_type_id }">${uBean.user_type }</option>
                                    </c:forEach> --%>
                                    </select>
                                </div>
                                <input type="hidden" name="menu_id" id="menu_id" value="">
                                
                                <div class="form-group text-right m-b-0">
                                    <button class="btn btn-gradient waves-effect waves-light" type="submit">
                                        Submit
                                    </button>
                                    <button type="reset" class="btn btn-light waves-effect m-l-5" >
                                        Reset
                                    </button>
                                    
                                </div>

                            </form>
                        </div> <!-- end card-box -->
                    </div>
                    
                    <div class="col-md-8">
                    <div class="card-box table-responsive">
                    <h6></h6>
                    <c:forEach var="mBean" items="${mainMenu1}" varStatus="status">
                    <div class="form-group col-md-8" >
                    <span class="accordion">&nbsp;&nbsp;<input type="checkbox" class="checkbox" value="${mBean.menu_id}">${mBean.menu_name}</span>
					<div class="panel sub-menu">
					<c:forEach var="count" items="${mBean.subMenu}">
					<div class="row">
					<span class="accordion"><input type="checkbox" class="checkbox"  value="${count.menu_id}">${count.menu_name}</span>
					<div class="panel sub-menu">
					<c:forEach var="count1" items="${count.subMenu}">
					<div class="row">
					<label class="childrens"><input type="checkbox" class="checkbox-sub"  value="${count1.menu_id}">${count1.menu_name}</label>
					</div>
					</c:forEach>
					</div>
					</div>
					</c:forEach>
					</div>
					 </div>
					</c:forEach>
                    </div>
                    </div>
                </div>
                <!-- end row -->

            </div> <!-- end container -->
        </div>
        
       </div></div>
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
            
            /* $(".checkbox").click(function() {            
                if ($(this).prop("checked")) {
                	 
                    $(".childrens input[type='checkbox']").each(function() {
                        $(this).attr("checked", "checked");
                    }); 
                }else{
                    $(".childrens input[type='checkbox']").each(function() {
                        $(this).removeAttr("checked");
                    });        
                }
            });  */
            $('.checkbox').on('change',function(){
            	if($(this).prop("checked") == true){
            	 $(this).parent().siblings('.panel.sub-menu').find('.checkbox-sub').each(function(){$(this).prop('checked',true);});

            	            }else{
            	 $(this).parent().siblings('.panel.sub-menu').find('.checkbox-sub').each(function(){$(this).prop('checked',false);});
            	            }
            	 
            	});
            
            $("button").click(function(){
                var favorite = [];
                $.each($("input[class='checkbox']:checked"), function(){            
                    favorite.push($(this).val());
                });
                $.each($("input[class='checkbox-sub']:checked"), function(){            
                    favorite.push($(this).val());
                });
                $("#menu_id").val(favorite.join(","));
               console.log(favorite.join(","));
            });

        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
        </script>
        

      
</body>
</html>