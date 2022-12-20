package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;

public class HomePage extends BaseClass {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //las pages tienen por objetivo centralizar los localizador y acciones de pagina

    // Locators
    By locatorBtnRegistrarte = By.xpath("//button[contains(text(),'Registrarte')]");
    By locatorBtnIniciarSesion= By.xpath("//button[@data-testid='login-button']");
    By locatorBtnSeleccionarUsuario= By.xpath("//button[@data-testid='user-widget-link']");
    By locatorBtnVerPerfil = By.xpath("//span[contains(text(),'Perfil')]");
    By locatorBtnVerConfiguracion = By.xpath("//span[contains(text(),'Configuración')]");
    By locatorBtnCerrarSesion= By.xpath("//span[contains(text(),'Cerrar sesión')]");
    By getLocatorBtnIniciarSesionText= By.xpath("//span[contains(text(),'Iniciar sesión')]");





    //Acciones
    public String IniciarSesionTexto(){
        return esperarAElementoWeb(getLocatorBtnIniciarSesionText).getText();
    }

    public void irARegistrarte(){
        click(esperarAElementoWeb(locatorBtnRegistrarte));
    }

    public void irAIniciarSesion(){
        click(esperarAElementoWeb(locatorBtnIniciarSesion));
    }

    public void verPerfil(){
        esperarXSegundos(3000);
        click(esperarAElementoWeb(locatorBtnSeleccionarUsuario));
        esperarXSegundos(2000);
        click(esperarAElementoWeb(locatorBtnVerPerfil));
    }

    public void verConfiguracion(){
        esperarXSegundos(3000);
        click(esperarAElementoWeb(locatorBtnSeleccionarUsuario));
        esperarXSegundos(2000);
        click(esperarAElementoWeb(locatorBtnVerConfiguracion));

    }

    public void cerrarSesion(){
        esperarXSegundos(3000);
        click(esperarAElementoWeb(locatorBtnSeleccionarUsuario));
        esperarXSegundos(3000);
        click(esperarAElementoWeb(locatorBtnCerrarSesion));


    }



}