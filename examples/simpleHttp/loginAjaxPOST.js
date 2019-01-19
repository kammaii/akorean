(function() {

  // Define a global variable that will represent the http request
  var httpRequest;

  // attach an event handler to the button with id "button4"
  document.getElementById("button5").addEventListener('click', makeRequest);

  // send an http request to the server
  function makeRequest() {
    httpRequest = new XMLHttpRequest();

    if (!httpRequest) {
      alert('Giving up :( Cannot create an XMLHTTP instance');
      return false;
    }

    httpRequest.onreadystatechange = handleResponse;
    httpRequest.open('POST', '/ajax/login');
    httpRequest.setRequestHeader('Content-Type', 'application/json');

    let username = document.getElementById("username5").value;
    let password = document.getElementById("password5").value;

    let payload = {username: username, password: password};
    httpRequest.send(JSON.stringify(payload));
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
