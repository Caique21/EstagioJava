/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estagio.utilidades;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import estagio.TelaPrincipalController;
import estagio.controladores.ctrDesign;
import estagio.controladores.ctrUsuario;
import estagio.controladores.ctrCliente;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author carlo
 */
public class Utils
{
    public static ArrayList<String>design;
    public static boolean confirmarUsuario(String titulo, String header,String txt_label)
    {
        ctrUsuario ctr = ctrUsuario.instancia();
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(titulo);
        dialog.setHeaderText(header);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        PasswordField pwd = new PasswordField();
        VBox vbox = new VBox();
        Label label = new Label(txt_label);
        vbox.getChildren().addAll(label,pwd);
        dialog.getDialogPane().setContent(vbox);
        dialog.setResultConverter(dialogButton -> 
        {
            if (dialogButton == ButtonType.OK) {
                return pwd.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        
        return result.isPresent() && ctr.matchPassword(TelaPrincipalController.usuario_logado.getParam2(), 
                pwd.getText());
    }
    
    public static boolean validaUsuario(String titulo, String header,String txt_label)
    {
        ctrUsuario ctr = ctrUsuario.instancia();
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(titulo);
        dialog.setHeaderText(header);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        PasswordField pwd = new PasswordField();
        VBox vbox = new VBox();
        Label label = new Label(txt_label);
        vbox.getChildren().addAll(label,pwd);
        dialog.getDialogPane().setContent(vbox);
        dialog.setResultConverter(dialogButton -> 
        {
            if (dialogButton == ButtonType.OK) {
                return pwd.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        
        if(result.isPresent())
        {
            if(ctr.matchPassword(TelaPrincipalController.usuario_logado.getParam2(), pwd.getText()))
                return true;
            else
                new Alert(Alert.AlertType.ERROR, "Senha incorreta",ButtonType.OK).showAndWait();
        }
        return false;
    }
    
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static int isValidCPForCNPJ(String str)
    {
        if(isValidCPF(str))
            return 1; //CPF válido
        else if(isValidCNPJ(str))
            return 2; //CNPJ válido
        return -1; //CPF ou CNPJ inválido.
    }
    
    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
           digito = Integer.parseInt(str.substring(indice,indice+1));
           soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    private static boolean isValidCPF(String cpf) {
        cpf = cpf.replace(".", "").replace("-", "");
        if ((cpf==null) || (cpf.length()!=11)) return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

    private static boolean isValidCNPJ(String cnpj) {
        cnpj = cnpj.replace(".", "").replace("-", "").replace("/", "");
        if ((cnpj==null)||(cnpj.length()!=14)) return false;

        Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
    }
    
    public static boolean validaPlaca(String placa)
    {
        Pattern brasil = Pattern.compile("[A-Z]{3}[0-9]{4}");
        Pattern mercosul = Pattern.compile("[A-Z]{3}[0-9][0-9A-Z][0-9]{2}");
        
        return brasil.matcher(placa).matches() || mercosul.matcher(placa).matches();
    }
    
    private static final String regex = "^(.+)@(.+)$";

    public static boolean validaEmail(String email)
    {
        Pattern pattern = Pattern.compile(regex);
        return email.matches(regex);
    }
    
    public static boolean validaCPF(String strCpf)
    {
        if (strCpf.isEmpty() || strCpf.length()!=11)
        {
            return false;
        }
        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;
        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;
        for (int nCount = 1; nCount < strCpf.length() - 1; nCount++)
        {
            digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();
            d1 = d1 + (11 - nCount) * digitoCPF;
            d2 = d2 + (12 - nCount) * digitoCPF;
        }
        resto = (d1 % 11);
        if (resto < 2)
        {
            digito1 = 0;
        } else
        {
            digito1 = 11 - resto;
        }
        d2 += 2 * digito1;
        resto = (d2 % 11);
        if (resto < 2)
        {
            digito2 = 0;
        } else
        {
            digito2 = 11 - resto;
        }
        String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
        return nDigVerific.equals(nDigResult) && numeroRepetem(strCpf);
    }
    
    public static boolean numeroRepetem(String strCpf)
    {
        for(int i = 0; i < strCpf.length() - 2; i++)
            if(strCpf.charAt(i) != strCpf.charAt(i + 1))
                return true;
        return false;
    }

    public static String validadorCPF(String cpf, Objeto cliente, ctrCliente ctrCli)
    {
        /*if (cpf.length() > 8 && cpf.length() < 14)
        {
            return "CPF incompleto";
        }
        else if (!validaCPF(cpf.replace(".", "").replace("-", "")))
        {
            return "CPF inválido";
        }
        else if ((cliente == null && cli.cpfExists(cpf) > 0) || 
             (cli.cpfExists(cpf) != Integer.parseInt(cliente.getParam1())))
        {
            return "CPF já cadastrado";
        }
        return "";*/
        if(!Utils.validaCPF(cpf.replace(".", "").replace("-", "")))
            return "CPF inválido";
        else if(cliente == null && ctrCli.cpfExists(cpf) > 0 || 
            cliente != null && ctrCli.cpfExists(cpf) != Integer.parseInt(cliente.getParam1()))  
            return "CPF já cadastrado";
        else
            return "";
    }

    
    public String consultaCep(String cep, String formato)
    {
        String username = "seu login";
        String password = "sua senha";
        String proxyHost = "177.131.35.1";
        String proxyPort = "3128";

        StringBuffer dados = new StringBuffer();
        try
        {
            //URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato"+formato);
            URL url = new URL("http://apps.widenet.com.br/busca-cep/api/cep." + formato + "?code=" + cep);

            //String userpass = username + ":" + password;
            //System.setProperty("http.proxyHost", proxyHost);
            //System.setProperty("http.proxyPort", proxyPort);
            URLConnection con = url.openConnection();
            con.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            //String encodedLogin = new BASE64Encoder().encode(userpass.getBytes());
            //con.setRequestProperty("Proxy-Authorization", "Basic " + encodedLogin);
            //con.setDoInput(true);
            //con.setDoOutput(true);
            //con.setAllowUserInteraction(false);
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String s = "";
            while (null != (s = br.readLine()))
            {
                dados.append(s);
            }
            br.close();
        } 
        catch (IOException ex)
        {
            System.out.println(ex);
        }
        return dados.toString();
    }
    
    public static Date addDay(Date date, int i)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, i);
        return cal.getTime();
    }

    public static Date addMonth(Date date, int i)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
    }

    public static Date addYear(Date date, int i)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, i);
        return cal.getTime();
    }
    
    ///YYYY-MM-DD
    public static String convertData(Date date)
    {
        String[] meses = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago",
            "Set", "Out","Nov", "Dez"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH) + " de " + meses[cal.get(Calendar.MONTH)] + " de " + 
            cal.get(Calendar.YEAR);
    }
    
    public static String convertDataUTC(Date date)
    {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
    
    ////DD de MM de YYYY
    public static Date convertData(String date)
    {
        ArrayList<String> meses = new ArrayList<>();
        meses.add("Jan");meses.add("Fev");meses.add("Mar");meses.add("Abr");meses.add("Mai");
        meses.add("Jun");meses.add("Jul");meses.add("Ago");meses.add("Set");meses.add("Out");
        meses.add("Nov");meses.add("Dez");
        
        String data = date.substring(0, date.indexOf(" ")) + "/" + 
            (meses.indexOf(date.substring(date.indexOf("de ") + 3, date.lastIndexOf(" de "))) + 1) + 
            "/" + date.substring(date.lastIndexOf("de ") + 3);
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            return formato.parse(data);
        }
        catch (ParseException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Date convertToDate(LocalDate date)
    {
        Instant instant = Instant.from(date.atStartOfDay(ZoneId.systemDefault()));
        return Date.from(instant);
    }
    
    public static java.sql.Date convertToSqlDate(LocalDate date)
    {
        java.sql.Date data = java.sql.Date.valueOf(date);
        return data;
    }
    
    public static LocalDate convertToLocalDate(Date date)
    {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public static Date setZeroTimeDate(Date data) 
    {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(data);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date res = calendar.getTime();

        return res;
    }
    
    public static Double truncate(double val)
    {
        /*DecimalFormat df = new DecimalFormat("#,##");
        return Double.parseDouble(df.format(val));*/
        return ((long)(val * 1e2)) / 1e2;
    }
    
    public static Double convertStringToDouble(String valor)
    {
        return Double.parseDouble(valor.replace(".", "").replace(",", "."));
    }
    
    public static String corrigiDecimais(String valor)
    {
        if(valor.substring(valor.indexOf(",") + 1).length() < 2)
            valor += "0";
        return valor;
    }
    
    public static String exibeCentavos(Double valor)
    {
        String aux = String.valueOf(valor);
        if(aux.substring(aux.indexOf(".") + 1).length() < 2)
            aux += "0";
        
        return aux;
    }
    
    public static String toRGBCode(Color color )
    {
        return String.format( "#%02X%02X%02X",
            (int)( color.getRed() * 255 ),
            (int)( color.getGreen() * 255 ),
            (int)( color.getBlue() * 255 ) );
    }
    
    public static java.awt.Color toRGB(String hex)
    {
        java.awt.Color cor = new java.awt.Color(
            Integer.valueOf(hex.substring( 1, 3 ), 16 ),
            Integer.valueOf(hex.substring( 3, 5 ), 16 ),
            Integer.valueOf(hex.substring( 5, 7 ), 16 ) );
        return cor;
    }
    
    public static void carregaDesign()
    {
        design = ctrDesign.instancia().carrega();
        geraArquivoCSS();
    }

    public static void geraArquivoCSS()
    {
        //File file = new File("src\\estagio\\utilidades\\CSS\\Style.css");
        File file = new File("C:/Program Files/EstagioJava/Estagio/src\\estagio\\utilidades\\CSS\\Style.css");
        try
        {
            FileWriter writer = new FileWriter(file);
            file.createNewFile();
            try
            {
                writer.write("");
                
                if(!design.isEmpty())
                {
                    writer.write(/*".list-cell \n"
                    + "{\n"
                    + "    -fx-control-inner-background:" + design.get(1) + ";\n"
                    + "    -fx-text-fill:" + design.get(7) + ";\n"
                    + "}\n\n"
                    +*/ ".jfx-list-view \n"
                    + "{\n"
                    + "    -fx-background-color: " + design.get(0) + ";\n"
                    + "}\n\n"
                            
                    + ".list-view:focused > .virtual-flow > .clipped-container > .sheet > .list-cell:filled:selected\n"
                    + "{\n"
                    + "    -fx-background-color: #0096c9;\n"
                    + "    -fx-text-fill: white;\n"      
                    + "}\n\n"
                            
                    + "list-view .list-cell:odd \n"
                    + "{\n"
                    + "    -fx-background-color: " + design.get(1) + ";\n"
                    + "    -fx-text-fill: " + design.get(2) + ";\n"
                    + "}\n\n"
                            
                    + ".list-view .list-cell:even \n"
                    + "{\n"
                    + "    -fx-background-color: " + design.get(0) + ";\n"
                    + "    -fx-text-fill: " + design.get(2) + ";\n"
                    + "}\n\n"
                            
                    + ".table-row-cell\n"
                    + "{\n" 
                    + "   -fx-background-color: transparent;\n"
                    + "}\n\n"
                            
                    + ".jfx-tab-pane .tab-header-background \n"
                    + "{\n"
                    + "    -fx-background-color:" +design.get(1) + "; \n"
                    + "}\n\n"
                            
                    + ".jfx-tab-pane .tab-label\n "
                    + "{\n"
                    + "    -fx-alignment: CENTER;\n"
                    + "    -fx-text-fill: " + design.get(2) + ";\n"
                    + "    -fx-font-weight: bold;\n"
                    + "}\n\n"
                            
                    + ".jfx-date-picker .text-field\n"
                    + "{\n"
                    + "    -jfx-unfocus-color: " + design.get(8) + ";\n"
                    + "    -fx-prompt-text-fill: " + design.get(7) + ";\n"
                    + "    -fx-text-fill:" + design.get(7) + ";\n"
                    + "    -fx-control-inner-background: transparent;\n"
                    + "}\n\n"
                            
                    + ".text-field, .text-area , .jfx-text-field, .jfx-text-area\n"
                    + "{\n"
                    + "    -jfx-unfocus-color: " + design.get(8) + ";\n"
                    + "    -jfx-focus-color: " + design.get(8) + ";\n"
                    + "    -fx-prompt-text-fill: " + design.get(7) + ";\n"
                    + "    -fx-text-fill:" + design.get(7) + ";\n"
                    + "    -fx-font-size: " + design.get(9) + ";\n"
                    + "}\n\n"
                            
                    + ".button , .jfx-button\n"
                    + "{\n"
                    + "    -fx-background-color: " + design.get(3) + ";\n"
                    + "    -fx-text-fill: " + design.get(5) + ";\n"
                    + "    -fx-font-size: " + design.get(6) + ";\n"
                    + "}\n\n"
                            
                    + ".button .rippler\n"
                    + "{\n"
                    + "      -fx-rippler-fill:" + design.get(4) + ";\n"
                    + "}\n\n"
                            
                    + ".radio-button, .jfx-radio-button, .label\n"
                    + "{\n"
                    + "      -fx-text-fill: " + design.get(2) + ";\n"
                    + "}\n\n"
                            
                    + ".combo-box, .jfx-combo-box\n"
                    + "{\n"
                    + "    -fx-background-color: transparent;\n"
                    + "    -fx-text-fill: " + design.get(2) + ";\n"
                    + "    -fx-cell-size: 1.66667em; \n"
                    + "    -jfx-unfocus-color: " + design.get(8) + ";\n"
                    + "    -jfx-focus-color: " + design.get(8) + ";\n"        
                    /*+ "}\n\n"
                    + ".jfx-combo-box{\n"
                    + "    -jfx-unfocus-color: " + design.get(8) + ";\n"
                    + "    -jfx-focus-color: " + design.get(8) + ";\n"
                    + "    -fx-prompt-text-fill: " + design.get(7) + ";\n"
                    */+"}\n\n"
                            
                    + ".table-view .column-header .label \n"
                    + "{\n"
                    + "    -fx-text-fill: " + design.get(2) + ";\n"
                    + "    -fx-font-weight: bold;\n"
                    + "}\n\n"
                    
                    + ".table-view .column-header-background\n"
                    + "{\n"
                    + "    -fx-background-color: transparent;\n"
                    + "    -fx-background-radius: 7px 7px 0px 0px;\n"
                    + "    -fx-background-insets: 0 11px 0 0;\n"
                    + "}\n\n"
                            
                    + ".table-row-cell:filled \n"
                    + "{\n"
                    + "    -fx-background-color: transparent;\n"
                    + "    -fx-text-fill: " + design.get(2) + ";\n"       
                    + "}\n\n"
                            
                    + ".table-row-cell:filled:selected \n"
                    + "{\n"
                    + "    -fx-background-color: #0096c9;\n"
                    + "    -fx-text-fill: " + design.get(2) + ";\n"      
                    + "}\n\n");
                }
                writer.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        catch (IOException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*if(!design.isEmpty())
        {
            try
            {
                FileWriter writer = new FileWriter(file);
                writer.write(".list-cell \n"
                    + "{\n"
                    + "    -fx-control-inner-background:" + design.get(1) + ";\n"
                    + "    -fx-text-fill:" + design.get(7) + ";\n"
                    + "}\n\n"
                    + ".jfx-list-view \n"
                    + "{\n"
                    + "    -fx-background-color: " + design.get(0) + ";\n"
                    + "}\n\n"
                    + "list-view .list-cell:odd \n"
                    + "{\n"
                    + "    -fx-background-color: " + design.get(1) + ";\n"
                    + "}\n\n"
                    + ".list-view .list-cell:even \n"
                    + "{\n"
                    + "    -fx-background-color: " + design.get(0) + ";\n"
                    + "}\n\n"
                    + ".table-row-cell{\n" 
                    + "   -fx-background-color: transparent;\n"
                    + "}\n\n"
                    + ".jfx-tab-pane .tab-header-background \n"
                    + "{\n"
                    + "    -fx-background-color:" +design.get(1) + "; \n"
                    + "}\n\n"
                    + ".jfx-tab-pane .tab-label\n "
                    + "{\n"
                    + "    -fx-alignment: CENTER;\n"
                    + "    -fx-text-fill: " + design.get(2) + ";\n"
                    + "    -fx-font-weight: bold;\n"
                    + "}"
                    + ".jfx-date-picker .text-field\n"
                    + "{\n"
                    + "    -jfx-unfocus-color: " + design.get(8) + ";\n"
                    + "    -fx-prompt-text-fill: " + design.get(7) + ";\n"
                    + "    -fx-text-fill:" + design.get(7) + ";\n"
                    + "    -fx-control-inner-background: transparent;\n"
                    + "}\n\n"
                    + ".text-field, .text-area\n"
                    + "{\n"
                    + "    -jfx-unfocus-color: " + design.get(8) + ";\n"
                    + "    -jfx-focus-color: " + design.get(8) + ";\n"
                    + "    -fx-prompt-text-fill: " + design.get(7) + ";\n"
                    + "    -fx-text-fill:" + design.get(7) + ";\n"
                    + "    -fx-font-size: " + design.get(9) + ";\n"
                    + "}\n\n"
                    + ".button\n"
                    + "{\n"
                    + "    -fx-background-color: " + design.get(3) + ";\n"
                    + "    -fx-text-fill: " + design.get(5) + ";\n"
                    + "    -fx-font-size: " + design.get(6) + ";\n"
                    + "}\n\n"
                    + ".button .rippler{\n"
                    + "      -fx-rippler-fill:" + design.get(4) + ";\n"
                    + "}\n\n"
                    + ".label, .radio-button\n"
                    + "{\n"
                    + "      -fx-text-fill: " + design.get(2) + ";\n"
                    + "}\n\n"
                    + ".combo-box \n"
                    + "{\n"
                    + "    -fx-background-color: transparent;\n"
                    + "    -fx-text-fill: " + design.get(2) + ";\n"
                    + "    -fx-cell-size: 1.66667em; \n"
                    + "    -jfx-unfocus-color: " + design.get(8) + ";\n"
                    + "    -jfx-focus-color: " + design.get(8) + ";\n"        
                    + "}\n\n"
                    + ".jfx-combo-box{\n"
                    + "    -jfx-unfocus-color: " + design.get(8) + ";\n"
                    + "    -jfx-focus-color: " + design.get(8) + ";\n"
                    + "    -fx-prompt-text-fill: " + design.get(7) + ";\n"
                    +"}\n\n");
                writer.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }
    
    public static void setDesign(int paineisPrincipais, List<Node> nodes)
    {
        int i = 0;
        if(design != null && !design.isEmpty())
        {
            for(Node node : nodes)
            {
                if(i < paineisPrincipais)//se for painel principal
                {
                    i++;
                    node.setStyle("-fx-background-color: " + design.get(0) + ";");
                }
                else
                    if(node instanceof Pane)
                    {
                        java.awt.Color cor = toRGB(design.get(1));
                        //"rgba(" + cor.getRed() + "," + cor.getGreen() + "," + cor.getBlue() + ",0.5)"
                        node.setStyle("-fx-background-color: rgba(" + + cor.getRed() + "," + cor.getGreen() + 
                             "," + cor.getBlue() + "," + Double.parseDouble(design.get(10))/100 + ");" +
                            "-fx-background-radius:5px;");
                    }
                    else if(node instanceof JFXButton)
                    {
                        node.setStyle("-fx-background-color: " + design.get(3) + "; -fx-text-fill: " + 
                            design.get(5) + "; -fx-font-size: " + design.get(6));
                        ((JFXButton) node).setRipplerFill(Color.web(design.get(4)));
                    }
                    else if(node instanceof JFXTextField)
                        node.setStyle("-fx-prompt-text-fill: "+ design.get(7) + "; -fx-text-fill: " + 
                            design.get(7) + "; -fx-font-size: " + design.get(9) + ";-jfx-focus-color: " + 
                             design.get(8) + ";-jfx-unfocus-color:" + design.get(8));
                    else if(node instanceof JFXTextArea)
                        node.setStyle("-fx-prompt-text-fill: "+ design.get(7) + "; -fx-text-fill: " + 
                            design.get(7) + "; -fx-font-size: " + design.get(9) + ";-jfx-focus-color: " + 
                             design.get(8) + ";-jfx-unfocus-color:" + design.get(8));
                    else if(node instanceof JFXPasswordField)
                        node.setStyle("-fx-prompt-text-fill: "+ design.get(7) + "; -fx-text-fill: " + 
                            design.get(7) + "; -fx-font-size: " + design.get(9) + ";-jfx-focus-color: " + 
                             design.get(8) + ";-jfx-unfocus-color:" + design.get(8));
                    else if(node instanceof JFXComboBox)
                        node.setStyle("-fx-background-color:" + design.get(2) + ";-fx-prompt-text-fill:" + design.get(7) +
                            ";-jfx-focus-color: " + design.get(8) + ";-jfx-unfocus-color:" + design.get(8));
                    //"-fx-background-color:white;-fx-prompt-text-fill:#6485e8;"
                    else if(node instanceof ListView)
                    {
                        java.awt.Color cor = toRGB(design.get(1));
                        node.setStyle("-fx-background-color: rgba(" + + cor.getRed() + "," + cor.getGreen() + 
                             "," + cor.getBlue() + "," + Double.parseDouble(design.get(10))/100 + ");" +
                            "-fx-background-radius:5px;");
                    }
                    else if(node instanceof JFXListView)
                    {
                        java.awt.Color cor = toRGB(design.get(1));
                        node.setStyle("-fx-background-color: rgba(" + + cor.getRed() + "," + cor.getGreen() + 
                             "," + cor.getBlue() + "," + Double.parseDouble(design.get(10))/100 + ");" +
                            "-fx-background-radius:5px;");
                    }
                    else if(node instanceof FontAwesomeIconView)
                    {   
                        ((FontAwesomeIconView) node).setStyle("-fx-fill: " + design.get(5));
                        ((FontAwesomeIconView) node).setSize(design.get(6));
                    }
                    else if(node instanceof Label)
                        node.setStyle("-fx-text-fill: " + design.get(2));
                    else if(node instanceof JFXRadioButton)
                        node.setStyle("-fx-text-fill: " + design.get(2));
                    else if(node instanceof Circle)
                    {
                        //node.setStyle("-fx-fill: " + design.get(1));
                        java.awt.Color cor = toRGB(design.get(2));
                        //"rgba(" + cor.getRed() + "," + cor.getGreen() + "," + cor.getBlue() + ",0.5)"
                        node.setStyle("-fx-fill: rgba(" + + cor.getRed() + "," + cor.getGreen() + 
                             "," + cor.getBlue() + "," + Double.parseDouble(design.get(10))/100 + ");" +
                            "-fx-background-radius:5px;");
                    }
                    else if(node instanceof Line)
                        node.setStyle("-fx-stroke: " + design.get(2));
            }
        }
    }
    
    public static String getPromptColor()
    {
        return design != null && !design.isEmpty() ? design.get(7) : "";
    }
    
    public static String getCorBotao()
    {
        return design != null && !design.isEmpty() ? design.get(5) : "";
    }
    
    public static String getFundoSecundaria()
    {
        return design != null && !design.isEmpty() ? design.get(1) : "";
    }
    
    public static String getFundoPrimaria()
    {
        return design != null && !design.isEmpty() ? design.get(0) : "";
    }
    
    public static String getFonte()
    {
        return design != null && !design.isEmpty() ? design.get(2) : "";
    }
    
    public static String getFundo2withOpacity()
    {
        if(design != null && !design.isEmpty())
        {
            java.awt.Color cor = toRGB(design.get(1));
            //"rgba(" + cor.getRed() + "," + cor.getGreen() + "," + cor.getBlue() + ",0.5)"
            return "-fx-background-color: rgba(" + + cor.getRed() + "," + cor.getGreen() + 
                 "," + cor.getBlue() + "," + Double.parseDouble(design.get(10))/100 + ");";
        }
        return "";
    }
    
    public static void centerImage(ImageView imageView) 
    {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }
}
