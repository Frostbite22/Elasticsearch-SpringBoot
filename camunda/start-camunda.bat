@echo off

set "CATALINA_HOME=%CD%\server\apache-tomcat-9.0.12"

echo "starting camunda BPM platform 7.10.0 on Apache Tomcat 9.0.12"

cd server\apache-tomcat-9.0.12\bin\
start startup.bat

ping -n 5 localhost > NULL
start http://localhost:8080/camunda-welcome/index.html
 