//Grupo 13: MarioRosellGarcía - XiangLin

package tp1.logic;

import java.util.ArrayList;
import java.util.List;

public class ActionList {
	private List<Action> actionList;
	
	public ActionList() {
		actionList = new ArrayList<>();
	}
	
	public void add(Action act) {
		this.actionList.add(act);
	}

	public int size() {
		return this.actionList.size();
	}
	
	//este le vamos a quitar, no hace falta si lo miramos en controler
	/*public void restricciones() {
		int der = 0, izq = 0, arr = 0, aba = 0;
		for(int i = 0; i < this.actionList.size(); i++) {
			//contadores
			Action action = this.actionList.get(i);
			if(action == Action.RIGHT && izq == 0) der++;
			else if(action == Action.LEFT && der == 0) izq++;
			else if(action == Action.UP && aba == 0) arr++;
			else if(action == Action.DOWN && arr == 0) aba++;
			//evitar contraddiciones y maximo de elementos
			if((der > 0 && action == Action.LEFT) || izq >= 5) this.actionList.set(i, null);
			else if((izq > 0 && action == Action.RIGHT)|| der >= 5) this.actionList.set(i, null);
			else if((aba > 0 && action == Action.UP)|| arr >= 5) this.actionList.set(i, null);
			else if((arr > 0 && action == Action.DOWN) || aba >= 5) this.actionList.set(i, null);
		}
		this.actionList.removeIf(action -> action == null);
	}*/
	
	public int getX(int pos) {
		return this.actionList.get(pos).getX();
	}
	public int getY(int pos) {
		return this.actionList.get(pos).getY();
	}

	public Action get(int i) {
		return this.actionList.get(i);
	}
}

