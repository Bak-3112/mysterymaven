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

<<meta charset="utf-8" />
        <title>Mystery</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" content="no-cache" />

        <link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">

        <!-- App css -->
        <link href="<%=UI_PATH%>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/metismenu.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/style.css" rel="stylesheet" type="text/css" />

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
                                    <li class="breadcrumb-item"><a href="#">Outlet</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">Create Outlet</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                
                <div class="col-lg-12">

                        <div class="card-box ">
                            <h4 class="header-title m-t-0">  </h4>
                            <form method="POST" action="<%=dashboardURL%>createOutletPost" enctype="multipart/form-data" autocomplete="off">
                               <div class="row">
                              
                              <div class="form-group col-md-6">
                                   <label for="brand_name">Brand<span class="text-danger">*</span></label>
                                    <select id="sk_brand_id" name="sk_brand_id" required class="form-control" onchange="getDealers(this.value);" >
                                    <option value="">Select brand</option>
                                    	 <c:forEach var="bBean" items="${brandList}">
                                    <option value="${bBean.sk_brand_id}">${bBean.brand_name}</option> 
                                    </c:forEach> 
                                    </select>
                                </div>
                               <div class="form-group col-md-6" >
                                    <label for="dealer_name">Dealer Name<span class="text-danger">*</span></label>
                                    <select id="sk_dealer_id" name="sk_dealer_id"  required class="form-control"  >
                                    <option value="">Select dealer</option>
                                    <c:forEach var="dBean" items="${activeDealerList}">
                                    	<option value="${dBean.sk_dealer_id }">${dBean.dealer_name }</option>
                                    	<%-- <input type="hidden" id="dealer_name" name="dealer_name" value="${dealer_name} "> --%>
                                    </c:forEach> 
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="Address">Location Name<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="outlet_name" name="outlet_name" onkeyup="exist(this.value)" placeholder="Enter Location name" data-parsley-maxlength="100"  >
                                    <span id="outleterrormsg" style="color:red"></span>
                                 <input type="text"   id="testbox" style="display: none">
                                </div>
                                 <div class="form-group col-md-3">
                                    <label for="Address">Outlet ID<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="outlet_id" name="outlet_id" onkeyup="existt(this.value)" placeholder="Enter outlet ID">
                                    <span id="outletiderrormsg" style="color:red"></span>
                                 <input type="text"   id="testbox" style="display: none">
                                </div>
                                <div class="form-group col-md-6">
                                   <label for="pass1">Outlet Type<span class="text-danger">*</span></label>
                                    <select id="outlet_type" name="outlet_type" required class="form-control" >
                                    <option value="">Select outlet type</option>
                                    	<option value="Sales">Sales</option>
                                    	<option value="Service">Service</option>
                                    	<!-- <option value="Sales & Service">Sales & Service</option> -->
                                    </select>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="region_name">Region Name<span class="text-danger">*</span></label>
									<select id="sk_region_id" name="sk_region_id" required class="form-control "   data-placeholder="Choose Regions.."  >
									<option value="" >Select Region</option>
                                    <c:forEach var="rBean" items="${activeRegionList}">
                                     <option value="${rBean.sk_region_id }">${rBean.region_name }</option>
                                     </c:forEach>
                                     </select>                           
                                 </div>
                                <div class="form-group col-md-6">
                                   <label for="state_name">State<span class="text-danger">*</span></label>
                  					<select id="sk_state_id" name="sk_state_id" required class="form-control " onchange="getCity(this.value);" >
                                     <option value="">Select state</option>
                                     <c:forEach var="uBean" items="${stateList}">
                                     <option value="${uBean.sk_state_id }">${uBean.state_name }</option>
                                     </c:forEach> 
                                     </select>   
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="city_name">City Name<span class="text-danger">*</span></label>
									<select id="sk_city_id" name="sk_city_id" required class="form-control"  >
									<option value="" >Select City</option>
                                    <c:forEach var="uBean" items="">
                                     <option value="${uBean.sk_city_id }">${uBean.city_name }</option>
                                     </c:forEach>
                                     </select>                           
                                 </div>
                                 
                                <div class="form-group col-md-6">
                                    <label for="Address">Latitude<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="lat" name="lat" placeholder="Enter latitude" data-parsley-pattern="^[0-9]*\.?[0-9]+$">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="Address">Longitude<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="lang" name="lang" placeholder="Enter longitude" data-parsley-pattern="^[0-9]*\.?[0-9]+$">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="Address">Contact Person<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="contact_person" name="contact_person" placeholder="Enter contact person" data-parsley-pattern="^[a-zA-Z ]+$"  data-parsley-maxlength="100">
                                </div>
                                
                                <div class="form-group col-md-6">
                                    <label for="Address">Email<span class="text-danger">*</span></label>
                                    <input type="email" parsley-trigger="change" required class="form-control" id="email" name="email" placeholder="Enter email">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="Address">Mobile<span class="text-danger">*</span></label>
                                    <input type="text"  data-parsley-type="number"   data-parsley-length="[10,10]" required class="form-control" id="mobile" name="mobile" placeholder="Enter Mobile" pattern="[1-9]{1}[0-9]{9}">
                                </div>
                                 <div class="form-group col-md-6">
                                    <label for="Adress">Address<span class="text-danger">*</span></label>
                                    <textarea class="form-control"  name="address" id="address" required></textarea>
                                </div> 
                                <div class="form-group col-md-6">
                                    <label for="Pincode">Pin-code<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="Pincode" name="Pincode" placeholder="Enter Pincode" pattern="^[1-9][0-9]{5}$">
                                </div>
                                </div>
                                 
                                <div class="form-group text-right m-b-0">
                                    <button class="btn btn-gradient waves-effect waves-light"  type="submit">
                                        Submit
                                    </button>
                                    <button type="reset" class="btn btn-light waves-effect m-l-5"  id="reset">
                                        Reset
                                    </button>
                                    
                                </div>
							
                            </form>
                        </div> <!-- end card-box -->
                    </div>
                    
                   
                </div>
                <!-- end row -->

            </div> <!-- end container -->
        </div>
        </div>
		</div>
		<!-- end container -->
	

	
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
     <!-- Parsley js -->
        <script type="text/javascript" src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>
	<!-- Init Js file -->
	<script type="text/javascript"
		src="<%=UI_PATH%>/assets/pages/jquery.form-advanced.init.js"></script>
	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.33.1/sweetalert2.js"></script>
	
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

                $('form').parsley();
                $('#reset').click(function () {
        		    $('form').parsley().reset();
        		}); 
            } );
            
           

        </script>
        <script>
        function getDealers(sk_brand_id) 
        { 
         
           $("#sk_dealer_id option").remove();  
         // var brand_id=$("#brand_id").val();
          
            $.ajax({
                  url: "<%=dashboardURL%>getDealersByBrandId",
                  type: "GET", 
                    data: { 'sk_brand_id': sk_brand_id },
                    
                  success: function(response)
                  
                              {
                	  $("#sk_dealer_id").append("<option value=''>Select Model</option>");
                	  $.each(response,function(k,v){
                          $("#sk_dealer_id").append("<option value='"+v.sk_dealer_id+"'>"+v.dealer_name+"</option>");

                	  });
                                    
                              }
                  });  
          
        }
        </script>
        <script>
        function getRegions(sk_dealer_id) 
        { 
         
           $("#sk_region_id option").remove();  
         // var brand_id=$("#brand_id").val();
          
            $.ajax({
                  url: "<%=dashboardURL%>getRegionsByDealerId",
                  type: "GET", 
                    data: { 'sk_dealer_id': sk_dealer_id },
                    
                  success: function(response)
                  
                              {
                	  $("#sk_region_id").append("<option value=''>Select Region</option>");
                	  $.each(response,function(k,v){
                          $("#sk_region_id").append("<option value='"+v.sk_region_id+"'>"+v.region_name+"</option>");

                	  });
                                    
                              }
                  });  
          
        }
        </script>
