--
-- PostgreSQL database dump
--

-- Started on 2009-05-04 20:24:07

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1505 (class 1259 OID 17501)
-- Dependencies: 6
-- Name: ware_category; Type: TABLE; Schema: public; Owner: erp; Tablespace: 
--

CREATE TABLE ware_category (
    id integer NOT NULL,
    name character varying(64) NOT NULL
);


ALTER TABLE public.ware_category OWNER TO erp;

--
-- TOC entry 1506 (class 1259 OID 17504)
-- Dependencies: 1505 6
-- Name: ware_category_id_seq; Type: SEQUENCE; Schema: public; Owner: erp
--

CREATE SEQUENCE ware_category_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.ware_category_id_seq OWNER TO erp;

--
-- TOC entry 1781 (class 0 OID 0)
-- Dependencies: 1506
-- Name: ware_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: erp
--

ALTER SEQUENCE ware_category_id_seq OWNED BY ware_category.id;


--
-- TOC entry 1776 (class 2604 OID 17514)
-- Dependencies: 1506 1505
-- Name: id; Type: DEFAULT; Schema: public; Owner: erp
--

ALTER TABLE ware_category ALTER COLUMN id SET DEFAULT nextval('ware_category_id_seq'::regclass);


--
-- TOC entry 1778 (class 2606 OID 17530)
-- Dependencies: 1505 1505
-- Name: ware_category_p_key; Type: CONSTRAINT; Schema: public; Owner: erp; Tablespace: 
--

ALTER TABLE ONLY ware_category
    ADD CONSTRAINT ware_category_p_key PRIMARY KEY (id);


-- Completed on 2009-05-04 20:24:07

--
-- PostgreSQL database dump complete
--

