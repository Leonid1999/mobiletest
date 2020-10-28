package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.BasePage;

public class Message extends Base{

    String message1 = "Hello! How are you?";
    String message2 = "Hello! I`m fine. Thank you!";

    String phoneNumber1 = "380679833048";
    String phoneNumber2 = "380970983572";



    @Test(priority = 1)
    public void createPrivateChat(){
        messagePage1.createChatWith(phoneNumber1);
        messagePage2.createChatWith(phoneNumber2);
    }

    @Test(priority = 2)
    public void sendMessage(){
        messagePage1.sendMessageTo(message1);
        String messageSender = messagePage1.getSendMessage();
        messagePage1.isElementDisplayed(By.id("com.dstarlab.icommunicator:id/read_indicator"));
        String messageRecipient = messagePage2.getSendMessage();
        Assert.assertEquals(messageSender, messageRecipient);

        messagePage2.sendMessageTo(message2);
        messageSender = messagePage2.getSendMessage();
        messagePage2.isElementDisplayed(By.id("com.dstarlab.icommunicator:id/read_indicator"));
        messageRecipient = messagePage1.getSendMessage();
        Assert.assertEquals(messageSender, messageRecipient);
    }
}
