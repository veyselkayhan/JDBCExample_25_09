package com.veysel.repository;

import com.veysel.repository.entity.Futbolcu;
import com.veysel.utility.ConnectionProvider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FutbolcuRepository implements ICrud<Futbolcu> {

    private PreparedStatement preparedStatement;
    private String sql="";
    private ConnectionProvider connectionProvider;

    public FutbolcuRepository() {
        this.connectionProvider=new ConnectionProvider();
    }

    @Override
    public void save(Futbolcu futbolcu) {
    sql="INSERT INTO futbolcular(isim, mevki,formano,deger,takim_id) VALUES (?,?,?,?,?)";
    try {
        preparedStatement= connectionProvider.getPreparedStatement(sql);
        preparedStatement.setString(1,futbolcu.getIsim());
        preparedStatement.setString(2,futbolcu.getMevki());
        preparedStatement.setInt(3,futbolcu.getFormaNo());
        preparedStatement.setLong(4,futbolcu.getDeger());
        preparedStatement.setLong(5,futbolcu.getTakimId());
        preparedStatement.executeUpdate();
        connectionProvider.closeConnection();

    }catch (Exception e){
        e.printStackTrace();
    }
    }

    @Override
    public void update(Futbolcu futbolcu) {

        sql="UPDATE futbolcular SET isim=? , mevki=?,formano=?,deger=?,takim_id=? WHERE id=?";
        try {
            preparedStatement= connectionProvider.getPreparedStatement(sql);
            preparedStatement.setString(1,futbolcu.getIsim());
            preparedStatement.setString(2,futbolcu.getMevki());
            preparedStatement.setInt(3,futbolcu.getFormaNo());
            preparedStatement.setLong(4,futbolcu.getDeger());
            preparedStatement.setLong(5,futbolcu.getTakimId());
            preparedStatement.setLong(6,futbolcu.getId());
            int etkilenenVeriler=preparedStatement.executeUpdate();
            if(etkilenenVeriler>0){
                System.out.println("Guncelleme Basarili!");
            }else {
                System.out.println("Guncelleme Basarisiz");
            }
            connectionProvider.closeConnection();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        sql="DELETE FROM futbolcular WHERE id=?";
        PreparedStatement preparedStatement=connectionProvider.getPreparedStatement(sql);
        try {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            connectionProvider.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Futbolcu> findAll() {
        sql="SELECT * FROM futbolcular";
        Futbolcu futbolcu=null;
        List<Futbolcu>futbolcular=new ArrayList<>();
        PreparedStatement preparedStatement= connectionProvider.getPreparedStatement(sql);
        try {
            Optional<ResultSet> resultSet = connectionProvider.getData(preparedStatement);
            while (resultSet.get().next()){
                String isim=resultSet.get().getString(2);
                String mevki = resultSet.get().getString("mevki");
                int formaNo=resultSet.get().getInt("formano");
                Long deger=resultSet.get().getLong("deger");
                Long takimId=resultSet.get().getLong("takim_id");
                Long id=resultSet.get().getLong("id");
                futbolcu=new Futbolcu(id,isim,mevki,formaNo,deger,takimId);
                futbolcular.add(futbolcu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return futbolcular;
    }


    @Override
    public Optional<Futbolcu> findbyId(long id) {
        sql="SELECT * FROM futbolcular WHERE id=?";
        Futbolcu futbolcu=null;
        PreparedStatement preparedStatement=connectionProvider.getPreparedStatement(sql);
        try {
            preparedStatement.setLong(1,id);
            Optional<ResultSet> resultSet = connectionProvider.getData(preparedStatement);
            while (resultSet.get().next()){
                //optimal kosullarda findById metodunda sadece 1 veri dönmesini gerekiyor.
                //ancak id'nin olasi tekrar ettigi bir senaryoda bu kisim bize fazladan veri dönecek
                //kurgu hatasi da tespit edebiliriz.
                String isim=resultSet.get().getString(2);
                String mevki = resultSet.get().getString("mevki");
                int formaNo=resultSet.get().getInt("formano");
                Long deger=resultSet.get().getLong("deger");
                Long takimId=resultSet.get().getLong("takim_id");
                futbolcu=new Futbolcu(id,isim,mevki,formaNo,deger,takimId);
                return Optional.of(futbolcu);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
