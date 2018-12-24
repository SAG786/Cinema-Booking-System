import java.util.*;
import java.io.*;
import java.util.regex.*; 
class Info
{
	String name,mobno,email;
	int gender;// 0 for woman, 1 for man
}
public class Cinema
{
	public static boolean isValidMob(String s) 
    { 
        	Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}"); 
        	Matcher m = p.matcher(s); 
        	return (m.find() && m.group().equals(s)); 
    } 
	public static void main(String []args) throws Exception
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Scanner sc=new Scanner(System.in);
		Connection con=DriverManager.getConnection("jdbc:odbc:Cinema");
		PreparedStatement ps=con.prepareStatement("insert into Ticket values(?,?,?,?)");
		int opt,num,x,y;
		String name,mobno,email;
		int gender;
		int n=5;
		int seat1[][]=new int[n][n];
		int seat2[][]=new int[n][n];
		int seat3[][]=new int[n][n];
		int ref1[][]=new int[n][n];
		int ref2[][]=new int[n][n];
		int ref3[][]=new int[n][n];
		ArrayList<Info>Andhadhun=new ArrayList<Info>();
		ArrayList<Info>Deadpool=new ArrayList<Info>();
		ArrayList<Info>Bazaar=new ArrayList<Info>();
		int pos_a=0,pos_c=0,pos_b=0,mov;
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
				seat1[i][j]=seat2[i][j]=seat3[i][j]=0;
		}
		
		
		while(true)
		{
			System.out.print("\n\t\tCinema Booking System\n\nEnter which movie do you want to see:\n1.Andhadhun\n2.Deadpool\n3.Bazaar\n4.Exit\nOption:");
			mov=sc.nextInt();
			if(mov<0&&mov>=4)
				break;
			while(true)
			{
				System.out.print("\n\n1.Buy/Book a ticket\n2.View the Cinema Hall\n3.Count\n4.View Ticket Details\n5.Exit\n\nOption:");
				opt=sc.nextInt();
				if(opt<1||opt>4)
					break;
				switch(opt)
				{
					case 1:
							System.out.print("\nEnter how many tickets do you want to buy:");
							num=sc.nextInt();
							for(int i=0;i<num;i++)
							{
								Info obj=new Info();
								while(true)
								{
									System.out.println("Enter position of seats to book :");
									x=sc.nextInt();
									y=sc.nextInt();
									if((x<0&&x>=n)||(y<0&&y>=n))
									{
										System.out.println("Invalid Position");
										continue;
									}
									if((mov==1&&seat1[x][y]==1)||(mov==2&&seat2[x][y]==1)||(mov==3&&seat3[x][y]==1))
									{
										System.out.println("This seat is already filled\nTry again");
										continue;
									}
									if(mov==1)
										seat1[x][y]=1;
									else if(mov==2)
										seat2[x][y]=1;
									else 	
										seat3[x][y]=1;
									break;
								}
								System.out.println("Enter Following details:");
								System.out.print("Name:");
								name=sc.next();
								while(true)
								{
									System.out.print("Mobile Number:");
									mobno=sc.next();
									if(isValidMob(mobno))
										break;
									System.out.println("Invalid..please type again");
								}
								System.out.print("Gender(0 for female, 1 for male):");
								gender=sc.nextInt();
								System.out.print("Enter email-address:");
								email=sc.next();
								obj.name=name;
								obj.mobno=mobno;
								obj.email=email;
								obj.gender=gender;
								if(mov==1)
								{
									Andhadhun.add(obj);
									ref1[x][y]=pos_a;
									pos_a++;
								}
								else if(mov==2)
								{
									Deadpool.add(obj);
									ref2[x][y]=pos_b;
									pos_b++;
								}
								else
								{
									Bazaar.add(obj);
									ref3[x][y]=pos_c;
									pos_c++;
								}
							}	
							break;
					case 2:
						/*	System.out.println("-----------------------------------------------------");
							System.out.println("--------------------Screen---------------------------");
							System.out.println("-----------------------------------------------------");*/
							
							System.out.println(" ---------------------");
							System.out.println(" -------SCREEN--------");
							System.out.println(" ---------------------");
							if(mov==1)
							{
								for(int i=0;i<n;i++)
								{
									for(int j=0;j<n;j++)
									{
										if(j==0)
											System.out.print(" | ");
										System.out.print(seat1[i][j]+" | ");
										if(j==n-1)	
											System.out.println();	
									}
									System.out.println(" ---------------------");
						
								}
							}
							else if(mov==2)
							{
								for(int i=0;i<n;i++)
								{
									for(int j=0;j<n;j++)
									{
										if(j==0)
											System.out.print(" | ");
										System.out.print(seat2[i][j]+" | ");
										if(j==n-1)	
											System.out.println();	
									}
									System.out.println(" ---------------------");
						
								}
							}
							
							else 
							{
								for(int i=0;i<n;i++)
								{
									for(int j=0;j<n;j++)
									{
										if(j==0)
											System.out.print(" | ");
										System.out.print(seat3[i][j]+" | ");
										if(j==n-1)	
											System.out.println();	
									}
									System.out.println(" ---------------------");
						
								}
							}
							break;
					case 3:
						
							if(mov==1)
								System.out.println("Out Off 25 tickets only "+ (25-pos_a) +" tickets are remaining");
							else if(mov==2)
								System.out.println("Out Off 25 tickets only "+ (25-pos_b) +" tickets are remaining");	
							else
								System.out.println("Out Off 25 tickets only "+ (25-pos_c) +" tickets are remaining");
							break;
					case 4:
							while(true)
							{
								System.out.print("Enter the (Position):");
								x=sc.nextInt();
								y=sc.nextInt();
								if((mov==1&&seat1[x][y]!=0)||(mov==2&&seat2[x][y]!=0)||(mov==3&&seat3[x][y]!=0))
								{
									break;
								}
								System.out.println("Seat is empty here..please try again...");
							}
							Info data=new Info();
							if(mov==1)
								data=Andhadhun.get(ref1[x][y]);
							else if(mov==2)
								data=Deadpool.get(ref2[x][y]);
							else
								data=Bazaar.get(ref3[x][y]);
								
							System.out.println("\n\t\tDetails\nName:"+data.name+"\nMobile Number:"+data.mobno+"\nEmail address:"+data.email);
							if(data.gender==0)
								System.out.println("Gender:female\n");
							else
								System.out.println("Gender:male\n");
							break;
				}
			}
		}			
	}
}
