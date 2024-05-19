package org.botos.sec06.assignment;

import lombok.Builder;

@Builder
public record Order(String name,
                    String category,
                    Integer price,
                    Integer quantity) {}
