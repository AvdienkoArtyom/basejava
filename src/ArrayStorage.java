import java.util.Arrays;

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
        if (r != null && containsTheIndex(r) == -1 && size < storage.length) {
            storage[size] = r;
            size++;
        }
    }

    Resume get(String uuid) {
        int position = containsTheIndex(uuid);
        if (position > -1) {
            return storage[position];
        } else {
            return null;
        }
    }

    void delete(String uuid) {
        int position = containsTheIndex(uuid);
        if (position > -1) {
            System.arraycopy(storage, position + 1, storage, position, size - 1 - position);
            storage[size - 1] = null;
            size--;
        }
    }

    boolean update(Resume resume) {
        boolean result = false;
        if (storage != null) {
            int position = containsTheIndex(resume);
            if (position > -1) {
                storage[position] = resume;
               result = true;
            }
        }
        return result;
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

    int containsTheIndex(Resume resume) {
        int index = containsTheIndex(resume.uuid);
        return index;
    }

    int containsTheIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                index = i;
                break;
            }
        }
        return index;
    }

}
