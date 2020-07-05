package com.ongiao.wis.controller.admin;

import com.ongiao.wis.constants.FilePath;
import com.ongiao.wis.controller.BaseController;
import com.ongiao.wis.pojo.Product;
import com.ongiao.wis.service.IProductService;
import com.ongiao.wis.utils.UploadHelper;
import com.ongiao.wis.utils.impl.ProductCsvReader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController extends BaseController {
    @Autowired
    private IProductService iProductService;

    private Logger logger = Logger.getLogger(ProductController.class);

    /**
     * Get all the products
     * @param request
     * @return
     */
    @RequestMapping(value = "/products")
    public String getProducts(HttpServletRequest request) {

        Map<String, Object> productMap = iProductService.getProducts();
        request.setAttribute("products", productMap);

        return this.render("product_page");
    }

    /**
     * Add a specific product into the database given name, code and weight
     * @param name
     * @param code
     * @param weight
     * @return
     */
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public Map<String, Object> addProduct(String name, String code, String weight) {
        Map<String, Object> productMap = new HashMap<>();

        // see if the product with same code has existed in the database
        productMap = iProductService.getProduct(code);

        if (!(Boolean) productMap.get("result")) {
            productMap = iProductService.addProduct(name, code, Double.valueOf(weight));

            return productMap;
        }

        productMap.put("result", false);
        productMap.put("msg", "This product has already been existed in the database.");
        productMap.put("data", null);
        return productMap;
    }

    /**
     * Upload csv file for the products
     * @param request
     * @param file
     * @return
     */
    @RequestMapping(value = "/products/csv", method = RequestMethod.POST)
    public String uploadProductCsv(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("Please select a file!");
        }

        if (!file.getContentType().equals("text/csv")) {
            System.out.println("We just support to upload a csv file!");
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

            ProductCsvReader reader = new ProductCsvReader();
            List<Product> products = reader.readProduct(FilePath.UPLOAD_DIRECTORY + fileName);

            iProductService.uploadProductCsv(products);

            logger.info("Uploaded product csv file successfully!");
        } catch (Exception e) {
            logger.error("Failed to upload csv file!");
            logger.error(e.getMessage());
        } finally {
            if (uploadFile != null) {
                uploadFile.deleteOnExit();
            }
        }

        return this.redirect("/products");
    }
}
