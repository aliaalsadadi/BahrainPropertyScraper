package org.sadadi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PropertyListingPage {
    WebDriver driver;


    public PropertyListingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // Using a full XPath to capture all h2 elements for listing titles
    @FindBy(xpath = "//div[contains(@class, 'jet-listing-grid__item') and contains(@class, 'jet-listing-dynamic-post-') and @data-post-id]")
    private List<WebElement> listingItems;

    public List<Property> extractProperties() {
        List<Property> properties = new ArrayList<>();
        System.out.println("Found listing items: " + listingItems.size());

        for (WebElement item : listingItems) {
            String title = "";
            int price = 0;
            String location = "";
            String rentOrSale = "";
            String propertyType = "";
            int beds = 0;
            int baths = 0;
            int sqm = 0;

            try {
                title = item.findElement(By.xpath(".//h2[contains(@class, 'jet-listing-dynamic-field__content')]")).getText();
            } catch (Exception e) {
//                System.err.println("Title not found in one listing: " + e.getMessage());
            }

            try {
                String priceText = item.findElement(By.xpath(".//div[contains(@class, 'jet-listing-dynamic-field__content') and contains(text(), 'BHD')]")).getText();
                price = Integer.parseInt(priceText.replaceAll("[^\\d]", ""));
            } catch (Exception e) {
//                System.err.println("Price not found in listing '" + title + "': " + e.getMessage());
            }

            try {
                location = item.findElement(By.xpath(".//div[contains(@class, 'jet-listing-dynamic-field__icon')]/following-sibling::div[contains(@class, 'jet-listing-dynamic-field__content')]")).getText();
            } catch (Exception e) {
//                System.err.println("Location not found in listing '" + title + "': " + e.getMessage());
            }

            try {
                propertyType = item.findElement(By.xpath(".//ul/li[1]/span")).getText();
            } catch (Exception e) {
//                System.err.println("Property type not found in listing '" + title + "': " + e.getMessage());
            }

            try {
                rentOrSale = item.findElement(By.xpath(".//ul/li[2]/span")).getText();
            } catch (Exception e) {
//                System.err.println("Rent or Sale info not found in listing '" + title + "': " + e.getMessage());
            }

            try {
                String bedsText = item.findElement(By.xpath(".//span[contains(@class, 'elementor-icon-list-text') and contains(text(), 'Bed')]")).getText();
                beds = Integer.parseInt(bedsText.replaceAll("[^\\d]", ""));
            } catch (Exception e) {
//                System.err.println("Beds info not found in listing '" + title + "': " + e.getMessage());
            }

            try {
                String bathsText = item.findElement(By.xpath(".//span[contains(@class, 'elementor-icon-list-text') and contains(text(), 'Bath')]")).getText();
                baths = Integer.parseInt(bathsText.replaceAll("[^\\d]", ""));
            } catch (Exception e) {
//                System.err.println("Baths info not found in listing '" + title + "': " + e.getMessage());
            }

            try {
                String sqmText = item.findElement(By.xpath(".//span[contains(@class, 'elementor-icon-list-text') and contains(text(), 'Sqm')]")).getText();
                sqm = Integer.parseInt(sqmText.replaceAll("[^\\d]", ""));
            } catch (Exception e) {
//                System.err.println("Sqm area not found in listing '" + title + "': " + e.getMessage());
            }

            Property property = new Property(title, price, location, rentOrSale, propertyType, beds, baths, sqm);
            if (title.isEmpty() && price == 0 && location.isEmpty() && rentOrSale.isEmpty() && propertyType.isEmpty() && beds == 0 && baths == 0 && sqm == 0) {
                continue;
            }
            properties.add(property);
        }

        return properties;
    }

    @FindBy(xpath = "//h2[@class='jet-listing-dynamic-field__content']")
    private List<WebElement> titles;


    @FindBy(xpath = "//div[contains(@class, 'jet-listing-dynamic-field__content')][contains(text(), 'BHD')]")
    private List<WebElement> prices;

    @FindBy(xpath = "//div[contains(@class, 'jet-listing-dynamic-field__icon')]/following-sibling::div[contains(@class, 'jet-listing-dynamic-field__content')]")
    private List<WebElement> locations;


    @FindBy(xpath = "/html/body/div[2]/section[2]/div/div/div/div[1]/div/div/div/div[*]/div/div/div/div/div[1]/div/ul/li[2]/span")
    private List<WebElement> RentorSale;

    @FindBy(xpath = "/html/body/div[2]/section[2]/div/div/div/div[1]/div/div/div/div[*]/div/div/div/div/div[1]/div/ul/li[1]/span")
    private List<WebElement> propertyTypes;

    @FindBy(xpath = "//span[contains(@class, 'elementor-icon-list-text') and contains(text(), 'Bed')]")
    private List<WebElement> numberOfBeds;

    @FindBy(xpath = "//span[contains(@class, 'elementor-icon-list-text') and contains(text(), 'Bath')]")
    private List<WebElement> numberOfBaths;

    @FindBy(xpath = "//span[contains(@class, 'elementor-icon-list-text') and contains(text(), 'Sqm')]")
    private List<WebElement> sqmAreas;


    // Retrieve all titles as text
    public List<String> getAllTitles() {
        return titles.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Integer> getAllPrices() {
        return prices.stream()
                .map(WebElement::getText)   // Extract text from each element
                .map(text -> text.replaceAll("[^0-9]", "")) // Remove non-numeric characters
                .map(Integer::parseInt)  // Convert to integer
                .collect(Collectors.toList());
    }

    public List<String> getAllLocations() {
        return locations.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getAllRentorSale() {
        return RentorSale.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getAllPropertyTypes() {
        return propertyTypes.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<Integer> getAllNumberOfBeds() {
        return numberOfBeds.stream()
                .map(WebElement::getText)   // Extract text from each element
                .map(text -> text.replaceAll("[^0-9]", "")) // Remove non-numeric characters
                .map(Integer::parseInt)  // Convert to integer
                .collect(Collectors.toList());
    }

    public List<Integer> getAllNumberOfBaths() {
        return numberOfBaths.stream()
                .map(WebElement::getText)   // Extract text from each element
                .map(text -> text.replaceAll("[^0-9]", "")) // Remove non-numeric characters
                .map(Integer::parseInt)  // Convert to integer
                .collect(Collectors.toList());
    }

    public List<Integer> getAllSqmAreas() {
        return sqmAreas.stream().map(WebElement::getText)
                .map(text -> text.replaceAll("[^0-9]", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

}
