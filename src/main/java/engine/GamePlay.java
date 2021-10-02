/*
 * Copyright (C) 2020-2021  G. Arslan
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms and conditions of the GNU General Public License,
 * version 2, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package main.java.engine;

import lombok.Getter;
import lombok.Setter;
import main.java.enums.PropertiesLoader;
import main.java.enums.Variables;
import main.java.gui.Menu;
import main.java.gameobject.*;
import main.java.gameobject.Box;
import main.java.gameobject.item.Key;
import main.java.gameobject.item.MasterKey;
import main.java.gameobject.item.Money;
import main.java.gameobject.item.Sword;
import main.java.gameobject.npc.Opa;
import main.java.gameobject.npc.Princess;
import main.java.gameobject.obstacles.Lock;
import main.java.gameobject.switches.Button;
import main.java.gameobject.switches.Portal;
import main.java.gameobject.villain.Villain;

import javax.swing.*;
import java.util.Properties;

import static java.lang.Integer.parseInt;

/**
 * This is where the GamePlay is set.
 */
public class GamePlay {
    @Getter
    @Setter
    private static boolean isCheat;
    @Getter
    @Setter
    private static boolean isBeenCheating;
    @Getter
    private static final Level<Visited> LVL = new Level<>();
    private static final Properties CONFIG_PROP = new PropertiesLoader("config", false).getProperties();
    private static final Properties TXT_PROP = new PropertiesLoader("text", true).getProperties();

    private static int x = 0; // x-axis distance from player
    private static int y = 0; // y-axis distance from player

    private GamePlay() {
        throw new IllegalStateException("Utility class");
    }

    public static void control(char c) {
        switch (c) {
            case 'i':
                Menu.getInstance().info();
                break;
            case 'c':
                commands();
                break;
            case 'w':
            case 'a':
            case 's':
            case 'd':
                movement(c);
                break;
            default:
                break;
        }
    }

    private static void commands() {
        switch (Menu.getInstance().cmd()) {
            case "glitch":
                cheatMode();
                break;
            case "swords":
                Player.getInstance().setSwords(Menu.getInstance().cmdSwords());
                break;
            case "keys":
                Player.getInstance().setKeys(parseInt(JOptionPane.showInputDialog(Menu.getInstance().cmdKeys())));
                break;
            default:
                break;
        }
    }

    private static void movement(char c) {
        x = 0;
        y = 0;

        switch (c) {
            case 'w':
                y--;
                checkBoxObstacle('y', -1);
                break;
            case 'a':
                x--;
                checkBoxObstacle('x', -1);
                break;
            case 's':
                y++;
                checkBoxObstacle('y', 1);
                break;
            case 'd':
                x++;
                checkBoxObstacle('x', 1);
                break;
            default:
                break;
        }

        if (isPlayerOnStairs()) return;

        if (isPlayerOnPortal()) return;

        movePlayerOnField(c);
    }


