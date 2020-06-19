








var month1=$("input.focusquestion1monthdata").map(function() {
    return this.value;
}).get().join(",");
var yes1=$("input.focusquestion1yes").map(function() {
    return this.value;
}).get().join(",");
var no1=$("input.focusquestion1no").map(function() {
    return this.value;
}).get().join(",");

month1 = month1.split(",").map(String).map(function(item){
	  return item + " 2019"
	});
yes1 = yes1.split(",").map(Number);
no1 = no1.split(",").map(Number);

var yesData1=[];
var noData1=[];

for(var i=0;i<yes1.length;i++){ if(yes1[i]!=0){yesData1.push(yes1[i])}  else{yesData1.push(null)}}
for(var i=0;i<no1.length;i++){ if(no1[i]!=0){noData1.push(no1[i])  }else{noData1.push(null)}}

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
        categories: month1 },
      
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
    	        name: 'Yes',
    	          "colorByPoint": false ,
    	            color: '#70A9FE',
    	        data:yesData1 },
      {
        name: 'No',
          "colorByPoint": false ,
            color: '#3F78CF',
        data: noData1 },
     
      ] });
      //# sourceURL=pen.js container-bar-graph-2

var month2=$("input.focusquestion2monthdata").map(function() {
    return this.value;
}).get().join(",");

var yes2=$("input.focusquestion2yes").map(function() {
    return this.value;
}).get().join(",");
var no2=$("input.focusquestion2no").map(function() {
    return this.value;
}).get().join(",");


month2 = month2.split(",").map(String).map(function(item){
	  return item + " 2019"
	});
yes2 = yes2.split(",").map(Number);
no2 = no2.split(",").map(Number);


var yesData2=[];
var noData2=[];

for(var i=0;i<yes2.length;i++){ if(yes2[i]!=0){yesData2.push(yes2[i])}  else{yesData2.push(null)}}
for(var i=0;i<no2.length;i++){ if(no2[i]!=0){noData2.push(no2[i])  }else{noData2.push(null)}}


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
      categories: month2 },
    
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
    	      name: 'Yes',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data: yesData2},
    {
      name: 'No',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: noData2},
   
    ] });

var month3=$("input.focusquestion3monthdata").map(function() {
    return this.value;
}).get().join(",");

var yes3=$("input.focusquestion3yes").map(function() {
    return this.value;
}).get().join(",");
var no3=$("input.focusquestion3no").map(function() {
    return this.value;
}).get().join(",");

yes3 = yes3.split(",").map(Number);
no3 = no3.split(",").map(Number);

var yesData3=[];
var noData3=[];

for(var i=0;i<yes3.length;i++){ if(yes3[i]!=0){yesData3.push(yes3[i])} else{yesData3.push(null)} }
for(var i=0;i<no3.length;i++){ if(no3[i]!=0){noData3.push(no3[i])  }else{noData3.push(null)}}

month3 = month3.split(",").map(String).map(function(item){
	  return item + " 2019"
	});

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
      categories: month3 },
    
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
    	      name: 'Yes',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data: yesData3},
    {
      name: 'No ',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: noData3},
  
    ] });



var month4=$("input.focusquestion4monthdata").map(function() {
    return this.value;
}).get().join(",");
var yes41=$("input.focusquestion4yes1").map(function() {
    return this.value;
}).get().join(",");

var yes42=$("input.focusquestion4yes2").map(function() {
    return this.value;
}).get().join(",");
var yes43=$("input.focusquestion4yes3").map(function() {
    return this.value;
}).get().join(",");
var no4=$("input.focusquestion4no").map(function() {
    return this.value;
}).get().join(",");
yes41 = yes41.split(",").map(Number);
yes42 = yes42.split(",").map(Number);
yes43 = yes43.split(",").map(Number);
no4 = no4.split(",").map(Number);

var yesData41=[];

var yesData42=[];
var yesData43=[];
var noData4=[];

for(var i=0;i<yes41.length;i++){ if(yes41[i]!=0){yesData41.push(yes41[i])} else{yesData41.push(null)} }
for(var i=0;i<yes42.length;i++){ if(yes42[i]!=0){yesData42.push(yes42[i])}  else{yesData42.push(null)} }
for(var i=0;i<yes43.length;i++){ if(yes43[i]!=0){yesData43.push(yes43[i])} else{yesData43.push(null)}  }
for(var i=0;i<no4.length;i++){ if(no4[i]!=0){noData4.push(no4[i])  }else{noData4.push(null)} }

