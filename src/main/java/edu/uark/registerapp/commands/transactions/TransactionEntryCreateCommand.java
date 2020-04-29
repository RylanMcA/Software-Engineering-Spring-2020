package edu.uark.registerapp.commands.transactions;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.ConflictException;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;
import edu.uark.registerapp.models.repositories.ProductRepository;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;

@Service
public class TransactionEntryCreateCommand implements ResultCommandInterface<UUID> {
	@Override
	public UUID execute() {


        Optional<ProductEntity> productOp = productRepository.findById(productId);
		Product product = new Product(productOp.get());

		Optional<TransactionEntryEntity> dupeCheck = 
		transactionEntryRepository.findByTransactionIdAndProductId(transactionId, productId);
		
		if(dupeCheck.isPresent()){
			throw new ConflictException("already exists");
		}

		final TransactionEntryEntity createdTransactionEntity =
			this.transactionEntryRepository.save(
				(new TransactionEntryEntity(this.transactionId, this.productId, 1, product.getPrice())));
		return createdTransactionEntity.getTransactionId();
	}

	// Properties
	private UUID transactionId;
	public UUID getTransactionId() {
		return this.transactionId;
	}
	public TransactionEntryCreateCommand setTransactionId(final UUID transactionId) {
		this.transactionId = transactionId;
		return this;
    }

    private UUID productId;
	public UUID getProductId() {
		return this.productId;
	}
	public TransactionEntryCreateCommand setProductId(final UUID productId) {
		this.productId = productId;
		return this;
	}

	@Autowired
    private TransactionEntryRepository transactionEntryRepository;
    
    @Autowired
    private ProductRepository productRepository;
}
