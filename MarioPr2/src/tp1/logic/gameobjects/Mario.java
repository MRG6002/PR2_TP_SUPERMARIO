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
		if(this.direccion == Action.STOP)  {
			aux = Messages.MARIO_STOP;
		}
		else if(this.direccion == Action.RIGHT) {
			aux = Messages.MARIO_RIGHT;
		}
		else if(this.direccion == Action.LEFT) {
			aux = Messages.MARIO_LEFT;
		}
		return aux;
	}
	
	public void update() {
		//automático
		boolean marioPierdeVida = false;
		if(this.actions.size() == 0) {
			marioPierdeVida = this.movAutomaticoMario();
		}
		else {
			int cont = 0;
			while(cont < this.actions.size() && !marioPierdeVida) {
				Action act = this.actions.get(cont);
				if(act == Action.DOWN) {
					if (this.MarioColisiona(act)) this.direccion = Action.STOP;
					while(!this.MarioColisiona(act) && !this.pos.estaAbajo()) {
						this.move(act);
						this.game.doInteractionsFrom(this);
					}
					this.isFalling = false;
					if(this.pos.estaAbajo()) {
						this.game.perderVida();
						if(this.game.numLives() > 0) {
							this.game.resetGame();
						}
					}
				}
				else if(!this.MarioColisiona(act)) {
					this.move(act);
					marioPierdeVida = this.game.doInteractionsFrom(this);
				}
				else if(this.MarioColisiona(act)) {
					if(act == Action.RIGHT) this.direccion = Action.LEFT;
					else if(act == Action.LEFT) this.direccion = Action.RIGHT;
				}
				cont++;
			}
		}
		this.isFalling = false;
		this.actions = new ActionList();
		if(marioPierdeVida) this.game.resetGame();
	}
	
	private boolean movAutomaticoMario() {
		boolean marioPierdeVida = false;
		if(this.direccion != Action.STOP) {
			if(this.MarioColisiona(Action.DOWN)) {
				if(this.MarioColisiona(this.direccion) || this.pos.EsBorde(this.direccion == Action.RIGHT)) {
					this.invertirDireccion();
				}
				else this.move(this.direccion);
			}
			else {
				if(this.pos.estaAbajo()) {
					this.game.perderVida();
					this.game.resetGame();
				}
				else this.move(Action.DOWN);
			}
			marioPierdeVida = this.game.doInteractionsFrom(this);
		}
		return marioPierdeVida;
	}
	
	public boolean MarioColisiona(Action act) {
		Position pos = new Position(act.getX(), act.getY());
		if(this.big && this.game.isSolid(pos.sumar(this.posBig))) {
			return true;
		}
		if(this.game.isSolid(pos.sumar(this.pos))) return true;
		return false;
	}
	
	public boolean interactWith(ExitDoor door) {
		return door.isInPosition(this.pos);
	}
	
	public boolean interactWith (Goomba goomba) {
		boolean marioPierdeVida = false;
		if(goomba.isAlive()) {
			if(goomba.isInPosition(this.pos) || (this.big && goomba.isInPosition(this.posBig))) {
				if(this.big && !this.isFalling) {
					this.big = false;
					this.posBig = null;
				}
				else if (!this.isFalling) {
					this.game.perderVida();
					marioPierdeVida = true;
				}
				game.sumar100();
				goomba.recieveInteraction(this);
			}
		}
		return marioPierdeVida;
	}
	
	@Override
	public void move(Action act) {
		this.isFalling = false;
		if(act == Action.RIGHT || act == Action.LEFT || act == Action.STOP) this.direccion = act;
		if (act == Action.DOWN) this.isFalling = true;
		super.move(act);
		if(this.big && this.pos.sumar(new Position (0, -1)).esValida()) 
			this.posBig = new Position(this.pos.sumar(new Position (0, -1)));
	}
		
	@Override
	public String toString () {
		return "Mario " + this.pos.toString() + " " + this.getIcon() + " Big:" + this.big + " Cayendo:" + this.isFalling; 
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
