package org.lsi.dao;


import org.lsi.entities.Compte;
import org.lsi.entities.Operation;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jdbc.repository.query.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
//import org.springframework.data.repository.query.Param;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByCompte_codeCompte(Long id);

	@Query("select o from Operation o where o.compte.codeCompte=:x")
	public Page<Operation> getOperations(@Param("x") Long code, Pageable pageable);
//
//	// 2 éme Solution
//	//public Page<Operation> findByCompte(Compte cp, Pageable pageable);
//
}
