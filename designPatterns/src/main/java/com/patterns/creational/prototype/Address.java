package com.patterns.creational.prototype;

public class Address implements Cloneable{
		private String address;

		public Address(String address) {
				this.address = address;
		}

		public String getAddress() {
				return address;
		}

		public void setAddress(String address) {
				this.address = address;
		}

		@Override public Address clone() {
				try {
						Address clone = (Address) super.clone();
						// TODO: copy mutable state here, so the clone can't change the internals of the original
						return clone;
				} catch (CloneNotSupportedException e) {
						throw new AssertionError();
				}
		}
}
