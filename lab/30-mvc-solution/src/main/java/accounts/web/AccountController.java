package accounts.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import accounts.AccountManager;
import rewards.internal.account.Account;
import rewards.internal.account.Beneficiary;

/**
 * A Spring MVC @Controller controller handling requests to view and modify
 * Account information.
 * <p>
 * Note that all the Account application classes are imported from the
 * <tt>rewards-db</tt> project:
 * <ul>
 * <li>Domain objects: {@link Account} and {@link Beneficiary}</li>
 * <li>Service layer: {@link AccountManager} interface and its
 * implementations</li>
 * <li>No repository layer is being used - the account-manager does
 * everything</li>
 */
@RestController
public class AccountController {

	private AccountManager accountManager;

	/**
	 * Creates a new AccountController with a given account manager.
	 */
	@Autowired
	public AccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	/**
	 * <p>
	 * Provide a model with an account for the account detail page.
	 * </p>
	 * 
	 * @param id
	 *            the id of the account
	 * @param model
	 *            the "implicit" model created by Spring MVC
	 */
	@RequestMapping("/accounts/{entityId}")
	public Account accountDetails(@PathVariable("entityId") long id) {
		return accountManager.getAccount(id);
	}

	/**
	 * <p>
	 * Provide a model with a list of all accounts for the account List page.
	 * </p>
	 * 
	 * @param model
	 *            the "implicit" model created by Spring MVC
	 */
	@RequestMapping("/accounts")
	public List<Account> accountList() {
		return accountManager.getAllAccounts();
	}
}
