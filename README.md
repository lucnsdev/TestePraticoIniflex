# Teste Pratico - Iniflex

Basta Executar o com.lucas/Main.java
<br>
Todos os requisitos são atendidos.
<br>
Os funcionários são lidos na tabela ```src/resources/employees.csv``` e inseridos na ```List<Employee>```.
<br>
```Employee``` e ```People``` são classes modelos.
<br>
```Enterprise``` é a classe que gerencia as classes modelos e consequentemente manipula os dados dos modelos. Isso torna o código organizado e de facil compreendimento em relação ao fluxo de funcionamento.
<br>
<br>
```java
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {                             // Remove a execução de dentro de uma thread se tiver em uma.

            @Override
            public void run() {
                System.out.println("-------------------------------------------------------------");

                Enterprise enterprise = new Enterprise();                       // Cria uma instancia para execução fora do contexto estatico
                MyProgram program = new MyProgram(enterprise);

                program.addictEmployeesToList();                                // Requisito 3.1
                program.removeEmployeeFromList("João");                         // Requisito 3.2
                System.out.println("\n");
                program.showAllListEmployees("TODOS OS FUNCIONÁRIOS");          // Requisito 3.3
                program.addictPercentageToWages(10);                            // Requisito 3.4
                program.showAllListEmployees("SALARIOS AJUSTADOS");             // Mostrar novos salários
                program.showEmployeesBy("Função");                              // Requisito 3.5 e 3.6
                program.showEmployeesWithBirthIn(new int[]{10, 12});            // Requisito 3.8
                program.showOlderEmployee();                                    // Requisito 3.9
                program.showInAlphabeticallyOrder();                            // Requisito 3.10
                program.showTotalWages();                                       // Requisito 3.11
                program.showMinimalWagesPerEmployee();                          // Requisito 3.12
            }
        });
    }
}
```
