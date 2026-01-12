package com.systemdesign.lld.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class FeeCalculationFactory {
		private Map<VehicleType, IFeeCalculationStrategy> strategyMap = new HashMap<>();

		public FeeCalculationFactory() {
				strategyMap.put(VehicleType.CAR, new CarFeeCalculationStrategy());
				strategyMap.put(VehicleType.BIKE, new BikeFeeCalculationStrategy());
				strategyMap.put(VehicleType.TRUCK, new TruckFessCalculationStrategy());
		}

		public IFeeCalculationStrategy getInstance(VehicleType vehicleType){
				return strategyMap.get(vehicleType);
		}
}
