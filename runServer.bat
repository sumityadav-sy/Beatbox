@echo off
REM Compile and run MusicServer

if not exist out mkdir out
javac -d out src\MusicServer.java

set PORT=4242
if not "%1"=="" set PORT=%1

echo Starting MusicServer on port %PORT%...
java -cp out MusicServer %PORT%
pause
