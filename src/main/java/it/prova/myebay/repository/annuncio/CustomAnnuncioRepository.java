package it.prova.myebay.repository.annuncio;

import java.util.List;

import it.prova.myebay.model.Annuncio;

public interface CustomAnnuncioRepository {
	public List<Annuncio> findByExample(Annuncio example);
	
	public List<Annuncio> findByExampleNotMyAnnunci(Annuncio example, Long id);
	
	public void scollegaAnnuncio(Long id);
}
