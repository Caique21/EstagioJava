PGDMP     -    9        	        y            estagio    13.3    13.2 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    25639    estagio    DATABASE     g   CREATE DATABASE estagio WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE estagio;
                postgres    false            �            1259    27912    acessos    TABLE     �   CREATE TABLE public.acessos (
    acess_codigo integer NOT NULL,
    acess_data_login timestamp without time zone NOT NULL,
    acess_data_logout timestamp without time zone NOT NULL,
    user_codigo integer NOT NULL
);
    DROP TABLE public.acessos;
       public         heap    postgres    false            �            1259    27910    acessos_acess_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.acessos_acess_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.acessos_acess_codigo_seq;
       public          postgres    false    219            �           0    0    acessos_acess_codigo_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.acessos_acess_codigo_seq OWNED BY public.acessos.acess_codigo;
          public          postgres    false    218            �            1259    27791    cliente    TABLE     �  CREATE TABLE public.cliente (
    cli_codigo integer NOT NULL,
    cli_nome character varying(45) NOT NULL,
    cli_cpf character varying(15) NOT NULL,
    cli_rg character varying(15) NOT NULL,
    cli_datacadastro date NOT NULL,
    cli_email character varying(45),
    cli_ativo boolean NOT NULL,
    cli_alteracao timestamp without time zone NOT NULL,
    ender_codigo integer NOT NULL
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            �            1259    27789    cliente_cli_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.cliente_cli_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.cliente_cli_codigo_seq;
       public          postgres    false    206            �           0    0    cliente_cli_codigo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.cliente_cli_codigo_seq OWNED BY public.cliente.cli_codigo;
          public          postgres    false    205            �            1259    27937    compra    TABLE     �  CREATE TABLE public.compra (
    comp_codigo integer NOT NULL,
    forn_codigo integer,
    cli_codigo integer,
    comp_qtd_parcelas integer NOT NULL,
    comp_valor_total numeric(10,2) NOT NULL,
    comp_ajuste numeric(10,2) NOT NULL,
    comp_data_compra date NOT NULL,
    comp_nota_fiscal character varying(45) NOT NULL,
    comp_data_emissao date NOT NULL,
    comp_vendedor character varying(45)
);
    DROP TABLE public.compra;
       public         heap    postgres    false            �            1259    27935    compra_comp_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.compra_comp_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.compra_comp_codigo_seq;
       public          postgres    false    223            �           0    0    compra_comp_codigo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.compra_comp_codigo_seq OWNED BY public.compra.comp_codigo;
          public          postgres    false    222            �            1259    27757    design    TABLE     X  CREATE TABLE public.design (
    desi_fundo_interface character varying(15) NOT NULL,
    desi_fundo2_interface character varying(15) NOT NULL,
    desi_fonte_interface character varying(15) NOT NULL,
    desi_fundo_botao character varying(15) NOT NULL,
    desi_preenchimento_botao character varying(15) NOT NULL,
    desi_fonte_botao character varying(15) NOT NULL,
    desi_tamanho_botao integer NOT NULL,
    desi_fonte_entrada character varying(15) NOT NULL,
    desi_foco_entrada character varying(15) NOT NULL,
    desi_tamanho_entrada integer NOT NULL,
    desi_opacidade integer NOT NULL
);
    DROP TABLE public.design;
       public         heap    postgres    false            �            1259    28085    despesa    TABLE     #  CREATE TABLE public.despesa (
    desp_codigo integer NOT NULL,
    desp_nome character varying(45) NOT NULL,
    desp_fixo boolean NOT NULL,
    desp_preco numeric(10,2) NOT NULL,
    desp_data_vencimento date NOT NULL,
    desp_descricao character varying(45),
    trans_codigo integer
);
    DROP TABLE public.despesa;
       public         heap    postgres    false            �            1259    28083    despesa_desp_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.despesa_desp_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.despesa_desp_codigo_seq;
       public          postgres    false    238            �           0    0    despesa_desp_codigo_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.despesa_desp_codigo_seq OWNED BY public.despesa.desp_codigo;
          public          postgres    false    237            �            1259    27762    endereco    TABLE     q  CREATE TABLE public.endereco (
    ender_codigo integer NOT NULL,
    ender_rua character varying(45) NOT NULL,
    ender_bairro character varying(45),
    ender_numero integer NOT NULL,
    ender_cidade character varying(45) NOT NULL,
    ender_estado character varying(45) NOT NULL,
    ender_cep character varying(13),
    ender_complemento character varying(45)
);
    DROP TABLE public.endereco;
       public         heap    postgres    false            �            1259    27760    endereco_ender_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.endereco_ender_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.endereco_ender_codigo_seq;
       public          postgres    false    202            �           0    0    endereco_ender_codigo_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.endereco_ender_codigo_seq OWNED BY public.endereco.ender_codigo;
          public          postgres    false    201            �            1259    27776    endereco_parametrizacao    TABLE     �   CREATE TABLE public.endereco_parametrizacao (
    para_nome character varying(50) NOT NULL,
    ender_codigo integer NOT NULL
);
 +   DROP TABLE public.endereco_parametrizacao;
       public         heap    postgres    false            �            1259    27929    forma_pagamento    TABLE     �   CREATE TABLE public.forma_pagamento (
    form_pagamento_codigo integer NOT NULL,
    form_pagamento_nome character varying(45) NOT NULL,
    form_pagamento_descricao character varying(90)
);
 #   DROP TABLE public.forma_pagamento;
       public         heap    postgres    false            �            1259    27927 )   forma_pagamento_form_pagamento_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.forma_pagamento_form_pagamento_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 @   DROP SEQUENCE public.forma_pagamento_form_pagamento_codigo_seq;
       public          postgres    false    221            �           0    0 )   forma_pagamento_form_pagamento_codigo_seq    SEQUENCE OWNED BY     w   ALTER SEQUENCE public.forma_pagamento_form_pagamento_codigo_seq OWNED BY public.forma_pagamento.form_pagamento_codigo;
          public          postgres    false    220            �            1259    27824 
   fornecedor    TABLE     D  CREATE TABLE public.fornecedor (
    forn_codigo integer NOT NULL,
    forn_nome character varying(45) NOT NULL,
    forn_cnpj character varying(18) NOT NULL,
    forn_email character varying(45),
    forn_ativo boolean NOT NULL,
    forn_alteracao timestamp without time zone NOT NULL,
    ender_codigo integer NOT NULL
);
    DROP TABLE public.fornecedor;
       public         heap    postgres    false            �            1259    27822    fornecedor_forn_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.fornecedor_forn_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.fornecedor_forn_codigo_seq;
       public          postgres    false    210            �           0    0    fornecedor_forn_codigo_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.fornecedor_forn_codigo_seq OWNED BY public.fornecedor.forn_codigo;
          public          postgres    false    209            �            1259    27806    funcionario    TABLE     �  CREATE TABLE public.funcionario (
    func_codigo integer NOT NULL,
    func_nome character varying(45) NOT NULL,
    func_cpf character varying(15) NOT NULL,
    func_rg character varying(15) NOT NULL,
    func_email character varying(45),
    func_datacadastro date NOT NULL,
    func_ativo boolean NOT NULL,
    func_alteracao timestamp without time zone NOT NULL,
    ender_codigo integer NOT NULL,
    func_vencimento_cnh date,
    func_cnh_frente bytea,
    func_cnh_verso bytea
);
    DROP TABLE public.funcionario;
       public         heap    postgres    false            �            1259    27804    funcionario_func_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.funcionario_func_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.funcionario_func_codigo_seq;
       public          postgres    false    208            �           0    0    funcionario_func_codigo_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.funcionario_func_codigo_seq OWNED BY public.funcionario.func_codigo;
          public          postgres    false    207            �            1259    27865    marca    TABLE     p   CREATE TABLE public.marca (
    marca_codigo integer NOT NULL,
    marca_nome character varying(45) NOT NULL
);
    DROP TABLE public.marca;
       public         heap    postgres    false            �            1259    27874    modelo    TABLE     �   CREATE TABLE public.modelo (
    modelo_codigo integer NOT NULL,
    modelo_nome character varying(45) NOT NULL,
    marca_codigo integer NOT NULL
);
    DROP TABLE public.modelo;
       public         heap    postgres    false            �            1259    27872    modelo_modelo_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.modelo_modelo_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.modelo_modelo_codigo_seq;
       public          postgres    false    213            �           0    0    modelo_modelo_codigo_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.modelo_modelo_codigo_seq OWNED BY public.modelo.modelo_codigo;
          public          postgres    false    212            �            1259    28098 	   pagamento    TABLE       CREATE TABLE public.pagamento (
    pag_codigo integer NOT NULL,
    pag_data date NOT NULL,
    pag_valor numeric(10,2) NOT NULL,
    parc_codigo integer,
    pag_forma_pagamento character varying(12) NOT NULL,
    pag_ativo boolean NOT NULL,
    desp_codigo integer
);
    DROP TABLE public.pagamento;
       public         heap    postgres    false            �            1259    28096    pagamento_pag_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.pagamento_pag_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.pagamento_pag_codigo_seq;
       public          postgres    false    240            �           0    0    pagamento_pag_codigo_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.pagamento_pag_codigo_seq OWNED BY public.pagamento.pag_codigo;
          public          postgres    false    239            �            1259    27768    parametrizacao    TABLE     "  CREATE TABLE public.parametrizacao (
    para_nome character varying(50) NOT NULL,
    para_fantasia character varying(50) NOT NULL,
    para_logogrande bytea,
    para_logopequeno bytea,
    para_email character varying(50) NOT NULL,
    para_razaosocial character varying(50) NOT NULL
);
 "   DROP TABLE public.parametrizacao;
       public         heap    postgres    false            �            1259    27988    parcela    TABLE     A  CREATE TABLE public.parcela (
    parc_codigo integer NOT NULL,
    parc_status integer,
    parc_datavencimento date NOT NULL,
    parc_numero integer NOT NULL,
    parc_datapagamento date,
    parc_valorpago numeric(6,2),
    parc_valorparcela numeric(10,2) NOT NULL,
    ven_codigo integer,
    comp_codigo integer
);
    DROP TABLE public.parcela;
       public         heap    postgres    false            �            1259    27986    parcela_parc_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.parcela_parc_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.parcela_parc_codigo_seq;
       public          postgres    false    228            �           0    0    parcela_parc_codigo_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.parcela_parc_codigo_seq OWNED BY public.parcela.parc_codigo;
          public          postgres    false    227            �            1259    28021    recebimento    TABLE     �   CREATE TABLE public.recebimento (
    rec_codigo integer NOT NULL,
    rec_data date NOT NULL,
    rec_valor numeric(10,2) NOT NULL,
    parc_codigo integer,
    rec_forma_pagamento character varying(12) NOT NULL,
    rec_ativo boolean NOT NULL
);
    DROP TABLE public.recebimento;
       public         heap    postgres    false            �            1259    28019    recebimento_rec_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.recebimento_rec_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.recebimento_rec_codigo_seq;
       public          postgres    false    231            �           0    0    recebimento_rec_codigo_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.recebimento_rec_codigo_seq OWNED BY public.recebimento.rec_codigo;
          public          postgres    false    230            �            1259    28116    registro_pagamento    TABLE     �   CREATE TABLE public.registro_pagamento (
    reg_pag_codigo integer NOT NULL,
    pag_codigo integer NOT NULL,
    reg_pag_relatorio character varying(200) NOT NULL,
    reg_pag_data date NOT NULL,
    func_codigo integer NOT NULL
);
 &   DROP TABLE public.registro_pagamento;
       public         heap    postgres    false            �            1259    28114 %   registro_pagamento_reg_pag_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.registro_pagamento_reg_pag_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 <   DROP SEQUENCE public.registro_pagamento_reg_pag_codigo_seq;
       public          postgres    false    242            �           0    0 %   registro_pagamento_reg_pag_codigo_seq    SEQUENCE OWNED BY     o   ALTER SEQUENCE public.registro_pagamento_reg_pag_codigo_seq OWNED BY public.registro_pagamento.reg_pag_codigo;
          public          postgres    false    241            �            1259    28034    registro_recebimento    TABLE     �   CREATE TABLE public.registro_recebimento (
    reg_rec_codigo integer NOT NULL,
    rec_codigo integer NOT NULL,
    reg_rec_relatorio character varying(200) NOT NULL,
    reg_rec_data date NOT NULL,
    func_codigo integer NOT NULL
);
 (   DROP TABLE public.registro_recebimento;
       public         heap    postgres    false            �            1259    28032 '   registro_recebimento_reg_rec_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.registro_recebimento_reg_rec_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 >   DROP SEQUENCE public.registro_recebimento_reg_rec_codigo_seq;
       public          postgres    false    233            �           0    0 '   registro_recebimento_reg_rec_codigo_seq    SEQUENCE OWNED BY     s   ALTER SEQUENCE public.registro_recebimento_reg_rec_codigo_seq OWNED BY public.registro_recebimento.reg_rec_codigo;
          public          postgres    false    232            �            1259    28137    telefone    TABLE     �   CREATE TABLE public.telefone (
    tel_codigo integer NOT NULL,
    tel_numero character varying(11),
    cli_codigo integer,
    func_codigo integer,
    forn_codigo integer,
    para_nome character varying(50)
);
    DROP TABLE public.telefone;
       public         heap    postgres    false            �            1259    28135    telefone_tel_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.telefone_tel_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.telefone_tel_codigo_seq;
       public          postgres    false    244            �           0    0    telefone_tel_codigo_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.telefone_tel_codigo_seq OWNED BY public.telefone.tel_codigo;
          public          postgres    false    243            �            1259    28052 
   transporte    TABLE     R  CREATE TABLE public.transporte (
    trans_codigo integer NOT NULL,
    func_codigo integer NOT NULL,
    vei_codigo integer NOT NULL,
    trans_data_saida date NOT NULL,
    trans_data_chegada date,
    trans_status character varying(45) NOT NULL,
    trans_tipo character varying(45) NOT NULL,
    trans_data_alteracao date NOT NULL
);
    DROP TABLE public.transporte;
       public         heap    postgres    false            �            1259    28050    transporte_trans_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.transporte_trans_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.transporte_trans_codigo_seq;
       public          postgres    false    235            �           0    0    transporte_trans_codigo_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.transporte_trans_codigo_seq OWNED BY public.transporte.trans_codigo;
          public          postgres    false    234            �            1259    27897    usuario    TABLE       CREATE TABLE public.usuario (
    user_codigo integer NOT NULL,
    user_nome character varying(45) NOT NULL,
    user_senha character varying(45) NOT NULL,
    user_nivel character varying(5) NOT NULL,
    user_ativo boolean NOT NULL,
    func_codigo integer
);
    DROP TABLE public.usuario;
       public         heap    postgres    false            �            1259    27895    usuario_user_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_user_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.usuario_user_codigo_seq;
       public          postgres    false    217            �           0    0    usuario_user_codigo_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.usuario_user_codigo_seq OWNED BY public.usuario.user_codigo;
          public          postgres    false    216            �            1259    27887    veiculo    TABLE     [  CREATE TABLE public.veiculo (
    vei_codigo integer NOT NULL,
    vei_chassi character varying(45),
    vei_placa character varying(8),
    vei_modelo character varying(45) NOT NULL,
    vei_marca character varying(45) NOT NULL,
    vei_ano integer NOT NULL,
    vei_cor character varying(45) NOT NULL,
    vei_descricao character varying(45)
);
    DROP TABLE public.veiculo;
       public         heap    postgres    false            �            1259    27953    veiculo_compra    TABLE     �   CREATE TABLE public.veiculo_compra (
    comp_codigo integer NOT NULL,
    vei_codigo integer NOT NULL,
    vei_comp_valor numeric(10,2) NOT NULL
);
 "   DROP TABLE public.veiculo_compra;
       public         heap    postgres    false            �            1259    27885    veiculo_vei_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.veiculo_vei_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.veiculo_vei_codigo_seq;
       public          postgres    false    215            �           0    0    veiculo_vei_codigo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.veiculo_vei_codigo_seq OWNED BY public.veiculo.vei_codigo;
          public          postgres    false    214            �            1259    28004    veiculo_venda    TABLE     �   CREATE TABLE public.veiculo_venda (
    ven_codigo integer NOT NULL,
    vei_codigo integer NOT NULL,
    vei_ven_valor numeric(10,2) NOT NULL
);
 !   DROP TABLE public.veiculo_venda;
       public         heap    postgres    false            �            1259    28068    veiculos_transportados    TABLE     s   CREATE TABLE public.veiculos_transportados (
    trans_codigo integer NOT NULL,
    vei_codigo integer NOT NULL
);
 *   DROP TABLE public.veiculos_transportados;
       public         heap    postgres    false            �            1259    27970    venda    TABLE     e  CREATE TABLE public.venda (
    ven_codigo integer NOT NULL,
    cli_codigo integer,
    forn_codigo integer,
    ven_qtd_parcelas integer NOT NULL,
    ven_valor_total numeric(10,2) NOT NULL,
    ven_ajuste numeric(10,2) NOT NULL,
    ven_data_compra date NOT NULL,
    ven_nota_fiscal character varying(45) NOT NULL,
    ven_data_emissao date NOT NULL
);
    DROP TABLE public.venda;
       public         heap    postgres    false            �            1259    27968    venda_ven_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.venda_ven_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.venda_ven_codigo_seq;
       public          postgres    false    226            �           0    0    venda_ven_codigo_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.venda_ven_codigo_seq OWNED BY public.venda.ven_codigo;
          public          postgres    false    225            �           2604    27915    acessos acess_codigo    DEFAULT     |   ALTER TABLE ONLY public.acessos ALTER COLUMN acess_codigo SET DEFAULT nextval('public.acessos_acess_codigo_seq'::regclass);
 C   ALTER TABLE public.acessos ALTER COLUMN acess_codigo DROP DEFAULT;
       public          postgres    false    218    219    219            �           2604    27794    cliente cli_codigo    DEFAULT     x   ALTER TABLE ONLY public.cliente ALTER COLUMN cli_codigo SET DEFAULT nextval('public.cliente_cli_codigo_seq'::regclass);
 A   ALTER TABLE public.cliente ALTER COLUMN cli_codigo DROP DEFAULT;
       public          postgres    false    205    206    206            �           2604    27940    compra comp_codigo    DEFAULT     x   ALTER TABLE ONLY public.compra ALTER COLUMN comp_codigo SET DEFAULT nextval('public.compra_comp_codigo_seq'::regclass);
 A   ALTER TABLE public.compra ALTER COLUMN comp_codigo DROP DEFAULT;
       public          postgres    false    222    223    223            �           2604    28088    despesa desp_codigo    DEFAULT     z   ALTER TABLE ONLY public.despesa ALTER COLUMN desp_codigo SET DEFAULT nextval('public.despesa_desp_codigo_seq'::regclass);
 B   ALTER TABLE public.despesa ALTER COLUMN desp_codigo DROP DEFAULT;
       public          postgres    false    238    237    238            �           2604    27765    endereco ender_codigo    DEFAULT     ~   ALTER TABLE ONLY public.endereco ALTER COLUMN ender_codigo SET DEFAULT nextval('public.endereco_ender_codigo_seq'::regclass);
 D   ALTER TABLE public.endereco ALTER COLUMN ender_codigo DROP DEFAULT;
       public          postgres    false    201    202    202            �           2604    27932 %   forma_pagamento form_pagamento_codigo    DEFAULT     �   ALTER TABLE ONLY public.forma_pagamento ALTER COLUMN form_pagamento_codigo SET DEFAULT nextval('public.forma_pagamento_form_pagamento_codigo_seq'::regclass);
 T   ALTER TABLE public.forma_pagamento ALTER COLUMN form_pagamento_codigo DROP DEFAULT;
       public          postgres    false    220    221    221            �           2604    27827    fornecedor forn_codigo    DEFAULT     �   ALTER TABLE ONLY public.fornecedor ALTER COLUMN forn_codigo SET DEFAULT nextval('public.fornecedor_forn_codigo_seq'::regclass);
 E   ALTER TABLE public.fornecedor ALTER COLUMN forn_codigo DROP DEFAULT;
       public          postgres    false    210    209    210            �           2604    27809    funcionario func_codigo    DEFAULT     �   ALTER TABLE ONLY public.funcionario ALTER COLUMN func_codigo SET DEFAULT nextval('public.funcionario_func_codigo_seq'::regclass);
 F   ALTER TABLE public.funcionario ALTER COLUMN func_codigo DROP DEFAULT;
       public          postgres    false    207    208    208            �           2604    27877    modelo modelo_codigo    DEFAULT     |   ALTER TABLE ONLY public.modelo ALTER COLUMN modelo_codigo SET DEFAULT nextval('public.modelo_modelo_codigo_seq'::regclass);
 C   ALTER TABLE public.modelo ALTER COLUMN modelo_codigo DROP DEFAULT;
       public          postgres    false    213    212    213            �           2604    28101    pagamento pag_codigo    DEFAULT     |   ALTER TABLE ONLY public.pagamento ALTER COLUMN pag_codigo SET DEFAULT nextval('public.pagamento_pag_codigo_seq'::regclass);
 C   ALTER TABLE public.pagamento ALTER COLUMN pag_codigo DROP DEFAULT;
       public          postgres    false    240    239    240            �           2604    27991    parcela parc_codigo    DEFAULT     z   ALTER TABLE ONLY public.parcela ALTER COLUMN parc_codigo SET DEFAULT nextval('public.parcela_parc_codigo_seq'::regclass);
 B   ALTER TABLE public.parcela ALTER COLUMN parc_codigo DROP DEFAULT;
       public          postgres    false    227    228    228            �           2604    28024    recebimento rec_codigo    DEFAULT     �   ALTER TABLE ONLY public.recebimento ALTER COLUMN rec_codigo SET DEFAULT nextval('public.recebimento_rec_codigo_seq'::regclass);
 E   ALTER TABLE public.recebimento ALTER COLUMN rec_codigo DROP DEFAULT;
       public          postgres    false    231    230    231            �           2604    28119 !   registro_pagamento reg_pag_codigo    DEFAULT     �   ALTER TABLE ONLY public.registro_pagamento ALTER COLUMN reg_pag_codigo SET DEFAULT nextval('public.registro_pagamento_reg_pag_codigo_seq'::regclass);
 P   ALTER TABLE public.registro_pagamento ALTER COLUMN reg_pag_codigo DROP DEFAULT;
       public          postgres    false    242    241    242            �           2604    28037 #   registro_recebimento reg_rec_codigo    DEFAULT     �   ALTER TABLE ONLY public.registro_recebimento ALTER COLUMN reg_rec_codigo SET DEFAULT nextval('public.registro_recebimento_reg_rec_codigo_seq'::regclass);
 R   ALTER TABLE public.registro_recebimento ALTER COLUMN reg_rec_codigo DROP DEFAULT;
       public          postgres    false    233    232    233            �           2604    28140    telefone tel_codigo    DEFAULT     z   ALTER TABLE ONLY public.telefone ALTER COLUMN tel_codigo SET DEFAULT nextval('public.telefone_tel_codigo_seq'::regclass);
 B   ALTER TABLE public.telefone ALTER COLUMN tel_codigo DROP DEFAULT;
       public          postgres    false    243    244    244            �           2604    28055    transporte trans_codigo    DEFAULT     �   ALTER TABLE ONLY public.transporte ALTER COLUMN trans_codigo SET DEFAULT nextval('public.transporte_trans_codigo_seq'::regclass);
 F   ALTER TABLE public.transporte ALTER COLUMN trans_codigo DROP DEFAULT;
       public          postgres    false    234    235    235            �           2604    27900    usuario user_codigo    DEFAULT     z   ALTER TABLE ONLY public.usuario ALTER COLUMN user_codigo SET DEFAULT nextval('public.usuario_user_codigo_seq'::regclass);
 B   ALTER TABLE public.usuario ALTER COLUMN user_codigo DROP DEFAULT;
       public          postgres    false    217    216    217            �           2604    27890    veiculo vei_codigo    DEFAULT     x   ALTER TABLE ONLY public.veiculo ALTER COLUMN vei_codigo SET DEFAULT nextval('public.veiculo_vei_codigo_seq'::regclass);
 A   ALTER TABLE public.veiculo ALTER COLUMN vei_codigo DROP DEFAULT;
       public          postgres    false    215    214    215            �           2604    27973    venda ven_codigo    DEFAULT     t   ALTER TABLE ONLY public.venda ALTER COLUMN ven_codigo SET DEFAULT nextval('public.venda_ven_codigo_seq'::regclass);
 ?   ALTER TABLE public.venda ALTER COLUMN ven_codigo DROP DEFAULT;
       public          postgres    false    225    226    226            �          0    27912    acessos 
   TABLE DATA           a   COPY public.acessos (acess_codigo, acess_data_login, acess_data_logout, user_codigo) FROM stdin;
    public          postgres    false    219   �      �          0    27791    cliente 
   TABLE DATA           �   COPY public.cliente (cli_codigo, cli_nome, cli_cpf, cli_rg, cli_datacadastro, cli_email, cli_ativo, cli_alteracao, ender_codigo) FROM stdin;
    public          postgres    false    206         �          0    27937    compra 
   TABLE DATA           �   COPY public.compra (comp_codigo, forn_codigo, cli_codigo, comp_qtd_parcelas, comp_valor_total, comp_ajuste, comp_data_compra, comp_nota_fiscal, comp_data_emissao, comp_vendedor) FROM stdin;
    public          postgres    false    223   w      �          0    27757    design 
   TABLE DATA             COPY public.design (desi_fundo_interface, desi_fundo2_interface, desi_fonte_interface, desi_fundo_botao, desi_preenchimento_botao, desi_fonte_botao, desi_tamanho_botao, desi_fonte_entrada, desi_foco_entrada, desi_tamanho_entrada, desi_opacidade) FROM stdin;
    public          postgres    false    200   �      �          0    28085    despesa 
   TABLE DATA           �   COPY public.despesa (desp_codigo, desp_nome, desp_fixo, desp_preco, desp_data_vencimento, desp_descricao, trans_codigo) FROM stdin;
    public          postgres    false    238   �      �          0    27762    endereco 
   TABLE DATA           �   COPY public.endereco (ender_codigo, ender_rua, ender_bairro, ender_numero, ender_cidade, ender_estado, ender_cep, ender_complemento) FROM stdin;
    public          postgres    false    202   �      �          0    27776    endereco_parametrizacao 
   TABLE DATA           J   COPY public.endereco_parametrizacao (para_nome, ender_codigo) FROM stdin;
    public          postgres    false    204   �      �          0    27929    forma_pagamento 
   TABLE DATA           o   COPY public.forma_pagamento (form_pagamento_codigo, form_pagamento_nome, form_pagamento_descricao) FROM stdin;
    public          postgres    false    221         �          0    27824 
   fornecedor 
   TABLE DATA           }   COPY public.fornecedor (forn_codigo, forn_nome, forn_cnpj, forn_email, forn_ativo, forn_alteracao, ender_codigo) FROM stdin;
    public          postgres    false    210   *      �          0    27806    funcionario 
   TABLE DATA           �   COPY public.funcionario (func_codigo, func_nome, func_cpf, func_rg, func_email, func_datacadastro, func_ativo, func_alteracao, ender_codigo, func_vencimento_cnh, func_cnh_frente, func_cnh_verso) FROM stdin;
    public          postgres    false    208   G      �          0    27865    marca 
   TABLE DATA           9   COPY public.marca (marca_codigo, marca_nome) FROM stdin;
    public          postgres    false    211   d      �          0    27874    modelo 
   TABLE DATA           J   COPY public.modelo (modelo_codigo, modelo_nome, marca_codigo) FROM stdin;
    public          postgres    false    213   �      �          0    28098 	   pagamento 
   TABLE DATA           ~   COPY public.pagamento (pag_codigo, pag_data, pag_valor, parc_codigo, pag_forma_pagamento, pag_ativo, desp_codigo) FROM stdin;
    public          postgres    false    240   �      �          0    27768    parametrizacao 
   TABLE DATA           �   COPY public.parametrizacao (para_nome, para_fantasia, para_logogrande, para_logopequeno, para_email, para_razaosocial) FROM stdin;
    public          postgres    false    203   �      �          0    27988    parcela 
   TABLE DATA           �   COPY public.parcela (parc_codigo, parc_status, parc_datavencimento, parc_numero, parc_datapagamento, parc_valorpago, parc_valorparcela, ven_codigo, comp_codigo) FROM stdin;
    public          postgres    false    228   �\      �          0    28021    recebimento 
   TABLE DATA           s   COPY public.recebimento (rec_codigo, rec_data, rec_valor, parc_codigo, rec_forma_pagamento, rec_ativo) FROM stdin;
    public          postgres    false    231   �\      �          0    28116    registro_pagamento 
   TABLE DATA           v   COPY public.registro_pagamento (reg_pag_codigo, pag_codigo, reg_pag_relatorio, reg_pag_data, func_codigo) FROM stdin;
    public          postgres    false    242   �\      �          0    28034    registro_recebimento 
   TABLE DATA           x   COPY public.registro_recebimento (reg_rec_codigo, rec_codigo, reg_rec_relatorio, reg_rec_data, func_codigo) FROM stdin;
    public          postgres    false    233   �\      �          0    28137    telefone 
   TABLE DATA           k   COPY public.telefone (tel_codigo, tel_numero, cli_codigo, func_codigo, forn_codigo, para_nome) FROM stdin;
    public          postgres    false    244   ]      �          0    28052 
   transporte 
   TABLE DATA           �   COPY public.transporte (trans_codigo, func_codigo, vei_codigo, trans_data_saida, trans_data_chegada, trans_status, trans_tipo, trans_data_alteracao) FROM stdin;
    public          postgres    false    235   `]      �          0    27897    usuario 
   TABLE DATA           j   COPY public.usuario (user_codigo, user_nome, user_senha, user_nivel, user_ativo, func_codigo) FROM stdin;
    public          postgres    false    217   }]      �          0    27887    veiculo 
   TABLE DATA           |   COPY public.veiculo (vei_codigo, vei_chassi, vei_placa, vei_modelo, vei_marca, vei_ano, vei_cor, vei_descricao) FROM stdin;
    public          postgres    false    215   �]      �          0    27953    veiculo_compra 
   TABLE DATA           Q   COPY public.veiculo_compra (comp_codigo, vei_codigo, vei_comp_valor) FROM stdin;
    public          postgres    false    224   �]      �          0    28004    veiculo_venda 
   TABLE DATA           N   COPY public.veiculo_venda (ven_codigo, vei_codigo, vei_ven_valor) FROM stdin;
    public          postgres    false    229   �]      �          0    28068    veiculos_transportados 
   TABLE DATA           J   COPY public.veiculos_transportados (trans_codigo, vei_codigo) FROM stdin;
    public          postgres    false    236   ^      �          0    27970    venda 
   TABLE DATA           �   COPY public.venda (ven_codigo, cli_codigo, forn_codigo, ven_qtd_parcelas, ven_valor_total, ven_ajuste, ven_data_compra, ven_nota_fiscal, ven_data_emissao) FROM stdin;
    public          postgres    false    226   ^      �           0    0    acessos_acess_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.acessos_acess_codigo_seq', 2, true);
          public          postgres    false    218            �           0    0    cliente_cli_codigo_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.cliente_cli_codigo_seq', 2, true);
          public          postgres    false    205            �           0    0    compra_comp_codigo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.compra_comp_codigo_seq', 1, false);
          public          postgres    false    222            �           0    0    despesa_desp_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.despesa_desp_codigo_seq', 1, false);
          public          postgres    false    237            �           0    0    endereco_ender_codigo_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.endereco_ender_codigo_seq', 4, true);
          public          postgres    false    201            �           0    0 )   forma_pagamento_form_pagamento_codigo_seq    SEQUENCE SET     X   SELECT pg_catalog.setval('public.forma_pagamento_form_pagamento_codigo_seq', 1, false);
          public          postgres    false    220            �           0    0    fornecedor_forn_codigo_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.fornecedor_forn_codigo_seq', 1, false);
          public          postgres    false    209            �           0    0    funcionario_func_codigo_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.funcionario_func_codigo_seq', 1, false);
          public          postgres    false    207            �           0    0    modelo_modelo_codigo_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.modelo_modelo_codigo_seq', 1, false);
          public          postgres    false    212            �           0    0    pagamento_pag_codigo_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.pagamento_pag_codigo_seq', 1, false);
          public          postgres    false    239            �           0    0    parcela_parc_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.parcela_parc_codigo_seq', 1, false);
          public          postgres    false    227            �           0    0    recebimento_rec_codigo_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.recebimento_rec_codigo_seq', 1, false);
          public          postgres    false    230            �           0    0 %   registro_pagamento_reg_pag_codigo_seq    SEQUENCE SET     T   SELECT pg_catalog.setval('public.registro_pagamento_reg_pag_codigo_seq', 1, false);
          public          postgres    false    241            �           0    0 '   registro_recebimento_reg_rec_codigo_seq    SEQUENCE SET     V   SELECT pg_catalog.setval('public.registro_recebimento_reg_rec_codigo_seq', 1, false);
          public          postgres    false    232            �           0    0    telefone_tel_codigo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.telefone_tel_codigo_seq', 5, true);
          public          postgres    false    243            �           0    0    transporte_trans_codigo_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.transporte_trans_codigo_seq', 1, false);
          public          postgres    false    234            �           0    0    usuario_user_codigo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.usuario_user_codigo_seq', 1, true);
          public          postgres    false    216            �           0    0    veiculo_vei_codigo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.veiculo_vei_codigo_seq', 1, false);
          public          postgres    false    214            �           0    0    venda_ven_codigo_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.venda_ven_codigo_seq', 1, false);
          public          postgres    false    225            �           2606    27919 $   acessos acessos_acess_data_login_key 
   CONSTRAINT     k   ALTER TABLE ONLY public.acessos
    ADD CONSTRAINT acessos_acess_data_login_key UNIQUE (acess_data_login);
 N   ALTER TABLE ONLY public.acessos DROP CONSTRAINT acessos_acess_data_login_key;
       public            postgres    false    219            �           2606    27921 %   acessos acessos_acess_data_logout_key 
   CONSTRAINT     m   ALTER TABLE ONLY public.acessos
    ADD CONSTRAINT acessos_acess_data_logout_key UNIQUE (acess_data_logout);
 O   ALTER TABLE ONLY public.acessos DROP CONSTRAINT acessos_acess_data_logout_key;
       public            postgres    false    219            �           2606    27798    cliente cliente_cli_cpf_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_cli_cpf_key UNIQUE (cli_cpf);
 E   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_cli_cpf_key;
       public            postgres    false    206            �           2606    27831 #   fornecedor fornecedor_forn_cnpj_key 
   CONSTRAINT     c   ALTER TABLE ONLY public.fornecedor
    ADD CONSTRAINT fornecedor_forn_cnpj_key UNIQUE (forn_cnpj);
 M   ALTER TABLE ONLY public.fornecedor DROP CONSTRAINT fornecedor_forn_cnpj_key;
       public            postgres    false    210            �           2606    27816 $   funcionario funcionario_func_cpf_key 
   CONSTRAINT     c   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_func_cpf_key UNIQUE (func_cpf);
 N   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT funcionario_func_cpf_key;
       public            postgres    false    208            �           2606    27871    marca marca_marca_nome_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.marca
    ADD CONSTRAINT marca_marca_nome_key UNIQUE (marca_nome);
 D   ALTER TABLE ONLY public.marca DROP CONSTRAINT marca_marca_nome_key;
       public            postgres    false    211            �           2606    27917    acessos pk_acesso 
   CONSTRAINT     Y   ALTER TABLE ONLY public.acessos
    ADD CONSTRAINT pk_acesso PRIMARY KEY (acess_codigo);
 ;   ALTER TABLE ONLY public.acessos DROP CONSTRAINT pk_acesso;
       public            postgres    false    219            �           2606    27796    cliente pk_cliente 
   CONSTRAINT     X   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT pk_cliente PRIMARY KEY (cli_codigo);
 <   ALTER TABLE ONLY public.cliente DROP CONSTRAINT pk_cliente;
       public            postgres    false    206            �           2606    27942    compra pk_compra 
   CONSTRAINT     W   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT pk_compra PRIMARY KEY (comp_codigo);
 :   ALTER TABLE ONLY public.compra DROP CONSTRAINT pk_compra;
       public            postgres    false    223            �           2606    28090    despesa pk_despesa 
   CONSTRAINT     Y   ALTER TABLE ONLY public.despesa
    ADD CONSTRAINT pk_despesa PRIMARY KEY (desp_codigo);
 <   ALTER TABLE ONLY public.despesa DROP CONSTRAINT pk_despesa;
       public            postgres    false    238            �           2606    27767    endereco pk_endereco 
   CONSTRAINT     \   ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT pk_endereco PRIMARY KEY (ender_codigo);
 >   ALTER TABLE ONLY public.endereco DROP CONSTRAINT pk_endereco;
       public            postgres    false    202            �           2606    27934 "   forma_pagamento pk_forma_pagamento 
   CONSTRAINT     s   ALTER TABLE ONLY public.forma_pagamento
    ADD CONSTRAINT pk_forma_pagamento PRIMARY KEY (form_pagamento_codigo);
 L   ALTER TABLE ONLY public.forma_pagamento DROP CONSTRAINT pk_forma_pagamento;
       public            postgres    false    221            �           2606    27829    fornecedor pk_fornecedor 
   CONSTRAINT     _   ALTER TABLE ONLY public.fornecedor
    ADD CONSTRAINT pk_fornecedor PRIMARY KEY (forn_codigo);
 B   ALTER TABLE ONLY public.fornecedor DROP CONSTRAINT pk_fornecedor;
       public            postgres    false    210            �           2606    27814    funcionario pk_funcionario 
   CONSTRAINT     a   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT pk_funcionario PRIMARY KEY (func_codigo);
 D   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT pk_funcionario;
       public            postgres    false    208            �           2606    27869    marca pk_marca 
   CONSTRAINT     V   ALTER TABLE ONLY public.marca
    ADD CONSTRAINT pk_marca PRIMARY KEY (marca_codigo);
 8   ALTER TABLE ONLY public.marca DROP CONSTRAINT pk_marca;
       public            postgres    false    211            �           2606    27879    modelo pk_modelo 
   CONSTRAINT     Y   ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT pk_modelo PRIMARY KEY (modelo_codigo);
 :   ALTER TABLE ONLY public.modelo DROP CONSTRAINT pk_modelo;
       public            postgres    false    213            �           2606    28103    pagamento pk_pagamento 
   CONSTRAINT     \   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT pk_pagamento PRIMARY KEY (pag_codigo);
 @   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT pk_pagamento;
       public            postgres    false    240            �           2606    27775     parametrizacao pk_parametrizacao 
   CONSTRAINT     e   ALTER TABLE ONLY public.parametrizacao
    ADD CONSTRAINT pk_parametrizacao PRIMARY KEY (para_nome);
 J   ALTER TABLE ONLY public.parametrizacao DROP CONSTRAINT pk_parametrizacao;
       public            postgres    false    203            �           2606    27993    parcela pk_parcela 
   CONSTRAINT     Y   ALTER TABLE ONLY public.parcela
    ADD CONSTRAINT pk_parcela PRIMARY KEY (parc_codigo);
 <   ALTER TABLE ONLY public.parcela DROP CONSTRAINT pk_parcela;
       public            postgres    false    228            �           2606    28026    recebimento pk_recebimento 
   CONSTRAINT     `   ALTER TABLE ONLY public.recebimento
    ADD CONSTRAINT pk_recebimento PRIMARY KEY (rec_codigo);
 D   ALTER TABLE ONLY public.recebimento DROP CONSTRAINT pk_recebimento;
       public            postgres    false    231            �           2606    28121 (   registro_pagamento pk_registro_pagamento 
   CONSTRAINT     r   ALTER TABLE ONLY public.registro_pagamento
    ADD CONSTRAINT pk_registro_pagamento PRIMARY KEY (reg_pag_codigo);
 R   ALTER TABLE ONLY public.registro_pagamento DROP CONSTRAINT pk_registro_pagamento;
       public            postgres    false    242            �           2606    28039 ,   registro_recebimento pk_registro_recebimento 
   CONSTRAINT     v   ALTER TABLE ONLY public.registro_recebimento
    ADD CONSTRAINT pk_registro_recebimento PRIMARY KEY (reg_rec_codigo);
 V   ALTER TABLE ONLY public.registro_recebimento DROP CONSTRAINT pk_registro_recebimento;
       public            postgres    false    233            �           2606    28142    telefone pk_telefone 
   CONSTRAINT     Z   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT pk_telefone PRIMARY KEY (tel_codigo);
 >   ALTER TABLE ONLY public.telefone DROP CONSTRAINT pk_telefone;
       public            postgres    false    244            �           2606    28057    transporte pk_transporte 
   CONSTRAINT     `   ALTER TABLE ONLY public.transporte
    ADD CONSTRAINT pk_transporte PRIMARY KEY (trans_codigo);
 B   ALTER TABLE ONLY public.transporte DROP CONSTRAINT pk_transporte;
       public            postgres    false    235            �           2606    27902    usuario pk_usario 
   CONSTRAINT     X   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT pk_usario PRIMARY KEY (user_codigo);
 ;   ALTER TABLE ONLY public.usuario DROP CONSTRAINT pk_usario;
       public            postgres    false    217            �           2606    27892    veiculo pk_veiculo 
   CONSTRAINT     X   ALTER TABLE ONLY public.veiculo
    ADD CONSTRAINT pk_veiculo PRIMARY KEY (vei_codigo);
 <   ALTER TABLE ONLY public.veiculo DROP CONSTRAINT pk_veiculo;
       public            postgres    false    215            �           2606    27957     veiculo_compra pk_veiculo_compra 
   CONSTRAINT     s   ALTER TABLE ONLY public.veiculo_compra
    ADD CONSTRAINT pk_veiculo_compra PRIMARY KEY (comp_codigo, vei_codigo);
 J   ALTER TABLE ONLY public.veiculo_compra DROP CONSTRAINT pk_veiculo_compra;
       public            postgres    false    224    224            �           2606    28008    veiculo_venda pk_veiculo_venda 
   CONSTRAINT     p   ALTER TABLE ONLY public.veiculo_venda
    ADD CONSTRAINT pk_veiculo_venda PRIMARY KEY (ven_codigo, vei_codigo);
 H   ALTER TABLE ONLY public.veiculo_venda DROP CONSTRAINT pk_veiculo_venda;
       public            postgres    false    229    229            �           2606    28072 0   veiculos_transportados pk_veiculos_transportados 
   CONSTRAINT     �   ALTER TABLE ONLY public.veiculos_transportados
    ADD CONSTRAINT pk_veiculos_transportados PRIMARY KEY (trans_codigo, vei_codigo);
 Z   ALTER TABLE ONLY public.veiculos_transportados DROP CONSTRAINT pk_veiculos_transportados;
       public            postgres    false    236    236            �           2606    27975    venda pk_venda 
   CONSTRAINT     T   ALTER TABLE ONLY public.venda
    ADD CONSTRAINT pk_venda PRIMARY KEY (ven_codigo);
 8   ALTER TABLE ONLY public.venda DROP CONSTRAINT pk_venda;
       public            postgres    false    226            �           2606    27904    usuario usuario_user_nome_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_user_nome_key UNIQUE (user_nome);
 G   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_user_nome_key;
       public            postgres    false    217            �           2606    27894    veiculo veiculo_vei_placa_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.veiculo
    ADD CONSTRAINT veiculo_vei_placa_key UNIQUE (vei_placa);
 G   ALTER TABLE ONLY public.veiculo DROP CONSTRAINT veiculo_vei_placa_key;
       public            postgres    false    215                       2606    27799    cliente fk_cliente_endereco    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fk_cliente_endereco FOREIGN KEY (ender_codigo) REFERENCES public.endereco(ender_codigo) ON DELETE CASCADE;
 E   ALTER TABLE ONLY public.cliente DROP CONSTRAINT fk_cliente_endereco;
       public          postgres    false    3008    202    206                       2606    27943    compra fk_compra_fornecedor    FK CONSTRAINT     �   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT fk_compra_fornecedor FOREIGN KEY (forn_codigo) REFERENCES public.fornecedor(forn_codigo);
 E   ALTER TABLE ONLY public.compra DROP CONSTRAINT fk_compra_fornecedor;
       public          postgres    false    210    3022    223                       2606    28091    despesa fk_despesa_transporte    FK CONSTRAINT     �   ALTER TABLE ONLY public.despesa
    ADD CONSTRAINT fk_despesa_transporte FOREIGN KEY (trans_codigo) REFERENCES public.transporte(trans_codigo);
 G   ALTER TABLE ONLY public.despesa DROP CONSTRAINT fk_despesa_transporte;
       public          postgres    false    235    3060    238            �           2606    27779 $   endereco_parametrizacao fk_enderpara    FK CONSTRAINT     �   ALTER TABLE ONLY public.endereco_parametrizacao
    ADD CONSTRAINT fk_enderpara FOREIGN KEY (para_nome) REFERENCES public.parametrizacao(para_nome) ON UPDATE CASCADE ON DELETE CASCADE;
 N   ALTER TABLE ONLY public.endereco_parametrizacao DROP CONSTRAINT fk_enderpara;
       public          postgres    false    204    3010    203                        2606    27784 -   endereco_parametrizacao fk_enderpara_endereco    FK CONSTRAINT     �   ALTER TABLE ONLY public.endereco_parametrizacao
    ADD CONSTRAINT fk_enderpara_endereco FOREIGN KEY (ender_codigo) REFERENCES public.endereco(ender_codigo) ON DELETE CASCADE;
 W   ALTER TABLE ONLY public.endereco_parametrizacao DROP CONSTRAINT fk_enderpara_endereco;
       public          postgres    false    3008    204    202                       2606    27832 !   fornecedor fk_fornecedor_endereco    FK CONSTRAINT     �   ALTER TABLE ONLY public.fornecedor
    ADD CONSTRAINT fk_fornecedor_endereco FOREIGN KEY (ender_codigo) REFERENCES public.endereco(ender_codigo) ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.fornecedor DROP CONSTRAINT fk_fornecedor_endereco;
       public          postgres    false    202    3008    210                       2606    27817 #   funcionario fk_funcionario_endereco    FK CONSTRAINT     �   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT fk_funcionario_endereco FOREIGN KEY (ender_codigo) REFERENCES public.endereco(ender_codigo) ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT fk_funcionario_endereco;
       public          postgres    false    3008    208    202                       2606    27880    modelo fk_modelo_marca    FK CONSTRAINT     �   ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT fk_modelo_marca FOREIGN KEY (marca_codigo) REFERENCES public.marca(marca_codigo) ON DELETE CASCADE;
 @   ALTER TABLE ONLY public.modelo DROP CONSTRAINT fk_modelo_marca;
       public          postgres    false    3026    211    213                       2606    28109    pagamento fk_pagamento_despesa    FK CONSTRAINT     �   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT fk_pagamento_despesa FOREIGN KEY (desp_codigo) REFERENCES public.despesa(desp_codigo);
 H   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT fk_pagamento_despesa;
       public          postgres    false    238    3064    240                       2606    28104    pagamento fk_pagamento_parcela    FK CONSTRAINT     �   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT fk_pagamento_parcela FOREIGN KEY (parc_codigo) REFERENCES public.parcela(parc_codigo);
 H   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT fk_pagamento_parcela;
       public          postgres    false    240    3052    228                       2606    27999    parcela fk_parc_compra    FK CONSTRAINT     �   ALTER TABLE ONLY public.parcela
    ADD CONSTRAINT fk_parc_compra FOREIGN KEY (comp_codigo) REFERENCES public.compra(comp_codigo) ON DELETE CASCADE;
 @   ALTER TABLE ONLY public.parcela DROP CONSTRAINT fk_parc_compra;
       public          postgres    false    228    223    3046                       2606    27994    parcela fk_parcela_venda    FK CONSTRAINT     �   ALTER TABLE ONLY public.parcela
    ADD CONSTRAINT fk_parcela_venda FOREIGN KEY (ven_codigo) REFERENCES public.venda(ven_codigo) ON DELETE CASCADE;
 B   ALTER TABLE ONLY public.parcela DROP CONSTRAINT fk_parcela_venda;
       public          postgres    false    228    3050    226                       2606    28027 "   recebimento fk_recebimento_parcela    FK CONSTRAINT     �   ALTER TABLE ONLY public.recebimento
    ADD CONSTRAINT fk_recebimento_parcela FOREIGN KEY (parc_codigo) REFERENCES public.parcela(parc_codigo);
 L   ALTER TABLE ONLY public.recebimento DROP CONSTRAINT fk_recebimento_parcela;
       public          postgres    false    3052    228    231                       2606    28045 1   registro_recebimento fk_reg_pagamento_funcionario    FK CONSTRAINT     �   ALTER TABLE ONLY public.registro_recebimento
    ADD CONSTRAINT fk_reg_pagamento_funcionario FOREIGN KEY (func_codigo) REFERENCES public.funcionario(func_codigo);
 [   ALTER TABLE ONLY public.registro_recebimento DROP CONSTRAINT fk_reg_pagamento_funcionario;
       public          postgres    false    233    3018    208                       2606    28127 /   registro_pagamento fk_reg_pagamento_funcionario    FK CONSTRAINT     �   ALTER TABLE ONLY public.registro_pagamento
    ADD CONSTRAINT fk_reg_pagamento_funcionario FOREIGN KEY (func_codigo) REFERENCES public.funcionario(func_codigo);
 Y   ALTER TABLE ONLY public.registro_pagamento DROP CONSTRAINT fk_reg_pagamento_funcionario;
       public          postgres    false    3018    242    208                       2606    28122 ,   registro_pagamento fk_registro_pag_pagamento    FK CONSTRAINT     �   ALTER TABLE ONLY public.registro_pagamento
    ADD CONSTRAINT fk_registro_pag_pagamento FOREIGN KEY (pag_codigo) REFERENCES public.pagamento(pag_codigo);
 V   ALTER TABLE ONLY public.registro_pagamento DROP CONSTRAINT fk_registro_pag_pagamento;
       public          postgres    false    242    3066    240                       2606    28040 0   registro_recebimento fk_registro_rec_recebimento    FK CONSTRAINT     �   ALTER TABLE ONLY public.registro_recebimento
    ADD CONSTRAINT fk_registro_rec_recebimento FOREIGN KEY (rec_codigo) REFERENCES public.recebimento(rec_codigo);
 Z   ALTER TABLE ONLY public.registro_recebimento DROP CONSTRAINT fk_registro_rec_recebimento;
       public          postgres    false    3056    233    231                       2606    28143    telefone fk_telefone_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fk_telefone_cliente FOREIGN KEY (cli_codigo) REFERENCES public.cliente(cli_codigo) ON DELETE CASCADE;
 F   ALTER TABLE ONLY public.telefone DROP CONSTRAINT fk_telefone_cliente;
       public          postgres    false    206    244    3014                       2606    28153    telefone fk_telefone_fornecedor    FK CONSTRAINT     �   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fk_telefone_fornecedor FOREIGN KEY (forn_codigo) REFERENCES public.fornecedor(forn_codigo) ON DELETE CASCADE;
 I   ALTER TABLE ONLY public.telefone DROP CONSTRAINT fk_telefone_fornecedor;
       public          postgres    false    210    3022    244                       2606    28148     telefone fk_telefone_funcionario    FK CONSTRAINT     �   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fk_telefone_funcionario FOREIGN KEY (func_codigo) REFERENCES public.funcionario(func_codigo) ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.telefone DROP CONSTRAINT fk_telefone_funcionario;
       public          postgres    false    3018    244    208                        2606    28158 #   telefone fk_telefone_parametrizacao    FK CONSTRAINT     �   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fk_telefone_parametrizacao FOREIGN KEY (para_nome) REFERENCES public.parametrizacao(para_nome) ON UPDATE CASCADE;
 M   ALTER TABLE ONLY public.telefone DROP CONSTRAINT fk_telefone_parametrizacao;
       public          postgres    false    3010    244    203                       2606    28058 $   transporte fk_transporte_funcionario    FK CONSTRAINT     �   ALTER TABLE ONLY public.transporte
    ADD CONSTRAINT fk_transporte_funcionario FOREIGN KEY (func_codigo) REFERENCES public.funcionario(func_codigo);
 N   ALTER TABLE ONLY public.transporte DROP CONSTRAINT fk_transporte_funcionario;
       public          postgres    false    235    3018    208                       2606    28063     transporte fk_transporte_veiculo    FK CONSTRAINT     �   ALTER TABLE ONLY public.transporte
    ADD CONSTRAINT fk_transporte_veiculo FOREIGN KEY (vei_codigo) REFERENCES public.veiculo(vei_codigo);
 J   ALTER TABLE ONLY public.transporte DROP CONSTRAINT fk_transporte_veiculo;
       public          postgres    false    215    235    3030                       2606    27922    acessos fk_user_codigo    FK CONSTRAINT     �   ALTER TABLE ONLY public.acessos
    ADD CONSTRAINT fk_user_codigo FOREIGN KEY (user_codigo) REFERENCES public.usuario(user_codigo) ON DELETE CASCADE;
 @   ALTER TABLE ONLY public.acessos DROP CONSTRAINT fk_user_codigo;
       public          postgres    false    3034    219    217                       2606    27905    usuario fk_usuario_funcionario    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_usuario_funcionario FOREIGN KEY (func_codigo) REFERENCES public.funcionario(func_codigo) ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.usuario DROP CONSTRAINT fk_usuario_funcionario;
       public          postgres    false    217    3018    208                       2606    28073 6   veiculos_transportados fk_vei_transportados_transporte    FK CONSTRAINT     �   ALTER TABLE ONLY public.veiculos_transportados
    ADD CONSTRAINT fk_vei_transportados_transporte FOREIGN KEY (trans_codigo) REFERENCES public.transporte(trans_codigo);
 `   ALTER TABLE ONLY public.veiculos_transportados DROP CONSTRAINT fk_vei_transportados_transporte;
       public          postgres    false    3060    236    235                       2606    28078 4   veiculos_transportados fk_vei_transportados_veiculos    FK CONSTRAINT     �   ALTER TABLE ONLY public.veiculos_transportados
    ADD CONSTRAINT fk_vei_transportados_veiculos FOREIGN KEY (vei_codigo) REFERENCES public.veiculo(vei_codigo);
 ^   ALTER TABLE ONLY public.veiculos_transportados DROP CONSTRAINT fk_vei_transportados_veiculos;
       public          postgres    false    215    236    3030            	           2606    27958 '   veiculo_compra fk_veiculo_compra_compra    FK CONSTRAINT     �   ALTER TABLE ONLY public.veiculo_compra
    ADD CONSTRAINT fk_veiculo_compra_compra FOREIGN KEY (comp_codigo) REFERENCES public.compra(comp_codigo) ON DELETE CASCADE;
 Q   ALTER TABLE ONLY public.veiculo_compra DROP CONSTRAINT fk_veiculo_compra_compra;
       public          postgres    false    223    3046    224            
           2606    27963 (   veiculo_compra fk_veiculo_compra_veiculo    FK CONSTRAINT     �   ALTER TABLE ONLY public.veiculo_compra
    ADD CONSTRAINT fk_veiculo_compra_veiculo FOREIGN KEY (vei_codigo) REFERENCES public.veiculo(vei_codigo) ON DELETE CASCADE;
 R   ALTER TABLE ONLY public.veiculo_compra DROP CONSTRAINT fk_veiculo_compra_veiculo;
       public          postgres    false    215    3030    224                       2606    28014 &   veiculo_venda fk_veiculo_venda_veiculo    FK CONSTRAINT     �   ALTER TABLE ONLY public.veiculo_venda
    ADD CONSTRAINT fk_veiculo_venda_veiculo FOREIGN KEY (vei_codigo) REFERENCES public.veiculo(vei_codigo) ON DELETE CASCADE;
 P   ALTER TABLE ONLY public.veiculo_venda DROP CONSTRAINT fk_veiculo_venda_veiculo;
       public          postgres    false    229    3030    215                       2606    27976    venda fk_venda_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.venda
    ADD CONSTRAINT fk_venda_cliente FOREIGN KEY (cli_codigo) REFERENCES public.cliente(cli_codigo);
 @   ALTER TABLE ONLY public.venda DROP CONSTRAINT fk_venda_cliente;
       public          postgres    false    3014    206    226                       2606    27981    venda fk_venda_fornecedor    FK CONSTRAINT     �   ALTER TABLE ONLY public.venda
    ADD CONSTRAINT fk_venda_fornecedor FOREIGN KEY (forn_codigo) REFERENCES public.fornecedor(forn_codigo);
 C   ALTER TABLE ONLY public.venda DROP CONSTRAINT fk_venda_fornecedor;
       public          postgres    false    3022    210    226                       2606    28009 $   veiculo_venda fk_venda_veiculo_venda    FK CONSTRAINT     �   ALTER TABLE ONLY public.veiculo_venda
    ADD CONSTRAINT fk_venda_veiculo_venda FOREIGN KEY (ven_codigo) REFERENCES public.venda(ven_codigo) ON DELETE CASCADE;
 N   ALTER TABLE ONLY public.veiculo_venda DROP CONSTRAINT fk_venda_veiculo_venda;
       public          postgres    false    226    229    3050                       2606    27948    compra pk_compra_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT pk_compra_cliente FOREIGN KEY (cli_codigo) REFERENCES public.cliente(cli_codigo);
 B   ALTER TABLE ONLY public.compra DROP CONSTRAINT pk_compra_cliente;
       public          postgres    false    3014    206    223            �   F   x�m���@D��R�,v�Yj��:�G����}��O��x8g/(���j�īU�j�����T��TDnY�      �   e   x�U�;
�@E��i��i��x�lÍ�|��)��^8���<ސ�9E�k+�@��E�"����F�l%�q~7xsn6��W�_|v����&-LD?,i�      �      x������ � �      �   4   x�SvsANeW�T62AΒ�ļ�ĢԼ��64�&dh`����� 	��      �      x������ � �      �   �   x���=�@���S�	+?ƒ�X
���f�d���FZ{��
����zy��>�GY��%��zt��
;P
rK�k�=�����>��8�KH���F�E׳6�0��''Ӂ���\�w]�L�Т��7Hū�R���5�gJ|"�C�2m�P�ލ����cg�x���������A%�Bǅ�	d�      �   <   x��qwQ�M�KM�H-)�TK=�6�4'�X!U!�(1�� ��$UAW�'�őӄ+F��� �c�      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x��ˎ%���7�8/ �x3҆�Ii�Ш'$���Bw	�K�)�b�>n��8���Υ*O�#3"<���mF.����������Z�y�/������~�?���������_�������������������������w��y����?���k�����
W���O��?�u��?�/^�ߡ�R��ܾ��O�|Ѯ?��'�q����J=��US\w���x�N|��͕C��N!���וf�����w�9^����Jo�k�1v�|����)5���+��ʉ�r���^y_I��U���\/%�c�iE>��Թ�w�~�����=�3��띧���/�>���U�����g�J�o��w���NbR�/ws���_���{!�T���M���#�s}�y������u^Q���ůg���)1�|��s}~���N��+�ʋ�%ﷺ;��_�#������θ��L�u��{��$� ��Y����?~�
G>�5��>k��.��|��t���W�/R�\���ک�������_[�����^���>�ח��]��'{�����ݵ���=�H/��>�I�#�%!WWl�1_����z��R=��ɝ���68��y���SM-���������g$߽2_���jzrD4b��|r�����S-�:%�>7��Ͻ����C+��g�#�7��?����~��~�y���u��s��u���~�˸��O���{~��i��O���/��y����X���I����K����/_7����׭�__w�����5���~�k���!\/�ښ��?|U�*h�����r'��L��'�.�6>�������X>;E֥y'�Ư|A�O^�y��e��O}ݗ���_�U�z<����]�׋}���߅��Y�w�s�x�y�p���b�3p��Z�����	׃n
�skh|�d��=D��#�c+Bj�341��$ĵ�F�O��i��o��}T j��z�����{��O~�͹��p����~��#o��������?={8"sV�ē��	\F�ȹa����/A�
��+�z  �9�ss��J`[P-� (�_��^��W?��Ej����n#_�~�CȖ[����n��o�n����g��>K?�}�:���/��ҿ�K~�}������K{�R��sc�~���w��Y����+_�|�蟾�~��[������o����>㘐��*��hR8�����t��+Ծ��g�WЖ�w��s�_Ylz��PI�/�������_�/���7����7=���7=�~��[�����oz�}�oz�}�oz�}�oz�}�oz����~������������������������������o�~��[������������������������[������~ӣ�Kӣ�Kӣ�Kӣ�K��.�M�������~���}_���ҟ_���}�oz�}�oz�}�oz��}�s�s�[��c/��K������}������7�X�޶�m��P��e*WN�]�Z�=�g�VF(yּ��k��	�^W|�C��ȵ�5c��m���#������5oޖ�]�lc�WN�5=�����3�}��'W��S�כ���'�g���=s���z��y[�Wm��Y3->x��ܱ����ʱo>i�:l���z=�Ի��FP��
�#��O�Ǻ�e��n���b�C���[*=�{r��eMbbM��X��yg�ǐw�����{�g/�k��p?+��}�����w�w�{\�V["���s�9�]f�5�'��m<��o	#�s�Jم�͓%��x��%��>��v{$��dW���֑W	���}�:j�ΟJ+��1��1�1��[{)a_�٩�1[Xi����2���z#D1�,�w��[�r�5כ%9�:���ɈN�3kx�w\k��g�ݑ���6���=���@���U���*�,���=f�������P�ۃ$ ���5t���k�ճ�yV��z�{���[�:��2���Iz�]r`��Nk^?}�R]��5��ޅ-׻S�t��՟�֘f}[
�9�0�;�0���� ���mӝJ)������sǧ��Y������z��XW�u��x���E|��k�q��e���',��(��2��;��Kx���{fz�gq�
	��������ҟ��=q���_�j�=��	�Q��:�,��O$�M�����Ͻ�La.M�����^������+��O�]kqѷ�y��1�h�2���缡�Xօ��d_Z4W�BjWIi{�UK��5��1�����V���k�jV��>��d%tG�bMy�z�g��\���]w���)v�������ì�
