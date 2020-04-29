package edu.uark.registerapp.commands.transactions;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;

@Service
public class TransactionEntryDeleteCommand implements VoidCommandInterface {
	@Transactional
	@Override
	public void execute() {
        for(TransactionEntryEntity entries : transactionEntryRepository.findByTransactionId(transactionId)){
            if(entries.getProductId() == this.productId){
                transactionEntryRepository.delete(entries);
            }
        }
	}

	// Properties
	private UUID productId;
	public UUID getProductId() {
		return this.productId;
	}
	public TransactionEntryDeleteCommand setProductId(final UUID productId) {
		this.productId = productId;
		return this;
    }
    
    private UUID transactionId;
	public UUID getTransactionId() {
		return this.transactionId;
	}
	public TransactionEntryDeleteCommand setTransactionId(final UUID transactionId) {
		this.transactionId = transactionId;
		return this;
    }



	@Autowired
    private TransactionEntryRepository transactionEntryRepository;
    
}
