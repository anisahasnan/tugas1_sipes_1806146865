package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{

    @Autowired
    PesawatDb pesawatDb;

    @Override
    public void addPesawat(PesawatModel pesawat){

        String jenis = "";
        if(pesawat.getJenisPesawat().equals("Komersial")){
            jenis = "1";
        }
        else if(pesawat.getJenisPesawat().equals("Militer")){
            jenis = "2";
        }

        String tipe = "";
        if(pesawat.getTipe().getId() == 1){
            tipe = "BO";
        }
        else if(pesawat.getTipe().getId() == 2){
            tipe = "AT";
        }
        else if(pesawat.getTipe().getId() == 3){
            tipe = "AB";
        }
        else if(pesawat.getTipe().getId() == 4){
            tipe = "BB";
        }

        String tahunDibuatStr = "" + pesawat.getTanggalDibuat().getYear();
        StringBuilder tahunRev = new StringBuilder();
        tahunRev.append(tahunDibuatStr);
        tahunRev = tahunRev.reverse();
        String tahunRevStr = tahunRev.toString();

        int tahunDibuatInt = pesawat.getTanggalDibuat().getYear();
        String tahunPlusFour = "" + (tahunDibuatInt + 8);

        char randomChar = (char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1));
        String randomLetter = "" + randomChar;
        char randomChar2 = (char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1));
        randomLetter += randomChar2;

        String nomorSeri = jenis + tipe + tahunRevStr + tahunPlusFour + randomLetter;
        pesawat.setNomorSeri(nomorSeri);

        pesawatDb.save(pesawat);
    }

    @Override
    public List<PesawatModel> getPesawatList(){
        return pesawatDb.findAll();
    }

    @Override
    public PesawatModel getPesawatByIdPesawat(Long idPesawat){
        try {
            return pesawatDb.findById(idPesawat).get();
        }
        catch(NoSuchElementException nullException){
            return null;
        }
    }

    @Override
    public PesawatModel updatePesawat(PesawatModel pesawat){
        PesawatModel targetPesawat = pesawatDb.findById(pesawat.getId()).get();

        try{
            targetPesawat.setMaskapai(pesawat.getMaskapai());
            targetPesawat.setTanggalDibuat(pesawat.getTanggalDibuat());
            targetPesawat.setTempatDibuat(pesawat.getTempatDibuat());
            targetPesawat.setJenisPesawat(pesawat.getJenisPesawat());
            targetPesawat.setTipe(pesawat.getTipe());

            String jenis = "";
            if(pesawat.getJenisPesawat().equals("Komersial")){
                jenis = "1";
            }
            else if(pesawat.getJenisPesawat().equals("Militer")){
                jenis = "2";
            }

            String tipe = "";
            if(pesawat.getTipe().getId() == 1){
                tipe = "BO";
            }
            else if(pesawat.getTipe().getId() == 2){
                tipe = "AT";
            }
            else if(pesawat.getTipe().getId() == 3){
                tipe = "AB";
            }
            else if(pesawat.getTipe().getId() == 4){
                tipe = "BB";
            }

            String tahunDibuatStr = "" + pesawat.getTanggalDibuat().getYear();
            StringBuilder tahunRev = new StringBuilder();
            tahunRev.append(tahunDibuatStr);
            tahunRev = tahunRev.reverse();
            String tahunRevStr = tahunRev.toString();

            int tahunDibuatInt = pesawat.getTanggalDibuat().getYear();
            String tahunPlusFour = "" + (tahunDibuatInt + 8);

            char randomChar = (char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1));
            String randomLetter = "" + randomChar;
            char randomChar2 = (char)((int)'A'+Math.random()*((int)'Z'-(int)'A'+1));
            randomLetter += randomChar2;

            String nomorSeri = jenis + tipe + tahunRevStr + tahunPlusFour + randomLetter;
            targetPesawat.setNomorSeri(nomorSeri);

            pesawatDb.save(targetPesawat);
            return targetPesawat;
        }
        catch(NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deletePesawat(Long idPesawat){
        pesawatDb.deleteById(idPesawat);
        return ;
    }

    @Override
    public List<PesawatModel> getPesawatTuaList() {
        List<PesawatModel> listPesawat = pesawatDb.findAll();

        List<PesawatModel> listPesawatTua = new ArrayList<>();
        for(PesawatModel pesawat: listPesawat){
            Integer usia = Period.between(pesawat.getTanggalDibuat(), LocalDate.now()).getYears();
            if(usia >= 10){
                listPesawatTua.add(pesawat);
            }
        }
        return listPesawatTua;
    }

    @Override
    public List<Integer> getPesawatTuaUsiaList() {
        List<PesawatModel> listPesawat = pesawatDb.findAll();
        List<Integer> listUsia = new ArrayList<>();

        for(PesawatModel pesawat: listPesawat){
            Integer usia = Period.between(pesawat.getTanggalDibuat(), LocalDate.now()).getYears();
            if(usia >= 10){
                listUsia.add(usia);
            }
        }
        return listUsia;
    }

    @Override
    public List<Integer> getJumlahTeknisi() {
        List<PesawatModel> listPesawat = pesawatDb.findAll();
        List<Integer> listJumlahTeknisi = new ArrayList<>();

        for(PesawatModel pesawat: listPesawat){
            Integer jumlahTeknisi = pesawat.getListTeknisi().size();
            listJumlahTeknisi.add(jumlahTeknisi);
        }
        return listJumlahTeknisi;
    }
}