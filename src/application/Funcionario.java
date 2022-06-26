package application;

public class Funcionario {
	private String nome;
	private Double salarioBruto;
	private Double descontoInss;
	private Double descontoIrrf;
	private Double salarioLiquido;

	public Funcionario () {
	}

	public Funcionario(String nome, Double salarioBruto, Double descontoInss, Double descontoIrrf,
			Double salarioLiquido) {
		this.nome = nome;
		this.salarioBruto = salarioBruto;
		this.descontoInss = descontoInss;
		this.descontoIrrf = descontoIrrf;
		this.salarioLiquido = salarioLiquido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(Double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public Double getDescontoInss() {
		return descontoInss;
	}

	public void setDescontoInss(Double descontoInss) {
		this.descontoInss = descontoInss;
	}

	public Double getDescontoIrrf() {
		return descontoIrrf;
	}

	public void setDescontoIrrf(Double descontoIrrf) {
		this.descontoIrrf = descontoIrrf;
	}

	public Double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(Double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

}