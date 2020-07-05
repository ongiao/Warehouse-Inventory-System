package com.ongiao.wis.utils.impl;

import com.ongiao.wis.pojo.Product;
import com.ongiao.wis.pojo.WarehouseInventory;
import com.ongiao.wis.utils.IReader;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class FileLineReader implements IReader {

    @Override
    public List<Product> readProduct (String path) {
        return null;
    }

    @Override
    public List<WarehouseInventory> readProductInventory (String path) {
        return null;
    }

    @Override
    public List<String[]> readLines(String path) {
        String charset = "utf-8";

        File csv = new File(path);

        CSVReader reader = null;
        List<String[]> list = null;

        try {
            reader = new CSVReader(new InputStreamReader(new FileInputStream(csv), charset));

            String [] header = reader.readNext();

            // reading second line
            list = reader.readAll();

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
