package tech.devinhouse.aviacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.aviacao.model.CheckIn;

import java.util.Optional;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, String> {

    boolean existsByPassageiro_Cpf (Long cpf);

    Optional<CheckIn> findByPassageiro_Cpf (Long cpf);

    boolean existsByAssento (String assento);
}
