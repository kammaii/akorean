var gnStartX = 0;
var gnStartY = 0;
var gnEndX = 0;
var gnEndY = 0;

window.addEventListener('touchstart',function(event) {
  gnStartX = event.touches[0].pageX;
  gnStartY = event.touches[0].pageY;
},false);

window.addEventListener('touchmove',function(event) {
  gnEndX = event.touches[0].pageX;
  gnEndY = event.touches[0].pageY;
},false);

window.addEventListener('touchend',function(event) {
  alert('START (' + gnStartX + ', ' + gnStartY + ')   END (' + gnEndX + ', ' + gnEndY + ')');
},false);
