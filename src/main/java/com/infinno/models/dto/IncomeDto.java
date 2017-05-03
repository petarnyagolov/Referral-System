package com.infinno.models.dto;

import java.math.BigDecimal;

public class IncomeDto extends EventDto{

    private BigDecimal sum;

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
