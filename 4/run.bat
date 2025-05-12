@echo off
setlocal

REM === Путь к Tomcat ===
set TOMCAT_HOME="C:\Program Files\Apache Software Foundation\Tomcat 11.0_JavaTomcat"

echo [*] Запуск Tomcat...
cd /d %TOMCAT_HOME%\bin
call catalina.bat run
