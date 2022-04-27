package com.LittleRedBook.www.util;

/*
    Jdbc的工具类
*/

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcUtil {
    //创建数据源
    private static DataSource dataSource = null;

    static {
        try {
            //由配置文件创建数据源
            dataSource=new ComboPooledDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //从数据源中获取连接
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    //释放连接资源
    public static void release(Connection conn, PreparedStatement st, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
