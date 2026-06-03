#!/bin/bash

PROJECT_DIR="/home/Umschueler/Axel/Java/TicTacToeGretap/src"

SERVER_NS="server-ns"
CLIENT_NS="client-ns"

SERVER_VETH="veth-server"
CLIENT_VETH="veth-client"

SERVER_IP="10.0.0.1/24"
CLIENT_IP="10.0.0.2/24"

echo "=== Cleanup ==="

sudo ip netns del "$SERVER_NS" 2>/dev/null
sudo ip netns del "$CLIENT_NS" 2>/dev/null

echo "=== Create Namespaces ==="

sudo ip netns add "$SERVER_NS"
sudo ip netns add "$CLIENT_NS"

echo "=== Create veth ==="

sudo ip link add "$SERVER_VETH" type veth peer name "$CLIENT_VETH"

sudo ip link set "$SERVER_VETH" netns "$SERVER_NS"
sudo ip link set "$CLIENT_VETH" netns "$CLIENT_NS"

echo "=== Configure IPs ==="

sudo ip netns exec "$SERVER_NS" ip addr add "$SERVER_IP" dev "$SERVER_VETH"
sudo ip netns exec "$CLIENT_NS" ip addr add "$CLIENT_IP" dev "$CLIENT_VETH"

sudo ip netns exec "$SERVER_NS" ip link set lo up
sudo ip netns exec "$SERVER_NS" ip link set "$SERVER_VETH" up

sudo ip netns exec "$CLIENT_NS" ip link set lo up
sudo ip netns exec "$CLIENT_NS" ip link set "$CLIENT_VETH" up

echo "=== Test ==="

sudo ip netns exec "$CLIENT_NS" ping -c 1 10.0.0.1

echo
echo "=== Ready ==="
echo
echo "Server:"
echo "sudo ip netns exec server-ns java -cp $PROJECT_DIR Main server"
echo
echo "Client:"
echo "sudo ip netns exec client-ns java -cp $PROJECT_DIR Main client 10.0.0.1"
echo
echo "Namespaces:"
ip netns

