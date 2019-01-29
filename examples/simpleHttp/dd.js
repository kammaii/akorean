const http = require('http');
const fs = require('fs');
const hostname = '127.0.0.1';
const port = 3000;

let serveFile = function(pathToFile, contentType, httpResponse) {
  fs.readFile(pathToFile, function(err, data) {
    //httpResponse.writeHead(200, {'Content-Type': 'text/html'});
    httpResponse.statusCode = 200;
    httpResponse.setHeader('Content-Type', contentType);
    httpResponse.write(data);
    httpResponse.end();
  });
}

let handleRequest = function(httpRequest, httpResponse) {
  try{
    console.log(httpRequest.url);
    if(httpRequest.url == '/app.js') {
      serveFile('app.js', 'text/javascript', httpResponse);
    } else {
      serveFile('app.html', 'text/html', httpResponse);
    }
  } catch(err) {
    console.log(err);
  }
}

const server = http.createServer(handleRequest);

server.listen(port, hostname, function() {
  console.log(`Server running at http://${hostname}:${port}/`);
});
