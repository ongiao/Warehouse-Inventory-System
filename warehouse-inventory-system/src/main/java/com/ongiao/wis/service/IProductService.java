package com.ongiao.wis.service;

import com.ongiao.wis.pojo.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by chenjunxing ON 2020-07-03 01:59.
 */
public interface IProductService {
    Map<String, Object> getProduct(String code);
    Map<String, Object> getProducts();
    Map<String, Object> addProduct(String name, String code, double weight);
    Map<String, Object> addProducts(List<Product> products);
    Map<String, Object> uploadProductCsv(List<Product> products);
    Map<String, Object> removeProduct(String code);
}
