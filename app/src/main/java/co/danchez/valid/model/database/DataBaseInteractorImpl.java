package co.danchez.valid.model.database;

import android.content.Context;

import co.danchez.valid.presenter.TopArtistsPresenter;
import co.danchez.valid.presenter.TopTracksPresenter;

public class DataBaseInteractorImpl implements DataBaseInteractor{

    private DataBaseRepository dataBaseRepository;

    public DataBaseInteractorImpl(TopTracksPresenter topTracksPresenter) {
        this.dataBaseRepository = new DBRepositoryImpl(topTracksPresenter);
    }

    @Override
    public void saveDataInDB(String data, int position, Context context) {
        dataBaseRepository.saveDataInDB(data, position, context);
    }

    @Override
    public void getDataFromDB(Context context, int position) {
        dataBaseRepository.getDataFromDB(context, position);
    }
}
