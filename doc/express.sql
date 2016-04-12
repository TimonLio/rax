--
-- PostgreSQL database dump
--

-- Started on 2009-05-04 20:22:43

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1492 (class 1259 OID 17451)
-- Dependencies: 1776 1777 6
-- Name: express; Type: TABLE; Schema: public; Owner: erp; Tablespace: 
--

CREATE TABLE express (
    id integer NOT NULL,
    ex_name character varying(256) NOT NULL,
    phone character varying(64) NOT NULL,
    settle_date date NOT NULL,
    tpl_size_x integer NOT NULL,
    tpl_size_y integer NOT NULL,
    tpl_date_x integer NOT NULL,
    tpl_date_y integer NOT NULL,
    tpl_date_fmt character varying(32) NOT NULL,
    tpl_src_name_x integer NOT NULL,
    tpl_src_name_y integer NOT NULL,
    tpl_src_phone_x integer NOT NULL,
    tpl_src_phone_y integer NOT NULL,
    tpl_src_zip_x integer NOT NULL,
    tpl_src_zip_y integer NOT NULL,
    tpl_src_address_lb_x integer NOT NULL,
    tpl_src_address_lb_y integer NOT NULL,
    tpl_src_address_rt_x integer NOT NULL,
    tpl_src_address_rt_y integer NOT NULL,
    tpl_dst_name_x integer NOT NULL,
    tpl_dst_name_y integer NOT NULL,
    tpl_dst_phone1_x integer NOT NULL,
    tpl_dst_phone1_y integer NOT NULL,
    tpl_dst_phone2_x integer NOT NULL,
    tpl_dst_phone2_y integer NOT NULL,
    tpl_dst_zip_x integer NOT NULL,
    tpl_dst_zip_y integer NOT NULL,
    tpl_dst_address_lb_x integer NOT NULL,
    tpl_dst_address_lb_y integer NOT NULL,
    tpl_dst_address_rt_x integer NOT NULL,
    tpl_dst_address_rt_y integer NOT NULL,
    tpl_src_address_indent integer DEFAULT 0 NOT NULL,
    tpl_dst_address_indent integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.express OWNER TO erp;

--
-- TOC entry 1493 (class 1259 OID 17456)
-- Dependencies: 6 1492
-- Name: express_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE express_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.express_id_seq OWNER TO erp;

--
-- TOC entry 1784 (class 0 OID 0)
-- Dependencies: 1493
-- Name: express_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: erp
--

ALTER SEQUENCE express_id_seq OWNED BY express.id;


--
-- TOC entry 1785 (class 0 OID 0)
-- Dependencies: 1493
-- Name: express_id_seq; Type: SEQUENCE SET; Schema: public; Owner: erp
--

SELECT pg_catalog.setval('express_id_seq', 1, true);


--
-- TOC entry 1778 (class 2604 OID 17508)
-- Dependencies: 1493 1492
-- Name: id; Type: DEFAULT; Schema: public; Owner: erp
--

ALTER TABLE express ALTER COLUMN id SET DEFAULT nextval('express_id_seq'::regclass);


--
-- TOC entry 1781 (class 0 OID 17451)
-- Dependencies: 1492
-- Data for Name: express; Type: TABLE DATA; Schema: public; Owner: erp
--

COPY express (id, ex_name, phone, settle_date, tpl_size_x, tpl_size_y, tpl_date_x, tpl_date_y, tpl_date_fmt, tpl_src_name_x, tpl_src_name_y, tpl_src_phone_x, tpl_src_phone_y, tpl_src_zip_x, tpl_src_zip_y, tpl_src_address_lb_x, tpl_src_address_lb_y, tpl_src_address_rt_x, tpl_src_address_rt_y, tpl_dst_name_x, tpl_dst_name_y, tpl_dst_phone1_x, tpl_dst_phone1_y, tpl_dst_phone2_x, tpl_dst_phone2_y, tpl_dst_zip_x, tpl_dst_zip_y, tpl_dst_address_lb_x, tpl_dst_address_lb_y, tpl_dst_address_rt_x, tpl_dst_address_rt_y, tpl_src_address_indent, tpl_dst_address_indent) FROM stdin;
1	申通	联系电话	1970-01-01	232	127	125	45	yy MM dd	37	63	38	55	72	55	34	71	94	90	117	63	117	55	117	50	157	55	115	71	174	90	0	0
4	天天	联系电话	2009-02-01	232	127	25	19	yyyy-MM-dd	85	65	44	65	80	97	40	72	108	95	126	92	139	65	170	65	170	93	130	72	201	91	0	0
2	圆通	联系电话	1970-01-01	232	127	24	18	yy     MM     dd	34	98	40	63	83	63	30	70	107	97	128	93	175	93	175	87	185	63	112	63	205	76	0	0
3	中通	联系电话	2008-12-31	232	127	39	20	MM    dd	42	90	42	56	85	56	40	66	109	89	132	90	132	56	132	61	175	56	129	66	200	89	0	0
5	顺丰	联系电话	1970-01-01	216	140	163	17	MM    dd	78	102	54	73	0	0	15	80	97	102	78	62	60	32	60	37	0	0	15	40	97	62	3	3
6	CCES	联系电话	1970-01-01	232	127	0	0		41	62	38	51	0	0	35	68	97	81	123	62	110	51	110	56	0	0	115	68	178	87	0	0
7	彪记	联系电话	1970-01-01	243	102	150	68	dd-MM-yyyy	51	20	22	25	0	0	15	30	93	61	113	20	100	27	100	22	0	0	94	38	171	61	0	0
8	宅急送	联系电话	1970-01-01	232	140	30	15	MM-dd	42	100	30	70	0	0	32	75	110	98	140	100	125	70	180	70	0	0	127	75	203	98	0	0
9	EMS	联系电话	1970-01-01	231	127	75	95	yyyy-MM-dd	36	89	80	89	85	58	20	62	108	78	130	89	175	89	175	84	178	58	113	62	208	78	5	5
-1	无		1970-01-01	232	127	0	0	yyyy-MM-dd	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
99	其他		1970-01-01	232	127	0	0	yyyy-MM-dd	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
0	韵达	联系电话	2009-03-15	232	127	23	23	yy    MM    dd	32	99	40	63	80	63	20	67	106	92	122	63	175	99	175	93	180	63	110	67	201	92	3	3
\.


--
-- TOC entry 1780 (class 2606 OID 17516)
-- Dependencies: 1492 1492
-- Name: express_p_key; Type: CONSTRAINT; Schema: public; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY express
    ADD CONSTRAINT express_p_key PRIMARY KEY (id);


-- Completed on 2009-05-04 20:22:43

--
-- PostgreSQL database dump complete
--

