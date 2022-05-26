import java.util.Scanner;
public class DiamondRush {
    private int grid[][];
    private int updateGrid[][];
    public int turnsAmount;
    private int randomPatch[];
    private int randomPatchPointer;
    private int turn;
    private int playerOneWheat;
    private int playerOneStone;
    private int playerTwoWheat;
    private int playerTwoStone;
    private int playerOneCities[][];
    private int playerOneCitiesAmount = 1;
    private int playerOneCitiesToPlace;
    private int playerTwoCities[][];
    private int playerTwoCitiesAmount = 1;
    private int playerTwoCitiesToPlace;
    private int playerOneWorkers;
    private int playerTwoWorkers;
    private int playerOneSoldiers;
    private int playerTwoSoldiers;
    private int playerOneNodeTracker[][];
    private int playerOneWorkerNodeTracker[][];
    private int playerTwoNodeTracker[][];
    private int playerTwoWorkerNodeTracker[][];
    private boolean gameOver;
    Scanner scan = new Scanner(System.in);

    DiamondRush (){

        this.grid = new int[35][200];
        this.playerOneNodeTracker = new int[36][5];
        this.playerOneWorkerNodeTracker = new int[36][2];
        this.playerTwoNodeTracker = new int[36][5];
        this.playerTwoWorkerNodeTracker = new int[36][2];
        this.turnsAmount = 1;
        this.turn = 1;
        this.playerOneStone = 5;
        this.playerTwoStone = 5;
        int[] newrandomPatch ={1,2,3,4,2,3,4,1,2,3,1,1,3,2,2,4,1,4,2,3,4,1,3,2,4,4,2,};

        // 1 is up, 2 is right, 3 is down, 4 is left
        this.randomPatch = newrandomPatch;
        this.randomPatchPointer = 0;
        //in the 2d array that is the player and two cities, the first value of each row represents if there is a city, the next 4 values represent
        //if a road has been been starting in each possible direction, each city can have 4 roads, one on the right, left, top and bottom of the city.
        // if the value of the first row is one, that means theres a city, if the next value is 1 that means there is a leftside road, if the next value
        // is one that means theres a top road, and ETC.
        this.playerOneCities = new int[9][5];
        this.playerOneCities[0][0] = 1;
        this.playerTwoCities = new int[9][5];
        this.playerTwoCities[0][0] = 1;
        this.playerOneWorkers = 1;
        this.playerTwoWorkers = 1;


        //randomly fills map with grass
        int grass = 60 + (int)(Math.random() * ((80 - 60) + 1));
        for (int i = 0; i < grass; i++){
            int x = 2 + (int)(Math.random() * ((32 - 2) + 1));
            int y = 2 + (int)(Math.random() * ((198 - 2) + 1));
            grid[x][y]= 22;
            int patchSize = 2 + (int)(Math.random() * ((8 - 2) + 1));
            for (int z = 0; z < patchSize; z ++){
                int whichWay = randomPatch[randomPatchPointer];
                if (whichWay == 1){if (grid[x][y-1] != 25 ||grid[x][y-1] != 26 ){grid[x][y-1]= 22; y--; }}
                if (whichWay == 2){if (grid[x+1][y] !=25 ||grid[x+1][y] != 26 ){grid[x][y-1]= 22; x++; }}
                if (whichWay == 3){if (grid[x][y+1] != 25 ||grid[x][y+1] != 26 ){grid[x][y-1]= 22; y++; }}
                if (whichWay == 4){if (grid[x-1][y] != 25 ||grid[x-1][y] != 26 ){grid[x][y-1]= 22; x--; }}
                if (randomPatchPointer < 26){
                    this.randomPatchPointer++;
                }
                else {this.randomPatchPointer = 0;}
            }
        }
        //randomly fills map with tree
        int tree = 30 + (int)(Math.random() * ((70 - 30) + 1));
        for (int i = 0; i < tree; i++){
            int x = 2 + (int)(Math.random() * ((32 - 2) + 1));
            int y = 2 + (int)(Math.random() * ((198 - 2) + 1));
            grid[x][y]= 23;
        }
        //Wheat randomizing
        int top = 1;
        int lengthIncriment = 10;
        int LI2 = lengthIncriment + 8;

        for (int i = 0; i < 20; i++){
            top = top * -1;
            int x = 0;
            int y = 0;
            lengthIncriment = lengthIncriment + 8;
            LI2 = LI2 + 8;
            if (top == 1){ x = 4 + (int)(Math.random() * ((14 - 4) + 1));}
            else { x = 18 + (int)(Math.random() * ((30 - 18) + 1));}

            y = lengthIncriment + (int)(Math.random() * ((LI2 - lengthIncriment) + 1));



            grid[x][y]= 22;
            int patchSize = 10 + (int)(Math.random() * ((20 - 10) + 1));
            for (int z = 0; z < patchSize; z ++){
                int whichWay = randomPatch[randomPatchPointer];
                if (whichWay == 1){if (i % 3 != 2){grid[x][y-1]= 19; y--; } else{grid[x][y-1]= 21; y--;} }
                if (whichWay == 2){if (i % 3 != 2){grid[x+1][y]= 19; x++; } else{grid[x+1][y]= 21; x++;} }
                if (whichWay == 3){if (i % 3 != 2){grid[x][y+1]= 19; y++; } else{grid[x][y+1]= 21; y++;} }
                if (whichWay == 4){if (i % 3 != 2){grid[x-1][y]= 19; x--; } else{grid[x-1][y]= 21; x--;} }
                if (randomPatchPointer < 26){
                    this.randomPatchPointer++;
                }
                else {this.randomPatchPointer = 0;}
            }

        }
        //Wheat randomizing
        for (int x = 0;x < 34; x++){
            for (int y = 0;y < 200; y++){
              if (grid[x][y] == 19){
                  if (grid[x+1][y] != 19 ){ grid[x+1][y] = 28;}
                  if (grid[x-1][y] != 19 ){ grid[x-1][y] = 28;}
                  if (grid[x][y+1] != 19 ){ grid[x][y+1] = 28;}
                  if (grid[x][y-1] != 19 ){ grid[x][y-1] = 28;}
              }
                if (grid[x][y] == 21){
                    if (grid[x+1][y] != 21 ){ grid[x+1][y] = 28;}
                    if (grid[x-1][y] != 21 ){ grid[x-1][y] = 28;}
                    if (grid[x][y+1] != 21 ){ grid[x][y+1] = 28;}
                    if (grid[x][y-1] != 21 ){ grid[x][y-1] = 28;}
                }
            }

        }
        //prints the borders of the map
        for (int x = 0;x < 34; x++){
            for (int y = 0;y < 200; y++){
                if (x == 0 || x == 32|| x == 33|| x == 1){
                    grid[x][y]= 26;
                }
                if (y == 0 || y == 199|| y == 198|| y == 1){
                    grid[x][y]= 25;

                }
            }
        }
        for (int i = 0; i < 12; i++){
            for (int j = 0; j < 19; j++)
            {

                grid[i+11][j+177] = 0;
            }
        }


        //**********Corners*************
        grid[0][0] = 38;
        grid[0][1] = 26;
        grid[1][1] = 38;
        grid[0][199] = 39;
        grid[0][198] = 26;
        grid[1][198] = 39;
        grid[33][0] = 37;
        grid[33][1] = 26;
        grid[32][1] = 37;
        grid[33][199] = 40;
        grid[33][198] = 26;
        grid[32][198] = 40;

        //*********starting settlements**********
        grid[5][8] = 1;
        grid[28][8] = 10;

        //**********Labrynth ***********
        grid[11][196] = 39;
        grid[12][196] = 25;
        grid[13][196] = 25;
        grid[14][196] = 25;
        grid[15][196] = 25;
        grid[16][196] = 25;
        grid[17][196] = 25;
        grid[18][196] = 25;
        grid[19][196] = 25;
        grid[20][196] = 25;
        grid[21][196] = 25;
        grid[22][196] = 25;
        grid[23][196] = 40;
        grid[11][177] = 38;
        grid[12][177] = 25;
        grid[13][177] = 25;
        grid[14][177] = 25;
        grid[15][177] = 25;
        grid[16][177] = 25;
        grid[17][177] = 0;
        grid[18][177] = 25;
        grid[19][177] = 25;
        grid[20][177] = 25;
        grid[21][177] = 25;
        grid[22][177] = 25;
        grid[23][177] = 37;

        grid[23][178] = 26;
        grid[23][179] = 26;
        grid[23][180] = 26;
        grid[23][181] = 26;
        grid[23][182] = 26;
        grid[23][183] = 26;
        grid[23][184] = 26;
        grid[23][185] = 26;
        grid[23][186] = 26;
        grid[23][187] = 26;
        grid[23][188] = 26;
        grid[23][189] = 26;
        grid[23][190] = 26;
        grid[23][191] = 26;
        grid[23][192] = 26;
        grid[23][193] = 26;
        grid[23][194] = 26;
        grid[23][195] = 26;

        grid[11][178] = 26;
        grid[11][179] = 26;
        grid[11][180] = 26;
        grid[11][181] = 26;
        grid[11][182] = 26;
        grid[11][183] = 26;
        grid[11][184] = 26;
        grid[11][185] = 26;
        grid[11][186] = 26;
        grid[11][187] = 26;
        grid[11][188] = 26;
        grid[11][189] = 26;
        grid[11][190] = 26;
        grid[11][191] = 26;
        grid[11][192] = 26;
        grid[11][193] = 26;
        grid[11][194] = 26;
        grid[11][195] = 26;


        grid[13][180] = 38;
        grid[14][180] = 25;
        grid[15][180] = 25;
        grid[16][180] = 25;
        grid[17][180] = 25;
        grid[18][180] = 25;
        grid[19][180] = 25;
        grid[20][180] = 25;
        grid[21][180] = 37;

        grid[13][193] = 39;
        grid[14][193] = 25;
        grid[15][193] = 25;
        grid[16][193] = 25;
        grid[17][193] = 0;
        grid[18][193] = 25;
        grid[19][193] = 25;
        grid[20][193] = 25;
        grid[21][193] = 40;

        grid[21][192] = 26;
        grid[21][191] = 26;
        grid[21][190] = 26;
        grid[21][189] = 26;
        grid[21][188] = 26;
        grid[21][187] = 26;
        grid[21][186] = 26;
        grid[21][185] = 26;
        grid[21][184] = 26;
        grid[21][183] = 26;
        grid[21][182] = 26;
        grid[21][181] = 26;

        grid[13][192] = 26;
        grid[13][191] = 26;
        grid[13][190] = 26;
        grid[13][189] = 26;
        grid[13][188] = 26;
        grid[13][187] = 26;
        grid[13][186] = 26;
        grid[13][185] = 26;
        grid[13][184] = 26;
        grid[13][183] = 26;
        grid[13][182] = 26;
        grid[13][181] = 26;


        grid[15][183] = 38;
        grid[16][183] = 25;
        grid[17][183] = 0;
        grid[18][183] = 25;
        grid[19][183] = 37;

        grid[15][190] = 39;
        grid[16][190] = 25;
        grid[17][190] = 25;
        grid[18][190] = 25;
        grid[19][190] = 40;

        grid[19][184] = 26;
        grid[19][185] = 26;
        grid[19][186] = 26;
        grid[19][187] = 26;
        grid[19][188] = 26;
        grid[19][189] = 26;

        grid[15][184] = 26;
        grid[15][185] = 26;
        grid[15][186] = 26;
        grid[15][187] = 26;
        grid[15][188] = 26;
        grid[15][189] = 26;



        //Diamond
        grid[17][186] = 20;






    }
    public void PrintGrid(int[][] map){
        System.out.println("******Turn Number: "+ this.turnsAmount + " ********");
        for (int i = 0;i < 34; i++){
            System.out.println();
            for (int x = 0;x < 200; x++){
                if (map[i][x] == 0){System.out.print(" ");}//Prints empty area
                if (map[i][x] == 1){System.out.print("A");}
                if (map[i][x] == 2){System.out.print("B");}
                if (map[i][x] == 3){System.out.print("C");}
                if (map[i][x] == 4){System.out.print("Å");}
                if (map[i][x] == 5){System.out.print("ß");}
                if (map[i][x] == 6){System.out.print("Ç");}
                if (map[i][x] == 7){System.out.print("Ä");}
                if (map[i][x] == 8){System.out.print("฿");}
                if (map[i][x] == 9){System.out.print("¢");}
                if (map[i][x] == 10){System.out.print("X");}
                if (map[i][x] == 11){System.out.print("Y");}
                if (map[i][x] == 12){System.out.print("Z");}
                if (map[i][x] == 13){System.out.print("⌘");}
                if (map[i][x] == 14){System.out.print("¥");}
                if (map[i][x] == 15){System.out.print("ζ");}
                if (map[i][x] == 16){System.out.print("✥");}
                if (map[i][x] == 17){System.out.print("ϒ");}
                if (map[i][x] == 18){System.out.print("ϟ");}
                if (map[i][x] == 19){System.out.print("*");}//Prints stone
                if (map[i][x] == 20){System.out.print("♦");}//prints Diamiond
                if (map[i][x] == 21){System.out.print("#");}//Prints wheat
                if (map[i][x] == 22){System.out.print("~");}//prints grass
                if (map[i][x] == 23){System.out.print("Δ");}//prints tree
                if (map[i][x] == 24){System.out.print("[");}//prints vertical line
                if (map[i][x] == 25){System.out.print("║");}//Prints vertical wall
                if (map[i][x] == 26){System.out.print("═");}//prints horizantal wall
                if (map[i][x] == 27){System.out.print("]");}//prints horizantal line
                if (map[i][x] == 28){System.out.print("|");}//prints horizantal line
                if (map[i][x] == 29){System.out.print("·");}//prints soilder
                if (map[i][x] == 30){System.out.print("☭");}//prints worker
                if (map[i][x] == 31){System.out.print("\uD83D\uDC19");}//prints monster
                if (map[i][x] == 32){System.out.print("⚔");}//prints soilder
                if (map[i][x] == 33){System.out.print("•");}//prints playerTwoRoad
                if (map[i][x] == 34){System.out.print("≡");}//prints playerOneRoad
                if (map[i][x] == 35){System.out.print("♖");}//prints Tower
                if (map[i][x] == 36){System.out.print("░");}//prints Reaped land
                if (map[i][x] == 37){System.out.print("╚");}//prints corner
                if (map[i][x] == 38){System.out.print("╔");}//prints corner
                if (map[i][x] == 39){System.out.print("╗");}//prints corner
                if (map[i][x] == 40){System.out.print("╝");}//prints corner
                if (map[i][x] == 41){System.out.print("▌");}//prints Reaped land



            }

        }


    }
    public void AddMaterial()
    {
        //this.turn ++;
        this.playerOneStone += 5;
        this.playerTwoStone += 5;
    }
    //Change turn basicaly means main menu, doesn't actually change the turn
    public void ChangeTurn(){
        Scanner scan = new Scanner(System.in);
        //*********************************************Player 1 ********************************************* Done
        if(this.turn == 1){
            PrintGrid(grid);
            System.out.println();
            System.out.println("♚¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯♛");
            System.out.println("¦Player 1 Turn:                                                                                                    ¦");
            System.out.println("¦▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩¦");
            System.out.println("¦Wheat: " + this.playerOneWheat + "                                                                                                          ¦");
            System.out.println("¦Stone: " + this.playerOneStone + "                                                                                                          ¦");
            System.out.println("¦Soldiers: " + this.playerOneSoldiers + "                                                                                                       ¦");
            System.out.println("¦Workers: " + this.playerOneWorkers + "                                                                                                        ¦");
            System.out.println("¦Settlements: " + this.playerOneCitiesToPlace + "                                                                                                    ¦");
            System.out.println("¦▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩¦");
            System.out.println("¦Actions:                                                                                                          ¦");
            System.out.println("¦1) Start new road                                                                                                 ¦");
            System.out.println("¦2) Continue road                                                                                                  ¦");
            System.out.println("¦3) Buy units                                                                                                      ¦");
            System.out.println("¦4) Manage units                                                                                                   ¦");
            System.out.println("¦5) End Turn                                                                                                       ¦");
            System.out.println("¦▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩◇▩◆▩¦");
            System.out.println("♛¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯♚");
            System.out.println("•6) View Data");
            mainMenu();

        }
        //*********************************************Player 2 ********************************************* Done
        if(this.turn == -1){
            PrintGrid(grid);
            System.out.println();
            System.out.println("☪•••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••✹");
            System.out.println("•Player 2 Turn:                                                                                                    •");
            System.out.println("•☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩•");
            System.out.println("•Wheat: " + this.playerTwoWheat + "                                                                                                          •");
            System.out.println("•Stone: " + this.playerTwoStone + "                                                                                                          •");
            System.out.println("•Soldiers: " + this.playerTwoSoldiers + "                                                                                                       •");
            System.out.println("•Workers: " + this.playerTwoWorkers + "                                                                                                        •");
            System.out.println("•Settlements: " + this.playerTwoCitiesToPlace + "                                                                                                    •");
            System.out.println("•☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩•");
            System.out.println("•Actions:                                                                                                          •");
            System.out.println("•1) Start new road                                                                                                 •");
            System.out.println("•2) Continue road                                                                                                  •");
            System.out.println("•3) Buy units                                                                                                      •");
            System.out.println("•4) Manage units                                                                                                   •");
            System.out.println("•5) End Turn                                                                                                       •");
            System.out.println("•☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩≈≈≈≈≈≈≈≈☩•");
            System.out.println("✹•••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••☪");
            System.out.println("•6) View Data");
            mainMenu();


        }


    }
    public void ViewData(){
        System.out.println("Player one Cities Array");
        for (int i = 0; i < 9; i++) {
            for (int x = 0; x < 5; x ++){
                System.out.print(this.playerOneCities[i][x] + " ");
            }
            System.out.println();
        }
        System.out.println("Player one Nodes Array");
        for (int i = 0; i < 36; i++) {
            for (int x = 0; x < 5; x ++){
                System.out.print(this.playerOneNodeTracker[i][x] + " ");
            }
            System.out.println();
        }
        System.out.println("Player two Cities Array");
        for (int i = 0; i < 9; i++) {
            for (int x = 0; x < 5; x ++){
                System.out.print(this.playerTwoCities[i][x] + " ");
            }
            System.out.println();
        }
        System.out.println("Player two Nodes Array");
        for (int i = 0; i < 36; i++) {
            for (int x = 0; x < 5; x ++){
                System.out.print(this.playerTwoNodeTracker[i][x] + " ");
            }
            System.out.println();
        }
    }
    //This method starts a new road on a settlement
    public void StartRoad(){
        //*********************************************Player 1 ********************************************* Done
        if (this.turn == 1) {
            System.out.println("Choose a settlement to place a new road.");
            for (int i = 0; i < 9; i++) {
                if (this.playerOneCities[i][0] > 0){
                    System.out.print(i + 1 + ") Settlement ");
                    printSpecialCharacter(this.playerOneCities[i][0]);
                    System.out.println();

                }
                else {i = 10;}
            }
            System.out.println("0) back");
            //try {}
            int settlement = trycatch();
            int[] cordinates = FindNode(settlement);
            if (settlement == 0){ChangeTurn(); }
            else {if (cordinates[0] + cordinates[1] == 0){
                System.out.println("Thats not a valid answer");
                StartRoad();
            }
            else {
                System.out.println("Do you want to build... ");
                if (this.playerOneCities[settlement - 1][1] == 0){System.out.println("1) Right");}
                if (this.playerOneCities[settlement - 1][2] == 0){System.out.println("2) Down");}
                if (this.playerOneCities[settlement - 1][3] == 0){System.out.println("3) Left");}
                if (this.playerOneCities[settlement - 1][4] == 0){System.out.println("4) Up");}
                System.out.println("0) back");
                int direction = trycatch();
                if(direction > 4){System.out.println("Invalid Number"); ChangeTurn(); }
                if (this.playerOneCities[settlement - 1][1] == 1 && direction == 1){System.out.println("Invalid Number");ChangeTurn();}
                if (this.playerOneCities[settlement - 1][2] == 1 && direction == 2){System.out.println("Invalid Number");ChangeTurn();}
                if (this.playerOneCities[settlement - 1][3] == 1 && direction == 3){System.out.println("Invalid Number");ChangeTurn();}
                if (this.playerOneCities[settlement - 1][4] == 1 && direction == 4){System.out.println("Invalid Number");ChangeTurn();}
                if (direction == 0){ChangeTurn();}
                else {System.out.println("How much road do you want to build?(1 road block cost 1 stone)");
                    int length = trycatch();
                    this.playerOneCities[settlement - 1][direction] = 1;

                    int node = 0;
                    for (int j = 0; j < 36; j++){
                        if (this.playerOneNodeTracker[j][0]== 0)
                        {
                            node = j;
                            j = 36;
                        }}
                    this.playerOneNodeTracker[node][3]= settlement;
                    BuildRoad(cordinates[0], cordinates[1], length, direction,settlement, node);

                   }
                }
            }
        }
        //*********************************************Player 2 ********************************************* done
        if (this.turn == -1) {
            System.out.println("Choose a settlement to place a new road.");
            for (int i = 0; i < 9; i++) {
                if (this.playerTwoCities[i][0] > 0){
                    System.out.print(i + 1 + ") Settlement ");
                    printSpecialCharacter(this.playerTwoCities[i][0] + 9);
                    System.out.println();

                }
                else {i = 10;}
            }
            System.out.println("0) back");
            int settlement = trycatch();
            int[] cordinates = FindNode(settlement + 9);
            if (settlement == 0){ChangeTurn(); }
            else {if (cordinates[0] + cordinates[1] == 0){
                System.out.println("Thats not a valid answer");
                StartRoad();
            }
            else {
                System.out.println("Do you want to build... ");
                if (this.playerTwoCities[settlement - 1][1] == 0){System.out.println("1) Right");}
                if (this.playerTwoCities[settlement - 1][2] == 0){System.out.println("2) Down");}
                if (this.playerTwoCities[settlement - 1][3] == 0){System.out.println("3) Left");}
                if (this.playerTwoCities[settlement - 1][4] == 0){System.out.println("4) Up");}
                System.out.println("0) back");
                int direction = trycatch();
                if(direction > 4){System.out.println("Invalid Number"); ChangeTurn(); }
                if (this.playerTwoCities[settlement - 1][1] == 1 && direction == 1){System.out.println("Invalid Number");ChangeTurn();}
                if (this.playerTwoCities[settlement - 1][2] == 1 && direction == 2){System.out.println("Invalid Number");ChangeTurn();}
                if (this.playerTwoCities[settlement - 1][3] == 1 && direction == 3){System.out.println("Invalid Number");ChangeTurn();}
                if (this.playerTwoCities[settlement - 1][4] == 1 && direction == 4){System.out.println("Invalid Number");ChangeTurn();}
                if (direction == 0){ChangeTurn();}
                else {System.out.println("How much road do you want to build?(1 road block cost 1 stone)");
                    int length = trycatch();
                    this.playerTwoCities[settlement - 1][direction] = 1;
                    int node = 0;
                    for (int j = 0; j < 36; j++){
                        if (this.playerTwoNodeTracker[j][0]== 0)
                        {
                            node = j;
                            j = 36;
                        }}
                    this.playerTwoNodeTracker[node][3]= settlement;
                    BuildRoad(cordinates[0], cordinates[1], length, direction,settlement, node);

                }
              }
            }
        }
    }


