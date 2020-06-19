if (/Mobi/.test(navigator.userAgent)) {
  // if mobile device, use native pickers
  $(".date-time input").attr("type", "datetime-local");
  $(".date input").attr("type", "date");
  $(".time input").attr("type", "time");
} else {
  // if desktop device, use DateTimePicker
  $("#datetimepicker").datetimepicker({
    icons: {
      time: "fa fa-clock-o",
      date: "fa fa-calendar",
      up: "fa fa-chevron-up",
      down: "fa fa-chevron-down",
      next: "fa fa-chevron-right",
      previous: "fa fa-chevron-left" } });


  $(".datepicker").datetimepicker({
    useCurrent: false,
  //  format: "L",
    format: "YYYY-MM-DD",
    icons: {
      next: "fa fa-chevron-right",
      previous: "fa fa-chevron-left" } }).on('dp.change', function(e) {
       $('#quizForm').formValidation('revalidateField', 'date');

    });;;


  var tp= $(".timepicker1").datetimepicker({
    format: "LT",
    icons: {
      up: "fa fa-chevron-up",
      down: "fa fa-chevron-down" },
     
  });
 

  $("#timepicker2").datetimepicker({
    format: "LT",
    icons: {
      up: "fa fa-chevron-up",
      down: "fa fa-chevron-down" } });


}
//# sourceURL=pen.js