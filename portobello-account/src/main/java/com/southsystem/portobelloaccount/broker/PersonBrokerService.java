package com.southsystem.portobelloaccount.broker;

import com.southsystem.portobelloaccount.broker.model.PersonCreatedMessage;
import com.southsystem.portobelloaccount.broker.model.PersonTypeEnum;
import com.southsystem.portobelloaccount.config.AccountParameterConfig;
import com.southsystem.portobelloaccount.config.BrokerInput;
import com.southsystem.portobelloaccount.model.Account;
import com.southsystem.portobelloaccount.model.enumeration.AccountTypeEnum;
import com.southsystem.portobelloaccount.repository.AccountRepository;
import com.southsystem.portobelloaccount.service.MessageService;
import com.southsystem.portobelloaccount.util.AccountUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class PersonBrokerService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountParameterConfig accountParameterConfig;

    @Autowired
    MessageService messageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonBrokerService.class);

    @StreamListener(BrokerInput.PERSON_CREATED)
    public void createAccount(PersonCreatedMessage message) {
        Account account = new Account(AccountUtils.generateAccountId(), accountParameterConfig.getAgency(), defineAccountType(message.getPersonType()), message.getPersonId());
        accountRepository.save(account).subscribe(
                it -> {
                   LOGGER.info(String.format("Account created: %s", it.getAccountId()));
                    messageService.sendAccountCreatedMessage(it.getAccountId(), message.getScore());
                },
                e -> LOGGER.error("Error creating account", e)
        );
    }

    private AccountTypeEnum defineAccountType(PersonTypeEnum personTypeEnum) {
        return PersonTypeEnum.PF.equals(personTypeEnum) ? AccountTypeEnum.C : AccountTypeEnum.E;
    }
}
