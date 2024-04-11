import java.util.ArrayList;
import java.util.Comparator;

public class HeapUsingIterativeBinaryTree<P, V> implements IHeap<P, V> {

	private int _count;
	private TreeNode<P, V> _root;
	private Comparator<P> _priorityComparator;
	
	public HeapUsingIterativeBinaryTree(Comparator<P> priorityComparator) {
		_count = 0;
		_priorityComparator = priorityComparator;
	}
	
	@Override
	public void Insert(P priority, V value) {
		TreeNode<P, V> newNode = new TreeNode<P, V>(priority, value);
		
		if (count() == 0) { //Inserto en la raiz
			_count++;
			_root = newNode;
		} else {
			
			_count++;
			
			byte[] movimientos = convertToBinary(_count);
			
			//Reccorer de acuerdo a los movimientos
			int index = 1;
			TreeNode<P, V> actual = _root;
			
			while (index < movimientos.length) {
				
				if (index == movimientos.length - 1) {
					if (movimientos[index] == 0) { // Inserto en el hijo izquierdo
						actual.set_left(newNode);
					} else { //Inserto en el hijo derecho
						actual.set_right(newNode);
					}
					newNode.set_parent(actual);
				} else {
					if (movimientos[index] == 0) { // Inserto en el hijo izquierdo
						actual = actual.get_left();
					} else { //Inserto en el hijo derecho
						actual = actual.get_right();
					}
				}
				
				index++;
			}
			
			
			//Hacer Swap
			actual = newNode;
			while((actual != null) && (actual.get_parent() != null)) {
				Swap(actual);
				actual = actual.get_parent();
			}
			
		}
		
	}
	
	private byte[] convertToBinary(int value) {
		
		ArrayList<Byte> listBytes = new ArrayList<Byte>();
		
		while (value > 0) {
			listBytes.add( (byte)(value % 2) );
			value = value / 2;
		}
		//listBytes.add((byte) (value % 2) );
		
		byte[] binaryBytes = new byte[listBytes.size()]; 
		
		for (int i = listBytes.size() - 1; i >= 0; i-- ) {
			binaryBytes[listBytes.size() - 1 - i] = listBytes.get(i);  
		}
		
		return binaryBytes;
	}
	
	private void Swap(TreeNode<P, V> actualNode) {
		if (actualNode != null) {
			
			if (actualNode.get_parent() != null) { //is not the root
				
				int result = _priorityComparator.compare(actualNode.get_priority(), actualNode.get_parent().get_priority());
				
				if (result > 0) { //if child is greater than parent
					P tempP = actualNode.get_priority();
					V tempV = actualNode.get_value();
					
					actualNode.set_priority(actualNode.get_parent().get_priority());
					actualNode.set_value(actualNode.get_parent().get_value());
					
					actualNode.get_parent().set_priority(tempP);
					actualNode.get_parent().set_value(tempV);
				}
				
			}
			
		}
	}
	

	@Override
	public V get() {
		if (isEmpty())
			return null;
		else 
			return _root.get_value();
	}

