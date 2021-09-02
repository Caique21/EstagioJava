PGDMP     %                    y            estagio    13.3    13.2 L               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    25498    estagio    DATABASE     g   CREATE DATABASE estagio WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE estagio;
                postgres    false            �            1259    25499    acessos    TABLE     �   CREATE TABLE public.acessos (
    acess_codigo integer NOT NULL,
    acess_data_login timestamp without time zone NOT NULL,
    acess_data_logout timestamp without time zone NOT NULL,
    user_codigo integer NOT NULL
);
    DROP TABLE public.acessos;
       public         heap    postgres    false            �            1259    25502    acessos_acess_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.acessos_acess_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.acessos_acess_codigo_seq;
       public          postgres    false    200                       0    0    acessos_acess_codigo_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.acessos_acess_codigo_seq OWNED BY public.acessos.acess_codigo;
          public          postgres    false    201            �            1259    25504    cliente    TABLE     �  CREATE TABLE public.cliente (
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
       public         heap    postgres    false            �            1259    25507    cliente_cli_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.cliente_cli_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.cliente_cli_codigo_seq;
       public          postgres    false    202                       0    0    cliente_cli_codigo_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.cliente_cli_codigo_seq OWNED BY public.cliente.cli_codigo;
          public          postgres    false    203            �            1259    25509    design    TABLE     X  CREATE TABLE public.design (
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
       public         heap    postgres    false            �            1259    25512    endereco    TABLE     q  CREATE TABLE public.endereco (
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
       public         heap    postgres    false            �            1259    25515    endereco_ender_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.endereco_ender_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.endereco_ender_codigo_seq;
       public          postgres    false    205                       0    0    endereco_ender_codigo_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.endereco_ender_codigo_seq OWNED BY public.endereco.ender_codigo;
          public          postgres    false    206            �            1259    25517    endereco_parametrizacao    TABLE     �   CREATE TABLE public.endereco_parametrizacao (
    para_nome character varying(50) NOT NULL,
    ender_codigo integer NOT NULL
);
 +   DROP TABLE public.endereco_parametrizacao;
       public         heap    postgres    false            �            1259    25520 
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
       public         heap    postgres    false            �            1259    25523    fornecedor_forn_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.fornecedor_forn_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.fornecedor_forn_codigo_seq;
       public          postgres    false    208                       0    0    fornecedor_forn_codigo_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.fornecedor_forn_codigo_seq OWNED BY public.fornecedor.forn_codigo;
          public          postgres    false    209            �            1259    25525    funcionario    TABLE     �  CREATE TABLE public.funcionario (
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
       public         heap    postgres    false            �            1259    25531    funcionario_func_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.funcionario_func_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.funcionario_func_codigo_seq;
       public          postgres    false    210                       0    0    funcionario_func_codigo_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.funcionario_func_codigo_seq OWNED BY public.funcionario.func_codigo;
          public          postgres    false    211            �            1259    25533    parametrizacao    TABLE     "  CREATE TABLE public.parametrizacao (
    para_nome character varying(50) NOT NULL,
    para_fantasia character varying(50) NOT NULL,
    para_logogrande bytea,
    para_logopequeno bytea,
    para_email character varying(50) NOT NULL,
    para_razaosocial character varying(50) NOT NULL
);
 "   DROP TABLE public.parametrizacao;
       public         heap    postgres    false            �            1259    25539    telefone    TABLE     �   CREATE TABLE public.telefone (
    tel_codigo integer NOT NULL,
    tel_numero character varying(11),
    cli_codigo integer,
    func_codigo integer,
    forn_codigo integer,
    para_nome character varying(50)
);
    DROP TABLE public.telefone;
       public         heap    postgres    false            �            1259    25542    telefone_tel_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.telefone_tel_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.telefone_tel_codigo_seq;
       public          postgres    false    213                       0    0    telefone_tel_codigo_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.telefone_tel_codigo_seq OWNED BY public.telefone.tel_codigo;
          public          postgres    false    214            �            1259    25544    usuario    TABLE       CREATE TABLE public.usuario (
    user_codigo integer NOT NULL,
    user_nome character varying(45) NOT NULL,
    user_senha character varying(45) NOT NULL,
    user_nivel character varying(5) NOT NULL,
    user_ativo boolean NOT NULL,
    func_codigo integer
);
    DROP TABLE public.usuario;
       public         heap    postgres    false            �            1259    25547    usuario_user_codigo_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_user_codigo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.usuario_user_codigo_seq;
       public          postgres    false    215                       0    0    usuario_user_codigo_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.usuario_user_codigo_seq OWNED BY public.usuario.user_codigo;
          public          postgres    false    216            T           2604    25549    acessos acess_codigo    DEFAULT     |   ALTER TABLE ONLY public.acessos ALTER COLUMN acess_codigo SET DEFAULT nextval('public.acessos_acess_codigo_seq'::regclass);
 C   ALTER TABLE public.acessos ALTER COLUMN acess_codigo DROP DEFAULT;
       public          postgres    false    201    200            U           2604    25550    cliente cli_codigo    DEFAULT     x   ALTER TABLE ONLY public.cliente ALTER COLUMN cli_codigo SET DEFAULT nextval('public.cliente_cli_codigo_seq'::regclass);
 A   ALTER TABLE public.cliente ALTER COLUMN cli_codigo DROP DEFAULT;
       public          postgres    false    203    202            V           2604    25551    endereco ender_codigo    DEFAULT     ~   ALTER TABLE ONLY public.endereco ALTER COLUMN ender_codigo SET DEFAULT nextval('public.endereco_ender_codigo_seq'::regclass);
 D   ALTER TABLE public.endereco ALTER COLUMN ender_codigo DROP DEFAULT;
       public          postgres    false    206    205            W           2604    25552    fornecedor forn_codigo    DEFAULT     �   ALTER TABLE ONLY public.fornecedor ALTER COLUMN forn_codigo SET DEFAULT nextval('public.fornecedor_forn_codigo_seq'::regclass);
 E   ALTER TABLE public.fornecedor ALTER COLUMN forn_codigo DROP DEFAULT;
       public          postgres    false    209    208            X           2604    25553    funcionario func_codigo    DEFAULT     �   ALTER TABLE ONLY public.funcionario ALTER COLUMN func_codigo SET DEFAULT nextval('public.funcionario_func_codigo_seq'::regclass);
 F   ALTER TABLE public.funcionario ALTER COLUMN func_codigo DROP DEFAULT;
       public          postgres    false    211    210            Y           2604    25554    telefone tel_codigo    DEFAULT     z   ALTER TABLE ONLY public.telefone ALTER COLUMN tel_codigo SET DEFAULT nextval('public.telefone_tel_codigo_seq'::regclass);
 B   ALTER TABLE public.telefone ALTER COLUMN tel_codigo DROP DEFAULT;
       public          postgres    false    214    213            Z           2604    25555    usuario user_codigo    DEFAULT     z   ALTER TABLE ONLY public.usuario ALTER COLUMN user_codigo SET DEFAULT nextval('public.usuario_user_codigo_seq'::regclass);
 B   ALTER TABLE public.usuario ALTER COLUMN user_codigo DROP DEFAULT;
       public          postgres    false    216    215                      0    25499    acessos 
   TABLE DATA           a   COPY public.acessos (acess_codigo, acess_data_login, acess_data_logout, user_codigo) FROM stdin;
    public          postgres    false    200   Kd                 0    25504    cliente 
   TABLE DATA           �   COPY public.cliente (cli_codigo, cli_nome, cli_cpf, cli_rg, cli_datacadastro, cli_email, cli_ativo, cli_alteracao, ender_codigo) FROM stdin;
    public          postgres    false    202   �d                 0    25509    design 
   TABLE DATA             COPY public.design (desi_fundo_interface, desi_fundo2_interface, desi_fonte_interface, desi_fundo_botao, desi_preenchimento_botao, desi_fonte_botao, desi_tamanho_botao, desi_fonte_entrada, desi_foco_entrada, desi_tamanho_entrada, desi_opacidade) FROM stdin;
    public          postgres    false    204   �d                 0    25512    endereco 
   TABLE DATA           �   COPY public.endereco (ender_codigo, ender_rua, ender_bairro, ender_numero, ender_cidade, ender_estado, ender_cep, ender_complemento) FROM stdin;
    public          postgres    false    205   �d       	          0    25517    endereco_parametrizacao 
   TABLE DATA           J   COPY public.endereco_parametrizacao (para_nome, ender_codigo) FROM stdin;
    public          postgres    false    207   Re       
          0    25520 
   fornecedor 
   TABLE DATA           }   COPY public.fornecedor (forn_codigo, forn_nome, forn_cnpj, forn_email, forn_ativo, forn_alteracao, ender_codigo) FROM stdin;
    public          postgres    false    208   �e                 0    25525    funcionario 
   TABLE DATA           �   COPY public.funcionario (func_codigo, func_nome, func_cpf, func_rg, func_email, func_datacadastro, func_ativo, func_alteracao, ender_codigo, func_vencimento_cnh, func_cnh_frente, func_cnh_verso) FROM stdin;
    public          postgres    false    210   �e                 0    25533    parametrizacao 
   TABLE DATA           �   COPY public.parametrizacao (para_nome, para_fantasia, para_logogrande, para_logopequeno, para_email, para_razaosocial) FROM stdin;
    public          postgres    false    212   �e                 0    25539    telefone 
   TABLE DATA           k   COPY public.telefone (tel_codigo, tel_numero, cli_codigo, func_codigo, forn_codigo, para_nome) FROM stdin;
    public          postgres    false    213   ��                0    25544    usuario 
   TABLE DATA           j   COPY public.usuario (user_codigo, user_nome, user_senha, user_nivel, user_ativo, func_codigo) FROM stdin;
    public          postgres    false    215   ��                  0    0    acessos_acess_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.acessos_acess_codigo_seq', 3, true);
          public          postgres    false    201            !           0    0    cliente_cli_codigo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.cliente_cli_codigo_seq', 1, false);
          public          postgres    false    203            "           0    0    endereco_ender_codigo_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.endereco_ender_codigo_seq', 11, true);
          public          postgres    false    206            #           0    0    fornecedor_forn_codigo_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.fornecedor_forn_codigo_seq', 1, false);
          public          postgres    false    209            $           0    0    funcionario_func_codigo_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.funcionario_func_codigo_seq', 1, false);
          public          postgres    false    211            %           0    0    telefone_tel_codigo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.telefone_tel_codigo_seq', 23, true);
          public          postgres    false    214            &           0    0    usuario_user_codigo_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.usuario_user_codigo_seq', 2, true);
          public          postgres    false    216            \           2606    25559 $   acessos acessos_acess_data_login_key 
   CONSTRAINT     k   ALTER TABLE ONLY public.acessos
    ADD CONSTRAINT acessos_acess_data_login_key UNIQUE (acess_data_login);
 N   ALTER TABLE ONLY public.acessos DROP CONSTRAINT acessos_acess_data_login_key;
       public            postgres    false    200            ^           2606    25561 %   acessos acessos_acess_data_logout_key 
   CONSTRAINT     m   ALTER TABLE ONLY public.acessos
    ADD CONSTRAINT acessos_acess_data_logout_key UNIQUE (acess_data_logout);
 O   ALTER TABLE ONLY public.acessos DROP CONSTRAINT acessos_acess_data_logout_key;
       public            postgres    false    200            b           2606    25563    cliente cliente_cli_cpf_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_cli_cpf_key UNIQUE (cli_cpf);
 E   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_cli_cpf_key;
       public            postgres    false    202            j           2606    25565 $   funcionario funcionario_func_cpf_key 
   CONSTRAINT     c   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_func_cpf_key UNIQUE (func_cpf);
 N   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT funcionario_func_cpf_key;
       public            postgres    false    210            `           2606    25567    acessos pk_acesso 
   CONSTRAINT     Y   ALTER TABLE ONLY public.acessos
    ADD CONSTRAINT pk_acesso PRIMARY KEY (acess_codigo);
 ;   ALTER TABLE ONLY public.acessos DROP CONSTRAINT pk_acesso;
       public            postgres    false    200            d           2606    25569    cliente pk_cliente 
   CONSTRAINT     X   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT pk_cliente PRIMARY KEY (cli_codigo);
 <   ALTER TABLE ONLY public.cliente DROP CONSTRAINT pk_cliente;
       public            postgres    false    202            f           2606    25571    endereco pk_endereco 
   CONSTRAINT     \   ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT pk_endereco PRIMARY KEY (ender_codigo);
 >   ALTER TABLE ONLY public.endereco DROP CONSTRAINT pk_endereco;
       public            postgres    false    205            h           2606    25573    fornecedor pk_fornecedor 
   CONSTRAINT     _   ALTER TABLE ONLY public.fornecedor
    ADD CONSTRAINT pk_fornecedor PRIMARY KEY (forn_codigo);
 B   ALTER TABLE ONLY public.fornecedor DROP CONSTRAINT pk_fornecedor;
       public            postgres    false    208            l           2606    25575    funcionario pk_funcionario 
   CONSTRAINT     a   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT pk_funcionario PRIMARY KEY (func_codigo);
 D   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT pk_funcionario;
       public            postgres    false    210            n           2606    25577     parametrizacao pk_parametrizacao 
   CONSTRAINT     e   ALTER TABLE ONLY public.parametrizacao
    ADD CONSTRAINT pk_parametrizacao PRIMARY KEY (para_nome);
 J   ALTER TABLE ONLY public.parametrizacao DROP CONSTRAINT pk_parametrizacao;
       public            postgres    false    212            p           2606    25579    telefone pk_telefone 
   CONSTRAINT     Z   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT pk_telefone PRIMARY KEY (tel_codigo);
 >   ALTER TABLE ONLY public.telefone DROP CONSTRAINT pk_telefone;
       public            postgres    false    213            r           2606    25581    usuario pk_user_codigo 
   CONSTRAINT     ]   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT pk_user_codigo PRIMARY KEY (user_codigo);
 @   ALTER TABLE ONLY public.usuario DROP CONSTRAINT pk_user_codigo;
       public            postgres    false    215            t           2606    25583    usuario usuario_user_nome_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_user_nome_key UNIQUE (user_nome);
 G   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_user_nome_key;
       public            postgres    false    215            v           2606    25584    cliente fk_cliente_endereco    FK CONSTRAINT     �   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT fk_cliente_endereco FOREIGN KEY (ender_codigo) REFERENCES public.endereco(ender_codigo) ON DELETE CASCADE;
 E   ALTER TABLE ONLY public.cliente DROP CONSTRAINT fk_cliente_endereco;
       public          postgres    false    2918    205    202            w           2606    25589 -   endereco_parametrizacao fk_enderpara_endereco    FK CONSTRAINT     �   ALTER TABLE ONLY public.endereco_parametrizacao
    ADD CONSTRAINT fk_enderpara_endereco FOREIGN KEY (ender_codigo) REFERENCES public.endereco(ender_codigo);
 W   ALTER TABLE ONLY public.endereco_parametrizacao DROP CONSTRAINT fk_enderpara_endereco;
       public          postgres    false    205    2918    207            x           2606    25594 3   endereco_parametrizacao fk_enderpara_parametrizacao    FK CONSTRAINT     �   ALTER TABLE ONLY public.endereco_parametrizacao
    ADD CONSTRAINT fk_enderpara_parametrizacao FOREIGN KEY (para_nome) REFERENCES public.parametrizacao(para_nome) ON UPDATE CASCADE;
 ]   ALTER TABLE ONLY public.endereco_parametrizacao DROP CONSTRAINT fk_enderpara_parametrizacao;
       public          postgres    false    207    212    2926            y           2606    25599 !   fornecedor fk_fornecedor_endereco    FK CONSTRAINT     �   ALTER TABLE ONLY public.fornecedor
    ADD CONSTRAINT fk_fornecedor_endereco FOREIGN KEY (ender_codigo) REFERENCES public.endereco(ender_codigo) ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.fornecedor DROP CONSTRAINT fk_fornecedor_endereco;
       public          postgres    false    2918    208    205            z           2606    25604 #   funcionario fk_funcionario_endereco    FK CONSTRAINT     �   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT fk_funcionario_endereco FOREIGN KEY (ender_codigo) REFERENCES public.endereco(ender_codigo) ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT fk_funcionario_endereco;
       public          postgres    false    2918    205    210            {           2606    25609    telefone fk_telefone_cliente    FK CONSTRAINT     �   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fk_telefone_cliente FOREIGN KEY (cli_codigo) REFERENCES public.cliente(cli_codigo) ON DELETE CASCADE;
 F   ALTER TABLE ONLY public.telefone DROP CONSTRAINT fk_telefone_cliente;
       public          postgres    false    213    2916    202            |           2606    25614    telefone fk_telefone_fornecedor    FK CONSTRAINT     �   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fk_telefone_fornecedor FOREIGN KEY (forn_codigo) REFERENCES public.fornecedor(forn_codigo) ON DELETE CASCADE;
 I   ALTER TABLE ONLY public.telefone DROP CONSTRAINT fk_telefone_fornecedor;
       public          postgres    false    2920    213    208            }           2606    25619     telefone fk_telefone_funcionario    FK CONSTRAINT     �   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fk_telefone_funcionario FOREIGN KEY (func_codigo) REFERENCES public.funcionario(func_codigo) ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.telefone DROP CONSTRAINT fk_telefone_funcionario;
       public          postgres    false    2924    213    210            ~           2606    25624 #   telefone fk_telefone_parametrizacao    FK CONSTRAINT     �   ALTER TABLE ONLY public.telefone
    ADD CONSTRAINT fk_telefone_parametrizacao FOREIGN KEY (para_nome) REFERENCES public.parametrizacao(para_nome);
 M   ALTER TABLE ONLY public.telefone DROP CONSTRAINT fk_telefone_parametrizacao;
       public          postgres    false    213    212    2926            u           2606    25629    acessos fk_user_codigo    FK CONSTRAINT     �   ALTER TABLE ONLY public.acessos
    ADD CONSTRAINT fk_user_codigo FOREIGN KEY (user_codigo) REFERENCES public.usuario(user_codigo) ON DELETE CASCADE;
 @   ALTER TABLE ONLY public.acessos DROP CONSTRAINT fk_user_codigo;
       public          postgres    false    2930    215    200                       2606    25634    usuario fk_usuario_funcionario    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_usuario_funcionario FOREIGN KEY (func_codigo) REFERENCES public.funcionario(func_codigo) ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.usuario DROP CONSTRAINT fk_usuario_funcionario;
       public          postgres    false    2924    215    210               3   x�3�4202�5��56P04�26�22�364B7�2�г02�4����� 0
*            x������ � �         ;   x�SvNeW73gNe0�,)J�+.H,J�+�Tvssu2v����HB�\1z\\\ 
�         L   x�34�LI,V���I-I,�tNMN,�44�(J-�LI�+IU(*38�8-�LML8��j��42 �3F��� �#,      	   7   x��qwQ�M�KM�(M-)�TK=�6�4'�X!U!�(1�� ��$��Ѐ+F��� �>�      
      x������ � �            x������ � �            x��˒%���7�[�#8n��f�P3�F� p@�����M3���B/��Î��s��}N��:m�U���ow`a��_�����?�O�?�����������e�?�������o�������?������\��?���������~��뺮p������#\������5�Z.����k.^��������{Rw�������^5�u�;���w���Wo�\9�+��J�}]i&�[/^��q����{��=��Ƹ�c����;��RS�o��[��x-��[���T{^%����R�=1��V�jK�{�}��g�!^���޳?����y�����s�^��ߑ}֫���+zg���$V ��r7W
<��xX�RNů�����<�>�ןw|ϫ�y]�5^�_�z��=�s��=��g>��T=�⪼�]�~������>"��k�w�쌫Q��^�?�n�Mb���UzX^���p��XS�鳦?�k��w�O��?��"5�U���g��j.J�Y�����Z���j:�y}�\�U�|�WϮm��]+^��Od��l��t�:rYru�V���A�ϯ�?+ճ������.h��_���,�9�=��R�H��y����1�z�@��+�UN���'GD#f^�W!�?�\�?��S���s�������?��|v9R{�����ݯ�]��_�.�/Z��?�/[��|����D��>����������h���g�m��؟��I?)���ߟ��u�����
���u�?�~��_����׸������)��W���������/w~��t>�|"��j��ڿ�{O�u�Sd]�wrn���$��U�W�_6����}��?��ZU�ǳ<����e}�������]�/��yG=׏��*�'��-�8h��˙Ja��p=�<w���H6;�C�،0"/;&�"$���0CKB\[�i$���Z��V��/��G�V����_���?��ޜ�����yo���{?�����]��?k�ӳ�#2g8A<i�ٞ�e����f@��!��B$���p߸�j�� @�s<7�Y����
 �b�����;}%���]�ƹ�}�6����=�l��]���+���]Okp__��������a���2�/�;������_����/(>7��ׯj{wOo�e�y��[��u·����~�����o����_�:�3�		��&��]��]oO��B�;��}�my�y�>��u��g�p�40��ҿ�K~�}������K�}���Kӣ�Kӣ�~�����o��Gߗ��Gߗ��Gߗ��Gߗ��G�o�~��[���M��/�M��/�M��/�M��/�M�������~���}_��}_��}_��}_��}�����o�~�7=���7=���7=���7=���ot�oz����~������������������Kӣ�Kӣ�Kӣ��[g�#����z�{��^z�|G�O/����g���)���n۵�zG.S�r*�Z׺��<{�2Bɳ���\�\OX���S�BF�u�s�Ols�o�g>�����f�y��g�r
o��%��WDx�1�+�>�¾�r���t���g?�>s�l��C����S��ۢ�j��Ϛi����玥�'=W�}�I��a+�w�뙥�%�7���V��g|�=�=�(���vc��8�����R�1ܓ[�/kk�7����;�x>��C|v�ܫ?{�^���Y���ӟ0�7t�s����j��y���i�2K�i?�em�Y�~K��P�.�n�,I�� Ǔ�/9��I5��� I-$�2ww����J����;�Q3w��P�Xq�����y�Qސ��K	�j�N5���J���5��mg�!��e��{��������,q�!����OFt�Y����Z���<k�,ͷ��� �y��⿦���]WA0fON��1#_�nx�ߝ�|ߘ�B�$��Ϯ��$�_s���H̳���{ݫ���b�a^���xN�����<wZ��髕�j,_����.4h�ޝ"������4��Rx8�9���y��N���o��TJ9��GOO�;>�݈�⤔�����ĺ��P�3fo/�S�^�+�/uo>aq5D��������^��Wl�3��<��vTHX�0V��8����������4ؤ�z�V��AglN`�2o@�qf9�}"�l��4�O~�]g
s�h�6>7��TԷ�g \yu~��Z���̓=��F��qw�?�]�Ʋ.��Uo$�Ң��R���HJۻ�Z�nר�e��Hu�'ŶB�W]��P��u��l$+��8�k���k>���jOu�[O�;G��t�t���f�<P����8�EU�����1�\c���3�x`tjm-�3?���^t32��7*&"�׎q���|�~�ʿzw���p�P������0�����2�3WI�6�_���{�Ǯ�\5O<�<�[DX��7+8\��=���>"���X����b�#�;,����E�����(n��ch� g)V�)6/��˹ f�=l)V,�A��h�e��Q,|6P��}��/6tplb�8��w�n�`���"1(�&�~�_�ͻ���Z Ox0m�4�w���;M,KC�nc���F�Z�Ѡ����LF��4��1d�*衔���c���a������-n�E	_�s�Pu�����៾�~��[�譿~������Ύ0�c�^�׾�u��J���=8��9%��į���</
��;`u>�KCL]^͂� x�.+�}���a��#lc�f��i7|	4�^h���%X�cQ��	wބIt*N|��X?��b�F�I�	s������}��Ӎʻ�  9Mm�N�Z�Q��D��'Hj�<h#4��w 5��~���	�J�$�C��+�)˳a	X�tL<Xj����7
œ�^�J�	�J��o�Y۹p�L��`��;��u�������i��S���x1@	��Q�m�E�U��F��$ڭ�g�7mYB��ZLpCvJȺ�8�)���$�ucy:J��V�����ǡ�ËeC�,�P�����U�kŅ��4u��R`gX.�b�M���`�� �1������O��0�Q����c_�c���=���|�T�sW4,�
�����"H��}��@�#TH.����±pi.yJ. :�n���lWy'7�p��h���d$ �b�R�OL��blPK��an��n6�f����� s;VYG��hPx*�1#VX�� /��3g�C0��{�(e��L��W��;L9�E�� �����-߫">��g�+xo�1?��4;4�K��Z�'���q9c��@0���0�M$�;���=����	2�r; t@�̿��jV6�9���m�g|��E؀&s-�=�K1a�O!6��0�F/�+�	(��m1	����ψG�@2����Px���ʠ������u�#g���8H2���b � �r/4��� m���rfsqo羱�rZ��*�4@0������=�����\���r�,�׳�y��� �HL�Wy�Ij�l�� 4����r��[j�Q��G �G�<щ)%s�kU��m@�*Z�tK�"�3r��1���~G��S����� (��G��]���ӑ��7ǁMF��Ġ�P�X��$V �y� �X����!�	!����6�x�����S�`md7BXQ�h������'L;��<���pt������NK�P�(��zQa����U����zyyd�cA�S���
�rp��3(�� ��r��Jф*p�m��aYX��b��=��G0�N��D��~�AyLN.cd'T�#(�������w������OnT��`�6{�d�h���k�y4��k0S�>�����$����{ %���U�CD�8���<����/����wn�������:$vo`��≗� Ta��p�;����uHODY>Ȓ3����w��.y�{�7!Q|w�Z��c�
��G�G�1���k��[HU4��a����HY-u���^�%6��y�>�HFv�p9ϺN/g�!�h��AC�c�3#UD��	}{�    &�.7���eN�'�[�B�X���e��FgV�7���c
dY����y��`�Y��<�=B|>`� �C7��W���X\?���c;Eԕx�����|�O;l6С�	�| �e��h���(���s�� ���p����n?%P�إ���:�Tp��{���P�������4��F'L.��Gy�1S�U�7�����<���
����A�tr8��T �����,���T�ֽ�	S����C	.-b�_4������dso*��@���@o@�H}�6�A7�Qu��K͆ta�0 *h���#����oF�J������b��ja����F���
�����K!���.��Ĳ�Cd� ��X˖s�X����！�p�b��Q� RhSq)В7����=Èؗ��N�oA���q�S~�O�<Z�5���O+��s�Js��ey�!�c���剩�������r��B e�-rz>t��]*e��q�1����n�;tJ� �ʴ��ә��[(�:X���suAwF�<�uI	G��@h7���X�UR)���T�(,�\%bۅ\u�f��i`�8��y ��G��rqY����h��������G��"�ʧ/�<�@6eꖼt�	�,r[� �Z�O��~���r�k������<}C|4�.��Q/�D���o9@�u$�g�=�p�i����N �h	 ��K�#1�� /n��h&�Ƹ=�}C`9�S'��>g-���R��o*O�<bE�!��lD��E�C� 76��C��� �s�ˀ������S�zΥ�&�ȁyY�n�� �^1����C�8��bX>x�����Lɜ������H�����b�qQ�㹺>Z#Q�w
,���Z1܁���'q�(�7	�6t�4�	�~��� t���+<b�^n�ޒ���m�U;A�����@���bȍ�#ą��jۆ@8Ho9n<S�w�I2��k���PW�e_G�n��7�%H�����u���T�^Q1(I�؝�� ���FF��ُ����`T�n���f�m�"���]3(ʌƈ/18��ی	��Wa���h�E�yɏC)l��ۍIz�Cl.₼D�.�=�1�%�Y����]��X4.j���`��Y{�/����{�Z���3L��i>�#�0��E�i7��w��w�-2�qG9�k|���_U�o��KfA`I�q���OG�"�,�t����8����C]6�lӷ��R�՝p��8���m,���.F���Z�����`�|��*�Qi�ᾰ��:P#���9���^͸8�S�����op�/t��08�8��h���ԉ�F���(��^g����(�������P=���^��Cl7�	�	š�3`xb�k�g=?A��{@���-�H8�;����& �V`7�7������}�PW�����؆�b/P��+�8���ɚw�g]0�@&����[
�Y4,p��q���S������-\��D�)��������9 ��ᾐ*��X���w��ž@����}��Z�}5ֻ�i4�y<�V��h�n��%���S
��)/�6�ѥ��Z@5,:���jt���!�O�������p��[�i�8�G`$Ϯ�Ѫr�Zn}ma��f�����y}^�2�o@4�2֦K;���n��Q�����e1@�ř�s�8^���Y���&HO�R܏(���Ix	&J�f��o�\v�k1,s�Y	0	j\:�ml<vb�'y&\L �J�������C`wXR���^�  "����9k���i`[�nj���q���������q�g�n�G﵃�+`hP�o�KOB�6۷�`�7�i��z�2A�G��=��a�=�� l��l�L�ZvjƤ��|�&G`���F6��o{�>�����h����s+6��+5g��9�g�`pR��{���~�)#�V�;���7��qL����?�ACZ:�^Np�Qm;�A8�N����Y=�����4\�iȁӀ�h8��c�b���Z��8������i0
b C�r�b`�{Av9@.�@߫�=�:e`�R�Bڳ�T�6v���HD{j��Nu���Wv�d�Y����/�at�T���:��ˍ�`�y�L��� /�p�!/�)��X���9�c��:�j�a�P���e�qz��,���YAj�	'al���{%�Z`r6O	�[]���D�}X���Wg��FA���\��%������Vgoh��#E@�� ���0��h����ePW����U)@|��~�<+F���C���e�z�|���\��R������*����T¼��LT���nac,O���9k7���^���ȴL>��7���|y3��y�?�֝����4�-�y��i�������ͤ�&H���*��d�h$��BႹ� ���t{���@�/lYYhi�A�*#����	)ua�j[��q5 z��6h�^h� 1	S�1�̠O/$
�ƣ=�Aqe�}��>G��P[ �v߰�Y.�����U0���}�����f;<�JoA��[�|*{�t�����/"�B�b�c߆�tF�ߩNö_�/PC���ǗLŶpl�a��/z�u��`�:��D!蓇�٭*XM/�C�����ݕ�6S2C�z��$ѐ�0I?l{WX���F*9�h#(��i�gα���j�yu�|�։�W�^�Cud�<���q|h\�Q�L�u�a��f4V��KfM w��]ի�������� @�4��ƌh�PRx�����>�q=(|]T:o��Hl�]1^�̠k{��|��,��@sj��"���r%�Xc	��$�a�C�K�_�(=x�[�e�oJ�qZ0���>�n 	�b��$�.���ǉE�"���n�ʵt��ǅ@��Ռ� 	{8F�@�\_���9�7Hp��	{��}p˽CК}��d!l��=,�K �/8��ĕ���,��U� �|r��Y��]�zU����@6�X8�O?y��e7O�������$A���PESYz��.h�,��Ն=s3#d�2*�Hwls�  ����fނ��L�	y:W�g8� ��"��qXJ]-A�R�f���^�j���XFe��)<�"R�LD7t�ޙ��%ք'$e�,y]qoh���O�GAT����Ls�na�HAu����}m#����@,sJB���F\^|J:��e�О�
Y�?��=�0�[�2Q;srXseY�۴���T|�2@7��2[ܼ��6qy㸝x����̍P�[��dM�-˝�"g�{�l
�
��Y��y���^����RX�r�w����e�/,�s�t>Od��F#Xw���B/ �m��Ѕ&�]��a�\Û�`*!z����#��H���ᙣ �ē��FFѭQ*����SD�&����AxW]�Z ��{s��d+�l
ڇ�����T`�d���<�u2��l��o�.|t��I�b�'4x�Xu�a$�!��m;�kE��;7v�(��!�(۔���>��x�����Sl��7����
�C⸔��|��
B��u����'���0�\�C�=�k�(E��7��`�I�QG��-K����)�F�Q{�ti��G@�>�Л�k��7U�@��v���O4�f�>�[�x�0��f��c�t/�('�F4����	��'"Ks���M��F��$��Ľ���b�>���	G�`����6��u����C���<��?uǉ�2�9��;i���'��1����bz`��sf? ��ڙ�}��7V��j����$�F�$Kr0'�����[>�x̓�S��Y�8 ��
��՘Rɫ����%�F3F}a/[�_�ZL2�'� �`�O���+��[5] GPX;5�"���mbKP t}�)�ˀ�jM�^��I���ס�6�\��PQd��0x�!0X7�8u�h�i��������ׄ��B;��    ����&�!9��~����K|!��,�4�}��*�У���"���'QU���M� �qa}�7��9*��W�N����b�/���08�N<�%���X}I��ހܾ�Y��I��{��^֗S='k���1t��mz����e��FY&$h$I�Nu�l��BϹ!��`��;]��fH�~qcF�ҀmF���E� h��p�\BO�R��c�2e�:���If�U�'vm�fn����t�Kk�b��V�|K�P���"��!��x�:́h��y�� �L�.d��b��0l�:ЎW�4χ��@���s͉�`�q��7�n��o�| �Z�0���~<t;�k�*�.��k.8�5��g�$(�S4~d�x.9�;�R�P4��Y�[�YY���F�	W�Y�
���H�N�3��ݜ��%,'u���4��*/ ~lM��d��E ���4Z6��k4$��5I�]Y��"�������>���fM���h��Ī8�h���6��&�1���0K��o���|j��$�4D��8�
ڠ��F��Xp0boDbT�3��Z����a�,(��}�i�K��s�6��fD��jj�X�`M5��~��\�'�T�`I�Z�G��}�ZZ��M�٢~ӓan}WY,hᵘ4�WXw,4����ֿV�����[�W��V��`��JR�t]����~=]��E6,Ɓ�����c�R�v]O0���7|��_���|�!�5�{�pA��bV�D�
H��*��-��;0ÂfDv��{��S�`��̝��Ĕ�NΝȔ'��D���7���5��rOu�������� �a�T������Z+��m���J���I��=w�)�[���������uo��c��/��<v���i�:�f;QQ�O�TZfaV���7\k^���v�U^�{�1֖��`�4���IaM�m�*� &ul�-E_ Bֈc��u�� Wh��cw���]7ªФ��Z(�� 9��0�gimt�Z��
�|�B7$�˦a��(��~c6�5����+��7��S-e��}nx�0t��7�>��*P�j�����Wߗ�ѡ~, l�DM�$��։e��T�� Fpww��R��0�C7S�pJձQ�\'���f��z|�x�h��b���,�á�[�|Ȩ�[��
�*��wE]s��a����B��5;���6�ݛl�-@����~䳢Y�a�i)�6�ͬ?��Y��aףa<��I�~3�x�fP ����Y�(����8��lU�6_�U`{	s޹���~ �w9��$��/��=
n�(�Ӳ��2�-D��j�f��m��\����̾7�Õ0��6w3��8S"�eba`ڇG�Kg��,��`�#�,V D݁���sX�:5���i���yF�;�#M�N�KB��=��-�0?� �&�~�*XR�x�"@	�i��2C�IϮW���*� X��k�����e��d���l{ͤ���;�O��>�� ���6�n鲵�Y��k���3��:} T�6����W�#��B�N�����	���Z��b�m�Z����jx��h/"��\�<��2� ��W�xd$�&��ՙ��d`�pK}��Ȇ��U���\k�;@e���ڊﯠ�����u׏�:���qҟ_�Ѐ�7-| �$��q�����n��E\�R��2��,H�I�_��Q3����6�r�[�U-�6�
C<�	D<Oz���fK~�i<��'����C��MT�������T\'��V.��R��>��stD7�J��6Q�̚#����)"����Yx�â�5���?�`^�gp(<��:�k��	�x�Y<&�!ͦ�١��itSc���&Ǟ0��'��%K

�:_̛��e��;?~s�r,!�f�����V�5�(]c~���=|���Xl��)r�Na�`�	^`�]����n��y�Tq�� T�m������Dc�	������"�� �^�9z�<�d��Q��h��g\	Y�Y��N`&�)I��MPZR�nQhB�=��ֽ��ZR����݄��3�|�<uӖ{�l*w�^����;����Yc9�5ŷ:�xɖ�!�Ҍ�zz�Vk��N�y��=G�sm�ʽ�ˬH��0�rDC�@�{��o����-���K��/�@ͳ+�B�Mz_��Q�V��0�")�UY85��$96Ss%o�)ɜ�su���ilOs ���9�p�e@����i^��1����&	�u
u)p�yM�o ����>��[} V�ͮ�	4��M�n��=J����l�ǂ�8"5��1�mΚ�O���ܧ��(�r������4;��>���Q��� ��,��3��#-얉����(�Z6nf�%T�)L=�n�'̫܅r}MSZ"�q3͛#��E��t�������82h#�Qv�c!L�[g�t���T�`|���@�KĜ{X���Q��6��I�"��i�G��H���;z7`T���[�V�Xɩ^�×i� ��5n:E@� ��^�\{��dG�a�Q��KZ�9U�`��c�
:(��'Y+'�㴘y�ߚ\<Drg�3Xb`b�O����Pw��uOS+�w�4�
�V��V�Y�}Z4$�d������8;��f���Fh���g��$�d 6�o��U,�B75!F7ک��b��@t=	������|����(�5�mw���Ե��;K�֫H��e��Y��k�9Z���^M�޺-�@] p�(�{�<6钸˅uRD����_����S t��˪#��<L'ۼK��x�ބmR�m��e�!YVC��+6�`t�d��n��W~Չ���`�I��#�`����+�sZ~�}̆�_�V
��o6g
d�F@m ��'[b�#�QO5��UM�������Q�_ �yc���1Z6���|F@;`X�o��֛h��6μDv���X��n��������ה���%/�ߺ��ݬ:�l]J�h���Ǵ4���LS�M�JA��5"�U�����^EHˬ���,#t�ek|��T���˔�8 ����8`F�	#Ϲ�|��-l��k��%�c�������6Lg`���e��Y�Y8q�j����
�0.����3����Y_��e�)���=�9k�U���P�`|r������i�} I٪P��f�S�n:��dz�����3�����	�YN�@ٗ�:Ik�̒�	Ȳ�W����T�yoɸ[q��]��X����2��?�<N�ž^����oi��fzN��e�F$yٔ,�Ma���y�:K�\�����_3���7�Y${�ڀ��XM��!�{�qA�����me�3�y,c���;]��`��
����>+A�x�O���AU����&8Vb
�E�b��6I��#�ئ �5E\Y�=;,?؜�	6CC8S�D{<"i�'uDe�i@!�b�ePc�����Ƀ'�w;y���j�^'���b2OI�`����߷�Z�����2'�o���P�z���p�cgy^��綽��HhD��h��Y&�r��N�MA���eT5�8*�]'�z�=�%7&6�ww���SYM���O�\�*���M� �&Ǔn|�}��4�콯簦bG���Ŧ�)(����Y�%PՍ�
ڧui.;�\��L�6�[������4��Tf� �85�&��%�du�sa��. b���:���1�뚟�d�F5���Ӯ�r��G��'����I}� �x�
6JsӞu�Xh� k��'�
]��#�ٚ��"{�I�u|G�S���+��|��2�υ����Uw-{��'��p�����WhFe�ߵ����{��._	Lfl(*���=��<1�و�^��n�;^o��5,�V��9͟��g̗�c�(�+�$+�V�W$f�<�%� `���-N���3�JB-��m%e'�t�����֣w	A�mt��KX���7DHlkX0-�>a
Yu]�i��� �Ң��U��1]�v��I�<�	��U5�L~�y�/�,'�Ѻ9�e�<u��F��?�)��zN쟿�     v��t N�o��&��=�����Sb�ラ�֯���n���n>��������'�J���v�"����X��5s���Y{���x���B��B �]Pð���{�1wǽ�rػē=����V���Ͷ�bό�4T��k����͘8��t��9 5�Z��G�� l��/��b֪��9�7�e�`)K��^�;�W=e�edp^� @c���<�.���,v��ƕ�*z��i�{�3[=�ѵ��)��d��ѽz�G�@�6[$��9'�$�����������5�X�9�y+}�	�.ع�Y�`!#
&`�V������Fy���1��x�Y���B�f��/n#ƢW�h�����nv��5����Ҹ�q?eQ��c��sg����5?n hAhf�Dc/H�j�)����$�R��=&�4!�kvٜ������la����ґ��~�qY�L1�j��6�]���6��(��R8��y`� <ViXP7��R�^=�zk��B~�C$�.�Kn�;��j��������V����q�#a�O��t77_j���/���Kz�)�{����k�m�*����=�׹W��YV_Y��ykt:��j���e���1���6��:LH<é��ց&@�������@Xs��]��_��"�P�Y�.����~:��ck�d�B�!������{��j^<�@��3�a�TR�D���W� �b�P�kڐ�T�΁�{���g��%�˂!Arj� ������x��D�M剛D�؅� ݝ����^�/{�m�S���T���~��2��,]�j�"ӑ�k��o{!�Ԁq���PX�aҿ�7�~�X���v���Rj�e����WV8�L�`>�J�dq и�۶w�(/$A�Y�0�u�+l��
���`o� ��2Z�@��l%��T:Y��CC��2�b��<��;Ab�j���+%�?B���vf�0eyLp{��59A�h���2�l��Ӿ�(
�~F+�	�v�cU��}�G@Ն�!���˧]�V�*Pq�y.;V�jˊSv�E4�e��&���eT�i~d<S+�"��m[b���-9r@Bɂa���ӯU���{�xlX�?�Ӱ^�+��ì���m�ăw,��8p�f��Amc�i�Ď!(���?FS���w[~Z<ԼVOb��h�˒#�mV�����̨
��mc#X����\ڮ�qI0uJV�:b�
�@��|�kڭ�g���|ŭ�5A�l"���	�F�-��~�aD���/�H��:ʌ�h���������L��9e��8G����^��,F���`GN]��Y�#��2 ٫�Eω��'�K'e�n�B�������lz�w۷�b�P?�4v���nE�l�`.NH>� �6������" ���K��ۼ=+�����jy�2�[��4 03 fHT>\�6Z�NZO����rj΄��~�6��-ps8[�ᄅX�e�m����X��6��)}N���[���=�#0�)�<���h�̣���5���; =���e��a�j�`}3��6�0�
07�L�c��ۑ���k��\�x��!	P�>�fs��B|�.:�<��=���2�)��8��l���W˄r4�}1�i^�jc���X�*&��ײ��(�xN�v�|�����Mn���S�eF	���IQ�zƩY����'(1�e<5f;i�^O6	�7�s��3xc�ܰ�4�a�. ������{���^}
층HRHVI�-�8�+�! 
<]��r~C�E�w�i�5���#�G.[duSE�K�]�`I�Iy�ӻtKMf��k߂�f�J��[�7����8 �Vlmf������%�Ȫ��`QՉ��B{��X/�J�lsq��R=�\��oz�m����-h�ɻ��2 �g�\T[,nq��E�*;��T� �(��,�ã"����:l���>�dg$�Z��~�me��|��Ԝ��M� �!R�ԍ���D���b9H� :��v)���X#+�?�u�����	Fݴ�6���/�+'��6\����u�ˍŬ�$���nNv (���:TV^l\e������*��;�ˮ���Pj�o�*�P�iXv�r�����Ùrl|So�=��Y?F��yu0)�R�O� ��:��@���z�[���`����m�ReϚm.��$Zk��(@��<[�M�W/{qC���nt{41�Ju���s۶�r���K��5���}�r��<���!]�[5=�x��'�tA^Eb"ke;z����[.?Vb��eg��6;wD�=���#��mK^�����v���ǋƵǅ5���u��|�cC�}bԧǉ��}a�Q�|lX���Ϗ��fų�]`�p58	��|�����FK�t�K'�N[;�sYyÒ,���Q;�(�X�|��l�.3α/�h�v��TFu�ɎJ��*żf�cvC����%F���s���˹P�Zcׇgl��l�����0/qb8k9`�梈ӑ}�z[X���|b��U����yDp9�˙�uE�r�q%�<�zYKX��-��6�����i�[�m���c�lRf1��g�Drf�#��g�Y0V >^ݾ����PRt���	 ���b���=`Mn��<N`:-������-e!�ֱo���"�qَ��O�a~���*j	�:�?#����>~&%�~O#��^a��QGb�c*v��3���|?�:���/v�E1D��k����,:E��^��0x���)���������>��Sڝ�mV�m�B�i����:�v���c��qN.��C2��H�1��[���W�ؘ�x����ƹ):��Ӛ;s����	m�f���7/�91n��;�ɂ}]?�2��N�-���0��6^XT�=,�X��,z����qzV�t=:R�g_oZP���j:����C���6��������*N�c��nR*"������s F�o�cj��/��� �3�|Zh�.�����G|�����ʻ"
`��N�X�%��pȀa���]���kxf�8�=#�lq�y�ç,�`�!��>�L��nF[#Aq1�m��#�dp�=�R�r�N�Z9�C-յ�|�8��jI�i�Xvh�5��Y{�U���Q64Լ���z����@c�U��f/��㬌�	A�ޭ����f�`�.�z������!�h�O��̟g�A���[>�9��G]�X�Ko�6�J�>P�%젠v��Ð�c3ڈ"`A젊�_.� ������8�'�x�z�ޓ�_�Lpgљ��`b�za�sU��!ݐ�<��l�Ƨ'��
+e]t{��t٣b��PӲ����q�&�O
p�Jlsmh,��]cjd�m�Σ��6m��6-�b�W>�f�%�BF��2���g�?k]/$�b��^�Τ	�n�oWsJ_7Az2^��s����F��?�Ɋ�
;�`�U#%����4'��ۣ�g��N�:�>.{^�$�����S����Co�Q%��id�9x�Ę^]o&�s���)n�����f�Y»��旀gB�� ��{���ڗ�� N�HK��=T�\%���j��G����㏖=}���F������e7c��0�y�<���H���Rkά��@���-/�~����6�)��� �cQa�i?,)���j�ŉ|o��5���vy����r+R�5�S	X�fL,cFv�1)�U>�W_�T���!���u��a�\����%�l���f� `+�wL׻'�Ȳ�e�?����9#���͒�`� r4�h�ńV��H�i��@ `CQ�>�G�'��[�nR������u�P����#yF�MY��$�{g�1;3:�<���W��=��h�r�]�[�c� @.������+F�' "dS$��'�*Mˎ�s��IKf8��kS�x]�2{���c�6Y�����E�A�f����^��g�H_��`��Wg^j���gU�O4�7�NW;x='����˷	�������4���Ag��n�::��-�j�w�	�G��|1<�`F���S�n�r���;r38��y    q�	����iG$'�"��a�趣c�v�mD&zb�bo-�����{��3�F��w8 `<kFo����C1���p��٦�PCc�n�d��k��!��`�`R�R8׻�t۠��?�v?Qwn ӓO��m��΀�3Gj��N>՞/�P�	���kuk��堽�>z�L@͑\w�8��yX�2���5�k:�h��=[=ds��|Ĭ�,=m]S��u6s.[�����QG�yuQ�{�Ցư�Bow}a� X�؅�=g��'�Q]������*��."���1g|=��ay��)�{�%������~��8ɝ1�-��+3Q��~0���>Qcu�3��t�P0&�ӯRg�����f�����h���m[���# � u]�0�B��d�H#��j�:l�n��F ��i��@�̓Oq܎z�C~_�}��j�ۨ"���i�qӬ����0�����q���T,%���cw���e�ڰYΙsJ��;�oog�nA��NN4g�|�6�9�$��t��0��y5c��e��d���h6.��js?5؂Gc�J]m[o�K��h�Kd�o���DV�p�NOsk^OWT��vP���i6WuL��퓑�^̰�Es;�*������>���ٟ���n�E�����5��jv���3��`g��.�
U���W/�FD~Yy{!�É0z�ʿ�f�4����0G���6�D���3`�ؾ��f�N��`<�ZW���0���jAE�i���r8اa�3e�
6��םM��]9�5��3�l�s�A^�]P����
�ۡkǦ-�����m�9����^Gļg �Ӻ2!���dd:�)'�4�L:%��)�0��b�C��t����8�i�a�:X-Pgt�n�S��aF/:A�a�����$�6ǀ(��(��r�q���x�4���гZ�����d�5�,H��6%�V�:�0��֠�
�{Ϡ8�����$c P�'�n�}�f��:�i|6c�B{�`C�i�Ͱ�ks&'m������l�'']����!/Ƹneù�7v*�eg�ӮT�̙_|��8(NㆤB�mkkz�u���][�B�zG�M�b��~OGs�}��15��z�����sv���`uA@��cŸ�/¥P�-��������)�)�>\��=)m�=Ec'9���I[s�"؏:S��kr�:�{�߆񝥎�0�����*���O:5�ZV��fU�	?��Y�F�ô��:���b�l�RY��<�y�� Ysd�^��M��='N���k��Z�!�ܓ����N�=e��jl�z=�d9�L��'-��I�e�n/��̞��Wl�M5bC��������$�遱�£�������p���'"�_�X����/3��e�B�1���,�6N��F�Vr��=���@��SV~W6��z���a��;3M��ϰ��m��(�!�ق�Ơ�X���vh�N�l0.,I;}z�#�et����6�/Iss�p
wm�����ĺ��n�l����K[��"ֿ��g�����.ʛ��
�*�\�-L��l%ԏp��59�B��O�����O�K/��0b�����pt�vh�G)��n�v�Gw�ֳmib�k�2e�������`�Er�Þv��g��tQ��\����/�Bc��77g�{�j��S�\9��3NP�u�gƈ�z���.�{�uv~�y�k�?���2�c~R"����l����s� t��^&���� �N�[ť�=����٫�`����m�<?9pL{�,�ɛ&��AY�4W������$]��b�� �N޲����@<M��4����`n���d'�����\P��J��])EJ�b����ų,$��-y2�`�H��A<U�޾ݸ��<4g;:�csm���9����@�N*QΎ�f�,��i@z���ѩkC<���2�x�V��`hMv�����e��p��g�-'�Ȯ_vq�)ԙ���4Dw�s�����&]9��v��)'���X�Zg�uq@f��~��tQ�T�m���4�A���?}z}=��j�b
��;�A7;���N(������S��w`���U�eƂ8SB���ɧ���!$�OG�=#��?�m�O��3�۾ٴW�Jig�v���.EFf"p �9���d� ���kc7�\ظ�-�`%e�E�L��= � 9O�9=�.���6��[N�f��V�������FN��m $�ZȞ�Λ8m$��6}e���;C`�&�[��� �E��p�؛�5;2<6��(e]�B���(�3�6�� [�ye�0��~�(q��;��zk���Ճ�S�a���AA��23 Lk�>����[%�L�Z��3���$��ZjN9u�� ���klV�ŗ'���]QG��~(	�j�r����@{8��N�,/�j� h�N'+��ѷ~�g����ak��ڲ�h"tu�����>Tc�36:dPG���%��LV�`
P߷�;�!�N[��ynȴ�lZvLxM0�s�fm��M���ikڥ��s�:��f	��s�̏v#|S=����]�k�F��`�rD��e��ʠE�,{��������JU��UM��߮��M�6�Aݹ���ۙ�w�5l���¦�R�w^_��A�F9b
!w��qF9޶���r6��'��M��g��Yp|�K�o�5);�|��?
%����t9���g�N=5T�����u�5[�^��;U���5L�,����� ZHsz`n��CAZ��C�y�L/�lcĪ�����mW'T��]L���ӭ�T̕�B
{ر�F7O�a�{���~�5�uۏi��{t�c�ŏ��)ʎnu;���`;C;�"=�	�&7d�Od��,���T^����Υio��e"�m{�˒���0�'��)�m<�\6K�t��s��c��6n]'�S���A_LHa3����������=:ʺ�l��*ϡs6 ��_[���`�y�&��{� ���qVr�,���6�
Q�?��U'Ձ��}��B��F��7�d]o�g.�M����>�vM@[dR;z�`6�(6��ޢ��1|>ˈZՑm�V��:Dۉ՜Cnݝ8 ��ӗY�˲�h¬ ���:x�-v�u
�	�j�����ڊ��'*
Γ�����cq��c'���0�;$�po8�)�S��J�ss��ڄ6}z����~��`O���j����F������QM��^�Ń��f8$��\gb��H��kc�e�"����
'����|�o��!�@Aw�f��m�M�_�cU`�÷�E�6]AY��b����md���LKہR���������S�3��z����4�>���=��6W�ܟ��F#MI��l^,bh����޼Bi�%ܨ,N:��Y1NO�3�:�
c��f+�EP'"R�¦c���֋�kw3��-x<,,�#>��vɏ��3��
]�9/GFa���^��B؝���rL�y#g�D3��t>>8�ͬk,��88K��[�=/��8��ZX-4Ŵi�i�(��Y	����`�B�gi��y����\��}O�#�.36���������h�©SX��7��-6`>���Yb�5k�L��S��*vxhL<b�j]K��~ qCJ��������]���¾&��{���0g.�i���+zﶭ��@�0=�ئ�k��6
 �d�?���c��]�=� d$-2�p�bh$���y'b}��ޒ_�ΐ+����Lp�|m�S��|��?n"e���e)��!;H�X2��l	�6
7˪Xh�_WHB5`�*;�H���^lq��Ρ�3Slm����m��X��u0���6c7fi��5q�$�.�Q�
u;�؏����I�~V���l�������?�8:��ij'�kw�l��t4��f�i0�e 
Yks�68O�ɸ�4*�N��� �v`�i_�Iβ��U����:; rm��l�g8��2�e��m�"j���LP�%?�$3�=9_��W��m�z�2O?o��i���?9����:����a�bI	��1@66���    Kh·O�W�~AcY&�o��}��Slu�h$�J�w�̻4"t�|��ҫ�l.�N�?��)c�����sn�>n�	p������])�'FE�;��)�ϭ�T��ɱ�K�v�B�d��{[o�HO��8Xu�V$;���s�x��/����,�����TZ��we�M
�/��5����[�3l(��)�������7���c���"�\�s �H<wp�	=�J��L�=;a�r8�e6�>�tz�#L�k�ۂ�_��t�U��2��3�*��E+J�'k&H�:I��g���� A }s������6�՛��)�] )ɦ3}x��V��q��d����1���j�K݃�Φ�OĻ���[O�����{NC��S�a<�%c�n�	T�Y1Oɟ�t�����q�&�F��eG����N"������˟u�ʗ�����?�iV0|M	�?��ј�
��#s��l(&qB�DK�^�7�iK��e-��C���f��#Тh�1m��[�8��4�pY�g^�����k�ɾ�؇-�&�ڑz��&���G�|Q|K�d)G�U[H�R:��t�?�S���62aլ���@lQtJԮy���|��g9�f8��ov�eF�J���d��Ep��|@&�o�W-���7��r{�n6[��y�m�bw�a���`S�RG����b�w��FԐ~|r#�f�'�����2O
�s�f��,��. �qƾ��=��>@��� ������>��s���g�������=��L�Hv�8�׆���-���`x\�O��:C݄-lÙ�	᱕�q���]��2[�#�VYLC��:h�t�'ܯ3�.�<���q�&;��t�$(���0��}�Aa
Q��)#����|��� �N�^ V䤐ظ�>(���a��tV'=:]���FU&S�ͭ��nԕ����� H��Oz���VZk��Y�ʃ�{k�Ѽ���{���cc�}�V�h6���U#��)!I�K��葌qԵ�k�Bo��	>l�Ar�+󉭠�aKI�q����( ,B7%�l��z�����k��V��:�~�G���ء�#h����HL��Zax4��<��Χ�	H�i��a�>��4�p�D� Y h�w�ڌ�;5�s֡�C�{�[��Xi�i���lɥ�{�(!Ȩ~[�Y�wP�1"[k��dNu2��S���БΦ��^�ҺUl�f=D�����!�v\tz��9�M��~�9�fu+K,�Ԑ�(^/�q��)���l?�u��<��os?�Q�	���Ԑj�a��eKXMd?�)8e	f�u}�݄?u�ɱ���3�g:�~9ƻ�<��4��ogo&`�������uR��U����r��g;7C�&�f́@��ѯ�mhh�<�l�-�I��s��)�P�u滺��B�Q1�5�2 �vc��X,��3����ig��.}-A7ٙ�s��v��#�P�~gدʞ"���c�hE؃��l� 4:��s�1kɉ�0ɿe[���`�c$�-���ڱ��w�!���:Tr�c���&NhӴ��~+��l&�:e~}��	a��A�tG��.g������I��	Rj��n�� � �Sly����8ảY��㉺z׽n�H�CN�Q�8�g:��_��O��ňl�YX��ަ���u�/���S�I�3��^U�K6�1���C6��i&:���g���p�h���˜$�^��.�w�p�f�&�����Y����_�\?�2�"�1��g���:�x��_�wM��Ud	V�q&��m����񩾗�P�y��ò�h�j+|�  �6�1�Q�ǔ.mSp~�W>��T�
�s�N������v�W����<��ҳ�؎т(�p����b0�C"�a؜������;�o�������3i�|�b��J���ý�an�̞9�Jb��~v~��y��uV��%�yȉ0PN�f�Vנ� 3q���]����m��b6�����tO�?(�W�:�h���	�?�uЍ7����Qs�9��@��0��->���y�`9���$B�p� ��Q�ʕ�i��A9-�Eu7�hd+��N���r�姫�)I��-:j���a/�a���7�v:��06v'0I�D=��oZ�tW��=����+|���h�V��\�e�muoP��Δ_�����l���|4��M�Pz� !E�=�8�7Z����Fw�]9t���=O�e��l'��Of�UM���<m�n��Aq^��[<�{\�z=���:uF��h:�'wf��m���H���eՈzs�[�C�$�z2�Bc{�z�4�>��!%�o�����[�o_��d8�Ñ��m>)������h�+~m�V�ݰ8��������i�u�-7ή����#3���-(d��e���axLO�S�]��~g�ɥ形��
�yl9k��a�����s`���'�����K���m�j�*�t��v�g�2���^
ܜ	��[�,3<�]އ�����`��Z��y�G'��uLؚ4mk���`���� �xj:��HQ���`���{rh�<r�2KWT��J��栢��!ah��	��ڍ��(gIu��Gq[��$oW�:�>9lܬʗ-��(D�b��v;L���\ۧS�UmRnm*�d2�e(�S�d�w��N1����5���V���vz_wr`~Z���B�h���F~�̺��N�U�"�K��<P���+@�|���`�� �`��jc{,pL�[�^�g�2�pL����W���@w"��(�n�/g�>	$0Ə	�;�{Y-����.����(p��c���P2='C�,]�X�f&��m�lR|�6��'��yd��!_��N�3*�#����L:���31�v`{h��FǢ��ߢMX/2`T���b���0f��/��i���:������2b���N^��V���
�� ��^l�5/G�a}l��o@�H{��z��E�#��w�z�P�Ya�f��2R����AY�Sl� t���E%�$vL�[��vO/�q���n�����<�d�� ��3)�v����jyUΧ�l��Kc�oS"PP�\0ކb��/XM�6�y��a��v�M�ez�t4o;�8�V���{�2��"+��A�~9�`����STy��
f@/(��Lt+�ܨI�;8$t��c)���4���In��p�?�&�&����&p�yU���eS����N-�}I�	�>CZ]|D�������gh�P?J��Չ`�U�@(֝�ZN�Fv��<�lx�������X:��.�����^b�԰7�mrF=����N9������n6	�t��A�G`�(M��y�TC�����Ea��r�(�J�X�Q���gG�Uݨg.����2)]V��b�B��D�i�,[�v��g/t�n�']�O��Ci)C�Pp�6�FK2
wt{�=�]����KVe�*����D=A���tg�y�ð�_���r#�� �pj�C��$K�����]2IfF� �M�N����<��y�K�^i�#%�4�#%���	Iב��)�h�G��n�I#��H�G?$��,O'ê���>?��7��]��l�:�t8��d��E�u�n�5�oK[*ޒ�i)�'�#���Y���L�5�hZ�臘�^�M����|�s���<��5�|ޢ��)�m6���L`W�k_� s�ޙ��|I��D���v[��4�G�vJf�ʲ�W�Y��S��[EH�Ɲo� ��������@�./P�A�5A�z�"-�b j<��t�G-����c�R���Yw�u��4�7�]�Qmu�d�a��sKp����;����u�%Lya%�Ri�r�<��^s��^g�Ś6�����i���x���!�?��<�� �hN�d�A�{��;��9fVh�#���wL�q��ީ���Ne,V�j1gc�Z�.(�"'�;8�<H�$�&���!������9���L�V&�yVɤ����L�v#a����g����H?IriRiv�<~�����3�J՛��҉|^�2�:�'�x��ݭeG�5Q����5Fb�շN�y��Z�/�FJ}j��o��    4eQ1iW$��ߔ̕]��/���5hW�]?���}�z+����4Ƚ/�Ղ����FM�.l����?7�`~�Lb��C	ۛʍ�Pc��W\�|Ǚ6ʡx���yP9���V�!q�^�'͵��n�C"�J�&�}:���#+��KFS�����+^<Q(�u��x{!lӯS0_Z2՜���"���f+�m��t��z%��$괦�b�^�|�Ȗ���mJ|7��a '�� o7�FL��<;�B�u��d0жd�����,{�9��oj-ӗ��pbL5�r�s῕�L��ܮ�)��z>/�}�Gn���Z��N:}K�X:�s�rz)��Bu�ː'��o�ɢ���
�C+�M2��G��tN�(�yg��T$��N��(���P_�Wȅv�U���'2%>��ڷ�H���$��]���M�q�bF�@���- a>�猱��!�o�Q�����ܒ��<D*fvfL�W���N����H����H�Jm�>:���uy+�3-���e�\��O-_��"J=��N�dʄ���	����3���E�rj`��3�?�ȷ��I��LU�'�i7ɞ�+���\�?�x��Bq[nD��΍9�KRhC{�=�������Jק�k[3��VRN~>ڄ̪?�j��>�p�S���iifB��cm� h_��Ĵ���ϡ΋i(�wE�Մ�.yiL1��R�ӿ�Z M��4�y�wi��m�ӗ4R�b*BP(G'��/������L~�"�w�d����
�{+��O~bL&li6��p�M���� H�ziSגȮ]h9>�:��~j5�-�S�̂9"��֝��A7�$W˶�1�V���X����h � �ƲL�6��+p��z�Q��Q.'��b�9υ�#�4/5kN���S>����dQ����¿&��p">9~���і��fw����?���S�(c�ۙ�ޘ7h&��0�Aq�iO���B+���(�!q�(����D!s�V��q4\�.�֓i������%���od+�?� h�	W{�-:py\Gy�gқZ��H�5����N��I���ġ���~�j�t�iv*n�Z���*��A�=�Z�� Bod-\�n�={6(�t^� ��/�&�T$��^�y{:t&_K��O�И]�2{mV���)ɶ�Н��;�� Jr�F���OސW��e�CK����MF9�x6p=�}x�i���h= <���o���`2�
%:[��"���S\T�V�>�4�'���S�̔�(��c�S���p�+��b���J_���D>�8y�H��r�y#�'D� �Z�{�eg�q���R)"S>U��9	[ۖ�-�AT�tn�[�n���%mnN}2�4_����$Z���}��y��BvI7�["��fwL�7�.�n��S�Vo��t�`��^����@f�6��U��N��y�X2&�T��u�%�.E�ø�����':b����;S��i��3����XBE)�96�!i�=Q#
Bj�KGY5�HZ>��:,"b�uR܋�,�3Ҭ#<ޚڌ�U��<��������m�7	P/����$/?ذyg�}�pM\])
�ʵS�_;,�B(�e�˄��,p?W����y>0�yP�>&R�X*���b�d��6��"�����i=`"mK ���Z#m~��$h�Ú���H����d���㩜��v����ش��sZ��ܝ2B^[�7��Qb$���W���B&���Y�Z�Æf6=�ۣ�EfCۂ�_3��r��9�T�
S�=ȥ���6G
����~*P{�������׸|�-m'�2p�����4��h�zզ�����<�[����x�h�p@e�p�X�Q)k��_��v9�1�C�c�����T�$
>G��@?�ֲ�^iR�%`���3��\��Fv*1��
[����-]JТ����q�˚N���p��-�(�>L�Hm���	.m�-JN��R	&OV��Znw�nk���.�H`�eiv��,%��`]��g�Ѡ�:[z�D�8�4��܆��r[V.©G�a],��t��Y���m�W`�W��l�ȴ8,��+FzJ@aCMS._V����j=$�D����,�pɩ�^�kwx$�A�K�/�3@��������~|2L��T�	[�7� �V䦝^�!׀Ú�����
~⼛P� >#�Ǝ!KOZN�����j�=؟�At�sCv[�''Hʳ���f��m�l�F$|�̱}6ȩm��<R���-փ������┽�A-.�%x���J���#O�M�m�C��{�P�<1�! H��yb,׭2d�↓��i�6z�yM	ԣ+������7~���1�.��a��o8�S'qPk����7��5��L��Y����Q���vދ;t�K�����U?e�\aw�1�M��O�n�8���Q3� �N^��.�ͬ�v���|@jo���5��O�h�ZqO�<WW`�<A��1��|��ц)�D95e
��N*�G0�52S��li�j*����5mk�3��O����|sH�� \oל?#֔B��'5�<�rG�+�y�,z�r�geӖ��}ˇ#��O�jG�0�7+�qU�G�� �c*��& �⨨�iX��w<�r��2+���z��z+�_�����m��Wq
Nb��Y��|�6>K��z ����py���W*� ��ǒ��Lü��ᢓ�5�*Ԓ��\6-dr�= {�lp�R���0%ܟК#�:�q�KɚZ�烽�yN�r'��,�'��e5�}.��g�p�WHۜn�4�:G����������)G� �"�2���KZc&Ci�����S�̣2�N���O��Li ��6$������2�`Od۾�~��3pC�LxȷJ&���]/��!_�d�tg9u?�Pa9����T�0N�����!�{z�v�wS%��c):���b����������R������?A���(A��e0|<o����}s��GC>����8M}�̴!}N�_�ڊ0��VV3=��U�����'��n:,`~ �%�q��b����owHmF����������^H���E0Y�y���{N(f4ޒB,����Q_�]h��h�'ҷᰳM!�w8��#���ԁ���J�o5®Jq,�?+K���Jyk��75���#U�܍zc�k8�:���M?��/�`�J��r(,	3���{?Ƕq��M\&�1;y�� ��*����
gʺ���Iv�9�_/��<��>��P�|����_�(B�Du�f%�s����1ω�v�sCi�i��l9h>��n�t0�(�I� �&o#q���R/�a� _z2/%�aύ��沔✜�9�������t�(BN�%/;hҪH	���;��Җ�~�(}��k=r��$��]}m�`�K���z���y�EY�?K��F���ï~��:9�뽀;�>��H�߿�PKd>�w6-�w�V+9��j���! �
nuP,�&{�}�� f��|g.�A�/}�����f�,ھ��0�t/����.��L�?G�f��)smR��
�Fwf�����$����7d=��M��0���^�H8)��G�}�w_���)}������+gc!@�&27�x�ަ}�RX����$ä��vO�E�#��;J`5��p��o*d "P힤�������s�N�^t��4<%� <�J0�,�2?x�0�T�s�2�tm����(�+��
ל�C@(Ya�zu�ӆ&�ʧF�M��1I��Ɓ���RW+��E�rm/���9:�mp���)� ��[�ܪ�ϒο�B�.�[������H�L�ZK����~�@�d�g�i]P(�t�Q�+��߸?������n�5KK����K��%W���-�/�Ih�%�!���u	2JΏ6#M�@�΁t-���?ƻ7Y�fL��icx3�'U[��XM�C�I�$5[�V��r�����{��-2��>=n�HlL:nƻ�j��G�6�/���5�Ia;����_D�nK��%��p�\P����ˤ^��%N�gL_7��^����K���DK�i��]�l'"c*
�[�!j    �&�j�&߾�ޛ���3ɞd���Hnkj���z��<���%Ø�d��|�Ay�g{����ٝK�7��s75��eH�ruN?�i���+�K��0�\ɪ�MO�ﴺ��p�X�9M2��8�O���Kon���}fP���a�nZX@�Xs��Adk:����Bs�=����^����R����$f%	"���g�S	����W�)���Ҹ�މ[ZE�۔ ���*�!?{��z��%�V�A�ӏ�� 
@����ZX$7��<�O.��1s�s`͜s�Fj��IR�L4��Vȅc�w��RWWTV���\����m�r|
`���V�H�b�r>��͔�^?��6V�L
��M̜aYj�AO.Z�ϩ�M�Rܦ�H�<%
�tAʣ?j��HW�X�0�t��c�oUM�>���+W���ߍAȺN���B�a��U�`Gi�'d�4sg�q>h*�F�|>�\����h1��B��L��ʇI+�r�Ꭱ�:}�&N�g�<׋���rs#���M�X:�^��ʥ^��Ҡʡ`
0���恼�
�*n
U��o%# ��q#V��%5�[^�;#��.|N�ҟ��f>e挐}�=�˜�B�Ƽ瀍�D���V�xZ��<���7���ͳ�'����N]<��=�B�&
`R�G2_��wu<�>�S~���9ȑ��?�~�Qv5"u��Nq�bdE�������C�	L@�/��@���e�w%:���,��3t~"9�S+�����"&��o���f#���* ��j~9��z"g+C��Y�s����aX�OE��+�#dU�y?�{�������|��n�X<���r� 1�S�L{�V�y�XP��R�5+�h�V7�l)S>VE��TB\�?1�qΓ���<^5�Pe�Q�L�8�~W�RbiO'5���y��l�#���9+�>���z?�����=s�9���93ן��F������Jqd&2� �2Ps�D'����|J^Doh(�F^��S6|�t6�ᦶ��\=jb�HM%2���#Mw w�s�Oܓe��QW\�ݖ	b�87'���KB"d�*~!n��~���K�+E���5��_'����zIkM�_8>�+���:݁�8�[�w�;�����u!識"yD�*�6�!y�L��/�
e�;��Şߋ%?!����:U��1��W
9Kt�'&��_X���I.�]�ƃk���f�M��*���l^�-�RB�w�k�]�����A�5A11��\����eS�A�짉�O> e�����73�@��~��e�n%�L��c��rv��ߥ���~6�����?g�	y.X�v�ە(�8�ڲ��$dM��B4����{~�ԧ�|��m\���jK���n�Qd�\�R7��U�v��+Giķ߱�o?�H�[��è'� kC�]��H5?'���Y�&�_S�����M\��V�|��Y��M�~�]�Y��M�P)P�8o7]�����d�Kޤ`�-���^�:iٗe�c0:����s����hZ��J]8�6�Q3�T"s�Cc�sƶ݉N��|oy֗��1N�&�ء+%�y����~��	����gJ��
��F[X~��H��L���L��Q(Ϗ��|�L��r�K��i>=���$�������V��Un��#'�Bu~��c>��h$2�����M���	ŝ��j��e��=.��yO��Z�4݆͗j��!_�d�gaf���->�U^	����;yi���e�0�G�(/҅���)�o+	����v7cn�Le/��Y�w٪愷T��t \����҆��ʳ~��TQ��`1U�W��Ѝ�~p�%i����>���Ydyb@��/�F�\|n$��[������}â����%cf1�#n:���|N�Ɉ6o��[n���X UX8�v8�/����9����CM����<%S3����"v�}�a���}e)=@�%逞ԛl�7SJ(�\�7�s��yfr��<3R],��_�\�f�����Уrɷtο����ɽ��Z��o��QGL�I��t��z])�x�i����̼�����J�9�X�;��q�
��u>�/�%�K���ڐ�[�3���84):���}l�8�㳕$����K���`�\���#�	�?�>���R��˪�,_|�K^%�8�*g�Tp�?{�t����5�J^�R��'J�pQ�h��������\����>_]8����~3�����H���C_��D5Xk7����+}�Fa�-Sϼ�����p;}��Ѓh��I�<g���ơ�k��=I;�W-ADfᘖW��&pwu]�	x��<kj��%N厑O��\;��(X��b�#����͸����&{;�C��m��Ǘ���B�cP�+	5�j+�ܥ9!�Q&����n0=�RS�ߏ��9"� ����h�YV���ėEjN�E���XrK@?�xs��R֣��we�G���~�����V|�m�2�"RbD3�vJ9&]�œ���w?f����OF���p���d�.{-�s�B��f"B%�\���bZ�b��&x����|1�o�n^Y
�D��a�s����-,�l[�'��w$Y�y���6����b@�c���FIS�����-�Ą���<���d���.ň�܁SJ3��}.�Q�e����Է׮ o�֬��"�D��08s:�$���L+�]^o$��f�F,�VJ�nE*�ifjWZ��2�?����ֿ0]�4�=[R8.��n|E4Z�]e~kv��>���m:�<����-C��&�ל��%�J{�c�	�H7J.^Q��5�&���5{���)O,���|�����	��W����	��*�^�?n{�K�K�ߊ�X�=39���P'k�q�j���yTׯ��e�SXL%�U�ϼ�Rߙ�&��[FK4B^�}�|з4�iAiL'�d[v+�t$���MR�{�p@ WI�aB��(�~Y�7�Q*�X�Ѕ0ː��p�ʗD�\���G����O��6@�ʧ,�'!3��u�����P��R�����E4OH�8�Rd�^@/&̬'y��?��� T�j�\a8��,\�Y�C�Ŗ����&�/�Od�n��_x�~/	��pikRi�-����t�ƜV��))Rp�"ˀ/o�Ý�FҎ�49��Eے�x������ᛷ���q�4.n�� ��t�/�6�s*Dll���f�3i;� Q{�$��O�9���.��<�Fr��Xh����¨�v��kr��ԭ�U<����[N�n��u�����$|��$�GK��(�_�w��Ѐ�8���rBU�)T�bu�J>�2h�^���?h�ѻ�x4	�F$r/�[W� �
��e�v�0�5�zP/RNz�f������g2ː2�(���%�Db[>�ۖ^�/Ek�9VI��C��>��0*X�kć�T�.�O���r�I�&`�=�?��k�k��.w��&��"q�O?L)#�XO�: �3tHq��r�n��>7!A`7v.T��]O?���O=�T���\T镭)>���u+=�T	�,d�&�Y�%Մ|Gh��?mH53���Q��m�6P����RU�9� �!��S=����b��O~��\/|�B����s�Rݩ�:�9�W�٭�$���H@��w��<[KͷUܞ"m^�� 㞶�@�"si8�qs8�&����*`3�n�h�G�!uA��ۻ8ժb��^���sW{���*ܑ��XL�� �xI���lZ=�Yj6�v�`>9�z��H:�_�n�߼�]^y�C^R���k*�+鎄-���HgB�@J��Vα���E[7~n�`F��tk������j��!�>���z��@릱�Q��>1�����I�1�ACr��*�ЁS�4��Ͱ��/`����w��!�0bA���'�I��r��X?��U	�N��僝j���o߳A�B�k��J�ĕCn�{��сN�e�M�,S����\�NR��\X��X��F��p��\#�<����R�.e���T-~>��I��g:�i�����I&A���VUQ��2&����k��j�!�)Do��vk­��T��    2��T;e4��11�B:M��YK�z*��{Iо�����J�Nq��A��¹��D-4�:y�iU�͟�(���ϝCK)��M��D��TP�<�k���.��i8�-^̠���;����\��xU{��:���*����������~���/)��dh���bZ��w5���r�\Tl���7͕R�zB:?R����#J�4�$�UQ�x>���nx���VYTg=7�-����`ː�>�U��i�Y1����9���F��.6��������ҍ{�2�@g!���H�![G�n�J�m!*4�I$�ű�Ƶ9�mr�e����d���>���7>;�5��ߊb�R3,s�HBۈ��h�m���B��<<?�J��U�C�i��H��8��D~��g}��D�l^�ѧ���#��pr�Ft�G�7Aq}�%���o��h;��$�U�iuL�WU±���۸���N�E��hU�6[��B�.%��B,�2�NR&�og��6���v+$�T���\yag�M�]�Zv*�z����5 c/�R~��rLyG�J�������(�{ʌ\�!]�B��*A�r���s�#� ��=����5���~�b�L�=��\���m��b��lfN�jԶ����Ԩ�\>��ܟR�ޙ�7������tN
��w�kK8��`�S���A�Miڎ�����È���۸��)�C�	,�dS�+;ދ��;��+���D�t�A8�����(��<n�>,h�p.����S�:�����U 	#��7�`�i.�JL]u��)KY�(��!p�#լɌ"?��I�ӄx��KhOs��7�E��`6>�F��>�cg�
=��E���s����FN�ܛ�|�#�����-m@7�#k���� �Q&�,�j�O�+!w-��>Yo��?(=��-9&dr�Ьc/��x	<+�������9�?d;Uv�����'��Y�M*���2=~���ͦ*R�0j)�T��.�VvC��ʁ��=��MDIa~�b�=<`N��7�P��歳O���z0�҉��������
-��R��U��/��ӉX�1I.v�D�
I���-?ё�M�^%O�D�o�'�n�K~5�0��|$ALo�YI3�'��ruA���s� @N97��6���|�\b�!��B�I�އ0�a杒��zJ��e\*��v���<L,�	(-gYm>�����"'�������X}~>��Ί��E^Z���%������9|ޗ���g�
\�-� ��TP@n[C:jȋ�l4�)ѝ����D�A��_8@'=+^��Oz�&��.\yloT��>;�VZN9����g��V��w0��SP~�T�t�ޠC�(M_%7��� ��|)�w��&��2�=-�/wvK
&���.ǃ��]�x��K��B?��AO�z�K.�N ���Q�,H�`�@�	?�]�3ڟ��nHEZ�/��S"��q���T�.Nz�u��A���7���?��#��l���WM�t�<U���tAgN`9xf&��h��@�jx@���lT�f��7���������&������u�0݁�o!dtK��rC��D��,3Xt�lc���V.o2B��ξ�f⭕x���-Γqs�$ô'�)��UAǹVM�vp�+8�W<)����X�YL�Ec�������BX+?�Z�-=e�[�h��y/J��-��݄Lɽ'UV��R�`=�������j�j��F�1����P
�����;�P8-�4�E�v��<�ӵîݿqbɧ�^²�#Lt��o92a�P^pݎ�?y:�O."�t�~P{ �)�X.���[���[��m�����P�ܰ�S�+B�Ii�֛����7�QZ����7���(�8W�[�_:wK���3Vm˟@���7�Q>��I�k�?�#�Od�	ld����?����J/�@��s�Sԧ"0-��<e�|р��n*\–N��Ǩ�X
	�a�W��az�3����|O���X���QE_���V�j^�e�"����_�����n,pI�ls7�eW�B�h�ᒯ6�1�T�Dh,�MM@ˎR楄��߾E.��B������w�j��NS-���WT�����@ڂe��IW�z^dS󶝠Ӏ!��M�4�K����ɺ��dt���Zo�8�/�.4��\��!���0�EY��`���<{>;�p[h:>�,f|X�n��K�-��6�����^!ц����׶��][���y��$uQ��x(��M��=c��<�#�������.)�)
�D��}%�m9D�o�{L�5���M�LaO0�����;�iVk��a~˿w��Aw�pS���Na��D�E�����q��M+ys�����$�rT��[�{Od��d}�y@­�o0�Of?Ob��?`@>9��z+u��nΉWI��r�3_���~?�?M��p~ �4���h�:P��V�z^p��f���'Kˉ~v����ԜόA�7H�Tqc�&R1� O�h�  ��%�!�&��תg#����������Ptl�L���zI`e3��v��z2�C����L��H&:��<7��J�i,��#��p��Ii�,�oc�I�h��E�b*���9k`��x2T�<��fjy>������	��z#�Ĺ�d�w.��1�L�j��������;��d��E^'_t�b{��Yiq��Mϻ�gJnJқ�y��!��C�N��6�"�;|Iyšv�<\�!��}S��,ˣiHų8�+��
�y���R�g;����Ԗ�-�=죽P9IN-�5Hϭ�/B��&K����v�,'2b!�PvM*��#@������˄�*��6�������1tU��g�|v���u�hL��^5�V}�&׾<N���B��ɀQ�"i�0�r�����u@:z���M�V.��l5q0پj3υ(]�mZ�d� �X�񇞇tl���9�h<��sG�^PRWy��^�z؆yf�!-\m���$��Ļ���2�$�A�5UU����,-B�M%w�ڑ&��o�1��Y�* SY&c'�{a��s�Ѓӯܦ�A�'t�91R#�e-:!��0�-��G��gC�R��G�}�*�ޖ���i��w0u���j^W3l� ��0�x�&^\��CR$/��֪<��e� fe3U�o5F�*�tK�2�T1K
��v�C{�![���7B�O�h>������Nm�2���.u�>R%ݺ�j�F
�/$�f
��4�`��ԫ�� �x�k ���t20��.��xC�����ch��QHң��eD4I=4�tf��|��H�{A��w��Az�>��*8������u�Wy��R�x~������p$��A+�fL�v���݁¬T.F���П*�������xx'��!�hv��L\K]0^�Q�H�R����E��-��e��8��?���S�H�<o�� �� L�~5:)�����_6
ɟÖ��$`)eJ��G���M��;��G�*�a���(A����C�}'U��DӅG����*Y�b��������s�%�8�\���Ȅ�D�'���5�
�f�0�/���Am��9ws�G��ߜd��w�1�s\��vTԤ�0��29���Z}�c�=ɴd�?������j����X����q����S����Tǳ��/��0�?�5�:�g��\N��҂��)8L��J\D�]B�U'Cf�L)���m`��0�Z�NETc�Jt��3�Yʡf1��HQm�Rm�zvt����?fy�)_��3�� ��3���Nʹ��|���#��M}��>(͙���~S8����\���oJT�K���Rq�D'��+�N$���2����A�aˮ)���0������#�:X!Cn@���I�X��c&�^��}`<Q 4Pt ]z���"��#S ��5�5�����!eD�>��D-��U��PS����査�)��FZ��>xL���B6�D|��cxŝ��Zd�B�W�G�	f/�͛�$	�<(�f����ߺJ�m# �k��1N]�kRSl�w	<�l�,�,�3��rxUڬ)�76Ē�Ұ���꜡J�|�U׷?w޳���
�@�?    �&_x�r]E���NP�Gޓ�o'=��+��F���I�I������]LESe\%���\�ߜV��)`�T<�H"J����z�
�+!�s�EI��[͘���ǝ���a��p�,A����S*ϥe�pu�i���{E�}N������,~�2�-�������M.������ǎ��T�[���D�h�m`i҄���7�Rx˃����+����M�O��p�q{��2���ͭ6wy����6����L�Qj�S���H���$׺	yf��%Oh���R1��|��@�#,ͮ�DA��%5Lm�B�����Մ��]{F�nJA�a����%���N6rɝ��Ku��^�ו гT%7"�}�Q�fc/$�����=��c����������.�r�qL�:�Y��5KZ�Tg��l��ͭĶ�9Ӆ%D�~�?���H��,�����s��ѴX�9^�3��pW��Jb�b�*$� ��Z���jr/���'㒚P���Sk"@	��bWg��$v�%���M�j���;�v.����1�0x�z�VM��39;�,.ʊW�ӷ��n�:���.S~���R �"�\]4=K7��,B�ܟ����M�a���� p�	���Τ�����t��S�V2�@vXg��m���"3��C�1�yd���h�{(�|ʌ��q��}���l������]���=rڴ�����IK��f"��H��#'G�3y�� H�S�V�[v� hi�m�������/2�)���*�'�w:!�9���>ۖ�o���`�n��?#�Bҍ&�Z���j��~"> ��@ˊ��!�~�L���,i��U������%�/p]t]��o~?�Z��~t{���;*C�О��;U���H�|�9�͔s��|>Ř������s'�B��$�_I�ϧr� �J�] ��5o}n4"t���u/~_'y��ۦ��I��4eފ�_V�	�}�Fi���y��x���%?H(�(�-oO�uy���c�oJ��~G���,~Y*��u�e}E�-�-}���	| i3U"1�����Ϻ�9p�A�H���*��_���6c&)�}R2U�͚(�l9YQ �u��fL)���F�!��BA��䐛.B�Mp�x��I�����&M��U���<6�c�@�9��S�z3wxg�E2� dj�܆��e�I�v؞_�k��"�7������ԑ�9�9�[O��XB�k�`l��jbz�g7��F��0�t�ּ�`R3>t�_����Hh���b����G�H��XY7�Ut݉�:����(���؜�g��s-�;��v̟s@p��%S� �;W4]�:.m~���/�=�K���uX��f���v�<�\,`�l˜���"ˬ�g�ꗍ3]_֢�T���!]]n{��B,�P�j=<˺��p)4/G_�$Z����O�D�4mxl���o�:'G�TL��$�ݍ�����S8B�o��P$��	���-}S����kn��#-����tȱc*�c<�h���7߄��ȳ)�
N��vwCXt�i���\6$�W�l!�'6��r����2_�T0ɄK�m$Z����W�ͽ&�٩6�+��񴢤*��w亡������ ӟ�ߔ|7��X6���%CD�1�{"��)�wVy A���G����/�[��t��><�V����rC���$����_�n�='� �+�X	1�Ia�� ��
;:�huyL�G�l��q�Fh�5C$����>w�:���'"�/|��k�Ğ�	�$�����C���eJ��_��ΡbG��7~���1������0_���Ql99@<����5���[N5�j�h1���.��ɓK�ⲝ�k2�
D��JK��xZZ�ܩ_�g*��'��^�f�gZϵiI�6���ܐ����c�_�
^��e5���Qpc��d`N� �Q��r%��4F��ۙMq8�����4�f�z�j34�d-���z���#��I���n�P�kz:C��z.�VM�G�l6r$������#�xp229aXΩdzΣ ګ7�K���j6����0�������������'�#���P��s,X)9�X�9H�y�r�1r=_�r�]��m��u��e�鴥���HF�(��wq�	6���X�r��Sof��5z Ի�"[��q(����N�DC�����m����-ݹ>ufKX���k?f��fu3cIw��Q�c��?ƚT��q����
@��{~�w/��2r�v����[U��p.x?��&c����y��Q�i�Kp�4wje�������g�V��7Miℚ�4���E)yg���E�ƣ�b����/�<�S�}���4� ;
�NCy�C/<�x)�r�	(�J�)����gǭP���x\WrKB�~u��d���r(ߏ�_{���R(]�T,)��s��  ӴJ��U�, gx�)W���*U݌�҈v��c-%#�]6}�ع葄��{O�Uv5jj�1�a�3T�fN@g�{���u!4}��D���;.��&�#Q=�j-��B���:��������~F�3� ��[�&P�y1���.	��Y/���5�c5"A���6�hI�+�v5�;:ߣ��'�碑<g��By�|���tz�d��8��hy*��nO�3��oN6t4K��<�?�h�]���7<�����~��Sϧ}Ѣ��72~N�9X"��m�CR`�:r2��B���n������� D��z\B�# ��?+% �M+��y$ܚG�����\7�ӆV`ip ૓����>��&l=7�ԭ�l�A�'��Z�0�߄�v�;d�߿z������~��|)��n�S:���_,�?������Ҁl	�I08û��ّ�x��;�����v<��m���*�~��_����G[����=	���P¹���f�4wXF_k��Ƞ	�t�-|���A=�k�k�}R>I����P$͏H�w�*u���0RK�\�Џ{�F�>w����r���a�B�Z5�p��m*M^2:���.�L"��SɦX?�y(�nbw��w0���K&>�9wc�'UCNXr�%�hI�4������q�KL��j�1��njfIv�B�e�>��L�?O�jӀn����c:�T�4���2;��S:�sL�:R��e8�a�S<��Wz�DP��uL�Y��0i}9��)l��uB���˚"-x
�a_Vh(�nB��=��e���j����Tbh/��zed�0��ڜ����:H9��&��Do��_����:]��v%f�B�|�˅q�ё�a`~]lSȍ �{^��f_����X�����j&��������������MGv�@2dz��m�jI+�+��4��v���2RʅxS��l��`i-����<4���|�ҁR�ΡĮn[���*�I��E�-����ǽ =��kO #g���I���2��dZ:�������{V{�Kd��ʙ����l9��l�W�v����u�-��ۖ���['���i�7�0{L��!�S�%�,�1����"�up��Y�%G�4U݃�,��ԥw ��~L�O� ���6$��w�%Ԛ^<W1_�\I���#f��t�YK6��WóoE�_'�ir�E�m���ˤ~��d�4���f�i���[�����o@9�I ����p��|�.Ý=7Q��K��c�僑L�/��� 8����>��;���y�������$�Q iMi�!�)-����a�����C�$�!���\�mr^s����׻��J�)i/3>z���i&sjoS�)f�b\��՘���������x�����T�C_����֫ڡR!�r� �)q�4��K��	Z?x�K�"�K���}N��9f#�SA8HX�r���{:��K�Ǒ��K����'��$����9!�4}*�k�#7r��֍�Ό�xϙ>�bT2���{`T��f���H�b������,(I�d�;:��ՏJ�~���H�勴��5�'K�d$�$��%�^{�E�	�9-�oފ��`q�R |ŖM<j.fj�b��l��|̤��z��8Ѷ�ڵ��ѶA���zy,˼�     �/8����*�i�M��&堦~1�r<�ES=�m<FRp&m��</�����4F�vwH��]�G�]��,h-:�؍gBk�!爥��K��lB�7oy��t��R�*�hU��<V�Jmo+��	����pf��(���L3������sM�O�Y���b�3Uu���'�S� ���.�@9�>�
V��㓖$�2$_v�_;�/��S��n,=�<ގ�bcI�����A��Q�/Ɓg*�X�P	�w��t���	лUu���8��V�~��W�kc��'��e�2vf�v�\pl���d��a��4L]�HR��)�>�ʿi9�kG`I��h��� ���3W;���e����M뚸��w���-qU�Y�&~�%�l��xS�;���=�(�-�J�Ъ{,�C.��T��R~�?m����?:_�м���kN�
���ݼ$���:l��p��1��X5`�ɓ{V�͛������U��
u����BR���E��F�m�,�Ѷx3���(h=�p�)��\yN���'*�G�d���9sؒ"�PO2�|-)�BS� ~����ߚ���l�^~���
���#�q(LO���Q��꥟\l�vBL�>{�P� ��͞ E�+�e���a7M]Е�!�V��3Ci$�Yܴ1-g
���J�L#�'�z .���X`��� �q����=���681�m��q�֠}@��a'A|���V��U(ѷسps#����Ġ������oS2T���lKTk 8��1Ծ���O���ʗƺ"a�ݯCtR'��C��s|j3���ڕ��w�77#&����s�i�:�46�&�C2j��i����<�_�6������oZ����}@43��4�H�N��M����Gw�r�Ή����CPk���fJ��
i�����Y,2���.�W�p�r�_ވ"#VZ':�z��V|^�����ț	��G�)Cs�S ��nho_�܅8֞����Y�M|��|�4R��NS�c:���,c-����_�彶$���fm>nt�}�S�a��������@&g�7�M��C>w�|z��V-�=�_�����$\����
3@P�����-b�,�ô��j�x!e��v�"ؐd�ÿ��˘��ʦtܥ\��y:[���)"eR&� �}h���ö�<?�4Vv�ǁ��eِ�Ne�������	.�~�E��O>?e��d�|����Bz���뉢|��������/����M��bM��%,|{�[
:��ܕc��ɳ�]��� �B��s�!��s�Pл�џ�{E�2nnL{5�SB��;���]Ŧg�NB7���^�������'s:R�����Q��%���K'��P�L;�hpn$G�pn�n����7r��ŉ;��H�9I}�h	�p��iy�7��+�
L�ęk+��a˓v�RА�P�^��Xm�KI�Ml�s<��v�G�]�W�������4�:i4Ί������Kl�>8-e$lk� N'E���:�<����d{��_�tWs�I䅵������`l���ׇY`j�#�"�}^����;�˩��@&�1��:��������3"H�	��@���(K��6:*��O2���!.�����X�xR"[�|�tb-z��M\J���\��ɘ;M}�$j��9Ѧt'��ⱽe�ݓèt�
�4|�5L��Ĺ�1�M���E�I�*x�.!!�ŀg�l�l&�i��k��$��sjm:���0��F�;���0&\F[�A�N'����ٞ�7�u=��Ɂ	/iZ�t�8�zG��>C�)"/^�^�~�b�b��Y�ݔ�+����qf�:$-v���@t�ob=v��⩎�fi�pq�-HV�`G(K״2�g����J�o|6Ѧ�4������a�fy�j���� ��rؾNo�逋0��һ獢E9 ���H�-_��n�L����3㼈�4��t$���e��w&c�Tj8ey����eQt=).smf̲;�4�Q�9����_S�ܕ��f��ƾП�"����yb�sT����Z��[�}�jt6yK>LW�?�ꑏZ.�9���>��a���4����y��D �t�@g���1�8ȕLRD�?�jF�O�п��S
�ӻ�̝R���[����6��y6���~��d�t�]�8+�R|$���>�\m�@���	�~����j���3w E���$K���ɔ���e�\^��v��p�����U�L�����ު#C������D��9���z����md1����>v+������#�����h0GyL�BTL���"(�OJ$�$�4qvkꭉX�?�������}txu)�Vr~~J��$z�+	n�
�2_�Yd↸N���w�EO���<�=�4���i�Z4N��~,S��\�	�+w���$g��M��w��O�����q�#ڏ	 �'���~����6M�2�0�<0�`kq}f*<�vn���1j�VS�\�]�X�٩�Q/��yǮC.	�Lg^�7��,[5��/�ߔR����mљdN��ԧ��d7"}UM�������2_0��*�d����[�oI�c��e��!mr�x��^X���6~�S���@���>n�2�>���fψ���2�Ή.L}B������=�)?��x���<p8���Fx�s���=�מ��'+f��Ķ��j6��|zȅ�v���f��x�	�墖����|�Qɱ{_y��;����1�5�.���������a�e����@�3_�l�T2��_��/Y��G6x4�2c��]KV�����ǿQs.����K3o ��l�Bs A���y&[�Z��̼�	.�;]��\n�μ���'g�D�1�&�N�^V��9�H�U)�)/��뤓E=�E��SO��������n�+!��'R8'�q�*s�N����<���M�i]S�&�Z\,���	�](�i�^��m�乂V��`[6�U��j_� ���C�K��R�z�\��1�]����8��/��"N���7͏�V�W�_�����42�`���x!��֩�S�+�}�Cٙh�6�5%\p��\2Nq'�R��5mJ�N�m@O;�s��@0��r��#9x������m�7�QlI�?9��R���3�k�����8�Ι�v9c>"hbyy]��u@p��s�F�Z���UHhd����PS���[J��哦�JelS��E��V�z����l�<�HB�� j��NaE�4�I�|\�S���X.'H*�^i���Ni�#xS���Jy̕����5;�k���,ᕴ Ը`!��d"^�w��Ή�/�OQ�t����^Wn�z���q�K�%NKzBi:<���[)���\t��A������I�S��A#ޤ-��� �����lz�T؃��s,- ��|��>b�Z�s�O:�)�}���\ _m襸La�-�爘����G��A��޺���B�����%Մ,���&q��Sd����(�j~Nl�W�˟����/��u�Ϣ,`���n{s�*2D�H7��g�3�U�P����|�)	"D,�ԺmC�a"�7f��?��:��Fg���x��$��ibr�m�N^�W���#������ߘ����
��fO��iN�����3�?~�sXVx��LZ��T�����z�	w�^�����*$�������zl�f�^���a|�Ox�����+a�WC�RI�u��4�H~7|+S�|؄V@�=E�Ԉ�p�G��M��R���N���A/�A��DKz�O��ם�%W�^�i���E�Rj4ط}7��ؚ�<ɕx�t�����)�=��rCӌ�ufj���m�Էx��XΤ���i	�
̡��g����3�/��R���O����&����{�]޹t%�z[q�a$����]���;&J���������$�^ ��m5��y^�t8$B��b��Ji�K��<?.��e�c:ټ�!�v���Ӭ��������^t�U[�eZ1�y�9�D����U�8�և�@	��OU���$P߈g��e���X����WmUw;:�4�%���ʥ=�E1�M|yR���)p����ѓr�~�&p��45r?��2o    3LM]�l�ws�y�ӽ%YO�.�Qǎで6�J�Y��w��^Re��daG��i?ݖ���a�GE3,������P�o�C:N�YD��Cf*�39x���/�5|���դ��c��<�2h�Iy��6q�[�R���ʷz��U���C�Ay��KQp�0;��w��[�4�zG��1��,q���+�-�}�Zf�Ȼ�T��\��*W9����NU���5|X��������{$5^z���I�8�$�r:�{��R���C�L�C�-$w��Y�5W8�Z��ً �Leն���4=!A���p���Tڧ�R�9��蛓��2m����SHr���x�߂�n�8�ax�+����q*�5ы��6Χ@����Å�4.��S������m׋�"I~�L�m%T��mϫ�����*5'fl�[�gU'M��ɬ<��෾g	��gj�R���m$C��9!'ա#?X���eL>K§nvlu�#�o#����ŗeOꂼq�,w	���/�դwSV,D1���,�:OAN�K"�Y
r�d{�� ��`����+�^��+%�˜�����M��>;�O�e�z�T�<̿�(s14A��>]���%��c����m��F+G&qo*���j5�0�*PR)b�%�fFT��_=��5�� &�t�K=5~%@���k�r�[���SU&EOt�<WC��^L��K����楧F:n
������C�`Pv�����өa���;�<W�cM}�H4�6�	�9�Ɗb��4�A-T�27�?�4��6��|:pF"����t�L������ؓ3�����($v�N�29�Z���*1���T7_Zd�CW��*�;G�)��Y8���� �CR0]= ���4h�J�fM�����+�Vક��=%2s�c𬥳\��+q�d '(�OSeh<�oҫ� � 䜯���ݩ��J�]ߓ��tS��cՉ�D1p߄����b2�r��}���/*7+�ab���$�ȒjKp�WJ^��s��<=��<��"ǘ}t_!eI w���� {�A~D2B���D4������@�þ���-���r}��&J�:l��|�o�g���^`�T}!�tҁA O.�)���m9������c2�j�*����@/��UϤ&�y6�O��;6Z���l�DI�k�![&�w�T����h�k�:�qL�����"MXe��[�q�n:ڠ�m�%d�PQ��~��f2���x�,���\kb,-�	>5�_�tҶߴ��?��ﶔ�g�س�|RkM�����������0�.�#�f�8�|��[or,*�����A����c�3�]���l��c�n6�.�A�s�΀hS6>�k�i��J	���򬑐�[جcJ$p��4���Y�J3ۮ�y��J]�=i�|�p����z{]C���=>}?��U$�d@yH���(JŬ�$�����̪��o������{ydb|����KM��/��ğ4�;��֠iɳ%>�7]S�V������#�����|(3�l堕r:m�Jče.����  {.5��	�5��(���VW�b@(��=���X�v�܉�&���0�NN&^�xe6����F�ч.d�f��c��L��p���kJ`�9j����c5��xL���#���(x�z��&1u1��%�S�W�;Z-d���Ռ29�����l�L��WR�N�e��G����d���~� �uRV��xqH�zڒ�{�o�,����p�J��PDc����RH0����ڈL�#�p�M�r�~8A�{�=O�+J_�s���9[l��戲!��\��ޤ��AH
1X"^4#b,e�=�R!6F@��R#	��W�k�K���2���O���ܝ��|�$s^%L�Z7����Z���3�1p�"��G^\�.� �z���Á��n'�f��i���v-4` ��Lv�k}i
�������դ��%Q�-	���#��};h;r�K�`-Ճ�ޙe��N��R�'j����ף�u��Y�NM�_B�iP��槛^��1W]���1��\�'60�Ԝ�!"W��JF�����jk�$L�aO��1��1�i����>�wz���l=
t��y]O��r.�Cy���	�$_����:��r~�Y�HG��D%�q�%F�g5�Sz6S�{����A�0'���EV���$��V}i��,��Z�F��d�����E�\�t�􏓁s����9�Xz����6�帟�I���`\�?�����L��Cio磚IC�}:���T�oE��$/ҋ�F��l���ka��>%9*�+Q.�#������#�n}���1�̯�u����c��˜�sSȁ"ւ���e�z�vH "Q���Ȁ�u�q�X�{)�)/}�x�W�?���C�j�ĳԽ/��H]F���@$�ל��v�Jy��7�#�>P�H�>���Ao"�S}�Ҭ���nHjJ3���9�W���s��N'�2� ���Xn>ϚR/�O��䊗�ƸmE�i�pؼ,�o���Hh�V�&"v0LWX�g�;dON���X	0��۔�G?(^$�1�#[� ��@@�3=Hm�����i�$_���)M�Ce��TI�p��C%�)��OQ��ʓ��8�,�x��gm���m�r�rv�9S���- �'w�z;�F:�k�Òr f�F�����_���6���@I�z�s+���y��7n�2o�	�"�d�P��m�e���z2���w�Q�������D �Y�t:���ux�2&D��=瑼+p�;u�U�$���!����ɬ�Ȝ��e�M{�y%�|�������˕Lv	�/��|ˑ���Acc�(㗓�z�y}���(e�������z~l�v�t���Z(v�M����0Zg���y�u6��>���t��%�ق���Ml^�qV�y�k���~#�wO�ޛ�|�������K�y�O����_����q����?���d����p/u��ZƩ�2�,��ɊL�vut�p�;�9��
�A��4i1{�t.	gr_^S�N�3���l|���w��2 h&��3r�{Jt)�����o�`�S[����HM|�RA�VbE��ڗO���++/�u�H]c��y\��^k��y
v��2�'����8r(j�6LoR˰�K���'�D�S<��q�v�U�PT0P�PEI����[�.��.T�^wW��ww�HҼT����*�^_6o��4v���� p����#=�����ض����<��ȎԕJ�$���I�i���Ky�QFe��xDs4q��D�*dK��_�D�}�ao��@+sC�8Lm�g�Qr`klWzR��5�O~D��v��\�<����MM��J.L~V�5ӊ����p�t�'Y���E�$��:)W�=�e��"�A����P����k#?s�����-�_ϔ%�����SŐ;��`�S�d��g@4凓���� ņg'�!w�t��R������{	��'�B%�m/*`�DL7ە��0ī��jmX��m��s�:��c����2%l��7j��S�RI��`l��c.����9���@�����X����w���N�u�	��;%x��%q���P�E�
�K#\'T��-֢���Y	J.�vr8,�MŞ�g�Q����M�������R�ٓw����,��r㩧X�u�4<�f��	��zb��p�H���Z�(/t��|�
����H�yfыK(V���H�0�䞆Ć�&D�f�W:��lS�Y^��1�M�OZ��m��U{��C� -�����^>�����i>	�d��״Ӈ���DY{��HEW��冦�k{���z���УI۶X��4���N�n��� Y��B�t	���&�����Q�ޅ��i}���6��ӰʹT|7����/?��.���v�a�43W��<��,O�3eDy��c�4[�?�䘑�Y�4��v�:���������4���L�,�u'4)�w�VC��6l�R��U�]pN:l�e�o5��>����Yt�_��Ο<�O��osŅ�o�F�hc�����y/�/'i�e�͙Ƃ���y��/����R~    �#��[sT��)YT췭�}��&Z��L*���/9�<��)��k��Dx��gfĔ>����{%��WV�/[�L�G(M�B�z�[��ŉo����3���u�A��n&bk�1۷��Т�i_�%y���O�� (��z�}��q����xI&���҃%�͎��������x*l�cb,վ��cn��u}�n�Z0���ga�:B��Y��ê8OZD��B�l��ˊ/�B����p���DJ��*��[�n��xġ��X���Rw:R�!_	"7u���G�q�9��}��k��ܗ?���\�!F�@�:j��$��
�'ѱ6R/�wo33c�0dɬ@PH~�ϒ|1_��(���paK@Y�ZCǰ��	��6g�~�3���#���������G�^"T.���C���n>��x)�r��ۚr���;�N��9m�'�y�=p����>i��}^�<��P�JxX�G}�w��,�A�ȹy�nk
�R�B�#}>X"؊5["ĉv��X��iR���<�.Fh܂s�XT�`9��@�u�U͵����o-I
i*��%�r�x�yT���%����ʏ��ɚm)�ƨc��^��^?R����'|�fWF�'\�[�6!cTP��U��d-��Cb	4�����)U'���1Oצ�J���)9�+�c�\�9��
��g:4V,翡����?���\(�?�g&pW�=1�I#�>z�'����S_,�r�\SB���������o��mal`c��AB�]�=c�Ŷ/�ZM�}A�4�^�RrN?�0X�4�o�+b<y��9�D��ҏe:�G���7���5zڵ���o������ɢ�%׮!UÑ���O%O� �66?D��b+Y�a��6�0���D�M"�r� �*�{#�_��$���/�a*�7C;><��ǥ|-���9�[	S���0|O��?�gYd�1ujzY&�;��tr���K o�S��d��Em�ک�s��h�jg�@C�� ��u����3�[�Ӷ�Kv���j�b��ւf���}J���Rf,��x�@��S}7&���Kq�����|���>Q�
�%�H=>��\����FF��t�`?4G<m�8�1/ "T��m�R�_���������py�i�٥�L��9�$�RZ�]��9KrJ��:<lϜg阢��7@�u����r��,!/͗��.�����o�(����)Xf*KL�ˍe^�J���!<(,�*�\iG�}M6��������ڳ2ͱKO�ץ�x=�_��8�_�lioy/��'�=ג	f���K޾�2Q�8`��g0�d�
��'�&:C�=t�."�s�@���r������@����娎(��	.�TK�ܕ�y�S���}"�_����mDO�xE�sy���1�M��.�4��'�1:�Ix~ *s9�n����y�9l�V>r��M�=�^���Z� ��AC� �7����$4��g!u�B�(y��j�\��/`Rn=�T
�V�'���|��mpC����3K�7��s� A�K�� ��^~��qsP)�*��V��PJ��1����f��C@�G�-4ds��_Q��6�7X�*]A|%�{���j�-,v?M(�۳ݨM����Z
�X&
�T���X���%���|
�h�|"P�x�7�+�*���0�d���x�L<*�)q���9H�K��[�u���1���٣a�A��*=� g;x��L:���U֙��C�]H�F����׬F����G�r���P"Z���.��}��?E���k�N]��~���P�2�&Ž`Q�?�~Qj&\�~����a��N���G��F��m���'.X�
�����e�-c�l�G�c"�z�v�����ʇ2
az�"�F ����=u�#���u�j�ʰ��Ӆ ���T��7/~�?^�-��ɇ�o����T {rX�װ�I��+�6�n�U0$Pu�V�%h�& ������q	�!?Nc�e�W���Y>
���<����P�J�HCe��&S���
�94O�TT�3�w�.�����#Yl�$6C�S�*w���P=��~�14
-oֳ�/�ю���SO��wPT��[7K�=yeD˾Fܯc�=Y���"��T�qLpZH�y(l��~`�6.Y&��[�âw7�3�*y��(��O��U5�����T��o8�n�M�Sj���*�*� ���*��H�9���RX�e wH�[!Tc�Fm��*yxm�mXΌ�#f�L�n�9��7� ��B�J;U ���|��U#�}����ۢes���
!�Cik�j��Ώ��lz�q��w|*x|���||W�L4 ���G���ގr��9�8��`��:&��x����~mm8�̥��­)�����_0���H�ڥtx�-�-tV��,���Sg��j�� �e"��U%-N���`
�G�q�p,=#�Q�9�����T���g| �V����Y�ɢ�jX��yaWH6���yPg�i��H��WSl�}����Q������Ǚ���/�R�����U��cr�OQr?�Mi�L3��r����g͓���Nx�v�ss41>�-��+5�4^q��u�w�ʇ�ΛZ��E���Yzi\Ǖ�O����p>,�k@ޕ�}�$�x���ut��fY���bp���1���� �u=nRM5}!���#�Bv��uNVt�>u�r�/��gm��O��� Ȁ�Y �L��ֈ]W@�cI�v�f��Tڻb�=��M�p[�3�8D���]��͊���\��@�:���)��>���|l�z��4@�8m�P|j;_o	��Ӑf艉\e����y�J�۳�ZKX��������Z�e�9z�BC��T� �ZH��|4�A6�`~�cr������<5`���� �	��a�>H��Z^��ioo˥�)D����Bˇ�LZ,��	�pb���{�L%^�Չf�U�<��9���QX�3�q�B6#�?���fi�J{:}��9��` oIEwSH�"4��O�_BC��^���*�:�C@�i	�X�o8�,)��3���V���V�w-:�X�� �30T.�����%��~�u$͜�d���M�wX�<�|�p��� �l9/�@,'�������·2	�����0-.(�k�5☒��hg����ވM���<�Iz�@$������*��h�:;<ք�a�f��u�ŋn�u�7��}��3Y��=�}��\U��qF��:��|}֞�E�p�}$��9���K�#��W��X���(��h�I���/�>xM�>f�� ُ~{��_��C����������᫧p
�3����`�k���{Hm������	��?d65͔4$��j��i����G壚h~:I�3T��jvG��yM��0f|�&ۯ]v˛�e�D�?y*�H���I[f-�S��[�3Sg��B~`��.�	c� �ہk�����[�:��x[=��nq-��x��x�}�=r.[W�eK���,3�NEټu�U���Q%��a�|�W��l5����ӛ�Z� ������X��^1W:UR����#�Y˺�/�-�r��7��d	�Y��Pc�{�����{�1��X(�����<É�i���X@�.�J��X��$ep���V"�� �8ر٢@D&{;�Oy�hcJ
���O�j�:��c����{�ˬ.������Ry����t�f�u���Tg�RCs�ҭ3W��ڃ�=Ҁ�ּ��a8_�64p�9�;a,�;�_w<i��ʪ�(N�L[�o= ��TT���hQ"*|�y}�:.�3G'w!�:�L�0�s	�G�d�)����Jk���(��z���C���װ:r���V?C��!�k�C�$�r[g�g�J�7ʾO��=��`.�]�p��}E�[�&E���a��~�B�.�z����vb�'+<����$V޶c����'����ٶ����D��c�� ��ホ̪��D-+�ϣ����C�sOz���>�'�-ou��:���xy}���x"7:��b_F��<%�yڭ�E*�8�r���<�״5�+3����焨����~*B�ЕW[k��z)�o��=�$u�?���㴗}IM�ȷ��n�����@T�F`���P��v���    ����_�mC���k}̡� Ҏ����'A�[����]NF��\w�t�E`;^����G��[!�	��d��Rm�%p+���F��S�e�-[O��������ұ�n�����_�N�+M4���${�u0��X�cs����EL�R�l;�E���nr��(���2�����O�d0�Sum��8��
��$�@e.���Ϯ�Ce��굒�����a�>��w( 6u!8nV��R�ukg���kٰ����t�[�Y%K�ꌅ> �����=Nm��8-�'��wȎ�U�h�3l|=�0�8�=����Q�N+ì�-AO���A�
-����#�j'>G��7m��Ɋ��T��A�Cj�5q�~7�e���@uK�mǺC�l)�>�ٙH�梱N�lq>9�;����}Q	��Gq�L����i'8�$�'K�����ZH]�����>�r�l��J0.A���J���Rg��&������'ԇ�A40��~�<,vG�����i�`���58��Ig�c��.ｔ l;���iB~�(9���FB8FW��|�l�wԸ�N��H�1;-�W�M!C��0��|y��-嚷��c	_���?�u'zZ$r�Z���Dpw8�"\c�%�4��?�!G| �<̑���N�������SmA�g�#-š@p�mk�#Ɉ!KRk+3\� I�g�H,U�J�����~m�zqʊ $1���4[�xs-�*�&w���m7���A��m��쓣�ܝ�&1�e!�aS�F���ڕ=X:�~�ò��脂����e#Z�Z��n��Kj��VշiG����
�����褝՟)��C��)8ٹ{�����jo�,W B���<����;��#N�ي/�"զ�C
��Pn��7�-��X���Ⱥ�(M���z���bw\>0��g����ǪaF��PN�.e�I�F̓`�LVi#��4Ĭ
!!�<z��8�/Y#׽K-���g��P��Y�*�����8�G�-�l%�����n%pe�ʗV�F�_!�L��?�\�[�=�w�?��[h�����iv�mOGHVw�g=n��-��zįH�3ޮ}��r��Z�U��	�����xP�>��e�)������X"�R9g��΍��`�U�6h Bm;�ŧj��ǉwWk����s"��\�f]�#���K�Ê�6��逺��SϔT ��[���P	i��i�1�عxj��T����ԟQ�b���R?���m���
V��V��^���n��K���e�QH����4˛ǎN4��a�R���������yH(0�mel��q�+��<�lBD>4y�0-<?�7�X?��4:+��q�=�ֶYo�*���Av�HR3�5��9܈�'VҐ�t6=р��s�ۦEl���&,*��D�,&t@�5��:�Z��D�����qM`��4Tq����g��l��Ȱ�[�tYF�r�v��,��b�
-����]�<���W0�)@�:�ɇ��F�U��v�NJ{�ы�C* <��z���{�'s(&�<q��G��)}��y�1��j7��s�C��%�U<���yZS5�S3�����O��[`�`��{��t[b�k�r-��_^��ɬS�GT�B��` �p�H��UFo����u5�m�_��y���Q-�ξ���l`��)zkQ:o��	p�������k>�#.^��]Y7�gG ��n¹�t��j2{��큧s�����V,�{��>�p��8����[ϱļ��6�gE�M��D]
dѬ��-8C|
���ͥ7{q��2n���R�j!OrC���%>�,�th\FX���D��|b��v�S�y���D��lutJ2�Nt-���rr7�YXV�����"�������Z�~x�K�m�í���sxT��Q��� K�:���%���:�S����I��x;w?���%�v�=)��Dˊ�X���SNg��ŝ��\��vU�s��S�,����Apk�:��{�3fO?컽D�IsGI�iJ4��en-=@τ�K�nt�֩?1��(ȼ�蜎�io����J�B#[X����V���W�5��r�`��_�	�Z#p/�:��&2�t����>bJ�)&M�üMd%�����O��`,���$P��S��Vt��u���z���u��/�$�#��[ �C�-�Ud���	�w��>o�m��>_vq�3K�uP�4dG�z��@�(V?��ah�6�	��c�W��&;�x��+�Y�{")�koH��1kRT�wHU���,g~>E�FQ�J�x�:���}8�$.�{X�P���h}�����d��$\e)MMm�\�����NvN>9D�X=�����|]J��bc�R��qߕֲ��i��5�AN�l�w�(��㈁C�V�(p��:V�^�0����6�C�PXN�rL�����O>>��P�mUR���ځG���0����\�p�;�M9�����x�ޟ ���'� ����:��ב��?,,W50n밙�Mw��Ęr��)G��a=:b_*P�E׈<%ٖ���8me�U	,;l��^i���e��<U8eM[Dx���w�'(��4�nj���˳P����e�d�$Pu\A��۷&����l�,�n5[�M>��X��yX`��ω(�Ԑ]� ~�C�rYc[�%ֺ�T�HU՚[�l�ׄ��a 2X���zS�\��u�P��_��|i�c��X�JD��a�����$[���0 ���8n�x��D���Y���"?*�<�����ʫ��xmĕ��g��k5fڭ��AbgIG�$DG��mm�F�	8��#��}��E��#��e�S=����ۄ|V�v����_����Ǘ��J�Y�9%×���a��y���XBV�A�ِ���A�����0��#��T��v3��͏]�T��W�~�������qᏱ�3	=��s�����+b�p
����%h?��L��L��������Vw�V�j	B�O\�nP�;vZ��|ɴ�]�$u�A},�R�e�'��0:�K`�8O����t�@ÖŖ5�V|jx	��@:��E-�9��a^	�e+��
7�C�bka���b�)kn%���\�4��ӳ:�=��k���%[=�	~�z����11�v�p�@�@8]��m#����d�A�{B�5��ٽ$��z��E4 �~�mo��LW�n �{�~Om��6Qg�c����!V'���8ښ�U�!��PR�na�]���T��E����n2hC8����\�g%�gr$��]��ygG���QY���ߢnB��s�=���|�W�z�����/��{������%x� \���E�H+t�A�K�����n�YK�Ŋ����쟖�Y_I�fx�ڀ$" 0��Yi�� ��<Ua�˼��O�.��	_jo���~~
�ԥ-��̙lg��~dS^��ydQ���J������:�Y����U���[Q[zz6{h_-`c^��ʡ��/9L��s�����Y�x�X*K������ȝ;P.�j$�mT����3��)H��`�(������!���IF7�q'�!���D4p��
��7����<߷Qj�ó��Xch��C���6�{{\i�}#y�2=�/3'���G��֑��2&8��v%��~W��кPK��B�KC[��v%^9�:���a�b�A��$N�	Ɛ�:���-�/O� �[x�Km#��D�J��v�g T�R=l9X�|�ނ��Y�e��_7cO�5w�4C�CA�[�5�t�Խ~<��K�c���=sX�8Q��xAVW�l��ҥ�6=�/K�W��ש���ͩ<:�ԫ~�E�r�?�(L�'�sR3ܣ�G�L�|�+�لf��f}u�%�c�����L#� �v��a�J�ϐN�����춒�Cۓ���x{Ub�s�.(���xt�#�},��ԕ� ���������W3L%U:ȓ<�[2ƣ?��_'��f*+P�c��-Þ�)�^��ݿ/�}y�
�y���]�OE��&V�x�ڍ�l��C�팗��0�䳚��xw�R]� )+n�k=T�°[���#$m<"Cv��c�j��+���p�ɟ8е���    �4�(B�QW&�}�k6 j���!օR���QJ����}m�������C�;�a��㉀��>��s��j��_�V'�Uq/AotP��������7 ����oX���J��}`����Bp飵xa1�
x�:Z�����k؏�q�ao�}�?]�����\vt*�H�j�%��J0�5y>5��� �x��H Q�Z6��7M����qOc��y�[yլϿ[)��9w|<>��5N���ǭ����_�G������G���kP�'qzP)���rօ%4�o(����>�_���Ӏueo��싍��h�v� z�P:S���g"sD�s�`W{I�6[�Q�x��
�5<s�JC,'=vR��(Q�x�\s4��_:Cg2:�N+!�XK\��}�UF:���k�Ԩ\G��q ��Aֱ�4�mt��s`�a�^Y��f�J����O�hOɧudm��;&j��I����
���q��j{��_B���/H)��Sχ�Ȭ^��vH�����d0�ET@<���]�vE{�r;��~)�鞂���ѭkРd�:>OVb��P�_�_��4����D`�3����*kL�#0`.��CUOR#i���m[���G�n��\�><\�IM��5W������(�`'�Sæ�h6Q,�!�%m�Fj��	ڪo�9��KT8N�������	����V�m-�+[������@�	�h�y%����A�BT(q�Iku����	�}�WV`Υu�J���Gŋ��l�*�n]�?����(��~��]p1�S�U������������MEEa>Yss;���¹��P��"� تf�\� �RC�N��S`�4�����9�S�Gg�P�a��~���O9<�}t�/�J�<�$�jS�t��YmD!�����O���2"�������D4z�m�ض}KC�:.��u�t_U�ͺ���J6�m�u�`���'�_�4]�=��G.Y��W��b����?���g��t!w��;3eM�i��:T��vKB����qjݢ��U�ԯ�p΃M`ݟ��b�&��A��]]�sW!z�9y
2���x��\[Yyۇ}+�f5n�}�\ܲi��f�*�D������*���t�!�{�6�Q�}'�?�tV4i��	B	�6,�[�m�&��1��+4��*�"z+���AM�Z�x��*��� �B0.:���8W@P]�m�=��
�^�� ��p�� V�������;�S �(}f��a���=�j9E�Tϥ����(�A�����^!�ҳ�Nn������~iGج�!r�DE��q�a⸑P�l����k�ɪ�*rm�o�uv��#iގ�z� \Gࠢx���?,'���G��'�lp��,����^ԭئi�:��2ڄ�������s��(��>�t�ܤ7 �٢|�}�`��t�^�ӑݎ?���eE^c-h~%����%7	��ZIU��jx	T���@an�z�1�K�~�]5#�Sӕr{f�seX\綱�`,"����
�f�#X���ѡ�ֻp�i^�ͅE\�V�)��RS'�^�f�+u`�պ��0q���!��U��CNz)��Ж�K$��a]�W� �%h֜'��Ԑ�M����g��WIW�Z��a����]{ -@H�!@c�I�!*�t��0�gS��.pΪL�-��`���
,�BU�&{�I�1�y��y���p)]��Vu(+�[Ia���4��b�������v��itB?Bײ�>=��9����g���*�K�c%@[�&a���à�sk��-����W�&(�Z���y`ӥG`0����:��x(������Cʪ�¦�����z�8�`+�V,���G~*���«���W=��8 �/Kq�%V���6�;�:hZ2��Q����"��ٲ�5��5�y_��%�ZW�+#h�f���cֻ�R��y=���:Y�=:�E�.��9�byt���Ds���z"���gP�52��d]���i(�ٜ]�C/B'5���-��?ov����s�j��e�f]az�4�VTŃ �c6�E
���х-Z�sj�k�1��=��jk|
�Ē�UfըY�f#[���a���c�*V'��gv�žq�r�D��X�����*��Dk����$Y�2�$�����@�^Z&�� �j��<�GN�3֞�xfD�����7�����9��J��Z+����ض��m�!�[C�C/���Bhﺊ5R[սR3�h�+��|t�v��/G��R��|���k���(q��K����EHw���V���(���
����*��bپ��5l8� ��S �v
�=C9�6=!BC"VQLJ�R7v@�j��X}
�����@��!F�����</�w	Ɯ�Wh��@�j;E5�$�)�t��X�O]���~C|���5�pY��.[m׳����gM��v�� ��H'�\G�|~ċo�&kTy�{��Ytpu'Z�qX�����l���ܪ����M7J?��<��
a ��C�V�-��Zv�-�*�'��P��u�Cr="�@������)a�}�\��
�>�~��'��ǵͷ�������vT���aӱ�!�?i�(���/Y��[�q"��39�f�j�_�aa`�&�@g�
�D���pg��ӫ��$I/�������S����uq"�M�o�@�����Ϟ"�]U�.2������u:;s�0��p�C���&!{�*Fv
 ��w�{^��#�A`:YJ���g�4N!;7;n]*���欺�gݚ>�;f��1?��`֩^|Sp��v;-d�Fs�8m�8�R�������_���Ȧ#����궣���v�{��|L���T�@�^��VgǛ��*0]�@;T�^o@�A�:�D��Iȹ���p ͨ�q�{ �.�ˁ�l�!W�LTo�`-���t�u��'�*
i��!��Ep�JWFC�e����'�7T���	^帬�;=��NV�,)z���*���݆N�dX�R⇵���hԢ��shsO��ԣ�������v�J;�ak)���@/�X@�Yfo��Q�3m7���|NU��v،LtL]�u��Z�\�Rق�'�f��y�EZ������'</o���?�^�8	R"X����a�����@�,qY��d<;x�hs#�� _��*�y�{���!����9�%��ϣy����^*�9B�4�����m:���(ʓ��wv�S�C���X��հ��?��x����PLB�������~:]��P����@%i�:�)V��'����PS��b��(u�b��Ss���<��̫5�@�X�i�3���Ö����"��:S�+\ݵ�x�� P�~Z��n1u���}j���?�.���_�Pz_��i���9�8�0шs�5?�c����#+{�;U��z��wׯ�#:s���JE�ݧ�t۔�����U��X¡�����T�����.�]|9+%�w�?���o}Ee�_��s�vhx����xٔ������O�A��5�_�B��t(�t@�����<]�=q�
��]���L���E����L����_�����13�wk ���x�3?0GQ	�0}_dƘ���^ӆ|�2��[�C�f`��S<	�����Y��{�a6�U�U�p�lI��=���Vy<M�͠�UW��AϮl�Q��v��dQ�	ށ)���i�!N���˰A�k��؃t���V���j��RȔ���<H竬Rp[��#��޽6�yV�L�B=�\|���k�����6v-s��t��ѐ�Y���)>,~���*`	�on�+���1�h��(��=� X%��#�y𡳶�<zg���+���s�ڼʲ+������宬��R��)��'y9uk��Q���ed�9����N�<*6�8�G>qpSln6�>r�3���p�ЎC\s���>U!�G'�h����K���n:;�0�r���l�8����һ����SY�p,HV����i�
ko�/��'�:���f8ρ'��ڋ^�1��~�뎮G8����o���f��+�m�YS��4��Y��~���|ox�[�3�+$=g64�������-���Ԟy�qୣ    �|�g� ��ӎ�py���ש{�u�����q���v!J��A�ĸ6�2>n,M�.0ģ��e�W�Sc�$j�-�
C��%�9#�����Z5�M���뜎뒱�,�EyY8��rM钰���ڹ<;d�ݻVL���*�egOK��aCV�|�%���ٟb=!N����1r�ۂ���-�,��q�?����&�$�D	�T��:�"X�k��<ȇ�'DFz-C~t�T��.a�_��Y�GV�'х��c��#�Sz#ɤ���>
�^5�%��0�h��*�.�>n�h�9n���Ql�*�.�1(t��.���qB@�+�eu�X�6�u��NW�7� U�=��o���rzF�ٶ��_��!�tW�sʵ�y�x��7N��e��<�xB�=���+z��h������!�<�tA1�b�6�x�#�=��#�<���1��b�5��3.|�޸y6��hz�/{�Y:Ξs��xl[�@�b�*�+)`'�.���H#�{�;N0|8�YjH�r��]b�Hy�5�#�.�� 8�>�A֌Sީ�}�B����jI�`!9Xo�<��{Q3x� 7;�٦	 Q�E#N����L�t�W$�'ٶ����%��Qo`��p]Nq��4�ې���n*U?cV�f��q��.��v���TY�F=����Q�a启mqt,�t2;�C�G�P������ڿ�j�j%�k#\�\4�4h��ZT@W�� ��ޔHS�X/�Q�=���%P�x�i����vB���8_X[��k:�o:�V�����n;l����ą����GM���u�/-n���9�,�/�ġeJ�זT[yBI'4�T��|]�S�J�O�y�X:ڴS�C(��!�������3%/�WHq�Қ�����H�K�r[���|��a��d��3��[Ba�Q
�Y���@�� [���v��z��]��#��I�\z��cy���="gLx6��jEbUs��0�$�){��8�� �ـ��Z%?3�����{Tm5�$��8����$z���Vn��@O�O��q5����P0��B�BS�ŏ�!ʸ��b*ӏ�JG�˜l[�Vh�%��:b�0�ǃ��U2���E̢�E�ޒ�J+9T�����l��q�!�%\����� �>��Ȼ=cd�� ���CS�O���ۨ�3�!��f2�<�zp�o>>��9^��X)���\�Q�W+��ֽ=u��ߪ������q �1cc/s �9T��ڷ�R�K�q��Y])��\��)������i䱍E�%(J���dx���!e�����A-�ut�0����Rq��d�z�3č,�Jd=m>ҴD��;�|�����Z��\�=����ƻխ�'Hb��P�t{ڶs_�j	�R�zzk?������S���P���W�B�`�ʣ�����g#9>����x��4�tO�t���ma�~��-.}(��*�A�D�OW����	�S%+���L��� MQ8�N3�7�e�FC�)���찢�TD��>�뫇�G�4?���s6���HׅO?}lYYp�Þ�V��))�< �x�2b�0��\o%��w_y��,T�j���[�>r�V�ߞD'X�|������W�4�W=���k"#d��*�C�Z���{���)�3 ���Y���q��i�6�_�nf�5l{��� H�,V��B���kR��)8��ؼ����/p4'�lok:�Ӓ�(<bڈ�0z�"�F�4�<z>���f����y�#����u�3��}Ё�Z���A��k\�M y�r�:��I��"۳9��L
IEH��2�D]�}��N�����r>m������7�e �Zf�ݔ-�Ub�QV�{KRA��d���|�a�R_6���b�8�ڳ�V�-���c:�O�ڐR�>Հ������.�^K%��_��=Ģ�̆ܳ�B�aTwp�y���<j+�q��}��s;�*x�U�=����)dc�2ۈ�R`�d����\1���=�D��.@�i�w�¾����ԑ��F-��XW����u�puu�_g�M�Ԭ�W��;v�l��&��0��i�UI�p�]��S(�;u��*T��<!��w�E�ؙ�fNM�W�Q�08�&���%/E�Uk�d�w�s�U�R�m�{��:-�ߎ���LQèef}���p�U$O-��@Y���S!��'�i}��p�+�o�J���׈A�h5vT�=��a�� �?�T:`�"�^�y:�٬©��ǌ�Z�Y��<����/Nc`g�����s���V��[�o,mye��}��枝󐃦���תt��J&^���\*,��cUJ�&��!~%�4��.�8Ǹ!8��`G�Ԩ���"�����ԗd�	�}V��X�Z/�Rh��hͣ[�4��n=@ن���3/���*ib� ��[ys'�[���9j�^�;�O��Y)Oӝ"�%ޏJ.�� "���(*n�=q逬����o�I�.��~^�{=��_u���
d:�i�����GG}�g;�t.�z���>�8[�]K>����K���dl��븩Ex^�m>�J ��F�#����_����)������E�&\�۷0����xo�|�Rי�t$V3.����s����ʫ�[��v�e�[�@e��qH��3�U���4C�������W(ִ�O;�fo8�F͞���Ѯ�K�AؚO��@m}�<O��p��;}���?͒�_���ɬ��޻�շ��,�'�O�$-���W�F����Ji0c��i�Ԗ�ǉG���s��ir]��3K�i ���̞X�E_�� t&�Yu�{I���YmX��*��q�G]Q�v���	\E�$����U�<%v��Vf�͂P���B�u��k���F���V�$p��J�6���Yty-�{����R������b�bq�ح%p����t�?U�� -��&8Z�/S����\0�E��0���^�n��_��
Q@���!r�=���j�mo�C�o���'� ��I���?�RUCV�y��G�)]��>1m;u�ڊxY�L�RoFڡY�����jA(SA4UfԳ�~�3�DeR����C{t`ɣ�:(�a�S�do��B���
w�?|�W��b�&8��Xv(�g�f�;.V�$Q�����a�L"��dM'`���g�h7���Z�J����b�z=#T�ʩk�"b]:6%����3��0��~]�՝�C	�<�PZiq���/�A��nL���
M ��yV`�E�V�S�;��x ���j�=,��
����}�p�&� \�<٩;k#\����7O;k���������/��v�ºMUF-l�ʱ�E��'�̳�- F�?W{�O�F�y^5�^���إK�R-����D�'h^f�c?C�t������bjtH�S�
W�򿆃0O��=��2���hر�����D��|��E�@]�Pn�T��ѭ�~x�>�w�Y�5�#4I�N�]g�?�K�D��U����W�5d�������q?�PCo#�^K	#����Fp�˰�����w3������?�&�*N��o���&C�H�8�'��	a������҃��ɍV*n�<���8ݨ)xwt�nN���B�
;=x^�=�h��B٩a�.uO�=��4�ZC����ՑV����،mV�@�\�A}���&�6ޢ+5F��q��X���s0���v�>��]��m6�*�xy����q�NsU���:�1*�Z�W��U���Jn9��vvh��� �P}]����w��<�C��|�׌xķ&Jd6��W���VZ)�R\�ؔg���6�`� t� O�W�}�9b/H�5X��7lo"�d�<�tߎa8Pv�)�����Ѷ���x��e�ݶM��_��j�� ����voC�Vo���g��C�19r�`��3]Ɍ�F�Ԥ�Π�ڎn͓�mmY*ґ�8}�ք9Ư�	ZK��L8��<�*�m�hj��d�E�k�7�S.mU�v��a�ŏ�NZ�W���p��|&�����윁����spy2<|�iz��ɜM�*r�C�La8���>��[���?�    �K�{DB{)�A�1n5	,ݮ٩�<�}�9�8�ց�� 2��T��ÉA��B6��i!�]Cb�jP_lx�BP�����$d��C��M�z�] ʡ�0�����:�Pޓ ��!Z}�1���|c��$}Tˤ${`w���(�4i�RE���-\�$([|��l�k�ܪ�GI�^��Oǿڽ����K��d�-گ�jw�A%0����E(��:}�_�����n��K�/����_}r���:����_�Dӿ�w�������������[�l����}��������K�/������_z���[�����}�/=����}��K��/������K�����_z�}�/=����}��o~�/=������K�/�ң�[���������_z�}�/=������K�/�ң�[���������_z�}����K��/������K�����_z�}�/=����}��K��/������K�����_z�}�J�^m���������g��^i��G?�bջj P~��U9kkz|~D/������}���u�+�����o_����?��?Wy��uQ|����3�����9��w���s����O~��������󟟹��^��+��=㝏��U���xu��7���'Y_�xt���]����?;��s�t�񓤦��7�<�����?��{��m�9ֵu��Gj�}=��UP�����u�Kw���}�'e.��e?��ɟ�iI��w���ͻ�%׷klp��_���jM���}��u�1��ȕ��L���_�(��~�B�M�9�M�����S���=u�0���&a&���c�=�\5����k������9�:Ƽ�s��_[�[n����Z��}�yk��i��wU����S��c����w^�_yg�W��[o�4Ӿ�Ӻ����w���v|�����+i�6�Q�ͷ��X��w��3_�8�­R��U��W;�9��8�������� %'�MT~�=���q����u������콸�ܐK���7�y�j`y��⚇����'7�����h��D�4$ٓ�={ռ+�[�y���w`t�[]�c�X�D�[d���s�OVϩ_/c�Q�g̾�"��v7����W;5����e��4y�����ӍS��G�я����O���B�Em_��yϛ]��|�U�<�r_�'7n�AN���H��Wln]݇z�;���I�����Xi�{�>s�׽ņ<b#f��b�޺ �:��fu������>oك�T�+�]w}<���t�H�6�tE��M��4��>YB��SgE2�^����ZNF�T�d#{����5�������=�&���'�q�|��檺΃~�%�O�l�"~�r�z��zҴtN�6�#����guw�r�$k���ז�*��VM%xڬ��HXl���#�=��X ��x�|~��I0"ʴ2j":�Lr�K#�;�B�.�8�]J�:ꙵ,�W�^��?�f9nƛ��%�����-[�9��[+�}��m�Q�(�]��%�ϯ�,��,�]�rmV ��P��:4��M����'��2���Wݙ�O��"�r��tPm�����ζsa�l�x�1����{�^�i"8�6��ަb+��<b�����=���a��q=��� ��)ZG���K X�޹�G�s���	ߧ]���y2wZ�*}���N��5��M��L0�}��g^�}8�S��Eko��-�S��Ջ��ɏ�`��	����3;! ��6���z��^��}�O";is6F]ܕw=|���l�&�ٲ͝��(hIcm������cy�,v�\K�i��E�;�̅l��xr�&M����S�וډܒ�%;`��G�򱠥�u����������}��6��R��8�N,��L]$I�D�����O���r��*7�ւ#�>����	P�׬��BbzW�|�2�ķeGXV�{ڧ><�.�������2�"�{�QB�F��>Ic�$Ez�#�!;i���"����g�eb"��0��{�XGs��K�ѿ�A."��y�.�n�'�k꬝+��լ�t9��l?�T�$^/�_g�F�y&�ghf�2��a�������!��K���0) 2�ٔ�RX �,��4�#�d(��X4�.����S�e":��>d�.V�)����F<�u�#�#�hhw�r;�~�V��vS����X\�L$?���Gt�$�����T���c��ÓE�`ղ��V�J ����%�������\��LzS�Kӝ�M���>���+�8eN"���X[a�,Yy/���W
�e�����u��Nҿ抉]:|�蕜���[בr�?�x|<�cO=Fz����àR ��~�-����A�	�L�	|{���En�4k+�������(���F�D��m���W���c�� F�>��Y�Ux~o8$5 "�6�h\	P#s<�֌����&A%���FI������}R>����)@�A��Oz�`�2�ȣX�h�5[[��4�2�nϼpt%	mwjz�l"�KHI�\@3A��'��8���q�W��>���;P�>��>,�$fk�Z��Օ|넩�6�w��������5���sA>�:Q�o��=���W�t���9�d���!a�T�#���.�;���,��zܸ�X3PlI�~�쉚����\ރ��������=`Z��wp�z��oBm}���O�",}�&��(,%�N������B>����j��}�����~�A2�i�����Ad���aK�4����-6֡ũ6�9�eُ�;3av�D��6�ғ;7�����Y�u���݌�w,p���_pu����=����_�˧�ʥ]��.7�r�ݻY�#'�<��d��qs.�^VM�;�M<lɷ}��w�Qux�a<lfo��CpU�)�1��Vp��k�,��ր�,H��	4�UnqA���{ӗq��I�\�K��w�7�(G>H���]������+zx<�)*�ͭ{��� �?��g��wX5ۙ��';�g)x��C�uh-)$��6��-�28TzR!��^�e����tK���Zӳ߉;����|�|�0���S5�K3[1~۟��C��d��u>��g��2�I��S1 HU�cnP=D��1*�̃-|��e��yM'����E�X�(E�-Xf��a;���񋾶r��Oh�Y���j[wC�bu�/
���<nwM�lјv�-��Ө�Ȱ�0u�������vN���aK
=v����G���t��ְ��-~� n�;�ǰ��F`=T���/�1b�������@��K�nrA=���X���㻗.���k<aO�Z���4r���M���M@��	H������$��,�7��C��p_,p���$����"�L�jy ��t�+�7O�����=�R�q������A"p7�ԣ;��h;	��<;t�mJ���V����^)��(����i����7۹�z��E�A�8�O%x�i��_KIZU��Y�u�����۲%o"U!h4���6ٕ���|_�o (�����A���>�^z�\�>��ڍMRRK<�CK�b�y�� "���Z)_p�rr�4jN��H/H��O��Z�����q�[b7�IЂśُ�`6:�C��
(A�U�D��n����C,��D4@r�ժ�@�XB�঳�.Yi�f�{C��;�_W�Q�Qz��O���ۼ�B3h��I�s�0�:6���v�``2���8��4�1%��,.6�Ă�|��$�]ӯ�?�p��j9���RI��6ޔP�!&��DE~��,���
闥H��Yq�]?�,\l֣-P�l9/6�S���|9:d5Mߓ�=_����O`c�հg8�6����<BI�`F�L�"[TAj7��ԇ&����Uh��Z�{�F�A7\&�P��VM\� ��Ο�Ȣ,�����[%d\��nAp��{g�7�39'����Z�"�m������l���������^H�>X@h%�`#\p%�c���Xm&rk�`�z��ٲg��k���!Z�l�
�q^Y_�r�    �{��4[F�q�]-��ȏ��B.�b�τ(	��q����P �VbN�7+�3�Dl^� #̲'T�6�_��Mu+�� �=g	�@�wzxK�?�	�0�zT�9��a�/�ai���=k�P	cD����;��]��-DT]�/X5�h[@���H�Er��z"L��Y��	�\ׄ~��|�k��a�8"ksZ����z]���^�5ĠS�N�L`�����|J�Ű��_�n�&7�;u��Q�IV���A���d%�2S%��y_k;Y$�󮤤�f�>�BXWA�-6��ޢΥ�<�kE��H��K,O��2���6:�X��ެ� _�9io27�}=�x�ý�����~�J�_�v�����L�Ղ����l��(�`�Q$c����2��K%<y��Q�JN����D��n����Hyى�7@��/����g��vu�h��_����U���/�`� �$��ۀ]��D��m]c���)D���� ���x��a�\��%�F��}����ɚ��<�*��!��Hsm�"���y�sa���,�g�y=�����%h6a���؇,�n�h^P?�.y��lť���dg{pP@�a��s�5q�G-��o	9lH MN܄�bC���<,��� �������#Y��X5�d�[�糀{�q��I�i�'o�䕬�i!��]�,rr�����
7f�WJz��elt6�(Mao�|}���
^Rإ�,kԈ�֘[wk0�����*��g�px��"H�<�N&x ������0�#�	�aI�RC6#=�@��y�k��>��B��� ��xO�? � bYA ��Toy=o�%�u$x���r+[�`���'���N���T���\Dǫ1�&zM���-�r{=VC c��&c�(O�#s+D�	��ij�+�J�G����Jr&q���$x�y�/{���`�*O1|#z�������懥o�9Y�܍�}Q��)��u�������O&*n0��ܡ�4�ߺKy D[����<��9gn6/5��t�o@;�����Zb�n�A~ľ�6XCŝ��s3	X�k�'��8�:7�/�v�A��1��ʒeku��DX�O�P
.��� ���<B|'�n_-��$������>�=��������	�'�D-˙�1��DLO"��7fQx����'�?�{�D�+�b������g;��g�ÛL8d�lbM.^b:w����s=�&�bI���b�����<_�
��U��^�H��c�,g ��V ��"���^di<��J��Ã?�"�d/�L�v@���j=�xp�X�V�c�uH��KxB�*+� B��<�QG�r��F��i
����� �>}�7���I"��7=���P�40Cu+�H¶>%fu����1,1� !]�v����|��o 1��PS�J\��[�*`%�ρ7f�����d�f���}&�7��+��5�n�k�b�y^�yO��&{-���!�,���e�/�u�ǲ4�#8a'�R�f�C}��,Jp�n��,G
yz�O�0X�o���}�~ȡ]���Ċ��\ٜ�O�ܬ` �kN.��.�|)a�pˁ�'��f�Ϩg3Ʋ�Q|^����C��+�p��l~k��k�
T9!��M�heOF�,aE�-�a�n�"��������Ny:��
*�z�;����¶�u9�G�w�G�c�1r<G��`8@��\�r?�^`�8��l���&"J5�\�"=�7<���8o�M�,
�"�Y��$0zN��m$�}��p]v������	� s ��x/;�2/P��Q���L�m�O�V��@��*WeE�po~hu�G�rTO�����	�-�^�A\�qi���eYk��ցg���<�ہ%�BD3l˘�b�D���,�σ<rrr�A@0ё�'kt�?^֟Y�$c�:�8_���)$�qM� �k	~e���;��H;$&E��"�f��*����/���^m���}<�W!iB ���m���Z�ֻ� 36z7���"����%��xy���%~��W���,xn�t�x���[i<�|<u�y��%�")��Crfg\�`-��S��|�`���n+���J�䋴�a9���M�&'`'�f߲1��<�"1Z����#З{9r�]���r� ���a�m�3��Y��tB/6h�w �=�HYW5����~߂�<o�"�����f����`�,c('xzAs ��JV���7@#y=�%S�w< �����v�c��Ճ=����z/��O�D��7!�c���Z�c-��<�Q/1RR<�z��e��UE ���o*�%�N��-n;���A�J�;��n�l�p�\���f ��F�yl�*�H�ZLǝH$�I��b�������$xy\߯J�p��׬��g5�ĭo�����'H�ɋD~�6��y_bB�H��Z�"��̹������ZD����76���,��:�;�����^�p�D��Ý��� ��@��=,�c��&���Ƣ�4�9�2[7%� �! ���B�:>�Ǔb��(@����� u0��.�n�}���asb��j^���S�/��2���s�@�g��$���l�k-�73s�<#H��4���4�e�+Y���,�=�9!d�'�DN����ae_�����Ĕ0QR<x��F�Z<'�ȀGr��gޤ����-T ƴ��4i����+�$H?o�o�L�(�Sw��m�W[�pR� Y:a���a�j�vx�V��Q�>��d5+^l*�	�k�RtĐ�a�N.&� ��<@p_�@j4 �|�j��լ�+ �5���0�W��Ff��r�&�6HE��2!���}�B���	�G�	��������͆�i�qqMދ{��A�z�BPe7���mٻC����A k,YXV���I�b�w��@."r�	��%rɒ�t؛w�o2,���R	Q��q��59+[�)2-֋�)�1�&�l�uÀ�Kk����"�, ��[��ֻ�[�#�:�� ��w�����%�p&OcY�S�@��t�t�#�n����gY��Q�_հH�h��t��@�3Z7�p/���{����:��;�#�FF�X��$�&�L�:|�l��o=3h�v{`�i|0_�HO��Nz|u�`_,r�Z��jW"���(� ȅ7�1 �v�D��Z 20:V��H4m{]Q��}���H��
O¶.K*�5��\�	�@7y��v6�4N_���#��'ۭl�����e_ (�}zbl��OHY�,[
ђh�3�+mr��(E:}�s��"�C`�3sK�����P-��w���M���uߋ�&dy����>/��1ءf?�� �/I�ݯi�]V�q��8���� �N����	�6n��&�O�,�9X{	��}���L ��x(	Kx	\N*�<x�|-����T|h�7��5V&��ޥ�d�l��������z>dgQ��!�?�6"l��6Pv���'0	xHP�\-<���_��r��
*�R�nގ�
�!dw_kl<�e�t�< ���`���D[)��`}�(hV�[�kx���R���G@!u�Jfh������ˠ���'N�� ����>p�
�ty�]w_���7v�����Jl��vK��v2�@��Z7���	���V �C�L:��c刜ӓ³���@ߗ�b-������e^ϪDs�#��t'�7�K��V��-�����9����<7y�_����6�Pj�n�!��lM�6a��c ɞ>��qsX�|Q��%����g-v*���HMo��r�J�A��=��{X��M�!���{̭!z�f?[j�H����擄�>����� 2��ή=q�'
~9��$���,��=]��` 	1����o�+��RX	��=ᙅ$ÆcߑI=@=�EL���S���.�P8H� ������O�u�\��L�;l̄k�,�b��X�<� ,��1Ac�y6h�)�6���    �3�H�����W�%��	�e�5c[z~Y�<��� z(����o�`zx|��@�6W�y�nL�}	1�����%�E�a�s���=<AB��s/[|l筶5��(�Cw�I�$Ž|�S%z`��~XM�e2�B\C���>�<(�#�@�D$ IΑ�7��`$��y��q,�=��<{،�n�ї�Cօ�����PVv	��Ć��.�瓇|u� C~� �_H��OlD�Ń�8�4��T
wY��o����:�L��Þ]2D�� (�P�֬�|����qp��a{���0����: F�\1��s�e��������i?x>R�������@�;�c�Y��%5�����@~������T����s��N�o3��	��ˆM������z?��Yɠ+�9�C��֪���S��(�}x�bU�%f���P�E*�廅���N$Is�8E�����$�0����>2��H詽��a&��?6W�\���Ӱ��BZ�{�o6�$�˒(vZއ�n�1����<\�P����MH����L&H��ԃߓ�uG^y�w�ރ �����=�7���_+;ܴ��d�o���zhS���B�6�Ȁ��^`t
�z���ٶaO�o���b�z�� �B�S� J{"J�yp�9��\�I �ڈǊ��� ��t��k5����ܞ�v�V�B�G���π�!��R��Ӡ`\ƛ�@���%s��tg�@���ӧ�өp!�� b�"vi���A�=��(J(�֛�yg�!�b�<�my3-	���l���t���A`���������q��c 5�<���F���&vB�t�_[�^ ������zm�\6M�T5��>Oa�%I��w�W=Yx��t�HÞx�}��ȗ:�"?+�M���b�����{��n5��- )[NQ����(�[����a��&����"Y��׻�Z����k��E�6�[!�Q{zw��+PBV�p&��	�����DNz��py�`�'��H�lc����� �`��A�c��s@���y:6h!�?r^j:�8�;��x��M��EBE=��V'a:����!w[�䙂+���v0 �S�ѻ籉�Ӟ7z���ə����*�����ՇdwD3D޼`����D�0�h(�n�dl�H[��ny��/����{	XՁ�i^౮mU�}L�[w��xG��e��^�$��V���L>dߵ��I�w���'��t���~��Y��R�d�'I�·[β.R�}�<\#��K{죸N^����yx�W������ (7Y��q�Ù�3l�=9���q�l�,]�~ێYM�,�>�YۼB��Q� �6$��<��=;���8jܦ6X5p+�g�ci�6+�Cfyt`3o\�nK�`���pQ����.���SP)v�>�2�6�g���`[~��B�O� q<{ w��?l�~-F�[�YT��'	-���˱�ro��L+�9ʬ��4l�	(�'9E@*�-�ᝥ���O�!�7��q�w�s�d�]��n�X.*W��9U~y;�K�`eZea1�Isn�����4�O�H��l���02P� fBʽ߶��
7 U�D�N������G���?��^�N�2BN�Q`Oq� �9�勈#d*��N��	I�IKlHV
��^�h��f@hb6�a[�6�ۼrkSpN<Y���Ê�[�BaO���j.��g�Y3囔�5v;�L�?�<�!��=O��ß�������x���V�����G��"�x����[
�r�S��/�\bY
���u:M��笹�!�~2
Og����}�h��[�T��h��۽Nt�,E�:ng�m'Ѱ����#��\���<������B���)! �����j��Og�ې'YS��M@��>x�w�
�y�=����=t'u8�n	.{�����TX������҉��|�T&��P6	��UM>#^���`�s�t \�-W�?���e��"K�$�ٹc����7)'�������I��6��X\���̼O�h`�P��ގ���\r��� e��{�vUYU�Y$��}.d�e��]�Q����;��Fέ�lEd�_�i,�W��ǁ��7�oܒ����=+�͞x=J�F�1?��_Μ��~$���ߏ���X�g���.5`V[f�B��Y�aD��$)�����$�����f��
���!GuD���wɨ<j)gg|6��S��KP����d�v�,	툣���(���q�N��������24��'�l�*d�A:�Q�ɑXv�)ݒ�z��b^=(~���
�K�?W������8Z�E��Vx˄�`+aF���%\�}1���C'\H{��ٰ Yʩ��^ȤV����X�,L�@�E��z�~_�ڊ���-)�Wǯ䦼W{ �LE\A-�x�Z5�~�%�g� =�'���wI���t���^Eq�Z ��x�ލ+C�%�.�sSV I���o4�t�����r?@(�� �$�hs��A�y��E�0��5D�)S?��Ŋy7*F#S��
��=F�@�
#U���R���q�Ǉ�DNG6��؍ �?�f�?�6�0?G����Φ��.^�p�K]H�Ү�,.r��*vP��Q���$�fe{�3I��ϋJC❤)p�7`B�)α��d�])��#��(0��:�${��Gr�͖P��F�&�7�N�,m�^|���SU��}��k��#��I�Hv�3	�'�q��l0v/�y��H�L��J��xJ>rE�3�b���G��ϫ\���W�([%�;'�`}�9">���nة����׮��3�'���I��>yAѼN5�)|�2�r���V�r�R_s�ߨ��j<B�b�J1���Jy;�� A�ш ���PmH�8%�A}YEL~-І�$z���� �HqC@'�H��R��#_	�6q��x9)sG�yEZ�^���C�B���(����� ��_։Љ�1W�ex��柶CMFx/�8]���2��1��0�3#�?�D���O�5I�4v�k򲝶�u����������,L{��r C�z�j���Q
�\�q������3~��;GQ�V͔�$2�.Sջ35���j:D�6R�'n�ZW,<�sE���b�v�\;*�YII}�V �
���X�z��쐭PϏ�F��o�MR��&���-����0��Il��O�R��2��T^x�k�rR9[�2���T$? m�W$��Vo�� %��
.:�%����'츂���@��-��g��X�h��G�\3I)R��;���AE��z&�M��|b��P7�G �?��
Yo�󌽵�*�_O�a�Dz�8W
|�3�d��g�7������Auuj�3����ǝKRl$i֡ŉ�e� �k�A1�e������m���"����PU�?G&U��d�))ʽ���*�ߩX�a@?�	��%ir�I���f��֯E��q��KG��aOr>9i���L�S�����x�r�՜�ٓ�/�Crl�HW���Z�G �Y���V4y�>���NZ$�akC��p*�NBR_�"�uL"�	f���>6bȇ����VB�]�8©DTrg	<el�[���5�MN׃1����%f@(D�N���px����*Q52�������9�2�@A\�`6gbD��,s�8�r�d@��<�%f��{{��/��x��9Y^�//��O�O�ץZ�K >�÷�w|I��o���K�-��B��h�T�� �P/�dZrL��,������&'�ֵ!�5G��Z>Tq+�k��>+y��zi "-��ށ��H��Ӎ�Lh�(���a����2�8�X��L"��-2�+ ��ud�/���:�
Hb��!<@;������%��-��+���@���d�H�;�,���.:v!��UgZ-Rг�1��6��N}���P�rGQ���$(�S�������'���[lM��Ӿ-h�ڧ�ˍ���Ku����H�$)*е��iP( ��dΙD�G�Ab��)� �    }��en��pntM��5\rj,�S^_  �]�=a�U3�y�z�8��x�/�=��?�e�����\2MHD��?n~N�"��.+��P\�i�༬ۏ%k&��G��zT��$�k�:qʝ�� %��_''<�DUj��u��'G��7��昂���� �~M��mp�j%�8�f��8'犃��F�M���%뚯�y�c���j`X�C��z�ʨپ_�A�!�I�)�uAP�E��H���ۯZ�H	M�}�
%�&N�/��Z���XXT�:FȢ��h��A�C�ᐅ+�?�"�(ڞ�k�&9ݩp;r�>���D&�>�}�D��|qx���k�[s��SCW�U{�T mrnrD�xx����fV��6"�S@n���#x��?S�ړ���B���>%�X�3?݈(%�H�6%�@m�����E��Rk���ٳ�0O�5�H0-���08or� �M�>�x����urʓ�t)��SP����c�UŞ��$P'D���$�(6�+]����q:�OY�T��fJ*0{OR��ݵ��Eԯo\��Tv��g!r�=�M�
*�HmI�'��D����h�H�,Qb��q��:��q��68������I��*�Պ�qz喑�t����q�'���K����}) ���7�e"<:<�e}AO���G���9�#�Lm@��/�Q��9�m�,��Շ|�gVYJV,�s����3!���8��M`'MRt_��"m~��|
*&���4�7�E�@�����[Ϸ�kTӤ�C�	<"`��(�qd�+%O�4����V	@Ճ1������9-)�v��OK��[����si���xN&���:Tȓ��3�7G�U��P9P6��g,Ȅk&}�d��?�\;���G��-G� ���ǩ�9V`�3?j�{E�X�P���) xp�s�����NZ��e��]<���
_:�/?$����Y�tIn����%�r.� ?D�-*�NTD`\W-��C]�R�E��w<�8o�w�}�*^Y��	�78^�Z�#�:F2&����I��K#�����'�P݀w��I��|�����T�/�؊e+�Kh�UOQLG�gLY��nW%V<Z����g���ٺ��Ȫ�mb9�� �����7T;bTC�ɨ�e"�C&��a�V���?SNe�?�C����/i�}Q��HAIu��'�JIY��E���"M�%��ZJ_��%%hXIaY�Ǯ�ţ�j޿+>�����H��1�C��Mr�F�?������&R<�pV*���kN�%F�"��8�YǚS�k��4
�w��@��u��?��R���w^�����r��͋xg§3p&����G�\�L@�"nLY]�����O�y�^պ���D������w`�w9*���Q��Y�~Ib�Z�'�O\�#��r,�y�g���:�ˌ��[�yp�����2�����Ī��^F�Ж�r��k��#׾������m��p@X�y�h���D�9���l�C�
���ã�8�Y�N1D�l�� "�¢�a��#]L��\Syu��1?�li�����k2F��x���G!��I���7��7+~6H�	���z�+�z=�k�� c�R`�l8�t��-e���� n�&�S�}�^��=.J�j���m�iJ�{�q\�����|ix!e�s�l%RC�yE\De�D���W�I��uui�?"��	e g��KPۺJHu4�����p�Ǆ�veC/�����o[�*W��?NH^�rg�\T���H���/�MD�ϱ� ���d�a�j��px��Q+I�%g�6�TĽ<��X��BF��l�xaZ����{�MA2Z$�kN7�V�Ej-�;SU����ԉ|=�k��P�z���K�3����R��G/A]�@�<�T��BQ�=�_�s�s_��r:���M��EF���c��O�,�m���D�e( Qgɨ�K��y$��狊��*u�j�y�<w�V\����d�v���{���ʸ��B^K�H�!�:'M&Mkm�s)5���h���m�D�,�l7��|1-���"�n̒.�||@�|Z3/������s�����]��@���A23�%1����+<o5�Pbl�=��R�S�,���,R�ֳ�7���Bˁ�(��z1�O��D�d��Sa�!q���*�&Gt�������'q~��c��k����.d8����Q��sN�ƛ�	�]��&��3]��Ф��RW����3O�K�]F�V �YHRZ�x@\˔\�����L�ɗ/h����,�D�G�=���\]�挦�IѢ����c>�dK��ev����-����]?��H�;�i�ծq�����s�
���}T:�����Y�k���������Z�:Ё�	�f��HLA^S��/��<�~�H}Km�N�tX[��08�s�C�gG�asj+Bbt�=�4SֳH	?�b!�ڃ,Z�S��j��7�h�!��%[�3i#2���EK(��P�Wߠ�qj6���r~4eI.W䪯"_H%-?Y8��S+EV2a�#r�ToI��\Z_K�(��y�Y"�	T����P?u]�FE�+�edr����A��s��52�Z�.�-3j�|'Oe��e��R`�U��r@�S�δ_u��zGkު*3+��q'l�PB���JU�fP�ƺ>|�>�M��<�#��wbIMf��ˉ�?Z.$El���u=� ����;JԘ���W;(m��f��H�s,���b��h����l|���r�_��r:!�j�R,Z�w��9��J�(h�r/Ơ�T��A�lu�@��W��-E�6Wǌ75U��k����n|�D1��n�[FW���}$=̩�ь��SIu�X	�=(�,�׀k]~���;��
@5�"�q��+ThkؒUA��XV��n{�Jq��Z��#��YшF��/�5i��l�!�P?8�&�����S+x�I����U]�]b��*E"������jP	�3���<��J��r�<�l{���m�H���(��C��5�x�;�,'�nK�l���~f�d�rZ�٩�yg�GJq�;7�a�{�-�C��F�%*�ϖݷ5���\�1M%�O#��y+�Ҝ�s�	�R��|8=.�D(���9���壃Jm�<R"_�� ��5[��l߈���+��B���ި���	��5MF����V�ve�+�r�^\&�����
�mdӐ�6� �*�m�I��N}C�֗_�?��@i��P�Sd����Z�{�{ -��U�W�@{���4H\�JQ���^���e���Z��QGv��w��$+�,�����q_�+]�K]���b1����)7�E�⟛|v�qh�<'g��n���[�G������:��F�G�o���?7L�[��{�����"�@n�R[�ېR8֣W��*t֡Dᚂ=�R�O�.e��xF����9,�o-��A,hw�I�h�ar�ȗIi�ۻ?�QU���ȇ�I�۔B%��Ӝp氢�5 ��#�jw�;K��c�<y�>��xte��~4U��D4�u�w���z�*?gY�&QN�Z�O3�����DL�uE�`����+ǫ�:������X2��Hl?���}�XMǸ��J�����������v�^Y�D���Д#�f�.�h�[t��.P N{��+��Q
��h�S��O�G�Z˫�~��&�G󐦠���zf�I��o�v)@4�wn�}i�1;է|4�<������*w�r�cD�;����Q�Ѱ�'ZoO�O 5M�CtLV����+��1d��M&u�r^��首�)�l�L��n��<�^W+��χL���]n�b�x�S�ʗo������f��xL�m��58!~0����^}�.�U?��v5G��tY������v$�19RPޓ���M[3�|w�<�W�����|37��Z���u1iY*��+\S�h1�H�W��ꮂ0#��tu)m�2]����_r߹�4*%"�+iP9��w�(�p]jY�<Ӣ��9���&��MEZ�FR�G��~�!\��o��\�V���WN�}#�%٫��R7    ��b��О���T9�W���Ϡ^9�HdjL�/�_�󖣌�4�W���Ly�΋��?�۹����ӌ�.�Ŭ��M��{�y��H�_e4jh��S�Ow�����el�7�J>�^DK�s���!�T�X�'u|в��,Qܫ�O�U����r�(�ԗ�*,��HdY,:��
2/Z�:��r�y�B2P�L�K~ ��Σ�(%�hP)�g���B
lܒ^�&(���n�"'8�\��u���l����'�0\m4�NS+��|�Ϲ��f�L���~)N��K��a�&،�U�^��=_/�q�6��0�md���Y��@Jt�O�����c]Y@�<�h�*[����t�dݍ�ɵ<3��:�_���IW���V�59`��q4F���>���G�;6����u=Ng�np�R�;���.{?<8ޯ\nYGG��g��h���_��.�vU"A\��3���/��h�@����������y��w�l*���|���S�e�kYK�����nn��t�U��-y���nk�O�|"�Ne_3�(��e.U�pH$���O"�����(�!�Q���vL�կf�G
?$�xZ�����ۑ�ZiWVj }H{��X�Ǥ)�R=�~�l���i��cM��~�`�mD��`ljI3�圸�����	�ڎ�ݛា&��Ġ{� m��w�|�K����5T�6�x��*����ڕ@@�Zs���J�@�'^E�}Us��=�C���I���5���K��F.��&�L�f�Nbw��<;n�MKSZ���H�Z:%�v����{u�G�]��@��/�Z�G��jp�]�䓐~!�m�=�����&쇿��'��T�&.�)*u&�v�yT�^k�e��Aq��������n��U)�	8R$4����y��i�_��$�B!d�8���S_�"�G����fTMΥ�i�[pru�)3�)r��!u��t)�	v��u<y����9��@���3�hF�u}��|�oQ��rl*7�:1�7M��݄6�J(<�A ���v��2�킭�1pU6�,h��Xbj�����H}�y]�!�G��$\W�7�YB.��CűrMJ�w����ίU���� .��J}��(n��עc�/,õ�ז�˦�ّ<�޸B�9�qK�kF�׸j9��ra? O�O,�g�	�����mԉ��\�cU��<��9X�N_��f��w�[�J�(\Ԃ�B����hy�G��`����s=;�}�*��p��P/��Pc�ss}�L�����T:�?�c����і5?R��{=8�L:z��H#w\o�7k��M�g�t�uZN������M�&����%�3�襬�,���'4���*_��u��S%�����%JI}����e�r����b$v��$�1�J��ܚDFE��PY�2C��M��BJJU2��+������E���̫��]/�~���?����>n�T	xU�>�R��i����.�k�W�?2�\�@��ʴhG��c����]g#U?�gt�C�CŲ����l�LHI@2n.IFzeE������O��ǅY���QYuw��t�U�,�_��s�ߕ����l'������H3����� ��\�iSB9�6���%���Y�:ׯ״V4y��E��6��@@�l�<��!�-}]�@j��T�� W&����u�A���h}'�]v�������u�d�,�0c��JD�-�+7o��-�!%�y?ݲ�R�C�)	W�T��vRPR�u����������V�
ڮ��VۧM�[4��e���WK\rc�Y�Q\V����$��ՈY�%��.��k;���>�����Y�]��}�GeCsD�W~��z�n�|.� � `N�'�=;L��ڮ��/.�t��/	�*������a �+�������p � s��8�{�2Ǯ{}��,�|u��dy�K���5PTj+	(�%�rd�Շ�ơh�A7B7��NT3S����[���x��
yf�� �|O��P��X�M��ɯ�nZ�9�*O�>��"��ئ�'���ݥ���P��.sL�MK#.�����q�h���-Ew��Dզ�3<'��6���)gV�T,r��{�X����Ǵ�MŇ������w3N�98����������p�܋H��^�T���Zub;���2�	��lE~U��)��P�^��3���)�Z��h$�C��l?��%~NF���W:��G��ܕz�/���Mʵ?�L��vaQM���&�ڞT��5��
�-��z��!_�3WP�*�BlU�d�k���dl������'�(�<���ُ��yy.�SH4%��y8��)mR��#�:Ht))\��*5\�w.��ˠH�j~�E��<�1n|뮐���*_��ד͆�k�;��!5)BJ]����6���K���䲤�}gB%a���A'g�D��2|>�"��F:�?Ν.��c&t_U��T�1�V:������;��+W�\�T]�\�!�1�T���d߿)-'�j��UQ�	��w��_�U���>>�C ��C9�O�F���%�j:A�yW�ث��+��.����|��ZH��h�=m��\�#�R�x�� :�;��1J��Z�s�p�Uk�k�<���&д)L!��'ݻ�s g���N)i��"�Y���?����Lw�C�<F����L`���9��](:>��߈\��p�Z[I�}d
Tn����P/hU=Z���{^*�y�b
��6�6�)��&'���`AYݤ�ϓ�e�u �郫�J��f��x���'�h��_��x��;Q�˒�{��rO5IPo[n̖�5&�[)��m�˫�/�m+�^_o^�f�_����؃D�Uo��E<P����q��KjAǫ~�%w7�hM@I�`�=�c��(�;&mk �:����})`������sÝ���%����p�����P���k�u�}MM�h���؜]��Zc���e?:�*u����-
�|%GI����]8>]�ܦ��7��&Եm���5�P�r��$�TAu��<Z��GA~�gҝ���oF9�t�I��k65%�z,'�==w�.'
[�"\��u�w�'|Q�)뢂�^hH_�AX���[��QK�pl��VҾ_'0��K��t�J�jOZu�Ύ�\�X]b��JjSm���>Z�H�W���4]�I��� a��|��>� ��4�rGN�נ�j��޺MP��Bvi�S�op�h$E�D�I�m��]��Y;�	��2��\������*RI����Bs��	�8��*%ͣҹmM�g�(�*�>�����������"���T�U�O���I]�鮠 ��qy��;⊌�T�]<���̣Yw���m�?���ѽY7A�o���wn�%���z�}�>6G@��C K��}.����8���C�ץrn_�E�����V���F��u�塏���	�^�D��|���Z��&�#D��3K�{bQ	CaK΍F�e�󘗯���i��0ne�WLn�K�x�rur�-)�I����E(Ԉv���QA\2�������,j��)�BT�`s�TD�)H㳖�p�.Z�E��v����^�VH�[�{{�{K���4�p�T�t+��ӽ����$�85�F��y���W���s�!�E�<�Tv���NU�d��DP�3E=�5�Օ�Z���4"�j¹\�����;⤭W�WR^Sqa?���٣C7���-A ���c�'���2Zl�9͆zuISt��9�RU�d����y�
߰���gc��a���!D�hAĻ$�HZ�oh��� ��S�)>[~@�3ۂ�.��U+}9n�3h��.TS�g��㾮��\�ͭvz2�%���߻z�8˶uj�Ov���0y�>7h֮��]�H�������s N,��q��[%��y� ��OF�I*�բ</�"�e-U����-�L��=�z�w�?�ہGc���a(](��0���_#�\�W��)݄�d�h$�����5�k�/'v�F����3E8�U���{�=~�������%Q��ָn�Inaɮ�%�ٟ���D�4'_H�)�Ytm^�����1�+1�u��T�:�[��`n���i���;�����X`s�����K�� V  ���u�PZE�Z݉�%����V/(%g�S�J<cNcQ<��/m�ߤ�����[�H���-לV���T��!��zXK��΅�+=!Q��[��`�r-/`���E�Fu�����T�b��j(���'?��8�5�Eލ�d�>��m5��V��-���$}�=�ь!�2�*Fx���WscԐ��ƴ�T?�3�~���BC�
BHx�z<�������7������${��ڥ�:��|���I��fY]�z���v�븇��Z��A��J)(�`ى!�%�h�bۛ\�����dU��{�[Ĕ��+��~��s1��,�|�"�<ɞ���C$�^�n3�4�U癳<�C>�`=J��멽�jȾ�hn���I�&�H:7���s2,L�}Y�[�򺼿���$��>U7��1��U�6�1htx�������؜U�^ҏP�)*.#V�t|C��'HH��cQ�����Ń䷭+�vݧ�%a�����;��5��r�1��2��1�#<2� �ݡ��B��Jr�WR�I�g|N�n�~�gC_�A΍�w��ng%H� ��SPZ�Th�PaI|��f��6۬�Ijl�ŭ;��j_��v<3 ��[����0��otݽZ���\�}{��Fd�Ԯuk[����uU4��F�p�٠�6Q�7�l���8�{>��i%nr��bH�)2��{�[�R�t����Y�D}��_ԣ�2&,;��Im���zw�ӳ� �5Ik{�.�Z�7^e��<\E����2C���n�7�)��̟AvuiĴ�~jT;��<����O�p�&E��"˞:�����s��u@�������_��������������u��         E   x�32�4�06274441��"w�Լ���Ԓ�L����k�Ks�RB���JR�b���� m5            x�3�LL��̃�9%��%�1~\1z\\\ �P�     