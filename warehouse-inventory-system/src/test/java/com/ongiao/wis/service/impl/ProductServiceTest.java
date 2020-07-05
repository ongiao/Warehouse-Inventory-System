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
        String code = "FM-HKTV66";
        String name = "typec charger";
        double weight = 30;

        iProductService.addProduct(name, code, weight);

        Map<String, Object> productMap = iProductService.getProduct("FM-HKTV66");

        Product product = (Product) productMap.get("data");

        Assert.assertEquals(true, productMap.get("result"));
        Assert.assertEquals("success", productMap.get("msg"));
        Assert.assertEquals("typec charger", product.getName());
        Assert.assertEquals("FM-HKTV66", product.getCode());
        Assert.assertEquals("30.0", String.valueOf(product.getWeight()));
        iProductService.removeProduct(code);
    }

    @Test
    public void TestGetProductList() {
        // insert before getting
        List<Product> list = new ArrayList<>();

        Product product1 = new Product("face paper", "FM-HKTV11", 10);
        Product product2 = new Product("chair", "FM-HKTV12", 170);

        list.add(product1);
        list.add(product2);
        iProductService.addProducts(list);

        Map<String, Object> productMap = iProductService.getProducts();

        Assert.assertEquals(true, productMap.get("result"));
        Assert.assertEquals("success", productMap.get("msg"));
        Assert.assertEquals(ArrayList.class.toString(), productMap.get("data").getClass().toString());
        iProductService.removeProduct("FM-HKTV11");
        iProductService.removeProduct("FM-HKTV12");
    }

    @Test
    public void testAddProducts() {
        List<Product> list = new ArrayList<>();

        Product product1 = new Product("face paper", "HKTV11", 10);
        Product product2 = new Product("chair", "HKTV12", 170);

        list.add(product1);
        list.add(product2);

        Map<String, Object> map = iProductService.addProducts(list);

        Assert.assertEquals(true, map.get("result"));
        Assert.assertEquals("You have successfully insert products!", map.get("msg"));
        iProductService.removeProduct(product1.getCode());
        iProductService.removeProduct(product2.getCode());
    }

    @Test
    public void testuploadProductCsv() {
        List<Product> list = new ArrayList<>();

        Product product1 = new Product("face paper", "HKTV11", 10);
        Product product2 = new Product("chair", "HKTV12", 170);

        list.add(product1);
        list.add(product2);

        Map<String, Object> map = iProductService.uploadProductCsv(list);

        Assert.assertEquals(true, map.get("result"));
        Assert.assertEquals("Successfully insert all products by csv file!", map.get("msg"));
        iProductService.removeProduct(product1.getCode());
        iProductService.removeProduct(product2.getCode());
    }

    @Test
    public void testRemoveProduct() {
        String code = "FM-HKTV66";
        String name = "typec charger";
        double weight = 30;

        iProductService.addProduct(name, code, weight);

        Map<String, Object> map = iProductService.removeProduct(code);

        Assert.assertEquals(true, map.get("result"));
        Assert.assertEquals("You have successfully remove the product!", map.get("msg"));

        Map<String, Object> productMap = iProductService.getProduct(code);
        Assert.assertEquals(false, productMap.get("result"));
        Assert.assertEquals("We can't find the product you have specified", productMap.get("msg"));
        Assert.assertEquals(null, productMap.get("data"));
    }
}
