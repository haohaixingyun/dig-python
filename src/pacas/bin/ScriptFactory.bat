@echo off
cls
echo ------------------------------------------------------------------
echo Start org.pacas.ScriptFactory
REM Created By Jeffrey Bian Mar 2008
java -cp .;pacas.jar;xalan.jar;xercesImpl.jar;serializer.jar;jacl.jar;jacob.jar;tcljava.jar org.pacas.ScriptFactory %1 %2 -icmd
echo ------------------------------------------------------------------
echo.
echo Done.
