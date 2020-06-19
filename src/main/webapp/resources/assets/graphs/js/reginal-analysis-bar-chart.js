var names1=$("input#regionbrand1").map(function() {
    return this.value;
}).get().join(",");

names1 = names1.split(",").map(String);



var values1=$("input#regionper1").map(function() {
    return this.value;
}).get().join(",");

values1 = values1.split(",").map(Number);
var dataarray = []
var colorsaray= ['#009ADA','#BB0A30','#000000','#BB0A30']
values1.forEach(function(item,index){
	
	dataarray.push( {
      "name": "",
        "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": parseInt(item),
            color:colorsaray[index]
        },
      
      ]
    })
    
})


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
            color: '#FF0000',
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
             color:'#FF0000'
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
          y:30,
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

  "series":dataarray,
 
}  )



var names2=$("input#regionbrand2").map(function() {
    return this.value;
}).get().join(",");

names2 = names2.split(",").map(String);



var values2=$("input#regionper2").map(function() {
    return this.value;
}).get().join(",");

values2 = values2.split(",").map(Number);
var dataarray1 = []
var colorsaray= ['#009ADA','#BB0A30','#000000','#BB0A30']
values2.forEach(function(item,index){
	
	dataarray1.push( {
      "name": "",
        "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": parseInt(item),
            color:colorsaray[index]
        },
      
      ]
    })
    
})


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
            color: '#FF0000',
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
             color:'#FF0000'
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
          y:30,
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

  "series": dataarray1
 
}  )

var names3=$("input#regionbrand3").map(function() {
    return this.value;
}).get().join(",");

names3 = names3.split(",").map(String);



var values3=$("input#regionper3").map(function() {
    return this.value;
}).get().join(",");

values3 = values3.split(",").map(Number);
var dataarray2 = []
var colorsaray= ['#009ADA','#BB0A30','#000000','#BB0A30']
values3.forEach(function(item,index){
	
	dataarray2.push( {
      "name": "",
        "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": parseInt(item),
            color:colorsaray[index]
        },
      
      ]
    })
    
})


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
            color: '#FF0000',
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
             color:'#FF0000'
         }
      },
  },
  yAxis: {
       max: 101,
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
          y:30,
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

  "series": dataarray2
 
}  )

var names4=$("input#regionbrand4").map(function() {
    return this.value;
}).get().join(",");

names4 = names4.split(",").map(String);



var values4=$("input#regionper4").map(function() {
    return this.value;
}).get().join(",");

values4 = values4.split(",").map(Number);
var dataarray4 = []
var colorsaray= ['#009ADA','#BB0A30','#000000','#BB0A30']
values4.forEach(function(item,index){
	
	dataarray4.push( {
      "name": "",
        "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": parseInt(item),
            color:colorsaray[index]
        },
      
      ]
    })
    
})

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
            color: '#FF0000',
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
             color:'#FF0000'
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
          y:30,
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

  "series": dataarray4
 
}  )


var names5=$("input#regionbrand5").map(function() {
    return this.value;
}).get().join(",");

names5 = names5.split(",").map(String);



var values5=$("input#regionper5").map(function() {
    return this.value;
}).get().join(",");

values5 = values5.split(",").map(Number);
var dataarray5 = []
var colorsaray= ['#009ADA','#BB0A30','#000000','#BB0A30']
values5.forEach(function(item,index){
	
	dataarray5.push( {
      "name": "",
        "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": parseInt(item),
            color:colorsaray[index]
        },
      
      ]
    })
    
})

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
            color: '#FF0000',
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
             color:'#FF0000'
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
          y:30,
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

  "series": dataarray5
 
}  )


var names6=$("input#regionbrand6").map(function() {
    return this.value;
}).get().join(",");

names6 = names6.split(",").map(String);



var values6=$("input#regionper6").map(function() {
    return this.value;
}).get().join(",");

values6 = values6.split(",").map(Number);
var dataarray6 = []
var colorsaray= ['#009ADA','#BB0A30','#000000','#BB0A30']
values6.forEach(function(item,index){
	
	dataarray6.push( {
      "name": "",
        "colorByPoint": false ,
        color: '#fff',
      "data": [
        {
          "name": "",
          "y": parseInt(item),
            color:colorsaray[index]
        },
      
      ]
    })
    
})

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
            color: '#FF0000',
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
             color:'#FF0000'
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
          y:30,
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

  "series": dataarray6
 
}  );