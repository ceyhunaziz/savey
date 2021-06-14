package az.savey.ms.savey.dao;

import az.savey.ms.savey.dao.model.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
    List<AccountEntity> findAllByIsActiveIsTrue();
    Optional<AccountEntity> findByIdAndIsActiveIsTrue(Long id);
}
