package com.ongiao.wis.service.impl;

import com.ongiao.wis.dao.ProductMapper;
import com.ongiao.wis.dao.WarehouseInventoryMapper;
import com.ongiao.wis.pojo.Product;
import com.ongiao.wis.pojo.WarehouseInventory;
import com.ongiao.wis.service.IWarehouseInventoryService;
import com.ongiao.wis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WarehouseInventoryServiceImpl implements IWarehouseInventoryService {

    private Logger logger = Logger.getLogger(WarehouseInventoryServiceImpl.class);

    public Map<String, Object> getAllWarehouseInventory() {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            WarehouseInventoryMapper warehouseInventoryMapper = sqlSession.getMapper(WarehouseInventoryMapper.class);
            List<Map<String, Object>> allWarehouseInventory = warehouseInventoryMapper.getAllWarehouseInventory();

            if (allWarehouseInventory == null || allWarehouseInventory.size() == 0) {
                resultMap.put("result", false);
                resultMap.put("msg", "There is no inventory records.");
                resultMap.put("data", null);
                return resultMap;
            }

            resultMap.put("result", true);
            resultMap.put("msg", "success");
            resultMap.put("data", allWarehouseInventory);

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            sqlSession.close();
        }

        return resultMap;
    }

    public Map<String, Object> getWareHouseInventoryByCode(String productCode) {
        Map<String, Object> resultMap = new HashMap<>();

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            WarehouseInventoryMapper warehouseInventoryMapper = sqlSession.getMapper(WarehouseInventoryMapper.class);

            List<Map<String, Object>> productInventory = warehouseInventoryMapper.getWareHouseInventoryByCode(productCode);

            if (productInventory == null || productInventory.size() == 0) {
                resultMap.put("result", false);
                resultMap.put("msg", "There is no inventory records.");
                resultMap.put("data", null);
                return resultMap;
            }

            resultMap.put("result", true);
            resultMap.put("msg", "success");
            resultMap.put("data", productInventory);

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            sqlSession.close();
        }

        return resultMap;
    }

    public Map<String, Object> getWareHouseInventoryByCodeAndLocation(Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            WarehouseInventoryMapper warehouseInventoryMapper = sqlSession.getMapper(WarehouseInventoryMapper.class);

            List<Map<String, Object>> productInventory = warehouseInventoryMapper.getWareHouseInventoryByCodeAndLocation(map);

            if (productInventory == null || productInventory.size() == 0) {
                resultMap.put("result", false);
                resultMap.put("msg", "There is no inventory records.");
                resultMap.put("data", null);
                return resultMap;
            }

            resultMap.put("result", true);
            resultMap.put("msg", "success");
            resultMap.put("data", productInventory);

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            sqlSession.close();
        }

        return resultMap;
    }

    public Map<String, Object> addInventory(Map<String, Object> map) {
        Map<String, Object> resultMap = new HashMap<>();

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            WarehouseInventoryMapper inventoryMapper = sqlSession.getMapper(WarehouseInventoryMapper.class);

            int i = inventoryMapper.addInventory(map);

            if (i > 0) {
                resultMap.put("result", true);
                resultMap.put("msg", "You have successfully insert a inventory!");
                resultMap.put("data", null);
            } else {
                resultMap.put("result", true);
                resultMap.put("msg", "You have successfully insert a inventory!");
                resultMap.put("data", null);
            }

            sqlSession.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultMap.put("result", false);
            resultMap.put("msg", "Failed to insert inventory/inventory!");
            resultMap.put("data", null);
        } finally {
            sqlSession.close();
        }

        return resultMap;
    }

    public Map<String, Object> uploadInventoryCsv(List<WarehouseInventory> inventories) {
        Map<String, Object> resultMap = new HashMap<>();

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            WarehouseInventoryMapper inventoryMapper = sqlSession.getMapper(WarehouseInventoryMapper.class);

            // 1. It will insert the data into database if these is no record in the database
            // 2. It will update the quanty (original value + added quanty) if there is record in the database
            inventoryMapper.addInventories(inventories);

            // Meanwhile check if there is record in product table. If not, add it into product table
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            for (WarehouseInventory inventory : inventories) {
                Product product = productMapper.getProduct(inventory.getProductCode());
                if (product == null) {
                    productMapper.addProduct(new Product(inventory.getProductName(), inventory.getProductCode(), inventory.getPerWeight()));
                }
            }

            sqlSession.commit();

            resultMap.put("result", true);
            resultMap.put("msg", "You have successfully insert inventories!");
            resultMap.put("data", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultMap.put("result", false);
            resultMap.put("msg", "Failed to insert inventory/inventories!");
            resultMap.put("data", null);
        } finally {
            sqlSession.close();
        }

        return resultMap;
    }

    public Map<String, Object> transfer(Map<String, Object> transferInfo) {
        Map<String, Object> resultMap = new HashMap<>();

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            WarehouseInventoryMapper inventoryMapper = sqlSession.getMapper(WarehouseInventoryMapper.class);

            int i = inventoryMapper.transfer(transferInfo);

            if (i == 0) {
                resultMap.put("result", false);
                resultMap.put("msg", "Failed to do transfermation!");
                resultMap.put("data", null);
                return resultMap;
            }

            sqlSession.commit();

            resultMap.put("result", true);
            resultMap.put("msg", "You have successfully finished transfermation!");
            resultMap.put("data", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            sqlSession.close();
        }

        return resultMap;
    }
}
