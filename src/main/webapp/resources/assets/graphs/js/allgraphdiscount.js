var amount=parseInt($('#amount').val());
var yes= parseInt($('#yes').val());
var no=parseInt($('#no').val()); 
// Create the chart
var chart = Highcharts.chart('customerchartdiscount', {
  chart: {
    type: 'pie'
  },
  title: {
    text: null,
    align: 'center',
    verticalAlign: 'middle',
    y: 0
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
            size: 150,
            cursor: 'pointer',
            dataLabels: {
                enabled: true
            },
            showInLegend: true
        },
    series: {
      dataLabels: {
        enabled: true,
           overflow:true,
           distance: 1,
          crop:false,
        format: ' {point.y}%',
           style: {
            color: '#030303',
           'font-family': "'BMWGroup-Regular'",
             fontSize:'10px',
             fontWeight:200
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
      "name": "Browsers",
      "colorByPoint": true,
        innerSize: '50%',
        "colorByPoint": false ,
      "data": [
        {
          "name": "Amount Not<br> Mentioned",
          "y": amount,
            color:'#99C8FF'
  
        },
        {
          "name": "Yes",
          "y":yes,
            color:'#3F78CF'
        },
        {
          "name": "No",
          "y":  no,
            color:'#69AEFF'
        },
       
        
      ]
    }
  ],

});

//var tot=0;
//chart.series[0].data.forEach(function(value,index){
//  tot = tot+value.y;  
//})
//var avg = (tot/chart.series[0].data.length).toFixed(1)+'%';
// chart.setTitle({ text: avg });