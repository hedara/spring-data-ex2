package com.samples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by edara on 8/6/16.
 */
@Component
public class CircleDao2 {
    @Autowired
    DataSource dataSource;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Circle getCircle(int id){
        String sql = "select * from circle where id=:id";
        SqlParameterSource inparams = new MapSqlParameterSource();
        ((MapSqlParameterSource)inparams).addValue("id",id);
        return namedParameterJdbcTemplate.query(sql,inparams,new RowMapper<Circle>() {
            public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Circle(rs.getInt("id"), rs.getString("name"));
            }
        }).get(0);
    }

}
