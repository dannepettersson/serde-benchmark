package se.vermiculus.benchmark.messages;

import se.vermiculus.veriprimer.platform.protocol.format.vasp.api.definitions.Api;

import java.time.LocalDate;
import java.util.List;


@Api.Message(id = VaspBenchmarkProtocolDefinition.ORDER, series = 0)
public record Order(String id, Customer customer, List<OrderLine> lines, OrderStatus status, LocalDate created,
                    Address shippingAddress, String notes) {
}
