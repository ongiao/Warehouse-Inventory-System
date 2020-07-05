package com.ongiao.wis.pojo;

import org.junit.Assert;
import org.junit.Test;

public class WarehouseInventoryTest {
    @Test
    public void TestWarehouseInventory() {
        WarehouseInventory warehouseInventory1 = new WarehouseInventory("Cola", "FM-HKTV08", "NP", 10, 15);

        Assert.assertEquals("Cola", warehouseInventory1.getProductName());
        Assert.assertEquals("FM-HKTV08", warehouseInventory1.getProductCode());
        Assert.assertEquals("NP", warehouseInventory1.getLocationName());
        Assert.assertEquals(10, warehouseInventory1.getQuanty());
        Assert.assertEquals(String.valueOf(15.0), String.valueOf(warehouseInventory1.getPerWeight()));

        WarehouseInventory warehouseInventory2 = new WarehouseInventory();

        warehouseInventory2.setProductName("Cola");
        warehouseInventory2.setProductCode("FM-HKTV08");
        warehouseInventory2.setLocationName("NP");
        warehouseInventory2.setQuanty(10);
        warehouseInventory2.setPerWeight(15);

        Assert.assertEquals("Cola", warehouseInventory2.getProductName());
        Assert.assertEquals("FM-HKTV08", warehouseInventory2.getProductCode());
        Assert.assertEquals("NP", warehouseInventory2.getLocationName());
        Assert.assertEquals(10, warehouseInventory2.getQuanty());
        Assert.assertEquals(String.valueOf(15.0), String.valueOf(warehouseInventory2.getPerWeight()));
    }


}
