package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.config.method.MethodSecurityMetadataSourceBeanDefinitionParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import com.techelevator.dao.MemberOfDao;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JdbcGroupDao implements GroupDao {

    //private MemberOfDao memberOfDao;
    //Might need to have the @Service above this class. @Service usually has the business logic of an application
    //usually resides within the service layer. Used to indicated that a class belongs to that layer

    private JdbcTemplate jdbcTemplate;
    private MemberOfDao memberOfDao;

    public JdbcGroupDao(JdbcTemplate jdbcTemplate, MemberOfDao memberOfDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.memberOfDao = memberOfDao;

    }



    @Override
    public List<Group> findAll() {
        List<Group> groupList = new ArrayList<>();
        String sql = "SELECT * FROM groups ORDER BY groups.name ASC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            groupList.add(mapRowToGroup(results));
        }

        return groupList;
    }

    //@Override
    public String findGroupByGroupId(Long groupId) {
        return jdbcTemplate.queryForObject("select name from groups where group_id = ?", String.class, groupId);
    }

    // @Override
    public Group findGroupByName(String name) {
        return jdbcTemplate.queryForObject("select * from groups where name = ?", Group.class, name);
    }

    @Override
    public Group findGroupByUserId(Long userId) {
        return null;
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
    public void create(String name, String groupDescription, Long userId) {
        String insertGroup = "INSERT INTO groups(name, group_description)" +
                " VALUES(?,?) RETURNING group_id;";
        Long newGroupId = jdbcTemplate.queryForObject(insertGroup, Long.class, name, groupDescription);
        int groupId = (int)newGroupId.longValue();




        String insertMember = "INSERT INTO member_of(user_id, group_id, invite_accepted)" +
                " VALUES(?,?,?)";
        jdbcTemplate.update(insertMember, userId, groupId, true);

//        jdbcTemplate.query(insertMember, userId, groupId, true);
    }

    //Below Override was to make implements happy for now until we figure out why
    @Override
    public Group getGroup(Long groupId) {
        Group group = null;
        String getGroup = "SELECT * FROM groups WHERE group_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(getGroup, groupId);
        if (results.next()) {
            group = mapRowToGroup(results);
        }
        return group;
    }



    private Group mapRowToGroup(SqlRowSet rs) {
        Group group = new Group();
        group.setGroupId(rs.getLong("group_id"));
        group.setName(rs.getString("name"));
        //group.setAuthorities(rs.getString("role")); -- not sure how to deal with the role
        group.setGroupDescription(rs.getString("group_description"));
        group.setCreatedOn(rs.getDate("created_on"));

        return group;
    }

}
