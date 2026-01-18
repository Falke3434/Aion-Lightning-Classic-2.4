@ECHO off
TITLE Aion Classic 2.4 - Chat Server Console
REM Set correct Path
REM SET PATH="C:\Program Files\Java\jdk1.8.0_202"

:START
CLS

ECHO Starting Aion Classic 2.4 Chat Server.
java -cp ./libs/*;AL-Chat.jar com.aionemu.chatserver.ChatServer
SET CLASSPATH=%OLDCLASSPATH%
IF ERRORLEVEL 2 GOTO START
IF ERRORLEVEL 1 GOTO ERROR
IF ERRORLEVEL 0 GOTO END
:ERROR
ECHO.
ECHO Chat Server has terminated abnormaly!
ECHO.
PAUSE
EXIT
:END
ECHO.
ECHO Chat Server is terminated!
ECHO.
PAUSE
EXIT
