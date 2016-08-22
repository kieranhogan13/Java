class MyData {

	private int data;
	private boolean ready;
	private boolean taken;

	public MyData() {
		ready = false;
		taken = true;
	}

	public synchronized void store(int data) {
		while (!taken);

		this.data = data;
		taken = false;
		ready = true;
	}

	public synchronized int load() {
		while (!ready);

		ready = false;
		taken = true;
		
		return this.data;
	}
}
