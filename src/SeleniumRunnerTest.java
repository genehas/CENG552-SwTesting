import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import Entities.MAPEntities;

public class SeleniumRunnerTest {

	public static WebDriver driver=null;
	public MAPEntities object=null;
	public String Param="";
	public String Arguments[]=new String[4];
	
	@Before
	public void FillArguments()
	{
		Arguments[0]="-DO=BROWSEROP:CloudLoginPageTest.txt";
		Arguments[1]="-ID=0";
		Arguments[2]="-MAP=CloudPageMap.xml";
		Arguments[3]="-DUT=DUT.txt";
	}
	@Test
	public void ReadUserArguments() throws InterruptedException {
		TestOperation.readArguments test= new TestOperation.readArguments();
		boolean output=test.readUserArguments(Arguments);
		assertEquals(true, output);
	}
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
	@Test
	public void VerfyParam() throws InterruptedException {
		UIOperation.BrowserOperation test= new UIOperation.BrowserOperation();
		boolean output=test.VerifyParam(object, Param, driver);
		assertEquals(false, output);
	}
	@Test
	public void GetMApObject() throws InterruptedException {
		UIOperation.BrowserOperation test= new UIOperation.BrowserOperation();
		MAPEntities output=test.getMapObject("");
		assertEquals(null, output);
	}
	@Test
	public void GetMApObjectUI() throws InterruptedException {
		UIOperation.UIDriver test= new UIOperation.UIDriver();
		MAPEntities output=test.getMapObject("");
		assertEquals(null, output);
	}
	@Test
	public void CheckObjectUI() throws InterruptedException {
		UIOperation.UIDriver test= new UIOperation.UIDriver();
		boolean output=test.checkObjectID("");
		assertEquals(false, output);
	}
	@Test
	public void CloseWEBUI() throws InterruptedException {
		UIOperation.UIDriver test= new UIOperation.UIDriver();
		boolean output=test.closeWEBUI();
		assertEquals(false, output);
	}
	
	

}
