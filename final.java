package revision.Finalyrproj;


/**
 * Write a description of class Algo2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Algo2
{   
    int fretb[][]=new int[6][20];//fretboard array
    Vector<Integer> c=new Vector<Integer>();//array of notes of chord
    /*boolean first=false;
    boolean third= false;
    boolean fifth= false;*/
    boolean open[]=new boolean[6];
    int window[][]=new int[6][4];//dda for fretboard window of 6x4
    int strings[]=new int[6];
    void noteMap()//initialize fretboard dda fully
    {
        int i,j;
        for(i=0;i<6;i++)
        {
           for(j=1;j<20;j++)
           {
               fretb[i][j]=fretb[i][j-1] +1;
               if(fretb[i][j]>12)
               {
                   fretb[i][j]=fretb[i][j]-12;
                }
           }
        }
        System.out.println();
        for(i=0;i<6;i++)
        {
            for(j=0;j<20;j++)
            {
                System.out.print(fretb[i][j]+"\t|");
            }
            System.out.println();
        }
    }
    
    void chord(int ch, int t)//find notes of chord
    {
        Hashtable<Integer, String> chord= new Hashtable<Integer,String>();
        chord.put(1,"1 8 5");//formula for major for 1 chord
        chord.put(2,"1,8,4");//formula for minor for 1 chord
       
        switch(t)
        {
            case 1:
            {
                c.add(ch);
                if(ch+7>12)
                {
                    c.add(ch+7-12);
                }
                else
                {
                    c.add(ch+7);
                }
                if(ch+4>12)
                {
                    c.add(ch+4-12);
                }
                else
                {
                    c.add(ch+4);
                }
                break;
            }
            //if chord major then ^
            case 2:
            {
                c.add(ch);
                if(ch+7>12)
                {
                    c.add(ch+7-12);
                }
                else
                {
                    c.add(ch+7);
                }
                if(ch+3>12)
                {
                    c.add(ch+3-12);
                }
                else
                {
                    c.add(ch+3);
                }
                break;
            }//if chord minor notes ^
            default:
        }
        System.out.println(" Notes of chord are : "+c);
        System.out.println();
    }
       
    void openPos()
    {
        int i,j;
        
        for(i=0;i<6;i++)
        {
            if(fretb[i][0]==c.get(0)||fretb[i][0]==c.get(1)||fretb[i][0]==c.get(2))
            {
                open[i]=true;
            }
        }
    }
    
    void shape(int lowestroot, int an)
    {
        int open[]={0,0,0,0,0,0};
        if(lowestroot<2||an<3)
        {
            return;
        }
        int i,j,k;
        for(j=0;j<6;j++)
            {
                for(k=0;k<4;k++)
                {
                    System.out.print(window[j][k]+"\t|");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
    }
    
    void notefind(int col)
    {      
        for(int x=0;x<6;x++)
        {
           strings[x]=0;
           for(int y=0;y<4;y++)
           {
               window[x][y]=0;//initialize window to 0
           }
        }
        int i,j;
        int k=0;
        /*for(i=5;i>2;i--)//find if root note exist in low 3 strings
        {
            for(j=col;j<col+4;j++)
            {
                if(fretb[i][j]==c.get(0))
                {
                    window[i][j-col]=c.get(0);
                    break;
                }
            }
        }*/
        int lowestroot=0;
        int allnotes=0;
        for(i=5;i>=0;i--)
        {
            for(j=col;j<col+4;j++)
            {
                if(fretb[i][j]==c.get(0))
                {
                    window[i][j-col]=c.get(0);
                    strings[i]=c.get(0);
                    if(i>lowestroot)
                    {
                        lowestroot=i;
                    }
                    allnotes++;                    
                }
                if(fretb[i][j]==c.get(1))
                {
                    window[i][j-col]=c.get(1);
                    strings[i]=c.get(1);
                    allnotes++;
                }
                if(fretb[i][j]==c.get(2))
                {
                    window[i][j-col]=c.get(2);
                    strings[i]=c.get(2);
                    allnotes++; 
                }
            }
        }
        shape(lowestroot, allnotes);
    }
    
    void display()
    {
        openPos();
        int i,j,k;
        for(i=0;i<12;i+=2)//get reference for window to actual fretboard
        {
            notefind(i);            
        }
    }
    
    void main()
    {
       Scanner sc=new Scanner(System.in);
       System.out.println("Enter the tuning");//get tuning input
       for(int i=5;i>=0;i--)
       {
           fretb[i][0]=sc.nextInt();//initialize open strings to tuning
       }
       System.out.println("Which chord do you want to find? What tone?");
       int ch=sc.nextInt();
       int t=sc.nextInt();//get chord and maj/min
       noteMap();
       chord(ch,t);
       display();
    }
}