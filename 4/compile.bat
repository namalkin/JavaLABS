@echo off
setlocal
echo Компиляция исходников...
if not exist build mkdir build
javac -d build -cp "lib/*" src\com\gameportal\Main.java src\com\gameportal\utils\Counter.java src\com\gameportal\models\User.java src\com\gameportal\servlets\LoginServlet.java src\com\gameportal\servlets\PortalServlet.java src\com\gameportal\servlets\UploadServlet.java
if %errorlevel% neq 0 (
  echo Ошибка компиляции.
  pause
  exit /b %errorlevel%
)
echo Компиляция успешна.
pause
