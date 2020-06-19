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
<title>CL contact</title>
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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">       
        <link rel="stylesheet" href="<%=UI_PATH%>/assets/graphs/css/style.css">
        <link rel="stylesheet" href="<%=UI_PATH%>/assets/graphs/css/responsive.css">  
       
<script src="assets/js/modernizr.min.js"></script>
<style>
.dot {
	height: 10px;
	width: 10px;
	background-color: #3c86d8;
	border-radius: 50%;
	display: inline-block;
}

.dot1 {
	height: 10px;
	width: 10px;
	background-color: #d8c53c;
	border-radius: 50%;
	display: inline-block;
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
				<div class="main-content  position-relative nav-left">
				<!-- <button class="btn btn-primary" id="export"> Download pdf</button> -->
					<form action="conquestContactdynamic" method="GET">
						<div class="position-relative">
							<div class="pt-3 pb-3  bg-white rounded desc-header"
								style="padding-left: 0px !important; margin-left: 18px;">
								<div class="d-flex flex-wrap ">
									<div class="col">
										<h2 class="d-inline-block pt-2"><b>Conquest & Loyalty:
											Contact</b></h2>
									</div>
									<div class="col col-160 ml-auto">
										<select class="form-control" name="brand_id"
											id="brand_id" onChange="getmonths(this.value);getdealers();getoutlets();getregionset()">
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
											<!-- <option value="">Select Region</option> -->
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
											class="form-control" id="Dealer" onChange="getoutlets()" >
										<!-- <option value="">Select Dealer</option> -->
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
					

					 <div class="container">           
        <div class="row my-3">
        <div class="col-md-6 my-3">
                 <h6 class="text-center  chart-title-heading "><b>Did you instantly receive an auto response email?</b></h6>
                  
                  <div id="responsecontainer" class="container-fluid shadow" style="background-color: white;overflow: hidden;padding-left: 0px;padding-right: 0px;padding-top: 1px;"></div>
               
            </div>
            <!-- <div class="col-md-6 my-3">
                <div class="border">
                  <h6 class="text-center mt-3">Did you instantly receive an auto response email?</h6>
                  <div id="responsecontainer" style="min-width: 310px; height: 270px; margin: 0 auto"></div>
                </div>
            </div> -->
            <div class="col-md-6 my-3">
               <h6 class="text-center  chart-title-heading "><b>Did the response meet the standards?</b></h6>
                    <div id="responsecontainer2" class="container-fluid shadow" style="background-color: white;overflow: hidden;padding-left: 0px;padding-right: 0px;padding-top: 1px;"></div>
           
            </div>
            <div class="col-md-6 my-3 dealers">
             <h6 class="text-center  chart-title-heading "><b>When were you contacted personally at first by the dealership?</b></h6>
                <div id="dealer" class="container-fluid shadow  " style="background-color: white;overflow: hidden;padding-left: 0px;padding-right: 0px;padding-top: 1px;"></div>
             
            </div>
            <div class="col-md-6 my-3 dealers1">
             <h6 class="text-center  chart-title-heading "><b>When were you contacted personally at first by the dealership?</b></h6>
                <div id="dealer1" class="container-fluid shadow" style="background-color: white;overflow: hidden;padding-left: 0px;padding-right: 0px;padding-top: 1px;"></div>
             
            </div>
            
            <div class="col-md-6 my-3">
             <h6 class="text-center  chart-title-heading "><b>Did the sales representative attempt to set an appointment?</b></h6>
                <div id="appointment" class="container-fluid shadow " style="background-color: white;overflow: hidden;padding-left: 0px;padding-right: 0px;padding-top: 1px;"></div>
             
            </div>
        </div>
          </div>
				</div>

			</div>
			<!-- container -->

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



        <!-- Parsley js -->
        <script type="text/javascript" src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>
        
        <!-- jQuery  -->
         <script src="https://code.highcharts.com/highcharts.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/jquery.slimscroll.js"></script>
		
		<!-- App js -->
	<script
		src="<%=UI_PATH%>/assets/js/jquery.core.js"></script>
	<script
		src="<%=UI_PATH%>/assets/js/jquery.app.js"></script>

    <%-- <script src="<%=UI_PATH%>/assets/js/response-email-chart.js"></script>
    <script src="<%=UI_PATH%>/assets/js/response-standards-chart.js"></script>   
    <script src="<%=UI_PATH%>/assets/js/dealership-chart.js"></script>
    <script src="<%=UI_PATH%>/assets/js/appointment-chart.js"></script>
 --%>
     <!-- graph js -->
         <script>
        
        function getregionset(){
     	   
     	   $('#Region').prop('selectedIndex',0);
        }
        
        </script>

        
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
                	 console.log(JSON.stringify(response));
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
          var autoresponseemailgraphjson= ${autoresponseemailgraphjson};
         console.log(autoresponseemailgraphjson)
          var arrMonth1=[];
          var yesCount = [];
          var noCount = [];
          $.each(autoresponseemailgraphjson,function(k,v){
        	  arrMonth1.push(v.month+","+v.year);
        	  yesCount.push(v.yesCount);
           	  noCount.push(v.noCount) ;
        	  
          });
          console.log(arrMonth1)
            console.log(noCount)

          Highcharts.chart('responsecontainer', {
        	  chart: {
        	    type: 'column',
        	    height: 270,
                width: 514,
                style: {
                  height:'292px',
              }
        	  },
        	     legend: {
        	          enabled:true,
        	        align: 'center',
        	        verticalAlign: 'top',
        	        x: 0,
        	        y: -25,
        	        itemDistance: 7,
        	         itemStyle: {
        	            color: '#003366',
        	             fontSize:'13px',
        	            fontWeight: 400,
        	         fontFamily: "'BMWGroup-Regular'",
        	        }
        	          
        	    },
        	     credits: {
        	      enabled: false
        	  },
        	  title: {
        	    text: null
        	  },
        	  xAxis: {
        	    categories: arrMonth1,
        	       tickLength: 0,
        	      lineColor: 'transparent',
        	      labels: {
        	         
        	         style: {
        	            color: '#003366',
        	            'font-family': "'BMWGroup-Regular'",
        	             'font-size':'13px',
        	             'font-weight':400,
        	         }
        	      },
        	  },
        	  yAxis: {
        	      reversedStacks: false,
        	    min: 0,
        	       gridLineColor: 'transparent',
        	        labels: {
        	            enabled: false,
        	        }, 
        	    title: {
        	      text: null
        	    }
        	  },
        	   
        	    
        	  tooltip: {
        	    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
        	    shared: true
        	  },
        	  plotOptions: {
        	    column: {
        	      stacking: 'percent'
        	    },
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
        	 series: [{
        		    name: 'Yes',
        		      "colorByPoint": false ,
        		        color: '#70A9FE',
        		    data: yesCount.map(Number)
        		  }, 
        		 /*  {
        			    name: 'No',
        			      "colorByPoint": false ,
        			        color: '#3F78CF',
        			    data: noCount.map(Number)
        			  }, */]
        	});

        </script>
     

        <script>
        var dealershipgraphresponse= ${dealershipgraphresponse};
        console.log(dealershipgraphresponse)
         var arrMonth=[];
         var After4hoursonthesameworkingday = [];
         var Within2hoursaftersendingtherequest = [];
         var Within4hoursaftersendingtherequest = [];
         var Notatall = [];
         var Withinnextworkingdayaftersendingtherequest=[];
         var Nopersonalresponseuntilendofnextworkingday=[];
         var year='';
         $.each(dealershipgraphresponse,function(k,v){
       	  arrMonth.push(v.month+","+v.year);
       	  
       	After4hoursonthesameworkingday.push(v.After4hoursonthesameworkingdayp);
       	Within2hoursaftersendingtherequest.push(v.Within2hoursaftersendingtherequestp) ;
       	Within4hoursaftersendingtherequest.push(v.Within4hoursaftersendingtherequestp) ;
       	Notatall.push(v.Notatallp) ;
       	Withinnextworkingdayaftersendingtherequest.push(v.Withinnextworkingdayaftersendingtherequestp);
       	Nopersonalresponseuntilendofnextworkingday.push(v.Nopersonalresponseuntilendofnextworkingdayp);
        year=v.year;
         });
         if(year==2019)
         {
        	 $('.dealers1').hide();
         console.log(After4hoursonthesameworkingday)
        Highcharts.chart('dealer', {
        	  chart: {
        	    type: 'column',
        	    	 height: 270,
                     width: 514,
                     style: {
                       height:'292px',
                   }
        	  },
        	     legend: {
        	          enabled:true,
        	        align: 'center',
        	        verticalAlign: 'top',
        	        x: 0,
        	        y: -25,
        	        itemDistance: 7,
        	          itemStyle: {
        	            color: '#003366',
        	             fontSize:'13px',
        	            fontWeight: 600,
        	         fontFamily: "'BMWGroup-Regular'",
        	        }
        	          
        	    },
        	     credits: {
        	      enabled: false
        	  },
        	  title: {
        	    text: null
        	  },
        	  xAxis: {
        	    categories: arrMonth,
        	       tickLength: 0,
        	      lineColor: 'transparent',
        	      labels: {
        	         
        	         style: {
        	            color: '#003366',
        	            'font-family': "'BMWGroup-Regular'",
        	             'font-size':'13px',
        	             'font-weight':400,
        	         }
        	      },
        	  },
        	  yAxis: {
        	      reversedStacks: false,
        	    min: 0,
        	       gridLineColor: 'transparent',
        	        labels: {
        	            enabled: false,
        	        }, 
        	    title: {
        	      text: null
        	    }
        	  },
        	   
        	    
        	  tooltip: {
        	    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
        	    shared: true
        	  },
        	  plotOptions: {
        	    column: {
        	      stacking: 'percent'
        	    },
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
        	  series: [{   
        		    name: 'After 4 hours on the same working day',
        		      "colorByPoint": false ,
        		        color: '#3F78CF',
        		    data:After4hoursonthesameworkingday.map(Number)
        		  },
        		           {
        		    name: 'Within 2 hours after sending the request',
        		      "colorByPoint": false ,
        		        color: '#5094FC',
        		    data: Within2hoursaftersendingtherequest.map(Number)
        		  },
        		          {
        		    name: 'Within 4 hours after sending the request',
        		      "colorByPoint": false ,
        		        color: '#83BCFF',
        		    data: Within4hoursaftersendingtherequest.map(Number)
        		  },          
        		  {
        			    name: 'Not at all',
        			      "colorByPoint": false ,
        			        color: '#B0D5FF',
        			    data: Notatall.map(Number)
        			  },
        	             
        	 ]
        	});
         }
         
         else
         
         {
        	 $('.dealers').hide();
        	  Highcharts.chart('dealer1', {
            	  chart: {
            	    type: 'column',
            	    	 height: 270,
                         width: 514,
                         style: {
                           height:'292px',
                       }
            	  },
            	     legend: {
            	          enabled:true,
            	        align: 'center',
            	        verticalAlign: 'top',
            	        x: 0,
            	        y: -25,
            	        itemDistance: 7,
            	          itemStyle: {
            	            color: '#003366',
            	             fontSize:'13px',
            	            fontWeight: 600,
            	         fontFamily: "'BMWGroup-Regular'",
            	        }
            	          
            	    },
            	     credits: {
            	      enabled: false
            	  },
            	  title: {
            	    text: null
            	  },
            	  xAxis: {
            	    categories: arrMonth,
            	       tickLength: 0,
            	      lineColor: 'transparent',
            	      labels: {
            	         
            	         style: {
            	            color: '#003366',
            	            'font-family': "'BMWGroup-Regular'",
            	             'font-size':'13px',
            	             'font-weight':400,
            	         }
            	      },
            	  },
            	  yAxis: {
            	      reversedStacks: false,
            	    min: 0,
            	       gridLineColor: 'transparent',
            	        labels: {
            	            enabled: false,
            	        }, 
            	    title: {
            	      text: null
            	    }
            	  },
            	   
            	    
            	  tooltip: {
            	    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
            	    shared: true
            	  },
            	  plotOptions: {
            	    column: {
            	      stacking: 'percent'
            	    },
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
            	  series: [{   
            		    name: 'After 4 hours on the same working day',
            		      "colorByPoint": false ,
            		        color: '#3F78CF',
            		    data:After4hoursonthesameworkingday.map(Number)
            		  },
            		           {
            		    name: 'Within next working day after sending the request',
            		      "colorByPoint": false ,
            		        color: '#5094FC',
            		    data: Withinnextworkingdayaftersendingtherequest.map(Number)
            		  },
            		          {
            		    name: 'Within 4 hours after sending the request',
            		      "colorByPoint": false ,
            		        color: '#83BCFF',
            		    data: Within4hoursaftersendingtherequest.map(Number)
            		  },          
            		  {
            			    name: 'No personal response until end of next working day',
            			      "colorByPoint": false ,
            			        color: '#B0D5FF',
            			    data: Nopersonalresponseuntilendofnextworkingday.map(Number)
            			  },
            	             
            	 ]
            	}); 
        	 
        	 
        	 
         }
        </script>
        
        <script>
        var appointmentchartjson= ${appointmentchartjson};
        console.log(appointmentchartjson)
         var arrMonth=[];
         var yesCount = [];
         var noCount = [];
         $.each(appointmentchartjson,function(k,v){
       	  arrMonth.push(v.month+","+v.year);
       	  yesCount.push(v.yesCount);
          	  noCount.push(v.noCount) ;
          	
         });
         console.log(yesCount)
           console.log(noCount)
        Highcharts.chart('appointment', {
        	  chart: {
        	    type: 'column',
        	    height: 270,
                width: 514,
                style: {
                  height:'292px',
              }
        	  },
        	     legend: {
        	          enabled:true,
        	        align: 'center',
        	        verticalAlign: 'top',
        	        x: 0,
        	        y: -25,
        	        itemDistance: 7,
        	         itemStyle: {
        	            color: '#003366',
        	             fontSize:'13px',
        	            fontWeight: 600,
        	         fontFamily: "'BMWGroup-Regular'",
        	        }
        	          
        	    },
        	     credits: {
        	      enabled: false
        	  },
        	  title: {
        	    text: null
        	  },
        	  xAxis: {
        	    categories: arrMonth,
        	       tickLength: 0,
        	      lineColor: 'transparent',
        	      labels: {
        	         
        	         style: {
        	            color: '#003366',
        	            'font-family': "'BMWGroup-Regular'",
        	             'font-size':'13px',
        	             'font-weight':400,
        	         }
        	      },
        	  },
        	  yAxis: {
        	      reversedStacks: false,
        	    min: 0,
        	       gridLineColor: 'transparent',
        	        labels: {
        	            enabled: false,
        	        }, 
        	    title: {
        	      text: null
        	    }
        	  },
        	   
        	    
        	  tooltip: {
        	    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
        	    shared: true
        	  },
        	  plotOptions: {
        	    column: {
        	      stacking: 'percent'
        	    },
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
        	  series: [{
        	    name: 'Yes',
        	      "colorByPoint": false ,
        	        color: '#70A9FE',
        	    data: yesCount.map(Number)
        	  },
        	           
        	  {
        	    name: 'No',
        	      "colorByPoint": false ,
        	        color: '#3F78CF',
        	    data: noCount.map(Number)
        	  },
        	          ]
        	});
        
        </script>
        
       <script>
       var meetthestandardsjson= ${meetthestandardsjson};
       console.log(meetthestandardsjson)
        var arrMonth=[];
        var yesCount = [];
        var noCount = [];
        $.each(meetthestandardsjson,function(k,v){
      	  arrMonth.push(v.month+","+v.year);
      	  yesCount.push(v.yesCount);
         	  noCount.push(v.noCount) ;
         	
        });
        console.log("alshdsdjh=="+yesCount)
          console.log(noCount)
       
       Highcharts.chart('responsecontainer2', {
    	   chart: {
    	     type: 'column',
    	     height: 270,
             width: 514,
             style: {
               height:'292px',
           }
    	   },
    	      legend: {      
    	         enabled:true,
    	         align: 'center',
    	         verticalAlign: 'top',
    	         x: 0,
    	         y: -25,
    	         itemDistance: 7,
    	           itemStyle: {
    	             color: '#003366',
    	              fontSize:'13px',
    	             fontWeight: 400,
    	          fontFamily: "'BMWGroup-Regular'",
    	         }
    	           
    	     },
    	      credits: {
    	       enabled: false
    	   },
    	   title: {
    	     text: null
    	   },
    	   xAxis: {
    	    
    	     categories: arrMonth,
    	        tickLength: 0,
    	       lineColor: 'transparent',
    	       labels: {
    	          
    	          style: {
    	             color: '#003366',
    	             'font-family': "'BMWGroup-Regular'",
    	              'font-size':'13px',
    	              'font-weight':400,
    	          }
    	       },
    	   },
    	   yAxis: {
    	          reversedStacks: false,
    	     min: 0,
    	        gridLineColor: 'transparent',
    	         labels: {
    	             enabled: false,
    	         }, 
    	     title: {
    	       text: false
    	     }
    	   },
    	    
    	     
    	   tooltip: {
    	     pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
    	     shared: true
    	   },
    	   plotOptions: {
    	     column: {
    	       stacking: 'percent'
    	     },
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
    	   series: [{
    	     name: 'Yes',   
    	       "colorByPoint": false ,
    	         color: '#70A9FE',
    	     data: yesCount.map(Number)
    	   }, /* {
      	     name: 'no',   
  	       "colorByPoint": false ,
  	         color: '#3F78CF',
  	     data: noCount.map(Number)
  	   }, */ ]
    	 });
       
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