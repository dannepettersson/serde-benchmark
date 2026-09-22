package se.vermiculus.benchmark.jmh.protobuf;

import se.vermiculus.benchmark.serialization.model.proto.Order;
import se.vermiculus.benchmark.serialization.protobuf.ProtobufMapper;

import java.io.IOException;
import org.openjdk.jmh.infra.Blackhole;
import se.vermiculus.benchmark.util.DatasetGenerator;

public class ProtobufSerDeBenchmark {

    private static final ProtobufMapper MAPPER = new ProtobufMapper();

    public static class BenchmarkState {
        public Order protoOrder = MAPPER.map(DatasetGenerator.createSingleOrder());
        public byte[] serializedProtoOrder;

        public BenchmarkState() {
            serializedProtoOrder = protoOrder.toByteArray();
        }
    }

    public void protobufSerialization(BenchmarkState state, Blackhole blackhole) {
        blackhole.consume(state.protoOrder.toByteArray());
    }

    public void protobufDeserialization(BenchmarkState state, Blackhole blackhole) throws IOException {
        blackhole.consume(Order.parseFrom(state.serializedProtoOrder));
    }
}