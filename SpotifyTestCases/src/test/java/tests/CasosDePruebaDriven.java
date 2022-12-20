package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import utils.DataDriven;
import utils.PropertiesDriven;

import java.util.ArrayList;

public class CasosDePruebaDriven {
    //Atributos
    private HomePage homePage;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private SearchPage searchPage;
    private UserPage userPage;
    private WebDriver driver;
    private final String browser = PropertiesDriven.getProperty("browser"); //Este valor eventualmente se vera modificado
    private final String propertyDriver = PropertiesDriven.getProperty("propertyDriver");
    private final String urlDriver = PropertiesDriven.getProperty("urlDriver");
    private final String url = PropertiesDriven.getProperty("url");
    private ArrayList<String> data; // null
    private ArrayList<String> data2;
    private String prueba;

    @BeforeMethod
    public void preparacionTests(){
        data= new ArrayList<String>(); //Array de tamaño 0
        data2= new ArrayList<String>(); //Segundo Array para evitar conflictos con data
        homePage = new HomePage(driver); //Se crea la page del home
        homePage.conexionBrowser(browser,propertyDriver,urlDriver); //Se conecta el driver de chrome
        registerPage = new RegisterPage(homePage.getDriver()); //se crea la page de registro
        loginPage= new LoginPage(homePage.getDriver());
        searchPage= new SearchPage(homePage.getDriver());
        userPage= new UserPage(homePage.getDriver());
        homePage.cargarPagina(url);
        homePage.maximizarBrowser();
    }

    public void Iniciar_Sesion(){
        data = DataDriven.getData(PropertiesDriven.getProperty("MetIniSes"));
        homePage.irAIniciarSesion();
        loginPage.completarFormularioIniciarSesion(data.get(1), data.get(2));

    }

    @Test
    public void CP001_Registro_Fallido_Captcha_en_blanco(){
        data = DataDriven.getData(PropertiesDriven.getProperty("CP001"));
        homePage.irARegistrarte();
        //registerPage.completarFormularioRegistro("domingo.saavedra@tsoftglobal.com", "domingo.saavedra@tsoftglobal.com", "123454321", "Pobre Domingo", "31", "Abril", "1990");
        registerPage.completarFormularioRegistro
                (data.get(1), data.get(2),data.get(3), data.get(4), data.get(5), data.get(6), data.get(7));
        //Assert.assertEquals("Confirma que no eres un robot.",registerPage.obtenerErrorCaptchaVacio());
        Assert.assertEquals(data.get(8),registerPage.obtenerErrorCaptchaVacio());
    }

    @Test
    public void CP002_Registro_Fallido_Password_Corta(){
        data = DataDriven.getData(PropertiesDriven.getProperty("CP002"));
        homePage.irARegistrarte();
        //registerPage.completarFormularioRegistro("", "", "1", "", "", "Mayo", "");
        registerPage.completarFormularioRegistro(data.get(1), data.get(2),data.get(3), data.get(4), data.get(5), data.get(6), data.get(7));
        //Assert.assertEquals("Tu contraseña es demasiado corta.",registerPage.obtenerErrorPasswordCorta());
        Assert.assertEquals(data.get(8),registerPage.obtenerErrorPasswordCorta());
    }

    @AfterMethod
    public void posTests(){
        registerPage.cerrarBrowser();
    }

    @Test
    public void CP003_Buscar_Artista_Seleccionar_Primer_Resultado(){
        data2 = DataDriven.getData(PropertiesDriven.getProperty("CP003"));
        Iniciar_Sesion();
        searchPage.realizarBusquedaCancionArtista(data2.get(1));
        searchPage.seleccionarPrimerArtistaBusqueda();
        Assert.assertEquals(data2.get(8),searchPage.obtenerTituloArtista());

    }

    @Test
    public void CP004_Ver_Perfil(){
        data2= DataDriven.getData(PropertiesDriven.getProperty("CP004"));
        Iniciar_Sesion();
        homePage.verPerfil();
        Assert.assertEquals(data2.get(8),userPage.nombreUsuario());
    }

    @Test
    public void CP005_Reproducir_Musica(){
        data2 = DataDriven.getData(PropertiesDriven.getProperty("CP005"));
        Iniciar_Sesion();
        searchPage.reproducirMusica(data2.get(1));
        Assert.assertEquals(data2.get(8), searchPage.obtenerEstadoReproduccion());

    }

    @Test
    public void CP006_Ver_Configuracion_Cuenta(){
        data2 = DataDriven.getData(PropertiesDriven.getProperty("CP006"));
        Iniciar_Sesion();
        homePage.verConfiguracion();
        Assert.assertEquals(data2.get(8),userPage.tituloConfiguracion());
    }

    @Test
    public void CP007_Cerrar_Sesion() {
        data2 = DataDriven.getData(PropertiesDriven.getProperty("CP007"));
        Iniciar_Sesion();
        homePage.cerrarSesion();
        Assert.assertEquals(data2.get(8), homePage.IniciarSesionTexto());

    }







}