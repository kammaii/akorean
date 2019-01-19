// Hyper Text Transport Protocol
// html = hyper text markup language
const http = require('http');
const fs = require('fs');
const url = require('url');
const { parse } = require('querystring');

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

let doAuthentication = function(username, password, httpResponse) {
  if(userAccounts[username]) {
    let passwordCheck = userAccounts[username].password;
    if(passwordCheck === password) {
      // Authenticated!!
      serveFile('home.html', 'text/html', httpResponse);
    } else {
      // Wrong password!!
      serveFile('unauthorized.html', 'text/html', httpResponse);
    }
  } else {
    // user doesn't exist!!
    serveFile('unauthorized.html', 'text/html', httpResponse);
  }
}

let handleLoginGET = function(httpRequest, httpResponse) {

  // get paramaters out of GET login url
  let loginRE = /login\?username=([^&]*)&password=(.*)/
  let paramResult = httpRequest.url.match(loginRE);
  let username = paramResult[1];
  let password = paramResult[2];

  doAuthentication(username, password, httpResponse);
}

let handleLoginPOST = function(httpRequest, httpResponse) {
  // As we get the body out of the request, we'll save it here:
  let body = '';

  // we don't know how big the http request body is. Nodejs talks to the
  // CPU and network card to get the buffer of data from the request body.
  // So, we listen for an event for whenever more data arrives, and when
  // data arrives, we save it to our body variable
  httpRequest.on('data', chunk => {
    body += chunk.toString();
  });

  httpRequest.on('end', () => {
    // we know that we have all the pieces (or chunks) of data from
    // the buffer. So, now we can use the contents of the http POST request
    // body to determine whether to authenticate a user
    let formData = parse(body);
    console.log(formData);
    let username = formData.username;
    let password = formData.password;
    doAuthentication(username, password, httpResponse);
  });
}

let handleRequest = function(httpRequest, httpResponse) {
  try {
    //console.log(httpResponse);

    // here's where we define the routes
    // Browsers will send GET or POST requests
    let decodedUrl = decodeURI(httpRequest.url);

    let urlRegex = /\/(([^.]+)\.(.*))/;
    let urlRegexResult = decodedUrl.match(urlRegex);

    console.log('--------------------------------');
    console.log(`RAW url: ${httpRequest.url}`);
    console.log(`Decoded url: ${decodedUrl}`);
    console.log(`HTTP Method: ${httpRequest.method}`);
    //console.log(`HTTP Headers: ${JSON.stringify(httpRequest.headers)}`);

    let requestUrl = url.parse(httpRequest.url);
    console.log(requestUrl);

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

    // Example of GET http request
    // expects a url similar to /login?username=foo&password=blah
    else if(httpRequest.url.match(/^\/login\?.*$/) &&
            httpRequest.method === 'GET') {

      console.log('This is a GET LOGIN request!');
      handleLoginGET(httpRequest, httpResponse);

    }

    // example of basic http POST request
    else if(httpRequest.url.match(/^\/login$/)
            && httpRequest.method === 'POST') {

      console.log("This is a POST LOGIN request");
      handleLoginPOST(httpRequest, httpResponse)

    }

    // example of ajax GET request
    else if(httpRequest.url.match(/^\/ajax\/login\?.*$/)
            && httpRequest.method === 'GET') {

      console.log("This is a AJAX GET request");

      httpResponse.statusCode = 200;
      httpResponse.setHeader('Content-Type', "application/json");

      // let's get "username=dave&password=secret"
      let queryString = requestUrl.query;
      let queryObj = parse(queryString);

      let response = {message: `Hello, ${queryObj.username}!`}
      httpResponse.write(JSON.stringify(response));
      httpResponse.end();

      // JSON
      // stringify - converts javascript into a String
      // parse - string to Javascript.

    }

    // example of ajax POST request
    else if(httpRequest.url.match(/^\/ajax\/login$/)
            && httpRequest.method === 'POST') {

      console.log("This is a AJAX POST request");

      httpResponse.statusCode = 200;
      httpResponse.setHeader('Content-Type', "application/json");
      let response = {message: 'Hello!'}
      httpResponse.write(JSON.stringify(response));
      httpResponse.end();

      // JSON
      // stringify - converts javascript into a String
      // parse - string to Javascript.

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
