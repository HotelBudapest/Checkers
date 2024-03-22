package Checkers;

//import org.reflections.Reflections;
//import org.reflections.scanners.Scanners;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.core.PFont;
import processing.event.MouseEvent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import java.io.*;
import java.util.*;

public class App extends PApplet {

    private Piece selectedPiece;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private Piece previousSelected;
    private int prevselectedRow = -1;
    private int prevselectedCol = -1;
    protected String[] turn = {"Black", "White"};
    protected String[] turnKings = {"BK", "WK"};
    protected int z = 0;

    public static int WIDTH = 640;
    public static int HEIGHT = 640;
    public static final int BOARD_WIDTH = 8;

    public static Piece[][] board = new Piece[8][8];
    public static final int CELLSIZE = WIDTH/BOARD_WIDTH;
    public static final int SIDEBAR = 0;
    public static final int[] BLACK_RGB = {181, 136, 99};
    public static final int[] WHITE_RGB = {240, 217, 181};
    public static final float[][][] coloursRGB = new float[][][] {
        //default - white & black
        {
                {WHITE_RGB[0], WHITE_RGB[1], WHITE_RGB[2]},
                {BLACK_RGB[0], BLACK_RGB[1], BLACK_RGB[2]}
        },
        //green
        {
                {105, 138, 76}, //when on white cell
                {105, 138, 76} //when on black cell
        },
        //blue
        {
                {196,224,232},
                {170,210,221}
        }
	};

    public static final int FPS = 60;


    public App() {
        
    }

    /**
     * Initialise the setting of the window size.
    */
	@Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

	@Override
    public void setup() {
        frameRate(FPS);

		//Set up the data structures used for storing data in the game
        for (int i = 0; i < 3; i ++){
            for (int j = 0; j < BOARD_WIDTH; j++){
                if ((i +j)%2 != 0){
                    board[i][j] = new Piece(j, i, "White");
                }
            }
        }
        
        for (int i = 7; i > 4; i--){
            for (int j = 0; j < BOARD_WIDTH; j++){
                if (((i - 7) + j)%2 == 0){
                    board[i][j] = new Piece(j, i, "Black");
                }
            }
        }
    }

    /**
     * Receive key pressed signal from the keyboard.
    */
	@Override
    public void keyPressed(){

    }
    
