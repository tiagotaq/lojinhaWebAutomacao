package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do módulo de Produtos")
public class ProdutosTest {
    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        //abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\WebDrivers\\ChromeDriver99\\chromedriver.exe");
        navegador = new ChromeDriver();

        //Vou maximizar a tela
        navegador.manage().window().maximize();

        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Navegar para a página da lojinha web
        navegador.get("http://165.227.93.41/lojinha-web-bugada/v2/");
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void NaoEPermitidoRegistrarProdutoComValorIgualAZero() {

        String mensagemApresentada = new LoginPage(navegador)
                .preencherUsuario("admin")
                .preencherSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeProduto()
                .preencherNomeProduto("Play Station sl")
                .preencherValorProduto("000")
                .preencherCoresProduto("preto, branco")
                .submeterFormularioDeAdicionarComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor maior que 7000")
    public void NaoEPermitidoRegistrarProdutoComValorMaiorQue7000(){
        String mensagemApresentada = new LoginPage(navegador)
                .preencherUsuario("admin")
                .preencherSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeProduto()
                .preencherNomeProduto("Play Station sl")
                .preencherValorProduto("700001")
                .preencherCoresProduto("preto, branco")
                .submeterFormularioDeAdicionarComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Adicionar produto com valor de 1 centavo")
    public void adicionarProdutoComValorDe1Centavo(){
        String mensagemApresentada = new LoginPage(navegador)
                .preencherUsuario("admin")
                .preencherSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeProduto()
                .preencherNomeProduto("Play Station sl vl ok")
                .preencherValorProduto("001")
                .preencherCoresProduto("azul, vermelho")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Adicionar produto com valor de 7000")
    public void adicionarProdutoComValorDe7000(){
        String mensagemApresentada = new LoginPage(navegador)
                .preencherUsuario("admin")
                .preencherSenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeProduto()
                .preencherNomeProduto("Play Station sl vl ok")
                .preencherValorProduto("700000")
                .preencherCoresProduto("azul, vermelho")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @AfterEach
    public void afterEach(){
        //fechar o navegador
        navegador.quit();
    }
}
