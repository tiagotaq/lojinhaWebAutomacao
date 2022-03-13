package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeAdicaoDeProdutoPage {
    private WebDriver navegador;

    public FormularioDeAdicaoDeProdutoPage(WebDriver navegador){
        this.navegador = navegador;
    }

    public FormularioDeAdicaoDeProdutoPage preencherNomeProduto(String nomeProduto){
        navegador.findElement(By.id("produtonome")).sendKeys(nomeProduto);
        return this;
    }

    public FormularioDeAdicaoDeProdutoPage preencherValorProduto(String valorProduto){
        navegador.findElement(By.name("produtovalor")).sendKeys(valorProduto);
        return this;
    }

    public FormularioDeAdicaoDeProdutoPage preencherCoresProduto(String cores){
        navegador.findElement(By.id("produtocores")).sendKeys(cores);
        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeAdicionarComErro(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new ListaDeProdutosPage(navegador);
    }

    public FormularioDeAdicaoDeProdutoPage submeterFormularioDeAdicaoComSucesso(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return this;
    }

    public String capturarMensagemApresentada(){
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }
}
