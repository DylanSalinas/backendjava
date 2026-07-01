@echo off
cd /d "%~dp0"

for /d %%D in ("C:\Program Files\Eclipse Adoptium\jdk-17*") do set "JAVA_HOME=%%D"
if not defined JAVA_HOME (
    echo No se encontro Java 17.
    pause
    exit /b 1
)
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo.
echo ============================================
echo  Modo MySQL - XAMPP debe estar encendido
echo  Base requerida: CREATE DATABASE ecommerce;
echo ============================================
echo.

call mvnw.cmd spring-boot:run -DskipTests -Dspring-boot.run.profiles=mysql
pause
