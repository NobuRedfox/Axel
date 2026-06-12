# JavaNetworkTicTacToe

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

The game uses a simple client/server structure.

* The server starts the game and waits for a connection.
* The client connects to the server by using the server IP address.
* Both players can then play TicTacToe over a TCP connection.

The server listens on port `5000`.

## Project Structure

```text
Java/JavaNetworkTicTacToe/
├── README.md
├── scripts/
│   └── start-local.sh
└── src/
    ├── Board.java
    ├── Game.java
    ├── GameClient.java
    ├── GameServer.java
    └── Main.java
```

## Run the Project locally

Go into the source folder:

```bash
cd Java/JavaNetworkTicTacToe/src
```

Compile the Java files:

```bash
javac *.java
```

Start the server:

```bash
java Main server
```

Start the client in another terminal:

```bash
java Main client 127.0.0.1
```

`127.0.0.1` means localhost. This only works when server and client run on the same computer.

## Play over two computers in the same network

Player 1 starts the server:

```bash
java Main server
```

Player 2 starts the client and uses the IP address of Player 1:

```bash
java Main client SERVER_IP
```

Example:

```bash
java Main client 192.168.178.115
```

`SERVER_IP` must be replaced with the real IP address of the computer where the server is running.

Player 2 does not need to change the source code. They only need to change the IP address in the start command.

## What the other player has to do

The other player needs a copy of this project, for example by cloning the repository:

```bash
git clone https://github.com/NobuRedfox/Axel.git
```

Then they go into the project folder:

```bash
cd Axel/Java/JavaNetworkTicTacToe/src
```

Then they compile the project:

```bash
javac *.java
```

Then they start the client with the server IP:

```bash
java Main client SERVER_IP
```

## Test the connection

Before starting the game, the other player can test whether the server is reachable.

```bash
nc -vz SERVER_IP 5000
```

If the port test says `succeeded`, the connection to the server works.

## Linux Namespace Version

The project can also be started inside two separate Linux network namespaces.

This simulates two independent computers connected through a virtual network.

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
* Add a graphical user interface
* Improve the game protocol