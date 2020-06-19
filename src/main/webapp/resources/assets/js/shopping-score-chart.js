
Highcharts.chart('graph-container', {
  chart: {
    type: 'line'
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
    tickLength: 0,
    lineColor: 'transparent',
    categories: [ 'Q1', 'Q2','Q3','Q4'],
      labels: {
         
         style: {
           
            'font-family': "'BMWGroup-Regular'",
             'font-size':'11px',
             'font-weight':400,
         }
      },
  },
  yAxis: {
       
    title: {
      text: null
    },
      labels: {
         format: '{value}%',
         style: {
           
            'font-family': "'BMWGroup-Regular'",
             'font-size':'11px',
             'font-weight':400,
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
      enableMouseTracking: true
    }
  },
  series: [{
    name: 'Audi',
       color: '#BB0A30',
    data: [85, 82,75,75.5],
       
      marker : {symbol : 'circle',radius : 5,}
  }, {
    name: 'BMW',
       color: '#009ADA',
    data: [90.5,90.5,90.5,87],
      marker : {symbol : 'circle',radius : 5 }
  },{
    name: 'JLR',
       color: '#989EA5',
    data: [70, 66],
      marker : {symbol : 'circle',radius : 5 }
  },{
    name: 'Mercedes',
       color: '#000000',
    data: [79, 75,81,73.17],
      marker : {symbol : 'circle',radius : 5 }
  }
          ]
});