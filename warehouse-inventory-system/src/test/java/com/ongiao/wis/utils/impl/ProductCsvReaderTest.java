package com.ongiao.wis.utils.impl;

import com.ongiao.wis.pojo.Product;
import com.ongiao.wis.pojo.WarehouseInventory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by chenjunxing ON 2020-07-03 10:54.
 */
public class ProductCsvReaderTest {
    @Test
    public void TestProductCsvRead() {
        String path = "./products.csv";
        ProductCsvReader productCsvReader = new ProductCsvReader();
        List<Product> products = productCsvReader.readProduct(path);

        for (Product product : products) {
            Assert.assertEquals("com.ongiao.wis.pojo.Product", product.getClass().getName());
        }
    }
}