month4 = month4.split(",").map(String).map(function(item){
	  return item + " 2019"
	});

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
      categories: month4 },
    
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
            
          format: '{point.y}',

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
    	      name: 'Yes, handed over during the consultation on official paper',
    	        "colorByPoint": false ,
    	          color: '#78a0dd',
    	      data: yesData41},
    {
      name: 'Yes, on official paper sent per e-mail during/right after the consultation',
        "colorByPoint": false ,
          color: '#6593d8',
      data: yesData42},
      {
          name: 'Yes, I got an written offer but not on official paper',
            "colorByPoint": false ,
              color: '#5285d3',
          data: yesData43},
          {
              name: 'No, I did not receive an offer at all',
                "colorByPoint": false ,
                  color: '#3f78cf',
              data: noData4},
    
    ] });

var month3=$("input.focusquestion3monthdata").map(function() {
    return this.value;
}).get().join(",");

var yes3=$("input.focusquestion3yes").map(function() {
    return this.value;
}).get().join(",");
var no3=$("input.focusquestion3no").map(function() {
    return this.value;
}).get().join(",");

yes3 = yes3.split(",").map(Number);
no3 = no3.split(",").map(Number);

var yesData3=[];
var noData3=[];

for(var i=0;i<yes3.length;i++){ if(yes3[i]!=0){yesData3.push(yes3[i])} else{yesData3.push(null)}}
for(var i=0;i<no3.length;i++){ if(no3[i]!=0){noData3.push(no3[i])  } else{noData3.push(null)}}
console.log(yesData3);
console.log(noData3)
month3 = month3.split(",").map(String).map(function(item){
	  return item + " 2019"
	});

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
      categories: month3 },
    
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
    	      name: 'Yes',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data: yesData3},
    {
      name: 'No ',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: noData3},
  
    ] });



/*var month5=$("input.question5monthdata").map(function() {
    return this.value;
}).get().join(",");
var one=$("input.question5one").map(function() {
    return this.value;
}).get().join(",");
var two=$("input.question5two").map(function() {
    return this.value;
}).get().join(",");
var three=$("input.question5three").map(function() {
    return this.value;
}).get().join(",");
month5 = month5.split(",").map(String);
one = one.split(",").map(Number);
two = two.split(",").map(Number);
three = three.split(",").map(Number);

var one1=[];
var two2=[];
var three3=[];
for(var i=0;i<one.length;i++){ if(one[i]!=0){one1.push(one[i])}  }
for(var i=0;i<two.length;i++){ if(two[i]!=0){two2.push(two[i])  }}
for(var i=0;i<three.length;i++){ if(three[i]!=0){three3.push(three[i])  }}

month5 = month5.split(",").map(String).map(function(item){
	  return item + " 2019"
	});*/

var month5=$("input.focusquestion5monthdata").map(function() {
    return this.value;
}).get().join(",");

var yes5=$("input.focusquestion5yes").map(function() {
    return this.value;
}).get().join(",");
var no5=$("input.focusquestion5no").map(function() {
    return this.value;
}).get().join(",");

yes5 = yes5.split(",").map(Number);
no5 = no5.split(",").map(Number);

var yesData5=[];
var noData5=[];

for(var i=0;i<yes5.length;i++){ if(yes5[i]!=0){yesData5.push(yes5[i])}  else{yesData5.push(null)}}
for(var i=0;i<no5.length;i++){ if(no5[i]!=0){noData5.push(no5[i])  }else{noData5.push(null)}}

month5 = month5.split(",").map(String).map(function(item){
	  return item + " 2019"
	});
/*Highcharts.chart('container-bar-graph-5', {
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
      categories: month5 },
    
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
        enabled: true,
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
      data: three3 },
      {
          name: 'Within 48 Hours',
            "colorByPoint": false ,
              color: '#70A9FE',
          data:one1  },
    {
      name: 'Within 5 Working Days',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: two2},
    ] 
    
    series: [
  	  {
  	      name: 'Yes',
  	        "colorByPoint": false ,
  	          color: '#70A9FE',
  	      data: yesData5},
  {
    name: 'No ',
      "colorByPoint": false ,
        color: '#3F78CF',
    data: noData5},

  ]


});*/

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
      categories: month5 },
    
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
    	      name: 'Yes',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data: yesData5},
    {
      name: 'No ',
        "colorByPoint": false ,
          color: '#3F78CF',
      data: noData5},
  
    ] });


   