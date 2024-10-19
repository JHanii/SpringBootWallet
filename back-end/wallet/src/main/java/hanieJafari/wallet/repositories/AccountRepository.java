package hanieJafari.wallet.repositories;

import hanieJafari.wallet.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
