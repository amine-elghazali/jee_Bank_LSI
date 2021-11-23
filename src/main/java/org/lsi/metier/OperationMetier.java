package org.lsi.metier;

import org.lsi.entities.Operation;

import java.util.List;

public interface OperationMetier {
	
	public boolean verser(Long code,double montant, Long codeEmp);
	public boolean retirer(Long code,double montant, Long codeEmp);
	public boolean virement(Long cpte1,Long cpte2,double solde, Long codeEmp);

	List<Operation> getAccountOperations(Long id);
	public PageOperation getOperation(Long codeCompte, int page, int size);


}
