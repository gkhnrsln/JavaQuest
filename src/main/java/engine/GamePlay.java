/*
 * Copyright (C) 2021  G. Arslan
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
    @Getter @Setter
    private static boolean isCheat;
    @Getter @Setter
    private static boolean isBeenCheating;
    @Getter
    private static final Level<Visited> LVL = new Level<>(); // Level
    private static final Properties CONFIG_PROP = new PropertiesLoader("config", false).getProperties();
    private static final Properties TXT_PROP = new PropertiesLoader("text", true).getProperties();

    private static int x = 0; // x-axis distance from player
    private static int y = 0; // y-axis distance from player
    private static int x2 = 0; // x-axis distance from player +1
    private static int y2 = 0; // y-axis distance from player +1

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
                GamePlay.cheatMode();
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
        //distance from player
        x = 0;
        y = 0;
        //distance from player +1
        x2 = 0;
        y2 = 0;

        switch (c) {
            case 'w':
                y--;
                y2 = y - 1;
                checkBoxObstacle('y', -1);
                break;
            case 'a':
                x--;
                x2 = x - 1;
                checkBoxObstacle('x', -1);
                break;
            case 's':
                y++;
                y2 = y + 1;
                checkBoxObstacle('y', 1);
                break;
            case 'd':
                x++;
                x2 = x + 1;
                checkBoxObstacle('x', 1);
                break;
            default:
                break;
        }

        //no weapon, no fight
        if (isPlayerInFrontOf(Variables.VILLAIN) && Player.getInstance().getSwords() < 1) {
            Menu.getInstance().text(TXT_PROP.getProperty("txt.villain.001"));
        }

        if (isPlayerInFrontOf(Variables.OBSTACLE)) {
            Sound.playSound(CONFIG_PROP.getProperty("sfx.obstacle"));
        }

        playerInFrontOfPerson();

        if (c == 'w') {
            if (isPlayerInFrontOf(Variables.OPA) && Opa.getMasterKey() == 0) {
                Sound.playSound(CONFIG_PROP.getProperty("sfx.txt"));
                Menu.getInstance().text(TXT_PROP.getProperty("txt.opa.003"));
            } else if (isPlayerInFrontOf(Variables.OPA)) {
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
        }

        if (isPlayerInFrontOf(Variables.LOCK) && Player.getInstance().getKeys() > 1
                || isPlayerInFrontOf(Variables.VILLAIN) && Player.getInstance().getSwords() > 0
                || isPlayerInFrontOf(Variables.LOCK) && Player.getInstance().getKeys() > 2
                || isPlayerInFrontOf(Variables.LOCK) && Player.getInstance().getMasterKeys() > 0
                || isPlayerInFrontOf(Variables.MONEY) || isPlayerInFrontOf(Variables.SWORD)
                || isPlayerInFrontOf(Variables.KEY) || isPlayerInFrontOf(Variables.MASTERKEY)
                || isPlayerInFrontOf(Variables.LOCK) && Player.getInstance().getKeys() > 0) {
            if (isPlayerInFrontOf(Variables.VILLAIN)) {
                Sound.playSound(CONFIG_PROP.getProperty("sfx.wpn.sword"));
                Player.getInstance().setSwords(Player.getInstance().getSwords() - 1);
            } else if (isPlayerInFrontOf(Variables.MONEY)) {
                Sound.playSound(CONFIG_PROP.getProperty("sfx.money"));
                Player.getInstance().ladeBild(CONFIG_PROP.getProperty("img.player.money"));
                Player.getInstance().setMoney(Player.getInstance().getMoney() + ((Money) LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y]).getValue());
            } else if (isPlayerInFrontOf(Variables.SWORD)) {
                Player.getInstance().setSwords(Player.getInstance().getSwords() + 1);
                Player.getInstance().ladeBild(CONFIG_PROP.getProperty("img.player.sword"));
            } else if (isPlayerInFrontOf(Variables.KEY)) {
                Player.getInstance().setKeys(Player.getInstance().getKeys() + 1);
            } else if (isPlayerInFrontOf(Variables.LOCK)) {
                Player.getInstance().setKeys(Player.getInstance().getKeys() - 1);
            } else if (isPlayerInFrontOf(Variables.MASTERKEY)) {
                Sound.playSound(CONFIG_PROP.getProperty("sfx.txt"));
                Player.getInstance().setMasterKeys(Player.getInstance().getMasterKeys() + 1);
            } else if (isPlayerInFrontOf(Variables.LOCK) && Player.getInstance().getMasterKeys() > 0) {
                Player.getInstance().setMasterKeys(Player.getInstance().getMasterKeys() - 1);
            }

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
        }
        isPlayerOnStairs();
        isPlayerOnPortal();
        //move Player image
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

        //player is in front of box, box is in front of button
        if (isPlayerInFrontOf(Variables.BOX) && LVL.getGameField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] instanceof Button) {
            //move Box on Button
            LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveDown();
            //remove block to enter next section in lvl 1
            LVL.getGameField()[18][14].verstecke();
            LVL.getGameField()[18][14] = null;
            //
            LVL.getGameField()[Player.getPosX()][Player.getPosY() + (y * 2)] = LVL.getGameField()[Player.getPosX()][Player.getPosY() + y];
            LVL.getGameField()[Player.getPosX()][Player.getPosY() + y] = null;
        }

        if (isPlayerInFrontOf(Variables.BOX) && !(LVL.getGameField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] instanceof GameObject)) {
            if (coord == 'x') {
                Sound.playSound(CONFIG_PROP.getProperty("sfx.interaction"));
                if (r == 1) {
                    LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveRight();
                } else if (r == -1) {
                    LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveLeft();
                }
            } else {
                Sound.playSound(CONFIG_PROP.getProperty("sfx.interaction"));
                if (r == -1) {
                    LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveUp();
                } else if (r == 1) {
                    LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveDown();
                }
            }
            LVL.getGameField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] = LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y];
            LVL.getGameField()[Player.getPosX() + x][Player.getPosY() + y] = null;
        } else if (isPlayerInFrontOf(Variables.BOX) && LVL.getGameField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] instanceof GameObject) {
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

    private static void playerInFrontOfPerson() {
        if (isPlayerInFrontOf(Variables.PRINCESS)) {
            Sound.playSound(CONFIG_PROP.getProperty("sfx.princess"));
            Menu.getInstance().text(TXT_PROP.getProperty("txt.princess.001"));
        } else if (isPlayerInFrontOf(Variables.OPA)) {
            Sound.playSound(CONFIG_PROP.getProperty("sfx.opa"));
            Menu.getInstance().text(TXT_PROP.getProperty("txt.opa.004"));
        }
    }

    private static void isPlayerOnStairs() {
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
        }
    }

    private static void isPlayerOnPortal() {
        if (LVL.getGameField()[Player.getPosX()][Player.getPosY()] instanceof Portal) {
            //teleport player to another position
            Player.getInstance().go(((Portal) LVL.getGameField()[Player.getPosX()][Player.getPosY()]).getPosX(),
                    ((Portal) LVL.getGameField()[Player.getPosX()][Player.getPosY()]).getPosY());
            int xt = ((Portal) LVL.getGameField()[Player.getPosX()][Player.getPosY()]).getPosX();
            int yt = ((Portal) LVL.getGameField()[Player.getPosX()][Player.getPosY()]).getPosY();
            Player.setPosX(xt);
            Player.setPosY(yt);
        }
    }

    private static void movePlayerOnField(char c) {
        if (GamePlay.isCheat() || !(isPlayerInFrontOf(Variables.BOX)
                && (LVL.getGameField()[Player.getPosX() + x2][Player.getPosY() + y2] instanceof GameObject))
                && !(isPlayerInFrontOf(Variables.OBSTACLE))) {
            switch (c) {
                case 'w':
                    Player.getInstance().moveUp();
                    Player.setPosY(Player.getPosY() + y);
                    break;
                case 'a':
                    Player.getInstance().moveLeft();
                    Player.setPosX(Player.getPosX() + x);
                    break;
                case 's':
                    Player.getInstance().moveDown();
                    Player.setPosY(Player.getPosY() + y);
                    break;
                case 'd':
                    Player.getInstance().moveRight();
                    Player.setPosX(Player.getPosX() + x);
                    break;
                default:
                    break;
            }
        }
    }

    /*
     * Hide an object from the world.
     * @param t must be 'x' or 'y'
     * @param r must be '-1' or '1'
     */
    private static void hideObject(char t, int r) {
        switch (t) {
            case 'x':
                x = r;
                break;
            case 'y':
                y = r;
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
        if (!isBeenCheating()) {
            isCheat = true;
            Player.getInstance().ladeBild(CONFIG_PROP.getProperty("img.player.cheat"));
            setBeenCheating(true);
            Player.getInstance().setSteps(-1);
        } else {
            isCheat = false;
            Player.getInstance().ladeBild(CONFIG_PROP.getProperty("img.player.down"));
        }
    }
}
