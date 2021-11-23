package org.lsi.dao;


import org.lsi.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
