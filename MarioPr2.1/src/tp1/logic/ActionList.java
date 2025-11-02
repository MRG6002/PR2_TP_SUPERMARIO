// Grupo 13: XiangLin - MarioRosellGarcia

package tp1.logic;

import java.util.Iterator;		
import java.util.ArrayList;
import java.util.List;

import tp1.view.Messages;

public class ActionList implements Iterable<Action> {
	private List<Action> actionList;
	
	public ActionList() {
		this.actionList = new ArrayList<>();
	}
	
	public boolean parse(String[] commandWords) {
		int i = 1; // commandWords[0] == action
		Action action = null;
		
		while(i < commandWords.length && Action.parseAction(commandWords[i]) != null) {
			action = Action.parseAction(commandWords[i]);
			if(action.isAction(Action.STOP) || (this.count(action) < 4 && !this.isOpposite(action))) this.actionList.addLast(action);
			i++;
		}
	return i == commandWords.length;
	}

	private int count(Action action) {
		int n = 0;
		
		for(Action aux: this.actionList) {
			if(aux.isAction(action)) n++;
		}
	return n;
	}
	
	private boolean isOpposite(Action action) {
		for(Action aux: this.actionList) {
			if(aux.isAction(Action.opposite(action))) return true;
		}
	return false;
	}
	
	@Override
	public Iterator<Action> iterator() {
	return this.actionList.iterator();
	}
	
	public void addLast(Action action) {
		this.actionList.addLast(action);
	}
	
	public void clear() {
		this.actionList.clear();
	}
	
	public int size() {
	return this.actionList.size();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for(Action action: this.actionList) stringBuilder.append(action.toString()).append(Messages.SPACE);
	return stringBuilder.toString();
	}
}
