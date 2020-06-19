 Highcharts.chart('container-bar-graph-4', {
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
    data: [46,34,41,47,49,34,42,50,61,49] },
  {
    name: 'No',
      "colorByPoint": false ,
        color: '#70A9FE',
    data: [54,66,59,53,51,66,58,50,39,51] },
  ] });