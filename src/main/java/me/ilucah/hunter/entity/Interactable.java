package me.ilucah.hunter.entity;

import me.ilucah.hunter.entity.implementation.moving.Player;
import me.ilucah.hunter.input.KeyInteract;
import me.ilucah.hunter.input.MouseInteract;

public interface Interactable {

    void onClick(MouseInteract click);

    void onKey(KeyInteract key);

    void onPlayerCollision(int playerX, int playerY);

    void onPlayerInteract(Player p, int interactX, int interactY);
}
