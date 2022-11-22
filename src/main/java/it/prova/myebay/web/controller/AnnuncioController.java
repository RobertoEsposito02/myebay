package it.prova.myebay.web.controller;

import java.security.Principal;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.annuncio.AnnuncioDTO;
import it.prova.myebay.exception.CreditoNonSufficienteException;
import it.prova.myebay.exception.LoginNonEffettuatoException;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.annuncio.AnnuncioService;

@Controller
@RequestMapping(value = "/annuncio")
public class AnnuncioController {

	@Autowired
	private AnnuncioService annuncioService;
	
	@GetMapping("/show/{idAnnuncio}")
	public String show(@PathVariable(required = true) Long idAnnuncio, Model model) {
		model.addAttribute("show_annuncio_attr", annuncioService.caricaSingoloEager(idAnnuncio));
		return "annuncio/show";
	} 

	@RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
	public String listAllAnnuncio(Annuncio annuncioExample, Model model) throws ParseException {
		//if(annuncioExample == null)
			//annuncioExample = new Annuncio("", 0, new SimpleDateFormat().parse(""),true);
		
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			model.addAttribute("list_annuncio_attr", annuncioService.caricaTuttiGliAnnunciNonMiei(annuncioExample));
		}else {
			model.addAttribute("list_annuncio_attr", annuncioService.findByExample(annuncioExample));
		}
		return "annuncio/list";
	}

	@PostMapping("/executeCompra")
	public String executeCompra(@RequestParam Long idAnnuncio, Model model,
			RedirectAttributes redirectAttrs, Principal principal) {
		
		if(principal == null) {
			model.addAttribute("idAnnuncio", idAnnuncio);
			return "/login";
		}
		
		try {
			annuncioService.executeCompra(idAnnuncio);
		}catch (LoginNonEffettuatoException e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("errorMessage", "Bisogna effettuare il login se si vuole aquistare");
			return "redirect:/login";
		}catch (CreditoNonSufficienteException e) {
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("errorMessage", "Credito non sufficiente");
			return "redirect:/annuncio/show/" + idAnnuncio; 
		} 
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/aquisto/list";
	}

	@GetMapping("/listMyAnnunci")
	public String listAllMyAnnunci(Model model) {
		model.addAttribute("list_annuncio_attr",annuncioService.caricaTuttiIMieiAnnunci());
		return "annuncio/list";
	}
	
	@GetMapping("/edit/{idAnnuncio}")
	public String edit(@PathVariable(required = true) Long idAnnuncio, Model model) {
		Annuncio annuncioModel = annuncioService.caricaSingoloEager(idAnnuncio);
		model.addAttribute("edit_annuncio_attr", AnnuncioDTO.buildAnnuncioFromModel(annuncioModel));
		return "annuncio/edit";
	}
	
	@PostMapping("/update")
	public String update(@Validated@ModelAttribute("edit_annuncio_attr") AnnuncioDTO annuncioDTO,
			//@PathVariable(required = true) Long idUtente,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			return "annuncio/edit";
		}
		
		annuncioService.aggiorna(annuncioDTO.buildAnnuncioModel());
		
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/home";
	}
	
	@GetMapping("/delete/{idAnnuncio}")
	public String delete(@PathVariable(required = true) Long idAnnuncio,Model model) {
		model.addAttribute("delete_annuncio_attr", annuncioService.caricaSingoloEager(idAnnuncio));
		return "annuncio/delete";
	}
	
	@PostMapping("/executeDelete")
	public String executeDelete(@RequestParam Long idAnnuncio, RedirectAttributes redirectAttrs) {
		
		annuncioService.scollegaAnnuncio(idAnnuncio);
		annuncioService.rimuovi(idAnnuncio);
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/home";
	}
	
	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("insert_annuncio_attr", new AnnuncioDTO());
		return "annuncio/insert";
	}
	
	@PostMapping("/executeInsert")
	public String executeInsert(@Validated@ModelAttribute("insert_annuncio_attr") AnnuncioDTO annuncioDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs, Principal principal) {
		
		if (result.hasErrors()) {
			return "annuncio/insert";
		}
		 
		annuncioService.inserisci(annuncioDTO.buildAnnuncioModel(),principal.getName());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/home";
	}
}
