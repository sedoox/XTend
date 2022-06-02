package uml

import java.util.List

class Customer {
	
	public String firstName
	public String lastName
	public List<Order> orders
	
	def getSummary() {
		'''
		*****************************************************
		******* C U S T O M E R ******** S U M M A R Y ******
		First name: «firstName»
		Last name: «lastName»
		
		Total order value: «orders.map[amount].reduce[p1, p2| p1 + p2]» Avarage Order Value: «(orders.map[amount].reduce[p1, p2| p1 + p2]/orders.length) as int»
		First order: «orders.minBy[date].date» Last order: «orders.maxBy[date].date»
		HappyCustomer Points (1 for every full 5.0): «(orders.map[amount].reduce[p1, p2| p1 + p2]/5) as int»
		
		-----------------------------------------------------
		Orders in chronological order:
		
		«FOR i : (0..orders.length-1)»«i+1». on «orders.sortBy[date].get(i).date»  value: «orders.get(i).amount» via «IF orders.get(i) instanceof PhoneOrder» Phone «ELSEIF orders.get(i) instanceof PostalOrder» Post «ELSE» Internet «ENDIF»
		«ENDFOR»
		*****************************************************
		'''
	}
	
}