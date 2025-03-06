package com.techSoft.commonPlatformUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonSelenium {
    public WebDriver driver;

    public CommonSelenium(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        try {
            waitFor(2);
            WebElement element = driver.findElement(locator);
            highlight(element);
            element.click();
            waitFor(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to click on element [" + locator + "]");
        }
    }

    public void ClickWithJS(By locators) {
        try {
            waitFor(1);
            WebElement ele = driver.findElement(locators);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            highlight(ele);
            executor.executeScript("arguments[0].click();", ele);
            waitFor(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to click on element [" + locators + "] ");
        }
    }

    public void sendKeys(By locator, String text) {
        try {
            waitFor(1);
            WebElement element = driver.findElement(locator);
            highlight(element);
            element.sendKeys(text);
            waitFor(1);
        } catch (Exception e) {
            e.getMessage();
            Assert.fail("Failed to send keys to element [" + locator + "]");
        }
    }

    public void sendKeys(WebElement element, String text) {
        try {
            waitFor(2);
            highlight(element);
            element.sendKeys(text);
            waitFor(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to send keys to element");
        }
    }

    public void enterValueUsingJS(By locator, String text) {
        try {

            WebElement element = driver.findElement(locator);
            highlight(element);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].setAttribute('text', arguments[1]);", element, text);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to Enter textValue to element");
        }
    }

    public void enterValueUsingJS(WebElement element, String text) {
        try {
            highlight(element);
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].setAttribute('text', arguments[1]);", element, text);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to Enter textValue to element");
        }
    }


    public void handleEleClickInterException(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Actions actions = new Actions(driver);
            highlight(element);
            actions.moveToElement(element).click().perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void waitForElementClickable(WebElement webElement, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception e) {
            System.err.println(
                    "Waited for element [" + webElement + "] to be clickable for " + timeoutSeconds + " seconds");
        }
    }


    public void waitForElementClickable(By locator, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
        } catch (Exception e) {
            System.err.println(
                    "Waited for Locator [" + locator + "] to be clickable for " + timeoutSeconds + " seconds");
        }
    }

    public void waitForElementToAppear(WebElement ele, int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.visibilityOf(ele));
        } catch (TimeoutException e) {
            e.printStackTrace();
            Assert.fail("Element did not appear within " + seconds + " seconds: " + ele);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An unexpected error occurred while waiting for the element: " + ele);
        }
    }

    public void waitForElementPresence(WebDriver driver, By by, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            System.err.println("Waited for element [" + by.toString() + "] presence for " + seconds + " seconds");
        }
    }

    public void waitForElementVisibility(WebDriver driver, By by, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            System.err.println("Waited for element [" + by.toString() + "] visibility for " + seconds + " seconds");
        }
    }

    public void waitForElementToBeClickable(By by, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            System.err
                    .println("Waited for element [" + by.toString() + "] to be clickable for " + seconds + " seconds");
        }
    }

    protected void waitForElementDisplay(WebDriver driver, By locator, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            System.err.println("Waited for element located by [" + locator + "] for " + seconds + " seconds");
        }
    }

    public void waitFor(int i) {
        try {
            Thread.sleep(1000 * i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollToElement(WebDriver driver, By locator, int yOffset) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
        js.executeScript("window.scrollBy(0, arguments[0]);", yOffset);
    }


    public boolean isTextInPage(String text) {
        try {
            return driver.getPageSource().contains(text);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isTextPresent(WebDriver driver, String actualText) {
        return actualText.contains(actualText);
    }

    public boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isWebElementPresent(By locator, int duration) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public void clickElementWithText(String textVal) {
        try {
            String xpath = String.format("//*[text()='%s' or contains(text(),'%s')]", textVal, textVal);
            waitFor(1);
            highlight(By.xpath(xpath));
            click(By.xpath(xpath));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An error occurred while clicking element with text: " + textVal + ". Error: " + e.getMessage());
        }
    }

    public void selectDropdownOptionByText(WebDriver driver, By dropdownLocator, String optionText) {
        try {
            WebElement dropdown = driver.findElement(dropdownLocator);
            highlight(dropdownLocator);
            Select select = new Select(dropdown);
            select.selectByVisibleText(optionText);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to select dropdown option: [" + optionText + "] from dropdown: " + dropdownLocator);
        }
    }

    public void clearElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        highlight(element);
        element.clear();
    }


    public void moveToElement(By locator) {
        try {
            waitFor(2);
            WebElement element = driver.findElement(locator);
            Actions actions = new Actions(driver);
            highlight(element);
            actions.moveToElement(element).build().perform();
            waitFor(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to move to element [" + locator + "]");
        }
    }

    public void doubleClick(By locator) {
        try {
            waitFor(2);
            WebElement element = driver.findElement(locator);
            highlight(element);

            Actions actions = new Actions(driver);
            actions.doubleClick(element).build().perform();

            waitFor(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to double click on element [" + locator + "]");
        }
    }

    public void click(WebElement element) {
        try {
            waitFor(2);
            highlight(element);
            element.click();
            waitFor(1);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to click on element");
        }
    }

    public void highlight(By element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style','background: ; border: 2px solid red;');", driver.findElement(element));
        } catch (Exception e) {
            e.printStackTrace();
            junit.framework.Assert.fail("Failed to highlight [" + element + "] element.");
        }
    }

    public void highlight(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style','background: ; border: 2px solid red;');", element);
        } catch (Exception e) {
            e.printStackTrace();
            junit.framework.Assert.fail("Failed to highlight [" + element + "] element.");
        }
    }


    public void sendKeysWithWait(By locator, String text, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds)); // Adjust timeout as needed
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        element.sendKeys(text);
    }


    public static void scrollIntoView(WebDriver driver, By locator) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollIntoView(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Failed to scroll to element.");
        }
    }

    public void scrollByPixels(int x, int y) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to scroll by pixels y [" + y + "] and [" + y + "]");
        }
    }

    public int getVerticalScrollOffset() {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            return ((Long) jsExecutor.executeScript("return window.pageYOffset;")).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("An error occurred while getting the vertical scroll offset.");
            return 0;
        }
    }

    public void switchToChildWindow() {
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                driver.switchTo().window(parentWindow);
                driver.close();
                driver.switchTo().window(windowHandle);
                System.out.println("closing Parent window and control on the new open window");
            }
        }
    }

    public void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, -1750)");
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,750)");
    }

    public void scrollToBottomOfPage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToTopOfPage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }

    public void setPageZoom(String zoomLevel) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.body.style.zoom = arguments[0];", zoomLevel);
    }


    public void scrollUpTo(By elementLocator) {
        try {
            WebElement element = driver.findElement(elementLocator);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + elementLocator);
        } catch (Exception ex) {
            ex.printStackTrace();
            Assert.fail("Failed to scroll to element: " + elementLocator);
        }
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void scrollClick(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView(true);", element);
        waitFor(1);
        js.executeScript("arguments[0].click();", element);
    }


    public void waitForOverlayToDisappear(By overlayElement, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayElement));
        } catch (Exception e) {
            System.err.println("Overlay did not disappear within " + timeoutSeconds + " seconds.");
        }
    }

    public static String generateRandomText(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static int getRandomNumberInRanges(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    public WebElement findElementFromFrame(String xPath) {

        List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
        int index = 0;
        for (WebElement frame : frames) {
            try {
                driver.switchTo().defaultContent();
                return driver.switchTo().frame(index).findElement(By.xpath(xPath));
            } catch (NoSuchFrameException | NoSuchElementException e) {
                index = index + 1;
            }
        }
        return driver.findElement(By.xpath(xPath));
    }

    public String getCurrentValueOfDropDownList(By by) {
        Select select = new Select(driver.findElement(by));
        return select.getFirstSelectedOption().getText();
    }

    public void clickOnTextSpan(String textVal) {
        String xpath = String.format("//span[.='%s']", textVal);
        click(By.xpath(xpath));
    }

    public void pressESCKey() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).perform();
        waitFor(3);
    }


    public String getCurrentDate() {
        Date date = new Date();
        DateFormat dateFormat2 = new SimpleDateFormat("dd MMM, yyyy");
        String date1 = dateFormat2.format(date);
        System.out.println(date1);
        return date1;
    }

    public String getTomorrowDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        Date date = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();
        System.out.println(dateFormat.format(date));

        DateFormat dateFormat2 = new SimpleDateFormat("dd MMM, yyyy");
        System.out.println(dateFormat2.format(date));
        return dateFormat2.format(date);
    }

    public String getYesterdaysDate() {
        Date date = DateUtils.addDays(new Date(), -1);
        SimpleDateFormat sdf = new SimpleDateFormat("d");
        return sdf.format(date);
    }

    public String getTomorrowsDate() {
        Date date = DateUtils.addDays(new Date(), 1);
        SimpleDateFormat sdf = new SimpleDateFormat("d");
        return sdf.format(date);
    }

    public List<String> getAllOptions(By locator) {
        Select action = new Select(driver.findElement(locator));
        List<WebElement> options = action.getOptions();
        List<String> values = new ArrayList<String>();
        int size = options.size();
        for (int i = 0; i < size; i++) {
            String op = options.get(i).getText();
            values.add(op);
        }
        return values;
    }

    public void selectByOption(By locator, String option) {
        Select action = new Select(driver.findElement(locator));
        action.selectByVisibleText(option);
    }

    public void mouseMoveByOffset(int x, int y) {
        Actions action = new Actions(driver);
        action.moveByOffset(x, y).perform();
    }

    public void hoverElement(WebElement el) {
        Actions action = new Actions(driver);
        action.moveToElement(el).perform();
        waitFor(1);
        action.release();

    }

    public void selectDropDownValue(int positionNeedToSelect) {
        Actions action = new Actions(driver);
        for (int i = 1; i <= positionNeedToSelect; i++) {
            action.sendKeys(Keys.DOWN);
        }
        action.sendKeys(Keys.ENTER);
        action.perform();
        waitFor(2);
    }

    public boolean containsIgnoreCase(String parent, String child) {
        return parent.toLowerCase().contains(child.toLowerCase());
    }

    public void getToUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForElementText(String textVal, int timeoutSeconds) {
        String xpath = String.format("//*[text()='%s' or contains(text(),'%s')]", textVal, textVal);
        waitForElementDisplay(By.xpath(xpath), timeoutSeconds);
    }

    public void scrollClickBtnWithText(String textVal) {
        String xpath = String.format("//button[text()='%s']", textVal, textVal);

        waitFor(1);
        scrollClick(By.xpath(xpath));
        waitFor(1);
    }


    public void sendKeysAction(CharSequence... text) {
        Actions action = new Actions(driver);
        action.sendKeys(text).build().perform();
    }

    public List<String> getTextValuesFromElements(By locator, int limit) {
        int count = 0;

        List<WebElement> elements = driver.findElements(locator);
        List<String> textValues = new ArrayList<String>();

        for (WebElement element : elements) {
            if (count <= limit) {
                textValues.add(element.getText());
                count++;
            } else
                return textValues;
        }
        return textValues;
    }

    public double getNumericValue(String numberTxt) // returns 1000.00 from " ₹ 1,000 " for calculations
    {
        String number = "";
        for (char c : numberTxt.toCharArray())
            if (Character.isDigit(c) || c == '.')
                number += c;
        return Double.parseDouble(number);
    }

    public void waitForNBLoad(int seconds) {
        try {
            for (int i = 0; i < seconds / 2; i++) {
                waitFor(2);
                if (!driver.getPageSource().contains("Loading..."))
                    break;
                else
                    System.out.println("Loading...");
            }

        } catch (Exception e) {
            System.err.println("Loading error");
        }
    }

    public void waitForNBLoad(String loadingId, int seconds) throws InterruptedException {
        try {
            for (int i = 0; i < seconds / 2; i++) {
                waitFor(2);
                if (!driver.getPageSource().contains(loadingId))
                    break;
                else
                    System.out.println("Waiting for [" + loadingId + "]");
            }

        } catch (Exception e) {
            System.err.println("Loading error");
        }

    }

    public void closeTab() {
        try {
            Thread.sleep(3);
            if (driver != null)
                driver.close();
            System.out.println("Tab is Closed Successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void waitForJsLoad(int seconds) {
        JavascriptExecutor js = (JavascriptExecutor) driver; // waits for loading indicator in browser header
        String ready;
        waitFor(2);
        try {
            for (int i = 0; i < seconds; i++) {
                ready = js.executeScript("return document.readyState").toString();
                System.out.println(ready);
                if (ready.equals("complete"))
                    break;
                else
                    waitFor(1);
            }
            waitFor(1);
        } catch (Exception e) {
            System.err.println("Error in waiting for browser load" + e.getMessage());
        }
    }

    protected void waitForElementDisplay(WebElement locator, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until(ExpectedConditions.visibilityOf(locator));
        } catch (Exception e) {
            System.err.println("Waited for element [" + locator + "] for " + timeoutSeconds + " seconds");
        }
    }

    protected void waitForElementDisplay(By locator, int timeoutSeconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            System.err.println("Waited for element [" + locator + "] for " + timeoutSeconds + " seconds");
        }
    }


    protected void clickAction(By locator) throws InterruptedException {
        Actions action = new Actions(driver);
        action.click(driver.findElement(locator)).perform();
    }

    public void switchToTab(int tabNo) {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(tabNo));
    }

    public void switchBackToParentWindowAndCloseChild() {
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                driver.close();
                System.out.println("Closed child window");
                driver.switchTo().window(parentWindow);
                System.out.println("Switched back to parent window");
                break; // Exit the loop after closing the child window
            }
        }
    }

    public void switchToChildWindowWithoutClosingParent() {
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                driver.switchTo().window(windowHandle);
                System.out.println("closing Parent window and control on the new open window");
            }
        }
    }

    public void scrollClickExactText(String textVal) {
        String xpath = String.format("//*[text()='%s']", textVal, textVal);
        scrollClick(By.xpath(xpath));
    }

    public void scrollClickText(String textVal) {
        String xpath = String.format("//*[text()='%s' or contains(text(),'%s')]", textVal, textVal);
        scrollClick(By.xpath(xpath));
    }

    protected String getAttribute(By locator, String attribute) {
        String attributeValue = driver.findElement(locator).getAttribute(attribute);

        return attributeValue != null ? attributeValue : "null";
    }

    public boolean isElementTextDisplayed(String textVal) {
        String xpath = String.format("//*[text()='%s' or contains(text(),'%s')]", textVal, textVal);

        try {
            return driver.findElement(By.xpath(xpath)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void refresh() {
        try {
            waitFor(1);
            driver.navigate().refresh();
            waitFor(2);
        } catch (Exception e) {
            Assert.fail("Failed to refresh page..");
        }
    }

    public void scrollDownTillLast() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        waitFor(1);
    }


    public String getText(By locator) {
        try {
            waitFor(2);
            highlight(locator);
            return driver.findElement(locator).getText();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to get String for element [" + locator + "]");
        }
        return null;
    }

    public boolean isElementDisplayed(By locator) {
        waitFor(2);
        highlight(locator);
        Coordinates coordinate = ((Locatable) driver.findElement(locator)).getCoordinates();
        coordinate.onPage();
        coordinate.inViewPort();
        return driver.findElement(locator).isDisplayed();
    }


    public String getRandomValueFromArray(List<String> values) {
        Random rand = new Random();
        String randomElement = values.get(rand.nextInt(values.size()));
        if (randomElement.equalsIgnoreCase("Select"))
            return getRandomValueFromArray(values);
        else
            return randomElement;
    }

    public int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public WebElement getRandomValueFromGivenList(List<WebElement> givenList) {
        Random rand = new Random();
        WebElement randomElement = givenList.get(rand.nextInt(givenList.size()));
        while (randomElement.getText().equalsIgnoreCase("select")) {
            continue;
        }
        return randomElement;
    }

    public void clearAndSendKeys(WebElement webelement, String str) {
        try {
            waitFor(1);
            highlight(webelement);
            webelement.clear();
            webelement.sendKeys(str);
            waitFor(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollUpto(WebElement e) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", e);
        } catch (Exception ea) {
            ea.printStackTrace();
            Assert.fail("Failed to scroll upto [" + e + "] element.");
        }
    }

    public void pressBack(int times) {
        for (int i = 1; i <= times; i++) {
            driver.navigate().back();
            waitFor(1);
        }

    }

    public void clickWithMoreWait(By locators) {
        try {
            waitFor(5);
            highlight(locators);
            driver.findElement(locators).click();
            waitFor(10);
            waitForJsLoad(60);
            waitForNBLoad(60);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed to click on element [" + locators + "] ");
        }
    }
}