package dao;

import exceptions.DaoException;
import model.Item;
import model.User;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Override
    public User createUser(User newUser) {
        User updatedUser= null;
        String sql =
                "INSERT INTO Person(first_Name,last_Name,email,phone,community_id) "+
                "VALUES (?,?,?,?,?) "+
                "RETURNING user_id ";
        try {
            int userID = jdbcTemplate.queryForObject(sql,int.class,
                    newUser.getFirstName(),
                    newUser.getLastName(),
                    newUser.getEmail(),
                    newUser.getPhone(),
                    newUser.getCommunity_id());
            updatedUser=getUserById(userID);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("Unable to update datastore", e);
        }
        catch (Exception e){
            throw new DaoException("An error has occurred",e);
        }
        return updatedUser;
    }

    @Override
    public User updateUser(User user) {
        User updatedUser = null;
        String sql="UPDATE person SET " +
                "first_name=?', " +
                "last_name=?, " +
                "email=?, " +
                "phone=?, " +
                "community_id=? " +
                "WHERE user_id=?";
        try
        {
            int rows = jdbcTemplate.update(sql,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getCommunity_id(),
                    user.getUser_id());
            if(rows!=1) {
                throw new DaoException("Update failed");
            }
            updatedUser = getUserById(user.getUser_id());
        }catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Cannot access database",ex);
        }catch(DataIntegrityViolationException ex) {
            throw new DaoException("Data integrity violation",ex);
        }
        return updatedUser;
    }

    @Override
    public Void deleteUser(int userId) {
        String sql=
                "DELETE FROM item_subcategory " +
                "WHERE item_id IN (select item_id from item where owner_id=?); " +
                "DELETE FROM item WHERE owner_id=?; " +
                "DELETE FROM person where user_id=? ";
        try {
            jdbcTemplate.update(sql, userId,userId,userId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        catch (DataIntegrityViolationException e) {
            throw new DaoException("There was an error deleting this user", e);
        }
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
