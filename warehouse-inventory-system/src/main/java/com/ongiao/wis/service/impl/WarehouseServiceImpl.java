package com.ongiao.wis.service.impl;

import com.ongiao.wis.dao.WarehouseMapper;
import com.ongiao.wis.service.IWarehouseService;
import com.ongiao.wis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WarehouseServiceImpl implements IWarehouseService {

    private Logger logger = Logger.getLogger(WarehouseServiceImpl.class);

    public Map<String, Object> getWarehouses() {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            WarehouseMapper warehouseMapper = sqlSession.getMapper(WarehouseMapper.class);
            List<Map<String, Object>> warehouses = warehouseMapper.getWarehouses();

            if (warehouses == null || warehouses.size() == 0) {
                resultMap.put("result", false);
                resultMap.put("msg", "There is no warehouse.");
                return resultMap;
            }

            resultMap.put("result", true);
            resultMap.put("msg", "success");
            resultMap.put("data", warehouses);

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            sqlSession.close();
        }

        return resultMap;
    }
}
