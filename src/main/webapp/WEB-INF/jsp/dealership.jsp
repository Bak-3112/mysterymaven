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

     <link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">
		
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
                                   
                                </ol>
                            </div>
                            <h4 class="page-title">Create Dealership</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                
                <div class="col-lg-4">

                        <div class="card-box ">
                            <h4 class="header-title m-t-0">  </h4>
                            <form method="POST" action="<%=dashboardURL%>createDealerPost" enctype="multipart/form-data" autocomplete="off">
                                
                                <div class="form-group col-md-12">
                                    <label for="state_name">Brand Name<span class="text-danger">*</span></label>
									<select id="brand_id" name="brand_id" required class="form-control "   data-placeholder="Choose States..">
									<option value="" >Select Brand</option>
                                    <c:forEach var="dbBean" items="${activebrandList}">
                                     <option value="${dbBean.sk_brand_id }">${dbBean.brand_name }</option>
                                     </c:forEach>
                                     </select>                           
                                 </div>
                                <div class="form-group col-md-12">
                                    <label for="Address">Dealership Name<span class="text-danger">*</span></label>
                                    <input type="text" required   class="form-control" id="dealership_name" name="dealer_name" onkeyup="getDealerExistance(this.value)" placeholder="Enter Dealership Name"    data-parsley-maxlength="100">
                                 <span id="dealererrormsg" style="color:red"></span>
                                 <input type="text"   id="testbox" style="display: none">
                                </div>
                                  <div class="form-group col-md-12">
                                    <label for="state_name">Region Name<span class="text-danger">*</span></label>
									<select id="region_id" name="region_id" required class="form-control "   data-placeholder="Choose States..">
									<option value="" >Select Region</option>
                                    <c:forEach var="rBean" items="${activeRegionList}">
                                     <option value="${rBean.sk_region_id }">${rBean.region_name }</option>
                                     </c:forEach>
                                     </select>                           
                                 </div>
                                <div class="form-group col-md-12">
                                    <label for="Address">Dealer Principal<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="contact_person" name="contact_persion" placeholder="Enter Dealership Principle" data-parsley-pattern="^[a-zA-Z ]+$"  data-parsley-maxlength="100" >
                                </div>
                                 <div class="form-group col-md-12">
                                    <label for="Address">Email<span class="text-danger">*</span></label>
                                    <input type="email" parsley-trigger="change" required class="form-control" id="email" name="email" placeholder="Enter Email">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Address">Mobile<span class="text-danger">*</span></label>
                                    <input type="text"  data-parsley-type="number"   data-parsley-length="[10,10]" required class="form-control" id="mobile" name="mobile" placeholder="Enter Mobile" pattern="[1-9]{1}[0-9]{9}">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Address">Latitude<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="lat" name="lat" placeholder="Enter Latitude" data-parsley-pattern="^[0-9]*\.?[0-9]+$">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Address">Longitude<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="lang" name="lan" placeholder="Enter Longitude" data-parsley-pattern="^[0-9]*\.?[0-9]+$">
                                </div>
                                <div class="form-group col-md-12">
                                    <label for="Address">Address<span class="text-danger"></span></label>
                                    <textarea class="form-control" required id="address" name="address"></textarea>
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
                    
                    <div class="col-md-8">
                        <div class="card-box table-responsive pl-2">
                            <h4 class="m-t-0 header-title"><b> </b></h4>
                           <ul class="nav nav-tabs">
                                        <li class="nav-item">
                                            <a href="#activestates" data-toggle="tab" aria-expanded="true" class="nav-link active">
                                                Active Dealers
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a href="#inactivestates" data-toggle="tab" aria-expanded="false" class="nav-link ">
                                                Inactive Dealers
                                            </a>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane fade show active" id="activestates">
                                         <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>Brand Name</th>
                                    <th>Dealership Name</th>
                                    <th>Dealer Principal</th>
                                    <th>Email</th>
                                    <th>Mobile</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="dBean" items="${activeDealerList}">
                                <tr>
                                      <td>${dBean.brand_name }</td>
                                      <td>${dBean.dealer_name }</td>
                                      <td>${dBean.contact_persion }</td>
                                      <td>${dBean.email }</td>
                                      <td>${dBean.mobile }</td>
                                    <td><a href="<%=dashboardURL%>editDealer/${dBean.sk_dealer_id}" class="fa fa-edit"></a>&nbsp;&nbsp;&nbsp;<a href="#"  class="fa fa-trash" data-id="${dBean.sk_dealer_id}" data-toggle="modal" data-target="#myModal"></a></td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                                        </div>
                                        <div class="tab-pane fade  " id="inactivestates">
                                         <table id="datatable-buttons" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                    <th>Brand Name</th>
                                    <th>Dealership Name</th>
                                    <th>Dealer Principal</th>
                                    <th>Email</th>
                                    <th>Mobile</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="dBean" items="${inactiveDealerList}">
                                <tr>
                                      <td>${dBean.brand_name }</td>
                                      <td>${dBean.dealer_name }</td>
                                      <td>${dBean.contact_persion }</td>
                                      <td>${dBean.email }</td>
                                      <td>${dBean.mobile }</td>
                                    <td><a href="#"  title="Restore the Dealer" class="mdi mdi-backup-restore" data-restoreid="${dBean.sk_dealer_id}" data-toggle="modal" data-target="#myModal1"></a></td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                                        </div>
                                       
                                    </div>

                            
                        </div>
                    </div>
                </div>
                <!-- end row -->

            </div> <!-- end container -->
        </div>
        </div></div>
        <!-- Signup modal content -->
                            


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
        	var url="<%=dashboardURL%>deleteDealer/"+value;
        	$(".btn-edit").attr("href",url);
        });
        
        $(".mdi-backup-restore").on("click",function(){
        	var value=$(this).attr("data-restoreid");
        	var url="<%=dashboardURL%>restoreDealer/"+value;
        	$(".btn-restore").attr("href",url);
        });
        
       
        
    </script>
       
       <script>
       $("#brand_id").on("change",function(){
       	var dealership_name=$("#dealership_name").val();
       	getDealerExistance(dealership_name);
       });
       </script>
       <script>
       
        
        $(".btnsubmit").on("click",function(){
      	  $('form').parsley();
        	if($("#dealererrormsg").text()=="Dealer already exists....!"){
        		 $("#testbox").prop('required',true);
        		 setTimeout(function(){
           			var id=$("#testbox").attr("data-parsley-id");
           			  $("#parsley-id-"+id+"").hide();
           		}, 10);
        	}else{
        		 $("#testbox").prop('required',false);
        	}
         
       });
        function getDealerExistance(name){
        	var brand_id=$("#brand_id").val();
        	  $("#dealererrormsg").text("");
        	  $.ajax({
                  url: "<%=dashboardURL%>getDealerExistance",
                  type: "GET", 
                    data: { 'name': name,'brand_id':brand_id  },
                  success: function(response)
                              {
                	  var msg=response.message;
                	  var statusmessage=response.status;
					   if(msg=="exist"  && statusmessage=="active"){
						   $("#dealererrormsg").append("Dealer already exists....!")
					   }else if(msg=="exist" && statusmessage=="inactive"){
  						   $("#dealererrormsg").text("Dealer is in Inactive tab ,Please restore ...!")
  					   }else{
 					var id=$("#testbox").attr("data-parsley-id");
 				   $("#parsley-id-"+id+"").remove();
						   $("#dealererrormsg").text("");
					   }
                              }
        	  });
        }
        
        $(".resetbtn").on("click",function(){
         	var id=$("#testbox").attr("data-parsley-id");
   			   $("#parsley-id-"+id+"").remove();
   					   $("#errormsg").text("");
   					   $("#testbox").prop('required',false);
         });
    
        </script>
        
        
          
        <script>
        jQuery(document).ready(function() {
	// 3 Capitalize string every 1st chacter of word to uppercase
	jQuery('#dealership_name').keyup(function() 
	{
		var str = jQuery('#dealership_name').val();
		var spart = str.split(" ");
		for ( var i = 0; i < spart.length; i++ )
		{
			var j = spart[i].charAt(0).toUpperCase();
			spart[i] = j + spart[i].substr(1);
		}
      jQuery('#dealership_name').val(spart.join(" "));
	
	});
	
	jQuery('#contact_person').keyup(function() 
			{
				var str = jQuery('#contact_person').val();
				var spart = str.split(" ");
				for ( var i = 0; i < spart.length; i++ )
				{
					var j = spart[i].charAt(0).toUpperCase();
					spart[i] = j + spart[i].substr(1);
				}
		      jQuery('#contact_person').val(spart.join(" "));
			
			});
});
        </script>  
</body>
</html>