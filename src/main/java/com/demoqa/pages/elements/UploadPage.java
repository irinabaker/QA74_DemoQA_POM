package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.security.Key;

public class UploadPage extends BasePage {

    Robot robot;

    public UploadPage(WebDriver driver) {
        super(driver);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    @FindBy(id = "uploadFile")
    WebElement uploadFile;

    public UploadPage performKeyEvent() {
        scrollWithJS(0,100,500);
        clickWithRectangle(uploadFile);
        //press SHIFT key
        pause(1000);
        robot.keyPress(KeyEvent.VK_SHIFT);
        pause(1000);
        //press d(upper case as SHIFT key is pressed)
        robot.keyPress(KeyEvent.VK_D);
        //release SHIFT key
        robot.keyRelease(KeyEvent.VK_SHIFT);
        pause(1000);
        //press 1, ., t, x, t
        robot.keyPress(KeyEvent.VK_1);
        robot.keyPress(KeyEvent.VK_PERIOD);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyPress(KeyEvent.VK_X);
        robot.keyPress(KeyEvent.VK_T);
        pause(1000);
        //press ENTER
        robot.keyPress(KeyEvent.VK_ENTER);
        return this;
    }

    @FindBy(id = "uploadedFilePath")
    WebElement uploadedFilePath;

    public UploadPage verifyFilePath(String path) {
        Assertions.assertTrue(isContainsText(path,uploadedFilePath));
        return this;
    }

    public UploadPage performMouseEvent() {
        scrollWithJS(0,100,1000);
        clickWithRectangle(uploadFile);
        pause(2000);
        //find coordinates of file D1.txt
//        Point location = MouseInfo.getPointerInfo().getLocation();
//        int x = (int) location.getX();
//        int y = (int) location.getY();
// 1470 x 956
        //840 *** 714
        //1226 *** 238
      //  System.out.println(x + " *** " + y);
        pause(2000);
        //hover mouse on the file by coordinates
        robot.mouseMove(1226,238);
        pause(2000);
        //press left(CRL + press) mouse button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        //release left mouse button
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        pause(2000);
        //press ENTER key
        robot.keyPress(KeyEvent.VK_ENTER);
        return this;
    }
}
