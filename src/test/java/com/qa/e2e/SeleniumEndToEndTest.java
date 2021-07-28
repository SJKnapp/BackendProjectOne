package com.qa.e2e;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class SeleniumEndToEndTest {

	WebDriver driver = new FirefoxDriver();

	public SeleniumEndToEndTest() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// maximize window
		driver.manage().window().maximize();
	}

	@Test
	void testers() {
		driver.get("http://127.0.0.1/");
	}
}
