package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobject.CartPage;
import pageobject.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver= driver;
	
	}
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css="[routerlink*='myorders'")
	WebElement orderHeader;
	
	public void waitForElementAppear(By findBy) {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		}
	
	public void waitForElementDisAppear(WebElement ele) throws InterruptedException {
	Thread.sleep(1000);

	//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
	//wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	public CartPage clickCart() {
		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage clickOrder() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
}
