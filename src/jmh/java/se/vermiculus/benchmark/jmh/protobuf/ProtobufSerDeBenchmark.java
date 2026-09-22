package se.vermiculus.benchmark.jmh.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import se.vermiculus.benchmark.serialization.Serializer;
import se.vermiculus.benchmark.serialization.model.proto.Order;
import se.vermiculus.benchmark.serialization.protobuf.ProtobufMapper;

import java.io.IOException;
import org.openjdk.jmh.infra.Blackhole;
import se.vermiculus.benchmark.util.DatasetGenerator;

public class ProtobufSerDeBenchmark implements Serializer {

    private static final ProtobufMapper MAPPER = new ProtobufMapper();

    private Order protoOrder;
    private byte[] serializedProtoOrder;

    @Override
    public void init(se.vermiculus.benchmark.messages.Order javaOrder) {
        this.protoOrder = MAPPER.map(javaOrder);
        this.serializedProtoOrder = this.serialize();
    }

    @Override
    public byte[] serialize() {
            return protoOrder.toByteArray();
    }

    @Override
    public Object deserialize() {
        try {
            return Order.parseFrom(this.serializedProtoOrder);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}