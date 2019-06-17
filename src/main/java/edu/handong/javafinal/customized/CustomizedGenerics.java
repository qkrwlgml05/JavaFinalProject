package edu.handong.javafinal.customized;

public class CustomizedGenerics <T> {
	private int size = 0;
	private int margin = 100;
	private Object[] data = new Object[margin];
	
	public void add (T input) {
		if (size == margin) {
			margin *= 2;
			Object[] temp = new Object[margin];
			int i = 0;
			for (Object value : data) {
				temp[i++] = value;
			}
			
			data = temp;
		}
		data[size] = input;
		size++;
	}
	
	public int size () {
		return size;
	}
	
	public T get (int index) {
		return (T)data[index];
	}

}
