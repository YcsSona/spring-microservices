package com.theonewhocode.message.functions;

import com.theonewhocode.message.dto.AccountsMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {

    private static final Logger log = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean // monitored by Spring Cloud Function
    public Function<AccountsMsgDto, AccountsMsgDto> email() {
        return accountsMsgDto -> {
            log.info("Sending email with the details: " + accountsMsgDto.toString());
            return accountsMsgDto;
        };
    }

    @Bean // monitored by Spring Cloud Function
    public Function<AccountsMsgDto, Long> sms() {
        return accountsMsgDto -> {
            log.info("Sending sms with the details: " + accountsMsgDto.toString());
            return accountsMsgDto.accountNumber();
        };
    }

}
