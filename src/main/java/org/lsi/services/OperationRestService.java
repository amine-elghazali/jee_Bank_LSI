package org.lsi.services;

import org.lsi.dao.CompteRepository;
import org.lsi.dao.EmployeRepository;
import org.lsi.entities.Compte;
import org.lsi.entities.Operation;
import org.lsi.entities.Versment;
import org.lsi.metier.OperationMetier;
import org.lsi.metier.PageOperation;
//import org.lsi.metier.PageOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/operation/")
public class OperationRestService {
	
	@Autowired
	OperationMetier operationMetier;

	@Autowired
	CompteRepository compteRepository;

	@Autowired
	EmployeRepository employeRepository;



	@GetMapping("compte/operations/{id}")
	public String compteOperations(Model model,@PathVariable("id")Long id ){
		Compte cp = compteRepository.findByCodeCompte(id);
		model.addAttribute("compteCode",cp.getCodeCompte());
		return "Operation/CompteOperation";
	}


	@GetMapping("compte")
	 public String getOperation(
			 Model model,
			@RequestParam Long codeCompte,
			@RequestParam int page,
			@RequestParam int size) {

			PageOperation pageOperation = operationMetier.getOperation(codeCompte, page, size);
			List<Operation> operations = pageOperation.getOperations();
			model.addAttribute("operations",operations);
			model.addAttribute("codeCompte",codeCompte);

		System.out.println(page+" "+size);
			model.addAttribute("oldPage",page);
			model.addAttribute("oldSize",size);


			for(Operation op : operations){
				System.out.println(op.getMontant());
				System.out.println(op.getDateOperation());
			}

		return "Operation/CompteOperationlist";
	}

	/*@RequestMapping(value="/versement",method=RequestMethod.PUT)
	public boolean verser(
			@RequestParam Long code,
			@RequestParam double montant,
			@RequestParam Long codeEmp) {
		return operationMetier.verser(code, montant, codeEmp);
	}

	@RequestMapping(value="/retrait",method=RequestMethod.PUT) 
	public boolean retirer(
			@RequestParam Long code,
			@RequestParam double montant,
			@RequestParam Long codeEmp) {
		return operationMetier.retirer(code, montant, codeEmp);
	}
	@RequestMapping(value="/virement",method=RequestMethod.PUT) 
	public boolean virement(
			@RequestParam Long cpte1,
			@RequestParam Long cpte2,
			@RequestParam double montant,
			@RequestParam Long codeEmp) {
		return operationMetier.virement(cpte1, cpte2, montant, codeEmp);
	} 

	@GetMapping("operations/{id}")
	public List<Operation> getAccountOperations(@PathVariable("id") Long id ){
		return operationMetier.getAccountOperations(id);
	}*/



	/*	$$$$$$$$$$$$$$$$$$$$	*/

	@GetMapping("verser")
	public String verserForm(Model model){
		model.addAttribute("comptes",compteRepository.findAll());
		model.addAttribute("employes",employeRepository.findAll());
		return "/Operation/Verser";
	}

	@GetMapping("retirer")
	public String retirerForm(Model model){
		model.addAttribute("comptes",compteRepository.findAll());
		model.addAttribute("employes",employeRepository.findAll());
		return "/Operation/Retirer";
	}

	@GetMapping("virer")
	public String virerForm(Model model){
		model.addAttribute("comptes",compteRepository.findAll());
		model.addAttribute("employes",employeRepository.findAll());
		return "/Operation/Virer";
	}




	@PutMapping("versement")
	public String verser(@RequestParam Long code,
						 @RequestParam double montant,
						 @RequestParam Long codeEmp) {

		 operationMetier.verser(code,montant,codeEmp);

		 return "redirect:verser";
	}

	@PutMapping("retrait")
	public String retirer(
			@RequestParam Long code,
			@RequestParam double montant,
			@RequestParam Long codeEmp) {
		 operationMetier.retirer(code, montant, codeEmp);
		return "redirect:retirer";
	}

	@PutMapping("virement")
	public String virement(
			@RequestParam Long cpte1,
			@RequestParam Long cpte2,
			@RequestParam double montant,
			@RequestParam Long codeEmp) {
		 operationMetier.virement(cpte1, cpte2, montant, codeEmp);

		return "redirect:virer";
	}


	@GetMapping("operation/{id}")
	public String getOperationCompte(@PathVariable("id")Long codeCompte){
		return "Operation/CompteOperation";
	}
}
