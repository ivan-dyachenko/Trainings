package model;

import java.util.Collection;

public interface DataDao {

    Data get(Long id);

    Data save(Data data);

    Collection<Data> find(String text);

    void shutdown();

    void checkpoint();

}
