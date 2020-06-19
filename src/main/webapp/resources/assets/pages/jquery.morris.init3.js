
/**
* Theme: Abstack - Bootstrap 4 Web App kit
* Author: Coderthemes
* Morris Chart
*/

!function($) {
    "use strict";

    var MorrisCharts = function() {};

    
    //creates line chart
    MorrisCharts.prototype.createLineChart = function(element, data, xkey, ykeys, labels, opacity, Pfillcolor, Pstockcolor, lineColors) {
        Morris.Line({
          element: element,
          data: data,
          xkey: xkey,
          ykeys: ykeys,
          labels: labels,
          fillOpacity: opacity,
          pointFillColors: Pfillcolor,
          pointStrokeColors: Pstockcolor,
          behaveLikeLine: true,
          gridLineColor: '#eef0f2',
          hideHover: 'auto',
          lineWidth: '3px',
          pointSize: 0,
          preUnits: '$',
          resize: true, //defaulted to true
          lineColors: lineColors
        });
    },
    
  //creates Bar chart
    MorrisCharts.prototype.createBarChart  = function(element, data, xkey, ykeys, labels, lineColors) {
        Morris.Bar({
            element: element,
            data: data,
            xkey: xkey,
            ykeys: ykeys,
            labels: labels,
            hideHover: 'auto',
            resize: true, //defaulted to true
            gridLineColor: '#eeeeee',
            barSizeRatio: 0.4,
            xLabelAngle: 35,
            barColors: lineColors
        });
    },
    
    MorrisCharts.prototype.init = function() {


       
        //create line chart
        var $data  = [
             { y: '2008', a: 50, b: 0 },
            { y: '2009', a: 75, b: 50 },
            { y: '2010', a: 30, b: 80 },
            { y: '2011', a: 50, b: 50 },
            { y: '2012', a: 75, b: 10 },
            { y: '2013', a: 50, b: 40 },
            { y: '2014', a: 75, b: 50 },
            { y: '2015', a: 100, b: 70 }
          ];
        this.createLineChart('morris-line-example', $data, 'y', ['a', 'b'], ['Series A', 'Series B'],['0.1'],['#ffffff'],['#999999'], ['#9368f3 ', '#4fbde9']);
      
        //creating bar chart
        var $barData  = [
            { y: '1', a: 100, b: 90 , c: 40, d:50 },
            { y: '2', a: 75,  b: 65 , c: 20, d:50 },
            { y: '3', a: 50,  b: 40 , c: 50, d:50 },
            { y: '4', a: 75,  b: 65 , c: 95, d:50 },
            { y: '5', a: 50,  b: 40 , c: 22, d:50 },
            { y: '6', a: 75,  b: 65 , c: 56, d:50 }
        ];
        this.createBarChart('morris-bar-example', $barData, 'y', ['a', 'b', 'c','d'], ['', '', '',''], ['#9368f3','#3ec396', '#ebeff2', '#4fbde9']);


    },
    //init
    $.MorrisCharts = new MorrisCharts, $.MorrisCharts.Constructor = MorrisCharts
}(window.jQuery),

//initializing 
function($) {
    "use strict";
    $.MorrisCharts.init();
}(window.jQuery);