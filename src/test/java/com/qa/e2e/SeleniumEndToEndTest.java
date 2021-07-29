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
		driver.manage().window().maximize();

		this.wait = new WebDriverWait(driver, 5);

	}

	@Test
	void TestDeleteButton() {
		driver.get("http://localhost:" + port + "/");
		this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("renderedTasks")));
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
		this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("renderedTasks")));
		List<WebElement> pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));
		assertEquals(4, pageElements.size());
		List<WebElement> inputValues = pageElements.get(0).findElements(By.cssSelector("input"));
		assertEquals(5, inputValues.size());
		assertEquals("one", inputValues.get(0).getAttribute("value"));
		assertEquals("a short task", inputValues.get(1).getAttribute("value"));
		assertEquals("1", inputValues.get(2).getAttribute("value"));
		assertEquals("100", inputValues.get(3).getAttribute("value"));
		assertEquals("2021-07-12", inputValues.get(4).getAttribute("value"));

		List<WebElement> buttonText = pageElements.get(0).findElements(By.cssSelector("button"));
		assertEquals("save", buttonText.get(0).getText());
		assertEquals("delete", buttonText.get(1).getText());

	}

	@Test
	void TestCreateEntry() {
		driver.get("http://localhost:" + port + "/");
		this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("renderedTasks")));
		List<WebElement> pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));

		List<WebElement> inputValues = pageElements.get(2).findElements(By.cssSelector("input"));

		String TaskName = "new task";
		inputValues.get(0).clear();
		inputValues.get(0).sendKeys(TaskName);

		String TaskDescription = "a automaticly created task";
		inputValues.get(1).clear();
		inputValues.get(1).sendKeys(TaskDescription);

		String TaskPrioity = "1";
		inputValues.get(2).clear();
		inputValues.get(2).sendKeys(TaskPrioity);

		String TaskLength = "199";
		inputValues.get(3).clear();
		inputValues.get(3).sendKeys(TaskLength);

		String TaskDate = "2021-09-01";
		inputValues.get(4).clear();
		inputValues.get(4).sendKeys(TaskDate);

		List<WebElement> buttons = pageElements.get(2).findElements(By.cssSelector("button"));
		assertEquals("save", buttons.get(0).getText());
		buttons.get(0).click();

		pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));
		List<WebElement> storedValues = pageElements.get(2).findElements(By.cssSelector("input"));
		assertEquals(5, storedValues.size());
		assertEquals(TaskName, storedValues.get(0).getAttribute("value"));
		assertEquals(TaskDescription, storedValues.get(1).getAttribute("value"));
		assertEquals(TaskPrioity, storedValues.get(2).getAttribute("value"));
		assertEquals(TaskLength, storedValues.get(3).getAttribute("value"));
		assertEquals(TaskDate, storedValues.get(4).getAttribute("value"));
	}

	@Test
	void TestOverDue() {
		driver.get("http://localhost:" + port + "/");
		this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("renderedTasks")));
		List<WebElement> pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));

		List<WebElement> inputValues = pageElements.get(0).findElements(By.cssSelector("input"));

		String htmlClass = "overdue";

		assertEquals(true, inputValues.get(0).getAttribute("class").contains(htmlClass));
		assertEquals(true, inputValues.get(1).getAttribute("class").contains(htmlClass));
		assertEquals(true, inputValues.get(2).getAttribute("class").contains(htmlClass));
		assertEquals(true, inputValues.get(3).getAttribute("class").contains(htmlClass));
		assertEquals(true, inputValues.get(4).getAttribute("class").contains(htmlClass));
	}

	@Test
	void TestStillTime() {
		driver.get("http://localhost:" + port + "/");
		this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("renderedTasks")));
		List<WebElement> pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));

		List<WebElement> inputValues = pageElements.get(2).findElements(By.cssSelector("input"));

		String htmlClass = "inDate";

		assertEquals(true, inputValues.get(0).getAttribute("class").contains(htmlClass));
		assertEquals(true, inputValues.get(1).getAttribute("class").contains(htmlClass));
		assertEquals(true, inputValues.get(2).getAttribute("class").contains(htmlClass));
		assertEquals(true, inputValues.get(3).getAttribute("class").contains(htmlClass));
		assertEquals(true, inputValues.get(4).getAttribute("class").contains(htmlClass));
	}

	@Test
	void TestDateNotSet() {
		driver.get("http://localhost:" + port + "/");
		this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("renderedTasks")));
		List<WebElement> pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));

		List<WebElement> inputValues = pageElements.get(3).findElements(By.cssSelector("input"));

		String htmlClass = "notSet";

		assertEquals(true, inputValues.get(0).getAttribute("class").contains(htmlClass));
		assertEquals(true, inputValues.get(1).getAttribute("class").contains(htmlClass));
		assertEquals(true, inputValues.get(2).getAttribute("class").contains(htmlClass));
		assertEquals(true, inputValues.get(3).getAttribute("class").contains(htmlClass));
		assertEquals(true, inputValues.get(4).getAttribute("class").contains(htmlClass));
	}

	@Test
	void TestStatusDoneToNotDone() {
		driver.get("http://localhost:" + port + "/");
		this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("renderedTasks")));
		List<WebElement> pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));

		WebElement testRow = pageElements.get(1);

		List<WebElement> inputValues = testRow.findElements(By.cssSelector("input"));
		WebElement doneButton = testRow.findElements(By.cssSelector("button")).get(2);

		String htmlClassStart = "done";

		assertEquals(true, inputValues.get(0).getAttribute("class").contains(htmlClassStart));
		assertEquals(true, inputValues.get(1).getAttribute("class").contains(htmlClassStart));
		assertEquals(true, inputValues.get(2).getAttribute("class").contains(htmlClassStart));
		assertEquals(true, inputValues.get(3).getAttribute("class").contains(htmlClassStart));
		assertEquals(true, inputValues.get(4).getAttribute("class").contains(htmlClassStart));

		doneButton.click();
		String htmlClassEnd = "inDate";

		pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));
		testRow = pageElements.get(1);
		inputValues = testRow.findElements(By.cssSelector("input"));

		assertEquals(true, inputValues.get(0).getAttribute("class").contains(htmlClassEnd));
		assertEquals(true, inputValues.get(1).getAttribute("class").contains(htmlClassEnd));
		assertEquals(true, inputValues.get(2).getAttribute("class").contains(htmlClassEnd));
		assertEquals(true, inputValues.get(3).getAttribute("class").contains(htmlClassEnd));
		assertEquals(true, inputValues.get(4).getAttribute("class").contains(htmlClassEnd));
	}

	@Test
	void TestStatusNotDoneToDone() {
		driver.get("http://localhost:" + port + "/");
		this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("renderedTasks")));
		List<WebElement> pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));

		WebElement testRow = pageElements.get(0);

		List<WebElement> inputValues = testRow.findElements(By.cssSelector("input"));
		WebElement doneButton = testRow.findElements(By.cssSelector("button")).get(2);

		String htmlClassStart = "overdue";

		assertEquals(true, inputValues.get(0).getAttribute("class").contains(htmlClassStart));
		assertEquals(true, inputValues.get(1).getAttribute("class").contains(htmlClassStart));
		assertEquals(true, inputValues.get(2).getAttribute("class").contains(htmlClassStart));
		assertEquals(true, inputValues.get(3).getAttribute("class").contains(htmlClassStart));
		assertEquals(true, inputValues.get(4).getAttribute("class").contains(htmlClassStart));

		doneButton.click();
		String htmlClassEnd = "done";

		pageElements = driver.findElements(By.cssSelector("#renderedTasks > div"));
		testRow = pageElements.get(0);
		inputValues = testRow.findElements(By.cssSelector("input"));

		assertEquals(true, inputValues.get(0).getAttribute("class").contains(htmlClassEnd));
		assertEquals(true, inputValues.get(1).getAttribute("class").contains(htmlClassEnd));
		assertEquals(true, inputValues.get(2).getAttribute("class").contains(htmlClassEnd));
		assertEquals(true, inputValues.get(3).getAttribute("class").contains(htmlClassEnd));
		assertEquals(true, inputValues.get(4).getAttribute("class").contains(htmlClassEnd));
	}

	@AfterEach
	void tearDown() {
		this.driver.close();
	}
}
