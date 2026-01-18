@echo off
TITLE Aion Classic 2.4 - Game Server Console
SET PATH="C:\Program Files\Java\jdk1.7.0_80\bin"
set JAVA_HOME=C:\Program Files\Java\jdk1.7.0_80
:START
CLS

echo Starting Aion Classic 2.4 Game Server.

REM -------------------------------------  
REM Default parameters for a basic server.
java -Xms1280m -Xmx8192m -XX:MaxHeapSize=8192m -Xdebug -XX:MaxNewSize=24m -XX:NewSize=24m -XX:+UseParNewGC -XX:+CMSParallelRemarkEnabled -XX:+UseConcMarkSweepGC -XX:-UseSplitVerifier -ea -javaagent:./libs/al-commons.jar -cp ./libs/*;./libs/AL-Game.jar com.aionemu.gameserver.GameServer
REM -------------------------------------
SET CLASSPATH=%OLDCLASSPATH%

if ERRORLEVEL 2 goto restart
if ERRORLEVEL 1 goto error
if ERRORLEVEL 0 goto end

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
