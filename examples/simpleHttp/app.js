$( document ).ready(function() {
  console.log( "ready!" );
  $('#submitBtn').click(function() {

    console.log('You clicked!')

    let value = $('#username').val()

    console.log(`The current value of the input is '${value}'`)

    if(value != undefined && value != null && value.length > 0) {
      console.log("Good!")
      // submit the form

    } else {
      console.log("BAD!")
      let infoMsgEl = $('#info-msg')
      infoMsgEl.html("<p>Oops! Please enter a username to continue!</p>");
    }

  })

})
