import java.io.IOException;

import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testClass {
	static ScreenRecorder sr;
	static WebDriver driver;

	public static void main(String[] args) {
		try {
			setUp();
			driver.get("http://www.google.com");
			Thread.sleep(5000);
			tearDown(true);
		} catch (Exception e) {
			e.printStackTrace();
			tearDown(false);
		}

	}

	public static void setUp() throws Exception {
		sr = utilities.getScreenRecorder();
		driver = new FirefoxDriver();
		sr.start();
	}

	public static void tearDown(boolean flag) {
		try {
			sr.stop();
			if (flag) {
				utilities.removeFiles(sr.getCreatedMovieFiles());
			} else {
				utilities.moveFile("testClass", sr.getCreatedMovieFiles());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.close();
	}
}
