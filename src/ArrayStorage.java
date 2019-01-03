import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size;


    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume r) {
        if (r != null) {
            boolean b = false;
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(r.uuid)) {
                    b = true;
                }
            }
            if (!b) {
                storage[size] = r;
                size++;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
                System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
                storage[size - 1] = null;
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return size;
    }
}
