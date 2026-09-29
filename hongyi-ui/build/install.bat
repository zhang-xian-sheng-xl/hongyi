@echo off
chcp 65001 >nul 2>&1
setlocal EnableDelayedExpansion
title Hongyi Service Installer

:: ============================================================
::  Hongyi Services Installer
::  Registers hongyi-server (backend) and hongyi-nginx (frontend)
::  as Windows services via WinSW.
::  All paths are relative to the directory of this script.
:: ============================================================

:: --- Check admin ---
net session >nul 2>&1
if %errorlevel% neq 0 goto :need_admin

:: --- Resolve script directory ---
set "SCRIPT_DIR=%~dp0"
set "SCRIPT_DIR=%SCRIPT_DIR:~0,-1%"

:: --- Header ---
echo ============================================================
echo   Hongyi Services Installer
echo ============================================================
echo.
echo   Script directory: %SCRIPT_DIR%
echo.

:: ============================================================
::  JAVA_HOME Check
:: ============================================================
if not defined JAVA_HOME goto :no_java_home

:have_java_home
echo   JAVA_HOME: %JAVA_HOME%
if exist "%JAVA_HOME%\bin\java.exe" goto :java_ok
echo   [WARN] JAVA_HOME is set but java.exe not found.
echo          The service will fail to start until JDK is installed.
goto :java_ok

:no_java_home
echo   [WARN] JAVA_HOME is not set.
echo        Attempting auto-detection from PATH...
where java >nul 2>&1
if %errorlevel% neq 0 goto :java_not_found
where java > "%TEMP%\hongyi_java_path.txt"
set /p JAVA_BIN=<"%TEMP%\hongyi_java_path.txt"
del "%TEMP%\hongyi_java_path.txt"
for %%i in ("%JAVA_BIN%") do set "JAVA_HOME_DIR=%%~dpi"
set "JAVA_HOME=%JAVA_HOME_DIR:~0,-4%"
echo   Detected JAVA_HOME: %JAVA_HOME%
setx JAVA_HOME "%JAVA_HOME%" /M >nul 2>&1
goto :java_ok

:java_not_found
echo [ERROR] Java not found in PATH either.
echo         Please install JDK and set JAVA_HOME, then re-run.
pause
exit /b 1

:java_ok
echo.

:: ============================================================
::  WinSW Preparation
:: ============================================================
if not exist "%SCRIPT_DIR%\WinSW-x64.exe" goto :winsw_missing
goto :winsw_ok

:winsw_missing
echo [ERROR] WinSW-x64.exe not found in script directory.
pause
exit /b 1

:winsw_ok
echo   Preparing WinSW executables...
copy /Y "%SCRIPT_DIR%\WinSW-x64.exe" "%SCRIPT_DIR%\hongyi-server.exe" >nul 2>&1
if %errorlevel% neq 0 goto :copy_server_fail
copy /Y "%SCRIPT_DIR%\WinSW-x64.exe" "%SCRIPT_DIR%\hongyi-nginx.exe" >nul 2>&1
if %errorlevel% neq 0 goto :copy_nginx_fail
echo   Done.
echo.
goto :copy_ok

:copy_server_fail
echo [ERROR] Failed to copy WinSW-x64.exe to hongyi-server.exe
pause
exit /b 1

:copy_nginx_fail
echo [ERROR] Failed to copy WinSW-x64.exe to hongyi-nginx.exe
pause
exit /b 1

:copy_ok

:: ============================================================
::  Verify Required Files
:: ============================================================
if not exist "%SCRIPT_DIR%\hongyi-server.jar" goto :missing_jar
if not exist "%SCRIPT_DIR%\nginx-1.24.0\nginx.exe" goto :missing_nginx
if not exist "%SCRIPT_DIR%\hongyi-server.xml" goto :missing_xml1
if not exist "%SCRIPT_DIR%\hongyi-nginx.xml" goto :missing_xml2
goto :files_ok

:missing_jar
echo [ERROR] hongyi-server.jar not found.
pause
exit /b 1

:missing_nginx
echo [ERROR] nginx.exe not found in nginx-1.24.0\
pause
exit /b 1

:missing_xml1
echo [ERROR] hongyi-server.xml not found.
pause
exit /b 1

