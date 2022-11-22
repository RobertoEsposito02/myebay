package it.prova.myebay.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.myebay.dto.ruolo.RuoloDTO;
import it.prova.myebay.dto.utente.UtenteDTO;
import it.prova.myebay.model.StatoUtente;
import it.prova.myebay.service.utente.UtenteService;
import it.prova.myebay.validation.ValidationNoPassword;
import it.prova.myebay.validation.ValidationWithPassword;

@Controller
public class RegisterController {
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("register_utente_attr", new UtenteDTO());
		return "/register";
	}

	@PostMapping("/executeRegister")
	public String executeRegister(
			@Validated({ ValidationWithPassword.class,
					ValidationNoPassword.class }) @ModelAttribute("register_utente_attr") UtenteDTO utenteDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (!result.hasFieldErrors("password") && !utenteDTO.getPassword().equals(utenteDTO.getConfermaPassword())) {
			result.rejectValue("confermaPassword", "utente.password.diverse");
		}
		
		if (result.hasErrors()) {
			return "/register";
		}

		utenteService.inserisciNuovo(utenteDTO.buildUtenteModel(false));

		redirectAttrs.addFlashAttribute("infoMessage", "Operazione eseguita correttamente, attendi che un admin ti abiliti");
		return "redirect:/login";
	}
}
