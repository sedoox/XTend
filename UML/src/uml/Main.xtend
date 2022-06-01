package uml

import java.time.LocalDate
import java.util.List
import java.util.ArrayList
import java.util.Arrays

class Main {
	def static void main(String[] args){
		val person1 = new Customer => [
			firstName = 'Jesse'
			lastName = 'James'
			orders = new ArrayList(Arrays.asList(new PhoneOrder => [
				date = LocalDate.parse("2021-04-23")
				amount = 400.25
				number = "06445465"
			], new PostalOrder => [
				date = LocalDate.parse("2021-04-23")
				amount = 400.25
				zipCode = "55887"
				]))
			]
			
		val person2 = new Customer => [
			firstName = 'Ford'
			lastName = 'Henry'
			orders = new ArrayList(Arrays.asList(new OnlineOrder => [
				date = LocalDate.parse("1998-03-13")
				amount = 63.52
				ipAddress = "205.204.22.22"
				]))	
			]
			
		val person3 = new Customer => [
			firstName = 'William'
			lastName = 'Wallace'
			orders = new ArrayList(Arrays.asList(new PostalOrder => [
				date = LocalDate.parse("2017-02-04")
				amount = 987.05
				zipCode = "55822"
			], new OnlineOrder => [
				date = LocalDate.parse("2020-02-05")
				amount = 632.78
				ipAddress = "205.204.22.47"
			], new 	PhoneOrder => [
				date = LocalDate.parse("2020-12-07")
				amount = 125.25
				number = "65411645"
				]))
			]
	}
	
	def dispatch static double getDeliveryCosts(Order order) {
		if (order.amount > 100) return 0
		switch order {
			PhoneOrder: if (order.amount <= 50) return 5 else return 4
			PostalOrder: if (order.amount <= 50) return 5.5 else return 3
			OnlineOrder: if (order.amount <= 50) return 4 else return 0
			default: return -1
		}
	}
	
	def dispatch static double getDeliveryCosts(PhoneOrder order) {
		if (order.amount <= 50)
			return 5
		else if (order.amount <= 100)
			return 4
		else
			return 0
	}
	
	def dispatch static double getDeliveryCosts(PostalOrder order) {
		if (order.amount <= 50)
			return 5.5
		else if (order.amount <= 100)
			return 3
		else
			return 0
	}
	
	def dispatch static double getDeliveryCosts(OnlineOrder order) {
		if (order.amount <= 50)
			return 4
		else
			return 0
	}

}