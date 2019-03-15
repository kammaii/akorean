const http = require('http');
const hostname = '0.0.0.0';
const fs = require('fs');
const port = 3000;

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

    if(urlRegexResult == null) {
      serveFile('home.html', 'text/html', res)
    } else {

      let filename = urlRegexResult[1];
      filename = decodeURI(filename);
      let filetype = filetypeResult[filetypeResult.length-1];
      console.log(filename);
      console.log(filetype);

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
      } else {
        serveFile('home.html', 'text/html', res)
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
