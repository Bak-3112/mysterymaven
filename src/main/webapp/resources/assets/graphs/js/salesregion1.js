var names1=$("input.region1").map(function() {
    return this.value;
}).get().join(",");

names1 = names1.split(",").map(String);

var values1=$("input.region1value").map(function() {
    return this.value;
}).get().join(",");

values1 = values1.split(",").map(Number);

Highcharts.chart('region-1', {
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
    categories: names1 },

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
     }, 
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
    data: values1 },
  
  ] });

var names2=$("input.region2").map(function() {
    return this.value;
}).get().join(",");

names2 = names2.split(",").map(String);

var values2=$("input.region2value").map(function() {
    return this.value;
}).get().join(",");

values2 = values2.split(",").map(Number);

Highcharts.chart('region-2', {
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
      categories:   names2 },
  
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
      name: '',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: values2 },
    
    ] });

var names3=$("input.region3").map(function() {
    return this.value;
}).get().join(",");

names3 = names3.split(",").map(String);

var values3=$("input.region3value").map(function() {
    return this.value;
}).get().join(",");

values3 = values3.split(",").map(Number);


Highcharts.chart('region-3', {
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
      categories: names3 },
  
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
      data: values3 },
    
    ] });

     

var names4=$("input.region4").map(function() {
    return this.value;
}).get().join(",");

names4 = names4.split(",").map(String);

var values4=$("input.region4value").map(function() {
    return this.value;
}).get().join(",");

values4 = values4.split(",").map(Number);


Highcharts.chart('region-4', {
      chart: {
        type: 'column' },
    
      title: {
        align: 'center',
        text:null,
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
        categories: names4 },
    
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
        data: values4 },
      
      ] });

var names5=$("input.region5").map(function() {
    return this.value;
}).get().join(",");

names5 = names5.split(",").map(String);

var values5=$("input.region5value").map(function() {
    return this.value;
}).get().join(",");

values5 = values5.split(",").map(Number);


Highcharts.chart('region-5', {
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
    categories:names5 },

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
           rotation:0,
         
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
    data: values5 },
  
  ] });


var names6=$("input.region6").map(function() {
    return this.value;
}).get().join(",");

names6 = names6.split(",").map(String);

var values6=$("input.region6value").map(function() {
    return this.value;
}).get().join(",");

values6 = values6.split(",").map(Number);


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
    categories: names6 },

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
    data: values6 },
  
  ] });