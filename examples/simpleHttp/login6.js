(function() {

  // Define a global variable that will represent the http request
  var httpRequest;

  // attach an event handler to the button with id "button4"
  document.getElementById("button6").addEventListener('click', handleSubmit);

  function handleSubmit() {
    // get user name and password to send
    let username = $('#username6').val();
    let password = $('#password6').val();
    let info = $('#info');

    console.log(`The current value of the input is '${username}'`);
    console.log(`The current value of the input is '${password}'`);

    if(validateCredentials(username, password, info)) {
        makeRequest(username, password);
    }

  }

  function handleResponse(responseData, textStatus) {
    console.log(jqXHR);
    console.log(textStatus);
    //let response = JSON.parse(jqXHR);
    //alert(response.message);
    alert(responseData.message);
  }

  // send an http request to the server
  function makeRequest(username, password) {

    let payload = {username: username, password: password};

    // var jqxhr = $.ajax({
    //   url: "login6",
    //   type: 'POST',
    //   dataType: 'text',
    //   data: JSON.stringify(payload),
    //   complete: function(jqXHR, textStatus) {
    //     console.log(jqXHR);
    //     console.log(textStatus);
    //     let response = JSON.parse(jqXHR.responseText);
    //     alert(response.message);
    //   }});

    $.post("login6", JSON.stringify(payload), handleResponse);

  }

})();
