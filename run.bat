@echo off
cd /d "%~dp0"

REM Busca Java 17 instalado (Temurin)
for /d %%D in ("C:\Program Files\Eclipse Adoptium\jdk-17*") do set "JAVA_HOME=%%D"
if not defined JAVA_HOME (
    echo No se encontro Java 17. Instalalo desde: https://adoptium.net/temurin/releases/?version=17
    pause
    exit /b 1
)
set "PATH=%JAVA_HOME%\bin;%PATH%"

REM Si el puerto 8080 esta ocupado, cerrar la instancia anterior
for /f "tokens=5" %%P in ('netstat -ano ^| findstr ":8080" ^| findstr "LISTENING"') do (
    echo Puerto 8080 ocupado. Cerrando proceso anterior ^(PID %%P^)...
    taskkill /PID %%P /F >nul 2>&1
    timeout /t 2 /nobreak >nul
)

echo.
echo ============================================
echo  preentrega-dylansalinas - modo facil (H2)
echo  No necesitas XAMPP ni MySQL
echo  Web:  http://localhost:8080
echo  API:  http://localhost:8080/productos
echo ============================================
echo.

call mvnw.cmd spring-boot:run -DskipTests -Dspring-boot.run.profiles=dev
pause
