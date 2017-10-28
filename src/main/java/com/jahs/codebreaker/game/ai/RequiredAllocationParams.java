package com.jahs.codebreaker.game.ai;

import com.google.common.collect.ImmutableList;
import com.jahs.codebreaker.model.PinColor;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class RequiredAllocationParams {

    private final List<ResolutionContext.FieldConfig> fieldConfigs;
    private final Set<PinColor> requiredButMissing;

    public RequiredAllocationParams(List<ResolutionContext.FieldConfig> fieldConfigs, Set<PinColor> requiredButMissing) {
        this.fieldConfigs = ImmutableList.copyOf(fieldConfigs);
        this.requiredButMissing = EnumSet.copyOf(requiredButMissing);
    }

    public List<ResolutionContext.FieldConfig> getFieldConfigs() {
        return fieldConfigs;
    }

    public Set<PinColor> getRequiredButMissing() {
        return requiredButMissing;
    }
}