    /**
     * Receive key released signal from the keyboard.
    */
	@Override
    public void keyReleased(){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Check if the user clicked on a piece which is theirs - make sure only whoever's current turn it is, can click on pieces
		
		//TODO: Check if user clicked on an available move - move the selected piece there. 
		//TODO: Remove captured pieces from the board
		//TODO: Check if piece should be promoted and promote it
		//TODO: Then it's the other player's turn.

        int col = mouseX / CELLSIZE;
        int row = mouseY / CELLSIZE;
        String allowedTurn = turn[z%2];
        String allowedKing = turnKings[z%2];
        if (board[row][col] == null || (board[row][col].type.equals(allowedTurn)) || (board[row][col].type.equals(allowedKing))){
            if ((col >= 0 && col < BOARD_WIDTH && row >= 0 && row < BOARD_WIDTH)){
                if (board[row][col] != null){
                    selectedPiece = board[row][col];
                    selectedRow = row;
                    selectedCol = col;

                    println(board[row][col].type + "clicked at: " + row + ", " + col);
                    previousSelected = selectedPiece;
                    prevselectedRow = row;
                    prevselectedCol = col;
                }
                else if ((board[row][col] == null) && (previousSelected != null) && (previousSelected.isValidMove(prevselectedRow, prevselectedCol, row, col, this))){
                    println("true");
                    if ((previousSelected.type == "BK") && (Math.abs(prevselectedRow - row) == 2) && ((board[(prevselectedRow + row)/2][(prevselectedCol + col)/2].type == "White") || (board[(prevselectedRow + row)/2][(prevselectedCol + col)/2].type == "WK"))){
                        board[(prevselectedRow + row)/2][(prevselectedCol + col)/2] = null;
                    }
                    if ((previousSelected.type == "Black")){
                        if ((prevselectedRow - row == 2) && ((board[(prevselectedRow + row)/2][(prevselectedCol + col)/2].type == "White") || (board[(prevselectedRow + row)/2][(prevselectedCol + col)/2].type == "WK"))){
                            board[(prevselectedRow + row)/2][(prevselectedCol + col)/2] = null;
                        }
                        if (row == 0){
                            previousSelected.type = "BK";
                        }
                    }
                    if ((previousSelected.type == "WK") && (Math.abs(row - prevselectedRow )== 2) && ((board[(prevselectedRow + row)/2][(prevselectedCol + col)/2].type == "Black") || (board[(prevselectedRow + row)/2][(prevselectedCol + col)/2].type == "BK"))){
                        board[(prevselectedRow + row)/2][(prevselectedCol + col)/2] = null;
                    }
                    if ((previousSelected.type == "White")){
                        if ((row - prevselectedRow == 2) && ((board[(prevselectedRow + row)/2][(prevselectedCol + col)/2].type == "Black") || (board[(prevselectedRow + row)/2][(prevselectedCol + col)/2].type == "BK"))){
                            board[(prevselectedRow + row)/2][(prevselectedCol + col)/2] = null;
                        }
                        if (row == 7){
                            previousSelected.type = "WK";
                        }
                    }
                    board[row][col] = previousSelected;
                    previousSelected.x = col;
                    previousSelected.y = row;
                    board[prevselectedRow][prevselectedCol] = null;
                    previousSelected = null;
                    prevselectedRow = -1;
                    prevselectedCol = -1;
                    selectedPiece = null;
                    selectedRow = -1;
                    selectedCol = -1;
                    z++;
                }
                else {
                    selectedPiece = null;
                    selectedRow = -1;
                    selectedCol = -1;
                    println("Empty cell clicked at: " + row + ", " + col);
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    /**
     * Draw all elements in the game by current frame. 
    */
	@Override
    public void draw() {
        this.noStroke();
        int whiteLeft = 0;
        int blackLeft = 0;
		//draw the board
		for (int i = 0; i < BOARD_WIDTH; i++){
            for (int j = 0; j < BOARD_WIDTH; j++){
                if ((i+j)%2 == 1){
                    fill(133, 94, 37);
                }
                else{
                    fill(217, 167, 80);
                }
                rect(i*CELLSIZE, j*CELLSIZE, CELLSIZE, CELLSIZE);
            }
        }

        for (int i = 0; i < BOARD_WIDTH; i ++){
            for (int j = 0; j < BOARD_WIDTH; j++){
                if (board[i][j] != null){
                    board[i][j].draw(this);
                    if ((board[i][j].type == "White") || (board[i][j].type == "WK")){
                        whiteLeft++;
                    }
                    else if ((board[i][j].type == "Black") || (board[i][j].type == "BK")){
                        blackLeft++;
                    }
                }
            }
        }

        if (whiteLeft == 0){
            endGame("Black");
        }
        if (blackLeft == 0){
            endGame("White");
        }


        if (selectedPiece != null) {
            for (int l = 0; l < BOARD_WIDTH; l++) {
                for (int m = 0; m < BOARD_WIDTH; m++) {
                    if (selectedPiece.isValidMove(selectedRow, selectedCol, m, l, this)){
                        if (board[m][l] == null){
                            fill(105, 158, 76, 5); 
                            rect(selectedCol*CELLSIZE, selectedRow*CELLSIZE, CELLSIZE, CELLSIZE);
                            fill(0, 0, 235, 128);
                            rect(l * CELLSIZE, m * CELLSIZE, CELLSIZE, CELLSIZE);
                        }
                    }
                }
            }
        }
    }


    public void endGame(String winner){
        if (winner.equals("White")){
            noLoop();
            background(133, 94, 37);
            textAlign(CENTER, CENTER);
            textSize(32);
            text("White Wins!", WIDTH/2, HEIGHT/2);
            
            println("white wins");
        }
        else if (winner.equals("Black")){
            noLoop();
            background(133, 94, 37);
            textAlign(CENTER, CENTER);
            textSize(32);
            text("Black Wins!", WIDTH/2, HEIGHT/2);
            
            println("Black wins");
        }
    }
		//draw highlighted cells
        
		//check if the any player has no more pieces. The winner is the player who still has pieces remaining
	
	/**
     * Set fill colour for cell background
     * @param colourCode The colour to set
     * @param blackOrWhite Depending on if 0 (white) or 1 (black) then the cell may have different shades
     */
	public void setFill(int colourCode, int blackOrWhite) {
		this.fill(coloursRGB[colourCode][blackOrWhite][0], coloursRGB[colourCode][blackOrWhite][1], coloursRGB[colourCode][blackOrWhite][2]);
	}

    public static void main(String[] args) {
        PApplet.main("Checkers.App");
    }


}
