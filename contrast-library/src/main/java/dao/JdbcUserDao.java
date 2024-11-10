package dao;

import exceptions.DaoException;
import model.Item;
import model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    public JdbcUserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUserById(int userId) {
        User user=null;
        String sql="SELECT user_id, " +
                "first_name, " +
                "last_name, " +
                "email, " +
                "phone, " +
                "p.community_id " +
                "FROM person" +
                "JOIN community c " +
                "ON p.community_id=c.community_id " +
                "WHERE user_id=?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);
            while(results.next()){
                user=mapRowToUser(results);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public List<User> getUserByCommunityId (int communityId) {
        List<User> users=new ArrayList<>();
        String sql="SELECT user_id, " +
                "first_name, " +
                "last_name, " +
                "email, " +
                "phone, " +
                "p.community_id " +
                "FROM person" +
                "JOIN community c " +
                "ON p.community_id=c.community_id " +
                "WHERE p.community_id=?";
        try{
            SqlRowSet results= jdbcTemplate.queryForRowSet(sql, communityId);
            while(results.next()){
                users.add(mapRowToUser(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return users;
    }

    private User mapRowToUser(SqlRowSet rowSet){
        User user = new User();

        user.setFirstName(rowSet.getString("first_name"));
        user.setLastName(rowSet.getString("last_name"));
        user.setEmail(rowSet.getString("email"));
        user.setPhone(rowSet.getString("phone"));
        user.setCommunity_id(rowSet.getInt("community_id"));
        user.setCommunity_name(rowSet.getString("community_name"));

        return user;
    }

}
