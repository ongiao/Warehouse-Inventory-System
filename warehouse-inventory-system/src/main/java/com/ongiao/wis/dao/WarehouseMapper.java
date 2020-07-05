package com.ongiao.wis.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface WarehouseMapper {
    List<Map<String, Object>> getWarehouses();
}
