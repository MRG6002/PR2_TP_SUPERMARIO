//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic;

import java.util.Objects;

import tp1.view.Messages;

public final class Position {

	private final int col;
	private final int row;

	public Position(int x, int y) {
		this.col = x;
		this.row = y;
	}
	
	public Position(Position pos) {
			this.col = pos.col;
			this.row = pos.row;
	}
	
	public boolean esValida() {
		 return (this.col >= 0 && this.col < Game.DIM_X && this.row >= 0 && this.row < Game.DIM_Y);
	}
	
	public String toString() {
		return Messages.POSITION.formatted(this.col, this.row);
	}
	
	@Override
	public boolean equals(Object obj) {
		return this==obj ||
			obj!=null && getClass() == obj.getClass() &&
			this.col == ((Position) obj).col &&
			this.row == ((Position) obj).row;
	}
	@Override 
	public int hashCode() {
		return Objects.hash(col,row);
	}
	
	public Position sumar(Position pos) {
		return new Position(this.col + pos.col, this.row + pos.row);
	}
	
	public boolean EsBorde(boolean derecha) {
		return ((this.col == 0 && !derecha) || (this.col == Game.DIM_X - 1 && derecha));
	}

	public boolean estaAbajo() {
		return this.row == Game.DIM_Y - 1;
	}

	public boolean enDerechaDe(Position pos) {
		return pos.col < this.col;
	}
	
	public boolean enIzquierdaDe(Position pos) {
		return pos.col > this.col;
	}
	
	public boolean encimaDe(Position pos) {
		return this.row < pos.row;
	}

}