package it.prova.myebay.repository.categoria;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;

import it.prova.myebay.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long>{
	Optional<Categoria> findByDescrizione(String descrizione);
}
