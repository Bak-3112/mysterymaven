window.onload = function() {
var nodelay=$('#nodelay').val();
var more=$('#more').val();
var upto=$('#upto').val();
var chart = new CanvasJS.Chart("chartContainer", {
    animationEnabled: true,
    title: {
        text: ""
    },
    data: [{
        type: "pie",
        startAngle: 240,
        yValueFormatString: "##0.00\"%\"",
        indexLabel: "{label} {y}",
        dataPoints: [
            {y: upto, label: "Upto 2 days"},
            {y: more, label: "More than 2 days"},          
            {y: nodelay, label: "No delay"}
        ]
    }]
});
chart.render();

}