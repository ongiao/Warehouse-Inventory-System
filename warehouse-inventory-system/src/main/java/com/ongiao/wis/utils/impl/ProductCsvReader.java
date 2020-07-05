package com.ongiao.wis.utils.impl;

import com.ongiao.wis.pojo.Product;
import com.ongiao.wis.pojo.WarehouseInventory;
import com.ongiao.wis.utils.IReader;
import java.util.ArrayList;
import java.util.List;

public class ProductCsvReader implements IReader {

    private FileLineReader fileLineReader = new FileLineReader();

    @Override
    public List<String[]> readLines (String path) {
        return null;
    }

    @Override
    public List<Product> readProduct (String path) {
        List<String[]> list = fileLineReader.readLines(path);

        List<Product> products = new ArrayList<>();

        for (String[] strings : list) {
            String name = strings[0];
            String code = strings[1];
            double weight = Double.valueOf(strings[2]);
            products.add(new Product(name, code, weight));
        }

        return products;
    }

    @Override
    public List<WarehouseInventory> readProductInventory (String path) {
        return null;
    }
}
