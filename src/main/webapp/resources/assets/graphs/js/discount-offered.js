 
// Create the chart
var chart = Highcharts.chart('customerchart', {
  chart: {
    type: 'pie'
  },
  title: {
    text: null,
    align: 'center',
    verticalAlign: 'middle',
    y: 0,
     
  },
    credits: {
      enabled: false
  },
  subtitle: {
    text: null
  },

  plotOptions: {
       pie: {
         
             crop: false,
            size: 180,
           overflow: "justify"
        },
    series: {
      dataLabels: {
        enabled: true,
           overflow:true,
          crop:false,
        format: '{point.name}: {point.y}%',
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
      "name": "Browsers",
      "colorByPoint": true,
        innerSize: '50%',
        "colorByPoint": false ,
      "data": [
        {
          "name": "Amount Not<br> Mentioned",
          "y": 54,
            color:'#99C8FF'
  
        },
        {
          "name": "Yes",
          "y":38,
            color:'#3F78CF'
        },
        {
          "name": "No",
          "y":  8,
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