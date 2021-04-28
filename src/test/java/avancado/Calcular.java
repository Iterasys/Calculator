package avancado;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.apache.commons.io.FileUtils;

import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class Calcular{

    private AndroidDriver driver;
    private DesiredCapabilities desiredCapabilities;
    private URL remoteUrl;
    private static String nomePasta = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime());
    private Massa page;
    private MobileElement btnOperacao;

    public Calcular(Massa base) {
        page = base;
    }

    // Funções ou Métodos de Apoio
    public void print(String nomePrint) throws IOException {
        File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        System.out.println("nomePasta:" + nomePasta);
        FileUtils.copyFile(foto, new File("target/" + nomePasta + "/" + nomePrint + ".png"));


    }



    @Before
    public void setUp() throws MalformedURLException {
        /*
        Valores previstos de flag:
        EL = Emulador Local
        EN = Emulador na Nuvem
        DL = Dispositivo Local
        DN = Dispositivo na Nuvem
         */

        String flag = "EN";

        desiredCapabilities = new DesiredCapabilities();

        switch(flag){
            case "EL":
                desiredCapabilities.setCapability("platformName", "Android");
                desiredCapabilities.setCapability("platformVersion", "9.0");
                desiredCapabilities.setCapability("deviceName", "emulator-5554");
                desiredCapabilities.setCapability("automationName", "uiautomator2");
                desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
                desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

                remoteUrl = new URL("https://127.0.0.1:4723/wd/hub");
                break;

            case "EN":
                desiredCapabilities.setCapability("platformName", "Android");
                desiredCapabilities.setCapability("platformVersion", "9.0");
                desiredCapabilities.setCapability("browserName", "");
                desiredCapabilities.setCapability("appiumVersion", "1.19.2");
                desiredCapabilities.setCapability("deviceName", "Samsung Galaxy S9 FHD GoogleAPI Emulator");
                desiredCapabilities.setCapability("deviceOrientation", "portrait");
                desiredCapabilities.setCapability("app", "storage:filename=Calculator_v7.8 (271241277)_apkpure.com.apk");
                desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
                desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
                desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
                desiredCapabilities.setCapability("SAUCE_USERNAME", "dh03iterasys");
                desiredCapabilities.setCapability("SAUCE_ACCESS_KEY", "f1893623-6408-4849-89aa-e4deab56f910");

                remoteUrl = new URL("https://dh03iterasys:f1893623-6408-4849-89aa-e4deab56f910@ondemand.us-west-1.saucelabs.com:443/wd/hub");
                break;

            case "DL":
                //TODO: a programar
                break;

            case "DN":
                //TODO: a programar
                break;
        }


    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Given("^abro a calculadora do Google no meu smartphone$")
    public void abroACalculadoraDoGoogleNoMeuSmartphone() {
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

    }

    @When("^seleciono \"([^\"]*)\" mais \"([^\"]*)\" e pressiono o botao Igual$")
    public void selecionoMaisEPressionoOBotaoIgual(String num1, String num2) throws IOException {
        print("Somar Dois Numeros Positivos - Passo 1 - Abriu a Calculadora");
        MobileElement btnA = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_" + num1);
        btnA.click();
        print("Somar Dois Numeros Positivos - Passo 2 - Clicou no botão " + num1);
        MobileElement btnSoma = (MobileElement) driver.findElementByAccessibilityId("plus");
        btnSoma.click();
        print("Somar Dois Numeros Positivos - Passo 3 - Clicou no botão de Soma");
        MobileElement btnB = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_" + num2);
        btnB.click();
        print("Somar Dois Numeros Positivos - Passo 4 - Clicou no botão " + num2);
        MobileElement btnIgual = (MobileElement) driver.findElementByAccessibilityId("equals");
        btnIgual.click();
        print("Somar Dois Numeros Positivos - Passo 5 - Clicou no botão Igual");
    }

    @Then("^exibe o resultado como \"([^\"]*)\"$")
    public void exibeOResultadoComo(String resultadoEsperado)  {
        MobileElement lblResultadoAtual = (MobileElement) driver.findElementById("com.google.android.calculator:id/result_final");
        assertEquals(resultadoEsperado, lblResultadoAtual.getText());
    }

    // Given com massa
    @Given("^que utilizo a massa \"([^\"]*)\" para testar a calculadora$")
    public void queUtilizoAMassaParaTestarACalculadora(String nomeMassa) throws MalformedURLException {
        // Configurações para execução do emulador na nuvem
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9.0");
        desiredCapabilities.setCapability("browserName", "");
        desiredCapabilities.setCapability("appiumVersion", "1.19.2");
        desiredCapabilities.setCapability("deviceName", page.deviceName);
        desiredCapabilities.setCapability("deviceOrientation", "portrait");
        desiredCapabilities.setCapability("app", "storage:filename=Calculator_v7.8 (271241277)_apkpure.com.apk");
        desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("SAUCE_USERNAME", "dh03iterasys");
        desiredCapabilities.setCapability("SAUCE_ACCESS_KEY", "f1893623-6408-4849-89aa-e4deab56f910");

        remoteUrl = new URL("https://dh03iterasys:f1893623-6408-4849-89aa-e4deab56f910@ondemand.us-west-1.saucelabs.com:443/wd/hub");

        // Abrir o app
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

    }

    @When("^realizo a operacao com dois numeros$")
    public void realizoAOperacaoComDoisNumeros() throws IOException {
        
        print("Somar Dois Numeros Positivos - Passo 1 - Abriu a Calculadora");
        MobileElement btnA = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_" + page.num1);
        btnA.click();
        print("Somar Dois Numeros Positivos - Passo 2 - Clicou no botão " + page.num1);

        // Selecionar a operação matemática
        // Exemplo com um botão para cada um das 4 operações
        /*
        switch(massa.operador){
            case "+":
                MobileElement btnSoma = (MobileElement) driver.findElementByAccessibilityId("plus");
                btnSoma.click();
                break;
            case "-":
                MobileElement btnSubtrair = (MobileElement) driver.findElementByAccessibilityId("minus");
                btnSubtrair.click();
                break;
            case "*":
                MobileElement btnMultiplicar = (MobileElement) driver.findElementByAccessibilityId("multiply");
                btnMultiplicar.click();
                break;
            case "/":
                MobileElement btnDividir = (MobileElement) driver.findElementByAccessibilityId("divide");
                btnDividir.click();
                break;
        }
*/
        // Exemplo resumido - lê o simbolo e transforma na operação
        switch(page.operador){
            case "+":
                btnOperacao = (MobileElement) driver.findElementByAccessibilityId("plus");
                break;
            case "-":
                btnOperacao = (MobileElement) driver.findElementByAccessibilityId("minus");
                break;
            case "*":
                btnOperacao = (MobileElement) driver.findElementByAccessibilityId("multiply");
                break;
            case "/":
                btnOperacao = (MobileElement) driver.findElementByAccessibilityId("divide");
                break;
        }
        btnOperacao.click();

        // Ficaria muito mais simples se a massa já tivesse a operação ao invés do simbol
       /*
        btnOperacao = (MobileElement) driver.findElementByAccessibilityId(massa.operador);
        btnOperacao.click();
       */

        print("Somar Dois Numeros Positivos - Passo 3 - Clicou no botão de Soma");
        MobileElement btnB = (MobileElement) driver.findElementById("com.google.android.calculator:id/digit_" + page.num2);
        btnB.click();
        print("Somar Dois Numeros Positivos - Passo 4 - Clicou no botão " + page.num2);
        MobileElement btnIgual = (MobileElement) driver.findElementByAccessibilityId("equals");
        btnIgual.click();
        print("Somar Dois Numeros Positivos - Passo 5 - Clicou no botão Igual");


    }

    @Then("^compara o resultado atual com o esperado$")
    public void comparaOResultadoAtualComOEsperado() {
        if(page.operador == "/" && page.num2 == "0"){
            // ToDo: mapear a mensagem de erro da divisao
        }
        else {
            MobileElement lblResultadoAtual = (MobileElement) driver.findElementById("com.google.android.calculator:id/result_final");
            assertEquals(page.resultadoEsperado, lblResultadoAtual.getText());
        }
    }
}
