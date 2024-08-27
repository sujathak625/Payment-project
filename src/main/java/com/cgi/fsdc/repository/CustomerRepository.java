package com.cgi.fsdc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.fsdc.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{}
