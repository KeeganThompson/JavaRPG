package main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    //create bools for pressed button
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
    //DEBUG
    boolean showDebugText = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        //get the key pressed
        int code = e.getKeyCode();
        //if key == WASD do something
        
        //TITLE STATE
        if(gp.gameState == gp.titleState) {
            titleState(code);
        }
        //PLAYSTATE
        else if(gp.gameState == gp.playState) {
            playState(code);
        }
        //PAUSE STATE
        else if(gp.gameState == gp.pauseState) {
            pauseState(code);
        }
        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState) {
            dialogueState(code);
        }
        //CHARACTER STATE
        else if(gp.gameState == gp.characterState) {
            characterState(code);
        }
        //OPTIONS STATE
        else if(gp.gameState == gp.optionState) {
            optionState(code);
        }
        //GAME OVER STATE
        else if(gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
    }
    public void titleState(int code) {
        if(gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 1;
                }
                if(gp.ui.commandNum == 1) {
                    // add later
                }
                if(gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
            if(code == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
        else if(gp.ui.titleScreenState == 1) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER) {
                if(gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if(gp.ui.commandNum == 1) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if(gp.ui.commandNum == 2) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if(gp.ui.commandNum == 3) {
                    gp.ui.titleScreenState = 0;
                }
            }
            if(code == KeyEvent.VK_ESCAPE) {
                gp.ui.titleScreenState = 0;
            }
        }
    }
    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if(code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionState;
        }
        //DEBUG
        if(code == KeyEvent.VK_T) {
            if(showDebugText == false) {
                showDebugText = true;
            } else if(showDebugText == true) {
                showDebugText = false;
            }
        }
        if(code == KeyEvent.VK_R) {
            gp.tileM.loadMap("/maps/worldV2.txt"); //can change map in real time and see the changes
        }
    }
    public void pauseState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code) {
        if(code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }
    public void characterState(int code) {
        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_W) {
            if(gp.ui.slotRow != 0) {
                gp.ui.slotRow--;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_A) {
            if(gp.ui.slotCol != 0) {
                gp.ui.slotCol--;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_S) {
            if(gp.ui.slotRow != 3) {
                gp.ui.slotRow++;    
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_D) {
            if(gp.ui.slotCol != 4) {
                gp.ui.slotCol++;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
    }
    public void optionState(int code) {

        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch(gp.ui.subState) {
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }

        if(code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            gp.playSE(9);
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            gp.playSE(9);
            if(gp.ui.commandNum > 5) {
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_A) {
            if(gp.ui.subState == 0) {
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.playSE(9);
                }
            }
        }
        if(code == KeyEvent.VK_D) {
            if(gp.ui.subState == 0) {
                if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.playSE(9);
                }
            }
        }
    }
    public void gameOverState(int code) {

        if(code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(9);
        }
        if(code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(9);
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.retry();
            }
            else if(gp.ui.commandNum == 1) {
                gp.ui.titleScreenState = 0;
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        //get keypressed
        int code = e.getKeyCode();
        //if WASD not pressed dont do anything
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
    }
}