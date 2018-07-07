package am.ith.myapplication.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.LinkedList;
import java.util.List;

import am.ith.myapplication.local.entity.SaveColorModel;

@Dao
public interface ColorSaveDao {
    @Insert
    void insert(SaveColorModel saveColorModel);

    @Query("SELECT * FROM color_table")
    List<SaveColorModel> getAllColors();

}
