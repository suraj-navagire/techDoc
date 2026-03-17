package com.systemdesign.lld.financiallimitsystem.validator;

import com.systemdesign.lld.financiallimitsystem.DataBase;
import com.systemdesign.lld.financiallimitsystem.model.DurationLimit;
import com.systemdesign.lld.financiallimitsystem.model.EntityLimitPackageMap;
import com.systemdesign.lld.financiallimitsystem.model.ILimit;
import com.systemdesign.lld.financiallimitsystem.model.LimitCheckRequest;
import com.systemdesign.lld.financiallimitsystem.model.LimitPackage;
import com.systemdesign.lld.financiallimitsystem.model.LimitUtilization;
import com.systemdesign.lld.financiallimitsystem.model.PartyType;
import com.systemdesign.lld.financiallimitsystem.model.Payee;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class DurationalLimitValidator implements ILimitValidator{
		@Override public boolean validate(LimitCheckRequest request, EntityLimitPackageMap entityLimitPackageMap) {

				LimitPackage limitPackage = entityLimitPackageMap.getLimitPackage();
				List<ILimit> limits = limitPackage.getLimitMap().get(request.getTransaction().getId());

				DurationLimit durationaLimit = null;
				for (ILimit limit : limits){
						if (limit instanceof DurationLimit){
								durationaLimit = (DurationLimit) limit;
								break;
						}
				}

				if (durationaLimit == null){
						return true;
				}

				int endTime = durationaLimit.getEndTime();

				if (isDurationalLimitCheckRequired(request, endTime)){
						return true;
				}

				//Validate limit. Ignoring currency conversion
				int inputAmount = request.getAmount().getAmount();

				int durationaLimitAmount = durationaLimit.getAmount().getAmount();

				LimitUtilization utilization = DataBase.getUtilizedLimit(PartyType.PAYEE, request.getPayeeId(), null);

				if (utilization == null){
						return true;
				}

				int utilizedAmount = utilization.getAmount().getAmount();

				int amountToBeValidated = utilizedAmount + inputAmount;

				return amountToBeValidated <= durationaLimitAmount;
		}

		private static boolean isDurationalLimitCheckRequired(LimitCheckRequest request, int endTime) {
				//Check When payee is created
				Payee payee = DataBase.getPayee(request.getPayeeId());

				LocalDateTime payeeCreationTime = payee.getCreationDate();

				Duration payeeCreatedDuration = Duration.between(payeeCreationTime.toLocalTime(), LocalTime.now());

				//No need to validate limit as payee is old enough
				return payeeCreatedDuration.toHours() > endTime;
		}

		@Override public boolean utilizeLimit(LimitCheckRequest request) {

				return false;
		}
}
