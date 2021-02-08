package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    @Test
    public void testPrincipal() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mrnna\\OneDrive\\Documentos\\Jobs\\QA - Webmotors\\chromedriver.exe"); //indica onde procurar o executável do chrome driver
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //tempo de espera antes de falhar um teste

        driver.get("http://www.webmotors.com.br"); //iniciar chrome driver no link indicado
        String marcaPesquisa = "honda"; //indicar a marca que deseja filtrar
        String modeloPesquisa = "CITY"; //indicar o modelo que deseja filtrar
        String versaoPesquisa = "1.5 DX 16V FLEX 4P AUTOMÁTICO"; //indicar a versão que deseja filtrar


        driver.findElement(By.cssSelector(".Button--red-home")).click(); //clicar em ver ofertas
        driver.findElement(By.xpath("//a[contains(@href, 'https://www.webmotors.com.br/carros/estoque/"+ marcaPesquisa +"')]")).click(); //selecionar a marca indicada
        driver.findElement(By.cssSelector(".Filters__line--gray")).click(); //clicar em ver todos os modelos
        driver.findElement(By.xpath("//a[contains(text(),'"+ modeloPesquisa +"')]")).click(); //selecionar o modelo indicado
        driver.findElement(By.cssSelector(".Filters__line--gray")).click(); //clicar em ver todas as versões
        driver.findElement(By.xpath("//a[contains(text(),'"+ versaoPesquisa +"')]")).click(); //selecionar a versão indicada

        //conferir se o resultado da busca é apresentada corretamente
        String modelo = "HONDA CITY"; //indicar o modelo e marca que deseja obter como resultado da busca
        String versao = "1.5 DX 16V FLEX 4P AUTOMÁTICO"; //indicar a versão que deseja obter como resultado da busca


        Boolean testeModelo = driver.findElements(By.xpath("//h2[contains(.,'"+ modelo +"')]")).size() > 0; //verificando se a marca e o modelo foram apresentados corretamente
        Boolean testeVersao = driver.findElements(By.xpath("//h3[contains(.,'"+ versao +"')]")).size() > 0; //verificando se a versão foi apresentada corretamente

        if(testeModelo == true && testeVersao == true) {
            System.out.println("Sucesso"); //será apresentada a mensagem de "Sucesso" caso a pesquisa (modelo, marca e versão) tenha sido concluída com sucesso
        } else {
            System.out.println("Erro"); //será apresentada a mensagem de "Erro" caso a pesquisa (modelo, marca e versão) NÃO tenha sido concluída com sucesso.
            //neste caso, o resultado da busca é diferente do esperado (filtrei pela Honda e foram retornados resultados da Fiat, por exemplo)
        }


        driver.close(); //encerra o navegador
    }
}
