package com.ongiao.wis.pojo;

import org.junit.Assert;
import org.junit.Test;

public class ProductTest {
    @Test
    public void testProduct() {
        Product product1 = new Product("coke cola", "FM-HKTV55", 20);

        Assert.assertEquals("coke cola", product1.getName());
        Assert.assertEquals("FM-HKTV55", product1.getCode());
        Assert.assertEquals(String.valueOf(20.0), String.valueOf(product1.getWeight()));

        Product product2 = new Product();

        product2.setName("coke cola");
        product2.setCode("FM-HKTV55");
        product2.setWeight(20);

        Assert.assertEquals("coke cola", product2.getName());
        Assert.assertEquals("FM-HKTV55", product2.getCode());
        Assert.assertEquals(String.valueOf(20.0), String.valueOf(product2.getWeight()));
    }
}
