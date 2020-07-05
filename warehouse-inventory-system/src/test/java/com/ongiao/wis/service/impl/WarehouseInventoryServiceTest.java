package com.ongiao.wis.service.impl;

import com.ongiao.wis.pojo.Product;
import com.ongiao.wis.pojo.WarehouseInventory;
import com.ongiao.wis.service.IWarehouseInventoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WarehouseInventoryServiceTest {

    private IWarehouseInventoryService service = new WarehouseInventoryServiceImpl();

    @Test
    public void testGetAllWarehouseInventory() {
        Map<String, Object> allWarehouseInventory = service.getAllWarehouseInventory();

        Assert.assertEquals(true, allWarehouseInventory.get("result"));
        Assert.assertEquals("success", allWarehouseInventory.get("msg"));
    }

    @Test
    public void testGetWareHouseInventoryByCode() {
        String code = "FM-HKTV08";

        Map<String, Object> result = service.getWareHouseInventoryByCode(code);

        Assert.assertEquals(true, result.get("result"));
        Assert.assertEquals("success", result.get("msg"));
    }

    @Test
    public void testGetWareHouseInventoryByCodeAndLocation() {
        Map<String, Object> map = new HashMap<>();

        map.put("productCode", "FM-HKTV08");
        map.put("targetLocation", "TKU");

        Map<String, Object> result = service.getWareHouseInventoryByCodeAndLocation(map);

        Assert.assertEquals(true, result.get("result"));
        Assert.assertEquals("success", result.get("msg"));
    }

    @Test
    public void testAddInventory() {
        Map<String, Object> addInfo = new HashMap<>();

        addInfo.put("productName", "nike running shoes");
        addInfo.put("productCode", "FM-HKTV22");
        addInfo.put("locationName", "NP");
        addInfo.put("quanty", 10);
        addInfo.put("perWeight", 100);

        Map<String, Object> result = service.addInventory(addInfo);

        Assert.assertEquals(true, result.get("result"));
        Assert.assertEquals("You have successfully insert inventory!", result.get("msg"));
    }

    @Test
    public void testAddInventories() {
//        Map<String, Object> addInfo = new HashMap<>();
//
//        addInfo.put("productName", "nike running shoes");
//        addInfo.put("productCode", "FM-HKTV22");
//        addInfo.put("locationName", "NP");
//        addInfo.put("quanty", 10);
//        addInfo.put("perWeight", 100);

        List<WarehouseInventory> list = new ArrayList<>();
        list.add(new WarehouseInventory("earpods", "FM-HKTV23", "NP", 20, 30));

        Map<String, Object> result = service.uploadInventoryCsv(list);

        Assert.assertEquals(true, result.get("result"));
        Assert.assertEquals("You have successfully insert inventories!", result.get("msg"));
    }

    @Test
    public void testTransfer() {
        Map<String, Object> transferInfo = new HashMap<>();

        List<WarehouseInventory> list = new ArrayList<>();
        list.add(new WarehouseInventory("earpods", "FM-HKTV23", "NP", 20, 30));
        list.add(new WarehouseInventory("earpods", "FM-HKTV23", "CWB", 10, 30));


        service.uploadInventoryCsv(list);

        transferInfo.put("productCode", "FM-HKTV23");
        transferInfo.put("sourceLocation", "NP");
        transferInfo.put("targetLocation", "CWB");
        transferInfo.put("quanty", 5);

        Map<String, Object> result = service.transfer(transferInfo);

        Assert.assertEquals(true, result.get("result"));
        Assert.assertEquals("You have successfully finished transfermation!", result.get("msg"));

//        Map<String, Object> sourceInfo = new HashMap<>();
//        sourceInfo.put("productCode", "FM-HKTV23");
//        sourceInfo.put("targetLocation", "NP");
//        Map<String, Object> sourceResult = service.getWareHouseInventoryByCodeAndLocation(sourceInfo);
//
//        Map<String, Object> targetInfo = new HashMap<>();
//        targetInfo.put("productCode", "FM-HKTV23");
//        targetInfo.put("targetLocation", "CWB");
//        Map<String, Object> targetResult = service.getWareHouseInventoryByCodeAndLocation(sourceInfo);
//
//        WarehouseInventory source = ((ArrayList)sourceResult.get("data"));
//        WarehouseInventory target = targetResult.get("data");
//
//        Assert.assertEquals(15, source.getQuanty());
//        Assert.assertEquals(15, target.getQuanty());
    }
}
