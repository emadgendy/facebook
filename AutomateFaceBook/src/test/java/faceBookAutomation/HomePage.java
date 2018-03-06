package faceBookAutomation;

import static org.testng.Assert.assertEquals;

import com.custom.library.BasePage;


public class HomePage extends BasePage{
	


	public ObjectMap objmap;

	public HomePage goTo_facebook_HomePage() throws Exception {
		
		// Get current working directory
		//String workingDir = System.getProperty("user.dir");
		// Get object map file
		//objmap = new ObjectMap(workingDir + "\\src\\test\\java\\faceBookAutomation\\ObjectMap.java");
		objmap = new ObjectMap("src/test/resources/objectPropertiesFile\\objectMap.properties");

		driver.get("https://www.facebook.com");
		String webSiteTitle = driver.getTitle();
		// System.out.println("the website title is : " + webSiteTitle);
		String expectedwebSiteTitle = "Facebook - Log In or Sign Up";
		assertEquals(expectedwebSiteTitle, webSiteTitle);
		System.out.println("Results : " + webSiteTitle);
		myLib.customWait(3);
		return this;
	}
	
	public HomePage Enter_userName(String UserName) throws Exception{
		// step 1 locate the login email:
		myLib.enterTextField(objmap.getLocator("user_ID_field"), UserName);
		myLib.customWait(2);
		return this;
	}
	
	public HomePage Enter_userPassword(String UserPassword) throws Exception{
		// step 2 locate login password:
		myLib.enterTextField(objmap.getLocator("password_field"), UserPassword);
		myLib.customWait(2);
		return this;
	}
	
public HomePage Click_loginButton() throws Exception{
		// step 3 locate login button:
		myLib.clickWebElement(objmap.getLocator("loginBtn"));
		myLib.customWait(3);
		return this;
	}

public HomePage Confirm_password(String confirm_Pass) throws Exception{
	myLib.enterTextField(objmap.getLocator("confirm_Pass"), confirm_Pass);
	return this;
}

public HomePage confirm_login() throws Exception{
	myLib.clickWebElement(objmap.getLocator("login_Btn2"));
	myLib.customWait(5);
	return this;
}
}
