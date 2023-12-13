package coding.sample.sharedqueue;

public class SharedQueue {
    class Element {
        String value;
        Element nextElement;


        Element(String value) {
            this.value = value;
            nextElement = null;
        }
    }

    private Element head = new Element(null);
    private Element last = head;
    private int waitingThreadCount;
    private Object putLock = new Object();
    private Object takeLock = new Object();

    public void put(String value) {
        synchronized (putLock) {
            if (value == null) {
                throw new NullPointerException();
            }
            Element newElement = new Element(value);
            last.nextElement = newElement;
            last = newElement;
            if (waitingThreadCount > 0) {
                putLock.notify();
            }
        }
    }

    public String take() {
        String value;
        synchronized (takeLock) {
            while (isEmpty()) {
                try {
                    synchronized (putLock) {
                        waitingThreadCount++;
                        putLock.wait();
                        waitingThreadCount--;
                    }
                } catch (InterruptedException e) {
                    //do nothing
                }

            }
            Element firstElement = head.nextElement;
            value = firstElement.value;
            firstElement.value = null;
            head = firstElement;
        }
        return value;
    }


    private boolean isEmpty() {
        return head.nextElement == null;
    }
}
