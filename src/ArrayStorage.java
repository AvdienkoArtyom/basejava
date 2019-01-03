import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for (int i = 0; i<storage.length; i++){
            if (storage[i]==null){
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid)
    {
        Resume resume = null;

        for (int  i = 0; i<storage.length; i++ ){
            if (storage[i]==null) continue;
            if (storage[i].uuid.equals(uuid)) resume = storage[i];
        }
        return resume;
    }

    void delete(String uuid) {
        for (int i = 0; i<storage.length; i++){
            if (storage[i]==null) continue;
            if (storage[i].uuid.equals(uuid)){
                storage[i] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        List<Resume> listResume = new ArrayList<>();
        for (int i = 0; i<storage.length; i++){
            if (storage[i]!=null){
                listResume.add(storage[i]);
            }
        }
        Resume[] resumes = listResume.toArray(new Resume[listResume.size()]);
        return resumes;
    }

    int size() {
        int count = 0;
        for (int i = 0; i<storage.length; i++){
            if (storage[i]!=null){
                count++;
            }
        }
        return count;
    }
}
