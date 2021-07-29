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
	void TestDeleteButton() {
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

	@Test
	void TestGetAll() {
		driver.get("http://localhost:" + port + "/");
		WebElement wait = this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("renderedTasks")));
		List<WebElement> pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));
		assertEquals(3, pageElements.size());
		List<WebElement> inputValues = pageElements.get(0).findElements(By.cssSelector("input"));
		assertEquals("one", inputValues.get(0).getAttribute("value"));
		assertEquals("a short task", inputValues.get(1).getAttribute("value"));
		assertEquals("1", inputValues.get(2).getAttribute("value"));
		assertEquals("100", inputValues.get(3).getAttribute("value"));
		assertEquals("2021-07-12", inputValues.get(4).getAttribute("value"));

		List<WebElement> buttonText = pageElements.get(0).findElements(By.cssSelector("button"));
		assertEquals("save", buttonText.get(0).getText());
		assertEquals("delete", buttonText.get(1).getText());
	}

	@AfterEach
	void tearDown() {
		this.driver.close();
	}
}
