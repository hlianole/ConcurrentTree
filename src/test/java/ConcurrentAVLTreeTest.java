import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class ConcurrentAVLTreeTest {

    private TestingTree tree;

    @BeforeEach
    public void setUp() {
        tree = new TestingTree();
    }

    @Test
    void testGetFromAnEmptyTree() {
        Optional<byte[]> result = tree.get("Non-existing key".getBytes());
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetAndPutSingleElement() {
        byte[] key = "key".getBytes();
        byte[] value = "value".getBytes();

        tree.put(key, value);
        Optional<byte[]> result = tree.get(key);

        assertTrue(result.isPresent());
        assertArrayEquals(value, result.get());
    }

    @Test
    void testGetFromNotEmptyTreeWithNonExistingKey() {
        byte[] key = "key".getBytes();
        byte[] value = "value".getBytes();
        byte[] nonExistingKey = "Non-existing key".getBytes();

        tree.put(key, value);
        Optional<byte[]> result = tree.get(nonExistingKey);

        assertEquals(Optional.empty(), result);
    }

    @Test
    void testValueUpdate() {
        byte[] key = "key".getBytes();
        byte[] value = "value".getBytes();
        byte[] newValue = "New value".getBytes();

        tree.put(key, value);
        tree.put(key, newValue);
        Optional<byte[]> result = tree.get(key);

        assertTrue(result.isPresent());
        assertArrayEquals(newValue, result.get());
    }

    @Test
    void testMultipleInsertions() {
        for (int i = 0; i < 128; i++) {
            byte[] key = ("key" + i).getBytes();
            byte[] value = ("value" + i).getBytes();
            tree.put(key, value);
        }

        for (int i = 0; i < 128; i++) {
            byte[] key = ("key" + i).getBytes();
            byte[] expectedValue = ("value" + i).getBytes();

            Optional<byte[]> result = tree.get(key);

            assertTrue(result.isPresent());
            assertArrayEquals(expectedValue, result.get());
        }
        assertTrue(tree.getRightDepth() < 9);
    }

    @Test
    void testEmptyArrayInsertion() {
        byte[] key = new byte[0];
        byte[] value = new byte[0];

        tree.put(key, value);
        Optional<byte[]> result = tree.get(key);

        assertTrue(result.isPresent());
        assertArrayEquals(value, result.get());
    }

    @RepeatedTest(10)
    void testConcurrency() throws InterruptedException {
        int threadCount = 20;
        int iterations = 512;

        for (int i = 0; i < iterations; i++) {
            String key = "key" + i;
            String value = "value" + i;
            tree.put(key.getBytes(), value.getBytes());
        }

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);
        AtomicInteger errors = new AtomicInteger(0);

        for (int t = 0; t < threadCount; t++) {
            final int threadId = t;
            executor.submit(() -> {
                try {
                    if (threadId < threadCount / 2) {
                        for (int i = 0; i < iterations; i++) {
                            String key = "key" + i;
                            String value = "value" + i;
                            Optional<byte[]> result = tree.get(key.getBytes());

                            assertTrue(result.isPresent());
                            assertArrayEquals(value.getBytes(), result.get());
                        }
                    } else {
                        for (int i = 0; i < iterations; i++) {
                            String key = "new key" + i;
                            String value = "new value" + i;
                            tree.put(key.getBytes(), value.getBytes());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    errors.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        assertTrue(latch.await(15, TimeUnit.SECONDS));
        assertEquals(0, errors.get());
        executor.shutdown();
    }

}
