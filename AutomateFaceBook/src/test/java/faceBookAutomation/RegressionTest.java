package faceBookAutomation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.custom.library.BasePage;
import com.custom.library.ExcelManager;

import atu.testrecorder.ATUTestRecorder;


public class RegressionTest extends BasePage {

	@DataProvider(name = "login_info")
	public static Object[][] login() {
		ExcelManager read = new ExcelManager("src/test/resources/login_info.xls");
		return read.getExcelData("Sheet1");
	}

	public ObjectMap objmap;
	HomePage HomePage = new HomePage();

	@Test(dataProvider = "login_info")
	public void faceBookLogin(String UserName, String UserPassword, String confirm_Pass) throws Exception {

		ATUTestRecorder recorder = new ATUTestRecorder("C:\\Users\\Training 2015\\Desktop\\Selenium_recorder",
				"TestVideo ", false);
		recorder.start();

		HomePage.goTo_facebook_HomePage();
		HomePage.Enter_userName(UserName);
		HomePage.Enter_userPassword(UserPassword);
		HomePage.Click_loginButton();
		HomePage.Confirm_password(confirm_Pass);
		HomePage.confirm_login();

		recorder.stop();

	}

}
