@ECHO off
TITLE Aion Classic 2.4  - Login Server Console
REM Set correct Path
REM SET PATH="C:\Program Files\Java\jdk1.8.0_202"

:START
CLS

echo Starting Aion Classic 2.4 Login Server.
REM -------------------------------------
REM Default parameters for a basic server.
java -cp ./libs/*;AL-Login.jar com.aionemu.loginserver.LoginServer
REM -------------------------------------
SET CLASSPATH=%OLDCLASSPATH%

if ERRORLEVEL 2 goto restart
IF ERRORLEVEL 1 GOTO ERROR
IF ERRORLEVEL 0 GOTO END

REM Restart...
:restart
echo.
echo Administrator Restart ...
echo.
goto start

REM Error...
:error
echo.
echo Server terminated abnormaly ...
echo.
goto end

REM End...
:end
echo.
echo Server terminated ...
echo.
pause
