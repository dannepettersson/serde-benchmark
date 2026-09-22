package se.vermiculus.benchmark.serialization.avro;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvroMapperTest {

    private final AvroMapper avroMapper = new AvroMapper();

 /*   @Test
    public void testMap() {
        SocialMediaPostJava pojoPost = DataGenerator.generateSocialMediaPost();
        SocialMediaPost avroPost = avroMapper.map(pojoPost);

        assertEquals(pojoPost.postId(), avroPost.getPostId());
        assertEquals(pojoPost.authorId(), avroPost.getAuthorId().toString());
        assertEquals(pojoPost.content(), avroPost.getContent().toString());
        assertEquals(pojoPost.timestamp(), avroPost.getTimestamp());
    }*/
}