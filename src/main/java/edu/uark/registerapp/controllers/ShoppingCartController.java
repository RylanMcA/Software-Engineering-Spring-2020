package edu.uark.registerapp.controllers;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// import edu.uark.registerapp.commands.products.ProductsQuery;
// import edu.uark.registerapp.commands.products.ProductCreateCommand;
// import edu.uark.registerapp.commands.products.ProductDeleteCommand;
// import edu.uark.registerapp.commands.products.ProductUpdateCommand;
// import edu.uark.registerapp.commands.products.ProductsSearch;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/cart")
public class ShoppingCartController extends BaseRouteController {
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
			ViewModelNames.IS_ELEVATED_USER.getValue(),
			this.isElevatedUser(activeUserEntity.get()));

		try {
			// if no search is entered, show all products in cart
			modelAndView.addObject(
				ViewModelNames.CART.getValue());
			// if a search term is entered, 
			// modelAndView.addObject(
			// 	ViewModelNames.PRODUCTS.getValue(), 
			// 	this.productsSearch.execute());
		} catch (final Exception e) {
			modelAndView.addObject(
				ViewModelNames.ERROR_MESSAGE.getValue(),
				e.getMessage());
			modelAndView.addObject(
				ViewModelNames.CART.getValue(),
				(new Product[0]));
		}
		
		return modelAndView;
    }
    
    // Properties
	@Autowired
	//private ProductsQuery productsQuery;
	//private ProductsSearch productsSearch;

    //This is a method for adding a product to the cart
    //If the product is in the cart, add more
    //If the product is not in the cart, add to cart
	@RequestMapping()
	public ModelAndView add() {
		return (new ModelAndView());
	}

    //This is the method for removing a product from the cart
    //If the product is in the cart, remove it
    //User should not be able to remove an item that doesn't exist

/*	
	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	public @ResponseBody ApiResponse deleteFromCart(
		@PathVariable final UUID productId, 
		final HttpServletRequest request,
		final HttpServletResponse response
	) {

		final ApiResponse elevatedUserResponse =
			this.redirectUserNotElevated(
				request,
				response,
				ViewNames.PRODUCT_LISTING.getRoute());

		if (!elevatedUserResponse.getRedirectUrl().equals(StringUtils.EMPTY)) {
			return elevatedUserResponse;
		}

		this.productDeleteCommand
			.setProductId(productId)
			.execute();

		return new ApiResponse();
	}
	
*/
    
    //This is the method for empyting the shopping cart
    //Removes all products from the cart
    //Should not be able to empty an empty carts

    /*@RequestMapping()
    public ModelAndView cancel() {
        return (new ModelAndView());
    }*/

    //This is the method to print a summary of the transaction
    //Displays each product in shopping cart along with price/quantity/total quantity/tostal price

    /*@RequestMapping()
    public ModelAndView summary() {
        return (new ModelAndView());
    }*/

}