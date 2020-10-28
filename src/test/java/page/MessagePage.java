package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class MessagePage extends BasePage{

    String xpathLastMessage= "//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[last()]";

    public MessagePage(WebDriver driver) {
        super(driver);
    }
    public void createChatWith(String phoneNumber){
        click(By.xpath("//android.widget.TextView[@text=\"PRIVATE\"]"));
        click(By.id("com.dstarlab.icommunicator:id/fab"));

        sendKey(By.id("com.dstarlab.icommunicator:id/search_view"), phoneNumber);

        waitVisibility(By.id("com.dstarlab.icommunicator:id/recycler_view"));
        driver.findElement(By.id("com.dstarlab.icommunicator:id/recycler_view")).findElement(By.xpath("//android.widget.LinearLayout[1]")).click();

        //Check chat is created
        isElementDisplayed(By.id("com.dstarlab.icommunicator:id/menu_video_secure"));
        isElementDisplayed(By.id("com.dstarlab.icommunicator:id/menu_call_secure"));
        isElementDisplayed(By.id("com.dstarlab.icommunicator:id/embedded_text_editor"));
    }

    public void sendMessageTo(String message){
        sendKey(By.id("com.dstarlab.icommunicator:id/embedded_text_editor"), message);
        click(By.id("com.dstarlab.icommunicator:id/send_button"));
    }

    public String getSendMessage(){
        waitVisibility(By.xpath(xpathLastMessage));
        WebElement lastMessageSender = driver.findElement(By.xpath(xpathLastMessage)).findElement(By.id("com.dstarlab.icommunicator:id/conversation_item_body"));
        String messageSender= lastMessageSender.getText();
        System.out.println(messageSender);
        Assert.assertTrue(lastMessageSender.isDisplayed());
        return messageSender;
    }
}
