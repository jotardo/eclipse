package single_linked_class;

public class Node<E> {
	private E element;
	private Node<E> next;
	public Node(E element, Node<E> next) {
		this.element = element;
		this.next = next;
	}
	
	public E getElement() {
		return element;
	}
	
	public Node<E> getNext() {
		return next;
	}
	
	public void setNext(Node<E> next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return "[" + this.element.toString() + ((this.next != null) ? this.next.toString() : "") + "]";
	}
}
