var month=$('#monthdata').val();
var yes=$('#yes').val();
var no=$('#no').val();
month = month.split(",").map(String);
yes = yes.split(",").map(Number);
no = no.split(",").map(Number);
var yesData=[];
var noData=[];
var monthData=[];
for(var i=0;i<yes.length;i++){ if(yes[i]!=0){yesData.push(yes[i])}  else{yesData.push(null)}}
for(var i=0;i<no.length;i++){ if(no[i]!=0){noData.push(no[i])  }else{noData.push(null)}}
for(var i=0;i<month.length;i++){ if(month[i]!=0){monthData.push(month[i]+" 2019")  }}
Highcharts.chart('crm-container-bar-graph', {
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
        categories: monthData },
      
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
    	      data: noData },
    	    {
    	      name: 'Records Found',
    	        "colorByPoint": false ,
    	          color: '#70A9FE',
    	      data: yesData},
    	    ] });
      //# sourceURL=pen.js




