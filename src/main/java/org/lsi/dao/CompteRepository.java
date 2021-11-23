package org.lsi.dao;

import org.lsi.entities.Client;
import org.lsi.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    //Optional<Object> findByCodeCompte(Long code);

    List<Compte> findByClient(Client client);

    Compte findByCodeCompte(Long id);

    List<Compte> findByClient_codeClient(Long id);
}
