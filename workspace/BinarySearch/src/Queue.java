import javax.xml.soap.Node;


public class Queue {


	public static void main(String[] args) {
		

	}
	private int size;
	private int queue[];
	int front =0 ;
	int rear = -1;
	int curentsize=0;
	public void QueueImp(int size){
		queue = new int [size];

		front =0;

	}

	public void enqueue(int elem){
		if (curentsize == size){
			System.out.println("queue ful");
		}

		rear++;
		queue[rear] =  elem;
		curentsize++;

	}

	public void dequeue(int elem){
		if (curentsize == 0){
			System.out.println("queue empty");
		}

		queue[front] =  elem;
		curentsize++;

	}
}
