var month=$('#month').val();
var yes=$('#yes').val();
var no=$('#no').val();
month = month.split(",").map(String);
yes = yes.split(",").map(Number);
no = no.split(",").map(Number);
Highcharts.chart('container', {
  chart: {
    type: 'column' },

  title: {
    align: 'left',
    text: 'CRM COMPLIANCE',
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
    categories:month },

  yAxis: {
     reversedStacks: false, 
    min: 0,
    title: {
      text: '' },
       labels: {
          format: '{value}%',         
      },

    stackLabels: {
      enabled: true,
      style: {
        fontWeight: 'bold',
        color: Highcharts.theme && Highcharts.theme.textColor || 'gray' } } },



  legend: {
    align: 'left',
    x: 90,
    verticalAlign: 'top',
    y: -10,
    floating: true,
    backgroundColor: Highcharts.theme && Highcharts.theme.background2 || 'white',
    // borderColor: '#CCC',
    // borderWidth: 1,
    shadow: false },

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
                fontFamily: '"Quattrocento Sans", sans-serif',
  },
      }
    }
  },

  series: [
  
  {
    name: 'No record found ',
      "colorByPoint": false ,
        color: '#003366',
    data: no },
  {
    name: 'Records Found',
      "colorByPoint": false ,
        color: '#a8c5e0',
    data: yes },
  ] });
//# sourceURL=pen.js