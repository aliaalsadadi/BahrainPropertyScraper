package org.sadadi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://houseme.bh/residential/");

        PropertyListingPage listingPage = new PropertyListingPage(driver);

        List<String> titles = listingPage.getAllTitles();
        List<String> prices = listingPage.getAllPrices();
        List<String> locations = listingPage.getAllLocations();
        List<String> RentorSale = listingPage.getAllRentorSale();
        List<String> propertyTypes = listingPage.getAllPropertyTypes();
        List<Integer> numberOfBeds = listingPage.getAllNumberOfBeds();
        List<Integer> numberOfBaths = listingPage.getAllNumberOfBaths();
        List<Integer> sqmAreas = listingPage.getAllSqmAreas();

        // Ensure all lists have the same size
        int expectedSize = titles.size();

        if (expectedSize == prices.size() &&
                expectedSize == locations.size() &&
                expectedSize == RentorSale.size() &&
                expectedSize == propertyTypes.size() &&
                expectedSize == numberOfBeds.size() &&
                expectedSize == numberOfBaths.size() &&
                expectedSize == sqmAreas.size()) {

            System.out.println("✅ All lists have the same size: " + expectedSize);

            // Print the values
            for (int i = 0; i < expectedSize; i++) {
                System.out.println("Listing " + (i + 1) + ":");
                System.out.println("Title: " + titles.get(i));
                System.out.println("Price: " + prices.get(i));
                System.out.println("Location: " + locations.get(i));
                System.out.println("Rent or Sale: " + RentorSale.get(i));
                System.out.println("Property Type: " + propertyTypes.get(i));
                System.out.println("Beds: " + numberOfBeds.get(i));
                System.out.println("Baths: " + numberOfBaths.get(i));
                System.out.println("Area: " + sqmAreas.get(i) + " sqm");
                System.out.println("-------------------------------");
            }

        } else {
            System.err.println("❌ Mismatch in list sizes:");
            System.err.println("Titles: " + titles.size());
            System.err.println("Prices: " + prices.size());
            System.err.println("Locations: " + locations.size());
            System.err.println("Rent or Sale: " + RentorSale.size());
            System.err.println("Property Types: " + propertyTypes.size());
            System.err.println("Beds: " + numberOfBeds.size());
            System.err.println("Baths: " + numberOfBaths.size());
            System.err.println("Sqm Areas: " + sqmAreas.size());
        }

        driver.quit();
    }
}
