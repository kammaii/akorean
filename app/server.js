const http = require('http');
const hostname = '0.0.0.0';
const fs = require('fs');
const port = 3000;
const mustache = require("mustache");

function readFileMustache(filename) {
  console.log("filename:" +filename);
    return new Promise((resolve, reject) => {
        fs.readFile(filename, "utf-8", (err, text) => {
            // if (err) reject(err);
            resolve(text);
        });
    });
};

function readMustache(template_header, template_footer, filename, res) {
  let headerPromise = readFileMustache(template_header);
  let footerPromise = readFileMustache(template_footer);
  let pagePromise = readFileMustache(filename);
  Promise.all([headerPromise, footerPromise, pagePromise]).then(function(values) {

    var template = values[2];
    var data = {
      header: values[0],
      footer: values[1]
    }
    serveMustache(template, data, res);
  });
}

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

    if(urlRegexResult == null) {
      serveFile('opening.html', 'text/html', res)
    } else {
      let filename = urlRegexResult[1];
      filename = decodeURI(filename);
      let filetype = filetypeResult[filetypeResult.length-1];

      if(filetype == 'html') {
        if(filename.match('tip')) {
          readMustache("template_header_tip.html", "template_footer_tip.html", urlRegexResult[1], res);
        } else {
          readMustache("", "template_footer.html", urlRegexResult[1], res);
        }

      } else if(filetype == 'css') {
        serveFile(filename, 'text/css', res)
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
