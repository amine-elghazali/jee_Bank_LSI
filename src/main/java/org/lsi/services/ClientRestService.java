package org.lsi.services;

import java.util.List;

import org.lsi.entities.Client;
import org.lsi.metier.ClientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client/")
public class ClientRestService {
	
	@Autowired
	private ClientMetier clientMetier;

	@GetMapping("form")
	public String showClientForm(Client client){
		return "Client/AjouterClient";
	}

	@PostMapping("ajouter")
	public String saveClient(Model model,Client client) {

		clientMetier.saveClient(client);

		return "redirect:consulter";
	}

	/*@RequestMapping(value="/ConsulterClients",method=RequestMethod.GET)
	public String listClient(Model model) {
		model.addAttribute("clients",clientMetier.listClient());
		return "redirect:index";
	}*/

	@GetMapping("consulter")
	public String showClientsList(Model model) {
		model.addAttribute("clients", clientMetier.listClient());
		return "Client/ConsulterClients";
	}
}


