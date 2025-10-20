package com.luke_industries.rest_app.services;

import com.luke_industries.rest_app.models.Operation;
import com.luke_industries.rest_app.models.OperationType;
import com.luke_industries.rest_app.models.Tax;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class TaxCalculator {
    private List<Operation> operations;
    private final double TAX_RATE = 0.2;
    private final int MINIMUM_SALES_AMOUNT = 20000;

    public List<Tax> calculateTaxes() {
        List<Tax> taxes = new ArrayList<>();
        double weightedAveragePrice = 0.0;
        int currentStockQuantity = 0;
        double currentDebt = 0.0;

        for ( Operation op: operations) {
            Tax tax = new Tax(0.0);
            if (op.getOperation() == OperationType.buy) {
                weightedAveragePrice = weightedAveragePrice == 0.0 ? op.getUnitCost() :
                        ((currentStockQuantity * weightedAveragePrice ) + (op.getQuantity() * op.getUnitCost())) /
                            (currentStockQuantity + op.getQuantity());

                currentStockQuantity+=op.getQuantity();

            } else {


                double profit = (op.getUnitCost() - weightedAveragePrice ) * op.getQuantity();
                currentStockQuantity -= op.getQuantity();

                if(profit > 0) {
                    double remaining = profit - currentDebt;
                    if (remaining > 0) {
                        if (profit >= MINIMUM_SALES_AMOUNT ) tax.setTax(remaining * TAX_RATE);
                    } else {
                        currentDebt = Math.abs(remaining);
                    }
                } else {
                    currentDebt += Math.abs(profit);
                }





            }
            taxes.add(tax);
        }
        return taxes;
    }
}
