package com.luke_industries.rest_app.services;

import com.luke_industries.rest_app.models.Operation;
import com.luke_industries.rest_app.models.OperationType;
import com.luke_industries.rest_app.models.Tax;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TaxCalculatorTest {
    private TaxCalculator taxCalculator;

    @Test
    void simpleBuyAndSell() {
        taxCalculator = new TaxCalculator(List.of(
                new Operation(OperationType.buy, 10.0, 10000),
                new Operation(OperationType.sell, 20.0, 5000)
        ));
        List<Tax> taxes = taxCalculator.calculateTaxes();
        assertEquals(2, taxes.size());
        assertEquals(0.0, taxes.get(0).getTax());
        assertEquals(10000.0, taxes.get(1).getTax());

    }

    @Test
    void  lossDeduction() {
        taxCalculator = new TaxCalculator(List.of(
                new Operation(OperationType.buy, 10.0, 10000),
                new Operation(OperationType.sell, 5.0, 5000),
                new Operation(OperationType.sell, 20.0, 3000)
        ));
        List<Tax> taxes = taxCalculator.calculateTaxes();
        assertEquals(3, taxes.size());
        assertEquals(0.0, taxes.get(0).getTax());
        assertEquals(0.0, taxes.get(1).getTax());
        assertEquals(1000.0, taxes.get(2).getTax());
    }

    @Test
    void weightedAveragePrice() {
        taxCalculator = new TaxCalculator(List.of(
                new Operation(OperationType.buy, 10.0, 10000),
                new Operation(OperationType.buy, 25.0, 5000),
                new Operation(OperationType.sell, 15.0, 10000)
        ));
        List<Tax> taxes = taxCalculator.calculateTaxes();
        assertEquals(3, taxes.size());
        assertEquals(0.0, taxes.get(0).getTax());
        assertEquals(0.0, taxes.get(1).getTax());
        assertEquals(0.0, taxes.get(2).getTax());
    }

    @Test
    void lossCarryForward() {
        taxCalculator = new TaxCalculator(List.of(
                new Operation(OperationType.buy, 10.0, 10000),
                new Operation(OperationType.sell, 5.0, 5000),
                new Operation(OperationType.sell, 15.0, 2000),
                new Operation(OperationType.sell, 20.0, 2000)
        ));
        List<Tax> taxes = taxCalculator.calculateTaxes();
        assertEquals(4, taxes.size());
        assertEquals(0.0, taxes.get(0).getTax());
        assertEquals(0.0, taxes.get(1).getTax());
        assertEquals(0.0, taxes.get(2).getTax());
        assertEquals(1000.0, taxes.get(3).getTax());
    }




}