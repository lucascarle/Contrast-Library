package dao;

import model.Person;

import java.util.List;

public interface PersonDao {

    /**
     * Get a person from the datastore that has the given id.
     *
     * @param personId is the id of the person
     * @return a filled out Person object, or null if no Person is found.
     */
    Person getPersonById(int personId);

    /**
     * Get a list of people from the datastore that belong to the given community by id,
     * order by person_id
     *
     * @param communityId is the id of the community
     * @return all people as person objects in a List or an empty list if no people are found.
     */
    List<Person> getPersonByCommunityId(int communityId);


}
