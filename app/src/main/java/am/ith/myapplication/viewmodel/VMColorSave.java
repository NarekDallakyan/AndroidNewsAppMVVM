package am.ith.myapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import am.ith.myapplication.local.dao.ColorSaveDao;
import am.ith.myapplication.local.entity.SaveColorModel;
import am.ith.myapplication.local.roomDB.ColorRoomDatabase;

public class VMColorSave extends AndroidViewModel {
    private ColorSaveDao colorSaveDao;
    private List<SaveColorModel> listLiveData;

    public VMColorSave(@NonNull Application application) {
        super(application);
        ColorRoomDatabase colorRoomDatabase=ColorRoomDatabase.getInstance();
        colorSaveDao=colorRoomDatabase.colorSaveDao();
        listLiveData = colorSaveDao.getAllColors();
    }

    public void insert (SaveColorModel saveColorModel) {
        new insertAsyncTask(colorSaveDao).execute(saveColorModel);
        Log.i("saveAreeeeec","------>"+saveColorModel.getPosition());
    }
    private static class insertAsyncTask extends AsyncTask<SaveColorModel, Void, Void> {

        private ColorSaveDao mAsyncTaskDao;

        insertAsyncTask(ColorSaveDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final SaveColorModel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }

    }

    public List<SaveColorModel> getListLiveData() {
        return listLiveData;
    }
}
