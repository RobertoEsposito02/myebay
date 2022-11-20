package it.prova.myebay.service.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Categoria;
import it.prova.myebay.repository.categoria.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	private CategoriaRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categoria> listAllCategorie() {
		return (List<Categoria>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria caricaSingoloCategoria(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Categoria categoriaInstance) {
		repository.save(categoriaInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Categoria categoriaInstance) {
		repository.save(categoriaInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToDelete) {
		repository.deleteById(idToDelete);
	}

	@Override
	public Categoria cercaCategoriaByDescrizione(String descrizoine) {
		return repository.findByDescrizione(descrizoine).orElse(null);
	}

}
