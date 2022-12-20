package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;

public class LoginPage extends BaseClass {

    public LoginPage(WebDriver driver) {
        super(driver);

    }
    // Localizadores

    By locatorTxtUsuario = By.xpath("//input[@id='login-username']");
    By locatorTxrContrasena= By.xpath("//input[@id='login-password']");
    By locatorBtnLogin= By.xpath("//button[@id= 'login-button']");




    //Accion

    public void completarFormularioIniciarSesion(String correo, String contrasena){
        esperarXSegundos(3000);
        agregarTexto((locatorTxtUsuario),correo);
        agregarTexto(esperarAElementoWeb(locatorTxrContrasena),contrasena);
        esperarXSegundos(1000);
        ScrollElementoWeb(esperarAElementoWeb(locatorBtnLogin));
        esperarXSegundos(1000);
        click(esperarAElementoWeb(locatorBtnLogin));

    }






}
