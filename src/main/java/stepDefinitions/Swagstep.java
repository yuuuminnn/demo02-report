package stepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class Swagstep {
    WebDriver driver = null;
//    @Before
//    public void setupDriver(){
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        String projectPath = System.getProperty("user.dir");
//        System.setProperty("webdriver.chrome.driver",projectPath + "/src/test/resources/Driver/chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//    }
//
////    @After
//    public void quitDriver(){
//        driver.close();
//        driver.quit();
//    }

    @Given("open url and login")
    public void open_url_and_login() throws InterruptedException {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
        driver.getPageSource().contains("Products");
        Thread.sleep(3000);
    }

//    @When("add product to cart")
//    public void addProductToCart() throws InterruptedException {
//        Select select = new Select(driver.findElement(By.xpath("//*[@class=\"product_sort_container\"]")));
//        select.selectByValue("hilo");
//        driver.findElement(By.xpath("//*[@class='btn btn_primary btn_small btn_inventory']")).click();
//    }

    @And("go to Cart and Checkout")
    public void goToCartAndCheckout() throws InterruptedException {

        driver.findElement(By.xpath("//*[@class=\"shopping_cart_link\"]")).click();
        driver.getPageSource().contains("Your Cart");
        driver.findElement(By.xpath("//*[@name=\"checkout\"]")).click();
        Thread.sleep(3000);
    }

    @And("input rceiving address")
    public void inputRceivingAddress() {
        driver.getPageSource().contains("Checkout: Your Information");
        driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys("test-addtess1");
        driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys("test-addtess2");
        driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys("test-addtess3");
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
    }

    @Then("checkout Overview")
    public void checkoutOverview() throws InterruptedException {
        driver.getPageSource().contains("Checkout: Overview");
        String AA;
        AA = driver.findElement(By.xpath("//*[@class=\"summary_info_label summary_total_label\"]")).getText();
        System.out.println("最终成交价格:" + AA);
        driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();
        driver.getPageSource().contains("Thank you for your order!");
        System.out.println(driver.findElement(By.xpath("//*[@class=\"complete-text\"]")).getText());
        Thread.sleep(3000);
    }

    @Then("userlogout")
    public void userlogout() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]")).click();

    }
}