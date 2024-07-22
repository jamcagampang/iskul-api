package com.jamsoftwares.iskul.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamsoftwares.iskul.account.exception.UserNotFoundException;
import com.jamsoftwares.iskul.account.exception.UserNotTeacherException;
import com.jamsoftwares.iskul.account.model.User;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;

	public User getAccount(Long userId) {

		User user = this.accountRepo.findById(userId).orElse(null);

		return user;
	}

	public boolean checkUserIfTeacher(Long userId) {

		User user = this.getAccount(userId);

		if (user == null) {
			throw new UserNotFoundException("User ID " + userId + " is not existing.");
		}

		if (AccountConstants.USER_NOT_TEACHER.equals(user.getUserType())) {
			throw new UserNotTeacherException("User ID " + userId + " is not existing.");
		}

		return true;
	}
}
