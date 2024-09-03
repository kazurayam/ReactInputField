import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement

TestObject makeTestObjectCSS(String id, String selector) {
	TestObject tObj = new TestObject(id)
	tObj.addProperty("css", ConditionType.EQUALS, selector)
	return tObj
}

// set the path of Firefox binary
System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox")

// Here we assume that the local Next.js Dev server is already running by
// cd my-next-app
// npx run dev
String url = 'http://localhost:3000'

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl(url)

// locate the <input> element
TestObject toInput = makeTestObjectCSS('input', "input")
WebUI.verifyElementPresent(toInput, 10, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(3)

// try typing a string into the <input> element which was rendered by React.js
WebUI.clearText(toInput)
WebUI.sendKeys(toInput, "Hooah")

WebUI.delay(3)

// make sure that a text was echoed in the sibling <p> element 
TestObject toValue = makeTestObjectCSS('toValue', "p")
WebUI.verifyElementText(toValue, 'Value: Hooah')

// Switch back to default content'
WebUI.switchToDefaultContent()

WebUI.closeBrowser()
