package com.demo;

import com.demo.datatypes.Agreement;
import com.demo.datatypes.Application;
import com.demo.datatypes.ToolType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class containing all the functionality for generating a new rental agreement
 * when provided an existing application
 */
public class AgreementManager {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yy");
    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getCurrencyInstance();

    /**
     * Generate a new rental agreement based on the information provided in a rental application
     * @param application Application object that provides tool and rental details for this rental agreement
     * @return Full rental agreement object
     */
    public Agreement generateAgreement(Application application) {
        var toolData = new ToolData();
        var agreement = new Agreement();

        agreement.setSelectedTool(toolData.getTool(application.getToolCode()));
        agreement.setRentalDays(application.getRentalDays());
        agreement.setCheckoutDate(application.getCheckoutDate());
        agreement.setDueDate(this.calculateDueDate(agreement.getCheckoutDate(), agreement.getRentalDays()));
        agreement.setChargeDays(this.calculateChargeDays(agreement.getSelectedTool().type(),
                agreement.getCheckoutDate().plusDays(1), agreement.getRentalDays()));
        agreement.setBaseCharge(this.calculateBaseCharge(agreement.getChargeDays(), agreement.getSelectedTool().type().dailyCharge()));
        agreement.setDiscountPercentage(application.getDiscountPercentage());
        agreement.setDiscountAmount(this.calculateDiscountAmount(agreement.getBaseCharge(), agreement.getDiscountPercentage()));
        agreement.setFinalCharge(this.calculateFinalCharge(agreement.getBaseCharge(), agreement.getDiscountAmount()));

        return agreement;
    }

    /**
     * Method for displaying the completed agreement to the console.
     * @param agreement Already completed rental agreement ready for user-facing display
     */
    public void displayAgreement(Agreement agreement) {
        System.out.println("Tool code: " + agreement.getSelectedTool().code());
        System.out.println("Tool type: " + agreement.getSelectedTool().type().label());
        System.out.println("Tool brand: " + agreement.getSelectedTool().brand());
        System.out.println("Rental days: " + agreement.getRentalDays());
        System.out.println("Checkout date: " + agreement.getCheckoutDate().format(DATE_FORMAT));
        System.out.println("Due date: " + agreement.getDueDate().format(DATE_FORMAT));
        System.out.println("Daily rental charge: " + CURRENCY_FORMAT.format(agreement.getSelectedTool().type().dailyCharge()));
        System.out.println("Charge days: " + agreement.getChargeDays());
        System.out.println("Pre-discount charge: " + CURRENCY_FORMAT.format(agreement.getBaseCharge()));
        System.out.println("Discount percent: " + agreement.getDiscountPercentage() + "%");
        System.out.println("Discount amount: " + CURRENCY_FORMAT.format(agreement.getDiscountAmount()));
        System.out.println("Final charge: " + CURRENCY_FORMAT.format(agreement.getFinalCharge()));
    }

    /**
     * Private helper method used to calculate the rental due date based on the starting date and number of rental days
     * @param startingDate Date the rental agreement will begin
     * @param rentalDays Total number of rental days on this agreement
     * @return Rental due date calculated using the provided starting date and rental days
     */
    private LocalDate calculateDueDate(LocalDate startingDate, int rentalDays) {
        return startingDate.plusDays(rentalDays);
    }

    /**
     * Private helper function that returns the total number of days within the rental period that are chargeable for the given tool type
     * @param toolType Object that represents the type of tool on this agreement.  This includes charge and exclusion information.
     * @param startingChargeDate Starting date within the rental period
     * @param rentalDays Total number of rental days on this agreement
     * @return Total number of chargeable days calculated as the rental days minus the exclusion days
     */
    private int calculateChargeDays(ToolType toolType, LocalDate startingChargeDate, int rentalDays) {
        var exclusionManager = new ExclusionManager();
        var rentalDaysCollection = exclusionManager.getRentalDays(startingChargeDate, rentalDays);
        var exclusionDays = 0;

        for (var day: rentalDaysCollection) {
            if (!toolType.holidayCharge() && day.isHoliday()) {
                exclusionDays++;
            } else {
                if (!toolType.weekendCharge() && day.isWeekend()) {
                    exclusionDays++;
                }
            }
        }

        return rentalDays - exclusionDays;
    }

    /**
     * Private helper function that calculates and returns the base charge for this tool.
     * @param chargeDays Total chargeable rental days for this agreement
     * @param dailyCharge The base daily rental charge for this tool, before discounts
     * @return Base charge for tool rental calculated as daily charge multiplied by the number of chargeable rental days
     */
    private BigDecimal calculateBaseCharge(int chargeDays, BigDecimal dailyCharge) {
        return dailyCharge.multiply(BigDecimal.valueOf(chargeDays)).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Private helper function that calculates and returns the discount amount for this agreement.
     * Base charge should be calculated prior to calling this function by using the calculateBaseCharge() method.
     * Discount amount is calculated by first dividing the whole number percentage amount by 100 to retrieve a decimal
     * representation of the percentage value.  This decimal amount is multiplied by the base charge for this agreement.
     * Rounding is done using the standard "half up" method.
     * @param baseCharge Base charge for this agreement as calculated by the calculateBaseCharge() method
     * @param discountPercentage Discount percentage as represented by a whole number
     * @return Calculated discount amount for this agreement
     */
    private BigDecimal calculateDiscountAmount(BigDecimal baseCharge, int discountPercentage) {
        return baseCharge.multiply(BigDecimal.valueOf(discountPercentage)).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
    }

    /**
     * Private helper function that calculates the final charge for the agreement.  This is the actual cost for this
     * rental that will be charged to the customer, after all exclusions and discounts are calculated and applied.
     * @param baseCharge Base charge for this agreement as calculated by the calculateBaseCharge() method
     * @param discountAmount Discount amount for this agreement as calculated by the calculateDiscountAmount() method
     * @return Final charge amount calculated by subtracting the discount amount from the base charge amount
     */
    private BigDecimal calculateFinalCharge(BigDecimal baseCharge, BigDecimal discountAmount) {
        return baseCharge.subtract(discountAmount);
    }
}
