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
		<%-- <link rel="stylesheet" href="<%=UI_PATH%>/css/nice-select.css" type="text/css" /> --%>
		
		
		 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">       
        <link rel="stylesheet" href="<%=UI_PATH%>/assets/graphs/css/style.css">
        <link rel="stylesheet" href="<%=UI_PATH%>/assets/graphs/css/responsive.css">
       
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
				<div class="main-content  position-relative nav-left">
				<!-- <button class="btn btn-primary" id="export"> Download pdf</button> -->
					<form action="financialServiceAnalysis" method="GET">
						<div class="position-relative">

							<div class="pt-3 pb-3  bg-white rounded desc-header"
								style="padding-left: 0px !important; margin-left: 18px;">
								<div class="d-flex flex-wrap ">
									<div class="col">
										<h2 class="d-inline-block pt-2"><b>Financial Service Analysis</b></h2>
									</div>
									<div class="col col-160 ml-auto">
										<select class="form-control" name="brand_id"
											id="brand_id" onChange="getmonths(this.value);getdealers();getoutlets();getregionset();">
										 <c:forEach var="gBean" items="${BrandList}" varStatus="count">
												<option value="${gBean.brand_id}" <c:if test = "${brand_id == gBean.brand_id }">selected </c:if>>${gBean.brand_name}</option>
											</c:forEach> 
											
										</select>
									</div>
								</div>
							</div>
						</div>
				<div class="container-fluid my-3">
					<div class="card shadow-card">
						
						
							<div class="row d-flex justify-content-center px-4 mb-3  mt-3">

									<div class="col-sm-6 col-md py-3">
										<label>Month</label> <select class="form-control" name="month"
											id="month">
											<option value="">Select Month</option>
											<option value="all" <c:if test = "${month == 'all' }">selected </c:if>>All</option>
										 <c:forEach var="gBean" items="${getMonths}">
												<option value="${gBean.month}" <c:if test = "${month == gBean.month }">selected </c:if>>${gBean.month_name}</option>
											</c:forEach> 

										</select>
									</div>
									<div class="col-sm-6 col-md py-3">
										<label>Region</label> <select name="region_id"
											class="form-control" id="Region" onChange="getdealers();getoutlets()">
											<%-- <option value="">Select Region</option>
											<option value="all" <c:if test = "${region_id == 'all' }">selected </c:if>>All</option>
											 <c:forEach var="rgBean" items="${activeRegionList}">
												<option value="${rgBean.sk_region_id}" <c:if test = "${region_id == rgBean.sk_region_id }">selected </c:if>>${rgBean.region_name}</option>
											</c:forEach>  --%>
											
											<c:choose>
                                           <c:when test = "${role_id == 7}">
                                   <c:forEach var="rgBean" items="${activeRegionList}">
												<option value="${rgBean.sk_region_id}" <c:if test = "${region_id == rgBean.sk_region_id }">selected </c:if>>${rgBean.region_name}</option>
											</c:forEach> 
                                      </c:when>
                                <c:otherwise>
                                <option value="">Select Region</option>
                                <option value="all" <c:if test = "${region_id == 'all' }">selected </c:if>>All</option>
											 <c:forEach var="rgBean" items="${activeRegionList}">
												<option value="${rgBean.sk_region_id}" <c:if test = "${region_id == rgBean.sk_region_id }">selected </c:if>>${rgBean.region_name}</option>
											</c:forEach>
                                </c:otherwise>
                               </c:choose>
											
										</select>
									</div>
									<div class="col-sm-6 col-md py-3">
										<label>Dealer</label> <select name="dealer_id"
											class="form-control" id="Dealer"onChange="getoutlets()" >
											<%-- <option value="">Select Dealer</option>
											<option value="all" <c:if test = "${dealer_id == 'all' }">selected </c:if>>All</option>
											 <c:forEach var="gBean" items="${activedealersbyid}">
												<option value="${gBean.dealer_id}" <c:if test = "${dealer_id == gBean.dealer_id }">selected </c:if>>${gBean.dealer_name}</option>
											</c:forEach> --%> 
											
											<c:choose>
                                           <c:when test = "${role_id == 7}">
                                           <c:forEach var="gBean" items="${activedealersbyid}">
												<option value="${gBean.dealer_id}" <c:if test = "${dealer_id == gBean.dealer_id }">selected </c:if>>${gBean.dealer_name}</option>
											</c:forEach> 
                                      </c:when>
											<c:otherwise>
											<option value="">Select Dealer</option>
                                <option value="all" <c:if test = "${dealer_id == 'all' }">selected </c:if>>All</option>
											 <c:forEach var="gBean" items="${activedealersbyid}">
												<option value="${gBean.dealer_id}" <c:if test = "${dealer_id == gBean.dealer_id }">selected </c:if>>${gBean.dealer_name}</option>
											</c:forEach> 
                                </c:otherwise>
                               </c:choose>
										</select>
									</div>
									<div class="col-sm-6 col-md py-3">
										<label>Location</label> <select name="outlet_id"
											class="form-control" id="Location">
											<option value="">Outlet Location</option>
											<option value="all" <c:if test = "${outlet_id == 'all' }">selected </c:if>>All</option>
											 <c:forEach var="gBean" items="${activeoutletsbyid}">
												<option value="${gBean.outlet_id}" <c:if test = "${outlet_id == gBean.outlet_id }">selected </c:if>>${gBean.outlet_name}</option>
											</c:forEach> 
											
										</select>
									</div>
									<div class="col-sm-6 col-md py-3">
										<label>Year</label> <select name="year"
											class="form-control" id="year">
											<option value="">Select Year</option> 
											<c:choose>
                                      <c:when test="${empty year}">
                                       <option value="2019">2019</option>
                                         <option value="2020">2020</option>	
                                      </c:when>
                                      <c:otherwise>
                                        <option value="${year}"<c:if test = "${selected_year == year}">selected </c:if>>${year}</option>
                                         <option value="2019">2019</option>
                                          <option value="2020">2020</option>	
                                     </c:otherwise>
                                      </c:choose>
										</select>
									</div>
									<div class="col-sm-6 col-md py-4" style="max-width: 100px;">
										<label></label>
										<button class="btn btn-primary btn-block" type="submit">View</button>
									</div>
								</div>
							</div>
							</div>
					</form>
					<input type="hidden" value="${month}" id="monthtemp"> <input
							type="hidden" value="${dealer}" id="dealertemp"> <input
							type="hidden" value="${region}" id="regiontemp"> <input
							type="hidden" value="${outlet}" id="outlettemp">
					<div class="container">
						  <div class="row">
                        <div class="col-md-6">
                        <h6 class="text-center  chart-title-heading "><b>Did the dealer staff actively offer BMW Financial Services?</b></h6>
                     
                          <div class="sub-card mt-4 shadow">
                         <div class="container-fluid mt-3 mb-5">
                          <div class="row">
                            <div class="col-md-12">
                              <div class="">
                                <div class="card-body">
                                  <div class="scroll-x">
                                 <div id="container-bar-graph-lifestyle" style="height: 425px; margin: 0 auto"></div>
                                 <img alt="" src="" id="canvasimg1">
                               </div>
                                </div>
                              </div>
                            </div>
                          </div>
                      </div>
                 </div>
                 <h6 class="text-center  chart-title-heading "><b>Did the Staff proactively offer or explain the 360 buy back program?</b></h6>
                        <div class="sub-card mt-2 shadow">
                        <div class="container-fluid mt-3 mb-5">
                          <div class="row">
                            <div class="col-md-12">
                              <div class="">
                                <div class="card-body">
                                   <div class="scroll-x">
                                     <div id="container-bar-graph" style="height: 425px; margin: 0 auto"></div>
                                     <img alt="" src="" id="canvasimg2">
                                  </div>
                                </div>
                              </div>
                            </div>
                          </div>
                      </div>
                 </div>
                        </div>
                         <div class="col-md-6 ">
                          <h6 class="text-center  chart-title-heading "><b>Financial Options offered breakdown ?</b></h6>
                          <div class="sub-card mt-4 shadow">
                            <div class="" id="processchart" style="width: 100%; height: 470px; margin: 0 auto"></div>
                           <img alt="" src="" id="canvasimg3">
                           </div><br>
                           <h6 class="text-center  chart-title-heading "><b>Did staff proactively handover finance offer ?</b></h6>
                           <div class="sub-card mt-2 shadow">
                         <div class="container-fluid mt-3 mb-5">
                          <div class="row">
                            <div class="col-md-12">
                              <div class="">
                                <div class="card-body">
                                  <div class="scroll-x">
                                 <div id="container-bar-graph-offer" style="height: 425px; margin: 0 auto"></div>
                                 <img alt="" src="" id="canvasimg1">
                               </div>
                                </div>
                              </div>
                            </div>
                          </div>
                      </div>
                 </div>
                           
                 </div>
                         </div> 
                      </div>
					</div>
				</div>

			</div>
			<!-- container -->

		</div>
      </div>
       
                           
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
        <%-- <script src="<%=UI_PATH%>/js/script.js"></script> --%>
          
        <!-- Init Js file -->
        
        <%-- <script type="text/javascript" src="<%=UI_PATH%>/assets/pages/jquery.form-advanced.init.js"></script> --%>
        <!-- Parsley js -->
        <script type="text/javascript" src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>
        <!-- manoj d starts -->
        
        <!-- jQuery  -->
        
	
	<script
		src="<%=UI_PATH%>/assets/js/popper.min.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/bootstrap.min.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/metisMenu.min.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/waves.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/jquery.slimscroll.js"></script>
		
		<!-- App js -->
	<script
		src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>
		
        <!-- Graphs Js's start -->
        <!-- high-charts -->
	<script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="<%=UI_PATH%>/assets/graphs/js/canvasjs.min.js"></script>
        
        
        <!-- Graphs Js's end-->
        
        <script>
         function getmonths(bid){
         	$("#month").empty();
         	 $.ajax({
                  url: "<%=dashboardURL%>getmonths",
                  type: "GET", 
                    data: { 'bid': bid },
                  success: function(response)
                              {
                 	 $("#month").prepend("<option value=''>Select Month</option><option value='all'>All</option>");
 					$.each(response,function(k,v){
 						$("#month").append("<option value='"+v.month+"'>"+v.month_name+"</option>");
 					})
                              }
        	  });
         }
         
         function getdealers(){
         	$("#Dealer").empty();
         	var bid=$("#brand_id").val();
         	var region_id=$("#Region").val();
         	var dealer_id=$("#Dealer").val();
         	 $.ajax({
                  url: "<%=dashboardURL%>getDealers",
                  type: "GET", 
                    data: { 'bid': bid,'region_id':region_id,'dealer_id':dealer_id },
                  success: function(response)
                              {
                 	 $("#Dealer").prepend("<option value=''>Select Dealer</option><option value='all'>All</option>");
 					$.each(response,function(k,v){
 						$("#Dealer").append("<option value='"+v.dealer_id+"'>"+v.dealer_name+"</option>");
 					})
                              }
        	  });
         }
         
         function getoutlets(){
         	$("#Location").empty();
         	var did=$("#Dealer").val();
         	var bid=$("#brand_id").val();
         	var region_id=$("#Region").val();
         	 $.ajax({
                  url: "<%=dashboardURL%>getoutlets",
                  type: "GET", 
                    data: { 'did': did,'bid':bid,'region_id':region_id},
                  success: function(response)
                              {
                 	 $("#Location").prepend("<option value=''>Select Outlet</option><option value='all'>All</option>");
 					$.each(response,function(k,v){
 						$("#Location").append("<option value='"+v.outlet_id+"' >"+v.outlet_name+"</option>");
 					})
                              }
        	  });
         }
         
            </script>

