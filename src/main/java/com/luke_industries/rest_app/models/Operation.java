package com.luke_industries.rest_app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Operation {
    private OperationType operation;
    private int unitCost;
    private int quantity;
}
