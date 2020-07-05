package com.ongiao.wis.utils.impl;

import com.ongiao.wis.pojo.Product;
import com.ongiao.wis.pojo.WarehouseInventory;
import com.ongiao.wis.service.IWarehouseInventoryService;
import com.ongiao.wis.service.impl.WarehouseInventoryServiceImpl;
import com.ongiao.wis.utils.IReader;
import java.util.ArrayList;
import java.util.List;

public class ProductQuantyCsvReader implements IReader {

    private FileLineReader fileLineReader = new FileLineReader();
    private IWarehouseInventoryService iWarehouseInventoryService = new WarehouseInventoryServiceImpl();

    public List<String[]> readLines (String path) {
        return null;
    }

    public List<Product> readProduct (String path) {
        return null;
    }

    public List<WarehouseInventory> readProductInventory (String path) {
        List<String[]> list = fileLineReader.readLines(path);

        List<WarehouseInventory> warehouseInventories = new ArrayList<>();

        for (String[] strings : list) {
            String productName = strings[0];
            String productCode = strings[1];
            String locationName = strings[2];
            int quanty = Integer.valueOf(strings[3]);
            double weight = Double.valueOf(strings[4]);

            warehouseInventories.add(new WarehouseInventory(productName, productCode, locationName, quanty, weight));
        }

        return warehouseInventories;
    }
}
