package org.botos.sec09.applications;

import lombok.Builder;

import java.util.List;

@Builder
public record UserInformation (Integer userId,
                               String username,
                               Integer balance,
                               List<Order> orders) {}
