# Serialization Comparison Benchmark

This project provides a comprehensive benchmark for comparing the performance of different serialization frameworks using Java. It measures the serialization and deserialization speed of the following formats:

*   **Protocol Buffers (Protobuf):** Google's binary serialization format.
*   **Avro:** Apache Avro, with its binary encodings.
*   **VASP:** VeriPrimer's proprietary binary serialization protocol


## Building the Project

To build the project, run the following command:

```bash
./gradlew clean build
```

This will compile the code, run the unit tests.

## Running the Benchmarks

### Performance Benchmarks

The project uses the Java Microbenchmark Harness (JMH) to provide accurate and reliable performance measurements. To run the benchmarks, execute the following command:

```bash
./gradlew jmh -PjavaVersion=25 -Pjmh.threads=1
```

The results will be printed to the console, showing the average time for each serialization and deserialization operation.

### JMH Benchmark Results

Here is the results from running the JMH benchmarks on JDK on Dans office desktop.

```
Benchmark                                   (javaRuntime)     (javaVendor)  (jmhThreads)  (jvmMemory)  (osArchitecture)  (osName)      (osVersion)     (processors)  Mode  Cnt   Score     Error    Units
JavaSerDeBenchmark.avroDeserialization       25.0.4+7-LTS  Eclipse_Temurin        1           2g            amd64         Linux      7.0.0-30-generic       32       avgt   20  3933.435  ± 23.841  ns/op
JavaSerDeBenchmark.avroSerialization         25.0.4+7-LTS  Eclipse_Temurin        1           2g            amd64         Linux      7.0.0-30-generic       32       avgt   20  1893.500  ± 30.966  ns/op
JavaSerDeBenchmark.protobufDeserialization   25.0.4+7-LTS  Eclipse_Temurin        1           2g            amd64         Linux      7.0.0-30-generic       32       avgt   20  1460.166  ± 20.905  ns/op
JavaSerDeBenchmark.protobufSerialization     25.0.4+7-LTS  Eclipse_Temurin        1           2g            amd64         Linux      7.0.0-30-generic       32       avgt   20   789.417  ±  5.481  ns/op
JavaSerDeBenchmark.vaspDeserialization       25.0.4+7-LTS  Eclipse_Temurin        1           2g            amd64         Linux      7.0.0-30-generic       32       avgt   20  1133.840  ±  3.321  ns/op
JavaSerDeBenchmark.vaspSerialization         25.0.4+7-LTS  Eclipse_Temurin        1           2g            amd64         Linux      7.0.0-30-generic       32       avgt   20  1470.907  ±  5.375  ns/op
```

### JMH Report

The `jmhReport` task generates a detailed HTML report of the JMH benchmark results. To generate this report, run:

```bash
./gradlew jmhReport -PjavaVersion=25 -Pjmh.threads=1
```

You can find the generated report at `build/reports/jmh/index.html`. This report provides a more interactive and detailed view of the benchmark results, including charts and statistical analysis.
