package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.PreparedStatement;

public class JdbcGroupDao  {
// need to add in implements GroupDao

    //Might need to have the @Service above this class. @Service usually has the business logic of an application
    //usually resides within the service layer. Used to indicated that a class belongs to that layer

    private JdbcTemplate jdbcTemplate;

    public JdbcGroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //@Override
    public String findGroupByGroupId(Long groupId) {
        return jdbcTemplate.queryForObject("select name from groups where group_id = ?", String.class, groupId);
    }

   // @Override
    public Group findGroupByName(String name) {
        return jdbcTemplate.queryForObject("select * from groups where name = ?", Group.class, name);
    }

//    @Override
//    public boolean create(String name, String description, String role) {
//        boolean groupCreated = false;

//        // create group
//        String insertGroup = "insert into groups (name,description,role) values(?,?,?)";
//        String id_column = "group_id";
//        groupCreated = jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement(insertGroup, new String[]{id_column});
//            ps.setString(1, name);
//            ps.setString(2, description);
//            ps.setString(3, role);
//            return ps;
//        }
//
//        return groupCreated;
//    }


    private Group mapRowToGroup(SqlRowSet rs) {
        Group group = new Group();
        group.setGroupId(rs.getLong("group_id"));
        group.setName(rs.getString("name"));
        group.setInviteSent(rs.getBoolean("invite_sent"));
        //group.setAuthorities(rs.getString("role")); -- not sure how to deal with the role
        group.setGroupDescription(rs.getString("group_description"));
        group.setJoinedOn(rs.getDate("joined-on"));
        return group;
    }

}
