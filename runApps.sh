!/bin/bash
clear
cd dos-server
(./gradlew clean build bootrun &>/dev/null) ;
java -jar ../dos-client/build/libs/dos-client-all-1.0-SNAPSHOT.jar




