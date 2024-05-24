package org.botos.sec10.assignment.buffer;

import lombok.Builder;

import java.time.LocalTime;
import java.util.Map;

@Builder
public record RevenueReport(LocalTime time,
                            Map<String, Integer> revenue) {}
