function makePurchase(skuId) {
  console.log(`Attempting to make purchase`);
  if(typeof Android !== 'undefined') {
    // pass the google play store purchase id
    Android.makePurchase(skuId);
  }
}

$( document ).ready(function() {

  $(".page__content__intro__button").on("click", function() {

    alert("it works!");

      // get the css id of the element that was clicked
      // for now, I'm just hard coding This
      // a.korean.hangul.donation.1
      // a.korean.hangul.donation.5
      // a.korean.hangul.donation.10

      let skuId = "a.korean.hangul.donation.1";
      makePurchase(skuId);
  });

});
