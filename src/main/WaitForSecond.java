package main;

public class WaitForSecond implements IDelay {
	
	public WaitForSecond(float second, Thread t) {
		waitFor(second, t);
	}

	@Override
	public void waitFor(float seconds, Thread t) {
		long waitTime = (long) (seconds * 1000);
		try {
			t.sleep(waitTime);
		} catch (InterruptedException e) {
			System.out.println("There is a proble mabout Thread!");
		}
		t.start();
	}
	
	public void execute() {
		
	}
	
}
