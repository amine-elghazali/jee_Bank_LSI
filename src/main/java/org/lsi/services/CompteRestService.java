package org.lsi.services;

import javax.persistence.DiscriminatorValue;
import javax.websocket.server.PathParam;

import org.lsi.entities.*;
import org.lsi.metier.ClientMetier;
import org.lsi.metier.CompteMetier;
import org.lsi.metier.EmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/compte/")
public class CompteRestService {
	
	@Autowired
	private CompteMetier compteMetier;

	@Autowired
	private EmployeMetier employeMetier;

	@Autowired
	private ClientMetier clientMetier;

	/*@GetMapping("form/CC")
	public String showClientForm(Model model,  Employe employe, Client client){
		model.addAttribute("employes",employeMetier.listEmployes());
		model.addAttribute("clients", clientMetier.listClient());
		return "Compte/AjouterCompte";
	}*/


	@GetMapping("form/cc")  //afficher la forme pour ajouter un client
	public String showCCForm(Model model,Employe employe, Client client)
	{
		CompteCourant compte=new CompteCourant();
		model.addAttribute("compte", compte);
		model.addAttribute("type", "CC");
		model.addAttribute("employes",employeMetier.listEmployes());
		model.addAttribute("clients", clientMetier.listClient());

		return "Compte/AjouterCompte";
	}


	@GetMapping("form/ce")  //afficher la forme pour ajouter un client
	public String showCEForm(Model model,Employe employe, Client client)
	{
		CompteEpargne compte=new CompteEpargne();
		model.addAttribute("compte", compte);
		model.addAttribute("type", "CE");
		model.addAttribute("employes",employeMetier.listEmployes());
		model.addAttribute("clients", clientMetier.listClient());
		return "Compte/AjouterCompte";
	}



	@PostMapping("ajouter/cc")
	public String saveCompteCourant(@ModelAttribute("compte") CompteCourant cp) {
		try {
			compteMetier.saveCompte(cp);
		}
		catch(Exception e) {
			System.out.println("waaahya");
			e.printStackTrace();
		}
		return "redirect:consulter";
	}


	@PostMapping("ajouter/ce")
	public String saveCompteEpargne(@ModelAttribute("compte") CompteEpargne cp) {
		try {
			compteMetier.saveCompte(cp);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return "redirect:/compte/consulter";
	}


	/***********************************************************************/



	/*@PostMapping("ajouter/cc")
	public String saveCompteCourant(Model model,Client client,Employe employe,CompteCourant compteCourant) {

		model.addAttribute("type", "CC");
		compteMetier.saveCompte(compteCourant);

		return "redirect:consulter";
	}


	@PostMapping("ajouter/ce")
	public String saveCompteEpargne(Model model,Client client,Employe employe,CompteEpargne compteEpargne) {
		model.addAttribute("type", "CE");
		compteMetier.saveCompte(compteEpargne);

		return "redirect:consulter";
	}*/


	@RequestMapping(value="/comptes/{code}", method=RequestMethod.GET)
	public Compte getCompte(@PathVariable("code") Long code) {
		return compteMetier.getCompte(code);
	}


	@GetMapping("consulter")
	public String getAllCompte(Model model){
		model.addAttribute("comptes", compteMetier.getAllCompte());
		//List<Compte> comptes = compteMetier.getAllCompte();

		return "Compte/ConsulterComptes";
	}


	@GetMapping("/operation/client/{id}")
	public String getAllComptesOfClient(@PathVariable("id") Long id){
				//compteMetier.getAllComptesOfClient(id);
		return "Operation/CompteOperation";
	}

}
