import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement

TestObject makeTestObjectXPath(String id, String xPath) {
	TestObject tObj = new TestObject(id)
	tObj.addProperty("xpath", ConditionType.EQUALS, xPath)
	return tObj
}
TestObject makeTestObjectCSS(String id, String selector) {
	TestObject tObj = new TestObject(id)
	tObj.addProperty("css", ConditionType.EQUALS, selector)
	return tObj
}

// set the path of Firefox binary
System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox")

String url = 'https://react.dev/learn/typescript#typing-dom-events'

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl(url)

// in the page, find the 5th div that contains 'App.tsx' 
// which is near the header string "DOM Events", locate the target <iframe> element
TestObject toIframe = makeTestObjectXPath('toIframe',
	"//div[contains(@class,'sandpack') and contains(.,'App.tsx')][5]//iframe")
WebUI.verifyElementPresent(toIframe, 10)

// switch the context into the <iframe>
WebUI.switchToDefaultContent()
boolean b = WebUI.switchToFrame(toIframe, 10, FailureHandling.STOP_ON_FAILURE)
assert b : 'failed to switch to the iframe'

// inside the <iframe>, make sure a HTML document has been loaded
TestObject toRoot = makeTestObjectXPath('toRoot', "/html/body")
WebUI.verifyElementPresent(toRoot, 10, FailureHandling.STOP_ON_FAILURE)

// locate the <input> element
TestObject toInput = makeTestObjectXPath('toInput',
	"//*[@id='root']/input")
WebUI.verifyElementPresent(toInput, 10, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(3)

// try typing a string into the <input> element which was rendered by React.js
WebUI.clearText(toInput)
WebUI.sendKeys(toInput, "Hooah")

WebUI.delay(3)

// make sure that a text was echoed in the sibling <p> element 
TestObject toValue = makeTestObjectXPath('toValue',
	"//*[@id='root']/p")
WebUI.verifyElementText(toValue, 'Value: Hooah')

// Switch back to default content'
WebUI.switchToDefaultContent()

WebUI.closeBrowser()
