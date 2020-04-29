package edu.uark.registerapp.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.registerapp.commands.exceptions.UnauthorizedException;
import edu.uark.registerapp.commands.transactions.TransactionCreateCommand;
import edu.uark.registerapp.commands.transactions.TransactionDeleteCommand;
import edu.uark.registerapp.commands.transactions.TransactionEntryCreateCommand;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.ApiResponse;
import edu.uark.registerapp.models.entities.ActiveUserEntity;

@RestController
@RequestMapping(value = "/api/transaction")
public class TransactionRestController extends BaseRestController {
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody ApiResponse createStartTransaction(
		final HttpServletRequest request,
		final HttpServletResponse response
	) {

		try {
			final ActiveUserEntity activeUserEntity =
				this.validateActiveUserCommand
					.setSessionKey(request.getSession().getId())
					.execute();

			if (activeUserEntity == null) {
				return this.redirectSessionNotActive(response);
			}

			UUID createdTransactionId = this.transactionCreateCommand
				.setEmployeeId(activeUserEntity.getId())
				.execute();

			return (new ApiResponse())
				.setRedirectUrl(
					ViewNames.PRODUCT_LISTING.getRoute()
						.concat("/")
						.concat(createdTransactionId.toString()));
		} catch (final UnauthorizedException e) {
			return this.redirectSessionNotActive(response);
		} catch (final Exception e) {
			System.out.println(
				"An unknown error occurred while attempting to start a transaction. "
				+ e.getMessage());

			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return (new ApiResponse())
				.setErrorMessage("Unable to start transaction. "
					+ e.getMessage());
		}
	}

	@RequestMapping(value = "/{transactionId}", method = RequestMethod.DELETE)
	public @ResponseBody ApiResponse cancelTransaction(
		@PathVariable final UUID transactionId, 
		final HttpServletRequest request,
		final HttpServletResponse response
	){
		final ApiResponse elevatedUserResponse =
		this.redirectUserNotElevated(
			request,
			response,
			ViewNames.MAIN_MENU.getRoute());

		if (!elevatedUserResponse.getRedirectUrl().equals("")) {
			return elevatedUserResponse;
		}

		this.deleteTransaction.setTransactionId(transactionId).execute();


		return new ApiResponse();
	}


	@RequestMapping(value="/{transactionId}/{productId}",method=RequestMethod.POST)
	public @ResponseBody ApiResponse addProduct(
		@PathVariable final UUID transactionId,
		@PathVariable final UUID productId,
		final HttpServletRequest request,
		final HttpServletResponse response
	){
		try {
			final ActiveUserEntity activeUserEntity =
				this.validateActiveUserCommand
					.setSessionKey(request.getSession().getId())
					.execute();

			if (activeUserEntity == null) {
				return this.redirectSessionNotActive(response);
			}


			createEntry.setTransactionId(transactionId).setProductId(productId).execute();

			return (new ApiResponse())
				.setRedirectUrl(
					ViewNames.PRODUCT_LISTING.getRoute()
						.concat("/")
						.concat(transactionId.toString()));
		} catch (final UnauthorizedException e) {
			return this.redirectSessionNotActive(response);
		} catch (final Exception e) {
			System.out.println(
				"An unknown error occurred while attempting to add an entry. "
				+ e.getMessage());

			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return (new ApiResponse())
				.setErrorMessage("Unable to add entry. "
					+ e.getMessage());
		}
	}

	@RequestMapping(value = "/{transactionId}/{prodcutId}", method = RequestMethod.DELETE)
	public @ResponseBody ApiResponse deleteProductEntry(
		@PathVariable final UUID transactionId, 
		final HttpServletRequest request,
		final HttpServletResponse response
	){
		final ApiResponse elevatedUserResponse =
		this.redirectUserNotElevated(
			request,
			response,
			ViewNames.MAIN_MENU.getRoute());

		if (!elevatedUserResponse.getRedirectUrl().equals("")) {
			return elevatedUserResponse;
		}

		this.deleteTransaction.setTransactionId(transactionId).execute();


		return new ApiResponse();
	}


	@Autowired
	private TransactionCreateCommand transactionCreateCommand;

	@Autowired
	private TransactionDeleteCommand deleteTransaction;

	@Autowired 
	private TransactionEntryCreateCommand createEntry;

}
