package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.TeknisiModel;
import apap.tugas.sipes.service.TeknisiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeknisiController{
    @Qualifier("teknisiServiceImpl")
    @Autowired
    private TeknisiService teknisiService;

}