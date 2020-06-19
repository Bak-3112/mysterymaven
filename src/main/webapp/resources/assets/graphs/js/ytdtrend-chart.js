
var months="";
$('.monthytdmtd').each(function(e){
	months = months+ $(this).val()+","
})
months = months.substr(0,months.length-1);

months=months.split(",");
var mtd="";
$('.mtd').each(function(e){
	
	if($(this).val()!=0){
	mtd = mtd+ $(this).val()+",";
}else{mtd = mtd+ null+",";}
	//alert(mtd);
})
mtd = mtd.substr(0,mtd.length-1);

mtd=mtd.split(",").map(Number);

var ytd = '';
$('.ytd').each(function(e){
	if($(this).val()!=0){
	ytd = ytd+ $(this).val()+",";
}else{ytd = ytd+null+",";}
})
ytd = ytd.substr(0,ytd.length-1)



ytd=ytd.split(",").map(Number);
Highcharts.chart('ytdchart', {
  chart: {
   
       inverted: false
  },
      legend: {
        align: 'center',
        verticalAlign: 'top',
        x: 0,
        y: 0,
        symbolHeight: 12,
        symbolWidth: 12,
        symbolRadius: 6, 
        itemStyle: {
            color: '#6c7070',
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
  subtitle: {
    text: null
  },
  xAxis: {
       tickInterval: 1,
    tickLength: 0,
    lineColor: 'transparent',
    categories: months,
      labels: {         
         style: {
            color: '#000000',
           'font-family': "'BMWGroup-Regular'",
             'font-size':'12px',
             'font-weight':500
         }
      },
  },
  yAxis: {
      max:100,
        tickInterval: 20,
    title: {
      text: null
    },
       labels: {
          format: '{value}%',
         style: {
            color: '#000000',
           'font-family': "'BMWGroup-Regular'",
             'font-size':'12px',
             'font-weight':400
         }
      },
  },
  plotOptions: {
	 
      // series:{
      //   //fillOpacity:0,
      //     lineWidth: 3,
      // marker: {
      //           enabled: false
      //       },},
    line: {
      dataLabels: {
    	  format: '{y} % ',
        enabled: true,
        crop:false,
        allowOverlap: true
      },
      enableMouseTracking: true
    }
  },
  series: [{
  
    name: 'National Average MTD',
       color: '#3F78CF',
    data: mtd,
       
      marker : {symbol : 'circle',radius : 5,}
  }, {
    
    name: 'National Average YTD',
       color: '#83BCFF',
    data: ytd,
      marker : {symbol : 'circle',radius : 5 }
  }
          ]
});