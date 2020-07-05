package com.ongiao.wis.controller.admin;

import com.ongiao.wis.constants.FilePath;
import com.ongiao.wis.controller.BaseController;
import com.ongiao.wis.pojo.Product;
import com.ongiao.wis.pojo.WarehouseInventory;
import com.ongiao.wis.service.IProductService;
import com.ongiao.wis.service.IWarehouseInventoryService;
import com.ongiao.wis.service.IWarehouseService;
import com.ongiao.wis.utils.UploadHelper;
import com.ongiao.wis.utils.impl.ProductQuantyCsvReader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WarehouseInventoryController extends BaseController {

    @Autowired
    private IWarehouseInventoryService iWarehouseInventoryService;

    @Autowired
    private IWarehouseService iWarehouseService;

    @Autowired
    private IProductService iProductService;

    private Logger logger = Logger.getLogger(WarehouseInventoryController.class);

    /**
     * G
     * @param request
     * @return
     */
    @RequestMapping(value = "/warehouse/inventory/all")
    public String getAllWarehouseInventory(HttpServletRequest request) {
        Map<String, Object> allWarehouseInventory = iWarehouseInventoryService.getAllWarehouseInventory();
        request.setAttribute("warehouseInventories", allWarehouseInventory);

        return this.render("inventory_page");
    }

    /**
     * Get the inventory of the specific product given the product code
     * @param code
     * @param request
     * @return
     */
    @RequestMapping(value = "/inventory/product")
    public String getProductInventory(String code, HttpServletRequest request) {
        Map<String, Object> productInventory = iWarehouseInventoryService.getWareHouseInventoryByCode(code);
        Map<String, Object> warehouses = iWarehouseService.getWarehouses();

        request.setAttribute("productInventory", productInventory);
        request.setAttribute("warehouses", warehouses);
        request.setAttribute("productCode", code);

        return this.render("product_inventory");
    }

    /***
    * @Description: Upload csv file for warehouse inventory
    * @Param: [request, file]
    * @return: java.lang.String
    */
    @RequestMapping(value = "/warehouse/inventory", method = RequestMethod.POST)
    public String uploadInventoryCsv(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            logger.error("Please select a file!");
        }

        if (!file.getContentType().equals("text/csv")) {
            logger.error("We just support to upload a csv file!");
        }

        String fileName = "";
        File uploadFile = null;
        Map<String, Object> resultMap = null;

        try {
            Collection<Part> parts = request.getParts();

            for (Part part : parts) {
                fileName = part.getSubmittedFileName();
            }

            byte[] buffer = file.getBytes();

            uploadFile = UploadHelper.upload(buffer, FilePath.UPLOAD_DIRECTORY, fileName);

            ProductQuantyCsvReader reader = new ProductQuantyCsvReader();
            List<WarehouseInventory> warehouseInventories = reader.readProductInventory(FilePath.UPLOAD_DIRECTORY + fileName);

            iWarehouseInventoryService.uploadInventoryCsv(warehouseInventories);

            logger.info("Uploaded csv file successfully!");
        } catch (Exception e) {
            logger.error("Failed to upload csv file!");
            logger.error(e.getMessage());
        }

        return this.redirect("/warehouse/inventory/all");
    }

    /***
    * @Description: Transfer inventory from one location to another location given the params as below.
    * @Param: [productCode, sourceLocation, targetLocation, quanty, request]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    */
    @ResponseBody
    @RequestMapping(value = "/transfermation", method = RequestMethod.POST)
    public Map<String, Object> transfer(String productCode, String sourceLocation, String targetLocation, String quanty, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> transferInfo = new HashMap<>();

        if (Integer.valueOf(quanty) < 0) {
            resultMap.put("result", false);
            resultMap.put("msg", "The quanty should not be negative. Please input again.");
            resultMap.put("data", null);
            return resultMap;
        }

        transferInfo.put("productCode", productCode);
        transferInfo.put("sourceLocation", sourceLocation);
        transferInfo.put("targetLocation", targetLocation);
        transferInfo.put("quanty", Integer.valueOf(quanty));

        Map<String, Object> productInventory = iWarehouseInventoryService.getWareHouseInventoryByCodeAndLocation(transferInfo);
        if (!(Boolean) productInventory.get("result")) {
            Map<String, Object> productMap = iProductService.getProduct(productCode);
            Product product = (Product) productMap.get("data");
            String name = product.getName();
            double weight = Double.valueOf(product.getWeight());
            Map<String, Object> addInfo = new HashMap<>();

            addInfo.put("productName", name);
            addInfo.put("productCode", productCode);
            addInfo.put("locationName", targetLocation);
            addInfo.put("quanty", Integer.valueOf(quanty));
            addInfo.put("perWeight", weight);

            iWarehouseInventoryService.addInventory(addInfo);
        }

        resultMap = iWarehouseInventoryService.transfer(transferInfo);

        return resultMap;
    }
}
