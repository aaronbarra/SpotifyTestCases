package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;

public class SearchPage extends BaseClass {


    public SearchPage(WebDriver driver) {
        super(driver);
    }

    //localizador

    By locatorBtnBuscar = By.xpath("//a[@href='/search']");
    By locatorBarraBusqueda = By.xpath("//input[@data-testid='search-input']");
    By locatorPrimerArtista = By.xpath("//div[@data-testid='herocard-click-handler']");
    By locatorTituloArtista = By.xpath("//div/div/span/h1");
    By locatorBtnReproducir = By.xpath("//div/div/div/div/div/div/button");
    By locatorEstadoReproduccion= By.xpath("//div[@data-testid='action-bar-row']/div/button");

    //accion

    public String obtenerTituloArtista(){
        String tituloArtista = esperarAElementoWeb(locatorTituloArtista).getText();
        return tituloArtista;
    }

    public String obtenerEstadoReproduccion(){
        String estadoReproduccion= esperarAElementoWeb(locatorEstadoReproduccion).getAttribute("aria-label");
        return estadoReproduccion;
    }

    public void realizarBusquedaCancionArtista(String buscar){
        esperarXSegundos(5000);
        click(esperarAElementoWeb(locatorBtnBuscar));
        esperarXSegundos(2000);
        agregarTexto(locatorBarraBusqueda,buscar);
        esperarXSegundos(3000);
    }

    public void seleccionarPrimerArtistaBusqueda(){
        esperarXSegundos(2000);
        click(locatorPrimerArtista);
        esperarXSegundos(2000);
    }

    public void reproducirMusica(String artista){
        esperarXSegundos(2000);
        realizarBusquedaCancionArtista(artista);
        seleccionarPrimerArtistaBusqueda();
        click(esperarAElementoWeb(locatorBtnReproducir));
        esperarXSegundos(2000);


    }





}
