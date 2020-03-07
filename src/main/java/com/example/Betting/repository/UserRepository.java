package com.example.Betting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Betting.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findById(long Id);
}
