package com.keyboardshop.utils;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.keyboardshop.utils.seeders.CategorySeeder;
import com.keyboardshop.utils.seeders.ProductSeeder;
import com.keyboardshop.utils.seeders.UserSeeder;

@Component
public class Seeder
{
	private final UserSeeder userSeeder;
	private final CategorySeeder categorySeeder;
	private final ProductSeeder productSeeder;

	public Seeder(UserSeeder userSeeder, CategorySeeder categorySeeder, ProductSeeder productSeeder)
	{
		this.userSeeder = userSeeder;
		this.categorySeeder = categorySeeder;
		this.productSeeder = productSeeder;
	}

	@EventListener
	public void seed(ContextRefreshedEvent event)
	{
		this.userSeeder.seed();
		this.categorySeeder.seed();
		this.productSeeder.seed();
	}
}
