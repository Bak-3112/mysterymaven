var month=$("input.question2monthdata").map(function() {
    return this.value;
}).get().join(",");

var yes=$("input.question2yes").map(function() {
    return this.value;
}).get().join(",");
var no=$('.question2no').val();
month = month.split(",").map(String);
yes = yes.split(",").map(Number);
no = no.split(",").map(Number);
var yesData = [];
for(var i=0;i<yes.length;i++){
	if(yes[i]!=0){
		yesData.push(yes[i]);
		}else{yesData.push(null);}
	}
var monthData=[];
for(var i=0;i<month.length;i++){ if(month[i]!=0){monthData.push(month[i]+" 2019" )  }}
Highcharts.chart('responsecontainer2', {
  chart: {
    type: 'column'
  },
     legend: {      
        enabled:true,
        align: 'center',
        verticalAlign: 'top',
        x: 0,
        y: -15,
        itemDistance: 7,
          itemStyle: {
            color: '#003366',
             fontSize:'13px',
            fontWeight: 400,
         fontFamily: "'BMWGroup-Regular'",
        }
          
    },
     credits: {
      enabled: false
  },
  title: {
    text: null
  },
  xAxis: {
   
    categories: monthData,
       tickLength: 0,
      lineColor: 'transparent',
      labels: {
         
         style: {
            color: '#003366',
            'font-family': "'BMWGroup-Regular'",
             'font-size':'13px',
             'font-weight':400,
         }
      },
  },
  yAxis: {
         reversedStacks: false,
    min: 0,
       gridLineColor: 'transparent',
        labels: {
            enabled: false,
        }, 
    title: {
      text: false
    }
  },
   
    
  tooltip: {
    pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.percentage:.0f}%)<br/>',
    shared: true
  },
  plotOptions: {
    column: {
      stacking: 'percent'
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
          verticalAlign:'middle',
          y:0,
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
  series: [{
    name: 'Yes',   
      "colorByPoint": false ,
        color: '#70A9FE',
    data: yesData
  }, ]
});