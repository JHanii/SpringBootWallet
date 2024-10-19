package hanieJafari.wallet.service;

import hanieJafari.wallet.models.Account;
import hanieJafari.wallet.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountStatementService accountStatementService;

    private final UserService userService;

    public AccountService(AccountRepository accountRepository, AccountStatementService accountStatementService, UserService userService) {
        this.accountRepository = accountRepository;
        this.accountStatementService = accountStatementService;
        this.userService = userService;
    }

    public Account createAccount(Account account) {
        Account a = accountRepository.save(account);
        userService.getUserById(account.getUser().getId()).addAccounts(a);
        accountStatementService.createAccountStatement(account.getId());
        return a;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account updateAccountBalance(Long id, BigDecimal balance) {
        Account account = getAccountById(id);
        account.setBalance(balance);
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}

