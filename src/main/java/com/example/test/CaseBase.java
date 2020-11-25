package com.example.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.util.JDBCUtil;
import com.jayway.jsonpath.JsonPath;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Test
public class CaseBase {

    private static String SqlType = "MySql";
    private static String PORT = "3306";
    private static String IP = "localhost";
    private static String DATABASE = "testcase";
    private static String USERNAME = "root";
    private static String PASSWORD = "Fqc**19890827";

    @Test(description = "111111111111111111")
    public void test001() throws SQLException{
        String sql_01 = "SELECT * FROM testcase WHERE id = ?";
        Map<Object,Object> body = getDataInSql(sql_01);
        JSONObject jObj = JSON.parseObject(JSON.toJSONString(body));
        Reporter.log("ssssssssssss");
        System.out.println(body);
        System.out.println(body.get("parameters"));
        System.out.println(JSON.toJSONString(body));
        System.out.println(jObj);
        System.out.println(jObj.toJSONString());
        System.out.println(jObj.toString());

//        List<String> list = JsonPath.read(jObj,"$.data.list[*].area");
    }

    public static Map<Object, Object> getDataInSql(String sql) throws SQLException {
        Connection conn = JDBCUtil.getConnection(SqlType,IP,PORT,DATABASE,USERNAME,PASSWORD);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Object,Object> map = new HashMap<>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,3);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                map.put("parameters",resultSet.getString("parameters"));
                map.put("method",resultSet.getString("method"));
                map.put("url",resultSet.getString("url"));
                map.put("id",resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.closeAll(conn,null,preparedStatement,resultSet);
        }
        return map;
    }
}
