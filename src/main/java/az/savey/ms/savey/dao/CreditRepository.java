package az.savey.ms.savey.dao;

import az.savey.ms.savey.dao.model.CreditEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditRepository extends CrudRepository<CreditEntity, Long> {
    List<CreditEntity> findAllByIsActiveIsTrue();
    Optional<CreditEntity> findByIdAndIsActiveIsTrue(Long id);
}
