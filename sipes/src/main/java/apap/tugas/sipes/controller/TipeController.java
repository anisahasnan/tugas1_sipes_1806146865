package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TipeController{
    @Qualifier("tipeServiceImpl")
    @Autowired
    private TipeService tipeService;

}