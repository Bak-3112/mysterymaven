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
  		  <style>
  		  body{
  		   font-family: 'BMWGroup-Regular';
  		  }
  		  body *:not(.fa):not(.menu-arrow):not([class^="fi-"]):not([class*=" fi-"]){  font-family: 'BMWGroup-Regular' !important; }
  		  .h1, .h2, .h3, .h4, .h5, .h6, h1, h2, h3, h4, h5, h6{
  		  font-family: 'BMWGroup-Regular';
  		  }
  		  label,th,span,a{font-family: 'BMWGroup-Regular';}
  		  .navbar-custom{
  		  margin-left:0px !important;
  		  }
  		  @media(max-width:767px){
  		  .page-title-box{padding-left:65px;}
  		  .enlarged .button-menu-mobile{position:absolute !important;left:100%;}
  		  }
  		  .navbar-custom .nav-link{
  		  color:rgb(255, 255, 255) !important;
  		  }
  		  .btn-gradient{
  		  background: linear-gradient(to top, #5d6dc3, #5d6dc3) !important;
  		  }
  		  .header-text{color: white;
    font-size: 18px;
    /* margin: 0 auto; */
    padding: 24px 0 0 0;
    text-align: center;}
    .highcharts-button-symbol{
    display: none;
    }
  		  </style>
<div class="topbar">

                <!-- LOGO -->
              <!--   <div class="topbar-left" style="background:#8e8e8e !important">
                    
                </div> -->

                <nav class="navbar-custom ml-0 pl-3" style="background:#8e8e8e !important">
						<a href="<%=dashboardURL%>home" class="logo float-left mt-1 ml-5">
                                        <span>
                                           <img src="<%=UI_PATH %>/design/images/bmw-logo.png" alt="" height="50" width="50"><img class="ml-3" src="<%=UI_PATH %>/design/images/mini-logo.svg" alt="" width="128px">  
                                        </span>
                        <i>
                        	
                        </i>
                    </a>
                    
                    <ul class="list-unstyled topbar-right-menu float-right mb-0">
                        
						
      <li class="nav-item px-2">
        <a class="nav-link" href="#">
          Welcome ${role_name }  team
<!--
            <span>
                <img src="assets/images/house-outline.png">
            </span>
-->
          </a>
      </li>
          <li class="nav-item px-2">
        <a class="nav-link" href="#">
            <img src="<%=UI_PATH %>/design/images/user.png">
              </a>
        </li>
        <li class="nav-item px-2">
        <a class="nav-link" href="<%=dashboardURL%>logout">
            <img src="<%=UI_PATH %>/design/images/logout.png">
              </a>
        </li>
         </ul>
<h5 class="header-text">Mystery Shopping Program </h5>
                </nav>

            </div>
            

