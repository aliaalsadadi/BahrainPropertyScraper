package org.sadadi;

public record Property(String title, int price, String location, String RentOrSale, String propertyType,
                       int numberOfBeds, int numberOfBaths, int sqmArea) {
}
