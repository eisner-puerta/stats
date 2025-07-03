package co.com.bancolombia.model.validators;

import co.com.bancolombia.model.userinteractionstats.userinteractionstats.UserInteractionStats;

public interface IHashValidator {
    boolean isValid(UserInteractionStats stats);
}