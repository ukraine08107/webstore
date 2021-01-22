package com.packt.webstore.domain.repository.impl;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.stereotype.Repository;
import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
    @Repository
    public class InMemoryProductRepository implements ProductRepository{

        private List<Product> listOfProducts = new ArrayList<Product>();

        public InMemoryProductRepository() {
            Product iphone = new Product("P1234","iPhone 5s", new BigDecimal(500));
            iphone.setDescription("Apple iPhone 5s smartphone with 4.00-inch 640x1136 display and 8-megapixel rear camera");
                    iphone.setCategory("Smartphone");
            iphone.setManufacturer("Apple");
            iphone.setUnitsInStock(1000);

            Product laptop_dell = new Product("P1235","Dell Inspiron", new BigDecimal(800));
            laptop_dell.setDescription("Dell Inspiron 14-inch Laptop(Black) with 3rd Generation Intel Core processors");
            laptop_dell.setCategory("Laptop");
            laptop_dell.setManufacturer("Dell");
            laptop_dell.setUnitsInStock(1000);

            Product tablet_Nexus = new Product("P1236","Nexus 7", new BigDecimal(300));
            tablet_Nexus.setDescription("Google Nexus 7 is the lightest7 inch tablet With a quad-core Qualcomm Snapdragon™ S4 Proprocessor");
            tablet_Nexus.setCategory("Tablet");
            tablet_Nexus.setManufacturer("Google");
            tablet_Nexus.setUnitsInStock(1000);

            Product laptop_google = new Product("P1237","Google Inspiron", new BigDecimal(666));
            laptop_google.setDescription("Google Laptop(Black");
            laptop_google.setCategory("Laptop");
            laptop_google.setManufacturer("Google");
            laptop_google.setUnitsInStock(1000);

            listOfProducts.add(iphone);
            listOfProducts.add(laptop_dell);
            listOfProducts.add(tablet_Nexus);
            listOfProducts.add(laptop_google);
        }
        public List<Product> getAllProducts() {
            return listOfProducts;
        }

        public Product getProductById(String productID) {
            Product productById = null;

            for(Product product : listOfProducts) {
                if(product!=null && product.getProductId()!=null && product.getProductId().equals(productID)){
                    productById = product;
                    break;
                }
            }

            if(productById == null){
                throw new IllegalArgumentException("No products found with the product id: "+ productID);
            }

            return productById;
        }

        public List<Product> getProductsByCategory(String category) {
            List<Product> productsByCategory = new ArrayList<Product>();

            for(Product product: listOfProducts) {
                if(category.equalsIgnoreCase(product.getCategory())){
                    productsByCategory.add(product);
                }
            }

            return productsByCategory;

        }
        public List<Product> getProductsByManufacturer(String manufacturer) {
            return null;
        }
public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
    Set<Product> addedProducts = new HashSet<Product>();
    addedProducts.addAll(listOfProducts);
    Set<String> criterias = filterParams.keySet();

    if (criterias.contains("brand")) {
        Set<Product> sorted = new HashSet<Product>() ;
        for (String brandName : filterParams.get("brand")) {
            for (Product product : listOfProducts) {
                if (brandName.equalsIgnoreCase(product.getManufacturer())) {
                    sorted.add(product);
                }
            }
        }
        addedProducts.retainAll(sorted);
    }
    if (criterias.contains("category")) {
        Set<Product> sorted = new HashSet<Product>() ;
        for (String category : filterParams.get("category")) {
            for (Product product : listOfProducts) {
                if (category.equalsIgnoreCase(product.getCategory())) {
                    sorted.add(product);
                }
            }
        }
        addedProducts.retainAll(sorted);
    }
    if (criterias.contains("price")) {
    ArrayList<String> prices = new ArrayList<String>();
        for (String priceString : filterParams.get("price")) {
        prices.add(priceString);
        }
    BigDecimal min = new BigDecimal(prices.get(0));
        BigDecimal max = new BigDecimal(prices.get(1));

        Set<Product> sorted = new HashSet<Product>() ;
            for (Product product : listOfProducts) {
                if (product.getUnitPrice().compareTo(min)>=0 && product.getUnitPrice().compareTo(max)<=0){
                    sorted.add(product);
                }
                }

        addedProducts.retainAll(sorted);
    }
    return addedProducts;
        }
}