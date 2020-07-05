package com.ongiao.wis.dao;

import com.ongiao.wis.pojo.Product;
import com.ongiao.wis.pojo.WarehouseInventory;
import jdk.nashorn.internal.runtime.PrototypeObject;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductMapper {
    Product getProduct(String code);
    List<Map<String, Object>> getProducts();
    int addProduct(Product product);
    int addProducts(List<Product> products);
    int removeProduct(String code);
    int uploadProductCsv(List<Product> products);
}
