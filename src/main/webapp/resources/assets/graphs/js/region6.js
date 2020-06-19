Highcharts.chart('region-6', {
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
    categories: ['Mr. A', 'Mr. B', 'Mr. C', 'Mr. D', 'Mr. E','Mr. F','Mr. G','Mr. H'] },

  yAxis: {
     reversedStacks: false, 
    min: 0,
    title: {
      text: '' },
       labels: {
           
          format: '{value}%', 
          enabled:false,   
         
      },

    stackLabels: {
      enabled: false,
      style: {
        fontWeight: 'bold',
        color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },



  legend: {
      enabled:false,
    align: 'left',
    x: 90,
    verticalAlign: 'top',
    y: -10,
    floating: true,
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
      stacking: 'normal',
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
    name: 'No record found ',
      "colorByPoint": false ,
        color: '#3F78CF',
    data: [63,75, 91, 67, 75,97, 68, 76] },
  
  ] });