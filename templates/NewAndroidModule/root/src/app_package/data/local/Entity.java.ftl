package ${packageName}.data.local;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = Entity.TABLE_NAME)
public class Entity {

    public static final String TABLE_NAME = "entity";

    @DatabaseField private Integer id;

    public Entity() {
    }
}
