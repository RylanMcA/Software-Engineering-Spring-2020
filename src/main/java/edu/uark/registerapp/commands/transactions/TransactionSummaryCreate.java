package edu.uark.registerapp.commands.transactions;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.entities.TransactionEntity;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;
import edu.uark.registerapp.models.repositories.TransactionRepository;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class TransactionSummaryCreate implements ResultCommandInterface<List<Product>> {
    @Override
    public List<Product> execute() {
        final LinkedList<Product> summary = new LinkedList<Product>();
        List<TransactionEntity> cashierId = transactionRepository.findByCashierId(activeUserId);
        
        for (final TransactionEntryEntity transactionEntity : transactionEntryRepository.findByTransactionId(transactionId)) {
            
		}
		
		return summary;
    }
    
    // Properties
	private UUID productId;
	public UUID getProductId() {
		return this.productId;
	}

    private UUID transactionId;
    public UUID getTransactionId(){
        return this.transactionId;
    }

    private UUID cashierId;
    public UUID getCashierId(){
        return this.cashierId;
    }

    private UUID referenceId;
	public UUID getReferenceId() {
		return this.referenceId;
	}

    private UUID activeUserId;
    public UUID getEmployeeId(){
        return this.activeUserId;
    }

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
    private TransactionEntryRepository transactionEntryRepository;

    @Autowired 
    private EmployeeRepository employeeRepo;

}