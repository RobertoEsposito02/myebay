package it.prova.myebay;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.Utente;
import it.prova.myebay.service.annuncio.AnnuncioService;
import it.prova.myebay.service.categoria.CategoriaService;
import it.prova.myebay.service.ruolo.RuoloService;
import it.prova.myebay.service.utente.UtenteService;

@SpringBootApplication
public class MyebayApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	
	@Autowired
	private UtenteService utenteServiceInstance;
	
	@Autowired
	private AnnuncioService annuncioService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	public static void main(String[] args) {
		SpringApplication.run(MyebayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ROLE_ADMIN"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Classic User", "ROLE_CLASSIC_USER"));
		}

		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = new Utente("admin", "admin", "Mario", "Rossi", new Date(),1000);
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ROLE_ADMIN"));
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("user") == null) {
			Utente classicUser = new Utente("user", "user", "Antonio", "Verdi", new Date(),100);
			classicUser.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			utenteServiceInstance.inserisciNuovo(classicUser);
			utenteServiceInstance.changeUserAbilitation(classicUser.getId());
		}

		if (utenteServiceInstance.findByUsername("user1") == null) {
			Utente classicUser1 = new Utente("user1", "user1", "Antonioo", "Verdii", new Date(),10);
			classicUser1.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			utenteServiceInstance.inserisciNuovo(classicUser1);
			utenteServiceInstance.changeUserAbilitation(classicUser1.getId());
		}

		if (utenteServiceInstance.findByUsername("user2") == null) {
			Utente classicUser2 = new Utente("user2", "user2", "Antoniooo", "Verdiii", new Date(),2);
			classicUser2.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic User", "ROLE_CLASSIC_USER"));
			utenteServiceInstance.inserisciNuovo(classicUser2);
			utenteServiceInstance.changeUserAbilitation(classicUser2.getId());
		}
		
		if(categoriaService.cercaCategoriaByDescrizione("Elettronica") == null) {
			Categoria nuovaCategoria = new Categoria("Elettronica","elettronica");
			Annuncio annuncio = new Annuncio("Aspirapolvere",70,new Date(),true);
			categoriaService.inserisciNuovo(nuovaCategoria);
			annuncio.getCategorie().add(nuovaCategoria);
			annuncio.setUtenteInserimento(utenteServiceInstance.listAllUtenti().get(0));
			annuncioService.inserisciNuovo(annuncio);
			nuovaCategoria.getAnnunci().add(annuncio);
			categoriaService.aggiorna(nuovaCategoria);
			
			Annuncio annuncio2 = new Annuncio("Televisore",40,new Date(),true);
			categoriaService.inserisciNuovo(nuovaCategoria);
			annuncio2.getCategorie().add(nuovaCategoria);
			annuncio2.setUtenteInserimento(utenteServiceInstance.listAllUtenti().get(1));
			annuncioService.inserisciNuovo(annuncio2);
			nuovaCategoria.getAnnunci().add(annuncio2);
			categoriaService.aggiorna(nuovaCategoria);
			
			Annuncio annuncio3 = new Annuncio("Portatile",40,new Date(),false);
			categoriaService.inserisciNuovo(nuovaCategoria);
			annuncio3.getCategorie().add(nuovaCategoria);
			annuncio3.setUtenteInserimento(utenteServiceInstance.listAllUtenti().get(0));
			annuncioService.inserisciNuovo(annuncio3);
			nuovaCategoria.getAnnunci().add(annuncio3);
			categoriaService.aggiorna(nuovaCategoria);
			
			Annuncio annuncio4 = new Annuncio("Cuffie",40,new Date(),true);
			categoriaService.inserisciNuovo(nuovaCategoria);
			annuncio4.getCategorie().add(nuovaCategoria);
			annuncio4.setUtenteInserimento(utenteServiceInstance.listAllUtenti().get(2));
			annuncioService.inserisciNuovo(annuncio4);
			nuovaCategoria.getAnnunci().add(annuncio4);
			categoriaService.aggiorna(nuovaCategoria);
		}
	}
}
