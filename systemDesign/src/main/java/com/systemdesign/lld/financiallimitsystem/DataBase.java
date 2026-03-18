package com.systemdesign.lld.financiallimitsystem;

import com.systemdesign.lld.financiallimitsystem.model.CurrencyAmount;
import com.systemdesign.lld.financiallimitsystem.model.EntityLimitPackageMap;
import com.systemdesign.lld.financiallimitsystem.model.EntityType;
import com.systemdesign.lld.financiallimitsystem.model.ILimit;
import com.systemdesign.lld.financiallimitsystem.model.LimitPackage;
import com.systemdesign.lld.financiallimitsystem.model.LimitUtilization;
import com.systemdesign.lld.financiallimitsystem.model.PartyType;
import com.systemdesign.lld.financiallimitsystem.model.Payee;
import com.systemdesign.lld.financiallimitsystem.model.PeriodicType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {

		//All these lists indicates rows in table
		private static List<ILimit> transactionLimits = new ArrayList<>();

		private static List<ILimit> periodicLimits = new ArrayList<>();

		private static List<ILimit> durationalLimits = new ArrayList<>();

		private static List<LimitPackage> limitPackages = new ArrayList<>();

		private static List<EntityLimitPackageMap> entityLimitPackageMapList = new ArrayList<>();

		private static Map<String, String> userWithUserGroup = new HashMap<>();

		private static Map<String, EntityLimitPackageMap> entityMap = new HashMap<>();

		private static Map<String, LimitUtilization> utilizedLimitMap = new HashMap<>();

		private static Map<String, Payee>  payeeMap = new HashMap<>();

		public static void addPeriodicLimits(ILimit periodicLimit) {
				periodicLimits.add(periodicLimit);
		}

		public static void addTransactionLimits(ILimit transactionLimit) {
				transactionLimits.add(transactionLimit);
		}

		public static void addDurationalLimits(ILimit durationalLimit) {
				durationalLimits.add(durationalLimit);
		}

		public static void addLimitPackages(LimitPackage limitPackage) {
				limitPackages.add(limitPackage);
		}

		public static void addEntityLimitPackageMapList(EntityLimitPackageMap entityLimitPackageMap) {
				entityLimitPackageMapList.add(entityLimitPackageMap);

				entityMap.put(entityLimitPackageMap.getEntityType()+"#"+entityLimitPackageMap.getEntityValue(), entityLimitPackageMap);
		}

		public static EntityLimitPackageMap getEntityLimitPackage(EntityType type, String value){
				return entityMap.get(type+"#"+value);
		}

		public static void mapUserWithUserGroup(String user, String userGroup){
				userWithUserGroup.put(user, userGroup);
		}

		public static String getUserGroupForUser(String user){
				return userWithUserGroup.get(user);
		}

		public static LimitUtilization getUtilizedLimit(PartyType partyType, String partyValue, PeriodicType type, LocalDate date, String transactionId){
				if(date != null){
						return utilizedLimitMap.computeIfAbsent(transactionId+"#"+partyType+"#"+partyValue+"#"+type+"#"+date, v -> {
								LimitUtilization utilization = new LimitUtilization();
								utilization.setAmount(new CurrencyAmount(0, null));
								utilization.setCount(0);
								utilization.setDate(type == PeriodicType.DAILY ?  LocalDate.now() : LocalDate.now().withDayOfMonth(1));
								utilization.setPartyType(partyType);
								utilization.setPartyValue(partyValue);
								utilization.setPeriodicType(type);
								utilization.setTransactionId(transactionId);

								return utilization;
						});
				} else{
						return utilizedLimitMap.get(transactionId+"#"+partyType+"#"+partyValue);
				}
		}

		public static Payee getPayee(String payeeId){
				return payeeMap.get(payeeId);
		}

		public static void updateLimitUtilization(String transactionId, PartyType partyType, String partyValue, PeriodicType type, LocalDate date, int updatedAmount, int updatedCount){
				LimitUtilization utilization = getUtilizedLimit(partyType, partyValue, type, date, transactionId);
				utilization.setCount(updatedCount);
				utilization.setAmount(new CurrencyAmount(updatedAmount, null));
		}
}
