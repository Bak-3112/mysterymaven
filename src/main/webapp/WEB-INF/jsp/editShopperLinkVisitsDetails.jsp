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
        <title>MYS-Edit Shopper Details</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">
		
		<link href="<%=UI_PATH%>/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <!-- DataTables --> 
        <link href="<%=UI_PATH%>/plugins/datatables/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/plugins/datatables/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css" />
         <!-- Responsive datatable examples -->
        <link href="<%=UI_PATH%>/plugins/datatables/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <!--  <link rel="stylesheet" href="<%=UI_PATH%>/assets/css/jquery-ui.css"> -->
        
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        
        <!-- Custom box css -->
        <link href="<%=UI_PATH%>/plugins/custombox/css/custombox.min.css" rel="stylesheet">

        <!-- App css -->
        <link href="<%=UI_PATH%>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/metismenu.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/style.css" rel="stylesheet" type="text/css" />

        <script src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>
		<link rel="stylesheet" href="<%=UI_PATH%>/css/nice-select.css" type="text/css" />
		<link rel="stylesheet" type="text/css"
	href="<%=UI_PATH%>/design/css/time.css">
<link rel="stylesheet"
	href="<%=UI_PATH%>/design/css/custom.css"
	type="text/css">
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
                                    <li class="breadcrumb-item"><a href="#">Mystery</a></li>
                                    <li class="breadcrumb-item"><a href="#">Update Mystery Shopping Visit</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">Update Mystery Shopping Visit</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                
                <div class="col-lg-12">

                        <div class="card-box ">
                            <h4 class="header-title m-t-0">  </h4>
                            <form method="POST" action="<%=dashboardURL%>updateMysteryShopperLinkDetails"autocomplete="off">
                               <div class="row">
                              
                               <div class="form-group col-md-3">
                                    <label for="Address">Name<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="name" name="name" placeholder="Enter Full Name" value="${name}">
                                     <input type="hidden" required class="form-control" id="sk_shopper_id" name="sk_shopper_id" placeholder="Enter Full Name" value="${sk_shopper_id}">
                                </div>
                                 <div class="form-group col-md-3">
											<label for="Address">Car Owned Brand<span
												class="text-danger">*</span></label> 
												<input type="text" class="form-control" id="shoppers_car_owned_brand" name="shoppers_car_owned_brand"  value="${shoppers_car_owned_brand}" placeholder="Enter Car Owned Brand" required>
										</div>
										
										<div class="form-group col-md-3">
											<label for="Address">Car Owned Model<span
												class="text-danger">*</span></label> <input type="text" 
												class="form-control" id="shoppers_car_owned" name="shoppers_car_owned"  value="${shoppers_car_owned}"
												
												placeholder="Enter Car Owned Model" required>
										</div>
										
										<div class="form-group col-md-3">
											<label for="Address">Year of Purchase<span
												class="text-danger">*</span></label> <input type="text" 
												class="form-control" id="shoppers_car_yop" name="shoppers_car_yop"  value="${shoppers_car_yop}"
												placeholder="Enter Year  Of Purchase" required data-parsley-length="[4, 4]" data-parsley-type="integer">
										</div>
										
                                <div class="form-group col-md-3">
                                    <label for="Address">Email given to dealership<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="email" name="email"  value="${email}"  placeholder="Enter Email">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="Address">Contact Number<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="contact_number" name="contact_number"  value="${contact_number}" data-parsley-type="digits" minlength="10" maxlength="10" placeholder="Enter Contact Number">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="Address">Age<span class="text-danger">*</span></label>
                                    <input type="text" required class="form-control" id="age" name="age"  value="${age}"  data-parsley-type="digits" placeholder="Enter Age">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Gender<span class="text-danger">*</span></label>
                                    <select id="gender" name="gender" required class="form-control" >
                                    <option value="${gender}">${gender}</option>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Quarter<span class="text-danger">*</span></label>
                                    <select id="quarter" name="quarter_disabled"  class="form-control" disabled>
                                    <option value="${quarter}">${quarter}</option>
                                    <option value="Q1">Q1</option>
                                    <option value="Q2">Q2</option>
                                    <option value="Q3">Q3</option>
                                    <option value="Q4">Q4</option>
                                    </select>
                                    <input type="hidden" name="quarter" value="${quarter}" />
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Year<span class="text-danger">*</span></label>
                                    <select id="year" name="year_disabled"     class="form-control" disabled  >
                                    <option value="${year}">${year}</option>
                                    <option value="2018">2018</option>
                                    <option value="2019">2019</option>
                                    <option value="2020">2020</option>
                                    <option value="2021">2021</option>
                                  </select>
                                    <input type="hidden" name="year" value="${year}" />
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Mode of Contact<span class="text-danger">*</span></label>
                                    <select id="mode_of_contact" name="mode_of_contact_disabled"   class="form-control" disabled>
                                  	<option value="${mode_of_contact}">${mode_of_contact}</option>
                                  	<option value="EMail/Website">EMail/Website</option>
                                  	<option value="Telephone">Telephone</option>
                                  	<option value="Walk In">Walk In</option>
                                  	<option value="Online Sales Channel">Online Sales Channel</option>
                                    </select>
                                     <input type="hidden" name="mode_of_contact" value="${mode_of_contact}" />
                                </div>
                               <div class="form-group col-md-3">
                                    <label for="Address">Scheduled Date<span class="text-danger">*</span></label>
                                    <input type="text" id="visit_date" name="visit_date"  value="${visit_date}" placeholder="Select Date" required   class="datepicker form-control">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="Address">Visit Date<span class="text-danger">*</span></label>
                                    <input type="text" id="shopper_link_visit_date" name="shopper_link_visit_date"  value="${shopper_link_visit_date}" placeholder="Select Date" required   class="datepicker form-control">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Brand<span class="text-danger">*</span></label>
                                    <select id="brand_id" name="brand_id_disabled"  class="form-control" onchange="getOutlets(this.value);getModelVariant(this.value)" disabled>
                                   <%-- <option value="${brand_id}">${brand_name}</option> --%>
                                   	<c:forEach var="dbBean" items="${activebrandList}">
                                     <option value="${dbBean.sk_brand_id }" <c:if test ="${brand_id == dbBean.sk_brand_id }">selected </c:if>>${dbBean.brand_name }</option>
                                     </c:forEach>
                                    </select>
                                     <input type="hidden" name="brand_id" value="${brand_id}" />
                                    
                                </div>
                               
                                <div class="form-group col-md-3">
                                    <label for="pass1">Outlet<span class="text-danger">*</span></label>
                                    <select id="outlet_id" name="outlet_id_disabled"  class="select2 form-control select2-multiple"  disabled>
                                    <%--  <option value="${outlet_id}">${outlet_name}</option> --%>
                                    <c:forEach var="mvBean" items="${outletList}">
                                     <option value="${mvBean.outlet_id}"<c:if test ="${outlet_id == mvBean.outlet_id }">selected </c:if>>${mvBean.dealer_name} ${mvBean.outlet_location_id} ${mvBean.outlet_name}</option>
                                     </c:forEach>
                                     </select>
                                 <input type="hidden" name="outlet_id" value="${outlet_id}" />
                                </div>
                                 <div class="form-group col-md-3">
                                    <label for="pass1">Model-Variant Shopped<span class="text-danger">*</span></label>
                                   <select id="variant_id" name="variant_id_disabled"   class="select2 form-control select2-multiple" data-parsley-errors-container="#chke1"  disabled>
                                     <%-- <option value="${brand_model_variant_id}">${model_name} ${variant_name }</option> --%>
                                     <c:forEach var="mvBean" items="${ModelVariantListByid}">
                                     <option value="${mvBean.variant_id}"<c:if test ="${brand_model_variant_id == mvBean.variant_id}">selected </c:if>>${mvBean.model_name} ${mvBean.variant_name}</option>
                                     </c:forEach> 
                                     </select>
                                     <input type="hidden" name="variant_id" value="${brand_model_variant_id}" />
                                     <input type="hidden" name="dealer_id" value="${dealer_id}" />
                                     
                                    <div id="chke1"></div>
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="pass1">Type of Mystery shopper<span class="text-danger">*</span></label>
                                    <select id="type" name="type" required class=" form-control" >
                                  	<option value="${type}">${type}</option>
                                  	<option value="EY Employee">EY Employee</option>
                                  	<option value="Friends and family">Friends and family</option>
                                  
                                    </select>
                                </div>
                                
                          <div class="form-group col-md-3">
                                    <label for="Start-&-End-Time">Start Time</label>
                    <div class="input-group date" id="timepicker1">
                      <input class="form-control" id="start_time" name=start_time value="${start_time}"
                        placeholder="Select" required> <span
                        class="input-group-addon input-group-append"><span class="fa fa-clock-o input-group-text"></span></span>
                    </div>    </div>
                                <div class="form-group col-md-3">
                    <label for="Start-&-End-Time">End Time</label>
                    <div class="input-group date" id="timepicker2">
                      <input class="form-control" id="end_time" name="end_time" value="${end_time}"
                        placeholder="Select" required> <span
                        class="input-group-addon input-group-append"><span class="fa fa-clock-o input-group-text"></span></span>
                    </div>
                  </div>
                                <div class="form-group col-md-3">
										<label for="Met-SC">Met SC</label> <select
											class="form-control" id="met_sc" name="met_sc" onchange="getMetDuringVisit(this.value);">
											<option value="${met_sc}">${met_sc}</option>
											<option value="yes">yes</option>
											<option value="no">no</option>
										</select>
									</div>
									<div class="form-group col-md-3">
										<label for="SC-Name">SC Name</label> <input id="sc_name"
											name="sc_name" value="${sc_name}" type="text"  placeholder="Enter SC Name"
											class="form-control"/>
									</div>
									<div class="form-group col-md-3">
										<label for="SC-Designation">SC Designation</label> <select
											class="form-control" id="sc_designation" name="sc_designation" >

											<option value="${sc_designation}">${sc_designation}</option>
											<option value="Sales Manager">Sales Manager</option>
											<option value="Sales Consultant">Sales Consultant</option>
											<option value="NA">NA</option>
										</select>
									</div>
									<div class="form-group col-md-3">
										<label for="SC-Description">SC Description</label> <input
											id="sc_description" name="sc_description" value="${sc_description}" type="text"
											placeholder="Enter SC Description" class="form-control" />
									</div>
                                </div>
                                
                                &nbsp;&nbsp;&nbsp; <h6>Personal Details Provided At Dealership</h6>
                                <br>
                                <div class="row">
                                <div class="form-group col-md-3">
										<label for="Name1">Name</label> <input id="shopper_link_name"
											name="shopper_link_name" type="text" value="${shopper_link_name}" placeholder="Enter Name" value=""
											class="form-control" required/>
									</div>
									<div class="form-group col-md-3">
										<label for="Age">Age</label> <input id="shopper_link_age"
											name="shopper_link_age" type="text" value="${shopper_link_age}"  placeholder="Enter Age" value=""
											class="form-control" required/>
									</div>
									<div class="form-group col-md-3">
										<label>Gender</label> <br> 
										<input type="radio"  name="shopper_link_gender"
																value="Male"
																<c:if test="${shopper_link_gender =='Male'}">checked</c:if>>
															<span>Male</span> 
															<input type="radio" name="shopper_link_gender"
																value="Female"
																<c:if test="${shopper_link_gender =='Female'}">checked</c:if>>
                                                                <span>Female</span>
										
										<%-- 
											<input type="radio" value="Male"  value="${shopper_link_gender=='Male'}" name="shopper_link_gender">
											<span>Male</span>
										</label> <label class="radio-label"> <input type="radio"
											value="Female" name="shopper_link_gender" value="${shopper_link_gender=='f'}"> <span>Female</span>
										</label> --%>
									</div>
									<div class="form-group col-md-3">
										<label for="Age1">Email ID Given To Dealership</label> <input
											id="shopper_link_email" name="shopper_link_email" type="email"
											placeholder="Enter Email" value="${shopper_link_email}" class="form-control" required/>
									</div>
									<div class="form-group col-md-3">
										<label for="number1">Contact Number</label> <input
											id="shopper_link_contact" name="shopper_link_contact" type="text"
											placeholder="Enter Mobile No" value="${shopper_link_contact}" class="form-control" required/>
									</div>
                                </div>
                                &nbsp;&nbsp;&nbsp; <h6>Vehicle Driven To Deaership</h6>
                                <br>
                                <div class="row">
                                <div class="form-group col-md-3">
										<label for="Name1">Brand</label> <input id="shoppers_link_shoppersCarBrand"
											name="shoppers_link_shoppersCarBrand" value="${shoppers_link_shoppersCarBrand}" type="text"  placeholder="Enter Brand"
											class="form-control" />
									</div>
									<div class="form-group col-md-3">
										<label for="Model">Model</label> <input id="shoppers_link_shoppersCarModel" name="shoppers_link_shoppersCarModel"
											type="text" value="${shoppers_link_shoppersCarModel}" placeholder="Enter Model" class="form-control" />
									</div>
									<div class="form-group col-md-3">
										<label for="Year-of-construction">Year of Construction</label>
										<input id="shoppers_link_shoppersCarYop" name="shoppers_link_shoppersCarYop" value="${shoppers_link_shoppersCarYop}" type="text"
											placeholder="Enter YOC" class="form-control" />
									</div>
								</div>
								&nbsp;&nbsp;&nbsp; <h6>Product Genius Details</h6>
                                <br>
                                <div class="row">
                                <div class="form-group col-md-3">
										<label for="Met-SC1">Met during visit</label> <input id="Shopper_link_productMet"
											name="Shopper_link_productMet" type="text" value="${Shopper_link_productMet}"placeholder="Enter Details"
											class="form-control" />
									</div>
									<div class="form-group col-md-3">
										<label for="Name-provided">Name provided</label> <input
											id="Shopper_link_productNameProvided" name="Shopper_link_productNameProvided" value="${Shopper_link_productNameProvided}" type="text"
											placeholder="Enter Details" class="form-control" />
									</div>
									<div class="form-group col-md-3">
										<label for="Select-Designation1">Name</label> <input
											id="Shopper_link_productName" name="Shopper_link_productName" value="${Shopper_link_productName}" type="text"
											placeholder="Enter Details" class="form-control" />
									</div>
									<div class="form-group col-md-3">
										<label for="Description1">Description</label> <input
											id="Shopper_link_productDesc" name="Shopper_link_productDesc" value="${Shopper_link_productDesc}" type="text"
											placeholder="Enter Details" class="form-control" />
									</div>
									<input type="hidden" id="visit_status" name="visit_status"  value="${visit_status }">
								</div>
                                 
                                <div class="form-group text-right m-b-0">
                                
                                    <button class="btn btn-gradient waves-effect waves-light" type="submit">
                                        Update
                                    </button>
                                    <a href="<%=dashboardURL%>getMysQuestionnaire/${sk_shopper_id}"><button type="button" class="btn btn-light waves-effect m-l-5">
                                        Cancel
                                    </button></a>
                                    
                                </div>
							
                            </form>
                        </div> <!-- end card-box -->
                    </div>
                    
                 
                </div>
                <!-- end row -->
				</div>
				</div>
            </div> <!-- end container -->
        </div>
        
       
        <!-- end wrapper -->


        <!-- Footer -->
        <jsp:include page="include/footer.jsp"></jsp:include>
        <!-- End Footer -->


        <!--  jQuery  -->
        <%-- <script src="<%=UI_PATH%>/assets/js/jquery.min.js"></script> --%>
        <script
    src='<%=UI_PATH%>/design/js/date&time/jquery-2.2.4.min.js'></script>
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

