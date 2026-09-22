package se.vermiculus.benchmark.serialization.protobuf;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProtobufMapperTest {

    private final ProtobufMapper protobufMapper = new ProtobufMapper();

   /* @Test
    public void testMap() {
        SocialMediaPostJava pojoPost = DataGenerator.generateSocialMediaPost();
        SocialMediaPost protoPost = protobufMapper.map(pojoPost);

        assertEquals(pojoPost.postId(), protoPost.getPostId());
        assertEquals(pojoPost.authorId(), protoPost.getAuthorId());
        assertEquals(pojoPost.content(), protoPost.getContent());
        assertEquals(pojoPost.timestamp(), protoPost.getTimestamp());
    }*/
}