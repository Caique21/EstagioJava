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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Carlos
 */
public class Parametrizacao
{
    private String nome;
    private String fantasia;
    private String email;
    private String razao;
    private List<Endereco>enderecos;
    private List<String>telefones;
    private BufferedImage logoGrande;
    private BufferedImage logoPequeno;

    public Parametrizacao()
    {
        carrega();
        this.enderecos = new ArrayList<>();
        this.telefones = new ArrayList<>();
    }

    public Parametrizacao(String nome, String fantasia, String email, String razao, List<Endereco> enderecos, List<String> telefones, BufferedImage logoGrande, BufferedImage logoPequeno)
    {
        this.nome = nome;
        this.fantasia = fantasia;
        this.email = email;
        this.razao = razao;
        this.enderecos = enderecos;
        this.telefones = telefones;
        this.logoGrande = logoGrande;
        this.logoPequeno = logoPequeno;
    }

    public Parametrizacao(String nome, String fantasia, String email, String razao, BufferedImage logoGrande, BufferedImage logoPequeno)
    {
        this.nome = nome;
        this.fantasia = fantasia;
        this.email = email;
        this.razao = razao;
        this.logoGrande = logoGrande;
        this.logoPequeno = logoPequeno;
        this.enderecos = new ArrayList<>();
        this.telefones = new ArrayList<>();
    }

    public Parametrizacao(String nome, String fantasia, String email, String razao)
    {
        this.nome = nome;
        this.fantasia = fantasia;
        this.email = email;
        this.razao = razao;
        this.enderecos = new ArrayList<>();
        this.telefones = new ArrayList<>();
    }

