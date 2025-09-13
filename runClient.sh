#!/bin/bash
# Compile and run BeatBox client

mkdir -p out
javac -d out src/BeatBox.java

HOST=${1:-localhost}
PORT=${2:-4242}
echo "Connecting BeatBox to $HOST:$PORT..."
java -cp out BeatBox $HOST $PORT
