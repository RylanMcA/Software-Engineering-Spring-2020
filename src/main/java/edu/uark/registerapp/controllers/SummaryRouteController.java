package edu.uark.registerapp.controllers;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.products.ProductsQuery;
import edu.uark.registerapp.commands.transactions.TransactionSummaryCreate;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.enums.EmployeeClassification;
import edu.uark.registerapp.models.repositories.ProductRepository;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;
import edu.uark.registerapp.models.repositories.TransactionRepository;

@Controller
@RequestMapping(value = "/txnSummary")
public class SummaryRouteController extends BaseRouteController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showSummary(
		@RequestParam final Map<String, String> queryParameters,
		final HttpServletRequest request
	) {
		//If they're here with a transaction Id, reutnr them to main menu
		return this.buildNoPermissionsResponse("/mainMenu");
	}

	@RequestMapping(value = "/{transactionId}", method = RequestMethod.GET)
	public ModelAndView startWithId(
		@PathVariable final UUID transactionId,
		@RequestParam final Map<String, String> queryParameters,
		final HttpServletRequest request
	){
		final Optional<ActiveUserEntity> activeUserEntity =
			this.getCurrentUser(request);
		if (!activeUserEntity.isPresent()) {
			return this.buildInvalidSessionResponse();
		} else if (!this.isElevatedUser(activeUserEntity.get())) {
			return this.buildNoPermissionsResponse(
				ViewNames.PRODUCT_LISTING.getRoute());
		}

		final ModelAndView modelAndView =
			this.setErrorMessageFromQueryString(
				new ModelAndView(ViewNames.PRODUCT_DETAIL.getViewName()),
				queryParameters);

		modelAndView.addObject(
			ViewModelNames.IS_ELEVATED_USER.getValue(),
			EmployeeClassification.isElevatedUser(
				activeUserEntity.get().getClassification()));
				
		modelAndView.addObject("Summary",
		this.summaryCreate.setProductId(transactionId).execute());

		return modelAndView;
	}

	// Properties
	@Autowired
	private ProductsQuery productsQuery;

	@Autowired
	ProductRepository productRepository;

	@Autowired 
	TransactionSummaryCreate summaryCreate;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private TransactionEntryRepository transactionEntryRepository;
}