    public Parametrizacao(String para)
    {
        this.nome = para;
        this.telefones = new ArrayList<>();
        this.enderecos = new ArrayList<>();
        get();
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getFantasia()
    {
        return fantasia;
    }

    public void setFantasia(String fantasia)
    {
        this.fantasia = fantasia;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRazao()
    {
        return razao;
    }

    public void setRazao(String razao)
    {
        this.razao = razao;
    }

    public List<Endereco> getEnderecos()
    {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos)
    {
        this.enderecos = enderecos;
    }

    public List<String> getTelefones()
    {
        return telefones;
    }
    
    public void addEndereco(Endereco endereco)
    {
        this.enderecos.add(endereco);
    }

    public void setTelefones(List<String> telefones)
    {
        this.telefones = telefones;
    }
    
    public void addTelefone(String telefone)
    {
        this.telefones.add(telefone);
    }

    public BufferedImage getLogoGrande()
    {
        return logoGrande;
    }

    public void setLogoGrande(BufferedImage logoGrande)
    {
        this.logoGrande = logoGrande;
    }

    public BufferedImage getLogoPequeno()
    {
        return logoPequeno;
    }

    public void setLogoPequeno(BufferedImage logoPequeno)
    {
        this.logoPequeno = logoPequeno;
    }
    
    public Parametrizacao carrega()
    {
        ResultSet rs = Banco.getCon().consultar("SELECT * FROM parametrizacao");
        Parametrizacao p = null;
        try
        {
            if(rs != null && rs.next())            
            {
                p = new Parametrizacao(rs.getString("para_nome"), rs.getString("para_fantasia"), 
                    rs.getString("para_email"),rs.getString("para_razaoSocial"));
                
                rs = Banco.getCon().consultar("SELECT * FROM telefone WHERE para_nome IS NOT NULL");
                while(rs != null && rs.next())
                    p.addTelefone(rs.getString("tel_numero"));
                
                rs = Banco.getCon().consultar("SELECT * FROM endereco INNER JOIN endereco_parametrizacao ON "
                    + "endereco_parametrizacao.ender_codigo = endereco.ender_codigo");
                while(rs != null && rs.next())
                    p.addEndereco(new Endereco(rs.getInt("ender_codigo"), rs.getString("ender_cep"),
                    rs.getString("ender_rua"), rs.getInt("ender_numero"), rs.getString("ender_bairro"), 
                    rs.getString("ender_complemento"), rs.getString("ender_cidade"), rs.getString("ender_estado")));
                //cep, rua, numero, bairro, complemento, cidade , estado
            }
            
            rs = Banco.getCon().consultar("SELECT * FROM parametrizacao WHERE para_logogrande IS NOT NULL AND"
                + " para_logopequeno IS NOT NULL");
            if(rs != null && rs.next())
            {
                p.setLogoGrande(ImageIO.read(new ByteArrayInputStream(rs.getBytes("para_logoGrande"))));
                p.setLogoPequeno(ImageIO.read(new ByteArrayInputStream(rs.getBytes("para_logoPequeno"))));
            }
        }
        catch (SQLException | IOException ex)
        {
            Logger.getLogger(Parametrizacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    public boolean salvar(String caminhoGrande, String caminhoPequeno)
    {
        boolean flag = true;
        String sql;
        File logoG = new File(caminhoGrande);
        File logoP = new File(caminhoPequeno);
        
        try
        {
            FileInputStream fGrande = new FileInputStream(logoG);
            FileInputStream fPequeno = new FileInputStream(logoP);
            Connection connection = null;
            PreparedStatement statement = null;
            connection = Banco.getCon().getConnection();

            statement = connection.prepareStatement("INSERT INTO parametrizacao (para_nome,para_fantasia,"
                    + "para_logoGrande,para_logoPequeno,para_email,para_razaoSocial) VALUES (?,?,?,?,?,?)");
            statement.setString(1, nome);
            statement.setString(2, fantasia);
            statement.setBinaryStream(3, fGrande, (int) logoG.length());
            statement.setBinaryStream(4, fPequeno, (int) logoP.length());
            statement.setString(5, email);
            statement.setString(6, razao);

            flag = statement.executeUpdate() == 1;
            statement.close();
            return flag && salvaEnderecos() && salvaTelefones();
        }
        catch (SQLException | FileNotFoundException ex)
        {
            flag = false;
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    public boolean salvar()
    {
        String sql = "INSERT INTO parametrizacao (para_nome, para_fantasia,para_email,para_razaoSocial) "
            + "VALUES ('$1','$2','$3','$4')";
        sql = sql.replace("$1", nome);
        sql = sql.replace("$2", fantasia);
        sql = sql.replace("$3", email);
        sql = sql.replace("$4", razao);
        
        return Banco.getCon().manipular(sql) && salvaEnderecos() && salvaTelefones();
    }
    
    public boolean altera(String caminhoGrande, String caminhoPequeno)
    {
        Connection connection = null;
        PreparedStatement statement = null;

        connection = Banco.getCon().getConnection();
        //nome,fantasia,logoGrande,logoPequeno,email,razaoSocial
        try
        {
            if(caminhoGrande !=null && caminhoPequeno !=null)
            {
                File arq = new File(caminhoGrande);
                File arq2 = new File(caminhoPequeno);
                FileInputStream f = new FileInputStream(arq);
                FileInputStream f2 = new FileInputStream(arq2);
                statement = connection.prepareStatement("UPDATE parametrizacao SET para_nome = ?,para_fantasia = ? ,"
                    + "para_email = ?, para_razaoSocial = ?, para_logoGrande = ?, para_logoPequeno = ?");
                statement.setBinaryStream(5, f, (int) arq.length());
                statement.setBinaryStream(6, f2, (int) arq2.length());
            }
            else if(caminhoGrande !=null)
            {
                File arq = new File(caminhoGrande);
                FileInputStream f = new FileInputStream(arq);
                statement = connection.prepareStatement("UPDATE parametrizacao SET para_nome = ?,para_fantasia = ? ,"
                    + "para_email = ?, para_razaoSocial = ?, para_logoGrande = ?");
                statement.setBinaryStream(5, f, (int) arq.length());
            }
            else if(caminhoPequeno !=null)
            {
                File arq2 = new File(caminhoPequeno);
                FileInputStream f2 = new FileInputStream(arq2);
                statement = connection.prepareStatement("UPDATE parametrizacao SET para_nome = ?,para_fantasia = ? ,"
                    + "para_email = ?, para_razaoSocial = ?, para_logoPequeno = ?");
                statement.setBinaryStream(5, f2, (int) arq2.length());
            }
            else
            {
                 statement = connection.prepareStatement("UPDATE parametrizacao SET para_nome = ?,para_fantasia = ? ,"
                    + "para_email = ?, para_razaoSocial = ?");
            }

            statement.setString(1, nome);
            statement.setString(2, fantasia);
            statement.setString(3, email);
            statement.setString(4, razao);
            
            try
            {
                statement.executeUpdate();
                statement.close();
                return true && salvaEnderecos() && salvaTelefones();

            } catch (SQLException e)
            {
                System.out.println(e);
            }
        }
        catch (SQLException | FileNotFoundException ex)
        {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    private boolean salvaEnderecos()
    {
        Banco.getCon().manipular("DELETE FROM endereco_parametrizacao");
        String sql;
        boolean flag = true;
        
        for(Endereco e : enderecos)
        {
            if(!e.existe())
            {
                e.salvar();
                e.setCodigo(Banco.getCon().getMaxPK("endereco", "ender_codigo"));
            }
            
            flag = flag && Banco.getCon().manipular("INSERT INTO endereco_parametrizacao(para_nome,ender_codigo)"
                + "VALUES ('"+nome+"',"+e.getCodigo()+")");
        }
        return flag;
    }

    private boolean salvaTelefones()
    {
        Banco.getCon().manipular("DELETE FROM telefone WHERE para_nome = '" + nome + "'");
        boolean flag = true;
        
        for(String telefone : telefones)
            flag = flag && new Telefone(telefone, this).salvar();
        return flag;
    }

    private void get()
    {
        if (nome != null)
        {
            ResultSet rs = Banco.getCon().consultar("SELECT * FROM parametrizacao");
            try
            {
                if (rs != null && rs.next())
                {
                    this.fantasia = rs.getString("para_fantasia");
                    this.email = rs.getString("para_email");
                    this.razao = rs.getString("para_razaoSocial");

                    rs = Banco.getCon().consultar("SELECT * FROM telefone WHERE para_nome IS NOT NULL");
                    while (rs != null && rs.next())
                    {
                        this.addTelefone(rs.getString("tel_numero"));
                    }

                    rs = Banco.getCon().consultar("SELECT * FROM endereco INNER JOIN endereco_parametrizacao ON "
                            + "endereco_parametrizacao.ender_codigo = endereco.ender_codigo");
                    while (rs != null && rs.next())
                    {
                        this.addEndereco(new Endereco(rs.getInt("ender_codigo"), rs.getString("ender_cep"),
                            rs.getString("ender_rua"), rs.getInt("ender_numero"), rs.getString("ender_bairro"), 
                                rs.getString("ender_complemento"), rs.getString("ender_cidade"), 
                                    rs.getString("ender_estado")));
                    }
                    //cep,rua, nuemro,bairro,complemento, cidade,estado
                }

                rs = Banco.getCon().consultar("SELECT * FROM parametrizacao WHERE para_logogrande IS NOT NULL AND "
                        + "para_logopequeno IS NOT NULL");
                if (rs != null && rs.next())
                {
                    this.logoGrande = ImageIO.read(new ByteArrayInputStream(rs.getBytes("para_logoGrande")));
                    this.logoPequeno = ImageIO.read(new ByteArrayInputStream(rs.getBytes("para_logoPequeno")));
                }
            }
            catch (SQLException | IOException ex)
            {
                Logger.getLogger(Parametrizacao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ByteArrayInputStream carregaLogoGrande()
    {
        ResultSet rs = Banco.getCon().consultar("SELECT para_logogrande FROM parametrizacao WHERE para_logogrande"
             + " IS NOT NULL");
        try
        {
            if(rs != null && rs.next())
                return new ByteArrayInputStream(rs.getBytes("para_logoGrande"));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Parametrizacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ByteArrayInputStream carregaLogoPequeno()
    {
        ResultSet rs = Banco.getCon().consultar("SELECT para_logopequeno FROM parametrizacao WHERE para_logopequeno"
             + " IS NOT NULL");
        try
        {
            if(rs != null && rs.next())
                return new ByteArrayInputStream(rs.getBytes("para_logopequeno"));
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Parametrizacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String carregaFantasia()
    {
        ResultSet rs = Banco.getCon().consultar("SELECT para_fantasia FROM parametrizacao");
        try
        {
            if(rs != null && rs.next())
                return rs.getString("para_fantasia");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Parametrizacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