:missing_xml2
echo [ERROR] hongyi-nginx.xml not found.
pause
exit /b 1

:files_ok

:: ============================================================
::  Create Log Directories
:: ============================================================
if not exist "%SCRIPT_DIR%\logs\server" mkdir "%SCRIPT_DIR%\logs\server"
if not exist "%SCRIPT_DIR%\logs\nginx" mkdir "%SCRIPT_DIR%\logs\nginx"

:: ============================================================
::  Uninstall Old Services (if any)
:: ============================================================
call :uninstall_if_exists "hongyi-server" "%SCRIPT_DIR%\hongyi-server.exe"
call :uninstall_if_exists "hongyi-nginx" "%SCRIPT_DIR%\hongyi-nginx.exe"

:: ============================================================
::  Install hongyi-server (backend)
:: ============================================================
echo   Installing hongyi-server (backend)...
pushd "%SCRIPT_DIR%"
"%SCRIPT_DIR%\hongyi-server.exe" install
set "RC=%errorlevel%"
popd
if %RC% neq 0 goto :install_server_fail
echo   hongyi-server installed successfully.
echo.
goto :server_ok

:install_server_fail
echo [ERROR] Failed to install hongyi-server service (RC=%RC%).
pause
exit /b 1

:server_ok

:: ============================================================
::  Install hongyi-nginx (frontend)
:: ============================================================
echo   Installing hongyi-nginx (frontend)...
pushd "%SCRIPT_DIR%"
"%SCRIPT_DIR%\hongyi-nginx.exe" install
set "RC=%errorlevel%"
popd
if %RC% neq 0 goto :install_nginx_fail
echo   hongyi-nginx installed successfully.
echo.
goto :nginx_ok

:install_nginx_fail
echo [ERROR] Failed to install hongyi-nginx service (RC=%RC%).
echo         Cleaning up hongyi-server...
"%SCRIPT_DIR%\hongyi-server.exe" stop >nul 2>&1
timeout /t 2 >nul 2>&1
"%SCRIPT_DIR%\hongyi-server.exe" uninstall >nul 2>&1
pause
exit /b 1

:nginx_ok

:: ============================================================
::  Set Service Descriptions
:: ============================================================
sc description hongyi-server "Hongyi Spring Boot Backend Service (port 49090)" >nul 2>&1
sc description hongyi-nginx "Hongyi Nginx Frontend Service (port 80)" >nul 2>&1

:: ============================================================
::  Start Services
:: ============================================================
echo   Starting hongyi-server...
sc start hongyi-server >nul 2>&1
if %errorlevel% neq 0 echo   [WARN] hongyi-server failed to start immediately.
echo.

timeout /t 5 /nobreak >nul 2>&1

echo   Starting hongyi-nginx...
sc start hongyi-nginx >nul 2>&1
if %errorlevel% neq 0 echo   [WARN] hongyi-nginx failed to start immediately.
echo.

:: ============================================================
echo ============================================================
echo   Installation Complete!
echo ============================================================
echo.
echo   Services installed:
echo     - hongyi-server  (Backend,  port 49090, auto start)
echo     - hongyi-nginx   (Frontend, port 80,    delayed auto start)
echo.
echo   Manage via:
echo     services.msc
echo     sc start hongyi-server   /  sc stop  hongyi-server
echo     sc start hongyi-nginx    /  sc stop  hongyi-nginx
echo.
pause
exit /b 0

:need_admin
echo [ERROR] Administrator privileges required.
echo Please right-click this script and select "Run as administrator".
pause
exit /b 1

:: ============================================================
::  Subroutine: Uninstall service if it exists
:: ============================================================
:uninstall_if_exists
sc query %1 >nul 2>&1
if %errorlevel% neq 0 goto :uninstall_done

echo   Removing existing %1 service...
if exist "%2" goto :use_winsw

:: No WinSW exe, use sc
sc stop %1 >nul 2>&1
timeout /t 2 >nul 2>&1
sc delete %1 >nul 2>&1
echo   Uninstalled old %1.
goto :uninstall_done

:use_winsw
"%2" stop >nul 2>&1
timeout /t 2 >nul 2>&1
"%2" uninstall >nul 2>&1
echo   Uninstalled old %1.

:uninstall_done
goto :eof
