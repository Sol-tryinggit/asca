package com.sol_tryinggit.asca.services;


import com.sol_tryinggit.asca.dto.AccountDetails;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ASCA_services {

    @Autowired
    private BasicFunctions basicFunctions;
    @Autowired
    private Checks checksFunctions;


    public String startAzureAudit(AccountDetails accountDetails) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        basicFunctions.login(driver,accountDetails.getUsername(), accountDetails.getPassword());
        String filePath = basicFunctions.createNewFile();
//        checksFunctions.check_1(driver,filePath);
        checksFunctions.check_2(driver,filePath);
        basicFunctions.logout(driver);
        driver.quit();

        return filePath;
    }
}
