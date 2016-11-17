package ${packageName}.data.local;

import com.j256.ormlite.dao.Dao;

import javax.inject.Inject;

public class EntityDao {

    private Dao<Entity, Integer> userDao;

    @Inject public EntityDao(Dao<Entity, Integer> userDao) {
        this.userDao = userDao;
    }

    public Dao<Entity, Integer> get() {
        return userDao;
    }
}
