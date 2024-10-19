package hanieJafari.wallet.repositories;

import hanieJafari.wallet.models.AccountStatement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountStatementRepository extends JpaRepository<AccountStatement, Long> {

}