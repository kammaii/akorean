function makePurchase(skuId) {
  console.log(`Attempting to make purchase`);
  if(typeof Android !== 'undefined') {
    // pass the google play store purchase id
    Android.makePurchase(skuId);
  }
}

$( document ).ready(function() {

  $(".page__content__intro__button").on("click", function(evt) {

    // make sure that the id of the links are equal to the
    // skuIds set in the Google Play store.
    let skuId = evt.target.id;
    //alert("Attempting to purchase: " + skuId);
    makePurchase(skuId);
  });

});
