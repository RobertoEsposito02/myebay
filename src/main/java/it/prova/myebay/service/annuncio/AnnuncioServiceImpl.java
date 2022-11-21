package it.prova.myebay.service.annuncio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.myebay.exception.CreditoNonSufficienteException;
import it.prova.myebay.exception.LoginNonEffettuatoException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Aquisto;
import it.prova.myebay.model.Utente;
import it.prova.myebay.repository.annuncio.AnnuncioRepository;
import it.prova.myebay.repository.aquisto.AquistoRepository;
import it.prova.myebay.repository.utente.UtenteRepository;

@Service
public class AnnuncioServiceImpl implements AnnuncioService{

	@Autowired
	private AnnuncioRepository repository;
	
	@Autowired
	private AquistoRepository aquistoRepository;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Annuncio> listAllAnnunci() {
		return (List<Annuncio>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Annuncio caricaSingoloAnnuncio(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Annuncio annuncioInstance) {
		repository.save(annuncioInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Annuncio annuncioInstance) {
		repository.save(annuncioInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToDelete) {
		repository.deleteById(idToDelete);
	}

	@Override
	public List<Annuncio> findByExample(Annuncio example) {
		return repository.findByExample(example);
	}

	@Override
	public Annuncio caricaSingoloEager(Long id) {
		return repository.findByIdConCategorie(id).orElse(null);
	}

	@Override
	public void executeCompra(Long idAnnuncio) {
		Annuncio annuncio = repository.findByIdConCategorie(idAnnuncio).orElse(null);
		
		if(SecurityContextHolder.getContext().getAuthentication().getName() != null) {
			UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Utente utente = utenteRepository.findByUsername(principal.getUsername()).orElse(null);
			if(annuncio.getPrezzo() <= utente.getCreditoResiduo()) {
				/*scalo i soldi dal conto dell utente che compra*/
				utente.setCreditoResiduo(utente.getCreditoResiduo()-annuncio.getPrezzo());
				/*creo un nuovo aquisto con i dati dell'annuncio*/
				Aquisto aquisto = new Aquisto(annuncio.getTestoAnnuncio(),new Date(), annuncio.getPrezzo(),utente);
				aquistoRepository.save(aquisto);
				/*aggiungo l'aquisto all'utente che compra*/
				utente.getAquisti().add(aquisto);
				utenteRepository.save(utente);
				/*aggiungo i soldi al conto del venditore */
				annuncio.getUtenteInserimento().setCreditoResiduo(annuncio.getUtenteInserimento().getCreditoResiduo()+annuncio.getPrezzo());
				/*cambio lo stato dell'annuncio da aperto a chiuso */
				annuncio.setAperto(false);
				repository.save(annuncio);
			}else {
				throw new CreditoNonSufficienteException("credito non sufficiente");
			}
		}else {
			throw new LoginNonEffettuatoException("Login non effettuato");
		}
		
		
	}

	@Override
	public List<Annuncio> caricaTuttiIMieiAnnunci() {
		UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utente utente = utenteRepository.findByUsername(principal.getUsername()).orElse(null);
		return repository.findAllMyAnnuncio(utente.getId());
	}

	@Override
	public List<Annuncio> caricaTuttiGliAnnunciNonMiei(Annuncio example) {
		UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utente utente = utenteRepository.findByUsername(principal.getUsername()).orElse(null);
		return repository.findByExampleNotMyAnnunci(example,utente.getId());
	}

}