    //This method uses the cordinates of a city in connjuntion with a direction and length to build a new road attached to a settlement.
    public void BuildRoad(int xPos, int yPos, int length, int direction,int settlement, int node){
        int roadLength = 1;
        //*********************************************Player 1 ********************************************* done
        if (this.turn == 1){
            for (int i = 0;length > i; i++) {
                if (this.playerOneStone > 0)
                {if (direction == 1){
                    if (this.grid[xPos][yPos+ roadLength] == 0 || this.grid[xPos][yPos+ roadLength] == 22 || this.grid[xPos][yPos+ roadLength] == 23|| this.grid[xPos][yPos+ roadLength] == 34)
                      {
                        this.grid[xPos][yPos + roadLength] = 34;
                        roadLength++;
                        this.playerOneStone --;
                    }
                }
                    if (direction == 2){
                        if (this.grid[xPos+ roadLength][yPos] == 0 || this.grid[xPos+ roadLength][yPos] == 22 || this.grid[xPos+ roadLength][yPos] == 23|| this.grid[xPos+ roadLength][yPos] == 34) {
                            this.grid[xPos + roadLength][yPos] = 34;
                            roadLength++;
                            this.playerOneStone --;
                        }
                    }
                    if (direction == 3){
                        if(this.grid[xPos][yPos- roadLength] == 0 || this.grid[xPos][yPos- roadLength] == 22 || this.grid[xPos][yPos- roadLength] == 23 || this.grid[xPos][yPos- roadLength] == 34) {
                            this.grid[xPos][yPos - roadLength] = 34;
                            roadLength++;
                            this.playerOneStone --;
                        }
                    }
                    if (direction == 4){
                        if (this.grid[xPos- roadLength][yPos] == 0 || this.grid[xPos- roadLength][yPos] == 22 || this.grid[xPos-roadLength][yPos] == 23|| this.grid[xPos-roadLength][yPos] == 34) {
                            this.grid[xPos - roadLength][yPos] = 34;
                            roadLength++;
                            this.playerOneStone --;
                        }
                    }
                }

            }
            roadLength = roadLength - 1;
            if (direction == 1){this.playerOneNodeTracker[node][0]=1; this.playerOneNodeTracker[node][1] = xPos; this.playerOneNodeTracker[node][2] = yPos + roadLength;}
            if (direction == 2){this.playerOneNodeTracker[node][0]=2; this.playerOneNodeTracker[node][1] = xPos + roadLength; this.playerOneNodeTracker[node][2] = yPos;}
            if (direction == 3){this.playerOneNodeTracker[node][0]=3; this.playerOneNodeTracker[node][1] = xPos; this.playerOneNodeTracker[node][2] = yPos - roadLength;}
            if (direction == 4){this.playerOneNodeTracker[node][0]=4; this.playerOneNodeTracker[node][1] = xPos - roadLength; this.playerOneNodeTracker[node][2] = yPos;}
            ChangeTurn();
        }
        //*********************************************Player 2 ********************************************* done
        if (this.turn == -1){
            for (int i = 0;length > i; i++) {
                if (this.playerTwoStone > 0)
                {if (direction == 1){
                    if (this.grid[xPos][yPos+ roadLength] == 0 || this.grid[xPos][yPos+ roadLength] == 22 || this.grid[xPos][yPos+ roadLength] == 23|| this.grid[xPos][yPos+ roadLength] == 33)
                    {
                        this.grid[xPos][yPos + roadLength] = 33;
                        roadLength++;
                        this.playerTwoStone --;
                    }
                }
                    if (direction == 2){
                        if (this.grid[xPos+ roadLength][yPos] == 0 || this.grid[xPos+ roadLength][yPos] == 22 || this.grid[xPos+ roadLength][yPos] == 23|| this.grid[xPos+ roadLength][yPos] == 33) {
                            this.grid[xPos + roadLength][yPos] = 33;
                            roadLength++;
                            this.playerTwoStone --;
                        }
                    }
                    if (direction == 3){
                        if(this.grid[xPos][yPos- roadLength] == 0 || this.grid[xPos][yPos- roadLength] == 22 || this.grid[xPos][yPos- roadLength] == 23 || this.grid[xPos][yPos- roadLength] == 33) {
                            this.grid[xPos][yPos - roadLength] = 33;
                            roadLength++;
                            this.playerTwoStone --;
                        }
                    }
                    if (direction == 4){
                        if (this.grid[xPos- roadLength][yPos] == 0 || this.grid[xPos- roadLength][yPos] == 22 || this.grid[xPos-roadLength][yPos] == 23|| this.grid[xPos-roadLength][yPos] == 33) {
                            this.grid[xPos - roadLength][yPos] = 33;
                            roadLength++;
                            this.playerTwoStone --;
                        }
                    }
                }

            }
            roadLength = roadLength - 1;
            if (direction == 1){this.playerTwoNodeTracker[node][0]=1; this.playerTwoNodeTracker[node][1] = xPos; this.playerTwoNodeTracker[node][2] = yPos + roadLength;}
            if (direction == 2){this.playerTwoNodeTracker[node][0]=2; this.playerTwoNodeTracker[node][1] = xPos + roadLength; this.playerTwoNodeTracker[node][2] = yPos;}
            if (direction == 3){this.playerTwoNodeTracker[node][0]=3; this.playerTwoNodeTracker[node][1] = xPos; this.playerTwoNodeTracker[node][2] = yPos - roadLength;}
            if (direction == 4){this.playerTwoNodeTracker[node][0]=4; this.playerTwoNodeTracker[node][1] = xPos - roadLength; this.playerTwoNodeTracker[node][2] = yPos;}
            ChangeTurn();
        }
    }
    //This method initiates the actions of the players units
    public void UnitActions()
    {
        //*********************************************Player 1 ********************************************* done
        if (this.turn == 1)
        {
            for (int i = 0; i < 36; i++)
            {
                //worker
                if (this.playerOneNodeTracker[i][4] == 1 ){
                    if (this.playerOneWorkerNodeTracker[i][0] == 0){this.playerOneWorkerNodeTracker[i][0] =this.playerOneNodeTracker[i][1]; this.playerOneWorkerNodeTracker[i][1] =this.playerOneNodeTracker[i][2];}
                    int mineral = Mine(i,this.playerOneWorkerNodeTracker[i][0],this.playerOneWorkerNodeTracker[i][1], 0);
                    if (mineral == 1){this.playerOneStone +=3;}
                    if (mineral == 2){this.playerOneWheat ++;}
                    if (mineral == 3){Mine(i,this.playerOneWorkerNodeTracker[i][0],this.playerOneWorkerNodeTracker[i][1], 0);}
                    System.out.println(mineral);

                }
                //soilder
                if (this.playerOneNodeTracker[i][4] == 2 ){
                    attack(i, this.playerOneNodeTracker[i][1],this.playerOneNodeTracker[i][2]);
                }

            }
        }
        //*********************************************Player 2 ********************************************* done
        if (this.turn == -1)
        {
            for (int i = 0; i < 36; i++)
            {
                //worker
                if (this.playerTwoNodeTracker[i][4] == 1 ){
                    if (this.playerTwoWorkerNodeTracker[i][0] == 0){this.playerTwoWorkerNodeTracker[i][0] =this.playerTwoNodeTracker[i][1]; this.playerTwoWorkerNodeTracker[i][1] =this.playerTwoNodeTracker[i][2];}
                    int mineral = Mine(i,this.playerTwoWorkerNodeTracker[i][0],this.playerTwoWorkerNodeTracker[i][1], 0);
                    if (mineral == 1){this.playerTwoStone +=3;}
                    if (mineral == 2){this.playerTwoWheat ++;}
                    if (mineral == 3){Mine(i,this.playerTwoWorkerNodeTracker[i][0],this.playerTwoWorkerNodeTracker[i][1], 0);}
                    System.out.println(mineral);

                }
                //soilder
                if (this.playerTwoNodeTracker[i][4] == 2 ){
                    attack(i, this.playerTwoNodeTracker[i][1],this.playerTwoNodeTracker[i][2]);
                }

            }
        }
    }
    public void attack(int node, int xPos, int yPos)
    {


        if (this.turn == 1) {
            System.out.println("Xpos:"+ xPos);
            System.out.println("Ypos:"+ yPos);
            if (this.grid[xPos + 1][yPos - 1] == 33){this.grid[xPos + 1][yPos - 1] = 0;System.out.println("Worked");}
            if (this.grid[xPos + 1][yPos] == 33){this.grid[xPos + 1][yPos] = 0;System.out.println("Worked");}
            if (this.grid[xPos + 1][yPos + 1] == 33){this.grid[xPos + 1][yPos +1] = 0;System.out.println("Worked");}

            if (this.grid[xPos][yPos - 1] == 33){this.grid[xPos][yPos-1] = 0;System.out.println("Worked");}
            if (this.grid[xPos][yPos + 1] == 33){this.grid[xPos][yPos+1] = 0;System.out.println("Worked");}

            if (this.grid[xPos - 1][yPos - 1] == 33){this.grid[xPos - 1][yPos - 1] = 0;System.out.println("Worked");}
            if (this.grid[xPos - 1][yPos] == 33){this.grid[xPos - 1][yPos] = 0;System.out.println("Worked");}
            if (this.grid[xPos - 1][yPos + 1] == 33){this.grid[xPos - 1][yPos + 1] = 0;System.out.println("Worked");}

        }
        if (this.turn == -1) {
            System.out.println("Xpos:"+ xPos);
            System.out.println("Ypos:"+ yPos);
            if (this.grid[xPos + 1][yPos - 1] == 34){this.grid[xPos + 1][yPos - 1] = 0;System.out.println("Worked");}
            if (this.grid[xPos + 1][yPos] == 34){this.grid[xPos + 1][yPos] = 0;System.out.println("Worked");}
            if (this.grid[xPos + 1][yPos + 1] == 34){this.grid[xPos + 1][yPos + 1] = 0;System.out.println("Worked");}

            if (this.grid[xPos][yPos - 1] == 34){this.grid[xPos][yPos - 1] = 0;System.out.println("Worked");}
            if (this.grid[xPos][yPos + 1] == 34){this.grid[xPos][yPos + 1] = 0;System.out.println("Worked");}

            if (this.grid[xPos - 1][yPos - 1] == 34){this.grid[xPos - 1][yPos - 1] = 0;System.out.println("Worked");}
            if (this.grid[xPos - 1][yPos] == 34){this.grid[xPos - 1][yPos] = 0;System.out.println("Worked");}
            if (this.grid[xPos - 1][yPos + 1] == 34){this.grid[xPos - 1][yPos + 1] = 50;System.out.println("Worked");}

        }

    }


