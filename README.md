# HeadSpin Hackathon

#### Team Name : TribeTester

Technology Stack :

1.Selenium Webdriver <br>
2.Java <br>
3.TestNG <br>
4.Maven<br>


<b> <u>Team Members :</u></b> <br>
Saurabh Gupta : saurabhfromautomation@gmail.com <br>
Anil Kumar Gompa : anil.gompa.kumar@gmail.com<br>
Shivani Sirige : shivanisirige@gmail.com<br>
Aman Verma : amanverma1296@gmail.com<br>

<b> <u>Setup :</u></b> <br>
Clone this project to your local environment

git clone https://github.com/TribeTester/TribeTesterWebMobile.git  

Open the cloned project in installed IDE.

Wait for maven to load all the dependencies. (It may take 2-3 minutes depending on your connection)

If you see any error or dependencies not downloaded, run below command on project root directory via Command Prompt or Terminal.

mvn clean install -DskipTests=true

### Framework Introduction :
- The following are the directories are defined in the framework. 

| Directory        |Description           | 
| ------------- |---------------| 
| Resources      | This directory contains the test resources used during the test execution like config properties files,driver and Log4j | 
| Tests | This folder contain test of Make my Trip  | 
| utils | This directory contains the all the common method and utilities like Action class,Date Utils, ExtentManeger,Extent test Manger,File Utils,Read properties,Test Listner.|
|Base | This folder contain Base Test Class, Invoke browser class to intalise the browser like local and remote |
|TestNG| This directory is test project directory where all the test cases and it's configurations are defined., this directory use extends the Test Framework directory for using test framework features.|

---
## Features
 - Batch file execution
 - Local Dashboard (Run time report)
 - Logging
 - Listeners
 - Object Repository
 - Reporting
 - Utilities

 __Details:__

 - _Batch File Execution_
   User can execute suites by simply executing batch files:
    - Windows: Execute the tests by double click on a `RunTests.bat` file

 - _Logging_
    - Log4j is configured to capture the test execution logs


 - _Reporting_
    - The framework generates `index.html` report. It resides in the same `target\surefire-reports` folder
    - This reports gives the link to all the different component of the TestNG reports like Groups & Reporter Output
    - On clicking these will display detailed descriptions of execution
    - As this is a html report you can open it with browser
    - Features of report
       - Test summary report (Pass, Fail, Skipped, Duration)
       - Detailed exception stacktrace of failed tests


 - _Local Dashboard (Run time report)_
    - Ability to view the current live test execution

 - _Utilities_
    - Different Utilities exist to read Excel, JSON, XML, PDF, Properties files	

 - _Listeners_
    - `Test Listener` -  To do some actions like taking  updating _run time report_ based on test results
	  - Like _On Failure_, _On Success_, _On Start_ etc.,


---

__Configuration in Config.properties:__
#Possible values for ModeOfExecution Local|Remote
ModeOfExecution=Local
#Possible values for Browser Chrome|Firefox
Browser=chrome
WebAppUrl= Make my trip url
RemoteServerHeadSpin = Your remote URL of Headspin instance



## Prerequisites

 - __Java__
    - Version: `jdk-1.8` or higher
	- Website: [link](https://java.com/en/)

 - __Apache Maven__
    - Version: `3` or higher
	- Website: [link](http://maven.apache.org/)
	- Setup Help [link_1](http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html), [link_2](http://www.tutorialspoint.com/maven/maven_environment_setup.htm)

 - __TestNG__
   - Version: `6` or higher
	- Website: [link](https://testng.org/doc/index.html)
