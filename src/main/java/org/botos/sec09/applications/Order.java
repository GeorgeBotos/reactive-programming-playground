package org.botos.sec09.applications;

import lombok.Builder;

@Builder
public record Order(Integer userId,
                    String productName,
                    Integer price) {
}
