
// Create the chart
var chart = Highcharts.chart('customerchart', {
  chart: {
    type: 'pie'
  },
  title: {
    text: '2017',
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
             crop: false,
            size: 250,
           overflow: "visible"
        },
    series: {
      dataLabels: {
        enabled: true,
        format: '{point.name}: {point.y:.2f}%',
           style: {
            color: '#030303',
           'font-family': "'bmwTypeWebLight'",
             fontSize:'13px',
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
          "name": "Purchase Consideration",
          "y": 94.92,
            color:'#366996'
  
        },
        {
          "name": "User Friendly Appointment Schedule",
          "y":94.25,
            color:'#304e64'
        },
        {
          "name": "Sales Consultation Eval ",
          "y":  90.19,
            color:'#004576'
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