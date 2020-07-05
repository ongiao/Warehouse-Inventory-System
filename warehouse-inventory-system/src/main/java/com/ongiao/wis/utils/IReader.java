package com.ongiao.wis.utils;

import com.ongiao.wis.pojo.Product;
import com.ongiao.wis.pojo.WarehouseInventory;

import java.util.List;

public interface IReader {
//    void read(String path);
    List<String[]> readLines(String path);
    List<Product> readProduct(String path);
    List<WarehouseInventory> readProductInventory(String path);
}
