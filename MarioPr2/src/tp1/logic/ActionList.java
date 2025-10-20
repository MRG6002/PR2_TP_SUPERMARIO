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
	
	public int getX(int pos) {
		return this.actionList.get(pos).getX();
	}
	public int getY(int pos) {
		return this.actionList.get(pos).getY();
	}

	public Action get(int i) {
		return this.actionList.get(i);
	}
	
	@Override 
	public String toString() {
		StringBuffer string = new StringBuffer();
		string.append("Actions:");
		for(Action action: this.actionList) {
			string.append(action.toString() + " ");
		}
		return string.toString();
	}
}

