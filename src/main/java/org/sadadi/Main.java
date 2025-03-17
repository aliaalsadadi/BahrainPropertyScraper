package org.sadadi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://houseme.bh/residential/");
//        DBHelper.clearResidential();
        PropertyListingPage listingPage = new PropertyListingPage(driver);
        listingPage.extractProperties().forEach(System.out::println);
        DBHelper.addListingPage(listingPage);
        // Locate the pagination container
        WebElement paginationContainer = driver.findElement(By.className("jet-filters-pagination"));
        // Retrieve all pagination items within the container
        List<WebElement> paginationItems = paginationContainer.findElements(By.className("jet-filters-pagination__item"));

        // Calculate the total number of pages
        int totalPages = paginationItems.size();
        System.out.println("Total number of pages: " + totalPages);
        for (int i=2;i<=totalPages;i++)
        {
            driver.get("https://houseme.bh/residential/?jsf=jet-engine:listingres&pagenum="+i);
            listingPage = new PropertyListingPage(driver);
            DBHelper.addListingPage(listingPage);
        }


        driver.quit();
    }

}
