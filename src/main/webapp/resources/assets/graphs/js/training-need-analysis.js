
var ques = $("input.subQuestion").map(function() {
    return this.value;
}).get().join(",");

var score = $("input.score").map(function() {
    return this.value;
}).get().join(",");

ques = ques.split(",").map(String);
score=score.split(",").map(Number); 
//alert(score);
// Create the chart
Highcharts.chart('container-fluid', {
  chart: {
    type: 'column'
  },
  
  credits: {
        enabled: false
    },
  xAxis: {
    type: 'category',
   categories: ques,
    labels:{
    style: { 
            fontSize: '13px'
        }
      }
  },
 title: {
        text: '',
        style: {
            fontWeight: 'bold',
            fontSize: '25px'
        }
    },
  yAxis: {
    max: 100,
    tickInterval: 20,
    title: {
      text: null
    },
    labels:{
    style: { 
            fontSize: '15px'
        }
      }

  },
  legend: {
    enabled: false
  },
  plotOptions: {
    series: {
      borderWidth: 0,
      dataLabels: {
        enabled: false,
        format: '{point.y:.1f}%'
      }
    }
  },

  tooltip: {
    headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
  },

  "series": [
    {
      "name": "Rating Standards",
      "colorByPoint": false,
      'color':'#3f78cf',
      'data':score
//      "data": [
//        {
//          "name": "Communication",
//          "y": 9,
//          
//        },
//        {
//          "name": "Personal <br> Presentation",
//          "y": 6,
//          
//        },
//        {
//          "name": "Product <br> Presentation",
//          "y": 9.7,
//          
//        },
//        {
//          "name": "Proactive <br> Sales Pitch",
//          "y": 3,
//         
//        },
//        {
//          "name": "Warm <br> send-off",
//          "y": 8.02,
//         
//        },
//        
//        {
//          "name": "Prompt <br> Follow-up",
//          "y": 1,
//         
//        }
//      ]
    }
  ],
});