package com.example.analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogService {

    @Resource
    LogRepository logRepository;

    private static final String defaultFilePath = "/Users/ishaanabhinav/bo-log-analyzer/analyzer/activites.log";

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    public void runLogParser(String filePath) {
        if (filePath == null || filePath.trim().length() == 0){
            filePath = defaultFilePath.toString();
        }

        logger.info("Reading file =" + filePath+".");

        // logger.info("Default file =" + defaultFilePath);
        
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            stream.forEach(this::parseLogFile);

        } catch (IOException e) {
            throw new RuntimeException("Fail to open file ", e);
        }
        logger.info("OK!");
    }

    public void parseLogFile(String line) {
        if(line.trim() == "") return;

        String dateTime = line.substring(0,line.indexOf(','));

        line = line.substring(line.indexOf(')')+1).trim();
        
        String[] data = line.split("#,!");
        
        Log log = new Log();
        log.setDate(LocalDateTime.parse(dateTime, ParserConstants.LOG_DATE_FORMAT));
        for(String log_property:data){
            String[] key_value = log_property.split("=");
            try {
                setLogData(log,key_value[0],key_value[1]);
            } catch (Exception e) {
                logger.error("String not key value pair -> "+ log_property + " -> e = " + e);
            }
        }
        logRepository.save(log);
        logger.info("OK!");
    }

    public Log parseLogLine(){
        String line = "2018-09-18 04:49:38,215 ERROR (defaulttask-95) IP-Address=157.49.141.133#,!User-Agent=Mozilla/5.0(WindowsNT10.0;WOW64;Trident/7.0;rv:11.0)likeGecko#,!X-Request-From=UIX#,!Request-Type=POST#,!API=/v1/admin/developers#,!User-Login=test@demo.com#,!User-Name=testUser#,!EnterpriseId=2#,!EnterpriseName=Enterprise-2#,!Auth-Status=#,!Status-Code=200#,!Response-Time=346#,!Request-Body=";
        
        String dateTime = line.substring(0,line.indexOf(','));

        line = line.substring(line.indexOf(')')+1).trim();
        
        /*Sample Log Data after splitting */
        // IP-Address=157.49.141.133
        // User-Agent=Mozilla/5.0(WindowsNT10.0;WOW64;Trident/7.0;rv:11.0)likeGecko
        // X-Request-From=UIX
        // Request-Type=POST
        // API=/v1/admin/developers
        // User-Login=test@demo.com
        // User-Name=testUser
        // EnterpriseId=2
        // EnterpriseName=Enterprise-2
        // Auth-Status=
        // Status-Code=200
        // Response-Time=346Request-Body=
        String[] data = line.split("#,!");
        
        Log log = new Log();
        log.setDate(LocalDateTime.parse(dateTime, ParserConstants.LOG_DATE_FORMAT));
        for(String log_property:data){
            String[] key_value = log_property.split("=");
            try {
                setLogData(log,key_value[0],key_value[1].toString());
            } catch (Exception e) {
                logger.error("String not key value pair -> "+ log_property + " -> e = " + e);
            }
        }
        logRepository.save(log);
        return log;
    }

    public Log setLogData(Log log, String key, String value){
        switch(key){
            case "IP-Address":
                log.setIpAddress(value);
            case "User-Agent":
                log.setUserAgent(value);
            case "Request-Type":
                log.setRequestType(value);
            case "API":
                log.setApi(value);
            case "User-Login":
                log.setUserLogIn(value);
            case "User-Name":
                log.setUserName(value);
            case "EnterpriseId":
                log.setEnterpriseId(Long.valueOf(value));
            case "EnterpriseName":
                log.setEnterpriseName(value);
            case "Status-Code":
                log.setStatusCode(Long.valueOf(value));
        }
        return log;
    }
}
