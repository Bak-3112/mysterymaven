/*var dataArray = []
$('.OnlineSalesChannelpercentagesubsection').each(function(index){

	var obj = {};
	var itemvalue = $(this).val().split(',');
	
	obj['name']=itemvalue[0];
	obj['y']= parseFloat(itemvalue[1]);
	obj['color']=itemvalue[2];
	
	dataArray.push(obj);
	
})*/

var dataArray = []
$('.OnlineSalesChannelpercentagesubsection').each(function(index){
  
  var obj = {};
  var itemvalue = $(this).val().split(',');
 
 // obj['name']=itemvalue[0]
  obj['name']=itemvalue[0].substring(0, 15);
  obj['y']= parseFloat(itemvalue[1]);
  obj['color']=itemvalue[2];
  dataArray.push(obj);
  
})

//dataArray.shift();
dataArray.pop();
dataArray.pop();
console.log( dataArray )
/*$('.processpercentagesubsection').each(function(index){
  
  var obj = {};
  var itemvalue = $(this).val().split(',');
  obj['name']=itemvalue[0];
  //obj['name']=itemvalue[0].substring(0, 15);
  obj['y']= parseFloat(itemvalue[1]);
  obj['color']=itemvalue[2];
  
  dataArray.push(obj);
  
})
dataArray.splice(3,1);
dataArray.pop();*/
console.log(dataArray)
// Create the chart
var per=$('#OnlineSalesChannelpercentage').val()+"%";

// Create the chart
var chart = Highcharts.chart('Onlinesale', {
  chart: {
    type: 'pie'
  },
  legend: {
	     
      itemStyle: {
          color: '#6c7070',
          fontWeight: 400,
       fontFamily: "'BMWGroup-Regular'",
      }
  },
  title: {
	    text: per,
	    align: 'center',
	    verticalAlign: 'middle',
	    y: -23
	  },
	    credits: {
	      enabled: false
	  },
	  subtitle: {
	    text: null
	  },

	  plotOptions: {
		  pie: {
	          allowPointSelect: true,
	          cursor: 'pointer',
	          dataLabels: {
	              enabled: true
	          },
	          size:180,
	          showInLegend: true
	      },
	    series: {
	      dataLabels: {
	        enabled: true,
	          overflow:true,
	          crop:false,
	        format: '{point.y:.2f}%',
	           style: {
	            color: '#030303',
	           'font-family': "'BMWGroup-Regular'",
	             fontSize:'10px',
	             fontWeight:500
	         }
	      }
	    }
	  },

	  tooltip: {
	    headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
	  },

  "series": [
	    {
	      "name": "",
	      "colorByPoint": true,
	        innerSize: '70%',
	        "colorByPoint": false ,
	      "data": dataArray,
	      dataLabels: {
	            distance: 5 
	        }
	    }
	  ],	

});

var tot=0;
chart.series[0].data.forEach(function(value,index){
  tot = tot+value.y;  
})
var avg = ((tot/chart.series[0].data.length)*2).toFixed(2)+'%';
 chart.setTitle({ text: per });