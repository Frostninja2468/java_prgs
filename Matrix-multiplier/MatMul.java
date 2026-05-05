import java.util.*;
public class MatMul 
{
						static int r1, c1, r2, c2;
						static int A[][], B[][], AB[][];
						static Scanner s = new Scanner(System.in);
						
						
						public static void Input()
						{
												System.out.println("Enter the values in Matrix A");
												int i, j;
												for(i = 0 ; i < r1 ; i++)
												{
																		for(j = 0 ; j < c1 ; j++)
																								A[i][j] = s.nextInt();
												}
												
												System.out.println("Enter the values in Matrix B");
												for(i = 0 ; i < r2 ; i++)
												{
																		for(j = 0 ; j < c2 ; j++)
																								B[i][j] = s.nextInt();
												}
												
												for(i = 0 ; i < r1 ; i++)
												{
																		for(j = 0 ; j < c2 ; j++)
																								AB[i][j] = 0;
												}
						}
						
						public static void Compute()
						{
												int i, j, k;
												for(i = 0 ; i < r1 ; i++)
												{
																		for(j = 0 ; j < c2 ; j++)
																		{
																								for(k = 0 ; k < c1 ; k++)
																														AB[i][j] += A[i][k]*B[k][j];
																		}
												}
						}
						
						public static void Display()
						{
												int i, j, max = Math.max(r1, r2);
												for(i = 0 ; i < max ; i++)
												{
																		for(j = 0 ; j < 2*(c1 + c2) ; j++)
																		{
																								if(j < c1)//for A matrix
																								{
																														if(i<r1)
																														{
																																				if(j == 0)
																																										System.out.print("|");
																																				System.out.print(A[i][j] + ((j == (c1-1))?"":" "));
																																				if(j == (c1-1))
																																										System.out.print("|");
																														}
																														else
																																				System.out.print("  ");
																								}
																								
																								
																								else if(j == c1 )//for gap b/w A and B matrix
																								{
																														if(i==max-1)
																																				System.out.print(" x ");
																														else
																																				System.out.print("   ");
																								}
																														
																														
					 															else if(j < (c1 + c2 + 1))//for B matrix
					 															{
																														if(i<r2)
																														{
																																				if(j == (c1 + 1))
																																										System.out.print("|");
																																				System.out.print(B[i][j - (c1 + 1)] + (j == (c1 + c2)?"":" "));
																																				if(j == (c1 + c2))
																																										System.out.print("|");
																														}
																														else
																																				System.out.print("  ");
																								}
					 																					
					 																					
																						else if(j == (2*c1 + c2 - 1))//for gap between B and AB matrix
																						{
																												if(i==max-1)
																																				System.out.print("--> ");
																														else
																																				System.out.print("    ");
																						}
																												
																												
																				else//for AB matrix
																				{
																														if(i<r1)
																														{
																																				if(j == (c1 + c2 + 2))
																																										System.out.print("|");
																																				System.out.print(AB[i][j - (2*c1 + c2)] + ((j == 2*(c1 + c2) - 1)?"":" "));
																																				if(j == 2*(c1 + c2) - 1)
																																										System.out.print("|");
																														}
																														else
																																				System.out.print("  ");
																								}
																		}
																		System.out.println();
												}
						}
						
						public static void main(String args[])
						{
												Scanner s = new Scanner(System.in);
												System.out.println("Enter the order of the first matrix : ");
												r1 = s.nextInt();
												c1 = s.nextInt();
												System.out.println("Enter the order of the second matrix : ");
												r2 = s.nextInt();
												c2 = s.nextInt();
												if(c1==r2)
												{
																		A  = new int[r1][c1];
																		B  = new int[r2][c2];
																		AB = new int[r1][c2];
																		Input();
																		Compute();
																		Display();
												}
												else
																		System.out.println("A and B are not compatible for multiplication");
						}
}