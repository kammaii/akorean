function createAudioElement(letter) {
  let sound = document.createElement('audio');
  sound.id  = 'audio-player';
  sound.controls = 'controls';
  sound.src = `audio/consonant/${letter}.mp3`;
  sound.type = 'audio/mp3';
  return sound;
}

var consonantsView = {};

(function(o) {

  o.consonantArray = ["ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㄲ", "ㄸ", "ㅃ", "ㅆ", "ㅉ"];
  o.consonantIdx = 0;
  o.currentAudio = null;
  o.render = function() {
    console.log(`Array length: ${o.consonantArray.length}` );
    console.log(`Rendering index: ${o.consonantIdx}`);
    let letter = o.consonantArray[o.consonantIdx];
    $('.consonants').html(`<div class="consonants__letter">${letter}</div>`);
    if(o.currentAudio) {
      o.currentAudio.pause();
      o.currentAudio.currentTime = 0;
    }
    o.currentAudio = createAudioElement(letter);
    $('.consonants').append(o.currentAudio);

    //https://developers.google.com/web/updates/2017/09/autoplay-policy-changes
    var promise = o.currentAudio.play();
    if (promise !== undefined) {
      promise.then(_ => {
        // Autoplay started!
      }).catch(error => {
        // Autoplay was prevented.
        // Show a "Play" button so that user can start playback.
        console.log("This device doesn't allow autoplaying audio");
      });
    }
  }

  o.swipeLeft = function() {
    console.log(`handling swipe left`);
    if(o.consonantIdx >= o.consonantArray.length-1) {
      o.consonantIdx = 0;
    } else {
      o.consonantIdx++;
    }
    o.render();
  }

  o.swipeRight = function() {
    console.log(`handling swipe right`);
    if(o.consonantIdx <=0) {
      o.consonantIdx = o.consonantArray.length - 1;
    } else {
      o.consonantIdx--;
    }
    o.render();
  }

  o.startx = 0;
  o.starty = 0;
  o.endx = 0;
  o.endy = 0;
  o.tolerance = 100; // max length of swipe allowed perpendicular
  o.minDistance = 150;

  o.init = function() {

    window.addEventListener('touchstart',function(event) {
      o.startx = event.touches[0].pageX;
      o.starty = event.touches[0].pageY;
      o.startTime = new Date().getTime();
    },false);

    window.addEventListener('touchmove',function(event) {
      o.endx = event.touches[0].pageX;
      o.endy = event.touches[0].pageY;
      o.endTime = new Date().getTime();
    },false);

    window.addEventListener('touchend',function(event) {
      console.log(`Handling Touch Event`);
      console.log(`Touch start: (${o.startx},${o.starty})`);
      console.log(`Touch end: (${o.endx},${o.endy})`);

      o.distx = o.endx - o.startx
      o.disty = o.endy - o.starty

      if(Math.abs(o.disty) < o.tolerance && Math.abs(o.distx) > o.minDistance) {

        if(o.distx < 0) {
          o.swipeLeft();
        } else {
          o.swipeRight();
        }
      }

    },false);

    o.render();
  }

  o.init();

})(consonantsView);
