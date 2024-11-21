package com.vvarun.two_database.productrepository;

import com.vvarun.two_database.productentity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product,Integer> {

}
