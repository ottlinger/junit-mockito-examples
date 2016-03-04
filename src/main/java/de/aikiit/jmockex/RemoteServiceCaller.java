package de.aikiit.jmockex;

public class RemoteServiceCaller {

    public static final class RemoteService {
        public String convert(long value) {
            return "alonglongway:" + value;
        }
    }

    private RemoteService remoteService = new RemoteService();

    public String serviceCall(long timestamp) {
        return remoteService.convert(timestamp);
    }

}
