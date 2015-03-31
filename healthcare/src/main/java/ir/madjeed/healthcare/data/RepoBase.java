package ir.madjeed.healthcare.data;
import java.util.List;

public interface RepoBase<C, D> {

    public Class<C> getRepoEntityType();
    public int create(C instance);
    public int update(C instance);
    public int delete(C instance);
    public C getByID(D id);
    public List<C> getAll();

}

