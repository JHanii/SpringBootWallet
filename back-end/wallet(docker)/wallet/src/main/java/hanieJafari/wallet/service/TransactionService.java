package hanieJafari.wallet.service;

import hanieJafari.wallet.models.AccountStatement;
import hanieJafari.wallet.models.Transaction;
import hanieJafari.wallet.repositories.TransactionRepository;
import hanieJafari.wallet.repositories.AccountStatementRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountStatementRepository accountStatementRepository;
    private final AccountService accountService;

    private static final BigDecimal MIN_WITHDRAWAL_AMOUNT = new BigDecimal("100000");
    private static final BigDecimal MAX_DAILY_WITHDRAWAL_AMOUNT = new BigDecimal("10000000");

    public TransactionService(TransactionRepository transactionRepository, AccountStatementRepository accountStatementRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountStatementRepository = accountStatementRepository;
        this.accountService = accountService;
    }

    // Create a new transaction and an update the account statement
    public Transaction createTransaction(Transaction transaction) {
        // Check if the AccountStatement exists
        AccountStatement accountStatement = accountStatementRepository.findById(transaction.getAccountStatement().getId())
                .orElseThrow(() -> new RuntimeException("Account statement not found"));

        if(!isValid(transaction)) {
            transaction.setFailedOn();
        }
        // Add transaction to the account statement
        accountStatement.getTransactions().add(transaction);

        // Save the transaction
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Update the balance
        if(!transaction.getFailed()){
            updateAccountStatementBalance(accountStatement, transaction);
        }
        return savedTransaction;
    }

    // Update the balance based on the transaction
    private void updateAccountStatementBalance(AccountStatement accountStatement, Transaction transaction) {
        if (transaction.getType().equalsIgnoreCase("DEPOSIT")) {
            accountStatement.setBalance(accountStatement.getBalance().add(transaction.getAmount()));
            accountService.getAccountById(accountStatement.getAccount().getId()).setBalance(accountStatement.getBalance());
        } else if (transaction.getType().equalsIgnoreCase("WITHDRAWAL")) {
            if(accountStatement.getBalance().compareTo(transaction.getAmount()) >=  0)
            {accountStatement.setBalance(accountStatement.getBalance().subtract(transaction.getAmount()));
             accountService.getAccountById(accountStatement.getAccount().getId()).setBalance(accountStatement.getBalance());
            }
        }
        // Save the updates
        accountStatementRepository.save(accountStatement);
    }

    public List<Transaction> getTransactionsByAccountStatementId(Long accountStatementId) {
        AccountStatement accountStatement = accountStatementRepository.findById(accountStatementId)
                .orElseThrow(() -> new RuntimeException("Account statement not found"));

        return accountStatement.getTransactions();
    }

    public boolean isValid(Transaction transaction) {
        // Check if the transaction type is "WITHDRAWAL"
        if ("WITHDRAWAL".equalsIgnoreCase(transaction.getType())) {
            // the amount must be at least 100,000
            if (transaction.getAmount() == null || transaction.getAmount().compareTo(MIN_WITHDRAWAL_AMOUNT) < 0) {
                return false;
            }

            BigDecimal totalDailyWithdrawals = calculateDailyWithdrawals(transaction.getDate());
            System.out.println(totalDailyWithdrawals);
            // total amount must be up to 10000000
            if (totalDailyWithdrawals.add(transaction.getAmount()).compareTo(MAX_DAILY_WITHDRAWAL_AMOUNT) > 0) {
                return false;
            }
        }

        // For other transaction types
        return true;
    }

    private BigDecimal calculateDailyWithdrawals(LocalDate transactionDate) {

        BigDecimal totalWithdrawals = transactionRepository.sumWithdrawalsByDate(transactionDate);

        // In case of no withdrawals transaction
        return totalWithdrawals != null ? totalWithdrawals : BigDecimal.ZERO;
    }
}