<script>
        function getStates(sk_region_id) 
        { 
         
           $("#sk_state_id option").remove();  
         // var brand_id=$("#brand_id").val();
          
            $.ajax({
                  url: "<%=dashboardURL%>getStatesByRegionId",
                  type: "GET", 
                    data: { 'sk_region_id': sk_region_id },
                  success: function(response)
                              {
                	  $("#sk_state_id").append("<option value=''>Select State</option>");
                	  $.each(response,function(k,v){
                          $("#sk_state_id").append("<option value='"+v.sk_state_id+"'>"+v.state_name+"</option>");

                	  });
                                    
                              }
                  });  
          
        }
        </script>

<script>
        function getCity(sk_state_id) 
        { 
         
           $("#sk_city_id option").remove();  
         // var brand_id=$("#brand_id").val();
          
            $.ajax({
                  url: "<%=dashboardURL%>getCitiesByStateId",
                  type: "GET", 
                    data: { 'sk_state_id': sk_state_id },
                  success: function(response)
                              {
                	  $("#sk_city_id").append("<option value=''>Select City</option>");
                	  $.each(response,function(k,v){
                          $("#sk_city_id").append("<option value='"+v.sk_city_id+"'>"+v.city_name+"</option>");

                	  });
                                    
                              }
                  });  
          
        }
        </script>
