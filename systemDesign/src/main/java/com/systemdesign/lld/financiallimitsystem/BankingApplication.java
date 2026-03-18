package com.systemdesign.lld.financiallimitsystem;

import com.systemdesign.lld.financiallimitsystem.model.Currency;
import com.systemdesign.lld.financiallimitsystem.model.CurrencyAmount;
import com.systemdesign.lld.financiallimitsystem.model.DurationLimit;
import com.systemdesign.lld.financiallimitsystem.model.EntityLimitPackageMap;
import com.systemdesign.lld.financiallimitsystem.model.EntityType;
import com.systemdesign.lld.financiallimitsystem.model.ILimit;
import com.systemdesign.lld.financiallimitsystem.model.LimitCheckRequest;
import com.systemdesign.lld.financiallimitsystem.model.LimitPackage;
import com.systemdesign.lld.financiallimitsystem.model.PeriodicLimit;
import com.systemdesign.lld.financiallimitsystem.model.PeriodicType;
import com.systemdesign.lld.financiallimitsystem.model.Transaction;
import com.systemdesign.lld.financiallimitsystem.model.TransactionLimit;

public class BankingApplication {

		private static String IMPS = "IMPS";

		private static String RTGS = "RTGS";

		private static String NEFT = "NEFT";

		public static void main(String[] args) {
				System.out.println("Banking Application Started");

				try {
						run();
				}catch (Exception e){
						e.printStackTrace();
				}


				System.out.println("Banking Application Terminated");
		}

		private static void run(){
				BankingApplication bankingApplication = new BankingApplication();

				bankingApplication.createSystemLevelLimits();

				//Retail user logs-in and customize its limits;
				bankingApplication.userUpdatingLimits("retail001");

				ILimitEvaluationService service = new LimitEvaluationService();

				LimitCheckRequest request = new LimitCheckRequest();
				request.setAmount(new CurrencyAmount(6000, null));
				request.setTransaction(new Transaction(IMPS, NEFT, "NEFT transaction"));
				request.setPayeeId("payee001");
				request.setUserId("retail001");


				boolean isAllowed = service.validate(request).isAllowed();
				System.out.println(isAllowed ? "Transaction is Allowed ": "Transaction is not allowed");

		}

