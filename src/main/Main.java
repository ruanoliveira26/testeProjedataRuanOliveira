package main;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import entities.Funcionario;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Funcionario> funcionarios = new ArrayList<>();

		System.out.println("Inicio inserção dos Funcionários.");
		System.out.println();

		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
		funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
		funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		funcionarios
				.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
		funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
		funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
		funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
		funcionarios
				.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
		funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

		System.out.println("Inserção dos Funcionários finalizada.");
		System.out.println();

		System.out.println("Removendo João da lista de Funcionários.");
		System.out.println();
		funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
		System.out.println("Funcionários após remover João.");
		System.out.println();

		System.out.println("Todos os Funcionários:");
		NumberFormat numberFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.getDefault()));
		for (Funcionario funcionario : funcionarios) {
			System.out.println("Nome: " + funcionario.getNome());
			System.out.println("Data de Nascimento: "
					+ funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			System.out.println("Salário: " + numberFormat.format(funcionario.getSalario()));
			System.out.println("Função: " + funcionario.getFuncao());
			System.out.println();

		}

		System.out.println("Aplicando aumento de 10% nos salários de todos os Funcionários.");
		System.out.println();
		for (Funcionario funcionario : funcionarios) {

			BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.10"));
			funcionario.setSalario(funcionario.getSalario().add(aumento));
		}

		// Não consegui resolver estes requisitos
		// 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função”
		// e o valor a “lista de funcionários”.
		// 3.6 – Imprimir os funcionários, agrupados por função.

		System.out.println("Funcionários que fazem aniversário nos meses 10 e 12:");
		for (Funcionario funcionario : funcionarios) {
			int mesAniversario = funcionario.getDataNascimento().getMonthValue();
			if (mesAniversario == 10 || mesAniversario == 12) {
				System.out.println("Nome: " + funcionario.getNome());
				System.out.println("Data de Nascimento: "
						+ funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				System.out.println("Salário: " + numberFormat.format(funcionario.getSalario()));
				System.out.println("Função: " + funcionario.getFuncao());
				System.out.println();
			}
		}

		System.out.println("Encontrando funcionário com a maior idade.");
		System.out.println();

		Funcionario funcionarioMaiorIdade = null;
		int idadeMaxima = -1; // Inicializando com um valor impossível

		for (Funcionario funcionario : funcionarios) {
			int idade = funcionario.getIdade();
			if (idade > idadeMaxima) {
				idadeMaxima = idade;
				funcionarioMaiorIdade = funcionario;
			}
		}

		System.out.println("Funcionário com a maior idade:");

		if (funcionarioMaiorIdade != null) {
			System.out.println("Nome: " + funcionarioMaiorIdade.getNome());
			System.out.println("Idade: " + funcionarioMaiorIdade.getIdade());
			System.out.println();
		}

		System.out.println("Lista de funcionários por ordem alfabética:");
		Collections.sort(funcionarios, Comparator.comparing(Funcionario::getNome));

		for (Funcionario funcionario : funcionarios) {
			System.out.println("Nome: " + funcionario.getNome());
			System.out.println("Data de Nascimento: "
					+ funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			System.out.println("Idade: " + funcionario.getIdade());
			System.out.println("Salário: " + numberFormat.format(funcionario.getSalario()));
			System.out.println("Função: " + funcionario.getFuncao());
			System.out.println();
		}

		System.out.println("Encontrando total dos salários dos funcionários.");
		System.out.println();
		BigDecimal totalSalarios = BigDecimal.ZERO;
		for (Funcionario funcionario : funcionarios) {
			totalSalarios = totalSalarios.add(funcionario.getSalario());
		}

		System.out.println("Total dos salários dos funcionários: R$" + numberFormat.format(totalSalarios));
		System.out.println();

		System.out.println("Quantidade de salários mínimos que cada funcionário ganha:");
		for (Funcionario funcionario : funcionarios) {
			BigDecimal salariosMinimos = funcionario.calcularSalariosMinimos();
			System.out.println(funcionario.getNome() + ": " + salariosMinimos);
		}
		// final Main
	}
}
