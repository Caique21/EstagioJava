/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import estagio.utilidades.Objeto;
import estagio.utilidades.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
    private String cegonha;
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

    public Transporte(int codigo, Funcionario motorista, String cegonha, Date saida, Date chegada, String status, String tipo, Timestamp alteracao)
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

    public Transporte(Funcionario motorista, String cegonha, Date saida, Date chegada, String status, String tipo, Timestamp alteracao)
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

    public String getCegonha()
    {
        return cegonha;
    }

    public void setCegonha(String cegonha)
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
        this.veiculos_transportados.add(new Veiculo(veiculo));
    }

    private void get()
    {
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM transporte WHERE trans_codigo = " + this.codigo);
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.alteracao = rs.getTimestamp("trans_data_alteracao");
                this.cegonha = rs.getString("trans_vei_placa");
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
                    rs.getString("trans_vei_placa"), rs.getDate("trans_data_saida"), 
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
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM transporte");
        ArrayList<Transporte>transportes = new ArrayList<>();
        Transporte t;
        
        try
        {
            while(rs != null && rs.next())            
            {   
                transportes.add(new Transporte(rs.getInt("trans_codigo"), new Funcionario(rs.getInt("func_codigo")), 
                    rs.getString("trans_vei_placa"), rs.getDate("trans_data_saida"), 
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

    public boolean salvar()
    {
        Connection connection = Banco.getCon().getConnection();
        PreparedStatement stmt = null;
        boolean flag = false;
        
        try
        {
            connection.setAutoCommit(false);
            
            stmt = connection.prepareStatement("INSERT INTO transporte(func_codigo,trans_vei_placa,trans_data_saida,"
                + "trans_data_chegada,trans_status,trans_tipo,trans_data_alteracao) VALUES(?,?,?,?,?,?,?)");
            
            stmt.setInt(1, this.motorista.getCodigo());
            stmt.setString(2, this.cegonha);
            stmt.setDate(3, new java.sql.Date(this.saida.getTime()));
            stmt.setString(5, this.status);
            stmt.setString(6, this.tipo);
            stmt.setTimestamp(7, new Timestamp(new java.util.Date().getTime()));
            
            if(this.chegada != null)
                stmt.setDate(4, new java.sql.Date(this.chegada.getTime()));
            else
                stmt.setDate(4, null);
            
            flag = stmt.executeUpdate() == 1;
            
            if(flag)
            {
                stmt = connection.prepareStatement("INSERT INTO veiculos_transportados(trans_codigo,vei_codigo)"
                        + " VALUES (CURRVAL('transporte_trans_codigo_seq'),?)");
                
                for (int i = 0; i < this.veiculos_transportados.size(); i++)
                {
                    stmt.setInt(1, this.veiculos_transportados.get(i).getCodigo());
                    stmt.addBatch();
                }
                
                for(int i : stmt.executeBatch())
                    if(i >= 0)
                        flag = true;
                    else
                    {
                        flag = false;
                        break;
                    }
                
                
                if(flag)
                    connection.commit();
            }
            
            if(!flag)
                connection.rollback();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return flag;
    }

    public boolean alterar()
    {
        Connection connection = Banco.getCon().getConnection();
        PreparedStatement stmt = null;
        boolean flag = false;
        
        try
        {
            connection.setAutoCommit(false);
            
            stmt = connection.prepareStatement("UPDATE transporte SET func_codigo = ?, trans_vei_placa = ?, "
                + "trans_data_saida = ?, trans_data_chegada = ?, trans_status = ?, trans_tipo = ?, "
                    + "trans_data_alteracao = ? WHERE trans_codigo = " + this.codigo);
            
            stmt.setInt(1, this.motorista.getCodigo());
            stmt.setString(2, this.cegonha);
            stmt.setDate(3, new java.sql.Date(this.saida.getTime()));
            stmt.setString(5, this.status);
            stmt.setString(6, this.tipo);
            stmt.setTimestamp(7, new Timestamp(new java.util.Date().getTime()));
            
            if(this.chegada != null)
                stmt.setDate(4, new java.sql.Date(this.chegada.getTime()));
            else
                stmt.setDate(4, null);
            
            flag = stmt.executeUpdate() == 1;
            
            connection.prepareStatement("DELETE FROM veiculos_transportados WHERE trans_codigo = " + 
                        this.codigo).executeUpdate();
            
            if(flag)
            {
                stmt = connection.prepareStatement("INSERT INTO veiculos_transportados(trans_codigo,vei_codigo)"
                        + " VALUES (?,?)");
                stmt.setInt(1, this.codigo);
                
                for(Veiculo v : this.veiculos_transportados)
                {
                    stmt.setInt(2, v.getCodigo());
                    stmt.addBatch();
                }
                
                for(int i : stmt.executeBatch())
                    if(i >= 0)
                        flag = true;
                    else
                    {
                        flag = false;
                        break;
                    }
                
                if(this.chegada != null && this.status.equals("Finalizado"))
                {
                    stmt = connection.prepareStatement("UPDATE despesa SET desp_data_vencimento = ? "
                        + "WHERE trans_codigo = ?");
                    stmt.setDate(1, new java.sql.Date(this.chegada.getTime()));
                    stmt.setInt(2, this.codigo);
                    
                    flag = stmt.executeUpdate() == 1;
                }
                
                if(flag)
                    connection.commit();
            }
            
            if(!flag)
                connection.rollback();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
            try
            {
                connection.close();
            }
            catch (SQLException ex1)
            {
                Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return flag;
    }

    public boolean apagar(int... codigo)
    {
        String sql = "DELETE FROM transporte WHERE trans_codigo = ";
        
        if(codigo.length == 0)
            sql += this.codigo;
        else
            sql += codigo;
        
        return Banco.getCon().manipular(sql);
    }

    public ArrayList<Objeto> getByFuncionario(String nome)
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        try
        {
            ResultSet rs = Banco.getCon().consultar("SELECT transporte.trans_codigo, trans_data_saida, "
                    + "trans_data_chegada, trans_status, trans_tipo, trans_vei_placa, func_nome FROM transporte "
                    + "INNER JOIN funcionario ON transporte.func_codigo = funcionario.func_codigo "
                    + "WHERE func_nome Ilike '%"+ nome + "%'");
            
            while(rs != null && rs.next())
            {
                ret.add(new Objeto(String.valueOf(rs.getInt("trans_codigo")),
                Utils.convertDataUTC(rs.getDate("trans_data_saida")),
                Utils.convertDataUTC(rs.getDate("trans_data_chegada")),
                rs.getString("trans_status"),
                rs.getString("trans_tipo"),
                rs.getString("trans_vei_placa"),
                rs.getString("func_nome")));
                
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return ret;
    }

    public ArrayList<Objeto> getByVeiculo(String nome)
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        try
        {
            ResultSet rs = Banco.getCon().consultar("SELECT DISTINCT(transporte.trans_codigo),trans_data_saida, "
                + "trans_data_chegada, trans_status, trans_tipo, trans_vei_placa, func_nome "
                + "FROM veiculos_transportados as trans INNER JOIN veiculo "
                + "ON trans.vei_codigo = veiculo.vei_codigo INNER JOIN transporte "
                + "ON transporte.trans_codigo = trans.trans_codigo INNER JOIN funcionario "
                + "ON transporte.func_codigo = funcionario.func_codigo "
                    + "WHERE vei_placa Ilike '%" + nome + "%' "
            + "UNION "
            + "SELECT DISTINCT(transporte.trans_codigo), trans_data_saida, trans_data_chegada, trans_status, "
                + "trans_tipo, trans_vei_placa, func_nome FROM veiculos_transportados as trans "
                + "INNER JOIN veiculo ON trans.vei_codigo = veiculo.vei_codigo INNER JOIN modelo "
                + "ON veiculo.modelo_codigo = modelo.modelo_codigo INNER JOIN transporte "
                + "ON transporte.trans_codigo = trans.trans_codigo INNER JOIN funcionario "
                + "ON transporte.func_codigo = funcionario.func_codigo "
                        + "WHERE modelo_nome Ilike '%" + nome + "%' "
            + "UNION "
            + "SELECT DISTINCT(transporte.trans_codigo), trans_data_saida, trans_data_chegada, trans_status, "
                + "trans_tipo, trans_vei_placa, func_nome FROM veiculos_transportados as trans "
                + "INNER JOIN veiculo ON trans.vei_codigo = veiculo.vei_codigo INNER JOIN modelo "
                + "ON veiculo.modelo_codigo = modelo.modelo_codigo INNER JOIN marca "
                + "ON modelo.marca_codigo = marca.marca_codigo INNER JOIN transporte "
                + "ON transporte.trans_codigo = trans.trans_codigo INNER JOIN funcionario "
                + "ON transporte.func_codigo = funcionario.func_codigo "
                        + "WHERE marca_nome Ilike '%" + nome + "%'");
            
            while(rs != null && rs.next())
            {
                ret.add(new Objeto(String.valueOf(rs.getInt("trans_codigo")),
                Utils.convertDataUTC(rs.getDate("trans_data_saida")),
                Utils.convertDataUTC(rs.getDate("trans_data_chegada")),
                rs.getString("trans_status"),
                rs.getString("trans_tipo"),
                rs.getString("trans_vei_placa"),
                rs.getString("func_nome")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return ret;
    }
    
    public ArrayList<Objeto> getByPeriodo(LocalDate inicio,LocalDate fim,boolean finalizada)
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        try
        {
            String sql = "SELECT trans_codigo, trans_data_saida,trans_data_chegada,trans_status,trans_tipo,"
                + "trans_vei_placa, func_nome FROM transporte INNER JOIN funcionario "
                + "ON transporte.func_codigo = funcionario.func_codigo WHERE $";
            
            if(!finalizada)
                sql = sql.replace("$", "trans_data_saida >= '" + inicio.toString() + "' "
                    + "AND trans_data_saida <= '" + fim.toString() + "' AND trans_status <> 'Finalizado'");
            else
                sql = sql.replace("$", "trans_data_saida >= '" + inicio.toString() + "' "
                    + "AND trans_data_chegada <= '" + fim.toString() + "' AND trans_status = 'Finalizado'");
            
            ResultSet rs = Banco.getCon().consultar(sql);
            
            while(rs != null && rs.next())
            {
                ret.add(new Objeto(String.valueOf(rs.getInt("trans_codigo")),
                Utils.convertDataUTC(rs.getDate("trans_data_saida")),
                Utils.convertDataUTC(rs.getDate("trans_data_chegada")),
                rs.getString("trans_status"),
                rs.getString("trans_tipo"),
                rs.getString("trans_vei_placa"),
                rs.getString("func_nome")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return ret;
    }
    
    public ArrayList<Objeto> getVeiculos(int codigo)
    {
        ArrayList<Objeto>ret = new ArrayList<>();
        try
        {
            ResultSet rs = Banco.getCon().consultar("SELECT vei_placa, marca_nome, modelo_nome,vei_chassi,vei_ano,"
                + " vei_cor FROM veiculos_transportados as trans INNER JOIN veiculo "
                + "ON trans.vei_codigo = veiculo.vei_codigo INNER JOIN modelo "
                + "ON veiculo.modelo_codigo = modelo.modelo_codigo INNER JOIN marca "
                + "ON modelo.marca_codigo = marca.marca_codigo WHERE trans_codigo = " + codigo);
            
            while(rs != null && rs.next())
            {
                ret.add(new Objeto(rs.getString("vei_placa"),
                rs.getString("marca_nome"),
                rs.getString("modelo_nome"),
                rs.getString("vei_chassi"),
                rs.getString("vei_ano"),
                rs.getString("vei_cor")));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
            Banco.getCon().setErro(ex.getMessage());
        }
        return ret;
    }
}
