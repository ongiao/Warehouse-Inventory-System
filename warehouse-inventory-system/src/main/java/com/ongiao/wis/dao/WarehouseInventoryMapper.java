package com.ongiao.wis.dao;

import com.ongiao.wis.pojo.WarehouseInventory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WarehouseInventoryMapper {
    List<Map<String, Object>> getAllWarehouseInventory();
    List<Map<String, Object>> getWareHouseInventoryByCode(String productCode);
    List<Map<String, Object>> getWareHouseInventoryByCodeAndLocation(Map<String, Object> map);
    int addInventory(Map<String, Object> map);
    int addInventories(List<WarehouseInventory> inventories);
    int transfer(Map<String, Object> transferInfo);
}
