
// Create the chart
var chart = Highcharts.chart('processchart', {
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
          "name": "Test Drive",
          "y": 98.82,
            color:'#036'
  
        },
        {
          "name": "Sales Consultation",
          "y": 97.28,
            color:'#a8c5e0'
        },
        {
          "name": "Contact ",
          "y":  87.46,
            color:'#214f80'
        },
        {
          "name": "Follow-Up",
          "y": 86.61,
            color:'#a4d3e3'
        },
        {
          "name": "Offer",
          "y": 85.54,
            color:'#82a1bd'
        },
        
      ]
    }
  ],

});

/*var tot=0;
chart.series[0].data.forEach(function(value,index){
  tot = tot+value.y;  
})
var avg = (tot/chart.series[0].data.length).toFixed(2)+'%';
 chart.setTitle({ text: avg });*/