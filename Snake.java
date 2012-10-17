import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Snake extends Applet
   implements KeyListener,Runnable 
{
	int food[]=new int[2];
	int scor;
   int width, height;
   int snak[][]=new int[50][2];
   int dir;
   int i,j;
  	boolean notrepeatedkeypress,colided;
  	int snaklength;
	Random rnd = new Random ();
	int speed;
	
	

   public void init() 
   	{ setBackground(Color.yellow);scor=0;
   	 
   		food[0]=100;
   		food[1]=100;speed=500;
      width = getSize().width;
      height = getSize().height;snaklength=5;
     	colided=false;
	dir=2;notrepeatedkeypress=true;
	snak[0][0]=300;snak[0][1]=300;//specify head position
    for(i=1;i<=snaklength;i++)//initialize head&trunk part positions of length=snakelength
    {		snak[i][0]=300;
     		snak[i][1]=300-(i-1)*20; 
    }
  
	addKeyListener( this );
   
   }

   public void keyPressed( KeyEvent e ) { }
   public void keyReleased( KeyEvent e ) { }
   
   public void keyTyped( KeyEvent e )
   {
      char c = e.getKeyChar();
      if(notrepeatedkeypress)
      {	
      		if(c=='a'&&dir!=1)
      			dir=3;
      		if(c=='w'&&dir!=2)
      			dir=0;
      		if(c=='d'&&dir!=3)
      			dir=1;
      		if(c=='s'&&dir!=0)
      			dir=2;
      }
      notrepeatedkeypress=false;
      
   }
   public void run()
   {
   		while(!colided)
   		{	repaint();
   			
   			if(dir==0)
   				snak[0][1]=snak[0][1]-20;
   			if(dir==3)
   				snak[0][0]=snak[0][0]-20;
   			if(dir==2)
   				snak[0][1]=snak[0][1]+20;
   			if(dir==1)
   				snak[0][0]=snak[0][0]+20;
   			for(j=snaklength+1;j>0;j--)
   			{
   				snak[j][0]=snak[j-1][0];snak[j][1]=snak[j-1][1];
   			}  
   			notrepeatedkeypress=true;
   		
   		
   			if(snak[0][0]==food[0]&&snak[0][1]==food[1]) 			
   			{
   				snaklength++;
     			food[0]=Math.abs(rnd.nextInt() % 40)*20;
   				food[1]=Math.abs(rnd.nextInt() % 30)*20;
   				scor+=10;
   			}	
   				
   			
   		
   		
   			
   			colided=false;
   			for(j=4;j<=snaklength;j++)
   			{
   				if(snak[0][1]==snak[j][1]&&snak[0][0]==snak[j][0]||snak[0][0]>width||snak[0][0]<0||snak[0][1]>height||snak[0][1]<0)
   				{	colided=true;
   					break;
   				}
   			}
   			
   			
   		
   			try
   			{
   			
   				Thread.sleep(1000-speed);
   			}
   			catch(InterruptedException ex)
   			{
   				
   			}
   			
   		}
   }
   public void start()
   {
   	 Thread snakemovement=new Thread(this);
   	 snakemovement.start();
   }
   public void destroy()
   {
		
   }
   
   public void paint( Graphics g ) 
   {     g.drawString("score:"+scor,750,10);
     	for(i=1;i<=snaklength;++i)
     	{  		g.fillOval(snak[i][0],snak[i][1],20,20);
     		g.drawOval(food[0],food[1],20,20);g.drawRect(food[0],food[1],20,20);
     		
   		}
   			if(colided)
     		{	
     			
     			g.drawString("GAMEOVER",350,100);
     			
     			g.drawString("TRY AGAIN",350,200);
     			
     	
     		}
   }
}