		private void createSystemLevelLimits(){

				//Creating limit for imps
				ILimit impsTransactionLimit = new TransactionLimit("1_LAKH_TRANSACTION", "1 Lakh transaction limit", new CurrencyAmount(0, Currency.INR), new CurrencyAmount(100000, Currency.INR));
				ILimit impsDailyPeriodicLimit = new PeriodicLimit("1_LAKH_DAILY", "1 Lakh daily limit", PeriodicType.DAILY, new CurrencyAmount(100000, Currency.INR), 10);
				ILimit impsMonthlyPeriodicLimit = new PeriodicLimit("10_LAKH_MONTHLY", "1 Lakh monthly limit",PeriodicType.MONTHLY, new CurrencyAmount(1000000, Currency.INR), 100);
				ILimit impsDurationalLimit = new DurationLimit("25K_24Hour", "25k till first 24 hour", 0, 24, new CurrencyAmount(25000, Currency.INR));

				//Adding it to db
				DataBase.addTransactionLimits(impsTransactionLimit);
				DataBase.addPeriodicLimits(impsDailyPeriodicLimit);
				DataBase.addPeriodicLimits(impsMonthlyPeriodicLimit);
				DataBase.addDurationalLimits(impsDurationalLimit);


				//Creating limit for neft
				ILimit neftTransactionLimit = new TransactionLimit("5_LAKH_TRANSACTION", "5 Lakh transaction limit", new CurrencyAmount(0, Currency.INR), new CurrencyAmount(500000, Currency.INR));
				ILimit neftDailyPeriodicLimit = new PeriodicLimit("10_LAKH_DAILY", "10 Lakh daily limit",PeriodicType.DAILY, new CurrencyAmount(1000000, Currency.INR), 10);
				ILimit neftMonthlyPeriodicLimit = new PeriodicLimit("20_LAKH_MONTHLY", "20 Lakh monthly limit",PeriodicType.MONTHLY, new CurrencyAmount(2000000, Currency.INR), 100);

				DataBase.addTransactionLimits(neftTransactionLimit);
				DataBase.addPeriodicLimits(neftDailyPeriodicLimit);
				DataBase.addPeriodicLimits(neftMonthlyPeriodicLimit);

				//Creating limit for rtgs
				ILimit rtgsTransactionLimit = new TransactionLimit("20_LAKH_TRANSACTION", "20 Lakh transaction limit", new CurrencyAmount(0, Currency.INR), new CurrencyAmount(1000000, Currency.INR));
				ILimit rtgsDailyPeriodicLimit = new PeriodicLimit("50_LAKH_DAILY", "50 Lakh daily limit",PeriodicType.DAILY, new CurrencyAmount(5000000, Currency.INR), 10);

				DataBase.addTransactionLimits(rtgsTransactionLimit);
				DataBase.addPeriodicLimits(rtgsDailyPeriodicLimit);

				//Creating limit package for imps neft and rtgs transaction
				LimitPackage limitPackage = new LimitPackage("SYSTEM_LIMIT_PACKAGE", "Limit package for system");

				limitPackage.addLimitMap(IMPS, impsTransactionLimit);
				limitPackage.addLimitMap(IMPS, impsDailyPeriodicLimit);
				limitPackage.addLimitMap(IMPS, impsMonthlyPeriodicLimit);
				limitPackage.addLimitMap(IMPS, impsDurationalLimit);

				limitPackage.addLimitMap(NEFT, neftTransactionLimit);
				limitPackage.addLimitMap(NEFT, neftDailyPeriodicLimit);
				limitPackage.addLimitMap(NEFT, neftMonthlyPeriodicLimit);


				limitPackage.addLimitMap(RTGS, rtgsTransactionLimit);
				limitPackage.addLimitMap(RTGS, rtgsDailyPeriodicLimit);

				//Storing this limit package in db
				DataBase.addLimitPackages(limitPackage);

				//Assigning this limit package at system level

				EntityLimitPackageMap entityLimitPackageMap = new EntityLimitPackageMap(EntityType.SYSTEM, null, limitPackage);


				//Storing in entity limit package mapping in db
				DataBase.addEntityLimitPackageMapList(entityLimitPackageMap);
		}

		private void userUpdatingLimits(String userId){

				//Here system only allowing to increase transaction limit and daily limit. User cannot update monthly and durational limit.
				//Creating limit for imps
				ILimit impsTransactionLimit = new TransactionLimit("50_LAKH_TRANSACTION", "50 Lakh transaction limit", new CurrencyAmount(0, Currency.INR), new CurrencyAmount(50000, Currency.INR));
				ILimit impsDailyPeriodicLimit = new PeriodicLimit("2_LAKH_DAILY", "2 Lakh daily limit",PeriodicType.DAILY, new CurrencyAmount(200000, Currency.INR), 10);

				//Adding it to db
				DataBase.addTransactionLimits(impsTransactionLimit);
				DataBase.addPeriodicLimits(impsDailyPeriodicLimit);

				//Creating limit package for imps
				LimitPackage limitPackage = new LimitPackage("SYSTEM_LIMIT_PACKAGE", "Limit package for system");

				limitPackage.addLimitMap(IMPS, impsTransactionLimit);
				limitPackage.addLimitMap(IMPS, impsDailyPeriodicLimit);

				//Storing this limit package in db
				DataBase.addLimitPackages(limitPackage);

				//Assigning this limit package at system level

				EntityLimitPackageMap entityLimitPackageMap = new EntityLimitPackageMap(EntityType.USER, userId, limitPackage);

				//Storing in entity limit package mapping in db
				DataBase.addEntityLimitPackageMapList(entityLimitPackageMap);
		}



}
