# Network Layers

A common way to think about a computer network is to break it into 7 Layers.
This is known as the "Open Systems Interconnection" (OSI) Network Model

## Physical Layer (Bottom Layer or Layer 1)

The Physical Layer represents the Physical Electronic components (cables, wires,
satellites, radio stations, etc) that are capable of transporting raw bits of
information.

## Data Link Layer (Layer 2)

The Data Link Layer handles the transmission of "Frames" of data between across
network boundaries.  

Ethernet, DSL and MAC Addresses operate at the Data Link Layer.

Every network device (such as a ethernet network card, or a wifi network card)
has a "MAC Address" (Media Access Control Address). You can think of a MAC
Address sort of like the Latitude and Longitude of your House. It never changes.

Your Internet Service Provider (ISP) is the company that you pay monthly to
connect to the internet. The "internet" is a bunch of connected networks. Your
ISP provides a way to communicate over the Data Link Layer and Network Layer. 

## Network Layer (Layer 3)

The Network Layer transfers Packets of data from a source to a destination.

The Internet Protocol (IP) describes the way that packets of data can be
transferred across a network

Every device (computer, mobile phone, tablet) has a unique IP Network Address.

All the layers above this layer are also part of the "Internet Protocol".

Packets are "Routed" from one computer to the next based on destination IP
Address found inside the packets.

The Network Layer (Layer 3) is only concerned with delivering packets from a
source to a destination. These packets might arrive out of order.

## Transport Layer (Layer 4)

TCP stands for Transmission Control Protocol. TCP provides reliable, ordered,
and error-checked delivery of network packets.

UCP stands for User Datagram Protocol. Unlike TCP, UDP does not provide
reliability or order. Its best used for broadcasting messages when you don't
really care if the messages arrive at the destination or not. For example,
sending logs is good candidate for UDP.

## Session Layer, Presentation Layer, and Application Layer

In my opinion, these last 3 layers can all be combined. These are the layers that software developers must deal with when writing programs.

A session exists anytime 2 computers are communicating. A session begins when
one computer attempts to contact another computer. And it ends when the
conversation between computers is over.

When you are writing a program that deals with Sessions, you need to be careful
to maintain the current "state" of the session.

(And, in fact, I think, the purpose of any software is to manage state!)

In my opinion, the Presentation Layer is part of the Application Layer. The
Presentation Layer is concerned with converting data into something that can be
understood by the application. For example, data might be written as binary
streams, but read as UTF-8 encoding.

And finally, we come to the Application Layer! This is the Layer that you invent
when you write software!

Here are some protocols that run at the "Application Layer": HTTP, HTTPS, FTP,
SMTP, SSH, SSL, and many others.

# Links
https://en.wikipedia.org/wiki/OSI_model
https://en.wikipedia.org/wiki/Internet_Protocol
https://en.wikipedia.org/wiki/Transmission_Control_Protocol
https://en.wikipedia.org/wiki/MAC_address
