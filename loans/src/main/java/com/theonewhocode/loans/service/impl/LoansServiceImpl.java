package com.theonewhocode.loans.service.impl;

import com.theonewhocode.loans.constants.LoansConstant;
import com.theonewhocode.loans.entity.Loans;
import com.theonewhocode.loans.exception.LoanAlreadyExistsException;
import com.theonewhocode.loans.repository.LoansRepository;
import com.theonewhocode.loans.service.ILoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class LoansServiceImpl implements ILoansService {

    @Autowired
    private LoansRepository loansRepository;


    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);

        optionalLoans.ifPresent(loan -> {
            throw new LoanAlreadyExistsException("Loan already registered with given mobile number " + mobileNumber);
        });

        loansRepository.save(createNewLoan(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstant.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstant.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstant.NEW_LOAN_LIMIT);
        newLoan.setCreatedAt(LocalDateTime.now());
        newLoan.setCreatedBy("ANONYMOUS");

        return newLoan;
    }
}
