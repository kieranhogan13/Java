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

		this.data = data;
		taken = false;
		ready = true;
	}

	public int load() {
		int data;

		while (!ready);
		data = this.data;      // save the value because after  
							 // Taken turns "true" it may 
							 // change at any time.
		ready = false;
		taken = true;
		return data;
	}
}

