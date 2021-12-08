package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.List;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public abstract class JdbcGroupDao implements GroupDao {
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
//    public List<Group> findAll() {
//        List<Group> groups = new ArrayList<Group>();
//
//        String sql = "SELECT * FROM groups JOIN member_of ON member_of.group_id= groups.group_id" +
//                " JOIN user ON user_id = member_of.user_id WHERE user_id = ?;";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//        while (results.next()) {
//            Group group = mapRowToGroup(results);
//            groups.add(group);
//        }
//        return groups;
//    }

    @Override
    //public boolean create(String name, String groupDescription, Date joinedOn) {
    public Group createGroup(Group group) {
        // boolean groupCreated = false;

        // create group
        String insertGroup = "INSERT INTO groups(name, group_description, joined_on)" +
                " VALUES(?,?,?) RETURNING group_id;";
        Long newGroupId = jdbcTemplate.queryForObject(insertGroup, Long.class, group.getName(), group.getGroupDescription(), group.getJoinedOn());

        return getGroup(newGroupId);
    }

    //Below Override was to make implements happy for now until we figure out why
    @Override
    public Group getGroup(Long groupId) {
        return null;
    }
    //GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        String id_column = "group_id";
//        groupCreated = jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement(insertGroup, new String[]{id_column});
//            ps.setString(1, name);
//            ps.setString(2, groupDescription);
//            ps.setDate(3, joinedOn);
//            return ps;
//        }
//        , keyHolder) == 1;
//
//        int newGroupId = (int) keyHolder.getKeys().get(id_column);
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
