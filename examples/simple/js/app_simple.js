const http = require('http');
// file system
const fs = require('fs');

const hostname = '127.0.0.1';
const port = 3000;

let afterRead = function(err, data)  {

}

let serveHtml = function(pathToHtmlFile, response) {
  fs.readFile(pathToHtmlFile, function(err, data) {

    response.statusCode = 200;
    response.setHeader('Content-Type', 'text/html');

    response.write(data);
    response.end();

  });
}

let handleRequest = function(httpRequest, httpResponse) {
  httpResponse.statusCode = 200;

  // Very simple string response
  httpResponse.setHeader('Content-Type', 'text/html');
  httpResponse.end(html);

}

const server = http.createServer(handleRequest);


server.listen(port, hostname, function() {
  console.log(`Server running at http://${hostname}:${port}/`);
});
