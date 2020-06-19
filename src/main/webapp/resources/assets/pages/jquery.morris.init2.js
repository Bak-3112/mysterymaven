
/**
* Theme: Abstack - Bootstrap 4 Web App kit
* Author: Coderthemes
* Morris Chart
*/

!function($) {
    "use strict";

    var MorrisCharts = function() {};

    //creates Stacked chart
    MorrisCharts.prototype.createStackedChart  = function(element, data, xkey, ykeys, labels, lineColors) {
        Morris.Bar({
            element: element,
            data: data,
            xkey: xkey,
            ykeys: ykeys,
            stacked: true,
            labels: labels,
            hideHover: 'auto',
            resize: true, //defaulted to true
            gridLineColor: '#eeeeee',
            barColors: lineColors
        });
    },

    
    MorrisCharts.prototype.init = function() {


        //creating Stacked chart
        var $stckedData  = [
        	{ y: 'Jan', a: 22 , b:78},
            { y: 'Feb', a: 29 , b:71 },
            { y: 'Mar', a: 22 , b:78},
            { y: 'Apr', a: 29 , b:71},
            { y: 'May', a: 29 , b:71 },
            { y: 'Jun', a: 22 , b:78},
            { y: 'Jul', a: 29 , b:71},
            { y: 'Aug', a: 22 , b:78 }
        ];
        this.createStackedChart('morris-bar-stacked', $stckedData,'y', ['a','b'], ['Percantage','Percantage'], ['#3c86d8','#d8c53c']);
        
        var $stckedData2  = [
        	{ y: 'Jan', a: 22 , b:78},
            { y: 'Feb', a: 29 , b:71 },
            { y: 'Mar', a: 22 , b:78},
            { y: 'Apr', a: 29 , b:71},
            { y: 'May', a: 29 , b:71 },
            { y: 'Jun', a: 22 , b:78},
            { y: 'Jul', a: 29 , b:71},
            { y: 'Aug', a: 22 , b:78 }
        ];
        this.createStackedChart('morris-bar-stacked2', $stckedData2,'y',  ['a','b'], ['Percantage','Percantage'], ['#3c86d8','#d8c53c']);
        
        var $stckedData1  = [
        	{ y: 'Jan', a: 22 , b:78},
            { y: 'Feb', a: 29 , b:71 },
            { y: 'Mar', a: 22 , b:78},
            { y: 'Apr', a: 29 , b:71},
            { y: 'May', a: 29 , b:71 },
            { y: 'Jun', a: 22 , b:78},
            { y: 'Jul', a: 29 , b:71},
            { y: 'Aug', a: 22 , b:78 }
        ];
        this.createStackedChart('morris-bar-stacked1', $stckedData1, 'y', ['a','b'], ['Percantage','Percantage'], ['#3c86d8','#d8c53c']);
        
        var $stckedData3  = [
        	{ y: 'Jan', a: 22 , b:78},
            { y: 'Feb', a: 29 , b:71 },
            { y: 'Mar', a: 22 , b:78},
            { y: 'Apr', a: 29 , b:71},
            { y: 'May', a: 29 , b:71 },
            { y: 'Jun', a: 22 , b:78},
            { y: 'Jul', a: 29 , b:71},
            { y: 'Aug', a: 22 , b:78 }
        ];
        this.createStackedChart('morris-bar-stacked3', $stckedData3, 'y', ['a','b'], ['Percantage','Percantage'], ['#3c86d8','#d8c53c']);

        var $stckedData4  = [
        	{ y: 'Jan', a: 22 , b:78},
            { y: 'Feb', a: 29 , b:71 },
            { y: 'Mar', a: 22 , b:78},
            { y: 'Apr', a: 29 , b:71},
            { y: 'May', a: 29 , b:71 },
            { y: 'Jun', a: 22 , b:78},
            { y: 'Jul', a: 29 , b:71},
            { y: 'Aug', a: 22 , b:78 }
        ];
        this.createStackedChart('morris-bar-stacked4', $stckedData4, 'y', ['a','b'], ['Percantage','Percantage'], ['#3c86d8','#d8c53c']);
    },
    //init
    $.MorrisCharts = new MorrisCharts, $.MorrisCharts.Constructor = MorrisCharts
}(window.jQuery),

//initializing 
function($) {
    "use strict";
    $.MorrisCharts.init();
}(window.jQuery);