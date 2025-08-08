package com.sb.bankapp.Service;

import java.util.List;

import com.sb.bankapp.Entity.Account;

public interface AccountService {

	public Account createAccount(Account account);

	public Account getAccountDetailsByAccountNumber(Long accountNumber);

	public List<Account> getAllAccountDetails();

	public Account depositAmount(Long accountNumber, Double amount);

	public Account withdrawAmount(Long accountNumber, Double amount);

	public void closeAccount(Long accountNumber);

}
