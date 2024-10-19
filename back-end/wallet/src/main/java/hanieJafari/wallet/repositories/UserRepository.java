package hanieJafari.wallet.repositories;

import hanieJafari.wallet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}