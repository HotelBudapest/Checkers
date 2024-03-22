package Checkers;

import processing.core.PApplet;

public class Piece extends App{
    int x;
    int y;
    int size = 70;
    String type;

    public Piece(int x, int y, String type){
        this.x = x;
        this.y = y;
        this.type = type;
    }


    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol, PApplet app){
        try{
            /*String allowedTurn = "Black";
            String currentTurn = turn[z%2];

            if (currentTurn != allowedTurn){
                return false;
            }*/

            if (this.type.equals("Black")){
                int[] m = {fromRow, fromCol, toRow, toCol};
                for (int c = 0; c < m.length; c++){
                    if ((m[c] > 7) || (m[c] < 0)){
                        return false;
                    }
                }
                if ((Math.abs(toCol - fromCol) == Math.abs(toRow - fromRow)) && (fromRow - toRow == 2)){
                    if ((board[toRow][toCol] != null)){
                        //System.out.println("Tulus");
                        return false;
                    }
                    if ((board[(toRow + fromRow)/2][(toCol + fromCol)/2] == null)){
                        //System.out.println("Tulus");
                        return false;
                    }
                    else if (((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "Black")) || ((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "BK"))){
                        //System.out.println("Tulus");
                        return true;
                    }
                    else if (((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "White")) || ((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "WK"))){
                        //System.out.println("Tulus");
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                if ((Math.abs(toCol - fromCol) == Math.abs(toRow - fromRow)) && (fromRow - toRow == 1)){
                    if ((board[toRow][toCol] == null)){
                        return true;
                    } 
                    if ((board[toRow][toCol].type.equals("Black")) || (board[toRow][toCol].type.equals("BK"))){
                        //oldPiece = board[fromRow][fromCol];
                        if ((fromCol > toCol) && !(toCol - 1 < 0)){
                            if (board[toRow - 1][toCol - 1] == null){
                                return true;
                            }
                            else{
                                return false; 
                            }
                        }
                        else if ((fromCol < toCol) && !(toCol + 1 > 7)){
                            if (board[toRow - 1][toCol + 1] == null){
                                return true;
                            }
                            else{
                                return false;
                            }
                        }
                        else{
                            return false;
                        }
                    }
                    else if ((!board[toRow][toCol].type.equals("Black")) || (!board[toRow][toCol].type.equals("BK"))) {
                        //oldPiece = board[fromRow][fromCol];
                        return true;
                    }
                }
                else if ((Math.abs(toCol - fromCol) != Math.abs(toRow - fromRow))){
                    return false;
                }
            }
    
    

            if (this.type.equals("BK")){
                int[] m = {fromRow, fromCol, toRow, toCol};
                for (int c = 0; c < m.length; c++){
                    if ((m[c] > 7) || (m[c] < 0)){
                        return false;
                    }
                }
                if ((Math.abs(toCol - fromCol) == Math.abs(toRow - fromRow)) && (Math.abs(fromRow - toRow) == 2)){
                    if ((board[toRow][toCol] != null)){
                        //System.out.println("Tulus");
                        return false;
                    }
                    if ((board[(toRow + fromRow)/2][(toCol + fromCol)/2] == null)){
                        //System.out.println("Tulus");
                        return false;
                    }
                    else if (((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "Black")) || ((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "BK"))){
                        //System.out.println("Tulus");
                        return true;
                    }
                    else if (((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "White")) || ((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "WK"))){
                        //System.out.println("Tulus");
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                if ((Math.abs(toCol - fromCol) == Math.abs(toRow - fromRow)) && (Math.abs(fromRow - toRow) == 1)){
                    if ((board[toRow][toCol] == null)){
                        return true;
                    } 
                    if ((board[toRow][toCol].type.equals("Black")) || (board[toRow][toCol].type.equals("BK")) ){
                        //oldPiece = board[fromRow][fromCol];
                        if ((fromCol > toCol) && !(toCol - 1 < 0)){
                            if (board[toRow - 1][toCol - 1] == null){
                                return true;
                            }
                            else{
                                return false; 
                            }
                        }
                        else if ((fromCol < toCol) && !(toCol + 1 > 7)){
                            if (board[toRow - 1][toCol + 1] == null){
                                return true;
                            }
                            else{
                                return false;
                            }
                        }
                        else{
                            return false;
                        }
                    }
                    else if ((!board[toRow][toCol].type.equals("Black")) || (!board[toRow][toCol].type.equals("BK"))){
                        //oldPiece = board[fromRow][fromCol];
                        return true;
                    }
                }
                else if ((Math.abs(toCol - fromCol) != Math.abs(toRow - fromRow))){
                    return false;
                }
            }

    
    
            if (this.type.equals("White")){
                int[] m = {fromRow, fromCol, toRow, toCol};
                for (int c = 0; c < m.length; c++){
                    if ((m[c] > 7) || (m[c] < 0)){
                        return false;
                    }
                }
                if ((Math.abs(toCol - fromCol) == Math.abs(toRow - fromRow)) && (toRow - fromRow == 2)){
                    if ((board[toRow][toCol] != null)){
                        //System.out.println("Tulus");
                        return false;
                    }
                    if ((board[(toRow + fromRow)/2][(toCol + fromCol)/2] == null)){
                        //System.out.println("Tulus");
                        return false;
                    }
                    else if (((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "Black")) ||  ((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "BK"))){
                        //System.out.println("Tulus");
                        return true;
                    }
                    else if (((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "White")) || ((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "WK"))){
                        //System.out.println("Tulus");
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                if ((Math.abs(toCol - fromCol) == Math.abs(toRow - fromRow)) && (toRow - fromRow == 1)){
                    if ((board[toRow][toCol] == null)){
                        return true;
                    } 
                    if ((board[toRow][toCol].type.equals("White")) || (board[toRow][toCol].type.equals("WK"))){
                        //oldPiece = board[fromRow][fromCol];
                        if ((fromCol > toCol) && !(toCol - 1 < 0)){
                            if (board[toRow + 1][toCol - 1] == null){
                                return true;
                            }
                            else{
                                return false; 
                            }
                        }
                        else if ((fromCol < toCol) && !(toCol + 1 > 7)){
                            if (board[toRow +1][toCol + 1] == null){
                                return true;
                            }
                            else{
                                return false;
                            }
                        }
                        else{
                            return false;
                        }
                    }
                    else if ((!board[toRow][toCol].type.equals("White")) || (!board[toRow][toCol].type.equals("White"))){
                        //oldPiece = board[fromRow][fromCol];
                        return true;
                    }
                }
                else if ((Math.abs(toCol - fromCol) != Math.abs(toRow - fromRow))){
                    return false;
                }
            }


            if (this.type.equals("WK")){
                int[] m = {fromRow, fromCol, toRow, toCol};
                for (int c = 0; c < m.length; c++){
                    if ((m[c] > 7) || (m[c] < 0)){
                        return false;
                    }
                }
                if  ((Math.abs(toCol - fromCol) == Math.abs(toRow - fromRow)) && (Math.abs(fromRow - toRow) == 2)){
                    if ((board[toRow][toCol] != null)){
                        //System.out.println("Tulus");
                        return false;
                    }
                    if ((board[(toRow + fromRow)/2][(toCol + fromCol)/2] == null)){
                        //System.out.println("Tulus");
                        return false;
                    }
                    else if (((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "Black")) || ((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "BK"))){
                        //System.out.println("Tulus");
                        return true;
                    }
                    else if (((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "White")) || ((board[(toRow + fromRow)/2][(toCol + fromCol)/2].type == "WK"))){
                        //System.out.println("Tulus");
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                if ((Math.abs(toCol - fromCol) == Math.abs(toRow - fromRow)) && (Math.abs(fromRow - toRow) == 1)){
                    if ((board[toRow][toCol] == null)){
                        return true;
                    } 
                    if ((board[toRow][toCol].type.equals("White")) || (board[toRow][toCol].type.equals("White"))){
                        //oldPiece = board[fromRow][fromCol];
                        if ((fromCol > toCol) && !(toCol - 1 < 0)){
                            if (board[toRow + 1][toCol - 1] == null){
                                return true;
                            }
                            else{
                                return false; 
                            }
                        }
                        else if ((fromCol < toCol) && !(toCol + 1 > 7)){
                            if (board[toRow +1][toCol + 1] == null){
                                return true;
                            }
                            else{
                                return false;
                            }
                        }
                        else{
                            return false;
                        }
                    }
                    else if ((!board[toRow][toCol].type.equals("White")) || (!board[toRow][toCol].type.equals("White"))){
                        //oldPiece = board[fromRow][fromCol];
                        return true;
                    }
                }
                else if ((Math.abs(toCol - fromCol) != Math.abs(toRow - fromRow))){
                    return false;
                }
            }
        }
        catch(IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

    public void draw(PApplet app){
        if (!isAnimating){
            if (type.equals("Black")){
                app.fill(255, 255, 255);
                app.ellipse((this.x * App.CELLSIZE) +(App.CELLSIZE / 2), (this.y * App.CELLSIZE) + (App.CELLSIZE / 2), this.size, this.size);
                app.fill(0, 0, 0);
                app.ellipse((this.x * App.CELLSIZE) +(App.CELLSIZE / 2), (this.y * App.CELLSIZE) + (App.CELLSIZE / 2), this.size - 15, this.size - 15);
            }
           
            if (type.equals("White")){
                app.fill(0);
                app.ellipse((this.x * App.CELLSIZE) +(App.CELLSIZE / 2), (this.y * App.CELLSIZE) + (App.CELLSIZE / 2), this.size, this.size);
                app.fill(255);
                app.ellipse((this.x * App.CELLSIZE) +(App.CELLSIZE / 2), (this.y * App.CELLSIZE) + (App.CELLSIZE / 2), this.size - 15, this.size - 15);
            }
    
            if (type.equals("BK")){
                app.fill(255, 255, 255);
                app.ellipse((this.x * App.CELLSIZE) +(App.CELLSIZE / 2), (this.y * App.CELLSIZE) + (App.CELLSIZE / 2), this.size, this.size);
                app.fill(0, 0, 0);
                app.ellipse((this.x * App.CELLSIZE) +(App.CELLSIZE / 2), (this.y * App.CELLSIZE) + (App.CELLSIZE / 2), this.size - 15, this.size - 15);
                app.fill(255, 255, 255);
                app.ellipse((this.x * App.CELLSIZE) +(App.CELLSIZE / 2), (this.y * App.CELLSIZE) + (App.CELLSIZE / 2), this.size - 30, this.size-30);
                app.fill(0, 0, 0);
                app.ellipse((this.x * App.CELLSIZE) +(App.CELLSIZE / 2), (this.y * App.CELLSIZE) + (App.CELLSIZE / 2), this.size - 45, this.size - 45);
            }
    
            if (type.equals("WK")){
                app.fill(0);
                app.ellipse((this.x * App.CELLSIZE) +(App.CELLSIZE / 2), (this.y * App.CELLSIZE) + (App.CELLSIZE / 2), this.size, this.size);
                app.fill(255);
                app.ellipse((this.x * App.CELLSIZE) +(App.CELLSIZE / 2), (this.y * App.CELLSIZE) + (App.CELLSIZE / 2), this.size - 15, this.size - 15);
                app.fill(0);
                app.ellipse((this.x * App.CELLSIZE) +(App.CELLSIZE / 2), (this.y * App.CELLSIZE) + (App.CELLSIZE / 2), this.size - 30, this.size - 30);
                app.fill(255);
                app.ellipse((this.x * App.CELLSIZE) +(App.CELLSIZE / 2), (this.y * App.CELLSIZE) + (App.CELLSIZE / 2), this.size - 45, this.size - 45);
            }
        }
    }
}
