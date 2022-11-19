package it.prova.myebay.service.aquisto;

import java.util.List;

import it.prova.myebay.model.Aquisto;

public interface AquistoService {
	public List<Aquisto> listAllAquisto() ;

	public Aquisto caricaSingoloAquisto(Long id);
	
	public void aggiorna(Aquisto aquistoInstance);

	public void inserisciNuovo(Aquisto aquistoInstance);

	public void rimuovi(Long idToDelete);
}
