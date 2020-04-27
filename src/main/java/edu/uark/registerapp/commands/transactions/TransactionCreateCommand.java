package edu.uark.registerapp.commands.transactions;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.entities.TransactionEntity;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;
import edu.uark.registerapp.models.repositories.ProductRepository;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;
import edu.uark.registerapp.models.repositories.TransactionRepository;

@Service
public class TransactionCreateCommand implements VoidCommandInterface {
	@Override
	public void execute() {
        final List<TransactionEntryEntity> transactionEntryEntities = new LinkedList<>();
        final long transactionTotal = 0;
        this.createTransaction(transactionEntryEntities, transactionTotal);

    }

    // Helper methods - useful for summmary
	@Transactional
	private void createTransaction(
		final List<TransactionEntryEntity> transactionEntryEntities,
		final long transactionTotal
	) {

		final TransactionEntity transactionEntity =
			this.transactionRepository.save(
				(new TransactionEntity(this.employeeId, transactionTotal, 1)));

		for (TransactionEntryEntity transactionEntryEntity : transactionEntryEntities) {
			transactionEntryEntity.setTransactionId(transactionEntity.getId());

			this.transactionEntryRepository.save(transactionEntryEntity);
		}
	}

	// Properties
	private UUID employeeId;
	public UUID getEmployeeId() {
		return this.employeeId;
	}
	public TransactionCreateCommand setEmployeeId(final UUID employeeId) {
		this.employeeId = employeeId;
		return this;
	}

	@Autowired
	ProductRepository productRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionEntryRepository transactionEntryRepository;
}
