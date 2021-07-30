PGDMP             	            y            oficina    13.2    13.2 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16394    oficina    DATABASE     g   CREATE DATABASE oficina WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE oficina;
                postgres    false            �            1255    24915    decrementa_estoque()    FUNCTION       CREATE FUNCTION public.decrementa_estoque() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE produto 
		SET prod_quantidade = prod_quantidade - new.prod_orc_quantidade
		WHERE prod_codigo = new.prod_codigo AND prod_consumivel = 'true';

           RETURN new;
END;
$$;
 +   DROP FUNCTION public.decrementa_estoque();
       public          postgres    false            �            1255    24910    incrementa_estoque()    FUNCTION       CREATE FUNCTION public.incrementa_estoque() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE produto 
		SET prod_quantidade = prod_quantidade + old.prod_orc_quantidade
		WHERE prod_codigo = old.prod_codigo AND prod_consumivel = 'true';

           RETURN new;
END;
$$;
 +   DROP FUNCTION public.incrementa_estoque();
       public          postgres    false            �            1259    16395    acessos    TABLE     �   CREATE TABLE public.acessos (
    acess_codigo integer NOT NULL,
    acess_data_login timestamp without time zone NOT NULL,
    acess_data_logout timestamp without time zone NOT NULL,
    user_codigo integer NOT NULL
);
    DROP TABLE public.acessos;
       public         heap    postgres    false            �            1259    16398    acessos_acess_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.acessos_acess_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.acessos_acess_codigo_seq;
       public          postgres    false    200            �           0    0    acessos_acess_codigo_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.acessos_acess_codigo_seq OWNED BY public.acessos.acess_codigo;
          public          postgres    false    201            �            1259    16400    cliente    TABLE     �  CREATE TABLE public.cliente (
    cli_codigo integer NOT NULL,
    cli_nome character varying(45) NOT NULL,
    cli_cpf character varying(15) NOT NULL,
    cli_rg character varying(15) NOT NULL,
    cli_email character varying(45),
    cli_datacadastro date NOT NULL,
    cli_ativo boolean NOT NULL,
    cli_alteracao timestamp without time zone NOT NULL,
    ender_codigo integer NOT NULL
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            �            1259    16403    cliente_cli_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.cliente_cli_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.cliente_cli_codigo_seq;
       public          postgres    false    202            �           0    0    cliente_cli_codigo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.cliente_cli_codigo_seq OWNED BY public.cliente.cli_codigo;
          public          postgres    false    203            �            1259    16405    compra    TABLE     ^  CREATE TABLE public.compra (
    comp_codigo integer NOT NULL,
    forn_codigo integer NOT NULL,
    comp_qtd_parcelas integer NOT NULL,
    comp_valor_total numeric(10,2) NOT NULL,
    comp_ajuste numeric(10,2) NOT NULL,
    comp_data_compra date NOT NULL,
    comp_nota_fiscal character varying(45) NOT NULL,
    comp_data_emissao date NOT NULL
);
    DROP TABLE public.compra;
       public         heap    postgres    false            �            1259    16408    compra_comp_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.compra_comp_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.compra_comp_codigo_seq;
       public          postgres    false    204            �           0    0    compra_comp_codigo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.compra_comp_codigo_seq OWNED BY public.compra.comp_codigo;
          public          postgres    false    205            �            1259    24738    design    TABLE     O  CREATE TABLE public.design (
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
    desi_opacidade integer
);
    DROP TABLE public.design;
       public         heap    postgres    false            �            1259    16410    despesa    TABLE     	  CREATE TABLE public.despesa (
    desp_codigo integer NOT NULL,
    desp_nome character varying(45) NOT NULL,
    desp_fixo boolean NOT NULL,
    desp_preco numeric(10,2) NOT NULL,
    desp_data_vencimento date NOT NULL,
    desp_descricao character varying(45)
);
    DROP TABLE public.despesa;
       public         heap    postgres    false            �            1259    16413    despesa_desp_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.despesa_desp_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.despesa_desp_codigo_seq;
       public          postgres    false    206            �           0    0    despesa_desp_codigo_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.despesa_desp_codigo_seq OWNED BY public.despesa.desp_codigo;
          public          postgres    false    207            �            1259    16415    endereco    TABLE     q  CREATE TABLE public.endereco (
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
       public         heap    postgres    false            �            1259    16418    endereco_ender_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.endereco_ender_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.endereco_ender_codigo_seq;
       public          postgres    false    208            �           0    0    endereco_ender_codigo_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.endereco_ender_codigo_seq OWNED BY public.endereco.ender_codigo;
          public          postgres    false    209            �            1259    24685    endereco_parametrizacao    TABLE     �   CREATE TABLE public.endereco_parametrizacao (
    para_nome character varying(50) NOT NULL,
    ender_codigo integer NOT NULL
);
 +   DROP TABLE public.endereco_parametrizacao;
       public         heap    postgres    false            �            1259    16420 
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
       public         heap    postgres    false            �            1259    16423    fornecedor_forn_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.fornecedor_forn_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.fornecedor_forn_codigo_seq;
       public          postgres    false    210            �           0    0    fornecedor_forn_codigo_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.fornecedor_forn_codigo_seq OWNED BY public.fornecedor.forn_codigo;
          public          postgres    false    211            �            1259    16425    marca    TABLE     p   CREATE TABLE public.marca (
    marca_codigo integer NOT NULL,
    marca_nome character varying(45) NOT NULL
);
    DROP TABLE public.marca;
       public         heap    postgres    false            �            1259    16428    modelo    TABLE     �   CREATE TABLE public.modelo (
    modelo_codigo integer NOT NULL,
    modelo_nome character varying(45) NOT NULL,
    marca_codigo integer NOT NULL
);
    DROP TABLE public.modelo;
       public         heap    postgres    false            �            1259    16431    modelo_modelo_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.modelo_modelo_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.modelo_modelo_codigo_seq;
       public          postgres    false    213            �           0    0    modelo_modelo_codigo_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.modelo_modelo_codigo_seq OWNED BY public.modelo.modelo_codigo;
          public          postgres    false    214            �            1259    16433 	   orcamento    TABLE     �   CREATE TABLE public.orcamento (
    orc_codigo integer NOT NULL,
    vei_codigo integer NOT NULL,
    orc_status character varying(45) NOT NULL
);
    DROP TABLE public.orcamento;
       public         heap    postgres    false            �            1259    16436    orcamento_orc_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.orcamento_orc_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.orcamento_orc_codigo_seq;
       public          postgres    false    215            �           0    0    orcamento_orc_codigo_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.orcamento_orc_codigo_seq OWNED BY public.orcamento.orc_codigo;
          public          postgres    false    216            �            1259    24838    ordem_de_servico    TABLE     �   CREATE TABLE public.ordem_de_servico (
    os_codigo integer NOT NULL,
    orc_codigo integer NOT NULL,
    os_status character varying(45) NOT NULL
);
 $   DROP TABLE public.ordem_de_servico;
       public         heap    postgres    false            �            1259    24836    ordem_de_servico_os_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.ordem_de_servico_os_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.ordem_de_servico_os_codigo_seq;
       public          postgres    false    240            �           0    0    ordem_de_servico_os_codigo_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.ordem_de_servico_os_codigo_seq OWNED BY public.ordem_de_servico.os_codigo;
          public          postgres    false    239            �            1259    24611 	   pagamento    TABLE       CREATE TABLE public.pagamento (
    pag_codigo integer NOT NULL,
    pag_data date NOT NULL,
    pag_valor numeric(10,2) NOT NULL,
    parc_codigo integer,
    pag_forma_pagamento character varying(12) NOT NULL,
    pag_ativo boolean NOT NULL,
    desp_codigo integer
);
    DROP TABLE public.pagamento;
       public         heap    postgres    false            �            1259    24609    pagamento_pag_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.pagamento_pag_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.pagamento_pag_codigo_seq;
       public          postgres    false    233            �           0    0    pagamento_pag_codigo_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.pagamento_pag_codigo_seq OWNED BY public.pagamento.pag_codigo;
          public          postgres    false    232            �            1259    24677    parametrizacao    TABLE     "  CREATE TABLE public.parametrizacao (
    para_nome character varying(50) NOT NULL,
    para_fantasia character varying(50) NOT NULL,
    para_logogrande bytea,
    para_logopequeno bytea,
    para_email character varying(50) NOT NULL,
    para_razaosocial character varying(50) NOT NULL
);
 "   DROP TABLE public.parametrizacao;
       public         heap    postgres    false            �            1259    16443    parcela_compra    TABLE     u  CREATE TABLE public.parcela_compra (
    parc_compra_codigo integer NOT NULL,
    parc_compra_status integer,
    parc_compra_datavencimento date NOT NULL,
    parc_compra_numero integer NOT NULL,
    parc_compra_datapagamento date,
    parc_compra_valorpago numeric(6,2),
    parc_compra_compra_cod integer NOT NULL,
    parc_compra_valorparcela numeric(10,2) NOT NULL
);
 "   DROP TABLE public.parcela_compra;
       public         heap    postgres    false            �            1259    16446 %   parcela_compra_parc_compra_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.parcela_compra_parc_compra_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 <   DROP SEQUENCE public.parcela_compra_parc_compra_codigo_seq;
       public          postgres    false    217            �           0    0 %   parcela_compra_parc_compra_codigo_seq    SEQUENCE OWNED BY     o   ALTER SEQUENCE public.parcela_compra_parc_compra_codigo_seq OWNED BY public.parcela_compra.parc_compra_codigo;
          public          postgres    false    218            �            1259    16448    produto    TABLE       CREATE TABLE public.produto (
    prod_codigo integer NOT NULL,
    prod_nome character varying(45) NOT NULL,
    prod_preco numeric(10,2) NOT NULL,
    prod_quantidade integer NOT NULL,
    prod_consumivel boolean NOT NULL,
    prod_descricao character varying(90)
);
    DROP TABLE public.produto;
       public         heap    postgres    false            �            1259    16451    produto_compra    TABLE     �   CREATE TABLE public.produto_compra (
    comp_codigo integer NOT NULL,
    prod_codigo integer NOT NULL,
    prod_comp_valor numeric(10,2) NOT NULL,
    prod_comp_quantidade integer NOT NULL
);
 "   DROP TABLE public.produto_compra;
       public         heap    postgres    false            �            1259    16454    produto_prod_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.produto_prod_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.produto_prod_codigo_seq;
       public          postgres    false    219            �           0    0    produto_prod_codigo_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.produto_prod_codigo_seq OWNED BY public.produto.prod_codigo;
          public          postgres    false    221            �            1259    16456    produtos_orcamento    TABLE     �   CREATE TABLE public.produtos_orcamento (
    orc_codigo integer NOT NULL,
    prod_codigo integer NOT NULL,
    prod_orc_preco numeric(10,2) NOT NULL,
    prod_orc_quantidade integer NOT NULL
);
 &   DROP TABLE public.produtos_orcamento;
       public         heap    postgres    false            �            1259    24851    produtos_os    TABLE     �   CREATE TABLE public.produtos_os (
    prod_os_codigo integer NOT NULL,
    os_codigo integer NOT NULL,
    prod_codigo integer NOT NULL,
    prod_os_preco numeric(10,2) NOT NULL,
    prod_os_quantidade integer NOT NULL
);
    DROP TABLE public.produtos_os;
       public         heap    postgres    false            �            1259    24849    produtos_os_prod_os_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.produtos_os_prod_os_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.produtos_os_prod_os_codigo_seq;
       public          postgres    false    242            �           0    0    produtos_os_prod_os_codigo_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.produtos_os_prod_os_codigo_seq OWNED BY public.produtos_os.prod_os_codigo;
          public          postgres    false    241            �            1259    24668    registro_pagamento    TABLE     �   CREATE TABLE public.registro_pagamento (
    reg_pag_codigo integer NOT NULL,
    reg_pag_relatorio character varying(600) NOT NULL,
    reg_pag_data date NOT NULL,
    user_codigo integer NOT NULL
);
 &   DROP TABLE public.registro_pagamento;
       public         heap    postgres    false            �            1259    24666 %   registro_pagamento_reg_pag_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.registro_pagamento_reg_pag_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 <   DROP SEQUENCE public.registro_pagamento_reg_pag_codigo_seq;
       public          postgres    false    235            �           0    0 %   registro_pagamento_reg_pag_codigo_seq    SEQUENCE OWNED BY     o   ALTER SEQUENCE public.registro_pagamento_reg_pag_codigo_seq OWNED BY public.registro_pagamento.reg_pag_codigo;
          public          postgres    false    234            �            1259    16459    servico    TABLE     �   CREATE TABLE public.servico (
    serv_codigo integer NOT NULL,
    serv_nome character varying(45) NOT NULL,
    serv_preco numeric(10,2) NOT NULL,
    serv_descricao character varying(45) NOT NULL
);
    DROP TABLE public.servico;
       public         heap    postgres    false            �            1259    16462    servico_serv_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.servico_serv_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.servico_serv_codigo_seq;
       public          postgres    false    223            �           0    0    servico_serv_codigo_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.servico_serv_codigo_seq OWNED BY public.servico.serv_codigo;
          public          postgres    false    224            �            1259    16464    servicos_orcamento    TABLE     �   CREATE TABLE public.servicos_orcamento (
    orc_codigo integer NOT NULL,
    serv_codigo integer NOT NULL,
    serv_orc_preco numeric(10,2) NOT NULL
);
 &   DROP TABLE public.servicos_orcamento;
       public         heap    postgres    false            �            1259    24869    servicos_os    TABLE     �   CREATE TABLE public.servicos_os (
    serv_os_codigo integer NOT NULL,
    os_codigo integer NOT NULL,
    serv_codigo integer NOT NULL,
    serv_os_preco numeric(10,2) NOT NULL
);
    DROP TABLE public.servicos_os;
       public         heap    postgres    false            �            1259    24867    servicos_os_serv_os_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.servicos_os_serv_os_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.servicos_os_serv_os_codigo_seq;
       public          postgres    false    244            �           0    0    servicos_os_serv_os_codigo_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.servicos_os_serv_os_codigo_seq OWNED BY public.servicos_os.serv_os_codigo;
          public          postgres    false    243            �            1259    16467    telefone    TABLE     �   CREATE TABLE public.telefone (
    tel_codigo integer NOT NULL,
    tel_numero character varying(11),
    cli_codigo integer,
    forn_codigo integer,
    para_nome character varying(50)
);
    DROP TABLE public.telefone;
       public         heap    postgres    false            �            1259    16470    telefone_tel_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.telefone_tel_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.telefone_tel_codigo_seq;
       public          postgres    false    226            �           0    0    telefone_tel_codigo_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.telefone_tel_codigo_seq OWNED BY public.telefone.tel_codigo;
          public          postgres    false    227            �            1259    16472    usuario    TABLE     �   CREATE TABLE public.usuario (
    user_codigo integer NOT NULL,
    user_nome character varying(45) NOT NULL,
    user_senha character varying(45) NOT NULL,
    user_nivel integer NOT NULL,
    user_ativo boolean NOT NULL
);
    DROP TABLE public.usuario;
       public         heap    postgres    false            �            1259    24887 
   usuario_os    TABLE     �   CREATE TABLE public.usuario_os (
    user_os_codigo integer NOT NULL,
    os_codigo integer NOT NULL,
    user_codigo integer NOT NULL,
    user_os_data_alteracao date NOT NULL
);
    DROP TABLE public.usuario_os;
       public         heap    postgres    false            �            1259    24885    usuario_os_user_os_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_os_user_os_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.usuario_os_user_os_codigo_seq;
       public          postgres    false    246            �           0    0    usuario_os_user_os_codigo_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.usuario_os_user_os_codigo_seq OWNED BY public.usuario_os.user_os_codigo;
          public          postgres    false    245            �            1259    16475    usuario_user_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_user_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.usuario_user_codigo_seq;
       public          postgres    false    228            �           0    0    usuario_user_codigo_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.usuario_user_codigo_seq OWNED BY public.usuario.user_codigo;
          public          postgres    false    229            �            1259    16477    veiculo    TABLE     �  CREATE TABLE public.veiculo (
    vei_codigo integer NOT NULL,
    vei_chassi character varying(45),
    vei_placa character varying(8) NOT NULL,
    vei_modelo character varying(45) NOT NULL,
    vei_marca character varying(45) NOT NULL,
    vei_ano integer NOT NULL,
    vei_cor character varying(45) NOT NULL,
    vei_descricao character varying(45),
    cli_codigo integer NOT NULL
);
    DROP TABLE public.veiculo;
       public         heap    postgres    false            �            1259    16480    veiculo_vei_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.veiculo_vei_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.veiculo_vei_codigo_seq;
       public          postgres    false    230            �           0    0    veiculo_vei_codigo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.veiculo_vei_codigo_seq OWNED BY public.veiculo.vei_codigo;
          public          postgres    false    231            �           2604    16482    acessos acess_codigo    DEFAULT     |   ALTER TABLE ONLY public.acessos ALTER COLUMN acess_codigo SET DEFAULT nextval('public.acessos_acess_codigo_seq'::regclass);
 C   ALTER TABLE public.acessos ALTER COLUMN acess_codigo DROP DEFAULT;
       public          postgres    false    201    200            �           2604    16483    cliente cli_codigo    DEFAULT     x   ALTER TABLE ONLY public.cliente ALTER COLUMN cli_codigo SET DEFAULT nextval('public.cliente_cli_codigo_seq'::regclass);
 A   ALTER TABLE public.cliente ALTER COLUMN cli_codigo DROP DEFAULT;
       public          postgres    false    203    202            �           2604    16484    compra comp_codigo    DEFAULT     x   ALTER TABLE ONLY public.compra ALTER COLUMN comp_codigo SET DEFAULT nextval('public.compra_comp_codigo_seq'::regclass);
 A   ALTER TABLE public.compra ALTER COLUMN comp_codigo DROP DEFAULT;
       public          postgres    false    205    204            �           2604    16485    despesa desp_codigo    DEFAULT     z   ALTER TABLE ONLY public.despesa ALTER COLUMN desp_codigo SET DEFAULT nextval('public.despesa_desp_codigo_seq'::regclass);
 B   ALTER TABLE public.despesa ALTER COLUMN desp_codigo DROP DEFAULT;
       public          postgres    false    207    206            �           2604    16486    endereco ender_codigo    DEFAULT     ~   ALTER TABLE ONLY public.endereco ALTER COLUMN ender_codigo SET DEFAULT nextval('public.endereco_ender_codigo_seq'::regclass);
 D   ALTER TABLE public.endereco ALTER COLUMN ender_codigo DROP DEFAULT;
       public          postgres    false    209    208            �           2604    16487    fornecedor forn_codigo    DEFAULT     �   ALTER TABLE ONLY public.fornecedor ALTER COLUMN forn_codigo SET DEFAULT nextval('public.fornecedor_forn_codigo_seq'::regclass);
 E   ALTER TABLE public.fornecedor ALTER COLUMN forn_codigo DROP DEFAULT;
       public          postgres    false    211    210            �           2604    16488    modelo modelo_codigo    DEFAULT     |   ALTER TABLE ONLY public.modelo ALTER COLUMN modelo_codigo SET DEFAULT nextval('public.modelo_modelo_codigo_seq'::regclass);
 C   ALTER TABLE public.modelo ALTER COLUMN modelo_codigo DROP DEFAULT;
       public          postgres    false    214    213            �           2604    16489    orcamento orc_codigo    DEFAULT     |   ALTER TABLE ONLY public.orcamento ALTER COLUMN orc_codigo SET DEFAULT nextval('public.orcamento_orc_codigo_seq'::regclass);
 C   ALTER TABLE public.orcamento ALTER COLUMN orc_codigo DROP DEFAULT;
       public          postgres    false    216    215            �           2604    24841    ordem_de_servico os_codigo    DEFAULT     �   ALTER TABLE ONLY public.ordem_de_servico ALTER COLUMN os_codigo SET DEFAULT nextval('public.ordem_de_servico_os_codigo_seq'::regclass);
 I   ALTER TABLE public.ordem_de_servico ALTER COLUMN os_codigo DROP DEFAULT;
       public          postgres    false    239    240    240            �           2604    24614    pagamento pag_codigo    DEFAULT     |   ALTER TABLE ONLY public.pagamento ALTER COLUMN pag_codigo SET DEFAULT nextval('public.pagamento_pag_codigo_seq'::regclass);
 C   ALTER TABLE public.pagamento ALTER COLUMN pag_codigo DROP DEFAULT;
       public          postgres    false    233    232    233            �           2604    16491 !   parcela_compra parc_compra_codigo    DEFAULT     �   ALTER TABLE ONLY public.parcela_compra ALTER COLUMN parc_compra_codigo SET DEFAULT nextval('public.parcela_compra_parc_compra_codigo_seq'::regclass);
 P   ALTER TABLE public.parcela_compra ALTER COLUMN parc_compra_codigo DROP DEFAULT;
       public          postgres    false    218    217            �           2604    16492    produto prod_codigo    DEFAULT     z   ALTER TABLE ONLY public.produto ALTER COLUMN prod_codigo SET DEFAULT nextval('public.produto_prod_codigo_seq'::regclass);
 B   ALTER TABLE public.produto ALTER COLUMN prod_codigo DROP DEFAULT;
       public          postgres    false    221    219            �           2604    24854    produtos_os prod_os_codigo    DEFAULT     �   ALTER TABLE ONLY public.produtos_os ALTER COLUMN prod_os_codigo SET DEFAULT nextval('public.produtos_os_prod_os_codigo_seq'::regclass);
 I   ALTER TABLE public.produtos_os ALTER COLUMN prod_os_codigo DROP DEFAULT;
       public          postgres    false    241    242    242            �           2604    24671 !   registro_pagamento reg_pag_codigo    DEFAULT     �   ALTER TABLE ONLY public.registro_pagamento ALTER COLUMN reg_pag_codigo SET DEFAULT nextval('public.registro_pagamento_reg_pag_codigo_seq'::regclass);
 P   ALTER TABLE public.registro_pagamento ALTER COLUMN reg_pag_codigo DROP DEFAULT;
       public          postgres    false    234    235    235            �           2604    16493    servico serv_codigo    DEFAULT     z   ALTER TABLE ONLY public.servico ALTER COLUMN serv_codigo SET DEFAULT nextval('public.servico_serv_codigo_seq'::regclass);
 B   ALTER TABLE public.servico ALTER COLUMN serv_codigo DROP DEFAULT;
       public          postgres    false    224    223            �           2604    24872    servicos_os serv_os_codigo    DEFAULT     �   ALTER TABLE ONLY public.servicos_os ALTER COLUMN serv_os_codigo SET DEFAULT nextval('public.servicos_os_serv_os_codigo_seq'::regclass);
 I   ALTER TABLE public.servicos_os ALTER COLUMN serv_os_codigo DROP DEFAULT;
       public          postgres    false    243    244    244            �           2604    16494    telefone tel_codigo    DEFAULT     z   ALTER TABLE ONLY public.telefone ALTER COLUMN tel_codigo SET DEFAULT nextval('public.telefone_tel_codigo_seq'::regclass);
 B   ALTER TABLE public.telefone ALTER COLUMN tel_codigo DROP DEFAULT;
       public          postgres    false    227    226            �           2604    16495    usuario user_codigo    DEFAULT     z   ALTER TABLE ONLY public.usuario ALTER COLUMN user_codigo SET DEFAULT nextval('public.usuario_user_codigo_seq'::regclass);
 B   ALTER TABLE public.usuario ALTER COLUMN user_codigo DROP DEFAULT;
       public          postgres    false    229    228            �           2604    24890    usuario_os user_os_codigo    DEFAULT     �   ALTER TABLE ONLY public.usuario_os ALTER COLUMN user_os_codigo SET DEFAULT nextval('public.usuario_os_user_os_codigo_seq'::regclass);
 H   ALTER TABLE public.usuario_os ALTER COLUMN user_os_codigo DROP DEFAULT;
       public          postgres    false    245    246    246            �           2604    16496    veiculo vei_codigo    DEFAULT     x   ALTER TABLE ONLY public.veiculo ALTER COLUMN vei_codigo SET DEFAULT nextval('public.veiculo_vei_codigo_seq'::regclass);
 A   ALTER TABLE public.veiculo ALTER COLUMN vei_codigo DROP DEFAULT;
       public          postgres    false    231    230            �          0    16395    acessos 
   TABLE DATA           a   COPY public.acessos (acess_codigo, acess_data_login, acess_data_logout, user_codigo) FROM stdin;
    public          postgres    false    200         �          0    16400    cliente 
   TABLE DATA           �   COPY public.cliente (cli_codigo, cli_nome, cli_cpf, cli_rg, cli_email, cli_datacadastro, cli_ativo, cli_alteracao, ender_codigo) FROM stdin;
    public          postgres    false    202   �
      �          0    16405    compra 
   TABLE DATA           �   COPY public.compra (comp_codigo, forn_codigo, comp_qtd_parcelas, comp_valor_total, comp_ajuste, comp_data_compra, comp_nota_fiscal, comp_data_emissao) FROM stdin;
    public          postgres    false    204   u      �          0    24738    design 
   TABLE DATA             COPY public.design (desi_fundo_interface, desi_fundo2_interface, desi_fonte_interface, desi_fundo_botao, desi_preenchimento_botao, desi_fonte_botao, desi_tamanho_botao, desi_fonte_entrada, desi_foco_entrada, desi_tamanho_entrada, desi_opacidade) FROM stdin;
    public          postgres    false    238   �      �          0    16410    despesa 
   TABLE DATA           v   COPY public.despesa (desp_codigo, desp_nome, desp_fixo, desp_preco, desp_data_vencimento, desp_descricao) FROM stdin;
    public          postgres    false    206         �          0    16415    endereco 
   TABLE DATA           �   COPY public.endereco (ender_codigo, ender_rua, ender_bairro, ender_numero, ender_cidade, ender_estado, ender_cep, ender_complemento) FROM stdin;
    public          postgres    false    208   �      �          0    24685    endereco_parametrizacao 
   TABLE DATA           J   COPY public.endereco_parametrizacao (para_nome, ender_codigo) FROM stdin;
    public          postgres    false    237   (      �          0    16420 
   fornecedor 
   TABLE DATA           }   COPY public.fornecedor (forn_codigo, forn_nome, forn_cnpj, forn_email, forn_ativo, forn_alteracao, ender_codigo) FROM stdin;
    public          postgres    false    210   S      �          0    16425    marca 
   TABLE DATA           9   COPY public.marca (marca_codigo, marca_nome) FROM stdin;
    public          postgres    false    212   �      �          0    16428    modelo 
   TABLE DATA           J   COPY public.modelo (modelo_codigo, modelo_nome, marca_codigo) FROM stdin;
    public          postgres    false    213   �      �          0    16433 	   orcamento 
   TABLE DATA           G   COPY public.orcamento (orc_codigo, vei_codigo, orc_status) FROM stdin;
    public          postgres    false    215    1      �          0    24838    ordem_de_servico 
   TABLE DATA           L   COPY public.ordem_de_servico (os_codigo, orc_codigo, os_status) FROM stdin;
    public          postgres    false    240   L1      �          0    24611 	   pagamento 
   TABLE DATA           ~   COPY public.pagamento (pag_codigo, pag_data, pag_valor, parc_codigo, pag_forma_pagamento, pag_ativo, desp_codigo) FROM stdin;
    public          postgres    false    233   i1      �          0    24677    parametrizacao 
   TABLE DATA           �   COPY public.parametrizacao (para_nome, para_fantasia, para_logogrande, para_logopequeno, para_email, para_razaosocial) FROM stdin;
    public          postgres    false    236   �1      �          0    16443    parcela_compra 
   TABLE DATA           �   COPY public.parcela_compra (parc_compra_codigo, parc_compra_status, parc_compra_datavencimento, parc_compra_numero, parc_compra_datapagamento, parc_compra_valorpago, parc_compra_compra_cod, parc_compra_valorparcela) FROM stdin;
    public          postgres    false    217   !�      �          0    16448    produto 
   TABLE DATA           w   COPY public.produto (prod_codigo, prod_nome, prod_preco, prod_quantidade, prod_consumivel, prod_descricao) FROM stdin;
    public          postgres    false    219   ��      �          0    16451    produto_compra 
   TABLE DATA           i   COPY public.produto_compra (comp_codigo, prod_codigo, prod_comp_valor, prod_comp_quantidade) FROM stdin;
    public          postgres    false    220   ��      �          0    16456    produtos_orcamento 
   TABLE DATA           j   COPY public.produtos_orcamento (orc_codigo, prod_codigo, prod_orc_preco, prod_orc_quantidade) FROM stdin;
    public          postgres    false    222   i�      �          0    24851    produtos_os 
   TABLE DATA           p   COPY public.produtos_os (prod_os_codigo, os_codigo, prod_codigo, prod_os_preco, prod_os_quantidade) FROM stdin;
    public          postgres    false    242   ��      �          0    24668    registro_pagamento 
   TABLE DATA           j   COPY public.registro_pagamento (reg_pag_codigo, reg_pag_relatorio, reg_pag_data, user_codigo) FROM stdin;
    public          postgres    false    235   Ȋ      �          0    16459    servico 
   TABLE DATA           U   COPY public.servico (serv_codigo, serv_nome, serv_preco, serv_descricao) FROM stdin;
    public          postgres    false    223   �      �          0    16464    servicos_orcamento 
   TABLE DATA           U   COPY public.servicos_orcamento (orc_codigo, serv_codigo, serv_orc_preco) FROM stdin;
    public          postgres    false    225   W�      �          0    24869    servicos_os 
   TABLE DATA           \   COPY public.servicos_os (serv_os_codigo, os_codigo, serv_codigo, serv_os_preco) FROM stdin;
    public          postgres    false    244   t�      �          0    16467    telefone 
   TABLE DATA           ^   COPY public.telefone (tel_codigo, tel_numero, cli_codigo, forn_codigo, para_nome) FROM stdin;
    public          postgres    false    226   ��      �          0    16472    usuario 
   TABLE DATA           ]   COPY public.usuario (user_codigo, user_nome, user_senha, user_nivel, user_ativo) FROM stdin;
    public          postgres    false    228   -�      �          0    24887 
   usuario_os 
   TABLE DATA           d   COPY public.usuario_os (user_os_codigo, os_codigo, user_codigo, user_os_data_alteracao) FROM stdin;
    public          postgres    false    246   ��      �          0    16477    veiculo 
   TABLE DATA           �   COPY public.veiculo (vei_codigo, vei_chassi, vei_placa, vei_modelo, vei_marca, vei_ano, vei_cor, vei_descricao, cli_codigo) FROM stdin;
    public          postgres    false    230   ��      �           0    0    acessos_acess_codigo_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.acessos_acess_codigo_seq', 178, true);
          public          postgres    false    201            �           0    0    cliente_cli_codigo_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.cliente_cli_codigo_seq', 8, true);
          public          postgres    false    203            �           0    0    compra_comp_codigo_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.compra_comp_codigo_seq', 7, true);
          public          postgres    false    205            �           0    0    despesa_desp_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.despesa_desp_codigo_seq', 16, true);
          public          postgres    false    207            �           0    0    endereco_ender_codigo_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.endereco_ender_codigo_seq', 20, true);
          public          postgres    false    209            �           0    0    fornecedor_forn_codigo_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.fornecedor_forn_codigo_seq', 9, true);
          public          postgres    false    211            �           0    0    modelo_modelo_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.modelo_modelo_codigo_seq', 1, true);
          public          postgres    false    214            �           0    0    orcamento_orc_codigo_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.orcamento_orc_codigo_seq', 20, true);
          public          postgres    false    216            �           0    0    ordem_de_servico_os_codigo_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.ordem_de_servico_os_codigo_seq', 1, false);
          public          postgres    false    239            �           0    0    pagamento_pag_codigo_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.pagamento_pag_codigo_seq', 37, true);
          public          postgres    false    232            �           0    0 %   parcela_compra_parc_compra_codigo_seq    SEQUENCE SET     T   SELECT pg_catalog.setval('public.parcela_compra_parc_compra_codigo_seq', 53, true);
          public          postgres    false    218            �           0    0    produto_prod_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.produto_prod_codigo_seq', 80, true);
          public          postgres    false    221            �           0    0    produtos_os_prod_os_codigo_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.produtos_os_prod_os_codigo_seq', 1, false);
          public          postgres    false    241            �           0    0 %   registro_pagamento_reg_pag_codigo_seq    SEQUENCE SET     T   SELECT pg_catalog.setval('public.registro_pagamento_reg_pag_codigo_seq', 27, true);
          public          postgres    false    234            �           0    0    servico_serv_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.servico_serv_codigo_seq', 49, true);
          public          postgres    false    224            �           0    0    servicos_os_serv_os_codigo_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.servicos_os_serv_os_codigo_seq', 1, false);
          public          postgres    false    243            �           0    0    telefone_tel_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.telefone_tel_codigo_seq', 51, true);
          public          postgres    false    227            �           0    0    usuario_os_user_os_codigo_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.usuario_os_user_os_codigo_seq', 1, false);
          public          postgres    false    245            �           0    0    usuario_user_codigo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.usuario_user_codigo_seq', 7, true);
          public          postgres    false    229            �           0    0    veiculo_vei_codigo_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.veiculo_vei_codigo_seq', 5, true);
          public          postgres    false    231            �           2606    16498 $   acessos acessos_acess_data_login_key 
   CONSTRAINT     k   ALTER TABLE ONLY public.acessos
    ADD CONSTRAINT acessos_acess_data_login_key UNIQUE (acess_data_login);
 N   ALTER TABLE ONLY public.acessos DROP CONSTRAINT acessos_acess_data_login_key;
       public            postgres    false    200            �           2606    16500 %   acessos acessos_acess_data_logout_key 
   CONSTRAINT     m   ALTER TABLE ONLY public.acessos
    ADD CONSTRAINT acessos_acess_data_logout_key UNIQUE (acess_data_logout);
 O   ALTER TABLE ONLY public.acessos DROP CONSTRAINT acessos_acess_data_logout_key;
       public            postgres    false    200            �           2606    16502    cliente cliente_cli_cpf_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_cli_cpf_key UNIQUE (cli_cpf);
 E   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_cli_cpf_key;
       public            postgres    false    202            �           2606    16504    marca marca_marca_nome_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.marca
    ADD CONSTRAINT marca_marca_nome_key UNIQUE (marca_nome);
 D   ALTER TABLE ONLY public.marca DROP CONSTRAINT marca_marca_nome_key;
       public            postgres    false    212            �           2606    16506    acessos pk_acess_codigo 
   CONSTRAINT     _   ALTER TABLE ONLY public.acessos
    ADD CONSTRAINT pk_acess_codigo PRIMARY KEY (acess_codigo);
 A   ALTER TABLE ONLY public.acessos DROP CONSTRAINT pk_acess_codigo;
       public            postgres    false    200            �           2606    16508    cliente pk_cli_codigo 
   CONSTRAINT     [   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT pk_cli_codigo PRIMARY KEY (cli_codigo);
 ?   ALTER TABLE ONLY public.cliente DROP CONSTRAINT pk_cli_codigo;
       public            postgres    false    202            �           2606    16510    compra pk_comp_codigo 
   CONSTRAINT     \   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT pk_comp_codigo PRIMARY KEY (comp_codigo);
 ?   ALTER TABLE ONLY public.compra DROP CONSTRAINT pk_comp_codigo;
       public            postgres    false    204            �           2606    16512    despesa pk_desp_codigo 
   CONSTRAINT     ]   ALTER TABLE ONLY public.despesa
    ADD CONSTRAINT pk_desp_codigo PRIMARY KEY (desp_codigo);
 @   ALTER TABLE ONLY public.despesa DROP CONSTRAINT pk_desp_codigo;
       public            postgres    false    206            �           2606    16514    endereco pk_ender_codigo 
   CONSTRAINT     `   ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT pk_ender_codigo PRIMARY KEY (ender_codigo);
 B   ALTER TABLE ONLY public.endereco DROP CONSTRAINT pk_ender_codigo;
       public            postgres    false    208            �           2606    16516    fornecedor pk_forn_codigo 
   CONSTRAINT     `   ALTER TABLE ONLY public.fornecedor
    ADD CONSTRAINT pk_forn_codigo PRIMARY KEY (forn_codigo);
 C   ALTER TABLE ONLY public.fornecedor DROP CONSTRAINT pk_forn_codigo;
       public            postgres    false    210            �           2606    16518    marca pk_marca_codigo 
   CONSTRAINT     ]   ALTER TABLE ONLY public.marca
    ADD CONSTRAINT pk_marca_codigo PRIMARY KEY (marca_codigo);
 ?   ALTER TABLE ONLY public.marca DROP CONSTRAINT pk_marca_codigo;
       public            postgres    false    212            �           2606    16520    modelo pk_modelo_codigo 
   CONSTRAINT     `   ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT pk_modelo_codigo PRIMARY KEY (modelo_codigo);
 A   ALTER TABLE ONLY public.modelo DROP CONSTRAINT pk_modelo_codigo;
       public            postgres    false    213            �           2606    16522    orcamento pk_orc_codigo 
   CONSTRAINT     ]   ALTER TABLE ONLY public.orcamento
    ADD CONSTRAINT pk_orc_codigo PRIMARY KEY (orc_codigo);
 A   ALTER TABLE ONLY public.orcamento DROP CONSTRAINT pk_orc_codigo;
       public            postgres    false    215            �           2606    24843    ordem_de_servico pk_os_codigo 
   CONSTRAINT     b   ALTER TABLE ONLY public.ordem_de_servico
    ADD CONSTRAINT pk_os_codigo PRIMARY KEY (os_codigo);
 G   ALTER TABLE ONLY public.ordem_de_servico DROP CONSTRAINT pk_os_codigo;
       public            postgres    false    240            �           2606    24616    pagamento pk_pagamento 
   CONSTRAINT     \   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT pk_pagamento PRIMARY KEY (pag_codigo);
 @   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT pk_pagamento;
       public            postgres    false    233            �           2606    24684    parametrizacao pk_parametriza 
   CONSTRAINT     b   ALTER TABLE ONLY public.parametrizacao
    ADD CONSTRAINT pk_parametriza PRIMARY KEY (para_nome);
 G   ALTER TABLE ONLY public.parametrizacao DROP CONSTRAINT pk_parametriza;
       public            postgres    false    236            �           2606    16526    parcela_compra pk_parc_compra 
   CONSTRAINT     k   ALTER TABLE ONLY public.parcela_compra
    ADD CONSTRAINT pk_parc_compra PRIMARY KEY (parc_compra_codigo);
 G   ALTER TABLE ONLY public.parcela_compra DROP CONSTRAINT pk_parc_compra;
       public            postgres    false    217            �           2606    16530    produto pk_prod_codigo 
   CONSTRAINT     ]   ALTER TABLE ONLY public.produto
    ADD CONSTRAINT pk_prod_codigo PRIMARY KEY (prod_codigo);
 @   ALTER TABLE ONLY public.produto DROP CONSTRAINT pk_prod_codigo;
       public            postgres    false    219            �           2606    16532    produto_compra pk_prod_compra 
   CONSTRAINT     �   ALTER TABLE ONLY public.produto_compra
    ADD CONSTRAINT pk_prod_compra PRIMARY KEY (comp_codigo, prod_codigo, prod_comp_valor);
 G   ALTER TABLE ONLY public.produto_compra DROP CONSTRAINT pk_prod_compra;
       public            postgres    false    220    220    220            �           2606    24749    produtos_orcamento pk_prod_orc 
   CONSTRAINT     �   ALTER TABLE ONLY public.produtos_orcamento
    ADD CONSTRAINT pk_prod_orc PRIMARY KEY (orc_codigo, prod_codigo, prod_orc_preco);
 H   ALTER TABLE ONLY public.produtos_orcamento DROP CONSTRAINT pk_prod_orc;
       public            postgres    false    222    222    222            �           2606    24856    produtos_os pk_prod_os_codigo 
   CONSTRAINT        ALTER TABLE ONLY public.produtos_os
    ADD CONSTRAINT pk_prod_os_codigo PRIMARY KEY (os_codigo, prod_codigo, prod_os_codigo);
 G   ALTER TABLE ONLY public.produtos_os DROP CONSTRAINT pk_prod_os_codigo;
       public            postgres    false    242    242    242            �           2606    24673 #   registro_pagamento pk_reg_pagamento 
   CONSTRAINT     m   ALTER TABLE ONLY public.registro_pagamento
    ADD CONSTRAINT pk_reg_pagamento PRIMARY KEY (reg_pag_codigo);
 M   ALTER TABLE ONLY public.registro_pagamento DROP CONSTRAINT pk_reg_pagamento;
       public            postgres    false    235            �           2606    16534    servico pk_serv_codigo 
   CONSTRAINT     ]   ALTER TABLE ONLY public.servico
    ADD CONSTRAINT pk_serv_codigo PRIMARY KEY (serv_codigo);
 @   ALTER TABLE ONLY public.servico DROP CONSTRAINT pk_serv_codigo;
       public            postgres    false    223            �           2606    16536    servicos_orcamento pk_serv_orc 
   CONSTRAINT     q   ALTER TABLE ONLY public.servicos_orcamento
    ADD CONSTRAINT pk_serv_orc PRIMARY KEY (orc_codigo, serv_codigo);
 H   ALTER TABLE ONLY public.servicos_orcamento DROP CONSTRAINT pk_serv_orc;
       public            postgres    false    225    225                       2606    24874    servicos_os pk_serv_os_codigo 
   CONSTRAINT        ALTER TABLE ONLY public.servicos_os
    ADD CONSTRAINT pk_serv_os_codigo PRIMARY KEY (os_codigo, serv_codigo, serv_os_codigo);
 G   ALTER TABLE ONLY public.servicos_os DROP CONSTRAINT pk_serv_os_codigo;
       public            postgres    false    244    244    244            �           2606    16538    telefone pk_tel_codigo 
   CONSTRAINT     \   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT pk_tel_codigo PRIMARY KEY (tel_codigo);
 @   ALTER TABLE ONLY public.telefone DROP CONSTRAINT pk_tel_codigo;
       public            postgres    false    226            �           2606    16540    usuario pk_user_codigo 
   CONSTRAINT     ]   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT pk_user_codigo PRIMARY KEY (user_codigo);
 @   ALTER TABLE ONLY public.usuario DROP CONSTRAINT pk_user_codigo;
       public            postgres    false    228                       2606    24892    usuario_os pk_user_os_codigo 
   CONSTRAINT     ~   ALTER TABLE ONLY public.usuario_os
    ADD CONSTRAINT pk_user_os_codigo PRIMARY KEY (os_codigo, user_codigo, user_os_codigo);
 F   ALTER TABLE ONLY public.usuario_os DROP CONSTRAINT pk_user_os_codigo;
       public            postgres    false    246    246    246            �           2606    16542    veiculo pk_vei_placa 
   CONSTRAINT     Y   ALTER TABLE ONLY public.veiculo
    ADD CONSTRAINT pk_vei_placa PRIMARY KEY (vei_placa);
 >   ALTER TABLE ONLY public.veiculo DROP CONSTRAINT pk_vei_placa;
       public            postgres    false    230            �           2606    16544    usuario usuario_user_nome_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_user_nome_key UNIQUE (user_nome);
 G   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_user_nome_key;
       public            postgres    false    228                        2620    24918 %   produtos_orcamento decrementa_estoque    TRIGGER     �   CREATE TRIGGER decrementa_estoque AFTER INSERT ON public.produtos_orcamento FOR EACH ROW EXECUTE FUNCTION public.decrementa_estoque();
 >   DROP TRIGGER decrementa_estoque ON public.produtos_orcamento;
       public          postgres    false    222    248                       2620    24919 %   produtos_orcamento incrementa_estoque    TRIGGER     �   CREATE TRIGGER incrementa_estoque BEFORE DELETE ON public.produtos_orcamento FOR EACH ROW EXECUTE FUNCTION public.incrementa_estoque();
 >   DROP TRIGGER incrementa_estoque ON public.produtos_orcamento;
       public          postgres    false    222    247                       2606    16545    telefone fk_cli_codigo    FK CONSTRAINT     �   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fk_cli_codigo FOREIGN KEY (cli_codigo) REFERENCES public.cliente(cli_codigo) ON DELETE CASCADE;
 @   ALTER TABLE ONLY public.telefone DROP CONSTRAINT fk_cli_codigo;
       public          postgres    false    3025    202    226                       2606    16550    veiculo fk_cli_codigo    FK CONSTRAINT     �   ALTER TABLE ONLY public.veiculo
    ADD CONSTRAINT fk_cli_codigo FOREIGN KEY (cli_codigo) REFERENCES public.cliente(cli_codigo) ON DELETE CASCADE;
 ?   ALTER TABLE ONLY public.veiculo DROP CONSTRAINT fk_cli_codigo;
       public          postgres    false    202    230    3025                       2606    16555    cliente fk_cli_endereco    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fk_cli_endereco FOREIGN KEY (ender_codigo) REFERENCES public.endereco(ender_codigo) ON DELETE CASCADE;
 A   ALTER TABLE ONLY public.cliente DROP CONSTRAINT fk_cli_endereco;
       public          postgres    false    202    208    3031                       2606    16560    compra fk_compra_fornecedor    FK CONSTRAINT     �   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT fk_compra_fornecedor FOREIGN KEY (forn_codigo) REFERENCES public.fornecedor(forn_codigo);
 E   ALTER TABLE ONLY public.compra DROP CONSTRAINT fk_compra_fornecedor;
       public          postgres    false    3033    204    210                       2606    24693 -   endereco_parametrizacao fk_enderpara_endereco    FK CONSTRAINT     �   ALTER TABLE ONLY public.endereco_parametrizacao
    ADD CONSTRAINT fk_enderpara_endereco FOREIGN KEY (ender_codigo) REFERENCES public.endereco(ender_codigo);
 W   ALTER TABLE ONLY public.endereco_parametrizacao DROP CONSTRAINT fk_enderpara_endereco;
       public          postgres    false    3031    237    208                       2606    24703 3   endereco_parametrizacao fk_enderpara_parametrizacao    FK CONSTRAINT     �   ALTER TABLE ONLY public.endereco_parametrizacao
    ADD CONSTRAINT fk_enderpara_parametrizacao FOREIGN KEY (para_nome) REFERENCES public.parametrizacao(para_nome) ON UPDATE CASCADE;
 ]   ALTER TABLE ONLY public.endereco_parametrizacao DROP CONSTRAINT fk_enderpara_parametrizacao;
       public          postgres    false    3067    236    237                       2606    16565    telefone fk_forn_codigo    FK CONSTRAINT     �   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fk_forn_codigo FOREIGN KEY (forn_codigo) REFERENCES public.fornecedor(forn_codigo) ON DELETE CASCADE;
 A   ALTER TABLE ONLY public.telefone DROP CONSTRAINT fk_forn_codigo;
       public          postgres    false    226    210    3033                       2606    16570    fornecedor fk_forn_endereco    FK CONSTRAINT     �   ALTER TABLE ONLY public.fornecedor
    ADD CONSTRAINT fk_forn_endereco FOREIGN KEY (ender_codigo) REFERENCES public.endereco(ender_codigo) ON DELETE CASCADE;
 E   ALTER TABLE ONLY public.fornecedor DROP CONSTRAINT fk_forn_endereco;
       public          postgres    false    210    208    3031                       2606    16575    modelo fk_marca_codigo    FK CONSTRAINT     �   ALTER TABLE ONLY public.modelo
    ADD CONSTRAINT fk_marca_codigo FOREIGN KEY (marca_codigo) REFERENCES public.marca(marca_codigo) ON DELETE CASCADE;
 @   ALTER TABLE ONLY public.modelo DROP CONSTRAINT fk_marca_codigo;
       public          postgres    false    212    3037    213                       2606    16580     produtos_orcamento fk_orc_codigo    FK CONSTRAINT     �   ALTER TABLE ONLY public.produtos_orcamento
    ADD CONSTRAINT fk_orc_codigo FOREIGN KEY (orc_codigo) REFERENCES public.orcamento(orc_codigo) ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.produtos_orcamento DROP CONSTRAINT fk_orc_codigo;
       public          postgres    false    215    3041    222                       2606    16585     servicos_orcamento fk_orc_codigo    FK CONSTRAINT     �   ALTER TABLE ONLY public.servicos_orcamento
    ADD CONSTRAINT fk_orc_codigo FOREIGN KEY (orc_codigo) REFERENCES public.orcamento(orc_codigo) ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.servicos_orcamento DROP CONSTRAINT fk_orc_codigo;
       public          postgres    false    225    215    3041                       2606    24844     ordem_de_servico fk_os_orcamento    FK CONSTRAINT     �   ALTER TABLE ONLY public.ordem_de_servico
    ADD CONSTRAINT fk_os_orcamento FOREIGN KEY (orc_codigo) REFERENCES public.orcamento(orc_codigo) ON UPDATE CASCADE ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.ordem_de_servico DROP CONSTRAINT fk_os_orcamento;
       public          postgres    false    215    240    3041                       2606    24622    pagamento fk_pagamento_despesa    FK CONSTRAINT     �   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT fk_pagamento_despesa FOREIGN KEY (desp_codigo) REFERENCES public.despesa(desp_codigo);
 H   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT fk_pagamento_despesa;
       public          postgres    false    206    233    3029                       2606    24617    pagamento fk_pagamento_parcela    FK CONSTRAINT     �   ALTER TABLE ONLY public.pagamento
    ADD CONSTRAINT fk_pagamento_parcela FOREIGN KEY (parc_codigo) REFERENCES public.parcela_compra(parc_compra_codigo);
 H   ALTER TABLE ONLY public.pagamento DROP CONSTRAINT fk_pagamento_parcela;
       public          postgres    false    3043    217    233            	           2606    16600    parcela_compra fk_parc_compra    FK CONSTRAINT     �   ALTER TABLE ONLY public.parcela_compra
    ADD CONSTRAINT fk_parc_compra FOREIGN KEY (parc_compra_compra_cod) REFERENCES public.compra(comp_codigo) ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.parcela_compra DROP CONSTRAINT fk_parc_compra;
       public          postgres    false    3027    217    204            
           2606    16605 %   produto_compra fk_prdo_compra_produto    FK CONSTRAINT     �   ALTER TABLE ONLY public.produto_compra
    ADD CONSTRAINT fk_prdo_compra_produto FOREIGN KEY (prod_codigo) REFERENCES public.produto(prod_codigo) ON DELETE CASCADE;
 O   ALTER TABLE ONLY public.produto_compra DROP CONSTRAINT fk_prdo_compra_produto;
       public          postgres    false    219    220    3045                       2606    16610 !   produtos_orcamento fk_prod_codigo    FK CONSTRAINT     �   ALTER TABLE ONLY public.produtos_orcamento
    ADD CONSTRAINT fk_prod_codigo FOREIGN KEY (prod_codigo) REFERENCES public.produto(prod_codigo) ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.produtos_orcamento DROP CONSTRAINT fk_prod_codigo;
       public          postgres    false    219    222    3045                       2606    16615 $   produto_compra fk_prod_compra_compra    FK CONSTRAINT     �   ALTER TABLE ONLY public.produto_compra
    ADD CONSTRAINT fk_prod_compra_compra FOREIGN KEY (comp_codigo) REFERENCES public.compra(comp_codigo) ON DELETE CASCADE;
 N   ALTER TABLE ONLY public.produto_compra DROP CONSTRAINT fk_prod_compra_compra;
       public          postgres    false    204    220    3027                       2606    24857 '   produtos_os fk_prod_os_ordem_de_servico    FK CONSTRAINT     �   ALTER TABLE ONLY public.produtos_os
    ADD CONSTRAINT fk_prod_os_ordem_de_servico FOREIGN KEY (os_codigo) REFERENCES public.ordem_de_servico(os_codigo) ON UPDATE CASCADE ON DELETE CASCADE;
 Q   ALTER TABLE ONLY public.produtos_os DROP CONSTRAINT fk_prod_os_ordem_de_servico;
       public          postgres    false    242    240    3069                       2606    24862    produtos_os fk_prod_os_produtos    FK CONSTRAINT     �   ALTER TABLE ONLY public.produtos_os
    ADD CONSTRAINT fk_prod_os_produtos FOREIGN KEY (prod_codigo) REFERENCES public.produto(prod_codigo) ON UPDATE CASCADE ON DELETE CASCADE;
 I   ALTER TABLE ONLY public.produtos_os DROP CONSTRAINT fk_prod_os_produtos;
       public          postgres    false    242    3045    219                       2606    16620 !   servicos_orcamento fk_serv_codigo    FK CONSTRAINT     �   ALTER TABLE ONLY public.servicos_orcamento
    ADD CONSTRAINT fk_serv_codigo FOREIGN KEY (serv_codigo) REFERENCES public.servico(serv_codigo) ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.servicos_orcamento DROP CONSTRAINT fk_serv_codigo;
       public          postgres    false    225    223    3051                       2606    24875 '   servicos_os fk_serv_os_ordem_de_servico    FK CONSTRAINT     �   ALTER TABLE ONLY public.servicos_os
    ADD CONSTRAINT fk_serv_os_ordem_de_servico FOREIGN KEY (os_codigo) REFERENCES public.ordem_de_servico(os_codigo) ON UPDATE CASCADE ON DELETE CASCADE;
 Q   ALTER TABLE ONLY public.servicos_os DROP CONSTRAINT fk_serv_os_ordem_de_servico;
       public          postgres    false    240    3069    244                       2606    24880    servicos_os fk_serv_os_servicos    FK CONSTRAINT     �   ALTER TABLE ONLY public.servicos_os
    ADD CONSTRAINT fk_serv_os_servicos FOREIGN KEY (serv_codigo) REFERENCES public.servico(serv_codigo) ON UPDATE CASCADE ON DELETE CASCADE;
 I   ALTER TABLE ONLY public.servicos_os DROP CONSTRAINT fk_serv_os_servicos;
       public          postgres    false    3051    223    244                       2606    24708 #   telefone fk_telefone_parametrizacao    FK CONSTRAINT     �   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fk_telefone_parametrizacao FOREIGN KEY (para_nome) REFERENCES public.parametrizacao(para_nome) ON UPDATE CASCADE;
 M   ALTER TABLE ONLY public.telefone DROP CONSTRAINT fk_telefone_parametrizacao;
       public          postgres    false    3067    226    236                       2606    16625    acessos fk_user_codigo    FK CONSTRAINT     �   ALTER TABLE ONLY public.acessos
    ADD CONSTRAINT fk_user_codigo FOREIGN KEY (user_codigo) REFERENCES public.usuario(user_codigo) ON DELETE CASCADE;
 @   ALTER TABLE ONLY public.acessos DROP CONSTRAINT fk_user_codigo;
       public          postgres    false    200    228    3057                       2606    24893 &   usuario_os fk_user_os_ordem_de_servico    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_os
    ADD CONSTRAINT fk_user_os_ordem_de_servico FOREIGN KEY (os_codigo) REFERENCES public.ordem_de_servico(os_codigo) ON UPDATE CASCADE ON DELETE CASCADE;
 P   ALTER TABLE ONLY public.usuario_os DROP CONSTRAINT fk_user_os_ordem_de_servico;
       public          postgres    false    246    240    3069                       2606    24898    usuario_os fk_user_os_usuario    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_os
    ADD CONSTRAINT fk_user_os_usuario FOREIGN KEY (user_codigo) REFERENCES public.usuario(user_codigo) ON UPDATE CASCADE ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.usuario_os DROP CONSTRAINT fk_user_os_usuario;
       public          postgres    false    246    3057    228            �   �  x�mYۑ3�
|��(6�U	�8�����3�U~�.F�����j��R��i�2�VdI½�l�����k��RG��U>W�	d�������Uk��`�/ŧtB�>���x���wy�!#��O�����)�_.�Ԁ�[k������,s�>_�i�!��~��j���n��K�h�f�4d�&ܔ&��s���>���A�&m 5�N%��9��AN��<H�`Ч�?p�>r���B�C*���%Rf{���84/�O��a2J��q��ìŭQ�>���ᵂ/}<q���2Ǣ��R�{C�xY�'\��h5�n���KWY�w�QgLt�|�A�O�D��q���o�M(� j�`��݌�k�y-Nz{���;p���8������j&�N�v4DSFN!>3�R��A-���y�G���M3ɘ(�!R
�2���J��J�T�Pe��E�U+�D�/���WT�HԴ!_>��ԨB-�.T�i1��$e��V,"��L"Xki+kg���Z�J���Q�G�5;��Ia�3:��,cJT��HQ~m�;K�E�W=I���	��Dh�m�dk��s<�H�[���C�<�3L�	�̫QM��7F\�:�(�pħ�����[ӻ1,L=���9ڭ���� ��+Ո��D���v�O-i��ʒH^	�]Y�W��-[�o�Z�[u�8z�Xh��l��f��1��"�c�N
S�'��"���v'�Z�đ�-Z��D��K�Y�Ȯ�t62ǲ��q�D�W	3<5��PL��0�ny��V����X�+3ûyXu^�$g��[k���ҍ�b7�E_�B�Y�{�t��Ad�u�d��Hg@4�G&-a��ew�Ҳ��J[;?ICx%�#H}��Db �g��8g�}�w8xL<�|6��3����}f�y%�8�q�'���1�]�r��L�^Tg|ad��3��\	!I$4�4,�lI%�Io��P7?q�Z��]3�8��J0�΢d@&+a�e�|�EAS���$�4F���x��1(�(H�N5��h0������F�Xv☝"��-<�G ����ֈ�8h=��
��7Ν}�x�"Q�Ãv�셣W�0�<��O3��8ԞΜ-�#&��'����DOrEG��ո������?#���
�`��/��$S�R�|E�C�I�meTn��8lZg�0G&�n�����Lb��y�xo���e�Oy���Au����+Vo8���9�fr��Л�8R�w��Uo$�ޮs�Q5�y�1ϋ���x�O���_��.4ĴL.�K�<��<�3ޥ�?��$�.�,���x7�ǹM�.�`11;��k�<��>�a��S�ǀ;f�k�p��Y
8"٣�J�'_��'ˮ6CJ�,�]e���%1���P�qi�ݍ]O�qa{��d����w��Æ�2#�.9q���>�3nQ=�RZ��mÞYvrL&��я��*���Mc�]��=e}[~�,4�_P��ɥ_�:��-�8�Z���~�H����dV���-kȠA���j�m����8���Kz�x�̕�%1��(��Mc�g ����q눌���I��zܚ{�r��ܺ�z�v�hp�l�����r���X��,�#�b]���㬱׎�#�{l�������j��"k�z�8�(ՑZW��P��+��^|r�κ�7�f�R�7>4C�2�i^s���o���^rW�.-�;������Y�TW]����p�Y�Н�N��I/��8�hᵮo7��2�ֻ�=�]���&����ZT����Q]FLZr���Q5_ׅG5�R�}��X3�ם-�Xnkis�8�|���%�Ţ����
�����*'W9q<�n��3�C�|/�xh�=�xֿqO���`t����=n:#[��8,�+<���z������N{t�~����ΘBܸL�R7SM�{���q�Owۡ�ۃm�����]�7�?f�5�>���&���g���S�evЙw/�a\���Q�k�McZV8���k�or"(PY�G,Sזz��dh��(ɸ2�n�}f6��G��x��%�+��`[���%�ȸ���K��,�>M���mR����FË�:\�G�Td|BC�_c��zs�n������z8yE;<�(w1���H��9FP�_p ���	���ׅ�s3����Eڌ�=��q3҅#�G�Y$cd6f�{A�pN�Ə�Y.fa�Cj�O,�W����Ȩ��      �   �   x�]�K
�@�u�)r�i�f��NV��7CD��l<���U�j��qZ��vٖu�������eAd��SJ��xз�P�����0r?�(H����
֥�i*%sF}�ۙ�%�Xs,JRRak�MDQ��3~|��	^�K��J(�b��t�2 ��;�^�9?      �   6   x�-��	  �s��]��{��:Tp.F��݊D>L3�do�W�S)Ʈ�8�	�      �   P   x�S604014�T60022��TvsAΒ�ļ�ĢԼNeCWWSNe3s'NC �@��������W� ��      �   z   x�e�;!��N� !�^�z��		b���A�bb�&���A��C+:�e�g��9�p�妭���;��J�Y�f��9�h�-9hվ���G
օ�v���C�x��co| ���^a�&�      �   s  x���KN�0�דS��켳�@,�"Eu���)FV��E/�A�N���+b)�3�xfp4d%k%4p%���A���\T� ���ܕ�
�&�8��0{��HnКFV5Y�\ˍ�̶�zAX� k���y��zR�T,ɢ�iV.N�v�'�tB��A?�	7�q��Y}K;ԡ�� ����$��bU)����^�̢a1w��Z��n_ѱ�k�jt��3=��[�ɃP������2
ʼ��~%���[WD�������&ƅ)iH�V���ݼk��x4U�p�q-*�Yq$$}}��^#���Zo��<I-??Ny#?�œz�,�w{�X�w3N�~X(��{�C�7�����4�/���o      �      x��O�L��KTpR��4����� 8��      �   M  x�m�MN�0���S�<�8���+6&u%KQ#%�8êB4/��}�aw]��8���O�<Ny�e����gp
�{4��RԴ`V��F�$�(tl1xRBߐ�1]�4ɷ�cJ��Uh�W�U���zԞ�H�$�K�ǔ���i�/�)�"l�E]cq�[D�1#���X�����Gy�C�(���rX�pf�;ʋHWQ� ���yJ�i�}�D���4�:kP��Zծ�pO�E�QYd����\8�Xf�C��3hڵ=���Ȋ�]�����ϲe"�ڠ3���Tۮ���r������8��h8���:6���xG!�7O0��      �   �  x�%�]��*���*�����ߏ�M&ؤ '����u܏̓����r>��۝��/�|$P%��ܾDeѰH6j.{:��A�I�x �.%:�f����jc��!�f�|�7�"���\��_dZ��DK����ie1:ʹ��q�ʅ,���`�ݾ�e��ew)9_d:Vͥ��,�/i���,/�P�UU�j�W����-U#�c#w�J��<����%��W�\6����M!����,�j\��λ�!�t�n^�SW�I��%�N��h��\C\̥�Hr�LA'6-��Z��}/J4�������-�c�����$�̷绯�� 9�c{��������>���۳������%鎤m+�.��reۉn�Ֆx_�^a�l���N�i��*b�]�w��Xt��|DNv�xY�Om�����C���zY��Zo����G�o�>5�6z<�={�6�7%ؾþ��d{�6�i��]R��;dPS:��g��E�
27�ÆWp��ŏ4pI	����]t{�a�f�����A�_��K,��VVu ��7h4�<����=q���Si5V�'z,��5�S��qĂ�\L����5��(#Y�29��`�N��e�Y�諘��������kS��1Kxm�Xן��#>�op�ݏ!��!�`���\܊�梩5�g��b���5�jF�t*��{-$ԾB0mr7�=0-���j�"ʓߍ,��B��LK~�؇h�.���Ӗf�L��{ܝ���^�Hw�+m��zU��,�@����ЫF^��+�ViL7�u�L7�#��a��\w5��PL�7}E���Tђc8x��|z�$�w�j��D��lsF�~�۱$^ɞ0�g�3���P�ۯ��P�9���]�:Fj=�3��m���o�O�F�~��	&����@�pk��j��^H��(���r�z�flXQf�Y3�2v-��Y�������J����n��g���rl      �      x�e\[z�0�|VV�0?�ۏ�(���@��Ҭj66U�m gΗ��-	Y�n���s����#q�k��#u~�Na��f�c�|䮹{A�;���F!�k��<!����G!���q��/�o4����[�j��rn&ŉ���(�������%I�}�9`��pi"w�.KV�׫WR��9� ,ٻ��ms��]��b��㴨!òNa���}�ͲL#��r����o:e�{���#a�R��#)]*�ruR+��a��/�>��C�l$v�}W*N\���Q���zS�����:Ҝ8W\k�i���|��*w��/Hi����l�Ҍ=���D�i��%.�sA�K����(�ǣ1�.��x� �����}䅠�w�A�(,M'��#߻<N�.���<qy��S�׵��h��*챂(��<�i��(�s����(eGʏ��k�_v�~֧T���~o��O�E�>�UE�m)r��)�p��P�nƐ���O�U��7Y��qX���˽���W�<FI��z~���e�0-��(Ť��
sT�-�w��������+���_GŘM��X�8�Yi��*���95��h���(���u��`�P~�'e�����n]�VV��e��V`Ua�O�I[^mݦ���n\<w����]�����Xtl.�ݑn�ϵ�Ԕ������|���Vl�e�Ԃ}mZ�4'�%u�7�桹�B ��m:�����x�v�&�2=zJ���M��e�k<�>���e�+���.6���2J7��q^��T�DW�1�p�9�i����|ٚH��S_p��xj��B��C����r�4����-���r��(Yq����5+& �C�����t)X�2����k�s��8&��a��4N���&���ע����i3{E�P��y6^m<�r4a3�/s�[��:7[,��e]����w��z�(<l8�cД��J� L[=� �n�b�A^�Q��Rm�r�ԛ�2��,�f�ք�<�~Y�ht����B�3dx�x5��:���o�B��Az��D�0�?`�pm��;J�Xjj����c}��Èz��6>��!H��d���B]t-̐���x�/o������O��p&)�Ȓ��`��$�i���f�F*A��M5�N��60 ���v�R�5��@A��P��8��h��-S���,c��t����:q�M�2"��ØD}j��c��7ù��X f��҉�\���Id�0�Y�a�<ʈ`t��!j-�D��X CN#ۿ"̎�M\�f��p߃r���{?�vC��	eP^��.�a�#�U ���P�Ѯ&�NMke�A������%��:Y1���	:��}�b�٬�J�l��D���Y����x*�sE|(4E���wc	�|�����,}`+�V�1ǔe��j���5�ib�0��*���C����p��i	������f8I�F�q3_E�2w�]��.T��^��ۭ�m��ͅ+W+�opj}��6&>�������p-�/!��"k~����J�Ԓf�H.�!�h~��p�x�[.qubW*c���Os�Y+�����C������x3ܧT-X`��YW�	ܯ���%��'���;��p,��Tb�2f�&͕@�?��P��!Ft�t�����sX�G!���~a�o6&��M#�^I�$�lf�h���V�7y���U�O�����OP|ƪ�`��\��(ʁ�j�֓?�4Ի�z .׆A��Q��=/1�я� �ʶ�	��1�0�&p+?�[�A�_��v3��݄�����~Y!�蠿�gw�W�W�G5��cLZ~��y�uP���
��r^�G�)����<���n���F�:���i��?&{�bO�%y�j���jU[R��,�oe���'ǧ�T\}q gP��>j���A��;*��xߎт��0򧊡d�~��C��X�l�Qc��?�E�Bi���b����,�!��C(�N�[[YgNx,Y�&�����y��N�h ��ڸh�.3]�2�k��E��¼��b���$�5I&��e���^W{��Q�HJ�!�W�����[�0�u����S0_D��3=$�S�Z&����@rW��6�'J�i�ӲA�k7!c�EC�)�ڐ�K巆%Ğ� /0��/�p��8{r��pXe�A*��k/�,k;o�8l�q~�A�x���W8~��߂�y����:��q�#�x{1�tό��־geyZ��z:�w�%Xɥ��z�Gs��*���GP�<�R�/�Q���I��}b��)v��X�0�P9��W���4b�kP���
��+*�W�×+��z���u��R)�[�v��H@��*F���9C	QSD��0��'K"R&v�B��ɚb7�r�xA�>
W]F��h�i��4t�EL�Kmȿ�VӟJ����
7��B�%XAp6��FY�p糒l	��_��ik/t�{�N���il���8�s0��㺕�A��,�qզ����%�B^�W�!��)7��і�:��?�xc�ô�8�1!��һd��羚Y)�
s\ V�g
��p]��k�q5OwOS�E�.��01@������|W���3iG�j��{�C��LN-Z��?�JB�-��o�A�ԴLF��x�4��	��Ԇ��!1b��2.v7BІQ1&ځ�()��0���x�:�]���jx�^Y톰��@��#���1���9��7�rV�1���]�#�߁�mm��R��IRtK�dܒկ> eh�E¬	l��^D��{�S[�#0Z^�Z�1ϖA�g��b�S����Rfj���[��F�qn��s�(��<�/0W3�*�3����뚨	�0�pGw+��45�F`�-y����>�!`&0&�Bn�Y*����m)�R9Զm��ZibF��`��ƷWF�(mZF����۞��Z@c���&B�S��0蕌��Щ���<���-� � \�A�Ziv.k�Yk���2��8vG�$��Ѻ�H�	2�?:�.h��i'��ð�Է�L8�r���[��Dݝ��p�^X�|'#A!1B�^� 	���*$u��_�۠��ʡ�L����27�X	T7Pڱ�'���A�G���A\��aR����]�"�k�1��7RQ��)4má������� ��4j*���_!��8蠏3�����M�m7ڭ��Ik-��=�P��>�u��^/�����/�;�:۸�Ť�J� �%�<#o��=�'5p�~Q�=�9�.\I�rc�Λ�(ˁ01.Q�,�GX�.#�ʨ�v�7����ƛ�
���_>�u��e{��o�K��a�R)�3qu��tL��#��W��b�������~�b~Ъ+J1�j�Q���*���[ʾ֚P�[�t�r�X�q+�t�5�
���ZثrHj����Z�)w���9�jf�?e~O[UͬHj�VN鲭���-�]n�@Ns�rZl�P˃�VV��ڬ#�֑Y��}5+�ҝ���U���H�N|����S���-~tZ³�d3 I�Rw�U�$��<\���j�}Ba�i�:��F�R�;s�4ߧYM"�\��Ai�F����VSb&{�4a� �J2%x3�d��t�b��M��s-;�Q	w������T�U+���mi���r��9� ������/���=192 ��8�l��~y�ʥS��� (Čm��n��Zr]Xb,B_�g����	��J�g�^(B\�C�T�2߄�U�}��:h!��x��7WBIV1�=�Z���cК���"�r�=��v��� �s�9=�͹L>2�-��(�b)���*��4���T�<����
�����NY�� a�[��Fe)�#��,7�m.b�J���3�N���:��n�t�j�k�)�a .HݵQ�RR|�L@�(Bl3�f���-��-�N!78z�����%X��+K��|Ir`�2R*�ڬ\A
�a>	�׬��Jq�p��{tB*wj�Ш��o��JDxq�G�H�~7
���{�����Ai���F�i��$�Ya��xZ:O� � �  �_���Sdr����J��_;PB�)0ݚ)�TM��B������!.8V��G'{`�'��^���;���j).�zG��bY�����.�B9�����i�h�V�7�}0�N��m7�d�J-��!�~V�(�"?ꊙ�L�$,!��TS����%s�U@��:	cB���@��:��*w<�
k�ڂp �4��N\�� g��V1;�
S¿wf<9��p
C{�$�2��S!&ۯK�n�v�{;�w��́\��/�o1'�~��cPe\A4��I^������1��{3f�]��DW�
�Ċ��#;ɥ������a� �M��U�s�E�ZP���,����.G��{O.6m:yA�O]ho5]�nRK%�W�5����I�R�Y)B�.��2
�,Ie�;W��o{�p�=V�S��D��+���7d�[�W�i֊6ϙ��JH�4k+���\(��g1A��_��~1z��oK��ַ%����mɜ�[O����$��i�05�]�d���]���KS%�YI���-P�e����\N,�ew�Vƣ_��Xa���'�a��y\� ��f�y��%�����&�68ha�6Ջ<�g���_\�������;�?SHb���܏7��[���+���#��A=(>�'L]�Y3Wn���+\��P��0X�j��v�݀��FtX)S4	���bE�RE���>����J�dgr�n��9Q��#�Ή�?��r)�K�J���ڙ U���6�~�&C���@���z���Z"���,�U\f)B��t�+�T�V	���sۃ".�q�1��u���K�	���1a�|w���[�d�l3�"4�6���'����ٙ�p��e37����tX�)d��uq�8����(4��(�6�������
:z]�f'f�F[S�y�'tj�%oy���s�C�p�7I~���V<�1N!���jЗ�P&�ZI�0�)����� !�0R��5�"���vܝʃK�l��V�SW	��Ҷ�.���&�<����y.C+�tv��SZK�8Ɛ7� 0�a�G��l�N�]�(�1G��	���
	���{�� ��N	�3X���F�b��	79�'4�ӑ���0Č��w.�s�sQZ�v����=���^{�G���)�ٱ��:�����$ѭ�_m��'tG����%����jϮ�`ȘFz,;�'�x��W1��0���d�I�ޟK�5V�b�a�I��o�]:l�'s٤�A���ݽ��Ƣ���+FPXF�����'+&��b7h�
��]ΟAN��%����D��b�*�V��q�N�d䅕��[�APR��N��Bx�r����jcJ�5�J�T�b�\��$W�~�p�X>(.���"���`YR���DS2 �)kEr��(�������FB���B�'�W/���/�<Q�_���aC���t�'��杤�:q-���^賅��g��g���6%�a���ʹ��E��0�������&�	cMV��#,�u��^S���x<*߃�u;�9d���4��sӻ�G��WZ8f<ʬD��aRFXƃ���-_-�i�n���g�ZhE��;(����[!�SC�Z�{���v�A���e��X¯��r�Bxx�����!X+��	̅	�i%������Z�ZN�Do�S�ըG��&'k?o�s���{�	�Z�9<&SB
���p������z��'O�e�wb2W(Ot���6�&�Qd��ُ7����zUyJua�UX�|["���\���0?z�Gz�&X���ka�������o��3��
E�+��-��V`ƣ�.�L$���0S��$L����;�B����Õ`�<�#$q�-2��6	�@���zK��<�v���0k|c�O\o�ݘ�!a\�iI���)�>��"��|���(�\���T�����V\�nV���f�߷n��[+�{s�$����#�n]^�q�60	����<��.۷�˕�15�����TZs��oNx��`.�(�Azw�)��Ma�`�v!7w~��`��.��0+)�W臇��]|PH-�V"����'�h�`!c��a6����C7�3��vHP)�ؚ��˒��c���J#�{Ҽ\N^3�!��6w,��xS')g�X��]������ ��"�)�X0_,�'1#fD�"���#?�ً���dm�&���A��c�it_�^�l�j���6S�&dQ7��"MW�S�BJ1�W]�򧢚��U��R�~��G�������ж�"��R�&i�1Q|ׇ�c)7k7@T��6zz�����m �D��c��b�o�������E���9!B�{k�����~ O�	{n���ܬ7���/�Vb}(���pA�`��Rq�FqN����nv��^w���9�!f6V{k0��n�U@�W��2����^��u����{b��ۢ?����y	�2+b2�m��ČR���lSV�Q�b,�0���
�~��$;��b�)et����jjFG���踂�����!���N�j��|}6X�?\A{�f_���](�G(�G���Xj������j���v{g�;g�8ᖰ�R��?yx�Q��Z�6O)?Y���{�K&�9E����K�4�������/�z�+٢ZN���rIˡ�	��Ku�<�Ԏ�Qz9W*�'��Tp�`E@A�>k�p���[bz�{�v7b�Rq���C�ݯb�#� (C�zvO�7.JsLw��k��n,���&z�%Z~㪁_Xx���cw�[��f�t�Pf~>}t\��2�(��sI�0�#�p���(-"�P
��=}��Ĥ[N2\�y�8t��u��8?I�e�Q�"�p�X�0��t�ן�֕p`c���*~�h��qӏ����>��mP�3�<J&��D��*~L5W��f��p2^l�O�Q��o�8��Z��k+�6C�ʯ��n�����`i��n�	=���K��/�7ʸh�DAi�7�����x��f45��?{6K����¾�����ʗ��L��t1�����w*g���X�%���Wj��f*���g�0��sh/6_���R���"�]��?*�R�	��
WU��f�G9�h�Č�)jI:^���ٿ��i��4�֜�Vx�Yy���<���F���a�g�P�����P���������^|=�O��#�C�~�s�)K�Y�l	�,����[8�VտARG%ܵ�9yУ	����3��ތ���7�����kH��')1fI%n߹�)�˗Af��;�h����a�W�C�ODo�;�<P�	�W�5�t7W�Ldϧ��S٧Q���iG$Tl�p�- �B+j�f���8�R�Vc�|	~�٩����ڈ��n1�)}�z���e{��r7�I|�5,�F8�ZW���Xz�A}2�5$�.��|=?�;9���qb~y��	�������ck      �      x�3��4�H,JL��22@�c���� h��      �      x������ � �      �      x������ � �      �      x��ɖ$9�e����U����}�� `�����?�Ex՝C�G1w3SSaaD��������������������}�?��������o�{]����|���:~_���C˥���}��k����]��yO��n5�ߕz�q߫���p�{��.|��ꍛ+�zŝB\���+��w�ū�=�Rs�|�]�����c���sg�Sj*�MW~���~s���jϫ䛿�^J�'ƴӊ|Vm�s��~��;����{�g^��w����x���?^��ߑ���Wi�-W��v=�I�@J��n�x�+�|/���_��w��yd}��?���W���+j�����l�{�5%��?{���|�?٩z~�Uy���Vw'=�������~��ߩ�3�Fm<�{����6�=��sV�auxů��OcM��Ϛ�v\ۿ���\��+���;���޿r�TsQb����ۯ�HW֊�l�V�Y�������瓽zvm���Z����!�gc�פ�Ց˒��+�rΘ�FR|�x��R=��ɝ���68��y���SM-���������g$߽2_���jzrD4b��|r�����S-�:%�/>7��Ͻ����K+��g�#�7��?����~������%�M�������%����/���>�������w�B�~~�W4~Qϳď��j�O�Ȥ�(��?�����������������_���ݯq-~��7���_[S���
QͿ����?�I��g��Y�y�W���s��{j���"�Ҽ�s�W� �'�ʼ����秾�����/תz=���u?�.���>o���������;�~<�<W�F8�wn���@s-]�T
{���A7�๋54�@���"�f�y�1�!	5����X��rO#��'ׂܴ��7x	�>*�z�<��y�����'?����g���{s������O��r^�Y��<{8"sV�ē��	\F�ȹa����/A�
��+�z  �9�ss��J`[P-� (�?��^��W?��Ej����n#_�~�CȖ[����n��o�n����g��>K?�}�:���/�{���?��5��{���^�T���ئ?~Uۻ{z�,��{Tނ��s>|�o�������[�o����?~u�g2ZM
ǻ|U�ޞ��y��w6���
����}�+�M���*i`��ҿ�K~�k����ϥ��Q���_z����}��}���߷~����_z����}/��G�K����߷~��}�}/��G�K����_z����}��}���߷~����_z����}/��G�K����߷~��}�}/��G�K����_z���?��_z�}���߷~���G�K��]���_�޿��ң勵��{�/=�ݾu�99ޭ�뱗j����w����>q��}���R,qo[�]k�w�2�+�Үu��ϳw+#�<k^Oϵ���u�+>��!�g�Z��1���6���{��ˈO\m��7oK�.{�1�+��֚�Q�^}E���R�+��)w��M�J]�z���3G�v͞9�Z{l=���-ګ������J�Xz{�s��7��K�R}G��Y�]�}#��ha�x�'�c�s��xKk7����ݡM��-��=����&1��|c�}ϼ���c�;��gg�˽���5n~����>�	�kpC�;ǻ�=�V�-��~
˹ߜ�.����X�6�u����9������ɒ��r<���s{�TC����B�+swx�ȫ���̾s5s�O
��G��Aɘ��魽�����TӘ-�4��^C�vf��_������-o�����bl��dD'ߙ5��;�5�x˳����|k�	���W�y �k����uc�����3���W��y���y(��A����:K��5��َ�<+[}�׽���-v�e�k��$����.9��s�5��|�R]��5��ޅ-׻S�t������1���Ns�a�w�a����Ad��ۦ;�R!<����S�Oi7��8)���a�81��8�@#���ۋ�ԫ���J��BݛOX\Qf/d`!w���������>��<�/���'�="*+/�?g�{�$6i���b{�����Pu�YN{�H"�z=퓟{י�\< ������8��� W^���y���oG� Cb��deܝ��yC����{�ɾ�h���>�,���.����5jj�c8R�I���U�x9Ԭx}<�J� �fŚ����*|��Sݻ���S��(!�5]=�1��Y3��6�oEQU>k>�aL+���h���4�Z[���"�~�݌��r����H��cC�#�.߻߱��]@ �,�,c�y�1(��湯̟��UR��{�W�w����ű�)W���$O���0��
�w{�-����� <9�-�{�������s9w��D��3
�y��-�Y��U{�Á����r.��k[�Kh�0;Zr�mu��y�s�����5N.��_�%Xk�H
E������o�.�`��L��'��G����N��P����X���V4(���$��92͟3z�
z(ele�a��4�&Gg�|�{Q�W�7T���Wcq���o������?{������xwgG��1C/�k_|@������N_��Z�z�������ĥ!&�./�fAG <g���>��{�İP���a3����G/4�sQ�,ұ(O၄;o�$:'>``��K�@����9J[X���}�͇�o��F��`���6n��	����j�A�$5x���
�;���y@?�X�΄�`�^�!u����ٰ,z:&,5���X���IX/V%��Y�D�7�����\�Z���T0�Ɲh�:����\R�4�v�)Ё�g��	����Ѩ�̢�*�c#^n���3�,!t`-���!;%d]n��
rs�ĺ�<%��z���UO��xZ��P��Ų���{(���u��?���5���kk����)�3,`1Ʀ]@D�MU ��N�H��j�
�L֨��`ݱ�ϱf����p>\�׹+
I�MLTyx$_��gz��*$QY�A�X�4�<� ���
7X�q����R8�@��Vq�� p1\��'��k16�%�wp�07Uk7X��r�^l���+���Q�4(<ۘ+,�u�`ᙳ�!�@�=��2
|��&��+w����"T{ ��\�F���U��3ׅ�7֘a�Y��%K�^-�D����1u^y �qpW�&�[��������@9� :��
�_Xk5+���s�6�3>��"l@������0ѧ�	�|�c��X�f鶘�l�B�g�#c ����{	(��PeP`Gm�mx�к���3�ou$\�F1 q@9��Ptf�6��r9��8��s��b9-gst �����^�@������M.NQd9]���ف�<���L�M$�t���
u�$�M�Tb�����r��[j�Q��G �G�<щ)%s�kU��m@�*Z�tK�"�3r��1���~G��S����� (��G��]���ӑ��7ǁMF��Ġ�P�X��$V �y� �X����!�	!����6�x�����S�`md7BXQ�h������'L;��<���pt������NK�P�(��zQa����U����zyyd�cA�S���
�rp��3(�� ��r��Jф*p�m��aYX��b��=��G0�N��D��~�AyLN.cd'T�#(�������w������OnT��`�6{�d�h���k�y4��k0S�>�����$����{ %���U�CD�8���<����/����wn�������:$vo`��≗� Ta��p�;����uHODY>Ȓ3����w��.y�{�7!Q|w�Z��c�
��G�G�1���k��[HU4��a����HY-u���^�%6��y�>�HFv�p9ϺN/g�!�h��AC�c�3#UD��	}{�&�.7���eN�'�[�B�X���e��FgV�7���c
dY����y��`�Y    ��<�=B|>`� �C7��W���X\?���c;Eԕx�����|�O;l6С�	�| �e��h���(���s�� ���p����n?%P�إ���:�Tp��{���P�������4��F'L.��Gy�1S�U�7�����<���
����A�tr8��T �����,���T�ֽ�	S����[	.-b�_4������dso*��@���@o@�H}�6�A7�Qu��K͆ta�0 *h���#����oF�J������b��ja����F���
�����K!���.��Ĳ�Cd� ��X˖s�X����！�p�b��Q� RhSq)В7����=È؏��N�oA���q�S~�O�<Z�5���O+��s�Js��ey�!�c���剩�������r��B e�-rz>t��]*e��q�1����n�;tJ� �ʴ��ә��Ptu��ɋ���
y�*�����n�	��2��RV����QX�)4�JĶ��*�-�$�:��Hq�� ��0��3��.��quZ���:E7�+a��-FE�E�O_�7
$x �l��-y��/.�Y�.:������B��-��D%:y ���h8\^r���r�P�H��{v�0�Ӱ�٩� ��@ �Gb��A^�`�Lh�q{6���r��N�}�Z@Qѥ(�T��;?.x(Ċ�C8\��8��/.�l�h,nl7�fcO$�
����]N��	����K�M��(�/8�1x! ��b<'�!��q3Z��|��[u���9P�E=`8>��&q/4Q�"����su}�F�>�X.̃�b�m=�O�~Q"nm�88'h�F��A�0)?Vx�~����%+[!��v�
]�������]ŐK�F���ն�p��r�x�T���d4��to	��˾�l�z�o,
�K��9g�4U����bP6�z�;	~F�MǍ*�����=����,+����۰E0a��fP��_bp�e�����2�����0��R�쥷�����\�y��]{`c�K��4P������h\�ϱ��{���_H;�dq� ����g�=�|�G�a\����n�1�ﾍ��7Zd�r����ӿ4��n��̂��&�n��Q���EBY�����;DYq�:���Ň�l ٦oMo�v�;�t�q #h��X�/H]�?��5`�uO���t/U`��>��}a��u�FT!��sv�!���qq��bm���_��ap�qP��F��©ō�ie/P4���\	,�Q��38����	 �2$zЯ��zS��n���CWg�8�Ę?�`�z2~�R���z��[��p�wM�M έ�*n�o:!(Aa�+��$��dq�K9���=��^�2�W@q�	��5�Ϻ`��L"W9�I	0�
ƳhX�2����h�#Y�ӿ[�V��<ST	�'�	 14hGYs ��a�}!=0T\=��	-�l'0�}�r��y�<:/ڵ2��j�w�5 �hL� xV�*pѺ#�@K�;�7;X�FS^�#l��K9����jXt6����"�CV�$s�5���7��=Ҫq�1��H�]�)�U�x�����Э�>�)b0�׃����e4\�2�h/d�M?�vn�/�|��9YA��b���3;��rq�FO�
GcM���+��Q.f����L��v�Ջ�8岃^��`���J�IP���oc�i�3>A�3�b�V:7�������Ò���b� p p�Ơ��ȩX86�n0HۺtS���X��+P�|G�wt���=sw�?z�t\C���xK\z�j�پU C��M��{���:�8Rg�������a�f�e�ֲS3&��P��C79�$|5�q�~۳��w���Fkv���X�Y,�EX��8sU�1<3������S����N1�"��=��'u�c*0Ĩ�](����Q�r2�ˍj��!v�5�/��qfw8<���NC�l�FáWC#^PL�����L�QK����! rA�^��)�)S�ZҞ���>а��F"�SCfv��Em���%;�R|��~Y���z�_�y/��[n���d��x醻yqO�Mw��}g�!0Æ�QWc#�
=�.������f����
R+O8	c[�+a,����xJ���d�怰 j��zu]�:��g6

���|W�(�/���:{C�^�)b �4� ��܆�\�XG�,d�/n��z�d�/֨J� �$����Y1Bp]l��/+�3�䣸����翶��U1ߦ�`�杵 e�Z,v��cy����Y��4p�����F��`��}��5=�Ǜ�5�K���d��ݥqliϣ���EsT?����n&U4A2���P1]'��@#ј�
���A�_�ۃ�X��|a��BKsT���P�HH�#WۺL��уX��A���B��I����g}z!Q�5�i�+c�+��9����y�����r��$�����O�~�� ��o0;��yTzR-ݪ�S!����� $(�q��6�3r��Nu��°@}��jl�>�d"(��cS��=x����ԙX0l$
A�<|X�n�P��kz"�hnX�്��ܶ�����'���I�aۻ�2d~6R�YFA�tN�=s��� �U�Ϋ�x��ӴN����[ ���P7�7�{�C�
'�d�筛��=0à����X2k�^�w�]F�7? ��9u7fD��
��}%������q@���y#�Gb���b��d]ۃn�#�gq�� �S[�)L7��+��K�$� ���XJ��F��c0�r/�(�S�ӂ��m��vk� H����'av���N,�y�G���\K�q\$�[�(}�@��c�����EI۞�~�w[��'����;�����A�f����W�4 r@��S�N\�^�l�"�]u��'G:�������Wu�حd���������nP��p�$�k/�i�^@KU4呥籰�ႀF�� �a9Pm�0�03B��
�;� ��t'�6W R}p,i�-���0�����p�{�#@@(/"�����a*�hKY�e�f���eT�9�,r 5��DtC����_bMxBR�͒����J(h�${Dumo�4��&�Q������6�i��2g�$�lm�����SX^V����%�s��C���-�3'��1W�e�M�K�,tC�.����n7�7�ۉכj8��HY���O�D߲�I/r����桀ѫp?�, ����=�,��)�zW}�L\V��"�0gK'���d@V�l0�Uaq'�-��@�f]hB��eO��5����{~�=b]��^��9
K<I��od��r:��=0E.jR�ɚ�wՅQA��O�7�;`I��Φ�}X�= *�K�.H�3^'Cy��{������G� �a��+6}B���U�F�8�&а��V���sc�b�y�����M�8��̌'���q��V\�w������<$�K���� �W=^W�1�}19�s�u>�ڃ˰��R�������u�Zڲ�O�z�h��L�6�z��	���zS�Zd~~a'`�0��D�o&�ck���'sLP�jf�?VI�b�r2lDC��.@?�a���z"�4���h�4:k�NN��L��+�,��C��0��q�	��Q�oS�\���M�>4��͓N�Sw�)�������~2}��\/��9Ga�R1_�9�'��yc�*�&{;yI�nTK�$s��)��w��|���<�:�E��	��0�]�)����y	(Q��n4c�����51��$�yR�� �$���ΡqK�q�U��p��S�!	��&��A��r����A�U���9Xx
l�Ε��E�����u#�S7���6���غ>�o~M�)��n@�(����m��(��Ǒ1�YX��r��bO�هܮҰN=���+�~�}UEk(��    �`և�����T�Ĝ���-��o]�	�c�ģY�X��ח������� ���4�!��{�e}9�Q0q�v�l !C��ަ�:�]V�o�eB�F���T���-t��]����l�4�7�a�/�f��]���ЙWY�%�4�!�*:�)��P�m�d�^�}b��i��KXLw�t��)�zn��Ƿ�
ua�/�"n)܁w������aЙ�Z�	Ѕ�Q]���Z��
����}���`.�9Q�2.P���M���� TKF����Տ�n�pMX%�e�~tM�'�f��,�epj�Ư��%�sǢW����?�uK>+�\��>�
9]�?�)��uF{=���S����p�A���^����i���V� �2��F���bẃ�Dq�&��+˜]$�pc���G�uѬ	�ݡV�XG-�r`����d>��Ö���a���A�����oA�2�����w��WaB�c�ڨ�F��C��|&��Z+��1���;��2�t�u~Nܦ�݌H��VMK��fx�O����Ĕj��#�]���[�/VK��"�	B4[�oz2̭O�*��-�ӂF�
뎅&`�1����*�6�ZK�
��*p,UQ)@
���K[Y����O��+���Ƃ�8p��|e��԰]�2���o�O}����B����j���J�+f"`��JR���i��aA3";M�a�)X��W���MbJY'�NdʓC{"Z~���EF��h���Ҁ����F[_s��0Y*C����J���EEm%������
݇�;��_{���be	D��躷T˱�Ɨ�v�lzӴZ����(�'v*-�0�����5�ю�P��*/��=�kK�@�q�K����&Ѷ�GG �:�ݖ�/!k�1Rݺ� B�+�L�����î� aUh�Ut-�������w�ϳ�6�?`-���n�>z���eӰ�}�o�1�z�QE��������d�>7�B�y��
��`(�u5��xc��K��P?�t�&c
�`F��2�t*�n#8��;�u)`lӡ�)�	8��بP���p`��[=>V<u4��|1]�`���P���-l>d�̭n~�hN���{ư��	�c����Z|���M6���_k�t?�YѬ��0մ�|��f�X�,�����0��$[�I<E3(�|�〬Z�qRA�`��u��w���*���9�\��Q?����z~Lї��7\�iYuN����"�l5h3Y۶�z.}^p	Kf߈��J��b��NK�)�ǃ2�00�C��ե3mw�i0�p+ ��@�Pu�9���Pi�����<�
���&k'�%!����\�q�_�f?G�)A���δLn��!Ƥg׫Z�lP ,Wъ5OI�VͲEZ2|�f���f��?����NH�Z]�?E�q�f�t�Z�,Hڵ��ҙ�g�>*PuLd֫ߑ�Z!J'���z̄��w�SA���6h��@��n5<�`����B.`�m�[�NA��vI<2�B��َ��L�w\20�����}d���*��n�� �5���L^�?m��OP��F���ɺ��O��s�8���Ѐ�O-| �$��q�����n��E\�R��2��,H�I�_��Q3����6�r�[�U-�6�
C<�	D<Oz���fK~�i<��'����C��MT�������T\'��V.��R��>��stD7�J��6Q�̚#����)"����Yx�â�5���?�`^�gp(<��:�k��	�x�Y<&�!ͦ�١��itSc���&Ǟ0��'��%K

�:_̛��e��;?~s�r,!�f�����V�5�(]c~���=|���Xl��)r�Na�`�	^`�]����n��y�Tq�� T�m������Dc�	������"�� �^�9z�<�d��Q��h��g\	Y�Y��N`&�)I��MPZR�nQhB�=��ֽ��ZR����݄��3�|�<uӖ{�l*w�^����;����Yc9�5ŷ:�xɖ�!�Ҍ�zz�Vk��N�y��=G�sm�ʽ�ˬH��0�rDC�@�{��o����-���K��/�@ͳ+�B�Mz_��Q�V��0�")�UY85��$96Ss%o�)ɜ�su���ilOs ���9�p�e@����i^��1����&	�u
u)p�yM�o ����>���� �J�]5hH���ݴ�{�n�'����)�SqDj�M� 3�9k?17��r��;"�y�V�{�/��0,����FaKtz���S̔����[&Z�[{���jٸ��Pa�0��>�@~�0�r��5qLmh���4o�TN���s�>4��c{�Ƞ��F��2��0�oU\�iӭ�S�����i/s�aqbG}��P&U<�P���"=��݀Q�߷o9>X�b%�z�_���Cָ��|LF{�s�������G��:,i��8T��
�ad(�0�,
�d��o��b��kr1�ɝ��`���a>-��kB��N�=M�����*�Zm�Z�g��i1А0{���J���dB��z�].7��o����؈�y�W���Ԅ�h�f�5n��$��X�����4�x��6jSת��,Q4[�"�oF0��A�g��!�hAS�{5�z�R�|J u�M���%�ؤK�v,�I�������O�iW/��`P��0�l�.uXs��_z�I��9J���dqX}{������˻�V^�U'>��r4w{��&Vl_�T��˯����i�=�1z9TVXX)좿ٜ)��g�� r�l!�I�`ZD=Ռv8T5	"J���G�~4��5��h��h��`�a%���[o�9�8��l`9�!·WB_k�_SO�Γ���8��Ǿ��v��D\�u)ŢY�����6L0M�6+)�ֈ�VK��_{A -�
V���E���6R)�k.S��OD��	�$�<��z���Ft��J�0�-�VBLJx���t0��y{ė��ff�f��}�ѯb�+�¸�+�_Z���R/�Rg}�7���:H
���)WŲ6C�V����N
[G{��a����$e�B�F��!O�� :��9��Z�[�,���"'�f-8d_B�$��3K�& ˶^���>R1�%�n�yN�w9v�Wb�6R�z˘J���8�+�z}�j{������9Q����]��eS>��6�y���,�s����[|�|@@��|g���j��b5������o.��1��籌Ղj�LtMσ�C+�*S�����9?٦kU��.��0X�)�e�-��$ͦ��c���qd����`s�&�u�L5B��ݟ��%���ۊ�A�mĺ�W�'2�,�a ��yF;��{�<O��<<%��I����ke�3ڧ�˜0����C]�5g��Վ�`�yan����"�%��Kg�x��G;�7=n[�Q� �LTv����ؖܘ�l�݁l#Oe5��C�>�rm�����6���Ov����i�Ӥ����Ú��������ܶ�k�g5h�@U7**h�nԥ��r5�2��l�nU(�˫;��S�9�X��h�dʗh��=υ�� ���j�����k~2(�}�x�O�^�9�WY�����'�1��(�(]�M{�	b!�up��=pgs�l+t�֏�fk&J��']g���N!t�"F��r�ݏ�>N��Vݵ�䟀_�µ�Bv*?����=l|��s�~��+�q�J`B0cCQ���_��ٌ�F���v3���x8`�ЭaI�����i�$�=c�cG�^�'	XA�½"1��!,� Smq���YTj�o+);���,ݕ���K�m�[u\�:�~�Ǹ!Bb[ÂiI�	SȪ��N[�5��8��m����$F��ڶ[|MB�L�}���d�ϓ~g9Y����/���|4"����H��sb��U ����p2�q�ly�s�y�i<�!<>��i��)~즾���Z]���(��p�D    ��l+b�@��h���]3N@�����ʏ٨)�*+��5k�ށ�7�sw܋)��K<��y�<m5�K^�l�+��xOC%������ߌ��X@�A-�`�3RC�ռ}t	
�Fq(!�ҋ*f��ݝ�~�^�m ��ꅺ�}�S�ZF�T1����ꢛ�Q�bG�l\���ǟʐ��=#��S�O]�Y��:�M��ݫ��x�� th�E��s"J�+��Y��K����Y3���c��b��0����{��2�`��lh
뙬m��n��I��Ўw�z�@!�o�)Y��6b,:p�v�{l���fg�h_O�N��A*�[��S��?�K=w���oQ�����f�,A4�B���V��켌N�!+Uy�cBKB�f��y���H�>�˶��;�.�;������i�ƼP�`c��X|ls<��^,�SXn���c�F�u��-E�����-�<D��˱�6�����?z�J���k���J�>v������KMC��ez�}�B/6v�^{Avm�mPe?�u���:�j�0��+�!;Oc�N��S�cb<����|0����ۆuZǃ����`8�t�:��50�R��k�S6�k��t�^�ʺ"�����O�rl͓��_�=D~����rozZ-���r�2̡�Jʝ�!���$Q��"rM���9Pt0��̘���zY0$�BN��Ķ�Sp�|������<q���p`�㠻��C�C��K��eaO9�� �S`�56��t����Z�������RMSd:�xm��m/��0��o
+;L���&����Q�Nv7ZJ���1t��
������Z	�,�r���$��5�Np��PY�t�v���� ]F��h�񖭤��J'K��ah��]�Z,۟�c'HS�=�p��GH����f�,�	n�U�&'��V�Y��ͼ��~�7Ea��hE:"�юv�J��O���9d���`q��KܪV� *n4�e�
^WmYq�&�����������>͏�gje_��mKl�|��%G(C(Y0L��q����5O�+�'�c�~ev��t����xP���n��l�9�m,4���1E�U���h�v^���n�OK�����Il��أm~Yr��*=��ֶ�Uaٹ�cl�?���K�u;.	�N��]G�Y�=H��/uM��r�l��:����5^�&�M���9��h�� ���5��7����^G�Q�t8�0R��w��)?��5��}���랗�h���ȩ�Q?p$Z_${�ü�9q\����-�U�:Yw��M�n�6Zl�ם�v��h�-=��	���T޷1�;^�Bd��z��p�a��gX�=X-�ZF��b7� f��ʇ+�F���I�i�>ڂ]N͙�?�O�F[�ng�6��+^�̹�4xa��k5�fC>=���c�_s+����xd�8e���#�`��y�u�C�f�z�'[��_6l�Wm�oƞ�&&\�]q�).�u;r�p͵��P/�:$j�'��l�a���_����@��罻G���]�5�W�S�m���j�P��o�"�;�KRm�KY�9��Z���E�)��/���a�t�I��S�zʷ�(���3)�T�85�y�Ct��%������l'-��I�� !�&w`���Z�|o�VC��6�C ڥ��}��|O�{ثO��6I
��J#i���v�6�@�G���W�o���.6m��&YC��yD��e��n�hvI��,)4)tz���bɣ�l�q�[0�,�B�����ҡ�\��֊m��,6�w_R�$Y�l!��:�y^h�V�ESɚcN".��X��+ò�Mﴍ\����-9yw�S �l��j��-N��h ]e�@X�j�%���erx�A��\�Ӑ�Ǟ���d_����o����ϓ���_�i 0Dʜ��~]��2^̝�1�D�]�.E��kdE�g��x��5P6�����FXz��e�y����Ն������nv���Ւ$������ �ӿ@CZ�������l����C�Zes�~ٕ��AJ��MV%�4�n\�еb{8Sn��O`�M��88���3�&E#X��� �Xg ���{Y�w+Cv���p��U��Y�����DkV���`k��Y�� �eo"n�u4Ѝn�&�[�n��yn��X΂�3�bI{�_ ��\ΰ����Q�!���b���/ ��ĕ.�K�HLd�lG/�0{y����J�������f�(�g�;�bĺX�m�k�;�����UZ�xѸ���f�2����zl�O���81���� ��1j��KB�����ݬxֲ,��'!Ԑo�����h)�N|���ikgv.+oX��A�!�z"jga�e����O����e�9���-�.��ʨ�9�Q�X���l|�nH�����v_s�T��`9�[k��p������-V�^v��%Nl�c- L�\q:��[oK���B�W�j�7;?�.G�``93���\N3��Ӂ�X/k	k��%R҆��ؽ:�c}�=�m�2��|��M�,��B�,�HΌq���:�l1�
�ǫ�W\b;J���9@#TXL|�����]���	L��]��_�,d�:�m22X�c7.�16�i ?̏04]E-�Y'�gbD#�����D����i���+��=�H|L�Ӂ{1��]�`r�"�Ů�(�h{-4�B�P�E��Zݫ{o�|;_�6x��<C����8x�A����ͪ���P�2-5�^���v�}��6��%TrH�1�I9f �p���jSo�ߞ�87E�0xZS8rg�߰9�Mٌ�az��e:'�m�y�:Y���']t�I�E��7������%|�u�E�ts1N�
t��GGJ ���MjT�]]M��U1��p`(]�0ضv43X�i}l5�MJE$�����v����xL��xu�{� �O-��!z�~��O��^S^yWD���������10� P۹�>sm����!9��'`��-�5o����7$sܧ�)?��hk$(.����rd�α\j�T��IT+s���֑o�|2Y-	7��-�&�3k�
�| ʆ��׺�ZOU�}Rh�ʕ��%�|��Q4!�޻�TU_݌��e�6T��c���8DM�� �р���lZ "h�a�a��5G���+ky�Mن�P	������z��clFQ,�T1������_RB��Y'~���Wo�{r��	�,:��LlY/��`�=���G������[a���n���.{T:jcZ��V��;N��D��	C�Y�m��}°kL�����yrߦ��ѦEX��j��0���Y���Qf��[�l�g��Ģb_,�A�+ؙ4������jN��&#HOƋPwn�>��v�G��!Y1Q�`��j�d�;ؐ�$?w{t�l4�)C���eϋ�$��u���~Ꜿo�/�VUª�F會��A����f[1W;m��f̍����mV�%�[�m~	x&T�j�l��Z��ؠ}��ԋ��q�C��U2n�0��pԭ�ѱΡ=1�h���י�i�ˡ,*A^v3F�S��Ƀ_Ɋd��-��̚�;����;-o��kS����|<�q��Ò����\���v<^3>���m�gވN,��"u\�>���i��2fd��~�P�sy��N����1hZ jV���I<\�=�v-�lf�R`|�t�{B�,�Z��sP�[h�30�_q��,6� G����ZL�aH0�T�F� 0��#��x�~rϻe�&�kp:�h��+ZW5x�;�8�g�۔U;L���q6��3��0h0�lqպ�|�6.��e�e:�
 �r��؊l�b�x B6E�:�|2�Ҵ�(a?�q��d��ٿ6�~�׵��!��?I:Fi��;�=��Q���`\��N�5~FP��5��	�Zyu楶|-}V��Dc�`|�t屃�sB��L�|����X�,��0N�tF�~�v�������R��zWj� Ip�*���3f�/���>��,'k/�#7�3.���@<~;�!�vDr�,B�m��n;:�m��Fd�'�)�֒������)�>�nDh|    �Ƴf���x�>S{�'	׾�m�L�14���N�_��j�{!�&
&� �cp���K�*��cl�u�b0=����f���83q������S��2��~������V����L\ڻ����$ ��u��3ߜ�U�(�xXs��#�&0�Lг�C6���G̺�"���5u��Xg3������k:q�u�W�p�w[i�.�v�������]H�s&�|��յN�hΏ��2;�"�ѡ��s�׃���7���`�XR��I����������ϰ2���SAZ0�5fQ�:#8�J�	c8��!u�8��m�i��ی�	��ݶ�)i>�P���.$H�4⯯F�C�f�6�oR:��I ��<����g;��u�g���v8��*R�9�V�0�Jy8:�Yпy'�N�RB(��?vg�Q�Z6���9��,�3��vV ��(��DAsV���0m�ѝs�M�zL��S+�WP3F|\�x�X@���k�f�r]�6�S�-x46��PѕѶ�6�ȏ����A6����Od�QG���4���tE��j�z؛fsU�T;}�>	���K]4�Si��1�>\��3�	���>���Z�n����Ys>�fgo1C�v��q�=�P��<Ny��nD䗕�R8�����;��`��I��!Y� s��l�Ot�qN=V��+�l����׉��c�u�\sͭ�T��Fh,�S�}69�Q&o�`3��x��tە�Y�(<c�f97���<����vl�r(�-�Nж�Ӯ��uD�{��1�+�[�OF���Bq�KˤS�����z,94�L�~�PﰋӜ����"�uF��8��f���.vZ�o;*K�ls�2*�B8.w��a��NC�q0o�Q=K��j�>�]�0Nf�]�̂�/�aSkŪs�Ёl��`����h>�Kb1��y��?P�'o�=�3��g3,D�W6����{�6grҶ
�l�Ȗ�qr���z~��b��V6�[ac'�BO�Qv�:�J%�����ŷ
���4nH*ٶ��g[�j�۵u+�wDx��*�x��t�1G�w��S�����p>gG�nVdm<�Q���"\
�Z�� ۙ��zLΑr����E��ړ��S4v��L��A�`��5�*��ء3�>�&g��X���m�Y�#� �绡B]���S�/�e�mVu���cK��@m�p:L�h�c] �(��&,��
γ��-��5G��ڤ��s��Q;꽆y�e��=�?�Ό�d��S�-��v���sL����?~�"�4_v�������y���T#6$z�-@�XH�O2��/<z�=�̌��׺�{"��e��23o]v�9!�~C����n�d@�l�j%����pY�z�,�8e�we�l�������1�� k�k��������-�a�8�%���m�F������ӧ;�\F'���njS�b�47
�p�f�O�,���?�&h���������+b�{.}&�?���"��i������5�2��4^�VB�
wiX��,�O�:�y��ft�e�Fl�9�^���N-��(��������z�-Mlv-^���^�3�H�y�������.꾙�803�e[h� ����w�\3��+��t�	
\��#<���^�}�}���o3�x��񇢵�^�r�OJD�������}�3 ��]�ˤ�w8��i}��t����;{5�8�-p��'�i����9y��\�;(���
UX���k7SL_�`��[�_��������;l��ۘ����r��Vp���\�Q�+�H�X��_��x��D>�%O,�v4��*�۷�'��lGg�sl��߷='6XH�I%�ٱ�,��6HO=9=:um�aW��[�����������#[��L#��r�㬼�����.�0�:3�1���N|��8 ��ܤ+'����`�η=�����A�?�. �,���ϕ>�.�j����&;(֖��O�O�G�W�\Lay~g7�f��7�	Ŷ{��3��q*�����ʷ�XgJ�Yw?�����1�d����Ӡ�a�����o�b[<�f�̹�o6-ĕ�R�٦�0�C��� u�d4�(�.�&����M/6nvD�.XI�lFQ>��A 7@Γ�DNO�g�ﶍ�@���`�Udpl>pl�Q��u���A6��g���&N����M_�Eb���X���m 4 tC�3�A8�fx͎�M�-JY���~� -��Ì��3:Ȗ�l�C^Y>�k��"J2���r��Z9�f�`�Tle�uPP�� �ھ�F:�{.��VI4ӭ��,�F4�(	�C���SN�5 <6�����d��ɯqvW�vGD�B�_J��������3?�N����~�( ZAŦ���ts����Y�o�z�� �$��l$�]�od��Ǥ��Ō�����*f�?<�U,���m��r-���y��[2m!��^휸YA�s,�mZǚv���N�YBzl����x*���TO&xvv�Ś�p9X���s��b�2hѼ-��,F}�~�<�;�RaxU���k�o��MuPw.���v&�f��6�-��)�T��WokПQ��B�ݪi�Q��-�>��͡�	8c�+�Y+l���[wM��6=�B	���>�@����8晥SO7��`aq�VC��9�NUD��s6�����@�8�Ҝ��)�P��l��a�(��9��*#2����m��	��tc��t�|s峐�v�����S?EX��,���*G���c��A2������C��s�i����[���1?���N�HzBa����)'���������si�[;z�H~�^���./4�)�	xv�wA�5���3] ����X�ꬍ[�I�ԫba�R،�4밶{��6���|����G�,�q��s� *��&f0�m���`�^,@<� ���m��)�eq��M�BT��xk{�Iu��m_��c���� <��(��B����bS�9,C���B�]���Ԏ�8� ���韷��5s��2�Vud[�� ��vb5��Bwg@f��e�,0�0+��w�}�o���mv��<~�+���+x�� ������X�w��؉=;0���w�N�G
�ԇa��R�ܜn�6�M���=#�?�<���o�Z��.���1�q�dmTS���l�`��	~�:י��:R�B����q���"-����at�9_���ś�u�FvPН�Y�n�w��� F���m�MWP6�Xgb9dY;)+��v��}�x��r�f�8��8�T�̱�';�;M���cr�z���U(�����HS��2����j,�7�P�A�e	7*����qV����L�α��,��Jvԉ���ԣ����u:��"��݌<'�FO���Og�]�c0��B�l�ˑ�Q�e#�׻�v'eq@��a�ș.�L�0��ONn3��a/N�ҥ�V�e�p2N��įVM1mm�0J�V����j�')���Y�fޡ��4�cp���x��ˌ��}p�:�j�9�D�p�V4퍳Ch��O��b��f�A�ӫ�#����X�Z�R��@ܐ��~�����$`jk��*���ɴ�x0;LǙ��@���(.��޻mk�j5�=LO-���Zw���/Y��a��XchiOG1 I���9�	�/v�sމX߹����r�3���B#�q:��7_�g$��v�換HY72}Y
r|�=�̮>[�����Ͳ*����P�9�ʎ(��tƽ[\�sh��[i8}q���e�gmv��؍YZ/oM�%ɰKkT�B��)�#�&�~��Ug89��p0�m��1�ND}t��	����6�#2�0�c�Gb�B��ܺΓ2�?���p�7����p�W���{U,�-�����E��9��N}�j��~���Z�,;:����e���YŞ�/V�'A�6E=����7T�4�����F@�v�Fp��O����� �����%��ç�_���,����>��)��C4�M��;b�]:q>�{��Y6sD��    �GŔ1����9�Y7�8���R�ʮ������ܔ��V{�Z׈���e;n�_2ka���7a��pp�:M���Y���F<�����Cd�Y�k~j*-�ջ�2�&��
�����m��-�6��Δ�tOt�h����j����dV��9 [$�;�O%f��0z9���2�a�?:����5�mAگ~���*rb����hQ	΢��5��m����γr�~E� ���rva}X`��M�ݔ�.��dә>��I���8GxT���e�͘mv~�ۥ�A�g��'�]��­�~}B��=�?����)�0˒�G��*�ʬ����T:XNgy��8c�N#k�#k}�N'�uI�����:M�K�����iV0�L	�?��ј�
��G��	B�PL℮��ֽ�o!ҖO�Z����ş�
oG�E��c��)R��q�3�i6�zϼ��1-_�*V�}�[�M��#��IM�Ӂ�F������R��������t���T�����mdªYp;>vi�آ�>�]'�ԃ����?�r��p���8��ˌf� ���6�6��=p��L�4�Z���o����l�7�z����Þ���Х��������bg��!���F@�`?N
��+�+�e�L��d�Y ��]@ �}3<�{n;}�x=�۳߉<}.�#�.����!?�g�W5.�{���ޑ�q*����g[���,𸎟`�u��	[؆3��c+��ho�ҧe� jG��,���{u���O�_gb]4yz���nMv,;�LIP��[�a������ʣS:F�Z���('Y �ҽ@2��I!�q�}P��������Nzt�ﳍ�L�B�[]5lݨ+;4G]�S�9@�8���tY����ƹ��%;�[���C�y���g!�'g�����>�l���>Fl�SB���6���#5�k�׾-:���u|�L��&W�[A!lÖ��O����!P@X �nJ8���a�����u���Y�"&@=L�C�GЮ/b�����5���hB�yx�O����$f�Ö}*�iv���I�@М!��=wjP�C%���p����Қ�
f��ْK��FQB�Q��೨�tcD��6������d I���?��#�M���u��X�z�ŷ�C�����0�s,��=��~s��V�X�!�Q�^��S:.��;� ~f��y�1��~n�:���!՘à�˖���~�Sp��L��.�	��cw�1�g��t�r�w��o��?S�����E
�S��o�I!�Wa~���}����M�3�h�0�n`G�����i�v�}�6��&E`b�Q+��B�י��:,�
MG����ˀ�ڍ-X�c�4��<rk_�m��f����dg��q��}���B���a�*{��#�C���a6ֳ���4.��Ƭ%'��$��m��c��X���8Okǖ�Kp���@ ��l8P1ȱ�k{�8�M�>��8����m��ͦ'���j��͂���z�G�sg#$n'H��s�0���xN���f���1'f���'��]��m#�Oe8�G1⨟��y�K?E�#�fa]��{�⊮օ�l�OI')�h��{MT�.H؈�P�'��R�������j&�i���/s�@�z�n�����I�	���/s�Ng�W�R�����/�+rS�|6,a	���h�x��{Z@��a�gLۆ��=�)��{�1����;,��q��·� kSѱ��uxL��6���䳿�A�@���?��(�PNOngyuHx��}-=ۏ�-��2Gy=.�?$R��ͩN߮zJ���n@hXA+9���笱 ����~�:�+����Ө$v��g��y��J\g5i��[b���� ��i�iuz�0���٥��A�6m/fc���,J�$���L�q������ٟ���X�x/��!5Ǚ�>$܁Ө�r�s��,�����mN� ���,��\)�f���2YTw3�F��O��	X,G\~����tڢ��������i��^p�j�Ӽccw��J�c����Lw5} ��o�(оjk��mU��E[��V���L�Ŏ�l�h,�&k��G�9����Qt�c��S�zӡ5 �
nt�ٕC�����Z�`O��v�H�d�ZE���;��f�v���9��S���!O�ד��0�S'a$����rgf��f�>��?]V��7��e�:�L�_�'�,4���I����Rb�[,�p�_�%��n��U�O��;I�1��"ߡY�XZ�����זj�����JoL �р	�)�vX��r��:�?�82S�9qقB�![�;�q����;�e9�w�\Z�+ꙩ��ǖ�69�,�Ka�N@@�=��>�\p���5��l��ަ���2K~h��p�.c�A�E��͙�x����1Ã���}�?�>�jЭ�N��Lpt�Y=QǄ��@ӶVl��?�.�"����Y������h�g!�&�#�-�tE�9�da�n*����O��ۯݘ��p��Q��Lqq����@�vp������ͪ|ق���A$.�=n�Äz�>����}:�Y�&�֦rO&Xf��?I��?��
X[^���h�]o��u'vq�U��!��&zj��g���\�t�aQex RNq��l�%y��" ��'y= �R�z�6���d�5���~�-��,�
����	�p7!�R�v�r�Mq��@c����밳���2��Q�Bߏh���<&��%�s24��5��if�;�6�&ŧns�~bQi�G�:�5�^�t>�2�02i|�Ȥ#Z;;�n���[1ot,�]�-Zф�"�F�y�)f�c{��ꚶIڬ��X��M,#6 /��z���u�j%|A	�0 ���X�r�������d��IK��j\�9R� z��	5A����oV^.#��P�k�Uq=Ŗ
@W��]TQ2Jb��%Yj��r�����v
��'a�γ�L����Y=��m�ˊ٪�W�|��`��
n�4��6%���m� ֠}���$?k�W_����i�؄A^�WMG�S�j5��*��-C,.��-�D��3�qJJ=E��<� �a􂢟�D��𯍚D��CBw�;��I�I�����	�3i�9a��l�m7�W[�8^6u�����Ԣۗ�1�`�3���G$�}�߽oL|�Ɓ���Ԑ�?:� �Jźs�R����p����Z<??y@K��a�å�5{~�3�K����R�MΨ'��OV�)����9����&!�n58�C��l��40��jh��t�ڿ(���_NE_�a+6*�]��ȸ���E>rZs�V& ��
��8@Q�י;��ek�.S�셮�Mb����k�)�r(-eh
����hIF�nb��Ǿ�V�~�{ɪ[�֛����!�0 Ӗ��r�#Oq��Vs��x͂���k����g<J_Gc
v�� �OS��_���r#�� �pj�C��$K�����]2IfF� �M��D�r�� �Fl7�|�~HȷY�N�U+y�}~��o`�����Nu:�pj	ɬ�s��0���'k:@ߖ�T>��%#'�RZOZG|{�����$k6д&�IM	&�|&�%���`羟�y(�k���E�wS�	�l'*s���n׾����3��b�$5����9�i����ƕe-�����!��7�����;�bA���G��	F;��]^����k��	��EZ�@�x�5��^�Z|W+�:�2���H#�0=ioH�ܣ���>*��^��(��zYu'0�w����>K���J,�����yҩ#��Du��L�5lN+;!��ӰT%���C���yh	�5
М:�6���>Mw@{s̬��-F@cK�V�:�S3����X���b��:��]P�E"N�vp�y�4I�M���C^#]�[{;r&+��L��I�1�}��8�-�F��O�G��	3�3�~��Ҥ�0� y�;DY��gĕ�7�5����e�u2O��ݻ[ˎ~k�����k���o�$�'�*_�3����*-�Wh:ʢbҮH���)�+�2H[_,��3FkЮ���~R����VpK	0+i�{_.ī{    ��7x��Z]��\Qun�����Ĭ��,�7�k��,*,�����3m�C�з��r@��'��C⎽NO�k_�$�Dڕ�M^�t��GV:՗
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
��:ZS��$�t�96Z���63���I6s?RSo�z�+����aJ���j�pR�����|�:x+��S�hI'y!9W��B��;Mdn���M�t��2�3�I�I��<F�6GZw��jj��P-�T:� D��=I -O����?����ixJ�AxH�`��Ye~<�@a��#�ve^���-�/<Q�W:��9/��P��"�괧M��O�T���c��E��y��V&�f��^�IOst���$R>�S(@>����U+�%��;��]ڷ*�=!��/p�̙��*S���~��0pӺ�PR�H��W.��q��?�=VKQ�Lk��(S){�R#?J� �3[�_ �<��K$�C,%)�d��mF�΁���Z`ǹ�w-n�͘<#����f�O��|˱�T����Ij�6V���I5��!6��IZd$o}z�ԑ�.�t܌wo� *��lv_ gwk���vJ+���:ݖ�EKȗᨹ�*&%䥗I�v�3J��Ϙ�nB	�i/�!�`Ϲ�����׻�ND�T��DC�0=L��.M�}e�7Cg�=�����Զ��"�y�%K�1����������I    ��;��o.|s�nj��ː>��~6/��{�'V�^�ar��UM����iu=qᢱVs�d�'�q*�A���Vs��̠����"<(����<
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
p�M>����T�f����"�$B�|�w/$p�U�D������Z��U�#aǱ�����x+�ٴz��l&`�&?&�|r6�29�tR�P���y3���|�<��̝f�T�	V�	[V�ב΄8���67$��cK�P��n����������c	�?C�}�?�2+��Mcq���}b6	/��c�����U��8i0;�a�_�f!%�/�|{;B:aĂ���O:��]��L3�~J_�̝f��;���I߾g�\�N�����%�+�� ��#Σ1�6�<#�X�dy=,G�����ﹰ�����:�ϹF�y�?��]��=��Z�|>�����t��p���#��L�?孪�&�eLPK���ң��C&�S� �$5�ք[9��6%e���v�hЯcb��t�<2U����T������}�yO�;�.,�&�.5�:Q�ss��    Zh4�#t���"Ҫ��?sQ.��;��R47�����G�� yp�>�]���p#Z*��A_��wn��ɹ.�.�� u5t��I7>U:>XA5��չ�������_Rv���n=Ŵ81�j �������qo�+�4�t~�V�YG�Vh�IL����|z�����]��.�4�zn�[:7�cy���6�!}�e�r��&�b�o��s<��!��s]l,����絭��ev��B��E�dC���ݾ���BTh+�H�cύks����\1���8?�}���o|v �k*a���a3�f.X�;M����	d��Z����Z�y*x~������: �Ә%<%�2H�q</��"5��~�!'��ټ�O���G.��,�h�� �o���BKr������v2�IL���گ��cO�?�q��ם��~YѪLm����d]J�7��X�e���L��,��Zm(���VH���w	r����B���~��T
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
�7mI�xP �ͦ�3���u=&���F R�:F�c���פ���x��JY2Ypg6<:��YS ol�%7#�a37��9C�,�8$��o�g�)j������M��H�亊v�������'��NzP�W:o�����j�BEm�ٻ���ʸJ"0��    .�9��'�S��x��D�G�u��6�WBp�:�%�,�xշ�1=<#R��;�'��Æ�A� nY��5�˧T�K%����~��_!���b���I1U�5Y�e�[f9�g[ɛ\�99�E�L)�g˩�-��i;���ј��Ҥ	'?�o����<$:�yI)W���;�����6�z���oe�?$;2�[m���S���m`����
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
��ODI2)��sB4i�T�רFn���֝��3}�Ũd*�/���ZU�7E��ż)��eϽYP��#��wt������?��s�Z�i/�k�O���HpIGKֽ�����sZ�߼3���P�@��-�x�\����v��.�5�2�I+/�v�q�ms�k%O�m�4OE3��X�yO�_p,�9�U&��h�:�M�AM�b��x���z�+�x���>L�Z�y^TEsKSi���    �$���",�L��)�Y�Zt|�7΄�RC�K鹗b�Mل|o��t�開��U���h�y����0Vԟ(�a����<#�QVI�f$'qO=��r���hKc��g��`��N|��A�1�]��r�}*�?�'-I^e>H��:�v�_^�]�Y�Xz*y�	�ƒ��{�vA�>_��T���/���pY�*E�w�����;p����1�q5�8��\�7Ov��$e�̈�����.$?��\C�Oi� ����ʟS:Z}p��r@׎�����J#�Apug�v"#3�� l�����5q�=����[�<�*M��K>�6Q�fw���{�Q
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
���~������h�o�n��Q�5�y�+��v��M߷SD{X]冦;+"���<���o�F;��I���$�&�I�CA�d�35�7�gz_�ݥw��@,}�M;�I����s�J�J����H^?�$��wL�Z��G+5ً�Ir�@�j '�v�pH��P/��4���y~\C�\�t�y{Cz�Է��Y#z�����׽趫��)˴b��,�s$��嫴qޭ��2 ����-��I���h=˔����e[1�ڪ�vt�ilK 瓕Kz�bڛ��X�AS�P�!��'�4��-Mณij:�~b�e�f���(���|�4�{;J��,�\�|���Om,��V��ý�������l[�    ~�-ϵ��ď�fXg}y�Y���$�t����,���T
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
��SO�X� ixJ�^;nx�������=���\Q^�.���0Ka�/����̢�P�DoÑ�af�=��M�x��t\-٦��� {cj�$���5�x�{�����AZ�2q�ٽ|>�i�o�|"� ��i�ӥ!��"��ۑ��@?�Mu��J#��D�)�G��m��i����c�&��y���������M����#�Z�����:!�mça3�i��n�߉�_~��] }w���8if6�2��y��X��gʈ<����i��7%~��1#5�i.���u>P���m���7�h"6���S	�:Y��N hR�7�)��zAm�$�":������t���,�jn!}�)���蘿���?yv�BQ��3�V��5��J}�\�?�^8�_NҲ�b�3�+�ű�_h7�ŉ���GЛ��SS���o[��h�M�TR��_r�y0uS�����>�?�̈)}��    !-5�J}�>�D_�j���P�����B�pߋ5�ƥ��g���`�>�L��@c�o5ݡE�ӾfJ����N{AP���^�6���e��LLQq�K���������/�T4�8��X�}?�'��܆���B�v�`��1���t8�4*���5�Uq�.��������ٗ_
�D/���J=����qU�?��D�(a���C'bq��/��t�`C�Dn�	"㏸�vs��x#<��%�/2OM��C���vu���It/�O�cm�^���ff�daȒY�����'�%�b�z�cQLK-�����<�aI�7m�v��=f��=G�)v��'���)F�t�D�\4;��*%��|��R��ʥ�5�nOw"?��sڊO��!{�\}?�;}�$���Jy���:��<�6:���n�Y`�.b�s��3���3���fG
�|�D�k�>�D���ͱ��Dmwy�]�и�p����r,%���l��k;lOic�Z��TjWK�����L��K�-������5�R.�Q�r���b�~����O��ͮ�4O��=�NmBƨ��>��?��ZD���hf9)%)S�N�=�c�2�M�`�Sr$/V-Ƹ�fs�����th�X�C���g4�ݹPZ��L��{bV�F�L}� LO6=�G�E��X��4L���
)��!l�MI/�(�'�����Ӄ�R�
{Ɯ�m_޵�����ih�X��~�!`��i��pW�x��Q�sD��3�t`���)fotk��ke1��H=:7"NÑ�EK"0�]C��#񥿟J�:�ml~���V$�0>���m ax�ˉ��D@�A�Uj�Fj�`	I�M_�T<o�v|x�K�Z�sP�� �a��Iϲȶc����L6�w����D3�� �|���Ɇ)���b�S���4T��4��2#>!$�9�l���g����m��ߓռ�µ����{���.s���:X��z�l-,ڧ�nL�'��⌗�����_}��PK*�z|Bɹ�5</9	���}��H�~h�x�&�qc^@D��;���ؿ؇��5 &���?�����̳K����s�IJ��B���s���/R+tx؞97��1EK( o�R�b#s��oYB^�/~�\��	=n-��Qԛ��S��T���˼���͋CxPX�U�)�Ҏ���lW#�.H,��ge�c��&0�K9:�z0�N37p"������^AO^{�%�PS���}�e��%p�X��`p�>��O�Mt��u{��]D��R�xK�d��3D��������QQ0N]\Ʃ�<�+O�*��D�����ۈ�N��
��-��c�{Y���\�i��O�ct*��� T�rN����zs���|� �s��{.=�t��E�vd?��6$o
���!Hhx��B��$Q�l���r�_���zΩN��O��9�@E���ҭ �;@�yc+ٱ��[M.��9ٔ.A�����Z�����b����f��ȑ�s� A�K���j�����R!e*%�R_��7N���$�n�p7�iB�_�v�6����ky(b� (�SA\FR�c��4-)�L�S�G����*��;��X�oTI,\�Q'{����3�(�ĕ�{� �.�NoE�i~~�I�L��¯V��=���<3��߷���� �b�z@�?0J5`h�f5��̀/="��������Ϝp���)�>���K��0�z*=���R�2�&Ž`Q�?����L�"���: 5;����]�G�<;zG���`Y*���ߵ�(Sn�l`kHN]��@�k4؅�*�[*�(��5��x��f�W[p0�ҕ�L��Q�i*�br4N�Ⱥө;o.^<�?^�-��͇�o����T {rX�װf&h��b�q������r���4n X��e��z��	���1«Gx�]5����2�,��4t�T� �P�ɔ<��|��2�����K�a-���,��@[�ԩM�����P=��d��khZ��g�_t
Ѯ���[O��wPTy֣�%ʞ�2�e_#��1�����
V���a�b
�y~&,��%��d�4��',z7qs;C��ꊂ����Us�\<�N������D?����� �k�򊎄�K;,-�u^p��>���B5�j�8����Q؆e��H8bvϤ�F_#�|��k)����Q�yp��[�0����=q��-Z6�y��:��v��y��8.Ϧ�W�|Ƿ����g�l���w�̄AP鞜���g�v�;��a ��w��0�Ǵ��V�s�������2��.����ګ���������]J�G�Ѣ�Bgu�s[h7��:��O�$I��*iq�_��S�X>�����k�a���9`�m�p'H�O�k����x��?�2Y�W��7/�
�ƞ�~� ��:-����i���oq ���5��xC\���1���Y�������-����S�<Sຢ)��i�9\�C�w��y2]��	/�n�&�w�ŀ���ۃ�+�;|���@��yS��~�/7K/��z��#��1����w�h?eɄz ^-�w�b�YV$�\��9C�l�g��y��TSM_��C�J��]�]����o����[#��Yp��Q�ud��, [���kĮ+��$i;b�]V
*�S1���&�Ox-E�`�����ał�UCW�OA pXC��Ft:�ANۼ>=0$N[3�:Η�[B��6�zb"WokA��,B�C�5����V�_�a{o�e�V-FYj�|А'8,UN S����&<Ȇl��rL�su�����l�:�9�9!��rX��� !��m�>���#;���.�K�~�*����=�/S��pu�x�!��l����c���x���H���3�,M]io�o�9���!��c
�Z��Q��d�#4d=��,�b��9d�� �����ɒ�u;3�-n,�meyע3�%�
��C墋�J.�[P_�W'@���H���1�r���E���;'���W��g�y�b9�~5l��\�v>�I��΅iqA�_��ǔ�eE;3^ז�Flr��3�u@�Z/{4o�K)GC����&,34����*^,x�h���q�-���w���z|�������U�N�3r���h���g��_D8'�W�����c����~������k��<�V��t�h�B���$�cV��
���Q�W���\�}�?b)�G��B�p���S8���jjk�۵R�D�R�j#vl�i�_���MM3%	 ����Fڼ�<����h~�I�+T���쎄+���a�8�&;�]vۛ�e�D��T��d���Z��h��g��<����n�[���A2��z�ϱ��u���z~���Z���f���{�\���ۖ���Xf
��rx��C}�J|%.���P��'�9j^�;'��7��'18=˧�:��c�t��3��G���uQ_ [n��ߨ��a$�f}�C�ES�.,P�Z�#�U���b�ěkҷ��'N�U�+�bE�l+՞b�k+���Qֿ��<��� ���"���Y}�7ESRh�,|[P�ֹMK~�<ݻ-Xfu�τn�m����g���5+�;oE�:[��������Z��^lD�t��ӜX���9����͉�	c)�n�U���Ǔ�������ʹU��@9IEem�%�"p����'��q���܅�"��35t�t,�%X�*ɚSl5�fm{Qh��Ci���]ݯau�aM�~�N;Cl׊� I@��Ps�J�7�yo��=�:`.�]�p��}E�[�&E���a��/���\]������!��OV8x5��I��c�2����T�b{�g�F�:**ݯ�9ZL?�dV��&jY)}�����"�{���4��S~R��Uǚ�sK�_������O�F�!Z��蓱c���<O{հHE3 �S�[������te����Ֆ�U�OE���jk-�\e���'���C��v���/�����〃ݭ������@�l�bNj��N�1�2�`u��xm���{�s('��c�<���C��Vow{x���� ���z؎�4    v{�~�V�r��;��Tv	�
r;���jYq���[��&b6z�0Z�Nح�����ӿ֕����y���6mnVUqC��IKʛm綈B�}B��C���8#QZ�4�>�閬�br����Z�?����ŕU`�c��>TFZ��^+���n����C=���"aSG�c�fE-<�v��������[�O�yE0�U�����X�c¨GZN�������%@��Ķ���᲋�|����'�f��A��cӠv0j�ie���%�)7_5�١%��^u�U���H���78Y�֚�36�{HM��"��Mxٮ���D�Rn۵�P;�ʤ/�`N&��h��1[�ON�N�y}k_T�����Q1���w�	N ��ɒn�1�=C�+�S��ՇU.���X	f#�%���>X)��Z����d"v�^o�Ā��=�����O]��� ^�q<M|L�X����:�Ǚ���z���l%��N;Mȏ%�ڃ\�H��J�ԙO�M����iN���|��2$�
��/3��\��qx,�q\����DO��OT�<B����V�k��亂&���g9� ��9����i<#����b�V�v�-�}��8N�m|$1dIjme�+$������QiZ{�zy���W_!NY�$&�tZfKo��P�z��n��sB��!\{>({�m;}�}r���s�$�,�N6�l�=Q���]ك���w�w�Pp]�"x�lD�[S��#qIm]]Ӫ�5���r��Vaѐ���":ig�g
�F��P�>D
Nv�^��9e���Z��2�����'2�6F�-2�I�~FÈSe��K��C�i�B�)�[E���a��<�%<3��=J���������L'����j��)D(�S�K����&X8�U��(�1��BH�*��70��K���u�R˧���m�Tj z֦J���}�r\�G�-�l%�����n%pe�ʗV�F�_!�,��\�~n���1���n�9�BҦ�Yw<!Y��f�=ݦ#�[����_�bg�]�g��1�j=Ws�Zs秊�A��$�u����o8�c��K�u�ѝw<��̧4�m�@��N��/�N�߮�R#9�Dx��V+̺(:G���*�V�l�9Z�u1��)�@R�%����-��I!Ӟcpڹxk��T����ԟQ�a���R?���m���
V��v��>���n��K�����QH����4˛ǎN4��a�R���������yH(0�el�_q�+�ȼ�lBD>4y�,<OG�?��4:+��q�=���Yo�*���Av�HR3�5��9܈�'VҐ�t7=р��9�cӢ6U��K�^l�p:�ɚ|UD�KV"r����� M`��4Tq�����z��:��aݯ@鱌���4?��v��'�<��ve�|����qM�ω(O>,�7��h��HuR�;�^TR�x੤��[����s<�CY0Q牛�=>Z�HL�{4Σ'���T���������V��k4��i/�do�8����
<o�͂).��H�m�կ	ʳv�|xq�'�NQ?�j��A��D�0���2z�O������m���|͇ݬ�jv���޲��>��q�E鼭�'��>0`������q���κ18?;�}w�c��TW��W�o<�3�g�?|`������N
�;���s,1�ّr�}'Q�Y4+>j��+x|���^0��[<�~éZ�ē<P�7z�/�9K(]����Q�M>��t;���� �]E"�t�::%�T'�eje9���,l�@�U{�,�e�Pvtm[?<�%�y�p�Xr�
Q�!J�Ѹd�S��q�����N�)p���I���v�$~X_UJ��{R�����l3Է��n��;���4��@�@I�bY��啃��Ju�/��3f�_��>�𤹣��4%j�2���gB�#��7zu�T���Nqd>Utn��7H��%H��-,p�NI�s���+�v	_�i���˄M���F�qb��u�v�d�\1%���W�a�&��������2���X��E��Cͧfy����_�ؽ��X?1�4�_�IG~�� <�[t����v��}<
>,�
_}���dg���iȎ��t��':P�~���
Bm�h�ǒ���Cv>�.7Wl�f�DR�׾���c,֤�b���?Y���y�jE+!���G��|����a�kB���A�sbf�^jS��s�pE��45�r%
S���8ٹ���b�D�^�K�u)���3J��5|WjX��xӆ�k���N��o�(��㊁C�V�(p��:V�^�0����6�C�PXNYrL�����O>>��P�mUR��YځG�����ޭ���9xB�^w�r�?V�?F��[��	"a/xr~B���\�#a��yYJ���rU����t��k3ƔcL9*w���R��,�F�)ɶ��l�m+۩J`9�a{��J'��6֭�)k�"�g�]}�<A���pS��ݞ��?<8����Q�J�0IUǅ�]�}k�XL�V���V����)��, ���Z����,@���7=D)�5�M�� a�+-�TP��5>�&M� �!�P ��\o
��˲��J��뷏� q���kY���<,��8�dk�9D��M�{�(�6��C[��G��7`���N᡼���Ih#����Vc����]$v�t�JBt�{�ֶm���C[:�)��i^��9�	^����18���U|�&�B����t������=�,��S
�
�-~�5T{,/�c@V������φ������F������fO[?v�R!�_��c�z���o��mt�?���p$�#�9�R�BX�r���)l�z��/�@������Ȳ9�h�K�<�lu�n��� !d�ĥ�U��c��h�אL��mORW�ǲ,�Yf�!�`���p\;�y����s< ��(��鵢�K�K0��9O_,jI�����N�.[��T�hrGc�O�OYs+����z���М�Չn*ʮ���l�d'�#��zd����י��!�t�?��,����U�	�Ϙa��H�/��0�h@x��������� ��Z���4r�m��n�l�aC�N�p�5�9��C\?���A��f��Y㥩�3�����אHt�A�����"<+A�>�#�M�
���7;J������(�gd�u�m�{�q8Η�K�be�#d���}�]/� L�ϴ-�c� ઄���DZ����X�Ϋ�"d-�+f�W�Z f|%M��j�����ng�}�ڂ���T�]�.�Z?��pX��&|��Ff��)�R�� 3g�U�����M�:�E=J��*m�z�M��� f)�F�O٪GpoEm��������QxeS`+�y�aB�?��a��<�/+/Ke��ܷ�2ϥ�s��<�ds���4�y��<��l%46�~dH-"w��Kn<�`�}��.����Ma��5��m�Z��*�Z��ek��B�9W�n?�H>�L����[d.醣�v�H�HDH{|R��Qzh]�%A�ߡ��!��s� ��\Z�����x�� {f'��cHG����>�'a�x��-��恥�G�@�m%�
;`�3 �S��,q�Qo��Y�m�Կnƞ��|���5�.�_�ր������� \�%>����gk'�v/�ꊒ-oߺ�צ��c	���T�Y�9���M��X�Q-������9'5�=���d��䮜f�;��Ս떀����T�F�SAN�����Z(U(>C:ճ��G��J2�,"���U�M^�z�̖w����2<��0C�RW�s�dWH2FB*�P:^�0�T� /���o�S���~�|���@e�1z�Z���)�^��ݿ�}y�
�����]�OE��&V�x�ڍ�l�<C�㌗��0�䳚��x_o�R]� )+n�k=T�°[}���6�
�!;DٱV�����X8s�'t�t��E�2��$��{�D�v;�úP���ԀR�����=a_�%�,a����    ���oX�v�x"�3��<���=�Z|��/����򪸋��7:�HTqR`yv��7 ����oX��J�_�}`��|?���ҩ�xa1�
x�:Z������3������7�>�?]����6;:�Y$R��Vh%�<�v��s��g���H Q�Z���7M|���qOc����.d��Y��n�8��P���y�^�D� |<J+Y������/�v�Qb����I�T
��ua	-�����~������Ӏueo��싍����v� z�P:S���g"sD�s��T{I�6G�Q�x��
�5<s�JC,'=NR��*QʘP.�9�͈�/���3������Q�%.{�~�UF:���k�֨\G��q ��Aֱ�4�mt��s`�e�^�?\�3ͦ��է��fў�/���j�oL�S���#��z������o��/Ծ ��w8~,=. �zF�!�K{C�V���Q���Rt�����B8�w@��L�{
���n]�%����d%��e����OHӡ��\L�8#�!�N�°�4̥�}��Ij$-�Rl�^������������Nj:,���=U_v`D1;�g����D���3J�f���Y��U�Hs���p�6@'g���t4&����Z\W�.1q;w���R�"�I�+@�Ã���P�@����K���N�.����K�m�tk��l�*�n]�Fś�Q����v�Ŕ�nL�U��s��1U�?�?�����|���v���s/$���E��U͢���:����O���쪆��ϩ��?:�b+�J�g�,�����3ک�h��Uj��%��]�j�C��j#
L%��|�]��]<|�\�ND���۶oi�R��������Yw�_��f��m��.�������Ǖ�K�g��q�����8}5�*6)>;)O�G�������.�nGcg��)5Mp]�J�nI(����:.��[4w~ʡ���^�yp�������Xö	)e?tW���U�^vNނL�}!2s9��GV�v�_%׬�퐢9��[��~��WeR���5|܀�X����.9�y���]5J����iO�`E��/� �PkS�r����6kb�P3�B��W�ZDo%2�� �6�	T����^����DU�EgBs��˽��pR���9�����J�5��Q����= N��W֙l��Ѱڳ���S4J�<�]���|�r��+�tCz�����޳��\�/���>"D���h�7N2L7*���^�uM2Yu]E���ü��C�5b$��1T~��Tt��������w��H3���<��V��u+�iڦ����6!=, �-�\�7J�w�U^���nQ>��~N0[{z�^�ӑݎ?���eG^c-h~%�\��%7	�7����D5��
�IC�0�U=l�%]?������J����\׹m�(���q��d�h3�,W�u��R���]��4/���"��V�)��RS'�^�f�+u`�Ӻ��0q���!��U��CNz+��Ж�K$��a]�W� �%h�Z7��Ԑ�M����g��OIW�Z��a����]{ -@H�!@c�I�!*�t��0�gS��.pΪL�-��`���
,�BU�&{�I�1�y��y���p)ݎ�Vu(+�[Ia���4��b�������v��itB�Bײ�>=���9����g:��U����� m)Z�%�g�rϭ-Z�xm47���4Aa�:%fZ�M���*�/�L����6o>)�f
�����"q��V�X���S~+���§���O���� �Kq�%V���6�;�:hZ2��Q����"��ٲ�5��5��>��%�ZW�?VF�V� iǬw�p��<���:Y�=:�E�.��9�byt���Ds���z"���WP�52��d]���e(���]�C/B'5���-�����%LQ/�T���˚�~��hkέ����l�8F]U�[��/<�Ԧ�~c�+�{��jk|
�Ē�UfըY�f#[��[a���c�*V'��gv��~q���D��X�����*��Dk����$Y�2�$�����@�^Z&�� �j��<�GN;+֞�xfD�����/�����9��J��Z+����8���mr �GC�K/���Bhﺊ5R[սR3�h�+�>t�N��oG��R����/m3��u�G���4˘_[��p���m�ݑJ����j��`��ܟR/f����pk[Æ#ri;�n��K�3�3k�"4$bŤ�,u�`Ԫ����5Ч�^`9y�	�'1��ͼ��H�[0�ܼB�
T�)�q7��Jq���$���+��e���jCkL�0|a](�ڞ�����gM��v�� ��H�\G�|���|M֨���U�f��՝h�;�a	j�S(JsT�\𪖾~�l�Q�	p=C��&+��D��Z}�t/k�U���T�̺C)������T�W���Ï�N	��'�
W uz�����|��V�6�
�Oۛ
�:Q�wևM�.��IW!M�����w�8N����JN�����jXXX�I�<�ݢB�"��z��,�rzU��$�E99���r�"�y>rk���t]�ȁe��[7P���}���HpOյ��h��@��k����1�`+���l�I�^����}�����״��pXN����{ę �SH��͎W�
��_�9�.�Y��O��ٯz�od0�T��)�f{\{���P��kܶF�D)�OT��l�/��Rd�@�z�t�Qp[�l��=|W>&[{ު�j d�j����D@�.i�]*X�/�	 � Jf��&��f��E@3�o<�^����q :�j�U6�W'X��&'�ge�	Ŭ�BZ��`��@ܪҕ�m�):r�I���Gg�O9.��N����UK���x�J��b���>V���a-|��i4j�fn����'�yH�Q�I��E�Z;o%��ư�D�J��,��,�7��5U�L�1���ϩ*:������K�n�V��c/�-�,qroVO��[���.]!^�M�Q~�����C�#�� �!��.?yf��(�%.+��gg�mnDt��|Q�X#o5co2;�vr�;����sN�;l(`��8�R��*����&Wn�A��FQ�����P/�r
���
V��]�q��C\և�b:�O֎'������v��z���*I�֑8N����P���CM%G�0�T�.��2�O���P���2�֜-b��J�|�[��M0��s�b~;\ݵ���� P�~Y��n^1u�����&�Z�7�rgZ/��7��} ������~�D#�)������<���KU8���p����rDg.B��UI��s��`Ñn��=X�1⳪�uK8T�ts�~��{l��.����w��ۯ?����$$����}�	,�{ء���?�˦����o�$��Y���[踛.E�.(�>?'D�aO�B:/zW%fm�jqQ���7�%e6�Ϡ�9Aty�L�=���2fw�� �(*���߇��? u�cڐoW����tc��L�wI�a�^�=��b�0��j��.�-�q�i �m��i�ou��J?zve[��%�_7���-�0�L����5�8��/���cҭ��Z]�[��sJ!S�c���� ���J�m_��P�z����Y�3���s�������9���V�EH_4FC�W����� X����j���%x�ux>�lb�Ǩ�QO�����`�,������������w�߮������S�]Y�?�t�.wel��k�b�<�ǩ[�S=�j��}��Z8`;�2Tl:q����Mu��و:�w���/6���̙��U!��\4��V�K���n:��0�r���m����uZ��C���,n8�+��mZ�����'�	��}��s��������~L�k���~��(j���|���p�
C|��~�Կ;:�_{�l�������^g�V�{ǊIϙ}�$���p����g^wx�(z_{�p��1.�����u�t�=6�����.DIV?hނ��X    ƏKS�1U�~l��cj̛D�]��Wa(��2w��S��Q@�O�f���n����c��d�A<KzQ>n�f��CS�$l&��v.�z��Sm��
b�Y�ے�s���U-?zI�Cx��XO����;���-�@���`��n���}=�����i�f�u��xV*t`�h������:�agƀ�H�e�]3��KX�hvV⑕7�#���!{�rp$pJO`$��VC�G�է������|[�ܥ���~6��t�P:�MPE�E5�5�%���1n( (}���N� ��F �����j��z������0P�N��a#�V9�x5d��j"��\[�W��P��D��P�a�x�����_��C���fw��FD��L����,�Q�0���,��ǯ���w��8�C�mW�H�θ�T{��٠���˿`��f�8{�u��}�m�J -܊��|����l�T��!#E����8���dg�!�ʍoo�y#�s�8�dz��x�\��Y3Ny���Q
Y�ʧq$���`����m�s�E���<�4g� D��8�F�R�;���C\�8\g�mUI+��G�����*I�*�����m,�!I'Y�T�~Ƭ�'���7�=z���}�=�:�ʍ�?�Kˣ���!_�����)�dv�9���S�P���N�d�ߍtU�y��ʎ�.A.�k4U_-*���x��soJ$�)y���(�v�g��r��4�|l��;!�Zp�/���J�3��7�L���?�j��6�xJ�h�BPwN�ի�s���9�6��Y��L�}��2%�kK���P�	�'�_W��R�d'����T�J�z�`r��������+��wiM���[�A$��#����-��n�PٰAW2��3��GBa�Q_
�Y����-�A�fo,���̑�~�W��(��>:fo��� 3{D��lRe+ԊĪ����d��%9M�3<��d_���|�6�*y��cj�^�Q�լ�ض�4�ܡI4���V^��@��dz����
��v)��N!H������e<�i1���|���cN���Y+����Y�l���A��*���"fQ��_oI�?�����`E��O۩b�l^	W7���=B'ȤO�X�ݞ1��e����Kס�ק�Y�mT��Йϐ��f2�L}
=�w�7_?��9^��X)���ޡQ�W+���}=u|ۿUo��?�p��� A�{�𮡲>P־m�j>����U�*�J�m��BeMɔ�^>�>n#�m,�-AɈP���_$���L�)���|-j1���w�I=du��D�6�M��S�7��w)�1�����%����/�W��}?Z���G���G:p�n�(�	҄����>������W�Z��S)�<���bT�U��)GXz)[0���P'X��(��w{��H�ϴwC%!��M5��;]9��ZX�����KJ�D�J{�>Q�����p�x�R�Jm��!�(e+HS��S���2`��ǔ���찢�RD����뫇�G�4��R��9� �C�R��§�~lYYp�˞�V��))�< �x�2b�0��\_%��w_y��,T�j���[�>r�Q�ߞD'X�|������O�4�W=���k"#d��*�C�Z���A��;;R�g 0'��J��qk�i�6�?�nf�5l���� H�,V��B���kR��)���ؼ����/p4'�lok:�Ӓ�(<bڈ�0z�"�F�4��z��O��?:3�s�D6�Z�q�T���@�jB���u�_��h����I%L�'��͹eeRH*�@�ŕI%ꚜ��XN��fG0G���i�E=�w��,h�2���l�[��
�[�
�g �|�/�G&)�eO/��c�=;Ma���:��s�r�)%!��S(X�1��,_X�"��TB��Ӌ��C,��lȽ[(�Fu�PZ�ǐ�Qϣ�bWa���{��Y�P�ӭ�﹭��H!{��F,��$����( ̈务'����@T��Q��F�q��)�{�yN���d0j��źb-�������c�:�¥f��lޱ��H�5U�ImL�p�Jꗣ�����@���3W��N�	��@����,�ig�^95-_F����c�̞"�|V�	���q�νV�Ja̯���i!�v������0�Zf�wh�g�nXE�Բi��m=��i�ԗ�ݟ�K^Y~sV��=�F��F�����{K��}�R	g�ݛ��4��wd�
��Z�+k/d�;�����/Nc`g�����s���V��W�o,mye��}��枝󐃦���תt��J&^���\*,��cUJ�&�9�_	4�:��,�1^Gn�3�15�0���öH.��q?�%�os��#2V��V�G�Z�;Z���9͡��[P���k�5��%�!��]%M,���*o�drK�6=W-����񉻣:+�i�SD��w��8 _ѱ��������z(�����A�����Q��UG�?���@���&�^�np=:��q`����f=��{q�r�*��8F�Σn=�bH�q����=xU�W�9Օ ���lGFQGѿ}3�S^ke���bM�طoaR,�I���5�Rי�v$V3.����s����ʧ�[��v�e�W�@e�g:�����a�t͚V�06Ux2�	Ś���iG��'ը�6�!ڕ��4[���<P[�~�'�o8�ǝ�cx�?͒���'���z�V�V�z��Z�H�����w\Q��v�+E����V�US[§�α��T}��~��,��� ��3{bh���;�̪+�G�.O�j�j��U�TW�s<��cW��O�*b'�χ>mV����
X�٠7B��KH���%z���Z�{����+��.�f��\�9�	�K9C�+_俫�!���b���B6��>���Z"�Kp�_~L�{<
o>k�\6�O�@`�&zu;����~�(D�/�,Ƚ�ڊ�fG�5���1f���",|^�h4������P���!+������V���v���l�	��|&w�7#��,KEVa� �� �*3��[�翙D��2)]�
a�=:�d�
u��,���ex��;�?|�O��b�&8��Xv)�g�f�;.V�$Q���>a�L"�1<ɚ8:0N�b];�l�nt'6��z�:�_K�^�zF���[��M�ztlJ�����N��y����ۡOn(��8Ygn����bq7��a��J�&C�*��!P+������jL��8��t/:�B�;�.5�n�/ܻ�5��9Dv�Κ���>�����i��_���|��7I�8naݦ*���Y�X�"nϓ^��� �񟫽�O�F�y^��^:��حK�R-�ߎ�D�'h^f�c�C�t������bitH�S�
W�򿆃0O��=��2���hر�����D���4�6ց���ܪ�з�G���r��w�Y�5�+4I�N�]g�?�K�D��U����W�5d�����1��~$%���Fֽ�FP�`��M.��:�㿛���[���_����a�[��8�ɐ=R+����j�B�^"�-�����bar����[<�(cL�5�n�ͩ�_Q�Y!�a��k�g�V{,T����R��ڳ�Lӟ5$��[iE�.O���V`�$�U�GjYkBh�+�RcĞ�����E^?9��lg鳀�ޥ(�f3������1.8i���#T=�3Fe�E���\eM��C9!hg����b��u9��i|w�ϔ�]�_���W�#�5Q"��.�ʣ/��Jɖ�Ǧ<#��i� �� ��xr�B�c�5��l ��`q�o>mo"�d�<����a8Pv�%�����Ѷ���x���"{m��?�;\�}�D6S�wؽ�[U�)v<��e��c��ȭ�=�2�t%3��R�67:����2�'M�ڲT�#�q�L�	s�_1���P�3���8�d��E��a���%:��	ބO��U	;�� {/~l�pҲ�R����S��3!��}��f��������˓���N�'�O�j�W��'�3��h�?���׽���ǿ���������DJu�N�[-K�kv)&�c�e�7�u`�n����o���    pbF(�P�͂$�pZ�hאX��^�����\�����ҡ��&i����`�`�@|A��(�M�� �-���R�l��Bx�>�eR�=0�;�ix�|��Y��Go��Z�#��l6۵zU⣤d/��O��{��K�#_��w%��l��~_V��=*��?�.B��ʯ�����4r>��[����/�/x�_����O�������5�����������{C�߷�����m�������������/�}��K�[������������������[�ң߷��G�/�}��K_�K�~������/=�}�_z�������G�/�}��K_�K�~������/=�}�_z��ֿ�����/�}��K���[�ң߷��G�o�K�~��oo�K�~_���ߗ�������/=�}�_z��ֿ�������K_���ߗ�ң߷��G�o��ңO[���������ީ����Fi�Y��U��g���*gmM�����{����>�V��y�T���o_����]?�O&��*����_�ӟ�ۯ�������O�?�����O~���_�����7�G?��3�?��Ͽ���+�/�{�;_��W����W������I�3��j����g��I�5���k<+~�������-�������?�9_�Z��~��U�J�z��_%��7����UGt��o{��zR�R�^���n��OK����s^ޝ?-�~]c��o��W�Ok���Go�����F��\w�o�|����{
�6�{�'(k��Y���=��0���&a&�݊c�=�\5�tfε�\���a�3�����2���k�o˭�^w������^M��k��QU�����E\�������;�}Z^�zs���˝�%��/hS��}����|w�fmիއo��غ�o��;?��ֽíR��]�:O��=��q,����˻��� %7�MT~�3���q��{>����jkz�>�xnȣ�����D5��wꀸץA�}�����l�����Z�y��I����O%�]o+/���{�������~- �h�j���}m������e�K7�2��{l"�i���X<|�[�:�.�ߦŻ���n�n�:�=��~���o����q�������/���=�6��y0�0�~on�h���6���ͯ�6ܺz.��w�ۧ�ho���r�>�s��{�y�F�\�Þ}uA�u����z��kr�W�`1������]s򙓧�~D���q�{(2�nJe�u���%�\�uV$C�%�^ϩ�d�H%-6�u����U��'iz?�׳iB۾w�����m����x�w.Z��d��.��(���ۨ'MK��n<�z�yX>������f Y[\����T�T�}�j*��f�F�b<<�������8lm��h���˹n�Q��Q�!g���1���v�q��ߥt���Y�����%q���k��f|�*Pr�_�����2ق�x^�������Wͣ�o%���{~�?de�~w��v�˵!�X�D�B�r��L,/7Ug7 K_D�ǀ�O?�d6>!��L��C�A�%��v�w��Ʌ]���cc��}��!}<�Dp$�Y}M!�V��yĦ���������a������kn����R���% lv�:SG푵�\� Dh��i�wt��3s�u��'�N��i=����hz�NWd���ۺ�O=�
��=f}��}E˷�.m�:W/fЯ�%?��M�'��G����� ��T�r���&�O���Dv��l���+ߞ|���l�&�ݲ͝��(hIcm��l��ر<�4��'�RmZ�p|��'s!��9f��>�E�=����\��H�-Z������-���#gw�����;��x�K�f��:�\��3u�$���7�s ?�������W���h�NJ�7@�?�f����=���zߖaY�Ch_���X�j6�i�;�ˌ�Xz�G	Uu���"�U��i���d줩ߋ�{&�q����N�����	���:����7؃\Dz'��]6�	n>7�k鬝+��Ӭ�t���l?�T�$^�_g�F晋�34�o��]����s{�m�@ԭM�{� lJp),�B�m�^2�~],�[��~k���2��@x2b��Ք��Yc#�ֺ�ۑl4�;���_�����f|�� Ͼɏd���4����?�$������f�:X�lb~����H*�>-c����&�%�6W�>�ޔ��tg���t�ާ��"vb%��I�:�k+l>�%+���V�J�"�x���@�.��M��\1��A�_� ��s�|��R��6�������c�b������* ���7����l4���`ʄꑀ�������G����IG���R�6%�6o[�v�J-��0z���� ͊�����!����F�J���Y�f��5�d7	�*!���4Jr}S#8��I��PCd'� �!j�4�`�2�ȣ؝h�5[���4�v�I�g�8�����75�x��#$��$1�OЌM�"�	|0�7�s>��
����U�z�֩��d$1[���Ȯ��W'L������KD��^�$��m����D�X�%��������=���m��[%=���S叀;0���NZ��$k�qY�f �ؒh=�쉚����\ރ��������=`Z��op�z��_Bm�y��'���>FLh��{�P�HsV� �D�Jf5��M����$�n�V���	�D�����0��o��H	�bc]Z�j#��Z���cW&̞��3�&^zs���3��.�_��q����"����������s7�����\�ԡK�9��An�{/kq�Ě�׋�q5nΣѫ���va'��y��|�/�}�U���c�f����=W���������L���o(Z&� Y77@��V�9���O�M_�M&�s-.B�U߸�\�"������o�
��N�߬L��5AST"��[7��� �?��W��wX5ۙ��';�g)x��K�uh-)$����-�28TzR!��݅e�	���vK���Zӳ߉;��݄tN-�E3p�T����V���Ol��y2���ggA|%��Y,�LĚ�\|�%��`u����A�!2FeR��<��G�[�_��tB�W[����R���e�����x ���k+W�x��&�5�-�vt7�.V��p���#�v�t��i7���:�j��S�JL 
|�j��-`(�v����t};�@�z%X?O�jl0�����{�p�lt �CU@�z�B������G��5x]�uÐ3j����a)C�3��ݺ�O�1� �Ե/�uk���[$�ᛀRA�����m��$��,�7��C��p_,p���$����"�,�j�TO:Ɠȇ�������O��zD�����A"p7�֣;��h;	�˼;t�mJ���V����>)��&B_�IjZln���v�^dm��E�$��S�C��k!o�k��R�V��/E�Dv]���<�lɗHUB���M@v%x���W?���m,���=H]��Ｗ��@��A��P�W��IJj��rii�B�Y P@C$b�_O� ��[nn�F��I2��)��	[�^�Z��VS���JL���1	Z��2���FgqH�Y�!Ȱ����w �M�;bT��R�.ID$�>��t�%dn:k����if{60t[��:�uu�>�w	��g�����AS7O��8�Q�q�4w��� ���l�Ĺ��I�)٬esѰ���=ޒ�vM�^��É��r�����m�)��CLz�����}Xi�
闥H��Yq�]?�,�l֫mP�j9o6�S���|�:d5-ߓ��>����O`c��pV8�6��x��<BI�`F�L�"[TAj7��ԇ&����Uh��Z�w�F�A/\&�P��VM\� ���gKdQ�w[WK��2�h� ����������\�\�]���LRi��	�`}xƞ�ҵZ�~��܉�Xz��C[��Z X�^%�D�lř;�Y���w��۬a�O�W�<��=�>͖Q��F\v�D�z���i������3!J�kx����    > ���[������"���"���լ��׶vS�
��M����,aH��N��������J ӧG��(f��23Kkdm�Y�$�J#jT����
�n!�����!G�7��Gz-�#��a��J�M`��&����]�ĝ{u�Y�ˢ\}����]8�Y�!��wrgc> �7]�[�-�����pc_�0��ݩ�w��L�� ����4��&+���*�_��Z��"i?�JJ�_f�-�u�b��-�<Z��vD���~��2����.��Ě"x�B� 礽S�X�����)��wv��m�+���C����S��V�2#;�-�U��@�H�l�ݹ+dޯ�Jx�n�ĕ �n5`u�:��O!�����__.�|	_��('�5����)�N��^$�#�<� I�˷�É,�ۺ�f�AS�Xv-�!@&H_�7��-�عKȍ���+]��V�5�$x�U
�)C.e��9�E���*z��
�3�Y2�	�z>�cו�K��<᭱X4��|�~�%\�f�GيK!�����ࠀ��D��k⚯8Z���rؐ ���	�Ćz��/yX8��7 ;{���G���%�j��ҷ��g���<�Ó�
�o�~�+Yy�B���Y��
�!��+��-,n�؟��f����l8�QZ�8� ��,���LRأ�,kԈ��XGwk0�����*��g�px��&H�<�N&������� },��	���$>�Ԑ�H��d������P����2a_��$@,+���#��-�筴d���^`�#Y`Ane�]�^�D�Bw���	zǝ�s��C$y|�����R+��c%00�h2&��<2�BdnX�
��v����yx���$gwػP�I������G븯�&���7��g���l~Y�����q��ݸ�����\w����9A`�d�����zL����@�5���e��3y�s�f�R�Y��K~�Y ���Ͽ����p�r����`w����$`������"�Ⱦ0څ�#���*K��	�9�a}<�B)�,���>����z<d��Ğ�I"�����>x<������~�	�'�D-˙����DLO"��7fQx����'�?�{�D�+�b��X���g;��9��M&�i�&/1�;�_�ҹ�e�j�$NB@�AHX|Y�/�s���z�i|�'ҽ�X*� m�Ȼ��ot�f�� Y�a�YI�gx�T����cX���;]��.0����
}l�i�Ay	/@H6Ce�D�r�g6���B�5��@��M|:;��;�����k52 I����ǣj]f�ae	A�֧�l���P9�#A�n��Xh�/���9��$���ʺ%� v��xc61�8i)A&��l�ZH��gbzө�HHpQ���v}�)�!�糘7�U5�k��~qf�V/#|��<��a�;ɖ[�|dQ�ktS�f92P��c~�����"M�=�{�C�0	��5"�)��X)<�b*<�Y,�( |��Z\�S]��R���
�oO��͎�Q�f�e;���26�����O����[���l�.��rC|Ǘ �<0ʞ�<X�2�[��2܌=D>���������t��T��w.���m�3�r��"�*<�ǜc$�W��`8@��<�r?�^`�8��l���&"J5�\�"=�7<���8_�M�,
�"�Y��$0zN��m$�}��p]v��mU��V�9��c���a��J�(P�i&��\+�xG��t����D�/?4��pϫ����'�~[�����T�� �1]� t�{��Z��u������s;��T��h�m�R��o^�b�}�Gn#@Nn~7&:2�c�b��+����3�dlAB��Ǔx=łN�$����W6� ��z2�ꂴCbRbk��{8��Y���6����x
I F�ls���>mx`�����{�����dW.�����Z</�<��,�`�sk�����,�xf�J�Y��ԑ����`��`�ə��T"��bO��ك�b^{�h7+œ/�Ɓ�>Уs64q�����0�}�������h��N;�@@_���y2�t��˙;  �>���aϼ�gm���8�I�(7-���j}���,��y�<E6���Q+��	�;�<Y�PN��� :�W��,y_�F&�z�K�r�x@ �/}������ zX?ѭ�^,G��7�x�o�A�4��Z-ر����yĨ��))�P=Fɲ�ƪ"�U��7ؒ�	������p_�WǠyG%�ek�b�t����v�@�k�-U%)X��z	���\,6b�Z����B�a�����S��`n��5_���F^���my"����� ����f����#&�t�U(�~��'
l�-ͬE$*���`C�|��Ȣl����	z����
�E�`Y��=�yZ� X.$i�ò�(j?Xk,Z�H#��-�uS�@rݟ+��㳾q<)�<�X�qz�
R3�+�����o/&1'����y>eY񂱾,3�z_~07�xWPA�� ��iV�A0��@����� I���J:�|�Y�8d�v� ��l�8�Q��9%7��<����GD��^S�DI��I��j��f �s�y�r��{�,P��C~Ф�s�3��� 	��Y~%R0U�O�c�_mC�I|�d���������u[����G)�f#�լx��x�&@��I�C|�5#8���D�l �}[�рL���ɫY%lW k��=`���>��(��*M�m��,�eBv{��Ѕ���v=�����Á'���͆�i�qqMއ{��A�z�BPe7���kٻC����A k,YXV���E�b�w��@."r�	��%rɖ�t؛o�o2,�rr��(��x��Z�������E̔�`y6��a@Y�����{r�dp�-�e���-�q	��ly��;�R�8��v8���,�%J qI�I:��W�E�Zس�`�ܯjX$a��]z.ki ��l���ck��
�OBI۝�ɂ]yJ@�������#I{���j �![t6�W�Z��غE��2����_=@3��\�V�4�ڕ��<�4r�{@��4�7����� -�^wT.m_����=Z�I��eIe�&O�%-� t��m�aSH��L����M��ne{�� �Ɨ� � �剱e�� Md�l)DK���ά�t�Q�6��d�q��-��d/�i4ά#5��%Ry��Z!�l%[�]ߝ�~7!L��"+����1ءf��� �/I���i�]V�q��8���� �N����	�6n�d��\��,����}zΎ[&��S<��%|.'R<�K~���;l*>��o�+�h��,ٺ"ﰴ�-��婞�Y�lD���	n#�&��_e�@�~�w���U����S�-��+�����B,5�����Bq�����Y6K��BX
F(O�����7�K��f�κ�_��i�7���<
Y��V20C�pG�M�_�X�]����<qr�-9nm��N�+��E䙧�2�4)"�o�X�����Jl<�vK��v2�@�Z/���	���V �C�L:��c刜˓»���@ߏ�b-������e=s���/�te���v�%�˓�r��x�}=�yN�~"��&��G� ׁ<@�f�J���6Ĳ��i�&L����Y�_�����B,Y�؞n8k�S��E�hz��s�V.���)�*�ú�mzA���ݗ`n�4��R�D��4l0�$��s�.
mhn� "C x���;�a���CNM�j��:��U��c>pL�e��!.�����Q��UH2l8���ԫ[�$��8�ʝ�����|O����&[��e���~��L��.� �u˃����4��g�V�r�a�Xa.0��8��Ԑ-�N��z�.i��Xn�/�Ю���Ǌ�i�fo ��CY �5,~k    ����
@X �\��ͺ1��#�L��+oKއ���d��{�x��(�g��>��&��Wm1j��Q���ܓnI�{9Ӭ=0�fOVӴL�Q�k���]���%}������$��9r����d�;�~9�Ų'5�g/����>#�tȺ�9�����.�r������|�.`ȏ$�ɴ�遍��xg4����J�.{��\�X��X	qٳK�����*֚5 ���Og�q������`��-��bd���]`<�^k�~��]��0|�[���+en�z�A]D��;ƞ�?R���1E����l���ҝ��,v"�����M =�\64h�����v������J]��șro�V%����dE���z,1��n�-R�,�-4�u"I��H�)
o�l��'I�9w�����1 ��@BO��f38��q������m������- �uX�D���>D���ɔ,�����=�wnBb�5O�@`�@*����t��;�*�[}S'�^8���p�mz޴s2~��p��6�������i�M9@~�	 ��#�z=�y�)�j:�3J	fsl�^��΁�
��Y�4�!������D����{����� ^��e+A�7���+��j9����v�V�B�G���π�!��R��Ӡ`\Ɨ�@���%s��tg�@���ӗ�өp!�� b�"vi���A�=��(J(�֛�yw�!�bȺ�cy3m	��1_�p��Y�� 0x�J�
�W��i�8TwH�<�t�(��q��N�.���a�@�� �O��v�mӔMU�A���<�y�}Ջ��K�4�9����'_�|��-�g�)�C���X�����`���VcI�����:`~�b�՚9{6�n��i/�EH{����z�`[|F�Q]�`s��!��9ݡ�C�0@	Y�Ù8�'����G9���剃�gt'�����}'@7 �)�l��>�v�����rl�B:��r�qpwn+���H=���zD��N�t���dC���3Wr߳�`@d��w�cۧ=o�𿻓3�ɡ7pU�=����ՇdwD3D�|`����D�0�h(�n�dl�H[��n�������{	XՁ�e^��cU�}L�[w��xG��e��>�$��V���L>dߵ��I�w���|m9�`w�S�,�a)w�ɓ�e��+g��ԾQ���vܥM�(��W|�=eD���%2z� �K��z��p��[h�"'�>�Sf�f鲠�'�v�j9�`iwzfm�
��G�;����B�@ Z��@�ʞq*ԸMm�j�V���ҪmV����$�f�x��_'ݶ���Aᢼ	"\-]�5L' d��R�s�1d�cB�6�w����fǅl#^�A��x�@�f�n�Y�<��%��X3	-���Ǳ�ro��,+�9ʬ��4l�	(�'9E@*�-�ᝥ���o�!�7��u�o�s�d�=��n�X.*W��9U~�:�K�`eZea1�Is�����4�O�H��l���02P� fBʽ߶��
� U�D�N��p���G���?��^�N�2BN�Q`Oq� ���㋈#d*��n��I�IKlHV
��>�h��e@hb6�a[�6�ۼrkSpN<Y���ˊ�W'Q�����X5��ܳȬ��M��{����[������7��G��O�C�˃T�o<�FF+v]r��J�p�#YLrL)0o��¢\D���?�XF����{�NS~���9k��uɡ�EF��B`�~��2Ͱqk�j:��p��ˉ.����S��l@z�$Vr�3}���}���'�g�Y��.�+tj6�L	���"p�}��yv��~9�݆<�r��~_���>�q*`7�l����b�Н�ጺ%�쑾G�V�Sa���~ZK'�B�R�0�B�$,V5���pw��	�Y�bЁpM[�xh+̥�2�E�|I�sǺ�V�_8RN6e	.I�����������Q����+�p#ϭn�"s��O���vv.����#�����"h�����`����޽UdfdfDd0��Fi�q�����'�|u8hϊFl�'^�R���G����3�!�Iw6��㬫;#��Yng�K�Ֆ���t�q)*I��&s�9���v����Y��«}g�Q����@�]2*�Z����>����k�� ��$Y�7�G�D;��l9�,5ܰ�cx%�I��g�	�S�o6W��� ��(��H,���n���Mi��?JYY��%눟+ъ�T�D�z���}+�e�y��0����.ƾ����B��.����lX�,��yn/dR+�L��M,e&����x�M=v��hmE�Iޖ�Ϋ�WrSޫ=��v�"	��m<�_�H�Ӓ��_�n��Uaߌ���Qj:ߋWd��8F� K�D<@
�ƕ!�t�Թ)	+�������7y:xp�b� �Je �r���� ��<�O��c����Ҕ��S�bż��)m�M�k	��b �t��*Q��R���q�Ǉ�DNG6��؍ �?�f�?�6�0?G����Φ��.^�p�K]H�Ү�,.r��*vP��Q���$�fe{�3I��ϋJC❤)p�7`B�)α��d�])��#��(0��:�${��Gr�͖P��F�&�7�N�,m�^|���SU��}��k��#��I�Hv�3	�'�q��l0v/�y��H�L��J��xJ>rE�3�b���G��ϫ\���W�([%�;'�`}�9">���nة����׮��3�'���I��>yAѼN5�)|�2�r���V�r�R_s�ߨ��j<B�b�J1���Jy;�� A�ш ���PmH�8%�A}YEL~-І�$z���� �HqC@'�H��R��#_	�6q��x9)sG�yEZ�^���C�B���(����� ��_։Љ�1W�ex���߶CMFx/�8]���2��1��0�3#�?�D���O�5H�4v�k򲝶�u����������,L{��r C�z�j���Q
�\�q������3~��;GQ�V͔�$2�.Sջ35���j:D�6R�'n�ZW,<�sE���b�v�\;*�YII}�V �
���X�z��쐭PϏ�F��o�MR��&���-����0��Il��O�R��2��T^x�k�rR9[�2���T$? m�W$��Vo�� %��
.:�%����'츂���@��-��g��X�h��G�\3I)R��;���AE��z&�M��|b��P7�G �?��
Yo�󌽵�*�_O�a�Dz�8W
|�3�d��g�7������Auuj�3����ǝKRl$i֡ŉ�e� �k�A1�e������m���"����PU�?G&U��d�))ʽ���YUJ�S��À~<;.8�K��& ����H1�_!��#���+��D�8�|rҲ�S���o���VsZgO޾ɱm"]�2{�kQTod�{�[��]�D�;i��3��5W��$;	I}Q�~�1u�`&�Y���؈!��ߢ[	�v���Q��%�d���n5�w��79]�X�ێ$�H���;Y��_�ፖv�D�LȸF4�>䢆;@���@q�؜�]��̉�D|�-R�m�#�ȗ�U���K��4�3�dy`���s?y>U_�j��.�@F�r��%�־mN~�.����
U�uS���C��i�1�6� "�� ���s��@[�V�<���k�l<Pŭ��M����}��1 ��xZ{rnN ]�_ L7.f0�q�l�S��=B�#,�H�bE�3y�$·�\��X ��בA�${��* � c�� ���S�:��$"��G���W ��ƒ�"�n� ���؅�K�W�i��IA�BD�P��Xf;�1�6n@}�qE��7���OɶV���T�t��.o�5�SN���%k�".7�u�ZR/�ᣆ�#=\���@��Y@}� X�9g��������Z���6ù�5E�pɩ��Ny}X��wq��}W���Y�e��W��\P����(�    }��f@�s�4!�����9� ��D��RBq1�E���n?�����m��QI�������)wj�������Uq��ב'��.�('�c
��Sx��5	�����@\��K�u✜+�Sa75J���k��9�Q���M`�OIJ��]*�fd�~=�Շ< '-����A��C"��X�rl�j�"m$4��=�+��8��l�k9P^'caQ��!�Zˣ%�Ad�C�L��\�h{�����t�V��ȝ� c�'"������3����Q���n�1��r�ߨ�ۧh�s�#
���3�|�F�0����r��������מ�H꘯,`�)q�2oD���FD)�xD*�)a�j��?_�.�  �Z3h�0�lΞ�܀y"�1D�iQ�E��y���l���Ƌ�4��/_��S���K������0�G��*��� �:!���&�F�.\麦/�N��!'}��ڕ���7SR��{��D��l/�~ux�j�P��{d<k���a�2�+���"�%I���%f����"i�D��;ǁ��P^ƹJ����/D�'u�_�lV+2��[Fv<��o�6ǹ�\#�;/��J�"�:������,�������U<�F��E�����3�I J��GA��ض�PNHW�Q�Ye)Y���qZ+K$΄p2��X7��4I�}9j�����)����o��� h��u��O
/Gl=�
�QM�Bu'���%J�Ǒ��<m��Cz[% U�~�&洤�۩??-Ó�nA�[p�Υy.�9�$�׮�P!O�"h�$��W�C�@�>~�� �u������ r��_�G��]~��/���X�Y:���}��b�B���ǧ ��q�e�R^�;ii���Nv�2+|�|x���LZ�#Lg]�%�V����˹������t:Q�q]���uUK�Z��ȟ�Q߁��xe��'�'�P�xQj��p�lDɘ|6s�&��/��g�Cu��&��򥎖z�;P}��b+���C,�i�V=E1M/�1ee��]MO�X�h�V��Wf��J"�����J�X�3��J�Py�QE'�Z��l�Lc��[UP���M9���Ɩ��U�E:t %i���L+%e.�:��4	�x#�jQ(E|��KJа�²R�]W�G� ԼW|~	!���c��8���G�~
R7���M�x�T�խל�K��E��q���5�׬�i6�1��+��5"�$Kȓ�le�5������F��΄Of�L6�����.���Eܘ���&'L�)��6󔽪u��͉x�W�%����rT�H��۳����L�(O�9��!9��dʱ��w�m�#@�.3�lIp�I�}RL�����7��D��zeB[�}ޮA��\��W����%��a}��5�����f�粭�*~��j4C�Tg�:������/ 8��
�2�@ӏt1]s�sME8��yG��(��Jzv~��1�/�|�����'���z�߬h�� �'X�j둮W��d|�5+ ��H�=��xH��㷔Q��f���tO��-
x�R��(M���c̶M�)�E��q����ᅔ��E��H=�q�uu�_ɫ�&��ե��D�&��e�.Am�*!�U��V>����}Rlڕ���ַ/�m��\5�'��qB����8���:��Fʵ�'}�m� �lx���$$S��V�̅�㔏ZI�-9��y�"��9m�2%2z-d����W��/�l
��"�^s��d(RC�h�ܙ���t��N��]{,�BP�#&\R_��,DĖ�?z	���ѤZ��j�9��3(�۝���� �QUD�h2��.2b��_3�
e�mS$�'�(C�:KF�]���� ���>_T�|V��W�ϛ���D���&#���p�[w�V�eh|�ZZD��9i2�hZk{�K�i��D#4�� h�%Rg���d���i�\T�tc�\p��� J�Ӛy߈����C���u���u �m���Y-�e}�_�y�q�c+���R�
f�mdِ򈴞��ݸZ�@)�dЋ��� �&{W�
���k-UUQ79�����]�Nn8���ç3�_���p!�dԕ���s
4ޜO ��%�4Ad���Ň&m�-������~-�y]��2�/-���B��B��Z��`����-dd�L�|A��� d'�79��@��ruɛ3�'E��J�J��P�-���	����D'Ow�@V#A�̧ALW��q^��ύ+D�׎W�Q�Z�p�f9�1�O��n�?F�Sj��@*'`�Ѫ#1yMqs��|���q �U,�M;��am��L� �U���ͩ�������LY�"%���hj�hANuJ���h�ɇ��~��lyΤ�Ƞ�"-�hr,B=D^|�.ǩل�jȾ��є%�\����|!���00d��S[L�8YɄMz���S�%�+si}-]��g��&P�>@zB��u�u�H���.�^(�����0j�$�̨-�<�Q���eOHK�aVY@(�O�:�~�9�/��y����<NƝ�iC	���*U}�A�z�v��A��|
@4��D��?E��Hj2ː_H_N���r!)b+ώ���@�^�Q��u��AiC�7#�E��`��gu�VG��d;�+�\�S��8����	�V�b��§�h�G�ٮWBEA�{�0������e���u��o)*���8f���Ҥ_�Ofu㫄 �!�vS�:0�J���#��`N]ňf��@�J���Jx����A9g��\���s�> ة]W ��1�e]�B[Ö�
��{Ų�|�uh؋�P��E�z��ΊF4�_~�I��gk�����16���>�Z�;8Lr������stU)�x�f�FpU�Jx�������U���c��)f����msG���G��w�q�{�Ye9)w[�gc݄�3�%����N5�;�>R��ݹ���[l����7:؝(Q	�쾭q����"�i*9|���[��欞�Mh�E����qy� B�(\ϙ�>/Tj�����G'A�����f�F���h_ɗ:�W\��F�P|�N -�i2�-ǵ�R�+�_AX�s���2Qo�t�T�h#�������Wl��L2��8w�r�������J�o���"��ާ�К���h��Vm_�i��c� qQ+E՗z{�֖-��Kk�7G�U��Y�#��$��#�6 \��}�t1.u�䳊u�H��n��,��n�ٵơE�H�	r�A�o-��뇢k��Q�M���c��0mtnk��a�#���)bHm}oCJ�LX�^}���UX�N�k
��Ki>����6�Q>�����'c���y'��i���"_&�Mdn��FU�F�"�'U,oS
�D�Os��ÊF�� P���`���,���#��i��f�ѕ��j/��TQ���Hp��=(�g�U���eћD9	
h�G>ʹ��b1��%��7ғ���C��\��sc�\n"��X/OH��c94��*	��w��gL3g�oڭ{e�Yt��[@S�t���=�n���@8���$F)䯣=pO�N,?�a�j-�������C��ޮ;��@��&!o��ۥ �P޹i���m��T�2�� .�L�#/O���M�Q�m�#���O�FiF�B�,h�=�?�4��0Y�{����BCĐ�v6��5�yIj���ZV����2	L���z]��R<2}�w����UO*_�����&������2�1���
�����\� ��z��`W��+��I��e1d/���S��1����H@yOR>7Al�x�����_�KS �����<k�WD��p�e�h[�pMأ�l"�_u���
�F��ե�q�t�s >~�}�vҨ��0��A��P�)G�4�uq�qd��L��洓r��lN7Qi�vI%9v��a�p]<����sm[Q^9��q�ؗd�z�wH�4|�}�C{��jR嬳^!<8L��X<�z�d�"��]0ſ�'2|��[�2z�t\_��    ���7�8�s� �ӧ�]d�Y�D��>� 9�ڿ�h��p��2��b������8o:�|�
���5�-�Cp���O���e9�Y>��WW�l�,.)��-^�^Q:�/�UXǑȲ4Xt(f�d^��u[�4���d�l�r��<@���G+QJ$ѠRL���ظ%��MP0+���ENpȹ�_��/���~�"xN�a.��hJ��V*B=���s�'��p��(G�R�F�Fg��M�׫>�9�{�^1�$  mdan��Z���U�8���(�Pq=���6��<�hy�3� U��{��g��Rɺ-��kyf@�u�;��ձ��.��,kr����h�b!�}"H�ߏw>l�#Q3�z��~�����wd;]�~xp�_�ܲ��.B�ή��]5�ZI]p��D��JogS_"�h�Ѓ���s���g�n����T$��"[�~�\ײ��+��������2�[�j!x�֤�v�D�ʾf�Qd#�\�n�H6.�/	�D�Q%��QC�<��[혦�_͜�4~H
��;,��#�#�Ү��@������ISt�z����<���>��ǚ�K��l�^ۈ)���Ԓf��9qK��'<f(�-�7�=mM�m�A�������
� a��k�vm:��-�U>��ٵ+��P��|�-�^��5N���G����{R�f�'�+���k��m�ru�\��MR�\1� ����#=&y2v�(����|.�q��tJ�7��3���� ��E�8T5�P_��4P%���*�1�'!�Brڴ-zb%A��M��O��T�&.�)*u&�v�yT�^k�e��Aq��������n��U)�	8R$4����y��i�_��$�B!d�8���S_�"�G����fTMΥ�i�[pru�)3�)r��!u��t)�	v��u<y����9��@���3�hF�u}��|�oQ��rl*7�:1��&
k�n��O%�u� U��z��wA��v�V��*�h4
S[,1���@���j�>Լ��ɣ�v����,!����X��&%ڻ�G�}y�תN��Y Rc�>�Hd���G�kѱ�����Z�kK�e���H�wo\!�ݜ����5#��k\�I|������'߳��ނD�݂6�D`~�Ʊ��mF�r]��a����s3�t�;�D�A.j�H!C�G���ߣg`���Mڹ����>�C�փg8�r���i�1����>~��kxzDj��		ʟȱ_�S�h˚��P���z&=U}��;��5�����3�X:�:-'\H�BQ�U�|��V�ƍT�RVI�Mz����o�s��������O҇R���>w����l9Ӄ�et1;�r��M%\nM"�"�c�,p�!߂˦�K!%�*�N�I@c��y��I�]��u宗�?�����FM��
���Qp���4]\�t�ς����B.q B~eZ�����M�`�������3�p�!ˡb�C�M~�v&�$ 7�$#����Ta��FЧC��¬in����;�l�鋪`��/a������GЎ�~������_��E��[G�Z�
Ĵ)��q�U������e���kZ+�<��"�vnu �K�W�ϐϖ���] 5RK��w�+Os���:� M�����.�z������:~�s�V���l%���7_���x��ؼ�n�q)�ΡG����\*AJ;)()ځ:x�~Pv�M�w�Jq��m�Zr��Ӧԭ j���q�%.��r�,�(.��y�
Qs�t�jĬ�UR�v۵�K��\
_�����,�.|�ϣ��9��+?q�y=�r�M>��L
�z 0��۞&�zm���a:O�k��N|��0�Sp��Q�F8 _���e�b�=p�c׽�v]R��jr���%\}�(*����Q9�V���l�P�֠��x'��)@u����xu<�f�<3��� A�'pw(�p]�ݦC���W�A7��]�'PsZe��wol��@O�����i�zi�9��pҦ����nnh��8r���L�����e�jSǙ�y��G��3�H*9C�=~�PUV�cZ�&��C��x�?������T�V|�v[e�g�c�E�^q�C*JQ�:��NK����"��~���y/��I��ǔo-B\4�!�o�U��
'�[��+�r��}�J��T�
�&��M��z�0���u�zsmO���5��
�-��z��!_�3WP�*�BlU�d�k���dl������'�(�<���ُ��yy.�SH4%��y8��)mR��#�:Ht))\��*5\�w.��ˠH�j~�E��<�1n|뮐���*_��֓͆�k�;��!5)BJ]����6���K���䲤�}gB%a���A'g��8ue�|�E2ɍt��;],�L&辪.pG��c��t\���UwwW���橺��.C�c����YɾSZN�N���P+������C��}|� `��r@��
W'J��t� ��8���W�;pW��]h��8�n=z��PѦ{4���XGƥ�%�")t�w"v�c���T�H=���ִ�@y]oM�iS�BP'O�w�� �>�����RҞ�E�9��٩�5.)3��y�Dw�7����%r���Pt|�W��$ᒵ��z����.��%ݡ^Ъz��1L���TD���.y!l>>mS<�MN�������IQ�'���@^�W?$����F�;/�ⳣO��Z���H)w�җ%����j��޶ܘ-�kkLr�R&��:�W�_<�V���
޼>�������������U�x>�����e㢥)�Ԃ�W��K�nњ���Y��{&�XQ�wL��@�u~Y	��R�:��sK��;M�-�Kr%o�6w5�~���d����Lр�0�9��յ�|+74�~t8�U�:빧[��J����##�p|��'�M1�oV�M�k�"�ok��l�(]Iz���,�y�P#����Ϥ;)�sߌr��(�"��+�ljJP�XN�zz��#-�_]N��E����t��O>��S�EͽА�^����+ķju�����69v��}�N`��)�V/醕�K՞��p�{�決�lɕԦ�N�9�	|�� �F�qi��	�A�.����}�A��i�厜T�Aw��%9�u���`��Ң����D	�H�p���,�\{����v0�T�e:5�au���U��j�Ak���d7pz�yTJ�G�sۚr7��Q�U�}��17�ٵ'�y��"���T�U�O���I]�鮠 ��qy��;⊌�T�]<���̣Yw���m�?���ѽY7A�o���wn�%���z�}�>6G@��C K��}.����8���C�ץrn_�E�����V���F��u�塏���	�^�D��|���Z��&�#D��3K�kbQ	CaK΍F�e�󘗯���i��0ne�WLn�K�x�rur�-)�I����E(Ԉv���QA\2�������,j��)�BT�`s�TD�)H㳖�p�.Z�E��v��?�^�VH�[�k{�{K���4�p�T�t+��ӽ����$�85�F��y���W���s�!�E�<�Tv���NU�d��DP�3E=�5�Օ�Z���4"�j¹\�����;⤭W�WR^Sqa?���٣C7���-A ���c�'���2Zl�9͆zuISt��9�RU�d����y�
߰���gc��a���!D�hAĻ$�HZ�oh��� ��S�)>[~@�3ۂ�.��U+}9n�3h��.TS�g��㾮��\�ͭvz2�%���߻z�8˶uj�Ov���0y�>7h֮��]�H�������s N,��q��[%��y� ��OF�I*�բ</�"�e-U����-�L��=�z�w�?�ہGc���a(](��0���_#�\�W��)݄�d�h$�����5�k�/'v�F����3E8�U���{�=~�������%Q��ָn�Inaɮ�%�ٟ���D�4'_H�)�Ytm^�����1�+1�u��T�:�[��`n���i���;�����X`s�����K�����u�PZE�Z݉�%����V/(%g�S�J<cNcQ<��/m�ߤ�����ą̎��� "  �����kN+e�e
|*A��B=��Ft��핞��Ixԭ·�n�E��0Hx��"p����]v�u*q�_U5�����j{��"��b2_�WŶ��n�U���P����h��E#<��ʫ�1j�RrcZv����p��nd��Y!$<w=��nt�`~�u�[�B
z���}��w�f��NN�$�����.Z��px;��u�C�j-L� ^}��K��Đƒ[�g��M���f�\����=�-b�D�`^?X�ƹF}�e�R�_�dO���!J/M��I	�䋪��Y�!Q�%����^]5d_w47���$s�Z$	�����9&��,ŭwy]�_}Pu�vg�*������y�4:����F]�]�|l�*�t/�G(���L:�!���$u���Tc��A��֕T����	�0u�|�����JU9��Qy���x�g��Pch!�A%��+)�$�>�u7r���u� �F�;�M�3�$|����)(	�w��q��$>v�j�3�D�mV�$56��V��DG��\o;� ���u�xy����7��^� �X�.Ҿ��]s#2~j׺�����^��*�Y#G8�l�h��ā�I6��e�=�G��79�MT1���E�=�-�L�H���F��U��A�/��@�b�6SXv�����xĚ��=J�g���2�_�"��^�?�����D��l��*�g�]]1���Վ� O����3�	A��Ȳ�N����?�Fp���?���������Ͽ�{���W�������U�      �   S   x�e��	� ��d�r��]���**
�s�q�/i�D2&�p������',a���Y!���/87ʱQ�?㲉�U���t      �   +  x�}VKr�6]�O��]�������dU\��U6M#!�t�r��� �X�D�fv*�C_?v�V��׼цm�0�<�ED��M��ѲF3� PuG8��Y�i��\�C��_{n��8�7�P(ɑ��f�I���w�F� ;h�a��C�DJ��J��l!��B���V}�w�VhRjVB
�lh�3;!-��'�àI
�k�G���_�9�{r����M�!��|­��3����J�J᭣5��Kx��h���F����v�CE�`���z��KH'x���r׻WKÿ�Z�\u��4���`�I>�Cr=�h��f��&"��#
�A�W�"��	���#�|h�{?�`{@���[/��dl���Q��9��bX�J��ć$��2q� Ee[b�3Z
�ŏ0�ru��8����v}��,�0/.Έ�G˜Zg���HeǍ9,F�� zN
��p�s��?~�(��,U\K�j����� �%�U��E�2j}[Q&���Z��d�6X��X����Y�JЄ��� @��Q����N����/��9�/G�}e��7��F�.$�����h'rm��x�W����̊�&��:`���lO��ٕ� Sx�w��EOBvF��1�� ^�Ru���I�F�H��(�@�!c�[j4�x|���4/7M��y`�K�#d�,������<��'9��S韞Q
}�D��?���[�$õV�x�e�����|z�ã��l�M
�{r�_�.��e}qi���B��j�Q�	��P��uO���L��1��}~6���ǈf�֧�}���dt�?kq�U6�:�_f�$m��>6�<���bB.B��oRq �R�J{��8��2"�����f��b��$�����.����+�̕>�%,��[d~ �5Z��ZH���κ�/��i'3�A��T��r��r1\Ĕ�S���yJ�^+.�{9?�L���T_\X�΂cҿK����yKI[�j�vp��
5/��`���)�!�M"��nzi
�F�$ٵ�q��5����
7      �   �   x�M�K!C��0]|D�.s�s:B��W	������I	:!G�	��^�����[8FR�P�l�mI���PN��K1��&wf�3L�l�R4Ψ�*' ֦�o�S-&,+�@s����0A�c��6a�`�纶�g����
`����Z�;�9Y      �   2   x�32�46�42�37�4�2�@���<c=NCτ���1����� ��      �      x������ � �      �      x������ � �      �   b  x�u�Mn�0���)t���/m%A��ֈ��IӔEe�PҋuD�"��;��4�����dq�q������&I��m����c��8-Eٸ���m[�4��H�1qˑK�y�3\n��q2޳�_���U�p�!-�ޣle�jV��{���%���3W�x��?���BTXf}��@)��;��7С`v��/R@�K��Y�-�3�Q�B&��O ]�,�lezO�V��}�6�J ��T$�TJEm �#?��(����{f/9�p��Qv��'�ՖS'1X0���j�̑Nȍ�_������K8s਑:��@�+ALN��@(:��;b��
tO����9�Yj0��y󵧺�zd�#����{R?$P��Mʜ������}[)�Wli�#T$A��4�V�VTw0�V+�9�_����_�k��'���=XQ�%�b�0T�`�(9zԀ��.I8��?1��n�T�Euu�#�o&�d7�"�"�N���J��;m�p%�s�}��|��䰻��xpѬ���bUςt��R��G��⠬���n��{u�et@u&���r}g�{�,I�%W�!�b�:�]ҧF��+@UtS��X��f�X�IN�`      �      x������ � �      �      x������ � �      �   �   x�]ϻB1�<�����Ҳ� ���������9u1�/J+2"�#�k�e�� �j�e��������À(j4f`
���;��
t��1�O��V��9?Fbk���s�z� ��,	��8lqb�����y���m"��1/      �   K   x�3�LL��̃�Ɯ%\f�ɉY�IɉI��FƜ��i\���I�p�9g^~Y��o�p�������1z\\\ �E�      �      x������ � �      �   W   x�3��t�0�0��tv	�r�s�	�4202�,(J,I��4�2�L�H,.��ttr6426�t���GRkhT�Z���i����� �n�     