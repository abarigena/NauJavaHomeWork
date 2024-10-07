package ru.danil.NauJava.CRUD.Interface;

public interface CrudRepository<T,ID> {

    void create(T ent);

    T read(ID id);

    void update(T ent);

    void delete(ID id);

}
