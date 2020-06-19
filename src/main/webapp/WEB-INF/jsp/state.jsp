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
        <meta http-equiv="X-UA-Compatible" content="IE=edge" content="no-cache" />

        <!-- App favicon -->
         <link rel="shortcut icon"
	href="<%=request.getContextPath()%>/resources/design/images/DQI-icon.png">


        <!-- App css -->
        <link href="<%=UI_PATH%>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/metismenu.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/style.css" rel="stylesheet" type="text/css" />

        <script src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>
        
        <style>
.gm-style-mtc,.gm-svpc
{
display: none;
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
                                   <!--  <li class="breadcrumb-item"><a href="#">Mystery</a></li>
                                    <li class="breadcrumb-item"><a href="#"></a></li> -->
                                </ol>
                            </div>
                            <h4 class="page-title">Create State</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                <jsp:include page="include/tab.jsp"></jsp:include>
        
                <div class="col-lg-6">

                        <div class="card-box ">
                            <h4 class="header-title m-t-0">  </h4>
                            <form method="POST" action="createStatePost" enctype="multipart/form-data" autocomplete="off">
                                <div class="form-group col-md-12">
                                    <label for="state_name">State Name<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" onkeyup="exist(this.value)" id="state_name" name="state_name" placeholder="Enter State Name" data-parsley-pattern="^[a-zA-Z ]+$"  data-parsley-maxlength="100">
                                <span id="stateerrormsg" style="color:red"></span>
                                 <input type="text"   id="testbox" style="display: none">
                                </div>
                                
                                <div class="form-group text-right m-b-0">
                                    <button class="btn btn-gradient waves-effect waves-light btnsubmit" type="submit">
                                        Submit
                                    </button>
                                    <button type="reset" class="btn btn-light waves-effect m-l-5 resetbtn" id="reset" >
                                        Reset
                                    </button>
                                    
                                </div>

                            </form>
                        </div> <!-- end card-box -->
                    </div>
                    
                    
                     <div class="col-md-6">
                        <div class="card-box table-responsive">
                            <h4 class="m-t-0 header-title"><b> </b></h4>
                           

                            <ul class="nav nav-tabs">
                                        <li class="nav-item">
                                            <a href="#activestates" data-toggle="tab" aria-expanded="true" class="nav-link active">
                                                Active States
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a href="#inactivestates" data-toggle="tab" aria-expanded="false" class="nav-link ">
                                                Inactive States
                                            </a>
                                        </li>
                                    </ul>
                                      <div class="tab-content">
                                        <div class="tab-pane fade show active" id="activestates">
                                         <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>State Name</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="uBean" items="${stateList}">
                                <tr>
                                    <td>${uBean.state_name }</td>
                                    <td><a href="<%=dashboardURL%>editState/${uBean.sk_state_id}" class="fa fa-edit"></a>&nbsp;&nbsp;&nbsp;<a href="#"  class="fa fa-trash" data-id="${uBean.sk_state_id}" data-toggle="modal" data-target="#myModal"></a></td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                                        </div>
                                        <div class="tab-pane fade  " id="inactivestates">
                                         <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>State Name</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="sBean" items="${inactivestateList}">
                                <tr>
                                    <td>${sBean.state_name }</td>
                                    <td><a href="#" data-restoreid="${sBean.sk_state_id}" data-toggle="modal" data-target="#myModal1" title="Restore the State" class="mdi mdi-backup-restore"></a></td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                                        </div>
                                       
                                    </div>
                        </div>
                    </div>
            </div> <!-- end container -->
        </div>
        </div></div>
       
                             
        <!-- end wrapper -->


        <!-- Footer -->
        <jsp:include page="include/footer.jsp"></jsp:include>
        <!-- End Footer -->


        <!--  jQuery  -->
        <script src="<%=UI_PATH %>/assets/js/jquery.min.js"></script>
        <script src="<%=UI_PATH %>/assets/js/popper.min.js"></script>
        <script src="<%=UI_PATH %>/assets/js/bootstrap.min.js"></script>
		<script src="<%=UI_PATH %>/assets/js/metisMenu.min.js"></script>
        <script src="<%=UI_PATH %>/assets/js/waves.js"></script>
        <script src="<%=UI_PATH %>/assets/js/jquery.slimscroll.js"></script>
         <!-- Modal-Effect -->
        <script src="<%=UI_PATH %>/plugins/custombox/js/custombox.min.js"></script>
        <script src="<%=UI_PATH %>/plugins/custombox/js/legacy.min.js"></script>
        
		 <!-- plugin Js -->
        <script src="<%=UI_PATH %>/plugins/switchery/switchery.min.js"></script>
        <script src="<%=UI_PATH %>/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js"></script>
        <script src="<%=UI_PATH %>/plugins/select2/js/select2.min.js" type="text/javascript"></script>
        <script src="<%=UI_PATH %>/plugins/bootstrap-select/js/bootstrap-select.js" type="text/javascript"></script>
        <!--  Required datatable js -->
        <script src="<%=UI_PATH %>/plugins/datatables/jquery.dataTables.min.js"></script>
        <script src="<%=UI_PATH %>/plugins/datatables/dataTables.bootstrap4.min.js"></script>
        <!-- Buttons examples -->
        <script src="<%=UI_PATH %>/plugins/datatables/dataTables.buttons.min.js"></script>
        <script src="<%=UI_PATH %>/plugins/datatables/buttons.bootstrap4.min.js"></script>
        <script src="<%=UI_PATH %>/plugins/datatables/jszip.min.js"></script>
        <script src="<%=UI_PATH %>/plugins/datatables/pdfmake.min.js"></script>
        <script src="<%=UI_PATH %>/plugins/datatables/vfs_fonts.js"></script>
        <script src="<%=UI_PATH %>/plugins/datatables/buttons.html5.min.js"></script>
        <script src="<%=UI_PATH %>/plugins/datatables/buttons.print.min.js"></script>
        <!-- Responsive examples -->
        <script src="<%=UI_PATH %>/plugins/datatables/dataTables.responsive.min.js"></script>
        <script src="<%=UI_PATH %>/plugins/datatables/responsive.bootstrap4.min.js"></script>

        <!--  App js -->
        <script src="<%=UI_PATH %>/assets/js/jquery.core.js"></script>
        <script src="<%=UI_PATH %>/assets/js/jquery.app.js"></script>
        <script src="<%=UI_PATH %>/js/script.js"></script>
          
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
            
            $('#reset').click(function () {
    		    $('form').parsley().reset();
    		});
            
            $(".fa-trash").on("click",function(){
            	var value=$(this).attr("data-id");
            	var url="<%=dashboardURL%>deleteState/"+value;
            	$(".btn-edit").attr("href",url);
            });
            
            $(".mdi-backup-restore").on("click",function(){
            	var value=$(this).attr("data-restoreid");
            	var url="<%=dashboardURL%>restoreState/"+value;
            	$(".btn-restore").attr("href",url);
            });
            
        </script>
   
   
     <script>
        jQuery(document).ready(function() {
	// 3 Capitalize string every 1st chacter of word to uppercase
	jQuery('#state_name').keyup(function() 
	{
		var str = jQuery('#state_name').val();
		var spart = str.split(" ");
		for ( var i = 0; i < spart.length; i++ )
		{
			var j = spart[i].charAt(0).toUpperCase();
			spart[i] = j + spart[i].substr(1);
		}
      jQuery('#state_name').val(spart.join(" "));
	
	});
	
});
        $(".btnsubmit").on("click",function(){
        	  $('form').parsley();
          	if($("#stateerrormsg").text()=="State is  already exist...!"){
          		 $("#testbox").prop('required',true);
          		 setTimeout(function(){
             			var id=$("#testbox").attr("data-parsley-id");
             			  $("#parsley-id-"+id+"").hide();
             		}, 10);
          	}else	if($("#stateerrormsg").text()=="State is in Inactive tab ,Please restore ...!"){
          		 $("#testbox").prop('required',true);
          		 setTimeout(function(){
             			var id=$("#testbox").attr("data-parsley-id");
             			  $("#parsley-id-"+id+"").hide();
             		}, 10);
          	}
          		else{
          		 $("#testbox").prop('required',false);
          	}
           
         });
        </script>       
        
      <!-- state existeor not -->
        <script type="text/javascript">
        function exist(name){
        	 $.ajax({
                 url: "<%=dashboardURL%>getStateExistance",
                 type: "GET", 
                   data: { 'name': name },
                 success: function(response)
                             {
               	  var msg=response.message;
               	 var statusmessage=response.status;
					   if(msg=="stateexist" && statusmessage=="active"){
						   $("#stateerrormsg").text("State is  already exist...!")
					   }else if(msg=="stateexist" && statusmessage=="inactive"){
						   $("#stateerrormsg").text("State is in Inactive tab ,Please restore ...!")
					   }else{
					var id=$("#testbox").attr("data-parsley-id");
				   $("#parsley-id-"+id+"").remove();
						   $("#stateerrormsg").text("");
					   }
                             }
       	  });
        }
        
        $(".resetbtn").on("click",function(){
        	var id=$("#testbox").attr("data-parsley-id");
			   $("#parsley-id-"+id+"").remove();
					   $("#stateerrormsg").text("");
					   $("#testbox").prop('required',false);
        });
        </script>
      
</body>
</html>