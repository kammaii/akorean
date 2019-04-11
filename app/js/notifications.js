
function scheduleNotification(hour, min) {
  console.log(`${hour}:${min}`);
  if(typeof Android !== 'undefined') {
    Android.scheduleNotification(hour, min);
  }
}

// The format is "HH:mm", "HH:mm:ss" or "HH:mm:ss.SSS" where HH is 00-23, mm is 00-59, ss is 00-59, and SSS is 000-999.
$("input[type=time]").on("change", function() {

  // get hour and min
  let timeValue = document.getElementById("alarmTime").value;
  console.log(`alarmTime value: ${timeValue}` );

  let hour = timeValue.slice(0,2);
  let minute = timeValue.slice(3,5);

  if(hour && minute) {
    console.log(`hour: ${hour}, minute: ${minute}` );
    localStorage.setItem("alarmTime", hour+":"+minute);
    scheduleNotification(hour, minute);
  }

});

$('#alarmSwitch').click(function(event) {

  //document.getElementById("alarmTime").value = null;
  let alarmSwitch = event.target.checked; // alarmSwitch true or false
  localStorage.setItem("alarmSwitch", alarmSwitch);
  if(alarmSwitch == true) {
    document.getElementById("alarmTime").disabled = false;
  } else {
    document.getElementById("alarmTime").disabled = true;
    // TODO: need to implement cancel
    //scheduleNotification(null);
  }
})

$( document ).ready(function() {

  let localAlarmSwitch = getBooleanItem("alarmSwitch", false);
  $('#alarmSwitch').prop('checked', localAlarmSwitch);
  console.log(`AlarmSwitch in localStorage: ${localAlarmSwitch}`);

  let localAlarmTime = getItem("alarmTime", "12:00");
  console.log(`AlarmTime in localStorage: ${localAlarmTime}`);
  if(localAlarmTime) {
    $('#alarmTime').val(localAlarmTime);
  }

  if(localAlarmSwitch) {
    document.getElementById("alarmTime").disabled = false;
  }

});
