@echo off
set APPLICATION=java-app-0.0.1.jar
set BIN_SCRIPT_PATH=%~dp0
set LIB_DIR_PATH=%BIN_SCRIPT_PATH:~0,-5%\lib
java -Djava.ext.dirs=%LIB_DIR_PATH% -jar %LIB_DIR_PATH%\%APPLICATION%
