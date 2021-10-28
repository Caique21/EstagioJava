/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Veiculo
{
    private int codigo;
    private String placa;
    private Modelo modelo;
    private String chassi;
    private int ano;
    private String cor;
    private String descricao;

    public Veiculo()
    {
    }

    public Veiculo(int codigo)
    {
        this.codigo = codigo;
        get();
    }

    public Veiculo(int codigo, String placa,  Modelo modelo, String chassi, int ano, String cor, String descricao)
    {
        this.codigo = codigo;
        this.placa = placa;
        this.modelo = modelo;
        this.chassi = chassi;
        this.ano = ano;
        this.cor = cor;
        this.descricao = descricao;
    }

    public Veiculo(String placa,  Modelo modelo, String chassi, int ano, String cor, String descricao)
    {
        this.placa = placa;
        this.modelo = modelo;
        this.chassi = chassi;
        this.ano = ano;
        this.cor = cor;
        this.descricao = descricao;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getPlaca()
    {
        return placa;
    }

    public void setPlaca(String placa)
    {
        this.placa = placa;
    }

    public Modelo getModelo()
    {
        return modelo;
    }

    public void setModelo(Modelo modelo)
    {
        this.modelo = modelo;
    }

    public String getChassi()
    {
        return chassi;
    }

    public void setChassi(String chassi)
    {
        this.chassi = chassi;
    }

    public int getAno()
    {
        return ano;
    }

    public void setAno(int ano)
    {
        this.ano = ano;
    }

    public String getCor()
    {
        return cor;
    }

    public void setCor(String cor)
    {
        this.cor = cor;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public boolean salvar()
    {
        String sql = "INSERT INTO veiculo(vei_placa,modelo_codigo,vei_chassi,vei_ano,vei_cor,vei_descricao) "
            + "VALUES('$1',$2,'$3',$4,'$5','$6')";
        sql = sql.replace("$1", this.placa);
        sql = sql.replace("$2", String.valueOf(this.modelo.getCodigo()));
        sql = sql.replace("$3", this.chassi);
        sql = sql.replace("$4", String.valueOf(this.ano));
        sql = sql.replace("$5", this.cor);
        sql = sql.replace("$6", this.descricao);
        
        return Banco.getCon().manipular(sql);
    }

    public boolean alterar()
    {
        String sql = "UPDATE veiculo SET vei_placa = '$1', modelo_codigo = $2, vei_chassi = '$3', vei_ano = $4,"
                + "vei_cor = '$5', vei_descricao = '$6' WHERE vei_codigo = " + this.codigo;
        sql = sql.replace("$1", this.placa);
        sql = sql.replace("$2", String.valueOf(this.modelo.getCodigo()));
        sql = sql.replace("$3", this.chassi);
        sql = sql.replace("$4", String.valueOf(this.ano));
        sql = sql.replace("$5", this.cor);
        sql = sql.replace("$6", this.descricao);
        
        return Banco.getCon().manipular(sql);
    }

    public boolean apagar()
    {
        return Banco.getCon().manipular("DELETE FROM veiculo WHERE vei_codigo = " + this.codigo);
    }
    
    private ArrayList<Veiculo>ler(ResultSet rs)
    {
        ArrayList<Veiculo>veiculos = new ArrayList<>();
        
        try
        {
            while(rs != null && rs.next())            
                veiculos.add(new Veiculo(rs.getInt("vei_codigo"), rs.getString("vei_placa"), 
                    new Modelo(rs.getInt("modelo_codigo")), rs.getString("vei_chassi"), rs.getInt("vei_ano"), 
                        rs.getString("vei_cor"), rs.getString("vei_descricao")));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Veiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return veiculos;
    }

    public ArrayList<Veiculo> getAll()
    {
        return  ler(Banco.getCon().consultar("SELECT * FROM veiculo"));
    }

    public ArrayList<Veiculo> getByMarca(String marca)
    {
        return  ler(Banco.getCon().consultar("SELECT * FROM veiculo WHERE modelo_codigo in "
            + "(SELECT modelo_codigo FROM marca INNER JOIN modelo ON marca.marca_codigo = modelo.marca_codigo "
                + "AND marca_nome Ilike '%" + marca + "%')"));
    }

    public ArrayList<Veiculo> getByModelo(String modelo)
    {
        return  ler(Banco.getCon().consultar("SELECT * FROM veiculo INNER JOIN modelo ON "
            + "modelo.modelo_codigo = veiculo.modelo_codigo AND modelo_nome Ilike '%" + modelo + "%'"));
    }

    public ArrayList<Veiculo> getByPlaca(String placa)
    {
        return  ler(Banco.getCon().consultar("SELECT * FROM veiculo WHERE vei_placa Ilike '%" + placa + "%'"));
    }

    private void get()
    {
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM veiculo WHERE vei_codigo = " + this.codigo);
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.ano = rs.getInt("vei_ano");
                this.chassi = rs.getString("vei_chassi");
                this.cor = rs.getString("vei_cor");
                this.descricao = rs.getString("vei_descricao");
                this.modelo = new Modelo(rs.getInt("modelo_codigo"));
                this.placa = rs.getString("vei_placa");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Veiculo.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
    }

    @Override
    public String toString()
    {
        String ret = "Veiculo {placa = " ;
        
        if(placa != null)
            ret += placa;
        ret += ", ";
        
        ret += "modelo " + modelo.toString() + ", ";
        
        ret += "chassi = ";
        if(chassi != null)
            ret += chassi;
        ret += ", ";
        
        ret += "ano=" + ano + ", cor=" + cor + ", ";
        
        ret += "descrição = ";
        if(descricao != null)
            ret += descricao;
        ret += "}";
                
        return ret;
    }
    
    
}
