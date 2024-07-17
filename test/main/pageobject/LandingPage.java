package pageobject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;	
	//tạo constructor (vì đc khỏi tạo từ đầu và xử lý đầu khi gọi đến lớp đó)
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}	
	//driver.findElement(By.id("userEmail")).sendKeys("long@gmail.com");
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public String getErrorMessage() {
		waitForWebElementAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	public ProductCatalogue LoginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		return productCatalogue;
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");

	}
}
