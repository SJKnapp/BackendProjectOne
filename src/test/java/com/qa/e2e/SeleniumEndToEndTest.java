package com.qa.e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = { "classpath:ToDoList-schema.sql",
		"classpath:ToDoList-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class SeleniumEndToEndTest {

	@LocalServerPort
	private int port;
	private WebDriverWait wait;

	WebDriver driver = new FirefoxDriver();

	public SeleniumEndToEndTest() {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// maximize window
		driver.manage().window().maximize();

		this.wait = new WebDriverWait(driver, 5);

	}

	@Test
	void delete() {
		driver.get("http://localhost:" + port + "/");
		WebElement wait = this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("renderedTasks")));
		List<WebElement> pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));
		int startSize = pageElements.size();
		WebElement deleteButton = pageElements.get(0).findElement(By.cssSelector("button:nth-child(7)"));
		deleteButton.click();
		pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));
		int endSize = pageElements.size();
		assertEquals(startSize, endSize + 1);
	}

//	@Test
//	void testers() {
//		driver.get("http://localhost:" + port + "/");
//		WebElement wait = this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("renderedTasks")));
//		WebElement w = driver.findElement(By.cssSelector("#renderedTasks > div:nth-child(1) > input:nth-child(1)"));
//		// wait.findElements(By.cssSelector("div:nthchild"));
//		w.sendKeys("aaaaa");
//		List<WebElement> pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));
//		WebElement r = pageElements.get(0).findElement(By.cssSelector("input:nth-child(1)"));
//		r.clear();
//		System.out.println(pageElements);
//	}

	@AfterEach
	void tearDown() {
		this.driver.close();
	}
}
