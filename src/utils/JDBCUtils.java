package utils;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {



    //用Druid进行连接
    private static DataSource dataSource ;
    static {
        Properties properties = new Properties();
        InputStream is= null;
        try {
//            is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            is=JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnectionByDruid(){
        Connection connection =null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return connection;
        }
    }



    public static Connection getConnection() {
        Connection connection =null;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties=new Properties();
            properties.load(is);
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driver = properties.getProperty("driver");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            return connection;
        }



    }
    public static void close(Statement ps, Connection connection,ResultSet resultSet ){
        try {
            if (ps != null) {
                ps.close();
            }
            if(connection!=null) {
                connection.close();
            }
            if(resultSet!=null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement ps, Connection connection){
        try {
            if (ps != null) {
                ps.close();
            }
            if(connection!=null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    //通用的增删改操作1.0 （不考虑事务）
//    public static void update1(String sql,Object...args) throws Exception {
//        Connection connection = JDBCUtils.getConnection();
//        PreparedStatement ps = connection.prepareStatement(sql);
//        for (int i = 0; i < args.length; i++) {
//            ps.setObject(i+1,args[i]);
//        }
//        ps.execute();
//        JDBCUtils.close(ps,connection);
//    }
//
//    //通用的增删改操作2.0 （考虑事务）
//    public static void update2(Connection connection,String sql,Object...args) {
//        PreparedStatement ps=null;
//        try {
//            ps = connection.prepareStatement(sql);
//            for (int i = 0; i < args.length; i++) {
//                ps.setObject(i+1,args[i]);
//            }
//            ps.execute();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtils.close(ps,null);
//        }
//    }
//
//
//
//
//
//
//    //通用的查询操作1.0 （不考虑事务）
//    public static  <T> List<T> queryForAll1(Class<T> clazz, String sql, Object...args){
//        Connection connection = null;
//        PreparedStatement ps =null;
//        ResultSet resultSet =null;
//        ResultSetMetaData metaData =null;
//        try {
//            connection = JDBCUtils.getConnection();
//            ps=connection.prepareStatement(sql);
//
//            //根据参数列表长度，对SQL的通配符进行操作
//            for (int i = 0; i < args.length; i++) {
//                ps.setObject(i+1,args[i]);
//            }
//
//            //获取查询结果集
//            resultSet =  ps.executeQuery();
//            //获取结果集的元数据
//            metaData = resultSet.getMetaData();
//            //通过元数据得到结果集的列数
//            int columnCount = metaData.getColumnCount();
//
//            List<T> list=new ArrayList();
//
//            //获取每一列的数据
//            while (resultSet.next()){
//
//                T obj = clazz.newInstance();
//
//                for (int i = 1; i <=columnCount; i++) {
//                    //获取列值
//                    Object columnValue = resultSet.getObject(i);
//                    //获取列名
//                    String columnName = metaData.getColumnName(i);
//
//                    //获取obj对象指定的columnName属性，并赋值为columnValue
//
//                    //通过反射，获取Customer类中的字段对象
//                    Field field = clazz.getDeclaredField(columnName);
//                    //字段可能为私有，要setAccessible(true)
//                    field.setAccessible(true);
//                    //将对象ctm的字段进行赋值
//                    field.set(obj,columnValue);
//
//                }
//                list.add(obj);
//            }
//            return list;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            JDBCUtils.close(ps,connection,resultSet);
//        }
//
//        return null;
//    }
//
//    //通用的查询操作2.0 （考虑事务）
//    public static  <T> List<T> queryForAll2(Connection connection,Class<T> clazz, String sql, Object...args){
//
//        PreparedStatement ps =null;
//        ResultSet resultSet =null;
//        ResultSetMetaData metaData =null;
//        try {
//            connection = JDBCUtils.getConnection();
//            ps=connection.prepareStatement(sql);
//
//            //根据参数列表长度，对SQL的通配符进行操作
//            for (int i = 0; i < args.length; i++) {
//                ps.setObject(i+1,args[i]);
//            }
//
//            //获取查询结果集
//            resultSet =  ps.executeQuery();
//            //获取结果集的元数据
//            metaData = resultSet.getMetaData();
//            //通过元数据得到结果集的列数
//            int columnCount = metaData.getColumnCount();
//
//            List<T> list=new ArrayList();
//
//            //获取每一列的数据
//            while (resultSet.next()){
//
//                T obj = clazz.newInstance();
//
//                for (int i = 1; i <=columnCount; i++) {
//                    //获取列值
//                    Object columnValue = resultSet.getObject(i);
//                    //获取列名
//                    String columnName = metaData.getColumnName(i);
//
//                    //获取obj对象指定的columnName属性，并赋值为columnValue
//
//                    //通过反射，获取Customer类中的字段对象
//                    Field field = clazz.getDeclaredField(columnName);
//                    //字段可能为私有，要setAccessible(true)
//                    field.setAccessible(true);
//                    //将对象ctm的字段进行赋值
//                    field.set(obj,columnValue);
//
//                }
//                list.add(obj);
//            }
//            return list;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            JDBCUtils.close(ps,connection,resultSet);
//        }
//
//        return null;
//    }
//
//    //查询特殊值（如总数、平均数、最大值等）的通用方法
//    public <T> T getValue(Connection connection,String sql,Object...args) {
//        PreparedStatement ps =null;
//        ResultSet resultSet =null;
//        try {
//            ps = connection.prepareStatement(sql);
//            for (int i = 0; i < args.length; i++) {
//                ps.setObject(i+1,args[i]);
//            }
//            resultSet = ps.executeQuery();
//            ResultSetMetaData metaData = resultSet.getMetaData();
//
//            if(resultSet.next()){
//               return (T)resultSet.getObject(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            JDBCUtils.close(ps,null,resultSet);
//        }
//        return null;
//    }
//
//
//    public static Date stringToDate(String inputDate)  {
//        Date sqlDate=null;
//        try {
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//            java.util.Date date = sdf.parse(inputDate);
//            sqlDate=new Date(date.getTime());
//        } catch (ParseException e) {
//            System.out.println("输入的日期错误");
//        }
//        return sqlDate;
//    }














}
