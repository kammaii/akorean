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

    httpRequest.onreadystatechange = handleResponse;
    httpRequest.open('GET', '/ajax/login?username=<TODO>&password=<TODO>');
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
