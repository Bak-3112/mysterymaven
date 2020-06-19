<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ page import = "java.util.ResourceBundle" %> 
    


<style>

</style>

  		 <!-- Modal HTML -->
       <div id="myModal" class="modal fade">
	<div class="modal-dialog modal-confirm">
		<div class="modal-content">
			<div class="modal-header justify-content-center border-0">
				<h6>Mystery Shopping Program</h6>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<!-- <input type="text" id="particular_zone_id" name="particular_zone_id"/> -->
			<div class="modal-body">
				<h6 class="text-center">Are you sure you want to delete this item?</h6>
			</div>
			
			<div class="modal-footer justify-content-center border-0">
				<button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
				<a class="btn-edit" href="#"><button type="button" class="btn btn-danger">Delete</button></a>
			</div>
		</div>
	</div>
</div>    

 <div id="myModal1" class="modal fade">
	<div class="modal-dialog modal-confirm">
		<div class="modal-content">
			<div class="modal-header justify-content-center border-0">
				<h6>Mystery Shopping Program</h6>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<!-- <input type="text" id="particular_zone_id" name="particular_zone_id"/> -->
			<div class="modal-body">
				<h6 class="text-center">Are you sure you want to restore this item?</h6>
			</div>
			
			<div class="modal-footer justify-content-center border-0">
				<button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
				<a class="btn-restore" href="#"><button type="button" class="btn btn-danger">Restore</button></a>
			</div>
		</div>
	</div>
</div>    