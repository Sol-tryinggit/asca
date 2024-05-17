package com.sol_tryinggit.asca.services;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.util.Properties;

@Service
public class BasicFunctions {

    public void login(WebDriver driver, String email, String password){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));


            driver.get("https://portal.azure.com/#home");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0116")));
            driver.findElement(By.id("i0116")).sendKeys(email);
            driver.findElement(By.id("idSIButton9")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("i0118")));
            driver.findElement(By.id("i0118")).sendKeys(password);
            driver.findElement(By.id("idSIButton9")).click();
            driver.findElement(By.id("declineButton")).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String createNewFile(){
        try{
            //C:\BCA Files\SEM 6\FY Project\Code files\Audit Report.txt
            File obj = new File("C:\\BCA Files\\SEM 6\\FY Project\\Code files\\Audit Report.txt");
            if(obj.createNewFile()){}else{
                Path filePath = Path.of("C:\\BCA Files\\SEM 6\\FY Project\\Code files\\Audit Report.txt");
                Files.newBufferedWriter(filePath, StandardOpenOption.TRUNCATE_EXISTING);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return "C:\\BCA Files\\SEM 6\\FY Project\\Code files\\Audit Report.txt";
    }

    public void logout(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.cssSelector("div[class='fxs-avatarmenu-header']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mectrl_body_signOut")));
        driver.findElement(By.id("mectrl_body_signOut")).click();
    }
}
