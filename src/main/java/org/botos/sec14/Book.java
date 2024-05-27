package org.botos.sec14;

import lombok.Builder;

@Builder
public record Book(int id,
                   String author,
                   String title) {}
