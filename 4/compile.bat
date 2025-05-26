@echo off
setlocal enabledelayedexpansion

REM === Настройки ===
set TOMCAT_HOME=C:\Program Files\Apache Software Foundation\Tomcat 11.0_JavaTomcat
set PROJECT_DIR=%~dp0
set DEST_DIR="%TOMCAT_HOME%\webapps\myportal"

REM === Компиляция сервлетов в build ===
echo [*] Компиляция Java файлов...
if exist build rmdir /s /q build
mkdir bui
set "JAVA_FILES="
for /r webapps\ROOT\WEB-INF\classes %%f in (*.java) do (
    set "JAVA_FILES=!JAVA_FILES! %%f"
)
javac -cp "%TOMCAT_HOME%\lib\servlet-api.jar" -d build !JAVA_FILES!
if errorlevel 1 (
    echo [!] Ошибка компиляции!
    exit /b 1
)

REM === Создание папки uploads, если не существует ===
if not exist webapps\ROOT\uploads (
    mkdir webapps\ROOT\uploads
)

REM === Создание папки counters, если не существует ===
if not exist webapps\ROOT\counters (
    mkdir webapps\ROOT\counters
)

REM === Удаление старой версии ===
echo [*] Удаление старой папки myportal...
rmdir /s /q %DEST_DIR%

REM === Копирование исходников и ресурсов в Tomcat ===
echo [*] Копирование файлов в Tomcat...
xcopy /s /i /y webapps\ROOT %DEST_DIR%

REM === Копирование .class файлов из build в Tomcat ===
xcopy /s /i /y build\* "%TOMCAT_HOME%\webapps\myportal\WEB-INF\classes\"

echo [✓] Готово! Теперь можно запускать сервер через run.bat
