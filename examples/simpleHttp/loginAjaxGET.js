(function() {

  // Define a global variable that will represent the http request
  var httpRequest;

  // attach an event handler to the button with id "button4"
  document.getElementById("button4").addEventListener('click', makeRequest);

  // send an http request to the server
  function makeRequest() {
    httpRequest = new XMLHttpRequest();

    if (!httpRequest) {
      alert('Giving up :( Cannot create an XMLHTTP instance');
      return false;
    }

    // get user name and password to send
    let username = document.getElementById("username4").value;
    let password = document.getElementById("password4").value;

    httpRequest.onreadystatechange = handleResponse;
    httpRequest.open('GET', `/ajax/login?username=${username}&password=${password}`);

    //httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    //httpRequest.send('userName=' + encodeURIComponent(userName));

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
