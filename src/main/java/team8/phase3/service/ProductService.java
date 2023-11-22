package team8.phase3.service;

import java.util.ArrayList;
import javax.sound.sampled.Port;
import team8.phase3.domain.Product;
import team8.phase3.repository.JdbcProductRepo;
import team8.phase3.repository.Status;

public class ProductService {

    private final JdbcProductRepo jdbcProductRepo;


    public ProductService(JdbcProductRepo jdbcProductRepo) {
        this.jdbcProductRepo = jdbcProductRepo;
    }

    public ArrayList<Product> getProductInfos(){
        return jdbcProductRepo.getProductInfos();
    }

    public Status addProduct(Product product){
        return jdbcProductRepo.addProductInfo(product);
    }

    public Status updateProduct(String attributeName, String newValue, Long productId){
        return jdbcProductRepo.updateProductAttribute(attributeName, newValue, productId);
    }
    public Status updateProduct(String attributeName, Long newValue, Long productId){
        return jdbcProductRepo.updateProductAttribute(attributeName, newValue, productId);
    }

    public Status deleteProduct(Long productId){
        return jdbcProductRepo.deleteProduct(productId);
    }

    public Product getProductById(Long productId){
        return jdbcProductRepo.getProductById(productId);
    }

    public long getLastId(){
        return jdbcProductRepo.getLastId();
    }
}
