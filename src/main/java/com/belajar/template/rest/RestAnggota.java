package com.belajar.template.rest;

import com.belajar.template.model.ModelAnggota;
import com.belajar.template.service.ServiceAnggota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/anggota")
public class RestAnggota {

    @Autowired
    ServiceAnggota serviceAnggota;

    @GetMapping("/all")
    public List<ModelAnggota> getAll() {
        return serviceAnggota.findAll();
    }

    @RequestMapping(value = "/cek/{username}", method = RequestMethod.GET)
    public String cekUsernameIsAvailable(@PathVariable String username) {
        List<ModelAnggota> modelAnggotaList = serviceAnggota.findAll();
        for (ModelAnggota ma: modelAnggotaList) {
            if (ma.getUsername().equalsIgnoreCase(username)){
                return "0";
            }
        }
        return "1";
    }

    @RequestMapping(value = "/cekActive/{username}", method = RequestMethod.GET)
    public String cekUsernameIsActive(@PathVariable String username) {
        List<ModelAnggota> modelAnggotaList = serviceAnggota.findAll();
        for (ModelAnggota ma: modelAnggotaList) {
            if (ma.getUsername().equalsIgnoreCase(username)){
                return ma.getAktif()+"";
            }
        }
        return "";
    }
    @RequestMapping(value = "/find_name_by_username/{username}", method = RequestMethod.GET)
    public String findUserById(@PathVariable String username) {
        List<ModelAnggota> modelAnggotaList = serviceAnggota.findAll();
        for (ModelAnggota ma: modelAnggotaList) {
            if (ma.getUsername().equalsIgnoreCase(username)){
                return ma.getNama()+"";
            }
        }
        return "";
    }

    @RequestMapping(value = "/insert/{nama}/{username}/{password}/{jabatan}", method = RequestMethod.GET)
    public List<ModelAnggota> setNew(@PathVariable String nama, @PathVariable String username, @PathVariable String password, @PathVariable int jabatan) {
        ModelAnggota anggotaBaru = new ModelAnggota();
        anggotaBaru.setNama(nama);
        anggotaBaru.setIdJabatan(jabatan);
        anggotaBaru.setUsername(username);
        anggotaBaru.setPassword(password);
        anggotaBaru.setAktif(1);
        serviceAnggota.save(anggotaBaru);
        return serviceAnggota.findAll();
    }

    //{"idJabatan":"1","nama":"lohya123","username":"lohya123","password":"lohya123", "aktif":"1"}
    //Content-Type : application/json; charset=utf-8
    //http://localhost:8080/rest/anggota/addNew
    @RequestMapping(value = "/addNew", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addNew(@RequestBody ModelAnggota modelAnggota) {
        System.out.println("body : " + modelAnggota.toString());
        serviceAnggota.save(modelAnggota);
        return "berhasil";
    }
}
