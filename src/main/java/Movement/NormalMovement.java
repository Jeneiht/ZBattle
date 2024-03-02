package Movement;

import com.trongthien.zBattle.character.Direction;
import com.trongthien.zBattle.character.Entity;
import com.trongthien.zBattle.component.CollisionChecker;

public class NormalMovement implements Movement{
    @Override
    public void move(Entity entity) {
        int speed = entity.getSpeed();
        Direction direction = entity.getDirection();
        CollisionChecker collisionChecker = entity.getCollisionChecker();
        switch (direction) {
            case UP:
                entity.setY(entity.getY() - speed);
                while (collisionChecker.checkCollisionTop(entity)) {
                    entity.setY(entity.getY() + 1);
                }
                break;
            case DOWN:
                entity.setY(entity.getY() + speed);
                while (collisionChecker.checkCollisionBottom(entity)) {
                    entity.setY(entity.getY() - 1);
                }
                break;
            case LEFT:
                entity.setX(entity.getX() - speed);
                while (collisionChecker.checkCollisionLeft(entity)) {
                    entity.setX(entity.getX() + 1);
                }
                break;
            case RIGHT:
                entity.setX(entity.getX() + speed);
                while (collisionChecker.checkCollisionRight(entity)) {
                    entity.setX(entity.getX() - 1);}
                break;
            case UP_LEFT:
                entity.setY(entity.getY() - speed / 2);
                while (collisionChecker.checkCollisionTop(entity)) {
                    entity.setY(entity.getY() + 1);
                }
                entity.setX(entity.getX() - speed / 2);
                while (collisionChecker.checkCollisionLeft(entity)) {
                    entity.setX(entity.getX() + 1);
                }
                break;
            case UP_RIGHT:
                entity.setY(entity.getY() - speed / 2);
                while (collisionChecker.checkCollisionTop(entity)) {
                    entity.setY(entity.getY() + 1);
                }
                entity.setX(entity.getX() + speed / 2);
                while (collisionChecker.checkCollisionRight(entity)) {
                    entity.setX(entity.getX() - 1);
                }
                break;
            case DOWN_LEFT:
                entity.setY(entity.getY() + speed / 2);
                while (collisionChecker.checkCollisionBottom(entity)) {
                    entity.setY(entity.getY() - 1);
                }
                entity.setX(entity.getX() - speed / 2);
                while (collisionChecker.checkCollisionLeft(entity)) {
                    entity.setX(entity.getX() + 1);
                }
                break;
            case DOWN_RIGHT:
                entity.setY(entity.getY() + speed / 2);
                while (collisionChecker.checkCollisionBottom(entity)) {
                    entity.setY(entity.getY() - 1);
                }
                entity.setX(entity.getX() + speed / 2);
                while (collisionChecker.checkCollisionRight(entity)) {
                    entity.setX(entity.getX() - 1);
                }
                break;
        }
    }
}
