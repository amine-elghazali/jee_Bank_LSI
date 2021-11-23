package org.lsi.metier;

import org.lsi.dao.GroupeRepository;
import org.lsi.entities.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupeMetierImpl implements GroupeMetier{

    @Autowired
    private GroupeRepository groupeRepository;

    @Override
    public Groupe saveGroupe(Groupe groupe) {
        return groupeRepository.save(groupe);
    }


    @Override
    public List<Groupe> listGroupes() {
        return groupeRepository.findAll();
    }


}
