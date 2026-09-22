package se.vermiculus.benchmark.jmh.avro;

import se.vermiculus.benchmark.serialization.Serializer;
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

public class AvroSerDeBenchmark implements Serializer {

    private static final AvroMapper MAPPER = new AvroMapper();

    private Order avroOrder;
    private byte[] serializedAvroOrder;

    @Override
    public void init(se.vermiculus.benchmark.messages.Order javaOrder) {
        this.avroOrder = MAPPER.map(javaOrder);
        this.serializedAvroOrder = this.serialize();
    }

    @Override
    public byte[] serialize() {
        try {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DatumWriter<Order> datumWriter = new SpecificDatumWriter<>(Order.class);
        Encoder encoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null);
        datumWriter.write(avroOrder, encoder);
        encoder.flush();
        return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object deserialize() {
     try  {
        DatumReader<Order> datumReader = new SpecificDatumReader<>(Order.class);
        Decoder decoder = DecoderFactory.get().binaryDecoder(this.serializedAvroOrder, null);
        return datumReader.read(null, decoder);
    } catch (IOException e) {
         throw new RuntimeException(e);
    }
    }
}