<!-- manoj d ends -->
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
        
        function getregionset(){
     	   
     	   $('#Region').prop('selectedIndex',0);
        }
        
        </script>
        
      <script>
	 
  var bmwfinancialservicegson=${bmwfinancialservicegson};
  
  console.log("bmwfinancialservicegson=="+JSON.stringify(bmwfinancialservicegson))
  var arrMonth=[];
  var yesCount = [];
  var noCount = [];
  $.each(bmwfinancialservicegson,function(k,v){
	  arrMonth.push(v.month+","+v.year);
	  yesCount.push(v.yesCount);
   	  noCount.push(v.noCount) ;
  });
  
  //console.log(yesCount)
    //console.log(noCount)
      Highcharts.chart('container-bar-graph-lifestyle', {
	      chart: {
		        type: 'column' },
		        title: {
			        align: 'center',
			        text: '',
			         style: {
			                color: '#6c7070',
			                fontWeight: '500',
			             fontSize:'10px'
			            }
			      },           
			      credits: {
			          enabled: false
			      },
			      xAxis: {
			          tickLength: 0,
			          labels:{
			               style:{
			                    fontFamily: "'BMWGroup-Regular'",
			               }
			          },
			        categories: arrMonth },
			        yAxis: {
				         reversedStacks: false, 
				               y: 16,
				        min: 0,
				        title: {
				          text: '' },
				           labels: {
				              format: '{value}%',
				               style:{
				                    'font-family': "'BMWGroup-Regular'",
				               }
				          },
				      
				        stackLabels: {
				          enabled: false,
				          style: {
				            fontWeight: 'bold',
				            color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
    	  
				            legend: {
				    	        align: 'center',
				    	        x: 0,
				    	        verticalAlign: 'top',
				    	        y: 0,
				    	        floating: false,
				    	        backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
				    	        // borderColor: '#CCC',
				    	        // borderWidth: 1,
				    	        shadow: false,
				    	        itemStyle: {
				    	                color: '#6c7070',
				    	                fontWeight: 400,
				    	             fontFamily: "'BMWGroup-Regular'",
				    	            }
				    	      },
    	  
				    	      tooltip: {
				    		        headerFormat: '<b>{point.x}</b><br/>',
				    		        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}' },
    	  
				    		        plotOptions: {
				    			        column: {
				    			          stacking: 'percent',
				    			          dataLabels: {
				    			            enabled: true,
				    			           } }, 
				    			      series: {
				    			          borderWidth: 0,
				    			          //   pointWidth: 26,
				    			             pointPadding: 0,
				    			                groupPadding: 0.1,
				    			                borderWidth: 0,
				    			                shadow: false,
				    			                dataLabels: {
				    			                    enabled: true,
				    			                    inside:true,
				    			                     rotation: 0,
				    			                    verticalAlign:'middle',
				    			                    y:0,
				    			                  format: '{point.y}%',
				    			                    color:'#fff',
				    			                     style: {
				    			                          textOutline: 0,
				    			                          fontSize:'12px',
				    			                          fontFamily: "'BMWGroup-Regular'",
				    			            },
				    			                }
				    			        }
				    			      },
    	  
    	    series: [
    	    
    	    {
    	      name: 'Yes ',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data: yesCount.map(Number) },
    	    {
    	      name: 'No',
    	        "colorByPoint": false ,
    	          color: '#3F78CF',
    	      data: noCount.map(Number) },
    	    ] });
	
	
		</script>
		
		
		<script>
		
		var buybackprogramgson=${buybackprogramgson};
		 console.log("buybackprogramgson=="+JSON.stringify(buybackprogramgson))
		  var arrMonth=[];
		  var yesCount = [];
		  var noCount = [];
		  $.each(buybackprogramgson,function(k,v){
			  arrMonth.push(v.month+","+v.year);
			  yesCount.push(v.yesCount);
		   	  noCount.push(v.noCount) ;
			  
		  });
		  console.log(yesCount)
		    console.log(noCount)
	
      Highcharts.chart('container-bar-graph', {
    	  chart: {
		      type: 'column' },
		    
		    title: {
		      align: 'center',
		      text: '',
		       style: {
		              color: '#6c7070',
		              fontWeight: '500',
		           fontSize:'10px'
		          }
		    },        
		    credits: {
		        enabled: false
		    },
		    xAxis: {
		        tickLength: 0,
		        labels:{
		              style:{
		                    fontFamily: "'BMWGroup-Regular'",
		               }
		        },
		      categories: arrMonth },


    	  
		      yAxis: {
			       reversedStacks: false, 
			             y: 16,
			      min: 0,
			      title: {
			        text: '' },
			         labels: {
			            format: '{value}%',       
			               style:{
			                    fontFamily: "'BMWGroup-Regular'",
			               }
			        },
			    
			      stackLabels: {
			        enabled: false,
			        style: {
			          fontWeight: 'bold',
			          color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
			    
			    
			    
			    legend: {
			      align: 'center',
			      x: 0,
			      verticalAlign: 'top',
			      y: 0,
			      floating: false,
			      backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
			      // borderColor: '#CCC',
			      // borderWidth: 1,
			      shadow: false,
			      itemStyle: {
			              color: '#6c7070',
			              fontWeight: 400,
			           fontFamily: "'BMWGroup-Regular'",
			          }
			    },
			    
			    tooltip: {
			      headerFormat: '<b>{point.x}</b><br/>',
			      pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}' },
    	  
    	    plotOptions: {
    	      column: {
    	        stacking: 'percent',
    	        dataLabels: {
    	          enabled: true,
    	         } }, 
    	    series: {
    	        borderWidth: 0,
    	        //   pointWidth: 26,
    	           pointPadding: 0,
    	              groupPadding: 0.1,
    	              borderWidth: 0,
    	              shadow: false,
    	        dataLabels: {
    	            enabled: true,
    	            inside:true,
    	             rotation: 0,
    	           
    	          format: '{point.y}%',
    	            color:'#fff',
    	             style: {
    	                  textOutline: 0,
    	                  fontSize:'12px',
    	                  fontFamily: "'BMWGroup-Regular'",
    	    },
    	        }
    	      }
    	    },
    	  
    	    series: [
    	    
    	    {
    	      name: 'Yes ',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data: yesCount.map(Number) },
    	    {
    	      name: 'No',
    	        "colorByPoint": false ,
    	          color: '#3F78CF',
    	      data: noCount.map(Number) },
    	    ] });
	</script>
	<script>
	
	var offeredbreakdowngson=${offeredbreakdowngson};
	console.log(offeredbreakdowngson)
	
	 var dataArray=[];
	
	   $.each(offeredbreakdowngson,function(k,v){
        	//alert(v.percentage);
        	if(v.percentage!=0){
        	 var obj={};
        	obj["name"]=v.option_text;
        	obj["y"]=JSON.parse(v.percentage);
        	dataArray.push(obj);
        	}
        });
	
	
	// Create the chart
	var chart = Highcharts.chart('processchart', {
	  chart: {
	    type: 'pie'
	  },
	  title: {
	    text: '',
	    align: 'center',
	    verticalAlign: 'middle',
	    y: -35
	  },
	    credits: {
	      enabled: false
	  },
	  subtitle: {
	    text: null
	  },

	  plotOptions: {
	       pie: {
	             crop: false,
	            size: 200,
	           overflow: "visible",
	              showInLegend: true,
	        },
	    series: {
	      dataLabels: {
	        enabled: true,
	          overflow:true,
	          crop:false,
	        format: '{point.y:.2f}%',
	           style: {
	            color: '#70A9FE',
	           'font-family': "'BMWGroup-Regular'",
	             fontSize:'10px',
	             fontWeight:500
	         }
	      }
	    }
	  },

	  tooltip: {
		    headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
		    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
		  },
	  

	  "series": [
	    {
	      "name": "Browsers",
	      
	      "colorByPoint": true,
	        innerSize: '70%',
	        color: '#70A9FE',
	      "data": dataArray,
	    }
	  ],

	});

	</script>
	
	
	
	<script>
	 
  var handoverfinanceoffergson=${handoverfinanceoffergson};
  
  console.log("handoverfinanceoffergson=="+JSON.stringify(bmwfinancialservicegson))
  var arrMonth=[];
  var yesCount = [];
  var noCount = [];
  $.each(handoverfinanceoffergson,function(k,v){
	  arrMonth.push(v.month+","+v.year);
	  yesCount.push(v.yesCount);
   	  noCount.push(v.noCount) ;
	  
  });
      Highcharts.chart('container-bar-graph-offer', {
	      chart: {
		        type: 'column' },
		        title: {
			        align: 'center',
			        text: '',
			         style: {
			                color: '#6c7070',
			                fontWeight: '500',
			             fontSize:'10px'
			            }
			      },           
			      credits: {
			          enabled: false
			      },
			      xAxis: {
			          tickLength: 0,
			          labels:{
			               style:{
			                    fontFamily: "'BMWGroup-Regular'",
			               }
			          },
			        categories: arrMonth },
			        yAxis: {
				         reversedStacks: false, 
				               y: 16,
				        min: 0,
				        title: {
				          text: '' },
				           labels: {
				              format: '{value}%',
				               style:{
				                    'font-family': "'BMWGroup-Regular'",
				               }
				          },
				      
				        stackLabels: {
				          enabled: false,
				          style: {
				            fontWeight: 'bold',
				            color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },
    	  
				            legend: {
				    	        align: 'center',
				    	        x: 0,
				    	        verticalAlign: 'top',
				    	        y: 0,
				    	        floating: false,
				    	        backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
				    	        // borderColor: '#CCC',
				    	        // borderWidth: 1,
				    	        shadow: false,
				    	        itemStyle: {
				    	                color: '#6c7070',
				    	                fontWeight: 400,
				    	             fontFamily: "'BMWGroup-Regular'",
				    	            }
				    	      },
    	  
				    	      tooltip: {
				    		        headerFormat: '<b>{point.x}</b><br/>',
				    		        pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}' },
    	  
				    		        plotOptions: {
				    			        column: {
				    			          stacking: 'percent',
				    			          dataLabels: {
				    			            enabled: true,
				    			           } }, 
				    			      series: {
				    			          borderWidth: 0,
				    			          //   pointWidth: 26,
				    			             pointPadding: 0,
				    			                groupPadding: 0.1,
				    			                borderWidth: 0,
				    			                shadow: false,
				    			                dataLabels: {
				    			                    enabled: true,
				    			                    inside:true,
				    			                     rotation: 0,
				    			                    verticalAlign:'middle',
				    			                    y:0,
				    			                  format: '{point.y}%',
				    			                    color:'#fff',
				    			                     style: {
				    			                          textOutline: 0,
				    			                          fontSize:'12px',
				    			                          fontFamily: "'BMWGroup-Regular'",
				    			            },
				    			                }
				    			        }
				    			      },
    	  
    	    series: [
    	    
    	    {
    	      name: 'Yes ',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data: yesCount.map(Number) },
    	    {
    	      name: 'No',
    	        "colorByPoint": false ,
    	          color: '#3F78CF',
    	      data: noCount.map(Number) },
    	    ] });
	
	
		</script>
		
		<script>
    $(document).ready(function() {
       var usedNames = {};
      $("select[name='year'] > option").each(function() {
        if (usedNames[this.text]) {
          $(this).remove();
        } else {
          usedNames[this.text] = this.value;
        }
      }); 
      
    });
    </script>
		
</body>
</html>