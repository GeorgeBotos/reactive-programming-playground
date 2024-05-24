package org.botos.sec10.assignment.groupby;

import lombok.Builder;

@Builder
public record PurchaseOrder(String item,
                            String category,
                            Integer price) {}
