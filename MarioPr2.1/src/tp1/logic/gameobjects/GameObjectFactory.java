package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.control.commands.Command;
import tp1.logic.GameWorld;

public class GameObjectFactory {
	private static final List<GameObject> availableObjects = Arrays.asList(
			new Land(null, null),
			new ExitDoor(null, null),
			new Goomba(null, null),
			new Mario(null, null)
		);
	
	public static GameObject parse(String objWords[], GameWorld game) {
		GameObject object = null;
		for (GameObject o: availableObjects) {
			object = o.parse(objWords);
			if(object != null) return object;
		}
	return object;
	}

}
