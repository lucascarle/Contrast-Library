package model;

public class Item {
    private int itemId;
    private int ownerId;
    private String ownerName;
    private int borrowerId;
    private String itemName;
    private String description;
    private String category;
    private String subcat;
    private boolean available = true;

    //Constructors//
    public Item() {}

    //Getters and Setters//
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    public int getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getBorrowerId() {
        return borrowerId;
    }
    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcat() {
        return subcat;
    }

    public void setSubcat(String subcatName) {
        this.subcat = subcatName;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
