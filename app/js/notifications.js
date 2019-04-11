
function scheduleNotification(hour, min) {
  if(Android) {
    Android.scheduleNotification(hour, min);
  }
}

$('#alarmSwitch').click(function(event) {
  document.getElementById("alarmTime").value = null;
  localStorage.setItem("alarmTime", null);

  let alarmSwitch = event.target.checked; // alarmSwitch true or false

  if(alarmSwitch == true) {
    document.getElementById("alarmTime").disabled = false;

    $("input[type=time]").on("change", function() { // get hour and min
      let timeValue = document.getElementById("alarmTime").value;
      let hour = timeValue.slice(0,2);
      let minute = timeValue.slice(3,5);

      if(hour && minute) {
        console.log("here");
        localStorage.setItem("alarmSwitch", alarmSwitch);
        localStorage.setItem("alarmTime", hour+":"+minute);
        scheduleNotification(hour, minute);
      }
    })

  } else {
    document.getElementById("alarmTime").disabled = true;
    scheduleNotification(null);
  }
})


$( document ).ready(function() {

  console.log(localStorage.getItem("alarmTime"));

  $('#alarmSwitch').prop('checked', getBooleanItem("alarmSwitch", false));
  $('#alarmTime').val(getBooleanItem("alarmTime"));

  if(getBooleanItem("alarmTime") == "null") {
    $('#alarmSwitch').prop('checked', false);
  }

});
