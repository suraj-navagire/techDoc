package com.patterns.creational.factoryPattern;

public class Server extends Computer{

		private String ram;

		private String hDD;

		private String core;

		public Server(String ram, String hDD, String core){
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
