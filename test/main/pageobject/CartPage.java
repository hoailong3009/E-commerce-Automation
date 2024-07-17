package pageobject;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;	

	@FindBy(xpath="//div[@class='cartSection']/h3")
	private List<WebElement> productTitles;
	
	@FindBy(css=".totalRow button")
	WebElement clickCheckOut;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	
	public 	Boolean VerifyProductDisplay(String procducName) {
		Boolean match = productTitles.stream().anyMatch(product -> product.getText().equalsIgnoreCase(procducName));
		return match;
	}
	public checkOutPage clickCheckOut() {
		clickCheckOut.click();
		return new checkOutPage(driver);
	}
	
	
}