<!-- Outlet existeor not -->
        <script type="text/javascript">
        function exist(name){
        	var dealer_id=$("#sk_dealer_id").val();
         	 $.ajax({
                  url: "<%=dashboardURL%>getOutletExistance",
                  type: "GET", 
                    data: { 'name': name,'dealer_id':dealer_id },
                  success: function(response)
                              {
                	  var msg=response.message;
                	 var statusmessage=response.status;
  					   if(msg=="outletexist" && statusmessage=="active"){
  						   $("#outleterrormsg").text("Outlet is  already exists...!")
  					   }else if(msg=="outletexist" && statusmessage=="inactive"){
  						   $("#outleterrormsg").text("Outlet is in Inactive tab ,Please restore ...!")
  					   }else{
  					var id=$("#testbox").attr("data-parsley-id");
  				   $("#parsley-id-"+id+"").remove();
  						   $("#outleterrormsg").text("");
  					   }
                              }
        	  });
         }
        $("#reset").on("click",function(){
            if($("#outleterrormsg").text()!="")
              {
              $("#outleterrormsg").text("");
              }
          });
        </script>
        </script>
<!-- OutletID existe or not -->
        <script type="text/javascript">
        function existt(name){
         	 $.ajax({
                  url: "<%=dashboardURL%>getOutletIdExistance",
                  type: "GET", 
                    data: { 'name': name, },
                  success: function(response)
                              {
                	  var msg=response.message;
                	 var statusmessage=response.status;
  					   if(msg=="outletexist" && statusmessage=="active"){
  						   $("#outletiderrormsg").text("Outlet Id is  already in use...!")
  					   }else if(msg=="outletexist" && statusmessage=="inactive"){
  						   $("#outletiderrormsg").text("Outlet Id is in Inactive tab ,Please restore ...!")
  					   }else{
  					var id=$("#testbox").attr("data-parsley-id");
  				   $("#parsley-id-"+id+"").remove();
  						   $("#outletiderrormsg").text("");
  					   }
                              }
        	  });
         }
        $("#reset").on("click",function(){
            if($("#outletiderrormsg").text()!="")
              {
              $("#outletiderrormsg").text("");
              }
          });
        </script>
        <script>
       $("#sk_dealer_id").on("change",function(){
       	var dealership_name=$("#outlet_name").val();
       	exist(name);
       });
       </script>
</body>
</html>