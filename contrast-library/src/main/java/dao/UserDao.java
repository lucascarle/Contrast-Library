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




}
