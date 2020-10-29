package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.service.PenerbanganService;
import apap.tugas.sipes.service.PesawatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PenerbanganController{
    @Qualifier("penerbanganServiceImpl")
    @Autowired
    private PenerbanganService penerbanganService;

    @Autowired
    private PesawatService pesawatService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
                false, 10));
    }

    @GetMapping("/")
    private String home(Model model){
        return "home";
    }

    @GetMapping("/penerbangan")
    public String viewAllPenerbangan(Model model){
        List<PenerbanganModel> listPenerbangan = penerbanganService.getPenerbanganList();
        model.addAttribute("listPenerbangan", listPenerbangan);
        return "view-all-penerbangan";
    }

    @GetMapping("/penerbangan/tambah")
    public String addPenerbanganFormPage(Model model){
        model.addAttribute("penerbangan", new PenerbanganModel());
        return "form-add-penerbangan";
    }

    @PostMapping("/penerbangan/tambah")
    public String addPenerbanganSubmit(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model){

        penerbanganService.addPenerbangan(penerbangan);
        model.addAttribute("nomorPenerbangan", penerbangan.getNomorPenerbangan());
        return "add-penerbangan";
    }

    @GetMapping("/penerbangan/{idPenerbangan}")
    public String viewDetailPenerbangan(
            @PathVariable Long idPenerbangan,
            Model model){

        if(idPenerbangan != null){
            PenerbanganModel penerbangan = penerbanganService.getPenerbanganByIdPenerbangan(idPenerbangan);
            if (penerbangan != null) {
                PesawatModel pesawat = penerbangan.getPesawat();
                boolean hasPesawat = (pesawat != null);

                model.addAttribute("hasPesawat", hasPesawat);
                model.addAttribute("penerbangan", penerbangan);
                model.addAttribute("pesawat", pesawat);

                return "view-penerbangan";
            }
            else{
                return "penerbangan-not-found";
            }
        }
        else{
            return "penerbangan-not-found";
        }
    }

    @GetMapping("/penerbangan/ubah/{idPenerbangan}")
    public String changePenerbanganFormPage(
            @PathVariable Long idPenerbangan,
            Model model){

        PenerbanganModel penerbangan = penerbanganService.getPenerbanganByIdPenerbangan(idPenerbangan);

        if(penerbangan != null){
            model.addAttribute("penerbangan", penerbangan);
            return "form-update-penerbangan";
        }
        else{
            return "penerbangan-not-found";
        }
    }

    @PostMapping("/penerbangan/ubah")
    public String changePenerbanganFormSubmit(
            @ModelAttribute PenerbanganModel penerbangan,
            Model model){

        if(penerbangan != null){
            PenerbanganModel penerbanganUpdated = penerbanganService.updatePenerbangan(penerbangan);
            model.addAttribute("penerbangan", penerbangan);
            return "update-penerbangan";
        }
        else{
            return "penerbangan-not-found";
        }
    }

    @GetMapping("/penerbangan/hapus/{idPenerbangan}")
    public String deletePenerbangan(
            @PathVariable Long idPenerbangan,
            Model model){

        PenerbanganModel penerbangan = penerbanganService.getPenerbanganByIdPenerbangan(idPenerbangan);

        if(penerbangan != null){
            penerbanganService.deletePenerbangan(idPenerbangan);
            model.addAttribute("penerbangan", penerbangan);
            return "delete-penerbangan";
        }
        else{
            return "penerbangan-not-found";
        }
    }
}