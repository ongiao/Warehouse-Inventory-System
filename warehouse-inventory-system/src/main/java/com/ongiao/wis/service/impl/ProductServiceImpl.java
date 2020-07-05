package com.ongiao.wis.service.impl;

import com.ongiao.wis.dao.ProductMapper;
import com.ongiao.wis.pojo.Product;
import com.ongiao.wis.service.IProductService;
import com.ongiao.wis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements IProductService {

    private Logger logger = Logger.getLogger(ProductServiceImpl.class);

    public Map<String, Object> getProduct(String code) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            Product product = productMapper.getProduct(code);

            if (product == null) {
                resultMap.put("result", false);
                resultMap.put("msg", "We can't find the product you have specified");
                resultMap.put("data", null);
                return resultMap;
            }

            resultMap.put("result", true);
            resultMap.put("msg", "success");
            resultMap.put("data", product);

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            sqlSession.close();
        }

        return resultMap;
    }

    public Map<String, Object> getProducts() {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
            List<Map<String, Object>> products = productMapper.getProducts();

            if (products == null || products.size() == 0) {
                resultMap.put("result", false);
                resultMap.put("msg", "There is no products.");
                resultMap.put("data", null);
                return resultMap;
            }

            resultMap.put("result", true);
            resultMap.put("msg", "success");
            resultMap.put("data", products);

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            sqlSession.close();
        }

        return resultMap;
    }

    public Map<String, Object> addProduct(String name, String code, double weight) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

            Product product = new Product(name, code, weight);

            int i = productMapper.addProduct(product);

            if (i == 0) {
                resultMap.put("result", false);
                resultMap.put("msg", "Failed to insert product");
                resultMap.put("data", null);
                return resultMap;
            }

            if (i > 0) {
                sqlSession.commit();
            }

            resultMap.put("result", true);
            resultMap.put("msg", "You have successfully insert the product!");
            resultMap.put("data", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            sqlSession.close();
        }

        return resultMap;
    }

    public Map<String, Object> addProducts(List<Product> products) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

            int i = productMapper.addProducts(products);

            if (i != products.size()) {
                resultMap.put("result", false);
                resultMap.put("msg", "Failed to insert product(s)!");
                resultMap.put("data", null);
                return resultMap;
            }

            sqlSession.commit();

            resultMap.put("result", true);
            resultMap.put("msg", "You have successfully insert products!");
            resultMap.put("data", null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

        return resultMap;
    }

    public Map<String, Object> uploadProductCsv(List<Product> products) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

            int i = productMapper.uploadProductCsv(products);

            sqlSession.commit();

            if (i == products.size()) {
                resultMap.put("result", true);
                resultMap.put("msg", "Successfully insert all products by csv file!");
                resultMap.put("data", null);
            } else if (i > 0 && i < products.size()) {
                resultMap.put("result", true);
                resultMap.put("msg", "Successfully insert some products by csv file because some product have been existed in the database!");
                resultMap.put("data", null);
            } else {
                resultMap.put("result", false);
                resultMap.put("msg", "All products you want to insert by csv file have been existed in the database!");
                resultMap.put("data", null);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            resultMap.put("result", false);
            resultMap.put("msg", "Failed to insert product(s)!");
            resultMap.put("data", null);
            return resultMap;
        } finally {
            sqlSession.close();
        }

        return resultMap;
    }

    public Map<String, Object> removeProduct(String code) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

            int i = productMapper.removeProduct(code);

            if (i == 0) {
                resultMap.put("result", false);
                resultMap.put("msg", "Failed to remove product");
                resultMap.put("data", null);
                return resultMap;
            }

            if (i > 0) {
                sqlSession.commit();
            }

            resultMap.put("result", true);
            resultMap.put("msg", "You have successfully remove the product!");
            resultMap.put("data", null);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            sqlSession.close();
        }

        return resultMap;
    }
}