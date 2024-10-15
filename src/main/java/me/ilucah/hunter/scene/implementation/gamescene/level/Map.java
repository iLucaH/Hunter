package me.ilucah.hunter.scene.implementation.gamescene.level;

import me.ilucah.hunter.entity.Entity;
import me.ilucah.hunter.entity.implementation.Brick;
import me.ilucah.hunter.entity.implementation.Cobblestone;
import me.ilucah.hunter.entity.implementation.Dirt;
import me.ilucah.hunter.scene.implementation.gamescene.GameScene;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Map {

    private final int[][] map;
    private final List<Entity> entityList;

    public Map(GameScene scene, int[][] map, int gridBlockSize) {
        this.map = map;

        entityList = new ArrayList<>();
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                int id = map[x][y];
                if (id == 0) {
                    entityList.add(new Dirt(scene, x * gridBlockSize, y * gridBlockSize, gridBlockSize));
                } else if (id == 1) {
                    entityList.add(new Cobblestone(x * gridBlockSize, y * gridBlockSize, gridBlockSize));
                } else if (id == 2) {
                    entityList.add(new Brick(scene,x * gridBlockSize, y * gridBlockSize, gridBlockSize));
                }
            }
        }
    }

    public int[][] getMap() {
        return map;
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public Optional<Entity> getEntityAt(int x, int y) {
        return entityList.stream().filter(e -> e.getBounds().contains(x, y)).findFirst();
    }

    public Optional<Entity> getEntityAt(int x, int y, int id) {
        return entityList.stream().filter(e -> e.getBounds().contains(x, y) && e.getId() == id).findFirst();
    }
}
