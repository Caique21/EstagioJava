/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Endereco
{
    private int codigo;
    private String cep;
    private String rua;
    private int numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private String estado;

    public Endereco()
    {
    }

    public Endereco(int codigo)
    {
        this.codigo = codigo;
        get();
    }

    public Endereco(int codigo, String cep, String rua, int numero, String bairro, String complemento, String cidade, String estado)
    {
        this.codigo = codigo;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Endereco(String cep, String rua, int numero, String bairro, String complemento, String cidade, String estado)
    {
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        existe();
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getCep()
    {
        return cep;
    }

    public void setCep(String cep)
    {
        this.cep = cep;
    }

    public String getRua()
    {
        return rua;
    }

    public void setRua(String rua)
    {
        this.rua = rua;
    }

    public int getNumero()
    {
        return numero;
    }

    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getComplemento()
    {
        return complemento;
    }

    public void setComplemento(String complemento)
    {
        this.complemento = complemento;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + this.codigo;
        hash = 67 * hash + Objects.hashCode(this.cep);
        hash = 67 * hash + Objects.hashCode(this.rua);
        hash = 67 * hash + this.numero;
        hash = 67 * hash + Objects.hashCode(this.bairro);
        hash = 67 * hash + Objects.hashCode(this.complemento);
        hash = 67 * hash + Objects.hashCode(this.cidade);
        hash = 67 * hash + Objects.hashCode(this.estado);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (this.numero != other.numero)
        {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep))
        {
            return false;
        }
        if (!Objects.equals(this.rua, other.rua))
        {
            return false;
        }
        if (!Objects.equals(this.bairro, other.bairro))
        {
            return false;
        }
        if (!Objects.equals(this.complemento, other.complemento))
        {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade))
        {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        if(this.codigo > 0)
            return "CEP " + this.cep + " \n" + this.rua + " " + this.numero + ", " + this.bairro + " - " + 
                this.complemento + "\n" + this.cidade + " - " + this.estado;
        return "sem endereço cadastrado";
    }
    
    public boolean salvar()
    {
        String sql = "INSERT INTO endereco(ender_cep,ender_rua,ender_numero,ender_bairro,ender_complemento,"
            + "ender_cidade,ender_estado) VALUES('$1','$2',$3,'$4','$5','$6','$7')";
        sql = sql.replace("$1", cep);
        sql = sql.replace("$2", rua);
        sql = sql.replace("$3", String.valueOf(numero));
        sql = sql.replace("$4", bairro);
        sql = sql.replace("$5", complemento);
        sql = sql.replace("$6", cidade);
        sql = sql.replace("$7", estado);
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar()
    {
        String sql = "UPDATE endereco SET ender_cep = '$2',ender_rua = '$3',ender_numero = $4,ender_bairro = '$5',"
            + "ender_complemento = '$6', ender_cidade = '$7', ender_estado = '$8' WHERE ender_codigo = $1";
        sql = sql.replace("$1", String.valueOf(codigo));
        sql = sql.replace("$2", cep);
        sql = sql.replace("$3", rua);
        sql = sql.replace("$4", String.valueOf(numero));
        sql = sql.replace("$5", bairro);
        sql = sql.replace("$6", complemento);
        sql = sql.replace("$7", cidade);
        sql = sql.replace("$8", estado);
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean apagar()
    {
        return Banco.getCon().manipular("DELETE FROM endereco WHERE ender_codigo = " + this.codigo);
    }

    public boolean existe()
    {
        String sql = "SELECT ender_codigo FROM endereco WHERE ender_rua = '$1' AND ender_bairro = '$2' AND "
                + "ender_numero = $3 AND ender_cidade = '$4' AND ender_estado = '$5' AND ender_cep = '$6' "
                + "AND ender_complemento = '$7'";
        sql = sql.replace("$1", rua);
        sql = sql.replace("$2", bairro);
        sql = sql.replace("$3", String.valueOf(numero));
        sql = sql.replace("$4", cidade);
        sql = sql.replace("$5", estado);
        sql = sql.replace("$6", cep);
        sql = sql.replace("$7", complemento);
        
        ResultSet rs = null;
        Endereco e = null;
        boolean resultado = false;
        rs = Banco.getCon().consultar(sql);
        try
        {
            if (rs!= null && rs.next())
            {
                this.codigo = rs.getInt("ender_codigo");
                resultado = true;
            }
        } catch (SQLException ex)
        {
            e = null;
        }
        return resultado;
    }

    private void get()
    {
        String sql = "SELECT * FROM endereco WHERE ender_codigo = " + codigo;
        ResultSet rs = Banco.getCon().consultar(sql);
        try
        {
            if (rs!= null && rs.next())
            {
                this.bairro = rs.getString("ender_bairro");
                this.cep = rs.getString("ender_cep");
                this.cidade = rs.getString("ender_cidade");
                this.complemento = rs.getString("ender_complemento");
                this.estado = rs.getString("ender_estado");
                this.numero = rs.getInt("ender_numero");
                this.rua = rs.getString("ender_rua");
            }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
