package avancado;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class) // Classe é executada com parametros
public class Massa {
    public String id;
    public String num1;
    public String operador;
    public String num2;
    public String resultadoEsperado;
    public String deviceName;

    public Massa(String id, String num1, String operador, String num2, String resultadoEsperado, String deviceName) {
        this.id = id;
        this.num1 = num1;
        this.operador = operador;
        this.num2 = num2;
        this.resultadoEsperado = resultadoEsperado;
        this.deviceName = deviceName;
    }

    public static Collection<String[]> lerCSV(String nomeMassa) throws IOException {
        List<String[]> dados = new ArrayList<String[]>();
        String linha;
        BufferedReader arquivo = new BufferedReader(new FileReader(nomeMassa));
        while((linha = arquivo.readLine()) != null){
            String campos[] = linha.split(";");
            dados.add(campos);
        }
        arquivo.close();

    return dados;
}


}
