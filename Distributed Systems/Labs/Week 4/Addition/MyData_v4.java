class MyData {

	private int data;   
	private boolean ready;
	private boolean taken;

	public MyData() {
		ready = false;
		taken = true;
	}

	public void store(int data) {
		while (!taken); 
		synchronized (this) { 
			this.data = data;
			taken = false;
			ready = true;
		}
	}

	public int load() {
		while (!ready);
		synchronized (this) {
			ready = false;
			taken = true;
			return this.data;
		}
	}


}
