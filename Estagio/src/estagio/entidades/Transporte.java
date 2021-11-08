/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Transporte
{
    private int codigo;
    private Funcionario motorista;
    private Veiculo cegonha;
    private Date saida;
    private Date chegada;
    private String status;
    private String tipo;
    private Timestamp alteracao;
    private ArrayList<Veiculo>veiculos_transportados;

    public Transporte()
    {
    }

    public Transporte(int codigo)
    {
        this.codigo = codigo;
        get();
    }

    public Transporte(int codigo, Funcionario motorista, Veiculo cegonha, Date saida, Date chegada, String status, String tipo, Timestamp alteracao)
    {
        this.codigo = codigo;
        this.motorista = motorista;
        this.cegonha = cegonha;
        this.saida = saida;
        this.chegada = chegada;
        this.status = status;
        this.tipo = tipo;
        this.alteracao = alteracao;
        this.veiculos_transportados = lerVeiculosTransportados();
    }

    public Transporte(Funcionario motorista, Veiculo cegonha, Date saida, Date chegada, String status, String tipo, Timestamp alteracao)
    {
        this.motorista = motorista;
        this.cegonha = cegonha;
        this.saida = saida;
        this.chegada = chegada;
        this.status = status;
        this.tipo = tipo;
        this.alteracao = alteracao;
        this.veiculos_transportados = lerVeiculosTransportados();
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public Funcionario getMotorista()
    {
        return motorista;
    }

    public void setMotorista(Funcionario motorista)
    {
        this.motorista = motorista;
    }

    public Veiculo getCegonha()
    {
        return cegonha;
    }

    public void setCegonha(Veiculo cegonha)
    {
        this.cegonha = cegonha;
    }

    public Date getSaida()
    {
        return saida;
    }

    public void setSaida(Date saida)
    {
        this.saida = saida;
    }

    public Date getChegada()
    {
        return chegada;
    }

    public void setChegada(Date chegada)
    {
        this.chegada = chegada;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public Timestamp getAlteracao()
    {
        return alteracao;
    }

    public void setAlteracao(Timestamp alteracao)
    {
        this.alteracao = alteracao;
    }

    public ArrayList<Veiculo> getVeiculos_transportados()
    {
        return veiculos_transportados;
    }

    public void setVeiculos_transportados(ArrayList<Veiculo> veiculos_transportados)
    {
        this.veiculos_transportados = veiculos_transportados;
    }
    
    public void addVeiculo(Veiculo veiculo)
    {
        if(this.veiculos_transportados == null)
            this.veiculos_transportados = new ArrayList<>();
        this.veiculos_transportados.add(veiculo);
    }
    
    public void addVeiculo(int veiculo)
    {
        if(this.veiculos_transportados == null)
            this.veiculos_transportados = new ArrayList<>();
        this.veiculos_transportados.add(new Veiculo(codigo));
    }

    private void get()
    {
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM transporte WHERE trans_codigo = " + this.codigo);
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.alteracao = rs.getTimestamp("trans_data_alteracao");
                this.cegonha = new Veiculo(rs.getInt("vei_codigo"));
                this.chegada = rs.getDate("trans_data_chegada");
                this.saida = rs.getDate("trans_data_saida");
                this.motorista = new Funcionario(rs.getInt("func_codigo"));
                this.status = rs.getString("trans_status");
                this.tipo = rs.getString("trans_tipo");
                this.veiculos_transportados = lerVeiculosTransportados();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
    }
    
    private ArrayList<Veiculo>lerVeiculosTransportados()
    {
        ArrayList<Veiculo>veiculos = new ArrayList<>();
        
        ResultSet rs = Banco.getCon().consultar("SELECT vei_codigo FROM veiculos_transportados "
            + "WHERE trans_codigo = " + this.codigo);
        
        try
        {
            while(rs != null && rs.next())            
                veiculos.add(new Veiculo(rs.getInt("vei_codigo")));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return veiculos;
    }
    
    private ArrayList<Transporte>ler(ResultSet rs)
    {
        ArrayList<Transporte>transportes = new ArrayList<>();
        Transporte t;
        
        try
        {
            while(rs != null && rs.next())            
            {   
                transportes.add(new Transporte(rs.getInt("trans_codigo"), new Funcionario(rs.getInt("func_codigo")), 
                    new Veiculo(rs.getInt("vei_codigo")), rs.getDate("trans_data_saida"), 
                    rs.getDate("trans_data_chegada"), rs.getString("trans_status"), rs.getString("trans_tipo"), 
                    rs.getTimestamp("trans_data_alteracao")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return transportes;
    }

    public ArrayList<Transporte> getAll()
    {
        return ler(Banco.getCon().consultar("SELECT * FROM transporte"));
    }
    
    
}
