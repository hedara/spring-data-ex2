package com.samples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by edara on 8/4/16.
 */
@Component
public class CircleDao {
    @Autowired
    Connection conn;
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;


    String getCircleSQL = "select * from circle where id=?";

    public Circle getCircle(int id) {
        Circle circle = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareStatement(getCircleSQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                return new Circle(id, rs.getString("name"));
            }
        }catch(SQLException ex){
            System.out.println("SQL exception "+ex);
            System.exit(9);
        }finally{
            try{
                ps.close();
                rs.close();
            }catch(Exception ex) {}
        }
        return null;
    }
    public Circle getCircle2(int id) {
        Circle circle = null;
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(getCircleSQL);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                return new Circle(id, rs.getString("name"));
            }
        }catch(Exception ex) {
            System.out.println("SQL exception "+ex);
            System.exit(9);
        }finally{
            try{
                ps.close();
                rs.close();
            }catch(Exception ex) {}
        }
        return null;
    }
    public Circle getCircle3(int id) {
        Circle c = jdbcTemplate.query(getCircleSQL,new Object[] {id},new RowMapper<Circle>(){
            public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
                Circle circle = new Circle(rs.getInt("id"),rs.getString("name"));
                return circle;
            }
        }).get(0);
        return c;
    }
    public boolean updateCircleName(int id, String newname) {
        String sql = "update circle set name=? where id=?";
        Object[] queryParams = {newname,id};
        int rows = jdbcTemplate.update(sql,queryParams);
        return rows>0?true:false;
    }

    public boolean insertCircle(int id, String name) {
        String sql = "insert into circle (id,name) values (?,?)";

        int count = jdbcTemplate.update(sql,id,name);
        return count >0? true: false;
    }

    public List<Circle> getAllCircles() {
        String sql = "select * from circle";
        List<Circle> circles = jdbcTemplate.query(sql,new RowMapper<Circle>(){
            public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
                Circle circle = new Circle(rs.getInt("id"),rs.getString("name"));
                return circle;
            }
        });
        return circles;
    }

}
