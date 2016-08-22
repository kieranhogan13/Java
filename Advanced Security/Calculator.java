//declarations
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Color;
import java.awt.Component;
 
class Calculator implements ActionListener
{
	//Initialise variables
    JFrame f;
    JTextField t;
    JTextArea t2;
    JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bdiv,bmul,bsub,badd,bdec,beq,bdel,bclr, brand0, brand1, brand2, bisprime, bnextprime, bsetofprime;
    static double a = 0, b = 0, result = 0, value;
    static long isprimenum = 0, m = 0, q = 0, r = 0, s = 0, hi = 0, lo = 0, w = 0, pm_seed = 0, i1_seed = 0, mers = 0;
    static int operator=0, c0 = 0, c1 = 0, c2 = 0, z = 10;
    static int[] i_seed;
    private int mt_index;
    private int[] mt_buffer = new int[624];
    static String pm_seed_string, value_string, new_value_string;
    static boolean isprime;
	//Creates calculator GUI
    Calculator()
    {
		//Main frame and visual buttons 
        f=new JFrame("Calculator and PRNG");
        t=new JTextField("");
        t2=new JTextArea("");
        b1=new JButton("1");
        b2=new JButton("2");
        b3=new JButton("3");
        b4=new JButton("4");
        b5=new JButton("5");
        b6=new JButton("6");
        b7=new JButton("7");
        b8=new JButton("8");
        b9=new JButton("9");
        b0=new JButton("0");
        bdiv=new JButton("/");
        bmul=new JButton("*");
        bsub=new JButton("-");
        badd=new JButton("+");
        bdec=new JButton(".");
        beq=new JButton("=");
        bdel=new JButton("Delete");
        bclr=new JButton("C");
        brand0=new JButton("R");
        brand1=new JButton("R1");
        brand2=new JButton("R2");
        bisprime=new JButton("IP");
        bnextprime=new JButton("NP");
		bsetofprime=new JButton("SP");

		//Button positions
        bdel.setBounds(60,380,100,40);
        bclr.setBounds(300,80,50,30);

        t.setBounds(20,35,610,30);
        b7.setBounds(20,80,50,30);
        b8.setBounds(90,80,50,30);
        b9.setBounds(160,80,50,30);

        bdiv.setBounds(230,80,50,30);
        b4.setBounds(20,130,50,30);
        b5.setBounds(90,130,50,30);
        b6.setBounds(160,130,50,30);

        bmul.setBounds(230,130,50,30);
        b1.setBounds(20,180,50,30);
        b2.setBounds(90,180,50,30);
        b3.setBounds(160,180,50,30);
        bsub.setBounds(230,180,50,30);

        bdec.setBounds(20,230,50,30);
        b0.setBounds(90,230,50,30);
        beq.setBounds(160,230,50,30);
        badd.setBounds(230,230,50,30);

        brand1.setBounds(370,80,50,30);
        brand2.setBounds(440,80,50,30);
        bisprime.setBounds(510,80,50,30);
		bnextprime.setBounds(580,80,50,30);

        brand0.setBounds(160,280,50,30);
        t2.setBounds(300,130,330,180);
		bsetofprime.setBounds(230,280,50,30);

		//Adds buttons to interface
        f.add(t);
        f.add(t2);
        f.add(b7);
        f.add(b8);
        f.add(b9);
        f.add(bdiv);
        f.add(b4);
        f.add(b5);
        f.add(b6);
        f.add(bmul);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(bsub);
        f.add(bdec);
        f.add(b0);
        f.add(beq);
        f.add(badd);
        f.add(bdel);
        f.add(bclr);
        f.add(brand0);
        f.add(brand1);
        f.add(brand2);
        f.add(bisprime);
        f.add(bnextprime);
        f.add(bsetofprime);

		//Creates square frame of size 310*310
        f.setLayout(null);
        f.setVisible(true);
        f.getContentPane().setBackground(Color.YELLOW);
        f.setSize(660,360);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        
		//Add listener to each button
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b0.addActionListener(this);
        badd.addActionListener(this);
        bdiv.addActionListener(this);
        bmul.addActionListener(this);
        bsub.addActionListener(this);
        bdec.addActionListener(this);
        beq.addActionListener(this);
        bdel.addActionListener(this);
        bclr.addActionListener(this);
        brand0.addActionListener(this);
        brand1.addActionListener(this);
        brand2.addActionListener(this);
        bisprime.addActionListener(this);
        bnextprime.addActionListener(this);
        bsetofprime.addActionListener(this);
    }
	//Button actions
    public void actionPerformed(ActionEvent e)
    {
		//Setting up what each button does/enters
        if(e.getSource()==b1)
            t.setText(t.getText().concat("1"));
        
        if(e.getSource()==b2)
            t.setText(t.getText().concat("2"));
        
        if(e.getSource()==b3)
            t.setText(t.getText().concat("3"));
        
        if(e.getSource()==b4)
            t.setText(t.getText().concat("4"));
        
        if(e.getSource()==b5)
            t.setText(t.getText().concat("5"));
        
        if(e.getSource()==b6)
            t.setText(t.getText().concat("6"));
        
        if(e.getSource()==b7)
            t.setText(t.getText().concat("7"));
        
        if(e.getSource()==b8)
            t.setText(t.getText().concat("8"));
        
        if(e.getSource()==b9)
            t.setText(t.getText().concat("9"));
        
        if(e.getSource()==b0)
            t.setText(t.getText().concat("0"));
        
        if(e.getSource()==bdec)
            t.setText(t.getText().concat("."));
        
		//Addition operation
        if(e.getSource()==badd)
        {
            a=Double.parseDouble(t.getText());
            operator=1;
            t.setText("");
        } 
        //Subtraction operation
        if(e.getSource()==bsub)
        {
            a=Double.parseDouble(t.getText());
            operator=2;
            t.setText("");
        }
        //Multiply operation
        if(e.getSource()==bmul)
        {
            a=Double.parseDouble(t.getText());
            operator=3;
            t.setText("");
        }
        //Divide operation
        if(e.getSource()==bdiv)
        {
            a=Double.parseDouble(t.getText());
            operator=4;
            t.setText("");
        }
        //Equal operation
        if(e.getSource()==beq)
        {
            b=Double.parseDouble(t.getText());
			//Result generator for operands
            switch(operator)
            {
                case 1: result=a+b;
                    break;
        
                case 2: result=a-b;
                    break;
        
                case 3: result=a*b;
                    break;
        
                case 4: result=a/b;
                    break;
        
                default: result=0;
            }
        
            t.setText(""+result);
        }
        //Java Random Number
        if(e.getSource()==brand0)
        {
        	//Built in Random java class
            Random rndNumbers = new Random();
            long rndNumber = 0;
            rndNumber = rndNumbers.nextLong();
            //if minus, makes positive
            if (rndNumber < 0)
	        	rndNumber = -rndNumber;
            t.setText("" + rndNumber);
            t2.setText("Random Number generated using built in Java Random");
        }  

        //Random Algorithm 1
      	if(e.getSource()==brand1) //Park Miller/Lehmer
        {
            while (pm_seed < 1000000000)
            {
            	//Uses these numbers as guide for limits
            	m = 2147483647L; //2^32
            	s = 48271L;
            	q = 44488L;
            	r = 3399L;
                
                //Uses hashed nano time as seed
                pm_seed = 0;
                pm_seed = System.nanoTime();
                pm_seed_string = ""+ pm_seed;
                pm_seed = pm_seed_string.hashCode();
              
              	//Finds highest and lowest numbers, and computes using: x(n+1) = g*x(n) mod n
    			hi = pm_seed / q;
    	        lo = pm_seed - q * hi;
    	        w = s * lo - r * hi;
    	        if (w > 0)
    	            pm_seed = w;
    	        else
    	            pm_seed = w + m;
                if (pm_seed < 0)
                    pm_seed = -pm_seed;
            }
	        t.setText("" + pm_seed);
			t2.setText("Random Number generated using Lehmer/Park Miller alg");
            pm_seed = 0;
        }  

		//Random Algorithm 2
      	if(e.getSource()==brand2) //Mersenne Twister
        {
        	while (mers < 1000000000)
            {
                t.setText("");
    			Random r = new Random();
    			//fills buffer with 624 random numbers
    	        for (int i = 0; i < 624; i++)
    	        {
    	            mt_buffer[i] = r.nextInt();
    	        }
    	        
    	        if (mt_index == 624)
    	        {
    	            mt_index = 0;
    	            int i = 0;
    	            int s;
                    
                    //Shifts masks and xors
    	            //Loops to shift bits xor if in the range 624-397
    	            for (; i < 624 - 397; i++) 
    	            {
    	                s = (mt_buffer[i] & 0x80000000) | (mt_buffer[i+1] & 0x7FFFFFFF);
    	                mt_buffer[i] = mt_buffer[i + 397] ^ (s >> 1) ^ ((s & 1) * 0x9908B0DF);
    	            }
    	            //Loops to shift bits xor if in i less than 623
    	            for (; i < 623; i++) 
    	            {
    	                s = (mt_buffer[i] & 0x80000000) | (mt_buffer[i+1] & 0x7FFFFFFF);
    	                mt_buffer[i] = mt_buffer[i - (624 - 397)] ^ (s >> 1) ^ ((s & 1) * 0x9908B0DF);
    	            }
    	            //Shift bits xor
    	            s = (mt_buffer[623] & 0x80000000) | (mt_buffer[0] & 0x7FFFFFFF);
    	            mt_buffer[623] = mt_buffer[396] ^ (s >> 1) ^ ((s & 1) * 0x9908B0DF);
    	        }
    	        //mt_buffer[mt_index++];
    	        mers = mt_buffer[mt_index++];
                if (mers < 0)
                    mers = -mers;
	        }
            t.setText("" + mers);
            t2.setText("Random Number generated using Mersenne Twister alg");
            mers = 0;
        }

        
      //   //Random Alorithm 3 (Generates num between 0 and 1)
      // 	if(e.getSource()==brand3) //WichmannHill
      //   {
      //       c0 = 30269;
      //       c1 = 30307;
      //       c2 = 30323;
      //       i_seed = new int[2];

      //       //Random rndNumbers = new Random();
      //       pm_seed = 0;
      //       pm_seed = System.nanoTime();
      //       pm_seed_string = ""+ pm_seed;
      //       pm_seed = pm_seed_string.hashCode();
      //       i1_seed = pm_seed;
            
      //       if (i1_seed==0) 
      //           i1_seed++;
      //       for (int j=0; j < i_seed.length; j++) 
      //       {
      //           if (i_seed[j]==0) 
      //               i_seed[j]++;
      //       }   
      //       if (i1_seed >= c0 || i_seed[0] >= c1 || i_seed[1] >= c2) 
      //       {
      //           i1_seed = i1_seed * 171 % c0;
      //           i_seed[0] = i_seed[0] * 172 % c1;
      //           i_seed[1] = i_seed[1] * 170 % c2;
      //           value =
      //             (double)i1_seed / c0 +
      //             (double)i_seed[0] / c1 +
      //             (double)i_seed[1] / c2;
      //           //value_string = "" + value;
      //           //new_value_string = value_string.substring(2);
      //           //pm_seed = Long.parseLong(new_value_string);
      //           //t.setText("" + pm_seed);
      //             t.setText("" + value);
				  // t2.setText("Random Number generated using WichmannHill");

      //       }
      //   }  
      	
        //Is prime
      	if(e.getSource()==bisprime)
        {
        	try
        	{
	        	if (t.getText() != "" || t.getText() != null)
	        	{
	        		isprimenum=Long.parseLong(t.getText());
		            String numStr = t.getText();
		            if(numStr.contains(".") || isprimenum == 0)
		                t2.setText("Number is less than 1");
		            else if(isprimenum>=1)
		            {
		                if (isprimenum%2==0)
		                {
		                    t2.setText("Is not prime"); 
		                }
		                else
		                {
		                    if (isprimenum == 3 || isprimenum == 5 || isprimenum == 7)
		                        t2.setText("Is prime");
		                    for(long i=3;i*i<=isprimenum;i+=2)
		                    {
		                        if(isprimenum%i==0)
		                            t2.setText("Is not prime");
		                        else
		                            t2.setText("Is prime");
		                    }
		                }
		            }
		        }
		    }
		    catch(Exception e1)
            {
            	System.out.println("Error: " + e1);
                t2.setText("Please enter a whole number");
            }
        }  

        //Next prime
      	if(e.getSource()==bnextprime)
        {
        	try
        	{
        		isprimenum=Long.parseLong(t.getText());
	        	while(true)
	        	{
	            	isprime = true;
	            	isprimenum=isprimenum+1;
	            	int sqt = (int)Math.sqrt(isprimenum);
	            	for (int i=2; i<=sqt; i++)
	            	{
	            		if(isprimenum%i==0)
	            		{
	               			isprime = false;
	            			break;
	            		}
	            	}
	            	if (isprime)
	            	{
	            		break;
	            	}
	        	}
	        	t.setText(""+ isprimenum);
        	}
        	catch(Exception e2)
        	{
        		System.out.println("Error: " + e2);
                t2.setText("Please enter a number");
        	}
        }  

        //10 large primes
      	if(e.getSource()==bsetofprime)
        {   
            t2.setText("Set of 10 random prime numbers:");
            for (int i=0; i < z; i++)
            {
	            while (pm_seed < 1000000000)
                {
                    //Generates random number using Lehmer
                	//Same as park miller button function
                    m = 2147483647L; //32
                    s = 48271L;
                    q = 44488L;
                    r = 3399L;
    	            
    	            pm_seed = 0;
    	            pm_seed = System.nanoTime();
    	            pm_seed_string = ""+ pm_seed;
    	            pm_seed = pm_seed_string.hashCode();
    	          
    				hi = pm_seed / q;
    		        lo = pm_seed - q * hi;
    		        w = s * lo - r * hi;
    		        if (w > 0)
    		            pm_seed = w;
    		        else
    		            pm_seed = w + m;
                    if (pm_seed < 0)
                        pm_seed = -pm_seed;
                }
	            //Finds the next prime number after the random number, ensuring its prime
	            //Same method of next prime number button function
				while(true)
	        	{
	            	isprime = true;
	            	pm_seed=pm_seed+1;
	            	int sqt = (int)Math.sqrt(pm_seed);
	            	for (int j=2; j<=sqt; j++)
	            	{
	            		if(pm_seed%j==0)
	            		{
	               			isprime = false;
	            			break;
	            		}
	            	}
	            	if (isprime)
	            	{
	            		break;
	            	}
	        	}
	            t2.append("\n" + pm_seed);
                pm_seed = 0;
		    }
        }  

        //Clear button
        if(e.getSource()==bclr)
        {
            t.setText("");
        	t2.setText("");
        }
        //Delete button
        if(e.getSource()==bdel)
        {
            String s=t.getText();
            t.setText("");
            for(int i=0;i<s.length()-1;i++)
            t.setText(t.getText()+s.charAt(i));
        }        
    }
	//Main
    public static void main(String...s)
    {
		//Run calculator
        new Calculator();
    }
}