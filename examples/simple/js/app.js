const http = require('http');
const fs = require('fs');
const mustache = require ('mustache');

const hostname = '127.0.0.1';
const port = 3000;

let serveHtml = (pathToHtmlFile, response) => {
  fs.readFile(pathToHtmlFile, function(err, data) {
    response.writeHead(200, {'Content-Type': 'text/html'});
    response.write(data);
    response.end();
  });
}

let serveSimpleTemplate = (response) => {

  var view = {
    name: "Mr. Bigglesworth"
  };

  var output = mustache.render("Hello, {{name}}!", view);

  response.writeHead(200, {'Content-Type': 'text/html'});
  response.write(output);
  response.end();

}

let serveHtmlTemplate = (pathToHtmlFile, view, response) => {
  fs.readFile(pathToHtmlFile, function(err, data) {
    response.writeHead(200, {'Content-Type': 'text/html'});
    var output = mustache.render(data.toString(), view);
    response.write(output);
    response.end();
  });
}

const server = http.createServer((req, res) => {
  res.statusCode = 200;

  // Very simple string response
  //res.setHeader('Content-Type', 'text/plain');
  //res.end('Hello World\n');

  // Simple html file response
  //serveHtml('html/index.html', res);

  // Simple template response
  //serveSimpleTemplate(res);

  // html template response
  let view = {
    "name": "Dave"
  };

  serveHtmlTemplate('templates/index.mustache', view, res);

});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});
