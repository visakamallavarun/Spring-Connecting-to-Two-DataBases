package com.vvarun.two_database.orderrepository;

import com.vvarun.two_database.orderentity.Order;
import com.vvarun.two_database.productentity.Product;
import org.springframework.data.repository.CrudRepository;

public interface OrderDAO extends CrudRepository<Order,Integer> {

}
