package com.ongiao.wis.pojo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;

public class WarehouseTest {

    @Test
    public void testWarehouse() {
        Warehouse warehouse1 = new Warehouse("NP");

        Assert.assertEquals("NP", warehouse1.getLocation());

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setLocation("NP");

        Assert.assertEquals("NP", warehouse2.getLocation());
    }
}

