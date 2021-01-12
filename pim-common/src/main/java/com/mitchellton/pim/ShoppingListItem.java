package com.mitchellton.pim;

import java.util.List;

public class ShoppingListItem {

    private final PartDo part;
    private final int amount;
    private final List<PriceDo> prices;

    public ShoppingListItem(PartDo part, int amount, List<PriceDo> prices) {
        this.part = part;
        this.amount = amount;
        this.prices = prices;
    }

    public PartDo getPart() {
        return part;
    }

    public int getAmount() {
        return amount;
    }

    public List<PriceDo> getPrices() {
        return prices;
    }
}
