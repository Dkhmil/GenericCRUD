import java.util.List;

public interface GenericDao<T,ID> {

    void create(T t) ;

    T read(T t,ID id) ;

    T update(T t) ;

    void delete(T t,ID id);

    List<T> readAll(T t) ;
}
