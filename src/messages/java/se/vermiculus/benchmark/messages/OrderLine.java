package se.vermiculus.benchmark.messages;

import se.vermiculus.veriprimer.platform.protocol.format.vasp.api.definitions.Api;

import java.math.BigDecimal;
import java.math.BigInteger;


@Api.Message(id = VaspBenchmarkProtocolDefinition.ORDER_LINE, series = 0)
public record OrderLine(Product product, BigInteger quantity, BigDecimal unitPrice, double discount) {
}
