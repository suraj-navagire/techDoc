package com.systemdesign.lld.financiallimitsystem.validator;

import com.systemdesign.lld.financiallimitsystem.DataBase;
import com.systemdesign.lld.financiallimitsystem.model.EntityLimitPackageMap;
import com.systemdesign.lld.financiallimitsystem.model.ILimit;
import com.systemdesign.lld.financiallimitsystem.model.LimitCheckRequest;
import com.systemdesign.lld.financiallimitsystem.model.LimitPackage;
import com.systemdesign.lld.financiallimitsystem.model.LimitUtilization;
import com.systemdesign.lld.financiallimitsystem.model.PartyType;
import com.systemdesign.lld.financiallimitsystem.model.PeriodicLimit;
import com.systemdesign.lld.financiallimitsystem.model.PeriodicType;

import java.util.List;

public class PeriodicLimitValidator implements ILimitValidator{
		@Override public boolean validate(LimitCheckRequest request, EntityLimitPackageMap entityLimitPackageMap) {

				LimitPackage limitPackage = entityLimitPackageMap.getLimitPackage();
				List<ILimit> limits = limitPackage.getLimitMap().get(request.getTransaction().getId());

				PeriodicLimit dailyLimit = null;
				PeriodicLimit monthlyLimit = null;
				for (ILimit limit : limits){
						if (limit instanceof PeriodicLimit){
								PeriodicLimit periodicLimit = (PeriodicLimit) limit;
								if (PeriodicType.DAILY == periodicLimit.getType()){
										dailyLimit = periodicLimit;
								}

								if (PeriodicType.MONTHLY == periodicLimit.getType()){
										monthlyLimit = periodicLimit;
								}
						}
				}

				return validateDailyLimit(dailyLimit, request) && validateMonthlyLimit(monthlyLimit, request);
		}

		private boolean validateDailyLimit(PeriodicLimit dailyLimit, LimitCheckRequest request) {
				if (dailyLimit == null){
						return true;
				}
				//Validate limit. Ignoring currency conversion
				int inputAmount = request.getAmount().getAmount();

				int dailyAmount = dailyLimit.getAmount().getAmount();
				int dailyCount = dailyLimit.getCount();


				//Fetch utilized limits for user
				LimitUtilization utilization = DataBase.getUtilizedLimit(PartyType.INITIATOR, request.getUserId(), PeriodicType.DAILY);

				if (utilization == null){
						return true;
				}

				int utilizedAmount = utilization.getAmount().getAmount();
				int utilizedCount = utilization.getCount();

				int amountToBeValidated = utilizedAmount + inputAmount;
				int countToBeValidated = utilizedCount + 1;

				return amountToBeValidated <= dailyAmount && countToBeValidated <= dailyCount;
		}

		private boolean validateMonthlyLimit(PeriodicLimit monthlyLimit, LimitCheckRequest request) {
				if (monthlyLimit == null){
						return true;
				}
				//Validate limit. Ignoring currency conversion
				int inputAmount = request.getAmount().getAmount();

				int monthlyAmount = monthlyLimit.getAmount().getAmount();
				int monthLyCount = monthlyLimit.getCount();


				//Fetch utilized limits for user
				LimitUtilization utilization = DataBase.getUtilizedLimit(PartyType.INITIATOR, request.getUserId(), PeriodicType.MONTHLY);

				if (utilization == null){
						return true;
				}

				int utilizedAmount = utilization.getAmount().getAmount();
				int utilizedCount = utilization.getCount();

				int amountToBeValidated = utilizedAmount + inputAmount;
				int countToBeValidated = utilizedCount + 1;

				return amountToBeValidated <= monthlyAmount && countToBeValidated <= monthLyCount;
		}

		@Override public boolean utilizeLimit(LimitCheckRequest request) {

				DataBase.updateLimitUtilization(PartyType.INITIATOR, request.getUserId(), PeriodicType.DAILY, );
				return false;
		}
}
