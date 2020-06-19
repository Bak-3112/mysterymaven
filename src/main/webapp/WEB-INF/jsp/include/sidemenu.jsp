<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import = "java.util.ResourceBundle" %>
<%
ResourceBundle resource =  ResourceBundle.getBundle("resources/web");
String UI_PATH=resource.getString("UiPath");
String title=resource.getString("dashboardTitle");
String dashboardURL=resource.getString("dashboardURL");
%>
<style>
.enlarged #wrapper #sidebar-menu .menu-title {
	display: block !important
}

.metismenu li{
margin-left: -10px;
}
.nav-third-level > li > a{
padding-left: 77px;
}
</style>


<div class="left side-menu">
                <div class="slimscroll-menu" id="remove-scroll">
				   
                    <div id="sidebar-menu">
                        <!-- Left Menu Start -->
                        <ul class="metismenu" id="side-menu">

<li class="menu-title text-right py-0" Style="pointer-events:auto" ><button type="button" style="height:44px" id="slidebutton" class="button-menu-mobile open-left waves-light waves-effect"> 
<img src="<%=UI_PATH %>/assets/images/frd-btn.jpg">

		
                            </button></li>
                           <c:forEach var="qBean" items="${sessionScope.mainMenu1}">
							
                            <li>
                            <c:if test="${qBean.menu_link!='#'}">
                                <a href="<%=dashboardURL%>${qBean.menu_link}"><i class="${qBean.menu_icon}"></i> <span>${qBean.menu_name} </span></a>
                                </c:if>
                                <c:if test="${qBean.menu_link=='#'}">
                                <a href="javascript: void(0);"><i class="${qBean.menu_icon}"></i> <span>${qBean.menu_name} </span> <span class="menu-arrow"></span></a>
                               
                                <ul class="nav-second-level collapse" aria-expanded="false">
                                 <c:forEach var="qBean1" items="${qBean.subMenu}">
                                  <li>
                                 <c:if test="${qBean1.menu_link!='#'}">
                                   <a href="<%=dashboardURL%>${qBean1.menu_link}"><!-- <i class="fa fa-file-text"> --></i>${qBean1.menu_name}</span></a>
                                    </c:if>
                                     <c:if test="${qBean1.menu_link=='#'}">
                                    <a href="javascript: void(0);"><!-- <i class="fa fa-file-text"> --></i>${qBean1.menu_name}<span class="menu-arrow"></span></a>
                                    
	                                	 <ul class="nav-third-level nav" aria-expanded="false">
	                                	 <c:forEach var="qBean2" items="${qBean1.subMenu}">
	                                    <li><a href="<%=dashboardURL%>${qBean2.menu_link}">${qBean2.menu_name}</a></li>
	                                    
	                                    </c:forEach>
	                                    </ul>
	                                    </c:if>
                                    </li>
                                    </c:forEach>
                                </ul>
                                
                                 </c:if>
                              
                            </li>
                          
							</c:forEach>
							<input type="hidden" value="${sessionScope.userId }" id="userId">
                        </ul>
                    </div>
                    </div>
                    </div>

	
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
if($("#userId").val()==""){
	window.location.replace("${pageContext.request.contextPath}/logout");
}
</script> 
<script>
                    $("#slidebutton").css("transform","rotate(180deg)");
                    $("#slidebutton").click(function() {
                    	if (  $( "#slidebutton" ).css( "transform" ) == 'none' ){
                            $("#slidebutton").css("transform","rotate(180deg)");
                        } else {
                            $("#slidebutton").css("transform","" );
                        }
                      });
                   
                    </script>