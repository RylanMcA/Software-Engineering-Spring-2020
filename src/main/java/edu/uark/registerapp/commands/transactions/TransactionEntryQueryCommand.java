package edu.uark.registerapp.commands.transactions;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;
import edu.uark.registerapp.models.repositories.ProductRepository;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;

@Service
public class TransactionEntryQueryCommand implements ResultCommandInterface<List<Product>> {
	@Override
	public List<Product> execute() {
		final LinkedList<Product> products = new LinkedList<Product>();

		for (final TransactionEntryEntity entryEntity : entryRepo.findByTransactionId(transactionId)) {
            final UUID entryProductId = entryEntity.getProductId(); //Has product ID
            final Optional<ProductEntity> productEntity = this.productRepository.findById(entryProductId);
            products.addLast(new Product(productEntity.get()));
            }
		
		return products;
    }
    
    private UUID transactionId;
	public UUID getTransactionId() {
		return this.transactionId;
	}
	public TransactionEntryQueryCommand setTransactionId(final UUID transactionId) {
		this.transactionId = transactionId;
		return this;
    }   

	@Autowired
    ProductRepository productRepository;
    
    @Autowired
    TransactionEntryRepository entryRepo;
}