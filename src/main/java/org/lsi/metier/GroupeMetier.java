package org.lsi.metier;

import org.lsi.entities.Groupe;

import java.util.List;

public interface GroupeMetier {
    Groupe saveGroupe(Groupe groupe);

    List<Groupe> listGroupes();
}
