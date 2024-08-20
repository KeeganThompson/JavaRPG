package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[200];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/worldV3.txt");
    }


    public void getTileImage() {
            //placeholder
            setup(0, "grass00", false);
            setup(1, "grass00", false);
            setup(2, "grass00", false);
            setup(3, "grass00", false);
            setup(4, "grass00", false);
            setup(5, "grass00", false);
            setup(6, "grass00", false);
            setup(7, "grass00", false);
            setup(8, "grass00", false);
            setup(9, "grass00", false);
            //placeholder
            setup(10, "grass00", false);
            setup(11, "grass01", false);
            setup(12, "water00", true);
            setup(13, "water01", true);
            setup(14, "water02", true);
            setup(15, "water03", true);
            setup(16, "water04", true);
            setup(17, "water05", true);
            setup(18, "water06", true);
            setup(19, "water07", true);
            setup(20, "water08", true);
            setup(21, "water09", true);
            setup(22, "water10", true);
            setup(23, "water11", true);
            setup(24, "water12", true);
            setup(25, "water13", true);
            setup(26, "road00", false);
            setup(27, "road01", false);
            setup(28, "road02", false);
            setup(29, "road03", false);
            setup(30, "road04", false);
            setup(31, "road05", false);
            setup(32, "road06", false);
            setup(33, "road07", false);
            setup(34, "road08", false);
            setup(35, "road09", false);
            setup(36, "road10", false);
            setup(37, "road11", false);
            setup(38, "road12", false);
            setup(39, "earth", false);
            setup(40, "wall", true);
            setup(41, "tree", true);


            setup(42, "floor_1", false);
            setup(43, "floor_2", false);
            setup(44, "floor_3", false);
            setup(45, "floor_4", false);
            setup(46, "floor_5", false);
            setup(47, "floor_6", false);
            setup(48, "floor_7", false);
            setup(49, "column", false);
            setup(50, "column_wall", false);
            setup(51, "doors_frame_left", false);
            setup(52, "doors_frame_right", false);
            setup(53, "doors_frame_top", false);
            setup(54, "floor_stairs", false);
            setup(55, "hole", false);
            setup(56, "wall_left", false);
            setup(57, "wall_edge_bottom_left", false);
            setup(58, "wall_edge_bottom_right", false);
            setup(59, "wall_edge_top_left", false);
            setup(60, "wall_edge_top_right", false);
            setup(61, "wall", false);
            setup(62, "wall_edge_tshape_bottom_left", false);
            setup(63, "wall_edge_tshape_bottom_right", false);
            setup(64, "wall_edge_tshape_left", false);
            setup(65, "wall_edge_tshape_right", false);
            setup(66, "wall_mid", false);
            setup(67, "wall_right", false);
            setup(68, "wall_top_left", false);
            setup(69, "wall_top_right", false);
            setup(70, "wall_top_mid", false);
            

    }
    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath) {
        try {
        InputStream is = getClass().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            String line = br.readLine();
            
            while (col < gp.maxWorldCol) {
                String numbers[] = line.split(" ");
                int num = Integer.parseInt(numbers[col]);

                mapTileNum[col][row] = num;
                col++;
            }
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
        br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
                
            }
        }
    }
}
