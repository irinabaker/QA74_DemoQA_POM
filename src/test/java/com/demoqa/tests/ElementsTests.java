package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.elements.BrokenLinksImagesPage;
import com.demoqa.pages.elements.ButtonsPage;
import com.demoqa.pages.elements.TextBoxPage;
import com.demoqa.pages.elements.UploadPage;
import com.demoqa.utils.MyArgumentsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;

public class ElementsTests extends TestBase {

    SidePanel sidePanel;
    ButtonsPage buttons;
    TextBoxPage textBox;
    BrokenLinksImagesPage broken;
    UploadPage upload;

    @BeforeEach
    public void precondition() {
        sidePanel = new SidePanel(driver);
        buttons = new ButtonsPage(driver);
        new HomePage(driver).getElements();
        textBox = new TextBoxPage(driver);
        broken = new BrokenLinksImagesPage(driver);
        upload = new UploadPage(driver);
    }

    @Test
    @Tag("smoky")
    public void doubleClickTest() {
        sidePanel.getButtons();
        buttons.doubleClick()
                .verifyDoubleClick("double click");
    }

    @Test
    public void rightClickTest() {
        sidePanel.getButtons();
        buttons.rightClick()
                .verifyRightClick("right click");
    }

    @Test
    public void copyPastTest() {
        sidePanel.getTextBox();
        textBox.copyPast("Friedrichstr. 12, Berlin")
                .clickOnSubmitButton()
                .verifyAddress();
    }

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    public void textBoxWithParameterTest(String name, String email, String address) {
        sidePanel.getTextBox();
        textBox.enterPersonalData(name,email,address)
                .clickOnSubmitButton()
                .verifyAddress();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void textBoxWthCsvFileTest(String name, String email, String address) {
        sidePanel.getTextBox();
        textBox.enterPersonalData(name,email,address)
                .clickOnSubmitButton()
                .verifyAddress();
    }

    @Test
    public void javascriptExecutorTest() {
        sidePanel.getTextBox();
        textBox.enterPersonalDataWithJS("Jamal Musiala","jamal@gm.com")
                .clickOnSubmitWithJS()
                .getInnerText()
                .verifyUrl()
                .refreshWithJS()
                .navigateWithJS("https://ilcarro.web.app")
                .verifyFaveIconTitle()
                ;
    }

    @Test
    public void getAllLinksTest() {
        sidePanel.getBrokenLinksImages();
        broken.getAllLinks();
    }

    @Test
    public void checkBrokenLinksTest() {
        sidePanel.getBrokenLinksImages();
        broken.checkBrokenLinks();
    }

    @Test
    public void checkBrokenImagesTest() {
        sidePanel.getBrokenLinksImages();
        broken.checkBrokenImages();
    }

    @Test
    public void performKeyEventTest() {
        sidePanel.getUpload();
        upload.performKeyEvent()
                .verifyFilePath("C:\\fakepath\\D1.txt")
                ;
    }

    @Test
    public void performMouseEventTest() {
        sidePanel.getUpload();
        upload.performMouseEvent()
                .verifyFilePath("C:\\fakepath\\D1.txt")
                ;
    }
}