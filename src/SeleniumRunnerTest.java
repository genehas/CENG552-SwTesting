import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import Entities.MAPEntities;

public class SeleniumRunnerTest {

	public static WebDriver driver=null;
	public MAPEntities object=null;
	public String Param="";
	@Test
	public void BrowserOperations() {
		UIOperation.BrowserOperation test= new UIOperation.BrowserOperation();
		boolean output=test.BrowserOperations("");
		assertEquals(false, output);
	}
	@Test
	public void CheckObjectID() {
		UIOperation.BrowserOperation test= new UIOperation.BrowserOperation();
		boolean output=test.checkObjectID("3000");
		assertEquals(false, output);
	}
	@Test
	public void ClickObject() throws InterruptedException {
		UIOperation.BrowserOperation test= new UIOperation.BrowserOperation();
		MAPEntities object=null;
		boolean output=test.ClickObject(object, driver);
		assertEquals(false, output);
	}
	@Test
	public void DoBrowserOperations() throws InterruptedException {
		UIOperation.BrowserOperation test= new UIOperation.BrowserOperation();
		
		boolean output=test.DoBrowserOperations();
		assertEquals(false, output);
	}
	@Test
	public void ObjectExist() throws InterruptedException {
		UIOperation.BrowserOperation test= new UIOperation.BrowserOperation();
		boolean output=test.IsExistObject(object, driver);
		assertEquals(false, output);
	}
	@Test
	public void SetParam() throws InterruptedException {
		UIOperation.BrowserOperation test= new UIOperation.BrowserOperation();
		boolean output=test.SetParam(object, Param, driver);
		assertEquals(false, output);
	}


}
