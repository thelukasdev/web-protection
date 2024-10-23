package Output;

import java.util.concurrent.atomic.AtomicLong;

public class DdosProtection {

    private static final long MAX_REQUESTS_PER_SECOND = 100; // Anpassen Sie diesen Wert entsprechend

    private static final AtomicLong requestCount = new AtomicLong(0);

    public static boolean isAllowed() {
        long currentCount = requestCount.incrementAndGet();
        if (currentCount > MAX_REQUESTS_PER_SECOND) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 150; i++) {
            if (isAllowed()) {
                System.out.println("Anfrage " + (i + 1) + " bearbeitet.");
            } else {
                System.out.println("Anfrage " + (i + 1) + " abgelehnt (DDoS-Schutz).");
            }
        }
    }
}