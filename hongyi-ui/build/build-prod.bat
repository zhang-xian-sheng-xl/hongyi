@echo off
chcp 65001 >nul 2>&1
title Hongyi UI - Production Build

:: ============================================================
::  Hongyi UI Production Build
::  Script location: build\build-prod.bat
::  Command: node --max_old_space_size=4096 ./node_modules/vite/bin/vite.js build --mode prod
:: ============================================================

:: Get the directory of this script (build\)
set "SCRIPT_DIR=%~dp0"
set "SCRIPT_DIR=%SCRIPT_DIR:~0,-1%"

:: Go to project root (parent of build\)
set "PROJECT_ROOT=%SCRIPT_DIR%\.."
for %%i in ("%PROJECT_ROOT%") do set "PROJECT_ROOT=%%~fi"

:: Target deployment directory
set "DEPLOY_DIR=D:\hongyi\dist-prod"

echo ============================================================
echo   Hongyi UI - Production Build
echo ============================================================
echo.
echo   Script:     %SCRIPT_DIR%
echo   Project:    %PROJECT_ROOT%
echo   Mode:       prod
echo   Command:    node --max_old_space_size=4096 ./node_modules/vite/bin/vite.js build --mode prod
echo   Deploy:     %DEPLOY_DIR%
echo.

:: Check Node.js
call :check_node
call :check_vite
call :check_env_prod

:: Clean old deployment directory
call :clean_deploy_dir

:: Run build
cd /d "%PROJECT_ROOT%"
echo   Building... Please wait, this may take a few minutes.
echo.

node --max_old_space_size=4096 ./node_modules/vite/bin/vite.js build --mode prod
set "BUILD_RC=%errorlevel%"

echo.
if %BUILD_RC% neq 0 goto :build_failed

:: Build success
echo   Build SUCCESS!
echo   Output: %PROJECT_ROOT%\dist
echo.

:: Copy to deployment directory
call :copy_to_deploy
echo.

echo ============================================================
echo   Build and Deploy Complete!
echo ============================================================
echo   Deployment: %DEPLOY_DIR%
echo.
pause
exit /b 0

:build_failed
echo   Build FAILED (RC=%BUILD_RC%)
echo.
pause
exit /b %BUILD_RC%

:: ============================================================
::  Subroutines
:: ============================================================
:check_node
node --version >nul 2>&1
if %errorlevel% neq 0 (
    echo   [ERROR] Node.js not found in PATH.
    echo           Please install Node.js and add it to PATH.
    pause
    exit /b 1
)
for /f "tokens=*" %%a in ('node --version') do echo   Node.js:    %%a
exit /b 0

:check_vite
if not exist "%PROJECT_ROOT%\node_modules\vite\bin\vite.js" (
    echo   [ERROR] vite.js not found at node_modules\vite\bin\vite.js
    echo           Please run 'pnpm install' or 'npm install' first.
    pause
    exit /b 1
)
echo   Vite:       %PROJECT_ROOT%\node_modules\vite\bin\vite.js
exit /b 0

:check_env_prod
if not exist "%PROJECT_ROOT%\.env.prod" (
    echo   [WARN] .env.prod not found in project root.
    echo          The build will use default environment variables.
    echo.
    exit /b 0
)
echo   Env file:   %PROJECT_ROOT%\.env.prod
exit /b 0

:clean_deploy_dir
if not exist "%DEPLOY_DIR%" goto :clean_done

echo   Cleaning old deployment directory...
echo     %DEPLOY_DIR%
rmdir /s /q "%DEPLOY_DIR%"
if %errorlevel% neq 0 (
    echo   [WARN] Failed to remove old deployment directory.
    echo          It may be locked by a running service.
    echo          Please stop hongyi-nginx service and try again.
    pause
    exit /b 1
)
echo   Old deployment directory removed.

:clean_done
exit /b 0

:copy_to_deploy
if not exist "%PROJECT_ROOT%\dist" (
    echo   [ERROR] Build output 'dist' folder not found.
    echo           The build may have failed or output to a different directory.
    exit /b 1
)

echo   Copying build output to deployment directory...
xcopy /e /i /h /y "%PROJECT_ROOT%\dist\*" "%DEPLOY_DIR%\" >nul 2>&1
if %errorlevel% neq 0 (
    echo   [ERROR] Failed to copy dist to %DEPLOY_DIR%
    exit /b 1
)
echo   Deployment directory updated: %DEPLOY_DIR%
exit /b 0

