package final_difficult;

import processing.core.PApplet;


public class FinalExamDifficultCorrectAnswer extends PApplet {

    // Number of columns and rows in the grid
    public int size = 21;

    public Cell[][] cells = new Cell[size][size];
    public int[] movableCellColor = new int[]{255, 0, 0}; // Initially set to RED

    // Width and height of each cell in the grid
    public int widthCell = 40;
    public int heightCell = 40;

    public long countText = -4000;
    public String inputText = "Input \"I L U W S A D C\" to play the game.";

    public void drawInputText(){
        if(countText <= -2){
            textSize(42);
            fill(0);
            text(inputText, 10, 400);
        }
        else if(countText == -1){
            inputText = "";
        }
        else if(countText <= 25){
            textSize(80);
            fill(0);
            text(inputText, 400, 400);
        }
    }

    public void setInputText(String _inputText){
        countText = 1;
        inputText = _inputText;
    }

    public void keyPressed() {
        setInputText(String.valueOf(key).toUpperCase());
        switch (String.valueOf(key).toLowerCase()){
            case "c":
                changeColor();
                break;
            case "w":
                moveUp();
                break;
            case "s":
                moveDown();
                break;
            case "a":
                moveLeft();
                break;
            case "d":
                moveRight();
                break;
            case "i":
                setICells(); // Original Heart Position and Color
                break;
            case "l":
                setHeartCells(); // Original Heart Position and Color
                break;
            case "u":
                setYouCells(); // Original Heart Position and Color
                break;
            default:
                inputText = "Input \"I L U W S A D C\" to play the game.";
                countText = -100;
                break;
        }
    }

    public void moveUp(){
        // TODO: write your code here.
        //  You should never use "new Cell()". Therefore, at each position of the cells,
        //  the cell object won't change and maintains the same reference from the beginning
        //  to the end of the program.
        //  P.S. all Cell objects have already been instantiated by createCell().

        for(int j = 0; j < size - 1; j ++)
            for(int i = 0; i < size; i ++)
                cells[i][j].isMovable = cells[i][j+1].isMovable;

        for(int i = 0; i < size; i ++)
            cells[i][size - 1].isMovable = false;
    }

    public void moveDown(){
        // TODO: write your code here.
        //  You should never use "new Cell()". Therefore, at each position of the cells,
        //  the cell object won't change and maintains the same reference from the beginning
        //  to the end of the program.
        //  P.S. all Cell objects have already been instantiated by createCell().

        for(int j = size - 1; j >= 1; j --)
            for(int i = 0; i < size; i ++)
                cells[i][j].isMovable = cells[i][j-1].isMovable;

        for(int i = 0; i < size; i ++)
            cells[i][0].isMovable = false;
    }

    public void moveLeft(){
        // TODO: write your code here.
        //  You should never use "new Cell()". Therefore, at each position of the cells,
        //  the cell object won't change and maintains the same reference from the beginning
        //  to the end of the program.
        //  P.S. all Cell objects have already been instantiated by createCell().

        for(int j = 0; j < size; j ++)
            for(int i = 0; i < size - 1; i ++)
                cells[i][j].isMovable = cells[i + 1][j].isMovable;

        for(int j = 0; j < size; j ++)
            cells[size - 1][j].isMovable = false;
    }

    public void moveRight(){
        // TODO: write your code here.
        //  You should never use "new Cell()". Therefore, at each position of the cells,
        //  the cell object won't change and maintains the same reference from the beginning
        //  to the end of the program.
        //  P.S. all Cell objects have already been instantiated by createCell().

        for(int j = 0; j < size; j ++)
            for(int i = size - 1; i >= 1; i --)
                cells[i][j].isMovable = cells[i - 1][j].isMovable;

        for(int j = 0; j < size; j ++)
            cells[0][j].isMovable = false;

    }

    public void changeColor() {
        movableCellColor = new int[]{ (int) random(255), (int)random(255), (int)random(255)};
    }


    public void settings() {
        size(widthCell * size, heightCell * size);
        createCells();
        clear();
        setHeartCells();
    }

    public void draw() {
        background(0);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                display(cells[i][j]);
            }
        }
        drawInputText();
        countText ++;
        surface.setTitle("LOVE IS A GAME. NOT AN EXAM.");
    }

    public void setHeartCells(){
        clear();
        movableCellColor = new int[]{255, 0, 0};

        final int N = 3;
        final int jOffset = 6;
        final int iOffset = 4;
        for (int j = 0; j < N; j++) {
            for (int i = 0; i <= 4 * N; i++) {
                double d1 = Math.sqrt(Math.pow(j - N, 2)
                        + Math.pow(i - N, 2));
                double d2 = Math.sqrt(Math.pow(j - N, 2)
                        + Math.pow(i - 3 * N, 2));
                if (d1 < N + 0.5 || d2 < N + 0.5) {
                    cells[i + iOffset][j + jOffset].isMovable = true;
                }
            }
        }
        for (int j = N; j < 3 * N; j++) {
            for (int i = 0; i < 4 * N + 1 - 2 * (j - N + 1); i++) {
                cells[i + iOffset + j - N + 1][j + jOffset].isMovable = true;
            }
        }
    }


    public void setICells() {
        clear();
        movableCellColor = new int[]{255, 0, 0};

        for(int j = 6; j <= 14; j ++)
            for(int i = 0; i <= size; i ++){
                if(i == 11 || i == 9 || i == 10)
                    cells[i][j].isMovable = true;
            }
    }

    public void setYouCells() {
        clear();
        movableCellColor = new int[]{255, 0, 0};

        for(int j = 6; j <= 13; j ++)
            for(int i = 0; i <= size; i ++){
                if(i == 6 || i == 7 || i == 13 || i == 14)
                    cells[i][j].isMovable = true;
            }

        for(int i = 8; i <= 12; i ++) {
            cells[i][13].isMovable = true;
        }

        for(int i = 7; i <= 13; i ++) {
            cells[i][14].isMovable = true;
        }
    }

    public void createCells(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Initialize each Cell object
                cells[i][j] = new Cell(i * widthCell,j * heightCell,
                        widthCell, heightCell);
            }
        }
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].isMovable = false;
            }
        }
    }

    public void display(Cell cell){
        stroke(255);
        if(cell.isMovable)
            fill(movableCellColor[0], movableCellColor[1], movableCellColor[2]); // Cell in RED
        else
            fill(200, 200, 200); // Cell in GREY
        rect(cell.x, cell.y, cell.w, cell.h);
    }

    public static void main(String[] args) {
        String[] processingArgs = {"LOVE IS A GAME. NOT AN EXAM."};
        FinalExamDifficultCorrectAnswer finalExamDifficultCorrectAnswer = new FinalExamDifficultCorrectAnswer();
        PApplet.runSketch(processingArgs, finalExamDifficultCorrectAnswer);
    }
}
