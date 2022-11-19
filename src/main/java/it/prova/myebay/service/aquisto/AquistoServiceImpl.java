package it.prova.myebay.service.aquisto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.model.Aquisto;
import it.prova.myebay.repository.aquisto.AquistoRepository;

@Service
public class AquistoServiceImpl implements AquistoService{

	@Autowired
	private AquistoRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Aquisto> listAllAquisto() {
		return (List<Aquisto>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Aquisto caricaSingoloAquisto(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Aquisto aquistoInstance) {
		repository.save(aquistoInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Aquisto aquistoInstance) {
		repository.save(aquistoInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToDelete) {
		repository.deleteById(idToDelete);
	}

}
