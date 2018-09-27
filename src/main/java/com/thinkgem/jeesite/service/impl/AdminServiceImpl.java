package com.thinkgem.jeesite.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.thinkgem.jeesite.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TianYu
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource(name = "dataSource")
    private DruidDataSource datasourceForMySQL;
    @Resource(name = "oracleDataSource")
    private DruidDataSource datasourceForOracle;

    @Override
    public List<Map<String, Object>> mysql(String sql) {
        try {
            return getMaps(sql, datasourceForMySQL.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Map<String, Object>> getMaps(String sql, DruidPooledConnection connection) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultwSet = statement.executeQuery(sql);
            //获得结果集结构信息,元数据
            ResultSetMetaData md = resultwSet.getMetaData();
            //获得列数
            int columnCount = md.getColumnCount();
            while (resultwSet.next()) {
                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), resultwSet.getObject(i));
                }
                list.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> oracle(String sql) {
        try {
            return getMaps(sql, datasourceForOracle.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

