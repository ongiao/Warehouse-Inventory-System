package com.ongiao.wis.service;

import com.ongiao.wis.pojo.WarehouseInventory;

import java.util.List;
import java.util.Map;

public interface IWarehouseInventoryService {
    Map<String, Object> getAllWarehouseInventory();
    Map<String, Object> getWareHouseInventoryByCode(String productCode);
    Map<String, Object> getWareHouseInventoryByCodeAndLocation(Map<String, Object> map);
    Map<String, Object> addInventory(Map<String, Object> map);
    Map<String, Object> uploadInventoryCsv(List<WarehouseInventory> inventories);
    Map<String, Object> transfer(Map<String, Object> transferInfo);
}