    /*
     * Check, if box can be moved further (i.e. if there are no obstacles).
     * @param coord, Either the X or Y coordinate.
     * @param r, ??
     */
    private static void checkBoxObstacle(char coord, int r) {
        switch (coord) {
            case 'y':
                y = r;
                break;
            case 'x':
                x = r;
                break;
            default:
                return;
        }

        if (isPlayerInFrontOf(Variables.BOX) && isBoxInFrontOfButton()) {
            //move Box on Button
            LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveDown();
            //remove block to enter next section in lvl 1
            LVL.getGameField()[18][14].verstecke();
            LVL.getGameField()[18][14] = null;
            //
            LVL.getGameField()[Player.getPosX()][Player.getPosY() + (y * 2)] = LVL.getGameField()[Player.getPosX()][Player.getPosY() + y];
            LVL.getGameField()[Player.getPosX()][Player.getPosY() + y] = null;
        }

        if (isPlayerInFrontOf(Variables.BOX) && !(isBoxInFrontOfObstacle())) {
            Sound.playSound(CONFIG_PROP.getProperty("sfx.interaction"));
            if (coord == 'x') {
                if (r == 1) {
                    LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveRight();
                } else if (r == -1) {
                    LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveLeft();
                }
            } else {
                if (r == -1) {
                    LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveUp();
                } else if (r == 1) {
                    LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveDown();
                }
            }
            LVL.getGameField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] = LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y];
            LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] = null;
        } else if (isPlayerInFrontOf(Variables.BOX) && isBoxInFrontOfObstacle()) {
            Sound.playSound(CONFIG_PROP.getProperty("sfx.obstacle"));
        }
    }

    /*
     * If Player is in front of a specified object.
     * @param object specified object
     * @return true, if Player is in front of a specified object
     */
    private static boolean isPlayerInFrontOf(String object) {
        switch (object) {
            case Variables.BUTTON:
                return LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Button;
            case Variables.BOX:
                return LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Box;
            case Variables.SWORD:
                return LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Sword;
            case Variables.MONEY:
                return LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Money;
            case Variables.PRINCESS:
                return LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Princess;
            case Variables.LOCK:
                return LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Lock;
            case Variables.OPA:
                return LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Opa;
            case Variables.NPC:
                return LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof NPC;
            case Variables.KEY:
                return LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Key;
            case Variables.MASTERKEY:
                return LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof MasterKey;
            case Variables.VILLAIN:
                return LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Villain;
            case Variables.OBSTACLE:
                return LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Obstacle && !GamePlay.isCheat();
            case Variables.STAIRS:
                return LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Stairs;
            default:
                return false;
        }
    }

    private static boolean isPlayerOnStairs() {
        if (isPlayerInFrontOf(Variables.STAIRS)) {
            Sound.playSound(CONFIG_PROP.getProperty("sfx.stairs"));
            //set lvl destination
            int newLvl = ((Stairs) LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y]).getLvl();
            //clear gameField
            for (int i = 0; i < parseInt(CONFIG_PROP.getProperty("field.length.x")); i++) {
                for (int j = 0; j < parseInt(CONFIG_PROP.getProperty("field.length.y")); j++) {
                    if (LVL.getGameField()[i][j] != null) {
                        LVL.getGameField()[i][j].verstecke();
                    }
                    LVL.getGameField()[i][j] = null;
                }
            }
            LVL.setGameField(LVL.loadLvl(newLvl));
            return true;
        }
        return false;
    }

    private static boolean isPlayerOnPortal() {
        if (LVL.getGameField()[Player.getPosX()][Player.getPosY()] instanceof Portal) {
            //teleport player to another position
            Player.getInstance().go(((Portal) LVL.getGameField()[Player.getPosX()][Player.getPosY()]).getPosX(),
                    ((Portal) LVL.getGameField()[Player.getPosX()][Player.getPosY()]).getPosY());
            int xt = ((Portal) LVL.getGameField()[Player.getPosX()][Player.getPosY()]).getPosX();
            int yt = ((Portal) LVL.getGameField()[Player.getPosX()][Player.getPosY()]).getPosY();
            Player.setPosX(xt);
            Player.setPosY(yt);
            return true;
        }
        return false;
    }

    private static boolean isPlayerInFrontOfPerson() {
        if (isPlayerInFrontOf(Variables.PRINCESS)) {
            Sound.playSound(CONFIG_PROP.getProperty("sfx.princess"));
            Menu.getInstance().text(TXT_PROP.getProperty("txt.princess.001"));
            return true;
        } else if (isPlayerInFrontOf(Variables.OPA)) {
            if (LVL.getLvl() == 3) {
                Sound.playSound(CONFIG_PROP.getProperty("sfx.opa"));
                Menu.getInstance().text(TXT_PROP.getProperty("txt.opa.004"));
                return true;
            }
            if (Opa.getMasterKey() == 0) {
                Sound.playSound(CONFIG_PROP.getProperty("sfx.txt"));
                Menu.getInstance().text(TXT_PROP.getProperty("txt.opa.003"));
            } else {
                if (Player.getInstance().getMoney() < 450) {
                    Sound.playSound(CONFIG_PROP.getProperty("sfx.txt"));
                    Menu.getInstance().text(TXT_PROP.getProperty("txt.opa.001"));
                } else {
                    LVL.getList().add(new Visited(Player.getPosX(), Player.getPosY() + y, LVL.getLvl()));
                    Player.getInstance().setMoney(Player.getInstance().getMoney() - 450);
                    Opa.setMasterKey(Opa.getMasterKey() - 1);
                    Player.getInstance().setMasterKeys(Player.getInstance().getMasterKeys() + 1);
                    Menu.getInstance().text(TXT_PROP.getProperty("txt.opa.002"));
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isBoxInFrontOfButton() {
        return LVL.getGameField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] instanceof Button;
    }

    private static boolean isBoxInFrontOfObstacle() {
        return LVL.getGameField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] instanceof Obstacle;
    }

    private static boolean isKeyCollected() {
        if (isPlayerInFrontOf(Variables.KEY)) {
            Player.getInstance().setKeys(Player.getInstance().getKeys() + 1);
            return true;
        }
        return false;
    }

    private static boolean isMasterKeyCollected() {
        if (isPlayerInFrontOf(Variables.MASTERKEY)) {
            Sound.playSound(CONFIG_PROP.getProperty("sfx.txt"));
            Player.getInstance().setMasterKeys(Player.getInstance().getMasterKeys() + 1);
            return true;
        }
        return false;
    }

    private static boolean isSwordCollected() {
        if (isPlayerInFrontOf(Variables.SWORD)) {
            Player.getInstance().ladeBild(CONFIG_PROP.getProperty("img.player.sword"));
            Player.getInstance().setSwords(Player.getInstance().getSwords() + 1);
            return true;
        }
        return false;
    }

    private static boolean isMoneyCollected() {
        if (isPlayerInFrontOf(Variables.MONEY)) {
            Sound.playSound(CONFIG_PROP.getProperty("sfx.money"));
            Player.getInstance().ladeBild(CONFIG_PROP.getProperty("img.player.money"));
            Player.getInstance().setMoney(Player.getInstance().getMoney() + ((Money) LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y]).getValue());
            return true;
        }
        return false;
    }

    private static boolean isLockOpened() {
        if (isPlayerInFrontOf(Variables.LOCK) && Player.getInstance().getKeys() > 0) {
            Player.getInstance().setKeys(Player.getInstance().getKeys() - 1);
            return true;
        }
        return false;
    }

    private static boolean isVillainDefeated() {
        //no weapong, no fight
        if (isPlayerInFrontOf(Variables.VILLAIN)) {
            if (Player.getInstance().getSwords() < 1) {
                Menu.getInstance().text(TXT_PROP.getProperty("txt.villain.001"));
            } else {
                Sound.playSound(CONFIG_PROP.getProperty("sfx.wpn.sword"));
                Player.getInstance().setSwords(Player.getInstance().getSwords() - 1);
                return true;
            }
        }
        return false;
    }

    private static void movePlayerOnField(char c) {
        if (isPlayerInFrontOfPerson()) return;

        if (isPlayerInFrontOf(Variables.BOX)) return;

        switch (c) {
            case 'w':
                if (isObstacle(c)) return;
                Player.getInstance().moveUp();
                break;
            case 'a':
                if (isObstacle(c)) return;
                Player.getInstance().moveLeft();
                break;
            case 's':
                if (isObstacle(c)) return;
                Player.getInstance().moveDown();
                break;
            case 'd':
                if (isObstacle(c)) return;
                Player.getInstance().moveRight();
                break;
            default:
                break;
        }

    }

    private static boolean isObstacle(char c) {
        if (isVillainDefeated() || isLockOpened() || isMoneyCollected() || isSwordCollected() || isKeyCollected() || isMasterKeyCollected()) {
            switch (c) {
                case 'w':
                    hideObject('y', -1);
                    break;
                case 'a':
                    hideObject('x', -1);
                    break;
                case 's':
                    hideObject('y', 1);
                    break;
                case 'd':
                    hideObject('x', 1);
                    break;
                default:
                    break;
            }
            return false;
        }

        if (isPlayerInFrontOf(Variables.OBSTACLE)) {
            Sound.playSound(CONFIG_PROP.getProperty("sfx.obstacle"));
            return true;
        }
        return false;
    }

    /*
     * Hide an object from the world.
     * @param axis must be 'x' or 'y'
     * @param distance must be '-1' or '1'
     */
    private static void hideObject(char axis, int distance) {
        if (distance != -1 && distance != 1)
            throw new IllegalArgumentException("Distance should be '-1' or '1'");
        switch (axis) {
            case 'x':
                x = distance;
                break;
            case 'y':
                y = distance;
                break;
            default:
                break;
        }
        //add new visited field to list
        LVL.getList().add(new Visited(Player.getPosX() + x, Player.getPosY() + y, LVL.getLvl()));
        //hide image from gameField
        LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y].verstecke();
        //remove object from gameField
        LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] = null;
    }

    //Enables or Disables the Cheat Mode, where you can bypass some obstacles.
    private static void cheatMode() {
        if (!isCheat) {
            isCheat = true;
            Player.getInstance().ladeBild(CONFIG_PROP.getProperty("img.player.cheat"));
            if (isBeenCheating()) return;
            setBeenCheating(true);
            Player.getInstance().setSteps(-1);
        } else {
            isCheat = false;
            Player.getInstance().ladeBild(CONFIG_PROP.getProperty("img.player.down"));
        }
    }

    //TODO: Every level needs own bg music, which should change by level
    public static void listenTo( Level<Visited> level) {
        level.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("lvl") && (evt.getNewValue().equals(4))) {
                Sound.playSoundLoop(CONFIG_PROP.getProperty("sfx.music"));
            } else {
                //TODO: avoid endless loop when leaving level 4
                Sound.stopSoundLoop();
            }
        });
    }
}
