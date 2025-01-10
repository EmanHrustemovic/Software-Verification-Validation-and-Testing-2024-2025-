import org.openqa.selenium.WebDriver;

public class BigBang {

    WebDriver webDriver;

    public BigBang(WebDriver webDriver){
        this.webDriver=webDriver;

    }

    public void openHomePage(){
        webDriver.get("https://www.bigbang.ba/");
    }

}
