package com.theonewhocode.accounts.service.impl;

import com.theonewhocode.accounts.dto.AccountsDto;
import com.theonewhocode.accounts.dto.CardsDto;
import com.theonewhocode.accounts.dto.CustomerDetailsDto;
import com.theonewhocode.accounts.dto.LoansDto;
import com.theonewhocode.accounts.entity.Accounts;
import com.theonewhocode.accounts.entity.Customer;
import com.theonewhocode.accounts.exception.ResourceNotFoundException;
import com.theonewhocode.accounts.mapper.AccountsMapper;
import com.theonewhocode.accounts.mapper.CustomerMapper;
import com.theonewhocode.accounts.repository.AccountsRepository;
import com.theonewhocode.accounts.repository.CustomerRepository;
import com.theonewhocode.accounts.service.ICustomerService;
import com.theonewhocode.accounts.service.client.CardsFeignClient;
import com.theonewhocode.accounts.service.client.LoansFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CardsFeignClient cardsFeignClient;

    @Autowired
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoan(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCard(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
