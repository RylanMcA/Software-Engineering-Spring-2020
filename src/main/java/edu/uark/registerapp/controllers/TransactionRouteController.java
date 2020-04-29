package edu.uark.registerapp.controllers;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.transactions.TransactionEntryQueryCommand;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/cart")
public class TransactionRouteController extends BaseRouteController {
    @RequestMapping(value = "/{transactionId}", method = RequestMethod.GET)
    public ModelAndView start(
		@PathVariable final UUID transactionId,
		@RequestParam final Map<String, String> queryParameters,
		final HttpServletRequest request
	) {

		System.out.println("Displaying cart for transaction " + transactionId.toString());
		final Optional<ActiveUserEntity> activeUserEntity =
			this.getCurrentUser(request);
		if (!activeUserEntity.isPresent()) {
			return buildInvalidSessionResponse();
		}

		ModelAndView modelAndView =
			this.setErrorMessageFromQueryString(
				new ModelAndView(ViewNames.SHOPPING_CART.getViewName()),
				queryParameters);

		modelAndView.addObject(
			"products",
			this.entryQuery.setTransactionId(transactionId).execute());

		modelAndView.addObject(
			ViewModelNames.IS_ELEVATED_USER.getValue(),
			this.isElevatedUser(activeUserEntity.get()));

		modelAndView.addObject("transactionId",transactionId);

		
		return modelAndView;
    }
    
    // Properties
	@Autowired
	private TransactionEntryQueryCommand entryQuery;
	


}