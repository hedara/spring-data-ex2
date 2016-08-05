package com.samples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by edara on 8/4/16.
 */
@Component
public class CircleDao {
    @Autowired
    Connection conn;
    Circle circle = null;
    PreparedStatement ps = null;
    String getCircleSQL = "select * from circle where id=?";

    public Circle getCircle(int id) {
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

}
