package se.vermiculus.benchmark.serialization;

import se.vermiculus.benchmark.messages.Order;

public interface Serializer {

    void init(Order javaOrder);

    byte[] serialize();

    Object deserialize();

}
