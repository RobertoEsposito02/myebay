package it.prova.myebay.repository.annuncio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.myebay.model.Annuncio;

public interface AnnuncioRepository extends CrudRepository<Annuncio, Long>, CustomAnnuncioRepository{
	
	@Query(" from Annuncio a left join fetch a.categorie c left join fetch a.utenteInserimento u where a.id = :id")
	Optional<Annuncio> findByIdConCategorie(Long id);
	
	@Query(" from Annuncio a left join fetch a.categorie c left join fetch a.utenteInserimento u where u.id = :id")
	List<Annuncio> findAllMyAnnuncio(Long id);
}
