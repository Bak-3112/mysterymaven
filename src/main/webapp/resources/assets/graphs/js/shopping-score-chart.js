var array=$('#array').val();
var array = JSON.parse(array)
//alert(array)
console.log(array)
scoreData = []

var colorsaray= ['#009ADA','#BB0A30','#000000','#BB0A30']
array.forEach(function(item,index){
	var lindedata = item.answerDetails.map(function(item,index){
	
		return item.percentage;
	})
	console.log(lindedata);
	scoreData.push({
    name: item.brand_name,
       color: colorsaray[index],
    data:lindedata.map(Number),
       
      marker : {symbol : 'circle',radius : 5,}
  })
	
	
})
	console.log(scoreData);
/*
Highcharts.chart('graph-container', {
  chart: {
    type: 'line'
  },
      legend: {
          enabled:false,
        align: 'left',
        verticalAlign: 'top',
        x: 0,
        y: 0,
          
    },
     credits: {
      enabled: false
  },
  title: {
    text: null
  },
  subtitle: {
    text: null
  },
  xAxis: {
    tickLength: 0,
    lineColor: 'transparent',
    categories: [ 'Q1', 'Q2','Q3','Q4'],
      labels: {
         
         style: {
           
            'font-family': "'BMWGroup-Regular'",
             'font-size':'11px',
             'font-weight':400,
         }
      },
  },
  yAxis: {
       
    title: {
      text: null
    },
      labels: {
         format: '{value}%',
         style: {
           
            'font-family': "'BMWGroup-Regular'",
             'font-size':'11px',
             'font-weight':400,
         }
      },
  },
  plotOptions: {
      series:{
      marker: {
                enabled: false
            },},
    line: {
      dataLabels: {
        enabled: false
      },
      enableMouseTracking: true
    }
  },
  series: scoreData,
});*/


Highcharts.chart('graph-container', {
	  chart: {
	    type: 'line',
	    inverted: false
	  },
	      legend: {
	          enabled:true,
	        align: 'center',
	        verticalAlign: 'bottom',
	        x: 0,
	        y: 0,
	        symbolHeight: 12,
	        symbolWidth: 12,
	        symbolRadius: 6, 
	        itemStyle: {
	            color: '#6c7070',
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
	  subtitle: {
	    text: null
	  },
	  xAxis: {  tickInterval: 1,
	    tickLength: 0,
	    lineColor: 'transparent',
	    categories: [ 'Q1', 'Q2','Q3','Q4'],
	      labels: {
	         
	         style: {
	           
	            'font-family': "'BMWGroup-Regular'",
	             'font-size':'11px',
	             'font-weight':400,
	         }
	      },
	  },
	  yAxis: {
		  max:100,
	      tickInterval: 20,
	    title: {
	      text: null
	    },
	      labels: {
	         format: '{value}%',
	         style: {
	           
	            'font-family': "'BMWGroup-Regular'",
	             'font-size':'11px',
	             'font-weight':400,
	         }
	      },
	  },
	  plotOptions: {
	      series:{
	      marker: {
	                enabled: true,
	          
	            },},
	    line: {
	      dataLabels: {
	    	  format: '{y} % ',
	        enabled: true
	      },
	      enableMouseTracking: true
	    }
	  },
	  series:scoreData,
	});