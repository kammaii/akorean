$(document).ready(function() {

  console.log( "ready!" );

  $('#submitBtn').click(function(event) {

    event.preventDefault();

    console.log('Attempting to submit form ... first validate credentials');

    let usernameValue = $('#username').val();
    let passwordValue = $('#password').val();
    let info = $('#info');

    console.log(`The current value of the input is '${usernameValue}'`)
    console.log(`The current value of the input is '${passwordValue}'`)

    if(validateCredentials(usernameValue, passwordValue, info)) {
      $('#form2').submit();
    }

  });

});
