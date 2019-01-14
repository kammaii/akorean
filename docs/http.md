# Inter process communication.

Many times 2 processes need to communicate. There are infinite different ways that two processes can communicate.

A very simple way would be one program writes to a file and
the reads what the program wrote.

Writing and reading to a file is not very well defined.

Protocols are formats that 2 programs can agree upon in order to communicate.

# Hyper Text Transport Protocol (http)

In http, a client (web browser) sends a request.

The server (which is another process, could be on same computer or different computer) will listen for requests and for each request will
send a response.

# Hyper Text Transport Protocol Secure (https)


## Two most popular commands:

GET

POST

# Ports

By default, Port 80 is used for http.
Port 443 is used for https.

# HTTP Network communication

## HTTP Request Header

The header of an HTTP Request is a known, fixed size. It contains meta data
about the request such as content-type and request method.

## HTTP Request Body Body

There is no way to know the size of the body of a HTTP Request. Also, the data
in the body of a HTTP Request doesn't arrive all at the same time.

It will arrive in pieces or chunks.

So an http server listening must continue to read the buffer of input from the
request until all the chunks arrive.

## Ajax
