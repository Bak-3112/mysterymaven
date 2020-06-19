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
          labels:{
               style:{
                    fontFamily: "'BMWGroup-Regular'",
               }
          },
        categories: ['Dummy1', 'Dummy2', 'Dummy3', 'Dummy4', 'Dummy5','Dummy6','Dummy7','Dummy8','Dummy9','Dummy10','Dummy11','Dummy12','Dummy13','Dummy14','Dummy15','Dummy16','Dummy17','Dummy18','Dummy19','Dummy20','Dummy1', 'Dummy2', 'Dummy3', 'Dummy4', 'Dummy5','Dummy6','Dummy7','Dummy7','Dummy8','Dummy9','Dummy10','Dummy11','Dummy12','Dummy13','Dummy14'] },
      
      yAxis: {
         reversedStacks: false, 
               y: 16,
        min: 0,
        title: {
          text: '' },
           labels: {
              format: '{value}%',
               style:{
                    'font-family': "'BMWGroup-Regular'",
               }
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
             
           // format: '{point.y}%',
           format: '',
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
        data: [11, 21, 11, 11, 17, 21, 16, 14, 00, 18, 53, 57,55,66,67,68,30,40,50,60,70,90,80,70,60,50,40,30,50,60,20,10,65,70,80] },
      {
        name: 'No',
          "colorByPoint": false ,
            color: '#70A9FE',
        data: [89, 79, 89, 89, 83, 79, 84, 86,100,82, 47, 43,45,44.33,32,70,60,50,40,30,10,20,30,40,50,60,70,50,40,80,90,45,30,20] },
      ] });
      //# sourceURL=pen.js


   /* life stlye analysis */
   Highcharts.chart('container-bar-graph-lifestyle', {
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
        labels:{
              style:{
                    fontFamily: "'BMWGroup-Regular'",
               }
        },
      categories: ['Dummy1', 'Dummy2', 'Dummy3', 'Dummy4', 'Dummy5','Dummy6','Dummy7','Dummy8','Dummy9','Dummy10','Dummy11','Dummy12','Dummy13','Dummy14','Dummy15','Dummy16','Dummy17','Dummy18','Dummy19','Dummy20','Dummy1', 'Dummy2', 'Dummy3', 'Dummy4', 'Dummy5','Dummy6','Dummy7','Dummy7','Dummy8','Dummy9','Dummy10','Dummy11','Dummy12','Dummy13','Dummy14'] },
    
    yAxis: {
       reversedStacks: false, 
             y: 16,
      min: 0,
      title: {
        text: '' },
         labels: {
            format: '{value}%',       
               style:{
                    fontFamily: "'BMWGroup-Regular'",
               }
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
           
         // format: '{point.y}%',
         format: '',
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
      data: [11, 21, 11, 11, 17, 21, 16, 14, 00, 18, 53, 57,55,66,67,68,30,40,50,60,70,90,80,70,60,50,40,30,50,60,20,10,65,70,80] },
    {
      name: 'No',
        "colorByPoint": false ,
          color: '#70A9FE',
      data: [89, 79, 89, 89, 83, 79, 84, 86,100,82, 47, 43,45,44.33,32,70,60,50,40,30,10,20,30,40,50,60,70,50,40,80,90,45,30,20] },
    ] });