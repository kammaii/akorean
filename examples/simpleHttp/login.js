$( document ).ready(function() {
  console.log( "ready!" );
  $('#submitBtn').click(function() {

    console.log('You clicked!')

    let valueUsername = $('#username').val()
    let infoUsername = $('#info-username');
    let infoPassword = $('#info-password');

    console.log(`The current value of the input is '${valueUsername}'`)

    if(valueUsername != undefined && valueUsername != null && valueUsername.length > 0) {
      console.log("Good Username")
      infoUsername.html("<label></label>");
      // submit the form

    } else {
      console.log("Bad Username")
      infoUsername.html("<label>Oops! Please enter a username to continue!</label>");
    }

    let valuePassword = $('#password').val();
    let numberPassword = valuePassword.match(/[0-9]/g);
    let letterPassword = valuePassword.match(/[a-z]/gi);

    if(valuePassword == undefined || valuePassword == null || valuePassword.length == 0) {
      console.log("Bad Password");
      infoPassword.html("<label>Ooops! Please enter a password to continue!</label>");
    } else if(numberPassword.length > 2 && letterPassword.length > 2) {
      infoPassword.html("<label></label>");
      console.log("Good Password");

    } else {
      console.log("Bad Password");
      infoPassword.html("<label>Password should be more than 2 numbers and 2 letters</label>");
    }
  })
})
