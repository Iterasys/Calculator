Feature: Calcularr
  Scenario: Somar Dois Numeros Inteiros Positivos
    Given abro a calculadora do Google no meu smartphone
    When seleciono "8" mais "5" e pressiono o botao Igual
    Then exibe o resultado como "13"

  Scenario Outline: Somar Dois Numeros Data Driven
    Given abro a calculadora do Google no meu smartphone
    When seleciono <num1> mais <num2> e pressiono o botao Igual
    Then exibe o resultado como <resultadoEsperado>
    Examples:
      |  num1 |  num2 | resultadoEsperado |
      | "8"   | "5"   | "13"              |
      | "7"   | "9"   | "16"              |
      | "3"   | "3"   | "6"               |

  Scenario: Calcular Com Massa de Teste
    Given que utilizo a massa "db/massaCalc.csv" para testar a calculadora
    When realizo a operacao com dois numeros
    Then compara o resultado atual com o esperado
