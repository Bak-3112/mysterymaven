
Highcharts.chart('ytdchart', {
  chart: {
    type: 'line',
       inverted: false
  },
      legend: {
          enabled:false,
        align: 'left',
        verticalAlign: 'top',
        x: 0,
        y: 0,
          
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
    categories: ['Jan 2018', 'Feb 2018', 'Mar 2018', 'Apr 2018','May 2018','June 2018','Jul 2018','Aug 2018'],
      labels: {
         
         style: {
            color: '#353e83',
           'font-family': "'bmwTypeWebLight'",
             'font-size':'12px',
             'font-weight':500
         }
      },
  },
  yAxis: {
      min:88,
        tickInterval: 2,
    title: {
      text: null
    },
       labels: {
          format: '{value}%',
         style: {
            color: '#353e83',
           'font-family': "'bmwTypeWebLight'",
             'font-size':'12px',
             'font-weight':500
         }
      },
  },
  plotOptions: {
      series:{
      marker: {
                enabled: false
            },},
    line: {
      dataLabels: {
        enabled: false
      },
      enableMouseTracking: false
    }
  },
  series: [{
    name: 'National Average MTD',
       color: '#99adc2',
    data: [88.2, 92.1, 91.5, 91,91,93.5,91.2,93.2],
       
      marker : {symbol : 'circle',radius : 5,}
  }, {
    name: 'National Average YTD',
       color: '#000000',
    data: [88.2, 90,90.2, 90.4,90.8,91,91,91.4],
      marker : {symbol : 'circle',radius : 5 }
  }
          ]
});