package com.jamsoftwares.iskul.account;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jamsoftwares.iskul.account.exception.UserNotFoundException;
import com.jamsoftwares.iskul.account.model.User;
import com.jamsoftwares.iskul.account.response.ResponseSingleTO;

@RestController
public class AccountController {

	Logger logger = LogManager.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseSingleTO> retrieveUser(@PathVariable Long userId) {

		ResponseSingleTO response = new ResponseSingleTO();

		User user = this.accountService.getAccount(userId);

		if (Objects.isNull(user)) {
			logger.info("[AccountController-retrieveUser(Long)] - User Not Found");
			throw new UserNotFoundException("User ID " + userId + " is not existing.");
		}

		logger.info("[AccountController-retrieveUser(Long)] - User Found - " + user);

		response.setUser(user);

		return ResponseEntity.ok(response);
	}
}
