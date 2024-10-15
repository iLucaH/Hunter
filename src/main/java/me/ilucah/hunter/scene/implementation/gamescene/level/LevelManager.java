package me.ilucah.hunter.scene.implementation.gamescene.level;

import me.ilucah.hunter.scene.implementation.gamescene.GameScene;
import me.ilucah.hunter.scene.implementation.gamescene.level.implementation.TutorialLevel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LevelManager {

    private Level currentState;
    private final ConcurrentMap<String, Level> levels = new ConcurrentHashMap<>();

    public LevelManager(GameScene scene) {
        levels.put("TutorialLevel", new TutorialLevel(scene));

        currentState = levels.get("TutorialLevel");
    }

    public void setLevel(Level state) {
        currentState = state;
    }

    public Level getLevel() {
        return currentState;
    }
}
