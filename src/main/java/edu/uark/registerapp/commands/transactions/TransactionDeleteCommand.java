package edu.uark.registerapp.commands.transactions;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.entities.TransactionEntity;
import edu.uark.registerapp.models.repositories.TransactionRepository;

@Service
public class TransactionDeleteCommand implements VoidCommandInterface {
	@Override
	public void execute() {
        final Optional<TransactionEntity> transactionEntity =
        this.transactionRepository.findById(this.transactionId);
        if (!transactionEntity.isPresent()) { // No record with the associated record ID exists in the database.
			throw new NotFoundException("Product");
        }
        
        this.transactionRepository.delete(transactionEntity.get());
	}

	// Properties
	private UUID transactionId;
	public UUID getProductId() {
		return this.transactionId;
	}
	public TransactionDeleteCommand setTransactionId(final UUID id) {
		this.transactionId = id;
		return this;
	}

	@Autowired
	private TransactionRepository transactionRepository;
}