package dao;

import model.User;

import java.util.List;

public interface UserDao {

    /**
     * Get a user from the datastore that has the given id.
     *
     * @param userId is the id of the person
     * @return a filled out User object, or null if no user is found.
     */
    User getUserById(int userId);

    /**
     * Get a list of users from the datastore that belong to the given community by id,
     * order by user_id
     *
     * @param communityId is the id of the community
     * @return all users as User objects in a List or an empty list if no users are found.
     */
    List<User> getUserByCommunityId(int communityId);

    /**
     * Add a new user to the datastore.
     *
     * @param newUser user object to be added
     * @return the user object of the newly created user including the userID
     **/
    User createUser(User newUser);

    /**
     * Updates an existing user in the datastore.
     *
     * @param user is a user object with any updated data.
     * @return the user object of the updated user.
     */
    User updateUser(User user);

    /**
     * Removes a user from the datastore.
     *
     * @param userId ID of the user to be removed from the datastore.
     */
    void deleteUser(int userId);




}
