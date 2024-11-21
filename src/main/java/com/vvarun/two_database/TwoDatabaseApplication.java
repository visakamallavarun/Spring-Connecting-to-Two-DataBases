package com.vvarun.two_database;

import com.vvarun.two_database.orderentity.Order;
import com.vvarun.two_database.orderrepository.OrderDAO;
import com.vvarun.two_database.productentity.Product;
import com.vvarun.two_database.productrepository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class TwoDatabaseApplication implements CommandLineRunner {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private OrderDAO orderDAO;

	public static void main(String[] args) {
		SpringApplication.run(TwoDatabaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product = new Product();
		product.setName("laptop");
		product.setPrice(200000.0);
		productDAO.save(product);

		Order order=new Order();
		order.setOrderFrom("HYD");
		order.setOrderDate(LocalDate.now());
		orderDAO.save(order);
	}
}
