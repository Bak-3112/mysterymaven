Highcharts.chart('container-bar-graph', {
      chart: {
        type: 'column' },
      
      title: {
        align: 'center',
        text: '',
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
               y: 16,
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
        y: 0,
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
        name: 'No record found ',
          "colorByPoint": false ,
            color: '#3F78CF',
        data: [11, 21, 11, 11, 17, 21, 16, 14, 00, 18, 53, 57] },
      {
        name: 'Records Found',
          "colorByPoint": false ,
            color: '#70A9FE',
        data: [89, 79, 89, 89, 83, 79, 84, 86,100,82, 47, 43] },
      ] });
      //# sourceURL=pen.js


//Graph 2
Highcharts.chart('container-bar-graph', {
    chart: {
      type: 'column' },
    
    title: {
      align: 'center',
      text: '',
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
      categories: ['January', 'February', 'March', 'April', 'May','June','July','August','September','October','November','December'] },
    
    yAxis: {
       reversedStacks: false, 
             y: 16,
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
      y: 0,
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
      name: 'No record found ',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: [11, 21, 11, 11, 17, 21, 16, 14, 00, 18, 53, 57] },
    {
      name: 'Records Found',
        "colorByPoint": false ,
          color: '#70A9FE',
      data: [89, 79, 89, 89, 83, 79, 84, 86,100,82, 47, 43] },
    ] });


