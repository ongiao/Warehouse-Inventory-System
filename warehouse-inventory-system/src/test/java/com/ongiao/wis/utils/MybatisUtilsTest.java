package com.ongiao.wis.utils;

import javafx.scene.media.VideoTrack;
import org.junit.Assert;
import org.junit.Test;

public class MybatisUtilsTest {
    @Test
    public void testGetSqlSession() {
        Assert.assertEquals("DefaultSqlSession", MybatisUtils.getSqlSession().getClass().getSimpleName());
    }
}
