@echo off
if not exist "build" mkdir build
javac -d build src/model/*.java src/ui/*.java src/main/Main.java
echo Compilation complete
