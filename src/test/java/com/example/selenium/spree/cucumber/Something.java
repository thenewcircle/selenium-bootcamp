package com.example.selenium.spree.cucumber;

import cucumber.api.PendingException;
import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.firefox.FirefoxDriver;

        import cucumber.api.java.After;
        import cucumber.api.java.Before;
        import cucumber.api.java.en.Given;
        import cucumber.api.java.en.Then;
        import cucumber.api.java.en.When;

public class Something {

    private WebDriver browser;

    @Before
    public void initialize() {
//        browser = new FirefoxDriver();
    }

    @After
    public void cleanup() {
//        browser.quit();
    }

    @Given("^I am viewing rails items$")
    public void i_am_viewing_rails_items() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^Items are \\$(\\d+)\\.(\\d+) or over$")
    public void items_are_$_or_over(int arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I choose \"([^\"]*)\"$")
    public void i_choose(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I add it to the cart$")
    public void i_add_it_to_the_cart() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^\"([^\"]*)\" is in my shopping cart$")
    public void is_in_my_shopping_cart(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^Total amount in the cart is \\$(\\d+)\\.(\\d+)$")
    public void total_amount_in_the_cart_is_$(int arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^I am viewing ruby items$")
    public void i_am_viewing_ruby_items() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}