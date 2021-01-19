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

            Product laptop_dell = new Product("P1235","Dell Inspiron", new BigDecimal(700));
            laptop_dell.setDescription("Dell Inspiron 14-inch Laptop(Black) with 3rd Generation Intel Core processors");
            laptop_dell.setCategory("Laptop");
            laptop_dell.setManufacturer("Dell");
            laptop_dell.setUnitsInStock(1000);

            Product tablet_Nexus = new Product("P1236","Nexus 7", new BigDecimal(300));
            tablet_Nexus.setDescription("Google Nexus 7 is the lightest7 inch tablet With a quad-core Qualcomm Snapdragon™ S4 Proprocessor");
                    tablet_Nexus.setCategory("Tablet");
            tablet_Nexus.setManufacturer("Google");
            tablet_Nexus.setUnitsInStock(1000);

            listOfProducts.add(iphone);
            listOfProducts.add(laptop_dell);
            listOfProducts.add(tablet_Nexus);
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
//        public Set<Product> getProductsByFilter(Map<String, List<String>>filterParams) {
//            Set<Product> productsByBrand = new HashSet<Product>();
//            Set<Product> productsByCategory = new HashSet<Product>();
//            Set<Product> productsByPrice = new HashSet<Product>();
//            Set<String> criterias = filterParams.keySet();
//
//            if(criterias.contains("brand")) {
//                for(String brandName: filterParams.get("brand")) {
//                    for(Product product: listOfProducts) {
//                        if(brandName.equalsIgnoreCase(product.getManufacturer())){
//                            productsByBrand.add(product);
//                        }
//                    }
//                }
//            }
//
//            if(criterias.contains("category")) {
//                for (String category : filterParams.get("category")) {
//                    productsByCategory.addAll(this.getProductsByCategory(category));
//                }
//            }
//
//            if(criterias.contains("price")) {
//                    for(String priceString: filterParams.get("unitPrice")) {
//                        for (Product product:listOfProducts) {
//                            if (product.getUnitPrice().toString().equalsIgnoreCase(priceString)){
//                                productsByPrice.add(product);
//                            }
//                        }
//
//                    }
//            }
//            productsByCategory.retainAll(productsByBrand);
//            //productsByCategory.addAll(productsByPrice);
//            return productsByCategory;
//
//                }

        public List<Product> getProductsByManufacturer(String manufacturer) {
            return null;
        }
public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {

    Set<Product> sorted = new HashSet<Product>() ;
    sorted.addAll(listOfProducts);

    List<Product> listToBeDeleted = new ArrayList<Product>();

    Set<String> criterias = filterParams.keySet();

    if (criterias.contains("brand")) {
        for (String brandName : filterParams.get("brand")) {
            for (Product product : sorted) {
                if (!brandName.equalsIgnoreCase(product.getManufacturer())) {
                    //sorted.remove(product);
                    listToBeDeleted.add(product);
                    System.out.println(product.toString());
                }
            }
        }
    }
sorted.removeAll(listToBeDeleted);
    listToBeDeleted = new ArrayList<Product>();

    if (criterias.contains("category")) {
        for (String category : filterParams.get("category")) {
            // productsByCategory.addAll(this.getProductsByCategory(category));
            for (Product product : sorted) {
                if (!category.equalsIgnoreCase(product.getCategory())) {
                    listToBeDeleted.add(product);
                }
            }
        }
    }
    sorted.removeAll(listToBeDeleted);
    listToBeDeleted = new ArrayList<Product>();

    if (criterias.contains("price")) {
        for (String priceString : filterParams.get("price")) {
            for (Product product : sorted) {
                if (!product.getUnitPrice().toString().equalsIgnoreCase(priceString)) {
                    listToBeDeleted.add(product);

                }
            }

        }

    }
    sorted.removeAll(listToBeDeleted);
//        productsByCategory.retainAll(productsByBrand);
//        //productsByCategory.addAll(productsByPrice);

    return sorted;

}



//        public List<Product> getProductsByManufacturer(String manufacturer) {
//            List<Product> productsByManufacturer = new ArrayList<Product>();
//            for (Product product : getAllProducts()) {
//                if (product.getManufacturer().equalsIgnoreCase(manufacturer)) {
//                    productsByManufacturer.add(product);
//                }
//            }





//            List<Product> productsByCategory = new ArrayList<Product>();
//
//            for(Product product: listOfProducts) {
//                if(manufacturer.equalsIgnoreCase(product.getManufacturer())){
//productsByCategory.retainAll(productsByManufacturer);
//                }
//            }
//            return productsByCategory;
//            List<Product> productsBуManufacturer= new ArrayList<Product>();
//
//            for(Product product: listOfProducts) {
//                if(manufacturer.equalsIgnoreCase(product.getManufacturer())){
//                    productsBуManufacturer.add(product);
//                }
//            }
//            return productsBуManufacturer;

//            return productsByManufacturer;
//        }
}