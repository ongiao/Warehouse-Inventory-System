package com.ongiao.wis.dao;

import com.ongiao.wis.pojo.Product;
import com.ongiao.wis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class ProductDaoTest {

    private Logger logger = Logger.getLogger(ProductDaoTest.class);

    @Test
    public void testAddProduct() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);

            int i = productMapper.addProduct(new Product("cola", "FM-HKTV33", 30));

            Assert.assertEquals(1, i);

            sqlSession.commit();

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            sqlSession.close();
        }
    }

//    @Test
//    public void test
}
