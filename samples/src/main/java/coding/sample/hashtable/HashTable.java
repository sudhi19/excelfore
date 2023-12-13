package coding.sample.hashtable;

public class HashTable {

    public static final int DEFAULT_BUCKET_SIZE = 16;
    public static final double RESIZE_THRESHOLD = 0.75;
    public static final int RESIZE_MULTIPLIER = 2;

    static private class Entry {
        Object key;
        Object value;
        Entry nextEntry;
    }

    private Entry[] table;

    private int size;

    public HashTable() {
        table = new Entry[DEFAULT_BUCKET_SIZE];
    }

    public HashTable(int initialSize) {
        table = new Entry[initialSize];
    }

    public void put(Object key, Object value) {
        int bucketNumber = hash(key);
        Entry entry = table[bucketNumber];
        while (entry != null) {

            if (entry.key.equals(key))
                break;
            entry = entry.nextEntry;
        }
        if (entry != null) {

            entry.value = value;
        } else {

            if (size >= RESIZE_THRESHOLD * table.length) {
                resize();
            }
            Entry newEntry = new Entry();
            newEntry.key = key;
            newEntry.value = value;
            newEntry.nextEntry = table[bucketNumber];
            table[bucketNumber] = newEntry;
            size++;
        }
    }

    public Object get(Object key) {
        int bucketNumber = hash(key);
        Entry entry = table[bucketNumber];
        while (entry != null) {

            if (entry.key.equals(key))
                return entry.value;
            entry = entry.nextEntry;
        }

        return null;
    }

    public void remove(Object key) {
        int bucketNumber = hash(key);
        if (table[bucketNumber] == null) {
            return;
        }
        if (table[bucketNumber].key.equals(key)) {
            table[bucketNumber] = table[bucketNumber].nextEntry;
            size--;
            return;
        }

        Entry previousEntry = table[bucketNumber];

        Entry currentEntry = previousEntry.nextEntry;

        while (currentEntry != null && !currentEntry.key.equals(key)) {
            currentEntry = currentEntry.nextEntry;
            previousEntry = currentEntry;
        }
        if (currentEntry != null) {
            previousEntry.nextEntry = currentEntry.nextEntry;
            size--;
        }
    }

    public boolean containsKey(Object key) {
        int bucketNumber = hash(key);
        Entry entry = table[bucketNumber];
        while (entry != null) {

            if (entry.key.equals(key))
                return true;
            entry = entry.nextEntry;
        }
        return false;
    }

    public int size() {
        return size;
    }

    private int hash(Object key) {
        return (Math.abs(key.hashCode())) % table.length;
    }

    private void resize() {
        Entry[] tempTable = new Entry[table.length * RESIZE_MULTIPLIER];
        for (int i = 0; i < table.length; i++) {
            Entry entry = table[i];
            while (entry != null) {
                Entry next = entry.nextEntry;
                int hash = (Math.abs(entry.key.hashCode())) % tempTable.length;
                entry.nextEntry = tempTable[hash];
                tempTable[hash] = entry;
                entry = next;
            }
        }
        table = tempTable;
    }

}
