@echo off
REM Compile and run BeatBox client

if not exist out mkdir out
javac -d out src\BeatBox.java

set HOST=localhost
set PORT=4242

if not "%1"=="" set HOST=%1
if not "%2"=="" set PORT=%2

echo Connecting BeatBox to %HOST%:%PORT%...
java -cp out BeatBox %HOST% %PORT%
pause
