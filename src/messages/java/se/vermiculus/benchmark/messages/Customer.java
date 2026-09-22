package se.vermiculus.benchmark.messages;

import se.vermiculus.veriprimer.platform.protocol.format.vasp.api.definitions.Api;

import java.time.LocalDate;
import java.util.List;


@Api.Message(id = VaspBenchmarkProtocolDefinition.CUSTOMER, series = 0)
public record Customer(long id, String firstName, String lastName, String email, LocalDate dateOfBirth, boolean vip,
                       List<Address> addresses) {
}
