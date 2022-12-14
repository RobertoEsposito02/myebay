package it.prova.myebay.service.categoria;

import java.util.List;
import java.util.Optional;

import it.prova.myebay.model.Categoria;

public interface CategoriaService {
	public List<Categoria> listAllCategorie() ;

	public Categoria caricaSingoloCategoria(Long id);
	
	public void aggiorna(Categoria categoriaInstance);

	public void inserisciNuovo(Categoria categoriaInstance);

	public void rimuovi(Long idToDelete);
	
	public Categoria cercaCategoriaByDescrizione(String descrizoine);
}
