package com.demoqa.pages.elements;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.List;

public class BrokenLinksImagesPage extends BasePage {

    public BrokenLinksImagesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a")
    List<WebElement> allLinks;

    public BrokenLinksImagesPage getAllLinks() {
        //size
        System.out.println("Total links on the page = " + allLinks.size());
        //list
        String url = "";
        Iterator<WebElement> iterator = allLinks.iterator();
        while (iterator.hasNext()) {
            url = iterator.next().getText();
            System.out.println(url);
            System.out.println("**********************************");
        }
        return this;
    }

    public BrokenLinksImagesPage checkBrokenLinks() {
        for (int i = 0; i < allLinks.size(); i++) {
            WebElement element = allLinks.get(i);
            String url = element.getDomAttribute("href");
            verifyLinks(url);
        }
        softly.assertAll();
        return this;
    }

    @FindBy(css = "img")
    List<WebElement> images;

    public BrokenLinksImagesPage checkBrokenImages() {
        System.out.println("Total images on the page = " + images.size());
        for (int i = 0; i < images.size(); i++) {
            WebElement image = images.get(i);
            String imageUrl = image.getAttribute("src");
            verifyLinks(imageUrl);

            try {
                boolean imageDisplayed = (Boolean) js.executeScript
                        ("return (typeof arguments[0].naturalWidth!=undefined && arguments[0].naturalWidth>0);", image);
                if (imageDisplayed) {
                    softly.assertThat(imageDisplayed);
//                    System.out.println("DISPLAY - OK");
//                    System.out.println("***************************");
                } else {
                    softly.fail("BROKEN image --> " + imageUrl);
//                    System.out.println("DISPLAY - BROKEN");
//                    System.out.println("***************************");
                }
            }catch (Exception e) {
                softly.fail("ERROR occurred");
               // System.out.println("ERROR occurred");
            }
        }
        softly.assertAll();
        return this;
    }
}
