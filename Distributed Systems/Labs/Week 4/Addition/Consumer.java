public class Consumer implements Runnable { // The consumer thread

   MyData data;
   
   public Consumer(MyData data) {
   
	  this.data = data;
   
   }
          
   public void run() {
      for (;;) {

         System.out.println ("Consumer: " + data.load());
         
		 try {
         
			// doze off for a random time (0 to 0.5 sec)
            Thread.sleep ((int) (Math.random() * 500));
         
		 } catch (InterruptedException e) { }
      }
   }
}