	@Override
	public V remove() {
		
		if (isEmpty()) {
			return null;
		}
		
		if (count() == 1) {
			_count--;
			TreeNode<P, V> temporal = _root;
			_root = null;
			return temporal.get_value();
		}
		
		//if has more than 1 element
		
		// Obtener el binario de la cantidad de nodos
		byte[] movimientos = convertToBinary(_count);
		
		// Encontrar el nodo a eliminar
		//Reccorer de acuerdo a los movimientos
		int index = 1;
		TreeNode<P, V> actual = _root;
		TreeNode<P, V> nodoToBeDeleted = null;
		
		while (index < movimientos.length) {
			
			if (index == movimientos.length - 1) {
				if (movimientos[index] == 0) { // Inserto en el hijo izquierdo
					nodoToBeDeleted = actual.get_left();
				} else { //Inserto en el hijo derecho
					nodoToBeDeleted = actual.get_right();
				}
				
			} else {
				if (movimientos[index] == 0) { // Inserto en el hijo izquierdo
					actual = actual.get_left();
				} else { //Inserto en el hijo derecho
					actual = actual.get_right();
				}
			}
			
			index++;
		}
		
		// Hacer Swap de la hoja con la raiz
		P tempPriority = nodoToBeDeleted.get_priority();
		V tempValue = nodoToBeDeleted.get_value();
		
		nodoToBeDeleted.set_priority(_root.get_priority());
		nodoToBeDeleted.set_value(_root.get_value());
		
		_root.set_priority(tempPriority);
		_root.set_value(tempValue);
		
		
		// Eliminar el nodo hoja
		tempPriority = nodoToBeDeleted.get_priority();
		tempValue = nodoToBeDeleted.get_value();
		
		TreeNode<P, V> parent = nodoToBeDeleted.get_parent(); 
		if (parent.get_left() == nodoToBeDeleted)
			parent.set_left(null);
		else 
			parent.set_right(null);
		
		
		// buscar el lugar de insercion
		actual = _root;
		
		while (actual != null) {
			
			boolean actualHasLeftChild = actual.get_left() != null;
			boolean actualHasRightChild = actual.get_right() != null;
			
			if (actualHasLeftChild && actualHasRightChild) { //Tiene a los 2 hijos
				
				//Si tiene a los dos hijos verifico quien es el mayor
				int result = _priorityComparator.compare(actual.get_left().get_priority(), actual.get_right().get_priority());
				
				if (result == 0) { //Son iguales
					
					result = _priorityComparator.compare(actual.get_priority(), actual.get_left().get_priority());
					if (result < 0) {
						P tempPriority2 = actual.get_priority();
						V tempValue2 = actual.get_value();
						
						actual.set_priority(actual.get_left().get_priority());
						actual.set_value(actual.get_left().get_value());
						
						actual.get_left().set_priority(tempPriority2);
						actual.get_left().set_value(tempValue2);
						
						actual = actual.get_left();
					} else {
						break;
					}
					
				} else if (result > 0){ //Hijo izquierdo mayor
					result = _priorityComparator.compare(actual.get_priority(), actual.get_left().get_priority());
					if (result < 0) {
						P tempPriority2 = actual.get_priority();
						V tempValue2 = actual.get_value();
						
						actual.set_priority(actual.get_left().get_priority());
						actual.set_value(actual.get_left().get_value());
						
						actual.get_left().set_priority(tempPriority2);
						actual.get_left().set_value(tempValue2);
						actual = actual.get_left();
					} else {
						break;
					}
				} else {
					result = _priorityComparator.compare(actual.get_priority(), actual.get_right().get_priority());
					if (result < 0) {
						P tempPriority2 = actual.get_priority();
						V tempValue2 = actual.get_value();
						
						actual.set_priority(actual.get_right().get_priority());
						actual.set_value(actual.get_right().get_value());
						
						actual.get_right().set_priority(tempPriority2);
						actual.get_right().set_value(tempValue2);
						actual = actual.get_right();
					} else {
						break;
					}
				}
				
			} else if (!actualHasLeftChild && !actualHasRightChild){ //No tiene hijos
				break;
			} else if (actualHasLeftChild){ //Solo tiene izquierdo
				int result = _priorityComparator.compare(actual.get_priority(), actual.get_left().get_priority());
				if (result < 0) {
					P tempPriority2 = actual.get_priority();
					V tempValue2 = actual.get_value();
					
					actual.set_priority(actual.get_left().get_priority());
					actual.set_value(actual.get_left().get_value());
					
					actual.get_left().set_priority(tempPriority2);
					actual.get_left().set_value(tempValue2);
				} else {
					break;
				}
			} else { //Solo tiene derecho
				int result = _priorityComparator.compare(actual.get_priority(), actual.get_right().get_priority());
				if (result < 0) {
					P tempPriority2 = actual.get_priority();
					V tempValue2 = actual.get_value();
					
					actual.set_priority(actual.get_right().get_priority());
					actual.set_value(actual.get_right().get_value());
					
					actual.get_right().set_priority(tempPriority2);
					actual.get_right().set_value(tempValue2);
				} else {
					break;
				}
			}
		}
		
		// reducir la cantidad
		_count--;
		
		return tempValue;
	}

	@Override
	public int count() {
		return _count;
	}

	@Override
	public boolean isEmpty() {
		return _count == 0;
	}

}