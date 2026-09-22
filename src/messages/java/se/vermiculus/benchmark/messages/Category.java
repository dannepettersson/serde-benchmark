package se.vermiculus.benchmark.messages;

import se.vermiculus.veriprimer.platform.protocol.format.vasp.api.definitions.Api;

import java.util.Objects;


@Api.Message(id = VaspBenchmarkProtocolDefinition.CATEGORY, series = 0)
public record Category(long id, String name, Category parent) {
}
