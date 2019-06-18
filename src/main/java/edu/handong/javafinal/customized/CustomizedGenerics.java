package edu.handong.javafinal.customized;

import java.util.ArrayList;


public class CustomizedGenerics <T>{
	private int size;
	private int margin;
	private Object[] data;
	
	public CustomizedGenerics (int margin) {
		size = 0;
		this.margin = margin;
		data = new Object[this.margin];
	}

	public CustomizedGenerics () {
		size = 0;
		this.margin = 100;
		data = new Object[margin];
	}
	
	public void add (T input){
		if (size == margin) {
			margin *= 2;
			Object[] temp = new Object[margin];
			int i = 0;
			for (Object value : data) {
				temp[i++] = value;
			}
			
			data = temp;
		}
		data[size++] = input;
	}
	
	public int size () {
		return size;
	}
	
	public T get (int index) {
		return (T)data[index];
	}
	
	public T[] get () {
		return (T[])data;
	}

}
