// Hyper Text Transport Protocol
// html = hyper text markup language
const http = require('http');
const fs = require('fs');
//const { parse } = require('querystring');

const hostname = '127.0.0.1';
// common http port : 80
const port = 3000;

let serveFile = (pathToFile, contentType, httpResponse) => {
  fs.readFile(pathToFile, function(err, data) {
    httpResponse.statusCode = 200;
    httpResponse.setHeader('Content-Type', contentType);
    httpResponse.write(data);
    httpResponse.end();
  });
}

let userAccounts = {
  'upgradingdave': {
    username: 'upgradingdave',
    password: 'secret'
  },
  'danny': {
    username: 'danny',
    password: 'secret2'
  }
};

let handleRequest = function(httpRequest, httpResponse) {
  try {
    //console.log(httpResponse);

    // here's where we define the routes
    // Browsers will send GET or POST requests
    // If they are GET requests, all the info is in the url like this:
    //  /login?username=upgradingdave&password=secret

    let decodedUrl = decodeURI(httpRequest.url);

    let urlRegex = /\/(([^.]+)\.(.*))/;
    let urlRegexResult = decodedUrl.match(urlRegex);

    console.log('--------------------------------');
    console.log(`RAW url: ${httpRequest.url}`);
    console.log(`Decoded url: ${decodedUrl}`);
    console.log(`HTTP Method: ${httpRequest.method}`);

    let filetypeResult = decodedUrl.split('.');

    let filename = urlRegexResult ? urlRegexResult[1] : '';
    let filetype = filetypeResult[filetypeResult.length-1];

    console.log(`filetype: ${filetype}`);
    console.log(`filename: ${filename}`);

    // how to serve 한글
    if(decodedUrl == '/안녕하세요.html') {

      console.log("Serving a 한글 url!!")
      serveFile('안녕하세요.html', 'text/html', httpResponse);

    }

    // Username and Password example
    // expects a url similar to /login?username=foo&password=blah
    else if(httpRequest.url.match(/^\/login\?.*$/)) {

      console.log('This is a LOGIN request!');
      // get paramaters out of GET login url
      let loginRE = /login\?username=([^&]*)&password=(.*)/
      let paramResult = httpRequest.url.match(loginRE);
      let username = paramResult[1];
      let password = paramResult[2];

      if(userAccounts[username]) {
        let passwordCheck = userAccounts[username].password;
        if(passwordCheck === password) {
          // Authenticated!!
          serveFile('home.html', 'text/html', httpResponse);
        } else {
          // Wrong password!!
        }
      } else {
        // user doesn't exist!!
      }
    }

    // example of basic http POST request
    else if(httpRequest.url.match(/^\/login$/)) {
      console.log("This is a POST LOGIN request");
      console.log(httpRequest);

      // Need to read the body of the request
      let body = '';
      httpRequest.on('data', chunk => {
        body += chunk.toString();
      });
      httpRequest.on('end', () => {
        parse(body);
      });
    }

    // Otherwise, serve the file based on it's filetype
    else if(filetype == 'css') {
      serveFile(filename, 'text/css', httpResponse)
    } else if(filetype == 'html') {
      serveFile(filename, 'text/html', httpResponse)
    } else if(filetype == 'svg') {
      serveFile(filename, 'image/svg+xml', httpResponse)
    } else if(filetype == 'js') {
      serveFile(filename, 'text/javascript', httpResponse)
    } else if(filetype == 'ttf') {
      serveFile(filename, 'font/ttf', httpResponse)
    } else if(filetype == 'mp3') {
      serveFile(filename, 'audio/mpeg', httpResponse)
    }

    // if we don't match any other routes, serve login.html
    else {
      serveFile('login.html', 'text/html', httpResponse);
      //httpResponse.statusCode = 200;

      // Very simple string httpResponseponse
      //httpResponse.setHeader('Content-Type', 'text/html');

      // everything after the headers is the "httpResponse Body"
      /*let html = "<html><head><title>Jquery</title></head>" +
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
               "</body>";*/

      //httpResponse.end(html);
    }
  } catch(err) {
    console.log(err);
  }
}

const server = http.createServer(handleRequest);

server.listen(port, hostname, function() {
  console.log(`Server running at http://${hostname}:${port}/`);
});
