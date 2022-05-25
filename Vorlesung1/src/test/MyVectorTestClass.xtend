package test

class MyVectorTestClass {
	def static void main(String[] args){
		var a = new MyVector(newArrayList(1, 2, 3))
		var b = new MyVector(newArrayList(6, 5, 4))
		var c = 10
		println(a * c)
	}
}