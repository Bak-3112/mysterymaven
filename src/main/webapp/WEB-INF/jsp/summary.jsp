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
	href="<%=UI_PATH%>/design/images/DQI-icon.png">

        <!-- App css -->
        <link href="<%=UI_PATH%>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/metismenu.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=UI_PATH%>/assets/css/style.css" rel="stylesheet" type="text/css" />

        <script src="<%=UI_PATH%>/assets/js/modernizr.min.js"></script>
        
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
		.report-h4{font-size: 18px;color: #000;}
		.dataTables_filter{
		    float: right;
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

                  <!--       <div class="row">
                    <div class="col-sm-12">
                        <div class="page-title-box">
                            <div class="btn-group pull-right">
                                <ol class="breadcrumb hide-phone p-0 m-0">
                                    <li class="breadcrumb-item"><a href="#">Mystery Shopping</a></li>
                                    <li class="breadcrumb-item"><a href="#">Reports</a></li>
                                </ol>
                            </div>
                            <h4 class="page-title">Reports</h4>
                        </div>
                    </div>
                </div> -->

                <div class="row">
                
                <div class="col-lg-12">
                            <form method="POST" action="<%=dashboardURL%>reportspost" enctype="multipart/form-data" autocomplete="off">

                        <div class="card-box">	
                              <div class="d-flex flex-wrap ">
									<div class="col">
										<h4 class="d-inline-block pt-2 report-h4"><b>Reports</b></h4>
									</div>
									<div class="col col-sm-2 ml-auto">
										<select class="form-control " name="brand_id" id="brand_id">
									
											<c:forEach var="qBean" items="${activebrandList}" >
										
												<option value="${qBean.sk_brand_id}" <c:if test = "${brand_id == qBean.sk_brand_id}"> selected</c:if>>${qBean.brand_name}</option>
												
											</c:forEach>
											<!-- <option>BMW</option>
											<option>MINI</option> -->
										</select>
									</div>
								</div>
                            <h4 class="header-title m-t-0">  </h4>
                             <div class="card-box ">
                               <div class="row">
                      
                            	<div class="col-md-4">
                                    <label for="Address" style="font-weight:600; color:#797979;">Report Name<span class="text-danger">*</span></label>
                                    <select  required class="form-control" id="report_type" name="report_type">
					                      <option value="LowScoringOutlets" <c:if test = "${report_type == 'LowScoringOutlets' }">selected </c:if>>Low Scoring outlets </option>
					                      <option value="SummarySheet" <c:if test = "${report_type == 'SummarySheet' }">selected </c:if>>Summary Sheet</option>
					                        <option value="ManagementLevel" <c:if test = "${report_type == 'ManagementLevel' }">selected </c:if>>Management Level</option>
					                         <option value="MSMResult" <c:if test = "${report_type == 'MSMResult' }">selected </c:if>>MSM Result</option>  
					                              <option value="output_level_report" <c:if test = "${report_type == 'output_level_report' }">selected </c:if>>Outlet Level Report</option>  
					                      </select>
                                </div>
                                <div class="col-md-4 years">
                                	      <label for="Address" style="font-weight:600; color:#797979;">Year<span class="text-danger">*</span></label>
								<select required class="form-control" name="year" id="year">
								<c:forEach var="qBean" items="${activeyearsList}" >
									<option value="${qBean.year}" <c:if test = "${year == qBean.year}"> selected</c:if>>${qBean.year}</option>
								</c:forEach>
										</select> 	
								</div>
								
								<div class="col-sm-4 months">
                                	      <label for="Address" style="font-weight:600; color:#797979;">Month<span class="text-danger">*</span></label>
											<select required class="form-control" name="month" id="month">
											<option value="all"  <c:if test = "${month == 'all'}"> selected</c:if>>All</option>
											<option value="01"  <c:if test = "${month == '01'}"> selected</c:if>>January</option>
											<option value="02"  <c:if test = "${month == '02'}"> selected</c:if>>February</option>
											<option value="03"  <c:if test = "${month == '03'}"> selected</c:if>>March</option>
											<option value="04"  <c:if test = "${month == '04'}"> selected</c:if>>April</option>
											<option value="05"  <c:if test = "${month == '05'}"> selected</c:if>>May</option>
											<option value="06"  <c:if test = "${month == '06'}"> selected</c:if>>June</option>
											<option value="07"  <c:if test = "${month == '07'}"> selected</c:if>>July</option>
											<option value="08"  <c:if test = "${month =='08'}"> selected</c:if>>August</option>
											<option value="09"  <c:if test = "${month == '09'}"> selected</c:if>>September</option>
											<option value="10"  <c:if test = "${month == '10'}"> selected</c:if>>October</option>
											<option value="11"  <c:if test = "${month == '11'}"> selected</c:if>>November</option>
											<option value="12"  <c:if test = "${month == '12'}"> selected</c:if>>December</option>
									<%-- 	<c:forEach var="qBean" items="${getMonths}">
											<option value="${qBean.month}">${qBean.monthName}</option>
										</c:forEach> --%>

									</select>
								</div>
								
                                 </div>
                                      <label></label>
                                  
								  <div class="row">
								<div class="col-md-4 regions">
								      <label for="Address" style="font-weight:600; color:#797979;">Region<span class="text-danger">*</span></label>
									<select required name="region_id" class="form-control" id="region_id" onchange="getDealers(this.value);">
										<option value="all"  <c:if test = "${region_id == 'all'}"> selected</c:if>>All</option>
										<c:forEach var="rBean" items="${activeregionsList}">
											<option value="${rBean.sk_region_id}" <c:if test = "${region_id == rBean.sk_region_id }">selected </c:if>>${rBean.region_name}</option>
										</c:forEach>
									</select>
								</div>
								 <label></label>
								
								<div class="col-md-4 dealers">
								      <label for="Address" style="font-weight:600; color:#797979;">Dealer<span class="text-danger">*</span></label>
									<select required name="dealer_id" class="form-control" id="sk_dealer_id" onchange="getOutlets(this.value);">
									<option value="all"  <c:if test = "${dealer_id == 'all'}"> selected</c:if>>All</option>
									<c:forEach var="rBean" items="${dealerListByRegionId}">
											<option value="${rBean.sk_dealer_id}" <c:if test = "${dealer_id == rBean.sk_dealer_id }">selected </c:if>>${rBean.dealer_name}</option>
										</c:forEach>
									</select>
								
								</div> <label></label>
								  
								<div class="col-md-4 outlets">
								    <label for="Address" style="font-weight:600; color:#797979;">Location<span class="text-danger">*</span></label>
									<select required name="outlet_id" class="form-control" id="sk_outlet_id">
									<option value="all"  <c:if test = "${outlet_id == 'all'}"> selected</c:if>>All</option>
									<c:forEach var="rBean" items="${outletList}">
											<option value="${rBean.outlet_id}" <c:if test = "${outlet_id == rBean.outlet_id }">selected </c:if>>${rBean.outlet_name}</option>
										</c:forEach>
									</select>
								</div>
								
                               
                                <label></label>
                               </div>
                               <div>&nbsp;&nbsp;</div>
                                 	  <div class="row">
                                <div class="col-md-6 mgmtclass">
                                    <button class="btn btn-gradient waves-effect waves-light" >
                                        View
                                    </button>
                                  
                                </div>
                                <div class="col-md-6 mgmtclass1">
                                    <a class="btn btn-gradient waves-effect waves-light"  >
                                        View
                                    </a>
                                  
                                </div>
							</div>
							</div>
                            
                        </div> <!-- end card-box -->
                        </form>
                    </div>
                    
                 
                </div>
                <div class="row" >
                               <div class="col-md-12">
                        <div class="card-box table-responsive">
                            <h4 class="m-t-0 header-title"><b> </b></h4>
                           <a href="<%=dashboardURL%>summarydownload"  style="margin-bottom: 13px;" target="_blank" class="btn btn-secondary buttons-excel buttons-html5 download">Excel</a>
                           

                            <table id="datatable-buttons1" class="table table-striped table-bordered" cellspacing="0" width="100%">
                                <thead>
                                <tr>
                                  <!--  <th>SL No</th> -->
                                    <th>Brand</th>
                                    <th>Region</th>
                                    <th>Regional Manager</th>
                                    <th>Month</th>
                                    <th>Dealer City</th>
                                    <th>Dealer Group Name</th>
                                    <th>Outlet</th>
                                    <th>%YTD Dealer Average </th>
                                     <th>YTD Dealer Rank</th>
                                    <th>%YTD Outlet Average </th> 
                                    <th>Monthly Dealer Rank</th>
                                    <th>%Monthly Dealer Average </th>
                                    <th>%Outlet Scores </th> 
                                   <c:forEach var="mvBean1" items="${summaryheader}" varStatus="loop"> 
                                      <th>% ${mvBean1.section_name } (Overall) </th>
                                    <c:forTokens var="status" items="${mvBean1.subsection_name }" delims="#">
                                       <th>% ${status} </th>
                                      </c:forTokens>
                                      </c:forEach> 
                                
                                 <th>CRM Compliance </th>
                                    <th>Data Accuracy</th>
                                    <th>Sales Consultant Name</th>  
                                 <th>Visit Date</th>
                                    <th>Mystery Shopper</th>
                                    <th>Email ID</th>
                                    <th>Mobile Number</th>
                                    <th>Metro/Non Metro</th>
                                    <th>Tier</th>
                                    <th>Missed Opportunities</th>
                                    <th>Point Reached</th>
                                    <th>Maximum Points</th> 
                                   <c:forEach var="mvBean1" items="${summaryheader1}" varStatus="loop"> 
                                    <c:forTokens var="status" items="${mvBean1.subsection_name }" delims="#">
                                       <th>Maximum ${status} </th>
                                      <th>Obtained ${status} </th>
                                      </c:forTokens>
                                      </c:forEach> 
                                   
                                </tr>
                                </thead>
                                <tbody>
                                 <c:forEach var="mvBean" items="${summaryScore}" varStatus="loop"> 
                               <tr>
                                 <%--  <td>${loop.index+1}</td> --%>
                                  <td>${mvBean.brand_name}</td>
                                     <td>${mvBean.region_name}</td>
                                    <td>${mvBean.regional_manager}</td>
                                    <td>${mvBean.monthname}</td>
                                    <td>${mvBean.outlet_city_name}</td>
                                   <td>${mvBean.dealer_name}</td>
                                    <td>${mvBean.outlet_name}</td>
                                    <td>${mvBean.ytd_dealer_avg1} %</td>
                                        <td>${mvBean.ytd_dealer_rank} </td>
                                    <td> ${mvBean.ytd_outlet_avg} %</td> 
                                     <td>${mvBean.rank}</td>
                                   <td> ${mvBean.monthly_dealer_avg} %</td>
                                     <td> ${mvBean.outlet_score} %</td> 
                                       <c:forEach var="mvBean1" items="${mvBean.sectionscore}" varStatus="loops"> 
                                     <td> ${mvBean1.sectionScores} %</td>
                                     <c:forEach var="mvBean2" items="${mvBean1.subsectionscore}" varStatus="loops"> 
                                     <td> ${mvBean2.subsectionScores} % </td>
                                     </c:forEach>
                                     </c:forEach>
                              
                                  <td>${mvBean.crm_compliance}</td>
                                	 <td>${mvBean.data_accuracy}</td>
                                   <td>${mvBean.sc_name}</td>
                                	 <td>${mvBean.visit_date}</td>
                                    <td>${mvBean.shopper_link_name}</td>
                                    <td>${mvBean.shopper_link_email}</td>
                                    <td>${mvBean.shopper_link_contact}</td>
                                    <td>${mvBean.metroandnonmetro}</td>
                                   <td>${mvBean.tier}</td>
                                     <td>
                                   <c:forEach var="question" items="${mvBean.missedOpportunitesList}" varStatus="count">
                                     ${count.count } .${question.question_text} <br>
            						</c:forEach>
                                     </td>  
                                    <td>${mvBean.point_reached}</td>
                                    <td>${mvBean.maximum_points}</td> 
                                     <c:forEach var="mvBean1" items="${mvBean.nonOscSubSectiobPoint}" varStatus="loops"> 
                                     <c:forEach var="mvBean2" items="${mvBean1.subsectionscore}" varStatus="loops"> 
                                     <td> ${mvBean2.maximum_points}  </td>
                                      <td> ${mvBean2.obtained_points}  </td>
                                     </c:forEach>
                                     </c:forEach>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            
                          <%--   <div class="btnwrap center">
   <a class="btn btn-grey mrg-top Previous" data-id="" >Previous</a> <input type="button"  class="btn btn-info mrg-top Next" value="Next" id="nextid" data-id=""/>
        <input type="hidden" id="pageCountVal"/>
          <input type="hidden" value="${limit }" name="count" id="pagecount"/>
            <input type="hidden" value=" " name="count" id="count"/>
    </div> --%>
     <ul id="pagination-demo" class="pagination-lg pull-right"></ul>
                        </div>
                    </div>
                    </div>
                <!-- end row -->
				</div>
				</div>
            </div> <!-- end container -->
        </div>
 

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
          
        <!-- Init Js file -->
        <script type="text/javascript" src="<%=UI_PATH%>/assets/pages/jquery.form-advanced.init.js"></script>
        <!-- Parsley js -->
        <script type="text/javascript" src="<%=UI_PATH%>/plugins/parsleyjs/parsley.min.js"></script>




<script>
window.onload = getIds;     

var counts;
var report_type=$('#report_type').val();
var year=$('#year').val();
var region_id=$('#region_id').val();
var month=$('#month').val();
var sk_dealer_id=$('#sk_dealer_id').val();
var sk_outlet_id=$('#sk_outlet_id').val();
var brand_id=$('#brand_id').val();
 function getIds(){
  
    var mode=$('#mode').val();
    $.ajax({
          url: "<%=dashboardURL%>getsummarycountIds",
          type: "GET", 
            data: {"report_type":report_type,'year':year,'region_id':region_id,'month':month,'sk_dealer_id':sk_dealer_id,'sk_outlet_id':sk_outlet_id,'brand_id':brand_id},
          success: function(response)
                      {
        	 
        	  counts=response.count;
        	  console.log(counts)
        	$("#count").val(counts);
        	
        	
              var as= response.count / 2;
              var myNumber = as;
              var integerPart = parseInt(myNumber);
              var decimalPart = (myNumber - integerPart).toFixed(2);
              var finall="";
              if(decimalPart[2].toString()=="0")
                {
                finall=integerPart;
               
                }
              else
                {
                finall=integerPart + 1;
               
                }
            
             document.getElementById("pageCountVal").value=finall;
             
             
             

             if($("#pagecount").val()==$("#count").val()-1){
            	 $('#nextid').attr("disabled", true);
             }else if(counts==0){
        		 $('#nextid').attr("disabled", true);
        	}else{
        		 $('#nextid').attr("disabled", false);
        	}
             
             for(var i=1;i!=finall+1;i++)
                {
               
                //$("#appendRow").append("<a href='/questionare/"+sid+"/"+i+"'>"+i+"</a>&nbsp;");
                
                }  
            
             
       
              
                  }
          });
} 

 var i = $("#pagecount").val();
 
$('.Next').on('click', function(){
	
	 i++;

	window.location.href ="/summary/"+report_type+"/"+year+"/"+month+"/"+region_id+"/"+sk_dealer_id+"/"+sk_outlet_id+"/"+brand_id+"/"+i;
	
})

 
 
 
if($("#pagecount").val()<=0){
        		
        		$(".Previous").hide();

}else{
	$(".Previous").removeAttr("display","none");
	 $('.Previous').on('click', function(){
	     i--;
	 	window.location.href ="/summary/"+report_type+"/"+year+"/"+month+"/"+region_id+"/"+sk_dealer_id+"/"+sk_outlet_id+"/"+brand_id+"/"+i;

		 
	 })
}
</script>




<script type="text/javascript">
$(document).ready(function() {
    //$('#Region').val($('#regionidvalue').val());
    //$('#month').val($('#monthvalue').val());
    
    $('.mgmtclass1').hide();
} ); 
$('#datatable-buttons').DataTable( {
    //"stripeClasses": ['odd-row', 'even-row'],
    dom: 'Bfrtip',
    "paging": false,
    buttons: {
      buttons: [{
        extend: 'excelHtml5',
        title: 'summary_sheet',
        customize: function (xlsx) {
  var new_style = '<?xml version="1.0" encoding="UTF-8"?><styleSheet xmlns="http://schemas.openxmlformats.org/spreadsheetml/2006/main" xmlns:mc="http://schemas.openxmlformats.org/markup-compatibility/2006" mc:Ignorable="x14ac" xmlns:x14ac="https://schemas.microsoft.com/office/spreadsheetml/2009/9/ac"><numFmts count="2"><numFmt numFmtId="171" formatCode="d/mm/yyyy;@"/><numFmt numFmtId="172" formatCode="m/d/yyyy;@"/></numFmts><fonts count="10" x14ac:knownFonts="1"><font><sz val="11"/><color theme="1"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><sz val="11"/><color theme="1"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><b/><sz val="11"/><color theme="1"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><sz val="11"/><color theme="0"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><i/><sz val="11"/><color theme="1"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><sz val="11"/><color rgb="FFC00000"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><sz val="11"/><color rgb="FF006600"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><sz val="11"/><color rgb="FF990033"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><sz val="11"/><color rgb="FF663300"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font><font><b/><sz val="11"/><color rgb="FFC00000"/><name val="Calibri"/><family val="2"/><scheme val="minor"/></font></fonts><fills count="16"><fill><patternFill patternType="none"/></fill><fill><patternFill patternType="gray125"/></fill><fill><patternFill patternType="solid"><fgColor rgb="FFC00000"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FFFF0000"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FFFFC000"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FFFFFF00"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF92D050"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF00B050"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF00B0F0"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF0070C0"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF002060"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF7030A0"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor theme="1"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FF99CC00"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FFFF9999"/><bgColor indexed="64"/></patternFill></fill><fill><patternFill patternType="solid"><fgColor rgb="FFFFCC00"/><bgColor indexed="64"/></patternFill></fill></fills><borders count="2"><border><left/><right/><top/><bottom/><diagonal/></border><border><left style="thin"><color indexed="64"/></left><right style="thin"><color indexed="64"/></right><top style="thin"><color indexed="64"/></top><bottom style="thin"><color indexed="64"/></bottom><diagonal/></border></borders><cellStyleXfs count="2"><xf numFmtId="0" fontId="0" fillId="0" borderId="0"/><xf numFmtId="9" fontId="1" fillId="0" borderId="0" applyFont="0" applyFill="0" applyBorder="0" applyAlignment="0" applyProtection="0"/></cellStyleXfs><cellXfs count="70"><xf numFmtId="0" fontId="0" fillId="0" borderId="0" xfId="0"/><xf numFmtId="0" fontId="0" fillId="0" borderId="0" xfId="0" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="2" fillId="0" borderId="0" xfId="0" applyFont="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="0" xfId="0" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="0" xfId="0" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="4" fillId="0" borderId="0" xfId="0" applyFont="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="4" fillId="0" borderId="0" xfId="0" applyFont="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="4" fillId="0" borderId="0" xfId="0" applyFont="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyBorder="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyBorder="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="0" xfId="0" applyAlignment="1"><alignment vertical="top" wrapText="1"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyBorder="1" applyAlignment="1"><alignment vertical="top" wrapText="1"/></xf><xf numFmtId="0" fontId="3" fillId="2" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="3" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="4" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="5" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="6" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="7" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="8" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="9" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="10" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="11" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="12" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="2" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="3" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="4" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="5" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="6" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="7" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="8" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="9" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="10" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="11" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="12" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="2" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="3" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="4" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="5" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="6" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="7" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="8" borderId="0" xfId="0" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="9" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="10" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="11" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="3" fillId="12" borderId="0" xfId="0" applyFont="1" applyFill="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top" textRotation="90"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" textRotation="255"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyBorder="1" applyAlignment="1"><alignment textRotation="45"/></xf><xf numFmtId="0" fontId="5" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="5" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="5" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="5" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyBorder="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="5" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="0" fontId="5" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyBorder="1" applyAlignment="1"><alignment horizontal="right" vertical="top"/></xf><xf numFmtId="0" fontId="6" fillId="13" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="6" fillId="13" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyFill="1" applyBorder="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="7" fillId="14" borderId="0" xfId="1" applyNumberFormat="1" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="7" fillId="14" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyFill="1" applyBorder="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="8" fillId="15" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyFill="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="8" fillId="15" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyFill="1" applyBorder="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="0" fontId="0" fillId="0" borderId="0" xfId="0" applyBorder="1" applyAlignment="1"><alignment vertical="top"/></xf><xf numFmtId="171" fontId="0" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="172" fontId="0" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="171" fontId="0" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="172" fontId="0" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="171" fontId="9" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="172" fontId="9" fillId="0" borderId="1" xfId="0" applyNumberFormat="1" applyFont="1" applyBorder="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="171" fontId="9" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf><xf numFmtId="172" fontId="9" fillId="0" borderId="0" xfId="0" applyNumberFormat="1" applyFont="1" applyAlignment="1"><alignment horizontal="center" vertical="top"/></xf></cellXfs><cellStyles count="2"><cellStyle name="Procent" xfId="1" builtinId="5"/><cellStyle name="Standaard" xfId="0" builtinId="0"/></cellStyles><dxfs count="0"/><tableStyles count="0" defaultTableStyle="TableStyleMedium2" defaultPivotStyle="PivotStyleLight16"/><colors><mruColors><color rgb="FF663300"/><color rgb="FFFFCC00"/><color rgb="FF990033"/><color rgb="FF006600"/><color rgb="FFFF9999"/><color rgb="FF99CC00"/></mruColors></colors><extLst><ext uri="{EB79DEF2-80B8-43e5-95BD-54CBDDF9020C}" xmlns:x14="https://schemas.microsoft.com/office/spreadsheetml/2009/9/main"><x14:slicerStyles defaultSlicerStyle="SlicerStyleLight1"/></ext></extLst></styleSheet>';
  xlsx.xl['styles.xml'] = $.parseXML(new_style);
  var sheet = xlsx.xl.worksheets['sheet1.xml'];

/*   $('row:even c', sheet).attr( 's','42' ); 
  $('row:odd c', sheet).attr( 's', '41' );   */
  $('row:first c', sheet).attr('s', '43');
  
/*    $('row*', sheet).each(function(index) {
      if (index == 0) {
          $(this).attr('ht', 50);
          $(this).attr('customHeight', 1);
        
      }
      
     }); */
  /*   
  $('row c[r^="I"], row c[r^="J"], row c[r^="L"], row c[r^="M"]', sheet).each( function () {
           if ( $(this).text()<= 90) {
               $(this).attr( 's', '57' );                                 
           }
          else{
             $(this).attr( 's', '15' );  
           } 
       }); */
  
  
    
    
    
  }
}]
}
});

</script>

	
           
           <script>
        function getDealers(regionId) 
        { 
         
           $("#sk_dealer_id option").remove();  
          var brand=$("#brand_id").val();
          //alert(brand);
          
            $.ajax({
                  url: "<%=dashboardURL%>getDealersbySubregion",
                  type: "GET", 
                    data: { 'regionId': regionId,
                    	'brand': brand  },
                  success: function(response)
                              {
                	  $("#sk_dealer_id").append("<option value=''>Select Dealer</option>");
                	  $("#sk_dealer_id").append("<option value='all'>All</option>");
                	  $.each(response,function(k,v){
                          $("#sk_dealer_id").append("<option value='"+v.sk_dealer_id+"'>"+v.dealer_name+"</option>");

                	  });
                                    
                              }
                  });  
          
        }
        </script>
         <script>
        function getOutlets(sk_dealer_id) 
        { 
         
           $("#sk_outlet_id option").remove();  
           var brand_id= $("#brand_id").val();
           var sk_dealer_id=$("#sk_dealer_id").val();
           $.ajax({
                  url: "<%=dashboardURL%>getOutletsbyDealers",
                  type: "GET", 
                    data: { 'sk_dealer_id': sk_dealer_id,'brand_id':brand_id},
                  success: function(response)
                              {
                	  $("#sk_outlet_id").append("<option value=''>Select Outlet</option>");
                	  $("#sk_outlet_id").append("<option value='all'>All</option>");
                	  $.each(response,function(k,v){
                          $("#sk_outlet_id").append("<option value='"+v.outlet_id+"'>"+v.outlet_name+"</option>");

                	  });
                                    
                              }
                  });  
          
        }
        </script>
        <script>
        $(".download").click(function(){
        	var year= $("#year").val();
        	var brand_id= $("#brand_id").val();
        	var month= "${month}";
        	var region_id= $("#region_id").val();
        	var sk_dealer_id= $("#sk_dealer_id").val();
        	var sk_outlet_id= $("#sk_outlet_id").val();
        	$(this).attr('href','<%=dashboardURL%>summarydownload/'+year+'/'+month+'/'+region_id+'/'+sk_dealer_id+'/'+sk_outlet_id+'/'+brand_id+'');
        });
        
        </script>

</body>
</html>