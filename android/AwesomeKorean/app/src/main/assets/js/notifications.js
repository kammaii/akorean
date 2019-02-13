function showAndroidNotification(title, message) {
  if(Android) {
    Android.showNotification(title, message);
  }
}

$( document ).ready(function() {
  $('#notifyBtn').click(function(event) {
    showAndroidNotification(
      "Korean Study Time",
      "Don't Forget to Study Korean!");
  });

});
