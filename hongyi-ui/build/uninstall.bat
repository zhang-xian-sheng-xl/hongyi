@echo off
chcp 65001 >nul 2>&1
setlocal EnableDelayedExpansion
title Hongyi Service Uninstaller

:: ============================================================
::  Hongyi Services Uninstaller
::  Removes hongyi-server and hongyi-nginx Windows services.
:: ============================================================

:: --- Check admin ---
net session >nul 2>&1
if %errorlevel% neq 0 goto :need_admin

:: --- Resolve script directory ---
set "SCRIPT_DIR=%~dp0"
set "SCRIPT_DIR=%SCRIPT_DIR:~0,-1%"

:: --- Header ---
echo ============================================================
echo   Hongyi Services Uninstaller
echo ============================================================
echo.

:: --- Track failures ---
set "ANY_FAILED=0"

:: ============================================================
::  Remove hongyi-server
:: ============================================================
call :remove_service "hongyi-server" "%SCRIPT_DIR%\hongyi-server.exe" "java.exe"

:: ============================================================
::  Remove hongyi-nginx
:: ============================================================
call :remove_nginx

:: ============================================================
::  Cleanup .exe files
:: ============================================================
echo   Cleaning up temporary executables...
if exist "%SCRIPT_DIR%\hongyi-server.exe" (
    del /f /q "%SCRIPT_DIR%\hongyi-server.exe" >nul 2>&1
    echo   Removed hongyi-server.exe
)
if exist "%SCRIPT_DIR%\hongyi-nginx.exe" (
    del /f /q "%SCRIPT_DIR%\hongyi-nginx.exe" >nul 2>&1
    echo   Removed hongyi-nginx.exe
)

:: ============================================================
echo.
echo ============================================================
if %ANY_FAILED% equ 0 goto :all_ok
echo   Uninstallation completed with errors.
echo   Please check services.msc for any remaining services.
echo ============================================================
echo.
pause
exit /b 1

:all_ok
echo   Uninstallation Complete!
echo   All services have been removed.
echo ============================================================
echo.
pause
exit /b 0

:need_admin
echo [ERROR] Administrator privileges required.
echo Please right-click this script and select "Run as administrator".
pause
exit /b 1

:: ============================================================
::  Subroutine: remove_service SERVICE_NAME WINSW_EXE PROCESS_NAME
:: ============================================================
:remove_service
sc query %1 >nul 2>&1
if %errorlevel% neq 0 goto :service_not_found

echo   Processing %1...

:: Try WinSW stop first
if exist "%2" (
    "%2" stop >nul 2>&1
)
timeout /t 3 >nul 2>&1

:: Fallback to sc stop
sc stop %1 >nul 2>&1
timeout /t 3 >nul 2>&1

:: Force kill if still running
sc query %1 | findstr /i "RUNNING" >nul 2>&1
if %errorlevel% neq 0 goto :service_stopped

echo   [WARN] Service still running, force-killing process...
taskkill /f /im %3 /fi "SERVICES eq %1" >nul 2>&1
timeout /t 2 >nul 2>&1

:service_stopped
:: Uninstall via WinSW if available
if exist "%2" goto :do_winsw_uninstall

:: Fallback to sc delete
sc delete %1 >nul 2>&1
if %errorlevel% equ 0 goto :service_uninstalled
echo   [ERROR] Failed to remove %1 via sc.
set "ANY_FAILED=1"
goto :service_not_found

:do_winsw_uninstall
"%2" uninstall >nul 2>&1
if %errorlevel% equ 0 goto :service_uninstalled
echo   [WARN] WinSW uninstall returned error, trying sc delete...
sc delete %1 >nul 2>&1
if %errorlevel% equ 0 goto :service_uninstalled
echo   [ERROR] Failed to remove %1.
set "ANY_FAILED=1"
goto :service_not_found

:service_uninstalled
echo   %1 removed.

:service_not_found
goto :eof

:: ============================================================
::  Subroutine: remove_nginx (nginx-specific cleanup)
:: ============================================================
:remove_nginx
call :remove_service "hongyi-nginx" "%SCRIPT_DIR%\hongyi-nginx.exe" "nginx.exe"

:: Extra: try nginx -s quit if exe still exists after removal
if exist "%SCRIPT_DIR%\nginx-1.24.0\nginx.exe" (
    "%SCRIPT_DIR%\nginx-1.24.0\nginx.exe" -s quit >nul 2>&1
    timeout /t 2 >nul 2>&1
)
goto :eof
