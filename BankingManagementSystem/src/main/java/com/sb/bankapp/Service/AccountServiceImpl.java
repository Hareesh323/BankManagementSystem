package com.sb.bankapp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.sb.bankapp.Entity.Account;
import com.sb.bankapp.repo.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository repo;

	@Override
	public Account createAccount(Account account) {
		Account account_saved = repo.save(account);
		return account_saved;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
		Optional<Account> account = repo.findById(accountNumber);
		if (account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account account_found = account.get();
		return account_found;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		List<Account> ListOfAccounts = repo.findAll();

		return ListOfAccounts;
	}

	@Override
	public Account depositAmount(Long accountNumber, Double amount) {
		Optional<Account> account = repo.findById(accountNumber);
		if (account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account accountpresent = account.get();
		Double totalBalance = accountpresent.getAccount_balance() + amount;
		accountpresent.setAccount_balance(totalBalance);
		return accountpresent;
	}

	@Override
	public Account withdrawAmount(Long accountNumber, Double amount) {
		Optional<Account> account = repo.findById(accountNumber);
		if (account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account accountpresent = account.get();
		Double accountBalance = accountpresent.getAccount_balance() - amount;
		accountpresent.setAccount_balance(accountBalance);
		repo.save(accountpresent);
		return accountpresent;
	}

	@Override
	public void closeAccount(Long accountNumber) {
		getAccountDetailsByAccountNumber(accountNumber);
		repo.deleteById(accountNumber);

	}
}
