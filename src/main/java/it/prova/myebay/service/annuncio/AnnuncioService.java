package it.prova.myebay.service.annuncio;

import java.util.List;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Utente;

public interface AnnuncioService {
	public List<Annuncio> listAllAnnunci() ;

	public Annuncio caricaSingoloAnnuncio(Long id);
	
	public void aggiorna(Annuncio annuncioInstance);

	public void inserisciNuovo(Annuncio annuncioInstance);

	public void rimuovi(Long idToDelete);
	
	public List<Annuncio> findByExample(Annuncio example);
	
	public Annuncio caricaSingoloEager(Long id);
	
	public void executeCompra(Long idAnnuncio);
	
	public List<Annuncio> caricaTuttiIMieiAnnunci();

	public List<Annuncio> caricaTuttiGliAnnunciNonMiei(Annuncio example);
}
