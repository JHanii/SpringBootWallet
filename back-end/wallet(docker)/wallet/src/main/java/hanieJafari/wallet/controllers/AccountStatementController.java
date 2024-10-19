package hanieJafari.wallet.controllers;

import hanieJafari.wallet.models.AccountStatement;
import hanieJafari.wallet.service.AccountService;
import hanieJafari.wallet.service.AccountStatementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account-statements")
public class AccountStatementController {

    private final AccountStatementService accountStatementService;
    private final AccountService accountService;

    public AccountStatementController(AccountStatementService accountStatementService, AccountService accountService) {
        this.accountStatementService = accountStatementService;
        this.accountService = accountService;
    }

    // Create a new account statement
    //@PostMapping
    public ResponseEntity<AccountStatement> createAccountStatement(@RequestBody long accountID) {
        AccountStatement createdStatement = accountStatementService.createAccountStatement(accountID);
        return ResponseEntity.ok(createdStatement);
    }

    // Get account statement by accountID
    @GetMapping("/{id}")
    public ResponseEntity<AccountStatement> getAccountStatementByAccountId(@PathVariable Long id) {
        AccountStatement accountStatement = accountService.getAccountById(id).getAccountStatement();
        return ResponseEntity.ok(accountStatement);
    }

}