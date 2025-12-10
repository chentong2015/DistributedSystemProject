package snowflake;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Generate unique IDs using the Twitter Snowflake algorithm.
 * Snowflake IDs are 64 bit positive longs composed of:
 * - 41 bits time stamp
 * - 10 bits machine id
 * - 12 bits sequence number
 *
 * https://github.com/twitter/snowflake
 * http://www.apache.org/licenses/LICENSE-2.0
 */
public class SnowflakeIdGenerator {

    private final long datacenterIdBits = 10L;
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private final long sequenceBits = 12L;
    private final long datacenterIdShift = sequenceBits;
    private final long timestampLeftShift = sequenceBits + datacenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private final long twepoch = 1288834974657L;
    private long datacenterId;

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    public SnowflakeIdGenerator(long datacenterId)  {
        if(datacenterId <= 0) {
            System.out.println("Data center ID cannot be less or equal to O, got value: " + datacenterId);
            throw new IllegalArgumentException("Data center ID cannot be less or equal to O, got value: "+datacenterId);
        }
        this.datacenterId = datacenterId;

        if (this.datacenterId > maxDatacenterId){
            System.out.println("SNOWFLAKE: datacenterId > maxDatacenterId; using random datacenter ID");
            Random rnd = new Random();
            this.datacenterId = rnd.nextInt((int)maxDatacenterId) + 1L;
        }
        System.out.println("SNOWFLAKE: initialised with datacenter ID " + this.datacenterId);
    }

    // Return the next unique id for the type with the given name
    // using the generator's id generation strategy.
    public synchronized long getId() throws InterruptedException {
        long timestamp = System.currentTimeMillis();
        if(timestamp < lastTimestamp) {
            long durationToWaitToRegulateClock = lastTimestamp - timestamp;
            System.out.println("Clock moved backwards. Refusing to generate id for milliseconds: " + durationToWaitToRegulateClock);
            TimeUnit.MILLISECONDS.sleep(durationToWaitToRegulateClock);
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;
        long id = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | sequence;
        if(id < 0) {
            System.out.println("ID is smaller than 0: " + id);
        }
        return id;
    }

    private long tilNextMillis(long lastTimestamp){
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}