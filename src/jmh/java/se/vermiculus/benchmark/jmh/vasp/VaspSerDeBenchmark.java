package se.vermiculus.benchmark.jmh.vasp;

import se.vermiculus.benchmark.messages.Order;
import se.vermiculus.benchmark.messages.VaspBenchmarkSerdesDictionary;
import org.openjdk.jmh.infra.Blackhole;
import se.vermiculus.benchmark.serialization.Serializer;
import se.vermiculus.benchmark.util.DatasetGenerator;

public class VaspSerDeBenchmark implements Serializer {

    private static final VaspBenchmarkSerdesDictionary DICTIONARY = new VaspBenchmarkSerdesDictionary();

    private Order javaOrder;
    private byte[] serializedJavaOrder;

    @Override
    public void init(Order javaOrder) {
        this.javaOrder = javaOrder;
        this.serializedJavaOrder = DICTIONARY.serialize(javaOrder);
    }

    @Override
    public byte[] serialize() {
        return DICTIONARY.serialize(this.javaOrder);
    }

    @Override
    public Object deserialize() {
        return DICTIONARY.deserialize(serializedJavaOrder);
    }
}
