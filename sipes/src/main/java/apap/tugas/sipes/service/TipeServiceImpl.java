package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.repository.TipeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TipeServiceImpl implements TipeService{

    @Autowired
    TipeDb tipeDb;

    @Override
    public void addTipe(TipeModel tipe){
        tipeDb.save(tipe);
    }

    @Override
    public List<TipeModel> getTipeList(){
        return tipeDb.findAll();
    }

    @Override
    public TipeModel getTipeByIdTipe(BigInteger idTipe){
        try {
            return tipeDb.findById(idTipe).get();
        }
        catch(NoSuchElementException nullException){
            return null;
        }
    }


}