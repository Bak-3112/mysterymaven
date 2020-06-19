
// Create the chart
Highcharts.chart('column-bar-chart1', {
  chart: {
    type: 'column'
  },
    legend: {
        enabled:false,
        align: 'right',
        verticalAlign: 'top',
        x: 0,
        y: -25,
        itemDistance: 7,
           itemStyle: {
            color: '#989ea5',
             fontSize:'13px',
            fontWeight: 400,
         fontFamily: "'BMWGroup-Regular'",
        }
    },
     credits: {
      enabled: false
  },
  title: {
    text: '',
    align: 'left'
  },
  subtitle: {
    text: null
  },
  xAxis: {
     
   tickLength: 0,
    type: 'category',
      
      lineColor: 'transparent',
      labels: {
         
         style: {
           
            'font-family': "'BMWGroup-Regular'",
             'font-size':'11px',
             'font-weight':400,
             color:'#989ea5'
         }
      },
  },
  yAxis: {
       max: 100,
      gridLineColor: 'transparent',
     labels: {
            enabled: false,
        },  
    title: {
      text: null
    }

  },
 
  plotOptions: {
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
           rotation: 270,
          verticalAlign:'top',
          y:15,
        format: '{point.y}%',
          color:'#fff',
           style: {
                textOutline: 0,
                fontSize:'10px',
                fontFamily:  "'BMWGroup-Regular'",
  },
      }
    }
  },

  tooltip: {
    headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
  },

  "series": [
    {
      "name": "",
        "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 87,
            color:'#BB0A30'
        },
      
      ]
    },{
      "name": "",
       "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 89,
            color:'#009ADA'
        },
       
      ]
    },
      {
      "name": "",
      "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 80,
            color:'#989EA5'
        },
        
      ]
    },
      {
      "name": "",
      "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 84,
             color: '#000000',
        },
       
      ]
    }
  ],
 
}  )

Highcharts.chart('column-bar-chart2', {
  chart: {
    type: 'column'
  },
    legend: {
        enabled:false,
        align: 'right',
        verticalAlign: 'top',
        x: 0,
        y: -25,
        itemDistance: 7,
           itemStyle: {
            color: '#989ea5',
             fontSize:'13px',
            fontWeight: 400,
         fontFamily: "'BMWGroup-Regular'",
        }
    },
     credits: {
      enabled: false
  },
  title: {
    text: '',
    align: 'left'
  },
  subtitle: {
    text: null
  },
  xAxis: {
     
   tickLength: 0,
    type: 'category',
      
      lineColor: 'transparent',
      labels: {
         
         style: {
           
            'font-family': "'BMWGroup-Regular'",
             'font-size':'11px',
             'font-weight':400,
             color:'#989ea5'
         }
      },
  },
  yAxis: {
       max: 100,
      gridLineColor: 'transparent',
     labels: {
            enabled: false,
        },  
    title: {
      text: null
    }

  },
 
  plotOptions: {
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
           rotation: 270,
          verticalAlign:'top',
          y:15,
        format: '{point.y}%',
          color:'#fff',
           style: {
                textOutline: 0,
                fontSize:'10px',
                fontFamily:  "'BMWGroup-Regular'",
  },
      }
    }
  },

  tooltip: {
    headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
  },

  "series": [
    {
      "name": "",
        "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 73,
            color:'#BB0A30'
        },
      
      ]
    },{
      "name": "",
       "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 93,
            color:'#009ADA'
        },
       
      ]
    },
      {
      "name": "",
      "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 56,
            color:'#989EA5'
        },
        
      ]
    },
      {
      "name": "",
      "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 71,
             color: '#000000',
        },
       
      ]
    }
  ],
 
}  )

Highcharts.chart('column-bar-chart3', {
  chart: {
    type: 'column'
  },
    legend: {
        enabled:false,
        align: 'right',
        verticalAlign: 'top',
        x: 0,
        y: -25,
        itemDistance: 7,
           itemStyle: {
            color: '#989ea5',
             fontSize:'13px',
            fontWeight: 400,
         fontFamily: "'BMWGroup-Regular'",
        }
    },
     credits: {
      enabled: false
  },
  title: {
    text: '',
    align: 'left'
  },
  subtitle: {
    text: null
  },
  xAxis: {
     
   tickLength: 0,
    type: 'category',
      
      lineColor: 'transparent',
      labels: {
         
         style: {
           
            'font-family': "'BMWGroup-Regular'",
             'font-size':'11px',
             'font-weight':400,
             color:'#989ea5'
         }
      },
  },
  yAxis: {
       max: 100,
      gridLineColor: 'transparent',
     labels: {
            enabled: false,
        },  
    title: {
      text: null
    }

  },
 
  plotOptions: {
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
           rotation: 270,
          verticalAlign:'top',
          y:15,
        format: '{point.y}%',
          color:'#fff',
           style: {
                textOutline: 0,
                fontSize:'10px',
                fontFamily:  "'BMWGroup-Regular'",
  },
      }
    }
  },

  tooltip: {
    headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
  },

  "series": [
    {
      "name": "",
        "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 69,
            color:'#BB0A30'
        },
      
      ]
    },{
      "name": "",
       "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 90,
            color:'#009ADA'
        },
       
      ]
    },
      {
      "name": "",
      "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 61,
            color:'#989EA5'
        },
        
      ]
    },
      {
      "name": "",
      "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 76,
             color: '#000000',
        },
       
      ]
    }
  ],
 
}  )


