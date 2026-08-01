import java.io.File;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Battle_ships 
{
    final static Scanner s = new Scanner(System.in);
    final static Random r = new Random();
    static int tri = 0, cnt = 0;
    static char grd[][] = {{'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},//to store the position of the ships
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'}};
    
    static String pos = "";
    static char gue[][] = {{'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},//for the player to guess on
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'},
                           {'≈','≈','≈','≈','≈','≈','≈','≈','≈','≈'}};

    static String ships[][] = {{"","","0","0","0"},//to store the position
                               {"","","","0","0"},
                               {"","","","0","0"},
                               {"","","","","0"},
                               {"","","","",""}};
    
    public static void main(String args[])
    {
        clearScreen();
        String str;// to store the co-ordinate
        randomize();// randomize function
        System.out.println("Enter the co-ordinate you want to search in \"AB\" format");
        System.out.println("Enter 0 to forfeit");
        System.out.println("\'O\' represents that ship is on that position");
        System.out.println("\'X\' represents that ship is not on that position");
        print(1);
        for(;;)
        {
            System.out.print("Enter the co-ordinate: ");
            str = s.next().toUpperCase();
            clearScreen();
            System.out.println(str);

            if(str.equals("0"))//if the player wants to forfeit
                break;

            if(str.equals("KK"))//admin command to show the positions of all ship parts
            {
                print(2);
                continue;
            }
            int x = (int)(str.charAt(0) - 65), y = (int)(str.charAt(1) - 65);//seperate the string co-ordinate into 
            //                                                                 something that can be used with array
            if(str.length() != 2 || x < 0 || x > 9 || y < 0 || y > 9 )//to check if the co=ordinates are valid or not
            {
                System.out.println("Invalid co-ordinate");
                continue;
            }
            
            check(str, x, y);//check function
            print(1);//print function
            
            if(cnt == 17)//if all the boats are found, then break the loop
                break;

        }
        if(cnt == 17)
            System.out.println("Congratulations, you won in " + tri + " tries");

        if(str.equals("0"))
        {
            print(3);
            System.out.println("No. of tries: " + tri);
            System.out.println("No. of battleship parts destroyed: " + cnt);
        }
        s.close();
    }

    static void check(String str, int x, int y)//to check if the co-ordinate has a battleship part on it  
    {//                                          and then to reflect the change into the player matrix
        int i, j;
        boolean dne = true;
        if(grd[y][x] == '■')//if there is a battleship part on the guessed co-ordinate
        {
            gue[y][x] = 'O';
            cnt++;
            for(i = 0 ; i < 5 ; i++)//to keep track of every boat and boat part that has been destroyed
            {
                dne = true;
                for(j = 0 ; j < 5 ; j++)
                {
                    if(str.equalsIgnoreCase(ships[i][j]))
                        ships[i][j] = "0";
                    if(!ships[i][j].equals("0"))
                        dne = false;
                }
                if(dne)
                    Bell("Bell.wav", i);//Bell function
            }
        }
        else//if there isn't a battleship part on the guessed co-ordinate
            gue[y][x] = 'X';
        tri++;
    }

    public static void Bell(String loc, int sh)//to notify the player if all parts of a single battleship are destroyed
    {
        try
        {
            File musPat = new File(loc);

            if(musPat.exists())
            {//the following if block displays which battleship is destroyed
                if(sh == 0)
                    System.out.println("Congrats, you discovered the 2 ship");
                else if (sh == 1 || sh == 2)
                    System.out.println("Congrats, you discovered a 3 ship");
                else if (sh == 3)
                    System.out.println("Congrats, you discovered the 4 ship");
                else if (sh == 4)
                    System.out.println("Congrats, you discovered the 5 ship");

                AudioInputStream audin = AudioSystem.getAudioInputStream(musPat);
                Clip clip = AudioSystem.getClip();
                clip.open(audin);
                clip.start();
                s.nextLine();
                
                for(int i = 0 ; i < 5 ; i++)
                    ships[sh][i] = "  ";
            }
            else
                System.out.println("Can't find file");

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

/*
    x refers to type of grid has to displayed:
    1 --> standard grid, for normal playing
    2 --> admin grid   , to display the main grid
    3 --> forfit grid  , if the player forfeits, then both grids are displayed to 
                         show which battleship part they couldn't find
*/
    static void print(int x)//to print the appropriate grid
    {
        int i, j;
        System.out.print("   A B C D E F G H I J");
        if(x == 3)
            System.out.println("\t\tA B C D E F G H I J");
        else
            System.out.println();

        System.out.print("   -------------------");
        if(x ==3)
            System.out.println("\t\t-------------------");
        else
            System.out.println();
        
        for(i = 0 ; i < 10 ; i++)
        {
            System.out.print((char)(i + 65) + " |");
            for(j = 0 ; j < 10 ; j++)
            {
                if(x == 1)
                    System.out.print(gue[i][j] + " ");
                else if(x == 2)
                    System.out.print(grd[i][j] + " ");
                else
                    System.out.print(grd[i][j] + " ");
            }
            if(x == 3)
            {
                System.out.print("\t\t");
                for(j = 0 ; j < 10 ; j++)
                    System.out.print(gue[i][j] + " ");
            }
            System.out.println();
        }
    }


/*  dir refers to direction:
        1 --> up
        2 --> right
        3 --> down
        4 --> left
        
        noob --> not out of bound => checks if the random co-ordinate chosen doesn't go out of bounds of the array
        novla --> not overlaping  => checks if the random co-ordinate chosen doesn't overlap with any other ship
*/
    static void randomize()//to randomly place the ships in the grid
    {//i & j are loop variables, k controls the size of the battleship, siz refers to the size of the battleship, 
        int dir, i, j, k , siz, posx = 0, posy = 0;//posx and posy together give the random co-ordinate of the battleship
        boolean noob = false, novla = false;
//      this loop is to check if the co-ordinates are        
        for(i = 1 , siz = 2; i <= 5 ; siz += ((i == 2)?0:1) , i++)// size always goes in the same order:
        {//                                                          2 --> 3 --> 3 --> 4 --> 5
            dir = r.nextInt(4) + 1;//randomly generating the direction
            for(noob = false; !(noob && novla) ; )//loops until both noob and novla are true
            {
                posx = r.nextInt(10);//randomly generating the co-ordinate
                posy = r.nextInt(10);
                switch(dir)
                {
                    case 1://if direction is up
                        noob = posy > (siz - 1);

                        for(j = posy, novla = true, k = 1; k <= siz && noob && novla ; j--, k++)   
                            novla = grd[j][posx] != '■';
                        break;
                        
                    case 2://if direction is right
                        noob = (9 - posx) > (siz - 1);

                        for(j = posx, novla = true, k = 1 ; k <= siz && noob && novla ; j++, k++)
                            novla = grd[posy][j] != '■';
                        break;

                    case 3://if direction is down
                        noob = (9 - posy) > (siz - 1);

                        for(j = posy, novla = true, k = 1 ; k <= siz && noob && novla ; j++, k++)
                            novla = grd[j][posx] != '■';
                        break;
                        
                    case 4://if direction is left
                        noob = posx > (siz - 1);

                        for(j = posx, novla = true, k = 1 ; k <= siz && noob && novla ; j--, k++)
                            novla = grd[posy][j] != '■';
                        break;
                }            
            }
            k = 1;
//          this loop is to place the battleship parts on the grid after they have been finallized in the previous loop
            switch(dir)
            {
                case 1://if direction is up
                    for( j = posy ; k <= siz ; j--, k++)
                    {
                        grd[j][posx] = '■';
                        ships[i - 1][k - 1] = Character.toString((char)(posx + 65)) + Character.toString((char)(j + 65));
                    }
                    break;

                case 2://if direction is right
                    for( j = posx ; k <= siz ; j++, k++)
                    {
                        grd[posy][j] = '■';
                        ships[i - 1][k - 1] = Character.toString((char)(j + 65)) + Character.toString((char)(posy + 65));
                    }
                    break;

                case 3://if direction is down
                    for( j = posy ; k <= siz ; j++, k++)
                    {
                        grd[j][posx] = '■';
                        ships[i - 1][k - 1] = Character.toString((char)(posx + 65)) + Character.toString((char)(j + 65));
                    }
                    break;

                case 4://if direction is left
                    for( j = posx ; k <= siz ; j--, k++)
                    {
                        grd[posy][j] = '■';
                        ships[i - 1][k - 1] = Character.toString((char)(j + 65)) + Character.toString((char)(posy + 65));
                    }
                    break;
            }
        }
    }

    public static void clearScreen() 
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}