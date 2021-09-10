PGDMP     ,            
        y            estagio    13.3    13.2 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
    public          postgres    false    206   `      �          0    27937    compra 
   TABLE DATA           �   COPY public.compra (comp_codigo, forn_codigo, cli_codigo, comp_qtd_parcelas, comp_valor_total, comp_ajuste, comp_data_compra, comp_nota_fiscal, comp_data_emissao, comp_vendedor) FROM stdin;
    public          postgres    false    223   �      �          0    27757    design 
   TABLE DATA             COPY public.design (desi_fundo_interface, desi_fundo2_interface, desi_fonte_interface, desi_fundo_botao, desi_preenchimento_botao, desi_fonte_botao, desi_tamanho_botao, desi_fonte_entrada, desi_foco_entrada, desi_tamanho_entrada, desi_opacidade) FROM stdin;
    public          postgres    false    200         �          0    28085    despesa 
   TABLE DATA           �   COPY public.despesa (desp_codigo, desp_nome, desp_fixo, desp_preco, desp_data_vencimento, desp_descricao, trans_codigo) FROM stdin;
    public          postgres    false    238   T      �          0    27762    endereco 
   TABLE DATA           �   COPY public.endereco (ender_codigo, ender_rua, ender_bairro, ender_numero, ender_cidade, ender_estado, ender_cep, ender_complemento) FROM stdin;
    public          postgres    false    202   q      �          0    27776    endereco_parametrizacao 
   TABLE DATA           J   COPY public.endereco_parametrizacao (para_nome, ender_codigo) FROM stdin;
    public          postgres    false    204   Y      �          0    27929    forma_pagamento 
   TABLE DATA           o   COPY public.forma_pagamento (form_pagamento_codigo, form_pagamento_nome, form_pagamento_descricao) FROM stdin;
    public          postgres    false    221   �      �          0    27824 
   fornecedor 
   TABLE DATA           }   COPY public.fornecedor (forn_codigo, forn_nome, forn_cnpj, forn_email, forn_ativo, forn_alteracao, ender_codigo) FROM stdin;
    public          postgres    false    210   �      �          0    27806    funcionario 
   TABLE DATA           �   COPY public.funcionario (func_codigo, func_nome, func_cpf, func_rg, func_email, func_datacadastro, func_ativo, func_alteracao, ender_codigo, func_vencimento_cnh, func_cnh_frente, func_cnh_verso) FROM stdin;
    public          postgres    false    208   �      �          0    27865    marca 
   TABLE DATA           9   COPY public.marca (marca_codigo, marca_nome) FROM stdin;
    public          postgres    false    211   �      �          0    27874    modelo 
   TABLE DATA           J   COPY public.modelo (modelo_codigo, modelo_nome, marca_codigo) FROM stdin;
    public          postgres    false    213   �      �          0    28098 	   pagamento 
   TABLE DATA           ~   COPY public.pagamento (pag_codigo, pag_data, pag_valor, parc_codigo, pag_forma_pagamento, pag_ativo, desp_codigo) FROM stdin;
    public          postgres    false    240   	      �          0    27768    parametrizacao 
   TABLE DATA           �   COPY public.parametrizacao (para_nome, para_fantasia, para_logogrande, para_logopequeno, para_email, para_razaosocial) FROM stdin;
    public          postgres    false    203   .	      �          0    27988    parcela 
   TABLE DATA           �   COPY public.parcela (parc_codigo, parc_status, parc_datavencimento, parc_numero, parc_datapagamento, parc_valorpago, parc_valorparcela, ven_codigo, comp_codigo) FROM stdin;
    public          postgres    false    228   o	      �          0    28021    recebimento 
   TABLE DATA           s   COPY public.recebimento (rec_codigo, rec_data, rec_valor, parc_codigo, rec_forma_pagamento, rec_ativo) FROM stdin;
    public          postgres    false    231   �	      �          0    28116    registro_pagamento 
   TABLE DATA           v   COPY public.registro_pagamento (reg_pag_codigo, pag_codigo, reg_pag_relatorio, reg_pag_data, func_codigo) FROM stdin;
    public          postgres    false    242   �	      �          0    28034    registro_recebimento 
   TABLE DATA           x   COPY public.registro_recebimento (reg_rec_codigo, rec_codigo, reg_rec_relatorio, reg_rec_data, func_codigo) FROM stdin;
    public          postgres    false    233   �	      �          0    28137    telefone 
   TABLE DATA           k   COPY public.telefone (tel_codigo, tel_numero, cli_codigo, func_codigo, forn_codigo, para_nome) FROM stdin;
    public          postgres    false    244   �	      �          0    28052 
   transporte 
   TABLE DATA           �   COPY public.transporte (trans_codigo, func_codigo, vei_codigo, trans_data_saida, trans_data_chegada, trans_status, trans_tipo, trans_data_alteracao) FROM stdin;
    public          postgres    false    235   '
      �          0    27897    usuario 
   TABLE DATA           j   COPY public.usuario (user_codigo, user_nome, user_senha, user_nivel, user_ativo, func_codigo) FROM stdin;
    public          postgres    false    217   D
      �          0    27887    veiculo 
   TABLE DATA           |   COPY public.veiculo (vei_codigo, vei_chassi, vei_placa, vei_modelo, vei_marca, vei_ano, vei_cor, vei_descricao) FROM stdin;
    public          postgres    false    215   r
      �          0    27953    veiculo_compra 
   TABLE DATA           Q   COPY public.veiculo_compra (comp_codigo, vei_codigo, vei_comp_valor) FROM stdin;
    public          postgres    false    224   �
      �          0    28004    veiculo_venda 
   TABLE DATA           N   COPY public.veiculo_venda (ven_codigo, vei_codigo, vei_ven_valor) FROM stdin;
    public          postgres    false    229   �
      �          0    28068    veiculos_transportados 
   TABLE DATA           J   COPY public.veiculos_transportados (trans_codigo, vei_codigo) FROM stdin;
    public          postgres    false    236   �
      �          0    27970    venda 
   TABLE DATA           �   COPY public.venda (ven_codigo, cli_codigo, forn_codigo, ven_qtd_parcelas, ven_valor_total, ven_ajuste, ven_data_compra, ven_nota_fiscal, ven_data_emissao) FROM stdin;
    public          postgres    false    226   �
      �           0    0    acessos_acess_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.acessos_acess_codigo_seq', 9, true);
          public          postgres    false    218            �           0    0    cliente_cli_codigo_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.cliente_cli_codigo_seq', 2, true);
          public          postgres    false    205            �           0    0    compra_comp_codigo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.compra_comp_codigo_seq', 1, false);
          public          postgres    false    222            �           0    0    despesa_desp_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.despesa_desp_codigo_seq', 1, false);
          public          postgres    false    237            �           0    0    endereco_ender_codigo_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.endereco_ender_codigo_seq', 6, true);
          public          postgres    false    201            �           0    0 )   forma_pagamento_form_pagamento_codigo_seq    SEQUENCE SET     X   SELECT pg_catalog.setval('public.forma_pagamento_form_pagamento_codigo_seq', 1, false);
          public          postgres    false    220            �           0    0    fornecedor_forn_codigo_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.fornecedor_forn_codigo_seq', 1, false);
          public          postgres    false    209            �           0    0    funcionario_func_codigo_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.funcionario_func_codigo_seq', 1, false);
          public          postgres    false    207            �           0    0    modelo_modelo_codigo_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.modelo_modelo_codigo_seq', 1, false);
          public          postgres    false    212            �           0    0    pagamento_pag_codigo_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.pagamento_pag_codigo_seq', 1, false);
          public          postgres    false    239            �           0    0    parcela_parc_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.parcela_parc_codigo_seq', 1, false);
          public          postgres    false    227            �           0    0    recebimento_rec_codigo_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.recebimento_rec_codigo_seq', 1, false);
          public          postgres    false    230            �           0    0 %   registro_pagamento_reg_pag_codigo_seq    SEQUENCE SET     T   SELECT pg_catalog.setval('public.registro_pagamento_reg_pag_codigo_seq', 1, false);
          public          postgres    false    241            �           0    0 '   registro_recebimento_reg_rec_codigo_seq    SEQUENCE SET     V   SELECT pg_catalog.setval('public.registro_recebimento_reg_rec_codigo_seq', 1, false);
          public          postgres    false    232            �           0    0    telefone_tel_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.telefone_tel_codigo_seq', 12, true);
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
       public          postgres    false    3014    206    223            �   �   x�m���0��)�@�����(�*U��z�3`y)��y�9O��Q��<)9^r��EY����!Š������Xa���b�`
�n��XW�΁_���v�q��%N��I�Ι�l�=Fһ������m�zY��&/��%��')֜ysе���N:������V�      �   �   x�M�K
�0D��)rɲ�[z�nL(Ԑ`�$����+�f�<����2"�.Y	 �[ʖ"�9����/P�����z�O��u�^A	Sf���o�o������Ͻ�׾Ϣ�p,2*GP�@c�(P'Z      �      x������ � �      �   3   x�Sv3ANe70�T62AΒ�ļ�ĢԼ��64�&dh`����� ��      �      x������ � �      �   �   x���;��0@��)|�UL>+J�D�RDY��f��`��#;ނ--��\lIҤ�X-S������2�l�ˊ��=������LG�pa\� ���4I"X}���}����6���\]���E55O���{�T��!����0Q�h���=���ϻ���q�Z����Q�B�e�����B�,;�잲i���̞�g����yz�{�!����      �      x�K�-(J-N�4����� '��      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   1   x�K�-(J-N�LK�+I,�L����\��\���\΢Ī�|�=... V��      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   4   x�3�4���43�4035�4�� .C#�����\�35��(�8�+F��� �qb      �      x������ � �      �      x�3�LL��̃�9%��%�1~\1z\\\ �3�      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     