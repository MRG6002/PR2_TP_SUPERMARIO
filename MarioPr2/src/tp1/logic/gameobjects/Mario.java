//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mario extends MovingObject {
	private ActionList actions = new ActionList();
	boolean big;
	Position posBig;
	
	public Mario(Game game, Position pos) {
		super(game, pos, Action.RIGHT);
		this.big = true;
		Position aux = new Position(0, -1).sumar(this.pos);
		if(aux.esValida()) {
			this.posBig = aux;
		}
	}
	
	public void marioExited() {
		this.game.marioExited();
	}
	
	public String getIcon() {
		String aux = "";
		if(this.dirEquals(Action.STOP))  {
			aux = Messages.MARIO_STOP;
		}
		else if(this.dirEquals(Action.RIGHT)) {
			aux = Messages.MARIO_RIGHT;
		}
		else if(this.dirEquals(Action.LEFT)) {
			aux = Messages.MARIO_LEFT;
		}
		return aux;
	}
	
	public void update() {
		//automático
		if(this.actions.size() == 0) {
			if(!this.dirEquals(Action.STOP)) movAutomatico();
			this.game.doInteractionsFrom(this);
		}
		else {
			int cont = 0;
			while(cont < this.actions.size() && this.isAlive()) {
				Action act = this.actions.get(cont);
				movNoAutomaticoMario(act);
				cont++;
			}
		}
		this.notFalling();
		this.actions = new ActionList();
		if(this.isAlive() == false) {
			this.game.perderVida();
		}
	}
	
	private void movNoAutomaticoMario(Action act) {
		if(act == Action.DOWN) {
			if (this.marioColisiona(act)) this.changeDireccion(Action.STOP);
			while(!this.marioColisiona(act) && !this.pos.estaAbajo()) {
				this.move(act);
				this.game.doInteractionsFrom(this);
			}
			this.notFalling();
			if(this.pos.estaAbajo()) {
				this.dead();
			}
		}
		else if(!this.marioColisiona(act)) {
			this.move(act);
			this.game.doInteractionsFrom(this);
		}
		else if(this.marioColisiona(act)) {
			if(act == Action.RIGHT) this.changeDireccion(Action.LEFT);
			else if(act == Action.LEFT) this.changeDireccion(Action.RIGHT);
		}
	}
	
	public boolean marioColisiona(Action act) { //necesitamos esta comprobacion para evitar choques con la cabeza
		Position pos = new Position(act.getX(), act.getY());
		return (this.big && this.game.isSolid(pos.sumar(this.posBig)))||(this.game.isSolid(pos.sumar(this.pos)));
	}
	
	public boolean interactWith(ExitDoor door) {
		return door.isInPosition(this.pos);
	}
	
	public void interactWith (Goomba goomba) {
		if(goomba.isAlive()) {
			if(goomba.isInPosition(this.pos) || (this.big && goomba.isInPosition(this.posBig))) {
				if(this.big && !this.isFalling()) {
					this.big = false;
					this.posBig = null;
				}
				else if (!this.isFalling()) {
					this.game.perderVida();
				}
				game.sumar100();
				goomba.recieveInteraction(this);
			}
		}
	}
	
	@Override
	public void move(Action act) {
		this.notFalling();
		if(act == Action.RIGHT || act == Action.LEFT || act == Action.STOP) this.changeDireccion(act);
		if (act == Action.DOWN) this.falling();
		super.move(act);
		if(this.big && this.pos.sumar(new Position (0, -1)).esValida()) 
			this.posBig = new Position(this.pos.sumar(new Position (0, -1)));
	}
		
	@Override
	public String toString () {
		return "Mario " + super.toString() + " " + this.getIcon() + " Big:" + this.big + " Cayendo:" + this.isFalling() + 
				this.actions.toString(); 
	}
		
	public void addAction(Action action) {
		this.actions.add(action);
	}
	
	@Override
	public boolean isInPosition(Position pos) {
		return ((this.big && this.posBig.equals(pos)) ||this.pos.equals(pos));
	}

	public int numVidas() {
		return this.game.numLives();
	}
}
