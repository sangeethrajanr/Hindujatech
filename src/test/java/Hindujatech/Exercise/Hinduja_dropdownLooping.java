package Hindujatech.Exercise;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.collect.Iterables;

public class Hinduja_dropdownLooping {

	public static void main(String[] args) throws Exception {
		// TODO Launch browser

		// WebDriver driver = new FirefoxDriver();
		// System.setProperty("webdriver.gecko.driver",
		// "./"+"\\drivers\\geckodriver.exe");

		//System.setProperty("webdriver.chrome.driver", "./" + "\\drivers\\chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		//options.setBinary("./" + "\\chrome\\chrome.exe");
		//options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://clicktobuy.hyundai.co.in/#/bookACar?modelCode=0Y");

		/*
		 * Testcase Select Model
		 */

		WebElement w = driver.findElement(By.xpath("//select[@id='inputmodel-01']"));
		Select dropdown = new Select(w);
		dropdown.selectByVisibleText(" New Verna ");

		// Select Fuel

		WebElement fuel = driver.findElement(By.xpath("//select[@id='inputfuel-01']"));
		Select dropdown1 = new Select(fuel);
		dropdown1.selectByVisibleText(" Petrol");

		Thread.sleep(3000);
		// select variant
		WebElement variant = driver.findElement(By.xpath("//select[@id='inputvrnt-01']"));
		Select dropdown2 = new Select(variant);
		dropdown2.selectByIndex(1);

		Thread.sleep(3000);
		// Select Exterior
		WebElement exterior = driver.findElement(By.xpath("//select[@id='inputext-01']"));
		Select dropdown3 = new Select(exterior);
		dropdown3.selectByIndex(1);

		Thread.sleep(3000);
		// Select Interior
		WebElement interior = driver.findElement(By.xpath("//select[@id='inputinter-01']"));
		Select dropdown4 = new Select(interior);
		dropdown4.selectByIndex(1);

		Thread.sleep(3000);
		// Select Dealer State
		WebElement state_dd = driver.findElement(By.xpath("//select[@id='state-01']"));

		Select state_dd_options = new Select(state_dd);

		//looping through state,city and dealer and print the details.
		List<WebElement> state_options = state_dd_options.getOptions();
		int i = 1;
		
		File file = new File(System.getProperty("user.dir")+"/Dealer_State_City_Dealership_List.txt");
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
		for (WebElement state : Iterables.skip(state_options, 1)) {
			System.out.println("State: " + state.getText() + " i: " + i);
			writer.write("State: " + state.getText() + " i: " + i+ "\n");
			state_dd_options.selectByIndex(i); i++;
			Thread.sleep(2000);
			WebElement city_dd = driver.findElement(By.xpath("//select[@id='dealer-city-01']"));
			Select city_dd_options = new Select(city_dd);
			List<WebElement> city_options = city_dd_options.getOptions();
			int j = 1;
			for (WebElement city : Iterables.skip(city_options, 1)) {
				System.out.println("\t City: " + city.getText() + " j:"+j);
				writer.write("\t City: " + city.getText() + " j:"+j + "\n");
				city_dd_options.selectByIndex(j); j++;
				Thread.sleep(2300);
				WebElement dealer_dd = driver.findElement(By.xpath("//select[@id='bookacardelar']"));
				Select dealer_dd_options = new Select(dealer_dd);
				List<WebElement> dealer_options = dealer_dd_options.getOptions();
			
				for (WebElement dealer : dealer_options) {
					System.out.println("\t\t Dealer: " + dealer.getText());
					writer.write("\t\t  Dealer: " + dealer.getText() +"\n");
				}
			}

		}
		writer.close();
		driver.close();
	}

}
