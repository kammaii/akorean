(function() {

  // Define a global variable that will represent the http request
  var httpRequest;

  // attach an event handler to the button with id "button4"
  document.getElementById("button4").addEventListener('click', handleSubmit);

  function handleSubmit() {
    // get user name and password to send
    let username = document.getElementById("username4").value;
    let password = document.getElementById("password4").value;
    let info = $('#info');

    console.log(`The current value of the input is '${username}'`);
    console.log(`The current value of the input is '${password}'`);

    if(validateCredentials(username, password, info)) {
        makeRequest(username, password);
    }

  }
  
  // send an http request to the server
  function makeRequest(username, password) {

    httpRequest = new XMLHttpRequest();

    if (!httpRequest) {
      alert('Giving up :( Cannot create an XMLHTTP instance');
      return false;
    }

    httpRequest.onreadystatechange = handleResponse;
    httpRequest.open('GET', `/login4?username=${username}&password=${password}`);

    httpRequest.send();

  }

  function handleResponse() {
    // XHR (Xml Http Request)
    if (httpRequest.readyState === XMLHttpRequest.DONE) {

      if (httpRequest.status === 200) {
        let response = JSON.parse(httpRequest.responseText);
        alert(response.message);
      } else {
        alert('There was a problem with the request.');
      }

    }
  }
})();
