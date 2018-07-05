package am.ith.myapplication.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import am.ith.myapplication.local.entity.SaveColorModel;

@Dao
public interface ColorSaveDao {
    @Insert
    void insert(SaveColorModel saveColorModel);
    @Query("SELECT position FROM color_table")
    long getPosition();
}
