var month=$("input.question4monthdata").map(function() {
    return this.value;
}).get().join(",");

var yes=$("input.question4yes").map(function() {
    return this.value;
}).get().join(",");
var no=$("input.question4no").map(function() {
    return this.value;
}).get().join(",");
month = month.split(",").map(String).map(function(item){
	return item + " 2019"
});
yes = yes.split(",").map(Number);
no = no.split(",").map(Number);

var yesdata4=[];
var nodata4=[];

for(var i=0;i<yes.length;i++){ if(yes[i]!=0){yesdata4.push(yes[i])} else{yesdata4.push(null)} }
for(var i=0;i<no.length;i++){ if(no[i]!=0){nodata4.push(no[i])}   else{nodata4.push(null)}}

/*var monthdata=[];
var yesdata4=[];
var nodata4=[];



for(var i=0;i<yesData.length;i++){

	if(yesData[i]!=0 || noData[i]!=0 ){
	
		monthdata.push(month[i]);
		yesdata4.push(yesData[i]);
		nodata4.push(noData[i]);
		
		
		}
	}*/

Highcharts.chart('appointment', {
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
    categories: monthdata,
       tickLength: 0,
      lineColor: 'transparent',
      labels: {
         
         style: {
            color: '#003366',
            'font-family': "'BMWGroup-Regular'",
             'font-size':'13px',
           //  'font-weight':400,
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
      text: null
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
  series: [ {
	    name: 'Yes',
	      "colorByPoint": false ,
	        color: '#70A9FE',
	    data: yesdata4
	  },{
    name: 'No',
      "colorByPoint": false ,
        color: '#3F78CF',
    data: nodata4
  },
           
 
          ]
});