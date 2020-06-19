
Highcharts.chart('ytdchart', {
  chart: {
    type: 'area',
       inverted: false
  },
      legend: {
        align: 'center',
        verticalAlign: 'top',
        x: 0,
        y: 0,
        symbolHeight: 12,
        symbolWidth: 12,
        symbolRadius: 6,          
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
       tickInterval: 2,
    tickLength: 0,
    lineColor: 'transparent',
    categories: ['Jan 2018', 'Feb 2018', 'Mar 2018', 'Apr 2018','May 2018','June 2018','Jul 2018','Aug 2018','Sep 2018','Oct 2018','Nov 2018','Dec 2018'],
      labels: {         
         style: {
            color: '#000000',
           'font-family': "'BMWGroup-Regular'",
             'font-size':'12px',
             'font-weight':500
         }
      },
  },
  yAxis: {
      min:84,
        tickInterval: 2,
    title: {
      text: null
    },
       labels: {
          format: '{value}%',
         style: {
            color: '#000000',
           'font-family': "'BMWGroup-Regular'",
             'font-size':'12px',
             'font-weight':500
         }
      },
  },
  plotOptions: {
      series:{
        fillOpacity:0,
          lineWidth: 3,
      marker: {
                enabled: false
            },
            
            
      },
    line: {
      dataLabels: {
        enabled: false
      },
      enableMouseTracking: true
    }
  },
  series: [{
    name: 'National Average MTD',
       color: '#3F78CF',
    data: [88.4, 90, 90.3, 90.5, 90.6, 91.2, 91.3, 91.6, 91.1, 91.4, 90.9, 90.3],
       
      marker : {symbol : 'circle',radius : 5,}
  }, {
    name: 'National Average YTD',
       color: '#83BCFF',
    data: [88.4, 92.4,91.5,91,91,93.8,92,93.3,86.8,92.5,85.7,84.2],
      marker : {symbol : 'circle',radius : 5 }
  }
          ]
});