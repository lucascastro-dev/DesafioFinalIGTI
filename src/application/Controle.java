package application;

import java.util.ArrayList;
import java.util.Scanner;

public class Controle {

    private Scanner sc = new Scanner(System.in);

    private String mostrarMenu() {

        StringBuilder sb = new StringBuilder();
        sb.append("    ----- MENU ----- \n");
        sb.append("1 - CADASTRAR FUNCIONÁRIO \n");
        sb.append("2 - IMPRIMIR CONTRACHEQUE \n");
        sb.append("0 - SAIR \n \n");
        sb.append("ENTRE COM A OPÇÃO: \n");

        System.out.print(sb);

        return sc.nextLine();
    }

    private Double computarTaxaInss(Double salario) {
        Double taxaInss = 0.0;

        if (salario > 6101.06) {
            taxaInss = 713.10;
        } else if (salario <= 1045) {
            taxaInss = salario * 0.075;
        } else if (salario > 1045.01 && salario <= 2089.6) {
            taxaInss = (1045 * 0.075) + (salario - 1045.01) * 0.09;
        } else if (salario > 2089.61 && salario <= 3134.4) {
            taxaInss = (1045 * 0.075) + (2089.6 - 1045.01) * 0.09 + (salario - 2089.61) * 0.12;
        } else if (salario > 3134.41 && salario <= 6101.06) {
            taxaInss = (1045 * 0.075) + (2089.6 - 1045.01) * 0.09 + (3134.4 - 2089.61) * 0.12 + (salario - 3134.4) * 0.14;
        }
        return taxaInss;
    }

    private Double computarTaxaIrrf(Double salary, Double inssFee) {
        Double taxaIrrf = 0.0;
        Double SemDescontoInss = salary - inssFee;

        if (SemDescontoInss > 1903.99 && SemDescontoInss <= 2826.65) {
            taxaIrrf = (SemDescontoInss * 0.075) - 142.8;
        } else if (SemDescontoInss > 2826.66 && SemDescontoInss <= 3751.05) {
            taxaIrrf = (SemDescontoInss * 0.15) - 354.8;
        } else if (SemDescontoInss > 3751.06 && SemDescontoInss <= 4664.68) {
            taxaIrrf = (SemDescontoInss * 0.225) - 636.13;
        } else if (SemDescontoInss > 4664.69) {
            taxaIrrf = (SemDescontoInss * 0.275) - 869.36;
        }

        return taxaIrrf;
    }

    public Integer findIndexEmployee(String nomeFuncionario, ArrayList<Funcionario> funcionarios) {
        for (Integer i = 0; i <= funcionarios.size() - 1; i++) {
            if (funcionarios.get(i).getNome().equals(nomeFuncionario)) {
                return i;
            }
        }
        return -1;
    }

    public void iniciar() {
        ArrayList<Funcionario> funcionario = new ArrayList<>();
        String opcao = mostrarMenu();

        while (!opcao.equals("0")) {
            switch (opcao) {
                case "1":
                    System.out.print("Nome: ");
                    Funcionario cadastroFuncionario = new Funcionario();
                    String item = sc.nextLine();
                    cadastroFuncionario.setNome(item);
                    System.out.print("Salário bruto: R$ ");
                    String salarioBruto = sc.nextLine();
                    System.out.println();
                    cadastroFuncionario.setSalarioBruto(Double.parseDouble(salarioBruto));
                    funcionario.add(cadastroFuncionario);
                    opcao = mostrarMenu();
                    break;

                case "2":
                    System.out.println("Entre com o nome do funcionário que deseja imprimir o contracheque: ");
                    String nomeFuncionario = sc.nextLine();
                    Integer idFuncionario = findIndexEmployee(nomeFuncionario, funcionario);
                    if (idFuncionario != -1) {
                        System.out.println("Funcionário encontrado... ");
                        System.out.println("Imprimindo contracheque...\n");
                        Funcionario selecionarFuncionario = funcionario.get(idFuncionario);
                        Double taxacaoInss = computarTaxaInss(selecionarFuncionario.getSalarioBruto());
                        selecionarFuncionario.setDescontoInss(taxacaoInss);
                        Double taxacaoIrrf = computarTaxaIrrf(selecionarFuncionario.getSalarioBruto(), selecionarFuncionario.getDescontoInss());
                        selecionarFuncionario.setDescontoIrrf(taxacaoIrrf);
                        Double salarioLiquido = selecionarFuncionario.getSalarioBruto() - selecionarFuncionario.getDescontoInss() - selecionarFuncionario.getDescontoIrrf();
                        selecionarFuncionario.setSalarioLiquido(salarioLiquido);
                        System.out.println("Funcionario: " + selecionarFuncionario.getNome() + "\n"
                                + "Salario bruto: R$ " + String.format("%.2f", selecionarFuncionario.getSalarioBruto()) + "\n"
                                + "INSS: R$ " + String.format("%.2f", selecionarFuncionario.getDescontoInss()) + "\n"
                                + "IRFF: R$ " + String.format("%.2f", selecionarFuncionario.getDescontoIrrf()) + "\n"
                                + "Salário líquido: R$ " + String.format("%.2f", selecionarFuncionario.getSalarioLiquido()) + "\n");
                        opcao = mostrarMenu();
                        break;
                    } else {
                        System.out.println("Funcionário não encontrado!\n");
                        opcao = mostrarMenu();
                        break;
                    }
                default:
                    System.out.println("Opção inválida\n");
                    opcao = mostrarMenu();
            }
        }
        sc.close();
    }
}