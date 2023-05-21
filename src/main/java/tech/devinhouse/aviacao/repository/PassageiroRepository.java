package tech.devinhouse.aviacao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.aviacao.model.Passageiro;

@Repository
public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {


}
