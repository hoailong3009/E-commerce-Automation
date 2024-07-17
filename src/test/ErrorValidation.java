package test;

import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;
import TestComponent.BaseTest;



public class ErrorValidation extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=TestComponent.Retry.class)
	public void getError() throws IOException, InterruptedException {
		landingPage.LoginApplication("long1@gmail.com", "long123");
		Assert.assertEquals("Incorrect  email or password.", landingPage.getErrorMessage());;

	}
}


