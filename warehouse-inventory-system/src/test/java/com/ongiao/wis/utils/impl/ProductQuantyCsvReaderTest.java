package com.ongiao.wis.utils.impl;

import com.ongiao.wis.pojo.WarehouseInventory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ProductQuantyCsvReaderTest {
    @Test
    public void TestProductQuantyCsvRead() {
        String path = "/Users/chenjunxing/Desktop/Documents/warehouse-inventory-system/src/test/java/com/ongiao/wis/utils/impl/quanty.csv";
        ProductQuantyCsvReader reader = new ProductQuantyCsvReader();
        List<WarehouseInventory> warehouseInventories = reader.readProductInventory(path);

        for (WarehouseInventory warehouseInventory : warehouseInventories) {
            Assert.assertEquals("com.ongiao.wis.pojo.WarehouseInventory", warehouseInventory.getClass().getName());
        }
    }
}
