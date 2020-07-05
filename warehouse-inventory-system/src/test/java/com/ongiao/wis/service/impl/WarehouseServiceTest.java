package com.ongiao.wis.service.impl;

import com.ongiao.wis.service.IWarehouseService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by chenjunxing ON 2020-07-05 12:32.
 */
public class WarehouseServiceTest {

    private IWarehouseService iWarehouseService = new WarehouseServiceImpl();

    @Test
    public void testGetWarehouses() {
        Map<String, Object> warehouses = iWarehouseService.getWarehouses();

        Assert.assertEquals(true, warehouses.get("result"));
        Assert.assertEquals("success", warehouses.get("msg"));
    }
}
