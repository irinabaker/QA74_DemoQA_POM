package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PracticeFormTests extends TestBase {

    @BeforeEach
    public void precondition() {
        new HomePage(driver).getForms();
        new SidePanel(driver).getPracticeForm();
    }

    @Test
    public void createAccountPositiveTest() {

    }
}
