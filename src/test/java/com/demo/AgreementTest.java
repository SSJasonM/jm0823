package com.demo;

import com.demo.datatypes.Application;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AgreementTest {
    /**
     * Test Case One
     * Tool Code - JAKR
     * Checkout Date - 9/3/15
     * Rental Days - 5
     * Discount - 101%
     * Success criteria - Because this case includes a discount above 100%, attempting to generate an agreement should
     * throw an exception due to the invalid discount amount.
     */
    @Test
    void generateAgreementTestCaseOne() {
        // Define test parameters
        var toolCode = "JAKR";
        var checkoutDate = LocalDate.of(2015, 9, 3);
        var rentalDays = 5;
        var discountPercentage = 101;

        // Create the application for this test scenario
        var application = this.generateApplication(toolCode, checkoutDate, rentalDays, discountPercentage);

        // Run the test case
        var testAgreement = new AgreementManager();
        assertThrows(IllegalArgumentException.class, () -> testAgreement.generateAgreement(application));
    }

    /**
     * Test Case Two
     * Tool Code - LADW
     * Checkout Date - 7/2/20
     * Rental Days - 3
     * Discount - 10%
     * Success criteria - validate finalized agreement output
     */
    @Test
    void generateAgreementTestCaseTwo() {
        // Define test parameters
        var toolCode = "LADW";
        var checkoutDate = LocalDate.of(2020, 7, 2);
        var rentalDays = 3;
        var discountPercentage = 10;

        // Create the application for this test scenario
        var application = this.generateApplication(toolCode, checkoutDate, rentalDays, discountPercentage);

        // Run the test case
        var testAgreement = new AgreementManager();
        var agreement = testAgreement.generateAgreement(application);

        // Validate acceptance criteria
        assertAll(
                () -> assertEquals(toolCode, agreement.getSelectedTool().code()),
                () -> assertEquals(ToolData.TYPE_LADDER, agreement.getSelectedTool().type().label()),
                () -> assertEquals(ToolData.BRAND_WERNER, agreement.getSelectedTool().brand()),
                () -> assertEquals(rentalDays, agreement.getRentalDays()),
                () -> assertEquals(checkoutDate, agreement.getCheckoutDate()),
                () -> assertEquals(LocalDate.of(2020, 7, 5), agreement.getDueDate()),
                () -> assertEquals(new BigDecimal("1.99"), agreement.getSelectedTool().type().dailyCharge()),
                () -> assertEquals(2, agreement.getChargeDays()),
                () -> assertEquals(new BigDecimal("3.98"), agreement.getBaseCharge()),
                () -> assertEquals(discountPercentage, agreement.getDiscountPercentage()),
                () -> assertEquals(new BigDecimal(".40"), agreement.getDiscountAmount()),
                () -> assertEquals(new BigDecimal("3.58"), agreement.getFinalCharge())
        );
    }

    /**
     * Test Case Three
     * Tool Code - CHNS
     * Checkout Date - 7/2/15
     * Rental Days - 5
     * Discount - 25%
     * Success criteria - validate finalized agreement output
     */
    @Test
    void generateAgreementTestCaseThree() {
        // Define test parameters
        var toolCode = "CHNS";
        var checkoutDate = LocalDate.of(2015, 7, 2);
        var rentalDays = 5;
        var discountPercentage = 25;

        // Create the application for this test scenario
        var application = this.generateApplication(toolCode, checkoutDate, rentalDays, discountPercentage);

        // Run the test case
        var testAgreement = new AgreementManager();
        var agreement = testAgreement.generateAgreement(application);

        // Validate acceptance criteria
        assertAll(
                () -> assertEquals(toolCode, agreement.getSelectedTool().code()),
                () -> assertEquals(ToolData.TYPE_CHAINSAW, agreement.getSelectedTool().type().label()),
                () -> assertEquals(ToolData.BRAND_STIHL, agreement.getSelectedTool().brand()),
                () -> assertEquals(rentalDays, agreement.getRentalDays()),
                () -> assertEquals(checkoutDate, agreement.getCheckoutDate()),
                () -> assertEquals(LocalDate.of(2015, 7, 7), agreement.getDueDate()),
                () -> assertEquals(new BigDecimal("1.49"), agreement.getSelectedTool().type().dailyCharge()),
                () -> assertEquals(3, agreement.getChargeDays()),
                () -> assertEquals(new BigDecimal("4.47"), agreement.getBaseCharge()),
                () -> assertEquals(discountPercentage, agreement.getDiscountPercentage()),
                () -> assertEquals(new BigDecimal("1.12"), agreement.getDiscountAmount()),
                () -> assertEquals(new BigDecimal("3.35"), agreement.getFinalCharge())
        );
    }

    /**
     * Test Case Four
     * Tool Code - JAKD
     * Checkout Date - 9/3/15
     * Rental Days - 6
     * Discount - 0%
     * Success criteria - validate finalized agreement output
     */
    @Test
    void generateAgreementTestCaseFour() {
        // Define test parameters
        var toolCode = "JAKD";
        var checkoutDate = LocalDate.of(2015, 9, 3);
        var rentalDays = 6;
        var discountPercentage = 0;

        // Create the application for this test scenario
        var application = this.generateApplication(toolCode, checkoutDate, rentalDays, discountPercentage);

        // Run the test case
        var testAgreement = new AgreementManager();
        var agreement = testAgreement.generateAgreement(application);

        // Validate acceptance criteria
        assertAll(
                () -> assertEquals(toolCode, agreement.getSelectedTool().code()),
                () -> assertEquals(ToolData.TYPE_JACKHAMMER, agreement.getSelectedTool().type().label()),
                () -> assertEquals(ToolData.BRAND_DEWALT, agreement.getSelectedTool().brand()),
                () -> assertEquals(rentalDays, agreement.getRentalDays()),
                () -> assertEquals(checkoutDate, agreement.getCheckoutDate()),
                () -> assertEquals(LocalDate.of(2015, 9, 9), agreement.getDueDate()),
                () -> assertEquals(new BigDecimal("2.99"), agreement.getSelectedTool().type().dailyCharge()),
                () -> assertEquals(3, agreement.getChargeDays()),
                () -> assertEquals(new BigDecimal("8.97"), agreement.getBaseCharge()),
                () -> assertEquals(discountPercentage, agreement.getDiscountPercentage()),
                () -> assertEquals(new BigDecimal("0.00"), agreement.getDiscountAmount()),
                () -> assertEquals(new BigDecimal("8.97"), agreement.getFinalCharge())
        );
    }

    /**
     * Test Case Five
     * Tool Code - JAKR
     * Checkout Date - 7/2/15
     * Rental Days - 9
     * Discount - 0%
     * Success criteria - validate finalized agreement output
     */
    @Test
    void generateAgreementTestCaseFive() {
        // Define test parameters
        var toolCode = "JAKR";
        var checkoutDate = LocalDate.of(2015, 7, 2);
        var rentalDays = 9;
        var discountPercentage = 0;

        // Create the application for this test scenario
        var application = this.generateApplication(toolCode, checkoutDate, rentalDays, discountPercentage);

        // Run the test case
        var testAgreement = new AgreementManager();
        var agreement = testAgreement.generateAgreement(application);

        // Validate acceptance criteria
        assertAll(
                () -> assertEquals(toolCode, agreement.getSelectedTool().code()),
                () -> assertEquals(ToolData.TYPE_JACKHAMMER, agreement.getSelectedTool().type().label()),
                () -> assertEquals(ToolData.BRAND_RIDGID, agreement.getSelectedTool().brand()),
                () -> assertEquals(rentalDays, agreement.getRentalDays()),
                () -> assertEquals(checkoutDate, agreement.getCheckoutDate()),
                () -> assertEquals(LocalDate.of(2015, 7, 11), agreement.getDueDate()),
                () -> assertEquals(new BigDecimal("2.99"), agreement.getSelectedTool().type().dailyCharge()),
                () -> assertEquals(5, agreement.getChargeDays()),
                () -> assertEquals(new BigDecimal("14.95"), agreement.getBaseCharge()),
                () -> assertEquals(discountPercentage, agreement.getDiscountPercentage()),
                () -> assertEquals(new BigDecimal("0.00"), agreement.getDiscountAmount()),
                () -> assertEquals(new BigDecimal("14.95"), agreement.getFinalCharge())
        );
    }

    /**
     * Test Case Six
     * Tool Code - JAKR
     * Checkout Date - 7/2/20
     * Rental Days - 4
     * Discount - 15%
     * Success criteria - validate finalized agreement output
     */
    @Test
    void generateAgreementTestCaseSix() {
        // Define test parameters
        var toolCode = "JAKR";
        var checkoutDate = LocalDate.of(2020, 7, 2);
        var rentalDays = 4;
        var discountPercentage = 50;

        // Create the application for this test scenario
        var application = this.generateApplication(toolCode, checkoutDate, rentalDays, discountPercentage);

        // Run the test case
        var testAgreement = new AgreementManager();
        var agreement = testAgreement.generateAgreement(application);

        // Validate acceptance criteria
        assertAll(
                () -> assertEquals(toolCode, agreement.getSelectedTool().code()),
                () -> assertEquals(ToolData.TYPE_JACKHAMMER, agreement.getSelectedTool().type().label()),
                () -> assertEquals(ToolData.BRAND_RIDGID, agreement.getSelectedTool().brand()),
                () -> assertEquals(rentalDays, agreement.getRentalDays()),
                () -> assertEquals(checkoutDate, agreement.getCheckoutDate()),
                () -> assertEquals(LocalDate.of(2020, 7, 6), agreement.getDueDate()),
                () -> assertEquals(new BigDecimal("2.99"), agreement.getSelectedTool().type().dailyCharge()),
                () -> assertEquals(1, agreement.getChargeDays()),
                () -> assertEquals(new BigDecimal("2.99"), agreement.getBaseCharge()),
                () -> assertEquals(discountPercentage, agreement.getDiscountPercentage()),
                () -> assertEquals(new BigDecimal("1.50"), agreement.getDiscountAmount()),
                () -> assertEquals(new BigDecimal("1.49"), agreement.getFinalCharge())
        );
    }

    /**
     * Helper function that generates a new application based on the test scenario.
     * Used for testing rental agreement output
     * @param toolCode Tool code for test scenario
     * @param checkoutDate Rental agreement checkout date for test scenario
     * @param rentalDays Number of days the test scenario rental agreement should generate for
     * @param discountPercentage Discount percentage used in the test scenario
     * @return Application object that can be used to test rental agreement output
     */
    Application generateApplication(String toolCode, LocalDate checkoutDate, int rentalDays, int discountPercentage) {
        var application = new Application();

        application.setToolCode(toolCode);
        application.setCheckoutDate(checkoutDate);
        application.setRentalDays(rentalDays);
        application.setDiscountPercentage(discountPercentage);

        return application;
    }
}