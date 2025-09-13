#!/bin/bash
# Compile and run MusicServer

mkdir -p out
javac -d out src/MusicServer.java

PORT=${1:-4242}
echo "Starting MusicServer on port $PORT..."
java -cp out MusicServer $PORT
