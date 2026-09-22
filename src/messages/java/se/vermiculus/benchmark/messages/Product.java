package se.vermiculus.benchmark.messages;

import se.vermiculus.veriprimer.platform.protocol.format.vasp.api.definitions.Api;

import java.math.BigDecimal;
import java.util.List;


@Api.Message(id = VaspBenchmarkProtocolDefinition.PRODUCT, series = 0)
public record Product(String sku, String name, String description, Category category, BigDecimal price,
                      int weightGrams, List<String> tags) {
}
