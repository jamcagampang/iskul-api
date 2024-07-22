package com.jamsoftwares.iskul.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jamsoftwares.iskul.account.model.User;

@Repository
public interface AccountRepository extends JpaRepository<User, Long> {

}
