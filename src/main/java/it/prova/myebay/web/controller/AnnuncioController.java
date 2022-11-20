package it.prova.myebay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.service.annuncio.AnnuncioService;

@Controller
@RequestMapping(value = "/annuncio")
public class AnnuncioController {

	@Autowired
	private AnnuncioService annuncioService;

	@PostMapping("/list")
	public String listAllAnnuncio(Annuncio annuncioExample ,Model model) {
		model.addAttribute("list_annuncio_attr", annuncioService.findByExample(annuncioExample));
		return "annuncio/list";
	}
}
