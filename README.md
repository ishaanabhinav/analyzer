# analyzer

Log Analyzer made using Spring Boot

This project uses spring boot to parse log files and retrieve the result via API.

Tech Stack:
-> Spring Initializer
-> Spring Boot
-> Spring JPA
-> Swagger UI

Steps:
1. Git clone this repository 
2. mvnw install -f pom.xml
3. mvn spring-boot:run

I would suggest to export this project in an IDE(Eclipse, VS Code) and run it using spring dev tools.


Assumptions Made:
-> Every entry in the given log file will be a single line entry and will follow the same format as below.
"2018-09-18 04:49:38,215 ERROR (defaulttask-95) IP-Address=157.49.141.133#,!User-Agent=Mozilla/5.0(WindowsNT10.0;WOW64;Trident/7.0;rv:11.0)likeGecko#,!X-Request-From=UIX#,!Request-Type=POST#,!API=/v1/admin/developers#,!User-Login=test@demo.com#,!User-Name=testUser#,!EnterpriseId=2#,!EnterpriseName=Enterprise-2#,!Auth-Status=#,!Status-Code=200#,!Response-Time=346#,!Request-Body=";

-> you can view the API documentation after running the projcet at :
http://localhost:8080/swagger-ui/

-> There are 3 end points as of now:

1. /log-parser/all -> This will take a paging criteria(see jpa pageable documentation or swagger api documentation on the above link) and return entries based on that.
  The entries can be sorted based on date and other aspects.

2. /log-parser/sampleParse -> This runs a sample parse, the input can be a single line string which follows the log format.
  If no input is given, it will default to a hard coded value.

3. /log-parser/parseLog -> This triggers the main parse Log methid, the input can be the file path or it defaults to the project directory and searches for "activities.log"


Future Improvements:
  -> Implement RSQL to give the capability to filter the logs based on multiple parameters.
  -> API end points to clean log entries based on certain parameters
  -> Shell capability using spring boot cli
  
