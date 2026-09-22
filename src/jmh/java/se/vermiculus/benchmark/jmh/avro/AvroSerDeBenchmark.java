package se.vermiculus.benchmark.jmh.avro;

import se.vermiculus.benchmark.serialization.avro.AvroMapper;
import se.vermiculus.benchmark.serialization.model.avro.Order;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.openjdk.jmh.infra.Blackhole;
import se.vermiculus.benchmark.util.DatasetGenerator;

public class AvroSerDeBenchmark {

    private static final AvroMapper MAPPER = new AvroMapper();

    public static class BenchmarkState {
        public Order avroOrder = MAPPER.map(DatasetGenerator.createSingleOrder());
        public byte[] serializedAvroOrder;

        public BenchmarkState() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DatumWriter<Order> datumWriter = new SpecificDatumWriter<>(Order.class);
                Encoder encoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
                datumWriter.write(avroOrder, encoder);
                encoder.flush();
                serializedAvroOrder = byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void avroSerialization(BenchmarkState state, Blackhole blackhole) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DatumWriter<Order> datumWriter = new SpecificDatumWriter<>(Order.class);
        Encoder encoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
        datumWriter.write(state.avroOrder, encoder);
        encoder.flush();
        blackhole.consume(byteArrayOutputStream.toByteArray());
    }

    public void avroDeserialization(BenchmarkState state, Blackhole blackhole) throws IOException {
        DatumReader<Order> datumReader = new SpecificDatumReader<>(Order.class);
        Decoder decoder = DecoderFactory.get().binaryDecoder(state.serializedAvroOrder, null);
        blackhole.consume(datumReader.read(null, decoder));
    }
}