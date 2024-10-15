package com.patterns.creational.factoryPattern;

public class PC extends Computer{

		private String ram;

		private String hDD;

		private String core;

		public PC(String ram, String hDD, String core){
				this.ram = ram;
				this.hDD = hDD;
				this.core = core;
		}

		@Override String getRam() {
				return ram;
		}

		@Override String getHDD() {
				return hDD;
		}

		@Override String getCore() {
				return core;
		}
}
