
function scheduleNotification(hour, min) {
  if(Android) {
    Android.scheduleNotification(hour, min);
  }
}

$( document ).ready(function() {

  //scheduleNotification(8, 30);
  // when user changes time and clicks a button, call "scheduleNotification"

});
