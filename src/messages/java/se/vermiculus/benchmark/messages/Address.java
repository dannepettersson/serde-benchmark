package se.vermiculus.benchmark.messages;

import se.vermiculus.veriprimer.platform.protocol.format.vasp.api.definitions.Api;


@Api.Message(id = VaspBenchmarkProtocolDefinition.ADDRESS, series = 0)
public record Address(String street, String city, String zip, String country) {
}
