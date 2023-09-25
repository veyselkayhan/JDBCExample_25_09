package com.veysel.service;

import com.veysel.repository.FutbolcuRepository;
import com.veysel.repository.ICrud;
import com.veysel.repository.entity.Futbolcu;

import java.util.List;
import java.util.Optional;

public class FutbolcuService implements ICrud<Futbolcu> {

    FutbolcuRepository futbolcuRepository;

    public FutbolcuService(){
        this.futbolcuRepository=new FutbolcuRepository();
    }
    @Override
    public void save(Futbolcu futbolcu) {
        futbolcuRepository.save(futbolcu);
    }

    @Override
    public void update(Futbolcu futbolcu) {
        if(findbyId(futbolcu.getId()).isPresent()){
            futbolcuRepository.update(futbolcu);
        }
    }

    @Override
    public void delete(Long id) {
        if(findbyId(id).isPresent()){
            futbolcuRepository.delete(id);
        }
    }

    @Override
    public List<Futbolcu> findAll() {
        List<Futbolcu>futbolcular = futbolcuRepository.findAll();
        if(futbolcular.size()==0){
            System.out.println("DataBasede Veri Bulunmamaktadir");
        }
        return futbolcular;
    }

    @Override
    public Optional<Futbolcu> findbyId(long id) {
        Optional<Futbolcu> futbolcu= futbolcuRepository.findbyId(id);
        if(futbolcu.isEmpty()){
            System.out.println("Boyle Bir Futbolcu Bulunamadi...");
        }
        return futbolcu;
    }
}
