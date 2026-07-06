import java.util.*;
public class Connect_4
{
    static final Scanner s = new Scanner(System.in);
    static boolean play = true, win = false;
//                 play = true => player 1  => X 
//                 play = false => player 2 => O
    static int x, y;
    static String che[] = {"", "", "", ""};
    static String[][] grd = {{"[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]"},
                             {"[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]"},
                             {"[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]"},
                             {"[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]"},
                             {"[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]"},
                             {"[ ]","[ ]","[ ]","[ ]","[ ]","[ ]","[ ]"}};
    
                             
    public static void main(String args[])
    {
        System.out.println("\'X\' is player 1");
        System.out.println("\'O\' is player 2");
        System.out.println("To forfeit, enter 0");
        String inc;

        display();
        for( ; !win ; )
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
            win = check();
        }
        System.out.println("Congratulations, Player " + ((!play)?"1 ":"2 ") + " won the game");
        System.out.println("out of game");
    }

    public static void enter()
    {
        int l;
        for(l = 0 ; l < 6; l++)
        {
            if(grd[l][x].equals("[X]") || grd[l][x].equals("[O]"))
                break;
        }
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
            System.out.println(i + 1);
            System.out.println("---------------------------");
        }
    }

    public static void reset(String[] ar)
    {
        for(int i = 0 ; i < 4 ; i++)
            ar[i] = "";
    }

    public static int length(String[] ar)
    {
        int l = 0, i;
        for(i = 0 ; i < 4 ; i++)
        {
            if(ar[i].equals(""))
                break;
            else
                l++;
        }
        return l;
    }

    /*  ch ---> check:
    1. Top Right            5. Bottom Left
    2. Right                6. Left
    3. Bottom Right         7. Top Left
    4. Bottom

*/

    public static boolean check()
    {
        String pl = (!play)?"[X]":"[O]";
        String tra[] = new String[4];
        int ch, i, xc, yc, len;

        for(i = 0 ; i < 4 ; i++)
            che[i] = pl;

        boolean dne = false;
        for(ch = 1 ; ch <= 7 && !dne; ch++)
        {
            reset(tra);
            xc = x;
            yc = y;
            System.out.println(xc + " " + yc);
            switch(ch)
            {
                case 1 :
                    for(i = 1, dne = true ; i <= 4 && xc < 7 && yc >= 0 && dne ; i++, xc++, yc--)
                    {
                        tra[i - 1] = grd[yc][xc];
                        System.out.println(dne + " " + ch + " " + xc + " " + yc + " " + grd[yc][xc] + " " + pl);
                    }
                    break;

                case 2 :
                    for(i = 1, dne = true ; i <= 4 && xc < 7 && dne ; i++, xc++)
                    {
                        tra[i - 1] = grd[yc][xc];
                        System.out.println(dne + " " + ch + " " + xc + "   " + grd[yc][xc] + " " + pl);
                    }
                    break;

                case 3 :
                    for(i = 1, dne = true ; i <= 4 && xc < 7 && yc < 6 && dne; i++, xc++, yc++)
                    {
                        tra[i - 1] = grd[yc][xc];
                        System.out.println(dne + " " + ch + " " + xc + " " + yc + " " + grd[yc][xc] + " " + pl);
                    }
                    break;

                case 4 :
                    for(i = 1, dne = true ; i <= 4 && yc < 6 && dne ; i++, yc++)
                    {
                        tra[i - 1] = grd[yc][xc];
                        System.out.println(dne + " " + ch + "   " + yc + " " + grd[yc][xc] + " " + pl);
                    }
                    break;

                case 5 :
                    for(i = 1, dne = true ; i <= 4 && xc >= 0 && yc < 6 && dne ; i++, xc--, yc++)
                    {
                        tra[i - 1] = grd[yc][xc];
                        System.out.println(dne + " " + ch + " " + xc + " " + yc + " " + grd[yc][xc] + " " + pl);
                    }
                    break;

                case 6 :
                    for(i = 1, dne = true ; i <= 4 && xc >= 0 && dne ; i++, xc--)
                    {
                        tra[i - 1] = grd[yc][xc];
                        System.out.println(dne + " " + ch + " " + xc + "   " + grd[yc][xc] + " " + pl);
                    }
                    break;

                case 7 :
                    for(i = 1, dne = true ; i <= 4 && xc >= 0 && yc >= 0 && dne ; i++, xc--, yc--)
                    {
                        tra[i - 1] = grd[yc][xc];
                        System.out.println(dne + " " + ch + " " + xc + " " + yc + " " + grd[yc][xc] + " " + pl);
                    }
                    break;

            }

            len = length(tra);
            if(len == 4)
                dne = Arrays.equals(che, tra);
            else
                dne = false;
            System.out.println(dne + " " + ch + " " + xc + " " + yc + " " + i + " " + len);
            System.out.println("Tra = " + Arrays.toString(tra));
            System.out.println("Che = " + Arrays.toString(che) + "\n");
        }
        return dne;
    }
}