package com.example.tlucontact;

public class Employee {
    private String id;
    private String name;
    private String position;
    private String phoneNumber;
    private String email;
    private String unitId;
    private String unitName;
    private String imageUrl;

    public Employee(String id, String name, String position, String phoneNumber,
                    String email, String unitId, String unitName, String imageUrl) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.unitId = unitId;
        this.unitName = unitName;
        this.imageUrl = imageUrl;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUnitId() { return unitId; }
    public void setUnitId(String unitId) { this.unitId = unitId; }

    public String getUnitName() { return unitName; }
    public void setUnitName(String unitName) { this.unitName = unitName; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
