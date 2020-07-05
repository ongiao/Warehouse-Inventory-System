package com.ongiao.wis.service.impl;

import com.ongiao.wis.pojo.Product;
import com.ongiao.wis.service.IProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductServiceTest {

    private IProductService iProductService = new ProductServiceImpl();

    @Test
    public void testGetProduct() {
        Map<String, Object> productMap = iProductService.getProduct("FM-HKTV01");

        Product product = (Product) productMap.get("data");

        Assert.assertEquals(true, productMap.get("result"));
        Assert.assertEquals("success", productMap.get("msg"));
        Assert.assertEquals("face mask", product.getName());
        Assert.assertEquals("FM-HKTV01", product.getCode());
        Assert.assertEquals("100.0", String.valueOf(product.getWeight()));
    }

//    @Test
//    public void TestGetProductList() {
//
//        Map<String, Object> productMap = iProductService.getProductLists();
//
//        Assert.assertEquals(true, productMap.get("result"));
//        Assert.assertEquals("success", productMap.get("msg"));
//        Assert.assertEquals(ArrayList.class.toString(), productMap.get("data").getClass().toString());
//    }

    @Test
    public void testAddProducts() {
        List<Product> list = new ArrayList<>();

        Product product1 = new Product("face paper", "HKTV11", 10);
        Product product2 = new Product("chair", "HKTV12", 170);
        Product product3 = new Product("SSD", "HKTV13", 50);

        list.add(product1);
        list.add(product2);
        list.add(product3);

        Map<String, Object> map = iProductService.addProducts(list);

        Assert.assertEquals(true, map.get("result"));
        Assert.assertEquals("You have successfully insert products!", map.get("msg"));
    }

    @Test
    public void testuploadProductCsv() {
        List<Product> list = new ArrayList<>();

        Product product1 = new Product("face paper", "HKTV11", 10);
        Product product2 = new Product("chair", "HKTV12", 170);
        Product product3 = new Product("SSD", "HKTV13", 50);

        list.add(product1);
        list.add(product2);
        list.add(product3);

        Map<String, Object> map = iProductService.uploadProductCsv(list);

        Assert.assertEquals(true, map.get("result"));
        Assert.assertEquals("Successfully insert all products by csv file!", map.get("msg"));
    }

    @Test
    public void testRemoveProduct() {
        String code = "FM-HKTV01";

        Map<String, Object> map = iProductService.removeProduct(code);

        Assert.assertEquals(true, map.get("result"));
        Assert.assertEquals("You have successfully remove the product!", map.get("msg"));

        Map<String, Object> productMap = iProductService.getProduct(code);
        Product product = (Product) productMap.get("data");
        Assert.assertEquals(0, product.getStatus());
    }
}
