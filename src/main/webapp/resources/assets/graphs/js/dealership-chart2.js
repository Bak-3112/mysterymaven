var month=$("input.question3monthdata").map(function() {
    return this.value;
}).get().join(",");

var one=$("input.question3one").map(function() {
    return this.value;
}).get().join(",");
var two=$("input.question3two").map(function() {
    return this.value;
}).get().join(",");
var three=$("input.question3three").map(function() {
    return this.value;
}).get().join(",");
var four=$("input.question3four").map(function() {
    return this.value;
}).get().join(",");
month = month.split(",").map(String);
one = one.split(",").map(Number);
two = two.split(",").map(Number);
three = three.split(",").map(Number);
four = four.split(",").map(Number);

var monthdata=[];
var onedata = [];
var twodata = [];
var threedata = [];
var fourdata = [];

/*for(var i=0;i<month.length;i++){
	
	if(one[i]!=0 || two[i]!=0 || three[i]!=0 || four[i]!=0){
	
		monthdata.push(month[i]);
		onedata.push(one[i]);
		twodata.push(two[i]);
		threedata.push(three[i]);
		fourdata.push(four[i]);
		
		}
	}*/

for(var i=0;i<month.length;i++){

	  if(one[i]!=0 || two[i]!=0 || three[i]!=0 || four[i]!=0){
	    if(month[i]!=0){
	    
	    monthdata.push(month[i]+" 2019");
	    }
	    if(one[i]!=0){
	    onedata.push(one[i]);
	    }else{ onedata.push(null);}
	    if(two[i]!=0){
	    twodata.push(two[i]);
	    }else{ twodata.push(null);}
	    if(three[i]!=0){
	    threedata.push(three[i]);
	    }else{ threedata.push(null);}
	    if(four[i]!=0){
	    fourdata.push(four[i]);
	    }else{ fourdata.push(null);}
	  
	    
	    }
	  }

Highcharts.chart('dealer', {
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
	  
	  min:0,
    categories: monthdata,
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
  series: [{   
    name: 'After 4 hours on the same working day',
      "colorByPoint": false ,
        color: '#3F78CF',
    data: onedata
  },
           {
    name: 'Within 2 hours after sending the request',
      "colorByPoint": false ,
        color: '#5094FC',
    data: threedata
  },
          {
    name: 'Within 4 hours after sending the request',
      "colorByPoint": false ,
        color: '#83BCFF',
    data: fourdata
  },          
  {
	    name: 'Not at all',
	      "colorByPoint": false ,
	        color: '#B0D5FF',
	    data: twodata
	  },]
});