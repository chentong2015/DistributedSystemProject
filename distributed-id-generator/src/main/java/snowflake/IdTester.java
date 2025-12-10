package snowflake;

public class IdTester {

    public static void main(String[] args) throws InterruptedException {
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1);
        System.out.println(idGenerator.getId());
        System.out.println(idGenerator.getId());
        System.out.println(idGenerator.getId());
        // 1998775344101855232
        // 1998775344101855233
        // 1998775344101855234
    }
}
