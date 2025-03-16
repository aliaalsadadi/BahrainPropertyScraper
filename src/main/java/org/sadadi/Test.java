package org.sadadi;

public class Test {
    public static void main(String[] args) {
        Property property = new Property("Luxury Apartment", 500000, "Downtown", "Sale", "Apartment", 3, 2, 120);

        DBHelper DBHelper = new DBHelper();
        DBHelper.addProperty(property);
    }
}
