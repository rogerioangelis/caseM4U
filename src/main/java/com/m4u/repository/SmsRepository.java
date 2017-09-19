package com.m4u.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.m4u.domain.Sms;

@Repository
public interface SmsRepository extends CrudRepository<Sms, Long>{

}
