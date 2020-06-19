
/*Highcharts.chart('dealer', {
  chart: {
    type: 'column'
  },
     legend: {
          enabled:true,
        align: 'center',
        verticalAlign: 'top',
        x: 0,
        y: -25,
        itemDistance: 7,
          itemStyle: {
            color: '#003366',
             fontSize:'13px',
            fontWeight: 600,
         fontFamily: "'BMWGroup-Regular'",
        }
          
    },
     credits: {
      enabled: false
  },
  title: {
    text: null
  },
  xAxis: {
    categories: ['January, 2018', 'April, 2018', 'July, 2018','October, 2018'],
       tickLength: 0,
      lineColor: 'transparent',
      labels: {
         
         style: {
            color: '#003366',
            'font-family': "'BMWGroup-Regular'",
             'font-size':'13px',
             'font-weight':400,
         }
      },
  },
  yAxis: {
      reversedStacks: false,
    min: 0,
       gridLineColor: 'transparent',
        labels: {
            enabled: false,
        }, 
    title: {
      text: null
    }
  },
   
    
  tooltip: {
    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
    shared: true
  },
  plotOptions: {
    column: {
      stacking: 'percent'
    },
        series: {
         
      borderWidth: 0,
      //   pointWidth: 26,
         pointPadding: 0,
            groupPadding: 0.1,
            borderWidth: 0,
            shadow: false,
      dataLabels: {
          enabled: true,
          inside:true,
           rotation: 0,
          verticalAlign:'middle',
          y:0,
        format: '{point.y}%',
          color:'#fff',
           style: {
                textOutline: 0,
                fontSize:'12px',
                fontFamily: "'BMWGroup-Regular'",
  },
      }
    }
  },
  series: [{   
	    name: 'After 4 hours on the same working day',
	      "colorByPoint": false ,
	        color: '#3F78CF',
	    data: [8, 19, 3,8]
	  },
	           {
	    name: 'Within 2 hours after sending the request',
	      "colorByPoint": false ,
	        color: '#5094FC',
	    data: [8, 19, 3,8]
	  },
	          {
	    name: 'Within 4 hours after sending the request',
	      "colorByPoint": false ,
	        color: '#83BCFF',
	    data: [8, 19, 3,8]
	  },          
	  {
		    name: 'Not at all',
		      "colorByPoint": false ,
		        color: '#B0D5FF',
		    data: [8, 19, 3,8]
		  },
             
 ]
});*/