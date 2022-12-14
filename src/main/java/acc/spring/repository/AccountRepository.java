package acc.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acc.spring.model.Account;

@Repository
public interface AccountRepository  extends JpaRepository<Account,Long>{
    
    @Query("SELECT a FROM Account a JOIN FETCH a.cliente WHERE a.id = (:id)")
    public Optional<Account> findByIdAndFetchClientEagerly(@Param("id") Long id);

    @Query(value="SELECT * FROM cuenta a WHERE a.cliente_id = (:id)",nativeQuery = true)
    public List<Account> findAllAccountsByClientId(@Param("id") Long clientId);
}
