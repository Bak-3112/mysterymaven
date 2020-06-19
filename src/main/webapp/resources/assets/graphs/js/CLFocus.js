Highcharts.chart('container-bar-graph-1', {
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
        name: 'No',
          "colorByPoint": false ,
            color: '#3F78CF',
        data: [11, 21, 11, 11, 17, 21, 16, 14, 00, 18, 25, 35] },
      {
        name: 'Yes',
          "colorByPoint": false ,
            color: '#70A9FE',
        data: [89, 79, 89, 89, 83, 79, 84, 86,100,82, 78, 65] },
      ] });
      //# sourceURL=pen.js container-bar-graph-2



Highcharts.chart('container-bar-graph-2', {
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
      name: 'No',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: [11, 11, 0, 6, 20, 5, 13, 0, 13, 5, 11, 11] },
    {
      name: 'Yes',
        "colorByPoint": false ,
          color: '#70A9FE',
      data: [89, 89, 100, 94, 80, 95, 87, 100,87,95, 89, 89] },
    ] });



Highcharts.chart('container-bar-graph-3', {
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
      name: 'No ',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: [0,0,3,3,0,3,5,0,8,3,14,3] },
    {
      name: 'Yes',
        "colorByPoint": false ,
          color: '#70A9FE',
      data: [100,100,97,97,100,97,95,100,9,97,86,97] },
    ] });

Highcharts.chart('container-bar-graph-4', {
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
      name: 'No ',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: [46,34,41,47,49,34,42,0,61,49,59,73] },
    {
      name: 'Yes',
        "colorByPoint": false ,
          color: '#70A9FE',
      data: [54,66,59,53,51,66,58,100,39,51,41,27] },
    ] });

Highcharts.chart('container-bar-graph-5', {
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
      name: 'No',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: [3,4,3,3,0,4,5,6,3,8,1,3] },
      {
          name: 'Within 48 Hours',
            "colorByPoint": false ,
              color: '#70A9FE',
          data:[89,95,89,89,94,92,95,89,76,87,81,62]  },
    {
      name: 'Within 5 Working Days',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: [8,1,11,11,6,8,5,3,18,2,19,32]},
    ] });

   