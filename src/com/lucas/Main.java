package com.lucas;

import com.lucas.core.Enterprise;
import com.lucas.core.MyProgram;

import javax.swing.SwingUtilities;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() { // Remove a execução de dentro de uma thread se tiver em uma.

            @Override
            public void run() {
                System.out.println("------------------------- Inicio do Programa -----------------------------");
                System.out.println("\n");

                Enterprise enterprise = new Enterprise();                       // Cria uma instancia para execução fora do contexto estatico
                MyProgram program = new MyProgram(enterprise);

                program.addictEmployeesToList();                                // Requisito 3.1
                program.removeEmployeeFromList("João");           // Requisito 3.2
                System.out.println("\n");
                program.showAllListEmployees("TODOS OS FUNCIONÁRIOS");     // Requisito 3.3
                System.out.println("\n");
                program.addictPercentageToWages(10);                            // Requisito 3.4
                program.showAllListEmployees("SALARIOS AJUSTADOS");        // Mostrar novos salários
                program.showEmployeesBy("Função");                         // Requisito 3.5 e 3.6
                program.showEmployeesWithBirthIn(new int[]{10, 12});            // Requisito 3.8
                program.showOlderEmployee();                                    // Requisito 3.9


                System.out.println("\n");
                System.out.println("------------------------- Fim do Programa -----------------------------");
            }
        });
    }
}
