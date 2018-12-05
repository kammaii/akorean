// Hyper Text Transport Protocol
// html = hyper text markup language
const http = require('http');
const fs = require('fs');

const hostname = '127.0.0.1';
// common http port : 80
const port = 3000;

let serveFile = (pathToFile, contentType, httpResponse) => {
  fs.readFile(pathToFile, function(err, data) {
    //response.writeHead(200, {'Content-Type': 'text/html'});
    httpResponse.statusCode = 200;
    httpResponse.setHeader('Content-Type', contentType);
    httpResponse.write(data);
    httpResponse.end();
  });
}

let handleRequest = function(httpRequest, httpResponse) {
  try {
  //console.log(httpResponse);

  // what is this request for?
  // here's where we define the routes
  console.log(httpRequest.url);
  if(httpRequest.url == '/app.js'){
    serveFile('app.js', 'text/javascript', httpResponse);
  } else {

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
             "<a href=\"#\" id=\"submitBtn\" class=\"btn\" value=\"Submit\">Submit</a>" +
             "</form>"+
             "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js\"></script>"+
             "<script src=\"http://127.0.0.1:3000/app.js\">" +
             "</script>"+
             "</body>";

    httpResponse.end(html);
  }
} catch(err) {
  console.log(err);
}

}

const server = http.createServer(handleRequest);

server.listen(port, hostname, function() {
  console.log(`Server running at http://${hostname}:${port}/`);
});