    // returns 0 if nothing found, returns 3 for horizantal line, returns 1 for stone, returns 23 for wheat.
    public int Mine(int node, int xPos, int yPos, int recursion)
    {

        //*********************************************Player 1 ********************************************* done
        if (this.turn == 1 && recursion < 10)
        {
            if (this.grid[xPos + 1][yPos - 1] == 19){this.grid[xPos + 1][yPos - 1] = 36; this.playerOneWorkerNodeTracker[node][0] = xPos + 1; this.playerOneWorkerNodeTracker[node][1] = yPos - 1;return 1;}
            if (this.grid[xPos + 1][yPos] == 19){this.grid[xPos + 1][yPos] = 36;this.playerOneWorkerNodeTracker[node][0] = xPos + 1; this.playerOneWorkerNodeTracker[node][1] = yPos; return 1;}
            if (this.grid[xPos + 1][yPos + 1] == 19){this.grid[xPos + 1][yPos + 1]= 36;this.playerOneWorkerNodeTracker[node][0] = xPos + 1; this.playerOneWorkerNodeTracker[node][1] = yPos + 1; return 1;}
            if (this.grid[xPos][yPos - 1] == 19){this.grid[xPos][yPos - 1] = 36;this.playerOneWorkerNodeTracker[node][0] = xPos; this.playerOneWorkerNodeTracker[node][1] = yPos - 1; return 1;}
            if (this.grid[xPos][yPos] == 19){this.grid[xPos][yPos]= 36;this.playerOneWorkerNodeTracker[node][0] = xPos; this.playerOneWorkerNodeTracker[node][1] = yPos; return 1;}
            if (this.grid[xPos][yPos + 1] == 19){this.grid[xPos][yPos + 1] = 36;this.playerOneWorkerNodeTracker[node][0] = xPos; this.playerOneWorkerNodeTracker[node][1] = yPos + 1; return 1;}
            if (this.grid[xPos - 1][yPos - 1] == 19){this.grid[xPos - 1][yPos - 1] = 36;this.playerOneWorkerNodeTracker[node][0] = xPos - 1; this.playerOneWorkerNodeTracker[node][1] = yPos - 1; return 1;}
            if (this.grid[xPos - 1][yPos] == 19){this.grid[xPos - 1][yPos] = 36;this.playerOneWorkerNodeTracker[node][0] = xPos - 1; this.playerOneWorkerNodeTracker[node][1] = yPos; return 1;}
            if (this.grid[xPos - 1][yPos + 1] == 19){this.grid[xPos - 1][yPos + 1] = 36;this.playerOneWorkerNodeTracker[node][0] = xPos - 1; this.playerOneWorkerNodeTracker[node][1] = yPos + 1; return 1;}

            if (this.grid[xPos + 1][yPos - 1] == 21){this.grid[xPos + 1][yPos - 1] = 36; this.playerOneWorkerNodeTracker[node][0] = xPos + 1; this.playerOneWorkerNodeTracker[node][1] = yPos - 1;return 2;}
            if (this.grid[xPos + 1][yPos] == 21){this.grid[xPos + 1][yPos] = 36;this.playerOneWorkerNodeTracker[node][0] = xPos + 1; this.playerOneWorkerNodeTracker[node][1] = yPos; return 2;}
            if (this.grid[xPos + 1][yPos + 1] == 21){this.grid[xPos + 1][yPos + 1]= 36;this.playerOneWorkerNodeTracker[node][0] = xPos + 1; this.playerOneWorkerNodeTracker[node][1] = yPos + 1; return 2;}
            if (this.grid[xPos][yPos - 1] == 21){this.grid[xPos][yPos - 1] = 36;this.playerOneWorkerNodeTracker[node][0] = xPos; this.playerOneWorkerNodeTracker[node][1] = yPos - 1; return 2;}
            if (this.grid[xPos][yPos] == 21){this.grid[xPos][yPos]= 36;this.playerOneWorkerNodeTracker[node][0] = xPos; this.playerOneWorkerNodeTracker[node][1] = yPos; return 2;}
            if (this.grid[xPos][yPos + 1] == 21){this.grid[xPos][yPos + 1] = 36;this.playerOneWorkerNodeTracker[node][0] = xPos; this.playerOneWorkerNodeTracker[node][1] = yPos + 1; return 2;}
            if (this.grid[xPos - 1][yPos - 1] == 21){this.grid[xPos - 1][yPos - 1] = 36;this.playerOneWorkerNodeTracker[node][0] = xPos - 1; this.playerOneWorkerNodeTracker[node][1] = yPos - 1; return 2;}
            if (this.grid[xPos - 1][yPos] == 21){this.grid[xPos - 1][yPos] = 36;this.playerOneWorkerNodeTracker[node][0] = xPos - 1; this.playerOneWorkerNodeTracker[node][1] = yPos; return 2;}
            if (this.grid[xPos - 1][yPos + 1] == 21){this.grid[xPos - 1][yPos + 1] = 36;this.playerOneWorkerNodeTracker[node][0] = xPos - 1; this.playerOneWorkerNodeTracker[node][1] = yPos + 1; return 2;}



            if (this.grid[xPos + 1][yPos - 1] == 28){this.playerOneWorkerNodeTracker[node][0] = xPos + 1; this.playerOneWorkerNodeTracker[node][1] = yPos - 1; return 3;}
            if (this.grid[xPos + 1][yPos] == 28){this.playerOneWorkerNodeTracker[node][0] = xPos + 1; this.playerOneWorkerNodeTracker[node][1] = yPos; return 3;}
            if (this.grid[xPos + 1][yPos + 1] == 28){this.playerOneWorkerNodeTracker[node][0] = xPos + 1; this.playerOneWorkerNodeTracker[node][1] = yPos + 1;return 3;}
            if (this.grid[xPos][yPos - 1] == 28){this.playerOneWorkerNodeTracker[node][0] = xPos; this.playerOneWorkerNodeTracker[node][1] = yPos - 1; return 3;}
            if (this.grid[xPos][yPos] == 28){this.playerOneWorkerNodeTracker[node][0] = xPos; this.playerOneWorkerNodeTracker[node][1] = yPos; return 3;}
            if (this.grid[xPos][yPos + 1] == 28){this.playerOneWorkerNodeTracker[node][0] = xPos; this.playerOneWorkerNodeTracker[node][1] = yPos + 1; return 3;}
            if (this.grid[xPos - 1][yPos - 1] == 28){this.playerOneWorkerNodeTracker[node][0] = xPos - 1; this.playerOneWorkerNodeTracker[node][1] = yPos - 1; return 3;}
            if (this.grid[xPos - 1][yPos] == 28){this.playerOneWorkerNodeTracker[node][0] = xPos - 1; this.playerOneWorkerNodeTracker[node][1] = yPos; return 3;}
            if (this.grid[xPos - 1][yPos + 1] == 28){this.playerOneWorkerNodeTracker[node][0] = xPos - 1; this.playerOneWorkerNodeTracker[node][1] = yPos + 1; return 3;}

            if (this.grid[xPos + 1][yPos - 1] == 20){ PlayerOneWin();return 0;}
            if (this.grid[xPos + 1][yPos] == 20){PlayerOneWin();return 0;}
            if (this.grid[xPos + 1][yPos + 1] == 20){PlayerOneWin();return 0;}
            if (this.grid[xPos][yPos - 1] == 20){PlayerOneWin();return 0;}
            if (this.grid[xPos][yPos] == 20){PlayerOneWin();return 0;}
            if (this.grid[xPos][yPos + 1] == 20){PlayerOneWin();return 0;}
            if (this.grid[xPos - 1][yPos - 1] == 20){PlayerOneWin();return 0;}
            if (this.grid[xPos - 1][yPos] == 20){PlayerOneWin();return 0;}
            if (this.grid[xPos - 1][yPos + 1] == 20){PlayerOneWin();return 0;}




        }

        //*********************************************Player 2 ********************************************* done
        if (this.turn == -1 && recursion < 10)
        {
            if (this.grid[xPos + 1][yPos - 1] == 19){this.grid[xPos + 1][yPos - 1] = 36; this.playerTwoWorkerNodeTracker[node][0] = xPos + 1; this.playerTwoWorkerNodeTracker[node][1] = yPos - 1;return 1;}
            if (this.grid[xPos + 1][yPos] == 19){this.grid[xPos + 1][yPos] = 36;this.playerTwoWorkerNodeTracker[node][0] = xPos + 1; this.playerTwoWorkerNodeTracker[node][1] = yPos; return 1;}
            if (this.grid[xPos + 1][yPos + 1] == 19){this.grid[xPos + 1][yPos + 1]= 36;this.playerTwoWorkerNodeTracker[node][0] = xPos + 1; this.playerTwoWorkerNodeTracker[node][1] = yPos + 1; return 1;}
            if (this.grid[xPos][yPos - 1] == 19){this.grid[xPos][yPos - 1] = 36;this.playerTwoWorkerNodeTracker[node][0] = xPos; this.playerTwoWorkerNodeTracker[node][1] = yPos - 1; return 1;}
            if (this.grid[xPos][yPos] == 19){this.grid[xPos][yPos]= 36;this.playerTwoWorkerNodeTracker[node][0] = xPos; this.playerTwoWorkerNodeTracker[node][1] = yPos; return 1;}
            if (this.grid[xPos][yPos + 1] == 19){this.grid[xPos][yPos + 1] = 36;this.playerTwoWorkerNodeTracker[node][0] = xPos; this.playerTwoWorkerNodeTracker[node][1] = yPos + 1; return 1;}
            if (this.grid[xPos - 1][yPos - 1] == 19){this.grid[xPos - 1][yPos - 1] = 36;this.playerTwoWorkerNodeTracker[node][0] = xPos - 1; this.playerTwoWorkerNodeTracker[node][1] = yPos - 1; return 1;}
            if (this.grid[xPos - 1][yPos] == 19){this.grid[xPos - 1][yPos] = 36;this.playerTwoWorkerNodeTracker[node][0] = xPos - 1; this.playerTwoWorkerNodeTracker[node][1] = yPos; return 1;}
            if (this.grid[xPos - 1][yPos + 1] == 19){this.grid[xPos - 1][yPos + 1] = 36;this.playerTwoWorkerNodeTracker[node][0] = xPos - 1; this.playerTwoWorkerNodeTracker[node][1] = yPos + 1; return 1;}

            if (this.grid[xPos + 1][yPos - 1] == 21){this.grid[xPos + 1][yPos - 1] = 36; this.playerTwoWorkerNodeTracker[node][0] = xPos + 1; this.playerTwoWorkerNodeTracker[node][1] = yPos - 1;return 2;}
            if (this.grid[xPos + 1][yPos] == 21){this.grid[xPos + 1][yPos] = 36;this.playerTwoWorkerNodeTracker[node][0] = xPos + 1; this.playerTwoWorkerNodeTracker[node][1] = yPos; return 2;}
            if (this.grid[xPos + 1][yPos + 1] == 21){this.grid[xPos + 1][yPos + 1]= 36;this.playerTwoWorkerNodeTracker[node][0] = xPos + 1; this.playerTwoWorkerNodeTracker[node][1] = yPos + 1; return 2;}
            if (this.grid[xPos][yPos - 1] == 21){this.grid[xPos][yPos - 1] = 36;this.playerTwoWorkerNodeTracker[node][0] = xPos; this.playerTwoWorkerNodeTracker[node][1] = yPos - 1; return 2;}
            if (this.grid[xPos][yPos] == 21){this.grid[xPos][yPos]= 36;this.playerTwoWorkerNodeTracker[node][0] = xPos; this.playerTwoWorkerNodeTracker[node][1] = yPos; return 2;}
            if (this.grid[xPos][yPos + 1] == 21){this.grid[xPos][yPos + 1] = 36;this.playerTwoWorkerNodeTracker[node][0] = xPos; this.playerTwoWorkerNodeTracker[node][1] = yPos + 1; return 2;}
            if (this.grid[xPos - 1][yPos - 1] == 21){this.grid[xPos - 1][yPos - 1] = 36;this.playerTwoWorkerNodeTracker[node][0] = xPos - 1; this.playerTwoWorkerNodeTracker[node][1] = yPos - 1; return 2;}
            if (this.grid[xPos - 1][yPos] == 21){this.grid[xPos - 1][yPos] = 36;this.playerTwoWorkerNodeTracker[node][0] = xPos - 1; this.playerTwoWorkerNodeTracker[node][1] = yPos; return 2;}
            if (this.grid[xPos - 1][yPos + 1] == 21){this.grid[xPos - 1][yPos + 1] = 36;this.playerTwoWorkerNodeTracker[node][0] = xPos - 1; this.playerTwoWorkerNodeTracker[node][1] = yPos + 1; return 2;}



            if (this.grid[xPos + 1][yPos - 1] == 28){this.playerTwoWorkerNodeTracker[node][0] = xPos + 1; this.playerTwoWorkerNodeTracker[node][1] = yPos - 1; return 3;}
            if (this.grid[xPos + 1][yPos] == 28){this.playerTwoWorkerNodeTracker[node][0] = xPos + 1; this.playerTwoWorkerNodeTracker[node][1] = yPos; return 3;}
            if (this.grid[xPos + 1][yPos + 1] == 28){this.playerTwoWorkerNodeTracker[node][0] = xPos + 1; this.playerTwoWorkerNodeTracker[node][1] = yPos + 1;return 3;}
            if (this.grid[xPos][yPos - 1] == 28){this.playerTwoWorkerNodeTracker[node][0] = xPos; this.playerTwoWorkerNodeTracker[node][1] = yPos - 1; return 3;}
            if (this.grid[xPos][yPos] == 28){this.playerTwoWorkerNodeTracker[node][0] = xPos; this.playerTwoWorkerNodeTracker[node][1] = yPos; return 3;}
            if (this.grid[xPos][yPos + 1] == 28){this.playerTwoWorkerNodeTracker[node][0] = xPos; this.playerTwoWorkerNodeTracker[node][1] = yPos + 1; return 3;}
            if (this.grid[xPos - 1][yPos - 1] == 28){this.playerTwoWorkerNodeTracker[node][0] = xPos - 1; this.playerTwoWorkerNodeTracker[node][1] = yPos - 1; return 3;}
            if (this.grid[xPos - 1][yPos] == 28){this.playerTwoWorkerNodeTracker[node][0] = xPos - 1; this.playerTwoWorkerNodeTracker[node][1] = yPos; return 3;}
            if (this.grid[xPos - 1][yPos + 1] == 28){this.playerTwoWorkerNodeTracker[node][0] = xPos - 1; this.playerTwoWorkerNodeTracker[node][1] = yPos + 1; return 3;}

            if (this.grid[xPos + 1][yPos - 1] == 20){ PlayerTwoWin();return 0;}
            if (this.grid[xPos + 1][yPos] == 20){PlayerTwoWin();return 0;}
            if (this.grid[xPos + 1][yPos + 1] == 20){PlayerTwoWin();return 0;}
            if (this.grid[xPos][yPos - 1] == 20){PlayerTwoWin();return 0;}
            if (this.grid[xPos][yPos] == 20){PlayerTwoWin();return 0;}
            if (this.grid[xPos][yPos + 1] == 20){PlayerTwoWin();return 0;}
            if (this.grid[xPos - 1][yPos - 1] == 20){PlayerTwoWin();return 0;}
            if (this.grid[xPos - 1][yPos] == 20){PlayerTwoWin();return 0;}
            
            if (this.grid[xPos - 1][yPos + 1] == 20){PlayerTwoWin();return 0;}

        }

        return 0;

    }
    public void ContinueRoad(){
        //*********************************************Player 1 ********************************************* done
        if (this.turn == 1)
        {
            int[] nodeTracker = new int[36];
            System.out.println("Choose a Road to Continue....");
            int list= 1;
            for (int i = 0; i < 36; i++)
            {
                if (this.playerOneNodeTracker[i][0] > 0 && this.playerOneNodeTracker[i][4] == 0){

                    System.out.print(list + ") Settlement "); printSpecialCharacter(this.playerOneNodeTracker[i][3]);
                    printSpecialCharacter(this.playerOneNodeTracker[i][0] + 18);
                    System.out.println();
                    list = list + 1;
                    nodeTracker[list - 1] = i;
                }
            }
            System.out.println("0) Back");

            int node = trycatch();
            if (node == 0){ChangeTurn();}
            if (node > list){System.out.println("Invalid Answer"); ContinueRoad();}
            else {
                System.out.println("Do you want to build... ");
                if (this.playerOneNodeTracker[nodeTracker[node]][0] !=3){ System.out.println("1) Right");}
                if (this.playerOneNodeTracker[nodeTracker[node]][0] !=4){System.out.println("2) Down");}
                if (this.playerOneNodeTracker[nodeTracker[node]][0] !=1){System.out.println("3) Left");}
                if (this.playerOneNodeTracker[nodeTracker[node]][0] !=2){System.out.println("4) Up");}
                int direction = trycatch();
                if(direction > 4){System.out.println("Invalid Number"); ContinueRoad(); }
                if (direction == 0){ChangeTurn();}
                else {System.out.println("How much road do you want to build?(1 road block cost 1 stone)");
                    int length = trycatch();
                    BuildRoad(this.playerOneNodeTracker[nodeTracker[node]][1], this.playerOneNodeTracker[nodeTracker[node]][2], length, direction ,this.playerOneNodeTracker[node][3], nodeTracker[node]);

                }

            }
        }
        //*********************************************Player 2 ********************************************* done
        if (this.turn == -1)
        {
            int[] nodeTracker = new int[36];
            System.out.println("Choose a Road to Continue....");
            int list= 1;
            for (int i = 0; i < 36; i++)
            {
                if (this.playerTwoNodeTracker[i][0] > 0 && this.playerTwoNodeTracker[i][4] == 0){

                    System.out.print(list + ") Settlement "); printSpecialCharacter(this.playerTwoNodeTracker[i][3]+9);
                    printSpecialCharacter(this.playerTwoNodeTracker[i][0] + 18);
                    System.out.println();
                    list = list + 1;
                    nodeTracker[list - 1] = i;
                }
            }
            System.out.println("0) Back");

            int node = trycatch();
            if (node == 0){ChangeTurn();}
            if (node > list){System.out.println("Invalid Answer"); ContinueRoad();}
            else {
                System.out.println("Do you want to build... ");
                if (this.playerTwoNodeTracker[nodeTracker[node]][0] !=3){ System.out.println("1) Right");}
                if (this.playerTwoNodeTracker[nodeTracker[node]][0] !=4){System.out.println("2) Down");}
                if (this.playerTwoNodeTracker[nodeTracker[node]][0] !=1){System.out.println("3) Left");}
                if (this.playerTwoNodeTracker[nodeTracker[node]][0] !=2){System.out.println("4) Up");}
                int direction = trycatch();
                if(direction > 4){System.out.println("Invalid Number"); ContinueRoad(); }
                if (direction == 0){ChangeTurn();}
                else {System.out.println("How much road do you want to build?(1 road block cost 1 stone)");
                    int length = trycatch();
                    BuildRoad(this.playerTwoNodeTracker[nodeTracker[node]][1], this.playerTwoNodeTracker[nodeTracker[node]][2], length, direction ,this.playerTwoNodeTracker[node][3], nodeTracker[node]);

                }

            }
        }
    }
    public void BuyMenu()
    {
        //*********************************************Player 1 ********************************************* done
        if (this.turn == 1)
        {System.out.println("1) Worker: Cost 5 Stone");
            System.out.println("2) Soldier: Cost 3 Stone and 2 Wheat");
            System.out.println("3) Settlement: 5 Wheat");
            System.out.println("0) Back");
            int menu = trycatch();
            if (menu == 0){ChangeTurn();}
            if (menu == 1){
                if (this.playerOneStone > 4) {
                    this.playerOneWorkers ++;
                    this.playerOneStone -= 5;
                    System.out.println("+ 1 Worker");
                    BuyMenu();

                }
                if (this.playerOneStone < 4){System.out.println("You don't have enough resources");}
                BuyMenu();
            }
            if (menu == 2){
                if (this.playerOneStone > 2 && this.playerOneWheat > 1) {
                    this.playerOneSoldiers ++;
                    this.playerOneStone -= 3;
                    this.playerOneWheat -= 2;
                    System.out.println("+ 1 Soldier");
                    BuyMenu();
                }
                if (this.playerOneWheat < 2 || this.playerOneStone < 3){System.out.println("You don't have enough resources");}
                BuyMenu();
            }
            if (menu == 3){
                if (this.playerOneWheat > 4 && this.playerOneCitiesToPlace == 0){
                    this.playerOneWheat -= 5;
                    this.playerOneCitiesAmount = this.playerOneCitiesAmount + 1;
                    this.playerOneCitiesToPlace = this.playerOneCitiesToPlace + 1;
                    for (int i = 0; i < 9; i++)
                    {
                        if (this.playerOneCities[i][0] == 0){this.playerOneCities[i][0] = this.playerOneCitiesAmount; i = 9;}
                    }
                    System.out.println("+ 1 Settlement");
                    BuyMenu();

                }
                if (this.playerOneCitiesToPlace > 1){System.out.println("You cant buy more than 1 city at a time.");}
                if (this.playerOneWheat < 4){System.out.println("You don't have enough resources");}
                BuyMenu();
            }

            if (menu > 3){ System.out.println("Invalid Input"); ChangeTurn();}
        }
        //*********************************************Player 2 ********************************************* done
        if (this.turn == -1)
        {System.out.println("1) Worker: Cost 5 Stone");
            System.out.println("2) Soldier: Cost 3 Stone and 2 Wheat");
            System.out.println("3) Settlement: 5 Wheat");
            System.out.println("0) Back");
            int menu = trycatch();
            if (menu == 0){ChangeTurn();}
            if (menu == 1){
                if (this.playerTwoStone > 4) {
                    this.playerTwoWorkers ++;
                    this.playerTwoStone -= 5;
                    System.out.println("+ 1 Worker");
                    BuyMenu();

                }
                if (this.playerTwoStone < 4){System.out.println("You don't have enough resources");}
                BuyMenu();
            }
            if (menu == 2){
                if (this.playerTwoStone > 2 && this.playerTwoWheat > 1) {
                    this.playerTwoSoldiers ++;
                    this.playerTwoStone -= 3;
                    this.playerTwoWheat -= 2;
                    System.out.println("+ 1 Soldier");
                    BuyMenu();
                }
                if (this.playerTwoWheat < 2 || this.playerTwoStone < 3){System.out.println("You don't have enough resources");}
                BuyMenu();
            }
            if (menu == 3){
                if (this.playerTwoWheat > 4 && this.playerTwoCitiesToPlace == 0){
                    this.playerTwoWheat -= 5;
                    this.playerTwoCitiesAmount = this.playerTwoCitiesAmount + 1;
                    this.playerTwoCitiesToPlace = this.playerTwoCitiesToPlace + 1;
                    for (int i = 0; i < 9; i++)
                    {
                        if (this.playerTwoCities[i][0] == 0){this.playerTwoCities[i][0] = this.playerTwoCitiesAmount; i = 9;}
                    }
                    System.out.println("+ 1 Settlement");
                    BuyMenu();

                }
                if (this.playerTwoCitiesToPlace > 1){System.out.println("You cant buy more than 1 city at a time.");}
                if (this.playerTwoWheat < 4){System.out.println("You don't have enough resources");}
                BuyMenu();
            }

            if (menu > 3){ System.out.println("Invalid Input"); ChangeTurn();}
        }



    }
    public void ManageUnits()
    {
        //*********************************************Player 1 ********************************************* done
        if (this.turn == 1)
        {
            int[] nodeTracker = new int[36];
            System.out.println("Do you want to... 1) place units? or 2) remove them?");
            System.out.println("0) Back ");
            int place = trycatch();
            int list = 1;
            if (place == 0){ChangeTurn();}
            if (place > 2){System.out.println("Invalid Answer"); ChangeTurn();}
            if (place == 1)
            {
                System.out.println("Area's available to place units...");
                for (int i = 0; i < 36; i++)
                {
                    if (this.playerOneNodeTracker[i][0] > 0 && this.playerOneNodeTracker[i][4] == 0){

                        System.out.print(list + ") Settlement "); printSpecialCharacter(this.playerOneNodeTracker[i][3]);
                        printSpecialCharacter(this.playerOneNodeTracker[i][0] + 18);
                        System.out.println();
                        list = list + 1;
                        nodeTracker[list - 1] = i;
                    }
                }
                System.out.println("0) Back ");
                int road = trycatch();
                if (road == 0){ChangeTurn();}
                if (road > list){System.out.println("Invalid Answer"); ChangeTurn();}
                if (road < list)
                {
                    System.out.println("What do you want to place?");
                    if (this.playerOneWorkers > 0){ System.out.println("1) Worker");}
                    if (this.playerOneSoldiers > 0){ System.out.println("2) Soldier");}
                    if (this.playerOneCitiesToPlace > 0){ System.out.println("3) Settlement");}
                    System.out.println("0) Back");
                    int placement = trycatch();
                    if (placement == 0){ManageUnits();}
                    if (placement > 3){System.out.println("Invalid Answer"); ManageUnits();}

                    if (placement == 1){this.playerOneWorkers--; this.playerOneNodeTracker[nodeTracker[road]][4] = 1;
                    this.grid[this.playerOneNodeTracker[nodeTracker[road]][1]][this.playerOneNodeTracker[nodeTracker[road]][2]] = 30;  ChangeTurn();

                    }

                    if (placement == 2){this.playerOneSoldiers--; this.playerOneNodeTracker[nodeTracker[road]][4] = 2;
                    this.grid[this.playerOneNodeTracker[nodeTracker[road]][1]][this.playerOneNodeTracker[nodeTracker[road]][2]] = 32;  ChangeTurn();

                    }

                    if (placement == 3){this.playerOneCitiesToPlace--; this.playerOneNodeTracker[nodeTracker[road]][4] = -1;
                        this.grid[this.playerOneNodeTracker[nodeTracker[road]][1]][this.playerOneNodeTracker[nodeTracker[road]][2]] = this.playerOneCitiesAmount;
                        if (this.playerOneNodeTracker[nodeTracker[road]][0] == 1){playerOneCities[road][3] = 1;}
                        if (this.playerOneNodeTracker[nodeTracker[road]][0] == 2){playerOneCities[road][4] = 1;}
                        if (this.playerOneNodeTracker[nodeTracker[road]][0] == 3){playerOneCities[road][1] = 1;}
                        if (this.playerOneNodeTracker[nodeTracker[road]][0] == 4){playerOneCities[road][2] = 1;}
                        ChangeTurn();

                    }

                }
            }
            if (place == 2)
            {
                System.out.println("Area's where units are placed...");
                for (int i = 0; i < 36; i++)
                {
                    if (this.playerOneNodeTracker[i][0] > 0 && this.playerOneNodeTracker[i][4] > 0){

                        System.out.print(list + ") Settlement "); printSpecialCharacter(this.playerOneNodeTracker[i][3]);
                        printSpecialCharacter(this.playerOneNodeTracker[i][0] + 18);
                        printSpecialCharacter(this.playerOneNodeTracker[i][4] + 22);
                        System.out.println();
                        list = list + 1;
                        nodeTracker[list - 1] = i;
                    }
                }
                System.out.println("0) Back ");
                int unit = trycatch();
                if (unit == 0){ChangeTurn();}
                if (unit > list){System.out.println("Invalid Answer"); ManageUnits();}
                if (unit > 0)
                {
                    if (this.playerOneNodeTracker[nodeTracker[unit]][4] == 1){
                        this.playerOneWorkers++;
                        this.playerOneNodeTracker[nodeTracker[unit]][4] = 0;
                        this.grid[this.playerOneNodeTracker[nodeTracker[unit]][1]][this.playerOneNodeTracker[nodeTracker[unit]][2]] = 33;}
                    if (this.playerOneNodeTracker[nodeTracker[unit]][4] == 2){
                        this.playerOneSoldiers++;
                        this.playerOneNodeTracker[nodeTracker[unit]][4] = 0;
                        this.grid[this.playerOneNodeTracker[nodeTracker[unit]][1]][this.playerOneNodeTracker[nodeTracker[unit]][2]] = 33;}
                    ChangeTurn();
                }
            }
        }

        //*********************************************Player 2 *********************************************
        if (this.turn == -1)
        {
            int[] nodeTracker = new int[36];
            System.out.println("Do you want to... 1) place units? or 2) remove them?");
            System.out.println("0) Back ");
            int place = trycatch();
            int list = 1;
            if (place == 0){ChangeTurn();}
            if (place > 2){System.out.println("Invalid Answer"); ChangeTurn();}
            if (place == 1)
            {
                System.out.println("Area's available to place units...");
                for (int i = 0; i < 36; i++)
                {
                    if (this.playerTwoNodeTracker[i][0] > 0 && this.playerTwoNodeTracker[i][4] == 0){

                        System.out.print(list + ") Settlement "); printSpecialCharacter(this.playerTwoNodeTracker[i][3]+9);
                        printSpecialCharacter(this.playerTwoNodeTracker[i][0] + 18);
                        System.out.println();
                        list = list + 1;
                        nodeTracker[list - 1] = i;
                    }
                }
                System.out.println("0) Back ");
                int road = trycatch();
                if (road == 0){ChangeTurn();}
                if (road > list){System.out.println("Invalid Answer"); ChangeTurn();}
                if (road < list)
                {
                    System.out.println("What do you want to place?");
                    if (this.playerTwoWorkers > 0){ System.out.println("1) Worker");}
                    if (this.playerTwoSoldiers > 0){ System.out.println("2) Soldier");}
                    if (this.playerTwoCitiesToPlace > 0){ System.out.println("3) Settlement");}
                    System.out.println("0) Back");
                    int placement = trycatch();
                    if (placement == 0){ManageUnits();}
                    if (placement > 3){System.out.println("Invalid Answer"); ManageUnits();}

                    if (placement == 1){this.playerTwoWorkers--; this.playerTwoNodeTracker[nodeTracker[road]][4] = 1;
                        this.grid[this.playerTwoNodeTracker[nodeTracker[road]][1]][this.playerTwoNodeTracker[nodeTracker[road]][2]] = 30;  ChangeTurn();

                    }

                    if (placement == 2){this.playerTwoSoldiers--; this.playerTwoNodeTracker[nodeTracker[road]][4] = 2;
                        this.grid[this.playerTwoNodeTracker[nodeTracker[road]][1]][this.playerTwoNodeTracker[nodeTracker[road]][2]] = 32;  ChangeTurn();

                    }

                    if (placement == 3){this.playerTwoCitiesToPlace--; this.playerTwoNodeTracker[nodeTracker[road]][4] = -1;
                        this.grid[this.playerTwoNodeTracker[nodeTracker[road]][1]][this.playerTwoNodeTracker[nodeTracker[road]][2]] = this.playerTwoCitiesAmount + 9;
                        if (this.playerTwoNodeTracker[nodeTracker[road]][0] == 1){playerTwoCities[road][3] = 1;}
                        if (this.playerTwoNodeTracker[nodeTracker[road]][0] == 2){playerTwoCities[road][4] = 1;}
                        if (this.playerTwoNodeTracker[nodeTracker[road]][0] == 3){playerTwoCities[road][1] = 1;}
                        if (this.playerTwoNodeTracker[nodeTracker[road]][0] == 4){playerTwoCities[road][2] = 1;}
                        ChangeTurn();

                    }

                }
            }
            if (place == 2)
            {
                System.out.println("Area's where units are placed...");
                for (int i = 0; i < 36; i++)
                {
                    if (this.playerTwoNodeTracker[i][0] > 0 && this.playerTwoNodeTracker[i][4] > 0){

                        System.out.print(list + ") Settlement "); printSpecialCharacter(this.playerTwoNodeTracker[i][3]+9);
                        printSpecialCharacter(this.playerTwoNodeTracker[i][0] + 18);
                        printSpecialCharacter(this.playerTwoNodeTracker[i][4] + 22);
                        System.out.println();
                        list = list + 1;
                        nodeTracker[list - 1] = i;
                    }
                }
                System.out.println("0) Back ");
                int unit = trycatch();
                if (unit == 0){ChangeTurn();}
                if (unit > list){System.out.println("Invalid Answer"); ManageUnits();}
                if (unit > 0)
                {
                    if (this.playerTwoNodeTracker[nodeTracker[unit]][4] == 1){
                        this.playerTwoWorkers++;
                        this.playerTwoNodeTracker[nodeTracker[unit]][4] = 0;
                        this.grid[this.playerTwoNodeTracker[nodeTracker[unit]][1]][this.playerTwoNodeTracker[nodeTracker[unit]][2]] = 33;}
                    if (this.playerTwoNodeTracker[nodeTracker[unit]][4] == 2){
                        this.playerTwoSoldiers++;
                        this.playerTwoNodeTracker[nodeTracker[unit]][4] = 0;
                        this.grid[this.playerTwoNodeTracker[nodeTracker[unit]][1]][this.playerTwoNodeTracker[nodeTracker[unit]][2]] = 33;}
                    ChangeTurn();
                }
            }
        }
    }

