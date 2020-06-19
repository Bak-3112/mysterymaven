Highcharts.chart('container-bar-graph-1', {
  chart: {
    type: 'column' },

  title: {
    align: 'center',
    text: null,
     style: {
            color: '#6c7070',
            fontWeight: '500',
         fontSize:'10px'
        }
  },        
 credits: {
      enabled: false
  },
  xAxis: {
      tickLength: 0,
    categories: ['January', 'February', 'March', 'April', 'May','June','July','August','September','October'] },

  yAxis: {
     reversedStacks: false, 
    min: 0,
    title: {
      text: '' },
       labels: {
          format: '{value}%',         
      },

    stackLabels: {
      enabled: false,
      style: {
        fontWeight: 'bold',
        color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },



  legend: {
    align: 'center',
    x: 0,
    verticalAlign: 'top',
    y:0,
    floating: false,
    backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
    // borderColor: '#CCC',
    // borderWidth: 1,
    shadow: false,
    itemStyle: {
            color: '#6c7070',
            fontWeight: 400,
         fontFamily: "'BMWGroup-Regular'",
        }
  },

  tooltip: {
    headerFormat: '<b>{point.x}</b><br/>',
    pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}' },

  plotOptions: {
    column: {
      stacking: 'percent',
      dataLabels: {
        enabled: true,
       } }, 
  series: {
      borderWidth: 0,
      //   pointWidth: 26,
         pointPadding: 0,
            groupPadding: 0.1,
            borderWidth: 0,
            shadow: false,
      dataLabels: {
          enabled: true,
          inside:true,
           rotation: 0,
         
        format: '{point.y}%',
          color:'#fff',
           style: {
                textOutline: 0,
                fontSize:'12px',
                fontFamily: "'BMWGroup-Regular'",
  },
      }
    }
  },

  series: [
  
  {
    name: 'Yes ',
      "colorByPoint": false ,
        color: '#3F78CF',
    data: [22, 29, 22, 11, 29, 13, 13, 0,5 ,3] },
  {
    name: 'No',
      "colorByPoint": false,
        color: '#70A9FE',
    data: [78, 71, 78, 89, 71,87, 87, 100, 95,97] },
  ] });