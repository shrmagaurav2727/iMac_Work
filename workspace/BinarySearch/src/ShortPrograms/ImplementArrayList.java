package ShortPrograms;

public class ImplementArrayList {

	
	public static void main(String[] args) {
		ImplementArrayList arr = new ImplementArrayList();
		arr.MyArrayList(10);
		
	}
	int size ;
	Object[] myList ;
	int index =-1;
	public void MyArrayList(int size){
		myList = new Object[size];
	}
	
	public void add( Object elem){
		if(index> myList.length){
			increaseSize();
		}
		
		myList[index++]= elem;
	}
	
	public void remove(int index){
		
	if (index > myList.length){
		System.out.println("No element at index");
		return;
	}
	for (int i = index; i < myList.length-1; i++) {
		myList[i] = myList[i+1];
		myList[i+1]= null;
	} 
	}
	
	public int Size(){
		return size;
	}
	public Object get(int Index){
		return myList[this.index];
	}
	
	public void increaseSize(){
		Object[] newObj = new Object[size*2];
		
		for (int i = 0; i < myList.length; i++) {
			newObj[i] = myList[i];
		}
		myList = newObj;
		
	}
	
	
}
