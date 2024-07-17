package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	// tạo constructor (vì đc khỏi tạo từ đầu và xử lý đầu khi gọi đến lớp đó)
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	@FindBy(css = "ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementAppear(productsBy);
		return products;
	}

	public WebElement getProductsByName(String productName) {
		WebElement pro = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return pro;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductsByName(productName);
		prod.findElement(addToCart).click();
		waitForElementAppear(toastMessage);
		waitForElementDisAppear(spinner);
	}

}
