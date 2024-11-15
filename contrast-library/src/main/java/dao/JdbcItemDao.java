package dao;

import exceptions.DaoException;
import model.Item;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcItemDao implements ItemDao {

    private final JdbcTemplate jdbcTemplate;
    private final String baseSqlSelect=
            "SELECT i.item_id, " +
            "owner_id, " +
            "borrower_id, " +
            "item_name, " +
            "item_description, " +
            "category_name, " +
            "subcat_name, " +
            "available " +
            "FROM item i " +
            "JOIN item_subcategory isub ON i.item_id = isub.item_id " +
            "JOIN subcategory s ON isub.subcat_id=s.subcat_id ";

    public JdbcItemDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Item getItemById(int itemId) {
        Item item = null;

        String sql =
                baseSqlSelect +
                "JOIN person p ON i.owner_id=p.user_id" +
                "WHERE item_id=?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, itemId);
            if (results.next()) {
                item = mapRowToItem(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return item;
    }

    @Override
    public List<Item> getItemsByOwnerId(int ownerId, boolean onlyShowAvail) {
        List<Item> items = new ArrayList<>();

        String sql =
                baseSqlSelect+
                "WHERE owner_id = ? ";
        if (onlyShowAvail) {
            sql += "AND available=true ";
        }
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, ownerId);
            while (results.next()) {
                items.add(mapRowToItem(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return items;
    }

    @Override
    public List<Item> getItemsByCategoryName(String categoryName, boolean onlyShowAvail) {
        List<Item> items = new ArrayList<>();

        String sql =
                baseSqlSelect +
                "WHERE category_name = ?";
        if (onlyShowAvail) {
            sql += "AND available=true ";
        }
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, categoryName);
            while (results.next()) {
                items.add(mapRowToItem(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return items;
    }

    @Override
    public List<Item> getItemsBySubcategoryName(String subcatName, boolean onlyShowAvail) {
        List<Item> items = new ArrayList<>();

        String sql =
                baseSqlSelect +
                "WHERE subcat_name= ?";
        if (onlyShowAvail) {
            sql += "AND available=true ";
        }
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, subcatName);
            while (results.next()) {
                items.add(mapRowToItem(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return items;
    }

    public List<Item> getItemsBySearchKeyword(String search, boolean onlyShowAvail) {
        List<Item> items = new ArrayList<>();

        String sql =
                baseSqlSelect+
                "WHERE item_name ilike %?% " +
                "OR item_description ilike %?% ";
        if (onlyShowAvail) {
            sql += "AND available=true ";
        }
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, search, search);
            while (results.next()) {
                items.add(mapRowToItem(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return items;

    }

    @Override
    public Item addItem(Item itemToAdd) {
        Item newItem = null;

        String sql =
                "INSERT INTO item (owner_ID,item_Name,item_Description) " +
                "VALUES (select user_ID FROM person WHERE user_id=?),?,?) " +
                "RETURNING item_id ";
        try {
            int newItemId =
                    jdbcTemplate.queryForObject(sql, int.class,
                    itemToAdd.getOwnerId(),
                    itemToAdd.getItemName(),
                    itemToAdd.getDescription());

            itemToAdd.setItemId(newItemId);
            //itemToAdd now completed with itemID can be put into subcat method
            linkItemToSubcategory(itemToAdd);
            newItem = getItemById(newItemId);

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newItem;
    }

    public void linkItemToSubcategory(Item item){
        String sqlAttachCategory =
                "INSERT INTO item_subcategory (subcat_id,item_id) " +
                "VALUES (SELECT subcat_ID from Subcategory WHERE category_Name= ? AND subcat_Name= ? ), ? ";
        try{
            jdbcTemplate.queryForObject(sqlAttachCategory,int.class,
                    item.getCategory(),
                    item.getSubcat(),
                    item.getItemId());
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    public void unlinkItemFromSubcategory(int itemId){
        String sql=
                "DELETE FROM item_subcategory " +
                "WHERE item_id=? ";
        try {
            jdbcTemplate.update(sql, itemId, itemId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
    }


    @Override
    public Item updateItem(Item item) {
        Item changedItem = null;
        String sql =
                "UPDATE item " +
                "SET " +
                "item_name= ?, " +
                "item_description= ?, " +
                "available = ? " +
                "WHERE item_id= ? ";
        try {
            int rowCt = jdbcTemplate.update(sql,
                    item.getItemName(),
                    item.getDescription(),
                    item.isAvailable(),
                    item.getItemId());
            unlinkItemFromSubcategory(item.getItemId());
            linkItemToSubcategory(item);
            if (rowCt != 1) {
                throw new DaoException("Unable to make update");
            } else {
                changedItem = getItemById(item.getItemId());
            }
        } catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Cannot access database", ex);
        } catch (DataIntegrityViolationException ex) {
            throw new DaoException("Data integrity violation", ex);
        }
        return changedItem;
    }

    @Override
    public void deleteItem(int itemId) {
        //TODO add category to SQL call
        String sql =
                "DELETE FROM item " +
                "WHERE item_id=? ";

        try {
            unlinkItemFromSubcategory(itemId);
            jdbcTemplate.update(sql, itemId, itemId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
    }

    private Item mapRowToItem(SqlRowSet rowSet) {
        Item item = new Item();
        item.setItemId(rowSet.getInt("item_id"));
        item.setOwnerId(rowSet.getInt("owner_id"));
        item.setBorrowerId(rowSet.getInt("borrower_id"));
        item.setItemName(rowSet.getString("item_name"));
        item.setDescription(rowSet.getString("item_description"));
        item.setCategory(rowSet.getString("category_name"));
        item.setSubcat(rowSet.getString("subcat_name"));
        item.setAvailable(rowSet.getBoolean("available"));

        return item;
    }
}



