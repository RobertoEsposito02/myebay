package it.prova.myebay.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.myebay.service.aquisto.AquistoService;

@Controller
@RequestMapping(value = "/aquisto")
public class AquistoController {
	
	@Autowired
	private AquistoService aquistoService;
	
	@GetMapping("/list")
	public String listITuoiAquisti(Model model) {
		model.addAttribute("list_aquisto_attr", aquistoService.trovaIMieiAquisti());
		return "aquisto/list";
	}
	
	@GetMapping("/show/{idAquisto}")
	public String show(@PathVariable(required = true) Long idAquisto,Model model) {
		model.addAttribute("show_aquisto_attr", aquistoService.caricaSingoloAquisto(idAquisto));
		return "aquisto/show";
	}
}
