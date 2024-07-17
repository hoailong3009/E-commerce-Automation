package test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponent.BaseTest;
import pageobject.CartPage;
import pageobject.OrderPage;
import pageobject.ProductCatalogue;
import pageobject.checkOutPage;
import pageobject.confirmationPage;

public class Login extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	/*
	 * public static void main(String[] args) throws InterruptedException {
	 * WebDriverManager.chromedriver().setup(); ChromeOptions options = new
	 * ChromeOptions(); options.addArguments("--remote-allow-origins=*"); WebDriver
	 * driver = new ChromeDriver(options);
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); LandingPage
	 * landingPage= new LandingPage(driver); landingPage.goTo(); ProductCatalogue
	 * productCatalogue =landingPage.LoginApplication("long@gmail.com", "Long123@");
	 * WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
	 */
	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("email"), input.get("password"));

		/*
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
		 * ".mb-3"))); List<WebElement> products=
		 * driver.findElements(By.cssSelector(".mb-3")); WebElement pro
		 * =products.stream().filter(product ->
		 * product.findElement(By.cssSelector("b")).getText().equals(productName)).
		 * findFirst().orElse(null);
		 * pro.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
		 * "#toast-container")));
		 * wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.
		 * cssSelector(".ng-animating"))));
		 * driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click
		 * (); List<WebElement> cartProducts=
		 * driver.findElements(By.xpath("//div[@class='cartSection']/h3")); Boolean
		 * match =cartProducts.stream().anyMatch(cartProduct->
		 * cartProduct.getText().equalsIgnoreCase(productName));
		 * driver.findElement(By.cssSelector(".totalRow button")).click(); Actions a
		 * =new Actions(driver);
		 * a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']"
		 * )), "india").build().perform();
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
		 * ".ta-results")));
		 * driver.findElement(By.xpath("(//i[@class='fa fa-search'])[2]")).click();
		 * driver.findElement(By.cssSelector(".action__submit")).click(); String
		 * confirmMessage =
		 * driver.findElement(By.cssSelector(".hero-primary")).getText();
		 * Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."))
		 * ;
		 */
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.clickCart();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		checkOutPage checkOutPage = cartPage.clickCheckOut();
		checkOutPage.selectCountry("india");
		confirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMessage = confirmationPage.GetConfirm();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistory() {
		ProductCatalogue productCatalogue = landingPage.LoginApplication("long1@gmail.com", "long123");
		OrderPage orderPage = productCatalogue.clickOrder();
		orderPage.VerifyOrderDisplay(productName);
	}

	public String getScreenShot(String testcaseName) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "/report/" + testcaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "/report/" + testcaseName + ".png";
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				"/Users/hoailong/eclipse-workspace/Automation/test/main/data/PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
