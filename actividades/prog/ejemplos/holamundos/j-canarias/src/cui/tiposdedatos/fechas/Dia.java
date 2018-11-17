package cui.tiposdedatos.fechas;

public class Dia {
	// Atributos
	int dia;

	int mes;

	int anno;

	int numeroSemanaMes;

	int numeroSemanaDia;

	boolean fiesta;

	boolean hoy;

	String nota;

	Dia(int anno, int mes, int dia) {
		this.setAnno(anno);
		this.setMes(mes);
		this.setDia(dia);
	}

	public boolean isFiesta() {
		return fiesta;
	}

	public void setFiesta(boolean fiesta) {
		this.fiesta = fiesta;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getDia() {
		return dia;
	}

	public String getDiaToString() {
		return Integer.toString(dia);
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getNumeroSemanaDia() {
		return numeroSemanaDia;
	}

	public void setNumeroSemanaDia(int numeroSemanaDia) {
		this.numeroSemanaDia = numeroSemanaDia;
	}

	public int getNumeroSemanaMes() {
		return numeroSemanaMes;
	}

	public void setNumeroSemanaMes(int numeroSemanaMes) {
		this.numeroSemanaMes = numeroSemanaMes;
	}

	public void mostrar() {
		System.out.println("Fecha: " + getDia() + "/" + getMes() + "/"
				+ getAnno());
	}
}