<script src='<%=UI_PATH%>/design/js/date&time/moment.min.js'></script>
  
<script src='<%=UI_PATH%>/design/js/date&time/bootstrap-datetimepicker.min.js'></script>
  <script src="<%=UI_PATH%>/design/js/date&time/date&time.js"></script>
    
        <!--  App js -->
        <script src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
        <script src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>
        <script src="<%=UI_PATH%>/js/script.js"></script>
          <script>
          
          </script>
        
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
        
    <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
                
            });
        </script>
        
        
            <script>
  $( function() {
    $( ".datepicker" ).datepicker({
    	dateFormat: "yy-mm-dd",
    	onSelect:function(){
    		$(this).parsley().validate()
    	}
    });
  } );
  </script>
  
  <input type="hidden" id="sc_name1" name="sc_name1" value="${sc_name}" > 
   <input type="hidden" id="sc_designation1" name="sc_designation1" value="${sc_designation}" > 
    <input type="hidden" id="sc_description1" name="sc_description1" value="${sc_description}" > 
  <script>
        function getOutlets(brand_id) 
        { 
           $("#outlet_id option").remove();  
          $.ajax({
                  url: "<%=dashboardURL%>getOutletsByBrandId",

                  type: "GET", 
                    data: { 'brand_id': brand_id },
                    
                  success: function(response)
                  
                              {
                	 
                	  $("#outlet_id").append("<option value=''>Select Outlet</option>");
                	  $.each(response,function(k,v){
                          $("#outlet_id").append("<option value='"+v.outlet_id+"'>"+v.dealer_name+" "+v.outlet_location_id+"  "+v.outlet_name+"</option>");

                	  });
                                    
                              }
                  });  
          
        }
  
      
        function getModelVariant(brand_id) 
        { 
         
           $("#variant_id option").remove();  
         // var brand_id=$("#brand_id").val();
          
            $.ajax({
                  url: "<%=dashboardURL%>getModelVariantById",
                  type: "GET", 
                    data: { 'brand_id': brand_id },
                  success: function(response)
                              {
                	
                	  $("#variant_id").append("<option value=''>Select Model-Variant</option>");
                	  $.each(response,function(k,v){
                          $("#variant_id").append("<option value='"+v.variant_id+"'>"+v.model_name+" "+v.variant_name+"</option>");

                	  });
                                    
                              }
                    });  
          
        }
        
        function getMetDuringVisit(Shopper_link_productMet) 
        { 
        	
           var  Shopper_link_productMet= Shopper_link_productMet;
         if(Shopper_link_productMet =='no')
        	 {
        	 $('#sc_name').val(""); 
        	 $('#sc_designation').val(""); 
        	 $('#sc_description').val(""); 
        	 }
         else
         {
        	var sc_name1=$("#sc_name1").val();
        	var sc_designation1=$("#sc_designation1").val();
        	var sc_description1=$("#sc_description1").val();
        	 $('#sc_name').val(sc_name1); 
        	 $('#sc_designation').val(sc_designation1); 
        	 $('#sc_description').val(sc_description1); 
         }
          
        }
        
        
        </script>
        
        
          <script>
		$(document).ready(function() {
			 var usedNames = {};
			$("select[name='gender'] > option").each(function() {
				if (usedNames[this.text]) {
					$(this).remove();
				} else {
					usedNames[this.text] = this.value;
				}
			}); 

			 var usedNames1 = {};
			$("select[name='quarter'] > option").each(function() {
				if (usedNames1[this.text]) {
					$(this).remove();
				} else {
					usedNames1[this.text] = this.value;
				}
			});   
			
			 var usedNames2 = {};
				$("select[name='year'] > option").each(function() {
					if (usedNames2[this.text]) {
						$(this).remove();
					} else {
						usedNames2[this.text] = this.value;
					}
				});   
				
				 var usedNames3 = {};
					$("select[name='mode_of_contact'] > option").each(function() {
						if (usedNames3[this.text]) {
							$(this).remove();
						} else {
							usedNames3[this.text] = this.value;
						}
					});  
					
					var usedNames4 = {};
					$("select[name='brand_id'] > option").each(function() {
						if (usedNames4[this.text]) {
							$(this).remove();
						} else {
							usedNames4[this.text] = this.value;
						}
					});
					
					var usedNames5 = {};
					$("select[name='outlet_id'] > option").each(function() {
						if (usedNames5[this.text]) {
							$(this).remove();
						} else {
							usedNames5[this.text] = this.value;
						}
					});
					
					var usedNames6 = {};
					$("select[name='variant_id'] > option").each(function() {
						if (usedNames6[this.text]) {
							$(this).remove();
						} else {
							usedNames6[this.text] = this.value;
						}
					});
					
					
					var usedNames7 = {};
					$("select[name='type'] > option").each(function() {
						if (usedNames7[this.text]) {
							$(this).remove();
						} else {
							usedNames7[this.text] = this.value;
						}
					});
					
					var usedNames8 = {};
					$("select[name='met_sc'] > option").each(function() {
						if (usedNames8[this.text]) {
							$(this).remove();
						} else {
							usedNames8[this.text] = this.value;
						}
					});
					
					var usedNames9 = {};
					$("select[name='sc_designation'] > option").each(function() {
						if (usedNames9[this.text]) {
							$(this).remove();
						} else {
							usedNames9[this.text] = this.value;
						}
					});
					
					

		});
	</script>
  
  
</body>
</html>