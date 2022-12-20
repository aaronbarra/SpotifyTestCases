package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.concurrent.TimeUnit;

public class CasosDePruebaObsoletos {
    //Atributos
    private WebDriver driver;
    private WebDriverWait wait;

    private JavascriptExecutor js;

    private final String rutaDriver= System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe";
    private final String propertyDriver = "webdriver.chrome.driver";

   @AfterMethod
    public void posCondicion(){
        driver.close();
    }


    @BeforeMethod
    public void preCondiciones(){

        System.setProperty(propertyDriver,rutaDriver);

        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver,10);

        js = (JavascriptExecutor) driver;

        driver.navigate().to("https://open.spotify.com/");

        driver.manage().window().maximize();
    }

    @Test
    public void CP001_Registro_Fallido_Captcha_en_blanco() {

        By localizadorBtnRegistrase = By.xpath("//button[contains(text(),'Registrarte')]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrase);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("domingo.saavedra@tsoftglobal.com");

        driver.findElement(By.name("confirm")).sendKeys("domingo.saavedra@tsoftglobal.com");

        driver.findElement(By.name("password")).sendKeys("123454321");

        driver.findElement(By.name("displayname")).sendKeys("Pobre Domingo :D");

        driver.findElement(By.id("day")).sendKeys("28");

        Select cmbMes = new Select(driver.findElement(By.id("month")));

        cmbMes.selectByValue("02");

        driver.findElement(By.name("year")).sendKeys("1991");


        driver.findElement(By.xpath("//label[@for='gender_option_male']")).click();

        driver.findElement(By.xpath("//label[@for='marketing-opt-checkbox']")).click();


        driver.findElement(By.xpath("//label[@for='third-party-checkbox']")).click();


        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Confirma que no eres un robot.')]")).getText(),"Confirma que no eres un robot.");

    }

    @Test
    public void CP002_Registro_Fallido_Captcha_en_blanco() {

        By localizadorBtnRegistrase = By.xpath("//button[contains(text(),'Registrarte')]");

        WebElement btnRegistrarse = driver.findElement(localizadorBtnRegistrase);

        btnRegistrarse.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).sendKeys("domingo.saavedra@tsoftglobal.com");

        driver.findElement(By.name("confirm")).sendKeys("domingo.saedra@tsoftglobal.com");

        driver.findElement(By.name("password")).sendKeys("123454321");

        driver.findElement(By.name("displayname")).sendKeys("Pobre Domingo :D");

        driver.findElement(By.id("day")).sendKeys("28");

        Select cmbMes = new Select(driver.findElement(By.id("month")));

        cmbMes.selectByValue("02");


        driver.findElement(By.name("year")).sendKeys("1991");

        WebElement optionMale = driver.findElement(By.xpath("//label[@for='gender_option_male']"));

        js.executeScript("arguments[0].scrollIntoView();", optionMale);


        optionMale.click();

        driver.findElement(By.xpath("//label[@for='marketing-opt-checkbox']")).click();


        driver.findElement(By.xpath("//label[@for='third-party-checkbox']")).click();

        WebElement btnRegistro  = driver.findElement(By.xpath("//button[@type='submit']"));

        js.executeScript("arguments[0].scrollIntoView();", btnRegistro);

        btnRegistro.click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Las direcciones de correo')]")).getText(),"Las direcciones de correo electrónico no coinciden.");
    }
    //Metodo para buscar cancion o artista.
    public void Buscar_Artista_Cancion(String artista){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/search']"))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath( "//input[@data-testid='search-input']"))).sendKeys(artista);

    }
    //Metodo para iniciar sesion.
    public void Iniciar_Sesion() {
        WebElement btnIniciarSesion =  driver.findElement(By.xpath("//button[@data-testid='login-button']"));

        btnIniciarSesion.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-username"))).sendKeys("aaron.araya94@gmail.com");

        WebElement btnLogin = driver.findElement(By.xpath("//button[@id='login-button']"));

        js.executeScript("arguments[0].scrollIntoView();", btnLogin);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-password"))).sendKeys("karina3008");

        driver.findElement(By.id("login-button")).click();

    }


    @Test
    public void CP003_Buscar_Artista_Seleccionar_Primer_Resultado() throws InterruptedException {

        Iniciar_Sesion();

        driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);

        Buscar_Artista_Cancion("Reggaeton");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-testid='herocard-click-handler']"))).click();

        Thread.sleep(2000);

        driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);

        Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(),'REGGAETON OLD SCHOOL TOP 300!')]")).getText(),"REGGAETON OLD SCHOOL TOP 300!");

    }

    @Test
    public void CP004_Ver_Perfil() throws InterruptedException {

        Iniciar_Sesion();

        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-testid='user-widget-link']"))).click();

        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/ul/li[2]/a"))).click();

        //driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);

        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(),'Aaron Isaac Barra Araya')]")).getText(),"Aaron Isaac Barra Araya");

    }

    @Test
    public void CP005_Reproducir_Musica() throws InterruptedException {

        Iniciar_Sesion();

        Buscar_Artista_Cancion("coldplay");

        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-testid='herocard-click-handler']"))).click();

        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div/div/div/div/button"))).click();

        Thread.sleep(10000);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@data-testid='action-bar-row']/div/button[@aria-label='Pausa']")).getAttribute("aria-label"),"Pausa");

    }

    @Test
    public void CP006_Ver_Configuracion_Cuenta() throws InterruptedException {

        Iniciar_Sesion();

        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-testid='user-widget-link']"))).click();

        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/ul/li[4]/a"))).click();

        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(),'Configuración')]")).getText(),"Configuración");

    }

    @Test
    public void CP007_Cerrar_Sesion() throws InterruptedException {

        Iniciar_Sesion();

        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-testid='user-widget-link']"))).click();

        driver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/ul/li[5]/button"))).click();

        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Iniciar sesión')]")).getText(),"Iniciar sesión");



    }



}
