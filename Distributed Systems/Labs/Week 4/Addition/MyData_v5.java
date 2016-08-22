class MyData {

   private int data; 
   private boolean ready;  
         
   public MyData() {
      ready = false;
   }
   
   public synchronized void store(int data) {
      while (ready)
         try {
            wait();
         } catch (InterruptedException e) { }
   
	  this.data = data;
      ready = true;
      notify();
   }

   public synchronized int load() {
      while (!ready)
         try {
            wait();  
         } catch (InterruptedException e) { }
      
	  ready = false;
      notify();
      return this.data;
   }
}

