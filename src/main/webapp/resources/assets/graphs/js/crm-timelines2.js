
      window.onload = function() {
      
      var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        title: {
            text: ""
        },
        data: [{
            type: "pie",
            startAngle: 240,
            yValueFormatString: "##0\"%\"",
            indexLabel: "{label} {y}",
            dataPoints: [
                {y: 16, label: "More than 2 days",color:"#70A9FE",indexLabelFontFamily: "'BMWGroup-Regular'"},
                {y: 72, label: "No delay",color:"#99C8FF",indexLabelFontFamily: "'BMWGroup-Regular'"},          
                {y: 13, label: "Upto 2 days", color:"#3F78CF",indexLabelFontFamily: "'BMWGroup-Regular'"}
            ]
        }]
      });
      chart.render();
      
      }