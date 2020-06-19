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
<html lang="en">
	<head>
		<title>MYS</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
		<link rel="shortcut icon"
	href="<%=UI_PATH%>/design/images/DQI-icon.png">
		<link rel='stylesheet' href='<%=UI_PATH%>/design/css/bootstrap.min.css'>
		<link rel="stylesheet" type="text/css" href="<%=UI_PATH%>/design/css/time.css">
		<link rel="stylesheet" type="text/css" href="<%=UI_PATH%>/design/css/custom.css">
		
		<style>
		.check-label span{display:inline-block;}
		.check-label span.block{display:block;}
		.upload-btn-wrapper {
  position: relative;
  overflow: hidden;
  display: inline-block;
}
.upload-btn-wrapper input[type=file] {
  font-size: 100px;
  position: absolute;
  left: 0;
  top: 0;
  opacity: 0;
}
.upload-btn{
padding: 10px 35px 10px 35px;
    background: #5367df;
    color: #fff;
    cursor: pointer;
    min-width: 70px;
    text-align: center;
    width: 100%;
    font-size: 12px;}
    .card {
    padding: 10px!important;
}
.mtop
{margin-top:35px;}
.sub-container{max-width:450px;margin:0 auto;}
.form-errors{color:#b43d3d;}
.mb-4{margin-bottom:20px;}
  .highlight {
    background-color: #1cd444 ;
}
		</style>
	</head>
	<body class="bodysection">
     
     
     <header class="header clearfix">
            <div class="logo pull-left">
                <a>
                    <img src="<%=UI_PATH%>/design/images/bmw.png"  width="50" height="50" alt="logo">
                </a>
                <a>
                    <img src="<%=UI_PATH%>/design/images/mini-logo.svg" width="128px"    alt="logo">
                </a>
               
            </div>
            <div class="greetingtext pull-right">
            	<span>Welcome <b>${name}</b></span>
            	<a href="<%=dashboardURL%>upload/${sid}"><i class="fa fa-home" aria-hidden="true"></i></a>
            </div>
            </header>
     	          	          
			      	
           <div class=" quizwrap quizwrap-adjst clearfix">
               <div class="col-xs-12   fig-wrap" >
                
              <figure class="banner-figure" >
                     <img src="<%=UI_PATH%>/design/images/car.jpg" width="956" height="450" class="img-responsive" >
                                     </figure>
             </div>
              <div class="col-xs-12  bg-white d-f fd-column jc-center form-wrap mtop">
              <h5 class="text-center mt-3">Please upload the requirement documents. Kindly provide reasons in case documents are not available</h5> 
                   	<div class="container sub-container">
                   	<form class="form-validate"  method="post"  action="<%=dashboardURL%>saveDocument" enctype="multipart/form-data" >
                   	<input type="hidden" id="sk_shopper_id" name="sk_shopper_id" value="${sk_shopper_id}"/>
				  	<input type="hidden" id="sid" name="sid" value="${sid}"/>
                   	<div class="row">
                   	  <div class="chiller_cb">
                        <label for="myCheckbox1" class="check-label" >							  
                         <span>Business card of sales consultant</span></label>							    		
					 </div>
                   	     <div class="col-md-4">
                   	      <div class="upload-btn-wrapper">
						<a class="btn upload-btn">Upload </a>
	                     <input type="file" id="file" name="files[0]" accept="application/pdf, application/jpg,application/jpeg,application/png" required  />
						</div>
                    </div>
                    <div class="col-md-8">
                     <div class="form-field">
                      <input id="business" name="documentType[0].documents" value="Business" type="hidden"class="form-control">
				  	  <input type="text" class="form-control mb-4"   id="business_comment" name="documentType[0].comment" required > 
					 </div>
                    </div>
                      <div class="chiller_cb">
                      <label for="myCheckbox1" class="check-label" >							  
                       <span>Brochure or any other marketing material shared by dealership</span></label>							    		
				    </div>
                    
                     <div class="col-md-4">
                   	      <div class="upload-btn-wrapper">
						<a class="btn upload-btn">Upload </a>
	                     <input type="file" id="file1" name="files[1]" accept="application/pdf, application/jpg,application/jpeg,application/png" required />
						</div>
                    </div>
                    <div class="col-md-8">
                     <div class="form-field">
                      <input id="brochure" name="documentType[1].documents" type="hidden" value="Brochure" class="form-control">
				      <input type="text" class="form-control mb-4"   id="brochure_comment"  name="documentType[1].comment" required>
					 </div>
                    </div>
                    
                     <div class="chiller_cb">
                     <label for="myCheckbox1" class="check-label" >							  
                     <span>Price list/ personalised proforma invoice</span></label>							    		
					</div>	
                     <div class="col-md-4">
                   	  <div class="upload-btn-wrapper">
					  <a class="btn upload-btn">Upload </a>
	                   <input type="file" id="file2" name="files[2]" accept="application/pdf, application/jpg,application/jpeg,application/png" required/>
					   </div>
                    </div>
                    <div class="col-md-8">
                     <div class="form-field">
                       <input id="priceList" name="documentType[2].documents" type="hidden" value="Pricelist" class="form-control">
				      <input type="text" class="form-control mb-4"   id ="priceList_comment" name="documentType[2].comment" required>
					 </div>
                    </div>
                    
                      <div class="chiller_cb">
                      <label for="myCheckbox1" class="check-label" >							  
                       <span>Specification sheet</span></label>							    		
				    </div>	
				    <div class="col-md-4">
                   	  <div class="upload-btn-wrapper">
					  <a class="btn upload-btn">Upload </a>
	                   <input type="file" id="file3" name="files[3]" accept="application/pdf, application/jpg,application/jpeg,application/png" required/>
					   </div>
                    </div>
                    
                    <div class="col-md-8">
                     <div class="form-field">
                       <input id="specification" name="documentType[3].documents"  type="hidden" value="Specificationssheet" class="form-control">
				      <input type="text" class="form-control mb-4" id ="specification_comment" name="documentType[3].comment" required>
					 </div>
                    </div>
                    
                    <div class="chiller_cb">
                      <label for="myCheckbox1" class="check-label" >							  
                       <span>Copy of Email/SMS shared upon confirmation of visit <span class="mx-auto block text-center">or</span>
							    		Copy of Thank you Email/SMS shared upon completion of visit</span>						    		
					</div>
					
					<div class="col-md-4">
                   	  <div class="upload-btn-wrapper">
					  <a class="btn upload-btn">Upload </a>
	                   <input type="file" id="file4" name="files[4]" accept="application/pdf, application/jpg,application/jpeg,application/png" required/>
					   </div>
                    </div>
                    
                    <div class="col-md-8">
                     <div class="form-field">
                      <input id="email" name="documentType[4].documents" type="hidden" value="Emailvisit" class="form-control">
                      <input type="text" class="form-control mb-4" id ="email_comment" name="documentType[4].comment"  required>
					 </div>
                    </div>
                      <div class="chiller_cb">
                       <label for="myCheckbox1" class="check-label" >							  
                          <span>Other evidences</span></label>							    		
					</div>
					<div class="col-md-4">
                   	  <div class="upload-btn-wrapper">
					  <a class="btn upload-btn">Upload </a>
	                   <input type="file" id="file5" name="files[5]" accept="application/pdf, application/jpg,application/jpeg,application/png" required>
					   </div>
                    </div>
                    <div class="col-md-8">
                     <div class="form-field">
                      <input id="otherEvidences" name="documentType[5].documents" value="Otherevidences" type="hidden" class="form-control">
                      <input type="text" class="form-control mb-4" id ="otherEvidences_comment" name="documentType[5].comment" required >
					 </div>
                    </div>
                     <div class="chiller_cb">
                     <label for="myCheckbox1" class="check-label" >							  
                      <span>Financing option related documents</span></label>							    		
					  </div>
					  <div class="col-md-4">
                   	  <div class="upload-btn-wrapper">
					  <a class="btn upload-btn">Upload </a>
	                   <input type="file" id="file6" name="files[6]" accept="application/pdf, application/jpg,application/jpeg,application/png" required>
					   </div>
                    </div>	
                    <div class="col-md-8">
                     <div class="form-field">
                     <input id="finanical" name="documentType[6].documents" type="hidden" value="Financial" class="form-control">
                      <input type="text" class="form-control mb-4" id ="finanical_comment" name="documentType[6].comment" required>
					 </div>
                    </div>
                      <div class="chiller_cb">
                      <label for="myCheckbox1" class="check-label" >							  
                       <span>Extended warranty</span></label>							    		
					 </div>	
					 <div class="col-md-4">
                   	  <div class="upload-btn-wrapper">
					  <a class="btn upload-btn">Upload </a>
	                   <input type="file" id="file7" name="files[7]" accept="application/pdf, application/jpg,application/jpeg,application/png" required/>
					   </div>
                    </div>
                    
                    <div class="col-md-8">
                     <div class="form-field">
                      <input id="warranty" name="documentType[7].documents" type="hidden" value="Warranty" class="form-control">
                      <input type="text" class="form-control mb-4" id ="warranty_comment" name="documentType[7].comment" required>
					 </div>
                    </div>
                   <div class="chiller_cb">
                    <label for="myCheckbox1" class="check-label" >							  
                     <span>Evidence of inquiry raised</span></label>							    		
					</div>
					<div class="col-md-4">
                   	  <div class="upload-btn-wrapper">
					  <a class="btn upload-btn">Upload </a>
	                   <input type="file" id="file8" name="files[8]" accept="application/pdf, application/jpg,application/jpeg,application/png" required >
					   </div>
                    </div>
                    <div class="col-md-8">
                     <div class="form-field">
                      <input id="inquiry" name="documentType[8].documents"  value="Inquiry" type="hidden" class="form-control">
                      <input type="text" class="form-control mb-4" id ="inquiry_comment" name="documentType[8].comment" required >
					 </div>
                    </div>  
                     </div>       
                   <div class="form-errors">
					 <ul></ul>
						    </div> 
						      <div class="col-xs-10">
							    <hr>	
							    <div class="form-errors"></div>		  						  					
				  					<a id="btn-back" href="<%=dashboardURL%>upload/${sid}" class="btn btn-info btn-grey mrg-top margin-right" >Back</a>
                                    
				  					 <button class="btn btn-info mrg-top"
											id="button-submit" type="submit">Proceed</button> 
				  				</div>
						</form>
						</div>
             </div>
        </div>
        
        <input type="hidden" id="shopper_count" name="shopper_count" value="${shopper_count}">
        
<div class="modal fade documentmodel" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content clearfix">
      
      <div class="modal-body text-center col-xs-12">
      <h4 class="text-center"><b>Thank you for uploading your evidences</b></h4 >
      <ul class="clearfix list-unstyled">
      <li class="col-xs-12 col-sm-12 uploadbutton" style="margin-bottom:20px">
        <a href="<%=dashboardURL%>upload/${sid}" class="btn  btn-info btn-grey btn-block">Exit</a>
        </li>
        <li class="col-xs-12 col-sm-12 uploadbutton" style="margin-bottom:20px">
        <a href="<%=dashboardURL%>shopperDetails/${sid}" class="btn btn-info btn-block">Continue with questionnaire</a>
        </li>
        </ul>
      </div>
      
    </div>
  </div>
</div>
	<script src="<%=UI_PATH%>/design/js/jquery.min.js"></script>
	<script src="<%=UI_PATH%>/design/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>	
 
	
<script>
		var shopper_count = $("#shopper_count").val();
		if (shopper_count == "" || shopper_count == null) 
		{

		} 
		else {
			 $('#myModal').modal('show');
		}
	</script>

<script>
$(document).ready(function() {
$('.form-validate').validate({
  errorContainer: $(this).find('.form-errors'),
  errorLabelContainer: $('ul', $(this).find('.form-errors')),
  wrapper: 'li',
groups: {
  mygroup: "files[0] documentType[0].comment files[1] documentType[1].comment files[2] documentType[2].comment files[3] documentType[3].comment files[4] documentType[4].comment files[5] documentType[5].comment files[6] documentType[6].comment files[7] documentType[7].comment files[8] documentType[8].comment"
}
});
});

$.extend($.validator.messages, {
required: "Please upload the evidences or provide reasons in case not available"
});

</script>
<script>

  /* $("input[type=file]").each(function(){
	
	$(this).on("change",function(){
		if($(this).val()!=""){
			console.log($(this).siblings().find("a").find(".upload-btn").css('background-color', 'green'));
		 	$(this).find("#business_comment").prop('disabled', true);
       	 	$(this).find(".upload-btn").css('background-color', 'green'); 
		}else{
			 $(this).find("#business_comment").prop('disabled', false);
			$(this).find(".upload-btn").css('background-color', '#5367df'); 
		}
	});
	
}); */  
</script>

<script>
$(function() {
    $("#file").change(function (){
    	if ($(this).val() != ""){
      $("#business_comment").prop('disabled', true);
      $("#file").siblings().closest(".btn.upload-btn").addClass("highlight");
    	}else{
      $("#business_comment").prop('disabled', false);
      $("#file").siblings().closest(".btn.upload-btn").removeClass("highlight");
    	}
    });
    $("#business_comment").change(function (){
      if($("#business_comment").val().trim() != ""){
         $("#file").prop('disabled', true);
       
      }else{
         $("#file").prop('disabled', false);

      }

    });
    
    $("#file1").change(function (){
    	if ($(this).val() != ""){
      $("#brochure_comment").prop('disabled', true);

      $("#file1").siblings().closest(".btn.upload-btn").addClass("highlight");
    	}else{
      $("#brochure_comment").prop('disabled', false);
      $("#file1").siblings().closest(".btn.upload-btn").removeClass("highlight");
    	}
    });
    $("#brochure_comment").change(function (){
      if($("#brochure_comment").val().trim() != ""){
         $("#file1").prop('disabled', true);
      }else{
         $("#file1").prop('disabled', false);
      }

    });
    
    $("#file2").change(function (){
    	if ($(this).val() != ""){
      $("#priceList_comment").prop('disabled', true);
      $("#file2").siblings().closest(".btn.upload-btn").addClass("highlight");
    	}else{
      $("#priceList_comment").prop('disabled', false);
      $("#file2").siblings().closest(".btn.upload-btn").removeClass("highlight");
    	}
    });
    $("#priceList_comment").change(function (){
      if($("#priceList_comment").val().trim() != ""){
         $("#file2").prop('disabled', true);
      }else{
         $("#file2").prop('disabled', false);
      }

    });
    
    $("#file3").change(function (){
    	if ($(this).val() != ""){
      $("#specification_comment").prop('disabled', true);
      $("#file3").siblings().closest(".btn.upload-btn").addClass("highlight");
    	}else{
      $("#specification_comment").prop('disabled', false);
      $("#file3").siblings().closest(".btn.upload-btn").removeClass("highlight");
    	}
    });
    $("#specification_comment").change(function (){
      if($("#specification_comment").val().trim() != ""){
         $("#file3").prop('disabled', true);
      }else{
         $("#file3").prop('disabled', false);
      }

    });
    
    $("#file4").change(function (){
    	if ($(this).val() != ""){
      $("#email_comment").prop('disabled', true);
      $("#file4").siblings().closest(".btn.upload-btn").addClass("highlight");
    	}else{
      $("#email_comment").prop('disabled', false);
      $("#file4").siblings().closest(".btn.upload-btn").removeClass("highlight");
    	}
    });
    $("#email_comment").change(function (){
      if($("#email_comment").val().trim() != ""){
         $("#file4").prop('disabled', true);
      }else{
         $("#file4").prop('disabled', false);
      }

    });
    
    $("#file5").change(function (){
    	if ($(this).val() != ""){
      $("#otherEvidences_comment").prop('disabled', true);
      $("#file5").siblings().closest(".btn.upload-btn").addClass("highlight");
    	}else{
      $("#otherEvidences_comment").prop('disabled', false);
      $("#file5").siblings().closest(".btn.upload-btn").removeClass("highlight");
    	}
    });
    $("#otherEvidences_comment").change(function (){
      if($("#otherEvidences_comment").val().trim() != ""){
         $("#file5").prop('disabled', true);
      }else{
         $("#file5").prop('disabled', false);
      }

    });
    
    $("#file6").change(function (){
    	if ($(this).val() != ""){
      $("#finanical_comment").prop('disabled', true);
      $("#file6").siblings().closest(".btn.upload-btn").addClass("highlight");
    	}else{
      $("#finanical_comment").prop('disabled', false);
      $("#file6").siblings().closest(".btn.upload-btn").removeClass("highlight");
    	}
    });
    $("#finanical_comment").change(function (){
      if($("#finanical_comment").val().trim() != ""){
         $("#file6").prop('disabled', true);
      }else{
         $("#file6").prop('disabled', false);
      }

    });
    
    $("#file7").change(function (){
    	if ($(this).val() != ""){
      $("#warranty_comment").prop('disabled', true);
      $("#file7").siblings().closest(".btn.upload-btn").addClass("highlight");
    	}else{
      $("#warranty_comment").prop('disabled', false);
      $("#file7").siblings().closest(".btn.upload-btn").removeClass("highlight");
    	}
    });
    $("#warranty_comment").change(function (){
      if($("#warranty_comment").val().trim() != ""){
         $("#file7").prop('disabled', true);
      }else{
         $("#file7").prop('disabled', false);
      }

    });
    
    $("#file8").change(function (){
    	if ($(this).val() != ""){
      $("#inquiry_comment").prop('disabled', true);
      $("#file8").siblings().closest(".btn.upload-btn").addClass("highlight");
    	}else{
      $("#inquiry_comment").prop('disabled', false);
      $("#file8").siblings().closest(".btn.upload-btn").removeClass("highlight");
    	}
    });
    $("#inquiry_comment").change(function (){
      if($("#inquiry_comment").val().trim() != ""){
         $("#file8").prop('disabled', true);
      }else{
         $("#file8").prop('disabled', false);
      }
    });
    
 });
</script>
</body>
</html>
 
