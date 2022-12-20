package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;

public class UserPage extends BaseClass {

    public UserPage(WebDriver driver) {
        super(driver);
    }

    //Localizador

    By locatorNombreUsuario= By.xpath("//span/h1");
    By locatorTituloConfiguracion= By.xpath("//div/h1");


    //Acciones

    public String nombreUsuario(){
        return esperarAElementoWeb(locatorNombreUsuario).getText();
    }
    public String tituloConfiguracion(){
        return esperarAElementoWeb(locatorTituloConfiguracion).getText();
    }
}
