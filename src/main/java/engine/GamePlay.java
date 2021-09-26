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

/**
 * This is where the GamePlay is set.
 */
public class GamePlay {
    @Getter
    private static String gameLang = System.getProperty("user.language").equals("de") ? "de" : "en";
    @Getter @Setter
    private static boolean isCheat;
    @Getter @Setter
    private static boolean isBeenCheating;
    @Getter
    private static final Level<Visited> lvl = new Level<>(); // Level

    private static int x = 0; // x-axis distance from player
    private static int y = 0; // y-axis distance from player
    private static int x2 = 0; // x-axis distance from player +1
    private static int y2 = 0; // y-axis distance from player +1

    private static Properties properties = PropertiesLoader.getInstance().getProperties();

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
                Player.getInstance().setKeys(Integer.parseInt(JOptionPane.showInputDialog(Menu.getInstance().cmdKeys())));
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
                //load img, player shows up
                Player.getInstance().ladeBild(properties.getProperty("img.player.up"));
                y--;
                y2 = y - 1;
                checkBoxObstacle('y', -1);
                break;
            case 'a':
                Player.getInstance().ladeBild(properties.getProperty("img.player.left"));
                x--;
                x2 = x - 1;
                checkBoxObstacle('x', -1);
                break;
            case 's':
                Player.getInstance().ladeBild(properties.getProperty("img.player.down"));
                y++;
                y2 = y + 1;
                //no weapon, no fight
                if (isPlayerInFrontOf(Variables.VILLAIN) && Player.getInstance().getSwords() < 1) {
                    Menu.getInstance().text(Variables.DE_TXT_GEGNER_001);
                }
                checkBoxObstacle('y', 1);
                break;
            case 'd':
                Player.getInstance().ladeBild(properties.getProperty("img.player.right"));
                x++;
                x2 = x + 1;
                checkBoxObstacle('x', 1);
                break;
            default:
                break;
        }

        if (isPlayerInFrontOf(Variables.OBSTACLE)) {
            Sound.playSound(properties.getProperty("sfx.obstacle"));
        }

        playerInFrontOfPerson();

        if (c == 'w') {
            if (isPlayerInFrontOf(Variables.OPA) && Opa.getMasterKey() == 0) {
                Sound.playSound(properties.getProperty("sfx.txt"));
                Menu.getInstance().text(Variables.DE_TXT_OPA_003);
            } else if (isPlayerInFrontOf(Variables.OPA)) {
                if (Player.getInstance().getMoney() < 450) {
                    Sound.playSound(properties.getProperty("sfx.txt"));
                    Menu.getInstance().text(Variables.DE_TXT_OPA_001);
                } else {
                    lvl.getList().add(new Visited(Player.getPosX(), Player.getPosY() + y, lvl.getLvl()));
                    Player.getInstance().setMoney(Player.getInstance().getMoney() - 450);
                    Opa.setMasterKey(Opa.getMasterKey() - 1);
                    Player.getInstance().setMasterKeys(Player.getInstance().getMasterKeys() + 1);
                    Menu.getInstance().text(Variables.DE_TXT_OPA_002);
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
                Sound.playSound(properties.getProperty("sfx.wpn.sword"));
                Player.getInstance().setSwords(Player.getInstance().getSwords() - 1);
            } else if (isPlayerInFrontOf(Variables.MONEY)) {
                Sound.playSound(properties.getProperty("sfx.money"));
                Player.getInstance().ladeBild(Variables.IMG_PLAYER_MONEY);
                Player.getInstance().setMoney(Player.getInstance().getMoney() + ((Money) lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y]).getValue());
            } else if (isPlayerInFrontOf(Variables.SWORD)) {
                Player.getInstance().setSwords(Player.getInstance().getSwords() + 1);
                Player.getInstance().ladeBild(Variables.IMG_PLAYER_SWORD);
            } else if (isPlayerInFrontOf(Variables.KEY)) {
                Player.getInstance().setKeys(Player.getInstance().getKeys() + 1);
            } else if (isPlayerInFrontOf(Variables.LOCK)) {
                Player.getInstance().setKeys(Player.getInstance().getKeys() - 1);
            } else if (isPlayerInFrontOf(Variables.MASTERKEY)) {
                Sound.playSound(properties.getProperty("sfx.txt"));
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
        if (isPlayerInFrontOf(Variables.BOX) && lvl.getGameField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] instanceof Button) {
            //move Box on Button
            lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveDown();
            //remove block to enter next section in lvl 1
            lvl.getGameField()[18][14].verstecke();
            lvl.getGameField()[18][14] = null;
            //
            lvl.getGameField()[Player.getPosX()][Player.getPosY() + (y * 2)] = lvl.getGameField()[Player.getPosX()][Player.getPosY() + y];
            lvl.getGameField()[Player.getPosX()][Player.getPosY() + y] = null;
        }

        if (isPlayerInFrontOf(Variables.BOX) && !(lvl.getGameField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] instanceof GameObject)) {
            if (coord == 'x') {
                Sound.playSound(properties.getProperty("sfx.interaction"));
                if (r == 1) {
                    lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveRight();
                } else if (r == -1) {
                    lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveLeft();
                }
            } else {
                Sound.playSound(properties.getProperty("sfx.interaction"));
                if (r == -1) {
                    lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveUp();
                } else if (r == 1) {
                    lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y].moveDown();
                }
            }
            lvl.getGameField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] = lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y];
            lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] = null;
        } else if (isPlayerInFrontOf(Variables.BOX) && lvl.getGameField()[Player.getPosX() + (x * 2)][Player.getPosY() + (y * 2)] instanceof GameObject) {
            Sound.playSound(properties.getProperty("sfx.obstacle"));
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
                return lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Button;
            case Variables.BOX:
                return lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Box;
            case Variables.SWORD:
                return lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Sword;
            case Variables.MONEY:
                return lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Money;
            case Variables.PRINCESS:
                return lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Princess;
            case Variables.LOCK:
                return lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Lock;
            case Variables.OPA:
                return lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Opa;
            case Variables.NPC:
                return lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof NPC;
            case Variables.KEY:
                return lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Key;
            case Variables.MASTERKEY:
                return lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof MasterKey;
            case Variables.VILLAIN:
                return lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Villain;
            case Variables.OBSTACLE:
                return lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Obstacle && !GamePlay.isCheat();
            case Variables.STAIRS:
                return lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] instanceof Stairs;
            default:
                return false;
        }
    }

    public static void playerInFrontOfPerson() {
        if (isPlayerInFrontOf(Variables.PRINCESS)) {
            Sound.playSound(properties.getProperty("sfx.princess"));
            Menu.getInstance().text(Variables.DE_TXT_PRINCESS_001);
        } else if (isPlayerInFrontOf(Variables.OPA)) {
            Sound.playSound(properties.getProperty("sfx.opa"));
            Menu.getInstance().text(Variables.DE_TXT_OPA_004);
        }
    }

    private static void isPlayerOnStairs() {
        if (isPlayerInFrontOf(Variables.STAIRS)) {
            Sound.playSound(properties.getProperty("sfx.stairs"));
            //set lvl destination
            int newLvl = ((Stairs) lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y]).getLvl();
            //clear gameField
            for (int i = 0; i < Variables.FIELD_LENGTH_X; i++) {
                for (int j = 0; j < Variables.FIELD_LENGTH_Y; j++) {
                    if (lvl.getGameField()[i][j] != null) {
                        lvl.getGameField()[i][j].verstecke();
                    }
                    lvl.getGameField()[i][j] = null;
                }
            }
            lvl.setGameField(lvl.loadLvl(newLvl));
        }
    }

    private static void isPlayerOnPortal() {
        if (lvl.getGameField()[Player.getPosX()][Player.getPosY()] instanceof Portal) {
            //teleport player to another position
            Player.getInstance().go(((Portal) lvl.getGameField()[Player.getPosX()][Player.getPosY()]).getPosX(),
                    ((Portal) lvl.getGameField()[Player.getPosX()][Player.getPosY()]).getPosY());
            int xt = ((Portal) lvl.getGameField()[Player.getPosX()][Player.getPosY()]).getPosX();
            int yt = ((Portal) lvl.getGameField()[Player.getPosX()][Player.getPosY()]).getPosY();
            Player.setPosX(xt);
            Player.setPosY(yt);
        }
    }

    private static void movePlayerOnField(char c) {
        if (GamePlay.isCheat() || !(isPlayerInFrontOf(Variables.BOX)
                && (lvl.getGameField()[Player.getPosX() + x2][Player.getPosY() + y2] instanceof GameObject))
                && !(isPlayerInFrontOf(Variables.OBSTACLE))) {
            switch (c) {
                case 'w':
                    Player.getInstance().moveUp();
                    Player.setPosY(Player.getPosY() + y);
                    Player.getInstance().setSteps(GamePlay.isBeenCheating() ? -1 : Player.getInstance().getSteps() + 1);
                    break;
                case 'a':
                    Player.getInstance().moveLeft();
                    Player.setPosX(Player.getPosX() + x);
                    Player.getInstance().setSteps(GamePlay.isBeenCheating() ? -1 : Player.getInstance().getSteps() + 1);
                    break;
                case 's':
                    Player.getInstance().moveDown();
                    Player.setPosY(Player.getPosY() + y);
                    Player.getInstance().setSteps(GamePlay.isBeenCheating() ? -1 : Player.getInstance().getSteps() + 1);
                    break;
                case 'd':
                    Player.getInstance().moveRight();
                    Player.setPosX(Player.getPosX() + x);
                    Player.getInstance().setSteps(GamePlay.isBeenCheating() ? -1 : Player.getInstance().getSteps() + 1);
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
        lvl.getList().add(new Visited(Player.getPosX() + x, Player.getPosY() + y, lvl.getLvl()));
        //hide image from gameField
        lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y].verstecke();
        //remove object from gameField
        lvl.getGameField()[Player.getPosX() + x][Player.getPosY() + y] = null;
    }

    //Enables or Disables the Cheat Mode, where you can bypass some obstacles.
    private static void cheatMode() {
        if (!isBeenCheating()) {
            isCheat = true;
            Player.getInstance().ladeBild(properties.getProperty("img.player.cheat"));
            setBeenCheating(true);
        } else {
            isCheat = false;
            Player.getInstance().ladeBild(properties.getProperty("img.player.down"));
        }
    }
}
