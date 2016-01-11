
public  class  Threadingimplementation extends Thread {
	
	
	
	public void run(){
	
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println("super class");
	
	}
	
	
	
	
	}
//		
//		MultiThreading abc = new MultiThreading();
//		
//		
//		
//		Thread thread1 = new Thread(abc);
//		Thread thread2 = new Thread(abc);
//		thread1.start();
//		thread2.start();
//	}


