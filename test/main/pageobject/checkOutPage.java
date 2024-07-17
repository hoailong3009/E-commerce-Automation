package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class checkOutPage  extends AbstractComponent{


	WebDriver driver;	
	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath="(//i[@class='fa fa-search'])[2]")
	WebElement selectCountry;
	@FindBy(css=".action__submit")
	WebElement submit;
	By result =By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		
		Actions a =new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}	
	public confirmationPage submitOrder() {
		submit.click();
		return new confirmationPage(driver);
		
	}
}
