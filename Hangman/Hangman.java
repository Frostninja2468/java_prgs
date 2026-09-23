import java.util.*;
public class Hangman
{
    static Scanner s = new Scanner(System.in);
    static char[] alph = new char[6];
    
    static String[][] head = { {"", " /",  " ", " ", " ",  "\\"}, 
                               {"", " |",  " ", " ", " ",  "|"}, 
                               {"", " \\", "_", "_", "_",  "/"}};

    static String[][] larm =  { {" ", " ", " ", "|"},
                                {" ", " ", "/", "|"},
                                {" ", "/", " ", "|"},
                                {"/", " ", " ", "|"}};

    static String[][] body =  { {" ", " ", " ", "|"},
                                {" ", " ", " ", "|"},
                                {" ", " ", " ", "|"},
                                {" ", " ", " ", "|"}};
                               
    static String[][] rarm =  { {" ", " ", " ", "|", "  ", " ", " "},
                                {" ", " ", "/", "|", "\\", " ", " "},
                                {" ", "/", " ", "|", " ", "\\", " "},
                                {"/", " ", " ", "|", " ", " ", "\\"}};

    static String[][] lleg =  { {" ", " ", " ", "|", "  ", " "}, 
                                {" ", " ", "/", " ", "  ", " "}, 
                                {" ", "/", " ", " ", " ", "  "}};                                
                                
    static String[][] rleg =  { {" ", " ", " ", "|", "  ", " "}, 
                                {" ", " ", "/", " ", "\\", " "}, 
                                {" ", "/", " ", " ", " ", "\\"}};

    public static void clearScreen() 
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void arprint(String[][] ar) 
    {
        int r = ar.length, c = ar[0].length;
        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++)
            {
                System.out.print(ar[i][j]);
            }
            System.out.println();
        }
    }

    public static void hang(int c) 
    {
        switch(c)
        {
            case 0:
                System.out.println();
                break;

            case 1:
                arprint(head);
                break;
            
            case 2:
                arprint(head);
                arprint(body);
                break;

            case 3:
                arprint(head);
                arprint(larm);
                break;

            case 4:
                arprint(head);
                arprint(rarm);
                break;

            case 5:
                arprint(head);
                arprint(rarm);
                arprint(lleg);
                break;

            case 6:
                arprint(head);
                arprint(rarm);
                arprint(rleg);
                break;

        }
    }

    public static boolean check(String str) 
    {
        int i, l = str.length();
        for(i = 0 ; i < l ; i++)
        {
            if(!Character.isLetter(str.charAt(i)))
                return false;
        }
        return true;
    }

// Test word: character
    public static void main(String[] args) 
    {
        int wr = 0;
        System.out.print("Enter the word to guess(with no spaces): ");
        
        String word;
        for(word = s.next() ; word.indexOf(" ") >= 0 || check(word) ; word = s.next())
        {
            System.out.println("Invalid Input");
            System.out.print("Enter the word to guess(with no spaces): ");
        }
        String[] wrd = new String[word.length()];
        clearScreen();

        System.out.println("Guess the word in 6 tries");
        System.out.println("To forfeit, enter 0");
        
        String gu = " ";
        for(; gu.equals("0") && wr <= 6 ;)
        {
            gu = s.next();
            if(gu.length() > 1)
            {
                clearScreen();
                System.out.println("Invalid Input");
                continue;
            }
        }
    }
}