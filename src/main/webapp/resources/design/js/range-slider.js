 
  $( function() {
    
	  // Min-Max slider script ends here
	  
	  
	  $( ".slider-range-min" ).slider({
     
      orientation: "horizontal",
      range: "min",
      max: 8,
      value: 2,
	  create: function( event, ui ) {
		  var max_limit = $(this).slider( "option", "max" );
   
//		  var min_limit =$(this).slider( "option", "min" );
//		  var min_limit_abs = Math.abs(min_limit);
		  var slide_value=  $(this).slider('value');
		  
          for(var i=0;i <= max_limit;i++){
             $('.values-wrap').append('<span style="left:'+((i*100)/max_limit)  +'%">'+ i +'</span>')
          }
          
		  },
      slide: function( event, ui ) {
		var max_limit = $(this).slider( "option", "max" );
		var min_limit =$(this).slider( "option", "min" );
		var min_limit_abs = Math.abs(min_limit)
			
      }
    });

 
  });
