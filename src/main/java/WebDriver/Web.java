package WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

public class Web {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.geeksforgeeks.org/");
        String pageTitle = driver.getTitle();

        System.out.println("Page Title: " + pageTitle);
        try {

            Thread.sleep(3000);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }
        driver.quit();
    }
}
