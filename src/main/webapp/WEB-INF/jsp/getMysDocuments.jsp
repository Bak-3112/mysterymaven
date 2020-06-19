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
        <title>MYS-MYS Documents</title>
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
		
		iframe img{
		width:100%;
		height:100%;}
		
		.thumbChecked{
		width:100%; height:auto; object-fit: contain;
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

                        <div class="page-title-box">
                        <div class="row">
                    <div class="col-sm-6">
                        
                            
                            <h4 class="page-title">Documents</h4>
                      
                    </div>
                    <div class="col-sm-6 ">
                            
                            <a class="btn btn-gradient waves-effect waves-light pull-right" onClick="generateZIP()">Download Evidences</a>
                      
                    </div>
                </div>
 </div>
                <div class="row">
                    <div class="col-md-12 gallery">
                         <div class="row imgContainer">
                            <c:forEach var="mvBean" items="${shopperDocumentList}">
                          <%--  <c:if test="${count=='1'}">
                           No records found
                           </c:if> --%>
                            <div class="col-md-3 ">
                            <div class="card-box " style="height: auto;width: 100%;">
                            <h6>${mvBean.document_type}</h6>
                             <c:if test="${mvBean.document_name==''}">
                            <h4>Not uploaded <br>" ${mvBean.document_type} "</h4>
                            </c:if> 
                            <c:if test="${mvBean.document_name!='' && mvBean.extension == 'pdf'}"> 
                           <a href="<%=dashboardURL%>uploads/documents/${mvBean.shopper_folder_name}/${mvBean.document_name}"><i class="fa fa-file-pdf-o"  aria-hidden="true" style="font-size:48px;color:red"></i></a>
                           </c:if>
                            <c:if test="${mvBean.document_name!='' && mvBean.extension == 'jpgpdf'}"> 
                           <img class="thumb"  style="width:100%; height:auto; object-fit: contain;"
src="${uploaded_file_location}/documents/${mvBean.shopper_folder_name}/${mvBean.document_name}" name="imgbox" id="imgbox">
	 </c:if> 
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
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.5/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip-utils/0.0.2/jszip-utils.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/1.3.8/FileSaver.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/4.0/1/MicrosoftAjax.js"></script>
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
     
     <script type="text/javascript">
     
     var links = [];
     
     $('.thumb').each(function () {
         $(this).removeClass().addClass('thumbChecked');
         $(this).css("border", "2px solid #c32032");
         links.push($(this).attr('src'));
         console.log(links);
       $(".thumbChecked").removeAttr("style");
         if (links.length != 0) {
           $('.download').css("display", "block");
         }

       });


     $('.gallery').on('click', '.thumbChecked', function () {

       $(this).removeClass().addClass('thumb');
       $(this).css("border", "2px solid white");
       var itemtoRemove = $(this).attr('src');
       links.splice($.inArray(itemtoRemove, links), 1);
       console.log(links);
       
       if (links.length == 0) {
         $('.download').css("display", "none");
       }

     });


     function generateZIP() {
       console.log('TEST');
       var zip = new JSZip();
       var count = 0;
       var zipFilename = "Evidences.zip";

       links.forEach(function (url, i) {
         var filename = links[i];
      
         filename = filename.replace('<%=dashboardURL%>${sk_shopper_id}/','');
         // loading a file and add it in a zip file
         JSZipUtils.getBinaryContent(url, function (err, data) {
           if (err) {
             throw err; // or handle the error
           }
           zip.file(filename, data, { binary: true });
           count++;
           if (count == links.length) {
             zip.generateAsync({ type: 'blob' }).then(function (content) {
               saveAs(content, zipFilename);
             });
           }
         });
       });
     }
     </script>

     
</body>
</html>