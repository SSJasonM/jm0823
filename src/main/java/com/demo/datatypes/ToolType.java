package com.demo.datatypes;

import java.math.BigDecimal;

/**
 * Static record representing a type of tool, which is fully constructed when instantiated
 * @param label What type of tool this record represents
 * @param dailyCharge The amount charged per chargeable rental day for this tool type
 * @param weekdayCharge Boolean value that represents if rental days during the week are chargeable for this tool type
 * @param weekendCharge Boolean value that represents if rental days during the weekend are chargeable for this tool type
 * @param holidayCharge Boolean value that represents if rental days that fall on specific holidays are chargeable for this tool type
 */
public record ToolType(String label, BigDecimal dailyCharge, Boolean weekdayCharge, Boolean weekendCharge, Boolean holidayCharge) { }