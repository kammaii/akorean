// Hyper Text Transport Protocol
// html = hyper text markup language
const http = require('http');

const hostname = '127.0.0.1';
// common http port : 80
const port = 3000;

let handleRequest = function(httpRequest, httpResponse) {
  //console.log(httpResponse);

  httpResponse.statusCode = 200;

  // Very simple string response
  httpResponse.setHeader('Content-Type', 'text/html');

  // everything after the headers is the "Response Body"
  let html = "<html><head><title>Jquery</title></head>" +
             "<body>"+
             "<div>Hello Jquery</div>"+
             "<form method=\"POST\" action=\"/login\">"+
             "<div>" +
             "<label for=\"username\"/>Username</label>"+
             "<input id=\"username\" name=\"username\" type=\"text\"></input>" +
             "</div>" +
             "<div>" +
             "<label for=\"password\"/>Password</label>"+
             "<input name=\"password\" type=\"password\"></input>" +
             "</div>" +
             "<input id=\"submitBtn\" class=\"btn\" type=\"submit\" value=\"Submit\">" +
             "</form>"+
             "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js\"></script>"+
             "<script>" +
             "$( document ).ready(function() {" +
             "  console.log( \"ready!\" );"+
             "  $('#submitBtn').click(function() { " +
             "    console.log('You clicked!') "+
             "    console.log($('#username').value) "+
             "  }) "+
             "});" +
             "</script>"

             ;
             "</body>";

  httpResponse.end(html);

}

const server = http.createServer(handleRequest);

server.listen(port, hostname, function() {
  console.log(`Server running at http://${hostname}:${port}/`);
});
