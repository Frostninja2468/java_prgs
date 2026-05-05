import java.util.*;
public class Simoul_Solver 
{
						static Scanner s = new Scanner(System.in);
						static String eq1, eq2;
						static int a1, a2, b1, b2, c1, c2;
						static int ix1, iy1, io1, ie1, ix2, iy2, io2, ie2;
						static double x, y;
						
						public static void extract()
						{
												ix1=eq1.indexOf('x');
												iy1=eq1.indexOf('y');
												if(eq1.indexOf('+') != -1)
																		io1=eq1.indexOf('+');
												else
																		io1=eq1.indexOf('-');
												ie1=eq1.indexOf('=');
												
												ix2=eq2.indexOf('x');
												iy2=eq2.indexOf('y');
												if(eq2.indexOf('+') != -1)
																		io2=eq2.indexOf('+');
												else
																		io2=eq2.indexOf('-');
												ie2=eq2.indexOf('=');
						}
						
						public static void convert()
						{
												String ssch;
												
												ssch=eq1.substring( 0 , ix1);
												if(ssch.equals(""))
																		a1=1;
												else
																		a1=Integer.parseInt(eq1.substring( 0 , ix1));
												
												ssch=eq1.substring( io1 + 1 , iy1).trim();
												if(ssch.equals(""))
																		b1=1;
												else
																		b1=Integer.parseInt(eq1.substring( io1 + 1 , iy1).trim());
												if(eq1.charAt(io1) == '-')
																		b1*=-1;
												
												c1=Integer.parseInt(eq1.substring( ie1 + 1 , eq1.length()).trim());
												
												ssch=eq2.substring( 0 , ix2);
												if(ssch.equals(""))
																		a2=1;
												else
																		a2=Integer.parseInt(eq2.substring( 0 , ix2));
												
												ssch=eq2.substring( io2 + 1 , iy2).trim();
												if(ssch.equals(""))
																		a2=1;
												else
																		b2=Integer.parseInt(eq2.substring( io2 + 1 , iy2).trim());
												if(eq2.charAt(io2) == '-')
																		b2*=-1;
												
												c2=Integer.parseInt(eq2.substring( ie2 + 1 , eq2.length()).trim());
						}
						
						public static int LCM(int a, int b)
						{
												int i, j;
												a = a * ((a>0)?1:-1);
												b = b * ((b>0)?1:-1);
												big_loop:
												for(i = a ; ; i+=a)
												{
																		for(j = b ; ; j+=b)
																		{
																								if(i==j)
																														break big_loop;
																								else if(i>j)
																														continue;
				      					else
				      											continue big_loop;
																		}
												}
												return i;
						}
						
						public static void comp_x()
						{
												int l = LCM(b1,b2);
												System.out.println(l);
												a1 *= l/b1;
												c1 *= l/b1;
												b1 *= l/b1;
												
												a2 *= l/b2;
												c2 *= l/b2;
												b2 *= l/b2;
												
												System.out.println("a1: " + a1);
												System.out.println("b1: " + b1);
												System.out.println("c1: " + c1);
												System.out.println("a2: " + a2);
												System.out.println("b2: " + b2);
												System.out.println("c2: " + c2);
												
												if((b1>0)==(b2>0))
																		x = (c1 - c2)*1.0/(a1 - a2);
												else
																		x = (c1 + c2)*1.0/(a1 + a2);
						}
						
						public static void main(String args[])
						{
												System.out.println("Enter the equations:");
												eq1 = s.nextLine();
												eq2 = s.nextLine();
												extract();
												convert();
												comp_x();
												y = (c1 - a1*x)/b1;
												System.out.println("The value for" + eq1 +" and " + eq2 + " are:");
												System.out.println("x = " + x);
												System.out.println("y = " + y);
						}
}