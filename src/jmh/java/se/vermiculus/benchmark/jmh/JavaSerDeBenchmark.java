package se.vermiculus.benchmark.jmh;

import se.vermiculus.benchmark.jmh.avro.AvroSerDeBenchmark;
import se.vermiculus.benchmark.jmh.protobuf.ProtobufSerDeBenchmark;
import se.vermiculus.benchmark.jmh.vasp.VaspSerDeBenchmark;
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
public class JavaSerDeBenchmark {

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
    public void setup() {
        EnvironmentUtil.printEnvironment();
    }

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        final AvroSerDeBenchmark.BenchmarkState avroState = new AvroSerDeBenchmark.BenchmarkState();
        final ProtobufSerDeBenchmark.BenchmarkState protobufState = new ProtobufSerDeBenchmark.BenchmarkState();
        final VaspSerDeBenchmark.BenchmarkState vaspState = new VaspSerDeBenchmark.BenchmarkState();

        final AvroSerDeBenchmark avroSerDeBenchmark = new AvroSerDeBenchmark();
        final ProtobufSerDeBenchmark protobufSerDeBenchmark = new ProtobufSerDeBenchmark();
        final VaspSerDeBenchmark vaspSerDeBenchmark = new VaspSerDeBenchmark();
    }

    @Benchmark
    public void avroSerialization(BenchmarkState state, Blackhole blackhole) throws IOException {
        state.avroSerDeBenchmark.avroSerialization(state.avroState, blackhole);
    }

    @Benchmark
    public void avroDeserialization(BenchmarkState state, Blackhole blackhole) throws IOException {
        state.avroSerDeBenchmark.avroDeserialization(state.avroState, blackhole);
    }

    @Benchmark
    public void protobufSerialization(BenchmarkState state, Blackhole blackhole) throws IOException {
        state.protobufSerDeBenchmark.protobufSerialization(state.protobufState, blackhole);
    }

    @Benchmark
    public void protobufDeserialization(BenchmarkState state, Blackhole blackhole) throws IOException {
        state.protobufSerDeBenchmark.protobufDeserialization(state.protobufState, blackhole);
    }

    @Benchmark
    public void vaspSerialization(BenchmarkState state, Blackhole blackhole) throws IOException {
        state.vaspSerDeBenchmark.vaspSerialization(state.vaspState, blackhole);
    }

    @Benchmark
    public void vaspDeserialization(BenchmarkState state, Blackhole blackhole) throws IOException {
        state.vaspSerDeBenchmark.vaspDeserialization(state.vaspState, blackhole);
    }
}