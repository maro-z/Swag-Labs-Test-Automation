package org.example.pages.product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.BasePage;
import org.example.pages.checkout.OverviewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage {
    private Logger log = LogManager.getLogger(ProductPage.class);
    private final By title = By.className("title");
    private final By products = By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']");
    private final By cart = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By removes = By.xpath("//button[@class='btn btn_secondary btn_small btn_inventory ']");
    private final By prices = By.className("inventory_item_price");
    private final By priceBars = By.className("pricebar");
    private final By productNames = By.xpath("//div[@class='inventory_item_name ']");
    private final By filter = By.className("product_sort_container");
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getTitle() {
        log.info("Getting page's title");
        return findElement(title);
    }
    public List<WebElement> getProducts(){
        log.info("Getting all the products");
        return findElements(products);
    }
    public WebElement getCart(){
        log.info("Getting cart button");
        return findElement(cart);
    }
    public WebElement getCartBadge(){
        log.info("Getting cart badge");
        return findElement(cartBadge);
    }
    public By getCartBadgeLocator(){
        log.info("Getting the locator of cart's badge");
        return cartBadge;
    }

    public List<WebElement> getRemoves() {
        log.info("Getting remove buttons");
        return findElements(removes);
    }
//    public List<WebElement> getPrices(){
//        return findElements(prices);
//    }
    public List<WebElement> getPriceBars(){
        log.info("Getting prices");
        return findElements(priceBars);
    }
    public List<WebElement> getProductNames(){
        log.info("Getting product's names");
        return findElements(productNames);
    }
    public WebElement getFilter(){
        log.info("Getting filter button");
        return findElement(filter);
    }

    public String getTitleText(){
        log.info("Getting Page's title text");
        return getTitle().getText();
    }
//    public WebElement getOneProduct(){
//        return getProducts().getFirst();
//    }
    public int getCartBadgeNumber(){
        log.info("Getting number of products in cart");
        return Integer.parseInt(getCartBadge().getText());
    }
    public void clickCart(){
        getCart().click();
        log.info("Cart button clicked");
    }
    public void clickOnMultipleProducts(List<String> names){
        log.debug("Adding these {} products to cart",names);
        List<WebElement> products = getProducts();
        for (WebElement e : products) {
            for (String n : names){
                if (e.getAttribute("name").contains(n)){
                    e.click();
                    log.debug("{} added to cart",n);
                    break;
                }
            }
        }
    }
    public void removeProducts(List<String>names){
        log.debug("Removing these {} products from cart",names);
        List<WebElement> removes = getRemoves();
        for (WebElement e : removes) {
            for (String n : names){
                if (e.getAttribute("name").contains(n)){
                    e.click();
                    log.debug("{} removed from cart",n);
                    break;
                }
            }
        }
    }
    public List<String> getProductsPrices(List<String> names){
        log.debug("Getting these {} products prices",names);
        List<WebElement> priceBars =getPriceBars();
        List <String> ans =new ArrayList<>();
        for (WebElement b : priceBars){
            String name = b.findElement(By.xpath("./button")).getAttribute("name");
            String price = b.findElement(By.xpath("./div")).getText();
            for (String n : names){
                if (name.contains(n)){
                    log.debug("Got price for: {}",n);
                    ans.add(price);
                    break;
                }
            }
        }
        return ans;
    }
    public double getProductsPricesTotal(List<String> names){
        log.debug("Getting the total price for these {}",names);
        List<String> prices = getProductsPrices(names);
        double total = 0;
        for (String price:prices){
            log.debug("Adding {} to total",price);
            price = price.replace('$',' ').trim();
            total += Double.parseDouble(price);
        }
        return total;
    }
//    public List<WebElement> getAddedProductsNames(List<String> names){
//        List<WebElement> ans = new ArrayList<>();
//        List<WebElement> products = getProductNames();
//        for (WebElement p: products){
//            for (String n : names){
//                if(p.getText().toLowerCase().contains(n)){
//                    ans.add(p);
//                    break;
//                }
//            }
//        }
//        return ans;
//    }
    public WebElement getProductName(String target){
        log.debug("Getting the clickable product name for: {}",target);
        List<WebElement> products = getProductNames();
        for (WebElement p: products){
            if(p.getText().toLowerCase().contains(target)){
                return p;
            }
        }
        return null;
    }
    public void setFilterAlphabetical(){
        Select filter = new Select(getFilter());
        filter.selectByValue("az");
        log.info("Filter set to alphabetical order");
    }
    public boolean isAlphabeticallyOrdered(){
        log.info("Checking that the products are alphabetically order");
        List<WebElement> names = getProductNames();
        List<String> temp = new ArrayList<>();
        for (WebElement n : names)
            temp.add(n.getText());
        String previous = "";
        for (String current: temp) {
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }

}
