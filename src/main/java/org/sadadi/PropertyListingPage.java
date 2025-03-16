package org.sadadi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PropertyListingPage {
    WebDriver driver;


    public PropertyListingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    // Using a full XPath to capture all h2 elements for listing titles

    @FindBy(xpath = "/html/body/div[2]/section[2]/div/div/div/div[1]/div/div/div/div[*]/div/div/div/div/div[2]/div/div/div/h2")
    private List<WebElement> titles;

    @FindBy(xpath = "/html/body/div[2]/section[2]/div[2]/div/div/div[1]/div/div/div/div[*]/div/div/div/a/div/div/div/div/div/div")
    private List<WebElement> prices;

    @FindBy(xpath = "/html/body/div[2]/section[2]/div[2]/div/div/div[1]/div/div/div/div[*]/div/div/div/div/div[3]/div/div/div/div[2]")
    private List<WebElement> locations;

    @FindBy(xpath = "/html/body/div[2]/section[2]/div/div/div/div[1]/div/div/div/div[*]/div/div/div/div/div[1]/div/ul/li[2]/span")
    private List<WebElement> RentorSale;

    @FindBy(xpath = "/html/body/div[2]/section[2]/div/div/div/div[1]/div/div/div/div[*]/div/div/div/div/div[1]/div/ul/li[1]/span")
    private List<WebElement> propertyTypes;

    @FindBy(xpath = "/html/body/div[2]/section[2]/div/div/div/div[1]/div/div/div/div[*]/div/div/div/div/div[4]/div[1]/div/div/ul/li[1]/span")
    private List<WebElement> numberOfBeds;

    @FindBy(xpath = "/html/body/div[2]/section[2]/div/div/div/div[1]/div/div/div/div[*]/div/div/div/div/div[4]/div[1]/div/div/ul/li[2]/span")
    private List<WebElement> numberOfBaths;

    @FindBy(xpath = "/html/body/div[2]/section[2]/div/div/div/div[1]/div/div/div/div[*]/div/div/div/div/div[4]/div[1]/div/div/ul/li[3]/span")
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
