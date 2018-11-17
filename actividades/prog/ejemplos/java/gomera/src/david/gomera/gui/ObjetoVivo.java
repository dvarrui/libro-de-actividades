package david.gomera.gui;

public class ObjetoVivo {
	//public final String estados = new {"STOP","UP","RIGTH","DOWN","LEFT"};
	private int posicionX;
	private int posicionY;
	private boolean oculto;
	private int dirMovimiento;
	private boolean inercia;
	
	
	public ObjetoVivo() {
		posicionX = 0;
		posicionY = 0;
		oculto = false;
		dirMovimiento = 0;
		inercia = false;
	}

	public void setDirMovimiento(int i) {
		dirMovimiento = i;
	}
	
	public int getDirMovimiento() {
		return dirMovimiento;
	}
	
	public boolean mover() {
		if (dirMovimiento==0) return true;
		else if (dirMovimiento==1) {
			posicionY=posicionY-1;
		}
		else if (dirMovimiento==2) {
			posicionY=posicionY-1;
			posicionX=posicionX+1;
		}
		else if (dirMovimiento==3) {
			posicionX=posicionX+1;
		}
		else if (dirMovimiento==4) {
			posicionY=posicionY+1;
			posicionX=posicionX+1;
		}
		else if (dirMovimiento==5) {
			posicionY=posicionY+1;
		}
		else if (dirMovimiento==6) {
			posicionY=posicionY+1;
			posicionX=posicionX-1;
		}
		else if (dirMovimiento==7) {
			posicionX=posicionX-1;
		}
		else if (dirMovimiento==8) {
			posicionY=posicionY-1;
			posicionX=posicionX-1;
		}
		return true;
	}
}