tsX緢�*�5�0��k�v4vfa�N���w�a�ӋnF�c��F�D��ڱ!�u����X�W�. �N��r�<���WW��W�Ov�*���=���;�vo���Ք���|�'}q�K�f�뻽�V�G�X �˖�=�Zlu��a�Ź�����^"�_���<y��,���=������t9�̵�-Ŋ%4H�-�̶:����<��|�ņ�M�'���ۍ��qQ$���D�o��yu�[�	�����#P��{��ei����m�px�[�?�sw�������=�]=�2��x�0�?�{�\���x�ō�(�+v��n���X� 2��W�o�~���ׯ>8�_����fp�Ћ�����.P)���G�8��ӗ��V���E�|��n�'qiH�����Y� ��Ebe�p�^"1,w�ml،�8�/����M��A����t,�Sx �Λ0�Nŉ뇽�R,�(8�=a���a�c�u��[z�Qy7 ����)qB�"<j��h��I�m�&z��@���~�7�3a X��xH}8ey6,����K��"�B�x֋UI;�|V)�"� k;.���0��q'��Np=�7�T0M��`
t`�a�|/(��o4*�-�h�
�؈��D�����-KX��.`��N	Y�:Ű�ܜ$�n,OG�b�ު`c��1�V�8Ttx�lhx��^
#�|]<�O��>c����ښ��� �A
��X��ilS@c0��0����3�5j�3Xw��s�z��x?���u�ƃ�BRaU^�W���hw�
�ET�sP8.��#Oi�@B  ��Vw��*���!���U����� \W��ɀ���A�j���!�M՚���l����`n�
!�h�u
O�6f�
�t�Xx��@}&�u� � ��_�<��=��z���)���u6��ѽ�{Uć3��ua�5�G�`�f��|ɒ�W��$�q?.gL�Wv����D{�V`1���"w5AP�`�(����ZC��¦@1�ܽ�L��p��d����|)�!L�)�fB;� ���p�#�Y�-� "[������Hf�~��^
o4�_C�Q[`t�.`p��[IW��Q@�C��&��M0�\�l.���7�XN��\����<��"P�g5p{�~��SY�@���zv�>�us>`�i ]�*�B]5Im�-��f-/�\�A��g��� �Q&OtbJɜ�Z���f�
�+ݒ���:t���@�@������T~��/-�3��s�y��ƅ�t�����q`�Q.01�5�3���@t�z'@�7V5�ok�dB��?�� ��~G��a��T<%XٍVT �1�e`���	ӎi=π1r>�$ <C� ����6�5�-`�^Tw��A�a�y�^^DY�X�B�T} �����w�J�0��!�|���E4�
�e� bX�j���t�������q��߇�`P�����	U��l g%�o��:z��w���U|�����>Y���-��m�%�C��̔��@r>�G7<�/�j �@I|�t�-N�Dv9>�tp����{s��x�C�=������ �x�7U��)-�E���0��p�Q���̮v4�bC+�K^���MH�ݻl��XA��-EC�Q�Ć"C����Ze�VR��xD0q84RV�D���`�Wk�w~���#��]}Gγ��˙e�1ڶt�P��    ��HG��~B�^���ˍ�yz������ֿ��:VA�`�{���U��h;����Y�)� �s�3Xsֆ�3�=D��< ��M���U�;4��hp��NQu%>c4��1���t�s� �E�/Z�z'J���8k�����u bt�yws�ۏAIT&v�h2�'��e��@�"B�8�E��&o�l.�&��	��=�сG�o�w��MF�,�5�4#��:Gk � ��k�9����,@g4�� 6K�r>��uon�T�u �W�K����"��c��l �ܛJ :)���:���"R���tЍ jT���R�B��]�6�
k$��k�4������//䪾Xt�Z����Q'?��)x��GD��R�&f����&�l�,��?cֲ�"�8?(�;o`��*ܡ�g0��T\
���m��A�0"��ûƣS��[P��?q�����6�Vel��ӊ~�ܺ��"hY�b����Dyb*�0��q8��k�Hx����ݿb�
G�0�qt@L� �fÅǸ����)���2m;�t�x�5]� p�⹺�;�B��ƺ���#j{ ��{Bk��*)��U�j*l�n
���B���v3	p��40RB�<�}�#������ew\݀�d}�N�M���D؁�k�QpQ�ӗ��� @ �2uK^��˄y��D�N�z�'od�Pok9�5QD�ND�D��!� ��{娗\�C{ŷ �:���]8��4lyvj' o�@ǥƑ�hd�7�_4Zcܞ;!���An��� PTt���7����Ώ
�"��g6����"[�!Z ��͡��S ����e@��tW���q�)o=��dA��<�,��7D^ p��	{H�G����,��V�`E�d� TQ���m�I��@T1��8�(��\]������;��`@��@[E蓸_�H��A:�	��C�Q�|:LG�Ǐ�_/�}o��V�6Ī��BWnom ��yW1��ҿ��az�mC ��7�)ջ��$M�5�[@�����#[������C}�Y�:MՀA*`�����^�N�_A�Qy�q�
#l���z��r0*�
���h���6lL����eFcė�`�m�l㫰̇�y4�"̼�ǡ6{���$=�!6qA^"x����}�,��`�rz,��sl0�ެ=��hY܃=@-kt��E�4��p���~��o����Ÿ��5����/�*�7�[�%� ��ɸ[��pԧ�q�P��r�{�QV��rxt�.H��[�[���N8]�g�Z�6���@#�OE-`��S�|>�Kب����p_�np�Uk�]�E��f\�惩XB�_�78�:d|�dTr�Q�?p�Dq�~Z���l�3�G�u�i���r}~@����}��ԡ������0<1�5X�������k��=�^tD��j$�ES�e�s+���NJPX�
��>I�+Y��R}dlC�m��L�P�c���d�;�.m ��U�qR�����,���@@�)��E�������"�UB�	`H�Q��op�p_HWO,pG�;�	�b_��@�w�>�΋v��yǾ��b�4�<�U�
\A��7PĒ�N���)��ߔ����R�|a-��Mwz5�HG��'�\}�F�`8i�ͭb��j`�#0�g�m�hU9^-���0tk�i�� ����>/k׷ �kӏ����K7��(DNV�?��� ��̎~��\��SŬ��X�'�
���G��Y{�$����N�z�\v�k1,s�Y	0	j\:�ml<vb�'y&\L �J�������C`wXR���^�  "����9k���i`[�nj���q���������q�g�n�G﵃�+`hP�o�KOB�6۷�`�7�i��z�2A�G��=��a�=�� l��l�L�ZvjƤ��|�&G`���F6��o{�>�����h����s+6��+5g��9�g�`pR��{���~�)#�V�;���7��qL����?�ACZ:�^Np�Qm;�A8�N����Y=�����4\�iȁӀ�h8��c�b���Z��8������i0
b C�r�b`�{Av9@.�@߫�=�:e`�R�Bڳ�T�6v���HD{j��Nu���Wv�d�Y����/�at�T���:��ˍ�`�y�L��� /�p�!/�)��X���9�c��:�j�a�P���e�qz��,���YAj�	'al���{%�Z`r6O	�[]���D�}X���Wg��FA���\��%������Vgoh��#E@�� ���0��h����ePW����U)@|��~�<+F���C���e�z�|���\��R������*����T¼��LT���nac,O���9k7���^���ȴL>��7���|y3��y�?�֝����4�-�y��i�������ͤ�&H���*��d�h$��BႹ� ���t{���@�/lYYhi�A�*#����	)ua�j[��q5 z��6h�^h� 1	S�1�̠O/$
�ƣ=�Aqe�}��>G��P[ �v߰�Y.�����U0���}�����f;<�JoA��[�|*{�t�����/"�B�b�c߆�tF�ߩNö_�/PC���ǗLŶpl�a��/z�u��`�:��D!蓇�٭*XM/�C�����ݕ�6S2C�z��$ѐ�0I?l{WX���F*9�h#(��i�gα���j�yu�|�։�W�^�Cud�<���q|h\�Q�L�u�a��f4V��KfM w��]ի�������� @�4��ƌh�PRx�����>�q=(|]T:o��Hl�]1^�̠k{��|��,��@sj��"���r%�Xc	��$�a�C�K�_�(=x�[�e�oJ�qZ0���>�n 	�b��$�.���ǉE�"�m���k��A#��dy��/@�p�������(i�s�o��n������{��5�@5�9�B��{X���@�_p* ݉+���Yd�����H��PQ1�|����Ձl�p��~�V��2n�$p�1m��H��a顊�<��<6c!6\ШY 1,�{�fF��_sdT���$��@�.�%ͼw���t�p�p��Eu㰔�Z� L��`)��������:Sx�E��9��n��39�K�	OHʺY����P	�d�����͙����đ�"��b1Q���F�!M�X���������""��t
��
�=_��{ha���e�v��<�ʲl�i��1p���e�n(�e��yC�m���q;�zS��)�"��ɚ�[�;�E�����<0z���@�!����ǝ��<�P�˪_X��l�|��*��F�*,�D���^ h�l��M(���	�й�7��TB�`ϯ�G���ދ��3GA`�'i����[�TNG�����EM*1Y��0*h� ����t,�V���˻Dy�����#��y��d(o�x�=�\]��D7��vŦOh��4�HbCG�vV׊֕wn�`Q,9/<BpQ2�)��}�����u!��؊��fR��o���q)=_�B������1��O &��aN�·�A{p��Q�0�o0��򓞣�XK[��)]oS �b�����&[��}�7��8]o�V+��o�/�LF��h��D}l����a�	*\���*�^LQN��h(|#8 ���3�[OD������Fg���I4��{{ŝŘ}Q�8�<���?*�m
���5�	ԇ&�yҩ�#e�s`w�X�O��c�!������9�(�~@*�^�3���1o�Z�do'/I܍jI��`N~;%[��"|�/4<�]�B��q@4!���1��W31/%J4؍f���^���&&��d:Oj��$��W�94n)< .�j� ���vj�?D"u�Ė� 0���S���՚ 轪��4�C�mҹr#����za��C`�n�q��A��w�[�    �ͯ	�1�v�h�:�M�Cr �w�82f3K��B��Y�i8���U�	�G3WyE�ϽO��h%=��l����o�sT��*���5����_�6apl�x4K"������s���}��ݓ�=��z/��/�z 
& N�n� �c����ZC�˪���LH�H����:�b��.�sC����w���͐F���6���ی�}��A�:�*���&7�RE�0e8�""u@����ګ�O��8���o	�鮗�4�^ϭ����X�.��E`[C�-�;��u���>:�RkA�O� ]���:9aؠu�� i�ߗ��	�2��.�oLܴ��
� "@�ta�+��Y�x�vׄU]6�G�$\pBk։��IP�6h��,�\r:w,z�L�h:��\�䳲��e�����������\g��3�9=UKXN��4i^�U^ �ؚV��n�� -30�i�l�/v�hH�k�����E2Q	7fm�}d]͚����`��Uq�т+�mNM�c
>l��a��t/)h��*#IPi�|7�q|&�A=֫�ʯ��`�ވ<Ĩ�g���"���xYP���,�H�^���m��͈4�m�� ����j���tI��OL���<�ص�����b��) D�E��'���4��X��k1-hD���Xhݭ�kӯ��� ����R�� ����5��z���l,X�W��+��������`��Oo�~��[��C�!�j����~?(�6�����.*IU�[��w`�͈�4���ͧ`�2_�;C7�)e��;�)O�h�u7 n=<kģ��J��cm}�A��d=�,!7��;P+5,�V,�����/ܓ+tz�DS��~�=2hc��9$�;���R-ǆ_R�y��M�ju�v��$�ة��¬���o�ּF;�C���0���c�-1�"�i/�#�D�2,UL��v[��@���Hu�^��2��.��	�n�U�IWѵP�� r�?�a>�������N����nH�M�B�Q����l�k�eG5VD�o���Zʒ����
a��o�+|"$�U��Մ�㍝��/��C�X@�҉��)H.���P/����A�����ץ��aL�n��'���c�B�N�Á�Vo��X���~��ty�YއC5�����Q3����U8�6���~�'�����kvpk�m(�7��[�Z����gE�"�T�R�m��Y`�4�îG�x��l�f$�͠@��}��jQ�I-u�q^�٪�m�>�����s�CG� ��r�1�I0E_{�pQ��e�9(7(
db[�p�ՠ�dm�*�,�y1�-$,�}#n�+a�m�f8-q�D������T�δ�Y����G2�Y� ��9B���0��uj @�=4�63��*�wG���L���{�;[pa~�~M��U�>��VD�8�2�e*�,��]�j��U@�\E+�<%�[5�i��+����I7�4�wܟ:!=|j1t @���m���ek�� i�6�Kg�u�@�@m�1��Y�~G�h�(�C��1FGߵN�>z۠�ES���܃4�^:D^��y�enA:)��%��H.M�g;�3��q���?���������.�4��w��2yQ���_A�Y��'�?u�;���?��=��oZ� �k'H�����1u�(�� ��e4�Y��%�����f�c��m�+�D�
�Z m>�x��x��]�͖.�N�x�mO�U+�(1$)����7�����N���\�5����}����n:�<)�m���5G��+�;hSD>m�3޳��9�Eyk����8<��Pxu�~��f�xL�C�M��C�'����v��M�=a�7TO��K�:�u��73�z�t�w~��0<��X&B��JE+��k�Q
������z�t�a���(�iS�֝�x� �Z�Z�����d'������ژ�;xy1�2f��,�9>oD����Ps�(yR�]���O1θ�>γ.��L|S�̭�� ,���ݢ0Є{���{�;�,�c#p�		ggn��y$�-���T�D��I�?$vF3��rjk�ou��-�CR���֭������!{����{�Y�V'�a�分T��|3�H/�5[��1�"3_���gW�;������{��/�8F �ua�ER.��pj*�Irl��J>�S�9���F���؞,�@68��s�/��ˀ0̍��Ӽ�=c�7�#w7L���>R�:�&��@4�׿�|���X1�6�j&А7q�i��(�JO�?\��S����S� 3�9k?17��r��;"�y�V�{�/��0,����FaKtz���S̔����[&Z�[{���jٸ��Pa�0��>�@~�0�r��5qLmh���4o�TN���s�>4��c{�Ƞ��F��2��0�oU\�iӭ�S�����i/s�aqbG}��P&U<�P���"=��݀Q�o9>X�b%�z�_���Cָ��|LF{�s�������G��:,i��8T��
�ad(�0�,
�d��o��b��kr1�ɝ��`���a>-��kB��N�=M�����*�Zm�Z�g��i1А0{���J���dB��z�].7��o����؈�y�W���Ԅ�h�f�5n��$��X�����4�x��6jSת��,Q4[�"�oF0��A�g��!�hAS�{5�z�R�|J u�M���%�ؤK�v,�I�������O�iW/��`P��0�l�.uXs��_z�I��9J���dqX}{������˻�V^�U'>��r4w{��&Vl_�T��˯����i�=�1z9TVXX)좿ٜ)��g�� r�l!�I�`ZD=Ռv8T5	"J���G�~4��5��h��h��`�a%���[o�9�8��l`9�!·WB_k�_SO�Γ���8��Ǿ��v��D\�u)ŢY�����6L0M�6+)�ֈ�VK��_{A -�
V���E���6R)�k.S��OD��	�$�<��z���Ft��J�0�-�VBLJx���t0��y{ė��ff�f��}�ѯb�+�¸�+�_Z���R/�Rg}�7���:H
���)WŲ6C�V����N
[G{��a����$e�B�F��!O�� :��9��Z�[�,���"'�f-8d_B�$��3K�& ˶^���>R1�%�n�yN�w9v�Wb�6R�z˘J���8�+�z}�j{�������9Q����]��eS>��6�y���,�s����[|�|@@��|g���j��b5������o.��1��籌Ղj�LtMσ�C+�*S�����9?٦kU��.��0X�)�e�-��$ͦ��c���qd����`s�&�u�L5B��ݟ��%���ۊ�A�mĺ�W�'2�,�a ��yF;��{�<O��<<%��I����ke�3ڧ�˜0����C]�5g��Վ�`�yan����"�%��Kg�x��G;�7=n[�Q� �LTv����ؖܘ�l�݁l#Oe5��C�>�rm�����6���Ov����i�Ӥ����Ú��������ܶ�k�g5h�@U7**h�nԥ��r5�2��l�nU(�˫;��S�9�X��h�dʗh��=υ�� ���j�����k~2(�}�x�O�^�9�WY�����'�1��(�(]�M{�	b!�up��=pgs�l+t�֏�fk&J��']g���N!t�"F��r�ݏ�>N��Vݵ�䟀_�µ�Bv*_����=l|��s�~��+�z�J`B0cCQ���_��ٌ�F���v3���x8`�ЭaI�����i�$�=c�cG�^�'	XA�½"1��!,� Smq���YTj�o+);���,ݕ���K�m�[u\�:�~�Ǹ!Bb[ÂiI�	SȪ��N[�5��8��m����$F��ڶ[|MB�L�}���d�ϓ~g9Y����/���|4"��    ��H��sb��U ����p2�z�ly�s�y�i<�!<>��i��)~즾���Z]���(��p�D��l+b�@��h���]3N@�����ʏ٨)�*+��5k�ށ�7�sw܋)��K<��y�<m5�K^�l�+��xOC%������ߌ��X@�A-�`�3RC�ռ}t	
�Fq(!�ҋ*f��ݝ�~�^�m ��ꅺ�}�S�ZF�T1����ꢛ�Q�bG�l\���ǯʐ��=#��S�O]�Y��:�M��ݫ��x�� th�E��s"J�+��Y��K����Y3���c��b��0����{��2�`��lh
뙬m��n��I��Ўw�z�@!�o�)Y��6b,:p�v�{l���fg�h_O�N��A*�[��S��?�K=w���oQ�����f�,A4�B���V��켌N�!+Uy�cBKB�f��y���H�>�˶��;�.�;������i�ƼP�`c��X|ls<��^,�SXn���c�F�u��-E�����-�<D��˱�6�����?zʿJ���k���J�>v�Ŀ����KMC��ez�}�B/6v�^{Avm�mPe?�u���:�j�0��+�!;Oc�N��S�cb<����|0����ۆuZǃ����`8�t�:��50�R��k�S6�k��t�^�ʺ"�����O�rl͓��_�=D~����rozZ-���r�2̡�Jʝ�!���$Q��"rM���9Pt0��̘���zY0$�BN��Ķ�Sp�|������<q���p`�㠻��C�C��K��eaO9�� �S`�56��t����Z�������RMSd:�xm��m/��0��o
+;L���&����Q�Nv7ZJ���1t��
������Z	�,�r���$��5�Np��PY�t�v���� ]F��h�񖭤��J'K��ah��]�Z,۟�c'HS�=�p��GH����f�,�	n�U�&'��V�Y��ͼ��~�7Ea��hE:"�юv�J��O���9d���`q��KܪV� *n4�e�
^WmYq�&�����������>͏�gje_��mKl�|��%G(C(Y0L��q����5O�+�'�c�~ev��t����xP���n��l�9�m,4���1E�U���h�v^���n�OK�����Il��أm~Yr��*=��ֶ�Uaٹ�cl�?���K�u;.	�N��]G�Y�=H��/uM��r�l��:����5^�&�M���9��h�� ���5��7���^G�Q�t8�0R��w��)?��5��}���랗�h���ȩ�Q?p$Z_${�ü�9q\����-�U�:Yw��M�n�6Zl�ǝ�v�ۭh�-=��	���T޷1�;^�Bd��z��p�a��gX�=X-�ZF��b7� f��ʇ+�F���I�i�>ڂ]N͙�?�O�F[�ng�6��+^�̹�4xa��k5�fC>=���c�_s+����xd�8e���#�`��y�u�C�f�z�'[��_6l�Wm�oƞ�&&\�]q�)(�u;r�p͵��P/�:$j�'��l�a���_����@��罻G���]�5�W�S�m���j�P��o�"�;�KRm�KY�9��Z���E�)��/���a�t�I��S�zʷ�(���3)�T�85�y�Ct��%������l'-��I�� !�&w`���Z�|o�VC��6�C ڥ��}��|O�{ثO��6I
��J#i���v�6�@�G���W�o���.6m��&YC��yD��e��n�hvI��,)4)tz���bɣ�l�q�[0�,�B�����ҡ�\��֊m��,6�w_R�$Y�l!��:�y^h�V�ESɚcN".��X��+ò�Mﴍ\����-9yw�S �l��j��-N��h ]e�@X�j�%���erx�A��\�Ӑ�Ǟ���d_����o����ϓ���_�i 0Dʜ��~\��2^̝�1�D�]�.E��kdE�g��x��5P6�����FXz��e�y����Ն������nv���Ւ$������ �ӿ@CZ�������l����C�Zes�~ٕ��AJ��MV%�4�n\�еb{8Sn��O`�M��88���3�&E#X��� �Xg ���{Y�w+Cv���p��U��Y�����DkV���`k��Y�� �eo"n�u4Ѝn�&�[�n��yn��X΂�3�bI{�_ ��\ΰ����Q�!���b���/ ��ĕ.�K�HLd�lG/�0{y����J�������f�(�g�;�bĺX�m�k�;�����UZ�xѸ���f�2����zl�O���81���� ��1j��KB�����ݬxֲ,��'!Ԑo�����h)�N|���ikgv.+oX��A�!�z"jga�e����O����e�9���-�.��ʨ�9�Q�X���l|�nH�����v_s�T��`9�[k��p������-V�^v��%Nl�c- L�\q:��[oK���B�W�j�7;?�.G�``93���\N3��Ӂ�X/k	k��%R҆��ؽ:�c}�=�m�2��|��M�,��B�,�HΌq���:�l1�
�ǫ�W\b;J���9@#TXL|�����]���	L��]��_�,d�:�m22X�c7.�16�i ?̏04]E-�Y'�gbD#�����D����i���+��=�H|L�Ӂ{1��]�`r�"�Ů�(�h{-4�B�P�E��Zݫ{o�|;_�6x��<C����8x�A����ͪ���P�2-5�^���v�}��6��%TrH�1�I9f �p���jSo�ߞ�87E�0xZS8rg�߰9�Mٌ�az��e:'�m�y�:Y���']t�I�E��7������%|�u�E�ts1N�
t��GGJ ���MjT�]]M��U1��p`(]�0ضv43X�i}l5�MJE$�����v����xL��xu�{� �O-��!z�~��O��^S^yWD���������10� P۹�>sm����!9��'`��-�5o����7$sܧ�)?��hk$(.����rd�α\j�T��IT+s���֑o�|2Y-	7��-�&�3k�
�| ʆ��׺�ZOU�}Rh�ʕ��%�|��Q4!�޻�TU_݌��e�6T��c���8DM�� �р���lZ "h�a�a��5G���+ky�Mن�P	������z��clFQ,�T1������_RB��Y'~���Wo�{r��	�,:��LlY/��`�=���G������[a���n���.{T:jcZ��V��;N��D��	C�Y�m��}°kL�����yrߦ��ѦEX��j��0���Y���Qf��[�l�g��Ģb_,�A�+ؙ4������jN��&#HOƋPwn�>��v�G��!Y1Q�`��j�d�;ؐ�$?w{t�l4�)C���eϋ�$�/�u���vꜾ��o�0��U?��9O=�ӫ��$�b�v�:�͘cw��۬8Kx�z���L�6���~�0�A����i	�㶇ꙫd�~a\M18�[ۣc�C{b��ҡ��3w��C7>XT���f�2�9/����ֿ[j͙5�v8��֏wZނaצ"e��:$x,*��8�%�^��8���x�f|p���.ϼ�XnaE긦}*+ӌ�e��.2&�}������˝�7�9��cд. �4����x��{��ZX��l�����z��Yֵl��P��2g`D�ⶱY2l�A�fmc��p�*�`�8�� ,`(��G ����w��M
���t��`AW��j�BwRq$�H�)�v�D{��l|5fgF'�'`�`��u�'�m\���v�tL ��t���`ň�@�l��u �dZ�i�Q�~��46i�g�m���k=�Cf��t��&�w{9�3��������k>����k �̵���Km�Z������������c��~��|�6�UѱXY�a��q30�����pAGgC��Z-��� ��(U�/f�g�h_���}*��    YN�^vGng\>/"n?�x�v�C"��$Y�b��3��vt��N���DO�R�%w1��y�S�}f݈���g��m9��\}(���O�}9۴�*ch�c��L�x��2��B�LL*�A
��z���nT�#����'���`z�i1Z�����qf�H��)@ç��e���6���}�nm#�Ù��w�Go�I �9��Ng�9��Q���vMGM`0��g��l.{���u�E���k�2���f.å�oK9 c�t�r9�8�.��x�:��]���/� �����Lz�dC;�k��ќwAev�EģC5=振�9�!oc7%�z/��ރ�P�9��'�3Ƶ��ae&J����>�`�'j̢�uFp���
�p�uC�,^q8;@یӒ!2�mB��m�S�|d��+]H6�i�__�^����m6��t2�� ��y�)��Q�v����8�Q�pbU�ps2�4�a���pt&���8N2r����P"����>�lT6�9sN�Y|g���� �-�Q�ɉ�欠��a�f�;� �d�����V8��f���l����������^m�[�hlT顢+�m�mx	���q�A�l<�{��Ȫ�����i�a��銪��j��7�檎�v��}2Bۋ��hn��B�1b�}� �g�0�3;|ڍ�݂W3��|^��0�b������%{@���y���E݈�/+o/�p8FoY�w�����7�C��1 �7�٦���z��W>�թ���R�
:��[[-�H�3��X��4lr��L�R�fP�����+ǳ�Qxƚ�rn0�+�*1x\{;t�ش�P[���m=�]��눘� 7cZW&�����Lg;��D��I���5e��Xrh��.�ڡ�a�94lY�E����q��9��E']6�B�vT�d��eT�p\�6#.��o����`�V�z�B��2}ֻ`a�̢�f�	_4æ֊U����tU�z���|V��b@��D���O��{Zg:��f,X�`�lH5m��xm��m1��ؠ�-5�䤫���<=��׭l8����NB�����uڕJ���9�o�iܐT(�mmM϶n�R�k�V�]��U����(c���:�=�&�S��?8��|Ύ:��.��xL���E�����A�3qQ���#�0Eׇ�A�'�-��h�$Ǚ�����<ik�U��Cg�}xM�\Ǳ`���0���1F"�{A��wC���I��_8P�*>۬�\1�ǖ6�ڈ�t���RǺ �Q,�MX*��g;O[�$k��p�IS���ɣv�{�Z�>D�{�����±��[X���A��,'����E4i��@��eW��S����FlH��[���8�d6=0v_x��W{����u��D$��:8�ef޺�|sB(�4�4^ӝ���ɀ;�h�J�׹�Ჾ�hY�q������U�>,�3sc����6ִ��ţ7D5[00�tq K�����٩�ƅ%i�OvĹ�N����Ԧ��"inN��ޟ�Y��9��MЂ�� 7�{i�W���\�L����E@y�^Q!Ye�k�ei0������Ұ&Yȟ�)t2���it�e�Fl�9�^���N-��(��������z�-Mlv-^���^�5�H�y�������.꾙�803�e[h� ����w�\3��+��t�	
\��#<���^�}�}���o3�x��񇢵�^�r�OJD�������}�3 ��]�ˤ�w8��i}��t����;{5�8�-p��'�i����9y��\�;(���
UX���k7SL_�`��[�_��������;l��ۘ����r��Vp���\�Q�+�H�X��_��x��D>�%O,�v4��*�۷�'��lGg�sl��߷='6XH�I%�ٱ�,��6HO=9=:um�aW��[�����������#[��L#��r�㬼�����.�0�:3�1���N|��8 ��ܤ+'����`�η=�����A�?�. �,���ϕ>�.�j����&;(֖��O�O�G�W�\Lay~g7�f��7�	Ŷ{��3��q*�����ʷ�XgJ�Yw?�����1�d����Ӡ�a������]�-�	3w�\w�7���[)�l�n��څ���L�:g2�`�L@p|m즗7;�e��L6�(�iv֠� ��u"�'ׅ�`�w�F_�yˉ�l0�*28�86ը�ɺ�m��� �Aٳ��y��D��Ԧ��"1]{g��Dw�6 ����� {3�fG�Ǧ����^H?|���a���d�C��!�,�5�o%�AvGw�@o��^�z0r*6���:(�sWf�im�g#�=��}�$��V�b�qF#���!WK�)��� �@}��� �J�����8�+��#�a��%�}@M^����h��yډY��C_m ���b��de�9������7x=lm�AS[6M���72|�c҇j�bF�F���t����*L���zg9���i�<O�-���Mˎ	�	�vNܬ���	�6�cM�Կz�^'r�,!=�bt`n<���n�o�'<;��bM�w�H���V�h��`1[�hޖeo��t�Yם_��0���Q��5�I�ۦ:�;qvTb;�N���q�Ö@�S*��뫷5��(GL!�n�4�(��u�Q���P������6��u����&eg���G��X�y�N ���t��ҩ�����P����f����x�*�{ݹ�� ��ES�A^�xDiN��s(Hk6s�0O���m�X��_�QX���Jr���1p�u�����YHa;V���機",t�p}�O���n�1�� }`�n{�����9�4E����N`���lg�b'T�=����l���������v������Y��4���L$�m�|Yr���<;Ż��ǚ�fa��.�{�Vv,Bu�ƭ�|�U�0�	)l�X�uXہ��xC��a{�GAY��A��8Y�9t� �k3�6��D�v/ �a �Q�6�J��Ų8��&W!*�g����:P���1S���h �� ��a�����e����������s®	H`�LjGO�ņ����[TÚ9��aQ�:���j�R�h;��s�m��3 �}�2�yYM���;]��Ŏ��A�6;@m�ӕU[���De A�yRV�?�~,�;�v�Ğ�y��;��Ђ#�x�ð]P�znN�]�ЦOOߞ�/{�i��W�^����ڸ@�6�)��k�x0[���`��L�y)b�^|m��Qd��U\��0���/�C��M�:D#;(��߬q�ͻ	��~l �
�r���Ѧ+(�Y�3�������ii;P�>S<�c9~3�as*w�XZ����&���1�g����*���{�h�)Iz�͋EMb5�pܛW�� �����I��8+��i|&X�Xal�l%��D�`�B��C�tL�:��zw�nF�Y��'��Epħ��.�1�ps^�K6��H�(쏲���Z���8 T��0o�L�h&y��ǧ'��u�Ű�a�Rz����8'�z�W����6�6M��?+a��B�ԓ�Zh�,��\3�P�z��1��ic<c��e���>��}5�M"P8u
+�����!��̧{1KL�f�����Up��Q�̀�G,]�k)�s� nHI~;|~��`0��KW�_��d�~<�����S ��u�`E�ݶ5^�����Tz���Fė���0Xs�1����������A�[�����9�D��\�[�k���`e���8�� Λ��r�3��b;~��M����,9>d�KfW�-��Fa�fY���
I�̜@eG�w:�ދ-�t�94{�a���4����]X�2���6;�f��,���&Βdإ5*X�n���wW?	�Ϫ3���{8жT��G'�>:M�~�nc����F،1�#�@!kmn���?ןFE�	����nl8��?�Y�ڽ��Ӗ]Tg@΢��m���_��y�M[D-\��	J��g�df~V�'��JкMQϺC���U5���<�'���]�\>�S    ,)�x9���&��v	m�������/h,��-4��z}�����dS�����y��B�N���^zu���ѩ���C1e�|���_s�m�Ǎ9��:����+���(�h{G?7��՞��5"9�yiَ[��Z�`o�M�)�N�j�dg�yn�O3�0`��e�񚟚J�w�.��I��B�ý�|�8y�r��3�?��8���Fb��~�6Y��+v����;��S�ٟ�g'�^g��f��N/v��u�z[�����n���X&|&ZET��hE��d��i['i���� �_$�o��]X�F�zt7%� %�t�/u��z8���sz�`3f��_�v�{��ٴ�x��8�p�_�P2v��c胴pʸ"�ǲd���8���2+�)�3����Y�7�ؤ�����Z߷�I�}�A�v��NS�^��0�
��)!�g�p9�^�zdN�  �ń N�hiݫ���!m��e{^p�Y�٬�vZ-=�m��!u��=��f.��̫���u�b5����[;R���d8�h�/�o)�L �ሿj�OXJG���N�z�~�P�F&����c���-�N��u"O=8�/8��,�����N���hV	�!��lӱh������M�9��F0Zn���f+q3���Q�.<왞l
]�躘�],�.vֈҏOn���@���2[�	A�t�L6�"��2��7�3 ��#��ד�=�����2>r������S}�T�"����������P��}�������	�^Pg����m8�>!<�R� ����+}Zf�v$���"�i(�W��N��u&�EC��x<��dǲ�Δ�<���Z��>(L!�<:�c$�����r�U ��)�$Ê����z<l?����G���>ۨ�d*���U�֍��Csԕ=����IO��?�Jkm�;^�Sy�uo�<4����}b�qrvlL������z��c��8%$�X`ia�}=�Q#���y�ۢC�-_7���4Hnre>��6l)	�4.q8�<�B覄ÛM�QoP��q������_����(b� ���;ty��"���)[ZC+�&t��7��T>i8Mb�=l٧"�f�H� ��._��s�5`�:T�q�xo w�z+�9��a�z�-�t{o%�o>��J7Fdkm#�!��ϩN�tj���:�����^Z��������P|{�9$ގ�N�;ǲ��s��7�Ѭne�E�r��E;�q1������g�nX��g��m��6��=!p��R�9��l����G>e�,�̴�ﲛ�>9v��}F�L��/�x���x��������,Rx����N
��
���P�S�l�fh���D�l�9(v;���M�紃�㴡e4)s�Z9�J��|W�a�Vh:*f�&^D�nl���ـv�[�:m�l5ۥ�%�&;�|���|$����U�S��r��{�����F�qqn7f-9Q&��l�5l4�b����y�X;��\��>�q^gÁ��@�u�X���	m����o�1���]�lïo6=!�V#Ȟ�h�����>��;!�p;AJM���YD�s�-Ϡ4S�'�91k�}<QW��m�}r(�i=�G�L��[�k]�)R���0���Wt�.�eswxJ:Iq�@��k�
u�@�F4��>y�f�:�Dg���,V3N�m@�8�����t���nN�Lؤt~�#v:+����_fW�6�>�lX�P�������*�,���?����{~S:>��2cj?OwX6�Tm�o` ֦�c9&��5��ҥm
ί��g݃ʁ`Sn�	Q8ڡ������4���Zz��1Ze��6z\�H�6#�S��]���a��܀а�V6r&��YcA��VI}5�u�W8̭��3�QI���ί�:����j�=��89����,����a&��K7pу�m�^��2��?�Y��I�����J[G-?�?�A�����^4=C>>j�3"}H��Q����`yY>,]ۜdA��DY:jW�R<�R;(�e���f�l��ԩ�X���tU1%���EGM�;l�=��@����N�y���&	���8�M��j�@��߰Q�}�/��۪ܙ��,����yۙ�?�|�X�M�>���s�)J�$��� �@��Ck6 ��莳+�Ns��i�������d���L����Qw�����"75(�sb��t��C�R�'7;�a^�N�HM���̶���}�Y��Qosˀuh�d�BO�Yhl/P���ч�;���-�2X��b�vK �����t��w8��c��'E�C����se��-�*����ޘ@"�>S4��λ���u�qd��s��,tC�,w��0��w*��r��,:���W�3S�1�-gmr:�Y@�������{L5}��$�c�3v	�b��MY�Ue����q�]�0�Ћ@��3!�pK��!c�����6X}�ՠ[+��6���D�z��	[s��m��>,��]\DOMǳ)j��lq� v�BM�G.[f�*sX��v�TtQ5$m�6��_�1���,i������(n�3�����*Sg�'���U��S��H\L{�n�	�p}��cc�tʳ�MʭM�L�� e*���n�i!��������
t��N��N���O�^>CHM�0�h�Ͼ�Y7���xâ��@���`IٜJ�rsE (��O�z ,����Vml���~k�Ջ�L[�)X6�Jѱ��nB$������L���'���#�agw/ �e�2҅��NyyLP6J��dh��k +��Dw �m�M�O��X�Ģ�0��u<�k����|Fe�ad��^�IG�vv&��lm�b��Xt��[��	�Ef���S�v5�,��E�5m��Y���x�XFl@^������T�J�� \a8 ދͱ���7�����(�`���@/ո�s���Vj��=+C߬�\F�����2(��z�-��`y���d�Ď)vK�����6nY?�����W�g��L�1 �z&����U-���������bi��mJ
ʑ�۰A�A���I~��;��8�����α	��L����m��jR}U|o[�X\d�[~1��/ gL2└z�*�yZA���E?��n��5�z���Tw,œ>�&��?�-��g�ds��?�^�n:�*��q�l��s�שE�/�c2��gH���H����{ߘ��a�G�!��:� �Jźs�R����p����Z<?_y@K��a�å�5{��3�K����R�MΨ'��OV�)����9����&!�n58�C��l��40��jh��t�ڿ(���_NE_�a+6*�]��ȸ���E>rZs�V& ��
��8@Q�י;��ek�.S�셮�Mb����k�)?r(-eh
����hIF�nb��Ǿ�V�~�{ɪ[�֛����!�0 Ӗ�������H�D;%H0����lC$ɒ�����L��7 w�Gt���#�����9Z��y������E�ҲGJ�7h�GJ`ˑ��#��R��D�r�� �Fl7�|�~HȷY�N�U+y�}~��o`�����Nu:�pj	ɬ�s��0���'k:@ߖ�T>��%#'�RZOZG|{�����$k6д&�IM	&�|&�%���`羟�y(�k���E�wS�	�l'*s���n׾����3��b�$5����9�i����ƕe-�����!��7�����;�bA���G��	F;��]^����k��	��EZ�@�x�5��^�Z|W+�:�2���H#�0=ioH�ܣ���>*��^��(��zYu'0�w����>K���J,�����yҩ#��Du��L�5lN+;!��ӰT%���C���yh	�5
М:�6���>Mw@{s̬��-F@cK�V�:�S3����X���b��:��]P�E"N�vp�y�4I�M���C^#]�[{;r&+��L��I�1�}��8�-�F��O�G��	3�3�~��Ҥ�0� y�;DY��gĕ�7�5����e�u2O��ݻ[ˎ~k�����k���o�$�'�*_�    3����*-�Wh:ʢbҮH���)�+�2H[_,��3FkЮ���~R����VpK	0+i�{_.ī{��7x��Z]��\Qun�����Ĭ��,�7�k��,*,�����3m�C�з��r@��'��C⎽NO�k_�$�Dڕ�M^�t��GV:՗
��R'Ž'�sW�x�P��*���Bئ_�`��d�9�}��E2�-3�V��d��8��Jd�	I.�iM��T��Y��-y+H�۔�n���@NP� �n�?��nyvB/��d��`�m�4}��5BY�s����Z�/��/�Ęj�����+�T��]�S��|^\5�:/��p!�Bi�t���t����R>���.ڗ!O.�&�o�E)?[��V��d"��6f#��Q���d��H�RiP\ʫ����6�섫���OdJ|Ɨ�o-'�rs5=H �û������Ō<�4��[ �|��ck�C�ߪ� �����%��+x�T��̘�|�5��A)��\��ё�7�(4��}tI�C��Vjg6Z����̹t'	�Z���E�zb�Bɔ	9�3�dygg8�l������'Ng<$�oc��6S���O��n�==W��ù~8񰟅�"�܈t��s֗�І��/z0J)��%9��O׶f:󭤜$�|�	�U<�-}�ጧ`K�����P%��<о���i��6�C��P��*V�	]�>Ҙb`ߥ~�۵@&��)�i��8���5��h�/i���T��P�N��_b17� ���&E����2ǟ�^�V�ߟ�ĘL��l����x��{A���Ҧ�%�]��r|Nu8���j�[����rD�`�;Ӄn$�I��m�c2�4���g� ��e�(?l`W��x)���<��\:N��Œs�WG�i^j֜*i��|F]�xɢ��ǅMj��D|r��e�-���J}Iu��)�K��m38P�h�3�1o�L6ua��"�Ӟ���Vz��Q�C�QJ3�-dՉB��k�h��;\t�'ӌ3!��K#E��Vh�'@�@-��[t�����hϤ7�n֑<k�a1�s�ٓ.����C��8������ ��T���t��U�)�^{.��A���Z�X��{�lP:�r=@ޓ_�M��Hp}����t<�L���+��"�1�Je�ڬ�'�S�m^�;�i!`wbYA��8��?����!�*�˒��`!9���r��l�z���f���?J�z 8xJ9�#@��aO�d�J*t�j�E>4	ȁ����G�&�}�i^O.�)���l�)�Q4��*��,]���W�΁�f/���`--�|0tq�f�����0~�FLO�JA��\�H�ΐ�+��RD&�|�D�s��-�#Zz9����ط\�h1eK�ܜ�d�i��10i�-I�J?<��0����#��n$�D���o@]��H7����`��6�Jk���̄)m�ի���88��\�dLx'�Nk�K"]�$�qS�8#Ot�N��c�v��))��!f�!�)9���4�R�'rl�EC�4{�F��D���j���|��uXDĴ뤸+?X�g��YGx�5�.��yJi�݁S?�0� o�^29zf�I^~�a�ά�xᚸ�R4,�k�,��vX\�P>��	��Y�~�2iS%��|`��H}L���T�����)�(psm0E��%��A�z�D�&�@	��F ���I�x�5ؕ���;����
/��S9;)�d͗��i��,i�;e���o�ף�H����/�Lʓ�t����lz�m�GU�̆(�'�f(@�w��r����4{�Kumim�<iŇ�T��>��ߩ���q�<[�Ne�li��ip�ф��Mq1My�=��#����8����>���R�>4r�RY�rnc؇��$��?���I|���~�U�es�Ҥ�J�x�g^W�b�*(�T&bV��~97�[���E�������5�$Ჵ[*�Q}����4�\��[��(��L�������*���u�]������̱%XJ,b���3<��\�A_u���jp:iF��3q嶬\�S�v+��X��
�����۸����9��iqX��W����*�6�\���Ǒ�%�zH��~[�s�+*X�a�S������H>:��C�0_fg���;��&d$��.�`�vo"���M;�lC��5��ُ��y7��A|F
~�B����B[��}Ք{�?���>��(ON:��g7��|��ٚ�H��c�l�S�`�y��;��9[�c�;q��){ӃZ\$K�t%��(�%��G�ޛ��2�,��Ρ�yb�C �*���.X�[e�R�'�/��xm���GW��'����o���7b\]b�	Ð��p̧N��v���o�Ikc�N����5�;<%(������w�ޗ���7��~��,���c
��şBݲqfo��fl4���]��Y��v����ގ�#k(s��Ѹ��xy����x��7bV	*��9�S��rj�2��T2"f�`rjd���'�,Ҹ�TJ���k��<=fhy,��/Y�搌�)@�6ޮ9F�)�^�Oj
yT�Wh�~X��;���ʦ-����GL���/Ԏ�`�oV��:$���1���WA(�T�yM@ �QQ��Ӱ:��x�� QeV�	<I�8���V$��17c�k���İ�����m|2
���� z?A���,�T�OA��!�%���y}-�E'�k�3[�U�%	!�lZ���{ �J��<�4��aJ�?�5G�u"��:��5�<��{���N�3�YrOD�-�&j.6 �\
�����$���9��i�u�P)��mҍ"{ S��A E\e��c���L��T��ǧB�Ge^�:=��(���@��.mHJs!m;4e~��ȶ}a���g��.��o�L����^ �C���4��r�~:��r%�ɩ0a�li�Cz��2���J���Rt@��'Ķ�ɕ�%�������'g?�����)RQ�ޭ�`�x��{��8Ꮖ|c�Cq��$=�iC�2�^�r�aͭ�fzJ'6F�2e#O�/�tX�� RK�>�9X��	�;D�m��ڌ& ���#%!ｐ�ǋ`���%����P,�h�%�Xn�k���(��>L+�тO�o�ag�B��p4�Gd9y��狕�j�]��X\V���?��ִ�n
j���G�b�'��4��p,uv7=�~ja_R�.7��k�PXf<���~�m�\���L�cv��3%.�s .�U����ΔuA%c���s��^2�y6�}4_���2]��
Q����.8�J��v���c�������Ӫ��r8�|9ݢ�`Q$$��nM�F�J���^�#�
A��d^JPÞ7���e)�99�s\�)n'�q�ZQ���K^>vФU�� ��w���-�3��Q����z��I`���ڒ�J%�n�~�̩���S��F���A�����_��dur��{!<v�}3)������|��lZ�=�t�Vr2����HC@���X�M��C���A�L	P��\4|�<_�#U	$%�X&�}�a2�i	��^>CÛ+\
��"�
��:ZS��$�t�96Z���63���I6s?RSo�z�+����aJ���j�pR�����|�:x+��S�hI'y!9W��B��;Mdn���M�t��2�3�I�I��<F�6GZw��jj��P-�T:� D��=I -O����?����ixJ�AxH�`��Ye~<�@a��#�ve^���-�/<Q�W:��9/��P��"�괧M��O�T���c��E��y��V&�f��^�IOst���$R>�S(@>����U+�%��;��]ڷ*�=!��/p�̙��*S���~��0pӺ�PR�H��W.��q��?�=VKQ�Lk��(S){�R#?J� �3[�_ �<��K$�C,%)�d��mF�΁���Z`ǹ�w-n�͘<#����f�O��|˱�T����Ij�6V���I5��!6��IZd$o}z�ԑ�.�t܌wo� *��lv_ gwk���vJ+���:ݖ�EKȗᨹ�*&%䥗I�v�3J��Ϙ�nB	�i/�!�`Ϲ�����׻    �ND�T��DC�0=L��.M�}e�7Cg�=�����Զ��"�y�%K�1����������I��;��o.|s�nj��ː>��~6/��{�'V�^�ar��UM����iu=qᢱVs�d�'�q*�A���Vs��̠����"<(����<
�憭�$��t���3΅��{#�� �/ػ��9�qI�JD����ާ*�ş��`SBo7�qѽ���з)ҁ%U�C~������K��R�ڧ=���㗗��Hn��yp��\*�c�=���9���BS���h����
�������l?�6���������k�T�9Ď�|zӛ)�~��m����Ǜ�9òԀ)��\�ܟS�����Mב�yJ�邔G����,�,a�>���ߪ� }*3ޗW�XWC���u+� _������ ���d-N�pi����|�T�+�l#�<|�ܥX��&bj�`?<�%�J���VL�4�CEu��;L�4�4$y�e	\k��F�i�D�t�H��K����A�C�`��w�yq�U��2��JF �q�a�F�YKj��4��wF��]����?{�|��!��{�9ׅ4*^�y�[�,ρ�
z񴜟yRq�o���g�ON�����x*�{8:�)��-L �"���d� ���x�}t��Xss �#;�h�V��jD�4��$�Ȋ�V1彭�1����_i?�ηb�2�Jt6�Y�3g��Dr��V���%0$EL�������F�ߕU@"���r0�D�V���`��=���1°�����WGȪ��~��F=W���!�8�+��L�x`u��6�Ab§Ι����� ��8Y���kV��p�n~�R�|���ݩ��vb��'7��y�j���ƣ%< �Zq�����ҞNj��g7�����Gr�sV}$0��~�i�{<:�sj�a�Qsf�?�|+�1�5۽���Ld8Re��l�N.i��������P(���R��$l���lN;�Mm5��z��Jd~c�G�� &�>�羟�'�Lu����-ľqnNЕ㗄D��-T�B������W��Kk��N|q,Fs��֚L�p|�9V�(��u�Cq���b/v�cu[Y�B�OyE�(��$2T�mHC��5��~�_v��v�Ë=�K~B
���u�:3cR=&��r��@OL�Ϳ�x?M�\>�č��q�ܛ2/U8{�ټ�[Υ�����0�IQ��;:��k�bbD�'��L�#ʦ�҃��O��| ���=�ofƁ;p�"����J��V�������'�K!O�leb5$d����\:���ڷ+Q�q��e��IȚ�9�h��������O�-���۸���Ֆf�G���&ȶ�T�n����ZLW���o�c�7�~.6�^1��5��QO�ֆ����M�j~N�	�PM��/��Y͛�"'Z���:���>9���n�ȳV$��R��q�n�B�?���Ɉ��I��[��� u�(�/ː�`t�^](�簭~Ѵ����pBm��<f*d�D���p�m9��|����/��c�TMr�CWJ��P+ح��� Pϔ���ߍ�,���͑L#>�8J7��pģP�9�o��'�p-0�儗fq�|z6��I��1��)��*���H�GN�:���[��|Z��Hd ��a��@c��;%�Wո�ʖ�{\�'�܉�(/h�/�Ւ�C���&����_�[|��
�~�w���-4��a���Q^����S��V(��!�n�ܬ��^69���U�	o���� �tC��g�����6��b��W+������DK�#�}"����Ā %�^
�����H$9�}[�?��3��E�M�K��b&G�t$ae=����l�K�܌��� ��pV�>p�_,[1�sJy;(������syJ�f9/iE�F����I1��Rz��K
�=�7ٞo��P�ho��������yf��X2%t���U͒7���O�G�o�˷�{]ﵖ���m��������6���R�f�&��]��yc������1<rб�w��2^��|�_rKH�R�͵!��$gi�qhRt<�����q
�g+I"���<}������G.���}P�����U�X��2��Jnq�U�h����`�0_�j��:$�"���O�*f�hќ����{�> ;z�8e�|��p^i��fr#?q'��*11�%��h݉j( ��n6	'W�܍��[��y'ԅ�a���(v��S�Ѫ�px����A�C�� `)${�v��Z����1-�h�lM��2��"� �y���5(K��#�r�v�76 Q�$���G2�e�q%J{��M�v��"[���w�/���<�Ǡ�Wj�V��KsB�L�'֙��`z�����sD(�5 �;�'Z�F�� sɉ/�Ԝ$�&��䖀~���$På�G����x��+����yA������e�E�Ĉf��rL�ʋ'|�7�;�~���X	.��&w���Y��ɂ]�Z�玅��D�J6عZ7�Ŵ���7L��g��!��$b��>/ܼ�4�/î缁�[XٶN#N@��H���.���l���Wŀ��������B��U[Ɖ	��5x0�9�zK]�c���f:��\l�f�4By+��o�]Aެ�Y��E��iap�tlITm�Vл��2H���,/�<Xέ�@݊T���Ԯ���e�B	�#H�a���i�o{��p\,1����h�~	�����
�=B|j��t@y|s�5[�yMЯ9]eK,~����d�n4�\����	j��Lבk�V��S�XL���D �˗50��8�?����U��$��~�x�ο�o�p{fr���N�
�~Ԡ����_���Z3���J,���yߥ�3MR�����h����z��oixӂҘN�ȶ�VX�H�ӛ�2��� � ���Ä4��Q���to��:T����a�!]r��/��0�͏@?���$�m��OY
XOBf*) �toI����&��A��h".��6*q8��`�$�^L�YO����A�.�ι�pL�Y�j���-I���Ml^ ����䏿&���*^h���֤�j[�)bkK�"�9�6��SR��6�E�+^�҇;5��Oir��*�%��sɡ�1��7o'���i\��P�z��:�_�m��*T��&�,��<g�v�]A���I|{��Ss*;]J+y�����6�<�"�7mo�Q������D-D�[�'�xj�g�� ��,=� LyiqI��C!H���ޥQf���	q�9�優>S����P�|�e�p�bD/�z�w��h0�H�^����A�ԉ���.a�k,+��^�4������u;���d�!e�Qz�;K���
�|Է-�n_��s�(�*ӇN�|z�aT�R�6ƚ٩�]6�hӁ�$V�"M�{�#׀א�]�"�M8��E�4�~�RF����u@g��.��H�8�}nB��n�\&<�����*~r�e�zn�>r����2�+[S|�;.:��Vz@�.n�=Y* �tM��RK*�	���Xiڐjf2u}����2m�$)�����z1rH C��z0]{��u#���^�^���@uI��>��S�u�s���w5�[�UI�+��j��T5`y���o��=Eڼ�c��=m���E��p���
p�M>����T�f����"�$B�|�w/$p�U�D������Z��U�#aǱ�����x+�ٴz��l&`�&?&�|r6�29�tR�P���y3���|�<��̝f�T�	V�	[V�ב΄8���67$��cK�P��n����������c	�?C�}�?�2+��Mcq���}b6	/��c�����U��8i0;�a�_�f!%�/�|{;B:aĂ���O:��]��L3�~J_�̝f��;���I߾g�\�N�����%�+�� ��#Σ1�6�<#�X�dy=,G�����ﹰ�����:�ϹF�y�?��]��=��Z�|>�����t��p���#��L�?孪�&�eLPK���ң��C&�S� �$    5�ք[9��6%e���v�hЯcb��t�<2U����T������}�yO�;�.,�&�.5�:Q�ss��Zh4�#t���"Ҫ��?sQ.��;��R47�����G�� yp�>�]���p#Z*��A_��wn��ɹ.�.�� u5t��I7>U:>XA5��չ�������_Rv���n=Ŵ81�j �������qo�+�4�t~�V�YG�Vh�IL����|z�����]��.�4�zn�[:7�cy���6�!}�e�r��&�b�o��s<��!��s]l,����絭��ev��B��E�dC���ݾ���BTh+�H�cύks����\1���8?�}���o|v �k*a���a3�f.X�;M����	d��Z����Z�y*x~������: �Ә%<%�2H�q</��"5��~�!'��ټ�O���G.��,�h�� �o���BKr������v2�IL���گ��cO�?�q��ם��~YѪLm����d]J�7��X�e���L��,��Zm(���VH���w	r����B���~��T
�FG��k> �^���^���*�4�$�9���uYQ�����C���b	=T���J5"��$�G���{Fai�kIk���Z��{\��������K��̜�ըm)So�Q��|���?�&6�3�n.﹃�I����z!֖pZ����:pOm��
ҴC!w���q��S·�X�ɦ�Wv���-w \Wrω���p�-����QR'y���}X�.�\ց��9��u�m�u� Fҭo��<|�\V?����*5S���PN�	C��G�Y�E~4:0<9�$�	�L��О�>�o䋔��l|�2(��}���z�-�0ҩ��PW�G��ʹ7��dF⩍��[ڀn"G��/�}A8��L|Y�=Ծ�~WB�Z��}0��H�Pzλ=ZrL�2�<	�Y�^��O�, xV�Mc��_�s|�v�4�<��9�O
#���TX��ez�|���M7T��a�R�����]
�소ѕÝ3z�盈���LŮzx����oJ�x%�[g��q!��$`�/?k��Z*p��׫��_VO���c�\�P�2�.e+t[~�#�5�J�J���p��=N5�ޗ�j�a 8��H���T��fO«�ꂨ���&��rn£m���,,��$C:��7���#`���;%|='����O˸T4���_�y�XPZβ�|h##�%"�EN�U�?)7d*���|�������Z�'J��!����s�4�/U�Ϡ�p[�*,����ܶ�tԐ	�hXS�;e��G߉"����p$�NzV��9��BM29]���ިb}v���r��{sϊ��"\��`j�����W��'�A#�4$�Q��Jn'�R�R��4�M�e�{Z�_��L7]��ѻ��c�H��~�����җ\X� X�@��=:X��<$��$~$��g*�?��ܐ���C_��'�Dd��M��m�B]��2뤁��Cˏop����%G�5؄﹯���$�ky�?m�Μ�(r��L���nÁ������٨���o����tm�oSM&�)|G��\a��B��f��&	$���Yf��4��X;/�\�d�<��}���[+�5��[�'��I�iN^S�᫂�s��*�� Wpp�xRN�I�v'��r��FGW/"	Eم�V~��2[z�V7����s�^(�~u!Z�a�	��{O�,��M���zZ�#Oi�#J)���`�Ncn9>`=9�����w6�pZRi�*���yyȧk�]�=���O��eEG����rd�ܡ����t.�\D�)����� 
S��\VC�T+ӷ�O�J�����J�a_�xW��/�$���7��)��o��^j�A�o�78�	Q,q����t�:>wf�ږ?��9�g�o��|����הG��r�����o���3.�^́��箧�OE`Z��y��2���T��'�ܓ�Q���|�45��gw%<=��=����B���4׍�(ռV�RENi���\�=��5�X�����n0ˮv�0�H�%_m80&b>6̩4��,X̛�.����K	93�}�\�Ʌ܋	4����7ժ���Z^����H	K=����	��j��Ȧ�m;A�B3�fi��D�}��u�/.������qh_�]hR5?�>��B�3�#a���Ї�L˗y�|v���t|�Y������9)|�[��m@
u3�B�7�1�m;���ʁ��$P�I��L/�P���a{�4�y�WF�A���]R�S����J��r�X�2��Jk:�E�T;�`f'V{�Fw`�"�����>�6��b1�����>�vc���)��� p����R�V:��n9s�IR��?������4���B�[��`|��~������|r,ڇ�V�`�ݜ��v=�f�8)�W�~��	�� �i�i�g�,t����l���6�͂�7�N�<���@e燩9���o�����M�b�A��(� @=lK�C.M��;��U�F*-?{�w�'ܡ��
��b������f�9����d���,��� ;��LtByn�}���X0�G=��,�	��Y�������~Y��I�T�5n�s��<��d��y�G���|(C%�i[>A�F�sE�d�\��c� �h!�k����we?�6�/ ���N��>�/���A���&�/��waϔܔ�7��D�CL�w����z�m�5D�w���C��9y�CF3�<�4|�Y�GӐ�gq�W+�$�L�M��v�:t�o�-[>�z>�G{�r��Zk:��[�_6�.+L�ԅ�%v��YNd�B�� T��G$�rQ/}���	�U��)lfM��ש�c誢�Ϧ��D��0�Qј.�jέ��]M�}y
����������E��a<��ӥ7��t���c���\h��j�`�}�f�Q�x���J�" r���=��>o�s$H�x�'��"�����'����=��zCZ��$|IK�w5V���e
I���k������YZ�V�J�ֵ#ML��zcxY�(~U ����L�N�O1��\'l�6�?�_�M'�
O�Nsb�F��ZtB�a�[�U'\�:Uφ�晏��U$�-W/�����`� H��ռ�f� �a���M��X��H^�ǭUy����A��f���j4��U2�ep�b�������VC�Pg?o�6.���|PeI_���6%du]�N}�6J�u��P�Z_Hl�
7�!i����W�k�AH���� ��#�d`>��]���������8����G5M:ʈh�zh��̂C������2�D��ǃ��}ܯUp(%���AE넯2���m	��d��8#_��I�Hnc�VT=*̘H5�;��Y�\�8�8�?U�5l�ӹ��N��C>��� �����`�ʣ8�����۝�<�[�/4�hsq.�(�ǧ*T��y�P�
Y���jtR�����ǿl�?�-��I�Rʔ2��������wۏ�U��Na!Q�͝���N�pى,���'N�U��Ť�S��HM�K�?q����ɑ	;��O���k��>07`�_7	��
�s�挏>ÿ9/�by�tc�3�4����Ioa��Ker�+����@{�i�
0��'U����19��ϱ�)$��@#'m�,�#��gS�^�a~"�ku�ϖ?(���ĥ��Sp� I����$���N�,���R 'v���6|a�Ɲ���&��Jeg&��C�b:���F�ږ<���Z���?~��:S�Lwg��AV�?f�ӡ��s��T%8G\���b�}P:�3m;6���p�����*#/ߔ�|�6(����r�NW~�H<4?�eH;_�)��Ö]S4���a:���GTu�B�܀j��(�4*m�Lt�DQ��x� h�� ��8qKE.cG� h�k�jp=1MICʈ�}r�ZD3�����2�ﳕ�?w�SJ�#��
��}�~H̅l<��t;�2*4.�:6�;9۵Ȟ�����F�^
�7mI�xP �ͦ�3���u=&���F R�:F�c���פ���x��JY2Ypg6<:��YS ol�%7#�a37��9C�,�8$��o�    �g�)j������M��H�亊v�������'��NzP�W:o�����j�BEm�ٻ���ʸJ"0��.�9��'�S��x��D�G�u��6�WBp�:�%�,�xշ�1=<#R��;�'��Æ�A� nY��5�˧T�K%����~��_!���b���I1U�5Y�e�[f9�g[ɛ\�99�E�L)�g˩�-��i;���ј��Ҥ	'?�o����<$:�yI)W���;�����6�z���oe�?$;2�[m���S���m`����
n��
� ǃ�Nυ/�]I�u�̞�K��
��;�b"���l���GX�]���E�Kj0�ڦ��KOO�	a���bݔ���Jq7J$���l�;�Ǘ��	��+�g�JnD8��Σ���^H��'A{����7Wm#8DK���#�]�%�^�u��&45k�� �θ��ұ�[�m�s�K�f�\� ��}Y$_���窗�i�h;r��g�����ĤŜUH$���t��^6��O�%5�J�ϧ�D�(^kŮ�l�I�~K�ᛛ:�NyIw�}�\؉��ca�������gr$v&Y\����o�s�u��9�]����#� �E4(��hz�n̳Y�^�?I_//���6�9v�@�,8��I%���XS�l�dX�����d]�Ef^e�$>c��>�z%t����P*��o�^��
	;٬��+/�r	{�i9s��㓖*m�Dd����GN�&g�~�A�*�4�C�F����L�4^��S;��_dTS��U�O��tB$s)�7|�-5�ߖ�"���rDFL��MT��{;(�X��D|@�����C<8���+����Y�Ⱬ`�3��ͷKp_�����~ֵ&����0+>X�wT�Ρ=w�<dۧ���sJ�)�t%��|�1��;c��N��>ܫH�����O��л �	j���hD�GY�^��N�T)>�MGI�,i8��m���"�����8��3���N��K~�PQ�[ޞ
����9N��?ޔ����2��Y��<T
���U��� [r[�ԑ��@�f�Db�?`��u�s�0�h��{�sU̟��~?m�LR�U�4�d�"�5Q^�r�&��A7��p�͘R2=�1!��C�)��9��!7]�>����œz̓��M�4ɫ6<��yl��d�Ts���d�f��Ϊ�d �������<����=����5D^o��;�2.�9�#Ws�s�Gg��8D���"���${�����n�)�x!a>>*�R�yy��f|� ��NN�#�.�jC��%ߏ�T'�����n����u�)���7P6��9��Nq�Z`w
��?��~-�K�4Pw�h��5u\�:����_�{�1�<w��
���3� �w�y��X��ٖ9����E�Y-'���/g���E�q�>OmC����⩅Xʡ(�z*x�u���Rh^���I���SM����i���D%!�uN����f?IB9������p�2��.H�)�7��[��z�χ-��
�GZh��}��c-�T�x@�NPo�	?��gS4���y�8��Ӽ[�lH��(B<�Ol&��?Ie���`�	�$��H�0ߋ��̛{Mv�Sm<W�1�iEIUb��uC�5W�o��sA:�?��)�n	��l:� �K���c��D"�S�(� �d$��ܿ��_F�tI�ZQ}x��,2{	U�P=@�I8Ot߿��zN�A,W�b��&���}/wt����֏�C�
���:
���k�HP-U�}�Tu�-�ODz_������=��Iz�!?�S}ǇVK�˔h����5�CŎ��o���c*9ډ�a������rr�x�m/>��kR����j8�P�b�W�]�'�l�e;��d���ܕ�^+!𴴼�S�p�T<�O:��4ͺϴ�kӒ.m�-1�!1>�=ǒ�����j2"a����B6���8Af�ԡ�J��i�~��3��p@��W�i � =��#�fh��Zؙ���E�Fn�� ���L���t���\�����T�l�H3bYCG0��ddr°�S���G�Wo���=��'�l�ɑ3�a.6��ݡ�;�$ S@7,7;�O�G�I��Q��V�X8�RrL��s����tc�z�������l�목˼�iK�E=�� P��>�\$4lJQ���>��>�̾�k� �w-E�����PJ3/������r�	nay�<I-[�s}��̖�2�7$��~�4 ��!_��f>ƒ�<}�����~�5�4-���ū�
h#1��L�^|9�d��8��̷�<7�\�~��M��3����x�����xi���*�����(�:o���	5)�i
ӕ�R��.:&ѽ���G��6�+_�x�����1�i�v̝��>^x6��R2�P��+R
m�ώ[�f��䖄X��F�����P�{��"�1�P�.(�XR��%�*^@@�i�ϫ�Y@��S���U���� ��ZJF��l>�@�s�#	-[�����j>���c �>g��͜�΢�����Bh��	����/
�w\��MRG�z�Z���i�u��-��K}�g���g)��CM���b�]d#=�^~w�)j
�1�j(D6���m���W�r�j�wt$�G��O��E#y�2���\�"6-*%��^�@�q~/��T�9�ݞ�@g��ߜl�h��y�`�$�1`ox^��,���Gߧ�O��E��od���s�D��L9���<u�d2�0�����Cek���61���<G,@R�VJ@^�V�\�H�5��s�S�n<�=����� �W'_Z;U}��c;L�znҩ[�ق��O�ӵ�a�	/�<w�:����ՙ���1����R�?5�H�t^���X���?�G����`p�w��#��{w.�3s�x���$]=U�4�_o�A��YS�{�է��s�=��.i��͑A��[�b��z*פ����|��ˁɡ$H��r���U�$�a��0������$}��s+;'�VÄ�"�j�-�Jo�T��d.t^;�]ؙD����M�~��P�������`n��L|$s���O������iKђBi��7�;(���b��xa�6c�9�e)��̒�ʅ<�,}�͙b��զ�tc�t���iP�3Ldv�-,2�tv�Nu�Q�p6À�x�3��������똢���a��r$�w�S�����ӗ5EZ�~þ��P�݄N-�{R��6rMu9�~_�����^�����41` w�9ɫ�"u�r�o�#L0���)�<�0.ǹu����J̪�0=�t���s�#�� ���ئ�L��h�!̾�'yE/$�zyA >7�L���'UC�A����?��쬁d����ՒVWN	i�G��me���^��"��Z�G#�yh.�K�������C�]ݶ��U��ϋ8Z�+aa�{Az��מ@F�2'k!�
�9�e�)$ɴt �3���������赕3Is��r>!�L�2� ����@[�bM�-7��N%Iӎo4,a��.�C�KY�cH'���E����K�:
i��eY�۩K� x]��z��A�!~�mH�O�?J�5�x�b�L���7G�ޡ��	��l�#��gߊL�2N���w���\㕗I�8�+.��PiN��ʹ�<M��L�-9jͣ߀r��,@~��K�N��]�;{n�(�l�h�#��_��5�Ap�)!)}�w 5��bqߡI>��@ҚҤC�SZ�����k���I
C�˫�8��,���-#�wM=R�S�^f6|��j��L��$ަ�9R��Ÿ��1��M�������*/�t��b'/}�W�C�B~��dS��i;��[�~�ؗ�E��d	m���+�s�FB��p���	�&���t�ϗ�#�Z��
��ODI2)��sB4i�T�רFn���֝��3}�Ũd*�/���ZU�7E��ż)��eϽYP��#��wt������?��s�Z�i/�k�O���HpIGKֽ�����sZ�߼3���P�@��-�x�\����v��.�5�2�I+/�v�q�ms�k%O�m�    4OE3��X�yO�_p,�9�U&��h�:�M�AM�b��x���z�+�x���>L�Z�y^TEsKSi����$���",�L��)�Y�Zt|�7΄�RC�K鹗b�Mل|o��t�開��U���h�y����0Vԟ(�a����<#�QVI�f$'qO=��r���hKc��g��`��N|��A�1�]��r�}*�?�'-I^e>H��:�v�_^�]�Y�Xz*y�	�ƒ��{�vA�>_��T���/���pY�*E�w�����;p����1�q5�8��\�7Ov��$e�̈�����.$?��\C�Oi� ����ʟS:Z}p��r@׎�����J#�Apug�v"#3�� l�����5q�=����[�<�*M��K>�6Q�fw���{�Q
[��ġU�X��\p�6'<��������st�&��y��3ל�n�˻yI,��u�JQ�JcЫ�j�:&�'��t�77	���!)��w�n=G=�2�t�y��э�tY0�m�f�m-Q�z���Sc���G�	�OT<'�~ɴ/�s�%Eb��d��ZR$��tA������5��-���N���5�G>B�P������2��K?��x턘�}���x��=A��W:%�����n���+�C6�&g��HL��icZ�%�6�F�kO��@\6%[�,����A`�H��{��mpb�0���A�����N��Hs'�ѫP�o�g��F*~5>�3��A;)����6�d�"6�;ؖ��@pؓc*�}�O��#�"��/�uE�V�_��N�ׇ��3���f��9�+/=� onFL�	 �����u*7hl" M��dԈ��V5�yο�m������Wߴ�o���hf4��iƑ��͛fu_���&�Q�=���67_͔J-Ҵ�+�Ͻ�Xd�7�]
�B�&嚿�EF��Nt*��3L)���$�5N���7!D0�xS��Ч L�G��޾�q�=U�5�,��N�fi�H흦��t�{mY�2ZN9C���{mIZ���|�����b�J�;��u��\�L�.>o$� ��|$������Z�{(+�4.Ky?QI�.n;f��U'-?[ĺY8�i!�)բ�Bʎ���E�!�l��_�1O=�M�(:�K�v'�t�2"_�SDʤL��w��fQI�m)y~�i���9���˲!5���������\��0�,�)$�|~ʐ�����݅�
12"�7
�E��L?{߅7:Y_~?���r5���ƿKX����tx�+ǆ��gC�:e� ��3,�C(9��:��w��?+�6��e�ܘ�j$��t�w:U��M��n
��L[G�bo�O
�t����ߣ��K"C���N�ˡb�v0���H�P���ݦ�i	o�"z��wD�u�js��.�n�j;��Loz�W����3�VÖ'�
��!��̽r�������p�x"j�x�»��mgS���i�u�h�?�����,ؒ}pZ�H��2A�(N��!}uJyPYo5���1�8v��R��k�;��%��pc���ԆG�E2��B�w ?�SKсL�c /u��/�%���[fD��c�TiQ�"!mtTڕ�d�B�\v/�S�2�0�D�@��%5,��Z��9�'�����%���	4�1w���I�*Es�M�N^��c{˪�'˚�* i�k��݉s�c��.iO�`��;U�@]BB؋�&���L�Ӿ��BI"����t�Ȼ�a,��wbcaL�����ΝNt�e�=zo��z,1�^Ҵ$2&$��q����)}:�:SD�^�����0����y�<!*�)�W��Õ�̆uHZ�X���&���z�D��S���d��[��#��P(��ieF�8~������l�Mi2�]�SK�l���z7��A��%�}�ސ�a:��w�E�r >#���[�;ݔ���-��g�y9fi$-�H2OI�?�Lƴ��p��J=!�ˢ�zR\��̘e3v�i�9�*�sn�䳿�ع+Γ�&�5�}�?E�.���Ę�V�5�@�2|����l �|�� ��#�\�slO�}4��^�h2W7m����@��@��VGc�-;q�+/����&�Ռ���9��,�wk�;���;�,x'i16mr�l<�;�@��L����qV@��H���}��p�Xu�(�+/�ѱ�X#g< � �,��I��5��)c˖��PS�>��q+��D� Q�79
�UG��7q�sɉf�?r{��\�����bl�)	,|�Vle��(eGN�P-�`��8���"ŅEP���H0�Ii����[��(��M�������R�������umI��W���e�����q��-��\��~�y�{Xid�Ӹ,�h�r�X�(��4�.W�@E�I�xO�:��`e��m�5K=�G�@JO\�5��x�1wm��e�a�y`������Tx���0��c�䭦¹��b�^�S#�^h)�]�\�+�μ�QoR3Y�jD�_B�)���?��Nۢ3ɜ�ϩOO{�nD����u�����e��`x[U�J���ߒ���˶�C����0X9ֽ�~Om���@������}��e@}
��͞���e�]���>��}[{>S~�9����y�p�=<���v�3z`�=-�?NV̪��m����l�����g�.!��Ώ����E-?��,]����c���(4�)wL;�c�k�]j}SY=�Y���"}C���g�f���d��t�_�Z9�l�.�h�eƸ?�)$���^���u���\}E%f�@����8�@�>��L�̵h��y]\�w�$|'����y=@��O��xc�M
�&̽�8�s  �0�RZS^���I'�z�0L���32/�5%H7��
WBt�O�pN��@U�2��0)!ux�-��/Ӻ�bMT��X���P��P��Q۰�s�J���l"�4վA(;��&8�&��^��t�Sc��|S�%p��_&��62D�4��o�;ĭ��f�DEͱ�id��v���B���S��@W2��]��3�*HmBkJ(��T�d��N6,�6�kڔԝ�ۀ�v��$�ˁ`���D�Gr�ăw��c�8�oأؒ�r̷�c�g,6���ى�qJ'�3�)�r�.|D����ʷ��L���;�����l����O�T-��RI�'M���ئ*�!�l�D�����8y��8,�QA�H�vi��|��X'�$<Ei�8\N�T�6�lM���:G�P���.�+�/���kv���QY�+i�q�BM�:&D<����U�#�_���P'�&g�%&���B��m��~��7J���� txj��R�A	X��2�Ng7�E��Q˓p��5�F�I[���A�=R/��s�����I&L�XZ e��}Ā��粟t�S�Z���@���Kq��[��1Y���'�4�����u;!����9-T%.��K�	Y�.�M��/���c��Q��&���D�ܗ?����_ޏ밟EY�}�
��hUd�F�n����g6�^;�֥����RD�X��u9��H�D8o�0�,Qu����.Շ��{9&�It���������h��7�G��96L���1���*�͞0�Ӝ��Ou���g��簬��#��$�^g	���R�Ƚ0e���UH(M˟�����P��9:2��������~勵�W�毆f	6� �4���i��n�V����	���{�Щ%�(��6�l�K�8?ŝę��^F�(����꟢ӯ;q5J�
���~������h�o�n��Q�5�y�+��v��M߷SD{X]冦;+"���<���o�F;��I���$�&�I�CA�d�35�7�gz_�ݥw��@,}�M;�I����s�J�J����H^?�$��wL�Z��G+5ً�Ir�@�j '�v�pH��P/��4���y~\C�\�t�y{Cz�Է��Y#z�����׽趫��)˴b��,�s$��嫴qޭ��2 ����-��I���h=˔����e[1�ڪ�vt�ilK 瓕Kz�bڛ��X�AS�P�!��'�4��-M�    ��ij:�~b�e�f���(���|�4�{;J��,�\�|���Om,��V��ý�������l[�~�-ϵ��ď�fXg}y�Y���$�t����,���T
g2r�T�3^Tk�x�v�I�;��SybeА��&�?l�"�l�����o��7~1�f*.����Y���֗���av���<���)h	*�2(�PEc�g�Y�XCW�[.��!�̌�wS�z��mT�r�ɱ= ��ϯk���)ʁ	 �Չ���Hj��H7���qjIB�t��t��nsi�z��+�t5ZH�Η�nk�p:�|��A*���m�e��izB�8�{��6A��O��js&C�7';%e���_7� <���Y����PqX���W<h#����T�k�wm�O�m���Mi\R��2O1'�ۮ�E��X=�b�J��e۞Woc�	UjN��6�"ϪN�":�Yy>M�o}�"Y���:��O�H�v�sBN�CG~�<"�1ʘ|��O����G �F)d-��/˞�y�Y�rM+^_`�I怜X�b$��Y�3t���ޗD����������$�5%W
��<KWJ �9���Ɂ�0(}v�1�������*�y��Q�bh���}�zo�K��ǂ/ˉ��-�V�L��T:��j�a�=T0��R��K�͌����z�3j�HL�龗zj�J� ۱���J�2勧�L��6��y���彘��I5�`�?%8P�KO�t4�䫃S]O��A���z+y'ͧS��6��w�y��9ƚ�$�h,m:�!&<<*s�?�ļ|h��Z"��enJ.i4�m>��%�t��D���9��8�ϭ�w)��'gz�5��1�EQH�ȝ�+drʵ~��Ub����
n��Ƞ��&?U�w�S���p</)o@N��`�z .>	�3hм�͚v1%-�/V��U+}�{Jd�<�"�YKg�}W�3�@NPʟ���x@ߤW�A��9_A)�ӻS3ѕJ��'	���Ǫ-�b��	=/���d:�������_TnV*����M�Ib�%�<��
��������yz.��y֍E�1��.�B�:� ��;D���܃��d�&=?�:h|}/��-�X�}-��[�����t;M�8�u�T���ߚ�s������BL��@�\vS.�c�rX���O?�dF��U4�S7�^,���IM��lJ���wl*�n�3��L�4��y�TC$�LP2SѦ�,ul�%!9E���lѷ�;��t�A9��K�������,��d�5"��RYL����XZr|j����m�iM�P=�m)��N�g'��֚��#u���+��a�]nG���	p
V�Ϸ��XTP����������g�/�hW�ي�	�*�l]J���r�Ѧl|R�LӐ���#���Y#!���YǔH�<�i��/���7�f�]?�󚃕�`{����)�JO)������#�1z|�~v��H�ɀ�6�De{Q��YkIp�����Uc!�X1�����������_���_�Q�?i.w���AӒgK|Ho���'���ۭ�G�-$R�t��Pf4���A+�tڊ���\2|�ͳ �\j��<k�sQH�!��JŀP��{�!����(��#MD�a2r��L����l���=��b�]���61���9���-�r�/Xה��s�~�'��j �X�/GLO7P��(�=Mb�*bx�K8�h��w�Z�6?���er�M=��r�������
H�r�C9�ɸ�76��A����*W�����%/���>Y�e���ᮕ�g���Z�v'��`�񩑵�2G��4%���|�p�<�4l{�W�����3js�����eCN?�柽I����b�D�hF�X�H{8ڥBl���$ �F`�A���]Oe21�Hͧ�;��I�,�J�r�nMf�F�g�c�p?DR�$]�A&�V���?�N
ͤ��,4��Zh�  ���,n���24L/��-��IueK��[ޫ�Gj��v�v�v�|�Z��3ˀ;�v=Υ@O�"1	���G�H׳杚ֿ�\��$�c�O7���c����c�<O
l`��9�SCD�^O��lmO���,I�^Þ|�c��c�Ӷ���|���2���z����&��4\������I�<��au������䑎���J,�K�T�jܧ�l�2����#���aNH�i��@1�Ir������Y,&�'�2��D$$�.�1�u��4x����'�vY�s��􎩻�m0r�q?�������z~������\�����G5���t����zߊ>4�I^�Ǎ�����´�}Jr6T�W��\pG��	}[Gf�2��))#b x�_=��;!��ܑ�9��4��E��5n��R�T� D��9���O��V��p7�R�S
^��5����<ه����g�{�^�ˑ�����H\�9Q-�ڕ�t%�o�G}�@��}>O���Dާ�D9�Y�U/�$�Ԕf���s*���-�*�N�e&�AT%3��|�5�^ޟ&��/W�qۊ8���[)�yY<�߄;���<���MD�`������wȞ�ƥ��`D�)g�~P�H &c�G��x)��&g&z��(�#�/��kI�"��S����"9����c�q�J�SL���NK�'�+qY����ڼ���x����s��я[ @O��v��t\�%� 
�L�xA/��&���m��恒��H�Vj!���& ]o��d���E��l���z˸ϥ�(d�9���n�>:r��9 '���@ֳ�t(�	����eL�t{�#yW��+w갫�IR%3Cd=��Y�9�����*���>J���jg��;,S�+��>_Y��#Ugi��ƈQ�/'��.1��d)��Qʬ�=R!)����>؎�r�k�P�J���a���u�`��l^�W}�ɳ�l1
ZK�_��7�ؼ8:�|�V��%�	��F0L�7��D��7C�� ��"�H9�3����N0O�$q��%_�^0�<���S7Ze�Y`ɓ�8����X�wNs���fi�b>0��\��
���`g��)���'�=��e �L0[g�:���RZC9/ߒ�<�����5��*�6��)��.�Ċ
(��/�ps�WV>^���>��ƾ���ֶ���bkefO���wq�P�m�8ޤ�aݗ>�)tO(։$�xz����:�ࡨ`�B���2-��a�t]n�]���j5��v��y���[��	T�?��lޚ�[?h��3��A�n9��Gz@3�^�m_��y0���+�:I˹)�R��uϗ�ȣ��N+��h��E� UȖ@׿�����ˁ<V�q����:(����
خ��<�j���b������y6 t鏏��$e+�\����k�IWg�^�O��G!�VI�uR��{��HI"D��&���"����F~�t	���[ڿ�)K����ۧ�!w��	����!��πh�'?0C��@��N�C��	���$9�[�ۗ�O<^�J�^T�Ή�n2�+/ta�W{9�ڰ&�ۘ:���u��ǒ��/�eJ�25o�6ק,��vS�����\�FIsVoぶ���ͱLcuw�������R\�wJ�:�K�B5���4|�b2�F�N��-Z&�Em�ӳ�\���pXz��=�O��;�L+����H����?�'�`�'�nCO�7X�'
��SO�X� ixJ�^;nx�������=���\Q^�.���0Ka�/����̢�P�DoÑ�af�=��M�x��t\-٦��� {cj�$���5�x�{�����AZ�2q�ٽ|>�i�o�|"� ��i�ӥ!��"��ۑ��@?�Mu��J#��D�)�G��m��i����c�&��y���������M����#�Z�����:!�mça3�i��n�߉�_~��] }w���8if6�2��y��X��gʈ<����i��7%~��1#5�i.���u>P���m���7�h"6���S	�:Y��N hR�7�)��zAm�$�":������t���,�jn!}�)���蘿���?yv�BQ��3�V��5��J}�\�?�^8�_NҲ�b�3�+��    ��_h7�ŉ���GЛ��SS���o[��h�M�TR��_r�y0uS�����>�?�̈)}��!-5�J}�>�D_�j���P�����B�pߋ5�ƥ��g���`�>�L��@c�o5ݡE�ӾfJ����N{AP���^�6���e��LLQq�K���������/�T4�8��X�}?�'��܆���B�v�`��1���t8�4*���5�Uq�.��������ٗ_
�D/���J=����qU�?��D�(a���C'bq��/��t�`C�Dn�	"㏸�vs��x#<��%�/2OM��C���vu���It/�O�cm�^���ff�daȒY�����'�%�b�z�cQLK-�����<�aI�7m�v��=f��=G�)v��'���)F�t�D�\4;��*%��|��R��ʥ�5�nOw"?��sڊO��!{�\}?�;}�$���Jy���:��<�6:���n�Y`�.b�s��3���3���fG
�|�D�k�>�D���ͱ��Dmwy�]�и�p����r,%���l��k;lOic�Z��TjWK�����L��K�-������5�R.�Q�r���b�~����O��ͮ�4O��=�NmBƨ��>��?��ZD���hf9)%)S�N�=�c�2�M�`�Sr$/V-Ƹ�fs�����th�X�C���g4�ݹPZ��L��{bV�F�L}� LO6=�G�E��X��4L���
)��!l�MI/�(�'�����Ӄ�R�
{Ɯ�m_޵�����ih�X��~�!`��i��pW�x��Q�sD��3�t`���)fotk��ke1��H=:7"NÑ�EK"0�]C��#񥿟J�:�ml~���V$�0>���m ax�ˉ��D@�A�Uj�Fj�`	I�M_�T<o�v|x�K�Z�sP�� �a��Iϲȶc����L6�w����D3�� �|���Ɇ)���b�S���4T��4��2#>!$�9�l���g����m��ߓռ�µ����{���.s���:X��z�l-,ڧ�nL�'��⌗�����_}��PK*�z|Bɹ�5</9	���}��H�~h�x�&�qc^@D��;���ؿ؇��5 &���?�����̳K����s�IJ��B���s���/R+tx؞97��1EK( o�R�b#s��oYB^�/~�\��	=n-��Qԛ��S��T���˼���͋CxPX�U�)�Ҏ���lW#�.H,��ge�c��&0�K9:�z0�N37p"������^AO^{�%�PS���}�e��%p�X��`p�>��O�Mt��u{��]D��R�xK�d��3D��������QQ0N]\Ʃ�<�+O�*��D�����ۈ�N��
��-��c�{Y���\�i��O�ct*��� T�rN����zs���|� �s��{.=�t��E�vd?��6$o
���!Hhx��B��$Q�l���r�_���zΩN�����ޙ�K�I��w� �_@ \C/���9��R�JR��u(��?�H�fw3]Y|���ex�[ �=�7�А�q�E�� �`e�tA�\�!�w�-����4�<o�v�6����ky(b� (�SA\FR�c�z���r&�)����}�@�s��`��;�$.¨��^���3�(�ĕ�{� �.�NoE�i�>ƤJ�f��Y�W��l����m~2���[VYgb j�v= ���04\�ABf�����V{�B�h��gN�<��)���ǧ��a:uU.��O�C��l���Ee�x�E���Epq�t jv��-;��y.���`Y*�����_�)��U�5$]��@�k4؅�*�[*�(��5��x��f�[[p0�ԕ�L��Q�i*�br4N�Ⱥ�S5v�����xe���'f��n~�S���a�_Ú'A�ی�u�W]��@Օ[����q� �z?O��%|��H8�U�^=���g�(�/S���SC@�*"�An�Lɓ�+���<-SiP]�h���ւ��d�5���Nm����C�����1��(��Y�6��#D;N�O=�AQ�n�,yP��-�q����d�7V����P�1�i!!桰�����۸du�L�n���E�\��<䩺� �>��W�\3���S�c�� �}6�O�-��|���|�G���#���KKa���!�Op l�P��������a83���3i����6�8��Z
�*�`Tt���V5��j�A/O܏n���q{+������e�:?�ó�%�U&��m��-�7��]13a� T�'�f�3{;�s�0��ໂqN��x�㱺�f�s�������2��N����ګ����/#�j���g����Y��,��RO�UʫY����$CW��8�/\�)t,����J±�GŃ���6s���SE�ڞ��[1^b�gU&���a��]!��S��A�]�E#u�_M�U�-�^|�F1Obh�K�g"fn�8Ky��W�R��Y>Eq��\g4��3�8��q�:���5O�{;��������������t{�x�y�יޭ(�:oj�����f�qW^?1B����<�yW��Uf�L���B{��-��eE⎊�՚�3��ζ>���I5��<.;T��م��u8Y���9�ݾ4b;��\?�z�� .f�2]�fX#v]u�%I����RPi�Z�Q�p�7|�m�,�ۿw56+,��r}
���Z�84���V����� A�5C��|	�%�?OC��'&r��4��"�=4*Qs`lϾj-a���6\vj�b������B��R�`j1 ���ф��m����u���v��ԀM[�[� '<�#��� Qky9b ���-�§U>�3o-�2i�d�'�1{K�=�2�xW'��W�p���#<>Fa	Έ�Aٌ���b&���+���2�|�S��!$�uL!U��0
{<~	YO{�*K��X�t��E$ c���|��|��Lr�[�r[Y޵��bɾ���P��"���{��W���	�4s:�<�Fz6�{�aQ�h�X�ѫ�ĳ����x�6�z.j;�$|��´���M�ֈcJֲ���kK{#6��O��'�u@�Z/�?�������!����X������s/<����޸Öj~����d=>J��~�3rU������E�|��Y{�N�����bp�t[ �.��T_���b��ǣU�'&Z����5������d?n�핺���#��>
FB�c����)���SS[�ݮ��C��!��6bW���%�;�x���4SҐ ���^n��S˓m��j���$�P!L�-��	W.B�5}�Ø�Q�l�v�-o6�mA��h#�&m��8Nq3�ne�L�yv���>��d'�Ƀdn��J�co]�4{�m�����=�<��͎�q7�9�ȹl]m�--���:e���W5��F��J\���^�O��ռ�'v\N<Oo�kY�Obpz�Ocu�{�\�TI	fc�lg-뢾@�\�-�ߨ��a$�f��C�ES�.,P�Z�#�U���b�ěkҷ��'N�U�#�bE�l+՞b�k+���Q��[�p���h`�f�����>坢�))�f>-�E��&��?L��,��t�g��׶K�z�3�ٚ֝���S��K�q�VH��\-fk6"�H�Z�nN,�]�|�������H7��~����+*��C�8A3m�� PNRQY�;�E��H|T����k�<��܅�"��35t�t,�%XU�5�Xj�+����������˻�_���Ś�[��v�خA<���m����*��(�>���j��xvY�ivN���n]O�9�곇�_�e�� ��OoK�E ����p�jr
�Xyێe�X�T�b{�g�F�:**ݯ�9ZL�n2��F���>�n~�gh��=��qT�{���T��ձ&�R�������r����"D�}}2vl��i��h�x�qʢ��_�֔�̀#�����R�
��=BW^m�����}����y�C� bZ��^�%5�#��{p������QH    �-S̎B���	2�_&�~��~��1�r�H;���0�n�v��w9�r��NС��xIc���'n�,'���QZJ�a��� ����N���l=E�j"f��FK��U��CS!;���4-�X�����|�O`i��ͪ*.�1iJy���QH�W�y�}z�g$J�����g>ݒU�PLNյa�S� ^+��4����
,v<������J�.d�Ӯ#�%�P��ޡ�H��Q���Y�FK׭�ew�)�e�Br���ӽo�f�,m�3�X�0ꖖ/�8�}���:��V�!;:\V����p����7���d���ol�F�;����=��1+�DVӫ�����I�޴�'+:�ZSy�y�	�P�A��݄��
� I�-�����L�Tfg"����:����ק�E%|<���3-Ύ{����ܞ,�V��Oh!uEv�w������+�l����+�;CTK��3�L����P����>�������3 n�����	/+�8��5['}���������R2���0�c�	�Q��P{�k	�]i\��:��	�Q�b;�"c �G��8_�6�	� ���a&��k�:6�%|1�K�p�֝�i��Ɂj]�G����ˊp����t�_��,�� �0G�^S;�kSp�_l�j"�N������������$#�,I���p%�$�Y"�T=*MkOS/����-�)+��d#�n@�l��͵�To��-�wN��݄k�e/|�e�/�O�sw�����܇M-aOT��jW�`�x���⎢
�kS���h=`kaj��{$.���kZUߦ�V.�^*,�/(��vV�@h4���C��d���7�S J:��-�\Y��#�hc�"c�d�g4�8Uf+��|0T�)ԟB�U�����cY�3#�ڣ4�Z�����q��t��>����B�B9����'�1O��3Y��`��X�j(�����p��d�\\�.���ޞ�F�C��gm��γ�#��Xɶ �����Wh�����m+_Z�U��3�+z|��r}n����1���n�9�BҦ�Y�=!Y��f���MG��؋��"	��x��g��1�j=Ws�'������A��$�y����O8�c��K�u|�;7�"xȃ�Wi\۠���0���^'�]��F&rΉ�~r�V�u1Pt�xw�T.�2+*��s���b�N=SR��BKl=��[@%��B�=�0�c���GS��Cp�RF���~{,#HH-��#p���g(X�3[!�{9TK�v�-&/Ul6V�-G!q�B���,o;:yФ҇5JՏ�Vw��k���!��h��Y���q|��F ��	���ô���<b��rk����	�PzX�f���bf�Y#I�X�dr�p#N�X!HC����D�>�an��������b����M��� j]2���cP�4�UXJ.�P�ѷ����uZ#úo��e]�-�i>��v��+�<��ve�|�?`^��� ��D�'��	VI�
�q�:)�G/�� T<�T�k�rz��9�̡L������-_$��=����R��<�������V��c4��iM�dO�8����
<o�͂)��H�m�կ	ʵv~}xq�'�NQQ5EJ����Y"yO�W��b���TԶ�-���n�F�;���޲��>��q�E鼥�'��>0`��������xq�ve�����^��	��U����߷�Ι۳�X��:�Ų�`���A�� c'�o=��Z���)7�wu)�E�⣶��)���7����˸���K1���L<������ ���ҡqa}��O�o�ݦ;�O��=��*�����)ɤ:ѵX(S+C���tfaY�"�ڋ�gj.c���k���.)�=G�>K��Q!J3DI4w��,u�<��x4c��t�O�돞&i����I����������g-+b�>f�O9�݂wJsi��U�΁�NŲ���+�����_�}j̘=����5�'�%�)�P˗��� =�.l6�ѭ[���p��h �s:���Ar^v+A
�la�wJZ��7_�װK��M�M��}Y&lj���4��4xȬӹ�'#��)9��4u8J�6��T����?e�˃�b?�@���O��Z�͗�ֱ{��!�~b�5 hV���܎��ox��V�8��$��=��x�Y���|����,��AҐ�����Ot�X�|�����4'ж�%_!����]N��f����!%��X�IQ��!U~����y�jE+!���G��|����a�kB���A��cf�jS��s�pE��45�r%
S��s;�9���b�D��K�u)���3J��5|WjX������9��}����K�#6Zex��͆�XQTz��L�O��
��C`9e�1�2*z�?��hj BU�UI��gj�S�Ì����f�s.p�½�0�7��?V����-{�H����_��j�f0��HX�k^G����\�����fb6ݭ��c�1�������}�@U]#�d[�v6㴕mW%�창W{�F��]�T�5m�î�a�����\w���
�.�B�H�~�=�-L�@�qaaWoߚ4�Ӳ��t��lu6�h
b=�a��V?'�<PCv��MQ�e�m�c�0hX�JS�#UTkn�O��_.vH�9T �`���M!�rYvבC��~����!��z�b-+���6~G�lM��À�úc�I�u��fy�ch�8��t��^��GGx(�~.�!�W~ß)z�՘i��f��%��yo��m�'�Ж�h��y��w�h��ahO�\F�*>n�Y���mj:fMVt��_��*f��_�*�=��1 ���c	Yq	�gC£����;�H'S)���4�6?v�R!�_��m캦��w��mt�?���p$�#�9�R8BX�r����)l�z��T���3ux*2m�Z����/[ݱ[��%@y>q)�AUj8��i9��5$Ӻwٓ�U��LKi���L0��l�.���<M_C�ӹ? [f[��ZQ��%霧/���|�҇y%p��H�o*�49�����'�姬��D��s��lvhN��D�(ʮ���l�d'�%�y{d����ۙ�}!�t�?��,s���U�	��x�f�����aрp�Y��Z3]�T��=�1h�N�D��"�����X�,
~;�hk�sT=䇸~BI�����v���CS�k$����kH$�ɠ�`�?vs�� P�ɑ�w�R���RJ@Fee�32��	�6�]�8l�K�_�2�2�N�߾Į���gږ�J pUB�_q\"��]v�,�
���"d-�+f�W�Z f|%M��j�����ng�}�ڂ���T�]�.�Z?��pX��&|��Ff��)�R�� 3g�U�]���My:�E=J��*m�z�N��� f)�F�W٪KpoEm����}���QxeS`+��<�0!��A�?l�G�f��c�,����S�:�#w�@����l�Qu��^�T�� u��������G��"r'��Ɲ��g{����?+ K�voo�|�F���ʪb��EjQ���,T��q���U��U��X�̜�"sH7e�[G�Fʘ� Bڕ���]��C�B-	R�
.9l�Kؕx����6��ŋ=�3�8�'lC��t�<	���fn�54,��8�-+G��;�P�J���`��z��fїR݌=��|���5��o�ր��S���8 W/��i��O��a�Dي�Y]Q���K�����,�^�[^�*6S7���`S��!uT�!���0��`�I�p�>�2����f�;��Ս떀���ߣ�3	������m��Z(U(>C:ճ>�G��J2mO"���U�M^���̖w����2<��0C�RW�s�dWH2FB*�P:^�0�T� O���o������~�|���@e�1zz�{^�lz��Ft���O�}�*���k�w�?َ�X��},sh7�#��rU|�3^v&�T��jr+�}�-Ku-����U�Y�PQ
�n��W���	�T��!ʎ���o��    ���y&�@�z��"<�8�UF]��!x�ـ�юb�|XJ��G(�[Hhv���]b�������nG�'>#��C��#���|�[�t�W�]���AE���˳�w߀H����aA���*%L�����~������d+��h������a?���������te�{�s�ѩ�"�r�=��B+!�8���԰�ޯd<���#�D�k��R��T4����=��F���]�n�U�>�n�8��P���<�(��8�"���J֧~Y1�ގ%v{�A����A��*��Y��t��|�O��h~}N�bN֕��o�/6�J<������C�L�~�qΩ�]�%�J�l%G	�I�q{�*����y+����I��D)�ri��lF|~�L���L<�8���b-q���
TM�\�ʮ�S�rգ�̳Y�2�DS��qS�-�݇mze}��g�M+m�O�3>͢=%�֑��:!�&-G��+�:������o	�7Ծ ��W8~L=.. �zF�!�K{C�V���Q��?St�����B8�w@��L�{
���G��A��Y��<Y�};C�~)~��thk#�=�ȻF����0�1͏���t\�U=I��[�m���ݻ�r�si?��py'5��\鎞�7;0�����O����D���O4����ų~'h����4/Q�8m�N�N[G' hk,L��[I����l]b�r�N!'��E��g�V�Q�ā&��ɗ���' ��]^Y�9���*���/��ݪd|O�u����7��T{��v�Ŕ�nL�V�����b<�����65��d���
��*nH6@]4��<`��Es�<Ju=:��O���쪆����N��yC��r���7K�uw<�����}4��*����\ȪM5ҡNg�����u>�.ˈ�.�r.������c��-�8?���}U�6�N�+Q�l< ��;�e���B��<~��tI�tO=8�d=N_��M�׊G���S�:�ٞ҅�m�c�̔5��	��P��-	�TRǩqt���W9�S���9n49�u�_�5l��R�Cwu��]��i��)�t�"�1�sm}d�mb���ոR�!Gpq˦�ϛ��L
Ѷ��p�4��%�0�uڸ�F�������9XѤ�&%����cl��͚�>ƌ:���#��P��D����5�j|�qګ|:���
����Ch��\Au���� N*�{9g4��3�X)֢�sT�&�,O�����u��>l4���=��R=�v��?�`�VN�!{��nHϚ;��{�B��+��a��G�����I���FBŲ��S��I&���ȵ��q���}�F��y;���ςp���.�9x;����.�iFB� C���G8���J�GzQ�b��m�;�h����ڂ�E{�������r�� |g��	|�u�����qziNGv;��>���y�����0r�ז�$��h%Uuf ��%PU�O����q;�dH,���wՌx�OMW���Εaq��Ǝ��� ~�;,$+D���`�jg��F�
�[�¥�y�6q�iZQ�؋KM�{}@���ԁ�W�N�����6��\20WM�9�pxB[/�D/��u_y����T�Ys�l�RCv�6�.�2�Ug�_Ml$]=�k�v�E��[~w클 !I� �Y$�����I�Rè�=La���9�2	�h���/vr(��gU��Y'MV�P�]���{�åt9"Zա�Pn%�I��x`�b8�1F&�c?�-��	�]����L������r�ik����.��� m)��%�g�rϭ-Z|���G_a��0j}$fZ�M���*�/�L����6o>)�f
�����E�\���[�<�a��䒊�҃
\�t�� l�,�a�lX]��*j���8�i���G�ڲ��kg�������}�FЖ�uj]u~�����m@ҎY��K�������`d%�������lz�����?��G����q@��,s��u����gsv�J���@8�k�X���١7J��Ω�=d{�5�u���Ҝ[Q؏�,q(���F�hox̩M��ƴW��|2���)�KVV�U�fe��lV3l�����9�X�|���!�Ɖ˭iFc���3P����4�w��VJ�W�dq���ۻU
xi���L�o�9m�X{��i�=���OxߨwK��:�s+I�wj���S�c�ҧ�Ɇ�n�� N
���*�HmU�Jͨ�\�����ڡ���J]~�_�f��u�����G/�2��!!ܥ�{[aw�R?����*�*��T�ËYhd�2��ְሃ\�N���)�������XE1)=K�D,���%�`�)�XN�v��y��f���%sn^��V��ո�t�8�i�cI\?u%V�����Ն2֘�ea�ºPl�]�r>�G�5�:�i�d�["��r1��/����Q9��U�f��՝h�+�a	j�S(J�T�\p��>?S6�(��~B��&+��D��Z}�t/k�U��T�̺B)������T�[����GW��M��r��+��x�����|��V�6�
�Oۛ
��Q�wևM�.���������d��nǉ�Nl�䄚٫y~���e���T��-*T,ݯ�s��ZN��[��$�('8PX.Z$_�GN�>N�ŉX6)�u�><N�G?{�wU]�Ȉv�$^��������	��6��쥪�)���^��yMˏ��d)�k�G�	�8�t���u��z�����uk�d�����x#�yX�z�M�5������}���e0
 �D J~�B$f;	��"�� ���ێ��Zg����1���ReT!{�S[�o&��tI�P�z�!L �Q�0�S'!�N6S<Z�4�~���el \�p/����\e3Q�u���nr�}�Q&�P̪(��j�Ȯ��*]і���#w���P=�{t&x�㲾��x�;Y岤�AL@�[��;j(v:�aeK���{�O�Q�6sC̡�=�CR��v҆.���y+A�4��� �V ��7`�g��AܮG%δ�O���9UE��a32�1u�֍�j�s�Ke"K�ܛ���ie�SW�[~p���d��!g�PzI�$He�`%��O���������e�B���,���͍��|=�/�k�f�iBf��Nns�t��z>��6��z{�t���|jt��+�	� ƻ�(OR��Y��N9�B�_b�Vî��8O�!.�CC1	�'k�{G��t-;�C��BV��i�H�XM��P���CM%G�0��]�)�e�O���P���2�֜-b��J̼�[�>�`�/��L��puצ�σ@yl�iٟ�y�ԕ����M.���o���:�|eo@�} ��?����D#�)��̎��G;����T��7;�y\�^���E��*I�v�l8�mS��:F�V�Nb	�*�n��gP���c�,v��|�p����~�\۟�IH���}�	,�{ء����eSƯ�G�[>IekD��}wӡh��S��s�@t��A+��wUb�2Ѫ�(/{3Rf3@~z�D���d߭��+�����E%8t��}�c���{aL��ʔWo�n��i#xOI�$�ڋ²g5C[���@VmW�ų%9����~[��4�7�:W]�/=���G�ہ��E��&Hx���>�5�8�/���cҩ��Z]�[��sJ!S>c���� ���J�m��P�z����Y�3���s��Φ������9��YV�EHo4FC�g����� X���~k���%x��y>�lb�Ǩ�QO�����`�,������������w�߮���aj�*ˮ,��v�G���
6J�ڧX���ԭ��G=�j��}��Z8`;��8��t�h���Mu��و���*��C;^lq�m�3?�T����!/���/}�30���<ü�A2���(�O��bH��7GOeqñ 5X���ަ5*��q�x���ۗ��<�<�j/z�Ǥ�F�m�;�ဢ�����_�׮`0ķi�gM�=����g�VH�yX��?��u6l�θw�X�����ЗN���    _[?P{�uǁ����� N�O;����ڻ^\���ו�C���2�؅(���[������4����
ޗ_�cL�y���+�t+��T挼�K�7
h�j�l7���s:�K�ĳ��eq��o�94�K�f�j��쐡w�Z1�6�� ��U<-�_�Y����;�g���8��c,��R���o6*J����u�t^^N�4��%ĳR� �D�`4��գ� �����5SY.���fg%Yy�pD�?d�Q�N�	�$���j��(t�z��r�T�� ��o�������/��渙.JG�	����ƠС溄�?�	 ����	`y�d׵r:]M�p@/ T��������6ld�*'����]Md�)�V���=�8��?�}X4�D�	�D�֯T�Kȣfw��FD��L����,�Q�0���,��Ǐ���w��8�C�mW�H^θ�T{��٠���Ϳ`��f�8{�u��}�m�J -����|����l�T��!#E����8���dg�!�ʍow�y#�s�8�d���x�\��Y3Ny���Q
Y�ʫq$���`����i�s�E�����4g�& D��8�F^R�3���C\�8\�d۪�Vܳ���F��U��Ut9����XnC�N���T��Y�W8��C�n@��R���
�Rud��|���GM��C���ѱ�S���sZ%E�Cu�>v�&k�n���ͫ�Tv��p	r�\Ӡ��jQ]5ƃd�{S"L�c�4F����?{�@��%���c|�	yւ�|ameT����dZu~�V�m�!0�S�/D��s��5�s`��1�������d�����))^[Rm�	%��xR�z�u�O�+�>I�qb�h�Nu1����P
&���ϔ�d_!ŵjHkO,>�� a\/Y�'>�m�Vv�ʆ���O�HHn	��D}(g	�A[�l��2X���#w���8�'Qr�5|t�ގ�7 f���1�٤�V��Uͩ��d�������l�/�Ld�Jh����cj��Q�լ�ض�4��B���JRZ=�V-<�?%����V G7�C�\ w
A
M�?V�(�"N��L?�+.s�-8l�Z�ŗd/��e�\�~0W��_1���zK2�)��P�5N+
����*�Ɇ@��pu3�K�#t�L�x:�"����e�d4d�M�>��*n�Z��|�l�Ǜ���S���c���TĊ�xa&b�T߳s�F	^�з[����n����?�Nt�u��ƌ���x�PY(k߶J5/�N���gt���Cr���d�{NK���6Ֆ�dD(�{�/��at�Ç�y�vޖ��u��;���:J=D�6�M��U�7��w(�1���H�Iz��|ċ�nv�Gky:�r��p���V�� M��o@���i��9|e�%��J�����C�z�"N���Cق�_�u�e*�rZ~������L�T⡾�T�=�ӕ�߷�%����dW�����G<]| ��'pO����2�R��4Eq�̟:�P�H�=����Êz�S�s��,��z����jL��*�"]>���eeM�	{R[��*���G�l� �qʈ��кs�U�xT/�	{~�Q��P1|���*3lom�ȹ[M{�`�����ʚ_YӜ^��s@"���0@����0�j	�����wv��8 `N�;f�Khƭ����)���԰�U�?H�3� �S�X�
��3�k�I	�;��&b����ќȳ��y�lcLK�����i#��蕋HiXP��P�����+�%>:3絏D6�Z�q�T�Z�@�jB��k�:ȯq[4���U�&u��l��ܲ2)$M ��ʤuM��F,;EO�#�������J���T��k�QvS��V�-GY��-I�3�m��Q�IJ}��Ë��j�NSX��<�ï�i�>�kCJI���T
�z�"���/z-����b:���3r�
e�Q��5�V�1df����UX������,{���V��\Vro���=�l#�K�E��Bbxf�r�з�w��<�7�T %�z�!p
��O��SGR2�T�b]��ʊR����ձ~��7�R��^�6��M���¤6�u�W%���w�oOu���ԙ��Py'��j ��S�cg95-_F����c���"��V�	�}�q�νV�Ja̷��>괐~;Rf�3}D-�������Y�V�<�l�eybKO�ptz�ԧ����%�L�9+Y�_#ZU���Q���ކ%߃���R	gꀝ��z5��wd�
��Z�3k/d�3���>S�8����o���υ>jnX��o%<����!��w �a���{v�C����^��m/+�xIֳr����U)e�p����@ӬS����v��t:�S�3�k8l��b^��S_�u'0�Y="cehku�dJ���5�n��:�� e^�v�T��6d������n�͝Ln�֦�%z��0>qwTg�<Mw�H�x?*����Wtl�F���Et�ĥ��ʟ{��&�3�<g��yy��|�;~�Q� p+��$��o�7��9���ҹ���z��$�l�vU,�8F�Σ.=�bH������=xU�W���+LSَ�����fN�����RŚp�o�¤2
X��
���qJ]g�ӑX�|��ʞε��*�o��ڕ�9o�m�9��!���xVI�S�Ʀ
Of^�X�r�>툛��5{��S#D��.�ak>������<�~�Y>���k�4K��[�'���z�V�V�z��Z�H?����w\Q��r�+E����V�US[�'�c'ϩ���u}��,��� ��3{bh}��Йlf��%q��'f�a5���`�+�9uE�۱+��'p����ClV����
X�Y�7B��KH���%z���Z�{����+��.�f��\�9��K9C�+俣�!���b���B6���T���Dʛ�h�|L�{\
o^s�\�O�@`�"zu;����~�(D��,Ƚ�\��fG�5���1f���",|�h4�'=F���z,HUY����t��|Ĵ���k+Nx�e�3	�K�i�f6\*�
��L�T�Q���=��$}�I��W��с%�n�P��O����oQ���+ܩ��_�C�����c١��=���tX}�DY��S�s;��2��cx�5qt`��źv�٢��Nljku+u�������P=+�����u�ؔ@Ϗb����v´��uiVw�%x�pz@i���:ps����1�f;.  V*4�B�Y���Z�O�W�,PV�8��I����+t���R���yý�\�|p��@d��9�py�c��<������_@棎�H��q�6U����*�jqz�t{0�~� ��\�Y>�m�y��{ig�c�.�J�x�~:�=��y�Qs���BЙf˧�Ӄ���!mL�* \�3���<�����ʜ
R �aǶn��q>�h"m�uqC�US�o;D�������߽fQ֔��$�;�w5����/�-
�Va��_y֐ժv��c�s��HJ@���{-%��V`��M.��:����\���֟������8&�e�����#5㈟H�&,��&�Ҫ�K.&7zX����2��t������9U�+
1+d8���yM���d�����=���7��k	j"�VGZQ��Sc3�X��sU��Z֚�x�����zǱ~�c��w�� ;�Y�,`�w)ʷ�̫|���w:>�;�U����tƨ<j^�+WY�[+��PN�١�&"�B�u��'�g���H��/N�q_3�ߚ(��L�_�җZi�dKq�cS�
�ڴN��w �u<�_!���|��l ��`q�����ؒe� �};��@�ѧXߣB��;zD�z������Ev�6�?�;\�}�D6S�gؽ�[U�)v<ޞe��c��ȩ�=�2�t%3��R�7:�k;�e4O���e�HG����Z�4�b$'h-��3���8�d��E��a���%:��	ބO��U	��g��?6r8i�_�_Ⱥ�)��J�>DB�sNr�BL������i���'s6�����3��h    ��H�uo��R���/=<�	]l�E�TmǸ�$�t�f�b�<�U���[ �<�x�R�'a���,HR����v��A}��UA��~�rT���] ��7I�5w=�(���b���@yO�P�hq��Ɛd��Q-���a�YOã�Ӥ�J]z÷pՒ�l�f�ٮ�s�%%{�<�j���K��/UĻ��yt�xh������ԟ�_�����0���F���E�/���_��_��I�S�t߷�YM�������[�{C��[�=o����-������s��K�/������~����o����-���������_z�}�/=������K�/�ң�[���������_z�}���������K�/���K��o��G߷�ң�[����������K�/���K��o��G߷�ң�[����{�/=������K�/�ң�[���������_z�}�/=������K�/�ң�[�����*=z�E�_����~���z��Fi�,�U�@�Y$�W嬭��������w�����"��ů��ʇ�ɿ}M���v�؟�L\�Q��E�ٟ_������#~����I������?�������������~���ox��w����_���w>��W�ѿ��������ϟd}1�ѱ��v����tϵ�u�k�O���������_����������X�ֱ������WA	��Nd6>|��/�q���ŧ���ԣ���f'Χ%M�����7�Ο�\߮�����5��������Ɯ^#WZ�3��o�������6=��7MOP�rOɷ�
k����U¸���Z����{*�}��r���~r���r�g#����*�ٺ~m�n����k�~���	�s���yT��s�O�"�Qv��y-~�}_-�o�9�L��N�^��ߩ>�}����s���ڬG=7ߦ?c�Bx߹j4�|���
�J��WYs_������X�Ǐ��{>�����7P����8'�ǅ2���՛W[ӳ���sC.͇o�h�)���ݏ�k$�'kk��̶7o|��n�ҐdO���U�D��n���ۺw�=��9ouݏ	 b!Mo-�Y����b<Y=�>|��y�FU�1�����uܬ_��|�>~��o��]X�^7N7NχQG?ƺ�;?���q�|����=ov�s�mV��`�a|}�ܸ�9a.>#��_�m�uu��O��O'�6^ކ7b���}����_�򈍘���={�x����q�'χ���eS��w]���<|��ӽ_"�E�8�9�w7�2�<K�d	=�O��zɳ�sj95RI��p�F��{�4�N�ޏ��l�жǵ�5W���:8����p>ٲ��<���9��6�I��9���4�g_,�u����M3��-.kh�_[�G��Z5��i��W#a�	.܎�Z���bl6�6�m�r��e'��(�ʨ��3�a/����i�|��w)��gֲ�_mzI\����o���\����>޷<l��8��n���qϷ�Gͣ�w%�t�=����P߳<�v�˵!<X�D�B�r��L,77Ug7 K�D�ˀ�O_ug6>!��L��C�A�%��v�g;�΅]<���!�p��*�Ezy���H�ܳz�B�����M3;��;��gf?p�����\/_�pp�4h���/�-`�{�~t�YK��B�&P|�v�[����i���I:S;yZ�(���N4=x�#2���mħ�y��O����E˷tOm�:W/fЯ�&?�M�'��K����� ��T�r<��&{O�q?����uqW���!7;���xXg�4w��6��%����Ok�ˎ�Y�g��yr-զ����|2�ɛ��u��<4ɶ��O}_Wj'RpK��@�A�~��ǂ�����;_������=��x�K�f��:���3u�$�}�7�s ?��?����[���h�NJ�'@�_�f���]=��ʼߖaYq�Mh����X�j6�n�;�ˌ��z�G	Uu���$�U��i���d줩ߋ�{&�q����N��'���`�u0
/�+F�n����NP��l��y�l���v�<�W�^�����x�]S%�x������䟡�}�쪇%��Kn�� �.mj�ä �p_dS�Ka��nӀ�t����bќ��S�O=���d���y�X���~��ȗ�m��؎d������x��zX�Z�M=n�;�`q�3��Hַ�M��;�k�SM���qO=��U�&�Xy(��B��2�H��j�n�js%�3�M9/Mw75������C�N�$�9�P�{cm���d���J^)|�AD7p�3/h���:I��+&v9��m�Wr��/o]G�a����}�=�=X�azǳ��
H��?�ɷ|;7��~$<�2�z$`��m�?��Ҭ�dn��+�;��sB�U���G;_��v��S]�lfp�fEW����Ԁ���\�q%@���[3F�@��@�����j%��G#8��I�<6PCd'� �!j>�у]�< "�bu^�9��lm�x��L�=�~��E�$�ݩ�ų�/!i\$�Lpe �X-ҟ��㼏��]^����J\�@��h;�� ����k�sdWW������F�%"�W�o�W{�T�"j����DD�Ͼ�~��f�_��u��𓭒����S叀;0���LZ��$�q�b�@@-�%��a�'jZ��ssy�.O�J�gb/��iM���������G��	���C�?�2��|�1�``B����;��:F����H$V2�i���.?<��@���V8r����^��;�-���D"RB��X��ڈ縖Ad?~b�̄��y���KO����v�f���w�w3߱�Y`V~�չ����p r�/��+�:t�ۻܐ>�-w�f-��X�`�z�9��͹4z5X5�.�6�l�%���f߹F��)�����i�b�U����`�[�a���O(Z&� Y7'@��V�9���w�M_�E&�s-.B�U߸�� ������w�
��N�_�L��� �D< 7��!Kh�K��|�����U
�a�lg"�Ξ�Ğ�h����ס���LB�`~�X��Pm�I�<�{��;�"p^���-q��}kM�~'�n�v����}�P4gO�\.�l��mb+Γ����t�[R,����D�'�O=Ā U���A�=?DƨL
{2��QD�����5�����!bM�a�\`�A��퀫�;0�/����_<��gcK�m������(\D�>���5��Ec�ɷ��N��"ú�ԭ���9f
�,)��]�6��	��ӭ[����i������>�'��PЫ��P�ǈEXCZ�#N��.ɺa���~��b)C�3��^��O��A<�k<����5�7If�7��& �����s� +T��O�@9n�}A�h�������*@��2��偠z�1�D>�<=����J��%�������UܠS��|3��$.���%�)�*�[���z��z��*?����:�l�^�A��I�>�<๻�f-%iU=6�RdMdץ�#�n˖��T�0�� ts��dW��{�}�#�h����z���k�����{�s!����j76II-�X-�o�5�jh�D,��j�|�y�ɍҨ8I�"U� e2?aK�k]K��j��1n�	�x\&Aof?r���,�?+�\V5W@y7���{F���T�K� ��W��c	��Κ�d�����]V�|]�F�D�U�?�z�nn��
͠��'A��(��P�;�EvD���hz6H��~ӤƔlֲ�h��f�=vM�^��É���"J%)�xSBM���z��s�8�c(�_�"y�g��w���|p�Y��@�����N�F����4}O��|E/���?��UVÞ�(���"��%	�2��lQ�ݴ�R��ZnV��hU�%�p�DB�N[M4qP�:Z"���sغZ�o��qqG����%ހ�`���j�|�-f�J��O���3���N+{!]�`���G�= �p��؏���b9���!���P2^@d�V���e0h0n��hi    ��*@�ye}U�eh�u��lLo�ewI�x�{k ?���(�>�$���	�/�Cp[�9߬,�l,�y,�0˞P�ڬ~-k7խ����?��%�iX���-y��c$����Q��ʇYd�̓��52����e@%�5���^dv_�Quվ`Ր�m�K�#����0ы�f%�&�vp]�����A�v��:����iQ����u��.z-��N�;�3�1/�����)��jfq[��7H������;GU&Y} WCLAV@�� �L����}��d��ϻ����Y�@a]A����{�:������"m�,�<�Z����� b="x�B|!礽]�X�����)��gv��m�+�~m�E����S�3�V�2#;�-��X��F���"�sWȼo/����F�+A8�j:�ju���B|O7< �d'��\ ��̏ PN�!j����S~�7,�+W��0�HdG�y�/@�<�ov�Y.�u��ރ��
�Z*C�L���o�!�S��r)�����g:t��&k6>H�ԫ�S�\�"͵e��v�Q�΅rgH�d���|�ˮ+h�4�لy�[cN�h�=�yA-�K���Ə��B
����AE������5q� ?�%�!49q2�u��o�p��o  v�^cۏd�>K`�d��o	����y �'�\����W��(2w=����Cp�[�7D[X(ܘ5�^)��"����p��4�q�A��Yb�+xyHa����Q#JZcnݭ��VZ6p�7��r"�N����1�� y�:���4lV�;�c���L�'H@�%�Hٌ��I��^����xO3o�,��m<u����e��r�R������L֑��lz$,ȭl���֋��Z�nX8Ao�SyN�s	G�ƴ��5=*"�����X	��?��	�<�̭�&����]��*y �+ə��.�o��i�}���:�cz�	�<������6^������d1p8�r7N�E�[�`"�Y br6vFN>����$z�s�.��.�m�bD�~X��L朹ټ�l��%��L������kY�Ms��9����`w����$`������$��Ⱦ0څ�#���*K��	���a�<�B)�,�k��^���Ժ=d|��~ؓD�GΚ�����P8�
,P�/(�,`'���g�X,g��1=�L�FޘE�=�B������!�p��c�#����0 D�W��o2�M�Y�5�x���y�r���L�T�%q: �B����|y�+P�3�WA��{y"�+�����Z����xGhfx���8+�r�����x3��q篫�0��f\`uB4X����!�6(/��f���Q��FZȽ��Hԧ)�OggC_ t���/�x�F$��^��xB����!�,"!�����q*�ư�HЂ�t��E�-�貿�DN CMI*q��nɫ���~<ޘM��NZ@J�	 8��R.����t��\�l<���i�uH�y-�=ݪ��ȇN?�8��C�����y@��0����dK�-�>�(�5�)[�(��1?��`龑��=��!�va8ЏkD+R�s9ds�Rx<�
Txp�>X�P4 ���9�ܫ�D�����,����?����v$F�ye,"\K	�5��µ������>�]�+P���7A�y`�=���e5���e
�{�|6�7gW_�;��,C+�,��5�6.�۾g��E��	4Tx%�9�H��5��� 	�s���`{�e�����_��(��6p�h����F��	6q�(x�dSd^ �<��9ݞ�y���@�uى_��j�'�́���˼@UBG��6H3	�Ep?�Z	��;U��\�%½��Aԅ{E��f�Q=����^'����zE�q�ǥ	@g��e�eX��>��xn��
Ͱ-cZ�a��γX�?��i�����DGp재�u$�xYfᓌ-�B�<�|y��X���5��%���#����̦: 퐘�Ëؚ�&���C�xf{y�;���Lw\��	 �.�9k�k[�6<����TD��`{ˋ�{�+�����u����I�^	���5����i�W<3l��,��ԑ����`��`�ə�qU"��bO��ك�b^��h�'+œ/�Ɔ�l>Уs64q�����0�}�������h��N;�@@_���y2tt��˙;  �^����a�ܞg-��	�ؠI�(�X"e]�,���n�Y|B�y�l"�ۣV�- �w�g�y�������t��*YYr� �L��8�L��� �_�r�A��#�W:@��~�[�X�>�o�6���<��g�j��5�,���� F��HIi���1J�7V��rV����O8�w�`��T��Z���3*��([�u����syn�t@��汥�d"k1w"a��&A8���F�S+xZ<l0���q}�*�L�m_��ߞ��+��MO�&;�� �$/�ی���}�	"]�k��_ 3�[�K3k�
���D0ؐ'�?�(����d�^"�s{��m=Xlxw�V� �I���,�������'�H�p�lݔpP����
%���O@O�%� Vk��������
���}|����Ỉªy�s�O�V�`�7��ާ�-�T��g�\�cV�A0��@����� I���>J:�|�Y�8d�v� ��l�8�Q��9%7��/<��}�GD��^S�DI��I��j��f �u�y�r��{�,P��C~Ф�s�3��� 	��Y�%R0U�O�c�_mA�I|�d���������uZ����G)�d#�լx��x�&@��J�C��5#8���D�l �}Y�рL����W�Jخ � �;{�p^���Qv�U�� Y�˄�v����wz'�z�'�o�?��N6b�i4���5x/�����
A����e��X�Gm ��daY9�'&�������@�<�'h{��%K~H�ao��ɰ�ˇK%D�^�= ��l��ȴX/b�d� �ȳ���:,�un�ߓ�$���[o9O[�n���K�ld˃���ٖ�ƶ��Ù<�e�OQ�sH�M�鎼�-J�e[G�~U�"	�e��uXK��h�`ý�[[�U |J���O�̏hxIn`�:���H�@0����Eg3��̠E��큭[���|-#=��;���4�}��%kH��]�оãL� ޸� �Iy�k����Xa�
"Ѵ�uE�����#9�G+<	ۺ,�����rIK$��A/�a��8}S���t�Ǟl��=nl��Ɨ}� �鉱e�>!Md�l)DK���ά���Q�6��d�q��-��d�i4��-5~�K��C�,B�%�J6���;�}/B
���EV�������`�������$�v��Qv=X�Ʊ�� �
��"85�&ڸ��?�l�`y�%����sv�2���$,�%pp9����q\�<��fS��x�X��F{����+��6K��Z�����E�F�����ۈ��n��@�=P����$�!A��r���NK���I,(>*�K�G�y;�+���A�}���D`���u�����m�$+�������Y��na@�W����K���,�e+��y��#�&�/D�/�.��CB�8�˖��6��	�+��E�yv�e\|iRD��رz<�:n+�q��-.��X�6k��V�&�ˣ[��3�@g܎I�#rNO
Ϛ�3�}_2�-����:�y=��y��+ӝ�ް{,^~XY ���'��s��d��&�<��!<��:����wB���݆XV�5mۄ���${V�د��a��E!����[lO7��ة��"M4���9`+	����~��a��6= ��J��M0�����l�U"�r�6�Oj����B�[<�� ;��L|�E؟<(��S���Ƴ��t�s��$Ę\ӿY��~�Ka%8�c�g��}G&� ��1I�;N�rg�@C�8 }<���:�w?��5rYg3��1�E���M��b��`�|�,������    ��c� V�L�2�|#5d��g^=p��S\,'��eh׌m��eE�[�7�l�,����	���=�
@X �\��ͺ1��%�<B�[ޖ����Ν�c�t�	Q��
Ͻl�M����b�hãxݹ'ݒ��IO�聡[�a5=��<
q�r��t󠤏8��$8G�ޠ~X��,r��ǱX����a3��gD_�Y68�6~CY�%P.�V�<�O��i���!��?=�Ub��tR)�e0"ཱྀ�/K��3�"{v�����|{@�Z�@�!z���r��C����6X���v���fp�dϱ��Z�<C`�<_|����Hك[��sP�Q�䎱gE���bc<�~��滖G��S�΂�U;�����u�&�.H.4�C�~v������f%��Hb�L��[�~oOE�� �ቋU=��UR7C��D����:�$�y$��7h6�ד�Ü;XJ��� sn ���j3������\ur�[�O�޶
uhs쉾ـ��:,K��iy����dJ
\�p�C�.�;7!�ǚ'�[ 0� rP~O:��y�ީxpB���x޴r2~��p�Z6�������i�M9@~�	 ��#w{=�y�)��qhg��fۆ=m�!�'���� h�CO�(�(����$�s�'�j#+�V2�o���Wn��(r�;r{2�~�U[Y�a�?� ��I�j�N�B�qIo6�:����ӝ�-VRO��O�N�a��n��9�Lإ9r�+��d^�(� [o�Ն4�!��ʹ$4P�Ƴ�J�������#�VJT��
��Oǡ������x��^��	�%~mA4�zh$��C�뵝s�4eS�p�#�<��$�B�e_�d���E"{�q<���W@ _�d��� 6|�V��>��2�';��X�� �l9E��_��Xo�f��M��<�Gڋd�^�j�?��QbT��\n�t�F��yܡ�C�0@	Y�Ù8�'����G9���剃���N"a���$�N�n �S��V}��
������ؠ�t��y��l���V�=�6�z	��vZ�����Ȇ�m�g
��g����N%F��&�O{���w'g6�Co�,{x��{V��M�y�ں��?���}�x�9�a�; }lE˻�Ҿ�ZG;��%`U��y�Ǻ�Up0�1-o�����.8���{��W Z�.�2��}׺��'	޽rӟ�k�A���ZfqK��e8H�$-;n9˺H����p�L��.����:y�{�SF���_�w�["�����dI���g�ϰ���ڟ�)�}�tYP�l;f5�r���xfm�
��G�;����B�@ Z��@��z�T�q��`���,�]��U۬���I�ͼq���N�-�����Ey0D�Z�dk�N@�NA����c�<ۄ�m���m�͎�F<���q�,���"�����1xn�KfQ�&�$��V�/Ǿ^Ƚ%�2�@�(��Ӱ&�,h���@���w�+��>-��ވ����%ϱK��v2|;��b��\��T����.	��i���l'͹������6<�#ó�Z���0|@!,�	)�~�c+�4�T!�;-�öZ
ٓ���\�'{�:-D��9��F�=�9P4H朗/"�����:�'|$A�&-�!Y9<(` �z9�1Λ= ���d�mm�$n�rȭM�9�d�c,+�o}�
�=m�/�����Ef͔oRN���>0����$����<I\<�*���~�A62Z�뒛�W↳�j`B����o),�IdO1���s�ed)�N?��4�;8��� X��9�(<�Y,�O�YF�6n�SM���n�v8�E0��븝H��D�JNr���rs�7�v���SNV"��
�z�G�� hph�_�>Lr_��s?��nC�d9LQ�7�
��>�q*`7�l����b�Н�ጺ%�쑾G�V�Sa���~ZK'�B�R�0sC�$,V5��xqw��	�Y�bЁp=�\���V �K�ej�,�� f�u3�
����&ך�H�ck1F��Bz�b�3�����>�W������޽UdfdfD�F
��,�%��+}��p�ky�������N;��s�"�A��{K0��Fi�q�����'�|u8hϊFl�'^�R���G����3�!�Iw6��㬫;#��Yng�K�Ֆ���t�q)*I��&s�9���v����Y��«}g�Q����@�]2*�Z����>����k�� ��$Y�7�G�D;��l9�,5ܰ�cx%�$����ͩ��7���Ys��{�rr$��J��ަ�؁W�������u�ϕhEl*A�p=��tQ��2�<�J��~�x	c_F@zy���	�^xr6,@�r�<�2��g����&�2��|Q<Ʀ��W����$oKJ���+�)��HF;S�WP�6���V$���iI��/H7�ɪ��o�]R�(5���+�WQ�V ���|" �w�ʐm	��D�ܔ�@R|�d���<<8�?��J�2 �	9�\wp��~�'�1��|Qi��ϩk�bލ��Ȕ6��µx�Q1E��H���A���pz���&��Ñ��=>v#�����ϩx�ϑ �t����f�K���:��R��� ������"AnԵ�#��Y�^�LR����C�x'i
\@z��Pm��s��/YnW���Hg+
�p�N>ɞ*�呜d�%�d����͹�6K���{��Tչ�F������-{��6���L�	|=;��iF�#!,Ӹ��9����F��̪�a����B���*��"�U5�V	���9X�(E�H������v���cw�+g���%$f)�O^�B4�SM&B
����\�r��7��@���\�7*0��P���C��(�R�Ο,%@�`4"@s�?T�"N���ǟDP_V�_�!4�ޥ�~-�$R���0�l��T`��W�D¢Mj?^N��|^�������ЬPd�?��t�F:m��%D���u"t�e�h^�����P��K�;N�ⴶL�k�� -����O"�s��k�_�-�����l�-v��l+9>��/���)K������Gᄚ�Ay��$W{�/9�����_����QԯU3%'�̷�T��LMiꪚ���������:��\Q}����&׎�jVAR_����B"�(��9�5;d+���oy��F������6{�c�)"�<)d)�!�T��L�$�^�Zu���T�֡��"<ɏ@���rƣ�1�7HIb���Nm@�7t�(�	;��r>�$�d�����i#�*�%�Q+��LR�Tn�Π&dP��^��|=<��%t���v�O>�B�ś�<com�J�דCq/��(Ε��-�)��M}�,h*xP]�Z���L�-*�q�I�uhqbs�,H��yP�Fv�}(k?&$G�2���&5<TU��ϑI�!/��|J�ro78�J�w*�u�ρg�EguI��`��1�)��+D�t�x��ґhw@���ONZ�?*��Բ?��6޸o5�u������&�)���@�FV���MޥO�����9c��Ps5��J�����H�GS�f��eyx����!��-��k)�p*��YOF[���V3y�|Mp���`�����@b��
��%���%�hiw�JT̈́�kD���C.j�tέ4P� ؀͙�u:˜(Nħ�"�v?�|�Y���^��K�A3;sN���ˋ;���S�u��_��d��-�_�j������l���P�>Z7��8 :ԋ@0���j# >� Bj�:��	�umE�c͑8����U�J���ϊ@�y�^�H���w ������t�b'��8�� �9"���4N)Vd=��H"|���@|}�K�q����2v��ΰ:E �c{I"rK��J./p� �l,Y.R�N��l���]���yՙV˟�,Dtu��e�S�m�Է�Q��~3	���lke�nA�Iy���[S?�o�Z��)�r��%E�R>j�8��%I�
t�!o��
�e��s&�    {��p
(�i`�E��j3�]S4p��K����{wO�w�� {��^6N~5��u�~�r��*i�?�LQ��������K��/%cZD98/��cɚ��{�Ѧ���9I���N@�r��?H	����	O Q�opy�ɑ��r�9� oih<%�G�_��p���A	�����Yg!�ɹ�`=�vS�t{ɺ�k`��E��!����Đ��ޥ2j�@��׃iP}�p��o
z]T�`Q?$�{��-���V+�FBS|�øB�����&���u2������<Z"oD�p8d���Ͼ��%��g�ڪINwj܎ܹ0F|"2�ɩj�:�~8_�*���3��Е��F��>@���Q�>�i�+7��𸍈���}�^���T���8Fz�P�|e�O�3�y#��O7"J��#R�M	<P�(o��"w�� �ԚAÄafs����y�!L�*/*Λ\.@f���O5^|�!�}�r����%]�4��5��<�XuU�g�%	�	Q�5	4�͸p�J�5}�tz�9�E֮����
�ޓT&bw�f{���WCo�"�݃ �YC�\l�_S���9,R[����8Qbf/+�.�6K�ؼ�q���e���N�)�B��~Rw���f�"s�^�ed�3��hs���5��R���.��C_
�m���r���AnY_Pœj$x�Q$�AnN�H=S�Р�}�n΁m0�t�!��U��K����D�L'�;��q�I�ݗ��H��(����I8�6��ƸmQ7�.���r�����4)�P� q�X�$Jq��J��&�=�7�UP�`�o"!`NKʺ����1<�����\���"!��I�x���$/��L��{�8T�����2�Z�I_1Y�  ׎0��zDo���H����q*}����̏��^Q /�(T<-||
 �\f+�弓�fkY�dOiz �·�����%?�t�%]�[@`���y	��9��n�J��UK��PWűԀq�����)��h��W�|~z�������F����g#1Gj�y����qp��0T7�ݠhR�-_�h�ǹ�1���-�b�J9���Fo�S����SV���Մ�d��V>k���1xe�n�$��y�XN�4�5=�)���ǎ�Pt2�e�����1vػUu��ϔS��al)�KZu_T�C7RP�F]mp��ɴRR�r����H�p�7B��R�W�wI	VRXV��j�h�������/!�v>��bL��a�\�����O�A�&7����#������������8�pֱ�T�u3�:�}%�Fd�d�y���l�杗cu@�s��(~�"ޙ�)����0���<�%��SV���i;���f��W��2�9��*��d���]���i�{�_����7Ǔ;$'W�H�Koy����!:���2c����g��'�$����o{�<�j@D)��Q&������t�ȵ�}%�}|~[�A/�g^!Zs�*QooF.������g��F3Nu֮SQ?[x/� �����(s4�H�5��=�T�C^�wt̏2[��g���̀��"^�'��Q�{z�!���͊���y�����q��AO��Z���?���#��4?~K%�k6�ۻI��{ߢ�W+e��Ҥڽ=�l�t�R�^D{�z}�!_^H��\$[���c^QY7Q�~����mal]]��HdiB�Y��Զ�R]m�㹸-��1!Ŧ]��K�m}���V��U�y�����ק���=ա|7R��<�oS�e�s,%�� !�jF���e.�|�JRm�٧�#q/�i;�)���k!�7^�����F8~��`S�����Ӎ�U%C�BDK��T����=u"_��c9��q0��f!"�����KP�$f �&�:�PTc��ןA�����\���"rF�q�w��6��i�S(Kn�� �?ѷ@
H�Y2*�Rn^ɶ����"�J]��|�4ϝ��$�G%6�]�f��޺{�2.C㳐��"�xȷ�I��G�Z��\JM�$%�9}A�,�:��E�%�0>_L�墢����K&�P*�����F�.��bD'�+e�`�mce���jI�(��
�[�;�[a�x���T0po#ˆ�G��,�M��Ƶ�r l J�'�^����4ٻ�TشEH\k�������d,��8vr�	E�>嘩�Z�gg�� à�du��S���|x/A�	"�L�/>4i�n���d�l�;h�̓�Rn�Q|i�w��:�2%�ğ�h!#f���.��D  �8���m� �x{!W��9�iqR�(�D���%��!x����K�q�t�d5��|�t�k祻1�ܸB�x�xE�Π�:�o��#��m�&�cĠ>��t�r��:S��7�K�1ϧ��R_�R۴�)�V�.��\��n��Qa؜ڊ��m=͔�,R�X��� ��T��ڡ��&�|�:�jɖ�Lڈ�+b��&�"�C��7�r��M����\�MY�����RI�C>��ԊC��Lؤǈ=�[ҽ2����%��w�z���k����'�O]��QQ��t�����b �\�p���Kbˌ�2��S�jxY���f��b���T�3�W������њ�������d�	�F0�P�,�Rէ���n�ĩϧ Dӡ/�A��)��GR�Y��B�rb揖I[yv|]O7 �8��5��c��J:��,�� 8�<���:�tp �_)檜�|�y懜N�Z�˄>ŝ@�=B�v�*
����1�'U�nwP,[]$�����xKQ�͕�1�MM�&�ڸ2è_%Qᴛ�ց�U��lI�s�*F4���TR�4V���hlE�9��5�Z�ߟ3���N��P���a�(�
��dU��+�����@�^d�R\/��#�H>pV4���hM<<[cH�/����o  ����
��a����`U�G����J��kp6�6��T��� <�e/��R�n��-O1��f�mo�;�o<��?
����{�+���*�I��R=�&���-��Vv�a���R\���}��b�>����D�J��e�m�C�&8.qLS�����d�J�4g�\nB��(5N�˳JF�zx�|�y��R/����W>:)zt��w5�7"�7G�J�����⢼7�����vhyM��i9����]Y�
¢�����zC����>��E�4���M?@��`�� f���ƹSߐ������<PZ~{ ����>u�����@�w��� О��8�RT}��!km�B����xsԑ]5��%;"�J"?�hµ|���J�RW�A>�XG���F`
��b����&�]kZ�ωę ������Q�~(��Ϊ����D�[;p<���F�V��8b���H1��"����6�΄����
]�u�Q��`����ӿKYk3��c��@�[}2Fڝw<�v��/�eR�D����`T�k�-�!R��6�PI��4� �9�hn e8���]��(��9"O��Om6]Y����M��,�g��݃�}�^���Y�I����|��L�*j!6x]Q2ءx#=���*<$l��%�n�17���&ۏ��t9�C�1.���0�~��|�4s6��ݺW=�Ew8�4�H�Y�K.�C��=���/�J�a�B�:��������Ѫ���_�A����<�)�����Yl��[�]
坛l_�v�N�)���<����ݴ���;?�{��k�f4,�ɂ��S�@MS����7||�J,4D�lg�I]����Fh��ee�?�A.����[�)���J��+��!�'>q��0^����y�>�orx���{/�S~��}<�@N�����"{�WߡFq�O��]͑�;]C��)�<���lL�T ��$%�s�֌7�]>�A����1u��>���s�֯qEt]GZ����
ה�=Z�&�U�𻺫 ̈j�9]]J�L�?b���wn'�J��JT�.��rJ3\�G(ϴ��iN;)���tE�j��T�cǫv��cꛫ;׶����Sjǈ}I    ���x���M�'��'?����&U�:����j��3�WN.��S�z"×��(�'M���U����S�������v�s`q�4c��l1��c����g$'R�W�n�C��]��~�r�M��O�Q�����%t.U �I�,�9�G���m��%��ū�+J'���
K�8Y���l��̋�b��f����-S�Hu��h%J�$T��Y=�������	
f%�ۻ�	9��k]���4�o6Bd ���6�WM���JE�'�s���.���_������lؽ	6�zU��� �u�׋!F����,�mYk �BV����*��x��FW�-�{�ʖ|����?]*Yw��rr-�贎sǗz�:v��%�eM��x�Q,��O�����·~$jf]��ٯ�T�l������+�[���E(=��u9Z���W+��]�HW��c��K$"-z�53~n4��lޭ�]>��$`2_d+��o��Z��~�b�����[�<�}U�rK^-d�ۚ��.�H��S��L5�ld�K�-��E�%��>�$~9�`H~���{��t�����I!��`�e��vd�VڕU�HҞ9=V�1i���@Ͽ�<���~ڇ��X�|���-�k�"E:�Z�t9'n)y���Ǭa���e�f���I�-1�#@z��=_��$�4~ծM'޸�A����6�v%�֜�o���+��ƉWQ�h_՜4zO����D{E�;<u����R���K��I*�+����]z��$OƎe�Ҕ��aÅ8.R��N����!p���^�р�h�����ˣ�����}WE8�$�_H�A��EO�$h�q��	�ᯣ��;U���|�J�I�]s���q��yP\���bp�x���fU�l�	ͩd�cޠ�8��%�=I@���P�=β���W���-���U�s�"G��\F�yʌn���vH��%��C�c�ݹ�zO^)�vuN�2�(t�A>Z��])1��[�a��ʍ�N��M�5t7�ͧʂ��f�*�a��� ��f�`�\�M4��-��Z�z C�w5Rj^�xȆ�Qr;	�U�Mw��t�Pq�\F��]�Ǿ��kU'q�,���R|$2J���#����BF��p-ⵥ���sv$�ǻ7����nNz��ᚑ��5�Z�$�\�������YdhoA�}�nAu"0?W�XU�6#b�.Fְ��W����o�Ý�V�� 
�`����#�Z���30Xh�&�\��zm���J��3c9���4����\?��5<="5Յ΄�O��/�u�e�ρT��^N=����>���[�͚�dS�C,f��.$s�(yӪI�aj�E	��*z)�$�&=��	z�7Ź�W�z���T	�'�C)m�RR��q�}Y�����2���`�Iș�?�� �Q�1T�̐o�eS襐�R�L��$����vѤ�.�꺅r��q�x���hd����U^ը���h�..l��g�Z���L!�8!�2-�����ئt���H���]8Ő�P���&?[;R���K��^AYQ�0`i#��!�qa�47|TV�{6��EU0ˁ藰��\�w�#h�j?ۉ��m��/�L�����#�-WbڔPθͪ�rI�~|ֲ���5��G��zw��:�%�+��g�g�D_�.��%U�;ȕ���cz�DEa�&�nZ�	{��]=�b��sz?�9K+�X{�Qw���͛/Ap�<BH	l�O��B��#A�C�U.� ����@<c?(;�&�;C���h���k-���iS�V 5�zY������X9eu����d��9� �{5b�d�*�K���ΥCa.��/{u�Csw>w��Q��Q镟8üB��&��A&H= �S��mώe��k��ŋ�0�'�Kµ��d'��u��)8��#�/���2Ά1��̱�^_�.)_]59Y���>o��JJu��Y�G�a�q(ZkЍ�x�����n��� i�:�i�B��D��� ��;z�.�n�!Ai�����t�����9���߻7���	��zwi��4T���� G8i�҈�jn77�G@9Z�m&vKA�݀�2Q����ω����h�Ù�$����?V�*��1-hC��}����݌�dNg*d+>d�����3�1�"R���!����V�؎m��Ly�?[�_U�v��?Լ�b�}�cʀ�!.	��7ۏ�{�_���-b�N��Q�>w���*�`�r�&Ss�]GT�:�b=����'U�`M���u�>�^pl���TG�ʭ[�:��>h2��Cr��{l��/
:% �g�#�v^���MIa`�fJ��u��r�]J
�@e�J��h�2(Rý��Q$3O|�ߺ+$��J��������d�������mHM��R��-��w���멵�,��oߙPI�q�q�ə?���O�H&�����s��e���W��(UvL���+�|����N��ʕ4�<U8�e��2x;+��oJ�ɢ��uU�`j����~Uy�s���� ��P�S�Q���D���N�w�6�*x�
y�-u� '߭G�� *�t�F�<>�ȸT�^$%����N��~��������՚�(ϣ�	4m
S��I������_����SJ�s�5G�?;�Ͼ�%�c&��8���n�&Xw�dA��r��O��7"�� \��VRo���Eu��;�ZU�7������h^���%/��ǧ�a���ɉ�t;XPV7)���cx�k��ꇤ��٨p�%^|v�>Z��W� )�NT��$�^`��SM�ۖ��ym�I�Vʤp[����g�J��W���Ƕ����3�>� �6B�[�j�T:���l\�4ŒZ��_{��!ZP�:+|���!J�I�H��/+a{_
X��mi��p���BqI��m ���!#����lw_S�)p4�6g���֘o�fُ���J]�c=�t�>_�QR�dd�OW��)��ͪ��um[��m�1���+I@/UP�e<�j�Q���t'łcp�Q:eR�:|�MM	��I�BO��{����ˉ�V����t��]��_�`ʺ�����k9~��V�n�R9�&Ǯ����	�=8���%ݰ�|�ړV��c/�<V��-���T�i=�����#���A;.�A~�#�;Hإ<|��5H�:��ܑ��5�ڽ$��nԶ��]Z���(I.�e�ko�u�u��r���L�&W"�.s���TR�=h���`y��N�;o�JI�tn[S���:
����5=��7��c0/���d�6�}� �� �d�D�o�+(��z\��"�@/�t��i�hC��h%�F��O:t{to�M��[��݆�bI�r�^b���P����os��b-�5�p��P�u���ן~�-��U#l��wshy�c��k¨�6Q�-��V��	����R�ބXT�Pؒs��o��<��+eid���1�[����1^�\��}AK�~R����u
5�]���fT��k@#i���*�Z�i
�U?؜.�l
����(����b������ϷW������^�ޒ�*"M%܂$U#�
��t��(�%�5�)���C�Q���u^�5񕤨��AwC�+�� �]e�y�S�#�q1��LQ�t�du%��d+M�H��p.��� ���8i���Հ��TA@\��%-�z���<#mKȟ{���$&���nN��^]��dq��TU6���=z���7,j-���t�+yQ;Z�.�6����?k% m��y�ϖP�̶�˿m�J_�[Ł�� ����ٺ�縯+�&.us���w�)d��.A��)βm������?L@*����k9r�-�wu'b�:� ��d�Ĭ�V��z^7�+���8e��G{�(����:lYK�tp�a��{�q�����v����m�s�F�~%Ly?��8ן�U��FJ7a%)�򼺼�k����ĉ]��&G�q�L��p��E��m�����_:r!�yI��+�5��x�[X��k�o�gsr�9Q-�ɗRq
u��G��D��b���s��Jy]�C8U�N�ֺ�%���z}���N��t'$���x� ]  ��iǒ����>v�0�V�Vw�`������J�Y��Ϙ�XD��K��7if��_x�Bf�V�gR�@nz�5����2>� q�a��R#�sa��JOH�$<�V��C7آ\�$��r�Q���.���:��د������O�=rl�wc1�����b[�m�ժt�ap(I_wOt4c��̢�wk���5d)�1-;���L��|7��Ь����d}7�k0?䍺?�-m!=�^��v��?3�B'�y�em��FV�^q� 8��G�:�s��u��R
�%XvbHc�-ڳ��&Wvr3~.YU~��1e��
0��{�\#�� �2_��/O�'�}�	�����̤M�E�y�,��(X���zj����;���nd��I-����M�b�~_��ֻ�.�>�:I��O���zD�rռ�c���f�.�E>6g�G���#�G��ˈU&ߐ��	��:�XT�n�1u� �m�J�]E��tI�:E�nh�Nia�C��|̨��uw����3�jw�14��⠒�����d�Y�Ӻ�_�����:a�s#��ۙF	>�z�|����;U�8TX�l�G��h��6�}��rq��N���W����{�ֺp�<L�{�]w�V�wC��i��ޮ�?�k�����}/�w]�#�l6h�MT����$�n�2���ϣzZ����&�Ru�̢t��G�T$�cy#xs�*Q_�� ��h��	��uR�),;�����l<bM���˳��W��7W}o/���P~��D��l��*�g�]]1���Վ� O�����Մ���Yd�S'������w�Z#�������s����{�������?����ǣ�      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   K   x�3�4���43�4�46���"w�Լ��Ԓ�L����k�Ks�RB���JRt|B\�b���� �l<      �      x������ � �      �      x�3�LL��̃�9%��%�1~\1z\\\ �3�      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     