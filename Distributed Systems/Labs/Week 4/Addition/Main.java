public class Main {   // This class is used to set things in motion.

   public static void main(String argv[]) {
   
	  MyData data = new MyData();
      
	  new Thread(new Producer(data)).start();
      new Thread(new Consumer(data)).start();
  }
}