    public void printSpecialCharacter(int key)
    {
        if(key == 1){System.out.print("A");}
        if(key == 2){System.out.print("B");}
        if(key == 3){System.out.print("C");}
        if(key == 4){System.out.print("Å");}
        if(key == 5){System.out.print("ß");}
        if(key == 6){System.out.print("Ç");}
        if(key == 7){System.out.print("Ä");}
        if(key == 8){System.out.print("฿");}
        if(key == 9){System.out.print("¢");}
        if(key == 10){System.out.print("X");}
        if(key == 11){System.out.print("Y");}
        if(key == 12){System.out.print("Z");}
        if(key == 13){System.out.print("⌘");}
        if(key == 14){System.out.print("¥");}
        if(key == 15){System.out.print("ζ");}
        if(key == 16){System.out.print("✥");}
        if(key == 17){System.out.print("ϒ");}
        if(key == 18){System.out.print("ϟ");}
        if(key == 19){System.out.print(" Right");}
        if(key == 20){System.out.print(" Down");}
        if(key == 21){System.out.print(" Left");}
        if(key == 22){System.out.print(" Up");}
        if(key == 23){System.out.print(" Worker ");}
        if(key == 24){System.out.print(" Soldier ");}


    }

    public int[] FindNode(int key) {
        int[] grid = {0,0};
        for (int i = 0; i < 34; i++) {
            for (int x = 0; x < 200; x++) {
                if (this.grid[i][x] == key){
                    grid[0]= i;
                    grid[1] = x;
                    return grid;
                }
            }
        }
        return grid;
    }
    public void PlayerOneWin(){
        this.gameOver = true;
        System.out.println("♛¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯♚");
        System.out.println("                           Player One has mined the diamond and has won the game");
        System.out.println("♚¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯♛");

    }
    public void PlayerTwoWin(){
        this.gameOver = true;
        System.out.println("☪•••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••✹");
        System.out.println("                           Player Two has mined the diamond and has won the game");
        System.out.println("✹•••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••••☪");
    }
    public void WrongInput(){
        scan.nextLine();
        System.out.println("Please enter Valid Input");
        mainMenu();
    }
    public int trycatch()
    {

        try {
           int answer = scan.nextInt();
            return answer;

        }
        catch (Exception e){
            return 0;}

    }
    public void mainMenu(){
        int answer = trycatch();
        if (answer == 1){StartRoad();}
        if (answer == 2){ContinueRoad();}
        if (answer == 3){BuyMenu();}
        if (answer == 4){ManageUnits();}
        if (answer == 5){if (this.turn == -1){AddMaterial();} this.turn *= - 1; UnitActions();  ChangeTurn();}
        if (answer == 6){ViewData();}
        if (answer == 0){WrongInput();}
    }

    public static void main(String[] args) {
        DiamondRush diamond = new DiamondRush();
        boolean done = false;


        while (!done){
            System.out.println();
            diamond.ChangeTurn();
            if (diamond.gameOver){done = true;}


        }
    }
}

