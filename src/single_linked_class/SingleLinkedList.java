package single_linked_class;

public class SingleLinkedList<E> {
	
	private Node<E> head;
	private Node<E> tail;
	private int size = 0;

	public SingleLinkedList() {}
	
	@Override
	public String toString() {
		if (this.head == null)
			return "[]";
		return this.head.toString();
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {
		if (this.isEmpty())
			return null;
		return this.head.getElement();
	}

	public E last() {
		if (this.isEmpty())
			return null;
		return this.tail.getElement();
	}
	
	public void addFirst(E e) {
		Node<E> newest = new Node<E>(e, this.head);
		this.head = newest;
		if (size == 0)
			this.tail = newest;
		size++;
	}
	
	public void addLast(E e) {
		Node<E> newest = new Node<E>(e, null);
		if (size == 0)
			this.head = newest;
		else
			this.tail.setNext(newest);
		this.tail = newest;
		size++;
	}
	
	public void removeFirst() {
		if (this.head == null) {
			return;
		}
		this.head = this.head.getNext();
		size--;
	}
	
	public static void main(String[] args) {
		SingleLinkedList<String> list = new SingleLinkedList<>();
		System.out.println(list.toString());
		//list.addLast("Hey");
		//list.addLast("it's me");
		//list.addLast("Goku");
		System.out.println(list.toString());
		list.removeFirst();
		System.out.println(list.toString());
	}

}
