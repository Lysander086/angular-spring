package com.example.counter.thirdParty.uuid;

/**
 * 饿汉模式
 */
public class GudyUuid {

    private static GudyUuid ourInstance = new GudyUuid();

    public static GudyUuid getInstance() {
        return ourInstance;
    }

    private GudyUuid() {
    }

    private SnowflakeIdWorker idWorker;

    public void init(long centerId, long workerId) {
        idWorker = new SnowflakeIdWorker(workerId, centerId);
    }

    public long getUUID() {
        return idWorker.nextId();
    }


}
