package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IframesPage extends BasePage {

    public IframesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "frame1")
    WebElement frame1;
    @FindBy(css = "h1")
    WebElement title;

    public IframesPage switchToIframeById() {
        driver.switchTo().frame(frame1);
        System.out.println(title.getText());
        return this;
    }
}
