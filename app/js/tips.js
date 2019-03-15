var tips = 
  {
    "tip0": {
      "timestamp": "20190212",
      "message": "Message 1"
    },

    "tip1": {
      "timestamp": "20190212",
      "message": "Message 2"
    },

    "tip2": {
      "timestamp": "20190212",
      "message": "Message 3"
    }
  }

function getRandomInt(max) {
  return Math.floor(Math.random() * Math.floor(max));
}


$( document ).ready(function() {
  // choose a tip (randomly)
  let tipsLength = Object.keys(tips).length;

  let randomNumber = getRandomInt(tipsLength);
  console.log(randomNumber);


  let randomTip = tips["tip" + randomNumber];

  $('.page__footer').html("<p>" + randomTip.message + "</p>");
});
