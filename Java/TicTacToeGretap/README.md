# TicTacToeGretap

This project started as a simple Java TicTacToe game and was later extended with network communication.

The goal was to combine Java programming with Linux networking and learn how applications communicate over a network.

## What I learned

* Object-oriented programming in Java
* Working with 2D arrays
* TCP client/server communication
* Java sockets
* Sending and receiving data over a network
* Linux network namespaces
* Virtual Ethernet connections (veth)
* Using Git and GitHub for version control

## How it works

The server manages the game board and waits for a client connection.

The client connects to the server and sends its moves.

Both players can play TicTacToe over a TCP connection.

## Project Structure

```text
src/
├── Board.java
├── GameClient.java
├── GameServer.java
├── Main.java

scripts/
└── start-local.sh
```

## Run the Project

Compile:

```bash
javac *.java
```

Start server:

```bash
java Main server
```

Start client:

```bash
java Main client 127.0.0.1
```

## Linux Namespace Version

The project can also be started inside two separate Linux network namespaces.

This simulates two independent computers connected through a virtual network.

Setup:

```bash
./scripts/start-local.sh
```

Server:

```bash
sudo ip netns exec server-ns java -cp src Main server
```

Client:

```bash
sudo ip netns exec client-ns java -cp src Main client 10.0.0.1
```

## Future Ideas

* Play between two real computers
* Connect namespaces through WireGuard
* Connect different systems through GREtap
* Add a graphical user interface
* Improve the game protocol

