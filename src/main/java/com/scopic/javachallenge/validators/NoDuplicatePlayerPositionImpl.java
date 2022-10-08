package com.scopic.javachallenge.validators;

import com.scopic.javachallenge.dto.TeamDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoDuplicatePlayerPositionImpl implements ConstraintValidator<NoDuplicatePlayerPosition, List<TeamDto>> {

    @Override
    public boolean isValid(List<TeamDto> teamDtoList, ConstraintValidatorContext context) {
        Set<String> positionSet = new HashSet<>();

//        HibernateConstraintValidatorContext validatorContext = context.unwrap(HibernateConstraintValidatorContext.class);
//        validatorContext.addMessageParameter()

        for (TeamDto teamDto : teamDtoList) {
            if (positionSet.contains(teamDto.getPosition())) {
                return false;
            }
            positionSet.add(teamDto.getPosition());
        }
        return true;
    }

}