Highcharts.chart('column-bar-chart4', {
  chart: {
    type: 'column'
  },
    legend: {
        enabled:false,
        align: 'right',
        verticalAlign: 'top',
        x: 0,
        y: -25,
        itemDistance: 7,
           itemStyle: {
            color: '#989ea5',
             fontSize:'13px',
            fontWeight: 400,
         fontFamily: "'BMWGroup-Regular'",
        }
    },
     credits: {
      enabled: false
  },
  title: {
    text: '',
    align: 'left'
  },
  subtitle: {
    text: null
  },
  xAxis: {
     
   tickLength: 0,
    type: 'category',
      
      lineColor: 'transparent',
      labels: {
         
         style: {
           
            'font-family': "'BMWGroup-Regular'",
             'font-size':'11px',
             'font-weight':400,
             color:'#989ea5'
         }
      },
  },
  yAxis: {
       max: 100,
      gridLineColor: 'transparent',
     labels: {
            enabled: false,
        },  
    title: {
      text: null
    }

  },
 
  plotOptions: {
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
           rotation: 270,
          verticalAlign:'top',
          y:15,
        format: '{point.y}%',
          color:'#fff',
           style: {
                textOutline: 0,
                fontSize:'10px',
                fontFamily:  "'BMWGroup-Regular'",
  },
      }
    }
  },

  tooltip: {
    headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
  },

  "series": [
    {
      "name": "",
        "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 89,
            color:'#BB0A30'
        },
      
      ]
    },{
      "name": "",
       "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 91,
            color:'#009ADA'
        },
       
      ]
    },
      {
      "name": "",
      "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 86,
            color:'#989EA5'
        },
        
      ]
    },
      {
      "name": "",
      "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 78,
             color: '#000000',
        },
       
      ]
    }
  ],
 
}  )


Highcharts.chart('column-bar-chart5', {
  chart: {
    type: 'column'
  },
    legend: {
        enabled:false,
        align: 'right',
        verticalAlign: 'top',
        x: 0,
        y: -25,
        itemDistance: 7,
           itemStyle: {
            color: '#989ea5',
             fontSize:'13px',
            fontWeight: 400,
         fontFamily: "'BMWGroup-Regular'",
        }
    },
     credits: {
      enabled: false
  },
  title: {
    text: '',
    align: 'left'
  },
  subtitle: {
    text: null
  },
  xAxis: {
     
   tickLength: 0,
    type: 'category',
      
      lineColor: 'transparent',
      labels: {
         
         style: {
           
            'font-family': "'BMWGroup-Regular'",
             'font-size':'11px',
             'font-weight':400,
             color:'#989ea5'
         }
      },
  },
  yAxis: {
       max: 100,
      gridLineColor: 'transparent',
     labels: {
            enabled: false,
        },  
    title: {
      text: null
    }

  },
 
  plotOptions: {
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
           rotation: 270,
          verticalAlign:'top',
          y:15,
        format: '{point.y}%',
          color:'#fff',
           style: {
                textOutline: 0,
                fontSize:'10px',
                fontFamily:  "'BMWGroup-Regular'",
  },
      }
    }
  },

  tooltip: {
    headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
  },

  "series": [
    {
      "name": "",
        "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 80,
            color:'#BB0A30'
        },
      
      ]
    },{
      "name": "",
       "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 86,
            color:'#009ADA'
        },
       
      ]
    },
      {
      "name": "",
      "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 46,
            color:'#989EA5'
        },
        
      ]
    },
      {
      "name": "",
      "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 70,
             color: '#000000',
        },
       
      ]
    }
  ],
 
}  )

Highcharts.chart('column-bar-chart6', {
  chart: {
    type: 'column'
  },
    legend: {
        enabled:false,
        align: 'right',
        verticalAlign: 'top',
        x: 0,
        y: -25,
        itemDistance: 7,
           itemStyle: {
            color: '#989ea5',
             fontSize:'13px',
            fontWeight: 400,
         fontFamily: "'BMWGroup-Regular'",
        }
    },
     credits: {
      enabled: false
  },
  title: {
    text: '',
    align: 'left'
  },
  subtitle: {
    text: null
  },
  xAxis: {
     
   tickLength: 0,
    type: 'category',
      
      lineColor: 'transparent',
      labels: {
         
         style: {
           
            'font-family': "'BMWGroup-Regular'",
             'font-size':'11px',
             'font-weight':400,
             color:'#989ea5'
         }
      },
  },
  yAxis: {
       max: 100,
      gridLineColor: 'transparent',
     labels: {
            enabled: false,
        },  
    title: {
      text: null
    }

  },
 
  plotOptions: {
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
           rotation: 270,
          verticalAlign:'top',
          y:15,
        format: '{point.y}%',
          color:'#fff',
           style: {
                textOutline: 0,
                fontSize:'10px',
                fontFamily:  "'BMWGroup-Regular'",
  },
      }
    }
  },

  tooltip: {
    headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
  },

  "series": [
    {
      "name": "",
        "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 80,
            color:'#BB0A30'
        },
      
      ]
    },{
      "name": "",
       "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 88,
            color:'#009ADA'
        },
       
      ]
    },
      {
      "name": "",
      "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 69,
            color:'#989EA5'
        },
        
      ]
    },
      {
      "name": "",
      "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": 77,
             color: '#000000',
        },
       
      ]
    }
  ],
 
}  );