package edu.uark.registerapp.commands.products;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.repositories.ProductRepository;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;

@Service
public class TransactionEntryDeleteCommand implements VoidCommandInterface {
	@Transactional
	@Override
	public void execute() {
		// final Optional<ProductEntity> productEntity = this.productRepository.findById(this.productId);
		// if (!productEntity.isPresent()) { // No record with the associated record ID exists in the database.
		// 	throw new NotFoundException("Product");
		// }

		//this.productRepository.delete();
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
    
    @Autowired
    private ProductRepository productRepository;
}
