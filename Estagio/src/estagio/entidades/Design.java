/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.entidades;

import estagio.utilidades.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Design
{
    private String fundo;
    private String fundo2;
    private String fonte;
    private String fundoBotao;
    private String preenchimento;
    private String fonteBotao;
    private int tamanhoBotao;
    private String fonteTexto;
    private String focoTexto;
    private int tamanhoTexto;
    private int opacidade;
    
    public Design()
    {
    }

    public Design(String fundo, String fundo2, String fonte, String fundoBotao, String preenchimento, String fonteBotao, int tamanhoBotao, String fonteTexto, String focoTexto, int tamanhoTexto)
    {
        this.fundo = fundo;
        this.fundo2 = fundo2;
        this.fonte = fonte;
        this.fundoBotao = fundoBotao;
        this.preenchimento = preenchimento;
        this.fonteBotao = fonteBotao;
        this.tamanhoBotao = tamanhoBotao;
        this.fonteTexto = fonteTexto;
        this.focoTexto = focoTexto;
        this.tamanhoTexto = tamanhoTexto;
    }

    public Design(String fundo, String fundo2, String fonte, String fundoBotao, String preenchimento, String fonteBotao, int tamanhoBotao, String fonteTexto, String focoTexto, int tamanhoTexto, int opacidade)
    {
        this.fundo = fundo;
        this.fundo2 = fundo2;
        this.fonte = fonte;
        this.fundoBotao = fundoBotao;
        this.preenchimento = preenchimento;
        this.fonteBotao = fonteBotao;
        this.tamanhoBotao = tamanhoBotao;
        this.fonteTexto = fonteTexto;
        this.focoTexto = focoTexto;
        this.tamanhoTexto = tamanhoTexto;
        this.opacidade = opacidade;
    }

    public String getFundo()
    {
        return fundo;
    }

    public void setFundo(String fundo)
    {
        this.fundo = fundo;
    }

    public String getFundo2()
    {
        return fundo2;
    }

    public void setFundo2(String fundo2)
    {
        this.fundo2 = fundo2;
    }

    public String getFonte()
    {
        return fonte;
    }

    public void setFonte(String fonte)
    {
        this.fonte = fonte;
    }

    public String getFundoBotao()
    {
        return fundoBotao;
    }

    public void setFundoBotao(String fundoBotao)
    {
        this.fundoBotao = fundoBotao;
    }

    public String getPreenchimento()
    {
        return preenchimento;
    }

    public void setPreenchimento(String preenchimento)
    {
        this.preenchimento = preenchimento;
    }

    public String getFonteBotao()
    {
        return fonteBotao;
    }

    public void setFonteBotao(String fonteBotao)
    {
        this.fonteBotao = fonteBotao;
    }

    public int getTamanhoBotao()
    {
        return tamanhoBotao;
    }

    public void setTamanhoBotao(int tamanhoBotao)
    {
        this.tamanhoBotao = tamanhoBotao;
    }

    public String getFonteTexto()
    {
        return fonteTexto;
    }

    public void setFonteTexto(String fonteTexto)
    {
        this.fonteTexto = fonteTexto;
    }

    public String getFocoTexto()
    {
        return focoTexto;
    }

    public void setFocoTexto(String focoTexto)
    {
        this.focoTexto = focoTexto;
    }

    public int getTamanhoTexto()
    {
        return tamanhoTexto;
    }

    public void setTamanhoTexto(int tamanhoTexto)
    {
        this.tamanhoTexto = tamanhoTexto;
    }

    public int getOpacidade()
    {
        return opacidade;
    }

    public void setOpacidade(int opacidade)
    {
        this.opacidade = opacidade;
    }
    
    public boolean salvar()
    {
        String sql = "insert into design(desi_fundo_interface,desi_fundo2_interface,desi_fonte_interface,"
            + "desi_fundo_botao,desi_preenchimento_botao,desi_fonte_botao,desi_tamanho_botao,desi_fonte_entrada,"
              + "desi_foco_entrada,desi_tamanho_entrada,desi_opacidade) "
                + "values ('$1','$2','$3','$4','$5','$6',$7,'$8','$9','$10',$11)";
        sql = sql.replace("$10", String.valueOf(tamanhoTexto));
        sql = sql.replace("$11", String.valueOf(opacidade));
        sql = sql.replace("$1", fundo);
        sql = sql.replace("$2", fundo2);
        sql = sql.replace("$3", fonte);
        sql = sql.replace("$4", fundoBotao);
        sql = sql.replace("$5", preenchimento);
        sql = sql.replace("$6", fonteBotao);
        sql = sql.replace("$7", String.valueOf(tamanhoBotao));
        sql = sql.replace("$8", fonteTexto);
        sql = sql.replace("$9", focoTexto);
        
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar()
    {
        String sql = "delete from design";
        
        return Banco.getCon().manipular(sql) && salvar();
    }
    
    public Design get()
    {
        ResultSet rs = Banco.getCon().consultar("select * from design");
        
        try
        {
            if(rs != null && rs.next())            
            {
                return new Design(rs.getString("desi_fundo_interface"), rs.getString("desi_fundo2_interface"), 
                    rs.getString("desi_fonte_interface"), rs.getString("desi_fundo_botao"), 
                    rs.getString("desi_preenchimento_botao"), rs.getString("desi_fonte_botao"), 
                    rs.getInt("desi_tamanho_botao"), rs.getString("desi_fonte_entrada"), 
                    rs.getString("desi_foco_entrada"), rs.getInt("desi_tamanho_entrada"),
                    rs.getInt("desi_opacidade"));
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Design.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
