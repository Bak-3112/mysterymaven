dataarr = [1,5,6];
// Create the chart
var chart = Highcharts.chart('processchart', {
  chart: {
    type: 'pie'
  },
  title: {
    text: '2017',
    align: 'center',
    verticalAlign: 'middle',
    y: -35
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
            size: 200,
           overflow: "visible",
              showInLegend: true,
        },
    series: {
      dataLabels: {
        enabled: false,
          overflow:true,
          crop:false,
        format: '{point.name}: <br>{point.y:.2f}%',
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
    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'+dataarr[parseInt('{point.x}')]
  },

  "series": [
    {
      "name": "Browsers",
      "colorByPoint": true,
        innerSize: '70%',
        "colorByPoint": false ,
      "data": [
        {
          "name": "Test Drive",
          "y": 97.71,
            color:'#CCE3FF'
  
        },
        {
          "name": "Sales <br>Consultation",
          "y":  96.73,
            color:'#99C8FF'
        },
        {
          "name": "Contact",
          "y": 89.98,
            color:'#83BCFF'
        },        
        {
          "name": "Follow-Up",
          "y": 84.23,
            color:'#5094FC'
        },       
        {
          "name": "Offer",
          "y": 83.67,
            color:'#3F78CF'
        },
        
      ]
    }
  ],

});

var tot=0;
chart.series[0].data.forEach(function(value,index){
  tot = tot+value.y;  
})
var avg = (tot/chart.series[0].data.length).toFixed(2)+'%';
 chart.setTitle({ text: avg });