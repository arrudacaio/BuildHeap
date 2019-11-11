import java.util.Arrays;
import java.util.Scanner;

public class Heap {

	private int[] heap;
	private int tail; // Tail vai controlar o índice do último elemento do array

	public Heap(int size) {
		this.heap = new int[size];
		this.tail = -1;
	}

	private boolean isEmpty() {
		return (this.tail == -1);
	}

	private int left(int i) {
		return (2 * i) + 1;
	}

	private int right(int i) {
		return (2 * i) + 2;
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private void heapfy(int i) {
		/*
		 * 1º Passo: Verificar condições de parada. -> Se o índice indicar um nó folha
		 * ou se for um índice inválido, não é necessário realizar heapfy.
		 */

		if (isLeaf(i) || isValidIndex(i)) {
			return;
		}

		int index_max = maxIndex(i, left(i), right(i));

		if (index_max != i) { // Se o maior valor não é a sub-raiz
			swap(i, index_max);
			heapfy(index_max);
		}

	}

	/*
	 * Para realizar o buildHeap -> Basta realizar o heapfy a partir do Pai da
	 * última folha até a raiz.
	 */
	private void buildHeap() {
		for (int i = parent(this.tail); i >= 0; i--) {
			heapfy(i);
		}
	}

	private int maxIndex(int i, int left, int right) {
		if (this.heap[i] > this.heap[left]) {
			if (isValidIndex(right)) {
				if (this.heap[i] < this.heap[right]) {
					return right;
				}
			}

			return i;
		}

		else {
			if (isValidIndex(right)) {
				if (this.heap[left] < this.heap[right]) {
					return right;
				}
			}
			return left;
		}
	}

	private boolean isLeaf(int i) {
		return i > parent(this.tail) && i <= this.tail;
	}

	private boolean isValidIndex(int i) {
		return i >= 0 && i <= this.tail;
	}

	private void swap(int i, int j) {
		int aux = this.heap[i];
		this.heap[i] = this.heap[j];
		this.heap[j] = aux;
	}
	// second flag



	private void printArray() {
		System.out.println(Arrays.toString(this.heap));
	}

	private boolean ehHeap() {
		boolean result = true;

		int i = 0;
		while (result && left(i) < this.heap.length && right(i) < this.heap.length) {
			if (this.heap[left(i)] > this.heap[parent(i)] || this.heap[right(i)] > this.heap[parent(i)]) {
				result = false;
			}
			i++;
		}

		return result;
	}

	// flag

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] array = getArray(sc);
		Heap heap = new Heap(array.length);
		// heap.buildHeap();
		// if(heap.ehHeap()) {
		// heap.printArray();
		// }
		

	}

	private static int[] getArray(Scanner scan) {
		String[] entrada = scan.nextLine().split(" ");
		int[] array = new int[entrada.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(entrada[i]);
		}
		return array;
	}

}
