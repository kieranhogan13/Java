public class Producer implements Runnable { // The producer thread class

   MyData data;
   
   public Producer(MyData data) {
      this.data = data;
   }
       
   public void run() {
      int i;

      for (i = 0; ; i++) {
         data.store(i);

		 System.out.println ("Producer: " + i);
         
		 try {
            // doze off for a random time (0 to 0.5 sec)
            Thread.sleep ((int) (Math.random()*500));
         } catch (InterruptedException e) { }
      }
   }
}
