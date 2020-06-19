
function getDetails(paci) 
        {
			
			var token="";
			$.ajax({
    	        url: "http://admin.lugma.co/getToken",
    	        type: "POST", 
    	        success: function(response)
    	                    {
    	        	var landmarkList=JSON.stringify(response);
    	        	var jsonString=JSON.parse(landmarkList);
    	        	for(var i=0;i<jsonString.length;i++)
        	 	    {
    	        		var token=jsonString[i].token
        	 	    }
    	        	
    	        	$.ajax({
            	        url: "https://kuwaitportal.paci.gov.kw/arcgisportal/rest/services/Hosted/PACIGeocoder/FeatureServer/0/query?where=civilid='"+paci+"'&f=pjson&token="+token+"&outFields=*",
            	        type: "POST", 
                        data: { 'paci': paci },
            	        success: function(response)
            	                    {
            	        	if(response.length=="79")
            	        	{
            	        		 generateToken();
            	        	}
            	        	var obj=JSON.parse(response);
            	        	for(var a=0;a<obj.features.length;a++)
            	        		{
            	        		var obj2=obj.features[a];
    	        				$("#latitude").val(obj2["attributes"]["lat"]);
    	        				$("#longitude").val(obj2["attributes"]["lon"]);
    							 $("#block").val(obj2["attributes"]["blockenglish"]);
    							$("#block_arabic").val(obj2["attributes"]["blockarabic"]);
    							$("#street").val(obj2["attributes"]["streetenglish"]);
    							$("#street_arabic").val(obj2["attributes"]["streetarabic"]);
    							//$("#avenue").val(obj2["attributes"]["lon"]);
    							//$("#avenue_arabic").val(obj2["attributes"]["lon"]);
    							$("#building").val(obj2["attributes"]["buildingnameenglish"]);
    							$("#building_arabic").val(obj2["attributes"]["buildingnamearabic"]);
    							$("#floor").val(obj2["attributes"]["floor_no"]);
    							$("#floor_arabic").val(obj2["attributes"]["floor_no"]); 
            	        		
            	        		 }
            	                    }
            	         });
    	        	
    	      			 }
    	        });
        }
		
        function generateToken() 
        {
        	
        	  $.ajax({
        	        url: "https://kuwaitportal.paci.gov.kw/arcgis/sharing/generateToken",
        	        type: "POST", 
                    data: { 'username': 'LuqmaUser','password': 'L0qGw8#7k','referer': 'requestip','expiration': '9999999','f': 'pjson'},
        	        success: function(response)
        	                    {
        	        			alert(response);
        	        			var obj=JSON.parse(response);
        	        			var token = obj.token;
        	        			//alert(token);
        	        			$.ajax({
        	            	        url: "http://admin.lugma.co/addToken",
        	            	        type: "POST", 
        	            	        data: { 'token': token},
        	            	        success: function(response)
        	            	                    {
        	            	                    }
        	            	        });                 
        	                    }
        	        });
        	
        }
