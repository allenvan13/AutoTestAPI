package com.example.util;

import java.sql.*;

public class JDBCUtil {



    /**1.1 获得数据库连接
     * 获取jdbc连接的方法getConnection （通过JDBCUtil.getConnection（）来获取一个JDBC的连接）
     * IP 为数据库所在的远程服务器的ip地址
     * PORT 为数据库访问的端口
     * DATABASE  要连接的数据库名称
     * */
    public static Connection getConnection(String SqlType,String IP,String PORT,String DATABASE,String username,String password){
        try {
            //MySql默认端口3306
            if (SqlType.equalsIgnoreCase("MySql")) {
                //1加载驱动
                Class.forName("com.mysql.jdbc.Driver");
                //2.获取连接
                String URL = "jdbc:mysql://"+ IP + ":" + PORT +"/"+ DATABASE+"?useUnicode=true&characterEncoding=utf-8&useSSL=false";
                return DriverManager.getConnection(URL, username, password);
            }

            //SQL server 默认端口1433
            if (SqlType.equalsIgnoreCase("SQLserver")) {

                Class.forName("com.mircosoft.jdbc.sqlserver.SQLServerDriver");
                String URL = "jdbc:mircosoft:sqlserver:"+ IP + ":" + PORT +";databasename="+ DATABASE;
                return DriverManager.getConnection(URL, username, password);
            }

            //Oracle默认端口1521
            if (SqlType.equalsIgnoreCase("Oracle")) {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String URL = "jdbc:oracle:thin:"+ IP + ":" + PORT +":"+ DATABASE;
                return DriverManager.getConnection(URL, username, password);
            }

        } catch (Exception e) {//捕捉所有的异常
            e.printStackTrace();
        }
        return null;
    }

    /**2.释放资源
     * 关闭，释放资源的方法close （若不存在使用下列资源，传递参数为null即可，通过JDBCUtil.close()关闭资源）
     * rs 为结果集，通过JDBC查到的结果集，使用后需关闭释放资源
     * ps st 为开启的sql语句
     * conn 为jdbc的连接
     * @throws SQLException
     * */
    public static void closeAll(Connection conn, Statement st, PreparedStatement ps, ResultSet rs) throws SQLException{
        //栈式关闭（最先连接，最后关闭连接）
        //关闭ResultSet
        if(rs != null) rs.close();
        //关闭PreparedStatement
        if(ps != null) ps.close();
        //关闭Statement
        if(st != null) st.close();
        //关闭Connection
        if(conn != null) conn.close();
    }
}
