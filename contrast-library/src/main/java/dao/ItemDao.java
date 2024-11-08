package dao;

import model.Item;

import java.util.List;

public interface ItemDao {


    /**
     * Get a item from the datastore that has the given id.
     *
     * @param itemId the id of the item.
     * @return a filled out Item object, or null if no Item is found.
     */
    Item getItemById(int itemId);

    /**
     * Get all items from the datastore for a given user,
     * ordered by item_id.
     *
     * @param ownerId the id of the user.
     * @param onlyShowAvail whether the List should account for item availability.
     * @return all items as Item objects in a list or an empty list if no Items are found.
     */
    List<Item> getItemsByOwnerId(int ownerId, boolean onlyShowAvail);

    /**
     * Get all items from the datastore for a given category,
     * ordered by item_id.
     *
     * @param categoryName the name of the Category.
     * @param onlyShowAvail whether the List should account for item availability.
     * @return all items as Item objects in a list or an empty list if no Items are found.
     */
    List<Item> getItemsByCategoryName(String categoryName, boolean onlyShowAvail);

    /**
     * Get all items from the datastore for a given subcategory, inherently part of a category,
     * ordered by item_id.
     *
     * @param subcatName the name of the Subcategory.
     * @param onlyShowAvail whether the List should account for item availability.
     * @return all items as Item objects in a list or an empty list if no Items are found.
     */
    List<Item> getItemsBySubcategoryName(String subcatName,boolean onlyShowAvail );

    /**
     * Get all items from the datastore containing the search query
     *
     * @param search the query to find similar items.
     * @param onlyShowAvail whether the List should account for item availability.
     * @return all items as Item objects in a list or an empty list if no Items are found.
     */
    List<Item> getItemsBySearchKeyword(String search,boolean onlyShowAvail);
    /**
     * Create a new item in the datastore.
     *
     * @param newItem the Item object being added, without an id.
     * @return the added Item complete with id, else null.
     */
    Item addItem(Item newItem);

    /**
     * Update an existing item in the datastore.
     *
     * @param item the item object with any changed information.
     * @return updated item object.
     */
    Item updateItem(Item item);

    /**
     * Remove item from the datastore by id.
     *
     * @param itemId the id of the item to be deleted.
     * No return.
     */
    void deleteItem(int itemId);

}
