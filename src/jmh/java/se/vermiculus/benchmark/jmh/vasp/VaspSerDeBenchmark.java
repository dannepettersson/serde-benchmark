package se.vermiculus.benchmark.jmh.vasp;

import se.vermiculus.benchmark.messages.Order;
import se.vermiculus.benchmark.messages.VaspBenchmarkSerdesDictionary;
import org.openjdk.jmh.infra.Blackhole;
import se.vermiculus.benchmark.util.DatasetGenerator;

public class VaspSerDeBenchmark {

    private static final VaspBenchmarkSerdesDictionary DICTIONARY = new VaspBenchmarkSerdesDictionary();

    public static class BenchmarkState {
        public Order javaOrder = DatasetGenerator.createSingleOrder();
        public byte[] serializedJavaOrder;

        public BenchmarkState() {
            serializedJavaOrder = DICTIONARY.serialize(javaOrder);
        }
    }

    public void vaspSerialization(BenchmarkState state, Blackhole blackhole) {
        byte[] serialized = DICTIONARY.serialize(state.javaOrder);
        blackhole.consume(serialized);
    }

    public void vaspDeserialization(BenchmarkState state, Blackhole blackhole) {
        Object deserialized = DICTIONARY.deserialize(state.serializedJavaOrder);
        blackhole.consume(deserialized);
    }
}
