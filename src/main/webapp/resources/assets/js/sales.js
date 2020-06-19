
/* five */

Highcharts.chart('con-chart-2', {
  chart: {
    type: 'column',
       marginTop: 60
  },
     legend: {
          enabled:false,
        align: 'left',
        verticalAlign: 'top',
        x: 0,
        y: -25,
        itemDistance: 7
          
    },
     scrollbar: {
        enabled: true
    },
     credits: {
      enabled: false
  },
  title: {
    text: null
  },
  xAxis: {
    categories: ['Adam Shaikh','Akshay Wadhwani','Arvind Dabas', 'Ashma S', 'Atinder Pal Singh','Dhruv Vasanth','Farhan Tanwar','Hemant Mehra','Jitender Joshi','Julian Mariadas','Karan Sahani','Kush','john','peter','amith','eqfqf','cqfqefq','ascac','ascaca','casca','acaaa','ams','gw','eg','egge','egeg','efe','ff','dqd','fuiefui','feyifh','fiyefh','dfsfsf','dsfsfs','adad','dasdad','fwewe','wewrw','ffwewe','fwew','dfaffaf','fewrwre','qer','asd','mnb','yuio','dfghj'],
 min: 3,
    tickLength: 0,
      lineColor: 'transparent',
      labels: {
         rotation:-90,
         style: {
            color: '#003366',
            'font-family': '"Quattrocento Sans", sans-serif',
             'font-size':'13px',
             'font-weight':400,
         }
      },
  },
  yAxis: {
  max:100,
      reversedStacks: false,
    min: 0,
       gridLineColor: 'transparent',
        labels: {
            enabled: true,
                format: '{value}%',
            
        }, 
    title: {
      text: 'Average of Outlet Scores'
    }
  },
   
    
  tooltip: {
    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
    shared: true
  },
  
  plotOptions: {
        series: {
      borderWidth: 0,
         pointPadding: 0,
            groupPadding: 0.1,
            borderWidth: 0,
            shadow: false,
      dataLabels: {
          enabled: true,
          y:-25,
        format: '{point.y}%',
          color:'#000',
           style: {
                textOutline: 0,
                fontSize:'12px',
                fontFamily: '"Quattrocento Sans", sans-serif',
  },
      }
    }
  },
  series: [{
    name: 'No',
      "colorByPoint": false ,
        color: '#003366',
    data: [100, 100, 100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,99,99,99,99,99,99,99,99,98,98,98,98,98,98,97,97,97,97,97,97,97,97,98,98,98,98,98,98,98,98]
  },
           
 
  
]
});