const http = require('http');
const hostname = '0.0.0.0';
const fs = require('fs');
const port = 3000;
const mustache = require("mustache");

let serveMustache = (template, data, res) => {
  var html = mustache.render(template, data);
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/html');
  res.write(html);
  res.end();
}

let serveFile = (pathToFile, contentType, res) => {
  fs.readFile(pathToFile, (err, data) => {
    res.statusCode = 200;
    res.setHeader('Content-Type', contentType);
    res.write(data);
    res.end();
  });
}

let handleRequest = (req, res) => {
  try {
    console.log(req.url);
    let urlRegex = /\/(([^.]+)\.(.*))/;
    let urlRegexResult = req.url.match(urlRegex);
    let filetypeResult = req.url.split('.');

    if(req.url.match('/mustache')) {
      fs.readFile("mustache.html", "utf8", (err, text) => {
        var template = text;
        var data = {
          name: "Danny",
          scripts: [
            {source:"js/jquery-3.3.1.min.js"},
            {source:"js/tips.js"}
          ]
        };
        serveMustache(template, data, res);
      });

    } else if(urlRegexResult == null) {
        serveFile('opening.html', 'text/html', res)
    } else {
      let filename = urlRegexResult[1];
      filename = decodeURI(filename);
      let filetype = filetypeResult[filetypeResult.length-1];

      if(filetype == 'css') {
        serveFile(filename, 'text/css', res)
      } else if(filetype == 'html') {
        serveFile(filename, 'text/html', res)
      } else if(filetype == 'svg') {
        serveFile(filename, 'image/svg+xml', res)
      } else if(filetype == 'js') {
        serveFile(filename, 'text/javascript', res)
      } else if(filetype == 'ttf') {
        serveFile(filename, 'font/ttf', res)
      } else if(filetype == 'mp3') {
        serveFile(filename, 'audio/mpeg', res)
      } else if(filetype == 'jpg') {
        serveFile(filename, 'image/jpg', res)
      } else {
        serveFile('opening.html', 'text/html', res)
      }
    }
  } catch(err) {
    console.log(err);
  }
}
const server = http.createServer(handleRequest);

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});
