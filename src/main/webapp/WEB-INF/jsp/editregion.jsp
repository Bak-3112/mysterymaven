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

       <!-- App favicon -->
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
                                <!--     <li class="breadcrumb-item"><a href="#">Stock</a></li>
                                    <li class="breadcrumb-item"><a href="#">Settings</a></li> -->
                                </ol>
                            </div>
                            <h4 class="page-title">Edit Region</h4>
                        </div>
                    </div>
                </div>

                <div class="row">
                <jsp:include page="include/tab.jsp"></jsp:include>
        
                <div class="col-lg-6">

                        <div class="card-box ">
                            <h4 class="header-title m-t-0">  </h4>
                            <form method="POST" action="<%=dashboardURL%>updateregion/${rid }" enctype="multipart/form-data" autocomplete="off">
                                 <div class="form-group col-md-12">
                                    <label for="state_name">Region Name<span class="text-danger">*</span></label>
                                    <input type="text" required value="${region_name }" onkeyup="exist(this.value)" class="form-control" id="region_name" name="region_name" placeholder="Enter Region Name"   data-parsley-maxlength="100">
                                   <span id="errormsg" style="color:red"></span>
                                 <input type="text"   id="testbox" style="display: none">
                                </div>
                                <input type="hidden" id="stid" value="${cid}">
                                  <input type="hidden" id="region_id" value="${rid}">
                                <input type="hidden" id="stname" value="${city_name}">
                                <div class="form-group col-md-12">
                                    <label for="state_name">City Name<span class="text-danger">*</span></label>
									<select id="state_id" name="sk_city_id" required class="form-control select2 form-control select2-multiple" multiple placeholder="Choose Cities.." data-parsley-errors-container="#chke">
                                    <c:forEach var="cBean" items="${cityList}">
                                     <option value="${cBean.sk_city_id }">${cBean.city_name }</option>
                                     </c:forEach>
                                     </select> 
                                     <div id="chke"></div>                           
                                 </div>
                                
                                <div class="form-group text-right m-b-0">
                                    <button class="btn btn-gradient waves-effect waves-light btnsubmit" type="submit">
                                        Update
                                    </button>
                                     <a href="<%=dashboardURL%>region"><button type="button" class="btn btn-light waves-effect m-l-5">
                                        Cancel
                                    </button></a>
                                    
                                </div>

                            </form>
                        </div> <!-- end card-box -->
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

        </script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('form').parsley();
            });
           
        </script>
        
        
         <script>
        jQuery(document).ready(function() {
	// 3 Capitalize string every 1st chacter of word to uppercase
	jQuery('#city_name').keyup(function() 
	{
		var str = jQuery('#city_name').val();
		var spart = str.split(" ");
		for ( var i = 0; i < spart.length; i++ )
		{
			var j = spart[i].charAt(0).toUpperCase();
			spart[i] = j + spart[i].substr(1);
		}
      jQuery('#city_name').val(spart.join(" "));
	
	});
	
});
        
        
       var stid=$("#stid").val().split(",");
       var stname=$("#stname").val().split(",");
       for(var i=0;i<stname.length;i++){
       $("#state_id").prepend("<option value='"+stid[i]+"' selected>"+stname[i]+"</option>")
       }
       var optionValues =[];
       $('#state_id option').each(function(){
          if($.inArray(this.value, optionValues) >-1){
             $(this).remove()
          }else{
             optionValues.push(this.value);
          }
       });

       $(".btnsubmit").on("click",function(){
       	  $('form').parsley();
         	if($("#errormsg").text()=="Region is  already exist ...!"){
         		 $("#testbox").prop('required',true);
         		 setTimeout(function(){
            			var id=$("#testbox").attr("data-parsley-id");
            			  $("#parsley-id-"+id+"").hide();
            		}, 10);
         	}else	if($("#errormsg").text()=="Region is in Inactive tab ,Please restore ...!"){
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
    	   var regionid=$("#region_id").val();
       	 $.ajax({
                url: "<%=dashboardURL%>getRegionExistanceByid",
                type: "GET", 
                  data: { 'name': name,'regionid':regionid },
                success: function(response)
                            {
              	  var msg=response.message;
              	 var statusmessage=response.status;
 					   if(msg=="regionexist" && statusmessage=="active"){
 						   $("#errormsg").text("Region is  already exist ...!")
 					   }else if(msg=="regionexist" && statusmessage=="inactive"){
 						   $("#errormsg").text("Region is in Inactive tab ,Please restore ...!")
 					   }else{
 					var id=$("#testbox").attr("data-parsley-id");
 				   $("#parsley-id-"+id+"").remove();
 						   $("#errormsg").text("");
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
      
</body>
</html>