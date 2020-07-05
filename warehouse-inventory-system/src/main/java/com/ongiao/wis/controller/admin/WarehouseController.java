package com.ongiao.wis.controller.admin;

import com.ongiao.wis.controller.BaseController;
import com.ongiao.wis.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WarehouseController extends BaseController {
    @Autowired
    private IWarehouseService iWarehouseService;

    /**
     * Get all the warehouses in Hong Kong
     * @return
     */
    @RequestMapping(value = "/warehouses")
    public Map<String, Object> getWarehouses() {
        return iWarehouseService.getWarehouses();
    }
}
