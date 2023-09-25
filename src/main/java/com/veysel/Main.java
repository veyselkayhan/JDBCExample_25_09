package com.veysel;

import com.veysel.controller.FutbolcuController;
import com.veysel.repository.FutbolcuRepository;
import com.veysel.repository.entity.Futbolcu;
import com.veysel.utility.ConnectionProvider;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    static Scanner sc= new Scanner(System.in);
    FutbolcuController futbolcuController= new FutbolcuController();

    FutbolcuRepository futbolcuRepository= new FutbolcuRepository();
    public static void main(String[] args) {


        FutbolcuRepository futbolcuRepository= new FutbolcuRepository();
//        Futbolcu futbolcu= new Futbolcu("Alperen","OrtaSaha",20,10L);
//        Futbolcu futbolcu1= new Futbolcu("Veysel","Kayhan",7,10L);

//        Futbolcu futbolcu1=new Futbolcu("Muhammet","Forvet",9,15000L,5L);
//
//        futbolcu1.setId(2L);
//        futbolcuRepository.update(futbolcu1);
//

//        Optional<Futbolcu>futbolcu=futbolcuRepository.findbyId(2L);
//        if (futbolcu.isPresent()){
//            System.out.println(futbolcu.get());
//        }else{
//            System.out.println("Boyle bir futbolcu bulunamadi");
//        }
//

        futbolcuRepository.delete(2L);






    }
    public void Menu() {
        int input =0;

        do{
            System.out.println("1-Futbolcu Olustur");
            System.out.println("2-Futbolcu Duzenle");
            System.out.println("3-Futbolcu Sil");
            System.out.println("4-Futbolcu Bul");
            System.out.println("5-Futbolculari Getir");
            System.out.println("0-Cikis");
            input=Integer.parseInt(sc.nextLine());

            switch (input){
                case 1:
                    

            }


        }while (input!=0);

    }

}