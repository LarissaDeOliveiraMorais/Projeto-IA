package agente;

import ambiente.Labirinto;
import geral.PosicaoXY;

public class AgenteLabirinto {

	// Movimentar para 4 dire��es (Cima, baixo, esquerda, direita)
	// Referencia do Labiririntoo 
	
	int x=2;
	int y= 2;
	int contadorLoop;
	
	private Labirinto labirinto;
	private MovimentosAgenteLabirinto movimento;
	
	private PosicaoXY posXY;
	
	private int pilhaMovimentos;

	public AgenteLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
		labirinto.setAgente(this);
		this.posXY = new PosicaoXY();
		this.movimento = MovimentosAgenteLabirinto.CIMA;
	}
	
	public void movimentar() {
		if (this.pilhaMovimentos >= 4) {
			labirinto.sujarAutomatico();
			movimentarLocalSujo();
			return;
		}
		PosicaoXY proximoMovimento = retornarMovimento();
		String valor = this.labirinto.retornarValorPosicaoLabirinto(proximoMovimento);
		
		if (valor.equals("L") || valor.equals("*A*")) {
			proximoMovimento();
			aumentarPilha();
			movimentar();
		} else {
			this.labirinto.limpar();
			this.posXY = proximoMovimento;
		}
	}
	
	private void aumentarPilha() {
		this.pilhaMovimentos++;
	}

	private void proximoMovimento() {
		switch(this.movimento) {
			case CIMA:
				this.movimento = MovimentosAgenteLabirinto.BAIXO;
				break;
			case BAIXO:
				this.movimento = MovimentosAgenteLabirinto.ESQUERDA;
				break;
			case ESQUERDA:
				this.movimento = MovimentosAgenteLabirinto.DIREITA;
				break;
			case DIREITA:
				this.movimento = MovimentosAgenteLabirinto.CIMA;
				break;
		}
	}
	
	

	public PosicaoXY retornarMovimento() {
		int retornoPosX = this.posXY.getPosX();
		int retornoPosY = this.posXY.getPosY();
		switch(movimento) {
			case CIMA:
				if (retornoPosX > 0) {
					retornoPosX -= 1;
				}
				break;
			case BAIXO:
				if (retornoPosX < this.labirinto.getTamanhoLabirinto() - 1) {
					retornoPosX += 1;
				}
				break;
			case ESQUERDA:
				if (retornoPosY > 0) {
					retornoPosY -= 1;
				}
				break;
			case DIREITA:
				if (retornoPosY < this.labirinto.getTamanhoLabirinto() - 1) {
					retornoPosY += 1;
				}
				break;
		}
		return new PosicaoXY(retornoPosX, retornoPosY);
	}

	public PosicaoXY getPosicao() {
		return this.posXY;
	}

	public boolean isAindaLimpando() {
		return pilhaMovimentos < 4;
	}

	public void zerarPilha() {
		this.pilhaMovimentos = 0;
	}

	public void setPosicao(PosicaoXY posicaoXY) {
		this.posXY = posicaoXY;
		
	}
	
	public void movimentarLocalSujo() {
		
		PosicaoXY posicaoXY = getPosicao();
		
		int posicaoAnteriorX = posicaoXY.getPosX();
		int posicaoAnteriorY = posicaoXY.getPosY();
		
		if ((posicaoAnteriorX == x)&&(posicaoAnteriorY == y)) {
			contadorLoop += 1;
			if (contadorLoop==5) {
				x=8;
				y=8;
			}
			if (contadorLoop==10) {
				x=6;
				y=8;
			}
			if (contadorLoop==15) {
				x=5;
				y=5;
			}
			if (contadorLoop==20) {
				x=8;
				y=1;
			}
			if (contadorLoop==25) {
				x=5;
				y=5;
			}
			if (contadorLoop==30) {
				x=4;
				y=8;
			}
			if (contadorLoop==35) {
				x=0;
				y=0;
			}
		}		
		
	    labirinto.limpar();
		if (x>posicaoXY.getPosX()) {
			posicaoXY.setPosX(posicaoXY.getPosX()+1);
		}else if (x<posicaoXY.getPosX()) {
			posicaoXY.setPosX(posicaoXY.getPosX()-1);
		}else if (y>posicaoXY.getPosY()) {
			posicaoXY.setPosY(posicaoXY.getPosX()+1);
		}else if (y<posicaoXY.getPosY()) {
			posicaoXY.setPosY(posicaoXY.getPosX()-1);
		}
	    labirinto.exibirLabirinto();
	    zerarPilha();

	}
	
}
