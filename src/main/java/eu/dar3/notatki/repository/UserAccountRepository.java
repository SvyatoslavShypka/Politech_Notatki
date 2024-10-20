package eu.dar3.notatki.repository;

import eu.dar3.notatki.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {

    UserAccountEntity findUserAccountEntityByUsername(String username);

}
