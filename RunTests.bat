
echo Executing using batch file

pushd %~dp0%

mvn clean test -Dsurefire.suiteXmlFiles=TestngXml/testng.xml

pause
