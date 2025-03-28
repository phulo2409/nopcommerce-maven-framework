set ProjectPath=%~dp0
java -jar "%ProjectPath%\libraries\selenium-server-4.29.0.jar" node --hub http:://192.168.100.83:4444 --selenium-manager true