package DAO;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class baseDAO {
    private QueryRunner queryRunner=new QueryRunner();

    public int update(String sql,Object...args){
        Connection connection = JDBCUtils.getConnectionByDruid();
        int update=-1;
        try {
            update = queryRunner.update(connection, sql, args);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(null,connection);
            //如果执行失败，返回-1
            return update;
        }
    }

    public <T> T queryForOne(Class<T> type, String sql, Object...args){
        Connection connection = JDBCUtils.getConnectionByDruid();
        T query =null;
        try {
             query=queryRunner.query(connection, sql, new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(null,connection);
            return query;
        }
    }

    public <T> List<T> queryForList(Class<T> type, String sql, Object...args){
        Connection connection = JDBCUtils.getConnectionByDruid();
        try {
            List<T> query = queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(null,connection);
        }
        return null;
    }

    public Object queryForSingleValue(String sql,Object...args){
        Connection connection = JDBCUtils.getConnectionByDruid();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler<>(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(null,connection);
        }
        return null;
    }

}
