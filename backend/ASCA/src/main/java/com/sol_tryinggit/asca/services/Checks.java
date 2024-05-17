package com.sol_tryinggit.asca.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;

@Service
public class Checks {

    public void check_1(WebDriver driver, String filePath) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("_weave_e_10")));
        driver.findElement(By.id("_weave_e_10")).sendKeys("Microsoft Entra ID");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("Microsoft_AAD_IAM_AzureActiveDirectory")));
        driver.findElement(By.id("Microsoft_AAD_IAM_AzureActiveDirectory")).click();
        driver.switchTo().frame(0);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='Properties']")));
        driver.findElement(By.cssSelector("button[name='Properties']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Manage security defaults")));
        driver.findElement(By.linkText("Manage security defaults")).click();
        driver.switchTo().defaultContent();
//        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("form-label-id-7textbox")));
        String securityDefaults = driver.findElement(By.id("form-label-id-7textbox")).getText();
//        driver.findElement(By.id("_weave_e_5")).click();
        driver.findElement(By.id("_weave_e_729")).click();
        try{
            FileWriter writer = new FileWriter(filePath,true);
            writer.write("Check number: 1.1.1\nName: Ensure Security Defaults is enabled on Azure Active Directory\n");
            writer.write("Description: The goal is to ensure that all organizations have a basic level of security enabled at no extra cost.\n");
            writer.write("Host: https://portal.azure.com\nGuideline: https://www.cisecurity.org/benchmark/azure\n");
            if(securityDefaults.equals("Enabled (recommended)")){
                writer.write("Result: Pass\n\n");
            }else{
                writer.write("Result: Fail\n");
                writer.write("Remediation: \nFrom Azure Portal\nTo enable security defaults in your directory:\n" +
                        "1. From Azure Home select the Portal Menu.\n2. Browse to Azure Active Directory > Properties\n" +
                        "3. Select Manage security defaults\n4. Set the Enable security defaults toggle to Yes\n" +
                        "5. Select Save\n\n");
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void check_2(WebDriver driver, String filePath) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("_weave_e_10")));
        driver.findElement(By.id("_weave_e_10")).sendKeys("Microsoft Entra ID");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("Microsoft_AAD_IAM_AzureActiveDirectory")));
        driver.findElement(By.id("Microsoft_AAD_IAM_AzureActiveDirectory")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Manage']")));
        driver.findElement(By.xpath("//div[text()='Manage']")).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Users']")));
        driver.findElement(By.xpath("//div[text()='Users']")).click();
        driver.switchTo().frame("_react_frame_1");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Per-user MFA']")));
        driver.findElement(By.xpath("//span[text()='Per-user MFA']")).click();
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> itr = windowHandles.iterator();
        String parentID = itr.next();
        String childID = itr.next();
        driver.switchTo().window(childID);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td:nth-child(7)")));
        String securityDefaults = driver.findElement(By.cssSelector("td:nth-child(7)")).getText();
        System.out.println(securityDefaults);
        driver.close();
        driver.switchTo().window(parentID);
        try{
            FileWriter writer = new FileWriter(filePath,true);
            writer.write("Check number: 1.1.3\nName: Ensure that 'Multi-Factor Auth Status' is 'Enabled' for all Non-Privileged Users\n");
            writer.write("Description: Enable multi-factor authentication for all non-privileged users.\n");
            writer.write("Host: https://portal.azure.com\nGuideline: https://www.cisecurity.org/benchmark/azure\n");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write("Date and Time of Audit: "+dtf.format(now)+"\n");
            if(securityDefaults.equals("Enabled")){
                writer.write("Result: Pass\n\n");
            }else{
                writer.write("Result: Fail\n");
                writer.write("""
                        Remediation:\s
                        Follow Microsoft Azure documentation and enable multi-factor authentication in your environment.
                        https://docs.microsoft.com/en-us/azure/active-directory/authentication/tutorial-enable\\u0002azure-mf

                        """);
            }
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
