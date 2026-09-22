package se.vermiculus.benchmark.jmh;

import se.vermiculus.benchmark.jmh.avro.AvroSerDeBenchmark;
import se.vermiculus.benchmark.jmh.protobuf.ProtobufSerDeBenchmark;
import se.vermiculus.benchmark.jmh.vasp.VaspSerDeBenchmark;
import se.vermiculus.benchmark.messages.Order;
import se.vermiculus.benchmark.serialization.Serializer;
import se.vermiculus.benchmark.util.DatasetGenerator;
import se.vermiculus.benchmark.util.EnvironmentUtil;
import java.io.IOException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(java.util.concurrent.TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
//@Warmup(iterations = 1, time = 1)
//@Measurement(iterations = 1, time = 1)

@Fork(2)
public class SerDeBenchmark {

    @Param({"os-name-placeholder"})
    public String osName;

    @Param({"os-version-placeholder"})
    public String osVersion;

    @Param({"os-rrchitecture-placeholder"})
    public String osArchitecture;

    @Param({"java-runtime-placeholder"})
    public String javaRuntime;

    @Param({"java-vendor-placeholder"})
    public String javaVendor;

    @Param({"1"})
    public Integer processors;

    @Param({"jvm-memory-placeholder"})
    public String jvmMemory;

    @Param({"1"})
    public Integer jmhThreads;

    @Setup(Level.Trial)
    public void setup()
    {
        EnvironmentUtil.printEnvironment();
    }

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        @Param({"AVRO", "PROTOBUF", "VASP"})
        public DeserializerType type;

        public Serializer serializer;

        private final Order javaOrder = DatasetGenerator.createSingleOrder();

        public enum DeserializerType {
            AVRO, PROTOBUF, VASP
        }

        @Setup(Level.Trial)
        public void setup() {
            serializer = switch (type) {
                case AVRO -> new AvroSerDeBenchmark();
                case PROTOBUF -> new ProtobufSerDeBenchmark();
                case VASP -> new VaspSerDeBenchmark();
            };

            serializer.init(javaOrder);
        }
    }

    @Benchmark
    public Object testSerialization(BenchmarkState state) {
        return state.serializer.serialize();
    }

    @Benchmark
    public Object testDeserialization(BenchmarkState state) {
        return state.serializer.deserialize();
    }
}