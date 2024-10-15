package com.patterns.creational.prototype.copyConstructor;

public class Address implements Prototype{
		private String address;

		public Address(String address) {
				this.address = address;
		}

		public Address(Address a){
				this.address = a.address;
		}
		public String getAddress() {
				return address;
		}

		public void setAddress(String address) {
				this.address = address;
		}

		@Override public Address copy() {
				return new Address(this);
		}
}
