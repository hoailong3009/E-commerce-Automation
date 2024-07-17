package pageobject;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;	

	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> orderName;
	
	@FindBy(css=".totalRow button")
	WebElement clickCheckOut;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	
	public 	Boolean VerifyOrderDisplay(String procducName) {
		Boolean match = orderName.stream().anyMatch(product -> product.getText().equalsIgnoreCase(procducName));
		return match;
	}

	
	
}
