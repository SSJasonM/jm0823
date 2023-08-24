package com.demo.datatypes;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class to represent the final rental agreement.
 * Property setters can be used for data validation/exception handling.
 */
public class Agreement {
    private Tool selectedTool;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private int chargeDays;
    private BigDecimal baseCharge;
    private int discountPercentage;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public Tool getSelectedTool() {
        return this.selectedTool;
    }
    public void setSelectedTool(Tool selectedTool) {
        this.selectedTool = selectedTool;
    }

    public int getRentalDays() {
        return this.rentalDays;
    }
    public void setRentalDays(int rentalDays) {
        if (rentalDays > 0) {
            this.rentalDays = rentalDays;
        } else {
            throw new IllegalArgumentException("All rental agreements must be for at least 1 day.");
        }
    }

    public LocalDate getCheckoutDate() {
        return this.checkoutDate;
    }
    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getChargeDays() {
        return this.chargeDays;
    }
    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public BigDecimal getBaseCharge() {
        return this.baseCharge;
    }
    public void setBaseCharge(BigDecimal baseCharge) {
        this.baseCharge = baseCharge;
    }

    public int getDiscountPercentage() {
        return this.discountPercentage;
    }
    public void setDiscountPercentage(int discountPercentage) {
        if (discountPercentage >= 0 && discountPercentage <= 100) {
            this.discountPercentage = discountPercentage;
        } else {
            throw new IllegalArgumentException("Please enter a valid discount percentage amount between 0-100 and try again.");
        }
    }

    public BigDecimal getDiscountAmount() {
        return this.discountAmount;
    }
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return this.finalCharge;
    }
    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }
}