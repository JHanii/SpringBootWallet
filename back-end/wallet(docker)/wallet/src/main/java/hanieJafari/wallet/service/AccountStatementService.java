package hanieJafari.wallet.service;

import hanieJafari.wallet.models.Account;
import hanieJafari.wallet.models.AccountStatement;
import hanieJafari.wallet.models.Transaction;
import hanieJafari.wallet.repositories.AccountRepository;
import hanieJafari.wallet.repositories.AccountStatementRepository;
import hanieJafari.wallet.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountStatementService {

    private final AccountStatementRepository accountStatementRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountStatementService(AccountStatementRepository accountStatementRepository,
                                   AccountRepository accountRepository,
                                   TransactionRepository transactionRepository) {
        this.accountStatementRepository = accountStatementRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public AccountStatement createAccountStatement(Long accountId) {
        // Fetch the account by ID
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Create the account statement
        AccountStatement accountStatement = new AccountStatement();
        accountStatement.setAccount(account);
        accountStatement.setAccountNumber(account.getAccountNumber());
        accountStatement.setBalance(account.getBalance());
        List<Transaction> transactions = new ArrayList<>();
        accountStatement.setTransactions(transactions);

        // Save the account statement
        AccountStatement savedStatement = accountStatementRepository.save(accountStatement);

        account.setAccountStatement(savedStatement);

        return savedStatement;
    }
}
