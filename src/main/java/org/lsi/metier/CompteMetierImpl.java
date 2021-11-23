package org.lsi.metier;


import java.util.Date;
import java.util.List;

import org.lsi.dao.ClientRepository;
import org.lsi.dao.CompteRepository;
import org.lsi.entities.Client;
import org.lsi.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteMetierImpl implements CompteMetier {
	
	
	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private ClientRepository clientRepository;
	@Override
	public Compte saveCompte(Compte cp) {
		// TODO Auto-generated method stub
		cp.setDateCreation(new Date());
		return compteRepository.save(cp);
	}

	@Override
	public Compte getCompte(Long code) {
		// TODO Auto-generated method stub
		return compteRepository.findByCodeCompte(code);
	}


	@Override
	public List<Compte> getAllCompte() {
		return compteRepository.findAll();
	}

	@Override
	public List<Compte> getAllComptesOfClient(Long id) {
		List<Compte> compteClient = compteRepository.findByClient_codeClient(id);

		return compteClient;
	}


}
