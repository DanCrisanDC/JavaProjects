package bll;

import model.Product;

public class ProductPriceValidator {
	
	private static final double MIN_PRICE = 0.0;

	public void validate(Product t) {

		if (t.getPrice() <= MIN_PRICE) {
			throw new IllegalArgumentException("The Client Age limit is not respected!");
		}
	}
}
