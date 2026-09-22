package se.vermiculus.benchmark.messages;

import se.vermiculus.veriprimer.platform.protocol.format.vasp.api.definitions.Api;

@Api.Enum
public enum OrderStatus {
    NEW,
    PAID,
    PICKING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
