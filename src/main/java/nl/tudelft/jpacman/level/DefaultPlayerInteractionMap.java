package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.Ghost;

/**
 * An extensible default interaction map for collisions caused by the player.
 *
 * The implementation makes use of the interactionmap, and as such can be easily
 * and declaratively extended when new types of units (ghosts, players, ...) are
 * added.
 *
 * @author Arie van Deursen
 * @author Jeroen Roosen
 *
 */
public class DefaultPlayerInteractionMap implements CollisionMap {

    private final CollisionMap collisions = defaultCollisions();

    @Override
    public void collide(Unit mover, Unit movedInto) {
        collisions.collide(mover, movedInto);
    }

    /**
     * Creates the default collisions Player-Ghost, Player-Pellet, and Ghost-Ghost.
     *
     * @return The collision map containing collisions for Player-Ghost,
     *         Player-Pellet, and Ghost-Ghost.
     */
    private static CollisionInteractionMap defaultCollisions() {
        CollisionInteractionMap collisionMap = new CollisionInteractionMap();

        collisionMap.onCollision(Player.class, Ghost.class,
            (player, ghost) -> player.setAlive(false));

        collisionMap.onCollision(Player.class, Pellet.class,
            (player, pellet) -> {
                pellet.leaveSquare();
                player.addPoints(pellet.getValue());
            });

        // Ghosts can move through each other without interaction
        collisionMap.onCollision(Ghost.class, Ghost.class,
            (ghost1, ghost2) -> {
                // No action needed - ghosts pass through each other
            });

        return collisionMap;
    }
}
