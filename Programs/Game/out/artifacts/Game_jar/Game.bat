@echo off
cd /d "%~dp0"
echo Start Game "Fall into the Unknown"
java -jar Game.jar
if errorlevel 1 pause