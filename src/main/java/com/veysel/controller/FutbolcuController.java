package com.veysel.controller;

import com.veysel.repository.FutbolcuRepository;
import com.veysel.repository.ICrud;
import com.veysel.repository.entity.Futbolcu;
import com.veysel.service.FutbolcuService;

import java.util.List;
import java.util.Optional;

public class FutbolcuController implements ICrud<Futbolcu> {

    FutbolcuRepository futbolcuRepository;
    FutbolcuService futbolcuService;

    public FutbolcuController(){
        this.futbolcuService=new FutbolcuService();
    }
    @Override
    public void save(Futbolcu futbolcu) {
        futbolcuService.save(futbolcu);
    }

    @Override
    public void update(Futbolcu futbolcu) {
    futbolcuService.update(futbolcu);
    }

    @Override
    public void delete(Long id) {
    futbolcuService.delete(id);
    }

    @Override
    public List<Futbolcu> findAll() {
        List<Futbolcu>futbolcular=futbolcuRepository.findAll();
        if (futbolcular.size()==0){
            System.out.println("Db veri bulunmamakta");
        }
        return futbolcular;
    }

    @Override
    public Optional<Futbolcu> findbyId(long id) {
        Optional<Futbolcu>futbolcu = futbolcuRepository.findbyId(id);
        if(futbolcu.isEmpty()){
            System.out.println("Boyle bir futbolcu bulunamadi");
        }
        return futbolcu;
    }
}
