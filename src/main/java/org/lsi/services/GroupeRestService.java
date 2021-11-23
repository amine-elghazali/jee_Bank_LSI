package org.lsi.services;


import org.lsi.dao.GroupeRepository;
import org.lsi.entities.Client;
import org.lsi.entities.Employe;
import org.lsi.entities.Groupe;
import org.lsi.metier.ClientMetier;
import org.lsi.metier.EmployeMetier;
import org.lsi.metier.GroupeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/groupe/")
public class GroupeRestService {

    @Autowired
    private GroupeMetier  groupeMetier;

    @Autowired
    private EmployeMetier employeMetier;

    @Autowired
    private GroupeRepository groupeRepository;

    /*
    @PostMapping("/groupes")
    public Groupe saveGroupe(@RequestBody Groupe groupe){
        return groupeMetier.saveGroupe(groupe);
    }
    */


    @GetMapping("form")
    public String showGroupeForm(Groupe groupe){
        return "Groupe/AjouterGroupe";
    }

    @GetMapping("form/EmpToGrp")
    public String showGroupeForm(Model model,Groupe groupe,Employe employe){
        model.addAttribute("groupes",groupeMetier.listGroupes());
        model.addAttribute("employes",employeMetier.listEmployes());

        return "Groupe/AjouterEmployeToGroupe";
    }

    @PostMapping("ajouter")
    public String saveGroupe(Model model,Groupe groupe) {

        groupeMetier.saveGroupe(groupe);

        return "redirect:consulter";
    }

    @PostMapping("ajouterEmpToGrp")
    public String saveEmpToGrp(@ModelAttribute("groupe")Groupe gr) {
      /*  Collection<Employe> emp = gr.getEmploye();
        System.out.println(gr.getCodeGroupe());
        System.out.println(gr.getNomGroupe());
        for (Employe em: emp
        ) {
            System.out.println(em.getNomEmploye());
        }
*/
        Groupe groupe = groupeRepository.findById(gr.getCodeGroupe()).get();
        gr.setNomGroupe(groupe.getNomGroupe());
        System.out.println(groupe.getNomGroupe());

        groupeMetier.saveGroupe(gr);
        return "redirect:consulter";
    }


    @GetMapping("consulter")
    public String showClientsList(Model model) {
        model.addAttribute("groupes",groupeMetier.listGroupes());

        return "/Groupe/ConsulterGroupes";
    }

    @PostMapping("/employes")
    public String saveEmploye(@ModelAttribute("employe") Employe e) {
        employeMetier.saveEmploye(e);
        return "redirect:/employes";
    }

    /*
    @GetMapping("employe")
    public String listEmployes(Model model) {
        model.addAttribute("employes", employeMetier.listEmployes());
        model.addAttribute("groupes", groupeMetier.listGroupes());
        return "/employes/index";
    }*/
}
