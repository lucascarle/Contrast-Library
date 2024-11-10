package model;

public class User {

    private int user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int community_id;
    private String community_name;

    //Constructors//
    public User() {}

    //Getters and Setters//
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getCommunity_id() {
        return community_id;
    }
    public void setCommunity_id(int community_id) {
        this.community_id = community_id;
    }
    public String getCommunity_name() {
        return community_name;
    }
    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }
}
