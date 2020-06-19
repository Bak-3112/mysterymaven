
      window.onload = function() {
    	  var nodelay=parseFloat($('#nodelay').val());
    	  var more=parseFloat($('#more').val());
    	  var upto=parseFloat($('#upto').val());
    	  console.log(nodelay);
    
/*      var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
       
    
        title: {
            text: ""
        },
        plotOptions: {
            pie: {
              allowPointSelect: true,
              cursor: 'pointer',
              dataLabels: {
                enabled: true
              },
              showInLegend: true
            }
          },
          legend: {
            reversed: true,
            symbolRadius: 0,
            fontFamily: "BMWGroup-Regular",
            fontWeight:400
          },

        data: [{
            type: "pie",
            startAngle: 240,
            yValueFormatString: "##0\"%\"",
            indexLabel: "{label} {y}",
            dataPoints: [
                {y: more, label: "More than 2 days",color:"#70A9FE",indexLabelFontFamily: "'BMWGroup-Regular'",showInLegend: true,legendText: "More than 2 days", },
                {y: upto, label: "Upto 2 days", color:"#3F78CF",indexLabelFontFamily: "'BMWGroup-Regular'",showInLegend: true,legendText: "Upto 2 days", },
                {y: nodelay, label: "No delay",color:"#99C8FF",indexLabelFontFamily: "'BMWGroup-Regular'",showInLegend: true,legendText: "No delay", },          
                
            ]
        }]
      });
      chart.render();
      
      }
      */
      
      Highcharts.chart('chartContainer', {
    	  chart: {
    	    type: 'pie' },

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

    	  subtitle: {
    	    text: '' },

    	  plotOptions: {
    	    pie: {
    	            showInLegend: true
    	        },
    	        legend: {
    	            reversed: true,
    	            symbolRadius: 0,
    	            fontFamily: "BMWGroup-Regular",
    	            fontWeight:200
    	          },itemStyle: {
    	                color: '#6c7070',
    	                fontWeight: 200,
    	             fontFamily: "'BMWGroup-Regular'",
    	            },
    	    series: {
    	      dataLabels: {
    	        enabled: true,
    	        format: '{point.y:.1f}%' }
    	      } 
    	    },


    	  tooltip: {
    	    headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
    	    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>' },


    	  series: [
    	  {
    	    name: "",
    	    colorByPoint: true,
    	    data: [	
    	 


    	  {
    	      name: "No delay",
    	      y: nodelay,
    	      drilldown: null,color:"#99C8FF" }, 
    	      {
        	      name: "More than 2 day",
        	      y: more,
        	      drilldown: "Chrome",
        	    	  color:"#70A9FE",},
        	    	    {
        	    	      name: "Upto 2 days",
        	    	      y: upto,
        	    	      drilldown: "Firefox" , color:"#3F78CF"},
        	    	      ] }],
    	      
    	  });
      }