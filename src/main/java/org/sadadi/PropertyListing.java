package org.sadadi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PropertyListing {
    private WebElement listingElement;

    public PropertyListing(WebElement listingElement) {
        this.listingElement = listingElement;
    }

    // Using a relative XPath to get the title (h2 element)
    public String getTitle() {
        return listingElement.findElement(By.xpath(".//h2")).getText();
    }

    // You can add other methods to extract more details if needed.
}
