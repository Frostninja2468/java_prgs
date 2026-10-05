import java.util.*;
public class Hangman
{
    static Scanner s = new Scanner(System.in);
    static char[] alph = new char[6];        
    static String word, wrng = "", gused="";
    static int hang = 0;
    static char[] wrd;
    
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

    public static void arprint(char[] ar) 
    {
        int r = ar.length;
        for(int i = 0; i < r; i++)
        {
            System.out.print(ar[i] + " ");
        }
        System.out.println();
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
            if(Character.isLetter(str.charAt(i)))
                return false;
        }
        return true;
    }

    public static void process(char ch)
    {
        String t = word;
        if(t.indexOf(ch) >= 0)
        {
            hang(hang);
            addcor(ch);
            arprint(wrd);
            if(wrng.length() > 0)
                System.out.println("Wrong: " + wrng);

        }
        else
        {
            wrng = wrng + ch + " ";
            hang(++hang);
            System.out.println("Wrong: " + wrng);
        }
    }

    public static void addcor(char ch)
    {
        String str = word;

        for(int p = 0, i = 0; str.indexOf(ch) >= 0; str = str.substring(str.indexOf(ch) + 1), i++)
        {
            p = p + str.indexOf(ch) + ((i == 0)?0:1);
            wrd[p] = ch;
        }
    }

// Test word: character
    public static void main(String[] args) 
    {
        boolean win = false;
        System.out.print("Enter the word to guess(with no spaces): ");

        for(word = s.nextLine() ; word.indexOf(" ") >= 0 || check(word) ; word = s.nextLine())
        {
            System.out.println("Invalid Input");
            System.out.print("Enter the word to guess(with no spaces): ");
        }
        wrd = new char[word.length()];
        for(int i = 0 ; i < word.length() ; wrd[i++] = '_');

        clearScreen();

        System.out.println("Guess the word in 6 tries");
        System.out.println("To forfeit, enter 0");
        
        String gu = " ";
        arprint(wrd);
        for(;hang <= 6 && !win ; )
        {
            System.out.print("Enter you guess: ");
            gu = s.next();

            if(gu.equals("reveal"))
            {
                clearScreen();
                System.out.println("The word is: " + word);
                continue;
            }
            else if(gu.length() > 1)
            {
                System.out.println("Invalid Input");
                continue;
            }
            else if( gu.equals("0"))
                break;

            if(gused.indexOf(gu) >= 0)
            {
                clearScreen();
                System.out.println(gu + " has been already guessed");
                continue;
            }
            gused = gused + gu.charAt(0);

            clearScreen();
            process(gu.charAt(0));

            win = true;
            for(int i = 0 ; i < wrd.length && win ; i++)
                win = wrd[i] != '_';
        }

        if(!win)
            System.out.println("The word was: " + word);
        else
            System.out.println("Congratulations, you won with " + (6 - hang) + " tries to go");
    }
}