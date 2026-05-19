import java.util.*;
public class Connect_4
{
    static final Scanner s = new Scanner(System.in);
    static boolean play = true, dne = true;
//                 play = true => player 1  => X 
//                 play = false => player 2 => O
    static int x, y;
    
    static String[][] grd = {{"[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]"},
                             {"[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]"},
                             {"[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]"},
                             {"[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]"},
                             {"[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]"},
                             {"[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]"},};
    
                             
    public static void main(String args[])
    {
        System.out.println("\'X\' is player 1");
        System.out.println("\'O\' is player 2");
        System.out.println("To forfeit, enter 0");
        String inc;

        display();
        for( ; dne ; )
        {
            inc = s.next().toUpperCase();
            if(inc.charAt(0) == '0' )
                break;

            if(inc.length() > 1 || ((int)inc.charAt(0)) > 72 || ((int)inc.charAt(0)) < 65)
            {
                System.out.println("Invalid co-ordinate");
                continue;
            }

            x = inc.charAt(0) - 65;
            enter();
            display();

        }
    }
    public static void enter()
    {
        int l;
        for(l = 0 ; l < 6; l++)
        {
            if(grd[l][x].equals("[X]") || grd[l][x].equals("[O]"))
                break;
        }
        System.out.println(l + " " + x);
        if(l == 0)
            System.out.println("No space left in column " + (char)(x + 65));
        else
        {
            grd[l - 1][x] = (play)?"[X]":"[O]";
            play = !play;
            y = l - 1;
        }
    }
    
    public static void display()
    {
        int i, j;
        System.out.println(" A   B   C   D   E   F   G");
        System.out.println("---------------------------");
        for(i = 0 ; i < 6 ; i++)
        {
            for(j = 0 ; j < 7 ; j++)
                System.out.print(grd[i][j] + " ");
            System.out.println();
            System.out.println("---------------------------");
        }
    }
/*  ch ---> check:
    1. Top Right            5. Bottom Left
    2. Right                6. Left
    3. Bottom Right         7. Bottom Left
    4. Bottom

*/

    public static void check()
    {
        String pl = (!play)?"[X]":"[O]";
        int ch, i, xc, yc;
        for(ch = 1 ; ch <= 7 && !dne; ch++)
        {
            xc = x;
            yc = y;
            switch(ch)
            {
                case 1 :
                    for(i = 1 ; i <= 4 && !dne ; i++, xc--, yc--)
                    {
                        if(grd[yc][xc].equals(pl))
                            dne = false;
                    }
                    break;

                case 2 :
                    for(i = 1 ; i <= 4 && !dne ; i++)
                    {
                        if(grd[yc][xc].equals(pl))
                            dne = false;
                    }
                    break;

                case 3 :
                    for(i = 1 ; i <= 4 && !dne; i++, xc++, yc++)
                    {
                        if(grd[yc][xc].equals(pl))
                            dne = false;
                    }
                    break;

                case 4 :
                    for(i = 1 ; i <= 4 && !dne ; i++)
                    {
                        if(grd[yc][xc].equals(pl))
                            dne = false;
                    }
                    break;

                case 5 :
                    for(i = 1 ; i <= 4 && !dne ; i++)
                    {
                        if(grd[yc][xc].equals(pl))
                            dne = false;
                    }
                    break;

                case 6 :
                    for(i = 1 ; i <= 4 && !dne ; i++)
                    {
                        if(grd[yc][xc].equals(pl))
                            dne = false;
                    }
                    break;

                case 7 :
                    for(i = 1 ; i <= 4 && !dne ; i++)
                    {
                        if(grd[yc][xc].equals(pl))
                            dne = false;
                    }
                    break;

            }
        }
    }
}