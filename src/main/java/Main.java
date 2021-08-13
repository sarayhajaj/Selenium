import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Ashkelon collage website");
        Scanner scanner = new Scanner(System.in);
        String userName;
        String password;
        do {
            System.out.println("Enter your user name:");
            userName = scanner.next();
            if (userName.length() <= 2)
                System.out.println("Invalid number please try again!");
        } while (userName.length() <= 2);
        do {
            System.out.println("Enter your Password:");
            password = scanner.next();
            if (password.length() <= 3)
                System.out.println("Invalid password please try again!");
        } while (password.length() <= 3);
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\edenb\\Downloads\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.aac.ac.il/");
        WebElement menu = driver.findElement(By.id("menu-%d7%9b%d7%9c%d7%9c%d7%99"));
        if (menu != null) {
            List<WebElement> menuItems = menu.findElements(By.tagName("li"));
            WebElement personalInfo = null;
            for (int i = 0; i <= menuItems.size(); i++) {
                WebElement menuItem = menuItems.get(i);
                if (menuItem.getText().contains("מידע אישי")) {
                    personalInfo = menuItem;
                    break;
                }
            }
            personalInfo.click();
        }
        WebElement usernameInput = driver.findElement(By.id("Ecom_User_ID"));
        if (usernameInput != null) {
            usernameInput.sendKeys(userName);

        }
        WebElement passwordInput = driver.findElement(By.id("Ecom_Password"));
        if (usernameInput != null) {
            passwordInput.sendKeys(password);

        }
        WebElement enter = driver.findElement(By.id("wp-submit"));
        if (enter != null) {
            enter.click();
        }
        List<WebElement> moodle = driver.findElements(By.className("col-sm-6"));
        for (WebElement webElement : moodle) {
            if (webElement.getText().equals("מערכת Moodle")) {
                webElement.click();
                break;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> courseList = driver.findElements(By.className("multiline"));
        if (courseList != null) {
            System.out.println("Your course list: ");
            int userInput = 0;
            do {
                for (int i = 0; i < courseList.size(); i++) {
                    System.out.println("Course number " + i + ": " + courseList.get(i).getText());
                    System.out.println(" ");
                }
                System.out.println("which course do you choose? (enter a number between 0-22)");
                userInput = scanner.nextInt();
                if (userInput < 0 || userInput > 22) {
                    System.out.println("Invalid number please try again:");
                }
            } while (userInput < 0 || userInput > 22);
            WebElement choice = courseList.get(userInput);
            choice.click();
        }
        WebElement menuAction = driver.findElement(By.className("usertext"));
        if (menuAction != null) {
            menuAction.click();
        }
        WebElement exitMoodle= driver.findElement(By.id("actionmenuaction-6"));
        if (exitMoodle != null) {
            exitMoodle.click();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> logOut = driver.findElements(By.tagName("li"));
        for (int i = 0; i < logOut.size(); i++) {
            if (logOut.get(i).getText().equals("יציאה")) {
                logOut.get(i).click();
                break;
            }
        }
    }
}






