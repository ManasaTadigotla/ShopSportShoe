package com.sportyshoespvtltd.shopsportshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoespvtltd.shopsportshoes.entity.CustomerOrder;

public interface CustomerOrderrepository extends JpaRepository<CustomerOrder,Long> {

}
