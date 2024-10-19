package hanieJafari.wallet.controllers;

import hanieJafari.wallet.models.Transaction;
import hanieJafari.wallet.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Create a new transaction
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }

    // Get all transactions for an account statement
    @GetMapping("/account-statement/{accountStatementId}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountStatementId(@PathVariable Long accountStatementId) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountStatementId(accountStatementId);
        return ResponseEntity.ok(transactions);
    }
}
