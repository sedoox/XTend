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
			], new PostalCode => [
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
			orders = new ArrayList(Arrays.asList(new PostalCode => [
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

}