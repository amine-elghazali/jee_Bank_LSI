package org.lsi.services;

import java.util.Collection;
import java.util.List;

import org.lsi.entities.Client;
import org.lsi.entities.Employe;
import org.lsi.entities.Groupe;
import org.lsi.metier.EmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employe/")
public class EmployeRestService {
	
	@Autowired
	private EmployeMetier employeMetier;

	@GetMapping("form")
	public String showClientForm(Model model,Employe employe){
		model.addAttribute("employes",employeMetier.listEmployes());
		for (Employe employeSup: employeMetier.listEmployes()) {
			System.out.println(employeSup.getNomEmploye());
		}
		return "Employe/AjouterEmploye";
	}

	@PostMapping("ajouter")
	public String saveClient(Model model, Employe employe) {

		employeMetier.saveEmploye(employe);

		return "redirect:consulter";
	}

	@GetMapping("consulter")
	public String showClientsList(Model model) {
		model.addAttribute("employes",employeMetier.listEmployes());
		List<Employe> listofemploye = employeMetier.listEmployes();
		for (Employe employe: listofemploye) {
			System.out.println(employe.getNomEmploye());
			System.out.println(employe.getEmployeSup().getNomEmploye());
			Collection<Groupe> groupe = employe.getGroupes();
			System.out.println("***********   GROUPES :   **********");
			for (Groupe grp : groupe){
				System.out.println(grp.getNomGroupe());
			}
		}



		return "Employe/ConsulterEmployes";
	}



	/*@RequestMapping(value="/employes",method=RequestMethod.POST)
	public Employe saveEmploye(@RequestBody Employe e) {
		return employeMetier.saveEmploye(e);
	}

	@RequestMapping(value="/employes",method=RequestMethod.GET)
	public List<Employe> listEmployes() {
		return employeMetier.listEmployes();
	}*/

}
