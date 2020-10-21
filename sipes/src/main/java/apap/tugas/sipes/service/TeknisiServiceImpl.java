package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.repository.TeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TeknisiServiceImpl implements TeknisiService{

    @Autowired
    TeknisiDb teknisiDb;

    @Override
    public void addTeknisi(TeknisiModel teknisi){
        teknisiDb.save(teknisi);
    }

    @Override
    public List<TeknisiModel> getTeknisiList(){
        return teknisiDb.findAll();
    }

    @Override
    public TeknisiModel getTeknisiByIdTeknisi(BigInteger idTeknisi){
        try {
            return teknisiDb.findById(idTeknisi).get();
        }
        catch(NoSuchElementException nullException){
            return null;
        }
    }


}