package org.lsi.metier;

import org.lsi.entities.Compte;

import java.util.List;

public interface CompteMetier {
	
	public Compte saveCompte(Compte cp);
	public Compte getCompte(Long code);

    public List<Compte> getAllCompte();

	List<Compte> getAllComptesOfClient(Long id);
}
