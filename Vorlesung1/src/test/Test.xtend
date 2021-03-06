package test

import java.util.List

class MyVector {

	List<Integer> components;
	new(List<Integer> components){
		this.components = components;
	}
	
	def operator_doubleArrow(int i){
		if (i < components.size)
			return components.get(i)
		else
			throw new IllegalArgumentException("Out of bounds");
	}
		
	def operator_plus(MyVector other){
		if (this.components.size == other.components.size){
			var List<Integer> newComponents = newArrayList();
			for (var i = 0; i < this.components.size; i++)
			newComponents.add((this => i) + (other => i));
			return new MyVector(newComponents);
		} else
			throw new IllegalArgumentException("Dimensions are incompatible!")
	}
	
	def operator_multiply(MyVector other){
		if (this.components.size == other.components.size){
			var sum = 0
			for (var i = 0; i < this.components.size; i++)
				sum += this.components.get(i) *	 other.components.get(i)
			return sum;
		} else
			throw new IllegalArgumentException("Dimensions are incompatible!")
	}
	
	def operator_multiply(int other){
		var List<Integer> newComponents = newArrayList();
		for (var i = 0; i < this.components.size; i++)
			newComponents.add(other*this.components.get(i));
		return newComponents;
	}
			
	override toString(){"<" + components.toString + ">"}

}