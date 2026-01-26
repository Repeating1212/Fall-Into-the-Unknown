@echo off
cd /d "%~dp0"
echo Starting Level Protocol...
java -jar Level_Protocol.jar
if errorlevel 1 pause