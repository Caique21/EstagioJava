/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Carlos
 */
public class Funcionario
{
    private int codigo;
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private String funcao;
    private Date data;
    private boolean ativo;
    private Timestamp alteracao;
    private Endereco endereco;
    private String endereco_completo;
    private Date vencimento;
    private BufferedImage cnh_frente;
    private BufferedImage cnh_verso;

    public Funcionario()
    {
    }

    public Funcionario(int codigo)
    {
        this.codigo = codigo;
        get();
    }

    public Funcionario(String nome)
    {
        this.nome = nome;
        get();
    }

    public Funcionario(int codigo, String nome, String cpf, String rg, String email, String funcao,Date data, boolean ativo, Timestamp alteracao, Endereco endereco, Date vencimento)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.funcao = funcao;
        this.data = data;
        this.ativo = ativo;
        this.alteracao = alteracao;
        this.endereco = endereco;
        this.endereco_completo = this.endereco.toString();
        this.vencimento = vencimento;
    }

    public Funcionario(String nome, String cpf, String rg, String email, String funcao, Date data, Endereco endereco, Date vencimento)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.funcao = funcao;
        this.data = data;
        this.endereco = endereco;
        this.vencimento = vencimento;
    }
    
    public Funcionario(int codigo, String nome, String cpf, String rg, String email, String funcao, Date data, Endereco endereco, Date vencimento)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.funcao = funcao;
        this.data = data;
        this.endereco = endereco;
        this.vencimento = vencimento;
    }
    
    public Funcionario(String nome, String cpf, String rg, String email, String funcao, Date data, Endereco endereco)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.funcao = funcao;
        this.data = data;
        this.endereco = endereco;
    }
    
    public Funcionario(int codigo, String nome, String cpf, String rg, String email, String funcao, Date data, Endereco endereco)
    {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.funcao = funcao;
        this.data = data;
        this.endereco = endereco;
    }

    public int getCodigo()
    {
        return codigo;
    }

    public void setCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getRg()
    {
        return rg;
    }

    public void setRg(String rg)
    {
        this.rg = rg;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFuncao()
    {
        return funcao;
    }

    public void setFuncao(String funcao)
    {
        this.funcao = funcao;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public boolean isAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }

    public Timestamp getAlteracao()
    {
        return alteracao;
    }

    public void setAlteracao(Timestamp alteracao)
    {
        this.alteracao = alteracao;
    }

    public Endereco getEndereco()
    {
        return endereco;
    }

    public void setEndereco(Endereco endereco)
    {
        this.endereco = endereco;
    }

    public String getEndereco_completo()
    {
        return endereco_completo;
    }

    public void setEndereco_completo(String endereco_completo)
    {
        this.endereco_completo = endereco_completo;
    }

    public Date getVencimento()
    {
        return vencimento;
    }

    public void setVencimento(Date vencimento)
    {
        this.vencimento = vencimento;
    }

    public BufferedImage getCnh_frente()
    {
        return cnh_frente;
    }

    public void setCnh_frente(BufferedImage cnh_frente)
    {
        this.cnh_frente = cnh_frente;
    }

    public BufferedImage getCnh_verso()
    {
        return cnh_verso;
    }

    public void setCnh_verso(BufferedImage cnh_verso)
    {
        this.cnh_verso = cnh_verso;
    }

    private void get()
    {
        String sql = "SELECT * FROM funcionario WHERE ";
        
        if(nome != null)
            sql += "func_nome = '" + this.nome + "'";
        else
            sql += "func_codigo = " + this.codigo;
        
        ResultSet rs = Banco.getCon().consultar(sql);
        
        try
        {
            if(rs != null && rs.next())            
            {
                this.codigo = rs.getInt("func_codigo");
                this.nome = rs.getString("func_nome");
                this.cpf = rs.getString("func_cpf");
                this.rg = rs.getString("func_rg");
                this.email = rs.getString("func_email");
                this.funcao = rs.getString("func_funcao");
                this.data = rs.getDate("func_datacadastro");
                this.ativo = rs.getBoolean("func_ativo");
                this.alteracao = rs.getTimestamp("func_alteracao");
                this.endereco = new Endereco(rs.getInt("ender_codigo"));
                this.endereco_completo = this.endereco.toString();
                this.vencimento = rs.getDate("func_vencimento_cnh");
                
                rs = Banco.getCon().consultar("SELECT func_cnh_frente, func_cnh_verso FROM funcionario WHERE "
                        + "func_codigo = " + this.codigo + " AND func_cnh_frente IS NOT NULL AND "
                                + "func_cnh_verso IS NOT NULL");
                if(rs != null && rs.next())
                {
                    setCnh_frente(ImageIO.read(new ByteArrayInputStream(rs.getBytes("func_cnh_frente"))));
                    setCnh_verso(ImageIO.read(new ByteArrayInputStream(rs.getBytes("func_cnh_verso"))));
                }
            }
        }
        catch (SQLException | IOException ex)
        {
            Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ArrayList<Funcionario>le(ResultSet rs)
    {
        ArrayList<Funcionario>funcionarios = new ArrayList<>();
        ResultSet rs2;
        
        try
        {
            while(rs != null && rs.next())            
            {
                Funcionario funcionario = new Funcionario(rs.getInt("func_codigo"),rs.getString("func_nome"),
                    rs.getString("func_cpf"), rs.getString("func_rg"), rs.getString("func_email"),rs.getString("func_funcao"),
                    rs.getDate("func_datacadastro"), rs.getBoolean("func_ativo"), rs.getTimestamp("func_alteracao"), 
                    new Endereco(rs.getInt("ender_codigo")),rs.getDate("func_vencimento_cnh"));
                
                rs2 = Banco.getCon().consultar("SELECT func_cnh_frente, func_cnh_verso FROM funcionario WHERE "
                        + "func_codigo = " + funcionario.getCodigo() + " AND func_cnh_frente IS NOT NULL AND "
                                + "func_cnh_verso IS NOT NULL");
                if(rs2 != null && rs2.next())
                {
                    funcionario.setCnh_frente(ImageIO.read(new ByteArrayInputStream(rs2.getBytes("func_cnh_frente"))));
                    funcionario.setCnh_verso(ImageIO.read(new ByteArrayInputStream(rs2.getBytes("func_cnh_verso"))));
                }
                funcionarios.add(funcionario);
            }
        }
        catch (SQLException | IOException ex)
        {
            Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return funcionarios;
    }

    public Funcionario getByCpf(String cpf)
    {
        ArrayList<Funcionario>funcionarios = 
                le(Banco.getCon().consultar("SELECT * FROM funcionario WHERE func_cpf = '" + cpf + "'"));
        return funcionarios.isEmpty() ? null : funcionarios.get(0);
    }

    public ArrayList<Funcionario> getByName(String nome, boolean ativo)
    {
        return le(Banco.getCon().consultar("SELECT * FROM funcionario WHERE func_nome Ilike '%" + nome + "%' AND "
            + "func_ativo = '" + ativo + "'"));
    }

    public ArrayList<String> getTelefones(int codigo)
    {
        ResultSet rs = Banco.getCon().consultar("SELECT tel_numero FROM telefone WHERE func_codigo = " + codigo);
        ArrayList<String>telefones = new ArrayList<>();
        
        try
        {
            while(rs != null && rs.next())            
                telefones.add(rs.getString("tel_numero"));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return telefones;
    }

    public boolean inativar()
    {
        return Banco.getCon().manipular("UPDATE funcionario SET func_ativo = 'false' WHERE func_codigo = "
            + this.codigo);
    }
    
    public boolean salvar()
    {
        PreparedStatement statement;
        try
        {
            boolean flag;
            statement = Banco.getCon().getConnection().prepareStatement("INSERT INTO funcionario (func_nome,func_cpf,"
                    + "func_rg,func_email,func_funcao,func_datacadastro,func_ativo,func_alteracao,ender_codigo,"
                    + "func_vencimento_cnh) VALUES (?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, this.nome);
            statement.setString(2, this.cpf);
            statement.setString(3, this.rg);
            statement.setString(4, this.email);
            statement.setString(5, this.funcao);
            statement.setDate(6, (java.sql.Date) this.data);
            statement.setBoolean(7, true);
            statement.setTimestamp(8, new Timestamp(new java.util.Date().getTime()));
            statement.setInt(9, this.endereco.getCodigo());
            statement.setDate(10, (java.sql.Date) this.vencimento);

            flag = statement.executeUpdate() == 1;
            statement.close();
            return flag;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean salvar(String frente, String verso)
    {
        boolean flag = true;
        String sql;
        File logoG = new File(frente);
        File logoP = new File(verso);
        
        try
        {
            FileInputStream fGrande = new FileInputStream(logoG);
            FileInputStream fPequeno = new FileInputStream(logoP);
            PreparedStatement statement = null;

            statement = Banco.getCon().getConnection().prepareStatement("INSERT INTO funcionario (func_nome,func_cpf,"
                + "func_rg,func_email,func_funcao,func_datacadastro,func_ativo,func_alteracao,ender_codigo,"
                    + "func_vencimento_cnh,func_cnh_frente, func_cnh_verso) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, this.nome);
            statement.setString(2, this.cpf);
            statement.setString(3, this.rg);
            statement.setString(4, this.email);
            statement.setString(5, this.funcao);
            statement.setDate(6, (java.sql.Date) this.data);
            statement.setBoolean(7, true);
            statement.setTimestamp(8, new Timestamp(new java.util.Date().getTime()));
            statement.setInt(9, this.endereco.getCodigo());
            statement.setDate(10, (java.sql.Date) this.vencimento);
            statement.setBinaryStream(11, fGrande, (int) logoG.length());
            statement.setBinaryStream(12, fPequeno, (int) logoP.length());

            flag = statement.executeUpdate() == 1;
            statement.close();
            return flag;
        }
        catch (SQLException | FileNotFoundException ex)
        {
            flag = false;
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean alterar(String frente, String verso)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        connection = Banco.getCon().getConnection();
        boolean flag;
        try
        {
            statement = connection.prepareStatement("UPDATE funcionario SET func_nome = ?,func_cpf = ? ,"
                    + "func_rg = ?, func_email = ?, func_funcao = ?, func_datacadastro = ?, func_alteracao = ?,"
                    + "ender_codigo = ?, func_vencimento = ? WHERE func_codigo = " + this.codigo);

            statement.setString(1, this.nome);
            statement.setString(2, this.cpf);
            statement.setString(3, this.rg);
            statement.setString(4, this.email);
            statement.setString(5, this.funcao);
            statement.setDate(6, (java.sql.Date) this.data);
            statement.setTimestamp(7, new Timestamp(new java.util.Date().getTime()));
            statement.setInt(8, this.endereco.getCodigo());
            statement.setDate(9, (java.sql.Date) this.vencimento);
            
            try
            {
                flag = statement.executeUpdate() == 1;
                
                if(!"atualizando".equals(frente))
                    flag = flag && atualizaFrenteCNH(frente);
                if(!"atualizando".equals(verso))
                    flag = flag && atualizaVersoCNH(verso);
                statement.close();
                return flag;
            } 
            catch (SQLException e)
            {
                System.out.println(e);
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private boolean atualizaFrenteCNH(String caminho)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        connection = Banco.getCon().getConnection();
        boolean flag = false;
        try
        {
            File arq = new File(caminho);
            FileInputStream f = new FileInputStream(arq);
            statement = connection.prepareStatement("UPDATE funcionario SET func_cnh_frente = ? "
                    + "WHERE func_codigo = " + this.codigo);
            statement.setBinaryStream(10, f, (int) arq.length());
            statement.executeUpdate();
            flag = statement.executeUpdate() == 1;
            statement.close();
            return flag;
        }
        catch (SQLException | FileNotFoundException ex)
        {
            Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;   
    }

    public ByteArrayInputStream carregaFrente(int codigo)
    {
        ResultSet rs = Banco.getCon().consultar("SELECT func_cnh_frente FROM funcionario WHERE func_cnh_frente"
             + " IS NOT NULL AND func_codigo = " + codigo);
        try
        {
            if(rs != null && rs.next())
                return new ByteArrayInputStream(rs.getBytes("func_cnh_frente"));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Parametrizacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private boolean atualizaVersoCNH(String caminho)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        connection = Banco.getCon().getConnection();
        boolean flag = false;
        try
        {
            File arq = new File(caminho);
            FileInputStream f = new FileInputStream(arq);
            statement = connection.prepareStatement("UPDATE funcionario SET func_cnh_verso = ? "
                    + "WHERE func_codigo = " + this.codigo);
            statement.setBinaryStream(10, f, (int) arq.length());
            statement.executeUpdate();
            flag = statement.executeUpdate() == 1;
            statement.close();
            return flag;
        }
        catch (SQLException | FileNotFoundException ex)
        {
            Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;   
    }
    
    public ByteArrayInputStream carregaVerso(int codigo)
    {
        ResultSet rs = Banco.getCon().consultar("SELECT func_cnh_verso FROM funcionario WHERE func_cnh_verso"
             + " IS NOT NULL AND func_codigo = " + codigo);
        try
        {
            if(rs != null && rs.next())
                return new ByteArrayInputStream(rs.getBytes("func_cnh_verso"));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Parametrizacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
