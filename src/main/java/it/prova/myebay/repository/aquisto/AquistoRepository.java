package it.prova.myebay.repository.aquisto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Aquisto;

public interface AquistoRepository extends CrudRepository<Aquisto, Long> , CustomAquistoRepository{
	
	@Query("select a from Aquisto a left join fetch a.utenteAquirente u where u.id = :id")
	List<Aquisto> findByidConUtente(Long id);
}
