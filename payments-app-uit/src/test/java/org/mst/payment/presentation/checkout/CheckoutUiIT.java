package org.mst.payment.presentation.checkout;

import static org.hamcrest.CoreMatchers.*;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import static org.jboss.arquillian.graphene.Graphene.*;
import org.jboss.arquillian.junit.Arquillian;
import static org.junit.Assert.assertThat;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * UI TESTS with ARQUILLIAN / GRAPHENE / DRONE / SELENIUM WEBDRIVER
 * 
 * DRONE:
 *  The Arquillian Drone extension for Arquillian provides a simple way how to include functional tests for your application
 *  with a web-based user interface. Arquillian Drone brings the power of WebDriver into the Arquillian framework.
 *  WebDriver provides a language how to communicate with a browser, like filling the forms, navigating
 *  on the pages and validating their content.
 * 
 * GRAPHENE:
 * Arquillian Graphene is a set of extensions for the WebDriver API, focused on rapid development and
 * usability in a Java environment. Its API encourages people to write tests for AJAX-based web applications in 
 * a concise and maintainable way. Graphene strives for reusable tests by simplifying the use of web page 
 * abstractions (Page Objects and Page Fragments). Even though you don’t have to learn Graphene in order to
 * use WebDriver in Arquillian tests, it is highly recommended. When using Graphene, the most tedious parts of
 * using WebDriver will be brought under control and you’ll seamlessly leverage best practices.
 * 
 * @author SchmittM
 */
@RunAsClient
@RunWith(Arquillian.class)
public class CheckoutUiIT {

    static String pageUrl = "http://%s:%s/payments-app/faces/index.xhtml";

    // Graphene wraps the instance of the browser you have just injected.
    // It instruments the WebDriver API in order to enable more advanced features

    @Drone
    WebDriver browser;

    @FindBy
    WebElement amt;

    @FindBy
    WebElement expiry;

    @FindBy
    WebElement ccy;

    @FindBy
    WebElement linkToCheckout;

    @FindBy
    WebElement checkout;

    @BeforeClass
    public static void init() {
        String host = System.getProperty("resource-host", "localhost");
        String port = System.getProperty("resource-port", "8080");
        pageUrl = String.format(pageUrl, host, port);
    }

    @Test
    public void testCheckout() {
        browser.get(pageUrl);
        assertThat(browser.getTitle(), startsWith("PAYMENTS PORTAL"));

        // graphene: synchronize full-page request
        guardHttp(linkToCheckout).click();

        assertThat(browser.getTitle(), startsWith("PAYMENTS - CHECKOUT"));

        WebElement ccn = browser.findElement(By.id("ccn"));

        ccn.sendKeys("4111");
        amt.sendKeys("1000");
        expiry.sendKeys("1220");
        ccy.sendKeys("EUR");
        guardHttp(checkout).click();

        assertThat(browser.getTitle(), startsWith("TXN CONFIRMED"));
        assertThat(browser.getPageSource(), containsString("Transaction successfully processed"));
    }

}
