package hanieJafari.wallet.repositories;

import hanieJafari.wallet.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountStatementId(Long accountStatementId);
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'WITHDRAWAL' AND t.date = :transactionDate AND t.failed = false")
    BigDecimal sumWithdrawalsByDate(@Param("transactionDate") LocalDate transactionDate);
}
