var tips =
  {
    "tip0": {
      "timestamp": "20190319",
      "type" : "anchor",
      "message": "ㅎ + ㄱ, ㄷ, ㅂ, ㅈ = ?"
    },

    "tip1": {
      "timestamp": "20190319",
      "type" : "anchor",
      "message": "If you feel hard to read 'ㅕ, ㅑ, ㅐ'"
    },

    "tip2": {
      "timestamp": "20190319",
      "type" : "anchor",
      "message": "When you try to memorize verb/adjective..."
    },

    "tip3": {
      "timestamp": "20190319",
      "type" : "anchor",
      "message": "(Batchim) ㄷ, ㅌ + (vowel)ㅣ = ?"
    },

    "tip4": {
      "timestamp": "20190319",
      "type" : "anchor",
      "message": "How to read naturally when the consonant 'ㅇ' comes after Batchim?"
    },

    "tip5": {
      "timestamp": "20190319",
      "type" : "anchor",
      "message": "Is there any good tools for learning language?"
    }


  }

function getRandomInt(max) {
  return Math.floor(Math.random() * Math.floor(max));
}

function tipsFn() {
  // choose a tip (randomly)
  let tipsLength = Object.keys(tips).length;

  let randomNumber = getRandomInt(tipsLength);

  let randomTip = tips["tip" + randomNumber]; //=tips.tip1

  $('.page__footer__date').html("<p>" + randomTip.timestamp + "</p>");

  if(randomTip["type"] == "anchor") {
    $('.page__footer__content').html("<a href='/tips/tip" + randomNumber + ".html'><img src='/img/touch.svg' style='width:50px'>" + randomTip.message + "</a>");

  } else if(randomTip["type"] == "text") {
    $('.page__footer__content').html("<p>" + randomTip.message + "</p>");
  }

};

$( document ).ready(tipsFn);

window.setInterval(tipsFn, 60000); //1mins
