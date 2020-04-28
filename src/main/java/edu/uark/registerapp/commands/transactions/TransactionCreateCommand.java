package edu.uark.registerapp.commands.transactions;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.models.entities.TransactionEntity;
import edu.uark.registerapp.models.repositories.TransactionRepository;

@Service
public class TransactionCreateCommand implements ResultCommandInterface<UUID> {
	@Override
	public UUID execute() {
		final TransactionEntity createdTransactionEntity =
			this.transactionRepository.save(
				(new TransactionEntity(this.employeeId)));
		return createdTransactionEntity.getId();
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
	private TransactionRepository transactionRepository;
}
